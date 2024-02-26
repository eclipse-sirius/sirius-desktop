/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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

import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Proxy;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.LayoutHelper;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.graphical.figures.SiriusLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ShiftEdgeIdentityAnchorOperation;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewSizeHint;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

/**
 * The {@link AirXYLayoutEditPolicy}. import org.eclipse.sirius.transversal.command.GEFRecordedCommandWrapper;
 * 
 * @author ymortier
 */
public class AirXYLayoutEditPolicy extends XYLayoutEditPolicy {
    /** layout helper used to help locate shape created with undefined points */
    SiriusLayoutHelper layoutHelper;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy#getCreateCommand(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Command getCreateCommand(final CreateRequest request) {
        Command cmd = null;

        if (!isNoteCreationRequest(request)) {
            if (isLocationUndefined(request.getLocation())) {
                cmd = buildCommand(request);
            } else {
                if (getHost() instanceof GraphicalEditPart) {
                    ((GraphicalEditPart) getHost()).getFigure().translateToAbsolute(request.getLocation());
                }

                if (isLocationUndefined(request.getLocation())) {
                    cmd = buildCommand(request);
                }
            }
        }

        if (cmd == null) {
            cmd = super.getCreateCommand(request);
        }
        return cmd;
    }

    private boolean isNoteCreationRequest(final CreateRequest request) {
        if (request instanceof CreateViewRequest) {
            final List<?> viewDescriptors = ((CreateViewRequest) request).getViewDescriptors();
            if (!viewDescriptors.isEmpty()) {
                final CreateViewRequest.ViewDescriptor desc = (CreateViewRequest.ViewDescriptor) viewDescriptors.get(0);
                if (ViewType.NOTE.equals(desc.getSemanticHint())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isLocationUndefined(final Point location) {
        if (LayoutHelper.UNDEFINED.getLocation().equals(location)) {
            return true;
        }
        return location.x < 0 && location.y < 0;
    }

    /**
     * Create the command.
     * 
     * @param request
     *            the create request.
     * @param size
     *            the size of the new view.
     * @param location
     *            the location of the new view.
     * @return the command to execute.
     */
    private Command buildCommand(final CreateRequest request) {
        IGraphicalEditPart host = (IGraphicalEditPart) getHost();
        final TransactionalEditingDomain editingDomain = host.getEditingDomain();
        final CompositeTransactionalCommand cc = new CompositeTransactionalCommand(editingDomain, DiagramUIMessages.AddCommand_Label);
        if (request instanceof CreateViewRequest) {
            List<? extends ViewDescriptor> viewDescriptors = ((CreateViewRequest) request).getViewDescriptors();

            Point locationReference = null;
            for (int i = 0; i < viewDescriptors.size(); i++) {
                final CreateViewRequest.ViewDescriptor viewDescriptor = viewDescriptors.get(i);

                viewDescriptor.setPersisted(true);
                final Object object = getRealObject(viewDescriptor);
                Dimension size = null;
                Point location = null;
                boolean hasLayoutData = false;
                if (object instanceof DDiagramElement) {
                    if (object instanceof AbstractDNode) {
                        LayoutData layoutData = SiriusLayoutDataManager.INSTANCE.getData((AbstractDNode) object);
                        if (layoutData == null) {
                            layoutData = SiriusLayoutDataManager.INSTANCE.getData((AbstractDNode) object, true);
                        }
                        if (layoutData != null) {
                            size = layoutData.getSize();
                            location = layoutData.getLocation();
                            hasLayoutData = true;
                        }
                    }
                } else {
                    size = ViewSizeHint.getInstance().consumeSize();
                }
                if (size == null) {
                    size = LayoutUtils.getDefaultDimension(viewDescriptor);
                }

                // handle unknown location as default createview command
                if (location == null) {
                    Object constraintFor = getConstraintFor(request);
                    if (constraintFor instanceof Rectangle) {
                        location = ((Rectangle) constraintFor).getLocation().getCopy();
                    }
                }

                if (location != null) {
                    Point centerLocation = location.getCopy();
                    if (locationReference != null && !hasLayoutData) {
                        int padding = SiriusLayoutDataManager.PADDING;
                        if (GraphicalHelper.isSnapToGridEnabled(host)) {
                            padding = GraphicalHelper.getGridSpacing(host);
                        }
                        locationReference.translate(padding, padding);
                        centerLocation = locationReference.getCopy();
                    }

                    final Rectangle rect = new Rectangle(centerLocation, size);
                    cc.compose(new SetBoundsCommand(editingDomain, DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, rect));
                    if (hasLayoutData || (locationReference != null && viewDescriptors.size() > 1)) {
                        cc.compose(SiriusLayoutDataManager.INSTANCE.getAddAdapterMakerCommand(editingDomain, viewDescriptor));
                    }
                    if (hasLayoutData && i == 0) {
                        locationReference = location.getCopy();
                    }
                }

            }
        }
        // If no SetBoundsCommand is added return null
        if (cc.reduce() == null || cc.size() == 0) {
            return null;
        }

        final CompoundCommand gefCompoundCmd = new CompoundCommand();
        gefCompoundCmd.add(chainGuideAttachmentCommands(request, new ICommandProxy(cc.reduce())));

        return gefCompoundCmd;
    }

    /**
     * Retrieves the value of the <code>realObject</code> instance variable of this viewDescriptor.
     * 
     * @param viewDescriptor
     *            The view descriptor.
     * @return The value of the <code>realObject</code> instance variable or null if the adapter isn't a proxy.
     */
    private Object getRealObject(final ViewDescriptor viewDescriptor) {
        Object result = null;
        final IAdaptable adapt = viewDescriptor.getElementAdapter();
        if (adapt instanceof Proxy) {
            result = ((Proxy) adapt).getRealObject();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy#createChildEditPolicy(org.eclipse.gef.EditPart)
     */
    @Override
    protected EditPolicy createChildEditPolicy(final EditPart child) {
        EditPolicy childEditPolicy = new AirResizableEditPolicy();

        if (isRegionContainerCompartment(getHost())) {
            if (isRegionContainer(child)) {
                /*
                 * If the added child is a region and a region container.
                 */
                childEditPolicy = new RegionRegionContainerResizableEditPolicy();
            } else {
                /*
                 * If the current element is a region container, each added child will be considered as a region and
                 * will receive the expected resizable edit policy.
                 */
                childEditPolicy = new RegionResizableEditPolicy();
            }
        } else if (isRegionContainer(child)) {
            /* If the added element is a region container. */
            childEditPolicy = new RegionContainerResizableEditPolicy();
        }

        return childEditPolicy;
    }

    private boolean isRegionContainer(EditPart part) {
        if (part instanceof AbstractDiagramContainerEditPart) {
            return ((AbstractDiagramContainerEditPart) part).isRegionContainer();
        }
        return false;
    }

    private boolean isRegionContainerCompartment(EditPart part) {
        if (part instanceof AbstractDNodeContainerCompartmentEditPart) {
            return isRegionContainer(((AbstractDNodeContainerCompartmentEditPart) part).getParent());
        }
        return false;
    }

    private boolean isRegion(EditPart part) {
        if (part instanceof AbstractDiagramElementContainerEditPart) {
            return ((AbstractDiagramElementContainerEditPart) part).isRegion();
        }
        return false;
    }

    @Override
    protected Rectangle getCurrentConstraintFor(GraphicalEditPart child) {
        IFigure fig = child.getFigure();
        Object constraint = fig.getParent().getLayoutManager().getConstraint(fig);
        if (constraint instanceof Rectangle) {
            return (Rectangle) constraint;
        } else {
            return new Rectangle(0, 0, -1, -1);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Override to use a specific layoutHelper that get the center of the visible part of the container and not just the
     * center of the part of the container.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy#getConstraintFor(org.eclipse.gef.requests.CreateRequest)
     */
    @Override
    protected Object getConstraintFor(CreateRequest request) {

        Object constraint = super.getConstraintFor(request);

        if (SiriusLayoutHelper.UNDEFINED.getLocation().equals(request.getLocation())) {
            Rectangle rect = (Rectangle) constraint;
            rect.setLocation(getLayoutHelper().getReferencePosition(getHostFigure(), ((FigureCanvas) getHost().getViewer().getControl()).getViewport(), (IGraphicalEditPart) getHost()));
            Point point = getLayoutHelper().validatePosition(getHostFigure(), rect);
            rect.setLocation(point);
            return rect;
        }
        return constraint;
    }

    @Override
    protected Object getConstraintFor(Request request, GraphicalEditPart child, Rectangle rect) {
        Object constraintFor = super.getConstraintFor(request, child, rect);
        if (constraintFor instanceof Rectangle && isRegionContainer(child)) {
            Rectangle rectangle = (Rectangle) constraintFor;
            rectangle.setWidth(-1);
            rectangle.setHeight(-1);
        }
        return constraintFor;

    }

    /**
     * Return this layout helper.
     * 
     * @return this layout helper
     */
    private SiriusLayoutHelper getLayoutHelper() {
        if (layoutHelper == null) {
            layoutHelper = new SiriusLayoutHelper((IGraphicalEditPart) getHost());
        }
        return layoutHelper;
    }

    @Override
    protected Object getConstraintFor(ChangeBoundsRequest request, GraphicalEditPart child) {
        Rectangle constraint = (Rectangle) super.getConstraintFor(request, child);

        // If the request is a move request, there is no reason to override the size of the GMF Node with the current
        // figure size. In some cases, the GMF Bounds may have been updated but the figure has not been refreshed yet.
        // Note that the auto-size is a particular case (-1): during an arrange all, the figure constraint is set before
        // the GMF bounds.
        if (REQ_MOVE_CHILDREN.equals(request.getType()) && (constraint.width() != -1 && constraint.height() != -1)) {
            // We retrieve the Size of the GMF Node attached to the edit part.
            Optional<Size> optionalSize = Optional.ofNullable(child.getModel()).filter(Node.class::isInstance).map(model -> ((Node) model).getLayoutConstraint()).filter(Size.class::isInstance)
                    .map(Size.class::cast);
            if (optionalSize.isPresent()) {
                Dimension dimension = new Dimension(optionalSize.get().getWidth(), optionalSize.get().getHeight());
                constraint.setSize(dimension);
            }
        } else if (isRegion(child)) {
            // We change the constraint width and height only for width and height sizeDelta with non 0 value
            Optional<Size> optionalSize = Optional.ofNullable(child.getModel()).filter(Node.class::isInstance).map(model -> ((Node) model).getLayoutConstraint()).filter(Size.class::isInstance)
                    .map(Size.class::cast);
            if (optionalSize.isPresent()) {
                Dimension dimension = null;
                if (optionalSize.get().getHeight() < 0 && request.getSizeDelta().height == 0) {
                    dimension = new Dimension(constraint.width, -1);
                    constraint.setSize(dimension);
                } else if (optionalSize.get().getWidth() < 0 && request.getSizeDelta().width == 0) {
                    dimension = new Dimension(-1, constraint.height);
                    constraint.setSize(dimension);
                }
            }
        }

        return constraint;
    }

    /**
     * {@inheritDoc}
     * 
     * Override to keep the incoming or outgoing edges at the same location.
     * 
     * @see org.eclipse.gef.editpolicies.ConstrainedLayoutEditPolicy#
     *      getResizeChildrenCommand(org.eclipse.gef.requests.ChangeBoundsRequest)
     */
    @Override
    protected Command getResizeChildrenCommand(ChangeBoundsRequest request) {
        Command superCommand = super.getResizeChildrenCommand(request);

        EditPart host = getHost();

        if (host instanceof IGraphicalEditPart) {
            TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) host).getEditingDomain();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, superCommand.getLabel());
            ctc.add(new CommandProxy(superCommand));

            ShiftEdgeIdentityAnchorOperation operation = new ShiftEdgeIdentityAnchorOperation(request);
            ICommand command = CommandFactory.createICommand(editingDomain, operation);
            ctc.add(command);
            return new ICommandProxy(ctc);
        }
        return superCommand;
    }
}
