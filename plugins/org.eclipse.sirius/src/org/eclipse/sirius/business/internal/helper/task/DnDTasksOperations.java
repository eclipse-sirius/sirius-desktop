/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.helper.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.DMappingBased;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.DNodeListElement;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.DragAndDropTarget;
import org.eclipse.sirius.EdgeTarget;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.DMappingBasedQuery;
import org.eclipse.sirius.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.business.internal.experimental.sync.DEdgeCandidate;
import org.eclipse.sirius.business.internal.metamodel.operations.DDiagramElementContainerSpecOperations;
import org.eclipse.sirius.description.ContainerMapping;
import org.eclipse.sirius.description.DiagramDescription;
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.description.DragAndDropTargetDescription;
import org.eclipse.sirius.description.EdgeMapping;
import org.eclipse.sirius.description.MappingBasedDecoration;
import org.eclipse.sirius.description.NodeMapping;
import org.eclipse.sirius.description.SemanticBasedDecoration;
import org.eclipse.sirius.description.tool.ContainerDropDescription;
import org.eclipse.sirius.description.tool.DragSource;
import org.eclipse.sirius.description.tool.ToolPackage;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;

/**
 * Common operations for DnD tasks.
 * 
 * @author mchauvin
 */
public final class DnDTasksOperations {

    /**
     * Avoid instantiation.
     */
    private DnDTasksOperations() {

    }

    private static DDiagram getParentDiagram(final DragAndDropTarget target) {
        DDiagram parentDiagram = null;
        if (target instanceof DDiagram) {
            parentDiagram = (DDiagram) target;
        } else if (target instanceof DNodeContainer) {
            parentDiagram = ((DNodeContainer) target).getParentDiagram();
        } else if (target instanceof DNodeList) {
            parentDiagram = ((DNodeList) target).getParentDiagram();
        } else if (target instanceof DNode) {
            parentDiagram = ((DNode) target).getParentDiagram();
        }
        return parentDiagram;
    }

    /**
     * Create the drop in task for node.
     * 
     * @param target
     *            the drop container target
     * @param mapping
     *            the mapping
     * @param droppedDiagramElement
     *            the dropped diagram element
     * @param droppedElement
     *            the semantic dropped element
     * @param semanticContainer
     *            the semantic drop container
     * @param moveEdges
     *            tell whether edges should be moved after dnd
     * @return the created task
     */
    public static AbstractCommandTask createDropinForNodeTask(final DragAndDropTarget target, final NodeMapping mapping, final DDiagramElement droppedDiagramElement, final EObject droppedElement,
            final EObject semanticContainer, final boolean moveEdges) {
        AbstractCommandTask result = new DropinForNodeTaskCommand();
        final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);
        if (target instanceof DNodeList) {
            result = new DropinForNodeTaskCommand() {
                @Override
                public void execute() {
                    DNodeListElement viewNodeListElement;
                    if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping())) {
                        // The mapping is the same so we don't create a new
                        // DNode
                        viewNodeListElement = (DNodeListElement) droppedDiagramElement;
                    } else {
                        viewNodeListElement = mapping.createListElement(droppedElement, parentDiagram);
                    }
                    ((DNodeList) target).getOwnedElements().add(viewNodeListElement);
                    if (!viewNodeListElement.equals(droppedDiagramElement)) {
                        if (moveEdges) {
                            DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, viewNodeListElement);
                        }

                        DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
                    }
                }
            };
        } else if (target instanceof DNodeContainer) {
            result = new DropinForNodeTaskCommand() {
                @Override
                public void execute() {
                    DNode dNode;
                    if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping())) {
                        // The mapping is the same so we don't create a new
                        // DNode
                        dNode = (DNode) droppedDiagramElement;
                    } else {
                        dNode = mapping.createNode(droppedElement, semanticContainer, parentDiagram);
                    }

                    final DNodeContainer dNodeContainer = (DNodeContainer) target;
                    if (dNodeContainer.getActualMapping().getAllBorderedNodeMappings().contains(mapping)) {
                        dNodeContainer.getOwnedBorderedNodes().add(dNode);
                    } else {
                        dNodeContainer.getOwnedDiagramElements().add(dNode);
                    }
                    if (!dNode.equals(droppedDiagramElement)) {
                        if (moveEdges) {
                            DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, dNode);
                        }

                        DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
                    }
                }
            };
        } else if (target instanceof DNode) {
            result = new DropinForNodeTaskCommand() {
                @Override
                public void execute() {
                    DNode dNode;
                    if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping())) {
                        // The mapping is the same so we don't create a new
                        // DNode
                        dNode = (DNode) droppedDiagramElement;
                    } else {
                        dNode = mapping.createNode(droppedElement, semanticContainer, parentDiagram);
                    }
                    ((DNode) target).getOwnedBorderedNodes().add(dNode);
                    if (!dNode.equals(droppedDiagramElement)) {
                        if (moveEdges) {
                            DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, dNode);
                        }

                        DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
                    }
                }
            };
        } else if (target instanceof DDiagram) {
            result = new DropinForNodeTaskCommand() {
                @Override
                public void execute() {
                    DNode dNode;
                    if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping())) {
                        // The mapping is the same so we don't create a new
                        // DNode
                        dNode = (DNode) droppedDiagramElement;
                    } else {
                        dNode = mapping.createNode(droppedElement, semanticContainer, parentDiagram);
                    }
                    parentDiagram.getOwnedDiagramElements().add(dNode);
                    if (!dNode.equals(droppedDiagramElement)) {
                        if (moveEdges) {
                            DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, dNode);
                        }

                        DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
                    }
                }
            };
        }
        return result;
    }

    /**
     * Create the drop in task for container.
     * 
     * @param target
     *            the drop container target
     * @param mapping
     *            the mapping
     * @param droppedDiagramElement
     *            the diagram dropped element (can be null if the drop element
     *            doesn't comes from a diagram)
     * @param droppedElement
     *            the semantic dropped element
     * @param semanticContainer
     *            the semantic drop container target
     * @param tool
     *            the drop tool
     * @return the created task
     */
    public static AbstractCommandTask createDropinForContainerTask(final DragAndDropTarget target, final ContainerMapping mapping, final DDiagramElement droppedDiagramElement,
            final EObject droppedElement, final EObject semanticContainer, final ContainerDropDescription tool) {
        return new AbstractCommandTask() {
            /**
             * {@inheritDoc}
             */
            public void execute() {
                final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);
                DDiagramElementContainer newDiagramElementContainer;
                if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping())) {
                    // The mapping is the same so we don't create a new
                    // DDiagramElementContainer
                    newDiagramElementContainer = (DDiagramElementContainer) droppedDiagramElement;
                } else {
                    // Create the new DiagramElement for the dropped container
                    newDiagramElementContainer = mapping.createContainer(droppedElement, semanticContainer, parentDiagram);
                }
                if (target instanceof DDiagram) {
                    ((DDiagram) target).getOwnedDiagramElements().add(newDiagramElementContainer);
                } else if (target instanceof DNodeContainer) {
                    ((DNodeContainer) target).getOwnedDiagramElements().add(newDiagramElementContainer);
                }
                // move contains children
                if (droppedDiagramElement instanceof DNodeContainer && newDiagramElementContainer instanceof DNodeContainer) {
                    DnDTasksOperations.moveSubNodes((DNodeContainer) droppedDiagramElement, droppedElement, tool, (DNodeContainer) newDiagramElementContainer);
                }
                // move edges
                if (!newDiagramElementContainer.equals(droppedDiagramElement)) {
                    if (tool.isMoveEdges()) {
                        DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, newDiagramElementContainer);
                    }

                    DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
                }
            }

            public String getLabel() {
                return "DropIn task for container";
            }
        };
    }

    private static void deletePreviousEdges(final DragAndDropTarget target, final DDiagramElement droppedDiagramElement) {

        final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);

        /* get the edges on the previous diagram element */
        final List<DEdge> edgesOnPreviousNode = new ArrayList<DEdge>();

        /*
         * if dropped diagram element is null => we dropped from the model
         * content view
         */
        if (droppedDiagramElement != null && parentDiagram != null) {
            final List<DEdge> edges = parentDiagram.getEdges();
            for (DEdge edge : edges) {
                if (edge.getSourceNode().equals(droppedDiagramElement) || edge.getTargetNode().equals(droppedDiagramElement)) {
                    edgesOnPreviousNode.add(edge);
                }
            }
        }

        /* remove edges */
        for (final DEdge edge : edgesOnPreviousNode) {
            SiriusUtil.delete(edge);
        }
    }

    /**
     * @param target
     *            the drop target
     * @param semanticTarget
     *            the semantic drop target
     * @param droppedDiagramElement
     *            the diagram element which was moved
     * @param createdDiagramElement
     *            the new diagram element created according to the new mapping
     *            for the dropped element (it can be equals to the
     *            droppedDiagramElement if the mapping is the same)
     */
    private static void moveEdges(final DragAndDropTarget target, final EObject semanticTarget, final DDiagramElement droppedDiagramElement, final DDiagramElement createdDiagramElement) {

        final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);

        Session session = SessionManager.INSTANCE.getSession(semanticTarget);
        DiagramMappingsManager mappingManager = DiagramMappingsManagerRegistry.INSTANCE.getDiagramMappingsManager(session, parentDiagram);
        final ModelAccessor accessor = session.getModelAccessor();
        final IInterpreter interpreter = session.getInterpreter();

        final DDiagramSynchronizer diagramSync = new DDiagramSynchronizer(interpreter, parentDiagram.getDescription(), accessor);
        diagramSync.setDiagram((DSemanticDiagram) parentDiagram);
        final DDiagramElementSynchronizer sync = diagramSync.getElementSynchronizer();

        /* maps for decorations */
        final Map<EdgeMapping, Collection<MappingBasedDecoration>> edgeToMappingBasedDecoration = new HashMap<EdgeMapping, Collection<MappingBasedDecoration>>();
        final Map<String, Collection<SemanticBasedDecoration>> edgeToSemanticBasedDecoration = new HashMap<String, Collection<SemanticBasedDecoration>>();

        /* get the edges on the previous diagram element */
        final List<DEdge> edgesOnPreviousNode = new ArrayList<DEdge>();

        /*
         * if dropped diagram element is null => we dropped from the model
         * content view
         */
        if (droppedDiagramElement != null) {
            final List<DEdge> edges = parentDiagram.getEdges();
            for (DEdge edge : edges) {
                if (edge.getSourceNode().equals(droppedDiagramElement) || edge.getTargetNode().equals(droppedDiagramElement)) {
                    edgesOnPreviousNode.add(edge);
                }
            }
        }

        /* create the mapping to edge targets map */
        final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = sync.computeMappingsToEdgeTargets(session.getSelectedSiriuss(false));

        /* get the candidate mappings */
        final List<EdgeMapping> candidateMappings = DnDTasksOperations.getCandidatesMappingsForCreatedDiagramElement(parentDiagram, createdDiagramElement);

        /* created element mapping based query */
        DMappingBasedQuery cdeQuery = new DMappingBasedQuery(createdDiagramElement);

        /* compute the good ones */
        for (final EdgeMapping candidateEdgeMapping : candidateMappings) {
            final List<DEdgeCandidate> selectedCandidates = new ArrayList<DEdgeCandidate>();
            for (final DEdge edge : edgesOnPreviousNode) {
                final Collection<DiagramElementMapping> sourceMappings = candidateEdgeMapping.getSourceMapping();
                final Collection<DiagramElementMapping> targetMappings = candidateEdgeMapping.getTargetMapping();

                if (cdeQuery.isFromAnyMapping(sourceMappings)) {
                    /* check target */
                    EdgeTarget edgeTarget = edge.getTargetNode();
                    DMappingBasedQuery edgeTargetQuery = new DMappingBasedQuery((DMappingBased) edgeTarget);
                    if (edgeTarget instanceof DMappingBased && edgeTargetQuery.isFromAnyMapping(targetMappings)) {
                        /* get candidates */
                        final Collection<DEdgeCandidate> candidates = diagramSync.computeEdgeCandidates(candidateEdgeMapping, mappingsToEdgeTargets);
                        for (final DEdgeCandidate candidate : candidates) {
                            if (candidate.getSourceView().equals(edge.getSourceNode()) && candidate.getTargetView().equals(createdDiagramElement)) {
                                if (DnDTasksOperations.addCandidate(edge, selectedCandidates, candidate)) {
                                    break;
                                }
                            }
                        }
                    }
                }
                if (cdeQuery.isFromAnyMapping(targetMappings)) {
                    /* check source */
                    EdgeTarget edgeSource = edge.getSourceNode();
                    DMappingBasedQuery edgeSourceQuery = new DMappingBasedQuery((DMappingBased) edgeSource);
                    if (edgeSource instanceof DMappingBased && edgeSourceQuery.isFromAnyMapping(sourceMappings)) {
                        /* get candidates */
                        final Collection<DEdgeCandidate> candidates = diagramSync.computeEdgeCandidates(candidateEdgeMapping, mappingsToEdgeTargets);
                        for (final DEdgeCandidate candidate : candidates) {
                            if (candidate.getSourceView().equals(createdDiagramElement) && candidate.getTargetView().equals(edge.getTargetNode())) {
                                if (DnDTasksOperations.addCandidate(edge, selectedCandidates, candidate)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if (!selectedCandidates.isEmpty()) {
                diagramSync.computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);
            }

            for (final DEdgeCandidate candidate : selectedCandidates) {
                sync.createNewEdge(mappingManager, candidate, mappingsToEdgeTargets, edgeToMappingBasedDecoration, edgeToSemanticBasedDecoration);
            }
        }
    }

    /**
     * Move the subNodes in case of drag'n'drop of a container.
     * 
     * @param droppedDiagramElement
     * @param containerDroppedElement
     * @param tool
     * @param diagramElementContainer
     */
    private static void moveSubNodes(final DNodeContainer oldDiagramElementContainer, final EObject containerDroppedElement, final ContainerDropDescription tool,
            final DNodeContainer newDiagramElementContainer) {
        final EList<DDiagramElement> ownedDiagramElementsToDrop = oldDiagramElementContainer.getOwnedDiagramElements();
        for (DDiagramElement diagramElementToDrop : ownedDiagramElementsToDrop) {
            final DragAndDropTargetDescription dragDragAndDropDescription = newDiagramElementContainer.getDragAndDropDescription();
            final ContainerDropDescription dropTool = DDiagramElementContainerSpecOperations.getBestDropDescription(dragDragAndDropDescription, diagramElementToDrop.getTarget(),
                    containerDroppedElement, containerDroppedElement, newDiagramElementContainer, DragSource.DIAGRAM_LITERAL, diagramElementToDrop);
            if (dropTool != null
                    && DnDTasksOperations.checkDragAndDropPrecondition(tool, diagramElementToDrop.getTarget(), containerDroppedElement, containerDroppedElement, newDiagramElementContainer)) {
                final DiagramElementMapping mapping = dropTool.getBestMapping(newDiagramElementContainer, diagramElementToDrop.getTarget());
                try {
                    if (mapping instanceof NodeMapping) {

                        DnDTasksOperations.createDropinForNodeTask(newDiagramElementContainer, (NodeMapping) mapping, diagramElementToDrop, diagramElementToDrop.getTarget(),
                                diagramElementToDrop.getTarget().eContainer(), tool.isMoveEdges()).execute();

                    } else if (mapping instanceof EdgeMapping) {

                        /* we do not handle edge mapping yet */

                    } else if (mapping instanceof ContainerMapping) {

                        DnDTasksOperations.createDropinForContainerTask(newDiagramElementContainer, (ContainerMapping) mapping, diagramElementToDrop, diagramElementToDrop.getTarget(),
                                diagramElementToDrop.getTarget().eContainer(), tool).execute();

                    }
                } catch (MetaClassNotFoundException e) {
                    SiriusPlugin.getDefault().error("Error while modifying model", e);
                } catch (FeatureNotFoundException e) {
                    SiriusPlugin.getDefault().error("Error while modifying model", e);
                }
            }
        }
    }

    private static boolean addCandidate(final DEdge edge, final Collection<DEdgeCandidate> candidates, final DEdgeCandidate candidate) {
        boolean added = false;
        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(edge.getActualMapping()).getEdgeMapping();
        if (edgeMapping.some()) {
            if (edgeMapping.get().isUseDomainElement()) {
                if (edge.getTarget() == candidate.getSemantic()) {
                    added = candidates.add(candidate);
                }
            } else {
                added = candidates.add(candidate);
            }
        }
        return added;
    }

    private static List<EdgeMapping> getCandidatesMappingsForCreatedDiagramElement(final DDiagram diagram, final DDiagramElement createdDiagramElement) {

        final List<EdgeMapping> candidatesMapping = new ArrayList<EdgeMapping>();

        final DiagramDescription diagramDescription = diagram.getDescription();
        final Session session = SessionManager.INSTANCE.getSession(createdDiagramElement.getTarget());

        final List<EdgeMapping> allEdgesMapping = new DiagramComponentizationManager().getAllEdgeMappings(session.getSelectedSiriuss(false), diagramDescription);
        for (final EdgeMapping mapping : allEdgesMapping) {
            DMappingBasedQuery dMappingBasedQuery = new DMappingBasedQuery(createdDiagramElement);
            if (dMappingBasedQuery.isFromAnyMapping(mapping.getSourceMapping()) || dMappingBasedQuery.isFromAnyMapping(mapping.getTargetMapping())) {
                candidatesMapping.add(mapping);
            }
        }
        return candidatesMapping;
    }

    /**
     * Create the task, which will remove the diagram element targeting the
     * dropped element before it was dropped (already done if the mapping of the
     * element is the same before and after the dropped).
     * 
     * @param viewContainer
     *            the container of the diagram element
     * @param element
     *            the dropped diagram element
     * @return the created task
     */
    public static AbstractCommandTask createRemoveAfterDropInTask(final EObject viewContainer, final EObject element) {

        return new AbstractCommandTask() {

            public void execute() {
                if (viewContainer instanceof DDiagram) {
                    ((DDiagram) viewContainer).getOwnedDiagramElements().remove(element);
                } else if (viewContainer instanceof DNodeList) {
                    ((DNodeList) viewContainer).getOwnedElements().remove(element);
                } else if (viewContainer instanceof DNodeContainer) {
                    final DNodeContainer viewNodeContainer = (DNodeContainer) viewContainer;
                    if (viewNodeContainer.getOwnedBorderedNodes().contains(element)) {
                        viewNodeContainer.getOwnedBorderedNodes().remove(element);
                    }
                    if (viewNodeContainer.getOwnedDiagramElements().contains(element)) {
                        viewNodeContainer.getOwnedDiagramElements().remove(element);
                    }
                } else if (viewContainer instanceof DNode) {
                    ((DNode) viewContainer).getOwnedBorderedNodes().remove(element);
                }
            }

            public String getLabel() {
                return null;
            }
        };
    }

    /**
     * Check the precondition of the tool.
     * 
     * @param description
     *            the drag & drop tool.
     * @param droppedElement
     *            the element to drop.
     * @param oldContainer
     *            the old semantic container, may be <code>null</code>.
     * @param newContainer
     *            the new semantic container.
     * @param newViewContainer
     *            the new view container.
     * @return <code>true</code> if the precondition is OK, false otherwise.
     */
    public static boolean checkDragAndDropPrecondition(final ContainerDropDescription description, final EObject droppedElement, final EObject oldContainer, final EObject newContainer,
            final EObject newViewContainer) {
        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
        if (oldContainer != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_OLD, oldContainer);
        }
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_NEW, newContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW, newViewContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, droppedElement);
        final String precondition = description.getPrecondition();

        boolean check = false;

        if (precondition != null && !StringUtil.isEmpty(precondition)) {
            check = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(droppedElement, description, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        } else {
            check = true;
        }
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_NEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW);
        interpreter.unSetVariable(IInterpreterSiriusVariables.ELEMENT);
        if (oldContainer != null) {
            interpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER_OLD);
        }
        return check;
    }
}
