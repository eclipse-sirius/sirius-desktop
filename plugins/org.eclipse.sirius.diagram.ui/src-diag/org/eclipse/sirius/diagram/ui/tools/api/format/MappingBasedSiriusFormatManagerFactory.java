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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizer;
import org.eclipse.sirius.diagram.business.api.refresh.CanonicalSynchronizerFactory;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedDiagramContentDuplicationSwitch;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.diagram.util.MappingBasedSiriusFormatManagerFactoryUtil;
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
     * The FormatDataManager handling format copy.
     */
    protected MappingBasedSiriusFormatDataManager formatDataManager;

    /**
     * A map containing source diagram to target diagram copied notes and text notes.
     */
    protected Map<Node, Node> sourceToTargetNoteMap;

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

        // Apply format according to map
        applyFormatAccordingToMap(sourceSession, sourceDiagram, correspondenceMap, targetSession, targetDiagram, copyNotes);

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
            applyFormatAccordingToMap(sourceSession, sourceDiagram, correspondenceMap, targetSession, targetDiagram, copyNotes);

            return targetDiagram;
        }
        // TODO: log / feedback representation creation failed
        return null;
    }

    private void applyFormatAccordingToMap(Session sourceSession, DDiagram sourceDiagram, final Map<EObject, EObject> correspondenceMap, Session targetSession, DDiagram targetDiagram,
            boolean copyNotes) {
        DiagramEditPart sourceDiagramEditPart = null;
        DiagramEditPart targetDiagramEditPart = null;
        try {
            Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(sourceSession, sourceDiagram);
            Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(targetSession, targetDiagram);

            if (!sourceDiagramEditParts.isEmpty() && !targetDiagramEditParts.isEmpty()) {
                sourceDiagramEditPart = sourceDiagramEditParts.stream().findFirst().get();
                targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();

                synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram, targetDiagramEditPart);

                // Apply format according to map
                applyFormatOnDiagram(sourceDiagramEditPart, correspondenceMap, targetDiagramEditPart);

                synchronizeTargetDiagram(targetSession, (DSemanticDiagram) targetDiagram, targetDiagramEditPart);

                // Copy notes if asked to
                if (copyNotes) {
                    copyNotes(sourceDiagram, targetDiagram, targetSession);
                }

                copyNodeDepthPositions(sourceDiagram, targetDiagram, copyNotes);
            }
        } finally {
            cleanAndDispose(sourceDiagramEditPart);
            cleanAndDispose(targetDiagramEditPart);
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
    protected void copyNotes(DDiagram sourceDiagram, DDiagram targetDiagram, Session targetSession) {
        // Get GMF diagrams
        final Diagram sourceGMFDiagram = MappingBasedSiriusFormatManagerFactoryUtil.getGMFDiagram(sourceDiagram);
        final Diagram targetGMFDiagram = MappingBasedSiriusFormatManagerFactoryUtil.getGMFDiagram(targetDiagram);

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
                    targetParentNode = getTargetDiagramTextNoteContainer(sourceNote);
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
            duplicateNoteAttachment(attach, sourceToTargetNoteMap.get(nodeAttachment), targetSession, noteIsSource);
        });

    }

    private Node getTargetDiagramTextNoteContainer(Node sourceNote) {
        Node textNoteContainer = null;
        EObject parent = sourceNote.eContainer();
        if (parent instanceof Node) {
            Node parentNode = (Node) parent;
            EObject element = parentNode.getElement();
            if (element instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().get(element);
                textNoteContainer = SiriusGMFHelper.getGmfNode(dDiagramElement);
                if (!parentNode.isSetElement()) {
                    Iterator<?> iterator = textNoteContainer.getChildren().iterator();
                    while (iterator.hasNext()) {
                        View child = (View) iterator.next();
                        // Here we may have a problem... I cannot find a proper check to got the correct container.
                        if (child.getType().equals(parentNode.getType())) {
                            textNoteContainer = (Node) child;
                            break;
                        }
                    }
                }
                return textNoteContainer;
            }
        }
        return textNoteContainer;
    }

    /**
     * Duplicate all notes attachments contained in {@code edges}. Depending on the value of
     * {@code edgesAreSourceEdges}, duplicated edges will originate from (true) or go to (false) {@code targetNote}.
     * 
     * @param sourceEdges
     * @param targetNote
     * @param targetSession
     * @param targetNoteIsSource
     */
    private void duplicateNoteAttachment(Edge edge, Node targetNote, Session targetSession, boolean targetNoteIsSource) {
        View sourceEdgeOtherBoundView = targetNoteIsSource ? edge.getTarget() : edge.getSource();
        EObject sourceEdgeOtherBoundElement = sourceEdgeOtherBoundView.getElement();
        View matchingTargetElement = null;
        // Retrieve targetDiagram element to link to "targetNote"
        if (sourceEdgeOtherBoundElement == null) {
            matchingTargetElement = sourceToTargetNoteMap.get(sourceEdgeOtherBoundView);
        } else {
            DDiagramElement targetDiagramElement = diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().get(sourceEdgeOtherBoundElement);
            if (targetDiagramElement != null) {
                matchingTargetElement = SiriusGMFHelper.getGmfView(targetDiagramElement, targetSession);
            } else {
                // Here, either the target of the note attachment is not present in the target diagram or the
                // source/target semantic has not been added to the semantic map while source element being
                // represented by a synchronized mapping.
                DiagramPlugin.getDefault()
                        .logInfo(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ImpossibleToCopyNoteInNonExistingOrUnreachableTarget, sourceEdgeOtherBoundElement));
            }
        }
        if (matchingTargetElement != null) {
            Edge noteAttachmentEdge = null;
            if (targetNoteIsSource) {
                noteAttachmentEdge = ViewService.createEdge(targetNote, matchingTargetElement, ViewType.NOTEATTACHMENT, PreferencesHint.USE_DEFAULTS);
            } else {
                noteAttachmentEdge = ViewService.createEdge(matchingTargetElement, targetNote, ViewType.NOTEATTACHMENT, PreferencesHint.USE_DEFAULTS);
            }

            copyEdgeFormatAndStyle(edge, noteAttachmentEdge);

        } else {
            // Here the target note cannot be found. This should not occur as all notes from
            // GMFNotationHelper.getNotes have been transformed.
            DiagramPlugin.getDefault().logInfo(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ImpossibleToResolveOtherBoundTargetNote, sourceEdgeOtherBoundView));
        }
    }

    /**
     * A simple duplication for the format and the style of {@code sourceEdge} to {@code targetEdge}
     * 
     * @param sourceEdge
     * @param targetEdge
     */
    private void copyEdgeFormatAndStyle(Edge sourceEdge, Edge targetEdge) {
        formatDataManager.copyGMFStyle(sourceEdge, targetEdge);
        targetEdge.setBendpoints(EcoreUtil.copy(sourceEdge.getBendpoints()));

        if (sourceEdge.getSourceAnchor() != null) {
            targetEdge.setSourceAnchor(EcoreUtil.copy(sourceEdge.getSourceAnchor()));
        } else {
            targetEdge.setSourceAnchor(null);
        }
        if (sourceEdge.getTargetAnchor() != null) {
            targetEdge.setTargetAnchor(EcoreUtil.copy(sourceEdge.getTargetAnchor()));
        } else {
            targetEdge.setTargetAnchor(null);
        }
        RoutingStyle originEdgeRoutingStyle = (RoutingStyle) sourceEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        if (originEdgeRoutingStyle != null) {
            RoutingStyle targetEdgeRoutingStyle = (RoutingStyle) targetEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
            targetEdgeRoutingStyle.setRouting(originEdgeRoutingStyle.getRouting());
            targetEdgeRoutingStyle.setJumpLinkStatus(originEdgeRoutingStyle.getJumpLinkStatus());
            targetEdgeRoutingStyle.setJumpLinkType(originEdgeRoutingStyle.getJumpLinkType());
            targetEdgeRoutingStyle.setJumpLinksReverse(originEdgeRoutingStyle.isJumpLinksReverse());
            targetEdgeRoutingStyle.setSmoothness(originEdgeRoutingStyle.getSmoothness());
        }
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
        formatDataManager = new MappingBasedSiriusFormatDataManager(correspondenceMap);
        formatDataManager.storeFormatData(sourceDiagramEditPart);
        formatDataManager.applyFormat(targetDiagramEditPart);
    }

    private void copyNodeDepthPositions(DDiagram sourceDiagram, DDiagram targetDiagram, Boolean copyNotes) {
        // Get GMF diagrams
        final Diagram sourceGMFDiagram = MappingBasedSiriusFormatManagerFactoryUtil.getGMFDiagram(sourceDiagram);

        List<DDiagramElement> allDiagramElements = sourceDiagram.getDiagramElements();
        List<View> allSourceViews = new ArrayList<View>();
        List<View> allTargetViews = new ArrayList<View>();
        Map<View, View> allSourceToTargetView = new HashMap<View, View>();
        List<View> sourceViewForSynchronizedMappingElements = new ArrayList<View>();
        allDiagramElements.forEach(dE -> {
            DDiagramElement targetDE = diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().get(dE);
            if (targetDE != null) {
                View sourceView = SiriusGMFHelper.getGmfView(dE);
                allSourceViews.add(sourceView);
                View targetView = SiriusGMFHelper.getGmfView(targetDE);
                allTargetViews.add(targetView);
                allSourceToTargetView.put(sourceView, targetView);
                MappingBasedSiriusFormatManagerFactoryUtil.addParentViewContainersToMap(sourceView, targetView, allSourceToTargetView);
            } else if ((dE.getMapping()) instanceof DiagramElementMapping && ((DiagramElementMapping) dE.getMapping()).isCreateElements()) {
                // We store the view for the diagram elements of the source diagram that are not specified in the layout
                // application mapping function but will be created from a synchronized mapping.
                sourceViewForSynchronizedMappingElements.add(SiriusGMFHelper.getGmfView(dE));
            }
        });

        if (copyNotes) {
            // Get notes views
            GMFNotationHelper.getNotes(sourceGMFDiagram).forEach(sourceNote -> {
                Node targetNote = sourceToTargetNoteMap.get(sourceNote);
                allSourceViews.add(sourceNote);
                allTargetViews.add(targetNote);
                allSourceToTargetView.put(sourceNote, targetNote);
                MappingBasedSiriusFormatManagerFactoryUtil.addParentViewContainersToMap(sourceNote, targetNote, allSourceToTargetView);
            });
            // Get text note views
            Collection<Node> diagramTextNotes = GMFNotationHelper.getTextNotes(sourceGMFDiagram);
            diagramTextNotes.stream().filter(sourceTextNote -> sourceGMFDiagram.getChildren().contains(sourceTextNote)).forEach(sourceTextNote -> {
                Node targetTextNote = sourceToTargetNoteMap.get(sourceTextNote);
                allSourceViews.add(sourceTextNote);
                allTargetViews.add(targetTextNote);
                allSourceToTargetView.put(sourceTextNote, targetTextNote);
                MappingBasedSiriusFormatManagerFactoryUtil.addParentViewContainersToMap(sourceTextNote, targetTextNote, allSourceToTargetView);
            });
        }

        if (!allSourceViews.isEmpty()) {
            // Order target diagram Views based on source diagram views
            allSourceViews.forEach(source -> {
                int insertIndex = 0;
                // For each children linked to an element, reorder according to source order
                EList<View> sourceContainerChildrens = ((View) source.eContainer()).getChildren();
                View target = allSourceToTargetView.get(source);
                for (View sourceContainerChild : sourceContainerChildrens) {
                    if (sourceContainerChild.equals(source)) {
                        ViewUtil.repositionChildAt((View) target.eContainer(), target, insertIndex);
                        break;
                    }
                    if (allSourceToTargetView.containsKey(sourceContainerChild) || sourceViewForSynchronizedMappingElements.contains(sourceContainerChild)) {
                        insertIndex++;
                    }
                }
            });
        }
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
