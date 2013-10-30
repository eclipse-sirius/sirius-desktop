/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.edit.parts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.CreateRequest;
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
import org.eclipse.sirius.diagram.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.edit.internal.part.DiagramElementEditPartOperation;
import org.eclipse.sirius.diagram.edit.internal.part.SelectionCommandAppender;
import org.eclipse.sirius.diagram.graphical.edit.policies.LaunchToolEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.NodeCreationEditPolicy;
import org.eclipse.sirius.diagram.graphical.edit.policies.SiriusContainerDropPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.DNodeListViewNodeListCompartmentItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.internal.edit.policies.canonicals.DumnySiriusCanonicalEditPolicy;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

import com.google.common.collect.Iterables;

/**
 * <p>
 * Abstract {@link EditPart} representing the Compartment zone of a DNodeList.
 * </p>
 * 
 * @see {@link ListCompartmentEditPart}
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public abstract class AbstractDNodeListCompartmentEditPart extends ListCompartmentEditPart implements INotableEditPart, ISurfaceEditPart {

    /**
     * @not-generated : copy/paste support
     */
    private boolean isSupportingViewActions = false;

    private Comparator mappingComparator = new Comparator() {
        public int compare(Object arg0, Object arg1) {
            if (!(arg0 instanceof View) || !(arg1 instanceof View)) {
                throw new IllegalArgumentException();
            }
            View view0 = (View) arg0;
            View view1 = (View) arg1;
            EObject element0 = view0.getElement();
            EObject element1 = view1.getElement();
            if (element0 instanceof DMappingBased && element1 instanceof DMappingBased) {
                EObject eObj = resolveSemanticElement();
                if (eObj instanceof DNodeList) {
                    DNodeList container = (DNodeList) eObj;
                    List allMappings = container.getActualMapping().getAllNodeMappings();
                    RepresentationElementMapping origin0 = ((DMappingBased) element0).getMapping();
                    RepresentationElementMapping origin1 = ((DMappingBased) element1).getMapping();
                    return allMappings.indexOf(origin0) - allMappings.indexOf(origin1);
                }
            }
            return 0;
        }
    };

    private Comparator<View> indexComparator = new Comparator<View>() {
        public int compare(View o1, View o2) {
            final EObject semantic = resolveSemanticElement();
            if (semantic instanceof DNodeList) {
                final EObject sem1 = ViewUtil.resolveSemanticElement(o1);
                final EObject sem2 = ViewUtil.resolveSemanticElement(o2);
                if (sem1 instanceof DNodeListElement && sem2 instanceof DNodeListElement) {
                    return ((DNodeList) semantic).getNodes().indexOf(sem1) - ((DNodeList) semantic).getNodes().indexOf(sem2);
                }
            }
            return 0;
        }
    };

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
    protected List getModelChildren() {
        // create a new view to avoid to change the super.getModelChildren list.
        List modelChildren = new ArrayList(super.getModelChildren());
        DiagramElementEditPartOperation.removeInvisibleElements(modelChildren);
        Collections.sort((List<View>) modelChildren, indexComparator);
        Collections.sort(modelChildren, mappingComparator);
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
        } else if (_request instanceof CreateRequest && ((CreateRequest) _request).getNewObject() instanceof MappingBasedToolDescription) {
            final Command command = super.getCommand(_request);
            if (command != null)
                return SelectionCommandAppender.addSelectionCommand(command, this);
        }
        return super.getCommand(_request);
    }
}
