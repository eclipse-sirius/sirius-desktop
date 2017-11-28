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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.ext.emf.AllContents;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
/**
 * Implementations of a
 * {@code Function&lt;EObject, Iterable&lt;Contribution&gt;&gt;} for the common
 * cases.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public final class ContributionFinder {
    private ContributionFinder() {
        // Prevents instantiation.
    }

    /**
     * Returns a contribution provider with a fixed set of contributions,
     * independently of the input model.
     * 
     * @param contribs
     *            the contributions to provide.
     * @return a function which provides the specified contributions to all
     *         input models.
     */
    public static Function<Iterable<EObject>, Iterable<Contribution>> providing(Contribution... contribs) {
        return providing(Arrays.asList(contribs));
    }

    /**
     * Returns a contribution provider with a fixed set of contributions,
     * independently of the input model.
     * 
     * @param contribs
     *            the contributions to provide.
     * @return a function which provides the specified contributions to all
     *         input models.
     */
    public static Function<Iterable<EObject>, Iterable<Contribution>> providing(final Iterable<Contribution> contribs) {
        return new Function<Iterable<EObject>, Iterable<Contribution>>() {
            public Iterable<Contribution> apply(Iterable<EObject> from) {
                return contribs;
            }
        };
    }

    /**
     * Returns a contribution provider which looks for all the
     * {@link Contribution} elements contained inside the input model (including
     * the input model itself if it is a contribution.
     * 
     * @return a function which finds all the Contribution elements in the input
     *         model.
     */
    public static Function<Iterable<EObject>, Iterable<Contribution>> intrinsic() {
        return new Function<Iterable<EObject>, Iterable<Contribution>>() {
            public Iterable<Contribution> apply(Iterable<EObject> from) {
                List<Contribution> result = new ArrayList<>();
                for (EObject root : from) {
                    Iterables.addAll(result, Iterables.filter(AllContents.of(root, ContributionPackage.eINSTANCE.getContribution(), true), Contribution.class));
                }
                return result;
            }
        };
    }
}
