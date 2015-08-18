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
package org.eclipse.sirius.diagram.ui.business.internal.query;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Queries on GEF and GMF requests.
 * 
 * @author edugueperoux
 */
@SuppressWarnings("restriction")
public class RequestQuery {

    /**
     * The contextual {@link Request}.
     */
    protected final Request request;

    /**
     * Default constructor.
     * 
     * @param request
     *            the {@link Request} on which to do queries.
     */
    public RequestQuery(Request request) {
        this.request = request;
    }

    /**
     * Checks if the current {@link Request} request is about a Note creation.
     * 
     * @return true if the current {@link Request} request is about a Note
     *         creation
     */
    public boolean isNoteCreationRequest() {
        boolean isNoteCreationRequest = false;
        if (request instanceof CreateViewRequest) {
            CreateViewRequest cvr = (CreateViewRequest) request;
            for (ViewDescriptor viewDesc : Iterables.filter(cvr.getViewDescriptors(), ViewDescriptor.class)) {
                if ("Note".equals(viewDesc.getSemanticHint())) { //$NON-NLS-1$
                    isNoteCreationRequest = true;
                    break;
                }
            }
        }
        return isNoteCreationRequest;
    }

    /**
     * Checks if the current {@link Request} request is about a GMF Note
     * connection creation.
     * 
     * @return true if the current {@link Request} request is about a GMF Note
     *         connection creation
     */
    public boolean isNoteAttachmentCreationRequest() {
        boolean isNoteAttachmentCreationRequest = false;
        if (request instanceof CreateConnectionViewRequest) {
            CreateConnectionViewRequest cvr = (CreateConnectionViewRequest) request;
            isNoteAttachmentCreationRequest = "NoteAttachment".equals(cvr.getConnectionViewDescriptor().getSemanticHint()); //$NON-NLS-1$
        }
        return isNoteAttachmentCreationRequest;
    }

    /**
     * Checks if the current {@link Request} request is about a GMF Text
     * creation.
     * 
     * @return true if the current {@link Request} request is about a GMF Text
     *         creation
     */
    public boolean isTextCreationRequest() {
        boolean isTextCreationRequest = false;
        if (request instanceof CreateViewRequest) {
            CreateViewRequest cvr = (CreateViewRequest) request;
            for (ViewDescriptor viewDesc : Iterables.filter(cvr.getViewDescriptors(), ViewDescriptor.class)) {
                if ("Text".equals(viewDesc.getSemanticHint())) { //$NON-NLS-1$
                    isTextCreationRequest = true;
                    break;
                }
            }
        }
        return isTextCreationRequest;
    }

    /**
     * Checks if the current {@link Request} request is about a GMF Note drop.
     * 
     * @return true if the current {@link Request} request is about a GMF Note
     *         drop
     */
    public boolean isNoteDropRequest() {
        boolean isNoteDropRequest = false;
        if (RequestConstants.REQ_ADD.equals(request.getType()) && request instanceof GroupRequest) {
            GroupRequest groupRequest = (GroupRequest) request;
            isNoteDropRequest = !Iterables.isEmpty(Iterables.filter(groupRequest.getEditParts(), NoteEditPart.class));
        }
        return isNoteDropRequest;
    }

    /**
     * Checks if the current {@link Request} request is about a GMF Text drop.
     * 
     * @return true if the current {@link Request} request is about a GMF Text
     *         drop
     */
    public boolean isTextDropRequest() {
        boolean isTextDropRequest = false;
        if (RequestConstants.REQ_ADD.equals(request.getType()) && request instanceof GroupRequest) {
            GroupRequest groupRequest = (GroupRequest) request;
            isTextDropRequest = !Iterables.isEmpty(Iterables.filter(groupRequest.getEditParts(), TextEditPart.class));
        }
        return isTextDropRequest;
    }

    /**
     * Tests whether the request is for a direct move. "Direct move" does not
     * include requests on a container to move its children.
     * 
     * @return <code>true</code> if the request asks for a direct move.
     */
    public boolean isMove() {
        Object type = request.getType();
        return RequestConstants.REQ_MOVE.equals(type) || org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants.REQ_DROP.equals(type);
    }

    /**
     * Tests whether the request is for a direct resize. "Direct resize" does
     * not include requests on a container to resize its children.
     * 
     * @return <code>true</code> if the request asks for a direct resize.
     */
    public boolean isResize() {
        return RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType()) || RequestConstants.REQ_RESIZE.equals(request.getType());
    }

    /**
     * Tests whether the request is for direct resizing an element from its top
     * (north) side. The resizing may be to make it bigger or smaller.
     * "Direct resize" does not include requests on a container to resize its
     * children.
     * 
     * @return <code>true</code> if the request is to resize an element from its
     *         top side.
     */
    public boolean isResizeFromTop() {
        boolean isResizeFromTop = false;
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
            isResizeFromTop = (RequestConstants.REQ_RESIZE_CHILDREN.equals(cbr.getType()) || RequestConstants.REQ_RESIZE.equals(cbr.getType()))
                    && (cbr.getResizeDirection() & PositionConstants.NORTH) != 0;
        }
        return isResizeFromTop;
    }

    /**
     * Tests whether the request is for direct resizing an element from its
     * bottom (south) side. The resizing may be to make it bigger or smaller.
     * "Direct resize" does not include requests on a container to resize its
     * children.
     * 
     * @return <code>true</code> if the request is to resize an element from its
     *         bottom side.
     */
    public boolean isResizeFromBottom() {
        boolean isResizeFromBottom = false;
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
            isResizeFromBottom = (RequestConstants.REQ_RESIZE_CHILDREN.equals(cbr.getType()) || RequestConstants.REQ_RESIZE.equals(cbr.getType()))
                    && (cbr.getResizeDirection() & PositionConstants.SOUTH) != 0;
        }
        return isResizeFromBottom;
    }

    /**
     * Tests whether the request is for direct resizing an element from its
     * right (east) side. The resizing may be to make it bigger or smaller.
     * "Direct resize" does not include requests on a container to resize its
     * children.
     * 
     * @return <code>true</code> if the request is to resize an element from its
     *         right side.
     */
    public boolean isResizeFromRight() {
        boolean isResizeFromRight = false;
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
            isResizeFromRight = (RequestConstants.REQ_RESIZE_CHILDREN.equals(cbr.getType()) || RequestConstants.REQ_RESIZE.equals(cbr.getType()))
                    && (cbr.getResizeDirection() & PositionConstants.EAST) != 0;
        }
        return isResizeFromRight;
    }

    /**
     * Tests whether the request is for direct resizing an element from its left
     * (west) side. The resizing may be to make it bigger or smaller.
     * "Direct resize" does not include requests on a container to resize its
     * children.
     * 
     * @return <code>true</code> if the request is to resize an element from its
     *         left side.
     */
    public boolean isResizeFromLeft() {
        boolean isResizeFromLeft = false;
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;
            isResizeFromLeft = (RequestConstants.REQ_RESIZE_CHILDREN.equals(cbr.getType()) || RequestConstants.REQ_RESIZE.equals(cbr.getType()))
                    && (cbr.getResizeDirection() & PositionConstants.WEST) != 0;
        }
        return isResizeFromLeft;
    }

    /**
     * Tests whether the request is sent to several different selected parts.
     * 
     * @return <code>true</code> if the request is sent to several different
     *         selected parts.
     */
    public boolean isMultiSelectionOperation() {
        if (request instanceof GroupRequest) {
            GroupRequest gr = (GroupRequest) request;
            return gr.getEditParts() != null && gr.getEditParts().size() > 1;
        } else {
            return false;
        }
    }

    /**
     * .
     * 
     * @return .
     */
    public Rectangle getLogicalDelta() {
        return getLogicalTransformedRectangle(new Rectangle());
    }

    /**
     * If the request is a {@link ChangeBoundsRequest}, it will return the
     * transformed rectangle.
     * 
     * @param bounds
     *            the rectangle to transform.
     * @return transformed rectangle.
     */
    public Rectangle getLogicalTransformedRectangle(Rectangle bounds) {
        Rectangle result = bounds.getCopy();
        if (request instanceof ChangeBoundsRequest) {
            ChangeBoundsRequest cbr = (ChangeBoundsRequest) request;

            Option<IGraphicalEditPart> part = getOnePart();
            if (part.some()) {
                GraphicalHelper.logical2screen(result, part.get());
            }
            result = cbr.getTransformedRectangle(result);
            if (part.some()) {
                GraphicalHelper.screen2logical(result, part.get());
            }
        }
        return result;
    }

    /**
     * Returns the first EditParts of the List containing the EditParts making
     * this Request.
     * 
     * @return The first EditParts of the List containing the EditParts making
     *         this Request.
     */
    protected Option<IGraphicalEditPart> getOnePart() {
        List<IGraphicalEditPart> editParts = getEditParts();
        if (editParts != null && !editParts.isEmpty()) {
            return Options.newSome(editParts.get(0));
        }
        return Options.newNone();
    }

    /**
     * Returns a List containing the EditParts making this Request.
     * 
     * @return A List containing the EditParts making this Request.
     */
    protected List<IGraphicalEditPart> getEditParts() {
        if (request instanceof GroupRequest) {
            return Lists.newArrayList(Iterables.filter(((GroupRequest) request).getEditParts(), IGraphicalEditPart.class));
        }
        return Collections.emptyList();
    }

    /**
     * Check if:
     * <UL>
     * <LI>all mappings of the creation tool of this request correspond to
     * BorderNode mapping,</LI>
     * <LI>or if all edit parts of the drop request is BorderNode edit part.
     * Warning the result is only available in case of drag'n'drop from diagram,
     * not from other view because in this case, the edit part is not
     * "real edit part" but a @link
     * {@link org.eclipse.sirius.diagram.ui.tools.internal.dnd.DragAndDropWrapper}
     * . The only solution in this case to know if it is a drop a element that
     * became bordered nodes is to call
     * {@link org.eclipse.sirius.diagram.ui.graphical.edit.internal.policies.validators.DragAndDropValidator#isConcerningOnlyBorderNodeFromView()}
     * .</LI>
     * </UL>
     * 
     * @return true in case of request concerning bordered nodes, false
     *         otherwise.
     */
    public boolean isDropOrCreationOfBorderNode() {
        boolean isConcerningBorderNode = false;
        if (request instanceof CreateRequest) {
            CreateRequest createRequest = (CreateRequest) request;
            if (createRequest.getNewObject() instanceof NodeCreationDescriptionImpl) {
                isConcerningBorderNode = true;
                NodeCreationDescriptionImpl nodeCreationDescriptionImpl = (NodeCreationDescriptionImpl) createRequest.getNewObject();
                for (NodeMapping nodeMapping : nodeCreationDescriptionImpl.getNodeMappings()) {
                    if (!DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings().equals(nodeMapping.eContainingFeature())) {
                        isConcerningBorderNode = false;
                    }
                }
            }
        } else if (RequestConstants.REQ_DROP.equals(request.getType())) {
            isConcerningBorderNode = !getEditParts().isEmpty() && Iterables.all(getEditParts(), Predicates.instanceOf(IBorderItemEditPart.class));
        }
        return isConcerningBorderNode;
    }

}
