/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.contribution;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.description.contribution.ContributionFactory;
import org.eclipse.sirius.description.contribution.ContributionPoint;

import com.google.common.base.Objects;

/**
 * Helper class to deal with {@link ContributionPoint}s.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public final class ContributionPointHelper {
    private ContributionPointHelper() {
        // Prevent instantiation.
    }

    /**
     * Create a new contribution point with the specified values.
     * 
     * @param point
     *            the contributed object.
     * @param origin
     *            the identifier for the original location of the contributed
     *            object.
     * @return the new {@link ContributionPoint}.
     */
    public static ContributionPoint make(EObject point, String origin) {
        ContributionPoint cp = ContributionFactory.eINSTANCE.createContributionPoint();
        cp.setContributed(point);
        cp.setOrigin(origin);
        return cp;
    }

    /**
     * Updates a list of contribution points to make new data, but only if the
     * new data is not equivalent to the current values. Note that the
     * implementation considers that an update is needed if the contribution
     * points re equivalent but are not in the same order.
     * 
     * @param originalPoints
     *            the original list of contribution points to update.
     * @param newPoints
     *            the new contribution points to consider.
     */
    public static void updateIfNeeded(EList<ContributionPoint> originalPoints, List<ContributionPoint> newPoints) {
        if (!sameContributions(originalPoints, newPoints)) {
            originalPoints.clear();
            originalPoints.addAll(newPoints);
        }
    }

    private static boolean sameContributions(EList<ContributionPoint> contributionPoints, List<ContributionPoint> newPoints) {
        boolean result = true;
        if (contributionPoints.size() != newPoints.size()) {
            result = false;
        } else {
            for (int i = 0; i < contributionPoints.size(); i++) {
                ContributionPoint oldPoint = contributionPoints.get(i);
                ContributionPoint newPoint = newPoints.get(i);
                if (!sameContribution(oldPoint, newPoint)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private static boolean sameContribution(ContributionPoint oldPoint, ContributionPoint newPoint) {
        return Objects.equal(oldPoint.getContributed(), newPoint.getContributed()) && Objects.equal(oldPoint.getOrigin(), newPoint.getOrigin());
    }
}
