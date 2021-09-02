/*******************************************************************************
 * Copyright (c) 2015, 2021 Obeo.
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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.ContainerMappingWithInterpreterHelper;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.tools.api.Messages;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Command that performs the dropin for a Container.
 * 
 * @author Florian Barbin
 *
 */
public class DropinForContainerTaskCommand extends AbstractCommandTask {

    private DragAndDropTarget target;

    private ContainerMapping mapping;

    private DDiagramElement droppedDiagramElement;

    private EObject droppedElement;

    private EObject semanticContainer;

    private ContainerDropDescription tool;

    /**
     * Constructor.
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
     */
    public DropinForContainerTaskCommand(DragAndDropTarget target, ContainerMapping mapping, DDiagramElement droppedDiagramElement, EObject droppedElement, EObject semanticContainer,
            ContainerDropDescription tool) {
        this.target = target;
        this.mapping = mapping;
        this.droppedDiagramElement = droppedDiagramElement;
        this.droppedElement = droppedElement;
        this.semanticContainer = semanticContainer;
        this.tool = tool;
    }

    @Override
    public String getLabel() {
        return Messages.DropinForContainerTaskCommand_taskLabel;
    }

    @Override
    public void execute() throws MetaClassNotFoundException, FeatureNotFoundException {
        final DDiagram parentDiagram = DnDTasksOperations.getParentDiagram(target);
        DDiagramElementContainer newDiagramElementContainer = null;
        if (droppedDiagramElement != null && mapping.equals(droppedDiagramElement.getMapping()) && droppedDiagramElement instanceof DDiagramElementContainer) {
            // The mapping is the same so we don't create a new
            // DDiagramElementContainer
            newDiagramElementContainer = (DDiagramElementContainer) droppedDiagramElement;
        } else if (mapping instanceof ContainerMapping) {
            IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semanticContainer);
            newDiagramElementContainer = new ContainerMappingWithInterpreterHelper(interpreter).createContainer(mapping, droppedElement, semanticContainer, parentDiagram);
        }
        if (newDiagramElementContainer != null) {
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

    }

}
