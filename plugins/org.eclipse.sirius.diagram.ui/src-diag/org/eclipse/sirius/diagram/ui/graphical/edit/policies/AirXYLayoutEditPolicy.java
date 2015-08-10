/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.LayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.graphical.figures.SiriusLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ShiftEdgeIdentityAnchorOperation;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewSizeHint;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

/**
 * The {@link AirXYLayoutEditPolicy}. import
 * org.eclipse.sirius.transversal.command.GEFRecordedCommandWrapper;
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
                    locationReference.translate(SiriusLayoutDataManager.PADDING, SiriusLayoutDataManager.PADDING);
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

        // If no SetBoundsCommand is added return null
        if (cc.reduce() == null || cc.size() == 0) {
            return null;
        }

        final CompoundCommand gefCompoundCmd = new CompoundCommand();
        gefCompoundCmd.add(chainGuideAttachmentCommands(request, new ICommandProxy(cc.reduce())));

        return gefCompoundCmd;
    }

    /**
     * Retrieves the value of the <code>realObject</code> instance variable of
     * this viewDescriptor.
     * 
     * @param viewDescriptor
     *            The view descriptor.
     * @return The value of the <code>realObject</code> instance variable or
     *         null if the adapter isn't a proxy.
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
            /*
             * If the current element is a region container, each added child
             * will be considered as a region and will receive the expected
             * resizable edit policy.
             */
            childEditPolicy = new RegionResizableEditPolicy();
        } else if (isRegionContainer(child)) {
            /* If the added element is a region container. */
            childEditPolicy = new RegionContainerResizableEditPolicy();
        }

        return childEditPolicy;
    }

    private boolean isRegionContainer(EditPart part) {
        if (part instanceof IDiagramContainerEditPart) {
            DDiagramElement element = ((IDiagramContainerEditPart) part).resolveDiagramElement();
            return isRegionContainer(element);
        }
        return false;
    }

    private boolean isRegionContainerCompartment(EditPart part) {
        if (part instanceof AbstractDNodeContainerCompartmentEditPart) {
            EObject element = ((AbstractDNodeContainerCompartmentEditPart) part).resolveSemanticElement();
            return isRegionContainer(element);
        }
        return false;
    }

    private boolean isRegionContainer(EObject element) {
        return element instanceof DNodeContainer && new DNodeContainerExperimentalQuery((DNodeContainer) element).isRegionContainer();
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
     * Override to use a specific layoutHelper that get the center of the
     * visible part of the container and not just the center of the part of the
     * container.
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
            layoutHelper = new SiriusLayoutHelper();
        }
        return layoutHelper;
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
