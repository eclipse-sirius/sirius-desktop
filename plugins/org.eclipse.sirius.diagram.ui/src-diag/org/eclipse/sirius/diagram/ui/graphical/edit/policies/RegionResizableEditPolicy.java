/*******************************************************************************
 * Copyright (c) 2013, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.handles.CompartmentCollapseHandle;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.CompartmentCollapseTracker;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.internal.query.model.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.PinnedElementsLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
            setResizeDirections(getParentStackDirection());
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
    protected List createSelectionHandles() {
        List createSelectionHandles = super.createSelectionHandles();

        addCollapseHandle(createSelectionHandles);
        return createSelectionHandles;
    }

    /**
     * Add the collapse handle of the region.
     * 
     * @param selectionHandles
     *            selection handles
     */
    protected void addCollapseHandle(List selectionHandles) {
        EditPart regionContainerPart = getRegionContainerPart();

        // DisableCollapse for Regions whose the RegionContainer is itself a
        // Region.
        if (concernRegion() && regionContainerPart instanceof AbstractDiagramElementContainerEditPart && !((AbstractDiagramElementContainerEditPart) regionContainerPart).isRegion()) {
            AbstractDiagramElementContainerEditPart hostPart = (AbstractDiagramElementContainerEditPart) getHost();
            LabelAlignment textAlignment = getTextAlignment(hostPart);
            Iterable<IResizableCompartmentEditPart> compartmentParts = Iterables.filter(hostPart.getChildren(), IResizableCompartmentEditPart.class);
            if (new EditPartQuery(hostPart).getDrawerStyle() != null && !Iterables.isEmpty(compartmentParts)) {
                selectionHandles.add(new RegionCollapseHandle(compartmentParts.iterator().next(), textAlignment, hostPart));
            }
        }
    }

    private LabelAlignment getTextAlignment(AbstractDiagramElementContainerEditPart hostPart) {
        LabelAlignment alignment = (LabelAlignment) ViewpointPackage.eINSTANCE.getLabelStyle_LabelAlignment().getDefaultValue();

        DDiagramElement dde = hostPart.resolveDiagramElement();
        Style style = dde.getStyle();
        boolean hiddenLabel = new DDiagramElementQuery(dde).isLabelHidden();
        if (style instanceof LabelStyle && !hiddenLabel) {
            alignment = ((LabelStyle) style).getLabelAlignment();
        }
        if (hiddenLabel && dde instanceof DNodeList) {
            List<DNodeListElement> ownedElements = ((DNodeList) dde).getOwnedElements();
            if (!ownedElements.isEmpty() && ownedElements.get(0).getStyle() instanceof LabelStyle) {
                alignment = ((LabelStyle) ownedElements.get(0).getStyle()).getLabelAlignment();
            }
        }
        return alignment;
    }

    @Override
    public Command getCommand(Request request) {
        Object type = request.getType();
        if (REQ_MOVE.equals(type) && (isDragAllowed() || isAuthorizedMoveRequest(request))) {
            return getMoveCommand((ChangeBoundsRequest) request);
        } else {
            return super.getCommand(request);
        }
    }

    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        if (concernRegion() && !isAuthorizedMoveRequest(request)) {
            return UnexecutableCommand.INSTANCE;
        }

        return super.getMoveCommand(request);
    }

    private boolean isAuthorizedMoveRequest(Request request) {
        Object isAuthorizedMoveRequest = request.getExtendedData().get(PinnedElementsLayoutProvider.PINNED_ELEMENTS_MOVE);
        if (isAuthorizedMoveRequest instanceof Boolean) {
            return (Boolean) isAuthorizedMoveRequest;
        }
        return false;
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
                    CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(((IDiagramElementEditPart) getHost()).getEditingDomain(),
                            Messages.RegionResizableEditPolicy_regionAutoSizeCommandLabel);
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
        EditPart host = getHost();
        RequestQuery query = new RequestQuery(request);

        if (host instanceof IGraphicalEditPart && new EditPartQuery((IGraphicalEditPart) host).isCollapsed()) {
            // Disable resize in the collapsed direction.
            boolean forbiddenCollapseResize = false;
            if (getParentStackDirection() == PositionConstants.NORTH_SOUTH) {
                forbiddenCollapseResize = (query.isResizeFromTop() || query.isResizeFromBottom()) && request.getSizeDelta().height() != 0;
            } else if (getParentStackDirection() == PositionConstants.EAST_WEST) {
                forbiddenCollapseResize = (query.isResizeFromLeft() || query.isResizeFromRight()) && request.getSizeDelta().width() != 0;
            }
            if (forbiddenCollapseResize) {
                return UnexecutableCommand.INSTANCE;
            }
        }

        if (isFirstRegionPart()) {
            request.getMoveDelta().setX(0);
            request.getMoveDelta().setY(0);
        } else if (getParentStackDirection() == PositionConstants.EAST_WEST) {
            request.getMoveDelta().setY(0);
        }
        return super.getResizeCommand(request);
    }

    /**
     * Complete the given composite command with Region specific resize commands: the commands to report the resize on
     * the RegionContainer or on the sibling regions.
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
     * Return true to add the default {@link AirResizableEditPolicy} behavior in completeResizeCommand: this will add
     * the sub commands to adjust children position: see
     * {@link org.eclipse.sirius.diagram.ui.internal.edit.commands.ChildrenAdjustmentCommand} .
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
            int stackDirection = getParentStackDirection();
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

        EditPart regionContainer = getRegionContainerPart();
        boolean allowedRegionContainerPropagation = !request.isConstrainedResize() || request.getExtendedData().get(RegionContainerResizableEditPolicy.REGION_RESIZE_PROPAGATOR) != regionContainer;

        int stackDirection = getParentStackDirection();
        if (stackDirection == PositionConstants.NORTH_SOUTH) {
            constrainedRequest = Options.newSome(getVStackConstrainedSiblingRequest(request, query, sizeDelta, allowedRegionContainerPropagation, regionContainer));
        } else if (stackDirection == PositionConstants.EAST_WEST) {
            constrainedRequest = Options.newSome(getHStackConstrainedSiblingRequest(request, query, sizeDelta, allowedRegionContainerPropagation, regionContainer));
        }
        return constrainedRequest;
    }

    private ChangeBoundsRequest getVStackConstrainedSiblingRequest(ChangeBoundsRequest request, RequestQuery query, Dimension sizeDelta, boolean allowedRegionContainerPropagation,
            EditPart regionContainer) {
        ChangeBoundsRequest constrainedRequest = null;
        ChangeBoundsRequest req = initSiblingRequest(request);

        if (query.isResizeFromTop()) {
            if (!isFirstRegionPart()) {
                Option<AbstractDiagramElementContainerEditPart> pred = getPrecedingRegion();
                if (pred.some() && !request.isConstrainedResize()) {
                    req.setEditParts(pred.get());
                    req.setResizeDirection(PositionConstants.SOUTH);
                    req.setSizeDelta(new Dimension(0, sizeDelta.getNegated().height));
                    constrainedRequest = req;
                }
            } else if (allowedRegionContainerPropagation) {
                // resize regioncontainer from first region part resize.
                req.setEditParts(regionContainer);
                req.setResizeDirection(request.getResizeDirection());
                req.setSizeDelta(new Dimension(0, sizeDelta.height));
                req.setMoveDelta(new Point(0, -sizeDelta.height));
                constrainedRequest = req;
            }
        } else if (query.isResizeFromBottom()) {
            if (!isLastRegionPart()) {
                Option<AbstractDiagramElementContainerEditPart> follo = getFollowingRegion();
                if (follo.some() && !request.isConstrainedResize()) {
                    Point moveDelta = new Point(sizeDelta.width, sizeDelta.height);
                    req.setEditParts(follo.get());
                    req.setResizeDirection(PositionConstants.NORTH);
                    req.setSizeDelta(new Dimension(0, sizeDelta.getNegated().height));
                    req.setMoveDelta(new Point(0, moveDelta.y));
                    constrainedRequest = req;
                }
            } else if (allowedRegionContainerPropagation) {
                // resize regioncontainer from last region part resize.
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
        } else if (query.isResizeFromRight() && allowedRegionContainerPropagation) {
            // resize regioncontainer.
            req.setEditParts(regionContainer);
            req.setResizeDirection(request.getResizeDirection());
            req.setSizeDelta(new Dimension(sizeDelta.width, 0));
            constrainedRequest = req;
        } else if (query.isResizeFromLeft() && allowedRegionContainerPropagation) {
            // resize regioncontainer.
            req.setEditParts(regionContainer);
            req.setResizeDirection(request.getResizeDirection());
            req.setSizeDelta(new Dimension(sizeDelta.width, 0));
            req.setMoveDelta(new Point(-sizeDelta.width, 0));
            constrainedRequest = req;
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

    private ChangeBoundsRequest getHStackConstrainedSiblingRequest(ChangeBoundsRequest request, RequestQuery query, Dimension sizeDelta, boolean regionContainerPropagationAllowed,
            EditPart regionContainer) {
        ChangeBoundsRequest constrainedRequest = null;
        ChangeBoundsRequest req = initSiblingRequest(request);

        if (query.isResizeFromLeft()) {
            if (!isFirstRegionPart()) {
                Option<AbstractDiagramElementContainerEditPart> pred = getPrecedingRegion();
                if (pred.some() && !request.isConstrainedResize()) {
                    req.setEditParts(pred.get());
                    req.setResizeDirection(PositionConstants.EAST);
                    req.setSizeDelta(new Dimension(sizeDelta.getNegated().width, 0));
                    constrainedRequest = req;
                }
            } else if (regionContainerPropagationAllowed) {
                // resize regioncontainer from first region part resize.
                req.setEditParts(regionContainer);
                req.setResizeDirection(request.getResizeDirection());
                req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                req.setMoveDelta(new Point(-sizeDelta.width, 0));
                constrainedRequest = req;
            }
        } else if (query.isResizeFromRight()) {
            if (!isLastRegionPart()) {
                Option<AbstractDiagramElementContainerEditPart> follo = getFollowingRegion();
                if (follo.some() && !request.isConstrainedResize()) {
                    req.setEditParts(follo.get());
                    req.setResizeDirection(PositionConstants.WEST);
                    req.setSizeDelta(new Dimension(-sizeDelta.width, 0));
                    req.setMoveDelta(new Point(sizeDelta.width, 0));
                    constrainedRequest = req;
                }
            } else if (regionContainerPropagationAllowed) {
                // resize regioncontainer from last region part resize.
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
        } else if (query.isResizeFromBottom() && regionContainerPropagationAllowed) {
            // resize regioncontainer.
            req.setEditParts(regionContainer);
            req.setResizeDirection(request.getResizeDirection());
            req.setSizeDelta(new Dimension(0, sizeDelta.height));
            constrainedRequest = req;
        } else if (query.isResizeFromTop() && regionContainerPropagationAllowed) {
            // resize regioncontainer from first region part resize.
            req.setEditParts(regionContainer);
            req.setResizeDirection(request.getResizeDirection());
            req.setSizeDelta(new Dimension(0, sizeDelta.height));
            req.setMoveDelta(new Point(0, -sizeDelta.height));
            constrainedRequest = req;
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
        List<AbstractDiagramElementContainerEditPart> siblingRegions = getSiblingRegionParts();
        int precedingIndex = siblingRegions.indexOf(getHost()) - 1;
        if (precedingIndex >= 0 && siblingRegions.size() >= precedingIndex) {
            return Options.newSome(siblingRegions.get(precedingIndex));
        }
        return Options.newNone();
    }

    private Option<AbstractDiagramElementContainerEditPart> getFollowingRegion() {
        List<AbstractDiagramElementContainerEditPart> siblingRegions = getSiblingRegionParts();
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

    private int getParentStackDirection() {
        int direction = PositionConstants.NONE;
        EditPart hostPart = getHost();
        if (hostPart instanceof AbstractDiagramElementContainerEditPart) {
            direction = ((AbstractDiagramElementContainerEditPart) hostPart).getParentStackDirection();
        }
        return direction;
    }

    private List<AbstractDiagramElementContainerEditPart> getSiblingRegionParts() {
        EditPart parent = getHost() != null ? getHost().getParent() : null;
        if (parent instanceof AbstractDNodeContainerCompartmentEditPart) {
            return Lists.newArrayList(Iterables.filter(parent.getChildren(), AbstractDiagramElementContainerEditPart.class));
        }
        return Collections.emptyList();
    }

    private boolean isFirstRegionPart() {
        Iterable<AbstractDiagramElementContainerEditPart> regionParts = getSiblingRegionParts();
        return !Iterables.isEmpty(regionParts) && Iterables.getFirst(regionParts, null) == getHost();
    }

    private boolean isLastRegionPart() {
        Iterable<AbstractDiagramElementContainerEditPart> regionParts = getSiblingRegionParts();
        return !Iterables.isEmpty(regionParts) && Iterables.getLast(regionParts, null) == getHost();

    }

    /**
     * Specific {@link CompartmentCollapseHandle} for Regions: it locates the handle on the Region label area and not in
     * its content pane, it takes the label alignment of the Region into account.
     */
    private static class RegionCollapseHandle extends CompartmentCollapseHandle {

        private AbstractDiagramElementContainerEditPart regionPart;

        /**
         * Constructor.
         * 
         * @param owner
         *            the compartment part to collapse (the part whose GMF node has the drawer style)
         * @param alignment
         *            the label alignment to take into account to correctly locate the handle.
         * @param regionPart
         *            the region part to notify when drawer syle is expanded or collapsed.
         */
        RegionCollapseHandle(IGraphicalEditPart owner, LabelAlignment alignment, AbstractDiagramElementContainerEditPart regionPart) {
            super(owner);
            this.regionPart = regionPart;
            setLocator(new RegionCollapseHandleLocator(alignment));
        }

        @Override
        public void notifyChanged(Notification notification) {
            super.notifyChanged(notification);

            // Redirect the collapse notification to the Region part.
            if (NotationPackage.eINSTANCE.getDrawerStyle_Collapsed() == notification.getFeature()) {
                regionPart.notifyChanged(notification);
            }
        }

        /*
         * Overriden to force the refresh of the current diagram, even if we are in manual refresh.
         */
        @SuppressWarnings("restriction")
        @Override
        protected DragTracker createDragTracker() {
            return new CompartmentCollapseTracker((IResizableCompartmentEditPart) getOwner()) {
                @Override
                protected Command getCommand(Boolean expand) {
                    Command command = super.getCommand(expand);
                    CompoundCommand commandPlusForceRefresh = new CompoundCommand(command.getLabel());
                    // If we are in manual refresh, add a new command to force the refresh of the current diagram.
                    Optional<DDiagram> optionalDDiagram = new org.eclipse.sirius.diagram.ui.business.api.query.EditPartQuery(regionPart).getDDiagram();
                    if (optionalDDiagram.isPresent()) {
                        if (!new DRepresentationQuery(optionalDDiagram.get()).isAutoRefresh()) {
                            commandPlusForceRefresh.add(new Command() {
                                @Override
                                public void execute() {
                                    Session session = SessionManager.INSTANCE.getSession(optionalDDiagram.get());
                                    if (session != null) {
                                        // Set the RefreshEditorsListener in forceRefresh mode
                                        session.getRefreshEditorsListener().setForceRefresh(true);
                                    }
                                }

                                @Override
                                public void undo() {
                                    execute();
                                }
                            });
                        }
                    }
                    commandPlusForceRefresh.add(command);
                    return commandPlusForceRefresh;
                }
            };
        }

        private class RegionCollapseHandleLocator implements Locator {
            private boolean isRegionTextLeftAligned;

            RegionCollapseHandleLocator(LabelAlignment alignment) {
                this.isRegionTextLeftAligned = LabelAlignment.LEFT.equals(alignment);
            }

            @Override
            public void relocate(IFigure target) {
                IFigure handleOwner = getOwnerFigure();
                if (handleOwner.getParent() != null) {
                    handleOwner = handleOwner.getParent();
                }

                Rectangle theBounds = handleOwner.getClientArea().getCopy();
                handleOwner.translateToAbsolute(theBounds);
                target.translateToRelative(theBounds);

                // Display the handle at the same y location
                // regarding the figure topology (list/container,
                // label style choices)
                int leftOffset = 5;
                int topOffset = 0;
                if (handleOwner instanceof DefaultSizeNodeFigure) {
                    topOffset = IContainerLabelOffsets.LABEL_OFFSET;
                } else if (handleOwner.getBorder() != null && handleOwner.getBorder().getInsets(null) != null) {
                    topOffset = IContainerLabelOffsets.LABEL_OFFSET - handleOwner.getBorder().getInsets(null).top;
                }
                if (isRegionTextLeftAligned) {
                    target.setLocation(theBounds.getTopRight().getTranslated(-getBounds().width(), topOffset));
                } else {
                    target.setLocation(theBounds.getLocation().getTranslated(leftOffset, topOffset));
                }
            }
        }
    }
}
