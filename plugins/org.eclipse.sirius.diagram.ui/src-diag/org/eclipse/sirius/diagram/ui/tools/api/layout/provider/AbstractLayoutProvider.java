/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.layout.provider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.profiler.ProfilerTask;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;

import com.google.common.collect.Iterables;

/**
 * An abstract layout provider.
 *
 * @author ymortier
 */
public abstract class AbstractLayoutProvider extends AbstractLayoutEditPartProvider {

    /**
     * Modeler Category.
     */
    public static final String GENERIC_MODELER_CAT = Messages.AbstractLayoutProvider_arrangeAllProfilerTaskCategory;

    /**
     * Arrange all.
     */
    public static final ProfilerTask ARRANGE_ALL = new ProfilerTask(GENERIC_MODELER_CAT, Messages.AbstractLayoutProvider_arrangeAllProfilerTaskName);

    /** Map all views with a its associated {@link ChangeBoundsRequest}. */
    protected Map<View, List<Request>> viewsToChangeBoundsRequest;

    /**
     * Create a new {@link AbstractLayoutProvider}.
     *
     */
    public AbstractLayoutProvider() {
        this.viewsToChangeBoundsRequest = new HashMap<View, List<Request>>();
    }

    /**
     * Set the map that maps all views with a its associated
     * {@link ChangeBoundsRequest}.
     *
     * @param viewsToChangeBoundsRequest
     *            Map all views with a its associated
     *            {@link ChangeBoundsRequest}.
     */
    public void setViewsToChangeBoundsRequest(final Map<View, List<Request>> viewsToChangeBoundsRequest) {
        this.viewsToChangeBoundsRequest = viewsToChangeBoundsRequest;
    }

    /**
     * Return the map that maps all views with a its associated
     * {@link ChangeBoundsRequest}.
     *
     * @return the map that maps all views with a its associated
     *         {@link ChangeBoundsRequest}.
     */
    public Map<View, List<Request>> getViewsToChangeBoundsRequest() {
        return viewsToChangeBoundsRequest;
    }

    @Override
    public final Command layoutEditParts(final GraphicalEditPart containerEditPart, final IAdaptable layoutHint) {
        DslCommonPlugin.PROFILER.startWork(ARRANGE_ALL);

        this.getViewsToChangeBoundsRequest().clear();

        /*
         * If we are asked to layout a whole diagram, check if a specific
         * provider has been registered for it (through the
         * org.eclipse.sirius.common.ui.airLayoutProvider extension point), and
         * use that provider if there is.
         */
        if (containerEditPart instanceof DiagramEditPart) {
            final DiagramEditPart diagramEditPart = (DiagramEditPart) containerEditPart;
            final LayoutProvider diagramLayoutProvider = getDiagramLayoutProvider(diagramEditPart, layoutHint);
            if (diagramLayoutProvider != null) {
                final Command command = getCommandFromDiagramLayoutProvider(diagramLayoutProvider, diagramEditPart, layoutHint);
                command.setLabel(Messages.AbstractLayoutProvider_arrangeAllCommandLabel);
                DslCommonPlugin.PROFILER.stopWork(ARRANGE_ALL);
                return command;
            }
        }

        final Command command = buildCommand(containerEditPart, layoutHint);
        command.setLabel(Messages.AbstractLayoutProvider_arrangeAllCommandLabel);
        this.getViewsToChangeBoundsRequest().clear();
        AbstractLayoutProvider.resetWrappedCommand(command);
        DslCommonPlugin.PROFILER.stopWork(ARRANGE_ALL);
        return command;
    }

    /**
     * Layout this list of selected objects, using the specified layout hint. The selected objects all reside within the
     * same parent container. Other elements that are part of the container but not specified in the list of objects,
     * are ignored.<BR/>
     * This method is similar to {@link #layoutEditParts(List, IAdaptable)} but with additional information concerning
     * <code>isArrangeAll</code>. Indeed, sometimes in caller we have this information and this avoids to recompute it
     * later. The default implementation does nothing specific but implementation can do specific behavior.
     * 
     * 
     * @param selectedObjects
     *            <code>List</code> of <code>EditPart</code> objects that are to be layed out.
     * @param layoutHint
     *            <code>IAdaptable</code> hint to the provider to determine the layout kind.
     * @param isArrangeAll
     *            true if the layout concerns an arrange of all elements of the diagram (ie the
     *            <code>selectedObjects</code> represent all the children of the diagram), false otherwise.
     * @return <code>Command</code> that when executed will layout the edit parts in the container
     */
    public Command layoutEditParts(List selectedObjects, IAdaptable layoutHint, boolean isArrangeAll) {
        return layoutEditParts(selectedObjects, layoutHint);
    }

    /**
     * Get the the diagram layout provider if there is one.
     *
     * @param diagramEditPart
     *            the diagram edit part
     * @param layoutHint
     *            the layout hint
     * @return the diagram layout provider if there is one, <code>null</code>
     *         otherwise
     */
    public LayoutProvider getDiagramLayoutProvider(final DiagramEditPart diagramEditPart, final IAdaptable layoutHint) {
        final LayoutProvider candidate = LayoutService.getProvider(diagramEditPart);
        if (candidate != null && candidate.isDiagramLayoutProvider()) {
            return candidate;
        } else {
            return null;
        }
    }

    private Command getCommandFromDiagramLayoutProvider(final LayoutProvider diagramLayoutProvider, final DiagramEditPart diagramEditPart, final IAdaptable layoutHint) {
        Command command = null;
        final AbstractLayoutEditPartProvider layoutProvider = diagramLayoutProvider.getLayoutNodeProvider(diagramEditPart);
        try {
            final Method method = layoutProvider.getClass().getMethod("layoutEditParts", new Class[] { GraphicalEditPart.class, IAdaptable.class }); //$NON-NLS-1$
            Assert.isNotNull(method);
            /* this code seems to avoid an infinite loop */
            if (method.getDeclaringClass() != AbstractLayoutProvider.class) {
                command = layoutProvider.layoutEditParts(diagramEditPart, layoutHint);
            }
        } catch (final SecurityException e) {
            SiriusTransPlugin.getPlugin().error(Messages.AbstractLayoutProvider_layoutError, e);
        } catch (final NoSuchMethodException e) {
            SiriusTransPlugin.getPlugin().error(Messages.AbstractLayoutProvider_layoutError, e);
        }
        if (command == null) {
            final List<?> children = diagramEditPart.getChildren();
            if (layoutProvider instanceof AbstractLayoutProvider) {
                command = ((AbstractLayoutProvider) layoutProvider).layoutEditParts(children, layoutHint, true);
            } else {
                command = layoutProvider.layoutEditParts(children, layoutHint);
            }
        }
        return command;
    }

    /**
     * Build the command which performs the layout of all the edit parts in the
     * specified container.
     * <p>
     * The resulting command is a compound command built recursively by calling
     * {@link #layoutEditParts(List, IAdaptable)} on all the descendants of the
     * specified container and then on the container itself.
     *
     * @param containerEditPart
     *            the container to layout.
     * @param layoutHint
     *            the hint to use.
     * @return a command to execute to perform the layout of the container and
     *         all its descendants.
     */
    private Command buildCommand(final GraphicalEditPart containerEditPart, final IAdaptable layoutHint) {
        final CompoundCommand cc = new CompoundCommand();
        // Depth-first recursion.
        for (final IGraphicalEditPart graphicalEditPart : Iterables.filter(containerEditPart.getChildren(), IGraphicalEditPart.class)) {
            final Command command = buildCommand(graphicalEditPart, layoutHint);
            if (command.canExecute()) {
                cc.add(command);
            }
        }
        // Base case: layout the container's direct children.
        final Command command = this.layoutEditParts(containerEditPart.getChildren(), layoutHint);
        if (command.canExecute()) {
            cc.add(command);
        }
        return cc;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    @Override
    public boolean provides(final IOperation operation) {
        return false;
    }

    /**
     * Search a request for the specified edit part and of the specified type.
     *
     * @param editPart
     *            the edit part.
     * @param requestType
     *            the type of the request.
     * @return the found request.
     */
    protected Request findRequest(final IGraphicalEditPart editPart, final Object requestType) {
        if (editPart != null) {
            return findRequest(editPart.getNotationView(), requestType);
        }
        return null;
    }

    /**
     * Search a request for the specified view and of the specified type.
     *
     * @param notationView
     *            the view.
     * @param requestType
     *            the type of the request.
     * @return the found request.
     */
    protected Request findRequest(final View notationView, final Object requestType) {
        final List<Request> requests = this.getViewsToChangeBoundsRequest().get(notationView);
        Request result = null;
        if (requests != null) {
            final Iterator<Request> iterRequests = requests.iterator();
            while (iterRequests.hasNext() && result == null) {
                final Request currentRequest = iterRequests.next();
                if (currentRequest.getType() != null && currentRequest.getType().equals(requestType)) {
                    result = currentRequest;
                }
            }
        }
        return result;
    }

    /**
     * Create a command with the specified request.
     *
     * @param request
     *            the request.
     * @param editPart
     *            the edit part.
     * @return an executable command.
     */
    protected Command buildCommandWrapper(final Request request, final EditPart editPart) {
        if (editPart instanceof IGraphicalEditPart) {
            List<Request> requests = this.getViewsToChangeBoundsRequest().get(((IGraphicalEditPart) editPart).getNotationView());
            if (requests == null) {
                requests = new LinkedList<Request>();
                this.getViewsToChangeBoundsRequest().put(((IGraphicalEditPart) editPart).getNotationView(), requests);
            }
            requests.add(request);
        }
        return new CommandWrapper(request, editPart);
    }

    /**
     * Tests whether an edit part should be considered as pinned (fixed size and
     * location) during the layout.
     *
     * @param part
     *            the edit part.
     * @return <code>true</code> if the edit part should be considered as
     *         pinned.
     */
    protected boolean isPinned(final IGraphicalEditPart part) {
        boolean isPinned = false;
        if (part.resolveSemanticElement() instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) part.resolveSemanticElement();
            isPinned = new PinHelper().isPinned(dDiagramElement);
        }
        return isPinned;
    }

    /**
     * Wraps a GMF Command.
     *
     * @author ymortier
     */
    protected static class CommandWrapper extends Command {

        /**
         * The wrapped command, initialized at the first call to
         * {@link #getWrappedCommand()}.
         */
        private Command wrappedCommand;

        /** The request. */
        private final Request request;

        /** The edit part. */
        private final EditPart editPart;

        /**
         * Create a new Command wrapper.
         *
         * @param request
         *            the request.
         * @param editPart
         *            the edit part.
         */
        public CommandWrapper(final Request request, final EditPart editPart) {
            this.request = request;
            this.editPart = editPart;
        }

        @Override
        public void execute() {
            this.getWrappedCommand().execute();
        }

        @Override
        public boolean canExecute() {
            return this.getWrappedCommand().canExecute();
        }

        @Override
        public boolean canUndo() {
            return this.getWrappedCommand().canUndo();
        }

        @Override
        public void undo() {
            this.getWrappedCommand().undo();
        }

        @Override
        public void redo() {
            this.getWrappedCommand().redo();
        }

        private Command getWrappedCommand() {
            if (wrappedCommand == null) {
                final Command cmd = editPart.getCommand(request);
                if (cmd == null) {
                    wrappedCommand = UnexecutableCommand.INSTANCE;
                } else {
                    wrappedCommand = cmd;
                }
            }
            return wrappedCommand;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getLabel() {
            return getWrappedCommand().getLabel();
        }

        /**
         * Returns the request that is causing the command.
         *
         * @return the request
         */
        public Request getRequest() {
            return request;
        }

        /**
         * Return the associated edit part.
         *
         * @return the editPart
         */
        public EditPart getEditPart() {
            return editPart;
        }
    }

    /**
     * Return the relative bounds of the specified edit part.
     *
     * @param graphicalEditPart
     *            the edit part.
     * @return the bounds of the specified edit part.
     */
    protected Rectangle getBounds(final IGraphicalEditPart graphicalEditPart) {
        Object existingRequest = this.findRequest(graphicalEditPart, org.eclipse.gef.RequestConstants.REQ_RESIZE);
        final Rectangle figureBounds = new Rectangle(graphicalEditPart.getFigure().getBounds());
        if (existingRequest instanceof ChangeBoundsRequest) {
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) existingRequest;
            if (changeBoundsRequest.getSizeDelta() != null) {
                adjustBounds(figureBounds, changeBoundsRequest);
            }
        }
        existingRequest = this.findRequest(graphicalEditPart, org.eclipse.gef.RequestConstants.REQ_MOVE);
        if (existingRequest instanceof ChangeBoundsRequest) {
            final ChangeBoundsRequest changeBoundsRequest = (ChangeBoundsRequest) existingRequest;
            if (changeBoundsRequest.getMoveDelta() != null) {
                figureBounds.setX(figureBounds.x + changeBoundsRequest.getMoveDelta().x);
                figureBounds.setY(figureBounds.y + changeBoundsRequest.getMoveDelta().y);
            }
        }
        return figureBounds;
    }

    private void adjustBounds(final Rectangle figureBounds, final ChangeBoundsRequest changeBoundsRequest) {
        switch (changeBoundsRequest.getResizeDirection()) {
        case PositionConstants.NORTH:
            figureBounds.setHeight(figureBounds.height + changeBoundsRequest.getSizeDelta().height);
            figureBounds.setY(figureBounds.y - changeBoundsRequest.getSizeDelta().height);
            break;
        case PositionConstants.SOUTH:
            figureBounds.setHeight(figureBounds.height + changeBoundsRequest.getSizeDelta().height);
            break;
        case PositionConstants.EAST:
            figureBounds.setWidth(figureBounds.width + changeBoundsRequest.getSizeDelta().width);
            break;
        case PositionConstants.WEST:
            figureBounds.setWidth(figureBounds.width + changeBoundsRequest.getSizeDelta().width);
            figureBounds.setX(figureBounds.x - changeBoundsRequest.getSizeDelta().width);
            break;
        case PositionConstants.NORTH_EAST:
            figureBounds.setHeight(figureBounds.height + changeBoundsRequest.getSizeDelta().height);
            figureBounds.setY(figureBounds.y - changeBoundsRequest.getSizeDelta().height);
            figureBounds.setWidth(figureBounds.width + changeBoundsRequest.getSizeDelta().width);
            break;
        case PositionConstants.NORTH_WEST:
            figureBounds.setHeight(figureBounds.height + changeBoundsRequest.getSizeDelta().height);
            figureBounds.setY(figureBounds.y - changeBoundsRequest.getSizeDelta().height);
            figureBounds.setWidth(figureBounds.width + changeBoundsRequest.getSizeDelta().width);
            figureBounds.setX(figureBounds.x - changeBoundsRequest.getSizeDelta().width);
            break;
        case PositionConstants.SOUTH_WEST:
            figureBounds.setHeight(figureBounds.height + changeBoundsRequest.getSizeDelta().height);
            figureBounds.setWidth(figureBounds.width + changeBoundsRequest.getSizeDelta().width);
            figureBounds.setX(figureBounds.x - changeBoundsRequest.getSizeDelta().width);
            break;
        default:
            figureBounds.setHeight(figureBounds.height + changeBoundsRequest.getSizeDelta().height);
            figureBounds.setWidth(figureBounds.width + changeBoundsRequest.getSizeDelta().width);
        }
    }

    /**
     * Return the first figure of type <code>type</code> that is the figure
     * <code>searchRoot</code> or a child of <code>searchRoot</code>.
     *
     * @param searchRoot
     *            the root figure to start the search.
     * @param type
     *            the type of the figure to return.
     * @return Return the first figure of type <code>type</code> that is the
     *         figure <code>searchRoot</code> or a child of
     *         <code>searchRoot</code>.
     */
    protected IFigure findChild(final IFigure searchRoot, final Class<?> type) {
        IFigure result = null;
        if (type.isInstance(searchRoot)) {
            result = searchRoot;
        }
        final Iterator<?> iterChildren = searchRoot.getChildren().iterator();
        while (iterChildren.hasNext() && result == null) {
            final IFigure child = (IFigure) iterChildren.next();
            result = findChild(child, type);
        }
        return result;
    }

    /**
     * Return all edit parts contained in root + root.
     *
     * @param root
     *            the root edit part
     * @return all edit parts contained in root + root.
     */
    public List<EditPart> getAllEditParts(final EditPart root) {
        final Set<EditPart> editParts = new HashSet<EditPart>();
        editParts.add(root);
        if (root instanceof GraphicalEditPart) {
            editParts.addAll(((GraphicalEditPart) root).getSourceConnections());
            editParts.addAll(((GraphicalEditPart) root).getTargetConnections());
        }
        final Iterator<EditPart> iterChildren = root.getChildren().iterator();
        while (iterChildren.hasNext()) {
            final EditPart next = iterChildren.next();
            editParts.addAll(getAllEditParts(next));
        }
        return new ArrayList<EditPart>(editParts);
    }

    private static void resetWrappedCommand(final Command command) {
        if (command instanceof CommandWrapper) {
            ((CommandWrapper) command).wrappedCommand = null;
        } else if (command instanceof CompoundCommand) {
            final Object[] children = ((CompoundCommand) command).getChildren();
            for (Object element : children) {
                if (element instanceof Command) {
                    AbstractLayoutProvider.resetWrappedCommand((Command) element);
                }
            }
        }
    }
}
