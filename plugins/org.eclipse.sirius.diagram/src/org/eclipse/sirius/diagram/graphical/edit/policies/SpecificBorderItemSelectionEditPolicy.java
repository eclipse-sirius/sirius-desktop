/******************************************************************************
 * Copyright (c) 2002, 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - adaptation
 ****************************************************************************/

package org.eclipse.sirius.diagram.graphical.edit.policies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPartViewer.Conditional;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableEditPolicyEx;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.query.AbstractDNodeQuery;
import org.eclipse.sirius.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.business.api.query.NodeQuery;
import org.eclipse.sirius.diagram.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.locator.FeedbackDBorderItemLocator;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.DDiagramElement;

/**
 * The specific
 * {@link org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy}
 * .
 * 
 * @author ymortier
 * @author jdupont
 */
public class SpecificBorderItemSelectionEditPolicy extends ResizableEditPolicyEx {

    /**
     * We keep all created feedbacks to delete them at the end of the drag
     * action.
     */
    private List<IFigure> feedbacks = new ArrayList<IFigure>();

    /**
     * For each collapsed figure, we keep all computed expanded coordinates
     * until the drag action is over.
     */
    private Map<IFigure, Rectangle> correspondingExpandedCoordinate = new HashMap<IFigure, Rectangle>();

    /**
     * Keep the collapsed node bounds to restore them when the drag action is
     * over. Only used in the scenario where we drag a collapsed node.
     */
    private Rectangle collapsedRectangle;

    /**
     * The edit part where feedbacks are activated.
     */
    private EditPart feedbacksActivated;

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableEditPolicyEx#eraseSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void eraseSourceFeedback(final Request request) {
        if (!(request instanceof ChangeBoundsRequest)) {
            return;
        }
        if ((REQ_MOVE.equals(request.getType()) && isDragAllowed()) || REQ_CLONE.equals(request.getType()) || REQ_ADD.equals(request.getType())) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
        }
        if (RequestConstants.REQ_DROP.equals(request.getType()) || org.eclipse.gef.RequestConstants.REQ_RESIZE.equals(request.getType())
                || org.eclipse.gef.RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType())) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
            eraseChangeBoundsProhibitedFeedbackWhenDrop();
        }
    }

    private void eraseChangeBoundsProhibitedFeedbackWhenDrop() {
        for (IFigure figure : feedbacks) {
            removeFeedback(figure);
        }
        feedbacks.clear();
        correspondingExpandedCoordinate.clear();
        // feedbacksActivated = false;
        feedbacksActivated = null;
        // isBorderedNodeDropping = false;
        collapsedRectangle = null;
    }

    /**
     * Calls other methods as appropriate. {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableEditPolicyEx#showSourceFeedback(org.eclipse.gef.Request)
     */
    @Override
    public void showSourceFeedback(final Request request) {
        if (!(request instanceof ChangeBoundsRequest)) {
            return;
        }
        if ((REQ_MOVE.equals(request.getType()) && isDragAllowed()) || REQ_ADD.equals(request.getType()) || REQ_CLONE.equals(request.getType())) {
            showChangeBoundsFeedback((ChangeBoundsRequest) request);
        }
        if (RequestConstants.REQ_DROP.equals(request.getType()) || org.eclipse.gef.RequestConstants.REQ_RESIZE.equals(request.getType())
                || org.eclipse.gef.RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType())) {
            showChangeBoundsFeedback((ChangeBoundsRequest) request);
        }
    }

    /**
     * Shows or updates feedback for a change bounds request.
     * 
     * @param request
     *            the request
     */
    @Override
    protected void showChangeBoundsFeedback(final ChangeBoundsRequest request) {
        // Get the figure of the target edit part
        EditPart hostEditPart = getHost();
        final IBorderItemEditPart borderItemEP = (IBorderItemEditPart) hostEditPart;
        EditPartViewer editPartViewer = hostEditPart.getViewer();
        Point shiftedLocation = request.getLocation().getCopy();
        EditPart targetEditPart = editPartViewer.findObjectAtExcluding(shiftedLocation, Collections.emptyList(), new IBorderedShapeEditPartCondition());

        if (targetEditPart instanceof AbstractGraphicalEditPart) {
            AbstractGraphicalEditPart targetAbstractGraphicalEditPart = (AbstractGraphicalEditPart) targetEditPart;
            IFigure targetFigure = targetAbstractGraphicalEditPart.getFigure();

            final IFigure feedback = getDragSourceFeedbackFigure();
            final PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds());
            getHostFigure().translateToAbsolute(rect);
            rect.translate(request.getMoveDelta());
            rect.resize(request.getSizeDelta());
            Rectangle realLocation = null;
            // Only necessary in the case of bordered node dropping
            if (isFeedbackForBorderedNodeDropping(request, targetAbstractGraphicalEditPart)) {

                activateProhibitedFeedbacks(targetAbstractGraphicalEditPart, request);

                DBorderItemLocator borderItemLocator = new FeedbackDBorderItemLocator(targetFigure);
                if (isCollapsed(borderItemEP)) {
                    borderItemLocator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                } else {
                    borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
                // Verify if the dropping of bordered node is not an element of
                // the same level
                if (targetAbstractGraphicalEditPart.getParent() != getHost().getParent().getParent()) {
                    // Verify if the parent is the diagram. If it, calculates
                    // the position of the ghost relative to the diagram,
                    // otherwise compared to the parent
                    if (targetAbstractGraphicalEditPart.getParent() instanceof DiagramEditPart) {
                        targetAbstractGraphicalEditPart.getFigure().translateToAbsolute(rect);
                    } else {
                        targetAbstractGraphicalEditPart.getFigure().translateToRelative(rect);
                    }
                } else {
                    getHostFigure().translateToRelative(rect);
                }
                // if the bordered node is collapsed, we extend the feedback to
                // consider his extended bounds
                if (hostEditPart instanceof IGraphicalEditPart && isCollapsed((IGraphicalEditPart) hostEditPart)) {
                    Dimension initialDim = getInitialDimension((IGraphicalEditPart) hostEditPart);
                    Rectangle newBoundsAbsolute = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, rect, null);
                    borderItemLocator.setConstraint(newBoundsAbsolute);
                    borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                    rect.setBounds(newBoundsAbsolute);
                }
                realLocation = borderItemLocator.getValidLocation(rect, feedback, Collections.singleton(feedback));

                targetFigure.translateToAbsolute(realLocation);
                feedback.translateToRelative(realLocation);
                feedback.setBounds(realLocation);
                // doesn't allows drop feedback of borderedNode to Diagram
            } else if (!(targetEditPart instanceof DDiagramEditPart)) {

                activateProhibitedFeedbacks(hostEditPart.getParent(), request);
                final IBorderItemLocator borderItemLocator = borderItemEP.getBorderItemLocator();

                if (borderItemLocator != null) {

                    getHostFigure().translateToRelative(rect);
                    Rectangle newRect = expandsCollapsedNodeBounds(borderItemEP, rect);
                    if (newRect != null) {
                        rect.setBounds(newRect);
                    }
                    if (borderItemLocator instanceof DBorderItemLocator) {
                        // Compute the list of figures to ignore during the
                        // conflict detection.
                        List<IFigure> figuresToIgnore = getFiguresToIgnore(request);
                        realLocation = ((DBorderItemLocator) borderItemLocator).getValidLocation(rect, borderItemEP.getFigure(), figuresToIgnore);
                    } else {
                        realLocation = borderItemLocator.getValidLocation(rect.getCopy(), borderItemEP.getFigure());
                    }
                    if (collapsedRectangle != null) {
                        restoreCollapsedNode(borderItemEP);
                    }
                    getHostFigure().translateToAbsolute(realLocation);

                    feedback.translateToRelative(realLocation);
                    feedback.setBounds(realLocation);
                }
            }
        }
    }

    private void restoreCollapsedNode(IBorderItemEditPart borderItemEP) {
        IBorderItemLocator borderItemLocator = borderItemEP.getBorderItemLocator();
        if (borderItemLocator instanceof DBorderItemLocator) {
            borderItemLocator.setConstraint(collapsedRectangle.getCopy());
            ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
        }
    }

    /**
     * We expand the given rect bounds to avoid conflicts. Old bounds are saved
     * in collapsedRectangle attribute to be able to restore them once the drag
     * and drop is over.
     * 
     * @param hostEditPart
     *            the collapsed figure edit part. The new expands constraints
     *            will be apply to the figure until the restoreCollapsedNode
     *            method is called.
     * @param rect
     *            the bounds to expand.
     * @return the expanded bounds.
     */
    private Rectangle expandsCollapsedNodeBounds(IBorderItemEditPart hostEditPart, final PrecisionRectangle rect) {
        if (isCollapsed(hostEditPart)) {
            IBorderItemLocator borderItemLocator = hostEditPart.getBorderItemLocator();
            if (borderItemLocator instanceof DBorderItemLocator) {
                if (collapsedRectangle == null) {
                    collapsedRectangle = ((DBorderItemLocator) borderItemLocator).getCurrentConstraint();
                }
                EditPart parentEditPart = hostEditPart.getParent();
                Rectangle parentBounds = null;
                if (parentEditPart instanceof IGraphicalEditPart) {
                    IFigure parentFigure = ((IGraphicalEditPart) parentEditPart).getFigure();
                    parentBounds = parentFigure.getBounds().getCopy();
                    if (parentFigure instanceof NodeFigure) {
                        parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
                    }

                    Dimension initialDim = getInitialDimension(hostEditPart);
                    Rectangle newBoundsAbsolute = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, rect, parentBounds);

                    Rectangle newBoundsFromFigure = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, collapsedRectangle, null);

                    borderItemLocator.setConstraint(newBoundsFromFigure);
                    ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);

                    return newBoundsAbsolute;
                }
            }
        }
        return null;

    }

    private boolean isCollapsed(IGraphicalEditPart editPart) {
        EObject element = editPart.resolveSemanticElement();
        if (element instanceof DDiagramElement) {
            DDiagramElementQuery query = new DDiagramElementQuery((DDiagramElement) element);
            return query.isIndirectlyCollapsed();
        }
        return false;
    }

    /**
     * Activates feedbacks for all collapsed node to avoid to place an other
     * node in their forbidden area.
     * 
     * @param hostEditPart
     *            the edit part hosting the figure we are moving.
     * @param targetEditPart
     *            the container target edit part where the node is moving (can
     *            be the drop target edit part or the current node container
     *            edit part)
     * @param request
     *            the change bounds request.
     */
    private void activateProhibitedFeedbacks(EditPart targetEditPart, ChangeBoundsRequest request) {

        // if prohibited feedbacks were activated for an other edit part, we
        // clean them.
        if (isFeedbacksActivated() && !isFeedbacksActivatedForEditPart(targetEditPart)) {
            eraseChangeBoundsProhibitedFeedbackWhenDrop();
        }
        for (Object child : targetEditPart.getChildren()) {
            if (!request.getEditParts().contains(child) && child instanceof AbstractDiagramBorderNodeEditPart) {
                AbstractDiagramBorderNodeEditPart borderNodeEditPart = (AbstractDiagramBorderNodeEditPart) child;
                if (isCollapsed(borderNodeEditPart)) {
                    configureFeedback(borderNodeEditPart);
                }
            }
        }
        feedbacksActivated = targetEditPart;
    }

    /**
     * Returns whether the prohibited feedbacks are activated for the given
     * target edit part.
     * 
     * @param targetEditPart
     *            the edit part we want to know if the prohibited feedbacks are
     *            activated.
     * @return true if activated otherwise false
     */
    private boolean isFeedbacksActivatedForEditPart(EditPart targetEditPart) {
        return feedbacksActivated == targetEditPart;
    }

    /**
     * Returns whether the prohibited feedbacks are activated somewhere
     * 
     * @return true if activated otherwise false
     */
    private boolean isFeedbacksActivated() {
        return feedbacksActivated != null;
    }

    /**
     * configure the prohibited feedback and area for the given border node edit
     * part.
     * 
     * @param borderNodeEditPart
     *            the edit part hosting the collapsed node figure.
     */
    private void configureFeedback(AbstractBorderItemEditPart borderNodeEditPart) {

        IFigure figure = borderNodeEditPart.getFigure();
        EditPart parentEditPart = borderNodeEditPart.getParent();
        // we don't create feedbacks if there are already activated.
        if (!isFeedbacksActivatedForEditPart(borderNodeEditPart.getParent())) {
            Dimension initialDim = getInitialDimension(borderNodeEditPart);
            Rectangle bounds = figure.getBounds().getCopy();
            Rectangle parentBounds = null;
            if (parentEditPart instanceof IGraphicalEditPart) {
                IFigure parentFigure = ((IGraphicalEditPart) parentEditPart).getFigure();
                parentBounds = parentFigure.getBounds().getCopy();
                if (parentFigure instanceof NodeFigure) {
                    parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
                }
            }
            Rectangle newBounds = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, bounds, parentBounds);
            final IBorderItemLocator borderItemLocator = borderNodeEditPart.getBorderItemLocator();
            // get real location from DBorderItemLocator
            Rectangle realNewBounds = getRealExpandedBounds(figure, newBounds, borderItemLocator);

            PrecisionRectangle precisionRectangle = new PrecisionRectangle(realNewBounds);
            // // Use a ghost rectangle for feedback
            RectangleFigure r = new RectangleFigure();
            r.setLineStyle(Graphics.LINE_DOT);
            r.setBounds(precisionRectangle);

            addFeedback(r);
            figure.translateToAbsolute(precisionRectangle);
            r.translateToRelative(precisionRectangle);
            r.setBounds(precisionRectangle);

            r.setBackgroundColor(ColorConstants.red);
            r.setAlpha(70);

            feedbacks.add(r);
            correspondingExpandedCoordinate.put(figure, realNewBounds);
        }
        Rectangle figureNewBounds = correspondingExpandedCoordinate.get(figure);
        if (figureNewBounds != null) {

            // we update the figure bounds to be considered by the border item
            // locator. Because we don't set the layout constraints, the figure
            // bounds will come back to their original collapsed bounds at
            // refresh
            // time.
            figure.setBounds(figureNewBounds);
        }
    }

    /**
     * Provides the location that should be if the node was not collapsed.
     * 
     * @param figure
     *            the current node figure.
     * @param candidateNewBounds
     *            the new bounds candidates.
     * @param borderItemLocator
     *            the figure edit part border item locator.
     * @return the real location from the border item locator.
     */
    private Rectangle getRealExpandedBounds(IFigure figure, Rectangle candidateNewBounds, final IBorderItemLocator borderItemLocator) {
        Rectangle realNewBounds = candidateNewBounds;
        if (borderItemLocator instanceof DBorderItemLocator) {
            Rectangle oldConstraint = ((DBorderItemLocator) borderItemLocator).getCurrentConstraint();
            borderItemLocator.setConstraint(candidateNewBounds);

            Dimension oldOffset = ((DBorderItemLocator) borderItemLocator).getBorderItemOffset();
            ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);

            realNewBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(candidateNewBounds, figure, Collections.singleton(figure));

            ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(oldOffset);
            borderItemLocator.setConstraint(oldConstraint);

        }
        return realNewBounds;
    }

    private Dimension getInitialDimension(IGraphicalEditPart editPart) {
        Object node = editPart.getModel();
        if (node instanceof Node) {
            NodeQuery query = new NodeQuery((Node) node);
            return query.getOriginalDimensionBeforeCollapse();
        }
        return new Dimension(0, 0);
    }

    /**
     * Tell if according to the <code>request</code>'s location we must provide
     * a feedback for a drop of borderedNode to another parent or a feedback for
     * a simple move of borderedNode without changing visually the parent/
     * 
     * @param request
     *            the {@link ChangeBoundsRequest} providing the location of the
     *            mouse
     * 
     * @param targetAbstractGraphicalEditPart
     *            the target EditPart on which to provide the feedback
     * 
     * @return true if we must provide a feedback for a drop
     */
    private boolean isFeedbackForBorderedNodeDropping(ChangeBoundsRequest request, AbstractGraphicalEditPart targetAbstractGraphicalEditPart) {
        boolean isFeedbackForBorderedNodeDropping = false;
        if (!(new RequestQuery(request).isResize() || org.eclipse.gef.RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType()))) {

            EditPart hostEditPart = getHost();
            IFigure diagramFigure = ((AbstractGraphicalEditPart) hostEditPart.getRoot().getChildren().get(0)).getFigure();

            IFigure targetFigure = targetAbstractGraphicalEditPart.getFigure();

            // verify not case of a label and not on the diagram to the
            // ghost
            // appears only on the nodes and not on the diagram during a
            // drop
            if (targetFigure != diagramFigure && !(hostEditPart instanceof DNodeNameEditPart)) {
                // Necessary for sequence diagrams, for the feedback does
                // not
                // shift
                isFeedbackForBorderedNodeDropping = targetAbstractGraphicalEditPart != hostEditPart && targetAbstractGraphicalEditPart != hostEditPart.getParent();
            }
        }
        return isFeedbackForBorderedNodeDropping;
    }

    /**
     * Returns the command contribution to a change bounds request.
     * 
     * @param request
     *            the change bounds requesgt
     * @return the command contribution to the request
     */
    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {
        final IBorderItemEditPart borderItemEP = (IBorderItemEditPart) getHost();
        final IBorderItemLocator borderItemLocator = borderItemEP.getBorderItemLocator();

        if (borderItemLocator != null) {
            final PrecisionRectangle rect = new PrecisionRectangle(getInitialFeedbackBounds());
            getHostFigure().translateToAbsolute(rect);
            rect.translate(request.getMoveDelta());
            rect.resize(request.getSizeDelta());

            getHostFigure().translateToRelative(rect);

            Rectangle realLocation;
            if (borderItemLocator instanceof DBorderItemLocator) {
                // Compute the list of figures to ignore during the conflict
                // detection.
                List<IFigure> figuresToIgnore = getFiguresToIgnore(request);

                // if the bordered node is collapsed, we compute his expanded
                // bounds
                Rectangle newBounds = expandsCollapsedNodeBounds(borderItemEP, rect);
                if (newBounds != null) {
                    rect.setBounds(newBounds);
                }

                realLocation = ((DBorderItemLocator) borderItemLocator).getValidLocation(rect, borderItemEP.getFigure(), figuresToIgnore);
                if (collapsedRectangle != null) {
                    restoreCollapsedNode(borderItemEP);
                    IFigure parentFigure = ((DBorderItemLocator) borderItemLocator).getParentFigure();
                    Rectangle parentBounds = parentFigure.getBounds().getCopy();
                    if (parentFigure instanceof NodeFigure) {
                        parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
                    }
                    Rectangle collapsedBounds = PortLayoutHelper.getCollapseCandidateLocation(collapsedRectangle.getSize(), realLocation, parentBounds);
                    realLocation.setBounds(collapsedBounds);
                }
                ((DBorderItemLocator) borderItemLocator).setFiguresToIgnoresDuringNextRelocate(figuresToIgnore);
            } else {
                realLocation = borderItemLocator.getValidLocation(rect.getCopy(), borderItemEP.getFigure());
            }

            final Point parentOrigin = borderItemEP.getFigure().getParent().getBounds().getTopLeft();
            final Dimension d = realLocation.getTopLeft().getDifference(parentOrigin);
            final Point location = new Point(d.width, d.height);
            final ICommand moveCommand = new SetBoundsCommand(borderItemEP.getEditingDomain(), DiagramUIMessages.Commands_MoveElement, new EObjectAdapter((View) getHost().getModel()), location);
            return new ICommandProxy(moveCommand);
        }
        return null;
    }

    /**
     * Return a list of all figure that will be moved by this
     * ChangeBoundsRequest (the figure of the editParts).
     * 
     * @param request
     *            The request created by the edit part that have been moved.
     * @return list of all figure that will be moved
     */
    protected List<IFigure> getFiguresToIgnore(final ChangeBoundsRequest request) {
        List<IFigure> figuresToIgnore = Lists.newArrayList();
        for (Object part : request.getEditParts()) {
            if (part instanceof org.eclipse.gef.GraphicalEditPart) {
                figuresToIgnore.add(((org.eclipse.gef.GraphicalEditPart) part).getFigure());
            }
        }
        return figuresToIgnore;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableEditPolicyEx#addSelectionHandles()
     */
    @Override
    protected void addSelectionHandles() {
        super.addSelectionHandles();
        boolean mustMoveCursorBeDisabled = mustMoveCursorBeDisabled();

        for (Object handle : handles) {
            if (mustMoveCursorBeDisabled) {
                if (handle instanceof MoveHandle || handle instanceof ResizeHandle) {
                    ((IFigure) handle).setCursor(null);
                }
            }
            if (handle instanceof MoveHandle) {
                // We set a drag tracker that will not cause the
                // duplication of graphical elements when holding "Ctrl" key
                // down and moving an element
                ((MoveHandle) handle).setDragTracker(new NoCopyDragEditPartsTrackerEx(getHost()));
            }

        }
    }

    /**
     * Check if the host corresponds to a borederdNode that is collapsed. In
     * this case, the move cursor must be disabled.
     * 
     * @return true if the move cursor must be disabled, false otherwise.
     */
    protected boolean mustMoveCursorBeDisabled() {
        boolean result = false;
        if (getHost() instanceof GraphicalEditPart) {
            EObject semantic = ((GraphicalEditPart) getHost()).resolveSemanticElement();
            if (semantic instanceof AbstractDNode) {
                result = new AbstractDNodeQuery((AbstractDNode) semantic).isBorderedNode() && new DDiagramElementQuery((AbstractDNode) semantic).isCollapsed();
            }
        }
        return result;
    }

    static class IBorderedShapeEditPartCondition implements Conditional {

        public boolean evaluate(EditPart editpart) {
            return editpart instanceof IBorderedShapeEditPart;
        }

    }
}
