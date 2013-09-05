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
package org.eclipse.sirius.business.internal.movida;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import org.eclipse.sirius.business.api.query.SiriusURIQuery;
import org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry;
import org.eclipse.sirius.description.Sirius;

/**
 * Represents a set of logical Siriuss, for example the ones requested by a
 * user, and provides validation on the consistency of the selection.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class SiriusSelection {
    private static final String INVALID_VIEWPOINT_URI = "Selection must contain only logical Sirius URIs";

    /**
     * The set of logical Sirius URIs which make up the selection itself.
     */
    private final Set<URI> logicalSiriuss = Sets.newHashSet();

    /**
     * The registry to use for Sirius informations.
     */
    private final SiriusRegistry registry;

    /**
     * True if the selection has changed since the last time validation was
     * performed.
     */
    private boolean needsValidation;

    /**
     * Whether or not the selection is consistent. The value of this field is
     * only meaningful when <code>needsValidation</code> is false.
     */
    private boolean isValid;

    private Set<URI> unavailable = Collections.emptySet();

    private Set<URI> conflicts = Collections.emptySet();

    private final Predicate<URI> isValidSiriusURI = new Predicate<URI>() {
        public boolean apply(URI input) {
            return SiriusURIQuery.isValidSiriusURI(input);
        }
    };

    private final Function<URI, Sirius> viewpointImplementation = new Function<URI, Sirius>() {
        public Sirius apply(URI from) {
            return registry.getSirius(from);
        }
    };

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to use for Sirius informations.
     */
    public SiriusSelection(SiriusRegistry registry) {
        this.registry = Preconditions.checkNotNull(registry);
    }

    /**
     * Return the current selection. It may not be consistent.
     * 
     * @return the current selection.
     */
    public Set<URI> getSelected() {
        return ImmutableSet.copyOf(logicalSiriuss);
    }

    /**
     * Sets the whole set of selected Siriuss at once.
     * 
     * @param selection
     *            the logical URIs of all the Siriuss to put in the
     *            selection.
     */
    public void setSelected(Set<URI> selection) {
        Preconditions.checkArgument(Iterables.all(selection, isValidSiriusURI), INVALID_VIEWPOINT_URI);
        logicalSiriuss.clear();
        logicalSiriuss.addAll(selection);
        needsValidation = true;
    }

    /**
     * Add a single Sirius to the selection.
     * 
     * @param viewpoint
     *            the logical URI of the Sirius to add.
     */
    public void select(URI viewpoint) {
        Preconditions.checkArgument(isValidSiriusURI.apply(viewpoint), INVALID_VIEWPOINT_URI);
        boolean added = logicalSiriuss.add(viewpoint);
        if (added) {
            needsValidation = true;
        }
    }

    /**
     * Remove a single Sirius from the selection.
     * 
     * @param viewpoint
     *            the logical URI of the Sirius to remove.
     */
    public void deselect(URI viewpoint) {
        Preconditions.checkArgument(isValidSiriusURI.apply(viewpoint), INVALID_VIEWPOINT_URI);
        boolean removed = logicalSiriuss.remove(viewpoint);
        if (removed) {
            needsValidation = true;
        }
    }

    /**
     * Check whether the current set of selected Sirius is consistent, i.e.
     * all the requested Siriuss are available and there are no internal
     * conflicts between any two pairs of Siriuss.
     * 
     * @return <code>true</code> if the current selection is consistent.
     */
    public boolean isValid() {
        validate();
        return isValid;
    }

    /**
     * Returns the set of logical URIs which are in the selection but are not
     * currently available in the registry, making the selection invalid.
     * 
     * @return the URIs of the logical viewpoints which are selected but not
     *         available.
     */
    public Set<URI> getUnavailableSiriuss() {
        return ImmutableSet.copyOf(unavailable);
    }

    /**
     * Returns the set of logical URIs which are in the selection but are in
     * conflict with at least one another selected Sirius.
     * 
     * @return the URIs of the logical viewpoints which are selected but for
     *         which there is a conflict.
     */
    public Set<URI> getConflictingSiriuss() {
        return ImmutableSet.copyOf(conflicts);
    }

    /**
     * Recompute the validity of the current selection.
     */
    public void validate() {
        if (needsValidation) {
            isValid = allRequiredSiriussAreAvailable() && noConflictsBetweenRequiredSiriuss();
            needsValidation = false;
        }
    }

    /**
     * Returns a message describing the validation errors, if any.
     * 
     * @return a message describing the validation errors.
     */
    public String getErrorMessage() {
        validate();
        Function<URI, String> shortName = new Function<URI, String>() {
            public String apply(URI uri) {
                return new SiriusURIQuery(uri).getSiriusName();
            }
        };
        // CHECKSTYLE:OFF
        StringBuilder sb = new StringBuilder();
        if (conflicts != null && !conflicts.isEmpty()) {
            sb.append("Conflicts with: " + Joiner.on(", ").join(Iterables.transform(conflicts, shortName))).append("\n");
        }
        if (unavailable != null && !unavailable.isEmpty()) {
            sb.append("Missing: " + Joiner.on(", ").join(Iterables.transform(unavailable, shortName))).append("\n");
        }
        // CHECKSTYLE:ON
        return sb.toString();
    }

    private boolean allRequiredSiriussAreAvailable() {
        unavailable = Sets.newHashSet(Iterables.filter(logicalSiriuss, new Predicate<URI>() {
            public boolean apply(URI input) {
                return viewpointImplementation.apply(input) == null;
            }
        }));
        return unavailable.isEmpty();
    }

    private boolean noConflictsBetweenRequiredSiriuss() {
        Set<URI> forbidden = Sets.newHashSet();
        for (URI uri : logicalSiriuss) {
            Sirius vp = viewpointImplementation.apply(uri);
            if (vp != null) {
                Iterables.addAll(forbidden, vp.getConflicts());
            }
        }
        conflicts = Sets.intersection(logicalSiriuss, forbidden);
        return conflicts.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        List<URI> uris = Lists.newArrayList(logicalSiriuss);
        Collections.sort(uris, Ordering.usingToString());
        return "Sirius Selection: " + Joiner.on(", ").join(uris);
    }
}
