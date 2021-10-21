/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
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
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.ui.Messages;

/**
 * Abstract class to validate InstanceRole move & resize request and get from it
 * a command.
 * 
 * @author edugueperoux
 * 
 */
public abstract class AbstractInstanceRoleValidator {

    /**
     * Common map of future location for instanceRoles in move/resize.
     */
    protected Map<InstanceRole, Point> moveDeltas = new HashMap<InstanceRole, Point>();

    /**
     * Common comparator to have calculated ordering in x coordinate.
     */
    protected InstanceRoleGraphicalHorizontalOrderingComparator comparator = new InstanceRoleGraphicalHorizontalOrderingComparator();

    /**
     * List of instanceRoles in selection of the request.
     */
    protected List<InstanceRole> instanceRoles;

    /**
     * {@inheritDoc}
     */
    public void setSequenceElements(List<InstanceRole> sequenceElements) {
        this.instanceRoles = sequenceElements;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isValid(ChangeBoundsRequest request) {
        Objects.requireNonNull(instanceRoles, Messages.AbstractInstanceRoleValidator_nullInstanceRoleList);

        return true;
    }

    /**
     * Get the index in the instanceRolesToMove left to right ordered list where
     * to insert the current instanceRole in move at the future location
     * locationAfterMoving.
     * 
     * @param locationAfterMoving
     *            the requested location for the instanceRole in move
     * @param instanceRolesToMove
     *            the instanceRolesToMove left to right ordered list
     * @return the index in the instanceRolesToMove left to right ordered list
     *         where to insert the current instanceRole in move at the future
     *         location locationAfterMoving
     */
    protected int getDropIndex(Point locationAfterMoving, List<InstanceRole> instanceRolesToMove) {
        ListIterator<InstanceRole> iterator = instanceRolesToMove.listIterator();
        InstanceRole instanceRoleEditPart;
        int x = 0;
        int i = 0;
        while (iterator.hasNext()) {
            instanceRoleEditPart = iterator.next();
            Bounds bounds = (Bounds) instanceRoleEditPart.getNotationNode().getLayoutConstraint();
            x = bounds.getX();
            if (moveDeltas != null && moveDeltas.containsKey(instanceRoleEditPart)) {
                x += moveDeltas.get(instanceRoleEditPart).x;
            }
            if (locationAfterMoving.x <= x) {
                return i;
            } else {
                i++;
            }
        }
        return i;
    }

    /**
     * Test if dropIndex is same as initialIndex.
     * 
     * @param dropIndex
     *            future index of the instanceRole in move.
     * @param instanceRole
     *            the instanceRole in move
     * 
     * @return true if the dropIndex is same as initialIndex
     */
    protected boolean dropIndexSameAsInitialIndex(int dropIndex, InstanceRole instanceRole) {
        List<InstanceRole> allInstanceRoleEditParts = getOrderedMovableInstanceRoles(Collections.<InstanceRole> emptyList());
        return allInstanceRoleEditParts.indexOf(instanceRole) == dropIndex;
    }

    /**
     * Get a left-to-right ordered list of movable InstanceRole.
     * 
     * @param instanceRoleToMove
     *            the InstanceRole in move
     * 
     * @return the left-to-right ordered list of movable InstanceRole
     */
    protected List<InstanceRole> getOrderedMovableInstanceRoles(List<InstanceRole> instanceRoleToMove) {
        List<InstanceRole> allInstanceRoles = new ArrayList<InstanceRole>(instanceRoles.get(0).getDiagram().getAllInstanceRoles());
        allInstanceRoles.removeAll(instanceRoleToMove);
        Collections.sort(allInstanceRoles, comparator);

        return allInstanceRoles;
    }

    /**
     * Move instanceRoles at the right of the requested location of the
     * instanceRole in move.
     * 
     * @param boundsAfterMoving
     *            requested Bounds of InstanceRole in move
     * @param iteratorForRight
     *            iterator on the left-right ordered list of instanceRoles at
     *            the right of requested location of the instanceRole in move
     */
    protected void moveOverLappedAtTheRightForMoving(Rectangle boundsAfterMoving, ListIterator<InstanceRole> iteratorForRight) {

    }

    public Map<InstanceRole, Point> getMoveDeltas() {
        return moveDeltas;
    }

}
