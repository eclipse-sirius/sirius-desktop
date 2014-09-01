/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.util.StringStatics;
import org.eclipse.gmf.runtime.diagram.core.commands.SetConnectionAnchorsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.INodeEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionEditPartQuery;
import org.eclipse.sirius.diagram.ui.business.api.query.ConnectionQuery;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SetConnectionBendpointsAccordingToDraw2DCommand;
import org.eclipse.sirius.diagram.ui.graphical.edit.policies.SetConnectionBendpointsAccordingToExtremityMoveCommmand;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This command avoids time consumption as long as it does not executed. The
 * "real" command is created during the execution.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ChangeBendpointsOfEdgesCommand extends AbstractTransactionalCommand {
    CompositeTransactionalCommand wrappedCommand;

    IGraphicalEditPart host;

    PrecisionPoint moveDelta;

    boolean ignorePrimarySelection;

    /**
     * Default constructor.
     * 
     * @param host
     *            the <i>host</i> EditPart on which this policy is installed.
     * @param moveDelta
     *            The move delta
     */
    public ChangeBendpointsOfEdgesCommand(IGraphicalEditPart host, PrecisionPoint moveDelta) {
        super(host.getEditingDomain(), "Adapt bendpoints of edges", null);
        this.host = host;
        this.moveDelta = moveDelta;
    }

    /**
     * Constructor that allows to ignore the primary selection (the first
     * selected element).
     * 
     * @param host
     *            the <i>host</i> EditPart on which this policy is installed.
     * @param moveDelta
     *            The move delta
     * @param ignorePrimarySelection
     *            If the host is the primary selection of the current editor,
     *            this command will have no effect.
     */
    public ChangeBendpointsOfEdgesCommand(IGraphicalEditPart host, PrecisionPoint moveDelta, boolean ignorePrimarySelection) {
        this(host, moveDelta);
        this.ignorePrimarySelection = ignorePrimarySelection;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) {
        CommandResult result = CommandResult.newOKCommandResult();
        if (!(ignorePrimarySelection && host.getSelected() == EditPart.SELECTED_PRIMARY)) {
            // It's possible that host is not in the list of movedEditParts in
            // case of "Arrange Selection" action. Indeed, in this case, the
            // arrange selection launch a "false" arrange all (see
            // ArrangeSelectionLayoutProvider.layoutEditParts(List, IAdaptable)
            // for more details). In this case we do not consider the move given
            // it will be "revert" later by the "PinnedElementsHandler".
            List<?> movedEditParts = host.getViewer().getSelectedEditParts();
            if (movedEditParts.contains(host) && host instanceof AbstractGraphicalEditPart) {
                List<AbstractGraphicalEditPart> allMovedEditParts = getMovedChildren(Iterables.filter(movedEditParts, AbstractGraphicalEditPart.class), true);
                AbstractGraphicalEditPart currentMovedEditPart = (AbstractGraphicalEditPart) host;
                List<AbstractGraphicalEditPart> currentMovedEditPartAndItsChildren = getMovedChildren(currentMovedEditPart, true);
                final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(host.getModel());
                for (AbstractGraphicalEditPart movedEditPart : currentMovedEditPartAndItsChildren) {
                    for (ConnectionEditPart connectionEditPart : Iterables.filter(movedEditPart.getSourceConnections(), ConnectionEditPart.class)) {
                        Option<CompositeTransactionalCommand> optionalCommand = getBendpointsChangedCommand(transactionalEditingDomain, moveDelta, connectionEditPart, allMovedEditParts, true);
                        if (optionalCommand.some()) {
                            if (wrappedCommand == null) {
                                wrappedCommand = optionalCommand.get();
                            } else {
                                for (Iterator<IUndoableOperation> iterator = optionalCommand.get().iterator(); iterator.hasNext();) {
                                    wrappedCommand.add(iterator.next());
                                }
                            }

                        }
                    }
                    for (ConnectionEditPart connectionEditPart : Iterables.filter(movedEditPart.getTargetConnections(), ConnectionEditPart.class)) {
                        Option<CompositeTransactionalCommand> optionalCommand = getBendpointsChangedCommand(transactionalEditingDomain, moveDelta, connectionEditPart, allMovedEditParts, false);
                        if (optionalCommand.some()) {
                            if (wrappedCommand == null) {
                                wrappedCommand = optionalCommand.get();
                            } else {
                                for (Iterator<IUndoableOperation> iterator = optionalCommand.get().iterator(); iterator.hasNext();) {
                                    wrappedCommand.add(iterator.next());
                                }
                            }
                        }
                    }
                }
            }
        }
        if (wrappedCommand != null) {
            if (wrappedCommand.canExecute()) {
                try {
                    wrappedCommand.execute(new NullProgressMonitor(), null);
                } catch (ExecutionException e) {
                    result = CommandResult.newErrorCommandResult(e);
                }
            } else {
                // Not expected to be there
                result = CommandResult.newWarningCommandResult("The adaptation of edges according to shape move can not be done.", null);
            }
        }
        return result;
    }

    @Override
    public boolean canUndo() {
        if (wrappedCommand != null) {
            return wrappedCommand.canUndo();
        }
        return true;
    }

    @Override
    public boolean canRedo() {
        if (wrappedCommand != null) {
            return wrappedCommand.canRedo();
        }
        return true;
    }

    @Override
    public void dispose() {
        host = null;
        wrappedCommand = null;
    }

    /**
     * Compute the command needed to adapt the bendpoints of the
     * <code>connectionEditPart</code> if needed.
     * 
     * @param transactionalEditingDomain
     *            the editing domain through which model changes are made
     * @param moveDelta
     *            The move delta
     * @param connectionEditPart
     *            the connectionEditPart to deal with
     * @param allMovedEditParts
     *            This list is used to check if the other end (source or target)
     *            is also moved. In this case, there is nothing to do for the
     *            last segment of oblique and rectilinear edges. If empty all
     *            parts of diagram are considered as moved (case of arrange all)
     * @param sourceMove
     *            true if the source of the <code>connectionEditPart</code> is
     *            moved, false if this is the target.
     * @return An optional command that computes the new bendpoints of the
     *         <code>connectionEditPart</code> if needed.
     */
    protected Option<CompositeTransactionalCommand> getBendpointsChangedCommand(TransactionalEditingDomain transactionalEditingDomain, Point moveDelta, ConnectionEditPart connectionEditPart,
            List<AbstractGraphicalEditPart> allMovedEditParts, boolean sourceMove) {
        Option<CompositeTransactionalCommand> result = Options.newNone();
        Connection connectionFigure = connectionEditPart.getConnectionFigure();
        // Check that this connectionEditPart is orthogonal tree branch and is a
        // layout component
        ConnectionEditPartQuery connectionEditPartQuery = new ConnectionEditPartQuery(connectionEditPart);
        if (new ConnectionQuery(connectionFigure).isOrthogonalTreeBranch(connectionFigure.getPoints()) && connectionEditPartQuery.isLayoutComponent()) {
            CompositeTransactionalCommand command = new CompositeTransactionalCommand(transactionalEditingDomain, "Map GMF to Draw2D");

            SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(transactionalEditingDomain, "Map GMF anchor to Draw2D anchor");
            setConnectionAnchorsCommand.setEdgeAdaptor(connectionEditPart);
            setConnectionAnchorsCommand.setNewSourceTerminal(((INodeEditPart) connectionEditPart.getSource()).mapConnectionAnchorToTerminal(connectionFigure.getSourceAnchor()));
            setConnectionAnchorsCommand.setNewTargetTerminal(((INodeEditPart) connectionEditPart.getTarget()).mapConnectionAnchorToTerminal(connectionFigure.getTargetAnchor()));
            command.add(setConnectionAnchorsCommand);

            SetConnectionBendpointsAccordingToDraw2DCommand setConnectionBendpointsCommand = new SetConnectionBendpointsAccordingToDraw2DCommand(transactionalEditingDomain);
            setConnectionBendpointsCommand.setLabel("Map GMF points to Draw2D points");
            setConnectionBendpointsCommand.setSourceMove(sourceMove);
            setConnectionBendpointsCommand.setMoveDelta(new PrecisionPoint(moveDelta));
            setConnectionBendpointsCommand.setEdgeAdapter(connectionEditPart);
            command.add(setConnectionBendpointsCommand);
            result = Options.newSome(command);
        } else if (connectionEditPartQuery.isEdgeWithObliqueRoutingStyle() || connectionEditPartQuery.isEdgeWithRectilinearRoutingStyle()) {
            if (!allMovedEditParts.isEmpty()) {
                if ((sourceMove && !allMovedEditParts.contains(connectionEditPart.getTarget())) || (!sourceMove && !allMovedEditParts.contains(connectionEditPart.getSource()))) {
                    CompositeTransactionalCommand command = new CompositeTransactionalCommand(transactionalEditingDomain, "Map GMF to Draw2D");
                    // Reset the connection anchor source and target considering
                    // it
                    // can be wrongly modified by the arrange selection (see
                    // ArrangeSelectionLayoutProvider.layoutEditParts(List,
                    // IAdaptable) and previous comment in
                    // changeBendpointsOfEdges
                    // for more details)
                    SetConnectionAnchorsCommand setConnectionAnchorsCommand = new SetConnectionAnchorsCommand(transactionalEditingDomain, StringStatics.BLANK);
                    setConnectionAnchorsCommand.setEdgeAdaptor(connectionEditPart);
                    setConnectionAnchorsCommand.setNewSourceTerminal(((INodeEditPart) connectionEditPart.getSource()).mapConnectionAnchorToTerminal(connectionFigure.getSourceAnchor()));
                    setConnectionAnchorsCommand.setNewTargetTerminal(((INodeEditPart) connectionEditPart.getTarget()).mapConnectionAnchorToTerminal(connectionFigure.getTargetAnchor()));
                    command.add(setConnectionAnchorsCommand);

                    SetConnectionBendpointsAccordingToExtremityMoveCommmand setConnectionBendpointsCommand = new SetConnectionBendpointsAccordingToExtremityMoveCommmand(transactionalEditingDomain);
                    setConnectionBendpointsCommand.setSourceMove(sourceMove);
                    setConnectionBendpointsCommand.setMoveDelta(new PrecisionPoint(moveDelta));
                    setConnectionBendpointsCommand.setEdgeAdapter(connectionEditPart);
                    command.add(setConnectionBendpointsCommand);
                    result = Options.newSome(command);
                }
            }
        }
        return result;
    }

    private List<AbstractGraphicalEditPart> getMovedChildren(Iterable<AbstractGraphicalEditPart> parentEditParts, boolean addSelf) {
        List<AbstractGraphicalEditPart> result = Lists.newArrayList();
        for (AbstractGraphicalEditPart abstractGraphicalEditPart : parentEditParts) {
            if (!(ignorePrimarySelection && abstractGraphicalEditPart.getSelected() == EditPart.SELECTED_PRIMARY)) {
                result.addAll(getMovedChildren(abstractGraphicalEditPart, true));
            }
        }
        return result;
    }

    private List<AbstractGraphicalEditPart> getMovedChildren(AbstractGraphicalEditPart parentEditPart, boolean addSelf) {
        List<AbstractGraphicalEditPart> result = Lists.newArrayList();
        if (addSelf) {
            result.add(parentEditPart);
        }
        result.addAll(getMovedChildren(Iterables.filter(parentEditPart.getChildren(), AbstractGraphicalEditPart.class), true));
        return result;
    }
}
