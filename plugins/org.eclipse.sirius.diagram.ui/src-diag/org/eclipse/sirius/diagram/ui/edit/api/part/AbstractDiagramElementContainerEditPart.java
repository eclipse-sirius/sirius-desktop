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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeContainerQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.AbstractDiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.validators.ResizeValidator;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDiagramElementContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.NonResizableAndNonDuplicableEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.canonicals.DumnySiriusCanonicalEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewLocationHint;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AlphaDropShadowBorder;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FoldingToggleAwareClippingStrategy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FoldingToggleImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerParallelogram;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerRectangleFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.ContainerWithTitleBlockFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.RegionRoundedGradientRectangle;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.RoundedCornerMarginBorder;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Basic implementation of top Level type of Diagram an List Containers.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramElementContainerEditPart extends AbstractBorderedDiagramElementEditPart implements IAbstractDiagramNodeEditPart {

    /**
     * Default spacing used for the layout manager of the content pane.
     * 
     * Will be used to locate the compartment edit part (for region container,
     * list) after the label.
     */
    public static final int DEFAULT_SPACING = 5;

    /** The content pane. */
    protected IFigure contentPane;

    /** The primary shape. */
    protected IFigure primaryShape;

    /** The background image. */
    private IFigure backgroundFigure;

    private Dimension intialDefaultSize;

    /**
     * Creates a new Container edit part.
     * 
     * @param view
     *            the GMF view.
     */
    public AbstractDiagramElementContainerEditPart(final View view) {
        super(view);
    }

    @Override
    protected void handleNotificationEvent(final Notification notification) {
        final EditPart styleEditPart = getStyleEditPart();
        // Refreshes edit part.
        if (styleEditPart != null) {
            final EObject element = ((IGraphicalEditPart) styleEditPart).resolveSemanticElement();
            if (element != null && element.eResource() != null) {
                styleEditPart.refresh();
            }
        }
        final EObject element = resolveSemanticElement();
        if (element != null && element.eResource() != null && getParent() != null) {
            refresh();
        }
        super.handleNotificationEvent(notification);
        AbstractDiagramNodeEditPartOperation.handleNotificationEvent(this, notification);

        handleDefaultSizeNotification(notification);
    }

    private void handleDefaultSizeNotification(Notification notification) {
        if (!notification.isTouch()) {
            Object feature = notification.getFeature();
            if (feature.equals(DiagramPackage.eINSTANCE.getDDiagramElementContainer_Width()) || feature.equals(DiagramPackage.eINSTANCE.getDDiagramElementContainer_Height())) {
                IFigure figure = getMainFigure();
                if (figure instanceof DefaultSizeNodeFigure && !isRegion()) {
                    setFigureDefaultSize((DefaultSizeNodeFigure) figure);
                }
            }
        }

    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        // compoundEditPolicy.addEditPolicy(new
        // SiriusGraphicalNodeEditPolicy(getEditingDomain()));
        AbstractDiagramNodeEditPartOperation.createDefaultEditPolicies(this);
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new SiriusGraphicalNodeEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DumnySiriusCanonicalEditPolicy());
        installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    @Override
    public IFigure getContentPane() {
        if (contentPane != null) {
            return contentPane;
        }
        return super.getContentPane();
    }

    /**
     * Default implementation treats passed figure as content pane. Respects
     * layout one may have set for generated figure.
     * 
     * @param nodeShape
     *            instance of generated figure class
     * 
     * @return the content pane
     * @was-generated
     */
    protected IFigure setupContentPane(IFigure nodeShape) {
        if (nodeShape.getLayoutManager() == null) {
            ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
            layout.setSpacing(getMapMode().DPtoLP(DEFAULT_SPACING));
            nodeShape.setLayoutManager(layout);
        }
        return nodeShape; // use nodeShape itself as contentPane
    }

    @Override
    public void refresh() {
        super.refresh();
        List<?> children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            EditPart editPart = (EditPart) children.get(i);
            editPart.refresh();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected List getModelChildren() {
        // create a new view to avoid to change the super.getModelChildren list.
        List<?> modelChildren = Lists.newArrayList(super.getModelChildren());
        DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);
        return modelChildren;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramListEditPart#getBackgroundFigure()
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramContainerEditPart#getBackgroundFigure()
     */
    public IFigure getBackgroundFigure() {
        return this.backgroundFigure;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramListEditPart#createBackgroundFigure()
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramContainerEditPart#createBackgroundFigure()
     */
    public void createBackgroundFigure() {
        this.backgroundFigure = DiagramContainerEditPartOperation.createBackgroundFigure(this);
    }

    /**
     * {@inheritDoc}
     * 
     * Creates figure for this edit part.
     * 
     * Body of this method does not depend on settings in generation model so
     * you may safely remove <i>generated</i> tag and modify it.
     * 
     * @was-generated
     */
    @Override
    protected NodeFigure createMainFigure() {
        final NodeFigure figure = createNodePlate();

        if (figure instanceof DefaultSizeNodeFigure) {
            setFigureDefaultSize((DefaultSizeNodeFigure) figure);
        }

        figure.setLayoutManager(new StackLayout());

        /*
         * Never comment this see trac #1014
         */
        this.createBackgroundFigure();
        if (this.getBackgroundFigure() != null) {
            figure.add(this.getBackgroundFigure());
        }

        primaryShape = createNodeShape();
        addDropShadow(figure, primaryShape);
        configureBorder(primaryShape);
        figure.add(primaryShape);
        contentPane = setupContentPane(primaryShape);
        return figure;
    }

    private void setFigureDefaultSize(DefaultSizeNodeFigure figure) {
        if (intialDefaultSize == null) {
            intialDefaultSize = figure.getDefaultSize().getCopy();
        }
        Object model = getModel();
        if (model instanceof Node) {
            EObject element = ((Node) model).getElement();
            if (element instanceof DDiagramElementContainer) {
                Integer dDiagramElementContainerWidth = ((DDiagramElementContainer) element).getWidth();
                Integer dDiagramElementContainerHeight = ((DDiagramElementContainer) element).getHeight();

                int widthToApplied = intialDefaultSize.width;
                int heightToApplied = intialDefaultSize.height;

                if (dDiagramElementContainerWidth != null && dDiagramElementContainerWidth > 0) {
                    int tempWidthToApplied = dDiagramElementContainerWidth * LayoutUtils.SCALE;
                    widthToApplied = tempWidthToApplied;
                }
                if (dDiagramElementContainerHeight != null && dDiagramElementContainerHeight > 0) {
                    int tempHeightToApplied = dDiagramElementContainerHeight * LayoutUtils.SCALE;
                    heightToApplied = tempHeightToApplied;
                }
                DefaultSizeNodeFigure dsnf = figure;
                Dimension d = new Dimension(widthToApplied, heightToApplied);
                dsnf.setDefaultSize(d);
            }
        }
    }

    /**
     * The primary shape has been created, configure it.
     * 
     * @param shapeFigure
     *            the figure which needs a border.
     */
    protected void configureBorder(IFigure shapeFigure) {
        if (isRegion() && shapeFigure != null) {
            if (isFirstRegionPart()) {
                shapeFigure.setBorder(new MarginBorder(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0));
            } else {
                RoundedCornerMarginBorder oneLineBorder = new RoundedCornerMarginBorder(PositionConstants.TOP);
                shapeFigure.setBorder(oneLineBorder);
                oneLineBorder.setCornerDimensions(DiagramContainerEditPartOperation.getCornerDimension(this));
                oneLineBorder.setMargin(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0);
                if (getParentStackDirection() == PositionConstants.EAST_WEST) {
                    oneLineBorder.setPosition(PositionConstants.LEFT);
                }
            }
        }
    }

    private boolean isFirstRegionPart() {
        EditPart parent = getParent();
        if (parent instanceof AbstractDNodeContainerCompartmentEditPart) {
            Iterable<AbstractDiagramElementContainerEditPart> regionParts = Iterables.filter(parent.getChildren(), AbstractDiagramElementContainerEditPart.class);
            return !Iterables.isEmpty(regionParts) && regionParts.iterator().next() == this;
        }
        return false;
    }

    /**
     * Return the direction of the parent stack. The method will return
     * {@link PositionConstants.None} if the parent is not a Region Container.
     * 
     * None if the parent is a FreeForm container.
     * 
     * @return the direction of the parent stack.
     */
    public int getParentStackDirection() {
        int direction = PositionConstants.NONE;
        DDiagramElement dde = resolveDiagramElement();
        if (dde instanceof DDiagramElementContainer) {
            DDiagramElementContainerExperimentalQuery query = new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) dde);
            if (query.isRegionInVerticalStack()) {
                direction = PositionConstants.NORTH_SOUTH;
            } else if (query.isRegionInHorizontalStack()) {
                direction = PositionConstants.EAST_WEST;
            }
        }
        return direction;
    }

    /**
     * Indicates if the current edit part is a region of its parent.
     * 
     * @return true if this part is a region.
     */
    public boolean isRegion() {
        DDiagramElement ddiagramElement = resolveDiagramElement();
        if (ddiagramElement instanceof DDiagramElementContainer) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) ddiagramElement;
            return new DDiagramElementContainerExperimentalQuery(ddec).isRegion();
        }
        return false;
    }

    @Override
    protected void refreshVisuals() {
        if (primaryShape != null) {
            if (isRegion()) {
                final boolean firstRegion = isFirstRegionPart();
                if (firstRegion && primaryShape.getBorder() instanceof LineBorder || !firstRegion && primaryShape.getBorder() instanceof MarginBorder) {
                    configureBorder(primaryShape);
                }
            }
            /* Update background for containers */
            final DDiagramElement diagElement = this.resolveDiagramElement();
            if (diagElement instanceof DNodeContainer) {
                final DNodeContainer container = (DNodeContainer) diagElement;
                if (primaryShape instanceof GradientRoundedRectangle && container.getOwnedStyle() instanceof FlatContainerStyle) {
                    if (((GradientRoundedRectangle) primaryShape).getBackgroundStyle() != ((FlatContainerStyle) container.getOwnedStyle()).getBackgroundStyle()) {
                        reInitFigure();
                    }
                }
            }
        }

        super.refreshVisuals();
        DiagramContainerEditPartOperation.refreshVisuals(this);
    }

    @Override
    protected void refreshForegroundColor() {
        super.refreshForegroundColor();
        DiagramContainerEditPartOperation.refreshForegroundColor(this);
    }

    @Override
    protected void refreshBackgroundColor() {
        super.refreshBackgroundColor();
        DiagramContainerEditPartOperation.refreshBackgroundColor(this);
    }

    @Override
    protected void refreshFont() {
        super.refreshFont();
        DiagramContainerEditPartOperation.refreshFont(this);
    }

    /**
     * Creates a figure for this edit part, depending on the label style.
     * 
     * @return a figure for this edit part.
     */
    protected NodeFigure createNodePlate() {
        Dimension defaultSize = LayoutUtils.NEW_DEFAULT_CONTAINER_DIMENSION;
        final EObject eObj = resolveSemanticElement();
        if (eObj instanceof DNodeContainer) {
            defaultSize = new DNodeContainerQuery((DNodeContainer) eObj).getDefaultDimension();
        }

        NodeFigure result;
        if (eObj instanceof DStylizable && eObj instanceof DDiagramElement) {
            final DStylizable viewNode = (DStylizable) eObj;
            Option<LabelBorderStyleDescription> getLabelBorderStyle = getLabelBorderStyle(viewNode);
            if (getLabelBorderStyle.some()) {
                result = new ContainerWithTitleBlockFigure(getMapMode().DPtoLP(defaultSize.width), getMapMode().DPtoLP(defaultSize.height), viewNode, getLabelBorderStyle.get());
            } else {
                result = new DefaultSizeNodeFigure(getMapMode().DPtoLP(defaultSize.width), getMapMode().DPtoLP(defaultSize.height));
            }
        } else {
            result = new DefaultSizeNodeFigure(getMapMode().DPtoLP(defaultSize.width), getMapMode().DPtoLP(defaultSize.height));
        }

        return result;
    }

    private Option<LabelBorderStyleDescription> getLabelBorderStyle(DStylizable viewNode) {
        if (viewNode.getStyle() instanceof FlatContainerStyle && viewNode.getStyle().getDescription() instanceof FlatContainerStyleDescription) {
            FlatContainerStyleDescription fcsd = (FlatContainerStyleDescription) viewNode.getStyle().getDescription();
            if (fcsd.getLabelBorderStyle() != null) {
                return Options.newSome(fcsd.getLabelBorderStyle());
            }
        }
        return Options.newNone();
    }

    /**
     * Creates the shape figure for this edit part, depending on the style :
     * shape, list.
     * 
     * @return a shape figure for this edit part.
     */
    protected IFigure createNodeShape() {
        DDiagramElement diagramElement = resolveDiagramElement();

        ViewNodeContainerFigureDesc shapeFigure = null;
        if (diagramElement instanceof DDiagramElementContainer) {
            final DDiagramElementContainer container = (DDiagramElementContainer) diagramElement;
            ContainerStyle ownedStyle = container.getOwnedStyle();
            if (ownedStyle instanceof ShapeContainerStyle) {
                shapeFigure = new ViewNodeContainerParallelogram();
            } else if (ownedStyle instanceof WorkspaceImage) {
                shapeFigure = new ViewNodeContainerRectangleFigureDesc();
            }
        } else {
            deactivate();
        }
        if (shapeFigure == null) {
            if (isRegion()) {
                shapeFigure = new RegionRoundedGradientRectangle(DiagramContainerEditPartOperation.getCornerDimension(this), DiagramContainerEditPartOperation.getBackgroundStyle(this));
            } else {
                shapeFigure = new GradientRoundedRectangle(DiagramContainerEditPartOperation.getCornerDimension(this), DiagramContainerEditPartOperation.getBackgroundStyle(this));
            }
        }

        // Compute label visibility
        if (diagramElement != null) {
            shapeFigure.getLabelFigure().setVisible(!(new DDiagramElementQuery(diagramElement).isLabelHidden()));
        }

        return shapeFigure;
    }

    /**
     * {@inheritDoc}
     * 
     * @not-generated add the folding toggle figure as bordered figure
     */
    @Override
    protected NodeFigure createNodeFigure() {
        BorderedNodeFigure nodeFigure = new BorderedNodeFigure(createMainFigure());
        nodeFigure.getBorderItemContainer().add(new FoldingToggleImageFigure(this));
        nodeFigure.getBorderItemContainer().setClippingStrategy(new FoldingToggleAwareClippingStrategy());
        return nodeFigure;
    }

    /**
     * Add a semi-transparent drop-shadow to the container, except for regions
     * or workspac eimage styles. These can have a non-rectangular contour and
     * transparent zones which should be kept as is.
     * 
     * @param figure
     *            the current figure which needs a border.
     * @param shape
     *            the shape to decorate
     */
    protected void addDropShadow(NodeFigure figure, IFigure shape) {
        DDiagramElement ddiagramElement = resolveDiagramElement();

        boolean needShadowBorder = true;
        if (ddiagramElement instanceof DDiagramElementContainer) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) ddiagramElement;
            needShadowBorder = !(new DDiagramElementContainerExperimentalQuery(ddec).isRegion() || ddec.getOwnedStyle() instanceof WorkspaceImage);
        }

        if (needShadowBorder) {
            AlphaDropShadowBorder shadowBorder = new AlphaDropShadowBorder(shape);
            figure.setBorder(shadowBorder);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @not-generated
     */
    @Override
    public ConnectionAnchor getSourceConnectionAnchor(final Request request) {
        if (request instanceof DropRequest) {
            final DropRequest dr = (DropRequest) request;
            if (dr.getLocation() != null) {
                /*
                 * We pass here during the feedback phase of the edge creation.
                 * This phase is handled by GMF alone, and we have access to the
                 * real expected location. We store that location in a globally
                 * accessible place where we can get it back during the actual
                 * creation phase.
                 */
                ViewLocationHint.getInstance().putData(ViewLocationHint.SOURCE_ANCHOR_LOCATION, dr.getLocation());
            } else {
                /*
                 * We pass here during the actual edge creation, triggered from
                 * the canonical refresh. At this point the request we get is
                 * the one created by
                 * DDiagramCanonicalEditPolicy#createConnections(), which does
                 * not have a location. We use the latest one saved during the
                 * feedback phase instead.
                 */
                final Point realLocation = (Point) ViewLocationHint.getInstance().getData(ViewLocationHint.SOURCE_ANCHOR_LOCATION);
                /*
                 * WARNING: we can not set the location of the request (and then
                 * simply call "super"), so the line below is is directly copied
                 * from ShapeNodeEditPart#getSourceConnectionAnchor().
                 */
                return getNodeFigure().getSourceConnectionAnchorAt(realLocation);
            }
        }
        return super.getSourceConnectionAnchor(request);
    }

    /**
     * See comments in {@link #getSourceConnectionAnchor(Request)}.
     * 
     * {@inheritDoc}
     * 
     * @not-generated
     */
    @Override
    public ConnectionAnchor getTargetConnectionAnchor(final Request request) {
        if (request instanceof DropRequest) {
            final DropRequest dr = (DropRequest) request;
            if (dr.getLocation() != null) {
                ViewLocationHint.getInstance().putData(ViewLocationHint.TARGET_ANCHOR_LOCATION, dr.getLocation());
            } else {
                final Point realLocation = (Point) ViewLocationHint.getInstance().getData(ViewLocationHint.TARGET_ANCHOR_LOCATION);
                return getNodeFigure().getTargetConnectionAnchorAt(realLocation);
            }
        }
        return super.getTargetConnectionAnchor(request);
    }

    @Override
    public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
        // FIX to prevent exception when hiding a port.
        IFigure pane = getContentPaneFor((IGraphicalEditPart) child);
        if (childFigure.getParent() != pane)
            return;
        super.setLayoutConstraint(child, childFigure, constraint);
    }

    @Override
    protected void removeChildVisual(EditPart childEditPart) {
        if (removeFixedChild(childEditPart)) {
            return;
        }
        super.removeChildVisual(childEditPart);
    }

    /**
     * Specific method to handle fixed children (DNode4EditPart).
     * 
     * @param childEditPart
     *            the part to remove if handled.
     * @return true if the child was removed.
     * @see #removeChildVisual(EditPart)
     * @was-generated
     */
    protected boolean removeFixedChild(EditPart childEditPart) {
        boolean removed = false;
        if (childEditPart instanceof AbstractDiagramElementContainerNameEditPart) {
            SiriusWrapLabel labelFigure = getPrimaryShape().getLabelFigure();
            labelFigure.setVisible(false);
            removed = true;
        } else if (childEditPart instanceof DNode4EditPart) {
            getBorderedFigure().getBorderItemContainer().remove(((DNode4EditPart) childEditPart).getFigure());
            removed = true;
        }
        return removed;
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    @Override
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (addFixedChild(childEditPart)) {
            return;
        }
        super.addChildVisual(childEditPart, -1);
    }

    /**
     * Specific method to handle fixed children (DNode4EditPart).
     * 
     * @param childEditPart
     *            the part to add if handled.
     * @return true if the child was added.
     * @see #addChildVisual(EditPart, int)
     * @was-generated
     */
    protected boolean addFixedChild(EditPart childEditPart) {
        boolean added = false;
        if (childEditPart instanceof AbstractDiagramElementContainerNameEditPart) {
            SiriusWrapLabel labelFigure = getPrimaryShape().getLabelFigure();
            labelFigure.setVisible(true);
            ((AbstractDiagramElementContainerNameEditPart) childEditPart).setLabel(labelFigure);
            added = true;
        } else if (childEditPart instanceof DNode4EditPart && ((DNode4EditPart) childEditPart).resolveSemanticElement() instanceof DDiagramElement) {
            IBorderItemLocator locator = createBorderItemLocator(getMainFigure(), (DDiagramElement) ((DNode4EditPart) childEditPart).resolveSemanticElement());
            // Convert child figure bounds to relative (There may be a
            // better solution, but none was found to recover the necessary
            // coordinates).
            final Point parentOrigin = getBorderedFigure().getHandleBounds().getTopLeft().getCopy();
            final Point negateParentOrigin = parentOrigin.getNegated();
            final Rectangle constraint = ((DNode4EditPart) childEditPart).getFigure().getBounds().getCopy();
            final Point newTopLeft = constraint.getTopLeft().getCopy().translate(negateParentOrigin);
            locator.setConstraint(new Rectangle(newTopLeft.x, newTopLeft.y, constraint.width, constraint.height));
            getBorderedFigure().getBorderItemContainer().add(((DNode4EditPart) childEditPart).getFigure(), locator);
            added = true;
        }
        return added;
    }

    /**
     * {@inheritDoc}
     */
    public void forceFigureAutosize() {
        final int width = -1;
        final int height = -1;
        final Dimension size = new Dimension(width, height);
        final int x = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_X())).intValue();
        final int y = ((Integer) getStructuralFeatureValue(NotationPackage.eINSTANCE.getLocation_Y())).intValue();
        final Point loc = new Point(x, y);
        ((GraphicalEditPart) getParent()).setLayoutConstraint(this, getFigure(), new Rectangle(loc, size));
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle getAutosizedDimensions() {
        ((GraphicalEditPart) getParent()).getFigure().validate();
        return getFigure().getBounds();
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated specific BorderItemEditPolicy and getCommand
     */
    protected LayoutEditPolicy createLayoutEditPolicy() {
        LayoutEditPolicy lep = new org.eclipse.sirius.diagram.ui.tools.api.policies.LayoutEditPolicy() {

            @Override
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

            @Override
            protected Command getMoveChildrenCommand(Request request) {
                return null;
            }

            @Override
            protected Command getCreateCommand(CreateRequest request) {
                return null;
            }

            /**
             * Redefines this method to allow the resizing of border items.
             * 
             * @see org.eclipse.gef.editpolicies.LayoutEditPolicy#getCommand(org.eclipse.gef.Request)
             */
            @Override
            public Command getCommand(final Request request) {
                if (REQ_RESIZE_CHILDREN.equals(request.getType()) && request instanceof ChangeBoundsRequest) {
                    final Command command = AbstractDiagramElementContainerEditPart.this.getResizeBorderItemCommand((ChangeBoundsRequest) request);
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
                        final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(getEditingDomain(), "Resize", new EObjectAdapter(graphicalEditPart.getNotationView()),
                                new Rectangle(position, dimension));
                        cmd = new ICommandProxy(setBoundsCommand);
                    }
                }
            }
        }
        return cmd;
    }

    /**
     * Returns the figure of the container.
     * 
     * @return the figure of the container.
     * @was-generated
     */
    public ViewNodeContainerFigureDesc getPrimaryShape() {
        if (primaryShape instanceof ViewNodeContainerFigureDesc) {
            return (ViewNodeContainerFigureDesc) primaryShape;
        }
        return null;
    }

    /**
     * Reinit the figure. It removes the current children of the main figure
     * (created with a previous style) and replace them with those created with
     * the current style.
     */
    @SuppressWarnings("unchecked")
    public void reInitFigure() {
        final IFigure mainFigure = ((BorderedNodeFigure) getFigure()).getMainFigure();
        final List<IFigure> prevChildren = new ArrayList<IFigure>(mainFigure.getChildren());
        ShapeCompartmentFigure containerCompartment = null;
        ResizableCompartmentFigure listCompartment = null;
        SiriusWrapLabel wrapLabel = null;
        final IFigure tmpFigure = createMainFigure();

        for (IFigure object : prevChildren) {
            if (object instanceof ShapeCompartmentFigure) {
                containerCompartment = (ShapeCompartmentFigure) object;
            } else if (object instanceof ViewNodeContainerFigureDesc) {
                for (Object object2 : ((ViewNodeContainerFigureDesc) object).getChildren()) {
                    if (object2 instanceof SiriusWrapLabel) {
                        wrapLabel = (SiriusWrapLabel) object2;
                    } else if (object2 instanceof ResizableCompartmentFigure) {
                        listCompartment = (ResizableCompartmentFigure) object2;
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
                if (listCompartment != null) {
                    figure.add(listCompartment);
                }
            }

            mainFigure.add((IFigure) tmpChildren[i], i);
        }
        if (containerCompartment != null) {
            mainFigure.add(containerCompartment);
        }
    }
}
