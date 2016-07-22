/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.business.internal.metamodel.operations;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.Messages;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.IContainerMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.DSemanticDiagramHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Implementation of DDiagramElementContainerImpl.java.
 * 
 * @author cbrun, mchauvin
 */
public final class DDiagramElementContainerSpecOperations {

    /** Avoid instanciations */
    private DDiagramElementContainerSpecOperations() {
        // empty.
    }

    /**
     * Return all nodes that are directly contained in the specified container.
     * 
     * @param container
     *            the container.
     * @return all nodes that are directly contained in the specified container.
     */
    public static Collection<AbstractDNode> getNodes(final DDiagramElementContainer container) {
        final Collection<AbstractDNode> result = new ArrayList<AbstractDNode>();
        for (final DDiagramElement elem : container.getElements()) {
            if (elem instanceof DNode) {
                result.add((DNode) elem);
                final DNode node = (DNode) elem;
                for (DNode borderNode : node.getOwnedBorderedNodes()) {
                    result.add(borderNode);
                }
            } else if (elem instanceof DNodeListElement) {
                result.add((DNodeListElement) elem);
            }

            if (elem instanceof DDiagramElementContainer) {
                DDiagramElementContainerSpecOperations.addViewNodes((DDiagramElementContainer) elem, result);
            }
        }
        return result;
    }

    private static void addViewNodes(final DDiagramElementContainer elem, final Collection<AbstractDNode> result) {
        if (elem instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) elem;
            result.addAll(container.getOwnedBorderedNodes());
            for (final DDiagramElement subElem : container.getOwnedDiagramElements()) {
                if (subElem instanceof DNode) {
                    result.add((DNode) subElem);
                }
                if (subElem instanceof DDiagramElementContainer) {
                    DDiagramElementContainerSpecOperations.addViewNodes((DDiagramElementContainer) subElem, result);
                }
            }
        } else if (elem instanceof DNodeList) {
            final DNodeList container = (DNodeList) elem;
            for (final DDiagramElement subElem : container.getOwnedElements()) {
                if (subElem instanceof DNode) {
                    result.add((DNode) subElem);
                }
                if (subElem instanceof DDiagramElementContainer) {
                    DDiagramElementContainerSpecOperations.addViewNodes((DDiagramElementContainer) subElem, result);
                }
            }
        }

    }

    /**
     * Return all containers that are directly contained in the specified
     * container.
     * 
     * @param container
     *            the container.
     * @return all containers that are directly contained in the specified
     *         container.
     */
    public static Collection<DDiagramElementContainer> getContainers(final DDiagramElementContainer container) {
        final Collection<DDiagramElementContainer> result = new ArrayList<DDiagramElementContainer>();
        for (final DDiagramElement elem : container.getElements()) {
            if (elem instanceof DDiagramElementContainer) {
                result.add((DDiagramElementContainer) elem);
            }
            if (elem instanceof DNodeContainer) {
                DDiagramElementContainerSpecOperations.addSiriusElementContainers((DNodeContainer) elem, result);
            }
        }
        return result;

    }

    private static void addSiriusElementContainers(final DNodeContainer container, final Collection<DDiagramElementContainer> result) {
        for (final DDiagramElement elem : container.getOwnedDiagramElements()) {
            if (elem instanceof DDiagramElementContainer) {
                result.add((DDiagramElementContainer) elem);
            }
            if (elem instanceof DNodeContainer) {
                DDiagramElementContainerSpecOperations.addSiriusElementContainers((DNodeContainer) elem, result);
            }
        }
    }

    /**
     * Return all elements that are in the specified container.
     * 
     * @param container
     *            the container.
     * @return all elements that are in the specified container.
     */
    public static EList<DDiagramElement> getElements(final DDiagramElementContainer container) {
        /*
         * subclasses should override it
         */
        return new BasicEList<DDiagramElement>();
    }

    /**
     * Return all nodes that are in the specified container and that have been
     * created from the specified mapping.
     * 
     * @param container
     *            the container.
     * @param mapping
     *            the node mapping.
     * @return all nodes that are in the specified container and that have been
     *         created from the specified mapping.
     */
    public static EList<DNode> getNodesFromMapping(final DDiagramElementContainer container, final NodeMapping mapping) {
        final EList<DNode> result = new BasicEList<DNode>();
        for (final DNode node : container.getNodes()) {
            if (node.getMapping() == mapping) {
                result.add(node);
            }
        }
        return result;
    }

    /**
     * Return all containers that are in the specified container and that have
     * been created from the specified mapping.
     * 
     * @param current
     *            the container.
     * @param mapping
     *            the node mapping.
     * @return all containers that are in the specified container and that have
     *         been created from the specified mapping.
     */
    public static EList<DDiagramElementContainer> getContainersFromMapping(final DDiagramElementContainer current, final ContainerMapping mapping) {
        final EList<DDiagramElementContainer> result = new BasicEList<DDiagramElementContainer>();
        for (final DDiagramElementContainer container : DDiagramElementContainerSpecOperations.getContainers(current)) {
            if (container.getActualMapping() == mapping) {
                result.add(container);
            }
        }
        return result;
    }

    /**
     * Return the parent {@link DDiagram} of the specified container.
     * 
     * @param container
     *            the container.
     * @return the parent {@link DDiagram} of the specified container.
     */
    public static DDiagram getParentDiagram(final DDiagramElementContainer container) {
        return DDiagramElementSpecOperations.getParentDiagram(container);
    }

    /**
     * Validated the specified container.
     * 
     * @param container
     *            the container to validate.
     * @return <code>true</code> if the container is valid.
     */
    public static boolean validate(final DDiagramElementContainer container) {
        ContainerMapping actualMapping = container.getActualMapping();
        if (actualMapping != null && container.getTarget() != null && DDiagramElementContainerSpecOperations.getFirstParentWithSemantic(container) != null) {
            final EObject mySemanticElement = container.getTarget();
            final EObject representedParent = DDiagramElementContainerSpecOperations.getFirstParentWithSemantic(container);
            EObject representedParentSemantic = DDiagramElementContainerSpecOperations.getFirstParentWithSemantic(container).getTarget();
            if (representedParent instanceof DSemanticDiagram) {
                representedParentSemantic = DSemanticDiagramHelper.getRootContent((DSemanticDiagram) representedParent);
            }
            if (!ContainerMappingHelper.getNodesCandidates((IContainerMappingExt) actualMapping, representedParentSemantic, ((DSemanticDecorator) representedParent).getTarget(), container)
                    .contains(mySemanticElement)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the first parent of the specified container that is a
     * {@link DSemanticDecorator}.
     * 
     * @param container
     *            the container.
     * @return the first parent of the specified container that is a
     *         {@link DSemanticDecorator}.
     */
    public static DSemanticDecorator getFirstParentWithSemantic(final DDiagramElementContainer container) {
        DSemanticDecorator result = null;
        EObject cur = container.eContainer();
        while (cur != null && result == null) {
            if (cur instanceof DSemanticDecorator) {
                result = (DSemanticDecorator) cur;
            }
            cur = cur.eContainer();
        }
        return result;
    }

    /**
     * Return the description of this D&D target.
     * 
     * @param self
     *            the container.
     * @return the description of this D&D target.
     */
    public static DragAndDropTargetDescription getDragAndDropDescription(final DDiagramElementContainer self) {
        return self.getActualMapping();
    }

    /**
     * Return the best drop description.
     * 
     * @param description
     *            .
     * @param droppedElement
     *            The semantic dropped element
     * @param oldContainer
     *            The old semantic container, can be null (for instance if drop
     *            comes from project explorer)
     * @param newContainer
     *            The new semantic container
     * @param newViewContainer
     *            The new view container
     * @param dragSource
     *            the drag source.
     * @param droppedDiagramElement
     *            The graphical dropped element.
     * @return he best drop description
     */
    public static ContainerDropDescription getBestDropDescription(final DragAndDropTargetDescription description, final EObject droppedElement, final EObject oldContainer, final EObject newContainer,
            final EObject newViewContainer, final DragSource dragSource, final EObject droppedDiagramElement) {

        final DDiagram diagram;
        if (newViewContainer instanceof DDiagram) {
            diagram = (DDiagram) newViewContainer;
        } else {
            diagram = ((DDiagramElement) newViewContainer).getParentDiagram();
        }

        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
        if (oldContainer != null) {
            interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_OLD, oldContainer);
        }
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_NEW, newContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW_NEW, newViewContainer);
        interpreter.setVariable(IInterpreterSiriusVariables.ELEMENT, droppedElement);
        final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);

        ContainerDropDescription bestDropDescription = null;

        /* find valid candidates */
        final Collection<ContainerDropDescription> candidates = Lists.newArrayList();
        for (final ContainerDropDescription dropTool : DDiagramElementContainerSpecOperations.getDropToolsOnActivatedLayers(diagram, description)) {
            if (DDiagramElementContainerSpecOperations.checkDragSource(dropTool, dragSource)
                    && DDiagramElementContainerSpecOperations.checkDroppedDiagramElement(dropTool, droppedDiagramElement, newViewContainer)) {
                if (DDiagramElementContainerSpecOperations.checkPrecondition(dropTool, safeInterpreter, droppedElement)) {
                    candidates.add(dropTool);
                }
            }
        }

        /*
         * if a candidate define a target mapping which matches he has priority
         */
        for (final ContainerDropDescription dropTool : candidates) {
            if (dropTool.getBestMapping((DragAndDropTarget) newViewContainer, droppedElement) != null) {
                if (bestDropDescription == null) {
                    bestDropDescription = dropTool;
                } else {
                    SiriusPlugin.getDefault().warning(
                            MessageFormat.format(Messages.DDiagramElementContainerSpecOperations_tooMuchDropDescErrorMsg, droppedElement, bestDropDescription.getName(), dropTool),
                            new RuntimeException());
                }
            }
        }
        /*
         * if there is no target mapping, the drag may not create a diagram
         * element but could be valid
         */
        if (dragSource == DragSource.PROJECT_EXPLORER_LITERAL && bestDropDescription == null && !candidates.isEmpty()) {
            bestDropDescription = (ContainerDropDescription) candidates.toArray()[0];
        }

        return bestDropDescription;
    }

    private static boolean checkDragSource(final ContainerDropDescription dropTool, final DragSource dragSource) {
        return dropTool.getDragSource() == DragSource.BOTH_LITERAL || dropTool.getDragSource() == dragSource;
    }

    private static boolean checkDroppedDiagramElement(final ContainerDropDescription dropTool, final EObject droppedDiagramElement, final EObject newViewContainer) {
        boolean valid = true;
        if (droppedDiagramElement instanceof DDiagramElement) {
            if (droppedDiagramElement.equals(newViewContainer)) {
                valid = dropTool.getContainers().contains(((DDiagramElement) droppedDiagramElement).getMapping());
            } else {
                valid = dropTool.getMappings().contains(((DDiagramElement) droppedDiagramElement).getMapping());
            }
        }
        return valid;
    }

    private static boolean checkPrecondition(final ContainerDropDescription dropTool, final RuntimeLoggerInterpreter safeInterpreter, final EObject droppedElement) {
        final String precondition = dropTool.getPrecondition();
        if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
            return safeInterpreter.evaluateBoolean(droppedElement, dropTool, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        }
        return true;
    }

    private static Collection<ContainerDropDescription> getDropToolsOnActivatedLayers(final DDiagram diagram, final DragAndDropTargetDescription mapping) {
        if (diagram.getDescription().getDefaultLayer() != null) {
            final Collection<AbstractToolDescription> allActivatedTools = Sets.newHashSet();
            allActivatedTools.addAll(diagram.getDescription().getDefaultLayer().getAllTools());
            for (Layer layer : diagram.getActivatedLayers()) {
                allActivatedTools.addAll(layer.getAllTools());
            }
            Collection<ContainerDropDescription> dropTools = DDiagramElementContainerSpecOperations.getDropTools(mapping);
            dropTools.retainAll(allActivatedTools);
            return dropTools;
        }
        return DDiagramElementContainerSpecOperations.getDropTools(mapping);
    }

    /**
     * Returns the drop tools of the mapping.
     * 
     * @param mapping
     * @return the drops tools of the mapping
     */
    private static Collection<ContainerDropDescription> getDropTools(final DragAndDropTargetDescription mapping) {
        Collection<ContainerDropDescription> dropTools;
        if (mapping instanceof DiagramElementMapping) {
            dropTools = Sets.newHashSet(new DiagramElementMappingQuery((DiagramElementMapping) mapping).getDropTools());
        } else {
            dropTools = Sets.newHashSet(mapping.getDropDescriptions());
        }
        return dropTools;
    }
}
