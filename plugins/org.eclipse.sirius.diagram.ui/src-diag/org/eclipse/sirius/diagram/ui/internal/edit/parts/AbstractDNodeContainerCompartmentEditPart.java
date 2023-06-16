/*******************************************************************************
 * Copyright (c) 2009, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ScrollPane;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INotableEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ShapeCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.layout.FreeFormLayoutEx;
import org.eclipse.gmf.runtime.diagram.ui.requests.EditCommandRequestWrapper;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.OneLineBorder;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.model.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.business.internal.view.ShowingViewUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.ISiriusEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DCompartmentConnectionRefreshMgr;
import org.eclipse.sirius.diagram.ui.edit.internal.part.layoutmanager.RegionContainerConstrainedToolbarLayout;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirXYLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPopupBarEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeContainerViewNodeContainerCompartmentItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.RegionCollapseAwarePropertyHandlerEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.ContainerBorderedNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.RegionRoundedGradientRectangle;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.ContainerCompartmentNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.ruler.SiriusSnapToHelperUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editpolicies.SiriusSnapFeedbackPolicy;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.LabelBorderStyleIds;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.OneLineMarginBorder;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * <p>
 * Abstract {@link EditPart} representing the Compartment zone of a DNodeContainer.
 * </p>
 * 
 * @see {@link ShapeCompartmentEditPart}
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractDNodeContainerCompartmentEditPart extends ShapeCompartmentEditPart implements INotableEditPart, ISiriusEditPart {

    /**
     * Default margin. Added to the border size to set the border insets of FreeForm containers.
     */
    public static final int DEFAULT_MARGIN = 4;

    /**
     * Creates a new AbstractDNodeContainerCompartmentEditPart.
     * 
     * @param view
     *            the view controlled by this editpart
     */
    public AbstractDNodeContainerCompartmentEditPart(View view) {
        super(view);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(Request request) {
        // We consider that for the following types of request, the target
        // should be the DNodeContainer containing this Compartment
        if (request instanceof CreateConnectionRequest || request instanceof ReconnectRequest) {
            return getParent();
        }
        return this;
    }

    @Override
    public Command getCommand(Request _request) {
        // We consider that for the following types of request,
        // it's the DNodeContainer containing this Compartment which should
        // create the command
        if (_request instanceof ReconnectRequest) {
            return getParent().getCommand(_request);
        }
        if (_request instanceof CreateConnectionRequest) {
            Command command = getParent().getCommand(_request);
            return command;
        }
        if (_request instanceof EditCommandRequestWrapper) {
            if (((EditCommandRequestWrapper) _request).getEditCommandRequest() instanceof CreateRelationshipRequest) {
                return getParent().getCommand(_request);
            }
        }
        return super.getCommand(_request);
    }

    @Override
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    @Override
    protected void refreshVisuals() {
        EObject eObj = resolveSemanticElement();
        if (eObj instanceof DNodeContainer) {
            DNodeContainer container = (DNodeContainer) eObj;
            if (container.getOwnedStyle() != null) {
                /* color */
                ContainerStyle ownedStyle = container.getOwnedStyle();
                final RGBValues borderColor = ownedStyle.getBorderColor();
                if (borderColor != null) {
                    getFigure().setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(borderColor));
                }

                if (getFigure() instanceof ResizableCompartmentFigure) {
                    ResizableCompartmentFigure rcf = (ResizableCompartmentFigure) getFigure();
                    configureBorder(rcf);

                    // Configure scroll pane border.
                    if (rcf.getScrollPane() != null) {
                        configureScrollPaneBorder(rcf.getScrollPane(), ownedStyle);
                    }
                }
            }
        }
        super.refreshVisuals();
    }

    @Override
    public IFigure createFigure() {
        IMapMode mapMode = getMapMode();
        ShapeCompartmentFigure scf = new ShapeCompartmentFigure(getCompartmentName(), mapMode);
        if (new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) resolveSemanticElement()).isRegion()) {
            // Make the shape compartment nestable.
            scf = new RegionShapeCompartmentFigure(getCompartmentName(), mapMode);
            scf.getScrollPane().setMinimumSize(new Dimension(0, 0));
        }

        configureBorder(scf);

        scf.getContentPane().setLayoutManager(getLayoutManager());
        scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
        scf.setTitleVisibility(false);
        scf.setToolTip((IFigure) null);
        return scf;
    }

    /**
     * Configure the border of the compartment figure.
     * 
     * @param rcf
     *            the figure of this edit part.
     */
    protected void configureBorder(ResizableCompartmentFigure rcf) {
        boolean shouldHaveBorder = isRegionContainerCompartment();
        Option<LabelBorderStyleDescription> labelBorderStyle = getLabelBorderStyle();
        if (labelBorderStyle.some()) {
            shouldHaveBorder = shouldHaveBorder || LabelBorderStyleIds.LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID.equals(labelBorderStyle.get().getId());
        }

        if (!shouldHaveBorder || isLabelHidden() || isCollapsed()) {
            if (rcf.getBorder() instanceof LineBorder || rcf.getBorder() == null) {
                // Do not draw the top line border for free form containers.
                rcf.setBorder(new MarginBorder(getMapMode().DPtoLP(1), 0, 0, 0));
            }

        } else if (rcf.getBorder() instanceof MarginBorder || rcf.getBorder() == null) {
            rcf.setBorder(new OneLineBorder(getMapMode().DPtoLP(1), PositionConstants.TOP));
        }

        // Update content pane if required to correctly place and show the
        // border.
        IFigure parentFigure = rcf.getParent();
        if (parentFigure != null && getParent() instanceof AbstractDiagramContainerEditPart) {
            AbstractDiagramContainerEditPart adcep = (AbstractDiagramContainerEditPart) getParent();
            if (shouldHaveBorder && adcep.getContentPane() != parentFigure) {
                parentFigure.remove(rcf);
                adcep.getContentPane().add(rcf);
            } else if (!shouldHaveBorder && adcep.getMainFigure() != parentFigure) {
                parentFigure.remove(rcf);
                adcep.getMainFigure().add(rcf);
            }
        }
    }

    /**
     * Configure the scrollpane border of this compartment figure.
     * 
     * @param scrollPane
     *            the figure of this edit part.
     * @param ownedStyle
     *            the current {@link ContainerStyle} of the diagram element.
     */
    protected void configureScrollPaneBorder(ScrollPane scrollPane, ContainerStyle ownedStyle) {
        int borderSize = 0;
        if (ownedStyle.getBorderSize() != null) {
            borderSize = ownedStyle.getBorderSize().intValue();
        }

        boolean fullLabelBorder = false;
        Option<LabelBorderStyleDescription> labelBorderStyle = getLabelBorderStyle();
        if (labelBorderStyle.some()) {
            fullLabelBorder = LabelBorderStyleIds.LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID.equals(labelBorderStyle.get().getId());
        }

        Border border = null;
        if (isRegionContainerCompartment()) {
            // scroll pane / layout compensation
            if (borderSize == 0) {
                border = new MarginBorder(0, 0, 0, 0);
            } else {
                border = new MarginBorder(0, 0, -1, -1);
            }
        } else if (fullLabelBorder) {
            border = new MarginBorder(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN);
        } else {
            // We should not have to report the border size
            // here, but the content pane of the figure will be
            // the main figure for FreeForm containers.
            int margin = borderSize + DEFAULT_MARGIN;
            border = new MarginBorder(margin, margin, margin, margin);

            // Correctly handle scrollpane border when when the primary shape
            // has a one side border.
            if (((AbstractDiagramElementContainerEditPart) getParent()).getPrimaryShape().getBorder() instanceof OneLineMarginBorder) {
                int lineBorderPosition = ((OneLineMarginBorder) ((AbstractDiagramElementContainerEditPart) getParent()).getPrimaryShape().getBorder()).getPosition();
                if (PositionConstants.TOP == lineBorderPosition) {
                    border = new MarginBorder(margin, DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN);
                } else if (PositionConstants.LEFT == lineBorderPosition) {
                    border = new MarginBorder(DEFAULT_MARGIN, margin, DEFAULT_MARGIN, DEFAULT_MARGIN);
                } else if (PositionConstants.BOTTOM == lineBorderPosition) {
                    border = new MarginBorder(DEFAULT_MARGIN, DEFAULT_MARGIN, margin, DEFAULT_MARGIN);
                } else if (PositionConstants.RIGHT == lineBorderPosition) {
                    border = new MarginBorder(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, margin);
                }
            }
        }
        scrollPane.setBorder(border);
    }

    private boolean isLabelHidden() {
        EObject element = resolveSemanticElement();
        if (element instanceof DDiagramElement) {
            return new DDiagramElementQuery((DDiagramElement) element).isLabelHidden();
        }
        return false;
    }

    private boolean isCollapsed() {
        DrawerStyle style = (DrawerStyle) ((View) getModel()).getStyle(NotationPackage.eINSTANCE.getDrawerStyle());
        return style == null ? false : style.isCollapsed();
    }

    private Option<LabelBorderStyleDescription> getLabelBorderStyle() {
        EObject element = resolveSemanticElement();
        if (element instanceof DDiagramElementContainer) {
            return new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) element).getLabelBorderStyle();
        }
        return Options.newNone();
    }

    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeContainerViewNodeContainerCompartmentItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
        // -- 01-08-2008 : bug drag & drop, we should use a CompoundEditPolicy.
        // CompoundEditPolicy dragDropEditPolicy = new CompoundEditPolicy();
        // dragDropEditPolicy.addEditPolicy(new
        // SiriusContainerDropPolicy(getEditingDomain()));
        // while (this.getEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE) != null) {
        // dragDropEditPolicy.addEditPolicy(this.getEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE));
        // this.removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
        // }
        // installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
        // dragDropEditPolicy);
        // -- 01-08-2008
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new SiriusContainerDropPolicy());
        installEditPolicy(EditPolicy.CONTAINER_ROLE, new NodeCreationEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());
        removeEditPolicy(EditPolicy.LAYOUT_ROLE);
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AirXYLayoutEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ContainerCompartmentNodeEditPolicy());
        installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new SiriusPopupBarEditPolicy());
        // Replace the feedback policy to have a lighter color for guides
        installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE, new SiriusSnapFeedbackPolicy());
        installEditPolicy(EditPolicyRoles.PROPERTY_HANDLER_ROLE, new RegionCollapseAwarePropertyHandlerEditPolicy());
    }

    @Override
    protected void setRatio(Double ratio) {
        if (getFigure().getParent() != null && getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
            super.setRatio(ratio);
        }
    }

    @Override
    protected List getModelChildren() {
        List<View> modelChildren = ShowingViewUtil.getModelChildren(getModel());
        if (!modelChildren.isEmpty()) {
            if (isRegionContainerCompartment() && getModel() instanceof View) {
                RegionContainerUpdateLayoutOperation.sortRegions((DNodeContainer) resolveSemanticElement(), modelChildren);
            }
        }
        return modelChildren;
    }

    @Override
    protected void addNotationalListeners() {
        super.addNotationalListeners();
        if (hasNotationView()) {
            ViewQuery viewQuery = new ViewQuery((View) getModel());
            Optional<DDiagram> diagram = viewQuery.getDDiagram();
            if (diagram.isPresent()) {
                addListenerFilter("ShowingMode", this, diagram.get(), DiagramPackage.eINSTANCE.getDDiagram_IsInShowingMode()); //$NON-NLS-1$
            }
        }
    }

    @Override
    protected void removeNotationalListeners() {
        super.removeNotationalListeners();
        removeListenerFilter("ShowingMode"); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#setVisibility(boolean)
     */
    @Override
    protected void setVisibility(boolean vis) {
        ShowingViewUtil.setVisibility(this, vis, SELECTED_NONE, getFlag(FLAG__AUTO_CONNECTIONS_VISIBILITY));
    }

    private boolean isRegionContainerCompartment() {
        Option<DNodeContainerExperimentalQuery> query = getDNodeContainerQuery();
        return query.some() && query.get().isRegionContainer();
    }

    private Option<DNodeContainerExperimentalQuery> getDNodeContainerQuery() {
        DNodeContainerExperimentalQuery query = null;
        EObject eObject = resolveSemanticElement();
        if (eObject instanceof DNodeContainer) {
            query = new DNodeContainerExperimentalQuery((DNodeContainer) eObject);
        }

        return Options.newSome(query);
    }

    /**
     * Return the set of {@link ConnectionNodeEditPart}s contained in the supplied shape compartment.
     * 
     * @param scep
     *            a shape compartment.
     * @return a {@link Set} of {@link ConnectionNodeEditPart}.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Set getConnectionNodes(ShapeCompartmentEditPart scep) {
        Set endPoints = new HashSet();
        Object modelObject = scep.getModel();
        if (scep.getViewer() == null || modelObject == null || !(modelObject instanceof View)) {
            return endPoints;
        }

        if (((View) modelObject).getDiagram() == null)
            return endPoints;

        Diagram diagram = ((View) modelObject).getDiagram();
        Map registry = scep.getViewer().getEditPartRegistry();
        List edges = diagram.getEdges();
        Iterator edgesIterator = edges.iterator();

        while (edgesIterator.hasNext()) {
            Edge edge = (Edge) edgesIterator.next();
            EditPart endPoint = (EditPart) registry.get(edge.getSource());
            if (isChildOf(scep, endPoint)) {
                Object cep = registry.get(edge);
                if (cep != null) {
                    endPoints.add(cep);
                }
                continue;
            }
            endPoint = (EditPart) registry.get(edge.getTarget());
            if (isChildOf(scep, endPoint)) {
                Object cep = registry.get(edge);
                if (cep != null) {
                    endPoints.add(cep);
                }
            }
        }
        return endPoints;
    }

    /**
     * Return <tt>true</tt> if <tt>parent</tt> child's ancestor; otherwise <tt>false</tt>
     * 
     * @param parent
     *            parent to consider
     * @param child
     *            child to consider
     * @return <tt>true</tt> or <tt>false</tt>
     */
    protected boolean isChildOf(EditPart parent, EditPart child) {
        EditPart walker = child;
        while (walker != null && walker != parent) {
            walker = walker.getParent();
        }
        return walker != null;
    }

    @Override
    public boolean canAttachNote() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#createConnectionRefreshMgr()
     */
    @Override
    protected ConnectionRefreshMgr createConnectionRefreshMgr() {
        return new DCompartmentConnectionRefreshMgr();
    }

    @Override
    protected LayoutManager getLayoutManager() {
        LayoutManager layoutManager = null;
        Option<DNodeContainerExperimentalQuery> query = getDNodeContainerQuery();
        if (query.some() && query.get().isRegionContainer()) {
            if (query.get().isVerticalStackContainer()) {
                layoutManager = new RegionContainerLayoutManager(true, this);
            } else if (query.get().isHorizontaltackContainer()) {
                layoutManager = new RegionContainerLayoutManager(false, this);
            }
        }

        if (layoutManager == null) {
            layoutManager = super.getLayoutManager();
        }
        return layoutManager;
    }

    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            return SiriusSnapToHelperUtil.getSnapHelper(this);
        }
        return super.getAdapter(key);
    }

    @Override
    protected void setCollapsed(boolean collapsed, boolean animate) {
        // Always disable the animation.
        super.setCollapsed(collapsed, false);
    }

    /**
     * Specific layout manager to handle Regions.
     * 
     * @author mporhel
     */
    public static class RegionContainerLayoutManager extends FreeFormLayoutEx {
        
        private static final boolean REGION_IMPROVED_AUTOSIZE_LAYOUT = Boolean.getBoolean("org.eclipse.sirius.diagram.ui.internal.region.autosize.figure.optimized"); //$NON-NLS-1$
        

        private final boolean isVertical;

        /**
         * Indicate if the compartment is contained in a regions container that is also itself a region of another
         * regions container. In this case, the layout, for horizontal stack, is impacted by other regions, brothers of
         * the container.
         */
        private final boolean isARegion;

        private final AbstractDNodeContainerCompartmentEditPart containerCompartmentEditPart;

        /**
         * Constructor.
         * 
         * @param isVertical
         *            true to layout as vertical stack, false otherwise
         * @param containerCompartmentEditPart
         *            the edit part using this layout manager
         */
        public RegionContainerLayoutManager(boolean isVertical, AbstractDNodeContainerCompartmentEditPart containerCompartmentEditPart) {
            this.isVertical = isVertical;
            this.containerCompartmentEditPart = containerCompartmentEditPart;
            isARegion = containerCompartmentEditPart.getParent() instanceof AbstractDiagramElementContainerEditPart
                    && ((AbstractDiagramElementContainerEditPart) containerCompartmentEditPart.getParent()).isRegion();
        }

        public boolean isVertical() {
            return isVertical;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void layout(IFigure parent) {
            Collection<IFigure> children = Lists.newArrayList(Iterables.filter(parent.getChildren(), IFigure.class));
            if (children.isEmpty()) {
                return;
            }

            Point offset = getOrigin(parent);

            int maxWidth = 0;
            int maxHeight = 0;
            int minY = Integer.MAX_VALUE;
            Map<IFigure, Rectangle> regionsBounds = new HashMap<>();

            Rectangle bounds;
            // First step : compute freeform layout bounds and keep the common
            // known max width and minimum y.
            for (IFigure f : children) {
                Object constraint = getConstraint(f);
                if (constraint instanceof Rectangle) {
                    bounds = ((Rectangle) constraint).getCopy();
                } else {
                    continue;
                }

                bounds = computeFreeFormLayoutExBounds(f, bounds);

                regionsBounds.put(f, bounds);
                maxWidth = Math.max(maxWidth, bounds.width);
                maxHeight = Math.max(maxHeight, bounds.height);
                minY = Math.min(minY, bounds.y);
            }

            boolean dependsOnRegionContainerWidth = new EditPartQuery(containerCompartmentEditPart).isAutoSized(true, false);
            int labelWidth = 0;
            IFigure parentLabelFigure = getParentLabelFigure(parent);
            if (dependsOnRegionContainerWidth) {
                if (parentLabelFigure != null) {
                    labelWidth = parentLabelFigure.getSize().width;

                    // For vertical stacks, take label into account to compute the
                    // regions common size.
                    if (isVertical) {
                        maxWidth = Math.max(maxWidth, labelWidth);
                    }
                }
            }

            int y = minY;
            int x = 0;

            // Second step : layout the regions, keep their order but use the
            // same width for vertical stacks and the same height for horizontal
            // stacks.
            for (IFigure f : children) {
                bounds = regionsBounds.get(f);

                bounds.setX(x);
                bounds.setY(y);

                if (isVertical) {
                    bounds.setWidth(maxWidth);
                    y += bounds.height;
                } else {
                    bounds.setHeight(maxHeight);
                    x += bounds.width;
                }

                if(!REGION_IMPROVED_AUTOSIZE_LAYOUT) {
                  setConstraint(f, bounds);
                }
                f.setBounds(bounds.translate(offset));
                // For vertical stack, notify last region of the width change (it is useful only if the current figure f
                // is also a region with HStack).
                if (isVertical && f instanceof ContainerBorderedNodeFigure) {
                    ((ContainerBorderedNodeFigure) f).fireContainerWidthChange(bounds.width);
                }
            }

            final IFigure last = Iterables.getLast(children);
            if (!isVertical && isARegion) {
                // If the current container compartment is a compartment of a container that is also a region, we
                // add a listener to potentially resize the last region of the current container.
                ContainerBorderedNodeFigure mainContainerFigure = getMainFigureOfAbstractDiagramElementContainerEditPart(parentLabelFigure);
                if (mainContainerFigure != null) {
                    mainContainerFigure.updateParentPropertyChangeListener(last, this);
                }
            }
            if (dependsOnRegionContainerWidth) {
                // For horizontal stacks, if label is longer than the regions
                // cumulative width, increase the last region size.
                int delta = labelWidth - x;
                if (!isVertical && delta > 0) {
                    bounds = regionsBounds.get(last);
                    bounds.setWidth(bounds.width + delta);
                    if(!REGION_IMPROVED_AUTOSIZE_LAYOUT) {
                        setConstraint(last, bounds);
                      }
                    last.setBounds(bounds.translate(offset));
                }
            }
        }

        /**
         * Get the first parent of currentFigure that is an instance of {@link ContainerBorderedNodeFigure}, ie the main
         * figure of AbstractDiagramElementContainerEditPart. This method relies on the existing figures hierarchy, ie
         * the ContainerBorderedNodeFigure is the grand father of the {@link RegionRoundedGradientRectangle} that has a
         * {@link RegionContainerConstrainedToolbarLayout}. When this figure is resized, we want to "notify" the figure
         * of the last regions to resize it if necessary.
         * 
         * @param currentFigure
         *            The current figure
         * @return The first parent as type BorderedNodeFigure.
         */
        private ContainerBorderedNodeFigure getMainFigureOfAbstractDiagramElementContainerEditPart(IFigure currentFigure) {
            ContainerBorderedNodeFigure result;
            if (currentFigure == null) {
                result = null;
            } else if (currentFigure instanceof ContainerBorderedNodeFigure) {
                result = (ContainerBorderedNodeFigure) currentFigure;
            } else {
                result = getMainFigureOfAbstractDiagramElementContainerEditPart(currentFigure.getParent());
            }
            return result;
        }

        /**
         * Get the label of the regions container containing the dNodeContainerCompartmentFigure.
         * 
         * @param dNodeContainerCompartmentFigure
         *            The figure representing the DNodeContainerCompartment.
         * @return The label figure of the regions container.
         */
        private IFigure getParentLabelFigure(IFigure dNodeContainerCompartmentFigure) {
            IFigure tmp = dNodeContainerCompartmentFigure;
            ViewNodeContainerFigureDesc parentShape = null;
            while (parentShape == null && tmp != null) {
                if (tmp instanceof ViewNodeContainerFigureDesc) {
                    parentShape = (ViewNodeContainerFigureDesc) tmp;
                    tmp = null;
                } else {
                    tmp = tmp.getParent();
                }
            }

            return parentShape != null ? parentShape.getLabelFigure() : null;
        }

        /*
         * @see FreeFormLayoutEx#layout(IFigure)
         */
        private Rectangle computeFreeFormLayoutExBounds(IFigure f, Rectangle copy) {
            Rectangle bounds = copy;

            int widthHint = bounds.width;
            int heightHint = bounds.height;
            if (widthHint == -1 || heightHint == -1) {
                Dimension prefSize = f.getPreferredSize(widthHint, heightHint);
                if (widthHint == -1) {
                    bounds.setWidth(prefSize.width);
                }
                if (heightHint == -1) {
                    bounds.setHeight(prefSize.height);
                }
            }
            Dimension min = f.getMinimumSize(widthHint, heightHint);
            Dimension max = f.getMaximumSize();

            if (min.width > bounds.width) {
                bounds.setWidth(min.width);
            } else if (max.width < bounds.width) {
                bounds.setWidth(max.width);
            }

            if (min.height > bounds.height) {
                bounds.setHeight(min.height);
            } else if (max.height < bounds.height) {
                bounds.setHeight(max.height);
            }
            return bounds;
        }
    };

    /**
     * Specific shape compartment figure to allow collapse of region, ie nested compartment.
     */
    private static class RegionShapeCompartmentFigure extends ShapeCompartmentFigure {

        /**
         * Create an instance.
         * 
         * @See {@link ShapeCompartmentFigure}
         * 
         * @param title
         *            figure's title.
         * @param mm
         *            the <code>IMapMode</code> that is used to initialize the default values of of the scrollpane
         *            contained inside the figure. This is necessary since the figure is not attached at construction
         *            time and consequently can't get access to the owned IMapMode in the parent containment hierarchy.
         */
        public RegionShapeCompartmentFigure(String title, IMapMode mm) {
            super(title, mm);
        }

        @Override
        public Dimension getMinClientDimension() {
            return new Dimension(0, 0);
        }
    }
}
