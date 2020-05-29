/*******************************************************************************
 * Copyright (c) 2020 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.format;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedDiagramContentDuplicationSwitch;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A factory for accessing EObject mapping-based format/layout/style application. Use
 * MappingBasedSequenceDiagramFormatManagerFactory instead for sequence diagrams. Calls to the apply methods shall be
 * embedded in a {@link Command}.
 * 
 * @author adieumegard
 */
public class MappingBasedSiriusFormatManagerFactory {

    /**
     * The singleton INSTANCE.
     */
    protected static final MappingBasedSiriusFormatManagerFactory INSTANCE = new MappingBasedSiriusFormatManagerFactory();

    /**
     * The Content duplication switch storing the transformation map.
     */
    protected MappingBasedDiagramContentDuplicationSwitch diagramContentDuplicationSwitch;

    /**
     * gives access to the singleton instance of <code>MappingBasedSiriusFormatManagerFactory</code>.
     * 
     * @return the singleton instance
     */
    public static MappingBasedSiriusFormatManagerFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Apply format on {@code targetDiagram} based on the {@code sourceDiagram}. Format data are applied only for
     * diagram elements whose semantic object is provided in the {@code correspondenceMap}.
     * 
     * @param sourceSession
     *            The {@link Session} for the source diagram
     * @param sourceDiagram
     *            The source diagram
     * @param correspondenceMap
     *            The mapping function between source diagram elements and target diagram elements
     * @param targetSession
     *            The {@link Session} for the target diagram
     * @param targetDiagram
     *            The target diagram
     * @param copyNotes
     *            Whether or not to copy source diagram notes to target diagram
     * @return The target diagram.
     */
    public DDiagram applyFormatOnDiagram(Session sourceSession, DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, DDiagram targetDiagram,
            boolean copyNotes) {

        // Check application correction
        checkApplyFormatOnDiagramCallCorrection(sourceDiagram, correspondenceMap, targetDiagram);

        Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(sourceSession, sourceDiagram);
        Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(targetSession, targetDiagram);

        if (!sourceDiagramEditParts.isEmpty() && !targetDiagramEditParts.isEmpty()) {
            synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram, targetDiagramEditParts.stream().findFirst().get());
            // Apply format according to map
            applyFormatOnDiagram(sourceDiagramEditParts.stream().findFirst().get(), correspondenceMap, targetDiagramEditParts.stream().findFirst().get());

            synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram, targetDiagramEditParts.stream().findFirst().get());
        }

        // Copy notes if asked to
        if (copyNotes) {
            copyNotes(sourceDiagram, targetDiagram);
        }

        return targetDiagram;
    }

    /**
     * Apply format on a new {@link DDiagram} for name @code {@code targetDiagramName} based on the
     * {@code sourceDiagram}. Format data are applied only for diagram elements whose semantic object is provided in the
     * {@code correspondenceMap}.
     * 
     * @param sourceSession
     *            The {@link Session} for the source diagram
     * @param sourceDiagram
     *            The source diagram
     * @param correspondenceMap
     *            The mapping function between source diagram elements and target diagram elements
     * @param targetSession
     *            The {@link Session} for the target diagram
     * @param targetDiagramName
     *            The target diagram name
     * @param targetDiagramRoot
     *            The root EObject for the new diagram
     * @param copyNotes
     *            Whether or not to copy source diagram notes to target diagram
     * @return The created target diagram.
     */
    public DDiagram applyFormatOnNewDiagram(Session sourceSession, DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, String targetDiagramName,
            EObject targetDiagramRoot, boolean copyNotes) {
        // Check application correction
        checkApplyFormatOnNewDiagramCallCorrection(sourceDiagram, correspondenceMap, targetDiagramName);

        DSemanticDiagram targetDiagram = createRepresentation(sourceDiagram, targetSession, targetDiagramName, targetDiagramRoot);

        if (targetDiagram != null) {
            // Initialize target diagram with mapped elements not created by target diagram creation
            populateDiagramFromSourceDiagram(sourceDiagram, correspondenceMap, targetSession, targetDiagram);

            // Apply format according to map
            DiagramEditPart sourceDiagramEditPart = null;
            DiagramEditPart targetDiagramEditPart = null;
            try {
                Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(targetSession, diagramContentDuplicationSwitch.getTargetDiagram());
                Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(sourceSession, sourceDiagram);
                if (!sourceDiagramEditParts.isEmpty() && !targetDiagramEditParts.isEmpty()) {
                    targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();
                    sourceDiagramEditPart = sourceDiagramEditParts.stream().findFirst().get();

                    synchronizeTargetDiagram(targetSession, targetDiagram, targetDiagramEditPart);

                    applyFormatOnDiagram(sourceDiagramEditPart, correspondenceMap, targetDiagramEditPart);

                    // Copy notes if asked to
                    if (copyNotes) {
                        copyNotes(sourceDiagram, targetDiagram);
                    }
                }
            } finally {
                cleanAndDispose(sourceDiagramEditPart);
                cleanAndDispose(targetDiagramEditPart);
            }

            return targetDiagram;
        }
        // TODO: log / feedback representation creation failed
        return null;
    }

    /**
     * Checks
     * {@link MappingBasedSiriusFormatManagerFactory#applyFormatOnDiagram(Session, DDiagram, Map, Session, DDiagram, boolean)}
     * call arguments.
     * 
     * @param sourceDiagram
     *            The source diagram.
     * @param correspondenceMap
     *            The correspondence map.
     * @param targetDiagram
     *            The target diagram.
     */
    protected void checkApplyFormatOnDiagramCallCorrection(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, DDiagram targetDiagram) {
        if (!sourceDiagram.getDescription().equals(targetDiagram.getDescription())) {
            throw new IllegalArgumentException();
        }
        checkMapCorrection(sourceDiagram, correspondenceMap, targetDiagram);
    }

    /**
     * Checks
     * {@link MappingBasedSiriusFormatManagerFactory#applyFormatOnNewDiagram(Session, DDiagram, Map, Session, String, EObject, boolean)}
     * call arguments.
     * 
     * @param sourceDiagram
     *            The source diagram.
     * @param correspondenceMap
     *            The correspondence map.
     * @param targetDiagramName
     *            The new target diagram name.
     */
    protected void checkApplyFormatOnNewDiagramCallCorrection(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, String targetDiagramName) {
        if (sourceDiagram == null || targetDiagramName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        checkMapCorrection(sourceDiagram, correspondenceMap, sourceDiagram);
    }

    /**
     * Checks
     * {@link MappingBasedSiriusFormatManagerFactory#applyFormatOnNewDiagram(Session, DDiagram, Map, Session, String, EObject, boolean)}
     * call Map argument.
     * 
     * @param sourceDiagram
     *            The source diagram.
     * @param correspondenceMap
     *            The correspondence map to check.
     * @param targetDiagram
     *            The target diagram.
     */
    protected void checkMapCorrection(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, DDiagram targetDiagram) {
        if (correspondenceMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        // TODO check map
        // sourceObject to targetObject correction = sourceDiagram[sourceObject].mapping compatible with
        // targetDiagram[targetObject] ?
    }

    /**
     * Synchronize {@code TargetDiagram} at GMF level and handle automatic layout.
     * 
     * @param targetSession
     *            The session holding the target diagram.
     * @param targetDiagram
     *            The target diagram.
     * @param targetDiagramEditPart
     *            The {@link DiagramEditPart} of the target diagram.
     */
    protected void synchronizeTargetDiagram(Session targetSession, DSemanticDiagram targetDiagram, DiagramEditPart targetDiagramEditPart) {
        // Generate GMF views
        final DiagramCreationUtil targetDiagramUtil = new DiagramCreationUtil(targetDiagram);
        if (!targetDiagramUtil.findAssociatedGMFDiagram()) {
            targetDiagramUtil.createNewGMFDiagram();
        }
        final Diagram targetGMFDiagram = targetDiagramUtil.getAssociatedGMFDiagram();
        CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(targetGMFDiagram);
        canonicalSynchronizer.storeViewsToArrange(false);
        canonicalSynchronizer.synchronize();

        targetSession.getRefreshEditorsListener().setForceRefresh(true);
        targetSession.getRefreshEditorsListener().addRepresentationToForceRefresh(targetDiagram);

        // Prevent automatic layout
        Map<Diagram, Set<View>> viewToArrangeCenter = SiriusLayoutDataManager.INSTANCE.getCreatedViewWithCenterLayout();
        Map<Diagram, Set<View>> viewToArrange = SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout();

        View diagramView = targetDiagramEditPart.getDiagramView();
        Set<View> set = viewToArrange.get(diagramView);
        if (set != null) {
            set.clear();
        }

        Set<View> set2 = viewToArrangeCenter.get(diagramView);
        if (set2 != null) {
            set2.clear();
        }
    }

    /**
     * Create elements in {@code targetDiagram} that were not already created based on the {@code correspondenceMap} and
     * the {@code sourceDiagram}.
     * 
     * @param sourceDiagram
     *            The source diagram.
     * @param correspondenceMap
     *            The semantic objects correspondence map.
     * @param targetSession
     *            The session holding the target diagram.
     * @param targetDiagram
     *            The target diagram to populate.
     */
    protected void populateDiagramFromSourceDiagram(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, DSemanticDiagram targetDiagram) {
        diagramContentDuplicationSwitch = new MappingBasedDiagramContentDuplicationSwitch(targetDiagram, correspondenceMap, targetSession);
        diagramContentDuplicationSwitch.doSwitch(sourceDiagram);
    }

    /**
     * Initialize a new representation of name {@code targetDiagramName} based on {@code sourceDiagram} and with root
     * element {@code targetDiagramRoot}.
     * 
     * @param sourceDiagram
     *            The representation used as model for creation.
     * @param targetSession
     *            The session holding the new representation.
     * @param targetDiagramName
     *            The name of the new representation.
     * @param targetDiagramRoot
     *            The root element used for initialization of the new representation.
     * @return The new representation.
     */
    protected DSemanticDiagram createRepresentation(DDiagram sourceDiagram, Session targetSession, String targetDiagramName, EObject targetDiagramRoot) {
        Collection<Viewpoint> selectedViewpoints = targetSession.getSelectedViewpoints(true);
        Collection<RepresentationDescription> descs = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(selectedViewpoints, targetDiagramRoot);
        DRepresentation targetRepresentation = null;
        String sourceDescName = sourceDiagram.getDescription().getName();
        Optional<RepresentationDescription> optDesc = descs.stream().filter(desc -> desc.getName().equals(sourceDescName)).findFirst();
        if (optDesc.isPresent()) {
            // Create target diagram
            targetRepresentation = DialectManager.INSTANCE.createRepresentation(targetDiagramName, targetDiagramRoot, optDesc.get(), targetSession, new NullProgressMonitor());

            DSemanticDiagram targetDiagram = (DSemanticDiagram) targetRepresentation;

            // Remove NotYetOpenedDiagramAdapter
            if (targetDiagram.eAdapters().contains(NotYetOpenedDiagramAdapter.INSTANCE)) {
                targetDiagram.eAdapters().remove(NotYetOpenedDiagramAdapter.INSTANCE);
            }
            return targetDiagram;
        }
        return null;
    }

    /**
     * Copy GMF nodes of {@code sourceDiagram} onto {@code targetDiagram}.
     * 
     * @param sourceDiagram
     *            The diagram to copy notes from.
     * @param targetDiagram
     *            tHe diagram to copy notes to.
     */
    protected void copyNotes(DDiagram sourceDiagram, DDiagram targetDiagram) {
        // Get GMF diagrams
        final DiagramCreationUtil sourceDiagramUtil = new DiagramCreationUtil(sourceDiagram);
        final Diagram sourceGMFDiagram = sourceDiagramUtil.getAssociatedGMFDiagram();
        final DiagramCreationUtil targetDiagramUtil = new DiagramCreationUtil(targetDiagram);
        final Diagram targetGMFDiagram = targetDiagramUtil.getAssociatedGMFDiagram();

        Collection<Node> sourceNotes = GMFNotationHelper.getNotes(sourceGMFDiagram);
        sourceNotes.forEach(sourceNote -> {
            EList sourceEdges = sourceNote.getSourceEdges();
            EList targetEdges = sourceNote.getTargetEdges();
            // TODO

        });

    }

    /**
     * Apply format of {@code sourceDiagramEditPart} on {@code targetDiagramEditPart} based on the
     * {@code correspondenceMap}. The method is only meant to be overridden and not called as API.
     * 
     * @param sourceDiagramEditPart
     *            The {@link DiagramEditPart} for the source diagram.
     * @param correspondenceMap
     *            The map between source diagram semantic elements and target diagram semantic elements.
     * @param targetDiagramEditPart
     *            The {@link DiagramEditPart} for the target diagram.
     */
    protected void applyFormatOnDiagram(DiagramEditPart sourceDiagramEditPart, Map<EObject, EObject> correspondenceMap, DiagramEditPart targetDiagramEditPart) {
        MappingBasedSiriusFormatDataManager formatDataManager = new MappingBasedSiriusFormatDataManager(correspondenceMap);
        formatDataManager.storeFormatData(sourceDiagramEditPart);
        formatDataManager.applyFormat(targetDiagramEditPart);
    }

    private Collection<DiagramEditPart> getDiagramEditPart(Session session, DRepresentation representation) {
        final List<DiagramEditPart> result = new ArrayList<DiagramEditPart>();
        final Collection<EObject> data = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, representation);
        Shell shell = new Shell();
        for (final EObject dataElement : data) {
            if (dataElement instanceof Diagram) {
                final Diagram diagram = (Diagram) dataElement;
                final DiagramEditPartService tool = new DiagramEditPartService();
                final DiagramEditPart diagramEditPart = tool.createDiagramEditPart(diagram, shell, PreferencesHint.USE_DEFAULTS);
                result.add(diagramEditPart);
            }
        }
        Display.getCurrent().asyncExec(new Runnable() {
            @Override
            public void run() {
                shell.dispose();
            }
        });
        return result;
    }

    private void cleanAndDispose(DiagramEditPart diagramEditPart) {
        // Clean to avoid memory leaks
        diagramEditPart.deactivate();
        // Memory leak : also disposing the
        // DiagramGraphicalViewer associated to this
        // DiagramEditPart
        diagramEditPart.getViewer().flush();
        diagramEditPart.getViewer().getEditDomain().getCommandStack().flush();
        diagramEditPart.getViewer().getControl().dispose();
        ((DiagramEditDomain) diagramEditPart.getViewer().getEditDomain()).removeViewer(diagramEditPart.getViewer());
    }
}
