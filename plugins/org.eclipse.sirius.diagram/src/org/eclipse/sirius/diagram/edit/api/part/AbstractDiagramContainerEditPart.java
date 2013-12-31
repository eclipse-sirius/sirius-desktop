/*******************************************************************************
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.edit.api.part;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.util.Proxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.RubberbandDragTracker;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.edit.internal.validators.ResizeValidator;
import org.eclipse.sirius.diagram.internal.edit.policies.DNodeContainerItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.NonResizableAndNonDuplicableEditPolicy;
import org.eclipse.sirius.diagram.tools.internal.figure.ContainerWithTitleBlockFigure;
import org.eclipse.sirius.diagram.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.InvisibleResizableCompartmentFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerParallelogram;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerRectangleFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;

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
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshForegroundColor()
     */
    @Override
    protected void refreshForegroundColor() {
        super.refreshForegroundColor();
        DiagramContainerEditPartOperation.refreshForegroundColor(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshForegroundColor()
     */
    @Override
    protected void refreshBackgroundColor() {
        super.refreshBackgroundColor();
        DiagramContainerEditPartOperation.refreshBackgroundColor(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshFont() {
        super.refreshFont();
        DiagramContainerEditPartOperation.refreshFont(this);
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
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        DiagramContainerEditPartOperation.refreshVisuals(this);
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
            Command cmd = CommonEditPartOperation.appendSelectionCommand(super.getCommand(request), this);
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
     * Return a command that changes the bounds of a border items.
     * 
     * @param request
     *            the request.
     * @return the command that changes the bounds of a border items.
     */
    protected Command getResizeBorderItemCommand(final ChangeBoundsRequest request) {

        Command cmd = UnexecutableCommand.INSTANCE;

        boolean valid = true;
        ResizeValidator resizeValidator = new ResizeValidator(request);
        valid = resizeValidator.validate();

        if (valid && getMetamodelType().isInstance(this.resolveSemanticElement())) {
            final Iterator<?> iterEditParts = request.getEditParts().iterator();
            while (iterEditParts.hasNext()) {
                final Object next = iterEditParts.next();
                if (next instanceof IGraphicalEditPart) {
                    final IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) next;
                    //
                    // get the semantic element.
                    final EObject semantic = graphicalEditPart.resolveSemanticElement();
                    if (semantic instanceof DNode) {
                        final Dimension dimension = new Dimension();
                        final Point position = new Point();
                        if (graphicalEditPart.getNotationView() instanceof Node && ((Node) graphicalEditPart.getNotationView()).getLayoutConstraint() instanceof Size) {
                            final Size size = (Size) ((Node) graphicalEditPart.getNotationView()).getLayoutConstraint();
                            dimension.width = size.getWidth();
                            dimension.height = size.getHeight();
                        }
                        if (graphicalEditPart.getNotationView() instanceof Node && ((Node) graphicalEditPart.getNotationView()).getLayoutConstraint() instanceof Location) {
                            final Location location = (Location) ((Node) graphicalEditPart.getNotationView()).getLayoutConstraint();
                            position.x = location.getX();
                            position.y = location.getY();
                        }
                        final DNode viewNode = (DNode) semantic;
                        boolean collapsed = new DDiagramElementQuery(viewNode).isIndirectlyCollapsed();
                        if (!collapsed && (viewNode.getResizeKind() == ResizeKind.NSEW_LITERAL || viewNode.getResizeKind() == ResizeKind.NORTH_SOUTH_LITERAL)) {
                            dimension.height += request.getSizeDelta().height / getZoomManager().getZoom();
                            switch (request.getResizeDirection()) {
                            case PositionConstants.NORTH:
                            case PositionConstants.NORTH_WEST:
                            case PositionConstants.NORTH_EAST:
                                position.y -= request.getSizeDelta().height / getZoomManager().getZoom();
                                break;
                            default:
                                break;
                            }

                        }
                        if (!collapsed && (viewNode.getResizeKind() == ResizeKind.NSEW_LITERAL || viewNode.getResizeKind() == ResizeKind.EAST_WEST_LITERAL)) {
                            dimension.width += request.getSizeDelta().width / getZoomManager().getZoom();
                            switch (request.getResizeDirection()) {
                            case PositionConstants.WEST:
                            case PositionConstants.NORTH_WEST:
                            case PositionConstants.SOUTH_WEST:
                                position.x -= request.getSizeDelta().width / getZoomManager().getZoom();
                                break;
                            default:
                                break;
                            }
                        }
                        final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), "Resize", new EObjectAdapter(graphicalEditPart.getNotationView()), new Rectangle(position,
                                dimension));
                        cmd = new ICommandProxy(setBoundsCommand);
                    }
                }
            }
        }
        return cmd;
    }

    /**
     * Reinit the figure. It removes the current children of the main figure
     * (created with a previous style) and replace them with those created with
     * the current style.
     */
    public void reInitFigure() {
        final IFigure mainFigure = ((BorderedNodeFigure) getFigure()).getMainFigure();
        final List<IFigure> prevChildren = new ArrayList(mainFigure.getChildren());
        InvisibleResizableCompartmentFigure compartment = null;
        SiriusWrapLabel wrapLabel = null;
        final IFigure tmpFigure = createMainFigure();

        for (IFigure object : prevChildren) {
            if (object instanceof InvisibleResizableCompartmentFigure) {
                compartment = (InvisibleResizableCompartmentFigure) object;
            } else if (object instanceof ViewNodeContainerFigureDesc) {
                for (Object object2 : ((ViewNodeContainerFigureDesc) object).getChildren()) {
                    if (object2 instanceof SiriusWrapLabel) {
                        wrapLabel = (SiriusWrapLabel) object2;
                    }
                }
            }
            mainFigure.remove(object);
        }

        // Add figures from new style
        final Object[] tmpChildren = tmpFigure.getChildren().toArray();
        for (int i = 0; i < tmpChildren.length; i++) {
            if (tmpChildren[i] instanceof ViewNodeContainerFigureDesc) {
                final ViewNodeContainerFigureDesc figure = (ViewNodeContainerFigureDesc) tmpChildren[i];
                if (wrapLabel != null) {
                    for (IFigure child : new ArrayList<IFigure>(figure.getChildren())) {
                        if (child instanceof SiriusWrapLabel) {
                            figure.remove(child);
                        }
                    }
                    figure.add(wrapLabel);
                }
            }

            mainFigure.add((IFigure) tmpChildren[i], i);
        }
        if (compartment != null) {
            mainFigure.add(compartment);
        }
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

    private Option<LabelBorderStyleDescription> hasLabelBorderStyle(DStylizable viewNode) {
        if (viewNode.getStyle() instanceof FlatContainerStyle && ((FlatContainerStyle) viewNode.getStyle()).getDescription() instanceof FlatContainerStyleDescription) {
            FlatContainerStyleDescription fcsd = (FlatContainerStyleDescription) ((FlatContainerStyle) viewNode.getStyle()).getDescription();
            if (fcsd.getLabelBorderStyle() != null) {
                return Options.newSome(fcsd.getLabelBorderStyle());
            }
        }
        return Options.newNone();
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeContainerItemSemanticEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
        /*
         * remove the connection items display
         */
        removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated specific BorderItemEditPolicy and getCommand
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        LayoutEditPolicy lep = new org.eclipse.sirius.diagram.tools.api.policies.LayoutEditPolicy() {

            protected EditPolicy createChildEditPolicy(EditPart child) {
                if (child instanceof AbstractBorderItemEditPart) {
                    return ((AbstractBorderItemEditPart) child).getPrimaryDragEditPolicy();
                }
                EditPolicy result = child.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
                if (result == null) {
                    result = new NonResizableAndNonDuplicableEditPolicy();
                }
                return result;
            }

            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }

            /**
             * Redefines this method to allow the resizing of border items.
             * 
             * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCommand(org.eclipse.gef.Request)
             */
            public Command getCommand(final Request request) {
                if (REQ_RESIZE_CHILDREN.equals(request.getType()) && request instanceof ChangeBoundsRequest) {
                    final Command command = AbstractDiagramContainerEditPart.this.getResizeBorderItemCommand((ChangeBoundsRequest) request);
                    if (command != null) {
                        return command;
                    }
                }
                return super.getCommand(request);
            }
        };
        return lep;
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    public ViewNodeContainerFigureDesc getPrimaryShape() {
        return (ViewNodeContainerFigureDesc) primaryShape;
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated : handle shape container style.
     */
    protected IFigure createNodeShape() {
        final EObject eObj = resolveSemanticElement();
        IFigure shapeFigure = null;
        if (eObj instanceof DDiagramElementContainer) {
            final DDiagramElementContainer container = (DDiagramElementContainer) eObj;
            if (container.getOwnedStyle() != null) {
                if (container.getOwnedStyle() instanceof ShapeContainerStyle) {
                    shapeFigure = new ViewNodeContainerParallelogram();
                } else if (container.getOwnedStyle() instanceof WorkspaceImage) {
                    shapeFigure = new ViewNodeContainerRectangleFigureDesc();
                }
            }
        } else {
            deactivate();
        }
        if (shapeFigure == null) {
            shapeFigure = new GradientRoundedRectangle(DiagramContainerEditPartOperation.getCornerDimension(this), DiagramContainerEditPartOperation.getBackgroundStyle(this));
        }

        return shapeFigure;
    }

    /**
     * {@inheritDoc}
     */
    protected NodeFigure createNodePlate() {
        NodeFigure result;
        final EObject eObj = resolveSemanticElement();
        if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
            final DStylizable viewNode = (DStylizable) eObj;
            Option<LabelBorderStyleDescription> hasLabelBorderStyle = hasLabelBorderStyle(viewNode);
            if (hasLabelBorderStyle.some()) {
                result = new ContainerWithTitleBlockFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40), viewNode, hasLabelBorderStyle.get());
            } else {
                result = new DefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
            }
        } else {
            result = new DefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
        }

        /*
         * here we need to set a default size to 150, 70 because this was the
         * image size before the 4.0
         */
        if (result instanceof DefaultSizeNodeFigure && eObj instanceof DNodeContainer) {
            final DNodeContainer container = (DNodeContainer) eObj;
            if (container.getOwnedStyle() instanceof FlatContainerStyle && !new DDiagramElementContainerExperimentalQuery(container).isRegion()
                    && !new DNodeContainerExperimentalQuery(container).isRegionContainer()) {
                ((DefaultSizeNodeFigure) result).setDefaultSize(LayoutUtils.DEFAULT_CONTAINER_DIMENSION);
            }
        }

        return result;
    }
}
