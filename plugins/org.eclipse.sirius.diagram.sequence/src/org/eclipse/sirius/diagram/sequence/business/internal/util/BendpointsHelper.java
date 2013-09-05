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
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import java.util.Collection;
import java.util.List;

import org.eclipse.gmf.runtime.notation.datatype.RelativeBendpoint;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Helper to compare two lists of GMF RelativeBenpoint to help avoid re-setting
 * the same values (which can trigger unwanted notifications).
 * 
 * @author pcdavid
 */
public final class BendpointsHelper {
    private BendpointsHelper() {
        // Prevents instantiations.
    }

    /**
     * Checks whether two objects are lists of GMF Bendpoints which are
     * equivalent (i.e. the same number of points with the same coordinates in
     * the same order).
     * 
     * @param oldValue
     *            the first (old) value.
     * @param newValue
     *            the second (new) value.
     * @return <code>true</code> if the two given values are equivalent lists of
     *         GMF Bendpoints.
     */
    public static boolean areSameBendpoints(Object oldValue, Object newValue) {
        boolean isTouch = false;
        if (oldValue instanceof Collection<?> && newValue instanceof Collection<?>) {
            List<RelativeBendpoint> newPoints = Lists.newArrayList(Iterables.filter((Collection<?>) newValue, RelativeBendpoint.class));
            List<RelativeBendpoint> oldPoints = Lists.newArrayList(Iterables.filter((Collection<?>) oldValue, RelativeBendpoint.class));
            if (newPoints.size() == oldPoints.size()) {
                isTouch = true;
                for (int i = 0; i < newPoints.size(); i++) {
                    RelativeBendpoint newPoint = newPoints.get(i);
                    RelativeBendpoint oldPoint = oldPoints.get(i);

                    boolean sourceTouch = newPoint.getSourceX() == oldPoint.getSourceX() && newPoint.getSourceY() == oldPoint.getSourceY();
                    boolean targetTouch = newPoint.getTargetX() == oldPoint.getTargetX() && newPoint.getTargetY() == oldPoint.getTargetY();

                    if (!(sourceTouch && targetTouch)) {
                        isTouch = false;
                        break;
                    }
                }
            }
        }
        return isTouch;
    }
}
