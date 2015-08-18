/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.gef.ui.figures.SlidableAnchor;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.figure.SequenceSlidableAnchor;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.ui.internal.view.factories.ViewLocationHint;

/**
 * Helper class to business code between different kinds of edit parts when
 * inheritance is not possible or inconvenient.
 * 
 * @author mporhel
 */
public final class ConnectionAnchorOperation {
    private ConnectionAnchorOperation() {
        // Prevent instantiation.
    }

    /**
     * If possible, returns an equivalent anchor to the one given, but
     * horizontally centered on its owner.
     * 
     * @param anchor
     *            the anchor.
     * @return an equivalent anchor horizontally centered on its owner.
     */
    public static ConnectionAnchor getHorizontallyCenteredAnchor(ConnectionAnchor anchor) {
        return getCorrectedAnchor(anchor, null, 0.5);
    }

    /**
     * If possible, returns an equivalent anchor to the one given, but
     * horizontally centered on its owner north face.
     * 
     * @param anchor
     *            the anchor.
     * @return an equivalent anchor horizontally centered on its owner north
     *         face.
     */
    public static ConnectionAnchor getHorizontallyCenteredAndTopAnchor(ConnectionAnchor anchor) {
        return getCorrectedAnchor(anchor, "(0.5,0)", 0.5); //$NON-NLS-1$
    }

    /**
     * If possible, returns an equivalent anchor to the one given, but
     * horizontally centered on its owner south face.
     * 
     * @param anchor
     *            the anchor.
     * @return an equivalent anchor horizontally centered on its owner north
     *         face.
     */
    public static ConnectionAnchor getHorizontallyCenteredAndBottomAnchor(ConnectionAnchor anchor) {
        return getCorrectedAnchor(anchor, "(0.5,1)", 0.5); //$NON-NLS-1$
    }

    private static ConnectionAnchor getCorrectedAnchor(ConnectionAnchor anchor, String defaultId, double preciseX) {
        ConnectionAnchor result = anchor;
        if (anchor instanceof SlidableAnchor) {
            String id = ((SlidableAnchor) anchor).getTerminal();
            if (StringUtil.isEmpty(id) && !StringUtil.isEmpty(defaultId)) {
                id = defaultId;
            }
            if (!StringUtil.isEmpty(id)) {
                PrecisionPoint pp = BaseSlidableAnchor.parseTerminalString(id);
                if (pp != null) {
                    pp.setPreciseX(preciseX);
                    result = new SequenceSlidableAnchor(anchor, pp);
                }
            }
        }
        return result;
    }

    /**
     * Adjusts the Y location of a request to match the Y location of the source
     * anchor as stored by ViewLocationHint. This can be used before a call to
     * {@link #getTargetConnectionAnchor(org.eclipse.gef.ConnectionEditPart)} to
     * make sure a connection is horizontal.
     * 
     * @param request
     *            the request to modify.
     */
    public static void matchRequestYLocationWithSourceAnchor(DropRequest request) {
        if (request instanceof Request && !new RequestQuery((Request) request).isSequenceMessageCreation()) {
            return;
        } else if (request.getLocation() != null) {
            Object sourceLocation = ViewLocationHint.getInstance().getData(ViewLocationHint.SOURCE_ANCHOR_LOCATION);
            if (sourceLocation instanceof Point && request instanceof CreateConnectionRequest) {
                EditPart sourceEP = ((CreateConnectionRequest) request).getSourceEditPart();
                EditPart targetEP = ((CreateConnectionRequest) request).getTargetEditPart();
                Point sourceLocationPoint = ((Point) sourceLocation).getCopy();
                if (!sourceEP.equals(targetEP)) {
                    request.getLocation().y = sourceLocationPoint.y;
                } else if (request.getLocation().y <= sourceLocationPoint.y) {
                    request.getLocation().y = sourceLocationPoint.y;
                }
            }
        }
    }

    /**
     * Return an horizontally centered anchor.
     * 
     * @param self
     *            .
     * @param request
     *            .
     * @param superSourceAnchor
     *            .
     * @return .
     */
    public static ConnectionAnchor getSourceConnectionAnchor(ISequenceEventEditPart self, Request request, ConnectionAnchor superSourceAnchor) {
        ConnectionAnchor result = ConnectionAnchorOperation.getHorizontallyCenteredAnchor(superSourceAnchor);

        if (request instanceof CreateConnectionRequest) {
            CreateConnectionRequest ccr = (CreateConnectionRequest) request;
            if (ccr.getLocation() == null) {
                result = ConnectionAnchorOperation.getHorizontallyCenteredAndBottomAnchor(superSourceAnchor);
            }
        }
        return result;
    }

    /**
     * During message creation, use the same y location as the corresponding
     * source connection anchor, stored in ViewLocationHint, to improve user
     * feedback.
     * 
     * @param self
     *            .
     * @param request
     *            .
     * @param superTargetAnchor
     *            .
     * @return .
     */
    public static ConnectionAnchor getTargetConnectionAnchor(ISequenceEventEditPart self, Request request, ConnectionAnchor superTargetAnchor) {
        ConnectionAnchor result = ConnectionAnchorOperation.getHorizontallyCenteredAnchor(superTargetAnchor);
        if (request instanceof CreateConnectionRequest) {
            CreateConnectionRequest ccr = (CreateConnectionRequest) request;
            if (ConnectionAnchorOperation.isCreateMessageToSelfRequest(ccr)) {
                result = ConnectionAnchorOperation.getHorizontallyCenteredAndTopAnchor(superTargetAnchor);
            } else {
                result = ConnectionAnchorOperation.getHorizontallyCenteredAnchor(superTargetAnchor);
            }
        } else if (request instanceof ReconnectRequest && !(((ReconnectRequest) request).getConnectionEditPart() instanceof NoteAttachmentEditPart)) {
            ReconnectRequest conn = (ReconnectRequest) request;
            ConnectionEditPart cep = (ConnectionEditPart) conn.getConnectionEditPart();
            result = cep.getConnectionFigure().getTargetAnchor();
        } else {
            result = superTargetAnchor;
        }
        return result;
    }

    private static boolean isCreateMessageToSelfRequest(CreateConnectionRequest ccr) {
        if ((ccr.getSourceEditPart() instanceof ExecutionEditPart || ccr.getSourceEditPart() instanceof LifelineEditPart)
                && (ccr.getTargetEditPart() instanceof ExecutionEditPart || ccr.getTargetEditPart() instanceof LifelineEditPart)) {
            LifelineEditPart sourceParentLifeline = EditPartsHelper.findParentLifeline((IGraphicalEditPart) ccr.getSourceEditPart());
            LifelineEditPart targetParentLifeline = EditPartsHelper.findParentLifeline((IGraphicalEditPart) ccr.getTargetEditPart());
            return sourceParentLifeline.equals(targetParentLifeline);
        }
        return false;
    }

    /**
     * Get an horizontally and vertically centered anchor.
     * 
     * @param anchor
     *            an anchor.
     * @return a centered anchor.
     */
    public static ConnectionAnchor safeCenterAnchor(ConnectionAnchor anchor) {
        ConnectionAnchor result = anchor;
        if (result != null) {
            result = new SequenceSlidableAnchor(anchor, new PrecisionPoint(0.5, 0.5));
        }
        return result;
    }
}
