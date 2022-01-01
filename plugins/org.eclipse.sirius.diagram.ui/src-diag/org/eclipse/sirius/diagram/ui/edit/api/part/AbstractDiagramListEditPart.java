/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.DirectEditRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.internal.part.CommonEditPartOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.policies.DNodeListItemSemanticEditPolicy;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.ViewNodeContainerFigureDesc;

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

    @Override
    public Class<?> getMetamodelType() {
        return DNodeList.class;
    }

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
            Command cmd = super.getCommand(request);
            return CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, cmd);
        }

        if (result == null) {
            result = super.getCommand(request);
        }
        return CommonEditPartOperation.handleAutoPinOnInteractiveMove(this, request, result);
    }

    @Override
    public DragTracker getDragTracker(Request request) {
        if (shouldUseRegionsContainerDragTracker(request)) {
            return getParent().getDragTracker(request);
        } else {
            return new NoCopyDragEditPartsTrackerEx(this);
        }
    }

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
    @Override
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
    @Override
    protected void createDefaultEditPolicies() {
        super.createDefaultEditPolicies();
        installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new DNodeListItemSemanticEditPolicy());
        /*
         * remove the connection items display
         */
        removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
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
}
