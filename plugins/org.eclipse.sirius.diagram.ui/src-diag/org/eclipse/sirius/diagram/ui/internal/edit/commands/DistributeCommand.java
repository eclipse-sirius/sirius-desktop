/*******************************************************************************
 * Copyright (c) 2014, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.internal.edit.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.diagram.ui.internal.figures.LabelHelper;
import org.eclipse.gmf.runtime.diagram.ui.internal.util.LabelViewConstants;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.ui.business.internal.operation.ShiftDirectBorderedNodesOperation;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.distribute.DistributeAction;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;

/**
 * This command distributes shapes OR labels of edges.<BR>
 * Performance information: This command is only time consuming on execution, not creation. The "real" command,
 * <code>wrappedCommand</code>, is created during the execution.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DistributeCommand extends AbstractTransactionalCommand {
    /** Command created only during the execution of the DistributeCommand. */
    CompositeTransactionalCommand wrappedCommand;

    /** List of parts to distribute (labels of edges or nodes but not a mix of both). */
    List<IGraphicalEditPart> editPartsToDistribute;

    /**
     * The distribution type must be one of:
     * <UL>
     * <LI>DistributeAction.HORIZONTALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_HORIZONTALLY</LI>
     * <LI>DistributeAction.VERTICALLY_WITH_UNIFORM_GAPS</LI>
     * <LI>DistributeAction.CENTERS_VERTICALLY</LI>
     * </UL>
     */
    private int distributeType;

    /**
     * Default constructor.
     *
     * @param domain
     *            my editing domain
     * @param editPartsToDistribute
     *            Selected edit parts that will be moved (distributed)
     * @param distributeType
     *            The distribution type
     */
    public DistributeCommand(TransactionalEditingDomain domain, List<IGraphicalEditPart> editPartsToDistribute, int distributeType) {
        super(domain, DistributeAction.getLabel(distributeType, true), null);
        this.editPartsToDistribute = editPartsToDistribute;
        this.distributeType = distributeType;
    }

    @Override
    protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) {
        CommandResult result = CommandResult.newOKCommandResult();
        if (wrappedCommand == null) {
            wrappedCommand = new CompositeTransactionalCommand(getEditingDomain(), this.getLabel());

            HashMap<IGraphicalEditPart, Rectangle> partToBounds = new HashMap<>();
            for (IGraphicalEditPart part : editPartsToDistribute) {
                Rectangle bounds = part.getFigure().getBounds().getCopy();
                partToBounds.put(part, bounds);
            }

            if (distributeType == DistributeAction.GAPS_HORIZONTALLY) {
                distributeHorizontallyWithUniformGaps(partToBounds);
            } else if (distributeType == DistributeAction.CENTERS_HORIZONTALLY) {
                distributeCentersHorizontally(partToBounds);
            } else if (distributeType == DistributeAction.GAPS_VERTICALLY) {
                distributeVerticallyWithUniformGaps(partToBounds);
            } else if (distributeType == DistributeAction.CENTERS_VERTICALLY) {
                distributeCentersVertically(partToBounds);
            }
        }

        if (wrappedCommand.size() > 0) {
            if (wrappedCommand.canExecute()) {
                try {
                    wrappedCommand.execute(new NullProgressMonitor(), null);
                } catch (ExecutionException e) {
                    result = CommandResult.newErrorCommandResult(e);
                }
            } else {
                // Not expected to be there
                result = CommandResult.newWarningCommandResult(Messages.DistributeCommand_errorMsg, null);
            }
        }
        return result;
    }

    /**
     * A {@link Comparator} that sorts the {@link IGraphicalEditPart parts} by
     * the x coordinate of the center of the bounds of the part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class PartByCenter implements Comparator<IGraphicalEditPart> {

        HashMap<IGraphicalEditPart, Rectangle> partToBounds;

        /**
         * Default constructor.
         */
        public PartByCenter(HashMap<IGraphicalEditPart, Rectangle> partToBounds) {
            this.partToBounds = partToBounds;
        }

        @Override
        public int compare(IGraphicalEditPart part1, IGraphicalEditPart part2) {
            Integer a = Integer.valueOf(partToBounds.get(part1).getCenter().x);
            Integer b = Integer.valueOf(partToBounds.get(part2).getCenter().x);
            return a.compareTo(b);
        }
    }

    /**
     * A {@link Comparator} that sorts the {@link IGraphicalEditPart parts} by
     * the y coordinate of the center of the bounds of the part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class PartByMiddle implements Comparator<IGraphicalEditPart> {

        HashMap<IGraphicalEditPart, Rectangle> partToBounds;

        /**
         * Default constructor.
         */
        public PartByMiddle(HashMap<IGraphicalEditPart, Rectangle> partToBounds) {
            this.partToBounds = partToBounds;
        }

        @Override
        public int compare(IGraphicalEditPart part1, IGraphicalEditPart part2) {
            Integer a = Integer.valueOf(partToBounds.get(part1).getCenter().y);
            Integer b = Integer.valueOf(partToBounds.get(part2).getCenter().y);
            return a.compareTo(b);
        }
    }

    /**
     * A function to get the new bounds according to the bounds of the previous
     * edit part and the gap. The {@link #apply(Object, Rectangle, int)} must be
     * called instead of {@link #apply(Object)} for this function. This function
     * does not modify the original bounds.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     *
     * @param <IGraphicalEditPart>
     *            The edit part from which getting the new bounds
     * @param <Rectangle>
     *            The returned value
     */
    public abstract class GetNewBoundsFunction implements Function<IGraphicalEditPart, Rectangle> {
        protected int gap;

        protected Rectangle previousPartBounds;

        public Rectangle apply(IGraphicalEditPart input, Rectangle previousPartBounds, int gap) {
            this.gap = gap;
            this.previousPartBounds = previousPartBounds;
            return apply(input);
        };
    };

    /**
     * A function with {@link HashMap<IGraphicalEditPart, Rectangle>} as
     * parameter.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     *
     */
    public abstract class FunctionWithBounds implements Function<IGraphicalEditPart, Integer> {
        HashMap<IGraphicalEditPart, Rectangle> partsToBounds;

        public FunctionWithBounds(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            this.partsToBounds = partsToBounds;
        }

        @Override
        public Integer apply(IGraphicalEditPart input) {
            return apply(partsToBounds.get(input));
        }

        protected abstract Integer apply(Rectangle rectangle);

    };

    /**
     * Get the x coordinate of the left side of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetLeftFunction extends FunctionWithBounds {
        public GetLeftFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.x);
        };
    };

    /**
     * Get the x coordinate of the center of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetCenterFunction extends FunctionWithBounds {
        public GetCenterFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.getCenter().x);
        };
    };

    /**
     * Get the x coordinate of the right side of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetRightFunction extends FunctionWithBounds {
        public GetRightFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.getRight().x);
        };
    };

    /**
     * Get the y coordinate of the top side of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetTopFunction extends FunctionWithBounds {
        public GetTopFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.y);
        };
    };

    /**
     * Get the y coordinate of the center the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetMiddleFunction extends FunctionWithBounds {
        public GetMiddleFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.getCenter().y);
        };
    };

    /**
     * Get the y coordinate of the bottom side of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetBottomFunction extends FunctionWithBounds {
        public GetBottomFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.getBottom().y);
        };
    };

    /**
     * Get width of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetWidthFunction extends FunctionWithBounds {
        public GetWidthFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.width);
        };
    };

    /**
     * Get height of the edit part.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public class GetHeightFunction extends FunctionWithBounds {
        public GetHeightFunction(HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
            super(partsToBounds);
        }

        @Override
        protected Integer apply(Rectangle rectangle) {
            return Integer.valueOf(rectangle.height);
        };
    };

    /**
     * Function to compute the gap for the parts to distribute.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    public abstract class GetGapFunction {
        protected Set<IGraphicalEditPart> partsToDistribute;

        protected Function<IGraphicalEditPart, Integer> getFirstPartMainAxisFunction;

        protected Function<IGraphicalEditPart, Integer> getLastPartMainAxisFunction;

        protected Function<IGraphicalEditPart, Integer> getSizeFunction;

        public GetGapFunction(Set<IGraphicalEditPart> partsToDistribute, Function<IGraphicalEditPart, Integer> getFirstPartMainAxisFunction,
                Function<IGraphicalEditPart, Integer> getLastPartMainAxisFunction, Function<IGraphicalEditPart, Integer> getSizeFunction) {
            this.partsToDistribute = partsToDistribute;
            this.getFirstPartMainAxisFunction = getFirstPartMainAxisFunction;
            this.getLastPartMainAxisFunction = getLastPartMainAxisFunction;
            this.getSizeFunction = getSizeFunction;
        }

        public abstract int apply(IGraphicalEditPart firstPart, IGraphicalEditPart lastPart);
    }

    /**
     * Must be called only when <code>wrappedCommand</code> is initialized.
     *
     * @param partsToBounds
     *            List of parts to distribute associated with their bounds (the
     *            bounds of their figure).
     */
    private void distributeHorizontallyWithUniformGaps(final HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
        GetNewBoundsFunction setXFunction = new GetNewBoundsFunction() {
            @Override
            public Rectangle apply(IGraphicalEditPart input) {
                return partsToBounds.get(input).getCopy().setX(previousPartBounds.getRight().x + gap);
            };
        };
        distributeWithUniformGaps(partsToBounds.keySet(), new GetLeftFunction(partsToBounds), new GetTopFunction(partsToBounds), new GetRightFunction(partsToBounds),
                new GetBottomFunction(partsToBounds), new GetWidthFunction(partsToBounds), new PartByCenter(partsToBounds), setXFunction, Functions.forMap(partsToBounds));
    }

    /**
     * Must be called only when <code>wrappedCommand</code> is initialized.
     *
     * @param partsToBounds
     *            List of parts to distribute associated with their bounds.
     */
    private void distributeVerticallyWithUniformGaps(final HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
        GetNewBoundsFunction setYFunction = new GetNewBoundsFunction() {
            @Override
            public Rectangle apply(IGraphicalEditPart input) {
                return partsToBounds.get(input).getCopy().setY(previousPartBounds.getBottom().y + gap);
            };
        };
        distributeWithUniformGaps(partsToBounds.keySet(), new GetTopFunction(partsToBounds), new GetLeftFunction(partsToBounds), new GetBottomFunction(partsToBounds),
                new GetRightFunction(partsToBounds), new GetHeightFunction(partsToBounds), new PartByMiddle(partsToBounds), setYFunction, Functions.forMap(partsToBounds));
    }

    private void distributeWithUniformGaps(Set<IGraphicalEditPart> partsToDistribute, Function<IGraphicalEditPart, Integer> getFirstPartMainAxisFunction,
            Function<IGraphicalEditPart, Integer> getFirstPartSecondAxisFunction, Function<IGraphicalEditPart, Integer> getLastPartMainAxisFunction,
            Function<IGraphicalEditPart, Integer> getLastPartSecondAxisFunction, Function<IGraphicalEditPart, Integer> getSizeFunction, Comparator<IGraphicalEditPart> comparator,
            GetNewBoundsFunction setValueFunction, Function<IGraphicalEditPart, Rectangle> getBoundsFunction) {
        distribute(partsToDistribute, getFirstPartMainAxisFunction, getFirstPartSecondAxisFunction, getLastPartMainAxisFunction, getLastPartSecondAxisFunction, comparator, setValueFunction,
                getBoundsFunction, new GetGapFunction(partsToDistribute, getFirstPartMainAxisFunction, getLastPartMainAxisFunction, getSizeFunction) {
                    @Override
                    public int apply(IGraphicalEditPart firstPart, IGraphicalEditPart lastPart) {
                        // Get available space between these 2 parts
                        int availableSpaceBetweenFirstAndLast = getFirstPartMainAxisFunction.apply(lastPart) - getLastPartMainAxisFunction.apply(firstPart);
                        // Get remaining space considering the size of other
                        // parts
                        int availableSpace = availableSpaceBetweenFirstAndLast;
                        for (IGraphicalEditPart part : partsToDistribute) {
                            if (!part.equals(firstPart) && !part.equals(lastPart)) {
                                availableSpace -= getSizeFunction.apply(part);
                            }
                        }
                        // Gap is rounded up to the lower Integer if the
                        // remainder is less than or equal to 0.5 and higher
                        // integer if the remainder is greater than 0.5.
                        return Math.round(((float) availableSpace) / (partsToDistribute.size() - 1));
                    }
                });
    }

    /**
     * Must be called only when <code>wrappedCommand</code> is initialized.
     *
     * @param partsToBounds
     *            List of parts to distribute associated with their bounds.
     */
    private void distributeCentersHorizontally(final HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
        GetNewBoundsFunction setCenterFunction = new GetNewBoundsFunction() {
            @Override
            public Rectangle apply(IGraphicalEditPart input) {
                Rectangle r = partsToBounds.get(input).getCopy();
                return r.setX(previousPartBounds.getCenter().x + gap - (r.width / 2));
            };
        };

        distributeCenters(partsToBounds.keySet(), new GetCenterFunction(partsToBounds), new GetTopFunction(partsToBounds), new GetBottomFunction(partsToBounds), new GetWidthFunction(partsToBounds),
                new PartByCenter(partsToBounds), setCenterFunction, Functions.forMap(partsToBounds));
    }

    /**
     * Must be called only when <code>wrappedCommand</code> is initialized.
     *
     * @param partsToBounds
     *            List of parts to distribute associated with their bounds.
     */
    private void distributeCentersVertically(final HashMap<IGraphicalEditPart, Rectangle> partsToBounds) {
        GetNewBoundsFunction setMiddleFunction = new GetNewBoundsFunction() {
            @Override
            public Rectangle apply(IGraphicalEditPart input) {
                Rectangle r = partsToBounds.get(input).getCopy();
                return r.setY(previousPartBounds.getCenter().y + gap - (r.height / 2));
            };
        };
        distributeCenters(partsToBounds.keySet(), new GetMiddleFunction(partsToBounds), new GetLeftFunction(partsToBounds), new GetRightFunction(partsToBounds), new GetHeightFunction(partsToBounds),
                new PartByMiddle(partsToBounds), setMiddleFunction, Functions.forMap(partsToBounds));
    }

    private void distributeCenters(Set<IGraphicalEditPart> partsToDistribute, Function<IGraphicalEditPart, Integer> getMainAxisFunction,
            Function<IGraphicalEditPart, Integer> getFirstPartSecondAxisFunction, Function<IGraphicalEditPart, Integer> getLastPartSecondAxisFunction,
            Function<IGraphicalEditPart, Integer> getSizeFunction, Comparator<IGraphicalEditPart> comparator, GetNewBoundsFunction setValueFunction,
            Function<IGraphicalEditPart, Rectangle> getBoundsFunction) {
        distribute(partsToDistribute, getMainAxisFunction, getFirstPartSecondAxisFunction, getMainAxisFunction, getLastPartSecondAxisFunction, comparator, setValueFunction, getBoundsFunction,
                new GetGapFunction(partsToDistribute, getMainAxisFunction, getMainAxisFunction, getSizeFunction) {
                    @Override
                    public int apply(IGraphicalEditPart firstPart, IGraphicalEditPart lastPart) {
                        // Gap is rounded up to the lower Integer if the
                        // remainder is less than or equal to 0.5 and higher
                        // integer if the remainder is greater than 0.5.
                        return Math.round(((float) (getFirstPartMainAxisFunction.apply(lastPart) - getLastPartMainAxisFunction.apply(firstPart))) / (partsToDistribute.size() - 1));
                    }
                });
    }

    /**
     * Generic distribute method that distributes shapes according to parameters
     * to customize the distribution.
     *
     * @param partsToDistribute
     *            List of {@link IGraphicalEditPart parts} to distribute
     *            (including the first and the last that don't move).
     * @param getFirstPartMainAxisFunction
     *            A function that returns the x or y coordinate of the Point to
     *            consider for detecting the first part
     * @param getFirstPartSecondAxisFunction
     *            If at least two part have the same coordinate for the main
     *            axis, this function is used to choose the first part
     * @param getLastPartMainAxisFunction
     *            A function that returns the x or y coordinate of the Point to
     *            consider for detecting the last part
     * @param getLastPartSecondAxisFunction
     *            If at least two part have the same coordinate for the main
     *            axis, this function is used to choose the last part
     * @param comparator
     *            A comparator to sort the part from left to right or from top
     *            to bottom
     * @param getNewBoundsFunction
     *            A function to set the new location of the part after
     *            distribution
     * @param getBoundsFunction
     *            A function that returns the current bounds rectangle
     * @param getGapFunction
     *            A function to get the gap between each part after distribution
     */
    private void distribute(Collection<IGraphicalEditPart> partsToDistribute, Function<IGraphicalEditPart, Integer> getFirstPartMainAxisFunction,
            Function<IGraphicalEditPart, Integer> getFirstPartSecondAxisFunction, Function<IGraphicalEditPart, Integer> getLastPartMainAxisFunction,
            Function<IGraphicalEditPart, Integer> getLastPartSecondAxisFunction, Comparator<IGraphicalEditPart> comparator, GetNewBoundsFunction getNewBoundsFunction,
            Function<IGraphicalEditPart, Rectangle> getBoundsFunction, GetGapFunction getGapFunction) {
        // Get first and last parts
        int firstPartMainAxis = 0;
        int firstPartSecondAxis = 0;
        int lastPartMainAxis = 0;
        int lastPartSecondAxis = 0;
        IGraphicalEditPart firstPart = null;
        IGraphicalEditPart lastPart = null;
        for (IGraphicalEditPart part : partsToDistribute) {
            if (firstPart == null || getFirstPartMainAxisFunction.apply(part) < firstPartMainAxis
                    || (getFirstPartMainAxisFunction.apply(part) == firstPartMainAxis && getFirstPartSecondAxisFunction.apply(part) < firstPartSecondAxis)) {
                firstPart = part;
                firstPartMainAxis = getFirstPartMainAxisFunction.apply(part);
                firstPartSecondAxis = getFirstPartSecondAxisFunction.apply(part);
            }
            if (lastPart == null || getLastPartMainAxisFunction.apply(part) > lastPartMainAxis
                    || (getLastPartMainAxisFunction.apply(part) == lastPartMainAxis && getLastPartSecondAxisFunction.apply(part) > lastPartSecondAxis)) {
                lastPart = part;
                lastPartMainAxis = getLastPartMainAxisFunction.apply(part);
                lastPartSecondAxis = getLastPartSecondAxisFunction.apply(part);
            }
        }
        if (firstPart.equals(lastPart)) {
            // The first part and the last part is the same (a large
            // figure that covers all the bounds of the current selection),
            // nothing to to!
            return;
        }

        // Get the gap between each parts
        int gap = getGapFunction.apply(firstPart, lastPart);
        // Sort other parts according to their centers
        List<IGraphicalEditPart> partsToMove = Lists.newArrayList(partsToDistribute);
        partsToMove.remove(firstPart);
        partsToMove.remove(lastPart);
        Collections.sort(partsToMove, comparator);
        Rectangle previousPartBounds = getBoundsFunction.apply(firstPart);
        // Move other parts with the same gap
        if (!(partsToMove.get(0) instanceof IBorderItemEditPart)) {
            for (IGraphicalEditPart editPart : partsToMove) {
                Rectangle newBounds = getNewBoundsFunction.apply(editPart, previousPartBounds, gap);

                IAdaptable adapter = new EObjectAdapter((Node) editPart.getModel());
                if (editPart instanceof AbstractDEdgeNameEditPart) {
                    // Compute label relative location (as in
                    // ResizableShapeLabelEditPolicy.getMoveCommand(ChangeBoundsRequest))
                    Point refPoint = getReferencePoint((AbstractDEdgeNameEditPart) editPart);
                    Point relativeLabelLocation = LabelHelper.offsetFromRelativeCoordinate(editPart.getFigure(), newBounds, refPoint);
                    wrappedCommand.compose(new SetBoundsCommand(wrappedCommand.getEditingDomain(), wrappedCommand.getLabel(), adapter, relativeLabelLocation));
                } else {
                    wrappedCommand.compose(new SetBoundsCommand(wrappedCommand.getEditingDomain(), wrappedCommand.getLabel(), adapter, newBounds));
                }
                previousPartBounds = newBounds;
            }
        } else {
            HashMap<IGraphicalEditPart, IFigure> partToFigureToIgnore = new HashMap<>();
            List<IFigure> additionalFiguresForConflictsDetection = new ArrayList<>();
            for (IGraphicalEditPart editPart : partsToMove) {
                partToFigureToIgnore.put(editPart, editPart.getFigure());
            }

            for (IGraphicalEditPart editPart : partsToMove) {
                Rectangle expectedNewBounds = getNewBoundsFunction.apply(editPart, previousPartBounds, gap);
                Rectangle newBounds = expectedNewBounds.getCopy();
                if (editPart instanceof IBorderItemEditPart) {
                    // If this bounds is for a border node, we must check that
                    // there is no conflicts with existing
                    IBorderItemEditPart borderEditPart = (IBorderItemEditPart) editPart;
                    IBorderItemLocator borderItemLocator = borderEditPart.getBorderItemLocator();
                    if (borderItemLocator instanceof DBorderItemLocator) {
                        newBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(newBounds, editPart.getFigure(), partToFigureToIgnore.values(), additionalFiguresForConflictsDetection);
                    } else {
                        newBounds = borderItemLocator.getValidLocation(newBounds, editPart.getFigure());
                    }
                    // Remove this figure from the list to ignore and add them
                    // to the additional figures with its new bounds.
                    partToFigureToIgnore.remove(editPart);
                    editPart.getFigure().setBounds(newBounds);
                    additionalFiguresForConflictsDetection.add(editPart.getFigure());
                }
                Dimension delta = newBounds.getLocation().getDifference(getBoundsFunction.apply(editPart).getLocation());
                if (delta.width != 0) {
                    wrappedCommand.compose(CommandFactory.createICommand(wrappedCommand.getEditingDomain(),
                            new ShiftDirectBorderedNodesOperation(Lists.newArrayList((Node) editPart.getModel()), new Dimension(delta.width, 0))));
                } else {
                    wrappedCommand.compose(CommandFactory.createICommand(wrappedCommand.getEditingDomain(),
                            new ShiftDirectBorderedNodesOperation(Lists.newArrayList((Node) editPart.getModel()), new Dimension(0, delta.height))));
                }
                previousPartBounds = expectedNewBounds;
            }
        }
    }

    /**
     * Get the referenced point used as target point. Method copied from
     * {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.ResizableShapeLabelEditPolicy#getReferencePoint()}.
     *
     * @return the referenced point used as target point
     */
    private Point getReferencePoint(AbstractDEdgeNameEditPart dEdgeNameEditPart) {
        if (dEdgeNameEditPart.getParent() instanceof AbstractConnectionEditPart) {
            PointList ptList = ((AbstractConnectionEditPart) dEdgeNameEditPart.getParent()).getConnectionFigure().getPoints();
            return PointListUtilities.calculatePointRelativeToLine(ptList, 0, getLocation(dEdgeNameEditPart), true);
        } else {
            return ((GraphicalEditPart) dEdgeNameEditPart.getParent()).getFigure().getBounds().getLocation();
        }
    }

    /**
     * Get the location among {@link LabelViewConstants} constants where to relocate the label figure. Method copied
     * from {@link org.eclipse.sirius.diagram.ui.graphical.edit.policies.ResizableShapeLabelEditPolicy#getLocation()}.
     *
     * @return the location among {@link LabelViewConstants} constants
     */
    private int getLocation(AbstractDEdgeNameEditPart dEdgeNameEditPart) {
        int location = LabelViewConstants.MIDDLE_LOCATION;
        switch (dEdgeNameEditPart.getKeyPoint()) {
        case ConnectionLocator.SOURCE:
            location = LabelViewConstants.TARGET_LOCATION;
            break;
        case ConnectionLocator.TARGET:
            location = LabelViewConstants.SOURCE_LOCATION;
            break;
        case ConnectionLocator.MIDDLE:
            location = LabelViewConstants.MIDDLE_LOCATION;
            break;
        default:
            location = LabelViewConstants.MIDDLE_LOCATION;
            break;
        }
        return location;
    }

    @Override
    public boolean canUndo() {
        if (wrappedCommand != null && wrappedCommand.size() > 0) {
            return wrappedCommand.canUndo();
        }
        return true;
    }

    @Override
    public boolean canRedo() {
        if (wrappedCommand != null && wrappedCommand.size() > 0) {
            return wrappedCommand.canRedo();
        }
        return true;
    }

    @Override
    public void dispose() {
        editPartsToDistribute = null;
        wrappedCommand = null;
    }
}
