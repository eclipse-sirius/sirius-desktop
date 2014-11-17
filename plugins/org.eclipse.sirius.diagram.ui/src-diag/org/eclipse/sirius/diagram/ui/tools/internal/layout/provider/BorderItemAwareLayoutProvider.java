/*******************************************************************************
 * Copyright (c) 2010, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.layout.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Vector;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNode;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.ILayoutNodeOperation;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.business.internal.query.DNodeContainerExperimentalQuery;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.RegionContainerUpdateLayoutOperation;
import org.eclipse.sirius.diagram.ui.internal.refresh.borderednode.CanonicalDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.AbstractLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.policies.ChangeBoundRequestRecorder;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.ArrangeAllWithAutoSize;
import org.eclipse.sirius.diagram.ui.tools.internal.part.SiriusDiagramGraphicalViewer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Layout provider that arranges all border items after another layout provider
 * (<code>initialLayoutProvider</code>) that arranges all the nodes (with
 * ChangeBoundsCommand).
 * 
 * The layout is made with several iterations. During each iterations, we store
 * the port center location of this iteration and the previous one. We compare
 * the current location with the two previous iteration to know if the port has
 * been moved. If no port is moved, we stop the arrange process.
 * 
 * @author ymortier
 * @author lredor
 */
public class BorderItemAwareLayoutProvider extends AbstractLayoutProvider {

    /**
     * Class to store the data of the previous iteration of the layout.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     * 
     */
    private static class BorderItemLayoutData {
        /**
         * The center location of the border item at the previous iteration.
         */
        Point previousCenterLocation;

        /**
         * The center location of the border item two iterations ago.
         */
        Point previousPreviousCenterLocation;

        /**
         * True if the border item is considered has moved, false otherwise.
         */
        boolean isMoved;

        protected Point getPreviousPreviousCenterLocation() {
            return previousPreviousCenterLocation;
        }

        protected void setPreviousPreviousCenterLocation(Point previousPreviousCenterLocation) {
            this.previousPreviousCenterLocation = previousPreviousCenterLocation;
        }

        protected Point getPreviousCenterLocation() {
            return previousCenterLocation;
        }

        protected void setPreviousCenterLocation(Point newCenterLocation) {
            setPreviousPreviousCenterLocation(getPreviousCenterLocation());
            this.previousCenterLocation = newCenterLocation;
        }

        protected boolean isMoved() {
            return isMoved;
        }

        protected void setMoved(boolean moved) {
            this.isMoved = moved;
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            String result = "";
            if (isMoved) {
                result += "Border item has been moved since previous layout.";
            } else {
                result += "Border item has not been moved since previous layout.";
            }
            result += " " + getPreviousCenterLocation();
            return result;
        }
    }

    /**
     * Class that store data about the element that is on the other side of the
     * border node.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static class BorderItemOppositeElementData {
        /**
         * The center of the opposite element.
         */
        Point center;

        /**
         * The location of this element on its container if it's a border node
         * (PositionConstants). Otherwise, this field equals
         * PositionConstants.NONE
         */
        int side;

        public BorderItemOppositeElementData(Point centerPoint) {
            this(centerPoint, PositionConstants.NONE);
        }

        public BorderItemOppositeElementData(Point centerPoint, int side) {
            center = centerPoint;
            this.side = side;
        }
    }

    /**
     * An abstract comparator for all comparators managed by the opposite
     * element of the bordered nodes.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private abstract class AbstractCoordinateComparator implements Comparator<IBorderItemEditPart> {

        /**
         * Gives an OppositeElementData for each border item.
         */
        Map<IBorderItemEditPart, BorderItemOppositeElementData> oppositeElementsDataByEditPart;

        /**
         * Default constructor.
         * 
         * @param oppositeElementsDataByEditPart
         */
        public AbstractCoordinateComparator(final Map<IBorderItemEditPart, BorderItemOppositeElementData> oppositeElementsDataByEditPart) {
            this.oppositeElementsDataByEditPart = oppositeElementsDataByEditPart;
        }
    }

    /**
     * A comparator for border item on the east side of its parent.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private class EastCoordinateComparator extends AbstractCoordinateComparator {
        /**
         * Default constructor.
         * 
         * @param vectorsByEditPart
         */
        public EastCoordinateComparator(final Map<IBorderItemEditPart, BorderItemOppositeElementData> oppositeElementsDataByEditPart) {
            super(oppositeElementsDataByEditPart);
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final IBorderItemEditPart o1, final IBorderItemEditPart o2) {
            int result = 0;
            final BorderItemOppositeElementData p1 = oppositeElementsDataByEditPart.get(o1);
            final BorderItemOppositeElementData p2 = oppositeElementsDataByEditPart.get(o2);

            if (p1.center.y == p2.center.y) {
                if (p1.side == PositionConstants.NORTH) {
                    result = p1.center.x < p2.center.x ? 1 : -1;
                } else {
                    result = p1.center.x > p2.center.x ? 1 : -1;
                }
            } else {
                result = p1.center.y > p2.center.y ? 1 : -1;
            }
            return result;
        }
    }

    /**
     * A comparator for border item on the south side of its parent.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private class SouthCoordinateComparator extends AbstractCoordinateComparator {
        /**
         * Default constructor.
         * 
         * @param vectorsByEditPart
         */
        public SouthCoordinateComparator(final Map<IBorderItemEditPart, BorderItemOppositeElementData> targetPointsByEditPart) {
            super(targetPointsByEditPart);
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final IBorderItemEditPart o1, final IBorderItemEditPart o2) {
            int result = 0;
            final BorderItemOppositeElementData p1 = oppositeElementsDataByEditPart.get(o1);
            final BorderItemOppositeElementData p2 = oppositeElementsDataByEditPart.get(o2);

            if (p1.center.x == p2.center.x) {
                if (p1.side == PositionConstants.WEST) {
                    result = p1.center.y < p2.center.y ? 1 : -1;
                } else {
                    result = p1.center.y > p2.center.y ? 1 : -1;
                }
            } else {
                result = p1.center.x > p2.center.x ? 1 : -1;
            }
            return result;
        }
    }

    /**
     * A comparator for border item on the west side of its parent.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private class WestCoordinateComparator extends AbstractCoordinateComparator {
        /**
         * Default constructor.
         * 
         * @param vectorsByEditPart
         */
        public WestCoordinateComparator(final Map<IBorderItemEditPart, BorderItemOppositeElementData> targetPointsByEditPart) {
            super(targetPointsByEditPart);
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final IBorderItemEditPart o1, final IBorderItemEditPart o2) {
            int result = 0;
            final BorderItemOppositeElementData p1 = oppositeElementsDataByEditPart.get(o1);
            final BorderItemOppositeElementData p2 = oppositeElementsDataByEditPart.get(o2);

            if (p1.center.y == p2.center.y) {
                if (p1.side == PositionConstants.NORTH) {
                    result = p1.center.x > p2.center.x ? 1 : -1;
                } else {
                    result = p1.center.x < p2.center.x ? 1 : -1;
                }
            } else {
                result = p1.center.y > p2.center.y ? 1 : -1;
            }
            return result;
        }
    }

    /**
     * A comparator for border item on the north side of its parent.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private class NorthCoordinateComparator extends AbstractCoordinateComparator {
        /**
         * Default constructor.
         * 
         * @param vectorsByEditPart
         */
        public NorthCoordinateComparator(final Map<IBorderItemEditPart, BorderItemOppositeElementData> targetPointsByEditPart) {
            super(targetPointsByEditPart);
        }

        /**
         * {@inheritDoc}
         * 
         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
         */
        @Override
        public int compare(final IBorderItemEditPart o1, final IBorderItemEditPart o2) {
            int result = 0;
            final BorderItemOppositeElementData p1 = oppositeElementsDataByEditPart.get(o1);
            final BorderItemOppositeElementData p2 = oppositeElementsDataByEditPart.get(o2);

            if (p1.center.x == p2.center.x) {
                if (p1.side == PositionConstants.WEST) {
                    result = p1.center.y > p2.center.y ? 1 : -1;
                } else {
                    result = p1.center.y < p2.center.y ? 1 : -1;
                }
            } else {
                result = p1.center.x > p2.center.x ? 1 : -1;
            }
            return result;
        }
    }

    /**
     * The margin of the bordered nodes :
     * <UL>
     * <LI>half on the right and half on the left</LI>
     * <LI>or half on the top and half on the bottom.</LI>
     * </UL>
     */
    public static final int MARGIN = 16;

    private static final int MAX_ITERATIONS = 10;

    /**
     * The initial layout provider that arrange the nodes (launch before the
     * arrange of bordered nodes).
     */
    AbstractLayoutProvider initialLayoutProvider;

    /**
     * Tell if the normal arrange process will be called before the border item
     * arrange.
     */
    boolean launchNormalArrange;

    /**
     * Stores the location of each border edit part compute during the previous
     * iteration.
     */
    Map<IBorderItemEditPart, BorderItemLayoutData> previousIterationDatasbyEditPart = new HashMap<IBorderItemEditPart, BorderItemLayoutData>();

    private Predicate<Object> validateAllElementInArrayListAreIDiagramElementEditPart = new Predicate<Object>() {

        @Override
        public boolean apply(Object input) {
            return input instanceof IDiagramElementEditPart;
        }
    };

    /**
     * The default constructor.
     * 
     * @param clp
     *            The layout provider to call before calling the layout of the
     *            border items.
     */
    public BorderItemAwareLayoutProvider(final AbstractLayoutProvider clp) {
        initialLayoutProvider = clp;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider#layoutEditParts(java.util.List,
     *      org.eclipse.core.runtime.IAdaptable)
     */
    @Override
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint) {
        return layoutEditParts(selectedObjects, layoutHint, true);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.business.api.layout.provider.AbstractLayoutProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    @Override
    public boolean provides(final IOperation operation) {
        boolean result = true;
        if (operation instanceof ILayoutNodeOperation) {
            final ILayoutNodeOperation layoutNodeOperation = (ILayoutNodeOperation) operation;
            for (ILayoutNode layoutNode : (Iterable<ILayoutNode>) layoutNodeOperation.getLayoutNodes()) {
                final Node node = layoutNode.getNode();
                final EObject semanticElement = ViewUtil.resolveSemanticElement(node);
                if (semanticElement instanceof DDiagramElement) {
                    final DDiagram diagram = ((DDiagramElement) semanticElement).getParentDiagram();
                    if (diagram.getDescription().getLayout() != null) {
                        result = false;
                    }
                } else if (!(semanticElement instanceof DSemanticDecorator)) {
                    result = false;
                }
            }
        }
        return result;
    }

    /**
     * Layout this list of selected objects, using the specified layout hint.
     * The selected objects all reside within the same parent container. Other
     * elements that are part of the container but not specified in the list of
     * objects, are ignored.
     * 
     * @param selectedObjects
     *            <code>List</code> of <code>EditPart</code> objects that are to
     *            be layed out.
     * @param layoutHint
     *            <code>IAdaptable</code> hint to the provider to determine the
     *            layout kind.
     * @param normalArrangeMustBeCalled
     *            Tell if the normal arrange process must be called before the
     *            border item arrange
     * @return <code>Command</code> that when executed will layout the edit
     *         parts in the container
     */
    public Command layoutEditParts(final List selectedObjects, final IAdaptable layoutHint, final boolean normalArrangeMustBeCalled) {
        this.launchNormalArrange = normalArrangeMustBeCalled;

        if (selectedObjects.isEmpty()) {
            return UnexecutableCommand.INSTANCE;
        }

        CompoundCommand result = new CompoundCommand();

        if (launchNormalArrange) {
            // Create a request recorder to record all ChangeBounds requests by
            // editparts.
            final EditPartViewer root = ((EditPart) selectedObjects.get(0)).getViewer();
            if (root instanceof SiriusDiagramGraphicalViewer) {
                final ChangeBoundRequestRecorder recorder = ((SiriusDiagramGraphicalViewer) root).getChangeBoundRequestRecorder();
                recorder.startRecording();
                result.add(lauchPrimaryArrangeAll(selectedObjects, layoutHint));
                recorder.stopRecording();
                registerChangeBoundsCommand(recorder);
                recorder.dispose();
            }
        }

        // Finds if there are unpinned diagram elements to keep fixed stored in
        // the LayoutHint as a Collection
        ArrayList<IDiagramElementEditPart> elementsToKeepFixed = Lists.newArrayList();
        if (layoutHint.getAdapter(Collection.class) instanceof ArrayList<?>
                && Iterables.all((ArrayList<?>) layoutHint.getAdapter(Collection.class), validateAllElementInArrayListAreIDiagramElementEditPart)) {
            elementsToKeepFixed = (ArrayList<IDiagramElementEditPart>) layoutHint.getAdapter(Collection.class);
        }

        // Create the specific command to layout the border items.
        final Command layoutBorderItems = layoutBorderItems(selectedObjects, 1, elementsToKeepFixed);
        if (layoutBorderItems != null && layoutBorderItems.canExecute()) {
            result.add(layoutBorderItems);
        }

        resetBoundsOfPinnedElements(selectedObjects, result, elementsToKeepFixed);
        this.getViewsToChangeBoundsRequest().clear();
        if (result.size() == 0) {
            result = null; // removeCommandsForPinnedElements(result);
        }
        return result;
    }

    /**
     * Launches the primary arrange all that arrange all nodes.
     * 
     * @param selectedObjects
     *            the objects to arrange.
     * @param layoutHint
     *            the layout hint.
     * @return the arrange command.
     */
    protected Command lauchPrimaryArrangeAll(final List selectedObjects, final IAdaptable layoutHint) {
        return initialLayoutProvider.layoutEditParts(selectedObjects, layoutHint);
    }

    /**
     * Register all the change bounds command recording during the initial
     * layout (layout without moving ports).
     * 
     * @param recorder
     *            The request recorder
     */
    protected void registerChangeBoundsCommand(final ChangeBoundRequestRecorder recorder) {
        for (Entry<EditPart, ChangeBoundsRequest> entry : recorder.getAllRequests().entries()) {
            final EditPart editPart = entry.getKey();
            if (editPart instanceof IGraphicalEditPart) {
                ChangeBoundsRequest cbr = entry.getValue();
                final List<EditPart> editParts = cbr.getEditParts();
                if (editParts != null) {
                    for (EditPart ep : editParts) {
                        final View v = ((IGraphicalEditPart) ep).getNotationView();
                        List<Request> requests = this.getViewsToChangeBoundsRequest().get(v);
                        if (requests == null) {
                            requests = new LinkedList<Request>();
                            this.getViewsToChangeBoundsRequest().put(v, requests);
                        }
                        requests.add(cbr);
                    }
                }
            }
        }
    }

    /**
     * Reset the size and location of the pinned elements to there values they
     * have before the arrange process.
     * 
     * @param selectedObjects
     *            The selected elements
     * @param compoundCommand
     *            Contains all the commands to execute at the end of the layout.
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have
     *            to stay fixed
     */
    private void resetBoundsOfPinnedElements(final List selectedObjects, final CompoundCommand compoundCommand, ArrayList<IDiagramElementEditPart> elementsToKeepFixed) {
        final String commandName = "Set Bounds";
        for (IGraphicalEditPart graphicalEditPart : Iterables.filter(selectedObjects, IGraphicalEditPart.class)) {
            EObject semanticElement = graphicalEditPart.resolveSemanticElement();
            if (semanticElement instanceof DDiagramElement) {
                DDiagramElement dDiagramElement = (DDiagramElement) semanticElement;
                if (new PinHelper().isPinned(dDiagramElement) || (elementsToKeepFixed != null && elementsToKeepFixed.contains(graphicalEditPart))) {
                    final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(semanticElement);
                    View notationView = graphicalEditPart.getNotationView();
                    if (notationView instanceof Node) {
                        final Node node = (Node) notationView;
                        resetBounds(compoundCommand, commandName, node, editingDomain);
                    }

                    // Keep the GMF model consistent for pinned RegionContainer
                    // and Regions
                    if (graphicalEditPart instanceof IDiagramContainerEditPart && dDiagramElement instanceof DNodeContainer
                            && new DNodeContainerExperimentalQuery((DNodeContainer) dDiagramElement).isRegionContainer()) {
                        AbstractDNodeContainerCompartmentEditPart comp = Iterables.getFirst(Iterables.filter(graphicalEditPart.getChildren(), AbstractDNodeContainerCompartmentEditPart.class), null);
                        if (comp != null && comp.getNotationView() != null) {
                            for (Node region : Iterables.filter(comp.getChildren(), Node.class)) {
                                resetBounds(compoundCommand, commandName, region, editingDomain);
                            }
                        }
                        compoundCommand.add(
                                new ICommandProxy(CommandFactory.createICommand(graphicalEditPart.getEditingDomain(), new RegionContainerUpdateLayoutOperation((Node) graphicalEditPart.getModel()))));
                    }
                }
            }
        }
    }

    private void resetBounds(final CompoundCommand compoundCommand, final String commandName, Node node, TransactionalEditingDomain editingDomain) {
        final EObjectAdapter objectAdapter = new EObjectAdapter(node);

        final LayoutConstraint layoutConstraint = node.getLayoutConstraint();
        if (layoutConstraint instanceof Bounds) {
            final Bounds bounds = (Bounds) layoutConstraint;
            final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(editingDomain, commandName, objectAdapter,
                    new Rectangle(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight()));
            compoundCommand.add(new ICommandProxy(setBoundsCommand));
        } else if (layoutConstraint instanceof Location) {
            final Location location = (Location) layoutConstraint;
            final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(editingDomain, commandName, objectAdapter, new Point(location.getX(), location.getY()));
            compoundCommand.add(new ICommandProxy(setBoundsCommand));
        } else if (layoutConstraint instanceof Size) {
            final Size size = (Size) layoutConstraint;
            final SetBoundsCommand setBoundsCommand = new SetBoundsCommand(editingDomain, commandName, objectAdapter, new Dimension(size.getWidth(), size.getHeight()));
            compoundCommand.add(new ICommandProxy(setBoundsCommand));
        }
    }

    /**
     * Method getBendpointsChangedCommand Different signature method that allows
     * a command to constructed for changing the bendpoints without requiring
     * the original Request.
     * 
     * @param connection
     *            Connection to generate the bendpoints changed command from
     * @param edge
     *            notation element that the command will operate on.
     * @param editingDomain
     *            the concern editing domain
     * @return Command SetBendpointsCommand that contains the point changes for
     *         the connection.
     */
    protected Command getBendpointsChangedCommand(Connection connection, Edge edge, TransactionalEditingDomain editingDomain) {
        Point ptRef1 = connection.getSourceAnchor().getReferencePoint();
        connection.translateToRelative(ptRef1);

        Point ptRef2 = connection.getTargetAnchor().getReferencePoint();
        connection.translateToRelative(ptRef2);

        SetConnectionBendpointsCommand sbbCommand = new SetConnectionBendpointsCommand(editingDomain);
        sbbCommand.setEdgeAdapter(new EObjectAdapter(edge));
        sbbCommand.setNewPointList(connection.getPoints(), ptRef1, ptRef2);

        return new ICommandProxy(sbbCommand);
    }

    /**
     * Layout all the border items of the selected elements.
     * 
     * @param selectedObjects
     *            The selected elements
     * @param nbIterations
     *            Number of made iterations. This number corresponds to the
     *            current iteration.
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have
     *            to stay fixed
     * @return The command to execute to layout the border items.
     */
    private Command layoutBorderItems(final List<?> selectedObjects, final int nbIterations, ArrayList<IDiagramElementEditPart> elementsToKeepFixed) {
        CompoundCommand cc = new CompoundCommand();
        for (Object object : selectedObjects) {
            if (object instanceof GraphicalEditPart) {
                final Command layoutBorderItems = layoutBorderItems((GraphicalEditPart) object, elementsToKeepFixed);
                if (layoutBorderItems != null && layoutBorderItems.canExecute()) {
                    cc.add(layoutBorderItems);
                }
            }
        }
        if (hasBeenMovedBorderItemsDuringLastIteration() && nbIterations < MAX_ITERATIONS) {
            // Remove all the request not called (because we compute again best
            // locations)
            removeRequestsOfThisCommand(cc);
            // We try to optimize the border items location with the previous
            // compute locations (record in
            cc = (CompoundCommand) layoutBorderItems(selectedObjects, nbIterations + 1, elementsToKeepFixed);
        }
        clearBorderItemLocations();
        return cc;
    }

    /**
     * Remove the request corresponding to this command of the map that maps all
     * views with a its associated {@link ChangeBoundsRequest}.
     * 
     * @param cc
     *            The compoundCommand that is not executed and for which we
     *            wan't to remove the corresponding request.
     */
    private void removeRequestsOfThisCommand(CompoundCommand cc) {
        for (Object childCommand : cc.getCommands()) {
            if (childCommand instanceof CompoundCommand) {
                removeRequestsOfThisCommand((CompoundCommand) childCommand);
            } else if (childCommand instanceof CommandWrapper) {
                CommandWrapper wrap = (CommandWrapper) childCommand;
                if (wrap.getEditPart() instanceof IGraphicalEditPart) {
                    List<Request> requests = getViewsToChangeBoundsRequest().get(((IGraphicalEditPart) wrap.getEditPart()).getNotationView());
                    if (requests != null) {
                        requests.remove(wrap.getRequest());
                    }
                }
            }
        }
    }

    /**
     * @return true if almost one of the border items has been moved during the
     *         last iteration, false otherwise.
     */
    private boolean hasBeenMovedBorderItemsDuringLastIteration() {
        for (IBorderItemEditPart borderItemEditPart : previousIterationDatasbyEditPart.keySet()) {
            if (previousIterationDatasbyEditPart.get(borderItemEditPart).isMoved()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Clear the data of border items of the previous iterations.
     */
    private void clearBorderItemLocations() {
        previousIterationDatasbyEditPart.clear();
    }

    /**
     * Layout all the border items of this graphicalEditPart.
     * 
     * @param graphicalEditPart
     *            The current element to deal with
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have
     *            to stay fixed
     * @return The command to execute to layout the border items of this
     *         graphical edit part.
     */
    private Command layoutBorderItems(final GraphicalEditPart graphicalEditPart, ArrayList<IDiagramElementEditPart> elementsToKeepFixed) {
        final CompoundCommand result = new CompoundCommand();
        if (graphicalEditPart instanceof IBorderedShapeEditPart) {
            final IBorderedShapeEditPart borderedEditPart = (IBorderedShapeEditPart) graphicalEditPart;
            if (borderedEditPart.getBorderedFigure() != null && !borderedEditPart.getBorderedFigure().getBorderItemContainer().getChildren().isEmpty()) {
                final Command layoutBorderItems = layoutBorderItems(borderedEditPart, elementsToKeepFixed);
                if (layoutBorderItems != null && layoutBorderItems.canExecute()) {
                    result.add(layoutBorderItems);
                }
            }

        }
        for (Object editPart : graphicalEditPart.getChildren()) {
            if (editPart instanceof GraphicalEditPart) {
                final Command layoutBorderItems = layoutBorderItems((GraphicalEditPart) editPart, elementsToKeepFixed);
                if (layoutBorderItems != null && layoutBorderItems.canExecute()) {
                    result.add(layoutBorderItems);
                }
            }
        }
        return result;
    }

    /**
     * Layout all the border items of this borderedShapeEditPart.
     * 
     * @param borderedShapeEditPart
     *            The current element to deal with
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have
     *            to stay fixed
     * @return The command to execute to layout the border items of this
     *         graphical edit part.
     */
    private Command layoutBorderItems(final IBorderedShapeEditPart borderedShapeEditPart, ArrayList<IDiagramElementEditPart> elementsToKeepFixed) {
        CompoundCommand resCommand = null;
        if (borderedShapeEditPart instanceof IGraphicalEditPart) {

            IGraphicalEditPart castedEditPart = (IGraphicalEditPart) borderedShapeEditPart;
            if (borderedShapeEditPart.getMainFigure() != null) {
                resCommand = new CompoundCommand();

                // Get the zoom level
                double scale = 1.0;
                if (castedEditPart.getRoot() instanceof DiagramRootEditPart) {
                    final ZoomManager zoomManager = ((DiagramRootEditPart) castedEditPart.getRoot()).getZoomManager();
                    scale = zoomManager.getZoom();
                }

                // Get the bounds of the container after arrange all (the
                // container of the border node).
                final Rectangle containerBoundsAfterArrangeAll = getBounds(castedEditPart, scale);
                final Point containerCenterAfterArrangeAll = containerBoundsAfterArrangeAll.getCenter();

                // Use the center of the opposite element to determine the ray
                // (this ray is then use to determine the side on which to put
                // the border node). Only the borderItemEditPart with only one
                // edge are put in the headings map.
                final Map<IBorderItemEditPart, Vector> headings = new HashMap<IBorderItemEditPart, Vector>();
                for (Object child : castedEditPart.getChildren()) {
                    if (child instanceof IBorderItemEditPart) {
                        if (!isPinned((IBorderItemEditPart) child) && !(elementsToKeepFixed != null && elementsToKeepFixed.contains(child))) {
                            computeHeading((IBorderItemEditPart) child, containerCenterAfterArrangeAll, scale, headings);
                        }
                    }
                }

                final Point topLeft = containerBoundsAfterArrangeAll.getTopLeft();
                // Make some trigonometry calculations to know which ports must
                // be on top, bottom, right, left border of their container.
                final double absoluteCos = Math
                        .abs(BorderItemAwareLayoutProvider.cos(new Vector(Math.abs(containerCenterAfterArrangeAll.x - topLeft.x), Math.abs(containerCenterAfterArrangeAll.y - topLeft.y))));
                final double absoluteSin = Math
                        .abs(BorderItemAwareLayoutProvider.sin(new Vector(Math.abs(containerCenterAfterArrangeAll.x - topLeft.x), Math.abs(containerCenterAfterArrangeAll.y - topLeft.y))));

                final List<IBorderItemEditPart> tops = getBorderItems(PositionConstants.NORTH, headings, absoluteCos, scale, containerCenterAfterArrangeAll);
                final List<IBorderItemEditPart> bottoms = getBorderItems(PositionConstants.SOUTH, headings, absoluteCos, scale, containerCenterAfterArrangeAll);
                final List<IBorderItemEditPart> rights = getBorderItems(PositionConstants.EAST, headings, absoluteSin, scale, containerCenterAfterArrangeAll);
                final List<IBorderItemEditPart> lefts = getBorderItems(PositionConstants.WEST, headings, absoluteSin, scale, containerCenterAfterArrangeAll);

                unfixLocator(tops);
                unfixLocator(bottoms);
                unfixLocator(rights);
                unfixLocator(lefts);

                final Command topCommand = layoutItems(tops, containerBoundsAfterArrangeAll, PositionConstants.NORTH, scale);
                if (topCommand != null && topCommand.canExecute()) {
                    resCommand.add(topCommand);
                }
                final Command bottomCommand = layoutItems(bottoms, containerBoundsAfterArrangeAll, PositionConstants.SOUTH, scale);
                if (bottomCommand != null && bottomCommand.canExecute()) {
                    resCommand.add(bottomCommand);
                }
                final Command rightCommand = layoutItems(rights, containerBoundsAfterArrangeAll, PositionConstants.EAST, scale);
                if (rightCommand != null && rightCommand.canExecute()) {
                    resCommand.add(rightCommand);
                }
                final Command leftCommand = layoutItems(lefts, containerBoundsAfterArrangeAll, PositionConstants.WEST, scale);
                if (leftCommand != null && leftCommand.canExecute()) {
                    resCommand.add(leftCommand);
                }
                for (IBorderItemEditPart topBorderItemEditPart : tops) {
                    if (topBorderItemEditPart.getSourceConnections().size() > 0) {
                        final Object connection = topBorderItemEditPart.getSourceConnections().get(0);
                        if (connection instanceof DEdgeEditPart) {
                            final DEdgeEditPart viewEdgeEditPart = (DEdgeEditPart) connection;
                            viewEdgeEditPart.getPrimaryShape().refreshLine();
                        }
                    } else if (topBorderItemEditPart.getTargetConnections().size() > 0) {
                        final Object connection = topBorderItemEditPart.getTargetConnections().get(0);
                        if (connection instanceof DEdgeEditPart) {
                            final DEdgeEditPart viewEdgeEditPart = (DEdgeEditPart) connection;
                            viewEdgeEditPart.refresh();
                        }
                    }
                }
            }
        }
        return resCommand;
    }

    private void computeHeading(IBorderItemEditPart borderItemEditPart, Point containerCenterAfterArrangeAll, double scale, Map<IBorderItemEditPart, Vector> headings) {
        final Vector heading = getHeading(borderItemEditPart, containerCenterAfterArrangeAll, scale);
        if (heading != null) {
            headings.put(borderItemEditPart, heading);
        }
    }

    /**
     * Layout all the border items on the location (N, S, E or W).
     * 
     * @param items
     *            A list of border items to layout
     * @param containerBounds
     *            The bounds of the container after arrange all
     * @param position
     *            The position of items on its container. Possible values can be
     *            found in {@link PositionConstants} and include NORTH, SOUTH,
     *            EAST and WEST.
     * @param zoomScale
     *            The scale of the diagram
     * @return Command A command to layout all items.
     */
    protected Command layoutItems(final List<IBorderItemEditPart> items, final Rectangle containerBounds, final int position, final double zoomScale) {

        final CompoundCommand res = new CompoundCommand();

        final boolean width = position == PositionConstants.NORTH || position == PositionConstants.SOUTH;

        final int availableSpace = width ? containerBounds.width : containerBounds.height;

        final int between = (int) ((availableSpace - getSize(items, width, zoomScale) - MARGIN) / (float) items.size());

        int current = MARGIN / 2;

        for (IBorderItemEditPart borderItemEditPart : items) {
            final ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_MOVE);
            Point newLocation;
            switch (position) {
            case PositionConstants.NORTH:
                newLocation = new Point(current, 0);
                break;
            case PositionConstants.SOUTH:
                newLocation = new Point(current, containerBounds.height);
                break;
            case PositionConstants.EAST:
                newLocation = new Point(containerBounds.width, current);
                break;
            case PositionConstants.WEST:
                newLocation = new Point(0, current);
                break;
            default:
                throw new IllegalArgumentException("Invalid items position.");
            }

            newLocation = newLocation.getTranslated(containerBounds.getTopLeft());

            // Make the location more precise with the
            // CanonicalDBorderItemLocator
            if (borderItemEditPart.getModel() instanceof Node && ((Node) borderItemEditPart.getModel()).eContainer() instanceof Node) {
                Node borderNode = (Node) borderItemEditPart.getModel();
                Node parentNode = (Node) borderNode.eContainer();
                CanonicalDBorderItemLocator borderItemLocator = new CanonicalDBorderItemLocator(parentNode, position);
                borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                borderItemLocator.setParentBorderBounds(containerBounds);
                newLocation = borderItemLocator.getValidLocation(new Rectangle(newLocation, borderItemEditPart.getFigure().getSize()), (Node) borderItemEditPart.getModel(), parentNode.getChildren());
            }

            // Store the location compute for this border item during this
            // iteration
            addBorderItemData(borderItemEditPart, newLocation);

            request.setEditParts(borderItemEditPart);
            request.setLocation(newLocation);
            final Rectangle boundsBorderItem = getBounds(borderItemEditPart, zoomScale);
            final Dimension difference = newLocation.getDifference(boundsBorderItem.getTopLeft());
            request.setMoveDelta(new Point(difference.width, difference.height));
            final Command command = this.buildCommandWrapper(request, borderItemEditPart);

            res.add(command);

            current += between + (int) ((width ? boundsBorderItem.width : boundsBorderItem.height) * zoomScale);

        }
        return res;
    }

    /**
     * Store the location compute for this border item during this iteration.
     * 
     * @param borderItemEditPart
     *            The concerned border item
     * @param newLocation
     *            The new location computed during the last iteration
     */
    private void addBorderItemData(IBorderItemEditPart borderItemEditPart, Point newLocation) {
        BorderItemLayoutData data = previousIterationDatasbyEditPart.get(borderItemEditPart);
        if (data == null) {
            data = new BorderItemLayoutData();
            data.setMoved(true);
            data.setPreviousCenterLocation(newLocation);
        } else {
            boolean alreadyUsedLocation = data.getPreviousCenterLocation().equals(newLocation);
            if (!alreadyUsedLocation) {
                alreadyUsedLocation = data.getPreviousPreviousCenterLocation() != null && data.getPreviousPreviousCenterLocation().equals(newLocation);
            }
            data.setPreviousCenterLocation(newLocation);
            if (alreadyUsedLocation) {
                data.setMoved(false);
            } else {
                data.setMoved(true);
            }
        }
        previousIterationDatasbyEditPart.put(borderItemEditPart, data);
    }

    /**
     * Consider all the border item locators with free location (ie the location
     * of the border item will be recomputed).
     * 
     * @param editParts
     *            The border items to reset
     */
    private void unfixLocator(final List<IBorderItemEditPart> editParts) {
        for (IBorderItemEditPart borderItemEditPart : editParts) {
            final IBorderItemLocator borderItemLocator = borderItemEditPart.getBorderItemLocator();
            if (borderItemLocator instanceof DBorderItemLocator) {
                ((DBorderItemLocator) borderItemLocator).unfix();
            }
        }
    }

    private int getSize(final List<IBorderItemEditPart> tops, final boolean width, final double zoomScale) {
        int size = 0;

        for (IBorderItemEditPart editPart : tops) {
            int editPartSize = (int) (((GraphicalEditPart) editPart).getFigure().getBounds().width * zoomScale);
            if (!width) {
                editPartSize = (int) (((GraphicalEditPart) editPart).getFigure().getBounds().height * zoomScale);
            }
            size += editPartSize;
        }
        return size;
    }

    /**
     * Compute the cosinus of a vector.
     * 
     * @param ray
     *            The vector
     * @return The cosinus value of the vector
     */
    private static double cos(final Vector ray) {
        return ray.x / Math.sqrt(Math.pow(ray.x, 2) + Math.pow(ray.y, 2));
    }

    /**
     * Compute the sinus of a vector.
     * 
     * @param ray
     *            The vector
     * @return The sinus value of the vector
     */
    private static double sin(final Vector ray) {
        return ray.y / Math.sqrt(Math.pow(ray.x, 2) + Math.pow(ray.y, 2));
    }

    /**
     * 
     * @param parts
     * @param scale
     * @return
     */
    private Map<IBorderItemEditPart, BorderItemOppositeElementData> getOppositeElementsData(List<IBorderItemEditPart> parts, double scale) {
        final Map<IBorderItemEditPart, BorderItemOppositeElementData> targetPoints = new HashMap<IBorderItemEditPart, BorderItemOppositeElementData>();
        for (IBorderItemEditPart borderItemEditPart : parts) {
            final BorderItemOppositeElementData oppositeElementData = getOppositeElementData(borderItemEditPart, scale);
            if (oppositeElementData != null) {
                targetPoints.put(borderItemEditPart, oppositeElementData);
            }
        }
        return targetPoints;
    }

    /**
     * Returns the list of border items to be at the <code>side</code> position.
     * These border items are sorted. To sort these element we compute again the
     * rays with a new distant point to be more precise on the order.
     * 
     * @param side
     *            the side to retrieve border nodes on (PositionConstants.NORTH,
     *            PositionConstants.SOUTH, PositionConstants.WEST or
     *            PositionConstants.EAST).
     * @param headings
     *            List of vector by border items
     * @param containerAbsoluteCosOrSin
     *            The absolute cos of the container if location is NORTH or
     *            SOUTH and sin of the container if location is WEST or EAST.
     * @param containerCenter
     *            the center of the container of the border items
     * @return the list of border items to be at the <code>side</code> position.
     */
    private List<IBorderItemEditPart> getBorderItems(int side, final Map<IBorderItemEditPart, Vector> headings, final double containerAbsoluteCosOrSin, double scale, final Point containerCenter) {

        final List<IBorderItemEditPart> parts = new LinkedList<IBorderItemEditPart>();

        for (Map.Entry<IBorderItemEditPart, Vector> entry : headings.entrySet()) {
            final Vector ray = entry.getValue();
            if (side == PositionConstants.NORTH || side == PositionConstants.SOUTH) {
                if (isOnNorthOrSouth(side, containerAbsoluteCosOrSin, entry, ray)) {
                    parts.add(entry.getKey());
                }
            } else if (isOnWestOrEast(side, containerAbsoluteCosOrSin, entry, ray)) {
                parts.add(entry.getKey());
            }
        }

        if (!parts.isEmpty()) {
            final Map<IBorderItemEditPart, BorderItemOppositeElementData> oppositeElementsDataByEditPart = getOppositeElementsData(parts, scale);
            if (side == PositionConstants.NORTH) {
                Collections.sort(parts, new NorthCoordinateComparator(oppositeElementsDataByEditPart));
            } else if (side == PositionConstants.SOUTH) {
                Collections.sort(parts, new SouthCoordinateComparator(oppositeElementsDataByEditPart));
            } else if (side == PositionConstants.WEST) {
                Collections.sort(parts, new WestCoordinateComparator(oppositeElementsDataByEditPart));
            } else if (side == PositionConstants.EAST) {
                Collections.sort(parts, new EastCoordinateComparator(oppositeElementsDataByEditPart));
            }

        }

        return parts;

    }

    private boolean isOnNorthOrSouth(int side, final double containerAbsoluteCos, Map.Entry<IBorderItemEditPart, Vector> entry, final Vector ray) {
        boolean result = false;
        if (ray.y != 0) {
            final double cos = BorderItemAwareLayoutProvider.cos(ray);

            if (Math.abs(cos) < containerAbsoluteCos) {
                if (side == PositionConstants.NORTH) {
                    if (ray.y < 0) {
                        result = true;
                    }
                } else if (ray.y > 0) {
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean isOnWestOrEast(int side, final double containerAbsoluteSin, Map.Entry<IBorderItemEditPart, Vector> entry, final Vector ray) {
        boolean result = false;
        if (ray.x != 0) {
            final double sin = BorderItemAwareLayoutProvider.sin(ray);
            if (Math.abs(sin) < containerAbsoluteSin) {
                if (side == PositionConstants.WEST) {
                    if (ray.x < 0) {
                        result = true;
                    }
                } else if (ray.x > 0) {
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Get heading (vector, angle, ...) between the center of the edit part and
     * the center of the edit part at the other side of the edge. Return null if
     * :
     * <UL>
     * <LI>there is no edge that is come from or go back to
     * <code>editPart</code></LI>
     * <LI>there is many edges that is come from or go back to
     * <code>editPart</code>
     * <LI>
     * 
     * @param editPart
     *            The editPart of the current border item
     * @param containerCenterAfterArrange
     *            The center of the border item parent after arrangeAll
     * @param scale
     *            The scale of the current diagram
     * @param launchNormalArrange
     *            Tell if the normal arrange process will be called before the
     *            border item arrange
     * @return A vector representing the edge
     */
    private Vector getHeading(final IBorderItemEditPart editPart, final Point containerCenterAfterArrange, final double scale) {
        final Point targetPoint = getTargetPoint(editPart, scale);
        if (targetPoint != null) {
            return new Vector(new PrecisionPoint(containerCenterAfterArrange), new PrecisionPoint(targetPoint));
        }
        return null;
    }

    /**
     * @param editPart
     * @param scale
     * @param targetPoint
     * @return
     */
    private Point getTargetPoint(final IBorderItemEditPart editPart, final double scale) {
        Point targetPoint = null;
        final GraphicalEditPart target = getTarget(editPart);

        if (target != null && editPart != target && !isAncestor(editPart, target) && !isAncestor(target, editPart)) {
            if (previousIterationDatasbyEditPart.get(target) != null) {
                targetPoint = previousIterationDatasbyEditPart.get(target).getPreviousCenterLocation();
            } else {
                targetPoint = getBounds((IGraphicalEditPart) target, scale).getCenter();
            }
        }
        return targetPoint;
    }

    private BorderItemOppositeElementData getOppositeElementData(final IBorderItemEditPart editPart, final double scale) {
        BorderItemOppositeElementData oppositeElementData = null;
        final GraphicalEditPart target = getTarget(editPart);

        if (target != null && editPart != target && !isAncestor(editPart, target) && !isAncestor(target, editPart)) {
            Point targetPoint;
            if (previousIterationDatasbyEditPart.get(target) != null) {
                targetPoint = previousIterationDatasbyEditPart.get(target).getPreviousCenterLocation();
            } else {
                targetPoint = getBounds((IGraphicalEditPart) target, scale).getCenter();
            }
            if (target instanceof IBorderItemEditPart) {
                oppositeElementData = new BorderItemOppositeElementData(targetPoint,
                        DBorderItemLocator.findClosestSideOfParent(new Rectangle(targetPoint, new Dimension(1, 1)), getBounds((IGraphicalEditPart) target.getParent(), scale)));
            } else {
                oppositeElementData = new BorderItemOppositeElementData(targetPoint);
            }
        }
        return oppositeElementData;
    }

    /**
     * Return the edit part that is at the other side of the edge. Return null
     * if :
     * <UL>
     * <LI>there is no edge that is come from or go back to
     * <code>editPart</code></LI>
     * <LI>there is many edges that is come from or go back to
     * <code>editPart</code>
     * <LI>
     * 
     * @param editPart
     *            A border edit part that is on an extremity of an edge.
     * @return the opposite graphical edit part
     */
    private GraphicalEditPart getTarget(final IBorderItemEditPart editPart) {
        GraphicalEditPart target = null;
        if (editPart.getSourceConnections().size() == 1 && editPart.getTargetConnections().isEmpty()) {
            target = (GraphicalEditPart) ((ConnectionEditPart) editPart.getSourceConnections().get(0)).getTarget();
        } else if (editPart.getSourceConnections().isEmpty() && editPart.getTargetConnections().size() == 1) {
            target = (GraphicalEditPart) ((ConnectionEditPart) editPart.getTargetConnections().get(0)).getSource();
        }
        return target;
    }

    /**
     * Check if <code>childCandidate</code> is contained in
     * <code>parentCandidate</code>.
     * 
     * @param childCandidate
     *            The child candidate
     * @param parentCandidate
     *            The parent candidate
     * @return true if parentCandidate contains the childCandidate.
     */
    private boolean isAncestor(final EditPart childCandidate, final EditPart parentCandidate) {
        EditPart currentParent = childCandidate.getParent();
        while (currentParent != null) {
            if (currentParent == parentCandidate) {
                return true;
            }
            currentParent = currentParent.getParent();
        }
        return false;
    }

    /**
     * Get the absolute bounds that this edit part will have after the execution
     * of arrangeAll.<BR>
     * The return bounds take into account the scale, ie if the real bounds is
     * {100, 200} the return bounds is {50, 100}.
     * 
     * @param graphicalEditPart
     *            The edit part to deal with
     * @param scale
     *            The current scale of the diagram
     * @return The bounds after arrangeAll
     */
    protected Rectangle getBounds(final IGraphicalEditPart graphicalEditPart, final double scale) {
        return getBounds(graphicalEditPart, scale, null);
    }

    /**
     * Get the absolute bounds that this edit part will have after the execution
     * of arrangeAll.<BR>
     * The return bounds take into account the scale, ie if the real bounds is
     * {100, 200} the return bounds is {50, 100}.
     * 
     * @param graphicalEditPart
     *            The edit part to deal with
     * @param scale
     *            The current scale of the diagram
     * @param parentMoveDelta
     *            The parent move delta if it is know, null otherwise
     * @return The bounds after arrangeAll
     */
    protected Rectangle getBounds(final IGraphicalEditPart graphicalEditPart, final double scale, final Dimension parentMoveDelta) {
        return getBounds(graphicalEditPart, scale, parentMoveDelta, true, true);
    }

    /**
     * Get the absolute bounds that this edit part will have after the execution
     * of arrangeAll.<BR>
     * The return bounds take into account the scale, ie if the real bounds is
     * {100, 200} the return bounds is {50, 100}.
     * 
     * @param graphicalEditPart
     *            The edit part to deal with
     * @param scale
     *            The current scale of the diagram
     * @param parentMoveDelta
     *            The parent move delta if it is know, null otherwise
     * @param processX
     *            A boolean value used to validate if calculating width is
     *            needed
     * @param processY
     *            A boolean value used to validate if calculating height is
     *            needed
     * @return The bounds after arrangeAll
     */
    protected Rectangle getBounds(final IGraphicalEditPart graphicalEditPart, final double scale, final Dimension parentMoveDelta, final boolean processX, final boolean processY) {

        Rectangle bounds = null;
        boolean isPinned = false;
        if (graphicalEditPart.resolveSemanticElement() instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) graphicalEditPart.resolveSemanticElement();
            isPinned = new PinHelper().isPinned(dDiagramElement);
            if (isPinned) {
                bounds = graphicalEditPart.getFigure().getBounds().getCopy();
            }
        }

        if (!isPinned) {
            // Compute location after arrange
            bounds = this.getBounds(graphicalEditPart);

            if (bounds != null) {
                final Dimension moveDelta = bounds.getTopLeft().getDifference(graphicalEditPart.getFigure().getBounds().getTopLeft());
                moveDelta.scale(1 / scale);

                // final Dimension sizeDelta =
                // bounds.getSize().getDifference(graphicalEditPart.getFigure().getBounds().getSize());
                // sizeDelta.scale(1 / scale);

                bounds = new Rectangle(graphicalEditPart.getFigure().getBounds()).translate(new Point(moveDelta.width, moveDelta.height));
                // bounds.setSize(bounds.getSize().getExpanded(sizeDelta));
            }
        }
        if (parentMoveDelta != null) {
            bounds = bounds.getTranslated(new Point(parentMoveDelta.width, parentMoveDelta.height));
        } else if (!(graphicalEditPart.getParent() instanceof IDDiagramEditPart) && bounds != null) {
            final IGraphicalEditPart parent = (IGraphicalEditPart) graphicalEditPart.getParent();
            final Rectangle parentBounds = getBounds(parent, scale);

            if (!isPinned(parent)) {
                final Dimension moveDelta = getScaledMoveDelta(parent, parentBounds, scale);
                bounds = bounds.getTranslated(new Point(moveDelta.width, moveDelta.height));
            }
        }
        graphicalEditPart.getFigure().translateToAbsolute(bounds);

        if (!isPinned && launchNormalArrange) {
            // Compute size after arrange (with auto-size)
            final Dimension moveDelta = getScaledMoveDelta(graphicalEditPart, bounds, scale);
            final Dimension sizeWithAutoSize = getSizeAfterAutoSize(graphicalEditPart, bounds, scale, moveDelta, processX, processY);
            bounds.setSize(sizeWithAutoSize);
        }
        return bounds;
    }

    /**
     * Compute the delta between the actual location of the <code>part</code>
     * and the target bounds.
     * 
     * @param part
     *            The edit part to deal with
     * @param targetBounds
     *            The target bounds
     * @param scale
     *            The current scale of the diagram
     * @return the delta between the actual location of the <code>part</code>
     *         and the target bounds
     */
    private Dimension getScaledMoveDelta(final IGraphicalEditPart part, final Rectangle targetBounds, final double scale) {
        final Point topLeft = part.getFigure().getBounds().getTopLeft();
        part.getFigure().translateToAbsolute(topLeft);
        final Dimension moveDelta = targetBounds.getTopLeft().getDifference(topLeft);
        moveDelta.scale(1 / scale);
        return moveDelta;
    }

    /**
     * Compute the size of this editPart after arrange all if this editPart is
     * in auto-size mode. This calculation is approximate by the leftmost child
     * and its size.
     * 
     * @param part
     *            The concern edit part
     * @param actualBounds
     *            The current bounds of this edit part
     * @param scale
     *            The current scale of the diagram
     * @param moveDelta
     *            The parent move delta if it is know, null otherwise
     * @return The size after arrange all (if needed).
     */
    private Dimension getSizeAfterAutoSize(final IGraphicalEditPart part, final Rectangle actualBounds, final double scale, final Dimension moveDelta, final boolean processX, final boolean processY) {
        final Dimension result = new Dimension(actualBounds.getSize());
        boolean shouldWidthAutoSized = ArrangeAllWithAutoSize.shouldBeAutosized(part) || part instanceof ShapeCompartmentEditPart;
        boolean shouldHeightAutoSized = shouldWidthAutoSized;
        if ((!shouldWidthAutoSized && !shouldHeightAutoSized) && part.getNotationView() instanceof Node) {
            final Node node = (Node) part.getNotationView();
            final LayoutConstraint layoutConstraint = node.getLayoutConstraint();
            if (layoutConstraint instanceof Size) {
                final Size size = (Size) layoutConstraint;
                shouldWidthAutoSized = size.getWidth() == -1;
                shouldHeightAutoSized = size.getHeight() == -1;
            }
        }

        // Take into account the default size for some specific figures (we set
        // a default size explicitly in createMainFigure of
        // DNodeContainerEditPart and DNodeContainerEditPart)
        Dimension defaultSize = new Dimension(0, 0);
        final IFigure f = part.getFigure();
        if (f instanceof BorderedNodeFigure && ((BorderedNodeFigure) f).getMainFigure() instanceof DefaultSizeNodeFigure) {
            defaultSize = ((DefaultSizeNodeFigure) ((BorderedNodeFigure) f).getMainFigure()).getDefaultSize();
        }
        if (shouldWidthAutoSized && processX) {
            final int rightSizeXCoordinate = getRightSizeXCoordinateOfRightMostChild(part, scale, moveDelta);
            result.width = Math.max(defaultSize.width, rightSizeXCoordinate - actualBounds.x);
        }
        if (shouldHeightAutoSized && processY) {
            final int bottomSizeYCoordinate = getBottomSizeYCoordinateOfLowestChild(part, scale, moveDelta);
            result.height = Math.max(defaultSize.height, bottomSizeYCoordinate - actualBounds.y);
        }
        return result;
    }

    /**
     * Return the x axis coordinate of the right size of the rightmost child
     * (after the arrange all).
     * 
     * @param part
     *            The parent
     * @param scale
     *            The current scale of the diagram
     * @param moveDelta
     *            The parent move delta if it is know, null otherwise
     * @param launchNormalArrange
     *            Tell if the normal arrange process will be called before the
     *            border item arrange
     * @return the x axis coordinate of the right size of the rightmost child
     */
    private int getRightSizeXCoordinateOfRightMostChild(final IGraphicalEditPart part, final double scale, final Dimension moveDelta) {
        int result = 0;
        final Collection<IGraphicalEditPart> children = Collections2.filter(part.getChildren(), Predicates.and(Predicates.instanceOf(IGraphicalEditPart.class),
                Predicates.not(Predicates.instanceOf(AbstractDiagramBorderNodeEditPart.class)), Predicates.not(Predicates.instanceOf(AbstractDiagramNameEditPart.class))));
        for (IGraphicalEditPart child : children) {
            if (child instanceof ShapeCompartmentEditPart) {
                // Only delegates to the grandchildren
                final Collection<IGraphicalEditPart> grandchildren = Collections2.filter(child.getChildren(), Predicates.and(Predicates.instanceOf(IGraphicalEditPart.class),
                        Predicates.not(Predicates.instanceOf(AbstractDiagramBorderNodeEditPart.class)), Predicates.not(Predicates.instanceOf(AbstractDiagramNameEditPart.class))));
                for (IGraphicalEditPart grandchild : grandchildren) {
                    final Rectangle bounds = getBounds(grandchild, scale, moveDelta, true, false);
                    final int rightSizeXCoordinate = bounds.x + bounds.width;
                    if (result < rightSizeXCoordinate) {
                        result = rightSizeXCoordinate;
                    }
                }
            } else {
                final Rectangle bounds = getBounds(child, scale, moveDelta, true, false);
                final int rightSizeXCoordinate = bounds.x + bounds.width;
                if (result < rightSizeXCoordinate) {
                    result = rightSizeXCoordinate;
                }
            }
        }
        return result;
    }

    /**
     * Return the y axis coordinate of the bottom size of the lowest child
     * (after the arrange all).
     * 
     * @param part
     *            The parent
     * @param scale
     *            The current scale of the diagram
     * @param moveDelta
     *            The parent move delta if it is know, null otherwise
     * @return the y axis coordinate of the bottom size of the lowest child
     */
    private int getBottomSizeYCoordinateOfLowestChild(final IGraphicalEditPart part, final double scale, final Dimension moveDelta) {
        int result = 0;
        final Collection<IGraphicalEditPart> children = Collections2.filter(part.getChildren(), Predicates.and(Predicates.instanceOf(IGraphicalEditPart.class),
                Predicates.not(Predicates.instanceOf(AbstractDiagramBorderNodeEditPart.class)), Predicates.not(Predicates.instanceOf(AbstractDiagramNameEditPart.class))));
        for (IGraphicalEditPart child : children) {
            if (child instanceof ShapeCompartmentEditPart) {
                // Only delegates to the grandchildren
                final Collection<IGraphicalEditPart> grandchildren = Collections2.filter(child.getChildren(), Predicates.and(Predicates.instanceOf(IGraphicalEditPart.class),
                        Predicates.not(Predicates.instanceOf(AbstractDiagramBorderNodeEditPart.class)), Predicates.not(Predicates.instanceOf(AbstractDiagramNameEditPart.class))));
                for (IGraphicalEditPart grandchild : grandchildren) {
                    final Rectangle bounds = getBounds(grandchild, scale, moveDelta, false, true);
                    final int bottomSizeYCoordinate = bounds.y + bounds.height;
                    if (result < bottomSizeYCoordinate) {
                        result = bottomSizeYCoordinate;
                    }
                }
            } else {
                final Rectangle bounds = getBounds(child);
                final int bottomSizeYCoordinate = bounds.y + bounds.height;
                if (result < bottomSizeYCoordinate) {
                    result = bottomSizeYCoordinate;
                }
            }
        }
        return result;
    }
}
