/*******************************************************************************
 * Copyright (c) 2009, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.parts;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.Border;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutAnimator;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.CreateRequest;
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
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.edit.internal.part.DCompartmentConnectionRefreshMgr;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.SelectionCommandAppender;
import org.eclipse.sirius.diagram.graphical.edit.policies.AirXYLayoutEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.SiriusPopupBarEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.DNodeContainerViewNodeContainerCompartmentItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.canonicals.DumnySiriusCanonicalEditPolicy;
import org.eclipse.sirius.diagram.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.policies.ContainerCompartmentNodeEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.InvisibleResizableCompartmentFigure;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <p>
 * Abstract {@link EditPart} representing the Compartment zone of a
 * DNodeContainer.
 * </p>
 * 
 * @see {@link ShapeCompartmentEditPart}
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public abstract class AbstractDNodeContainerCompartmentEditPart extends ShapeCompartmentEditPart implements INotableEditPart {

    private static final int DEFAULT_MARGIN = 4;

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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getCommand(org.eclipse.gef.Request)
     */
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
        if (_request instanceof CreateRequest && ((CreateRequest) _request).getNewObject() instanceof MappingBasedToolDescription) {
            final Command command = super.getCommand(_request);
            if (command != null)
                return SelectionCommandAppender.addSelectionCommand(command, this);
        }
        Command command = super.getCommand(_request);
        return command;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart#refreshVisuals()
     */
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

                int borderSize = ownedStyle.getBorderSize().intValue();
                if (borderSize == 0) {
                    borderSize = 1;
                }
                if (getFigure() instanceof ResizableCompartmentFigure) {
                    ResizableCompartmentFigure rcf = (ResizableCompartmentFigure) getFigure();
                    if (rcf.getScrollPane() != null) {
                        // We should not have to report the border size here,
                        // but the compartment is not a child of the View
                        Border border;
                        int margin = borderSize + DEFAULT_MARGIN;
                        if (isRegionContainerCompartment()) {
                            // Keep the defaut beahavior
                            border = new MarginBorder(margin, borderSize, borderSize, borderSize);
                        } else {
                            border = new MarginBorder(margin, margin, margin, margin);
                        }
                        rcf.getScrollPane().setBorder(border);
                    }
                }
            }
        }
        super.refreshVisuals();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#createFigure()
     */
    public IFigure createFigure() {
        IMapMode mapMode = getMapMode();
        ShapeCompartmentFigure scf = new InvisibleResizableCompartmentFigure(getCompartmentName(), mapMode);
        scf.getContentPane().setLayoutManager(getLayoutManager());
        scf.getContentPane().addLayoutListener(LayoutAnimator.getDefault());
        scf.setTitleVisibility(false);
        scf.setToolTip((IFigure) null);

        return scf;
    }

    /**
     * Sets up a specific edit policy to handle drop requests. <br/>
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart#createDefaultEditPolicies()
     */
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
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DumnySiriusCanonicalEditPolicy());
        installEditPolicy(EditPolicy.CONTAINER_ROLE, new NodeCreationEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());
        removeEditPolicy(EditPolicy.LAYOUT_ROLE);
        installEditPolicy(EditPolicy.LAYOUT_ROLE, new AirXYLayoutEditPolicy());
        installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ContainerCompartmentNodeEditPolicy());
        installEditPolicy(EditPolicyRoles.POPUPBAR_ROLE, new SiriusPopupBarEditPolicy());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ResizableCompartmentEditPart#setRatio(java.lang.Double)
     */
    protected void setRatio(Double ratio) {
        if (getFigure().getParent() != null && getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
            super.setRatio(ratio);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getModelChildren()
     */
    /*
     * Hide non-visible elements
     */
    protected List getModelChildren() {
        // create a new view to avoid to change the super.getModelChildren list.
        List<View> modelChildren = Lists.newArrayList(Iterables.filter(super.getModelChildren(), View.class));
        DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);

        if (isRegionContainerCompartment() && getModel() instanceof View) {
            RegionContainerUpdateLayoutOperation.sortRegions((View) getModel(), modelChildren);
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
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refresh()
     */
    public void refresh() {
        if (resolveSemanticElement() != null && resolveSemanticElement().eResource() != null) {
            super.refresh();
        }
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.INotableEditPart#canAttachNote()
     */
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
            Iterator children = parent.getChildren().iterator();
            Point offset = getOrigin(parent);

            int maxWidth = 0;
            int maxHeight = 0;
            int minY = Integer.MAX_VALUE;
            Map<IFigure, Rectangle> regionsBounds = Maps.newHashMap();

            IFigure f;
            Rectangle bounds;
            // First step : compute freeform layout bounds and keep the common
            // known max width and minimum y.
            while (children.hasNext()) {
                f = (IFigure) children.next();
                bounds = (Rectangle) getConstraint(f);
                if (bounds == null)
                    continue;

                bounds = computeFreeFormLayoutExBounds(f, bounds.getCopy());

                regionsBounds.put(f, bounds);
                maxWidth = Math.max(maxWidth, bounds.width);
                maxHeight = Math.max(maxHeight, bounds.height);
                minY = Math.min(minY, bounds.y);
            }

            int y = minY;
            int x = 0;
            children = parent.getChildren().iterator();
            // Second step : layout the regions : same width and keep their
            // order.
            while (children.hasNext()) {
                f = (IFigure) children.next();
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
}
