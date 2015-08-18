/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

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
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
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
    protected static final String REGION_AUTO_SIZE_PROPAGATOR = "region_auto-size_propagator"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getAutoSizeCommand(Request request) {
        Command autoSizeCommand = super.getAutoSizeCommand(request);
        if (concernRegionContainer()) {
            IDiagramElementEditPart host = (IDiagramElementEditPart) getHost();
            TransactionalEditingDomain domain = host.getEditingDomain();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(domain, "Region Container Auto Size Command");
            ctc.add(new CommandProxy(autoSizeCommand));
            autoSizeCommand = new ICommandProxy(ctc);

            // Propagate the auto-size request to the regions.
            Request req = new Request();
            req.setType(request.getType());
            req.getExtendedData().put(REGION_AUTO_SIZE_PROPAGATOR, getHost());

            Object object = request.getExtendedData().get(REGION_AUTO_SIZE_PROPAGATOR);
            for (EditPart regionPart : getRegionParts()) {
                if (object != regionPart) {
                    ctc.add(new CommandProxy(regionPart.getCommand(req)));
                }
            }

            ctc.add(CommandFactory.createICommand(domain, new RegionContainerUpdateLayoutOperation((Node) host.getModel())));
        }
        return autoSizeCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        Command resizeCommand = super.getResizeCommand(request);
        if (concernRegionContainer()) {
            resizeCommand = getRegionContainerResizeCommand(request, resizeCommand);
        }
        return resizeCommand;
    }

    private Command getRegionContainerResizeCommand(ChangeBoundsRequest request, Command command) {
        if (request.getEditParts().size() > 1) {
            return UnexecutableCommand.INSTANCE;
        }

        Command regionsResizeCommand = command;
        Collection<ChangeBoundsRequest> siblingRequests = getConstrainedRegionRequests(request);
        if (!siblingRequests.isEmpty()) {
            String name = "Region Container Resize Composite Command";
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(((IDiagramElementEditPart) getHost()).getEditingDomain(), name);
            ctc.add(new CommandProxy(command));
            regionsResizeCommand = new ICommandProxy(ctc);

            for (ChangeBoundsRequest siblingRequest : siblingRequests) {
                if (siblingRequest.getEditParts() != null) {
                    for (EditPart constrainedPart : Iterables.filter(siblingRequest.getEditParts(), EditPart.class)) {
                        Command constrainedCommand = constrainedPart.getCommand(siblingRequest);

                        if (constrainedCommand == null) {
                            constrainedCommand = UnexecutableCommand.INSTANCE;
                        }

                        ctc.add(new CommandProxy(constrainedCommand));
                    }
                }
            }
        } else if (!(request.isConstrainedMove() || request.isConstrainedResize())) {
            // Deactivate the manual resize of RegionCotnainer when there are no
            // regions.
            regionsResizeCommand = UnexecutableCommand.INSTANCE;
        }

        return regionsResizeCommand;
    }

    private Collection<ChangeBoundsRequest> getConstrainedRegionRequests(ChangeBoundsRequest request) {
        Collection<ChangeBoundsRequest> constrainedRequests = Lists.newArrayList();

        RequestQuery query = new RequestQuery(request);
        Dimension sizeDelta = request.getSizeDelta().getCopy();
        Point moveDelta = request.getMoveDelta().getCopy();

        List<AbstractDiagramElementContainerEditPart> regionToResize = getRegionParts();
        int stackDirection = getStackDirection();
        // Handle first and last regions.
        if (query.isResizeFromTop() && stackDirection == PositionConstants.NORTH_SOUTH || query.isResizeFromLeft() && stackDirection == PositionConstants.EAST_WEST) {
            Option<AbstractDiagramElementContainerEditPart> firstRegionPart = getFirstRegionPart();
            if (firstRegionPart.some() && !request.isConstrainedResize()) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(firstRegionPart.get());
                req.setSizeDelta(sizeDelta.getCopy());
                constrainedRequests.add(req);
                regionToResize.remove(firstRegionPart.get());
            }
            // shift all other regions.
            if (!regionToResize.isEmpty() && !request.isConstrainedResize()) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(Lists.newArrayList(regionToResize));

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
            if (lastRegionPart.some() && !request.isConstrainedResize()) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(lastRegionPart.get());
                req.setSizeDelta(sizeDelta.getCopy());
                constrainedRequests.add(req);
                regionToResize.remove(lastRegionPart.get());
            }
        }

        // Handle horizontal resize for vstacks and vertical resize for hstacks.
        if (stackDirection == PositionConstants.NORTH_SOUTH && (query.isResizeFromLeft() || query.isResizeFromRight())) {
            if (!regionToResize.isEmpty() && !request.isConstrainedResize()) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(Lists.newArrayList(regionToResize));
                req.setSizeDelta(new Dimension(sizeDelta.width, 0));
                constrainedRequests.add(req);
            }
        } else if (stackDirection == PositionConstants.EAST_WEST && (query.isResizeFromTop() || query.isResizeFromBottom())) {
            if (!regionToResize.isEmpty() && !request.isConstrainedResize()) {
                ChangeBoundsRequest req = initConstrainedRequest(request);
                req.setEditParts(Lists.newArrayList(regionToResize));
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
