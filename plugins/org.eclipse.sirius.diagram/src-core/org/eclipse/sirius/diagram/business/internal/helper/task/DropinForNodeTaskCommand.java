/*******************************************************************************
 * Copyright (c) 2009, 2021 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.helper.task.AbstractCommandTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.description.extensions.INodeMappingExt;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.NodeMappingHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.MappingHelper;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.tools.internal.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Objects;

/**
 * Default command for dropin node.
 * 
 * @author lredor
 */
public class DropinForNodeTaskCommand extends AbstractCommandTask {

    private DragAndDropTarget target;

    private NodeMapping mapping;

    private DDiagramElement droppedDiagramElement;

    private EObject droppedElement;

    private EObject semanticContainer;

    private boolean moveEdges;

    /**
     * Constructor.
     * 
     * @param target
     *            the drop container target.
     * @param mapping
     *            the mapping.
     * @param droppedDiagramElement
     *            the dropped diagram element.
     * @param droppedElement
     *            the semantic dropped element.
     * @param semanticContainer
     *            the semantic drop container.
     * @param moveEdges
     *            tell whether edges should be moved after dnd.
     */
    public DropinForNodeTaskCommand(DragAndDropTarget target, NodeMapping mapping, DDiagramElement droppedDiagramElement, EObject droppedElement, EObject semanticContainer, boolean moveEdges) {
        this.target = target;
        this.mapping = mapping;
        this.droppedDiagramElement = droppedDiagramElement;
        this.droppedElement = droppedElement;
        this.semanticContainer = semanticContainer;
        this.moveEdges = moveEdges;
    }

    /**
     * (non-Javadoc).
     * 
     * @see org.eclipse.sirius.business.api.helper.task.ICommandTask#execute()
     */
    @Override
    public void execute() {
        final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);
        if (target instanceof DNodeList) {
            handleDNodeListCase(parentDiagram);
        } else if (target instanceof DNodeContainer) {
            handleDNodeContainerCase(parentDiagram);
        } else if (target instanceof DNode) {
            handleDNodeCase(parentDiagram);
        } else if (target instanceof DDiagram) {
            handleDDiagramCase(parentDiagram);
        }
    }

    private void handleDNodeListCase(DDiagram parentDiagram) {
        AbstractDNode abstractDNode = null;
        boolean isBorderNode = isBorderNodeMapping(mapping, ((DNodeList) target).getActualMapping());
        if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping())) {
            if ((isBorderNode && droppedDiagramElement instanceof DNode) || (!isBorderNode && droppedDiagramElement instanceof DNodeListElement)) {
                // The mapping is a border node mapping and the
                // current dragged element is a DNode so we don't need
                // to
                // create a new one.
                abstractDNode = (AbstractDNode) droppedDiagramElement;
            }

        } else if (abstractDNode == null) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
            if (isBorderNode) {
                abstractDNode = new NodeMappingHelper(interpreter).createNode((INodeMappingExt) mapping, droppedElement, semanticContainer, parentDiagram);
            } else {
                abstractDNode = new NodeMappingHelper(interpreter).createListElement(mapping, droppedElement, parentDiagram);
            }
        }
        if (abstractDNode instanceof DNodeListElement) {
            ((DNodeList) target).getOwnedElements().add((DNodeListElement) abstractDNode);
        } else if (abstractDNode instanceof DNode) {
            ((DNodeList) target).getOwnedBorderedNodes().add((DNode) abstractDNode);
        }
        if (!Objects.equal(abstractDNode, droppedDiagramElement)) {
            if (moveEdges) {
                DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, abstractDNode);
            }

            DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
        }
    }

    /**
     * Returns whether the given node mapping is used to define a border node.
     * 
     * @param nodeMapping
     *            the node mapping
     * @param abstractNodeMapping
     *            the abstract node mapping for which the given node mapping is a sub-node.
     * @return true if the given mapping is a border node for the given abstract node mapping, otherwise false.
     */
    private static boolean isBorderNodeMapping(NodeMapping nodeMapping, AbstractNodeMapping abstractNodeMapping) {
        return MappingHelper.getAllBorderedNodeMappings(abstractNodeMapping).contains(nodeMapping);
    }

    private void handleDNodeContainerCase(DDiagram parentDiagram) {
        DNode dNode;
        if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping()) && droppedDiagramElement instanceof DNode) {
            // The mapping is the same and the droppedDiagramElement
            // is a DNode (could be a DNodeListElement for instance)
            // so we don't create a new DNode
            dNode = (DNode) droppedDiagramElement;
        } else {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
            dNode = new NodeMappingHelper(interpreter).createNode((INodeMappingExt) mapping, droppedElement, semanticContainer, parentDiagram);
        }

        final DNodeContainer dNodeContainer = (DNodeContainer) target;
        if (isBorderNodeMapping(mapping, dNodeContainer.getActualMapping())) {
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

    private void handleDNodeCase(DDiagram parentDiagram) {
        DNode dNode;
        if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping()) && droppedDiagramElement instanceof DNode) {
            // The mapping is the same so we don't create a new
            // DNode
            dNode = (DNode) droppedDiagramElement;
        } else {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
            dNode = new NodeMappingHelper(interpreter).createNode((INodeMappingExt) mapping, droppedElement, semanticContainer, parentDiagram);
        }
        ((DNode) target).getOwnedBorderedNodes().add(dNode);
        if (!dNode.equals(droppedDiagramElement)) {
            if (moveEdges) {
                DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, dNode);
            }

            DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
        }

    }

    private void handleDDiagramCase(DDiagram parentDiagram) {
        DNode dNode;
        if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping()) && droppedDiagramElement instanceof DNode) {
            // The mapping is the same so we don't create a new
            // DNode
            dNode = (DNode) droppedDiagramElement;
        } else {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(droppedElement);
            dNode = new NodeMappingHelper(interpreter).createNode((INodeMappingExt) mapping, droppedElement, semanticContainer, parentDiagram);
        }
        parentDiagram.getOwnedDiagramElements().add(dNode);
        if (!dNode.equals(droppedDiagramElement)) {
            if (moveEdges) {
                DnDTasksOperations.moveEdges(target, semanticContainer, droppedDiagramElement, dNode);
            }

            DnDTasksOperations.deletePreviousEdges(target, droppedDiagramElement);
        }
    }

    @Override
    public String getLabel() {
        return Messages.DropinForNodeTaskCommand_taskLabel;
    }
}
