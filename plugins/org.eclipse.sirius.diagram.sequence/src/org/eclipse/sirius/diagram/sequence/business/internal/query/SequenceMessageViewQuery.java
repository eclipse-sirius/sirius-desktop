/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import org.eclipse.gmf.runtime.notation.Anchor;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.sequence.business.internal.VerticalRangeFunction;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Lifeline;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.business.internal.util.CacheHelper;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Preconditions;

/**
 * Queries on sequence messages (all kinds).
 * 
 * @author pcdavid
 */
public class SequenceMessageViewQuery {
    /**
     * The message to query.
     */
    private final Edge edge;

    /**
     * Constructor.
     * 
     * @param edge
     *            the message to query.
     */
    public SequenceMessageViewQuery(Edge edge) {
        Preconditions.checkArgument(Message.notationPredicate().apply(edge));
        this.edge = edge;
    }

    /**
     * Tests whether this a reflective message, i.e. both its source and target are in the context of the same lifeline.
     * 
     * @return <code>true</code> if this message is reflective.
     */
    public boolean isReflective() {
        Option<Node> optSource = getSourceLifeline();
        Option<Node> optTarget = getTargetLifeline();
        return optSource.some() && optTarget.some() && optSource.get() == optTarget.get();
    }

    /**
     * Returns the lifeline in the context of which this message is sent.
     * 
     * @return the lifeline in the context of which this message is sent.
     */
    public Option<Node> getSourceLifeline() {
        Option<Node> result = Options.newNone();
        View source = edge.getSource();
        Option<ISequenceElement> iSequenceElement = ISequenceElementAccessor.getISequenceElement(source);
        if (iSequenceElement.some()) {
            Option<Lifeline> lifeline = iSequenceElement.get().getLifeline();
            if (lifeline.some()) {
                result = Options.newSome(lifeline.get().getNotationNode());
            }
        }
        return result;
    }

    /**
     * Returns the lifeline in the context of which this message is received.
     * 
     * @return the lifeline in the context of which this message is received.
     */
    public Option<Node> getTargetLifeline() {
        Option<Node> result = Options.newNone();
        View target = edge.getTarget();
        Option<ISequenceElement> iSequenceElement = ISequenceElementAccessor.getISequenceElement(target);
        if (iSequenceElement.some()) {
            Option<Lifeline> lifeline = iSequenceElement.get().getLifeline();
            if (lifeline.some()) {
                result = Options.newSome(lifeline.get().getNotationNode());
            }
        }
        return result;
    }

    /**
     * Returns the vertical range of the message.
     * 
     * @return the vertical range of the message.
     */
    public Range getVerticalRange() {
        Range result = null;
        if (CacheHelper.isDragTrackerCacheEnabled()) {
            result = CacheHelper.getViewToRangeCache().get(edge);
        }
        if (result == null) {
            RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();
            if (bendpoints == null || bendpoints.getPoints().isEmpty()) {
                result = Range.emptyRange();
            } else {
                int firstY = getFirstPointVerticalPosition(true);
                if (isLogicallyInstantaneous()) {
                    if (validateFirstPointStability(firstY)) {
                        result = new Range(firstY, firstY);
                    } else {
                        int lastY = getLastPointVerticalPosition(false);
                        result = new Range(lastY, lastY);
                    }
                } else {
                    int lastY = getLastPointVerticalPosition(false);
                    if (msgToSelfInvalidEndLocation(edge.getSource(), edge.getTarget())) {
                        firstY = VerticalRangeFunction.INSTANCE.apply(edge.getSource()).getUpperBound();
                        lastY = firstY + LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_VERTICAL_GAP;
                    }
                    result = new Range(Math.min(firstY, lastY), Math.max(firstY, lastY));
                }
            }
            if (CacheHelper.isDragTrackerCacheEnabled()) {
                CacheHelper.getViewToRangeCache().put(edge, result);
            }
        }
        return result;
    }

    /**
     * Use only to check stability. Returns the vertical range of the message calculated with anchor position from
     * source or from target.
     * 
     * @param source
     *            anchor selection for range computation.
     * @return the vertical range of the message from source or target, as requested.
     */
    public Range getVerticalRange(boolean source) {
        Range result;
        RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();
        if (bendpoints == null || bendpoints.getPoints().isEmpty()) {
            result = Range.emptyRange();
        } else {
            int firstY = getFirstPointVerticalPosition(source);
            int lastY = getLastPointVerticalPosition(source);
            result = new Range(Math.min(firstY, lastY), Math.max(firstY, lastY));
        }
        return result;
    }

    /**
     * FIXME this workaround should be remove when business rules will be move from sequence ui to sequence core. This
     * workaround is needed when dropping an execution on messages that will be reconnected.
     * 
     * @param firstY
     *            the vertical position of the first bendpoint
     * @return if the range should be calculated with the other end of the message.
     */
    private boolean validateFirstPointStability(int firstY) {
        // if (getSource() instanceof ISequenceEvent) {
        // ISequenceEvent ise = (ISequenceEvent) getSource();
        // Edge edge = (Edge) this.getNotationView();
        // // Validate that the edge notation source is the same as the SMEP
        // source notation
        // return
        // edge.getSource().getElement().equals(ise.resolveSemanticElement());
        // }
        return true;
    }

    /**
     * .
     * 
     * @param source
     *            .
     * @return .
     */
    public int getFirstPointVerticalPosition(boolean source) {
        RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();
        RelativeBendpoint firstPoint = (RelativeBendpoint) bendpoints.getPoints().get(0);
        return getPointVerticalPosition(firstPoint, source);
    }

    /**
     * 
     * @param otherEnd
     * @return
     */
    private boolean msgToSelfInvalidEndLocation(View end, View otherEnd) {
        // FIXME
        // if (SequenceDiagramNotationHelper.isSequenceEvent(end)) {
        // if (isReflective() && otherEnd != null) {
        // if (EcoreUtil.isAncestor(otherEnd, end)) {
        // return true;
        //
        // }
        // // Point location = ((ISequenceEvent)
        // // end).getFigure().getBounds().getLocation();
        // // return location.x == 0 && location.y == 0;
        // }
        // }
        return false;
    }

    /**
     * .
     * 
     * @param source
     *            .
     * @return .
     */
    public int getLastPointVerticalPosition(boolean source) {
        RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();
        RelativeBendpoint lastPoint = (RelativeBendpoint) bendpoints.getPoints().get(bendpoints.getPoints().size() - 1);
        return getPointVerticalPosition(lastPoint, source);
    }

    /**
     * .
     * 
     * @param bendpoint
     *            .
     * @param source
     *            .
     * @return .
     */
    public int getPointVerticalPosition(RelativeBendpoint bendpoint, boolean source) {
        int verticalPosition = 0;
        if (!source) {
            int tgtRefY = getTargetAnchorVerticalPosition();
            verticalPosition = tgtRefY + bendpoint.getTargetY();
        } else {
            int srcRefY = getSourceAnchorVerticalPosition();
            verticalPosition = srcRefY + bendpoint.getSourceY();
        }
        return verticalPosition;
    }

    /**
     * .
     * 
     * @return .
     */
    public int getSourceAnchorVerticalPosition() {
        IdentityAnchor srcAnchor = null;
        Anchor sourceAnchor = edge.getSourceAnchor();
        if (sourceAnchor instanceof IdentityAnchor) {
            srcAnchor = (IdentityAnchor) sourceAnchor;
        }
        View source = edge.getSource();
        Range sourceRange = new Range(0, 0);
        if (source instanceof Node) {
            boolean cacheEnabled = CacheHelper.isDragTrackerCacheEnabled();
            CacheHelper.setDragTrackerCacheEnabled(false);
            sourceRange = new SequenceNodeQuery((Node) source).getVerticalRange();
            CacheHelper.setDragTrackerCacheEnabled(cacheEnabled);
        }
        return getAnchorAbsolutePosition(srcAnchor, sourceRange);
        // could not return 0 : other utility method take 0,5 precision point
        // for null anchor.
        // return 0;
    }

    /**
     * Should not be called if !(getTarget() instanceof ISequenceEvent).
     * 
     * @return target anchor absolute position.
     */
    public int getTargetAnchorVerticalPosition() {
        IdentityAnchor tgtAnchor = null;
        Anchor targetAnchor = edge.getTargetAnchor();
        if (targetAnchor instanceof IdentityAnchor) {
            tgtAnchor = (IdentityAnchor) targetAnchor;
        }
        View target = edge.getTarget();
        if (target instanceof Node) {
            boolean cacheEnabled = CacheHelper.isDragTrackerCacheEnabled();
            CacheHelper.setDragTrackerCacheEnabled(false);
            Range targetRange = new SequenceNodeQuery((Node) target).getVerticalRange();
            CacheHelper.setDragTrackerCacheEnabled(cacheEnabled);

            return getAnchorAbsolutePosition(tgtAnchor, targetRange);
        }
        return getSourceAnchorVerticalPosition();
        // could not return 0 : other utility method take 0,5 precision point
        // for null anchor.
        // return 0;
    }

    private int getAnchorAbsolutePosition(IdentityAnchor anchor, Range range) {
        double relY = anchor != null ? parseRelY(anchor.getId()) : 0.5;
        return range.getLowerBound() + (int) Math.round(relY * range.width());
    }

    private double parseRelY(String terminal) {
        try {
            return Float.parseFloat(terminal.substring(terminal.indexOf(',') + 1, terminal.indexOf(')')));
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private boolean isLogicallyInstantaneous() {
        return !isReflective();
    }
}
