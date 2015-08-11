/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.edit.api.part;

import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.util.Proxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.RubberbandDragTracker;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.ResetOriginEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDiagramElementContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeContainerItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.api.policy.CompoundEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.LabelBorderStyleIds;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;

import com.google.common.collect.Iterables;

/**
 * Basic implementation of top Level type of Diagram Containers.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramContainerEditPart extends AbstractDiagramElementContainerEditPart implements IDiagramContainerEditPart {

    /**
     * Creates a new Container edit part.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramContainerEditPart(final View view) {
        super(view);
    }

    public Class<?> getMetamodelType() {
        return DNodeContainer.class;
    }

    /**
     * Indicates if the current edit part is a region of its parent.
     * 
     * @return true if this part is a region.
     */
    public boolean isRegionContainer() {
        DDiagramElement ddiagramElement = resolveDiagramElement();
        if (ddiagramElement instanceof DNodeContainer) {
            DNodeContainer ddec = (DNodeContainer) ddiagramElement;
            return new DNodeContainerExperimentalQuery(ddec).isRegionContainer();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        Command result = null;
        if (request.getType() != null && RequestConstants.REQ_PASTE.equals(request.getType())) {
            Iterable<ShapeCompartmentEditPart> shapeCompartmentChildren = Iterables.filter(children, ShapeCompartmentEditPart.class);
            if (shapeCompartmentChildren.iterator().hasNext()) {
                ShapeCompartmentEditPart lastShapeCompartmentEditPart = Iterables.getLast(shapeCompartmentChildren);
                result = lastShapeCompartmentEditPart.getCommand(request);
            }
            if (result == null) {
                result = super.getCommand(request);
            }
        } else {
            Command cmd = super.getCommand(request);
            result = CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, cmd);
        }
        return result;
    }

    /**
     * Get the command to create Port.
     * 
     * @param originalCommand
     *            the original command, which will be wrapped in the new command
     * @param request
     *            the create view request
     * @return a command to create port which wrap the original command
     */
    protected Command getPortCreationCommand(final Command originalCommand, final CreateViewRequest request) {
        final CompositeCommand compositeCommand = new CompositeCommand("Create View");
        compositeCommand.compose(new CommandProxy(originalCommand));
        final Iterator<? extends ViewDescriptor> iterDescriptor = request.getViewDescriptors().iterator();
        LayoutUtils.prepareFigureForDummyAdds(this.getBorderedFigure().getBorderItemContainer());
        while (iterDescriptor.hasNext()) {
            final ViewDescriptor viewDescriptor = iterDescriptor.next();
            final IAdaptable adapt = viewDescriptor.getElementAdapter();
            if (adapt instanceof Proxy) {
                final Object createdElement = ((Proxy) adapt).getRealObject();
                if (createdElement instanceof DNode) {
                    final EObject containerSemanticElement = this.resolveSemanticElement();
                    if (((DDiagramElementContainer) containerSemanticElement).getActualMapping().getAllBorderedNodeMappings().contains(((DNode) createdElement).getActualMapping())) {
                        //
                        // Create a port...
                        final Rectangle bounds = PortLayoutHelper.getBounds(this, (DNode) createdElement, viewDescriptor, (DDiagramElement) containerSemanticElement);
                        viewDescriptor.setPersisted(true);
                        compositeCommand.compose(new SetBoundsCommand(getEditingDomain(), DiagramUIMessages.SetLocationCommand_Label_Resize, viewDescriptor, bounds));
                        compositeCommand.compose(SiriusLayoutDataManager.INSTANCE.getAddAdapterMakerCommand(getEditingDomain(), viewDescriptor));
                    }
                }
            }
        }
        LayoutUtils.releaseDummys(this.getBorderedFigure().getBorderItemContainer());
        return new ICommandProxy(compositeCommand.reduce());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(final Request request) {
        if (request instanceof SelectionRequest && ((SelectionRequest) request).isAltKeyPressed()) {
            return new RubberbandDragTracker();
        }
        return new NoCopyDragEditPartsTrackerEx(this);
    }

    /**
     * Performs a direct edit request (usually by showing some type of editor).
     * Is required to have the same behavior as AbstractDiagramNodeEditPart
     * 
     * @param request
     *            the direct edit request
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.TopGraphicEditPart#performDirectEditRequest(org.eclipse.gef.requests.DirectEditRequest)
     */
    @Override
    protected void performDirectEditRequest(Request request) {
        if (request instanceof DirectEditRequest) {
            Request req = new Request();
            req.setType(org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT);
            super.performDirectEditRequest(req);
        } else if (org.eclipse.gef.RequestConstants.REQ_DIRECT_EDIT.equals(request.getType())) {
            super.performDirectEditRequest(request);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeContainerItemSemanticEditPolicy());
        /*
         * remove the connection items display
         */
        removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
        EditPolicy currentComponentEditPolicy = getEditPolicy(EditPolicy.COMPONENT_ROLE);
        if (currentComponentEditPolicy instanceof CompoundEditPolicy) {
            ResetOriginEditPolicy resetOriginEditPolicy = new ResetOriginEditPolicy();
            resetOriginEditPolicy.setHost(this);
            ((CompoundEditPolicy) currentComponentEditPolicy).addEditPolicy(resetOriginEditPolicy);
        } else {
            CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
            compoundEditPolicy.addEditPolicy(currentComponentEditPolicy);
            compoundEditPolicy.addEditPolicy(new ResetOriginEditPolicy());
            installEditPolicy(EditPolicy.COMPONENT_ROLE, compoundEditPolicy);
        }
    }

    @Override
    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        IFigure pane = null;
        if (editPart instanceof IBorderItemEditPart) {
            pane = getBorderedFigure().getBorderItemContainer();
        } else if (editPart instanceof AbstractDiagramElementContainerNameEditPart || isRegionContainer() || hasFullLabelBorder()) {
            // Add the name edit part to the content pane figure which is
            // currently the primary shape, see
            // AbstractDiagramElementContainerEditPart.createMainFigure().
            //
            // Also add all non border parts to the content pane if the current
            // part is a region container of define a full label border. This is
            // done to retrieve the "list"
            // behavior and layout.
            pane = getContentPane();
        } else {
            pane = super.getContentPaneFor(editPart);
        }
        return pane;
    }

    private boolean hasFullLabelBorder() {
        EObject element = resolveSemanticElement();
        if (element instanceof DDiagramElementContainer) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) element;
            if (ddec.getStyle() instanceof FlatContainerStyle && ddec.getStyle().getDescription() instanceof FlatContainerStyleDescription) {
                FlatContainerStyleDescription fcsd = (FlatContainerStyleDescription) ddec.getStyle().getDescription();
                return fcsd.getLabelBorderStyle() != null && LabelBorderStyleIds.LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID.equals(fcsd.getLabelBorderStyle().getId());
            }
        }
        return false;
    }

    @Override
    protected void handleNotificationEvent(Notification notification) {
        if (DiagramPackage.eINSTANCE.getDNodeContainer_ChildrenPresentation().equals(notification.getFeature())) {
            handleMajorSemanticChange();
        } else {
            super.handleNotificationEvent(notification);
        }
    }
}
