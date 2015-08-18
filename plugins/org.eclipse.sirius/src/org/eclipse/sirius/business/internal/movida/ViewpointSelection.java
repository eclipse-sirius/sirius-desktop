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
import org.eclipse.sirius.business.api.query.ViewpointURIQuery;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

/**
 * Represents a set of logical Viewpoints, for example the ones requested by a
 * user, and provides validation on the consistency of the selection.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ViewpointSelection {
    private static final String INVALID_VIEWPOINT_URI = "Selection must contain only logical Viewpoint URIs";

    /**
     * The set of logical Sirius URIs which make up the selection itself.
     */
    private final Set<URI> logicalViewpoints = Sets.newHashSet();

    /**
     * The registry to use for viewpoint informations.
     */
    private final ViewpointRegistry registry;

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

    private final Predicate<URI> isValidViewpointURI = new Predicate<URI>() {
        public boolean apply(URI input) {
            return ViewpointURIQuery.isValidViewpointURI(input);
        }
    };

    private final Function<URI, Viewpoint> viewpointImplementation = new Function<URI, Viewpoint>() {
        public Viewpoint apply(URI from) {
            return registry.getViewpoint(from);
        }
    };

    /**
     * Constructor.
     * 
     * @param registry
     *            the registry to use for viewpoint informations.
     */
    public ViewpointSelection(ViewpointRegistry registry) {
        this.registry = Preconditions.checkNotNull(registry);
    }

    /**
     * Return the current selection. It may not be consistent.
     * 
     * @return the current selection.
     */
    public Set<URI> getSelected() {
        return ImmutableSet.copyOf(logicalViewpoints);
    }

    /**
     * Sets the whole set of selected viewpoints at once.
     * 
     * @param selection
     *            the logical URIs of all the viewpoints to put in the
     *            selection.
     */
    public void setSelected(Set<URI> selection) {
        Preconditions.checkArgument(Iterables.all(selection, isValidViewpointURI), INVALID_VIEWPOINT_URI);
        logicalViewpoints.clear();
        logicalViewpoints.addAll(selection);
        needsValidation = true;
    }

    /**
     * Add a single viewpoint to the selection.
     * 
     * @param viewpoint
     *            the logical URI of the viewpoint to add.
     */
    public void select(URI viewpoint) {
        Preconditions.checkArgument(isValidViewpointURI.apply(viewpoint), INVALID_VIEWPOINT_URI);
        boolean added = logicalViewpoints.add(viewpoint);
        if (added) {
            needsValidation = true;
        }
    }

    /**
     * Remove a single viewpoint from the selection.
     * 
     * @param viewpoint
     *            the logical URI of the viewpoint to remove.
     */
    public void deselect(URI viewpoint) {
        Preconditions.checkArgument(isValidViewpointURI.apply(viewpoint), INVALID_VIEWPOINT_URI);
        boolean removed = logicalViewpoints.remove(viewpoint);
        if (removed) {
            needsValidation = true;
        }
    }

    /**
     * Check whether the current set of selected viewpoints is consistent, i.e.
     * all the requested viewpoints are available and there are no internal
     * conflicts between any two pairs of viewpoints.
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
    public Set<URI> getUnavailableViewpoints() {
        return ImmutableSet.copyOf(unavailable);
    }

    /**
     * Returns the set of logical URIs which are in the selection but are in
     * conflict with at least one another selected viewpoint.
     * 
     * @return the URIs of the logical viewpoints which are selected but for
     *         which there is a conflict.
     */
    public Set<URI> getConflictingViewpoints() {
        return ImmutableSet.copyOf(conflicts);
    }

    /**
     * Recompute the validity of the current selection.
     */
    public void validate() {
        if (needsValidation) {
            isValid = allRequiredViewpointAreAvailable() && noConflictsBetweenRequiredViewpoints();
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
                return new ViewpointURIQuery(uri).getViewpointName();
            }
        };
        // CHECKSTYLE:OFF
        StringBuilder sb = new StringBuilder();
        if (conflicts != null && !conflicts.isEmpty()) {
            sb.append("Conflicts with: " + Joiner.on(", ").join(Iterables.transform(conflicts, shortName))).append("\n"); //$NON-NLS-2$ //$NON-NLS-3$
        }
        if (unavailable != null && !unavailable.isEmpty()) {
            sb.append("Missing: " + Joiner.on(", ").join(Iterables.transform(unavailable, shortName))).append("\n"); //$NON-NLS-2$ //$NON-NLS-3$
        }
        // CHECKSTYLE:ON
        return sb.toString();
    }

    private boolean allRequiredViewpointAreAvailable() {
        unavailable = Sets.newHashSet(Iterables.filter(logicalViewpoints, new Predicate<URI>() {
            public boolean apply(URI input) {
                return viewpointImplementation.apply(input) == null;
            }
        }));
        return unavailable.isEmpty();
    }

    private boolean noConflictsBetweenRequiredViewpoints() {
        Set<URI> forbidden = Sets.newHashSet();
        for (URI uri : logicalViewpoints) {
            Viewpoint vp = viewpointImplementation.apply(uri);
            if (vp != null) {
                Iterables.addAll(forbidden, vp.getConflicts());
            }
        }
        conflicts = Sets.intersection(logicalViewpoints, forbidden);
        return conflicts.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        List<URI> uris = Lists.newArrayList(logicalViewpoints);
        Collections.sort(uris, Ordering.usingToString());
        return "Viewpoint Selection: " + Joiner.on(", ").join(uris); //$NON-NLS-2$
    }
}
