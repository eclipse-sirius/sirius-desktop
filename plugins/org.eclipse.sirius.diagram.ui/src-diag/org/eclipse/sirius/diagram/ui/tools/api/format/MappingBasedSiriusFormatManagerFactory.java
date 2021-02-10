/*******************************************************************************
 * Copyright (c) 2020, 2021 Obeo.
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayMode;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.ui.business.api.helper.graphicalfilters.CompositeFilterApplicationBuilder;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedDiagramContentDuplicationSwitch;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.diagram.util.MappingBasedSiriusFormatManagerFactoryHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.SiriusEditPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * A factory for accessing EObject mapping-based format/layout/style application. Calls to the apply methods shall be
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
     * The FormatDataManager handling format copy.
     */
    protected MappingBasedSiriusFormatDataManager formatDataManager;

    /**
     * A map containing source diagram to target diagram copied notes and text notes.
     */
    protected Map<Node, Node> sourceToTargetNoteMap;

    /**
     * A boolean stating if the call is done on a sequence diagram. Used for SequenceDiagram-specific format application
     * actions.
     */
    protected boolean isAppliedOnSequenceDiagram;

    /**
     * Gives access to the singleton instance of <code>MappingBasedSiriusFormatManagerFactory</code>.
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
     * Calls to this API shall be embedded in a command.
     * 
     * @param sourceSession
     *            The {@link Session} for the source diagram. Must not be null.
     * @param sourceDiagram
     *            The source diagram. Must not be null.
     * @param correspondenceMap
     *            The mapping function between source diagram elements and target diagram elements Must not be null. In
     *            the case where {@code sourceDiagram} is a Sequence diagram, must provide a mapping for each semantic
     *            element of {@code sourceDiagram}.
     * @param targetSession
     *            The {@link Session} for the target diagram. Must not be null.
     * @param targetDiagram
     *            The target diagram. Must not be null.
     * @param copyNotes
     *            Whether or not to copy source diagram notes to target diagram.
     * @return The target diagram.
     */
    public DDiagram applyFormatOnDiagram(Session sourceSession, DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, DDiagram targetDiagram,
            boolean copyNotes) {

        // Check application correction
        checkApplyFormatOnDiagramCallCorrection(sourceDiagram, correspondenceMap, targetDiagram, sourceSession, targetSession);

        // Apply format according to map
        applyFormatAccordingToMap(sourceSession, sourceDiagram, correspondenceMap, targetSession, targetDiagram, copyNotes);

        return targetDiagram;
    }

    /**
     * Apply format on a new {@link DDiagram} for name {@code targetDiagramName} based on the {@code sourceDiagram}.
     * Format data are applied only for diagram elements whose semantic object is provided in the
     * {@code correspondenceMap}.
     * 
     * Calls to this API shall be embedded in a command.
     * 
     * @param sourceSession
     *            The {@link Session} for the source diagram. Must not be null.
     * @param sourceDiagram
     *            The source diagram. Must not be null.
     * @param correspondenceMap
     *            The mapping function between source diagram elements and target diagram elements. Must not be null. In
     *            the case where {@code sourceDiagram} is a Sequence diagram, must provide a mapping for each semantic
     *            element of {@code sourceDiagram}.
     * @param targetSession
     *            The {@link Session} for the target diagram. Must not be null.
     * @param targetDiagramName
     *            The target diagram name. Must not be null or equal to "".
     * @param targetDiagramRoot
     *            The root EObject for the new diagram. Must not be null.
     * @param copyNotes
     *            Whether or not to copy source diagram notes to target diagram.
     * @return The created target diagram.
     */
    public DDiagram applyFormatOnNewDiagram(Session sourceSession, DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, String targetDiagramName,
            EObject targetDiagramRoot, boolean copyNotes) {

        // Check application correction
        checkApplyFormatOnNewDiagramCallCorrection(sourceDiagram, correspondenceMap, targetDiagramName, sourceSession, targetSession, targetDiagramRoot);

        DSemanticDiagram targetDiagram = createRepresentation(sourceDiagram, targetSession, targetDiagramName, targetDiagramRoot);

        if (targetDiagram != null) {
            // Apply format according to map
            applyFormatAccordingToMap(sourceSession, sourceDiagram, correspondenceMap, targetSession, targetDiagram, copyNotes);

            return targetDiagram;
        }
        return null;
    }

    /**
     * This is the core of the format application API.
     * 
     * @param sourceSession
     *            The {@link Session} for the source diagram. Must not be null.
     * @param sourceDiagram
     *            The source diagram. Must not be null.
     * @param correspondenceMap
     *            The mapping function between source diagram elements and target diagram elements. Must not be null. In
     *            the case where {@code sourceDiagram} is a Sequence diagram, must provide a mapping for each semantic
     *            element of {@code sourceDiagram}.
     * @param targetSession
     *            The {@link Session} for the target diagram. Must not be null.
     * @param targetDiagramName
     *            The target diagram name. Must not be null or equal to "".
     * @param copyNotes
     *            Whether or not to copy source diagram notes to target diagram.
     */
    private void applyFormatAccordingToMap(Session sourceSession, DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, DDiagram targetDiagram,
            boolean copyNotes) {

        diagramContentDuplicationSwitch = new MappingBasedDiagramContentDuplicationSwitch((DSemanticDiagram) targetDiagram, correspondenceMap, targetSession);
        diagramContentDuplicationSwitch.doSwitch(sourceDiagram);

        // Apply filters and layers state on target diagram
        applyFiltersAndLayersState(sourceDiagram, targetDiagram);
        synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram);
        DiagramEditPart sourceDiagramEditPart = null;
        DiagramEditPart targetDiagramEditPart = null;
        try {
            Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(sourceSession, sourceDiagram);
            Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(targetSession, targetDiagram);

            if (!sourceDiagramEditParts.isEmpty() && !targetDiagramEditParts.isEmpty()) {
                sourceDiagramEditPart = sourceDiagramEditParts.stream().findFirst().get();
                targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();

                // Apply format according to map
                applyFormatOnDiagram(sourceDiagramEditPart, correspondenceMap, targetDiagramEditPart);
                synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram);

                // Copy notes if asked to
                if (copyNotes) {
                    copyNotes(sourceDiagram, targetDiagram, targetSession);
                    synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram);
                }

                MappingBasedSiriusFormatManagerFactoryHelper.applyNodeDepthPositions(sourceDiagram, targetDiagram, copyNotes, diagramContentDuplicationSwitch, sourceToTargetNoteMap);
                synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram);
            }
        } finally {
            cleanAndDispose(sourceDiagramEditPart);
            cleanAndDispose(targetDiagramEditPart);
        }
    }

    private void applyFiltersAndLayersState(DDiagram sourceDiagram, DDiagram targetDiagram) {
        DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility(targetDiagram);
        Optional<ResourceSet> optionalTargetResourceSet = Optional.ofNullable(targetDiagram).map(EObject::eResource).map(Resource::getResourceSet);
        if (optionalTargetResourceSet.isPresent()) {
            ResourceSet targetResourceSet = optionalTargetResourceSet.get();
            sourceDiagram.getActivatedLayers().forEach(layer -> {
                EObject targetLayer = targetResourceSet.getEObject(EcoreUtil.getURI(layer), false);
                if (targetLayer instanceof Layer) {
                    targetDiagram.getActivatedLayers().add((Layer) targetLayer);
                }
            });

            targetDiagram.getActivatedFilters().clear();
            sourceDiagram.getActivatedFilters().forEach(filter -> {
                EObject targetFilter = targetResourceSet.getEObject(EcoreUtil.getURI(filter), false);
                if (targetFilter instanceof FilterDescription) {
                    targetDiagram.getActivatedFilters().add((FilterDescription) targetFilter);
                }
            });
            // Execute same code as in
            // org.eclipse.sirius.diagram.ui.business.internal.command.RefreshDiagramOnOpeningCommand.doExecute()
            if (targetDiagram.getActivatedFilters().size() != 0) {
                CompositeFilterApplicationBuilder builder = new CompositeFilterApplicationBuilder(targetDiagram);
                builder.computeCompositeFilterApplications();
            }

            if (DisplayMode.NORMAL.equals(DisplayServiceManager.INSTANCE.getMode())) {
                DisplayServiceManager.INSTANCE.getDisplayService().refreshAllElementsVisibility(targetDiagram);
            }
        }
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
     * @param targetSession
     *            The source diagram session.
     * @param sourceSession
     *            The target diagram session.
     */
    private void checkApplyFormatOnDiagramCallCorrection(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, DDiagram targetDiagram, Session sourceSession, Session targetSession) {
        checkDiagram(sourceDiagram);
        checkDiagram(targetDiagram);
        checkSourceDiagramVSTargetDiagram(sourceDiagram, targetDiagram);
        checkSourceAndTargetSessions(sourceSession, targetSession);
        isAppliedOnSequenceDiagram = computeIsSequenceDiagram(sourceDiagram);
        checkMapSourceCorrection(sourceDiagram, correspondenceMap);
    }

    private void checkDiagram(DDiagram diagram) {
        if (diagram == null) {
            throw new IllegalArgumentException(Messages.MappingBasedSiriusFormatManagerFactory_ErrorDiagramIsNull);
        }
    }

    private void checkSourceDiagramVSTargetDiagram(DDiagram sourceDiagram, DDiagram targetDiagram) {
        if (sourceDiagram.equals(targetDiagram)) {
            throw new IllegalArgumentException(Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramsAreTheSame);
        }
        if (!EqualityHelper.areEquals(sourceDiagram.getDescription(), targetDiagram.getDescription())) {
            throw new IllegalArgumentException(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndTargetDiagramDecriptionsDoesNotMatch,
                    sourceDiagram.getDescription().getName(), targetDiagram.getDescription().getName(), sourceDiagram.getDescription(), targetDiagram.getDescription()));
        }
    }

    private void checkSourceAndTargetSessions(Session sourceSession, Session targetSession) {
        if (sourceSession == null || targetSession == null) {
            throw new IllegalArgumentException(Messages.MappingBasedSiriusFormatManagerFactory_ErrorSourceAndOrTargetSessionsNull);
        }
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
     * @param targetSession
     *            The source diagram session.
     * @param sourceSession
     *            The target diagram session.
     * @param targetDiagramRoot
     */
    private void checkApplyFormatOnNewDiagramCallCorrection(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, String targetDiagramName, Session sourceSession,
            Session targetSession, EObject targetDiagramRoot) {
        checkDiagram(sourceDiagram);
        if (targetDiagramName == null || targetDiagramName.isEmpty()) {
            throw new IllegalArgumentException(Messages.MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramNameIsEmpty);
        }
        if (targetDiagramRoot == null) {
            throw new IllegalArgumentException(Messages.MappingBasedSiriusFormatManagerFactory_ErrorTargetDiagramRootIsNull);
        }
        checkSourceAndTargetSessions(sourceSession, targetSession);
        isAppliedOnSequenceDiagram = computeIsSequenceDiagram(sourceDiagram);
        checkMapSourceCorrection(sourceDiagram, correspondenceMap);
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
     */
    private void checkMapSourceCorrection(DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap) {
        if (correspondenceMap.isEmpty()) {
            throw new IllegalArgumentException(Messages.MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIsEmpty);
        }
        if (isAppliedOnSequenceDiagram) {
            List<EObject> allSourceDiagramSemanticElements = sourceDiagram.getDiagramElements().stream().map(DDiagramElement::getTarget).collect(Collectors.toList());
            boolean contains = correspondenceMap.keySet().containsAll(allSourceDiagramSemanticElements);
            if (!contains) {
                throw new IllegalArgumentException(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIncompleteOnSequenceDiagram, sourceDiagram));
            }
        }
    }

    /**
     * Synchronize {@code TargetDiagram} at GMF level and handle automatic layout.
     * 
     * @param targetSession
     *            The session holding the target diagram.
     * @param targetDiagram
     *            The target diagram.
     */
    private void synchronizeTargetDiagram(Session targetSession, DSemanticDiagram targetDiagram) {

        // We do a Sirius refresh before to keep both Sirius and GMF diagram consistent. Indeed, some DDiagramElements
        // might have been created according to the source diagram but for some reasons (inconsistency between the
        // target and source semantic model) some Sirius elements might have been removed by the refresh.
        DialectManager.INSTANCE.refresh(targetDiagram, new NullProgressMonitor());
        // Generate GMF views
        final DiagramCreationUtil targetDiagramUtil = new DiagramCreationUtil(targetDiagram);
        if (!targetDiagramUtil.findAssociatedGMFDiagram()) {
            targetDiagramUtil.createNewGMFDiagram();
        }
        final Diagram targetGMFDiagram = targetDiagramUtil.getAssociatedGMFDiagram();
        if (targetGMFDiagram != null) {
            CanonicalSynchronizer canonicalSynchronizer = CanonicalSynchronizerFactory.INSTANCE.createCanonicalSynchronizer(targetGMFDiagram);
            canonicalSynchronizer.storeViewsToArrange(false);
            canonicalSynchronizer.synchronize();

            // Prevent automatic layout
            Map<Diagram, Set<View>> viewToArrangeCenter = SiriusLayoutDataManager.INSTANCE.getCreatedViewWithCenterLayout();
            Map<Diagram, Set<View>> viewToArrange = SiriusLayoutDataManager.INSTANCE.getCreatedViewsToLayout();

            Set<View> set = viewToArrange.get(targetGMFDiagram);
            if (set != null) {
                set.clear();
            }

            Set<View> set2 = viewToArrangeCenter.get(targetGMFDiagram);
            if (set2 != null) {
                set2.clear();
            }
        }
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
    private DSemanticDiagram createRepresentation(DDiagram sourceDiagram, Session targetSession, String targetDiagramName, EObject targetDiagramRoot) {
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
        } else {
            DiagramPlugin.getDefault()
                    .logError(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ImpossibleToSuitableDescription,
                            descs.stream().map(desc -> desc.getName()).collect(Collectors.joining(", ")), sourceDescName, //$NON-NLS-1$
                    SiriusEditPlugin.getPlugin().getUiCallback().getSessionNameToDisplayWhileSaving(targetSession)));
        }
        return null;
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
    private void applyFormatOnDiagram(DiagramEditPart sourceDiagramEditPart, Map<EObject, EObject> correspondenceMap, DiagramEditPart targetDiagramEditPart) {
        formatDataManager = new MappingBasedSiriusFormatDataManager(correspondenceMap);
        formatDataManager.storeFormatData(sourceDiagramEditPart);

        if (isAppliedOnSequenceDiagram) {
            for (Entry<DDiagramElement, DDiagramElement> entry : diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().entrySet()) {
                View sourceGmfView = SiriusGMFHelper.getGmfView(entry.getKey());
                View targetGmfView = SiriusGMFHelper.getGmfView(entry.getValue());
                if (sourceGmfView instanceof Node && targetGmfView instanceof Node) {
                    Node sourceNode = (Node) sourceGmfView;
                    Node targetNode = (Node) targetGmfView;
                    MappingBasedSiriusFormatManagerFactoryHelper.copyNodeLayout(sourceNode, targetNode);
                    formatDataManager.copySiriusStyle(entry.getKey(), entry.getValue());
                    formatDataManager.copyGMFStyle(sourceNode, targetNode);
                } else if (sourceGmfView instanceof Edge && targetGmfView instanceof Edge) {
                    Edge sourceEdge = (Edge) sourceGmfView;
                    Edge targetEdge = (Edge) targetGmfView;
                    MappingBasedSiriusFormatManagerFactoryHelper.copyEdgeLayout(sourceEdge, targetEdge);
                    formatDataManager.copySiriusStyle(entry.getKey(), entry.getValue());
                    formatDataManager.copyGMFStyle(sourceEdge, targetEdge);
                }
            }
        } else {
            formatDataManager.applyFormat(targetDiagramEditPart);
        }
    }

    /**
     * Computes whether or not {@code sourceDiagram} is a SequenceDiagram.
     * 
     * @param sourceDiagram
     * @return
     */
    private boolean computeIsSequenceDiagram(DDiagram sourceDiagram) {
        boolean isSequenceDiagram = false;
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(sourceDiagram.getDescription().eClass().getEPackage())) {
                isSequenceDiagram = diagramTypeDescriptor.getDiagramDescriptionProvider().requiresNonStandardFormatDataManagers();
                break;
            }
        }
        return isSequenceDiagram;
    }

    /**
     * Get all {@link DiagramEditPart} elements registered in {@code session} whose representation matches
     * {@code representation}.
     * 
     * @param session
     * @param representation
     * @return
     */
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

    /**
     * Copy GMF notes of {@code sourceDiagram} onto {@code targetDiagram}.
     * 
     * @param sourceDiagram
     *            The diagram to copy notes from.
     * @param targetDiagram
     *            The diagram to copy notes to.
     * @param targetSession
     *            The session for the target diagram.
     * @param diagramContentDuplicationSwitch
     */
    private void copyNotes(DDiagram sourceDiagram, DDiagram targetDiagram, Session targetSession) {
        // Get GMF diagrams
        final Diagram sourceGMFDiagram = MappingBasedSiriusFormatManagerFactoryHelper.getGMFDiagram(sourceDiagram);
        final Diagram targetGMFDiagram = MappingBasedSiriusFormatManagerFactoryHelper.getGMFDiagram(targetDiagram);

        // Get all notes
        Collection<Node> sourceNotes = GMFNotationHelper.getNotes(sourceGMFDiagram);
        sourceNotes.addAll(GMFNotationHelper.getTextNotes(sourceGMFDiagram));

        // Initialize and clear sourceToTargetNoteMap
        if (sourceToTargetNoteMap == null) {
            sourceToTargetNoteMap = new HashMap<Node, Node>();
        }
        sourceToTargetNoteMap.clear();

        // Duplicate notes into target diagram and apply source note style
        sourceNotes.forEach(sourceNote -> {
            Node targetNote = null;
            if (ViewType.NOTE.equals(sourceNote.getType())) {
                // Handle notes
                targetNote = GMFNotationHelper.createNote(targetGMFDiagram, GMFNotationHelper.getNoteDescription(sourceNote));
            } else {
                // Handle text notes
                View targetParentNode = null;
                if (sourceNote.eContainer().equals(sourceGMFDiagram)) {
                    targetParentNode = targetGMFDiagram;
                } else {
                    targetParentNode = MappingBasedSiriusFormatManagerFactoryHelper.getTargetDiagramTextNoteContainer(sourceNote, diagramContentDuplicationSwitch);
                }
                if (targetParentNode != null) {
                    targetNote = GMFNotationHelper.createTextNote(targetParentNode, GMFNotationHelper.getNoteDescription(sourceNote));
                } else {
                    DiagramPlugin.getDefault().logInfo(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ImpossibleToFindTargetTextNoteContainer, sourceNote));
                    return;
                }
            }
            targetNote.setLayoutConstraint(EcoreUtil.copy(sourceNote.getLayoutConstraint()));
            if (sourceNote.isSetElement()) {
                targetNote.setElement(sourceNote.getElement());
            }
            formatDataManager.copyGMFStyle(sourceNote, targetNote);
            sourceToTargetNoteMap.put(sourceNote, targetNote);
        });

        // Duplicate note attachments if possible
        Collection<Edge> notesAttachments = GMFNotationHelper.getNotesAttachments(sourceGMFDiagram);
        notesAttachments.forEach(attach -> {
            Node nodeAttachment = (Node) attach.getSource();
            Boolean noteIsSource = true;
            if (!GMFNotationHelper.isNote(nodeAttachment) && !GMFNotationHelper.isTextNote(nodeAttachment)) {
                nodeAttachment = (Node) attach.getTarget();
                noteIsSource = false;
            }
            MappingBasedSiriusFormatManagerFactoryHelper.duplicateNoteAttachment(attach, sourceToTargetNoteMap.get(nodeAttachment), targetSession, noteIsSource, diagramContentDuplicationSwitch,
                    sourceToTargetNoteMap, formatDataManager);
        });

    }

    /**
     * Clean {@code diagramEditPart} element to avoid memory leaks.
     * 
     * @param diagramEditPart
     */
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
