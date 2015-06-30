/**
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsManager;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramDescriptionMappingsRegistry;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.api.query.DiagramDescriptionMappingManagerQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.DDiagramElementContainerSpecOperations;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ContainerDropDescription;
import org.eclipse.sirius.diagram.description.tool.DirectEditLabel;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.view.HideDDiagramElementLabel;
import org.eclipse.sirius.diagram.tools.api.command.view.RevealDDiagramElementsLabel;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusGMFHelper;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ResetStylePropertiesToDefaultValuesCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.handler.ChangeFilterActivation;
import org.eclipse.sirius.tests.support.command.CreateNoteAttachmentRecordingCommand;
import org.eclipse.sirius.tests.support.command.CreateNoteRecordingCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.DragSource;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Diagram specific generic test case.
 * 
 * @author mchauvin
 */
public class SiriusDiagramTestCase extends AbstractToolDescriptionTestCase {

    private static final String TOOL_NAME_INCORRECT = "The tool name is not correct";

    private static final String LAYER_NAME_INCORRECT = "The layer name is not correct (not found in the diagram description of this diagram)";

    private static final String FILTER_NAME_INCORRECT = "The filter name is not correct";

    private static final String MAPPING_NAME_INCORRECT = "The mapping name is not correct";

    private IDiagramCommandFactory commandFactory;

    @Override
    protected IDiagramCommandFactory getCommandFactory() {
        if (commandFactory == null) {
            commandFactory = DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
            commandFactory.setUserInterfaceCallBack(new NoUICallback());
        }
        return commandFactory;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                IViewPart outlineView = page.findView("org.eclipse.ui.views.ContentOutline");
                if (outlineView != null) {
                    page.hideView(outlineView);
                }
            }
        });
    }

    /**
     * Performs a arrange all request on diagramEditPart.
     * 
     * @param diagramEditPart
     *            the {@link DiagramEditPart} on to do the request
     */
    protected void arrangeAll(DiagramEditPart diagramEditPart) {
        ArrangeRequest arrangeRequest = new ArrangeRequest(ActionIds.ACTION_ARRANGE_ALL);
        List<Object> partsToArrange = new ArrayList<Object>();
        partsToArrange.add(diagramEditPart);
        arrangeRequest.setPartsToArrange(partsToArrange);
        diagramEditPart.performRequest(arrangeRequest);
    }

    /**
     * Apply a container creation tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param container
     *            the graphical container, for instance the diagram
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyContainerCreationTool(final String toolName, final DDiagram diagram, final EObject container) {
        final AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool instanceof ContainerCreationDescription) {
            final Command command = getCommand(container, tool);
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(container);
            boolean canExecute = command.canExecute();
            domain.getCommandStack().execute(command);
            return canExecute;
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.TOOL_NAME_INCORRECT);
    }

    /**
     * Apply a generic tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param container
     *            the graphical container, for instance the diagram
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyGenericTool(final String toolName, final DDiagram diagram, final EObject container) {
        final AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool instanceof ToolDescription) {
            final Command command = getCommand(container, tool);
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(container);
            boolean canExecute = command.canExecute();
            domain.getCommandStack().execute(command);
            return canExecute;
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.TOOL_NAME_INCORRECT);
    }

    /**
     * Apply a node creation tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param container
     *            the graphical container, for instance the diagram
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyNodeCreationTool(final String toolName, final DDiagram diagram, final EObject container) {
        final AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool != null) {
            final Command command = getCommand(container, tool);
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(container);
            boolean canExecute = command.canExecute();
            domain.getCommandStack().execute(command);
            return canExecute;
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.TOOL_NAME_INCORRECT);
    }

    /**
     * Apply a node creation tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param layerName
     *            the layer name
     * @param diagram
     *            the diagram
     * @param container
     *            the graphical container, for instance the diagram
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyNodeCreationTool(final String layerName, final String toolName, final DDiagram diagram, final EObject container) {
        boolean result = false;
        Layer layer = getLayer(diagram, layerName);
        if (layer != null) {
            final AbstractToolDescription tool = getTool(layer, toolName);
            if (tool != null) {
                Command command = getCommand(container, tool);
                result = command.canExecute();
                session.getTransactionalEditingDomain().getCommandStack().execute(command);
            }
        }
        return result;
    }

    /**
     * Apply a node creation tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param container
     *            the graphical container, for instance the diagram
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applySelectionTool(final String toolName, final DDiagram diagram, final EObject container) {
        boolean result = false;
        AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool != null) {
            Collection<EObject> selectedElements = getSelectedEObject(tool, diagram, container);
            Command command = getCommand(container, tool, selectedElements);
            result = command.canExecute();
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
        return result;
    }

    /**
     * Apply a node creation tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param layerName
     *            the layer name
     * @param diagram
     *            the diagram
     * @param container
     *            the graphical container, for instance the diagram
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applySelectionTool(final String layerName, final String toolName, final DDiagram diagram, final EObject container) {
        boolean result = false;
        Layer layer = getLayer(diagram, layerName);
        if (layer != null) {
            AbstractToolDescription tool = getTool(layer, toolName);
            if (tool != null) {
                Collection<EObject> selectedElements = getSelectedEObject(tool, diagram, container);
                Command command = getCommand(container, tool, selectedElements);
                result = command.canExecute();
                session.getTransactionalEditingDomain().getCommandStack().execute(command);
            }
        }
        return result;
    }

    /**
     * Delete from model the element represented by the edit part.
     * 
     * @param editParts
     *            the edit parts to delete
     */
    protected void delete(final EditPart... editParts) {
        final DeleteFromModelAction actionDelegate = new DeleteFromModelAction();
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        final GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
        for (EditPart editPart : editParts) {
            viewer.appendSelection(editPart);
        }
        TestsUtil.synchronizationWithUIThread();
        actionDelegate.run();
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Delete the edit part using the suppr keyboard touch.
     * 
     * @param editPart
     *            the edit part
     */
    @SuppressWarnings("restriction")
    protected void deleteViaKeyboard(final EditPart editPart) {
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        final DeleteWithHookAction actionDelegate = new DeleteWithHookAction(editor);
        final GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
        viewer.appendSelection(editPart);
        actionDelegate.update();
        actionDelegate.run();
    }

    /**
     * Delete from diagram the element represented by the edit part.
     * 
     * @param editPart
     *            the edit part
     */
    protected void deleteFromDiagram(final EditPart editPart) {
        final DeleteFromDiagramAction actionDelegate = new DeleteFromDiagramAction();
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        final GraphicalViewer viewer = (GraphicalViewer) editor.getAdapter(GraphicalViewer.class);
        viewer.appendSelection(editPart);
        final Action mockAction = new Action() {
            // Nothing to specialize
        };
        actionDelegate.selectionChanged(mockAction, viewer.getSelection());
        actionDelegate.run(mockAction);
    }

    /**
     * Drop a semantic instance in a view.
     * 
     * @param semanticElement
     *            the element to drop.
     * @param containerView
     *            the drop target view.
     * @param diagramElt
     *            the diagram element to drop.
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean dropSemantic(final EObject semanticElement, final DragAndDropTarget containerView, final DDiagramElement diagramElt) {
        boolean result = false;
        DragAndDropTargetDescription dragDragAndDropDescription = containerView.getDragAndDropDescription();
        if (dragDragAndDropDescription != null) {
            ContainerDropDescription dropTool = DDiagramElementContainerSpecOperations.getBestDropDescription(dragDragAndDropDescription, semanticElement, null,
                    ((DSemanticDecorator) containerView).getTarget(), containerView, DragSource.PROJECT_EXPLORER_LITERAL, diagramElt);
            if (dropTool != null) {
                Command command = getCommandFactory().buildDropInContainerCommandFromTool(containerView, semanticElement, dropTool);
                result = command.canExecute();
                session.getTransactionalEditingDomain().getCommandStack().execute(command);
            }
        }
        return result;
    }

    /**
     * Create a new note in the diagram.
     * 
     * @param diagram
     *            the diagram
     * @param noteText
     *            the note text
     * @return <code>true</code> if the note could be created,
     *         <code>false</code> otherwise
     */
    protected final boolean createNote(final DDiagram diagram, final String noteText) {
        boolean result = false;
        Diagram gmfDiagram = getGmfDiagram(diagram);
        Command createNoteCmd = new CreateNoteRecordingCommand(session.getTransactionalEditingDomain(), gmfDiagram, noteText);
        result = createNoteCmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(createNoteCmd);
        return result;
    }

    /**
     * Attach an existing note to a node.
     * 
     * @param noteText
     *            the note text to identify the note to attach
     * @param diagramElement
     *            the target diagram element
     * @return <code>true</code> if the note could be attached to the target
     *         node, <code>false</code> otherwise
     */
    protected final boolean attachNote(final String noteText, final DDiagramElement diagramElement) {
        boolean result = false;
        Node gmfNode = getGmfNode(diagramElement);
        Diagram gmfDiagram = GMFNotationHelper.getGMFDiagrams(gmfNode.eResource()).iterator().next();
        for (final Node note : GMFNotationHelper.getNotes(gmfDiagram)) {
            String noteDescription = GMFNotationHelper.getNoteDescription(note);
            if (noteText.equals(noteDescription)) {
                Command createNoteAttachmentCmd = new CreateNoteAttachmentRecordingCommand(session.getTransactionalEditingDomain(), note, gmfNode);
                result = createNoteAttachmentCmd.canExecute();
                session.getTransactionalEditingDomain().getCommandStack().execute(createNoteAttachmentCmd);
            }
        }
        return result;
    }

    /**
     * Get the selected objects.
     * 
     * @param tool
     *            the tool
     * @param diagram
     *            the diagram
     * @return the selected objects
     */
    protected Collection<EObject> getSelectedEObject(final AbstractToolDescription tool, final DDiagram diagram) {
        return getSelectedEObject(tool, diagram, diagram);
    }

    /**
     * Get the selected objects.
     * 
     * @param tool
     *            the tool
     * @param diagram
     *            the diagram
     * @param container
     *            the container
     * @return the selected objects
     */
    protected Collection<EObject> getSelectedEObject(final AbstractToolDescription tool, final DDiagram diagram, final EObject container) {
        return Collections.<EObject> emptyList();
    }

    /**
     * Get a selection command for the tool.
     * 
     * @param container
     *            the container
     * @param tool
     *            the tool
     * @param selectedElements
     *            the selected elements
     * @return the command build to execute the tool's operation on the given
     *         container
     */
    protected Command getCommand(final EObject container, final AbstractToolDescription tool, final Collection<EObject> selectedElements) {
        Command cmd = null;
        if (tool instanceof SelectionWizardDescription && container instanceof DSemanticDecorator) {
            cmd = getCommandFactory().buildSelectionWizardCommandFromTool((SelectionWizardDescription) tool, (DSemanticDecorator) container, selectedElements);
        } else if (tool instanceof OperationAction) {
            cmd = getCommandFactory().buildOperationActionFromTool((OperationAction) tool, Lists.newArrayList(Iterables.filter(selectedElements, DSemanticDecorator.class)));
        }
        return cmd;
    }

    /**
     * Apply an edge creation tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param source
     *            the graphical source element
     * @param target
     *            the graphical target element
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyEdgeCreationTool(final String toolName, final DDiagram diagram, final EdgeTarget source, final EdgeTarget target) {
        boolean result = false;
        AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool instanceof EdgeCreationDescription) {
            Command command = getCommandFactory().buildCreateEdgeCommandFromTool(source, target, (EdgeCreationDescription) tool);
            result = command.canExecute();
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
        return result;
    }

    /**
     * Apply an edge reconnection tool on a diagram.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param edge
     *            the edge
     * @param source
     *            the graphical source element
     * @param target
     *            the graphical target element
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyEdgeReconnectionTool(final String toolName, final DDiagram diagram, final DEdge edge, final EdgeTarget source, final EdgeTarget target) {
        boolean result = false;
        AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool instanceof ReconnectEdgeDescription) {
            Command command = getCommandFactory().buildReconnectEdgeCommandFromTool((ReconnectEdgeDescription) tool, edge, source, target);
            result = command.canExecute();
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
        return result;
    }

    /**
     * Applies the {@link ContainerDropDescription} with the given name, on the
     * given target container and the dropped dDiagram element. It simulates a
     * DDiagramElement drop from the same diagram.
     * 
     * @param diagram
     *            the diagram in which the tool should be applied
     * @param dndToolName
     *            the name of the {@link ContainerDropDescription} tool
     * @param dropContainer
     *            the container in which element should be dropped
     * @param droppedDDiagramElement
     *            the dropped {@link DDiagramElement} from a diagram.
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyContainerDropDescriptionTool(final DDiagram diagram, final String dndToolName, DragAndDropTarget dropContainer, DDiagramElement droppedDDiagramElement) {
        boolean result = false;
        AbstractToolDescription dndTool = getTool(diagram, dndToolName);
        if (dndTool instanceof ContainerDropDescription) {
            Command command = getCommandFactory().buildDropInContainerCommandFromTool(dropContainer, droppedDDiagramElement, (ContainerDropDescription) dndTool);
            result = command.canExecute();
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
        return result;
    }

    /**
     * Applies the {@link ContainerDropDescription} with the given name, on the
     * given target container and the dropped semantic element. It simulates a
     * semantic element drop from example the Model Explorer view.
     * 
     * @param diagram
     *            the diagram in which the tool should be applied
     * @param dndToolName
     *            the name of the {@link ContainerDropDescription} tool
     * @param dropContainer
     *            the container in which element should be dropped
     * @param droppedElement
     *            the dropped EObject (if the Drop is made from the Model
     *            content view) or the {@link DDiagramElement} if the drop is
     *            made from an existing {@link DDiagramElement}.
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyContainerDropDescriptionTool(final DDiagram diagram, final String dndToolName, DragAndDropTarget dropContainer, EObject droppedElement) {
        boolean result = false;
        AbstractToolDescription dndTool = getTool(diagram, dndToolName);
        if (dndTool instanceof ContainerDropDescription) {
            Command command = getCommandFactory().buildDropInContainerCommandFromTool(dropContainer, droppedElement, (ContainerDropDescription) dndTool);
            result = command.canExecute();
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
        return result;
    }

    /**
     * Apply a direct edit tool on a diagram element.
     * 
     * @param toolName
     *            the tool name
     * @param diagram
     *            the diagram
     * @param element
     *            the diagram element
     * @param value
     *            the new value to set
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyDirectEditTool(final String toolName, final DDiagram diagram, final DDiagramElement element, final String value) {
        boolean result = false;
        AbstractToolDescription tool = getTool(diagram, toolName);
        if (tool instanceof DirectEditLabel) {
            Command command = getCommandFactory().buildDirectEditLabelFromTool(element, (DirectEditLabel) tool, value);
            result = command.canExecute();
            session.getTransactionalEditingDomain().getCommandStack().execute(command);
        }
        return result;
    }

    /**
     * Apply a deletion tool on a diagram element.
     * 
     * @param element
     *            the diagram element
     * @return <code>true</code> if the tool could be applied,
     *         <code>false</code> otherwise
     */
    protected final boolean applyDeletionTool(final DDiagramElement element) {
        boolean result = false;
        Command command = getCommandFactory().buildDeleteDiagramElement(element);
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        return result;
    }

    /**
     * Searches the given {@link DDiagram} for a tool of the given name and
     * returns it.
     * 
     * @param diagram
     *            The diagram to search for a tool.
     * @param toolName
     *            The name of the searched tool (&quot;chapter&quot; for the
     *            &quot;Create new chapter&quot; tool).
     * @return The searched tool, <code>null</code> if it cannot be found.
     */
    protected final AbstractToolDescription getTool(final DDiagram diagram, final String toolName) {
        final List<AbstractToolDescription> tools = DiagramComponentizationTestSupport.getAllTools(session, diagram.getDescription());
        return getTool(tools, toolName);
    }

    /**
     * Searches the given {@link Layer} for a tool of the given name and returns
     * it.
     * 
     * @param layer
     *            The layer to search for a tool.
     * @param toolName
     *            The name of the searched tool (&quot;chapter&quot; for the
     *            &quot;Create new chapter&quot; tool).
     * @return The searched tool, <code>null</code> if it cannot be found.
     */
    protected final AbstractToolDescription getTool(final Layer layer, final String toolName) {
        return getTool(layer.getAllTools(), toolName);
    }

    private AbstractToolDescription getTool(final List<AbstractToolDescription> tools, final String toolName) {
        AbstractToolDescription theAbstractToolDescription = null;
        for (int i = 0; i < tools.size(); i++) {

            final AbstractToolDescription tool = tools.get(i);
            final String name = tool.getName();
            if (name != null && name.equals(toolName)) {
                theAbstractToolDescription = tool;
                break;
            }
        }
        return theAbstractToolDescription;
    }

    /**
     * Searches the given {@link DDiagram} for a layer of the given name and
     * returns it.
     * 
     * @param diagram
     *            The diagram to search for a tool.
     * @param layerName
     *            The name of the searched layer.
     * @return The retrieved layer, or <code>null</code> if it cannot be found.
     */
    protected final Layer getLayer(final DDiagram diagram, final String layerName) {
        final Collection<Layer> layers = DiagramComponentizationTestSupport.getAllLayers(session, diagram.getDescription());

        for (final Layer layer : layers) {
            if (layer.getName().equals(layerName)) {
                return layer;
            }
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.LAYER_NAME_INCORRECT);
    }

    /**
     * Searches the given {@link Layer} for a node mapping of the given name and
     * returns it.
     * 
     * @param layer
     *            The layer to search for a tool.
     * @param mappingName
     *            The name of the searched mapping.
     * @return The retrieved mapping, or throws an
     *         {@link IllegalArgumentException} if it cannot be found.
     */
    protected final NodeMapping getNodeMapping(final Layer layer, final String mappingName) {

        final DiagramDescriptionMappingsManager mappingsManager = DiagramDescriptionMappingsRegistry.INSTANCE.getDiagramDescriptionMappingsManager(session,
                SiriusDiagramTestCase.getParentDiagramDescription(layer));
        computeMapping(mappingsManager);

        final Set<DiagramElementMapping> allMappings = new DiagramDescriptionMappingManagerQuery(mappingsManager).computeAllMappings();
        final Iterable<NodeMapping> allNodeMappings = Iterables.filter(allMappings, NodeMapping.class);
        for (NodeMapping nodeMapping : allNodeMappings) {
            if (mappingName.equals(nodeMapping.getName()) && SiriusDiagramTestCase.getParentLayer(nodeMapping) == layer) {
                return nodeMapping;
            }
        }

        throw new IllegalArgumentException(SiriusDiagramTestCase.MAPPING_NAME_INCORRECT);
    }

    private void computeMapping(final DiagramDescriptionMappingsManager mappingsManager) {
        if (session != null) {
            mappingsManager.computeMappings(session.getSelectedViewpoints(false));
        } else {
            mappingsManager.computeMappings(null);
        }
    }

    /**
     * Searches the given {@link Layer} for an edge mapping of the given name
     * and returns it.
     * 
     * @param layer
     *            The layer to search for a tool.
     * @param mappingName
     *            The name of the searched mapping.
     * @return The retrieved mapping, or throws an
     *         {@link IllegalArgumentException} if it cannot be found.
     */
    protected final EdgeMapping getEdgeMapping(final Layer layer, final String mappingName) {
        final DiagramDescriptionMappingsManager mappingsManager = DiagramDescriptionMappingsRegistry.INSTANCE.getDiagramDescriptionMappingsManager(session,
                SiriusDiagramTestCase.getParentDiagramDescription(layer));
        for (final EdgeMapping edgeMapping : mappingsManager.getEdgeMappings()) {
            if (mappingName.equals(edgeMapping.getName())) {
                return edgeMapping;
            }
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.MAPPING_NAME_INCORRECT);
    }

    /**
     * Searches the given {@link Layer} for a node mapping of the given name and
     * returns it.
     * 
     * @param layer
     *            The layer to search for a tool.
     * @param mappingName
     *            The name of the searched mapping.
     * @return The retrieved mapping, or throws an
     *         {@link IllegalArgumentException} if it cannot be found.
     */
    protected final ContainerMapping getContainerMapping(final Layer layer, final String mappingName) {

        final DiagramDescriptionMappingsManager mappingsManager = DiagramDescriptionMappingsRegistry.INSTANCE.getDiagramDescriptionMappingsManager(session,
                SiriusDiagramTestCase.getParentDiagramDescription(layer));
        computeMapping(mappingsManager);

        final Set<DiagramElementMapping> allMappings = new DiagramDescriptionMappingManagerQuery(mappingsManager).computeAllMappings();
        final Iterable<ContainerMapping> allContainerMappings = Iterables.filter(allMappings, ContainerMapping.class);
        for (ContainerMapping containerMapping : allContainerMappings) {
            if (mappingName.equals(containerMapping.getName()) && SiriusDiagramTestCase.getParentLayer(containerMapping) == layer) {
                return containerMapping;
            }
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.MAPPING_NAME_INCORRECT);
    }

    /**
     * Browse the model upward (from the leaf to the root) and return the first
     * viewpoint found.
     * 
     * @param anyElement
     *            any {@link EObject} instance.
     * @return the viewpoint if found, null otherwise.
     */
    private static DiagramDescription getParentDiagramDescription(final EObject anyElement) {
        EObject current = anyElement;
        while (current != null) {
            current = current.eContainer();
            if (current instanceof DiagramDescription) {
                return (DiagramDescription) current;
            }
        }
        return null;
    }

    /**
     * return the layer which contains this mapping if available.
     * 
     * @param mapping
     *            the diagram element mapping
     * @return the layer containing if there is one, <code>null</code> otherwise
     */
    private static Layer getParentLayer(final DiagramElementMapping mapping) {
        EObject current = mapping;
        while (current != null) {
            current = current.eContainer();
            if (current instanceof Layer) {
                return (Layer) current;
            }
        }
        return null;
    }

    /**
     * Searches the given {@link DDiagram} for a filter of the given name and
     * returns it.
     * 
     * @param diagram
     *            The diagram to search for a tool.
     * @param filterName
     *            The name of the searched filter.
     * @return The retrieved filter, or <code>null</code> if it cannot be found.
     */
    protected final FilterDescription getFilter(final DDiagram diagram, final String filterName) {
        final Collection<FilterDescription> filters = diagram.getDescription().getFilters();

        for (final FilterDescription filter : filters) {
            if (filter.getName().equals(filterName)) {
                return filter;
            }
        }
        return null;
    }

    /**
     * Get a command for the tool.
     * 
     * @param container
     *            the container
     * @param tool
     *            the tool
     * @return the command build to execute the tool's operation on the given
     *         container
     */
    protected final Command getCommand(final EObject container, final AbstractToolDescription tool) {

        Command cmd = null;

        if (tool instanceof NodeCreationDescription) {
            if (container instanceof DDiagram) {
                cmd = getCommandFactory().buildCreateNodeCommandFromTool((DDiagram) container, (NodeCreationDescription) tool);
            } else if (container instanceof DDiagramElementContainer) {
                cmd = getCommandFactory().buildCreateNodeCommandFromTool((DDiagramElementContainer) container, (NodeCreationDescription) tool);
            } else if (container instanceof DNode) {
                cmd = getCommandFactory().buildCreateNodeCommandFromTool((DNode) container, (NodeCreationDescription) tool);
            }
        } else if (tool instanceof ContainerCreationDescription) {
            if (container instanceof DDiagram) {
                cmd = getCommandFactory().buildCreateContainerCommandFromTool((DDiagram) container, (ContainerCreationDescription) tool);
            } else if (container instanceof DDiagramElementContainer) {
                cmd = getCommandFactory().buildCreateContainerCommandFromTool((DDiagramElementContainer) container, (ContainerCreationDescription) tool);
            }
        } else if (tool instanceof ToolDescription) {
            cmd = getCommandFactory().buildGenericToolCommandFromTool(container, (ToolDescription) tool);
        }
        return cmd;
    }

    /**
     * Activate a layer.
     * 
     * @param dDiagram
     *            the {@link DDiagram}
     * @param layerName
     *            the layer name
     * @return <code>true</code> if the activation could be made,
     *         <code>false</code> otherwise
     */
    protected final boolean activateLayer(final DDiagram dDiagram, final String layerName) {
        Layer layer = getLayer(dDiagram, layerName);
        if (layer != null && !dDiagram.getActivatedLayers().contains(layer)) {
            Command changeLayersActivationCmd = new ChangeLayerActivationCommand(session.getTransactionalEditingDomain(), dDiagram, layer, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(changeLayersActivationCmd);
            return true;
        }
        return false;
    }

    /**
     * Deactivate a layer.
     * 
     * @param dDiagram
     *            the {@link DDiagram}
     * @param layerName
     *            the layer name
     * @return <code>true</code> if the deactivation could be made,
     *         <code>false</code> otherwise
     */
    protected final boolean deactivateLayer(final DDiagram dDiagram, final String layerName) {
        Layer layer = getLayer(dDiagram, layerName);
        if (layer != null && dDiagram.getActivatedLayers().contains(layer)) {
            Command changeLayersActivationCmd = new ChangeLayerActivationCommand(session.getTransactionalEditingDomain(), dDiagram, layer, new NullProgressMonitor());
            session.getTransactionalEditingDomain().getCommandStack().execute(changeLayersActivationCmd);
            return true;
        }
        return false;
    }

    /**
     * Activate a filter.
     * 
     * @param diagram
     *            the diagram
     * @param filterName
     *            the filter name
     * @return <code>true</code> if the activation could be made,
     *         <code>false</code> otherwise
     */
    protected final boolean activateFilter(final DDiagram diagram, final String filterName) {
        final FilterDescription filter = getFilter(diagram, filterName);
        if (filter != null) {
            return setFilterActivation(diagram, filter, true);
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.FILTER_NAME_INCORRECT);
    }

    /**
     * Deactivate a filter.
     * 
     * @param diagram
     *            the diagram
     * @param filterName
     *            the filter name
     * @return <code>true</code> if the deactivation could be made,
     *         <code>false</code> otherwise
     */
    protected final boolean deactivateFilter(final DDiagram diagram, final String filterName) {
        final FilterDescription filter = getFilter(diagram, filterName);
        if (filter != null) {
            return setFilterActivation(diagram, filter, false);
        }
        throw new IllegalArgumentException(SiriusDiagramTestCase.FILTER_NAME_INCORRECT);
    }

    private boolean setFilterActivation(final DDiagram diagram, final FilterDescription filter, final boolean visible) {
        final Runnable change = new ChangeFilterActivation((IDiagramWorkbenchPart) EclipseUIUtil.getActiveEditor(), diagram, filter, visible);
        change.run();
        return true;
    }

    /**
     * Hide the specified {@link DDiagramElement}'s label.
     * 
     * @param dDiagramElement
     *            the specified {@link DDiagramElement} for which hide the label
     */
    protected void hideLabel(DDiagramElement dDiagramElement) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command hideDDiagramElementLabelCmd = new HideDDiagramElementLabel(domain, Collections.singleton(dDiagramElement));
        domain.getCommandStack().execute(hideDDiagramElementLabelCmd);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Reveal the specified {@link DDiagramElement}'s label.
     * 
     * @param dDiagramElement
     *            the specified {@link DDiagramElement} for which reveal the
     *            label
     */
    protected void revealLabel(DDiagramElement dDiagramElement) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command revealDDiagramElementLabelCmd = new RevealDDiagramElementsLabel(domain, Collections.singleton(dDiagramElement));
        domain.getCommandStack().execute(revealDDiagramElementLabelCmd);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Get the diagram element from the semantic one.
     * 
     * @param diagram
     *            the diagram
     * @param semanticElement
     *            the semantic element
     * @return the first diagram element which has as target the semantic
     *         element given as parameter
     */
    protected final DDiagramElement getFirstDiagramElement(final DDiagram diagram, final EObject semanticElement) {
        return (DDiagramElement) getFirstRepresentationElement(diagram, semanticElement);
    }

    /**
     * Get the diagram element from the specified {@link EObject} semantic
     * element and the specified {@link DiagramElementMapping}.
     * 
     * @param diagram
     *            the diagram
     * @param semanticElement
     *            the semantic element
     * @param diagramElementMapping
     *            the specified {@link DiagramElementMapping}
     * @return the first diagram element which has as target the semantic
     *         element given as parameter and with the specified
     *         {@link DiagramElementMapping}
     */
    protected final DDiagramElement getFirstDiagramElement(final DDiagram diagram, final EObject semanticElement, final DiagramElementMapping diagramElementMapping) {
        DDiagramElement dDiagramElement = null;
        for (final DRepresentationElement element : diagram.getRepresentationElements()) {
            if (element.getTarget() == semanticElement && element.getMapping() == diagramElementMapping && element instanceof DDiagramElement) {
                dDiagramElement = (DDiagramElement) element;
                break;
            }
        }
        return dDiagramElement;
    }

    /**
     * Get the diagram element which owns the given label.
     * 
     * @param diagram
     *            the diagram on which to retrieve elements
     * @param label
     *            the label
     * @return a collection of diagram elements with the label
     */
    public List<DDiagramElement> getDiagramElementsFromLabel(final DDiagram diagram, final String label) {
        return getDiagramElementsFromLabel(diagram, label, DDiagramElement.class);
    }

    /**
     * Get the diagram element which owns the given label and are instances of
     * the given class.
     * 
     * @param <T>
     *            the class
     * @param diagram
     *            the diagram on which to retrieve elements
     * @param label
     *            the label
     * @param searchedClass
     *            the class
     * @return collection of searchedClass instances with the label
     */
    public <T extends DDiagramElement> List<T> getDiagramElementsFromLabel(final DDiagram diagram, final String label, final Class<T> searchedClass) {
        final List<T> found = Lists.newArrayList();
        final Iterator<EObject> it = diagram.eAllContents();
        while (it.hasNext()) {
            final EObject cur = it.next();
            if (searchedClass.isInstance(cur) && labelFeature(cur.eClass()) != null) {
                if (label.equals(getLabelValue(cur))) {
                    found.add(searchedClass.cast(cur));
                }
            }
        }
        return found;
    }

    private String getLabelValue(final EObject cur) {
        return (String) cur.eGet(labelFeature(cur.eClass()));
    }

    private EStructuralFeature labelFeature(final EClass class1) {
        return class1.getEStructuralFeature("name");
    }

    /**
     * Get the diagram element from the semantic one.
     * 
     * @param diagram
     *            the diagram
     * @param semanticElement
     *            the semantic element
     * @return the first diagram element which has as target the semantic
     *         element given as parameter
     */
    protected final DNode getFirstNodeElement(final DDiagram diagram, final EObject semanticElement) {
        return getFirstRepresentationElement(diagram, semanticElement, DNode.class);
    }

    /**
     * Get the diagram element from the semantic one.
     * 
     * @param diagram
     *            the diagram
     * @param semanticElement
     *            the semantic element
     * @return the first diagram element which has as target the semantic
     *         element given as parameter
     */
    protected final DEdge getFirstEdgeElement(final DDiagram diagram, final EObject semanticElement) {
        return getFirstRepresentationElement(diagram, semanticElement, DEdge.class);
    }

    /**
     * Check if an viewpoint element is visible in a diagram.
     * 
     * @param diagram
     *            the diagram
     * @param element
     *            the element
     * @return <code>true</code> if visible, <code>false</code> otherwise
     */
    protected static final boolean isVisible(final DDiagram diagram, final DDiagramElement element) {
        if (diagram == null) {
            throw new IllegalArgumentException("diagram should not be null");
        }
        if (element == null) {
            throw new IllegalArgumentException("element should not be null");
        }
        return DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, element);
    }

    /**
     * Get the GMF Diagram from the diagram.
     * 
     * @param diagram
     *            the diagram
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    protected Diagram getGmfDiagram(final DDiagram diagram) {
        return SiriusGMFHelper.getGmfDiagram(diagram, session);
    }

    /**
     * Get the GMF view from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    protected View getGmfView(final DDiagramElement diagramElement) {
        return SiriusGMFHelper.getGmfView(diagramElement, session);
    }

    /**
     * Get the GMF view from the diagram element and assert there is only one
     * GMF view for this diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @param clazz
     *            The type of the desired view
     * @param session
     *            the session to use
     * 
     * @return the view which has as element the diagram element given as
     *         parameter or null if any
     */
    protected static View getGmfViewAndAssertOnlyOne(final EObject diagramElement, final Class<? extends View> clazz, final Session session) {
        View result = null;
        if (diagramElement instanceof DSemanticDecorator) {

            final Session sessionToUse;
            if (session == null) {
                sessionToUse = new EObjectQuery(diagramElement).getSession();
            } else {
                sessionToUse = session;
            }

            if (sessionToUse != null) {
                final ECrossReferenceAdapter crossReference = sessionToUse.getSemanticCrossReferencer();
                for (final org.eclipse.emf.ecore.EStructuralFeature.Setting setting : crossReference.getInverseReferences(diagramElement)) {
                    if (clazz.isInstance(setting.getEObject()) && setting.getEStructuralFeature() == NotationPackage.eINSTANCE.getView_Element()) {
                        if (result != null) {
                            Assert.fail("We should have only one GMF view for a diagram element.");
                        }
                        result = (View) setting.getEObject();
                    }
                }
            }
        }
        return result;
    }

    /**
     * Get the GMF node from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the node which has as element the diagram element given as
     *         parameter or null if any
     */
    protected Node getGmfNode(final DDiagramElement diagramElement) {
        return SiriusGMFHelper.getGmfNode(diagramElement, session);
    }

    /**
     * Get the GMF edge from the diagram element.
     * 
     * @param diagramElement
     *            the diagram element
     * @return the edge which has as element the diagram element given as
     *         parameter or null if any
     */
    protected Edge getGmfEdge(final DDiagramElement diagramElement) {
        return SiriusGMFHelper.getGmfEdge(diagramElement, session);
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagramElement
     *            the diagram element
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagramElement diagramElement) {
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        TestsUtil.synchronizationWithUIThread();
        return getEditPart(diagramElement, editor);
    }

    /**
     * Get the editPart corresponding to this diagram <BR>
     * The editPart is search in the active editor.
     * 
     * @param diagram
     *            the diagram
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagram diagram) {
        final IEditorPart editor = EclipseUIUtil.getActiveEditor();
        return getEditPart(diagram, editor);
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagramElement
     *            the diagram element
     * @param editor
     *            the editor containing the editPart
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagramElement diagramElement, final IEditorPart editor) {
        final View gmfView = getGmfView(diagramElement);
        return getEditPart(gmfView, editor);
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param diagram
     *            the diagram
     * @param editor
     *            the editor containing the editPart
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    protected IGraphicalEditPart getEditPart(final DDiagram diagram, final IEditorPart editor) {
        final Diagram gmfDiagram = getGmfDiagram(diagram);
        return getEditPart(gmfDiagram, editor);
    }

    /**
     * Get the editPart corresponding to this diagram element.<BR>
     * The editPart is search in the active editor.
     * 
     * @param gmfView
     *            the gmf view
     * @param editor
     *            the editor containing the editPart
     * 
     * @return the editPart corresponding to the diagram element given as
     *         parameter or null if any
     */
    private IGraphicalEditPart getEditPart(final View gmfView, final IEditorPart editor) {
        IGraphicalEditPart result = null;
        if (gmfView != null && editor instanceof DiagramEditor) {
            final Map<?, ?> editPartRegistry = ((DiagramEditor) editor).getDiagramGraphicalViewer().getEditPartRegistry();
            final Object editPart = editPartRegistry.get(gmfView);
            if (editPart instanceof IGraphicalEditPart) {
                result = (IGraphicalEditPart) editPart;
            }
        }
        return result;
    }

    /**
     * Set the value of an attribute of a {@link DDiagram}.
     * 
     * @param domain
     *            the {@link EditingDomain}
     * @param diagram
     *            the {@link DDiagram}
     * @param eAttributeName
     *            the name of the attribute to set
     * @param newValue
     *            the new value of the attribute to set
     * @return <code>True</code> if the command was executable,
     *         <code>False</code> otherwise.
     * 
     */
    protected boolean setDDiagramAttribute(final EditingDomain domain, final DDiagram diagram, final String eAttributeName, final Object newValue) {
        boolean result = false;
        EClass eClass = diagram.eClass();
        List<EAttribute> eAttributes = eClass.getEAllAttributes();
        Iterator<EAttribute> iterator = eAttributes.iterator();
        EAttribute targetAttribute = null;
        while (iterator.hasNext() && targetAttribute == null) {
            final EAttribute eAttribute = iterator.next();
            if (eAttribute.getName().equals(eAttributeName)) {
                targetAttribute = eAttribute;
            }
        }
        Command setCmd = SetCommand.create(domain, diagram, targetAttribute, newValue);
        result = setCmd.canExecute();
        session.getTransactionalEditingDomain().getCommandStack().execute(setCmd);
        return result;
    }

    /**
     * Unsynchronize the diagram.
     * 
     * @param diagram
     *            the diagram
     */
    protected void unsynchronizeDiagram(final DDiagram diagram) {
        // Unsynchronized the diagram
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command setUnsynschronizedCmd = SetCommand.create(domain, diagram, DiagramPackage.Literals.DDIAGRAM__SYNCHRONIZED, false);
        domain.getCommandStack().execute(setUnsynschronizedCmd);
    }

    /**
     * Retrieve the label for the element.
     * 
     * @param diagramElement
     *            element to retrieve the label.
     * @return the label corresponding to element.
     */
    protected LabelEditPart getLabelEditPart(DDiagramElement diagramElement) {
        IGraphicalEditPart editPart = getEditPart(diagramElement);
        for (Object child : editPart.getChildren()) {
            if (child instanceof LabelEditPart) {
                return (LabelEditPart) child;
            }
        }
        return null;
    }

    /**
     * Checks that all label that should be hidden are effectively hidden in the
     * given diagram, and that there is no other hidden label.
     * 
     * @param diagram
     *            the diagram to test
     * @param elementsThatShouldHaveHiddenLabel
     *            the list of label that should be hidden in the given diagram
     */
    protected void checkForHiddenLabels(final DDiagram diagram, DDiagramElement... elementsThatShouldHaveHiddenLabel) {

        // We first get all the elements that should have visible labels
        HashSet<DDiagramElement> allDiagramElements = Sets.newHashSet(diagram.getOwnedDiagramElements());
        for (DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            Iterator<DDiagramElement> filter = Iterables.filter(diagramElement.eContents(), DDiagramElement.class).iterator();
            while (filter.hasNext()) {
                allDiagramElements.add(filter.next());
            }
        }
        SetView<DDiagramElement> elementsThatShouldHaveVisibleLabel = Sets.difference(allDiagramElements, Sets.newHashSet(elementsThatShouldHaveHiddenLabel));

        // And ensure that all these elements have visible labels
        for (DDiagramElement elementThatShouldHaveVisibleLabel : elementsThatShouldHaveVisibleLabel) {

            LabelEditPart labelEditPart = getLabelEditPart(elementThatShouldHaveVisibleLabel);
            TestCase.assertNotNull("This element's label should not be hidden : " + elementThatShouldHaveVisibleLabel, labelEditPart);
            TestCase.assertTrue("This element's label should not be hidden : " + elementThatShouldHaveVisibleLabel, labelEditPart.getFigure().isVisible());
        }

        // Then we ensure that all elements that should be hidden are
        // effectively hidden
        for (DDiagramElement elementThatShouldHaveHiddenLabel : elementsThatShouldHaveHiddenLabel) {
            LabelEditPart labelEditPart = getLabelEditPart(elementThatShouldHaveHiddenLabel);
            TestCase.assertTrue("This element's label should be hidden : " + elementThatShouldHaveHiddenLabel, labelEditPart == null || !labelEditPart.getFigure().isVisible());
        }
    }

    /**
     * Reset the style properties to default values.
     * 
     * @param dDiagramElement
     *            the diagram element to reset style
     * @param diagram
     *            the diagram
     */
    protected void resetStylePropertiesToDefaultValues(DDiagramElement dDiagramElement, DDiagram diagram) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Iterator<EObject> iterator = new EObjectQuery(dDiagramElement).getInverseReferences(NotationPackage.Literals.VIEW__ELEMENT).iterator();
        TestCase.assertTrue("The DDiagramElement : " + dDiagramElement + " must have a associated GMF View", iterator.hasNext());
        EObject next = iterator.next();
        TestCase.assertTrue("The DDiagramElement : " + dDiagramElement + " must have a associated GMF View", next instanceof View);
        View view = (View) next;
        Command command = new ResetStylePropertiesToDefaultValuesCommand(domain, diagram, Collections.singletonMap(view, dDiagramElement));
        domain.getCommandStack().execute(command);
    }

    /**
     * Passed file on read only status. Please note that some file systems might
     * not support setting the file as read only. This method will make the test
     * fail if we could mark the file as read only.
     * 
     * @param file
     *            the file to pass in read only status
     * @throws Exception
     *             the exception
     * @deprecated use {@link
     *             EclipseTestsSupportHelper.INSTANCE.setReadOnlyStatus(boolean,
     *             IFile...)} instead and explicitly check its effect.
     */
    @Deprecated
    protected void setReadOnly(IFile file) throws Exception {
        EclipseTestsSupportHelper.INSTANCE.setReadOnlyStatus(true, file);

        TestCase.assertTrue("The file must be read only", file.isReadOnly());
    }

    /**
     * Get file in the temporary project name with the fileName passed in
     * parameter.
     * 
     * @param fileName
     *            the file name
     * @return the file corresponding to parameter
     */
    protected IFile getFile(String fileName) {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(SiriusTestCase.TEMPORARY_PROJECT_NAME).getFile(fileName);
    }
}
