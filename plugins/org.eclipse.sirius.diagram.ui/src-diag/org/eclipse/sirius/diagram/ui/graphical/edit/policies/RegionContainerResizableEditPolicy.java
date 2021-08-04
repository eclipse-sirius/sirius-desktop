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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.model.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.ChildrenAdjustmentCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A {@link AirResizableEditPolicy} able to handle region containers.
 * 
 * @author mporhel
 */
public class RegionContainerResizableEditPolicy extends AirResizableEditPolicy {

    /**
     * Key to store the part responsible for auto-size propagation.
     */
    protected static final String REGION_AUTO_SIZE_PROPAGATOR = "sirius.region.auto.size.propagator"; //$NON-NLS-1$

    /**
     * Key to store the part responsible for resize propagation.
     */
    protected static final String REGION_RESIZE_PROPAGATOR = "sirius.region.resize.propagator"; //$NON-NLS-1$

    /**
     * Key to store the initial request for resize propagation.
     */
    protected static final String REGION_RESIZE_INITIAL_REQUEST = "sirius.region.resize.initial.request"; //$NON-NLS-1$

    @Override
    protected Command getAutoSizeCommand(Request request) {
        Command autoSizeCommand = super.getAutoSizeCommand(request);
        if (concernRegionContainer()) {
            autoSizeCommand = getRegionContainerAutoSizeCommand(request, autoSizeCommand);
        }
        return autoSizeCommand;
    }

    /**
     * Returns a composite command with the given initial command and the RegionContainer specific auto-size commands to
     * propagate the auto-size to the regions.
     * 
     * @param request
     *            the initial request
     * @param autoSizeCommand
     *            the initial command
     * @return a composite command with the initial command and the region container specific additional commands.
     */
    protected Command getRegionContainerAutoSizeCommand(Request request, Command autoSizeCommand) {
        IDiagramElementEditPart host = (IDiagramElementEditPart) getHost();
        TransactionalEditingDomain domain = host.getEditingDomain();
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(domain, Messages.RegionContainerResizableEditPolicy_regionContainerAutoSizeCommandLabel);
        ctc.add(new CommandProxy(autoSizeCommand));
        Command regionContainerAutoSizeCommand = new ICommandProxy(ctc);

        // Propagate the auto-size request to the regions.
        Request req = new Request();
        req.setType(request.getType());
        req.getExtendedData().put(REGION_AUTO_SIZE_PROPAGATOR, host);
        for (EditPart regionPart : getRegionParts()) {
            if (host != regionPart) {
                ctc.add(new CommandProxy(regionPart.getCommand(req)));
            }
        }

        ctc.add(CommandFactory.createICommand(domain, new RegionContainerUpdateLayoutOperation((Node) host.getModel())));
        return regionContainerAutoSizeCommand;
    }

    /**
     * Complete the given composite command with RegionContainer specific resize commands: the commands to report the
     * RegionContainer resize on its regions.
     */
    @Override
    protected void completeResizeCommand(CompositeTransactionalCommand ctc, ChangeBoundsRequest request) {
        if (request.getEditParts().size() > 1 && !request.isConstrainedResize()) {
            ctc.add(UnexecutableCommand.INSTANCE);
            return;
        }

        Collection<ChangeBoundsRequest> siblingRequests = getConstrainedRegionRequests(request);
        if (!siblingRequests.isEmpty()) {
            for (ChangeBoundsRequest siblingRequest : siblingRequests) {
                if (siblingRequest.getEditParts() != null) {
                    for (IGraphicalEditPart constrainedPart : Iterables.filter(siblingRequest.getEditParts(), IGraphicalEditPart.class)) {
                        Command constrainedCommand = constrainedPart.getCommand(siblingRequest);
                        if (constrainedCommand == null) {
                            ctc.add(UnexecutableCommand.INSTANCE);
                        } else {
                            ctc.add(new CommandProxy(constrainedCommand));
                        }
                    }
                }
            }
        } else if (!(request.isConstrainedMove() || request.isConstrainedResize())) {
            // Deactivate the manual resize of RegionContainer when there are no
            // regions.
            ctc.add(UnexecutableCommand.INSTANCE);
        }

        // Adjust border nodes and edges.
        ctc.add(new ChildrenAdjustmentCommand((IGraphicalEditPart) getHost(), request, true, false));
    }

    private Collection<ChangeBoundsRequest> getConstrainedRegionRequests(ChangeBoundsRequest request) {
        Collection<ChangeBoundsRequest> constrainedRequests = new ArrayList<>();

        RequestQuery query = new RequestQuery(request);
        Dimension sizeDelta = request.getSizeDelta().getCopy();
        Point moveDelta = request.getMoveDelta().getCopy();

        List<AbstractDiagramElementContainerEditPart> regionToResize = getRegionParts();
        int stackDirection = getStackDirection();
        // Handle first and last regions.
        Object resizePropagator = request.getExtendedData().get(REGION_RESIZE_PROPAGATOR);
        if (query.isResizeFromTop() && stackDirection == PositionConstants.NORTH_SOUTH || query.isResizeFromLeft() && stackDirection == PositionConstants.EAST_WEST) {
            Option<AbstractDiagramElementContainerEditPart> firstRegionPart = getFirstRegionPart();
            if (firstRegionPart.some() && (!request.isConstrainedResize() || resizePropagator != firstRegionPart.get())) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(firstRegionPart.get());
                req.setSizeDelta(sizeDelta.getCopy());
                constrainedRequests.add(req);
            }
            // shift all other regions.
            regionToResize.remove(firstRegionPart.get());
            if (!regionToResize.isEmpty() && (!request.isConstrainedResize() || !regionToResize.contains(resizePropagator))) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(new ArrayList<AbstractDiagramElementContainerEditPart>(regionToResize));

                if (stackDirection == PositionConstants.NORTH_SOUTH) {
                    req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                    req.setMoveDelta(new Point(0, -moveDelta.y));
                } else if (stackDirection == PositionConstants.EAST_WEST) {
                    req.setSizeDelta(new Dimension(0, sizeDelta.height));
                    req.setMoveDelta(new Point(-moveDelta.x, 0));
                }
                constrainedRequests.add(req);
                regionToResize.clear();
            }
        } else if (query.isResizeFromBottom() && stackDirection == PositionConstants.NORTH_SOUTH || query.isResizeFromRight() && stackDirection == PositionConstants.EAST_WEST) {
            // Resize the last region.
            Option<AbstractDiagramElementContainerEditPart> lastRegionPart = getLastRegionPart();
            if (lastRegionPart.some() && (!request.isConstrainedResize() || resizePropagator != lastRegionPart.get())) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(lastRegionPart.get());
                req.setSizeDelta(sizeDelta.getCopy());
                constrainedRequests.add(req);
                regionToResize.remove(lastRegionPart.get());
            }
        }

        // Handle horizontal resize for vstacks and vertical resize for hstacks.
        if (request.isConstrainedResize() && resizePropagator != null) {
            regionToResize.remove(resizePropagator);
        }
        if (!regionToResize.isEmpty()) {
            if (stackDirection == PositionConstants.NORTH_SOUTH && (query.isResizeFromLeft() || query.isResizeFromRight()) && sizeDelta.width != 0) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(new ArrayList<AbstractDiagramElementContainerEditPart>(regionToResize));
                req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                constrainedRequests.add(req);
            } else if (stackDirection == PositionConstants.EAST_WEST && (query.isResizeFromTop() || query.isResizeFromBottom()) && sizeDelta.height != 0) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(new ArrayList<AbstractDiagramElementContainerEditPart>(regionToResize));
                req.setSizeDelta(new Dimension(0, sizeDelta.height));
                constrainedRequests.add(req);
            }
        }

        return constrainedRequests;
    }

    private ChangeBoundsRequest initConstrainedRequest(ChangeBoundsRequest request) {
        ChangeBoundsRequest req = new ChangeBoundsRequest();
        req.setConstrainedResize(true);
        req.setConstrainedMove(true);
        req.setType(request.getType());
        req.setResizeDirection(request.getResizeDirection());
        req.getExtendedData().put(REGION_RESIZE_PROPAGATOR, getHost());
        req.getExtendedData().put(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY, request.getExtendedData().get(SiriusResizeTracker.CHILDREN_MOVE_MODE_KEY));
        req.getExtendedData().put(REGION_RESIZE_INITIAL_REQUEST, request);
        return req;
    }

    private boolean concernRegionContainer() {
        boolean regionImpacted = false;
        EditPart hostPart = getHost();
        if (hostPart instanceof AbstractDiagramElementContainerEditPart) {
            IDiagramElementEditPart ideep = (IDiagramElementEditPart) hostPart;
            DDiagramElement dde = ideep.resolveDiagramElement();
            regionImpacted = dde instanceof DNodeContainer && new DNodeContainerExperimentalQuery((DNodeContainer) dde).isRegionContainer();
        }
        return regionImpacted;
    }

    private int getStackDirection() {
        int direction = PositionConstants.NONE;
        EditPart hostPart = getHost();
        if (hostPart instanceof AbstractDiagramElementContainerEditPart) {
            IDiagramElementEditPart ideep = (IDiagramElementEditPart) hostPart;
            DDiagramElement dde = ideep.resolveDiagramElement();
            if (dde instanceof DNodeContainer) {
                DNodeContainerExperimentalQuery query = new DNodeContainerExperimentalQuery((DNodeContainer) dde);
                if (query.isVerticalStackContainer()) {
                    direction = PositionConstants.NORTH_SOUTH;
                } else if (query.isHorizontaltackContainer()) {
                    direction = PositionConstants.EAST_WEST;
                }
            }
        }
        return direction;
    }

    private List<AbstractDiagramElementContainerEditPart> getRegionParts() {
        AbstractDNodeContainerCompartmentEditPart comp = Iterables.getFirst(Iterables.filter(getHost().getChildren(), AbstractDNodeContainerCompartmentEditPart.class), null);
        if (comp != null) {
            return Lists.newArrayList(Iterables.filter(comp.getChildren(), AbstractDiagramElementContainerEditPart.class));
        }
        return Collections.emptyList();
    }

    private Option<AbstractDiagramElementContainerEditPart> getFirstRegionPart() {
        Iterable<AbstractDiagramElementContainerEditPart> regionParts = getRegionParts();
        return Options.newSome(Iterables.getFirst(regionParts, null));
    }

    private Option<AbstractDiagramElementContainerEditPart> getLastRegionPart() {
        Iterable<AbstractDiagramElementContainerEditPart> regionParts = getRegionParts();
        return Options.newSome(Iterables.getLast(regionParts, null));
    }

}
