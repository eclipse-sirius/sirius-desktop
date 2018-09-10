/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.business.internal.helper.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramMappingsManagerRegistry;
import org.eclipse.sirius.diagram.business.api.query.DMappingBasedQuery;
import org.eclipse.sirius.diagram.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramElementSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.business.internal.experimental.sync.DEdgeCandidate;
import org.eclipse.sirius.diagram.business.internal.helper.decoration.DecorationHelperInternal;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementContainerWithInterpreterOperations;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

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

    /**
     * Provides the diagram containing the given target.
     * 
     * @param target
     *            the drag and drop target.
     * @return the diagram.
     */
    public static DDiagram getParentDiagram(final DragAndDropTarget target) {
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
        return new DropinForNodeTaskCommand(target, mapping, droppedDiagramElement, droppedElement, semanticContainer, moveEdges);
    }

    /**
     * Create the drop in task for container.
     * 
     * @param target
     *            the drop container target
     * @param mapping
     *            the mapping
     * @param droppedDiagramElement
     *            the diagram dropped element (can be null if the drop element doesn't comes from a diagram)
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
        return new DropinForContainerTaskCommand(target, mapping, droppedDiagramElement, droppedElement, semanticContainer, tool);
    }

    /**
     * Deletes edges previously attached to the current dropped element.
     * 
     * @param target
     *            the DragAndDropTarget
     * @param droppedDiagramElement
     *            the dropped diagram element.
     */
    static void deletePreviousEdges(final DragAndDropTarget target, final DDiagramElement droppedDiagramElement) {

        final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);

        /* get the edges on the previous diagram element */
        final List<DEdge> edgesOnPreviousNode = new ArrayList<DEdge>();

        /*
         * if dropped diagram element is null => we dropped from the model content view
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
     * Updates edges according to the new created diagram element.
     * 
     * @param target
     *            the drop target
     * @param semanticTarget
     *            the semantic drop target
     * @param droppedDiagramElement
     *            the diagram element which was moved
     * @param createdDiagramElement
     *            the new diagram element created according to the new mapping for the dropped element (it can be equals
     *            to the droppedDiagramElement if the mapping is the same)
     */
    static void moveEdges(final DragAndDropTarget target, final EObject semanticTarget, final DDiagramElement droppedDiagramElement, final DDiagramElement createdDiagramElement) {

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
         * if dropped diagram element is null => we dropped from the model content view
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
        final Map<DiagramElementMapping, Collection<EdgeTarget>> mappingsToEdgeTargets = sync.computeMappingsToEdgeTargets(session.getSelectedViewpoints(false));

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
                new DecorationHelperInternal(parentDiagram, interpreter, accessor).computeDecorations(mappingsToEdgeTargets, edgeToSemanticBasedDecoration, edgeToMappingBasedDecoration);
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
    static void moveSubNodes(final DNodeContainer oldDiagramElementContainer, final EObject containerDroppedElement, final ContainerDropDescription tool,
            final DNodeContainer newDiagramElementContainer) {
        for (DDiagramElement diagramElementToDrop : new ArrayList<DDiagramElement>(oldDiagramElementContainer.getOwnedDiagramElements())) {
            final DragAndDropTargetDescription dragDragAndDropDescription = newDiagramElementContainer.getDragAndDropDescription();
            final ContainerDropDescription dropTool = DDiagramElementContainerWithInterpreterOperations.getBestDropDescription(dragDragAndDropDescription, diagramElementToDrop.getTarget(),
                    containerDroppedElement, containerDroppedElement, newDiagramElementContainer, DragSource.DIAGRAM_LITERAL, diagramElementToDrop);
            if (dropTool != null
                    && DnDTasksOperations.checkDragAndDropPrecondition(tool, diagramElementToDrop.getTarget(), containerDroppedElement, containerDroppedElement, newDiagramElementContainer)) {
                final DiagramElementMapping mapping = ContainerMappingWithInterpreterHelper.getBestMapping(dropTool, newDiagramElementContainer, diagramElementToDrop.getTarget());
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
                    SiriusPlugin.getDefault().error(Messages.DnDTasksOperations_modelErrorMsg, e);
                } catch (FeatureNotFoundException e) {
                    SiriusPlugin.getDefault().error(Messages.DnDTasksOperations_modelErrorMsg, e);
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

        final List<EdgeMapping> allEdgesMapping = new DiagramComponentizationManager().getAllEdgeMappings(session.getSelectedViewpoints(false), diagramDescription);
        for (final EdgeMapping mapping : allEdgesMapping) {
            DMappingBasedQuery dMappingBasedQuery = new DMappingBasedQuery(createdDiagramElement);
            if (dMappingBasedQuery.isFromAnyMapping(mapping.getSourceMapping()) || dMappingBasedQuery.isFromAnyMapping(mapping.getTargetMapping())) {
                candidatesMapping.add(mapping);
            }
        }
        return candidatesMapping;
    }

    /**
     * Create the task, which will remove the diagram element targeting the dropped element before it was dropped
     * (already done if the mapping of the element is the same before and after the dropped).
     * 
     * @param viewContainer
     *            the container of the diagram element
     * @param element
     *            the dropped diagram element
     * @return the created task
     */
    public static AbstractCommandTask createRemoveAfterDropInTask(final EObject viewContainer, final EObject element) {

        return new AbstractCommandTask() {

            @Override
            public void execute() {
                if (viewContainer instanceof DDiagram) {
                    ((DDiagram) viewContainer).getOwnedDiagramElements().remove(element);
                } else if (viewContainer instanceof DNodeList) {
                    DNodeList dNodeList = (DNodeList) viewContainer;
                    if (dNodeList.getOwnedBorderedNodes().contains(element)) {
                        dNodeList.getOwnedBorderedNodes().remove(element);
                    } else if (dNodeList.getOwnedElements().contains(element)) {
                        dNodeList.getOwnedElements().remove(element);
                    }

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

            @Override
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
