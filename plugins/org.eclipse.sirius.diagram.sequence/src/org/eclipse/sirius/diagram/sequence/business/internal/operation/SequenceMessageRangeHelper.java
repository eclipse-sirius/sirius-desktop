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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.IdentityAnchor;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.util.Range;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Iterables;

/**
 * A helper to handle the change of vertical range for sequence messages.
 * 
 * @author pcdavid
 */
public class SequenceMessageRangeHelper {
    private static final String TOP_CENTER_TERMINAL = "(0.5, 0.0)"; //$NON-NLS-1$

    /**
     * Sets the range for a normal (non-reflective), horizontal message.
     * 
     * @param edge
     *            the edge representing the message.
     * @param range
     *            the final range of the message. In practice it is a range of
     *            width 1 for horizontal messages.
     * @param sourceTop
     *            the logical vertical position of the top of the source element
     *            of the message.
     * @param targetTop
     *            the logical vertical position of the top of the target element
     *            of the message.
     */
    public void setMessageRangeForNormalMessage(Edge edge, Range range, final int sourceTop, final int targetTop) {
        resetAnchors(edge);

        RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();

        int[] sourceX = getSourceX(bendpoints);
        int[] targetX = getTargetX(bendpoints);
        assert sourceX != null && sourceX.length == 2;
        assert targetX != null && targetX.length == 2;

        /*
         * The vertical offsets from the top of the source/target.
         */
        int sourceDeltaY = range.getLowerBound() - sourceTop;
        int targetDeltaY = range.getLowerBound() - targetTop;

        List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();
        newBendpoints.add(new RelativeBendpoint(sourceX[0], sourceDeltaY, targetX[0], targetDeltaY));
        newBendpoints.add(new RelativeBendpoint(sourceX[1], sourceDeltaY, targetX[1], targetDeltaY));

        bendpoints.setPoints(newBendpoints);
    }

    /**
     * Sets the range for a reflective message.
     * 
     * @param edge
     *            the edge representing the message.
     * @param range
     *            the final range of the message. In practice it is a range of
     *            width 1 for horizontal messages.
     * @param sourceTop
     *            the logical vertical position of the top of the source element
     *            of the message.
     * @param targetTop
     *            the logical vertical position of the top of the target element
     *            of the message.
     */
    public void setMessageRangeForMessageToSelf(Edge edge, Range range, final int sourceTop, final int targetTop) {
        resetAnchors(edge);

        RelativeBendpoints bendpoints = (RelativeBendpoints) edge.getBendpoints();

        if (bendpoints.getPoints().size() == 2) {
            List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();
            RelativeBendpoint firstRB = (RelativeBendpoint) bendpoints.getPoints().get(0);
            RelativeBendpoint secondRB = (RelativeBendpoint) bendpoints.getPoints().get(1);
            newBendpoints.add(firstRB);
            int hGap = LayoutConstants.MESSAGE_TO_SELF_BENDPOINT_HORIZONTAL_GAP;
            Option<Message> msg = ISequenceElementAccessor.getMessage(edge);
            if (msg.some()) {
                hGap = msg.get().getReflexiveMessageWidth();
            }

            newBendpoints.add(new RelativeBendpoint(firstRB.getSourceX() + hGap, firstRB.getSourceY(), firstRB.getTargetX() + hGap, firstRB.getTargetY()));
            newBendpoints.add(new RelativeBendpoint(firstRB.getSourceX() + hGap, secondRB.getSourceY(), firstRB.getTargetX() + hGap, secondRB.getTargetY()));
            newBendpoints.add(secondRB);
            bendpoints.setPoints(newBendpoints);
        }

        int[] sourceX = getSourceX(bendpoints);
        int[] targetX = getTargetX(bendpoints);
        assert sourceX != null && sourceX.length == 4;
        assert targetX != null && targetX.length == 4;

        /*
         * The vertical offsets of the two first/top bendpoints from the top of
         * the source/target.
         */
        int topSourceDeltaY = range.getLowerBound() - sourceTop;
        int topTargetDeltaY = range.getLowerBound() - targetTop;

        /*
         * The vertical offsets of the two last/bottom bendpoints from the top
         * of the source/target.
         */
        int bottomSourceDeltaY = range.getUpperBound() - sourceTop;
        int bottomTargetDeltaY = range.getUpperBound() - targetTop;

        List<RelativeBendpoint> newBendpoints = new ArrayList<RelativeBendpoint>();
        newBendpoints.add(new RelativeBendpoint(sourceX[0], topSourceDeltaY, targetX[0], topTargetDeltaY));
        newBendpoints.add(new RelativeBendpoint(sourceX[1], topSourceDeltaY, targetX[0], topTargetDeltaY));
        newBendpoints.add(new RelativeBendpoint(sourceX[2], bottomSourceDeltaY, targetX[0], bottomTargetDeltaY));
        newBendpoints.add(new RelativeBendpoint(sourceX[3], bottomSourceDeltaY, targetX[0], bottomTargetDeltaY));

        bendpoints.setPoints(newBendpoints);
    }

    private int[] getSourceX(final RelativeBendpoints bendpoints) {
        int[] sourceXs = new int[bendpoints.getPoints().size()];
        int i = 0;
        for (RelativeBendpoint rb : Iterables.filter(bendpoints.getPoints(), RelativeBendpoint.class)) {
            sourceXs[i] = rb.getSourceX();
            i += 1;
        }
        return sourceXs;
    }

    private int[] getTargetX(final RelativeBendpoints bendpoints) {
        int[] targetXs = new int[bendpoints.getPoints().size()];
        int i = 0;
        for (RelativeBendpoint rb : Iterables.filter(bendpoints.getPoints(), RelativeBendpoint.class)) {
            targetXs[i] = rb.getTargetX();
            i += 1;
        }
        return targetXs;
    }

    /**
     * Reset the anchors of an edge to a known, easy to handle location: the
     * center of the top side of the source or target element.
     */
    private void resetAnchors(Edge edge) {
        edge.setSourceAnchor(createCanonicalAnchor());
        edge.setTargetAnchor(createCanonicalAnchor());
    }

    private IdentityAnchor createCanonicalAnchor() {
        IdentityAnchor anchor = NotationFactory.eINSTANCE.createIdentityAnchor();
        anchor.setId(TOP_CENTER_TERMINAL);
        return anchor;
    }

}
