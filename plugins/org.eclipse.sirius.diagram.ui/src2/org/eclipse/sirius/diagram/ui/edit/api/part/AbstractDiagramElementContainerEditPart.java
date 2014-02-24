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

import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Shape;
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
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.edit.internal.part.AbstractDiagramNodeEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.graphical.edit.policies.SiriusGraphicalNodeEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.internal.edit.policies.canonicals.DumnySiriusCanonicalEditPolicy;
import org.eclipse.sirius.diagram.internal.view.factories.ViewLocationHint;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IContainerLabelOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.figure.AlphaDropShadowBorder;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FoldingToggleAwareClippingStrategy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.FoldingToggleImageFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.OneLineMarginBorder;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Basic implementation of top Level type of Diagram an List Containers.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramElementContainerEditPart extends AbstractBorderedDiagramElementEditPart implements IAbstractDiagramNodeEditPart {

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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderedShapeEditPart#createDefaultEditPolicies()
     */
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        // CompoundEditPolicy compoundEditPolicy = new CompoundEditPolicy();
        // compoundEditPolicy.addEditPolicy(new
        // SiriusGraphicalNodeEditPolicy(getEditingDomain()));
        AbstractDiagramNodeEditPartOperation.createDefaultEditPolicies(this);
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new SiriusGraphicalNodeEditPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DumnySiriusCanonicalEditPolicy());
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
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
            layout.setSpacing(getMapMode().DPtoLP(5));
            nodeShape.setLayoutManager(layout);
        }
        return nodeShape; // use nodeShape itself as contentPane
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh() {
        final EObject element = resolveSemanticElement();
        if (element != null && this.getMetamodelType().isInstance(element)) {
            super.refresh();

            List<?> children = getChildren();
            for (int i = 0; i < children.size(); i++) {
                EditPart editPart = (EditPart) children.get(i);
                editPart.refresh();
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getModelChildren()
     */
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
    protected NodeFigure createMainFigure() {
        final NodeFigure figure = createNodePlate();

        if (figure instanceof DefaultSizeNodeFigure && isRegion()) {
            DefaultSizeNodeFigure dsnf = (DefaultSizeNodeFigure) figure;
            Dimension d = new Dimension(100, 60);
            dsnf.setDefaultSize(d);
            dsnf.setMinimumSize(d);
        } else if (figure instanceof DefaultSizeNodeFigure) {
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
            // If the figure is a shape, do not draw the shape border.
            if (shapeFigure instanceof Shape) {
                ((Shape) shapeFigure).setOutline(false);
            }

            if (isFirstRegionPart()) {
                shapeFigure.setBorder(new MarginBorder(IContainerLabelOffsets.LABEL_OFFSET, 0, 0, 0));
            } else {
                OneLineMarginBorder oneLineBorder = new OneLineMarginBorder(PositionConstants.TOP);
                shapeFigure.setBorder(oneLineBorder);
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

    private int getParentStackDirection() {
        int direction = PositionConstants.NONE;
        EditPart parent = getParent();
        if (parent instanceof AbstractDNodeContainerCompartmentEditPart) {
            EObject element = ((AbstractDNodeContainerCompartmentEditPart) parent).resolveSemanticElement();
            if (element instanceof DNodeContainer) {
                DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery((DNodeContainer) element);
                if (query.isVerticalStackContainer()) {
                    direction = PositionConstants.NORTH_SOUTH;
                } else if (query.isHorizontaltackContainer()) {
                    direction = PositionConstants.EAST_WEST;
                }
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
            final boolean firstRegion = isFirstRegionPart();
            if (firstRegion && primaryShape.getBorder() instanceof LineBorder || !firstRegion && primaryShape.getBorder() instanceof MarginBorder) {
                configureBorder(primaryShape);
            }
        }

        super.refreshVisuals();
    }

    /**
     * Creates a figure for this edit part, depending on the label style.
     * 
     * @return a figure for this edit part.
     */
    protected abstract NodeFigure createNodePlate();

    /**
     * Creates the shape figure for this edit part, depending on the style :
     * shape, list.
     * 
     * @return a shape figure for this edit part.
     */
    protected abstract IFigure createNodeShape();

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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
        // FIX to prevent exception when hiding a port.
        IFigure pane = getContentPaneFor((IGraphicalEditPart) child);
        if (childFigure.getParent() != pane)
            return;
        super.setLayoutConstraint(child, childFigure, constraint);
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
        if (childEditPart instanceof DNode4EditPart) {
            getBorderedFigure().getBorderItemContainer().remove(((DNode4EditPart) childEditPart).getFigure());
            return true;
        }
        return false;
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
        if (childEditPart instanceof DNode4EditPart && ((DNode4EditPart) childEditPart).resolveSemanticElement() instanceof DDiagramElement) {
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
            return true;
        }
        return false;
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
}
