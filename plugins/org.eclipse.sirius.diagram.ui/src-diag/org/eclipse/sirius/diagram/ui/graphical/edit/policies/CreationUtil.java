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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.Objects;
import java.util.function.Function;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.ContainerCreationDescriptionQuery;
import org.eclipse.sirius.diagram.business.api.query.NodeCreationDescriptionQuery;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.RequestDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.AbstractSelectionWizardCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.PaneBasedSelectionWizardCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.SelectionWizardCommand;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Useful operations for {@link NodeCreationEditPolicy} and {@link ContainerCreationEditPolicy}.
 * 
 * @author ymortier
 */
public class CreationUtil {

    /** The location of the clicked point. */
    private final Point realLocation;

    /** The computed size of the element to create. */
    private final Dimension realSize;

    /** The EMF Command Factory. */
    private final IDiagramCommandFactory emfCommandFactory;

    /** The request. */
    private final CreateRequest request;

    /** The edit part. */
    private final EditPart editPart;
    
    /** The real container for stack composition. */
    private EditPart stackContainer;
    
    /** The location in stack container. */
    private Point locationInContainer;

    /**
     * Creates a new <code>CreationUtil</code> with the specified request and location.
     * 
     * @param request
     *            the request.
     * @param commandFactory
     *            the emf command factory.
     * @param realLocation
     *            the location of the clicked point.
     * @param editPart
     *            the edit part
     * @since 0.9.0
     */
    public CreationUtil(final CreateRequest request, final IDiagramCommandFactory commandFactory, final Point realLocation, final EditPart editPart) {
        this(request, commandFactory, realLocation, null, editPart);
    }

    /**
     * Creates a new <code>CreationUtil</code> with the specified request and location.
     * 
     * @param request
     *            the request.
     * @param commandFactory
     *            the emf command factory.
     * @param realLocation
     *            the location of the clicked point.
     * @param realSize
     *            the computed size of the element to create, null if the default size must be used
     * @param editPart
     *            the edit part
     * @since 0.9.0
     */
    public CreationUtil(final CreateRequest request, final IDiagramCommandFactory commandFactory, final Point realLocation, final Dimension realSize, final EditPart editPart) {
        // Reminder: realSize is used in Sequence Diagram.
        // It should be a sub-case in this plugin.
        this.realLocation = realLocation;
        this.realSize = realSize;
        this.request = request;
        this.emfCommandFactory = commandFactory;
        this.editPart = editPart;
    }

    /**
     * Sets the stackContainer context for part in stack list.
     * 
     * @param location of creation based on container.
     * @param stackContainer context for creation.
     */
    public void setStackContainer(final Point location, EditPart container) {
        this.stackContainer = Objects.requireNonNull(container);
        this.locationInContainer = Objects.requireNonNull(location);
    }
    
    private CompoundCommand wrapCommandWithLayout(AbstractToolDescription tool, final org.eclipse.emf.common.command.Command emfCommand) {
        final CompoundCommand compoundCommand = new CompoundCommand(tool.getName());

        compoundCommand.add(createLayoutDataCommand());
        compoundCommand.add(new ICommandProxy(new GMFCommandWrapper(getEditingDomain(), emfCommand)));
        return compoundCommand;
    }
    
    /**
     * Returns a command that is able to create a node in the specified container with the specified tool.
     * 
     * @param container
     *            the container.
     * @param tool
     *            the node creation description tool.
     * @return a command that is able to create a node in the specified container with the specified tool.
     */
    public Command getNodeCreationCommand(final DDiagramElementContainer container, final NodeCreationDescription tool) {
        if (new NodeCreationDescriptionQuery(tool).canCreateIn(container)) {
            return wrapCommandWithLayout(tool, emfCommandFactory.buildCreateNodeCommandFromTool(container, tool));
        }
        return null;
    }

    /**
     * Returns a command that is able to create a node in the specified node with the specified tool.
     * 
     * @param node
     *            the node.
     * @param tool
     *            the node creation description tool.
     * @return a command that is able to create a node in the specified node with the specified tool.
     */
    public Command getNodeCreationCommand(final DNode node, final NodeCreationDescription tool) {
        if (new NodeCreationDescriptionQuery(tool).canCreateIn(node)) {
            return wrapCommandWithLayout(tool, emfCommandFactory.buildCreateNodeCommandFromTool(node, tool));
        }
        return null;
    }

    /**
     * Returns a command that is able to create a node in the specified viewpoint with the specified tool.
     * 
     * @param diagram
     *            the diagram.
     * @param tool
     *            the node creation description tool.
     * @return a command that is able to create a node in the specified viewpoint with the specified tool.
     */
    public Command getNodeCreationCommand(final DDiagram diagram, final NodeCreationDescription tool) {
        if (new NodeCreationDescriptionQuery(tool).canCreateIn(diagram)) {
            return wrapCommandWithLayout(tool, emfCommandFactory.buildCreateNodeCommandFromTool(diagram, tool));
        }
        return null;
    }

    /**
     * Returns a command that is able to create a container in the specified container with the specified tool.
     * 
     * @param viewNodeContainer
     *            the container
     * @param ccdTool
     *            the container creation description tool.
     * @return a command that is able to create a container in the specified container with the specified tool.
     */
    public Command getContainerCreationDescription(final DDiagramElementContainer viewNodeContainer, final ContainerCreationDescription ccdTool) {
        if (new ContainerCreationDescriptionQuery(ccdTool).canCreateIn(viewNodeContainer)) {
            return wrapCommandWithLayout(ccdTool, emfCommandFactory.buildCreateContainerCommandFromTool(viewNodeContainer, ccdTool));
        }
        return null;
    }

    /**
     * Returns a command that is able to create a container in the specified viewpoint with the specified tool.
     * 
     * @param diagram
     *            the diagram.
     * @param ccdTool
     *            the container creation description tool.
     * @return a command that is able to create a container in the specified viewpoint with the specified tool.
     */
    public Command getContainerCreationDescription(final DDiagram diagram, final ContainerCreationDescription ccdTool) {
        if (new ContainerCreationDescriptionQuery(ccdTool).canCreateIn(diagram)) {
            return wrapCommandWithLayout(ccdTool, emfCommandFactory.buildCreateContainerCommandFromTool(diagram, ccdTool));
        }
        return null;
    }

    /**
     * Returns a command that is able to launch a wizard tool.
     * 
     * @param selectionTool
     *            the wizard description.
     * @param containerView
     *            the container view.
     * @return a command that is able to launch a wizard tool.
     */
    public Command getSelectionWizardCommand(final SelectionWizardDescription selectionTool, final EObject containerView) {
        return getSelectionWizardCommand(selectionTool, containerView, input ->
            new SelectionWizardCommand(emfCommandFactory, selectionTool, input, (DSemanticDecorator) containerView));
    }

    /**
     * Returns a command that is able to launch a wizard tool.
     * 
     * @param selectionTool
     *            the wizard description.
     * @param containerView
     *            the container view.
     * @return a command that is able to launch a wizard tool.
     */
    public Command getPaneBasedSelectionWizardCommand(final PaneBasedSelectionWizardDescription selectionTool, final EObject containerView) {
        return getSelectionWizardCommand(selectionTool, containerView, input ->
            new PaneBasedSelectionWizardCommand(emfCommandFactory, selectionTool, input, (DSemanticDecorator) containerView));
    }

    private Command getSelectionWizardCommand(final AbstractToolDescription selectionTool, final EObject containerView, 
            final Function<TreeItemWrapper, AbstractSelectionWizardCommand> commandFactory) {
        if (containerView != null) {
            final TreeItemWrapper input = new TreeItemWrapper(null, null);
            if (AbstractSelectionWizardCommand.canCreateCommand(selectionTool, containerView, input)) {
                return wrapCommandWithLayout(selectionTool, commandFactory.apply(input));
            }
        }
        return UnexecutableCommand.INSTANCE;   
    }
    
    private TransactionalEditingDomain getEditingDomain() {
        if (editPart instanceof IGraphicalEditPart) {
            return ((IGraphicalEditPart) editPart).getEditingDomain();
        }
        return null;
    }

    /**
     * Returns a command that is able to launch an user request.
     * 
     * @param requestDescription
     *            the request description tool.
     * @param host
     *            the host.
     * @return a command that is able to launch an user request.
     */
    public Command getRequestToolCommand(final RequestDescription requestDescription, final EditPart host) {
        final Request req = new Request();
        req.setType(requestDescription.getType());

        //
        // Check the precondition.
        if (requestDescription.getPrecondition() != null && !StringUtil.isEmpty(requestDescription.getPrecondition()) && host instanceof IGraphicalEditPart) {
            EObject semantic = ((IGraphicalEditPart) host).resolveSemanticElement();
            if (semantic instanceof DSemanticDecorator) {
                semantic = ((DSemanticDecorator) semantic).getTarget();
            }
            boolean valid = false;
            final IInterpreter acceleoInterpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
            acceleoInterpreter.setVariable(IInterpreterSiriusVariables.CONTAINER, semantic);
            try {
                valid = acceleoInterpreter.evaluateBoolean(semantic, requestDescription.getPrecondition());
            } catch (final EvaluationException e) {
                RuntimeLoggerManager.INSTANCE.error(requestDescription, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
            }
            acceleoInterpreter.unSetVariable(IInterpreterSiriusVariables.CONTAINER);
            if (!valid) {
                return null; // the precondition is not checked.
            }
        }

        // wrap the command because the command will start the
        // wizard in its creation
        // see FunctionalChainElementsCreationEditPolicy
        final Command cmd = new RequestToolCommand(req, requestDescription, host);
        return cmd;
    }

    /**
     * Returns <code>true</code> if a node can be created on the container with the specified tool. Returns a command
     * that is able to execute a generic {@link ToolDescription}.
     * 
     * @param containerView
     *            the view element on which the tool has been invoked.
     * @param toolDesc
     *            the tool to invoke.
     * @return a command which executes the specified {@link ToolDescription} in the context of the view element.
     */
    public Command getGenericToolCommand(final EObject containerView, final ToolDescription toolDesc) {
        return wrapCommandWithLayout(toolDesc, emfCommandFactory.buildGenericToolCommandFromTool(containerView, toolDesc));
    }

    private Command createLayoutDataCommand() {
        return new Command() {
            @Override
            public void execute() {
                // The size of the request take into account the zoom (got
                // the size in 100%)
                Dimension size = null;
                if (realSize != null) {
                    size = realSize.getCopy();
                } else {
                    size = adaptRequestSizeToZoomFactor();
                }
                SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(editPart, realLocation.getCopy(), size));
                if (stackContainer != null) {
                    SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(stackContainer, locationInContainer.getCopy(), size));                    
                }
            }
        };
    }

    /**
     * Transform the size depending the zoom factor.
     * 
     * @return A new dimension
     */
    private Dimension adaptRequestSizeToZoomFactor() {
        if (request.getSize() == null) {
            return null;
        }
        final Dimension dimension = request.getSize().getCopy();
        // The size of the request take into account the zoom (got
        // the size in 100%)
        double scale = 1.0;
        if (editPart.getRoot() instanceof DiagramRootEditPart) {
            final ZoomManager zoomManager = ((DiagramRootEditPart) editPart.getRoot()).getZoomManager();
            scale = zoomManager.getZoom();
        }
        dimension.performScale(1 / scale);
        return dimension;
    }

    /**
     * A command that handle a RequestTool.
     * 
     * @author Obeo</a>
     */
    private static class RequestToolCommand extends Command {

        private Command wrappedCmd;

        private final EditPart host;

        private final RequestDescription requestDescription;

        private final Request req;

        /**
         * Constructor.
         * 
         * @param req
         *            the request
         * @param desc
         *            teh request description
         * @param host
         *            the graphical edit part
         */
        RequestToolCommand(final Request req, final RequestDescription desc, final EditPart host) {
            super(desc.getName());
            this.req = req;
            this.requestDescription = desc;
            this.host = host;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gef.commands.Command#execute()
         */
        @Override
        public void execute() {
            wrappedCmd = host.getCommand(req);
            final CompoundCommand cc = new CompoundCommand();
            cc.add(wrappedCmd);
            if (requestDescription.isForceRefresh()) {
                final Command refresh = this.getRefreshSiriusCommand();
                if (refresh != null) {
                    cc.add(refresh);
                }
            }
            wrappedCmd = cc;
            if (wrappedCmd != null && wrappedCmd.canExecute()) {
                wrappedCmd.execute();
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gef.commands.Command#canUndo()
         */
        @Override
        public boolean canUndo() {
            if (wrappedCmd != null) {
                return wrappedCmd.canUndo();
            }
            return false;
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        @Override
        public void undo() {
            if (wrappedCmd != null) {
                wrappedCmd.undo();
            }
        }

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.gef.commands.Command#redo()
         */
        @Override
        public void redo() {
            if (wrappedCmd != null) {
                wrappedCmd.redo();
            }
        }

        /**
         * Return the command that refreshes the viewpoint.
         * 
         * @return the command that refreshes the viewpoint.
         */
        private Command getRefreshSiriusCommand() {
            EditPart current = host;
            while (!(current instanceof DDiagramEditPart) && current != null) {
                current = current.getParent();
            }
            if (current != null) {
                final Request requ = new Request(RequestConstants.REQ_REFRESH_VIEWPOINT);
                return current.getCommand(requ);
            } else {
                return null;
            }
        }
    }

}
