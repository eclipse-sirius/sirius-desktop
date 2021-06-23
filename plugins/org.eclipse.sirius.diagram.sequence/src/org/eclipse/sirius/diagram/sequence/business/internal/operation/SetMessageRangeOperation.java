/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.sequence.business.internal.operation;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.sequence.business.api.util.Range;
import org.eclipse.sirius.diagram.sequence.tool.internal.Messages;
import org.eclipse.sirius.diagram.ui.business.internal.operation.AbstractModelChangeOperation;

import com.google.common.base.Objects;

/**
 * An operation to set the vertical range of a sequence message.
 * 
 * @author pcdavid, smonnier
 */
public class SetMessageRangeOperation extends AbstractModelChangeOperation<Void> {

    private Edge message;

    private final Range range;

    private View source;

    private Rectangle sourceBounds;

    private View target;

    private Rectangle targetBounds;

    /**
     * .
     * 
     * @param message
     *            .
     * @param range
     *            .
     */
    public SetMessageRangeOperation(Edge message, Range range) {
        this(message, range, false);
    }

    /**
     * .
     * 
     * @param message
     *            .
     * @param range
     *            .
     * @param copyEdge
     *            .
     */
    public SetMessageRangeOperation(Edge message, Range range, boolean copyEdge) {
        super(Messages.SetMessageRangeOperation_operationName);
        this.message = message;
        this.range = range;
    }

    /**
     * .
     * 
     * @param src
     *            .
     * @param srcBounds
     *            .
     */
    public void setSource(View src, Rectangle srcBounds) {
        this.source = src;
        this.sourceBounds = srcBounds.getCopy();
    }

    /**
     * .
     * 
     * @param tgt
     *            .
     * @param tgtBounds
     *            .
     */
    public void setTarget(View tgt, Rectangle tgtBounds) {
        this.target = tgt;
        this.targetBounds = tgtBounds.getCopy();
    }

    @Override
    public Void execute() {
        if (message.getElement() != null && message.getElement().eContainer() != null) {
            Edge currentMessage = message;

            currentMessage.setSource(source);
            if (currentMessage.getElement() instanceof DEdge && source.getElement() instanceof EdgeTarget) {
                ((DEdge) currentMessage.getElement()).setSourceNode((EdgeTarget) source.getElement());
            }
            currentMessage.setTarget(target);
            if (currentMessage.getElement() instanceof DEdge && target.getElement() instanceof EdgeTarget) {
                ((DEdge) currentMessage.getElement()).setTargetNode((EdgeTarget) target.getElement());
            }

            int srcTop = sourceBounds.getTop().y;
            int tgtTop = targetBounds.getTop().y;
            SequenceMessageRangeHelper helper = new SequenceMessageRangeHelper();
            if (isMessageToSelf(currentMessage)) {
                helper.setMessageRangeForMessageToSelf(currentMessage, range, srcTop, tgtTop);
            } else {
                helper.setMessageRangeForNormalMessage(currentMessage, range, srcTop, tgtTop);
            }
        }
        return null;
    }

    /**
     * FIXME This method should be in an helper class.
     * 
     * @param msg
     *            an Edge
     * @return if the edge is reflexive.
     */
    private boolean isMessageToSelf(Edge msg) {
        View src = msg.getSource();
        View tgt = msg.getTarget();
        return Objects.equal(src, tgt) || Objects.equal(src.eContainer(), tgt) || Objects.equal(src, tgt.eContainer());
    }

}
