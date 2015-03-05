/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
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
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.ui.edit.api.part.ISiriusEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DCompartmentConnectionRefreshMgr;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.AirXYLayoutEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusPopupBarEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeContainerViewNodeContainerCompartmentItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.LabelBorderStyleIds;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.ContainerCompartmentNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.ruler.SiriusSnapToHelperUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gef.editpolicies.SiriusSnapFeedbackPolicy;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * <p>
 * Abstract {@link EditPart} representing the Compartment zone of a
 * DNodeContainer.
 * </p>
 * 
 * @see {@link ShapeCompartmentEditPart}
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractDNodeContainerCompartmentEditPart extends ShapeCompartmentEditPart implements INotableEditPart, ISiriusEditPart {

    /**
     * Default margin. Added to the border size to set the border insets of
     * FreeForm containers.
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
            if (container.getOwnedStyle() != null && container.getOwnedStyle() instanceof FlatContainerStyle) {
                /* color */
                FlatContainerStyle ownedStyle = (FlatContainerStyle) container.getOwnedStyle();
                final RGBValues borderColor = ownedStyle.getBorderColor();
                if (borderColor != null) {
                    getFigure().setForegroundColor(VisualBindingManager.getDefault().getColorFromRGBValues(borderColor));
                }
                int borderSize = 0;
                if (ownedStyle.getBorderSize() != null) {
                    borderSize = ownedStyle.getBorderSize().intValue();
                }

                if (getFigure() instanceof ResizableCompartmentFigure) {
                    ResizableCompartmentFigure rcf = (ResizableCompartmentFigure) getFigure();
                    configureBorder(rcf);
                    if (rcf.getScrollPane() != null) {
                        Border border;
                        if (isRegionContainerCompartment()) {
                            // scroll pane / layout compensation
                            if (borderSize == 0) {
                                border = new MarginBorder(0, 0, 0, 0);
                            } else {
                                border = new MarginBorder(0, 0, -1, -1);
                            }
                        } else {
                            // We should not have to report the border size
                            // here, but the content pane of the figure will be
                            // the main figure for FreeForm containers.
                            int margin = borderSize + DEFAULT_MARGIN;
                            border = new MarginBorder(margin, margin, margin, margin);
                        }
                        rcf.getScrollPane().setBorder(border);
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

        configureBorder(scf);

        scf.getContentPane().setLayoutManager(getLayoutManager());
        scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
        scf.setTitleVisibility(false);
        scf.setToolTip((IFigure) null);
        return scf;
    }

    private void configureBorder(ResizableCompartmentFigure rcf) {
        boolean shouldHaveBorder = isRegionContainerCompartment();
        Option<LabelBorderStyleDescription> labelBorderStyle = getLabelBorderStyle();
        if (labelBorderStyle.some()) {
            shouldHaveBorder = shouldHaveBorder || LabelBorderStyleIds.LABEL_FULL_BORDER_STYLE_FOR_CONTAINER_ID.equals(labelBorderStyle.get().getId());
        }

        if (!shouldHaveBorder || isLabelHidden()) {
            if (rcf.getBorder() instanceof LineBorder) {
                // Do not draw the top line border for free form containers.
                rcf.setBorder(new MarginBorder(getMapMode().DPtoLP(1), 0, 0, 0));
            }
        } else if (rcf.getBorder() instanceof MarginBorder) {
            rcf.setBorder(new OneLineBorder(getMapMode().DPtoLP(1), PositionConstants.TOP));
        }
    }

    private boolean isLabelHidden() {
        EObject element = resolveSemanticElement();
        if (element instanceof DDiagramElement) {
            return new DDiagramElementQuery((DDiagramElement) element).isLabelHidden();
        }
        return false;
    }

    private Option<LabelBorderStyleDescription> getLabelBorderStyle() {
        EObject element = resolveSemanticElement();
        if (element instanceof DDiagramElementContainer) {
            DDiagramElementContainer ddec = (DDiagramElementContainer) element;
            if (ddec.getStyle() instanceof FlatContainerStyle && ddec.getStyle().getDescription() instanceof FlatContainerStyleDescription) {
                FlatContainerStyleDescription fcsd = (FlatContainerStyleDescription) ddec.getStyle().getDescription();
                return Options.newSome(fcsd.getLabelBorderStyle());
            }
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
    }

    @Override
    protected void setRatio(Double ratio) {
        if (getFigure().getParent() != null && getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
            super.setRatio(ratio);
        }
    }

    /*
     * Hide non-visible elements
     */
    @Override
    protected List getModelChildren() {
        // create a new view to avoid to change the super.getModelChildren list.
        List<View> modelChildren = Lists.newArrayList(Iterables.filter(super.getModelChildren(), View.class));
        DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);
        if (isRegionContainerCompartment() && getModel() instanceof View) {
            RegionContainerUpdateLayoutOperation.sortRegions((DNodeContainer) resolveSemanticElement(), modelChildren);
        }
        return modelChildren;
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
     * Return the set of {@link ConnectionNodeEditPart}s contained in the
     * supplied shape compartment.
     * 
     * @param scep
     *            a shape compartment.
     * @return a {@link Set} of {@link ConnectionNodeEditPart}.
     */
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
     * Return <tt>true</tt> if <tt>parent</tt> child's ancestor; otherwise
     * <tt>false</tt>
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
                layoutManager = new RegionContainerLayoutManager(true);
            } else if (query.get().isHorizontaltackContainer()) {
                layoutManager = new RegionContainerLayoutManager(false);
            }
        }

        if (layoutManager == null) {
            layoutManager = super.getLayoutManager();
        }
        return layoutManager;
    }

    /**
     * Specific layout manager to handle Regions.
     * 
     * @author mporhel
     */
    private static class RegionContainerLayoutManager extends FreeFormLayoutEx {

        private final boolean isVertical;

        /**
         * Constructor.
         * 
         * @param isVertical
         *            , true to layout as vertical stack.
         */
        public RegionContainerLayoutManager(boolean isVertical) {
            this.isVertical = isVertical;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void layout(IFigure parent) {
            Collection<IFigure> children = Lists.newArrayList(Iterables.filter(parent.getChildren(), IFigure.class));
            Point offset = getOrigin(parent);

            int maxWidth = 0;
            int maxHeight = 0;
            int minY = Integer.MAX_VALUE;
            Map<IFigure, Rectangle> regionsBounds = Maps.newHashMap();

            Rectangle bounds;
            // First step : compute freeform layout bounds and keep the common
            // known max width and minimum y.
            for (IFigure f : children) {
                bounds = (Rectangle) getConstraint(f);
                if (bounds == null)
                    continue;

                bounds = computeFreeFormLayoutExBounds(f, bounds.getCopy());

                regionsBounds.put(f, bounds);
                maxWidth = Math.max(maxWidth, bounds.width);
                maxHeight = Math.max(maxHeight, bounds.height);
                minY = Math.min(minY, bounds.y);
            }

            int labelWidth = 0;
            IFigure parentLabelFigure = getParentLabelFigure(parent);
            if (parentLabelFigure != null) {
                labelWidth = parentLabelFigure.getSize().width;

                // For vertical stacks, take label into account to compute the
                // region common size.
                if (isVertical) {
                    maxWidth = Math.max(maxWidth, labelWidth);
                }
            }

            int y = minY;
            int x = 0;

            // Second step : layout the regions, keep their order but use the
            // same width for vertical stacks and the same height for horizontal
            // stacks.
            for (IFigure f : children) {
                bounds = regionsBounds.get(f);

                bounds.x = x;
                bounds.y = y;

                if (isVertical) {
                    bounds.width = maxWidth;
                    y += bounds.height;
                } else {
                    bounds.height = maxHeight;
                    x += bounds.width;
                }

                setConstraint(f, bounds);
                f.setBounds(bounds.translate(offset));
            }

            // For horizontal stacks, if label is longer than the regions
            // cumulative width, increase the last region size.
            int delta = labelWidth - x;
            if (!isVertical && delta > 0 && !children.isEmpty()) {
                IFigure last = Iterables.getLast(children);
                bounds = regionsBounds.get(last);
                bounds.setWidth(bounds.width + delta);
                setConstraint(last, bounds);
                last.setBounds(bounds.translate(offset));
            }
        }

        private IFigure getParentLabelFigure(IFigure parent) {
            IFigure tmp = parent;
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
                    bounds.width = prefSize.width;
                }
                if (heightHint == -1) {
                    bounds.height = prefSize.height;
                }
            }
            Dimension min = f.getMinimumSize(widthHint, heightHint);
            Dimension max = f.getMaximumSize();

            if (min.width > bounds.width) {
                bounds.width = min.width;
            } else if (max.width < bounds.width) {
                bounds.width = max.width;
            }

            if (min.height > bounds.height) {
                bounds.height = min.height;
            } else if (max.height < bounds.height) {
                bounds.height = max.height;
            }
            return bounds;
        }
    };

    @Override
    public Object getAdapter(Class key) {
        if (key == SnapToHelper.class) {
            return SiriusSnapToHelperUtil.getSnapHelper(this);
        }
        return super.getAdapter(key);
    }
}
