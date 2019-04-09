/*******************************************************************************
 * Copyright (c) 2008, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.edit.api.part;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.ResizableEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.util.Proxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.MappingHelper;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.AbstractDiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DefaultDirectEditOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.edit.internal.validators.ResizeValidator;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SpecificBorderItemSelectionEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewLocationHint;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IStyleConfigurationRegistry;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.StyleConfiguration;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.ext.gmf.runtime.editpolicies.SiriusSnapFeedbackPolicy;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.AirDefaultSizeNodeFigure;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.util.AnchorProvider;
import org.eclipse.swt.graphics.Color;

import com.google.common.collect.Iterables;

/**
 * The default behaviours of nodes, lists and containers.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramNodeEditPart extends AbstractBorderedDiagramElementEditPart implements IDiagramNodeEditPart {

    /** Remembers the last backgroundColor which has been set. */
    private Color bgColor;

    /**
     * Creates a new Node edit part.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramNodeEditPart(final View view) {
        super(view);
    }

    @Override
    protected void handleNotificationEvent(final Notification notification) {
        Set<EditPart> partToRefresh1 = DiagramElementEditPartOperation.handleNotificationEvent(this, notification, false);
        for (EditPart editPart : partToRefresh1) {
            editPart.refresh();
        }
        super.handleNotificationEvent(notification);
        Set<EditPart> partToRefresh2 = AbstractDiagramNodeEditPartOperation.handleNotificationEvent(this, notification, false);
        partToRefresh2.removeAll(partToRefresh1);
        for (EditPart editPart : partToRefresh2) {
            editPart.refresh();
        }
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        AbstractDiagramNodeEditPartOperation.createDefaultEditPolicies(this);
        // Add the Sirius feedback policy to have a the same lighter color for
        // guides of snap features for border nodes on Node
        installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE, new SiriusSnapFeedbackPolicy());
    }

    @Override
    public void refresh() {
        super.refresh();
        this.refreshVisuals();

        Iterable<EditPart> children = Iterables.filter(getChildren(), EditPart.class);
        for (EditPart childEditPart : children) {
            childEditPart.refresh();
        }
    }

    @Override
    protected List getModelChildren() {
        List<?> modelChildren = super.getModelChildren();
        EObject diagramElement = this.resolveDiagramElement();
        if (diagramElement instanceof DNode) {
            final DNode node = (DNode) diagramElement;
            if (node.getStyle() != null && ((NodeStyle) node.getStyle()).getLabelPosition() == LabelPosition.NODE_LITERAL || StringUtil.isEmpty(node.getName())) {
                DiagramNodeEditPartOperation.removeLabel(this, modelChildren);
            }
        }
        return modelChildren;
    }

    /**
     * Get the command to create Port.
     * 
     * @param originalCommand
     *            the orginal command, which will be wrapped in the new command
     * @param request
     *            the create view request
     * @return a command to create port which wrap the original command
     */
    protected Command getPortCreationCommand(final Command originalCommand, final CreateViewRequest request) {
        final CompositeCommand compositeCommand = new CompositeCommand(Messages.IAbstractDiagramNodeEditPart_createViewCommandLabel);
        compositeCommand.compose(new CommandProxy(originalCommand));
        final Iterator<?> iterDescriptor = request.getViewDescriptors().iterator();
        LayoutUtils.prepareFigureForDummyAdds(this.getBorderedFigure().getBorderItemContainer());
        while (iterDescriptor.hasNext()) {
            final ViewDescriptor viewDescriptor = (ViewDescriptor) iterDescriptor.next();
            final IAdaptable adapt = viewDescriptor.getElementAdapter();
            if (adapt instanceof Proxy) {
                final Object createdElement = ((Proxy) adapt).getRealObject();
                if (createdElement instanceof DNode) {
                    final EObject containerSemanticElement = this.resolveSemanticElement();
                    if (MappingHelper.getAllBorderedNodeMappings(((DNode) containerSemanticElement).getActualMapping()).contains(((DNode) createdElement).getActualMapping())) {
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

        if (valid) {
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
                        final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), Messages.IAbstractDiagramNodeEditPart_resizeCommandLabel,
                                new EObjectAdapter(graphicalEditPart.getNotationView()), new Rectangle(position, dimension));
                        cmd = new ICommandProxy(setBoundsCommand);
                    }
                }
            }
        }
        return cmd;
    }

    @Override
    public void refreshFigure() {
        IStyleEditPart styleEditPart = getStyleEditPart();
        if (styleEditPart != null) {
            DiagramNodeEditPartOperation.refreshFigure(styleEditPart);
        }
    }

    @Override
    protected void setBackgroundColor(final Color color) {
        if (bgColor != color) {
            bgColor = color;
            super.setBackgroundColor(color);
        }
    }

    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        DiagramNodeEditPartOperation.refreshVisuals(this);
    }

    @Override
    protected void refreshFont() {
        super.refreshFont();
        DiagramNodeEditPartOperation.refreshFont(this);
    }

    @Override
    protected void performDirectEditRequest(final Request request) {
        if (this.getChildren().isEmpty() || !(this.getChildren().get(0) instanceof IDiagramNameEditPart && this.getNodeLabel() != null)) {
            DefaultDirectEditOperation.performDirectEditRequest(this, request, this.getNodeLabel());
        } else {
            super.performDirectEditRequest(request);
        }
    }

    @Override
    public void performRequest(final Request request) {
        if (request instanceof DirectEditRequest || RequestConstants.REQ_DIRECT_EDIT.equals(request.getType())) {
            this.performDirectEditRequest(request);
        } else {
            super.performRequest(request);
        }
    }

    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        ConnectionAnchor result = null;
        if (request instanceof CreateConnectionRequest && getMainFigure() instanceof AirDefaultSizeNodeFigure) {
            final CreateConnectionRequest createConnectionRequest = (CreateConnectionRequest) request;
            final StyleConfiguration configuration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(this.resolveDiagramElement().getDiagramElementMapping(),
                    this.resolveDiagramElement().getStyle());
            if (configuration != null && createConnectionRequest.getLocation() != null) {
                final AnchorProvider anchorProvider = configuration.getAnchorProvider();
                if (anchorProvider != null) {
                    result = ((AirDefaultSizeNodeFigure) this.getMainFigure()).createAnchor(anchorProvider, new PrecisionPoint(createConnectionRequest.getLocation()));
                }
            }
        }
        if (result == null && request instanceof DropRequest) {
            /*
             * See comments in {@link AbstractDiagramListEditPart#getSourceConnectionAnchor(Request)}.
             */
            final DropRequest dr = (DropRequest) request;
            if (dr.getLocation() != null) {
                ViewLocationHint.getInstance().putData(ViewLocationHint.SOURCE_ANCHOR_LOCATION, dr.getLocation());
            } else {
                final Point realLocation = (Point) ViewLocationHint.getInstance().getData(ViewLocationHint.SOURCE_ANCHOR_LOCATION);
                result = getNodeFigure().getSourceConnectionAnchorAt(realLocation);
            }
        }
        if (result != null) {
            return result;
        } else {
            return super.getSourceConnectionAnchor(request);
        }
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        ConnectionAnchor result = null;
        if (request instanceof CreateConnectionRequest && getMainFigure() instanceof AirDefaultSizeNodeFigure && !(((CreateConnectionRequest) request).getSourceEditPart() instanceof NoteEditPart)) {
            final CreateConnectionRequest createConnectionRequest = (CreateConnectionRequest) request;
            final StyleConfiguration configuration = IStyleConfigurationRegistry.INSTANCE.getStyleConfiguration(this.resolveDiagramElement().getDiagramElementMapping(),
                    this.resolveDiagramElement().getStyle());
            if (configuration != null && createConnectionRequest.getLocation() != null) {
                final AnchorProvider anchorProvider = configuration.getAnchorProvider();
                if (anchorProvider != null) {
                    result = ((AirDefaultSizeNodeFigure) this.getMainFigure()).createAnchor(anchorProvider, new PrecisionPoint(createConnectionRequest.getLocation()));
                }
            }
        }
        if (result == null && request instanceof DropRequest) {
            /*
             * See comments in {@link AbstractDiagramListEditPart#getSourceConnectionAnchor(Request)}.
             */
            final DropRequest dr = (DropRequest) request;
            if (dr.getLocation() != null) {
                ViewLocationHint.getInstance().putData(ViewLocationHint.TARGET_ANCHOR_LOCATION, dr.getLocation());
            } else {
                final Point realLocation = (Point) ViewLocationHint.getInstance().getData(ViewLocationHint.TARGET_ANCHOR_LOCATION);
                result = getNodeFigure().getTargetConnectionAnchorAt(realLocation);
            }
        }
        if (result != null) {
            return result;
        } else {
            return super.getTargetConnectionAnchor(request);
        }
    }

    @Override
    public Command getCommand(final Request request) {
        Command result = null;
        RequestQuery requestQuery = new RequestQuery(request);
        if (requestQuery.isNoteCreationRequest() || requestQuery.isTextCreationRequest() || requestQuery.isNoteDropRequest() || requestQuery.isTextDropRequest()) {
            result = UnexecutableCommand.INSTANCE;
        } else {
            Command cmd = super.getCommand(request);
            result = CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, cmd);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @not-generated
     */
    @Override
    public EditPolicy getPrimaryDragEditPolicy() {
        final EditPolicy result = new SpecificBorderItemSelectionEditPolicy();

        final ResizableEditPolicy ep = (ResizableEditPolicy) result;
        final DDiagramElement dde = resolveDiagramElement();
        if (dde instanceof DNode) {
            DiagramNodeEditPartOperation.updateResizeKind(ep, (DNode) dde);
        }

        return result;
    }

    @Override
    public DragTracker getDragTracker(Request request) {
        return new NoCopyDragEditPartsTrackerEx(this);
    }
}
