/*******************************************************************************
 * Copyright (c) 2020, 2023 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.diagram.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Bendpoints;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.refresh.DiagramCreationUtil;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedDiagramContentDuplicationSwitch;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.tools.internal.SiriusCopierHelper;

/**
 * Utility functions for MappingBasedFormatManagerFactories.
 * 
 * @author adieumegard
 *
 */
public final class MappingBasedSiriusFormatManagerFactoryHelper {

    private MappingBasedSiriusFormatManagerFactoryHelper() {
        // Override default constructor
    }

    /**
     * Retrieve the GMF diagram for {@code sourceDiagram}. GMF diagram is created if necessary.
     * 
     * @param sourceDiagram
     *            The source Sirius diagram
     * @return The GMf diagram
     */
    public static Diagram getGMFDiagram(DDiagram sourceDiagram) {
        final DiagramCreationUtil sourceDiagramUtil = new DiagramCreationUtil(sourceDiagram);
        if (!sourceDiagramUtil.findAssociatedGMFDiagram()) {
            sourceDiagramUtil.createNewGMFDiagram();
        }
        return sourceDiagramUtil.getAssociatedGMFDiagram();
    }

    /**
     * Recursively add parent containers to map if they are not present. Stop as soon as a container is found in the
     * map.
     * 
     * @param sourceView
     *            The source.
     * @param targetView
     *            The target.
     * @param sourceToTargetView
     *            The holding map.
     */
    public static void addParentViewContainersToMap(View sourceView, View targetView, Map<View, View> sourceToTargetView) {
        EObject sourceViewContainer = sourceView.eContainer();
        if (sourceViewContainer instanceof View && !sourceToTargetView.containsKey(sourceView.eContainer())) {
            View targetViewContainer = (View) targetView.eContainer();
            sourceToTargetView.put((View) sourceViewContainer, targetViewContainer);
            addParentViewContainersToMap((View) sourceViewContainer, targetViewContainer, sourceToTargetView);
        }
    }

    /**
     * Copy layout information from {@code sourceGmfView} to {@code targetGmfView}.
     * 
     * @param sourceGmfView
     *            The source view
     * @param targetGmfView
     *            The target view
     */
    public static void copyNodeLayout(Node sourceGmfView, Node targetGmfView) {
        LayoutConstraint layoutConstraintCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceGmfView.getLayoutConstraint());
        targetGmfView.setLayoutConstraint(layoutConstraintCopy);
    }

    /**
     * Copy layout information from {@code sourceEdge} to {@code targetEdge}.
     * 
     * @param sourceEdge
     *            The source edge
     * @param targetEdge
     *            The target edge
     */
    public static void copyEdgeLayout(Edge sourceEdge, Edge targetEdge) {
        // Copy
        Bendpoints bendpointsCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceEdge.getBendpoints());
        Anchor sourceAnchorCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceEdge.getSourceAnchor());
        Anchor targetAnchorCopy = SiriusCopierHelper.copyWithNoUidDuplication(sourceEdge.getTargetAnchor());

        // Apply
        Bendpoints oldBendpoints = targetEdge.getBendpoints();
        if (oldBendpoints instanceof RelativeBendpoints && bendpointsCopy instanceof RelativeBendpoints) {
            // Use this method to allow correct notification handle in
            // org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart.handleNotificationEvent(Notification) and
            // induce a refresh of the edge figure.
            ((RelativeBendpoints) oldBendpoints).setPoints(((RelativeBendpoints) bendpointsCopy).getPoints());
        } else {
            // Fallback but seems not necessary
            targetEdge.setBendpoints(bendpointsCopy);
        }
        targetEdge.setSourceAnchor(sourceAnchorCopy);
        targetEdge.setTargetAnchor(targetAnchorCopy);

        // Copy routing style as it is considered format
        Style routingStyle = sourceEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        Style targetRoutingStyle = targetEdge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
        if (targetRoutingStyle != null) {
            targetEdge.getStyles().remove(targetRoutingStyle);
        }
        targetEdge.getStyles().add(SiriusCopierHelper.copyWithNoUidDuplication(routingStyle));

        copyLabelLayout(sourceEdge, targetEdge);
    }

    /**
     * Copy border label layout information from {@code sourceNode} to {@code targetNode}.
     * 
     * @param sourceNode
     *            The source node
     * @param targetNode
     *            The target node
     */
    public static void copyBorderLabelLayout(Node sourceNode, Node targetNode) {
        final Node sourceLabelNode = SiriusGMFHelper.getLabelNode(sourceNode);
        final Node targetLabelNode = SiriusGMFHelper.getLabelNode(targetNode);
        if (sourceLabelNode != null && targetLabelNode != null) {
            copyNodeLayout(sourceLabelNode, targetLabelNode);
        }
    }

    /**
     * Copy label layout information from {@code sourceEdge} to {@code targetEdge}.
     * 
     * @param sourceEdge
     *            The source edge
     * @param targetEdge
     *            The target edge
     */
    public static void copyLabelLayout(Edge sourceEdge, Edge targetEdge) {
        final Node sourceLabelNode = SiriusGMFHelper.getLabelNode(sourceEdge);
        final Node targetLabelNode = SiriusGMFHelper.getLabelNode(targetEdge);

        copyNodeLayout(sourceLabelNode, targetLabelNode);
    }

    /**
     * Retrieve the container for {@code sourceNote} TextNote.
     * 
     * @param sourceNote
     *            The TEXT kind note
     * @param diagramContentDuplicationSwitch
     *            The content duplication switch containing the DDiagramElement's map
     * @return The container node for the text note.
     */
    public static Node getTargetDiagramTextNoteContainer(Node sourceNote, MappingBasedDiagramContentDuplicationSwitch diagramContentDuplicationSwitch) {
        Node textNoteContainer = null;
        EObject parent = sourceNote.eContainer();
        if (parent instanceof Node) {
            Node parentNode = (Node) parent;
            EObject element = parentNode.getElement();
            if (element instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().get(element);
                textNoteContainer = SiriusGMFHelper.getGmfNode(dDiagramElement);
                if (textNoteContainer != null && !parentNode.isSetElement()) {
                    Iterator<?> iterator = textNoteContainer.getChildren().iterator();
                    while (iterator.hasNext()) {
                        View child = (View) iterator.next();
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
     * Duplicate all notes attachments contained in {@code edges}. Depending on the value of {@code targetNoteIsSource},
     * duplicated edges will originate from (true) or go to (false) {@code targetNote}.
     * 
     * @param edge
     *            The edge
     * @param targetNote
     *            The note attached to the edge
     * @param targetSession
     *            The target diagram session
     * @param targetNoteIsSource
     *            Whether or not {@code targetNote} is the source (true) or the target (false) of {@code edge}
     * @param diagramContentDuplicationSwitch
     *            The content duplication switch containing the DDiagramElement's map
     * @param sourceToTargetNoteMap
     *            The mapping between source diagram notes nodes and target diagram notes nodes
     * @param formatDataManager
     *            The format data manager to apply format
     * @param targetGMFDiagram
     *            the GMF diagram where note attachment should be duplicated
     */
    // CHECKSTYLE:OFF
    public static void duplicateNoteAttachment(Edge edge, Node targetNote, Session targetSession, boolean targetNoteIsSource,
            MappingBasedDiagramContentDuplicationSwitch diagramContentDuplicationSwitch, Map<Node, Node> sourceToTargetNoteMap, MappingBasedSiriusFormatDataManager formatDataManager,
            Diagram targetGMFDiagram) {
        View sourceEdgeOtherBoundView = targetNoteIsSource ? edge.getTarget() : edge.getSource();
        EObject sourceEdgeOtherBoundElement = sourceEdgeOtherBoundView.getElement();
        View matchingTargetElement = null;
        // Retrieve targetDiagram element to link to "targetNote"
        if (sourceEdgeOtherBoundElement == null) {
            matchingTargetElement = sourceToTargetNoteMap.get(sourceEdgeOtherBoundView);
        } else {
            DDiagramElement targetDiagramElement = diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().get(sourceEdgeOtherBoundElement);
            if (targetDiagramElement != null && targetDiagramElement.eResource() != null) {
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
            Collection<Edge> targetNotesAttachments = GMFNotationHelper.getNotesAttachments(targetGMFDiagram);
            Edge noteAttachmentEdge = null;
            if (targetNoteIsSource) {
                noteAttachmentEdge = searchNoteAttachment(targetNotesAttachments, targetNote, matchingTargetElement);
                if (noteAttachmentEdge == null) {
                    noteAttachmentEdge = ViewService.createEdge(targetNote, matchingTargetElement, ViewType.NOTEATTACHMENT, PreferencesHint.USE_DEFAULTS);
                }
            } else {
                noteAttachmentEdge = searchNoteAttachment(targetNotesAttachments, matchingTargetElement, targetNote);
                if (noteAttachmentEdge == null) {
                    noteAttachmentEdge = ViewService.createEdge(matchingTargetElement, targetNote, ViewType.NOTEATTACHMENT, PreferencesHint.USE_DEFAULTS);
                }
            }

            copyEdgeFormatAndStyle(edge, noteAttachmentEdge, formatDataManager);

        } else {
            // Here the target note cannot be found. This should not occur as all notes from
            // GMFNotationHelper.getNotes have been transformed.
            DiagramPlugin.getDefault().logInfo(MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ImpossibleToResolveOtherBoundTargetNote, sourceEdgeOtherBoundView));
        }
    }
    // CHECKSTYLE:ON

    /**
     * Search a corresponding note attachment in the list <code>targetNotesAttachments</code>.
     * 
     * @param targetNotesAttachments
     *            List of note attachments in which to search.
     * @param sourceView
     *            the source view of the searched note attachment
     * @param targetView
     *            the target view of the searched note attachment
     * @return the found note attachment or null if any
     */
    private static Edge searchNoteAttachment(Collection<Edge> targetNotesAttachments, View sourceView, View targetView) {
        for (Edge edge : targetNotesAttachments) {
            if (sourceView != null && sourceView.equals(edge.getSource()) && targetView != null && targetView.equals(edge.getTarget())) {
                return edge;
            }
        }
        return null;
    }

    /**
     * A simple duplication for the format and the style of {@code sourceEdge} to {@code targetEdge}
     * 
     * @param sourceEdge
     * @param targetEdge
     * @param formatDataManager
     */
    private static void copyEdgeFormatAndStyle(Edge sourceEdge, Edge targetEdge, MappingBasedSiriusFormatDataManager formatDataManager) {
        formatDataManager.copyGMFStyle(sourceEdge, targetEdge);
        Bendpoints oldBendpoints = targetEdge.getBendpoints();
        Bendpoints newBendpoints = EcoreUtil.copy(sourceEdge.getBendpoints());
        if (oldBendpoints instanceof RelativeBendpoints && newBendpoints instanceof RelativeBendpoints) {
            // Use this method to allow correct notification handle in
            // org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart.handleNotificationEvent(Notification) and
            // induce a refresh of the edge figure.
            ((RelativeBendpoints) oldBendpoints).setPoints(((RelativeBendpoints) newBendpoints).getPoints());
        } else {
            // Fallback but seems not necessary
            targetEdge.setBendpoints(EcoreUtil.copy(sourceEdge.getBendpoints()));
        }

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
     * Move {@code targetDiagram} diagram nodes to match {@code sourceDiagram} diagram nodes relative depth ('Z')
     * positions.
     * 
     * @param sourceDiagram
     *            The source diagram
     * @param targetDiagram
     *            The target diagram
     * @param copyNotes
     *            Whether or not we take into account notes
     * @param diagramContentDuplicationSwitch
     *            The content duplication switch containing the DDiagramElement's map
     * @param sourceToTargetNoteMap
     *            The mapping between source diagram notes nodes and target diagram notes nodes
     */
    public static void applyNodeDepthPositions(DDiagram sourceDiagram, DDiagram targetDiagram, Boolean copyNotes, MappingBasedDiagramContentDuplicationSwitch diagramContentDuplicationSwitch,
            Map<Node, Node> sourceToTargetNoteMap) {
        // Get GMF diagrams
        final Diagram sourceGMFDiagram = MappingBasedSiriusFormatManagerFactoryHelper.getGMFDiagram(sourceDiagram);

        List<DDiagramElement> allDiagramElements = sourceDiagram.getDiagramElements();
        List<View> allSourceViews = new ArrayList<View>();
        List<View> allTargetViews = new ArrayList<View>();
        Map<View, View> allSourceToTargetView = new HashMap<View, View>();
        List<View> sourceViewForSynchronizedMappingElements = new ArrayList<View>();
        allDiagramElements.forEach(dE -> {
            DDiagramElement targetDE = diagramContentDuplicationSwitch.getSourceDDiagramElementToTargetDDiagramElementMap().get(dE);
            // we make sure that the targetDE has not been removed by the refresh (detached)
            if (targetDE != null && targetDE.eResource() != null) {
                View sourceView = SiriusGMFHelper.getGmfView(dE);
                allSourceViews.add(sourceView);
                View targetView = SiriusGMFHelper.getGmfView(targetDE);
                allTargetViews.add(targetView);
                allSourceToTargetView.put(sourceView, targetView);
                MappingBasedSiriusFormatManagerFactoryHelper.addParentViewContainersToMap(sourceView, targetView, allSourceToTargetView);
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
                MappingBasedSiriusFormatManagerFactoryHelper.addParentViewContainersToMap(sourceNote, targetNote, allSourceToTargetView);
            });
            // Get text note views
            Collection<Shape> diagramTextNotes = GMFNotationHelper.getTextNotes(sourceGMFDiagram);
            diagramTextNotes.stream().filter(sourceTextNote -> sourceGMFDiagram.getChildren().contains(sourceTextNote)).forEach(sourceTextNote -> {
                Node targetTextNote = sourceToTargetNoteMap.get(sourceTextNote);
                allSourceViews.add(sourceTextNote);
                allTargetViews.add(targetTextNote);
                allSourceToTargetView.put(sourceTextNote, targetTextNote);
                MappingBasedSiriusFormatManagerFactoryHelper.addParentViewContainersToMap(sourceTextNote, targetTextNote, allSourceToTargetView);
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
}
