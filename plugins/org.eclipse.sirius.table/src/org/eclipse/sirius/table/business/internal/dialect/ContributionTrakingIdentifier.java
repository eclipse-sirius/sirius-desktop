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
package org.eclipse.sirius.table.business.internal.dialect;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.description.contribution.ContributionPoint;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

/**
 * A function which identifies elements from a representation's effective
 * description according to the VSM origin of the elements, as recorded in the
 * table's contribution points.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ContributionTrakingIdentifier implements Function<EObject, Object> {
    private final Function<EObject, String> pathFunction;

    private Supplier<EObject> edSupplier;

    private Supplier<Iterable<ContributionPoint>> cpSupplier;

    /**
     * Constructor.
     * 
     * @param edSupplier
     *            a supplier for the effective representations description to
     *            use.
     * @param cpSupplier
     *            a supplier for the contribution points to use.
     * 
     * @param pathFunction
     *            the function to use to get the intrinsic identifiers of the
     *            elements.
     */
    public ContributionTrakingIdentifier(Supplier<EObject> edSupplier, Supplier<Iterable<ContributionPoint>> cpSupplier, Function<EObject, String> pathFunction) {
        this.edSupplier = Preconditions.checkNotNull(edSupplier);
        this.cpSupplier = Preconditions.checkNotNull(cpSupplier);
        this.pathFunction = Preconditions.checkNotNull(pathFunction);
    }

    /**
     * {@inheritDoc}
     */
    public Object apply(EObject from) {
        String currentURI = pathFunction.apply(from);
        if (isInContributionScope(from)) {
            ContributionPoint cp = findMostSpecificContributionPoint(from, edSupplier.get(), cpSupplier.get());
            if (cp != null) {
                String parentURI = pathFunction.apply(cp.getContributed());
                String relativeURI = currentURI.replace(parentURI, ""); //$NON-NLS-1$
                return cp.getOrigin() + relativeURI;
            } else {
                throw new RuntimeException("Internal error: contributed element has no matching contribution data.");
            }
        } else {
            return currentURI;
        }
    }

    private boolean isInContributionScope(EObject from) {
        EObject scopeRoot = edSupplier.get();
        return scopeRoot != null && (from == scopeRoot || EcoreUtil.isAncestor(scopeRoot, from));
    }

    private ContributionPoint findMostSpecificContributionPoint(EObject obj, EObject root, Iterable<ContributionPoint> points) {
        EObject current = obj;
        while (current != root.eContainer()) {
            ContributionPoint cp = findContributionPoint(current, points);
            if (cp != null) {
                return cp;
            }
            current = current.eContainer();
        }
        return null;
    }

    private ContributionPoint findContributionPoint(EObject from, Iterable<ContributionPoint> contributionPoints) {
        // TODO Replace this search by a lookup in an index re-created from
        // findMostSpecificContributionPoint().
        for (ContributionPoint cp : contributionPoints) {
            if (cp.getContributed() == from) {
                return cp;
            }
        }
        return null;
    }
}
