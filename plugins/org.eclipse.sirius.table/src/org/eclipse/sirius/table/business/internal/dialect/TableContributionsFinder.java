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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.eclipse.sirius.description.contribution.Contribution;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.emf.AllContents;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.internal.contribution.ReuseHelper;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Helper to find all the contributions which should be applied to produce the
 * effective version of a representation description, in the order they should
 * be applied.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class TableContributionsFinder implements Function<Iterable<EObject>, Iterable<Contribution>> {
    private final TableDescription mainDescription;

    /**
     * Constructor.
     * 
     * @param mainDescription
     *            the main description for which we want to produce an effective
     *            version.
     */
    public TableContributionsFinder(TableDescription mainDescription) {
        this.mainDescription = mainDescription;
    }

    /**
     * {@inheritDoc}
     */
    public Iterable<Contribution> apply(Iterable<EObject> from) {
        LinkedHashSet<EObject> sortedRepresentations = getRelevantRepresentations(getAllRepresentations(from));
        ArrayList<EObject> reversed = Lists.newArrayList(sortedRepresentations);
        Collections.reverse(reversed);

        List<Contribution> result = Lists.newArrayList();
        for (EObject root : reversed) {
            Iterables.addAll(result, Iterables.filter(AllContents.of(root, ContributionPackage.eINSTANCE.getContribution(), true), Contribution.class));
        }
        return result;
    }

    private LinkedHashSet<EObject> getRelevantRepresentations(Set<EObject> allRepresentations) {
        LinkedHashSet<EObject> result = Sets.newLinkedHashSet();
        boolean changed = result.add(mainDescription);
        while (changed) {
            changed = false;
            // Add all the Representations we reuse.
            for (RepresentationDescription r1 : Iterables.filter(Lists.newArrayList(result), RepresentationDescription.class)) {
                for (RepresentationDescription r2 : Iterables.filter(allRepresentations, RepresentationDescription.class)) {
                    if (r1 != r2 && reuses(r1, r2)) {
                        changed = changed || result.add(r2);
                    }
                }
            }
            // Add all the RepresentationExtensions which extend any of us.
            for (RepresentationExtensionDescription ext : Iterables.filter(allRepresentations, RepresentationExtensionDescription.class)) {
                for (RepresentationDescription r : Iterables.filter(Lists.newArrayList(result), RepresentationDescription.class)) {
                    if (isExtending(ext, r)) {
                        changed = changed || result.add(ext);
                    }
                }
            }
        }
        return result;
    }

    private boolean reuses(RepresentationDescription user, RepresentationDescription used) {
        return new ReuseHelper().reuses(user, used);
    }

    private boolean isExtending(RepresentationExtensionDescription extension, RepresentationDescription extended) {
        String targetSiriusURI = getTargetSiriusURI(extended);
        String targetRepresentationName = extended.getName();
        return Objects.equal(extension.getViewpointURI(), targetSiriusURI) && Objects.equal(extension.getRepresentationName(), targetRepresentationName);
    }

    private String getTargetSiriusURI(RepresentationDescription target) {
        Option<EObject> parentVp = new EObjectQuery(target).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getViewpoint());
        if (parentVp.some()) {
            Viewpoint vp = (Viewpoint) parentVp.get();
            String uri = getSiriusURI(vp);
            if (uri != null) {
                return uri;
            }
        }
        return null;
    }

    /**
     * Returns the viewpoint URI associated to the given Sirius.
     * 
     * @param vp
     *            the viewpoint to get the URI from
     * @return the viewpoint URI associated to the given Sirius
     */
    protected String getSiriusURI(Viewpoint vp) {
        Option<URI> uri = new ViewpointQuery(vp).getViewpointURI();
        if (uri.some()) {
            return uri.get().toString();
        } else {
            return null;
        }
    }

    private Set<EObject> getAllRepresentations(Iterable<EObject> from) {
        Set<EObject> result = Sets.newHashSet();
        for (EObject eObject : from) {
            Iterables.addAll(
                    result,
                    Iterables.filter(AllContents.of(eObject, true),
                            Predicates.or(Predicates.instanceOf(RepresentationDescription.class), Predicates.instanceOf(RepresentationExtensionDescription.class))));
        }
        return result;
    }
}
