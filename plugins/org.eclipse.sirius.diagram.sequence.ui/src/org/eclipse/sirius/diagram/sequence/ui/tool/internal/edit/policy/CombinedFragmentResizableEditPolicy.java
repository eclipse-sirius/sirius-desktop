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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.policy;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.command.IdentityCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.operation.VerticalSpaceExpansion;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.operation.SequenceEditPartsOperations;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentCompartmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.OperandEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.AbstractInteractionFrameValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator.ISEComplexMoveValidator;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A specific AirResizableEditPolicy to combined fragment roles move & resize
 * requests.
 * 
 * @author smonnier
 */
public class CombinedFragmentResizableEditPolicy extends AbstractFrameResizableEditPolicy {

    private static final String RESIZE = "Resize";

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getMoveCommand(ChangeBoundsRequest request) {
        cancelHorizontalDelta(request);
        CombinedFragmentEditPart hostPart = (CombinedFragmentEditPart) getHost();

        ICommand solution = IdentityCommand.INSTANCE;
        RequestQuery requestQuery = new RequestQuery(request);
        if (hostPart.getSelected() == EditPart.SELECTED_PRIMARY && requestQuery.isMove()) {
            ISEComplexMoveValidator validator = ISEComplexMoveValidator.getOrCreateValidator(request, requestQuery, hostPart.getISequenceEvent());
            if (validator != null && validator.isValid()) {
                CompositeTransactionalCommand ctc = buildNewMoveCommand(hostPart, request, validator);
                postProcessDefaultCommand(hostPart, request, ctc, null);
                solution = ctc;
            } else {
                solution = org.eclipse.gmf.runtime.common.core.command.UnexecutableCommand.INSTANCE;
            }
        }
        return new ICommandProxy(solution);
    }

    private CompositeTransactionalCommand buildNewMoveCommand(CombinedFragmentEditPart hostPart, ChangeBoundsRequest request, ISEComplexMoveValidator validator) {
        TransactionalEditingDomain editingDomain = hostPart.getEditingDomain();
        ISEComplexMoveCommandBuilder builder = new ISEComplexMoveCommandBuilder(editingDomain, "Execution Move Composite Command", new RequestQuery(request), validator);
        return builder.buildCommand();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        cancelHorizontalDelta(request);

        CombinedFragmentEditPart cfep = (CombinedFragmentEditPart) getHost();
        ISequenceEvent fragment = cfep.getISequenceEvent();
        AbstractInteractionFrameValidator validator = AbstractInteractionFrameValidator.getOrCreateResizeValidator(request, (CombinedFragment) fragment);

        Command result;
        if (validator != null && validator.isValid()) {
            CompositeTransactionalCommand ctc = getRezizeCustomCommand(cfep, request);

            Range expansionZone = validator.getExpansionZone();
            ICommand autoExpand = null;
            if (expansionZone != null && !expansionZone.isEmpty()) {
                SequenceDiagram diagram = fragment.getDiagram();
                Collection<ISequenceEvent> eventToIgnore = Collections.singletonList(fragment);
                autoExpand = CommandFactory.createICommand(cfep.getEditingDomain(), new VerticalSpaceExpansion(diagram, expansionZone, 0, eventToIgnore));
            }

            postProcessDefaultCommand(cfep, request, ctc, autoExpand);
            result = new ICommandProxy(ctc);
        } else {
            result = UnexecutableCommand.INSTANCE;
        }

        return result;
    }

    private CompositeTransactionalCommand getRezizeCustomCommand(CombinedFragmentEditPart self, ChangeBoundsRequest request) {
        CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(self.getEditingDomain(), "Combined Fragment Resize Composite Command");
        ctc.add(CombinedFragmentResizableEditPolicy.getResizeBorderItemTCommand(self, request));
        Option<CombinedFragment> combinedFragmentOption = ISequenceElementAccessor.getCombinedFragment(self.getNotationView());
        if (combinedFragmentOption.some() && !combinedFragmentOption.get().getOperands().isEmpty()) {
            // if (!validateResize(request, combinedFragmentOption.get())) {
            // // FIXME this validation should be removed because done in the
            // // validator now.
            // return UnexecutableCommand.INSTANCE;
            // }
            if (request.getResizeDirection() == PositionConstants.NORTH) {
                // we need to process the same resize to the first operand
                // and keep the others unchanged
                addResizeOperandsFromNorthCommand(ctc, request);
            } else if (request.getResizeDirection() == PositionConstants.SOUTH) {
                // we need to process the same resize to the last operand
                // and keep the others unchanged
                addResizeOperandsFromSouthCommand(ctc, request);
            }
        }
        return ctc;
    }

    private void postProcessDefaultCommand(CombinedFragmentEditPart self, ChangeBoundsRequest request, CompositeTransactionalCommand ctc, ICommand autoExpand) {
        if (ctc != null && ctc.canExecute()) {
            if (autoExpand != null) {
                ctc.compose(autoExpand);
            }

            SequenceEditPartsOperations.addRefreshGraphicalOrderingCommand(ctc, self);
            SequenceEditPartsOperations.addRefreshSemanticOrderingCommand(ctc, self);
            SequenceEditPartsOperations.addSynchronizeSemanticOrderingCommand(ctc, self.getISequenceEvent());
        }
    }

    /**
     * Returns the command to resize the combined fragment from the north face,
     * and therefore resize up the first operand.
     * 
     * @param ctc
     *            the current transactional command.
     * @param request
     *            the request for a resize.
     */
    private void addResizeOperandsFromNorthCommand(CompositeTransactionalCommand ctc, ChangeBoundsRequest request) {
        // we need to process the same resize to the first operand
        OperandEditPart firstOperandEditPart = getFirstOperandEditPart();
        Option<Operand> firstOperand = ISequenceElementAccessor.getOperand(firstOperandEditPart.getNotationView());
        int position = 0;

        OperandEditPart followingOperandEditPart = getFollowingOperandEditPart(position);
        Option<Operand> followingOperand = followingOperandEditPart != null ? ISequenceElementAccessor.getOperand(followingOperandEditPart.getNotationView()) : Options.<Operand> newNone();

        position++;

        Point newLocation = null;
        Dimension newDimension = null;
        if (firstOperand.some()) {
            Range verticalRange = firstOperand.get().getVerticalRange();
            newLocation = new Point(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT);
            newDimension = new Dimension(firstOperand.get().getBounds().width, verticalRange.width() + request.getSizeDelta().height);
            ctc.add(createOperandSetBoundsCommand(firstOperandEditPart, newLocation, newDimension));
        }

        if (followingOperandEditPart != null && followingOperand.some()) {

            // recurse
            while (followingOperandEditPart != null && followingOperand.some()) {
                Range verticalRange = followingOperand.get().getVerticalRange();
                newLocation = new Point(0, newLocation.y + newDimension.height);
                newDimension = new Dimension(followingOperand.get().getBounds().width, verticalRange.width());
                ctc.add(createOperandSetBoundsCommand(followingOperandEditPart, newLocation, newDimension));
                followingOperandEditPart = getFollowingOperandEditPart(position);
                followingOperand = followingOperandEditPart != null ? ISequenceElementAccessor.getOperand(followingOperandEditPart.getNotationView()) : Options.<Operand> newNone();
                position++;
            }

        }
    }

    /**
     * Returns the command to resize the combined fragment from the south face,
     * and therefore resize down the last operand.
     * 
     * @param ctc
     *            the current transactional command.
     * @param request
     *            the request for a resize.
     */
    public void addResizeOperandsFromSouthCommand(CompositeTransactionalCommand ctc, ChangeBoundsRequest request) {
        // we need to process the same resize to the first operand if it is
        // single
        OperandEditPart firstOperandEditPart = getFirstOperandEditPart();
        Option<Operand> firstOperand = ISequenceElementAccessor.getOperand(firstOperandEditPart.getNotationView());
        int position = 0;

        OperandEditPart followingOperandEditPart = getFollowingOperandEditPart(position);
        Option<Operand> followingOperand = followingOperandEditPart != null ? ISequenceElementAccessor.getOperand(followingOperandEditPart.getNotationView()) : Options.<Operand> newNone();

        position++;

        OperandEditPart lastOperandEditPart = getLastOperandEditPart();

        Point newLocation = null;
        Dimension newDimension = null;
        if (firstOperand.some()) {
            Range verticalRange = firstOperand.get().getVerticalRange();
            newLocation = new Point(0, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT);
            if (firstOperandEditPart == lastOperandEditPart) {
                newDimension = new Dimension(firstOperand.get().getBounds().width, verticalRange.width() + request.getSizeDelta().height);
            } else {
                newDimension = new Dimension(firstOperand.get().getBounds().width, verticalRange.width());
            }
            ctc.add(createOperandSetBoundsCommand(firstOperandEditPart, newLocation, newDimension));
        }

        if (followingOperandEditPart != null && followingOperand.some()) {
            Range verticalRange = followingOperand.get().getVerticalRange();

            // recurse
            while (followingOperandEditPart != null && followingOperand.some() && followingOperandEditPart != lastOperandEditPart) {
                newLocation = new Point(0, newLocation.y + newDimension.height);
                newDimension = new Dimension(followingOperand.get().getBounds().width, verticalRange.width());
                ctc.add(createOperandSetBoundsCommand(followingOperandEditPart, newLocation, newDimension));
                followingOperandEditPart = getFollowingOperandEditPart(position);
                followingOperand = followingOperandEditPart != null ? ISequenceElementAccessor.getOperand(followingOperandEditPart.getNotationView()) : Options.<Operand> newNone();
                verticalRange = followingOperand.get().getVerticalRange();
                position++;
            }
            newLocation = new Point(0, newLocation.y + newDimension.height);
            newDimension = new Dimension(followingOperand.get().getBounds().width, verticalRange.width() + request.getSizeDelta().height);
            ctc.add(createOperandSetBoundsCommand(lastOperandEditPart, newLocation, newDimension));

        }
    }

    /**
     * Finds the following {@link OperandEditPart} of the current
     * {@link OperandEditPart} identified by the index currentOperandIndex.
     * 
     * @param currentOperandIndex
     *            the index of the current {@link OperandEditPart}
     * @return the following {@link OperandEditPart}
     */
    private OperandEditPart getFollowingOperandEditPart(int currentOperandIndex) {
        CombinedFragmentCompartmentEditPart combinedFragmentCompartmentEditPart = Iterables.getOnlyElement(Iterables.filter(getHost().getChildren(), CombinedFragmentCompartmentEditPart.class));
        Iterable<OperandEditPart> operandEditPartList = Iterables.filter(combinedFragmentCompartmentEditPart.getChildren(), OperandEditPart.class);
        for (OperandEditPart operandEditPart : operandEditPartList) {
            Option<Operand> operandOption = ISequenceElementAccessor.getOperand(operandEditPart.getNotationView());
            if (operandOption.some()) {
                Operand operand = operandOption.get();
                int operandIndex = operand.getIndex();
                if (operandIndex == currentOperandIndex + 1) {
                    return operandEditPart;
                }
            }
        }
        return null;
    }

    /**
     * Finds the first {@link OperandEditPart}.
     * 
     * @return the first {@link OperandEditPart}
     */
    private OperandEditPart getFirstOperandEditPart() {
        CombinedFragmentCompartmentEditPart combinedFragmentCompartmentEditPart = Iterables.getOnlyElement(Iterables.filter(getHost().getChildren(), CombinedFragmentCompartmentEditPart.class));
        for (OperandEditPart operandEditPart : Iterables.filter(combinedFragmentCompartmentEditPart.getChildren(), OperandEditPart.class)) {
            Option<Operand> operandOption = ISequenceElementAccessor.getOperand(operandEditPart.getNotationView());
            if (operandOption.some()) {
                Operand operand = operandOption.get();
                int operandIndex = operand.getIndex();
                if (operandIndex == 0) {
                    return operandEditPart;
                }
            }
        }
        return null;
    }

    /**
     * Finds the last {@link OperandEditPart}.
     * 
     * @return the last {@link OperandEditPart}
     */
    private OperandEditPart getLastOperandEditPart() {
        CombinedFragmentCompartmentEditPart combinedFragmentCompartmentEditPart = Iterables.getOnlyElement(Iterables.filter(getHost().getChildren(), CombinedFragmentCompartmentEditPart.class));
        Iterable<OperandEditPart> operandEditPartList = Iterables.filter(combinedFragmentCompartmentEditPart.getChildren(), OperandEditPart.class);
        int childrenOperandEditPartNumber = Iterables.size(operandEditPartList);
        for (OperandEditPart operandEditPart : operandEditPartList) {
            Option<Operand> operandOption = ISequenceElementAccessor.getOperand(operandEditPart.getNotationView());
            if (operandOption.some()) {
                Operand operand = operandOption.get();
                int operandIndex = operand.getIndex();
                if (operandIndex == childrenOperandEditPartNumber - 1) {
                    return operandEditPart;
                }
            }
        }
        return null;
    }

    /**
     * Returns the command to resize a bordered node.
     * 
     * @param part
     *            the edit part corresponding to the bordered node.
     * @param request
     *            the request for a resize.
     * @return the command to resize a bordered node.
     */
    public static AbstractTransactionalCommand getResizeBorderItemTCommand(IGraphicalEditPart part, ChangeBoundsRequest request) {
        final EObject semantic = part.resolveSemanticElement();
        if (semantic instanceof DNodeContainer) {
            final double zoom = ((ZoomManager) part.getViewer().getProperty(ZoomManager.class.toString())).getZoom();
            final Dimension dimension = CombinedFragmentResizableEditPolicy.getDimensionFromView(part);
            final Point position = CombinedFragmentResizableEditPolicy.getPositionFromView(part);
            dimension.height += request.getSizeDelta().height / zoom;
            switch (request.getResizeDirection()) {
            case PositionConstants.NORTH:
            case PositionConstants.NORTH_WEST:
            case PositionConstants.NORTH_EAST:
                position.y -= request.getSizeDelta().height / zoom;
                break;
            default:
                break;
            }
            return new SetBoundsCommand(part.getEditingDomain(), RESIZE, new EObjectAdapter(part.getNotationView()), new Rectangle(position, dimension));
        }
        return null;
    }

    /**
     * Creates the {@link SetBoundsCommand} to resize an operand.
     * 
     * @param part
     *            the {@link OperandEditPart}
     * @param location
     *            the new location of the operand
     * @param dimension
     *            the new dimension of the operand
     * @return a command to resize an operand
     */
    private AbstractTransactionalCommand createOperandSetBoundsCommand(IGraphicalEditPart part, Point location, Dimension dimension) {
        return new SetBoundsCommand(part.getEditingDomain(), RESIZE, new EObjectAdapter(part.getNotationView()), new Rectangle(location, dimension));
    }

    private static Point getPositionFromView(IGraphicalEditPart part) {
        final Point position = new Point();
        if (part.getNotationView() instanceof Node && ((Node) part.getNotationView()).getLayoutConstraint() instanceof Location) {
            final Location location = (Location) ((Node) part.getNotationView()).getLayoutConstraint();
            position.x = location.getX();
            position.y = location.getY();
        }
        return position;
    }

    private static Dimension getDimensionFromView(IGraphicalEditPart part) {
        final Dimension dimension = new Dimension();
        if (part.getNotationView() instanceof Node && ((Node) part.getNotationView()).getLayoutConstraint() instanceof Size) {
            final Size size = (Size) ((Node) part.getNotationView()).getLayoutConstraint();
            dimension.width = size.getWidth();
            dimension.height = size.getHeight();
        }
        return dimension;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Collection<ISequenceEventEditPart> getChildrenToFeedBack(ChangeBoundsRequest request) {
        Collection<ISequenceEventEditPart> feedback = Lists.newArrayList();
        if (getHost() instanceof CombinedFragmentEditPart && new RequestQuery(request).isMove()) {
            CombinedFragmentEditPart cfep = (CombinedFragmentEditPart) getHost();
            for (CombinedFragmentCompartmentEditPart cpt : Iterables.filter(cfep.getChildren(), CombinedFragmentCompartmentEditPart.class)) {
                Iterables.addAll(feedback, Iterables.filter(cpt.getChildren(), OperandEditPart.class));
            }
        }
        return feedback;
    }
}
