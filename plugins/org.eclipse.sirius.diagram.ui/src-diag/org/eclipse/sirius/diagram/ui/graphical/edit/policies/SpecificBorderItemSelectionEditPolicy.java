/******************************************************************************
 * Copyright (c) 2002, 2018 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Obeo - adaptation
 ****************************************************************************/

package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

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
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.AbstractBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderedShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableEditPolicyEx;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.IBorderItemLocator;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.AbstractDNodeQuery;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.business.internal.query.RequestQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.internal.part.PortLayoutHelper;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.CenterEditPartEdgesCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.commands.ChangeBendpointsOfEdgesCommand;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.operation.ShiftEdgeIdentityAnchorOperation;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.styles.IBorderItemOffsets;
import org.eclipse.sirius.diagram.ui.tools.internal.edit.command.CommandFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.locator.FeedbackDBorderItemLocator;
import org.eclipse.sirius.diagram.ui.tools.internal.ui.NoCopyDragEditPartsTrackerEx;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * The specific {@link org.eclipse.gmf.runtime.diagram.ui.editpolicies.BorderItemSelectionEditPolicy} .
 * 
 * @author ymortier
 * @author jdupont
 */
public class SpecificBorderItemSelectionEditPolicy extends ResizableEditPolicyEx {

    /**
     * Key for extended data of request. This key corresponds to a map that for each DDiagramElement (border nodes)
     * stores the absolute screen location computed for the feedback figures.<BR>
     * This map is set during drawing of border node feedback. This map is used later in the
     * {@link SiriusContainerDropPolicy} to have the realLocation, and so the real delta for each border nodes, instead
     * of the location corresponding to the move makes with the mouse. This allows to compute correctly the new linked
     * edges bendpoints to only move the last segment.
     */
    public static final String BORDER_NODE_REAL_LOCATION_KEY = "borderNodesRealLocation"; //$NON-NLS-1$

    /**
     * Key for extended data of request. This key corresponds to feedback figures. This figures are added during drawing
     * of feedback when several border nodes are move simultaneously. The last moved figure is not added to this list
     * (because there will be no further feedback after this one).
     */
    private static final String BORDER_NODE_FEEDBACKS_KEY = "borderNodeFeedbacks"; //$NON-NLS-1$

    /**
     * We keep all created feedbacks to delete them at the end of the drag action.
     */
    private List<IFigure> feedbacks = new ArrayList<IFigure>();

    /**
     * For each collapsed figure, we keep all computed expanded coordinates until the drag action is over.
     */
    private Map<IFigure, Rectangle> correspondingExpandedCoordinate = new HashMap<IFigure, Rectangle>();

    /**
     * The edit part where feedbacks are activated.
     */
    private EditPart feedbacksActivated;

    /**
     * Status of the feedback figure. True if the feedback figure is displayed (created by the
     * showChangeBoundsFeedback(ChangeBoundsRequest) method), false if not already created or deleted (with method
     * eraseChangeBoundsFeedback(ChangeBoundsRequest)).<BR>
     * This avoids to create this feedback figure in method getFiguresToIgnore(ChangeBoundsRequest). <BR>
     * This was done because, there is no way to know if the feedback figure is null or not (private field).
     */
    private boolean feedbackFigureDisplayed;

    private BorderNodeCollapseManager borderNodeCollapseManager;

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
        // Remove the feedback from request extended data
        getBorderNodeFeedbacks(request).remove(getDragSourceFeedbackFigure());
        if ((REQ_MOVE.equals(request.getType()) && isDragAllowed()) || REQ_CLONE.equals(request.getType()) || REQ_ADD.equals(request.getType())) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);

        }
        if (RequestConstants.REQ_DROP.equals(request.getType()) || org.eclipse.gef.RequestConstants.REQ_RESIZE.equals(request.getType())
                || org.eclipse.gef.RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType())) {
            eraseChangeBoundsFeedback((ChangeBoundsRequest) request);
            eraseChangeBoundsProhibitedFeedbackWhenDrop();
        }
    }

    @Override
    protected void eraseChangeBoundsFeedback(ChangeBoundsRequest request) {
        feedbackFigureDisplayed = false;
        super.eraseChangeBoundsFeedback(request);
    }

    /**
     * Return the list of existing feedback figures containing in the request. If the request does not contains feedback
     * figures, an empty list is returned.
     * 
     * @param request
     *            The request containing the extended data.
     * @return the list of existing feedback figures contained in the request.
     */
    @SuppressWarnings("unchecked")
    private List<IFigure> getBorderNodeFeedbacks(Request request) {
        Object result = request.getExtendedData().get(BORDER_NODE_FEEDBACKS_KEY);
        if (result instanceof List<?> && Iterables.all((List<?>) result, Predicates.instanceOf(IFigure.class))) {
            return (List<IFigure>) result;
        } else {
            return new ArrayList<IFigure>();
        }
    }

    /**
     * Return a map with for each DDiagramElement the location of its feedback computed during the showSourceFeedback.
     * If the request does not contains this map, an empty one is returned.
     * 
     * @param request
     *            The request containing the extended data.
     * @return map with for each DDiagramElement the location of its feedback
     */
    @SuppressWarnings("unchecked")
    private Map<DDiagramElement, Point> getLocationsForDDiagramElement(Request request) {
        Object result = request.getExtendedData().get(BORDER_NODE_REAL_LOCATION_KEY);
        if (result instanceof Map<?, ?>) {
            return (Map<DDiagramElement, Point>) result;
        } else {
            return new HashMap<>();
        }
    }

    private void eraseChangeBoundsProhibitedFeedbackWhenDrop() {
        for (IFigure figure : feedbacks) {
            removeFeedback(figure);
        }
        feedbacks.clear();
        correspondingExpandedCoordinate.clear();
        feedbacksActivated = null;
        if (borderNodeCollapseManager != null) {
            borderNodeCollapseManager.dispose();
        }
    }

    private BorderNodeCollapseManager getOrCreateBorderNodeCollapseManager() {
        if (borderNodeCollapseManager == null) {
            borderNodeCollapseManager = new BorderNodeCollapseManager();
        }
        return borderNodeCollapseManager;
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
            feedbackFigureDisplayed = true;
            final PrecisionRectangle feedbackRectangle = new PrecisionRectangle(getInitialFeedbackBounds());
            getHostFigure().translateToAbsolute(feedbackRectangle);
            feedbackRectangle.translate(request.getMoveDelta());
            feedbackRectangle.resize(request.getSizeDelta());
            Rectangle realLocation = null;

            // Only necessary in the case of bordered node dropping
            EditPartQuery editPartQuery = new EditPartQuery((IGraphicalEditPart) hostEditPart);
            if (isFeedbackForBorderedNodeDropping(request, targetAbstractGraphicalEditPart) && (!editPartQuery.isInShowingMode() || !editPartQuery.isInLayoutingMode())
                    && isMouseOnTargetFigure(targetAbstractGraphicalEditPart, shiftedLocation)) {
                activateProhibitedFeedbacks(targetAbstractGraphicalEditPart, request);
                DBorderItemLocator borderItemLocator = new FeedbackDBorderItemLocator(targetFigure);
                if (getOrCreateBorderNodeCollapseManager().isCollapsed(borderItemEP)) {
                    borderItemLocator.setBorderItemOffset(IBorderItemOffsets.COLLAPSE_FILTER_OFFSET);
                } else {
                    borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                }
                // Verify if the dropping of bordered node is not an element of the same level
                if (targetAbstractGraphicalEditPart.getParent() != getHost().getParent().getParent()) {
                    // calculates the position of the ghost relative to the parent
                    targetAbstractGraphicalEditPart.getFigure().translateToRelative(feedbackRectangle);
                } else {
                    getHostFigure().translateToRelative(feedbackRectangle);
                }
                // if the bordered node is collapsed, we extend the feedback
                // to consider his extended bounds
                if (hostEditPart instanceof IGraphicalEditPart && getOrCreateBorderNodeCollapseManager().isCollapsed((IGraphicalEditPart) hostEditPart)) {
                    Dimension initialDim = getOrCreateBorderNodeCollapseManager().getInitialDimension((IGraphicalEditPart) hostEditPart);
                    Rectangle newBoundsAbsolute = PortLayoutHelper.getUncollapseCandidateLocation(initialDim, feedbackRectangle, null);
                    borderItemLocator.setConstraint(newBoundsAbsolute);
                    borderItemLocator.setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);
                    feedbackRectangle.setBounds(newBoundsAbsolute);
                }
                realLocation = borderItemLocator.getValidLocation(feedbackRectangle, feedback, getFiguresToIgnore(request), getBorderNodeFeedbacks(request));

                targetFigure.translateToAbsolute(realLocation);
                feedback.translateToRelative(realLocation);
                feedback.setBounds(realLocation);
                storeFeedback(feedback, request);
                // Add the real location in request (this location is used
                // inSiriusContainerDropPolicy)
                Map<DDiagramElement, Point> locationsForDDiagramElement = getLocationsForDDiagramElement(request);
                if (getOrCreateBorderNodeCollapseManager().isCollapsed(borderItemEP)) {
                    realLocation = PortLayoutHelper.getCollapseCandidateLocation(borderItemEP.getFigure().getSize(), realLocation, targetFigure.getBounds());
                }
                locationsForDDiagramElement.put((DDiagramElement) borderItemEP.resolveSemanticElement(), realLocation.getLocation());
                request.getExtendedData().put(BORDER_NODE_REAL_LOCATION_KEY, locationsForDDiagramElement);
            } else {
                activateProhibitedFeedbacks(hostEditPart.getParent(), request);
                final IBorderItemLocator borderItemLocator = borderItemEP.getBorderItemLocator();

                if (borderItemLocator != null) {
                    getHostFigure().translateToRelative(feedbackRectangle);
                    Rectangle newRect = getOrCreateBorderNodeCollapseManager().expandCollapsedNodeBounds(borderItemEP, feedbackRectangle);
                    if (newRect != null) {
                        feedbackRectangle.setBounds(newRect);
                    }
                    if (borderItemLocator instanceof DBorderItemLocator) {
                        // Compute the list of figures to ignore during the
                        // conflict detection.
                        List<IFigure> figuresToIgnore = getFiguresToIgnore(request);
                        realLocation = ((DBorderItemLocator) borderItemLocator).getValidLocation(feedbackRectangle, borderItemEP.getFigure(), figuresToIgnore, getBorderNodeFeedbacks(request));
                    } else {
                        realLocation = borderItemLocator.getValidLocation(feedbackRectangle.getCopy(), borderItemEP.getFigure());
                    }
                    if (getOrCreateBorderNodeCollapseManager().hasBeenExpanded()) {
                        getOrCreateBorderNodeCollapseManager().restoreCollapsedNode(borderItemEP);
                    }
                    getHostFigure().translateToAbsolute(realLocation);

                    feedback.translateToRelative(realLocation);
                    feedback.setBounds(realLocation);
                    storeFeedback(feedback, request);
                }
            }
        }
    }

    /**
     * Check if the given point is located on the figure of the given editPart.</br>
     * This method is based on getClientArea which ignore the shadow area on bottom and right of a container.
     */
    private boolean isMouseOnTargetFigure(AbstractGraphicalEditPart editPart, Point point) {
        IFigure figure = editPart.getFigure();
        Point copy = point.getCopy();
        figure.translateToRelative(copy);
        return figure.getClientArea(new Rectangle()).contains(copy);
    }

    /**
     * The feedback is stored only if the request corresponds to several elements and that the current element is not
     * the last.
     * 
     * @param feedback
     *            The figure to store.
     * @param request
     *            The request to store in.
     */
    private void storeFeedback(IFigure feedback, ChangeBoundsRequest request) {
        if (request.getEditParts().size() > 1 && !getHost().equals(request.getEditParts().get(request.getEditParts().size() - 1))) {
            // Store the feedback new location in request to use it for
            // other feedback conflict detection
            List<IFigure> borderNodeFeedbacks = getBorderNodeFeedbacks(request);
            int currentIndex = borderNodeFeedbacks.indexOf(feedback);
            if (currentIndex != -1) {
                borderNodeFeedbacks.set(currentIndex, feedback);
            } else {
                borderNodeFeedbacks.add(feedback);
            }
            request.getExtendedData().put(BORDER_NODE_FEEDBACKS_KEY, borderNodeFeedbacks);
        }
    }

    /**
     * Activates feedbacks for all collapsed node to avoid to place an other node in their forbidden area.
     * 
     * @param hostEditPart
     *            the edit part hosting the figure we are moving.
     * @param targetEditPart
     *            the container target edit part where the node is moving (can be the drop target edit part or the
     *            current node container edit part)
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
                if (getOrCreateBorderNodeCollapseManager().isCollapsed(borderNodeEditPart)) {
                    configureFeedback(borderNodeEditPart, getBorderNodeFeedbacks(request));
                }
            }
        }
        feedbacksActivated = targetEditPart;
    }

    /**
     * Returns whether the prohibited feedbacks are activated for the given target edit part.
     * 
     * @param targetEditPart
     *            the edit part we want to know if the prohibited feedbacks are activated.
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
     * configure the prohibited feedback and area for the given border node edit part.
     * 
     * @param borderNodeEditPart
     *            the edit part hosting the collapsed node figure.
     * @param otherFeedbackFigures
     *            In case of simultaneous moves, this list corresponds to the already known border nodes after move
     *            (generally the feedback figure)
     */
    private void configureFeedback(AbstractBorderItemEditPart borderNodeEditPart, List<IFigure> otherFeedbackFigures) {

        IFigure figure = borderNodeEditPart.getFigure();
        EditPart parentEditPart = borderNodeEditPart.getParent();
        // we don't create feedbacks if there are already activated.
        if (!isFeedbacksActivatedForEditPart(borderNodeEditPart.getParent())) {
            Dimension initialDim = getOrCreateBorderNodeCollapseManager().getInitialDimension(borderNodeEditPart);
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
            Rectangle realNewBounds = getRealExpandedBounds(figure, newBounds, borderItemLocator, otherFeedbackFigures);

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
     * @param otherFeedbackFigures
     *            In case of simultaneous moves, this list corresponds to the already known border nodes after move
     *            (generally the feedback figure)
     * @return the real location from the border item locator.
     */
    private Rectangle getRealExpandedBounds(IFigure figure, Rectangle candidateNewBounds, final IBorderItemLocator borderItemLocator, List<IFigure> otherFeedbackFigures) {
        Rectangle realNewBounds = candidateNewBounds;
        if (borderItemLocator instanceof DBorderItemLocator) {
            Rectangle oldConstraint = ((DBorderItemLocator) borderItemLocator).getCurrentConstraint();
            borderItemLocator.setConstraint(candidateNewBounds);

            Dimension oldOffset = ((DBorderItemLocator) borderItemLocator).getBorderItemOffset();
            ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(IBorderItemOffsets.DEFAULT_OFFSET);

            realNewBounds = ((DBorderItemLocator) borderItemLocator).getValidLocation(candidateNewBounds, figure, Collections.singleton(figure), otherFeedbackFigures);

            ((DBorderItemLocator) borderItemLocator).setBorderItemOffset(oldOffset);
            borderItemLocator.setConstraint(oldConstraint);
            // The setConstraint has been reset to old value. The
            // borderItemMovedState must be reset to avoid that
            // borderItemLocator considers it as a real change.
            ((DBorderItemLocator) borderItemLocator).resetBorderItemMovedState();

        }
        return realNewBounds;
    }

    /**
     * Tell if according to the <code>request</code>'s location we must provide a feedback for a drop of borderedNode to
     * another parent or a feedback for a simple move of borderedNode without changing visually the parent.
     * 
     * @param request
     *            the {@link ChangeBoundsRequest} providing the location of the mouse
     * 
     * @param targetAbstractGraphicalEditPart
     *            the target EditPart on which to provide the feedback
     * 
     * @return true if we must provide a feedback for a drop
     */
    private boolean isFeedbackForBorderedNodeDropping(ChangeBoundsRequest request, AbstractGraphicalEditPart targetAbstractGraphicalEditPart) {
        boolean isFeedbackForBorderedNodeDropping = false;
        if (!(new RequestQuery(request).isResize() || RequestConstants.REQ_RESIZE_CHILDREN.equals(request.getType()) || RequestConstants.REQ_MOVE.equals(request.getType()))) {

            EditPart hostEditPart = getHost();
            IFigure diagramFigure = ((AbstractGraphicalEditPart) hostEditPart.getRoot().getChildren().get(0)).getFigure();

            IFigure targetFigure = targetAbstractGraphicalEditPart.getFigure();

            // verify not case of a label and not on the diagram to the ghost
            // appears only on the nodes and not on the diagram during a drop
            if (targetFigure != diagramFigure && !(hostEditPart instanceof DNodeNameEditPart)) {
                // Necessary for sequence diagrams, for the feedback does not
                // shift
                isFeedbackForBorderedNodeDropping = targetAbstractGraphicalEditPart != hostEditPart && targetAbstractGraphicalEditPart != hostEditPart.getParent();
            }
        }
        return isFeedbackForBorderedNodeDropping;
    }

    @Override
    protected Command getResizeCommand(ChangeBoundsRequest request) {
        EditPart host = getHost();

        if (host instanceof IBorderItemEditPart) {
            final IBorderItemEditPart borderItemEP = (IBorderItemEditPart) host;
            Rectangle newBounds = getNewBounds(request);

            SetBoundsCommand setBoundsCommand = new SetBoundsCommand(borderItemEP.getEditingDomain(), DiagramUIMessages.SetLocationCommand_Label_Resize,
                    new EObjectAdapter((View) getHost().getModel()), newBounds);

            TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) borderItemEP).getEditingDomain();
            CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(editingDomain, setBoundsCommand.getLabel());
            ctc.add(setBoundsCommand);

            // Compute the shift delta according to the real future bounds.
            if (newBounds != null) {
                PrecisionPoint delta = new PrecisionPoint();
                if (getHost() instanceof IGraphicalEditPart) {
                    Point parentOrigin = borderItemEP.getFigure().getParent().getBounds().getTopLeft();
                    Point oldRelativeBounds = borderItemEP.getFigure().getBounds().getTopLeft().translate(parentOrigin.getNegated());
                    delta = new PrecisionPoint(newBounds.getLocation().translate(oldRelativeBounds.getNegated()));
                }
                ShiftEdgeIdentityAnchorOperation operation = new ShiftEdgeIdentityAnchorOperation(request, newBounds.getSize(), delta);
                ctc.add(CommandFactory.createICommand(editingDomain, operation));
            }

            // we add a command to keep the edges centered (if they should be)
            CenterEditPartEdgesCommand centerEditPartEdgesCommand = new CenterEditPartEdgesCommand(borderItemEP, request);
            ctc.add(centerEditPartEdgesCommand);
            return new ICommandProxy(ctc);
        }
        return super.getResizeCommand(request);
    }

    /**
     * Returns the command contribution to a change bounds request.
     * 
     * @param request
     *            the change bounds request
     * @return the command contribution to the request
     */
    @Override
    protected Command getMoveCommand(final ChangeBoundsRequest request) {

        final IBorderItemEditPart borderItemEP = (IBorderItemEditPart) getHost();
        Rectangle newBounds = getNewBounds(request);
        if (newBounds != null) {
            final ICommand moveCommand = new SetBoundsCommand(borderItemEP.getEditingDomain(), DiagramUIMessages.Commands_MoveElement, new EObjectAdapter((View) getHost().getModel()),
                    newBounds.getLocation());
            Command result = new ICommandProxy(moveCommand);

            if (getHost() instanceof IGraphicalEditPart) {
                final Point parentOrigin = borderItemEP.getFigure().getParent().getBounds().getTopLeft();
                Point oldRelativeBounds = borderItemEP.getFigure().getBounds().getTopLeft().translate(parentOrigin.getNegated());
                PrecisionPoint delta = new PrecisionPoint(newBounds.getLocation().translate(oldRelativeBounds.getNegated()));
                GraphicalHelper.applyZoomOnPoint((IGraphicalEditPart) getHost(), delta);

                IGraphicalEditPart hostPart = (IGraphicalEditPart) getHost();
                CompositeTransactionalCommand ctc = new CompositeTransactionalCommand(hostPart.getEditingDomain(), result.getLabel());
                ctc.add(new CommandProxy(result));
                ctc.add(new ChangeBendpointsOfEdgesCommand(hostPart, delta));
                result = new ICommandProxy(ctc);
            }
            return result;
        }
        return null;
    }

    /**
     * Computes the new bounds of the border item.
     * 
     * @param request
     *            the change bounds request.
     * @return the new bounds according to the request and the border item locator.
     */
    private Rectangle getNewBounds(final ChangeBoundsRequest request) {
        IBorderItemEditPart borderItemEP = (IBorderItemEditPart) getHost();
        IBorderItemLocator borderItemLocator = borderItemEP.getBorderItemLocator();
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
                Rectangle newBounds = getOrCreateBorderNodeCollapseManager().expandCollapsedNodeBounds(borderItemEP, rect);
                if (newBounds != null) {
                    rect.setBounds(newBounds);
                }

                realLocation = ((DBorderItemLocator) borderItemLocator).getValidLocation(rect, borderItemEP.getFigure(), figuresToIgnore, getBorderNodeFeedbacks(request));
                if (getOrCreateBorderNodeCollapseManager().hasBeenExpanded()) {
                    getOrCreateBorderNodeCollapseManager().restoreCollapsedNode(borderItemEP);
                    IFigure parentFigure = ((DBorderItemLocator) borderItemLocator).getParentFigure();
                    Rectangle parentBounds = parentFigure.getBounds().getCopy();
                    if (parentFigure instanceof NodeFigure) {
                        parentBounds = ((NodeFigure) parentFigure).getHandleBounds().getCopy();
                    }
                    Rectangle collapsedBounds = PortLayoutHelper.getCollapseCandidateLocation(getOrCreateBorderNodeCollapseManager().getCollapsedRectangle().getSize(), realLocation, parentBounds);
                    realLocation.setBounds(collapsedBounds);
                }
                ((DBorderItemLocator) borderItemLocator).setFiguresToIgnoresDuringNextRelocate(figuresToIgnore);
            } else {
                realLocation = borderItemLocator.getValidLocation(rect.getCopy(), borderItemEP.getFigure());
            }

            final Point parentOrigin = borderItemEP.getFigure().getParent().getBounds().getTopLeft();
            final Dimension d = realLocation.getTopLeft().getDifference(parentOrigin);
            final Point location = new Point(d.width, d.height);
            return new Rectangle(location, rect.getSize());

        }
        return null;
    }

    /**
     * Return a list of all figure that will be moved by this ChangeBoundsRequest (the figure of the editParts).
     * 
     * @param request
     *            The request created by the edit part that have been moved.
     * @return list of all figure that will be moved
     */
    protected List<IFigure> getFiguresToIgnore(final ChangeBoundsRequest request) {
        List<IFigure> figuresToIgnore = new ArrayList<>();
        for (Object part : request.getEditParts()) {
            if (part instanceof org.eclipse.gef.GraphicalEditPart) {
                figuresToIgnore.add(((org.eclipse.gef.GraphicalEditPart) part).getFigure());
            }
        }
        // Add the feedback figure only if it was created before (by a drag for
        // example).
        if (feedbackFigureDisplayed) {
            figuresToIgnore.add(getDragSourceFeedbackFigure());
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
     * Check if the host corresponds to a borederdNode that is collapsed. In this case, the move cursor must be
     * disabled.
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

        @Override
        public boolean evaluate(EditPart editpart) {
            return editpart instanceof IBorderedShapeEditPart;
        }

    }
}
