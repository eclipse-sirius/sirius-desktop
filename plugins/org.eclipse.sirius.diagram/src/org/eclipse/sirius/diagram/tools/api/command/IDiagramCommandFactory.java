/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.tools.api.command;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.DoubleClickDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.tools.api.command.DCommand;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.PasteDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;

/**
 * The command factory for diagram dialect.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface IDiagramCommandFactory extends ICommandFactory {
    /**
     * Create a command that executes the operation of a {@link BehaviorTool}.
     * <p>
     * The operations of a {@link BehaviorTool} are executed on the <code>rootObject</code> parameter. If the
     * <code>executeFromRootContainer</code> is <code>true</code> the operations are executed on the root container of
     * <code>rootObject</code>.
     * </p>
     * <p>
     * Finally, if the <code>deepProcess</code> parameter is <code>true</code>, the operations are executing on all
     * children (with a deep path) according to the {@link BehaviorTool#getDomainClass()} and the
     * {@link BehaviorTool#getPrecondition()}.
     * </p>
     * 
     * @param rootObject
     *            the selected object.
     * @param tool
     *            the behavior.
     * @param executeFromRootContainer
     *            if <code>true</code> the tool is applied on the root container of <code>rootObject</code>.
     * @param deepProcess
     *            if <code>true</code>
     * @return return the command that executes a behavior.
     */
    Command buildLaunchRuleCommandFromTool(DSemanticDecorator rootObject, BehaviorTool tool, boolean executeFromRootContainer, boolean deepProcess);

    /**
     * Create a command that creates a node.
     * 
     * @param container
     *            container element in which the command should put the created node.
     * @param tool
     *            {@link NodeCreationDescription} used to build the command.
     * @return a command able to create the node and putting it in the container, corresponding to the
     *         {@link NodeCreationDescription}.
     */
    Command buildCreateNodeCommandFromTool(DDiagramElementContainer container, NodeCreationDescription tool);

    /**
     * Create a command that creates a node.
     * 
     * @param node
     *            the node container in which the command should put the created node.
     * @param tool
     * 
     *            the {@link NodeCreationDescription} used to build the command.
     * @return a command able to create the node and putting it around the node, corresponding to the
     *         {@link NodeCreationDescription}.
     */
    Command buildCreateNodeCommandFromTool(DNode node, NodeCreationDescription tool);

    /**
     * Create a command that create a node and put it in the viewpoint.
     * 
     * @param diagram
     *            the viewpoint in which the command should put the created node.
     * @param tool
     *            the {@link NodeCreationDescription} used to build the command.
     * @return a command able to create the node and putting it into the viewpoint, corresponding to the
     *         {@link NodeCreationDescription}.
     */
    Command buildCreateNodeCommandFromTool(DDiagram diagram, NodeCreationDescription tool);

    /**
     * Create a command that is able to execute the operations of a
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription} .
     * 
     * @param tool
     *            the tool, it must be a
     *            {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription} .
     * @param dContainer
     *            the clicked designer container.
     * @param selectedElement
     *            the selected element.
     * @return a command that is able to execute the operations of
     *         {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription} .
     */
    Command buildSelectionWizardCommandFromTool(SelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement);

    /**
     * Create a command that is able to execute the operations of a
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription} .
     * 
     * @param tool
     *            the tool, it must be a
     *            {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription} .
     * @param dContainer
     *            the clicked designer container.
     * @param selectedElement
     *            the selected element.
     * @return a command that is able to execute the operations of
     *         {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription} .
     */
    Command buildPaneBasedSelectionWizardCommandFromTool(PaneBasedSelectionWizardDescription tool, DSemanticDecorator dContainer, Collection<EObject> selectedElement);

    /**
     * Create a command that is able to execute the operations of a
     * {@link org.eclipse.sirius.description.tool.JavaActionMenuItem}.
     * 
     * @param tool
     *            the tool, it must be a {@link org.eclipse.sirius.description.tool.JavaActionMenuItem} , the action to
     *            launch.
     * @param selectedViews
     *            the clicked designer container.
     * @param javaAction
     *            the java action to execute.
     * @return a command that is able to execute the operations of
     *         {@link org.eclipse.sirius.description.tool.JavaActionMenuItem} .
     */
    Command buildJavaActionFromTool(ExternalJavaAction tool, Collection<DSemanticDecorator> selectedViews, IExternalJavaAction javaAction);

    /**
     * Create a command that is able to execute the operations of a
     * {@link org.eclipse.sirius.description.tool.OperationMenuItem}.
     * 
     * @param tool
     *            the tool, it must be a {@link org.eclipse.sirius.description.tool.OperationMenuItem} , the operation
     *            to launch.
     * @param selectedViews
     *            the clicked designer container.
     * 
     * @return a command that is able to execute the operations of
     *         {@link org.eclipse.sirius.description.tool.OperationMenuItem}.
     */
    Command buildOperationActionFromTool(OperationAction tool, Collection<DSemanticDecorator> selectedViews);

    /**
     * Build an edge creation command from a tool description.
     * 
     * @param source
     *            the source node of the connection.
     * @param target
     *            the target node of the connection.
     * @param tool
     *            the tool that describes how to create the new edge.
     * @return a command able to create the edge.
     */
    Command buildCreateEdgeCommandFromTool(EdgeTarget source, EdgeTarget target, EdgeCreationDescription tool);

    /**
     * Create a reconnection command.
     * 
     * @param tool
     *            the description of the reconnection.
     * @param edge
     *            the edge to reconnect.
     * @param source
     *            the source node.
     * @param target
     *            the target node.
     * @return the reconnection command.
     */
    Command buildReconnectEdgeCommandFromTool(ReconnectEdgeDescription tool, DEdge edge, EdgeTarget source, EdgeTarget target);

    /**
     * Create a command able to drop a node into a container.
     * 
     * @param dContainer
     *            the target designer container.
     * @param element
     *            the node to drop.
     * @param tool
     *            the tool that describes the operation.
     * @return a command able to drop a node into a container.
     */
    Command buildDropInContainerCommandFromTool(DragAndDropTarget dContainer, DDiagramElement element, ContainerDropDescription tool);

    /**
     * Create a command able to drop a node into a container.
     * 
     * @param dContainer
     *            the target designer container.
     * @param droppedElement
     *            the element to drop
     * 
     * @param tool
     *            the tool that describes the operation.
     * @return a command able to drop a node into a container.
     */
    Command buildDropInContainerCommandFromTool(DragAndDropTarget dContainer, EObject droppedElement, ContainerDropDescription tool);

    /**
     * Create a command able to paste a semantic element into a container.
     * 
     * @param dContainer
     *            the target designer container.
     * @param element
     *            the node targeting the semantic element to paste.
     * @param tool
     *            the tool that describes the operation.
     * @return a command able to paste a semantic element into a container.
     */
    Command buildPasteCommandFromTool(DSemanticDecorator dContainer, DSemanticDecorator element, PasteDescription tool);

    /**
     * Create a command able to paste a semantic element into a container.
     * 
     * @param dContainer
     *            the target designer container.
     * @param copiedElement
     *            the element to drop
     * 
     * @param tool
     *            the tool that describes the operation.
     * @return a command able to drop a node into a container.
     */
    Command buildPasteCommandFromTool(DSemanticDecorator dContainer, EObject copiedElement, PasteDescription tool);

    /**
     * Create a command able to execute an action when double clicking on an element.
     * 
     * @param dDiagramElement
     *            the element that can be double clicked.
     * 
     * @param tool
     *            the tool that describes the operation.
     * @return a command able to execute a defined action when double clicking on dDiagramElement.
     */
    Command buildDoubleClickOnElementCommandFromTool(DDiagramElement dDiagramElement, DoubleClickDescription tool);

    /**
     * Create a command that creates a container.
     * 
     * @param diagram
     *            the diagram that owns the container.
     * @param tool
     *            The tool that describes the container creation.
     * @return the command that creates the container.
     */
    Command buildCreateContainerCommandFromTool(DDiagram diagram, ContainerCreationDescription tool);

    /**
     * Create a command that is able to create a container into the specified container.
     * 
     * @param nodeContainer
     *            the parent container.
     * @param tool
     *            the tool that describes how to create the new container.
     * @return a command that is able to create a container into the specified container.
     */
    Command buildCreateContainerCommandFromTool(DDiagramElementContainer nodeContainer, ContainerCreationDescription tool);

    /**
     * Create a command to delete a viewpoint.
     * 
     * @param vp
     *            the viewpoint to delete.
     * @return the command.
     */
    Command buildDeleteDiagram(DDiagram vp);

    /**
     * Create a command to delete a viewpoint element from the diagram.
     * 
     * @param element
     *            element to delete.
     * @return a command that delete the DDiagramElement without deleting the semantic one.
     */
    Command buildDeleteFromDiagramCommand(DDiagramElement element);

    /**
     * Returns a command that can delete the specified element.
     * 
     * @param element
     *            the element to delete.
     * @return a command that can delete the specified element.
     */
    Command buildDeleteDiagramElement(DDiagramElement element);

    /**
     * Build a direct edit label command using the corresponding tool description.
     * 
     * @param repElement
     *            : the element on which the label should be changed.
     * @param directEditTool
     *            : the tool description.
     * @param newValue
     *            : the new label value
     * @return : a command which prepare the model request interpreter and set the new label.
     */
    Command buildDirectEditLabelFromTool(DRepresentationElement repElement, DirectEditLabel directEditTool, String newValue);

    /**
     * Build a command that is able to refresh a refreshable element.
     * 
     * @param refreshableElement
     *            the element to refresh.
     * @return a command that is able to refresh a refreshable element.
     */
    Command buildRefreshCommand(DRefreshable refreshableElement);

    /**
     * Build a command that is able to hide many elements.
     * 
     * @param elementsToHide
     *            the elements to hide.
     * @return a command that is able to hide many elements.
     */
    Command buildHideCommand(Set<EObject> elementsToHide);

    /**
     * Build a command that is able to hide label of many elements.
     * 
     * @param elementsToHide
     *            the elements to hide.
     * @return a command that is able to hide label of many elements.
     */
    Command buildHideLabelCommand(Set<EObject> elementsToHide);

    /**
     * Build a command that is able to hide label of many elements.
     * 
     * @param elementsToHide
     *            the elements to hide.
     * @param selectedLabelVisualIds
     *            the VisualIds of the edge labels to hide sorted by edge.
     * @return a command that is able to hide label of many elements.
     */
    Command buildHideLabelSelectionCommand(Set<EObject> elementsToHide, Map<EObject, List<Integer>> selectedLabelVisualIds);

    /**
     * Build a command that is able to reveal one diagram element.
     * 
     * @param diagramElement
     *            the diagram element to reveal.
     * @return a command that is able to reveal one diagram element.
     */
    Command buildRevealCommand(DDiagramElement diagramElement);

    /**
     * Build a command that is able to reveal label of one diagram element.
     * 
     * @param diagramElement
     *            the diagram element for which you want to reveal the label.
     * @return a command that is able to reveal the label of one diagram element.
     */
    Command buildRevealLabelCommand(DDiagramElement diagramElement);

    /**
     * Build a command that is able to reveal all elements of a diagram.
     * 
     * @param diagram
     *            the diagram.
     * @return a command that is able to reveal all elements of a diagram.
     */
    Command buildRevealElementsCommand(DDiagram diagram);

    /**
     * Build a command that is able to reveal all elements of a diagram.
     * 
     * @param elementsToReveal
     *            the diagram elements to reveal.
     * @return a command that is able to reveal all elements of a diagram.
     */
    Command buildRevealElementsCommand(Set<DDiagramElement> elementsToReveal);

    /**
     * Create a command that is able to create a diagram.
     * 
     * @param description
     *            the tool that describes how to create the diagram.
     * @param semanticElement
     *            the element from which the diagram will be created.
     * @param monitor
     *            a {@link IProgressMonitor} to show progression of {@link DDiagram} creation
     * @return a command that is able to create a diagram.
     */
    DCommand buildCreateDiagramFromDescription(DiagramDescription description, EObject semanticElement, IProgressMonitor monitor);

    /**
     * Create a command to execute a quick fix .
     * 
     * @param fix
     *            quick fix description.
     * @param fixTarget
     *            Target to execute fix on.
     * @param diagram
     *            the current diagram
     * @return an executable command.
     */
    Command buildQuickFixOperation(ValidationFix fix, EObject fixTarget, DDiagram diagram);

    /**
     * Create a command to execute a generic {@link ToolDescription}.
     * 
     * @param containerView
     *            the view the tool has been invoked on.
     * @param toolDesc
     *            the description of the tool.
     * @return a command which executes the behavior specified in the tool in the context of the view.
     */
    Command buildGenericToolCommandFromTool(EObject containerView, ToolDescription toolDesc);

    /**
     * Create a command that shifts nodes to insert or remove vertical blank space in a diagram.
     * 
     * @param diagram
     *            the diagram in which insert blank space
     * @param startX
     *            the initial location in pixel to insert blank space
     * @param spaceToInsert
     *            the number of pixels to insert
     * 
     * @return a command able to insert blank space (move all elements).
     */
    Command buildInsertOrRemoveVerticalBlankSpaceCommand(DDiagram diagram, int startX, int spaceToInsert);


    /**
     * Build a command that is able to bring to front many elements.
     * 
     * @param elementsToBringToFront
     *            The elements to bring to front (Node or Edge). These elements must be of the same type and have the
     *            same parent.
     * @return a command that is able to bring to front many elements.
     * @throws UnsupportedOperationException
     *             in case of a not supported operation
     */
    Command buildBringToFrontCommand(List<? extends View> elementsToBringToFront) throws UnsupportedOperationException;

    /**
     * Build a command that is able to send to back many elements.
     * 
     * @param elementsToSendToBack
     *            The elements to send to back (Node or Edge). These elements must be of the same type and have the same
     *            parent.
     * @return a command that is able to send to back many elements.
     * @throws UnsupportedOperationException
     *             in case of a not supported operation
     */
    Command buildSendToBackCommand(List<? extends View> elementsToSendToBack) throws UnsupportedOperationException;

    /**
     * Build a command that is able to bring forward many elements.
     * 
     * @param elementsToBringForward
     *            the elements to bring forward (Node or Edge). These elements must be of the same type and have the
     *            same parent.
     * @param index
     *            -1 for a default behavior (move forward of one element) or the new index to move element to (index
     *            mainly used for edges to move them on the next crossing edge over them for example)
     * 
     * @return a command that is able to bring forward many elements.
     * @throws UnsupportedOperationException
     *             in case of a not supported operation
     */
    Command buildBringForwardCommand(List<? extends View> elementsToBringForward, int index) throws UnsupportedOperationException;

    /**
     * Build a command that is able to send backward many elements.
     * 
     * @param elementsToSendBackward
     *            The elements to send backward (Node or Edge). These elements must be of the same type and have the
     *            same parent.
     * @param index
     *            -1 for a default behavior (move backward of one element) or the new index to move element to (index
     *            mainly used for edges to move them on the next crossing edge below them for example)
     * 
     * @return a command that is able to send backward many elements.
     * @throws UnsupportedOperationException
     *             in case of a not supported operation
     */
    Command buildSendBackwardCommand(List<? extends View> elementsToSendBackward, int index) throws UnsupportedOperationException;
}
