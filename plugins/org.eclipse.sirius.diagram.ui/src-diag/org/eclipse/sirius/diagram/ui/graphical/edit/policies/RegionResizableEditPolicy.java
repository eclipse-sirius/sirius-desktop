/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A {@link AirResizableEditPolicy} able to handle regions.
 * 
 * @author mporhel
 */
public class RegionResizableEditPolicy extends AirResizableEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * Configure the edit policy regarding the host.
     */
    @Override
    public void setHost(EditPart host) {
        super.setHost(host);

        if (concernRegion()) {
            setResizeDirections(getStackDirection());
            setDragAllowed(false);
        }
    }

    /**
     * For regions, only put resize handles on authorized directions.
     *
     * {@inheritDoc}
     */
    @Override
    protected void createResizeHandle(List handles, int direction) {
        if (!concernRegion() || (getResizeDirections() & direction) == direction) {
            super.createResizeHandle(handles, direction);
        }
    }

    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        if (concernRegion()) {
            return UnexecutableCommand.INSTANCE;
        }

        return super.getMoveCommand(request);
    }

    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        if (concernRegion()) {
            return UnexecutableCommand.INSTANCE;
        }
        return super.getAlignCommand(request);
    }

    @Override
    protected Command getAutoSizeCommand(Request request) {
        Command autoSizeCommand = super.getAutoSizeCommand(request);
        if (concernRegion()) {
            EditPart regionContainerPart = getRegionContainerPart();
            Object object = request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_AUTO_SIZE_PROPAGATOR);
            if (object == getHost()) {
                autoSizeCommand = null;
            } else if (object != regionContainerPart) {
                // Redirect the auto-size to the parent RegionContainer
                Request req = new Request();
                req.setType(request.getType());
                req.getExtendedData().put(RegionContainerResizableEditPolicy.REGION_AUTO_SIZE_PROPAGATOR, getHost());

                Command regionContainerAutoSizeCommand = regionContainerPart.getCommand(req);
                if (getHost() instanceof IDiagramElementEditPart && regionContainerAutoSizeCommand != null) {
                    CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(((IDiagramElementEditPart) getHost()).getEditingDomain(), "Region Auto Size Composite Command");
                    ctc.add(new CommandProxy(autoSizeCommand));
                    ctc.add(new CommandProxy(regionContainerAutoSizeCommand));
                    autoSizeCommand = new ICommandProxy(ctc);
                }
            }
        }
        return autoSizeCommand;
    }

    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.showChangeBoundsFeedback(request);

        if (concernRegion()) {
            Option<ChangeBoundsRequest> siblingRequest = getConstrainedSiblingRequest(request);
            if (siblingRequest.some() && siblingRequest.get().getEditParts() != null && siblingRequest.get().getEditParts().size() == 1) {
                if (siblingRequest.get().getTransformedRectangle(new Rectangle()).equals(new Rectangle())) {
                    return;
                }
                EditPart siblingPart = (EditPart) siblingRequest.get().getEditParts().get(0);
                siblingPart.showSourceFeedback(siblingRequest.get());
            }
        }
    }

    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.eraseChangeBoundsFeedback(request);

        if (concernRegion()) {
            Option<ChangeBoundsRequest> siblingRequest = getConstrainedSiblingRequest(request);
            if (siblingRequest.some() && siblingRequest.get().getEditParts() != null && siblingRequest.get().getEditParts().size() == 1) {
                if (siblingRequest.get().getTransformedRectangle(new Rectangle()).equals(new Rectangle())) {
                    return;
                }
                EditPart siblingPart = (EditPart) siblingRequest.get().getEditParts().get(0);
                siblingPart.eraseSourceFeedback(siblingRequest.get());
            }
        }
    }

    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        if (isFirstRegionPart()) {
            request.getMoveDelta().setX(0);
            request.getMoveDelta().setY(0);
        } else if (getStackDirection() == PositionConstants.EAST_WEST) {
            request.getMoveDelta().setY(0);
        }
        return super.getResizeCommand(request);
    }

    /**
     * Complete the given composite command with Region specific resize
     * commands: the commands to report the resize on the RegionContainer or on
     * the sibling regions.
     */
    @Override
    protected void completeResizeCommand(CompositeTransactionalCommand ctc, ChangeBoundsRequest request) {
        boolean invalidRequest = request.getEditParts().size() > 1 && !request.isConstrainedResize() || request.isCenteredResize();
        if (invalidRequest || !validateResize(request)) {
            ctc.add(org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE);
        }

        Option<ChangeBoundsRequest> siblingRequest = getConstrainedSiblingRequest(request);
        if (siblingRequest.some() && siblingRequest.get().getEditParts() != null && siblingRequest.get().getEditParts().size() == 1) {
            EditPart siblingPart = (EditPart) siblingRequest.get().getEditParts().get(0);
            ctc.add(new CommandProxy(siblingPart.getCommand(siblingRequest.get())));
        } else if (!(request.isConstrainedMove() || request.isConstrainedResize())) {
            ctc.add(org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE);
        }

        Option<ChangeBoundsRequest> adjustChildrenRequest = getAdjustChildrenRequest(request);
        if (adjustChildrenRequest.some()) {
            super.completeResizeCommand(ctc, adjustChildrenRequest.get());
        }
    }

    /**
     * Return true to add the default {@link AirResizableEditPolicy} behavior in
     * completeResizeCommand: this will add the sub commands to adjust children
     * position: see
     * {@link org.eclipse.sirius.diagram.ui.internal.edit.commands.ChildrenAdjustmentCommand}
     * .
     * 
     * @param request
     *            the current change bounds request.
     * @return true to add the default {@link AirResizableEditPolicy} behavior.
     */
    protected Option<ChangeBoundsRequest> getAdjustChildrenRequest(ChangeBoundsRequest request) {
        ChangeBoundsRequest requestToCompensate = null;
        RequestQuery requestQuery = new RequestQuery(request);
        if (request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) == getRegionContainerPart()) {
            Object object = request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_INITIAL_REQUEST);
            int stackDirection = getStackDirection();
            boolean needsAdjust = false;
            if (stackDirection == PositionConstants.NORTH_SOUTH) {
                needsAdjust = requestQuery.isResizeFromLeft() || requestQuery.isResizeFromRight() || requestQuery.isResizeFromTop() && isFirstRegionPart();
            } else if (stackDirection == PositionConstants.EAST_WEST) {
                needsAdjust = requestQuery.isResizeFromTop() || requestQuery.isResizeFromBottom() || requestQuery.isResizeFromLeft() && isFirstRegionPart();
            }
            if (needsAdjust && object instanceof ChangeBoundsRequest) {
                requestToCompensate = (ChangeBoundsRequest) object;
            }
        } else {
            if (isFirstRegionPart() && (requestQuery.isResizeFromLeft() || requestQuery.isResizeFromTop())) {
                // The move part has been canceled for the concrete resize of
                // the region, because the move will be done on the region
                // container but we need to compensate the move on the children.
                Dimension delta = request.getSizeDelta().getNegated();
                request.setMoveDelta(new Point(delta.width, delta.height));
            }
            requestToCompensate = request;
        }
        return Options.newSome(requestToCompensate);
    }

    private boolean validateResize(ChangeBoundsRequest request) {
        IFigure hostFigure = getHostFigure();
        if (hostFigure != null) {
            Dimension minimumSize = hostFigure.getMinimumSize().getCopy();
            Rectangle resultingBounds = new RequestQuery(request).getLogicalTransformedRectangle(hostFigure.getBounds().getCopy());
            return resultingBounds.width >= minimumSize.width && resultingBounds.height >= minimumSize.height;
        }
        return false;
    }

    private Option<ChangeBoundsRequest> getConstrainedSiblingRequest(ChangeBoundsRequest request) {
        Option<ChangeBoundsRequest> constrainedRequest = Options.newNone();

        RequestQuery query = new RequestQuery(request);
        Dimension sizeDelta = request.getSizeDelta().getCopy();

        int stackDirection = getStackDirection();
        if (stackDirection == PositionConstants.NORTH_SOUTH) {
            constrainedRequest = Options.newSome(getVStackConstrainedSiblingRequest(request, query, sizeDelta));
        } else if (stackDirection == PositionConstants.EAST_WEST) {
            constrainedRequest = Options.newSome(getHStackConstrainedSiblingRequest(request, query, sizeDelta));
        }
        return constrainedRequest;
    }

    private ChangeBoundsRequest getVStackConstrainedSiblingRequest(ChangeBoundsRequest request, RequestQuery query, Dimension sizeDelta) {
        ChangeBoundsRequest constrainedRequest = null;
        ChangeBoundsRequest req = initSiblingRequest(request);

        if (query.isResizeFromTop()) {
            EditPart regionContainer = getRegionContainerPart();
            if (isFirstRegionPart()) {
                if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                    // resize regioncontainer.
                    req.setEditParts(regionContainer);
                    req.setResizeDirection(request.getResizeDirection());
                    req.setSizeDelta(new Dimension(0, sizeDelta.height));
                    req.setMoveDelta(new Point(0, -sizeDelta.height));
                    constrainedRequest = req;
                }
            } else {
                Option<AbstractDiagramElementContainerEditPart> pred = getPrecedingRegion();
                if (pred.some() && !request.isConstrainedResize()) {
                    req.setEditParts(pred.get());
                    req.setResizeDirection(PositionConstants.SOUTH);
                    req.setSizeDelta(new Dimension(0, sizeDelta.getNegated().height));
                    constrainedRequest = req;
                }
            }
        } else if (query.isResizeFromBottom()) {
            EditPart regionContainer = getRegionContainerPart();
            if (isLastRegionPart()) {
                if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                    // resize regioncontainer.
                    req.setEditParts(regionContainer);
                    req.setResizeDirection(request.getResizeDirection());
                    req.setSizeDelta(new Dimension(0, sizeDelta.height));
                    constrainedRequest = req;
                    // Handle F3 for regions in region to allow manual layout.
                    // Allow manual resize of the region container to reduce
                    // empty space due to VSM specified size or recursive
                    // regions.
                    constrainedRequest = handleEmptySpaceInContainerOnLastRegionResize(request, constrainedRequest);
                }
            } else {
                Option<AbstractDiagramElementContainerEditPart> follo = getFollowingRegion();
                if (follo.some() && !request.isConstrainedResize()) {
                    Point moveDelta = new Point(sizeDelta.width, sizeDelta.height);
                    req.setEditParts(follo.get());
                    req.setResizeDirection(PositionConstants.NORTH);
                    req.setSizeDelta(new Dimension(0, sizeDelta.getNegated().height));
                    req.setMoveDelta(new Point(0, moveDelta.y));
                    constrainedRequest = req;
                }
            }
        } else if (query.isResizeFromRight()) {
            EditPart regionContainer = getRegionContainerPart();
            if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                // resize regioncontainer.
                req.setEditParts(regionContainer);
                req.setResizeDirection(request.getResizeDirection());
                req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                constrainedRequest = req;
            }
        } else if (query.isResizeFromLeft()) {
            EditPart regionContainer = getRegionContainerPart();
            if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                // resize regioncontainer.
                req.setEditParts(regionContainer);
                req.setResizeDirection(request.getResizeDirection());
                req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                req.setMoveDelta(new Point(-sizeDelta.width, 0));
                constrainedRequest = req;
            }

        }
        return constrainedRequest;
    }

    private ChangeBoundsRequest handleEmptySpaceInContainerOnLastRegionResize(ChangeBoundsRequest initialRequest, ChangeBoundsRequest constrainedRequest) {
        ChangeBoundsRequest result = constrainedRequest;
        Object object = initialRequest.getExtendedData().get(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY);
        boolean keepSameAbsoluteLocation = (object == null && SiriusResizeTracker.DEFAULT_CHILDREN_MOVE_MODE) || (object != null && ((Boolean) object).booleanValue());
        if (!initialRequest.isConstrainedResize() && !keepSameAbsoluteLocation) {
            result.setMoveDelta(new Point(0, 0));
            result.setSizeDelta(new Dimension(0, 0));

            IFigure hostFigure = getHostFigure();
            IFigure parentFigure = hostFigure.getParent();
            // Restrict the move to the parent bounds.
            if (!parentFigure.getBounds().contains(new RequestQuery(initialRequest).getLogicalTransformedRectangle(hostFigure.getBounds()).getBottomRight())) {
                result = null;
            }
        }
        return result;
    }

    private ChangeBoundsRequest getHStackConstrainedSiblingRequest(ChangeBoundsRequest request, RequestQuery query, Dimension sizeDelta) {
        ChangeBoundsRequest constrainedRequest = null;
        ChangeBoundsRequest req = initSiblingRequest(request);

        if (query.isResizeFromLeft()) {
            EditPart regionContainer = getRegionContainerPart();
            if (isFirstRegionPart()) {
                if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                    // resize regioncontainer.
                    req.setEditParts(regionContainer);
                    req.setResizeDirection(request.getResizeDirection());
                    req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                    req.setMoveDelta(new Point(-sizeDelta.width, 0));
                    constrainedRequest = req;
                }
            } else {
                Option<AbstractDiagramElementContainerEditPart> pred = getPrecedingRegion();
                if (pred.some() && !request.isConstrainedResize()) {
                    req.setEditParts(pred.get());
                    req.setResizeDirection(PositionConstants.EAST);
                    req.setSizeDelta(new Dimension(sizeDelta.getNegated().width, 0));
                    constrainedRequest = req;
                }
            }
        } else if (query.isResizeFromRight()) {
            if (isLastRegionPart()) {
                EditPart regionContainer = getRegionContainerPart();
                if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                    // resize regioncontainer.
                    req.setEditParts(regionContainer);
                    req.setResizeDirection(request.getResizeDirection());
                    req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                    constrainedRequest = req;
                    // Handle F3 for regions in region to allow manual layout.
                    // Allow manual resize of the region container to reduce
                    // empty space due to VSM specified size or recursive
                    // regions.
                    constrainedRequest = handleEmptySpaceInContainerOnLastRegionResize(request, constrainedRequest);
                }
            } else {
                Option<AbstractDiagramElementContainerEditPart> follo = getFollowingRegion();
                if (follo.some() && !request.isConstrainedResize()) {
                    req.setEditParts(follo.get());
                    req.setResizeDirection(PositionConstants.WEST);
                    req.setSizeDelta(new Dimension(-sizeDelta.width, 0));
                    req.setMoveDelta(new Point(sizeDelta.width, 0));
                    constrainedRequest = req;
                }
            }
        } else if (query.isResizeFromBottom()) {
            EditPart regionContainer = getRegionContainerPart();
            if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                // resize regioncontainer.
                req.setEditParts(regionContainer);
                req.setResizeDirection(request.getResizeDirection());
                req.setSizeDelta(new Dimension(0, sizeDelta.height));
                constrainedRequest = req;
            }
        } else if (query.isResizeFromTop()) {
            EditPart regionContainer = getRegionContainerPart();
            if (!request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer) {
                // resize regioncontainer.
                req.setEditParts(regionContainer);
                req.setSizeDelta(new Dimension(0, sizeDelta.height));
                req.setMoveDelta(new Point(0, -sizeDelta.height));
                constrainedRequest = req;
            }
        }
        return constrainedRequest;
    }

    private ChangeBoundsRequest initSiblingRequest(ChangeBoundsRequest request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest();
        req.setConstrainedResize(true);
        req.setConstrainedMove(true);
        req.setType(request.getType());
        req.getExtendedData().put(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR, getHost());
        req.getExtendedData().put(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY, request.getExtendedData().get(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY));
        req.getExtendedData().put(RegionContainerResizableEditPolicy.REGION_RESIZE_INITIAL_REQUEST, request);
        return req;
    }

    private EditPart getRegionContainerPart() {
        EditPart regionContainer = getHost().getParent();
        if (regionContainer != null) {
            regionContainer = regionContainer.getParent();
        }
        return regionContainer;
    }

    private Option<AbstractDiagramElementContainerEditPart> getPrecedingRegion() {
        List<AbstractDiagramElementContainerEditPart> siblingRegions = getSiblingRegionPart();
        int precedingIndex = siblingRegions.indexOf(getHost()) - 1;
        if (precedingIndex >= 0 && siblingRegions.size() >= precedingIndex) {
            return Options.newSome(siblingRegions.get(precedingIndex));
        }
        return Options.newNone();
    }

    private Option<AbstractDiagramElementContainerEditPart> getFollowingRegion() {
        List<AbstractDiagramElementContainerEditPart> siblingRegions = getSiblingRegionPart();
        int followingIndex = siblingRegions.indexOf(getHost()) + 1;
        if (followingIndex > 0 && siblingRegions.size() > followingIndex) {
            return Options.newSome(siblingRegions.get(followingIndex));
        }
        return Options.newNone();
    }

    private boolean concernRegion() {
        boolean regionImpacted = false;
        EditPart hostPart = getHost();
        if (hostPart instanceof AbstractDiagramElementContainerEditPart) {
            IDiagramElementEditPart ideep = (IDiagramElementEditPart) hostPart;
            DDiagramElement dde = ideep.resolveDiagramElement();
            regionImpacted = dde instanceof DDiagramElementContainer && new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) dde).isRegion();
        }
        return regionImpacted;
    }

    private int getStackDirection() {
        int direction = PositionConstants.NONE;
        EditPart hostPart = getHost();
        if (hostPart instanceof AbstractDiagramElementContainerEditPart) {
            IDiagramElementEditPart ideep = (IDiagramElementEditPart) hostPart;
            DDiagramElement dde = ideep.resolveDiagramElement();
            if (dde != null && dde.eContainer() instanceof DNodeContainer) {
                DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery((DNodeContainer) dde.eContainer());
                if (query.isVerticalStackContainer()) {
                    direction = PositionConstants.NORTH_SOUTH;
                } else if (query.isHorizontaltackContainer()) {
                    direction = PositionConstants.EAST_WEST;
                }
            }
        }
        return direction;
    }

    private List<AbstractDiagramElementContainerEditPart> getSiblingRegionPart() {
        EditPart parent = getHost() != null ? getHost().getParent() : null;
        if (parent instanceof AbstractDNodeContainerCompartmentEditPart) {
            return Lists.newArrayList(Iterables.filter(parent.getChildren(), AbstractDiagramElementContainerEditPart.class));
        }
        return Collections.emptyList();
    }

    private boolean isFirstRegionPart() {
        Iterable<AbstractDiagramElementContainerEditPart> regionParts = getSiblingRegionPart();
        return !Iterables.isEmpty(regionParts) && Iterables.getFirst(regionParts, null) == getHost();
    }

    private boolean isLastRegionPart() {
        Iterable<AbstractDiagramElementContainerEditPart> regionParts = getSiblingRegionPart();
        return !Iterables.isEmpty(regionParts) && Iterables.getLast(regionParts, null) == getHost();

    }
}
