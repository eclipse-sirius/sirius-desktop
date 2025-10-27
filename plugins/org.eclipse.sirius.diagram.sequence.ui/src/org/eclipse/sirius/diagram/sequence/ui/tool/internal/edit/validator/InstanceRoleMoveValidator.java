/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Validator for ChangeBoundsRequest of type RequestConstants#REQ_MOVE.
 * 
 * @author edugueperoux
 */
public class InstanceRoleMoveValidator extends AbstractInstanceRoleValidator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(ChangeBoundsRequest request) {
        boolean valid = super.isValid(request);

        if (instanceRoles.size() != 0) {
            SequenceDiagram sequenceDiagram = instanceRoles.get(0).getDiagram();
            List<InstanceRole> allInstanceRoles = sequenceDiagram.getSortedInstanceRole();

            RequestQuery query = new RequestQuery(request);
            Point moveDelta = query.getLogicalDelta().getLocation();

            Collections.sort(instanceRoles, comparator);

            // if move request is constrained then it's a explicit user move
            // request
            // otherwise it's a indirect request from ConnectionCreationTool for
            // a
            // Create Message for example
            if (!request.isConstrainedMove()) {
                moveDelta.setY(0);
            }

            Iterable<Rectangle> notMovedIRBounds = Iterables.transform(Iterables.filter(allInstanceRoles, Predicates.not(Predicates.in(instanceRoles))), ISequenceElement.PROPER_LOGICAL_BOUNDS);

            // Iterate on all instanceRoles to move from the request
            for (InstanceRole instanceRole : instanceRoles) {
                moveDeltas.put(instanceRole, moveDelta.getCopy());

                Rectangle boundBeforeResizing = instanceRole.getBounds();
                Rectangle boundAfterResizing = boundBeforeResizing.getTranslated(moveDelta);

                for (Rectangle notMovedBounds : notMovedIRBounds) {
                    int nmbLeftX = notMovedBounds.getLeft().x;
                    int nmbRightX = notMovedBounds.getRight().x;
                    int mLeftX = boundAfterResizing.getLeft().x;
                    boolean leftOverlapFixIR = nmbLeftX <= mLeftX && mLeftX <= nmbRightX;

                    if (leftOverlapFixIR) {
                        valid = false;
                        break;
                    }
                }
            }
        }
        return valid;
    }
}
