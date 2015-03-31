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
import org.eclipse.gef.commands.CompoundCommand;
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        if (concernRegion()) {
            return UnexecutableCommand.INSTANCE;
        }

        return super.getMoveCommand(request);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        Command resizeCommand = super.getResizeCommand(request);
        if (concernRegion()) {
            resizeCommand = getRegionResizeCommand(request, resizeCommand);
        }
        return resizeCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAlignCommand(AlignmentRequest request) {
        if (concernRegion()) {
            return UnexecutableCommand.INSTANCE;
        }
        return super.getAlignCommand(request);
    }

    /**
     * {@inheritDoc}
     */
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

                CompoundCommand cc = new CompoundCommand();
                cc.add(autoSizeCommand);
                cc.add(regionContainerPart.getCommand(req));
                autoSizeCommand = cc;
            }
        }
        return autoSizeCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void showChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.showChangeBoundsFeedback(request);

        if (concernRegion()) {
            Option<ChangeBoundsRequest> siblingRequest = getConstrainedSiblingRequest(request);
            if (siblingRequest.some() && siblingRequest.get().getEditParts() != null && siblingRequest.get().getEditParts().size() == 1) {
                EditPart siblingPart = (EditPart) siblingRequest.get().getEditParts().get(0);
                siblingPart.showSourceFeedback(siblingRequest.get());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        super.eraseChangeBoundsFeedback(request);

        if (concernRegion()) {
            Option<ChangeBoundsRequest> siblingRequest = getConstrainedSiblingRequest(request);
            if (siblingRequest.some() && siblingRequest.get().getEditParts() != null && siblingRequest.get().getEditParts().size() == 1) {
                EditPart siblingPart = (EditPart) siblingRequest.get().getEditParts().get(0);
                siblingPart.eraseSourceFeedback(siblingRequest.get());
            }
        }
    }

    private Command getRegionResizeCommand(ChangeBoundsRequest request, Command command) {
        boolean invalidRequest = request.getEditParts().size() > 1 && !request.isConstrainedResize() || request.isCenteredResize();
        if (invalidRequest || !validateResize(request)) {
            return UnexecutableCommand.INSTANCE;
        }

        Command regionResizeCommand = command;
        Option<ChangeBoundsRequest> siblingRequest = getConstrainedSiblingRequest(request);
        if (siblingRequest.some() && siblingRequest.get().getEditParts() != null && siblingRequest.get().getEditParts().size() == 1) {
            EditPart siblingPart = (EditPart) siblingRequest.get().getEditParts().get(0);
            regionResizeCommand = composeCommands(command, siblingPart.getCommand(siblingRequest.get()));
        } else if (!(request.isConstrainedMove() || request.isConstrainedResize())) {
            regionResizeCommand = UnexecutableCommand.INSTANCE;
        }

        return regionResizeCommand;
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
        ChangeBoundsRequest constrainedRequest = null;

        RequestQuery query = new RequestQuery(request);
        Dimension sizeDelta = request.getSizeDelta().getCopy();

        ChangeBoundsRequest req = new ChangeBoundsRequest();
        req.setConstrainedResize(true);
        req.setConstrainedMove(true);
        req.setType(request.getType());

        int stackDirection = getStackDirection();
        if (stackDirection == PositionConstants.NORTH_SOUTH) {
            if (query.isResizeFromTop()) {
                if (!isFirstRegionPart() && !request.isConstrainedResize()) {
                    Option<AbstractDiagramElementContainerEditPart> pred = getPrecedingRegion();
                    if (pred.some()) {
                        req.setEditParts(pred.get());
                        req.setResizeDirection(PositionConstants.SOUTH);
                        req.setSizeDelta(new Dimension(0, sizeDelta.getNegated().height));
                        constrainedRequest = req;
                    }
                }
            } else if (query.isResizeFromBottom()) {
                if (isLastRegionPart() && !request.isConstrainedResize()) {
                    // resize regioncontainer.
                    EditPart regionContainer = getRegionContainerPart();
                    req.setEditParts(regionContainer);
                    req.setResizeDirection(request.getResizeDirection());
                    req.setSizeDelta(new Dimension(0, sizeDelta.height));
                    constrainedRequest = req;
                } else if (!request.isConstrainedResize()) {
                    Option<AbstractDiagramElementContainerEditPart> follo = getFollowingRegion();
                    if (follo != null) {
                        Point moveDelta = new Point(sizeDelta.width, sizeDelta.height);

                        req.setEditParts(follo.get());
                        req.setResizeDirection(PositionConstants.NORTH);
                        req.setSizeDelta(new Dimension(0, sizeDelta.getNegated().height));
                        req.setMoveDelta(new Point(0, moveDelta.y));
                        constrainedRequest = req;
                    }
                }
            }
        } else if (stackDirection == PositionConstants.EAST_WEST) {
            if (query.isResizeFromLeft()) {
                if (!isFirstRegionPart() && !request.isConstrainedResize()) {
                    Option<AbstractDiagramElementContainerEditPart> pred = getPrecedingRegion();
                    if (pred.some()) {
                        req.setEditParts(pred.get());
                        req.setResizeDirection(PositionConstants.EAST);
                        req.setSizeDelta(new Dimension(sizeDelta.getNegated().width, 0));
                        constrainedRequest = req;
                    }
                }
            } else if (query.isResizeFromRight()) {
                if (isLastRegionPart() && !request.isConstrainedResize()) {
                    // resize regioncontainer.
                    EditPart regionContainer = getRegionContainerPart();
                    req.setEditParts(regionContainer);
                    req.setResizeDirection(request.getResizeDirection());
                    req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                    constrainedRequest = req;
                } else if (!request.isConstrainedResize()) {
                    Option<AbstractDiagramElementContainerEditPart> follo = getFollowingRegion();
                    if (follo != null) {
                        Point moveDelta = new Point(sizeDelta.width, sizeDelta.height);

                        req.setEditParts(follo.get());
                        req.setResizeDirection(PositionConstants.LEFT);
                        req.setSizeDelta(new Dimension(sizeDelta.getNegated().width, 0));
                        req.setMoveDelta(new Point(moveDelta.x, 0));
                        constrainedRequest = req;
                    }
                }
            }
        }
        return Options.newSome(constrainedRequest);
    }

    private EditPart getRegionContainerPart() {
        EditPart regionContainer = getHost().getParent();
        if (regionContainer != null) {
            regionContainer = regionContainer.getParent();
        }
        return regionContainer;
    }

    private Command composeCommands(Command initialcommand, Command constrainedCommand) {
        Command regionResizeCommand = UnexecutableCommand.INSTANCE;
        if (getHost() instanceof IDiagramElementEditPart && constrainedCommand != null) {
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(((IDiagramElementEditPart) getHost()).getEditingDomain(), "Region Resize Composite Command");
            ctc.add(new CommandProxy(initialcommand));
            ctc.add(new CommandProxy(constrainedCommand));
            regionResizeCommand = new ICommandProxy(ctc);
        }
        return regionResizeCommand;
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
