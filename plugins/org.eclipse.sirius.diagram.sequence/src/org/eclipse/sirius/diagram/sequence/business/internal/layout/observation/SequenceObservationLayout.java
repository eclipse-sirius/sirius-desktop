/*******************************************************************************
 * Copyright (c) 2012, 2021 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.business.internal.layout.observation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ObservationPoint;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.AbstractSequenceLayout;
import org.eclipse.sirius.diagram.sequence.business.internal.ordering.EventEndHelper;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.ui.business.internal.query.DNodeQuery;
import org.eclipse.sirius.ext.base.Option;

import com.google.common.collect.Maps;

/**
 * Computes the appropriate graphical locations of observation points on a sequence diagram.
 * 
 * @author mporhel
 */
public class SequenceObservationLayout extends AbstractSequenceLayout<ObservationPoint, Point> {

    private Map<EventEnd, ObservationPoint> endToObservationPoint;

    /**
     * Constructor.
     * 
     * @param sequenceDiagram
     *            the sequence diagram for which to compute the observation point locations.
     */
    public SequenceObservationLayout(SequenceDiagram sequenceDiagram) {
        super(sequenceDiagram);
        this.endToObservationPoint = Maps.newHashMap();
    }

    @Override
    protected void init(boolean pack) {
        for (ObservationPoint point : sequenceDiagram.getAllObservationPoints()) {
            Option<EventEnd> observedEventEnd = point.getObservedEventEnd();

            if (observedEventEnd.some()) {
                endToObservationPoint.put(observedEventEnd.get(), point);
            }
        }

    }

    @Override
    protected Map<? extends ObservationPoint, Point> computeLayout(boolean pack) {
        HashMap<ObservationPoint, Point> computedLayout = Maps.newHashMap();

        if (endToObservationPoint.isEmpty()) {
            return computedLayout;
        }

        for (ISequenceEvent ise : sequenceDiagram.getAllDelimitedSequenceEvents()) {
            Rectangle bounds = ise.getProperLogicalBounds();

            List<EventEnd> foundEnds = sequenceDiagram.findEnds(ise);
            for (EventEnd eventEnd : foundEnds) {
                Point refPoint = null;
                ObservationPoint observationPoint = endToObservationPoint.get(eventEnd);
                if (observationPoint != null) {
                    if (eventEnd instanceof SingleEventEnd) {
                        SingleEventEnd see = (SingleEventEnd) eventEnd;
                        if (ise instanceof Message) {
                            Message msg = (Message) ise;
                            if (msg.isReflective()) {
                                refPoint = see.isStart() ? bounds.getTopLeft().getCopy() : bounds.getBottomRight().getCopy();
                            } else {
                                refPoint = see.isStart() ? bounds.getTopLeft().getCopy() : bounds.getBottomRight().getCopy();
                            }
                        } else if (ise instanceof AbstractFrame || ise instanceof Operand) {
                            refPoint = see.isStart() ? bounds.getTopLeft().getCopy() : bounds.getBottomLeft().getCopy();
                        } else {
                            refPoint = see.isStart() ? bounds.getTop().getCopy() : bounds.getBottom().getCopy();
                        }

                    } else if (eventEnd instanceof CompoundEventEnd) {
                        if (ise instanceof State && ise.isLogicallyInstantaneous()) {
                            // getcenter ?
                            refPoint = bounds.getLeft().getCopy();
                        } else if (ise instanceof Execution) {
                            SingleEventEnd see = EventEndHelper.getSingleEventEnd(eventEnd, ise.getSemanticTargetElement().get());
                            refPoint = see.isStart() ? bounds.getTop().getCopy() : bounds.getBottom().getCopy();
                        } else if (ise instanceof Operand) {
                            SingleEventEnd see = EventEndHelper.getSingleEventEnd(eventEnd, ise.getSemanticTargetElement().get());
                            refPoint = see.isStart() ? bounds.getTopLeft().getCopy() : bounds.getBottomLeft().getCopy();
                        }
                    }
                    if (refPoint != null) {
                        computedLayout.put(observationPoint, refPoint);
                    }
                }
            }
        }

        return computedLayout;
    }

    @Override
    protected boolean applyComputedLayout(Map<? extends ObservationPoint, Point> finalLocations, boolean pack) {
        boolean applied = false;
        for (ObservationPoint point : sequenceDiagram.getAllObservationPoints()) {
            Point computedCenter = finalLocations.get(point);
            if (computedCenter != null) {
                Node notationNode = point.getNotationNode();
                Bounds bounds = (Bounds) notationNode.getLayoutConstraint();

                // Center the node on the computed location.
                int midWidth = bounds.getWidth() / 2;
                if (bounds.getWidth() == -1) {
                    midWidth = new DNodeQuery((DNode) point.getNotationNode().getElement()).getDefaultDimension().width / 2;
                }
                int midHeight = bounds.getHeight() / 2;
                if (bounds.getHeight() == -1) {
                    midHeight = new DNodeQuery((DNode) point.getNotationNode().getElement()).getDefaultDimension().height / 2;
                }

                int x = computedCenter.x - midWidth;
                int y = computedCenter.y - midHeight;

                bounds.setX(x);
                bounds.setY(y);
                applied = true;
            }
        }
        return applied;
    }

    @Override
    protected Point getOldLayoutData(ObservationPoint obsPoint) {
        return obsPoint.getObservedLogicalLocation();
    }

    @Override
    protected void dispose() {
        this.endToObservationPoint.clear();

        super.dispose();
    }
}
