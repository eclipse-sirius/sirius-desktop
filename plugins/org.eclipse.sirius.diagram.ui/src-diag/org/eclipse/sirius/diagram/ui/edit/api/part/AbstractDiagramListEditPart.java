/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
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
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramContainerEditPartOperation;
import org.eclipse.sirius.diagram.ui.edit.internal.part.DiagramListEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeListItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.api.figure.GradientRoundedRectangle;
import org.eclipse.sirius.diagram.ui.tools.api.figure.SiriusWrapLabel;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ViewNodeContainerFigureDesc;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;

import com.google.common.collect.Iterables;

/**
 * The default implementation of lists.
 * 
 * @author ymortier
 */
public abstract class AbstractDiagramListEditPart extends AbstractDiagramElementContainerEditPart implements IDiagramListEditPart {

    /**
     * Creates a new <code>AbstractDiagramListEditPart</code>.
     * 
     * @param view
     *            The GMF view.
     */
    public AbstractDiagramListEditPart(final View view) {
        super(view);
    }

    public Class<?> getMetamodelType() {
        return DNodeList.class;
    }

    /**
     * {@inheritDoc}
     * 
     * @see AbstractDiagramElementContainerEditPart#handleNotificationEvent(org.eclipse.sirius.diagram.edit.api.part.Notification)
     */
    @Override
    protected void handleNotificationEvent(final Notification notification) {
        super.handleNotificationEvent(notification);
        DiagramListEditPartOperation.handleNotificationEvent(this, notification);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshForegroundColor()
     */
    @Override
    protected void refreshForegroundColor() {
        super.refreshForegroundColor();
        DiagramListEditPartOperation.refreshForegroundColor(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refreshForegroundColor()
     */
    @Override
    protected void refreshBackgroundColor() {
        super.refreshBackgroundColor();
        DiagramListEditPartOperation.refreshBackgroundColor(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void refreshFont() {
        super.refreshFont();
        DiagramListEditPartOperation.refreshFont(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart#refreshVisuals()
     */
    @Override
    protected void refreshVisuals() {
        super.refreshVisuals();
        DiagramListEditPartOperation.refreshVisuals(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        Command result = null;
        RequestQuery requestQuery = new RequestQuery(request);
        if (requestQuery.isNoteCreationRequest() || requestQuery.isTextCreationRequest() || requestQuery.isNoteDropRequest() || requestQuery.isTextDropRequest()) {
            result = UnexecutableCommand.INSTANCE;
        } else if (RequestConstants.REQ_PASTE.equals(request.getType())) {
            for (ListCompartmentEditPart lcep : Iterables.filter(children, ListCompartmentEditPart.class)) {
                result = lcep.getCommand(request);
            }
        } else {
            final Command cmd = CommonEditPartOperation.appendSelectionCommand(super.getCommand(request), this);
            return CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, cmd);
        }

        if (result == null) {
            result = super.getCommand(request);
        }
        return CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, result);
    }

    /**
     * Reinit the figure. It removes the current children of the main figure
     * (created with a previous style) and replace them with those created with
     * the current style.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void reInitFigure() {
        final IFigure mainFigure = ((BorderedNodeFigure) getFigure()).getMainFigure();
        final List<IFigure> prevChildren = new ArrayList(mainFigure.getChildren());
        ResizableCompartmentFigure compartment = null;
        SiriusWrapLabel wrapLabel = null;
        final IFigure tmpFigure = createMainFigure();

        for (IFigure object : prevChildren) {
            if (object instanceof ViewNodeContainerFigureDesc) {
                for (Object object2 : ((ViewNodeContainerFigureDesc) object).getChildren()) {
                    if (object2 instanceof SiriusWrapLabel) {
                        wrapLabel = (SiriusWrapLabel) object2;
                    } else if (object2 instanceof ResizableCompartmentFigure) {
                        compartment = (ResizableCompartmentFigure) object2;
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
                figure.add(compartment);
            }

            mainFigure.add((IFigure) tmpChildren[i], i);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#getDragTracker(org.eclipse.gef.Request)
     */
    @Override
    public DragTracker getDragTracker(Request request) {
        return new NoCopyDragEditPartsTrackerEx(this);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.edit.api.part.IDiagramListEditPart#getPrimaryFigure()
     */
    public ViewNodeContainerFigureDesc getPrimaryFigure() {
        return getPrimaryShape();
    }

    /**
     * gets the content pane for the supplied editpart.
     * 
     * @param editPart
     *            the edit part to use to get the contents pane
     * @return <code>IFigure</code>
     */
    protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
        if (editPart instanceof IBorderItemEditPart) {
            return getBorderedFigure().getBorderItemContainer();
        } else {
            return getContentPane();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeListItemSemanticEditPolicy());
        /*
         * remove the connection items display
         */
        removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
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
        return new GradientRoundedRectangle(DiagramContainerEditPartOperation.getCornerDimension(this), DiagramContainerEditPartOperation.getBackgroundStyle(this));
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated
     */
    protected NodeFigure createNodePlate() {
        return new DefaultSizeNodeFigure(getMapMode().DPtoLP(40), getMapMode().DPtoLP(40));
    }
}
