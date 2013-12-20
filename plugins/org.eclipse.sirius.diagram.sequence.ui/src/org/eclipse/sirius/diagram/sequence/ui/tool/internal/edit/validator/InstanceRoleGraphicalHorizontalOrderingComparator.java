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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.validator;

import java.util.Comparator;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;

/**
 * Comparator to have calculated ordering in x coordinate.
 * 
 * @author edugueperoux
 */
public class InstanceRoleGraphicalHorizontalOrderingComparator implements Comparator<InstanceRole> {

    private Map<InstanceRole, Point> moveDeltas;

    /**
     * Default constructor.
     */
    public InstanceRoleGraphicalHorizontalOrderingComparator() {
    }

    public Map<InstanceRole, Point> getMoveDeltas() {
        return moveDeltas;
    }

    public void setMoveDeltas(Map<InstanceRole, Point> moveDeltas) {
        this.moveDeltas = moveDeltas;
    }

    /**
     * {@inheritDoc}
     */
    public int compare(InstanceRole o1, InstanceRole o2) {
        Node o1Node = o1.getNotationNode();
        Bounds o1NodeBounds = (Bounds) o1Node.getLayoutConstraint();
        int x1 = o1NodeBounds.getX();
        if (getMoveDeltas() != null && getMoveDeltas().containsKey(o1)) {
            x1 += getMoveDeltas().get(o1).x;
        }
        Node o2Node = o2.getNotationNode();
        Bounds o2NodeBounds = (Bounds) o2Node.getLayoutConstraint();
        int x2 = o2NodeBounds.getX();
        if (getMoveDeltas() != null && getMoveDeltas().containsKey(o2)) {
            x2 += getMoveDeltas().get(o2).x;
        }
        return x1 - x2;
    }

}
