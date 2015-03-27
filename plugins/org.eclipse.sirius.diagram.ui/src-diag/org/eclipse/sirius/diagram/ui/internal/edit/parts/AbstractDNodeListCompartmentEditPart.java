/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INotableEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.ISurfaceEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.ISiriusEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeListViewNodeListCompartmentItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.canonicals.DumnySiriusCanonicalEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * <p>
 * Abstract {@link EditPart} representing the Compartment zone of a DNodeList.
 * </p>
 * 
 * @see {@link ListCompartmentEditPart}
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractDNodeListCompartmentEditPart extends ListCompartmentEditPart implements INotableEditPart, ISurfaceEditPart, ISiriusEditPart {

    /**
     * @not-generated : copy/paste support
     */
    private boolean isSupportingViewActions = false;

    private static class ComparisonHelper {
        private DNodeList self;

        public ComparisonHelper(DNodeList self) {
            this.self = self;
        }

        public void sort(List<View> views) {
            /*
             * The main sort criterion is based on the elements' mapping's
             * position in the VSM, so that all instances of the same mapping
             * are grouped together, and if a mapping M1 appears before another
             * M2 in the specification, all instances of M1 appear before those
             * of M2.
             */
            final EList<NodeMapping> allMappings = self.getActualMapping().getAllNodeMappings();
            Function<View, Integer> mappingIndex = new Function<View, Integer>() {
                @Override
                public Integer apply(View view) {
                    if (view != null) {
                        EObject element = view.getElement();
                        if (element instanceof DMappingBased) {
                            RepresentationElementMapping mapping = ((DMappingBased) element).getMapping();
                            /*
                             * Use a plain indexOf search here, assuming that in
                             * practice there are never more than a handful of
                             * mappings inside a list container.
                             */
                            return allMappings.indexOf(mapping);
                        }
                    }
                    return Integer.MAX_VALUE;
                }
            };
            /*
             * Inside a group of elements from the same mapping, use the
             * DNodeListItem order. As opposed to the mappings, the number of
             * actual items can grow very large, so we pre-compute the elements'
             * indices with a linear scan to avoid repeated calls to indexOf for
             * each comparison.
             */
            final Map<DNodeListElement, Integer> indices = Maps.newHashMap();
            EList<DNodeListElement> listElements = self.getOwnedElements();
            int i = 0;
            for (DNodeListElement current : listElements) {
                indices.put(current, i);
                i++;
            }
            Function<View, Integer> nodeIndex = new Function<View, Integer>() {
                @Override
                public Integer apply(View view) {
                    if (view != null) {
                        EObject sem = ViewUtil.resolveSemanticElement(view);
                        if (sem != null && indices.containsKey(sem)) {
                            return indices.get(sem);
                        }
                    }
                    return Integer.MAX_VALUE;
                }
            };
            /*
             * Perform the actual sort, combining the two criteria above.
             */
            Collections.sort(views, Ordering.natural().onResultOf(mappingIndex).compound(Ordering.natural().onResultOf(nodeIndex)));
        }
    }

    /**
     * Creates a new AbstractDNodeListCompartmentEditPart.
     * 
     * @param view
     *            the view controlled by this editpart
     */
    public AbstractDNodeListCompartmentEditPart(View view) {
        super(view);
    }

    /**
     * @not-generated :drag/drop
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeListViewNodeListCompartmentItemSemanticEditPolicy());
        installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
        installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new SiriusContainerDropPolicy());
        installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new DumnySiriusCanonicalEditPolicy());
        installEditPolicy(EditPolicy.CONTAINER_ROLE, new NodeCreationEditPolicy());
        installEditPolicy(RequestConstants.REQ_LAUNCH_TOOL, new LaunchToolEditPolicy());
    }

    public boolean isSupportingViewActions() {
        return this.isSupportingViewActions;
    }

    /**
     * {@inheritDoc}
     * 
     * @not-generated : need for copy/paste support
     * @see org.eclipse.gmf.runtime.diagram.ui.internal.editparts.ISurfaceEditPart#setIsSupportingViewActions(boolean)
     */
    public void setIsSupportingViewActions(boolean supportsViewActions) {
        this.isSupportingViewActions = supportsViewActions;

    }

    /**
     * @was-generated
     */
    protected boolean hasModelChildrenChanged(Notification evt) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public IFigure createFigure() {
        ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
        result.setTitleVisibility(false);
        result.setToolTip((IFigure) null);

        // Now that the border size is taken into account to calculate border
        // margin; reduce the scroll pane insets to retrieve the previous
        // minimum/preferred size, scroll-bar visibility condition for one pixel
        // borders.
        IFigure contentPane = result.getContentPane();
        if (contentPane != null && contentPane.getBorder() instanceof MarginBorder) {
            Insets insets = contentPane.getBorder().getInsets(result);
            Insets legacyBorderCompensation = new Insets(0, -1, -1, -1);
            contentPane.setBorder(new MarginBorder(insets.getAdded(legacyBorderCompensation)));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    protected void setRatio(Double ratio) {
        if (getFigure().getParent() != null && getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
            super.setRatio(ratio);
        }
    }

    /*
     * hide non visible elements
     */
    @Override
    protected List<?> getModelChildren() {
        @SuppressWarnings("unchecked")
        List<View> modelChildren = Lists.newArrayList(super.getModelChildren());
        DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);
        new ComparisonHelper((DNodeList) resolveSemanticElement()).sort(modelChildren);
        return modelChildren;
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
     * 
     * {@inheritDoc}
     * 
     * @not-generated : need for copy/paste support
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.CompartmentEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    public DragTracker getDragTracker(Request request) {
        return getParent().getDragTracker(request);
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @not-generated : need for copy/paste support
     * @see org.eclipse.gmf.runtime.diagram.ui.internal.editparts.ISurfaceEditPart#getPrimaryEditParts()
     */
    public List getPrimaryEditParts() {
        List connections = new ArrayList();

        Object diagramEditPart = getViewer().getEditPartRegistry().get(getDiagramView());

        List shapes = getChildren();
        Set connectableEditParts = new HashSet(shapes);
        Iterator iter = shapes.iterator();
        while (iter.hasNext()) {
            getBorderItemEditParts((EditPart) iter.next(), connectableEditParts);
        }

        if (diagramEditPart instanceof DiagramEditPart) {
            Iterator diagramConnections = ((DiagramEditPart) diagramEditPart).getConnections().iterator();
            while (diagramConnections.hasNext()) {
                ConnectionEditPart connection = (ConnectionEditPart) diagramConnections.next();
                if (connectableEditParts.contains(connection.getSource()) || connectableEditParts.contains(connection.getTarget()))
                    connections.add(connection);
            }
        }

        if (connections.size() > 0 || shapes.size() > 0) {
            List primaryEditParts = new ArrayList();
            primaryEditParts.addAll(shapes);
            primaryEditParts.addAll(connections);
            return primaryEditParts;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * This method searches an edit part for a child that is a border item edit
     * part
     * 
     * @not-generated : need for copy/paste support
     * @param parent
     *            part needed to search
     * @param set
     *            to be modified of border item edit parts that are direct
     *            children of the parent
     */
    private void getBorderItemEditParts(EditPart parent, Set retval) {

        Iterator iter = parent.getChildren().iterator();
        while (iter.hasNext()) {
            EditPart child = (EditPart) iter.next();
            if (child instanceof IBorderItemEditPart) {
                retval.add(child);
                retval.addAll(child.getChildren());
            }
            getBorderItemEditParts(child, retval);
        }
    }

    /**
     * Overridden to refresh {@link DNodeListElementEditPart} for example to
     * refresh label alignment.
     */
    @Override
    public void refresh() {
        super.refresh();

        Iterable<EditPart> children = Iterables.filter(getChildren(), EditPart.class);
        for (EditPart childEditPart : children) {
            childEditPart.refresh();
        }
    }

    /**
     * @not-generated : need for selection support {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getCommand(org.eclipse.gef.Request)
     */
    public Command getCommand(Request _request) {
        RequestQuery requestQuery = new RequestQuery(_request);
        if (requestQuery.isNoteCreationRequest() || requestQuery.isTextCreationRequest() || requestQuery.isNoteDropRequest() || requestQuery.isTextDropRequest()) {
            return UnexecutableCommand.INSTANCE;
        }
        return super.getCommand(_request);
    }
}
