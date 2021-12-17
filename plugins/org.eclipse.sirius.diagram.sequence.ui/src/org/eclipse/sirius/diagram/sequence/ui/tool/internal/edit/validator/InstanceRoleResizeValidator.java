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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.RequestQuery;

import com.google.common.collect.Iterables;

/**
 * Validator for ChangeBoundsRequest of type RequestConstants#REQ_RESIZE.
 * 
 * @author edugueperoux
 * 
 */
public class InstanceRoleResizeValidator extends AbstractInstanceRoleValidator {

    private Map<InstanceRole, Dimension> sizeDeltas = new HashMap<InstanceRole, Dimension>();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(ChangeBoundsRequest request) {
        boolean valid = super.isValid(request);

        if (instanceRoles.size() != 0) {
            SequenceDiagram sequenceDiagram = instanceRoles.get(0).getDiagram();
            List<InstanceRole> preResizeOrder = sequenceDiagram.getSortedInstanceRole();

            RequestQuery query = new RequestQuery(request);
            Rectangle logicalDelta = query.getLogicalDelta();

            Point moveDelta = logicalDelta.getLocation();
            moveDelta.setY(0);
            Dimension sizeDelta = logicalDelta.getSize();

            for (InstanceRole instanceRole : instanceRoles) {
                moveDeltas.put(instanceRole, moveDelta.getCopy());
                sizeDeltas.put(instanceRole, sizeDelta.getCopy());

                Rectangle boundBeforeResizing = instanceRole.getBounds();
                Rectangle boundAfterResizing = boundBeforeResizing.getTranslated(moveDelta).resize(sizeDelta);

                if (boundAfterResizing.width <= 0) {
                    valid = false;
                }
            }

            // Avoid all reorders
            List<InstanceRole> postResizeOrder = getPostResizeOrder(preResizeOrder);
            if (!Iterables.elementsEqual(preResizeOrder, postResizeOrder)) {
                valid = false;
            }
        }
        return valid;
    }

    private List<InstanceRole> getPostResizeOrder(List<InstanceRole> allInstanceRoles) {
        List<InstanceRole> result = new ArrayList<InstanceRole>(allInstanceRoles);
        comparator.setMoveDeltas(moveDeltas);
        Collections.sort(result, comparator);
        comparator.setMoveDeltas(null);
        return result;
    }

    public Map<InstanceRole, Dimension> getSizeDeltas() {
        return sizeDeltas;
    }

}
