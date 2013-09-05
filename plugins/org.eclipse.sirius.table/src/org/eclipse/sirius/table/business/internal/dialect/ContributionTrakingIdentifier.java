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

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import org.eclipse.sirius.description.contribution.ContributionPoint;
import org.eclipse.sirius.table.metamodel.table.DTable;

/**
 * A function which identifies elements from a representation's effective
 * description according to the VSM origin of the elements, as recorded in the
 * table's contribution points.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ContributionTrakingIdentifier implements Function<EObject, Object> {
    private final DTable table;

    private final Function<EObject, String> pathFunction;

    /**
     * Constructor.
     * 
     * @param table
     *            the representation for which we must identify the effective
     *            description's elements.
     * @param pathFunction
     *            the function to use to get the intrinsic identifiers of the
     *            elements.
     */
    public ContributionTrakingIdentifier(DTable table, Function<EObject, String> pathFunction) {
        this.table = Preconditions.checkNotNull(table);
        this.pathFunction = Preconditions.checkNotNull(pathFunction);
    }

    /**
     * {@inheritDoc}
     */
    public Object apply(EObject from) {
        String currentURI = pathFunction.apply(from);
        if (isInContributionScope(from)) {
            ContributionPoint cp = findMostSpecificContributionPoint(from, table.getEffectiveDescription(), table.getContributionPoints());
            if (cp != null) {
                String parentURI = pathFunction.apply(cp.getContributed());
                String relativeURI = currentURI.replace(parentURI, "");
                return cp.getOrigin() + relativeURI;
            } else {
                throw new RuntimeException("Internal error: contributed element has no matching contribution data.");
            }
        } else {
            return currentURI;
        }
    }

    private boolean isInContributionScope(EObject from) {
        EObject scopeRoot = table.getEffectiveDescription();
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
