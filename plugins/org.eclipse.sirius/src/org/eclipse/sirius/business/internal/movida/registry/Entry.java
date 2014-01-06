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
package org.eclipse.sirius.business.internal.movida.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.query.ViewpointURIQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Represents Sirius definition known to the registry, with all the
 * associated meta-data about its current status and relations with other
 * Sirius definitions.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class Entry {
    /**
     * The Sirius URI (<code>viewpoint:/...</code>) identifying this entry.
     * This logical identifier never changes for a given entry, even though the
     * actual Sirius it resolves to may come from different resources at
     * different times.
     */
    private final URI identifier;

    /**
     * The concrete Sirius which implements the logical Sirius URI
     * identifying this entry.
     */
    private final Viewpoint viewpoint;

    /**
     * The entries representing the Viewpoints we declares a conflict with.
     */
    private final Set<URI> conflicts = Sets.newHashSet();

    /**
     * The entries representing the Viewpoints we declares a "reuses" relation
     * with.
     */
    private final Set<URI> reused = Sets.newHashSet();

    /**
     * The entries representing the Viewpoints we declares a "extends" relation
     * with.
     */
    private final Set<URI> customizes = Sets.newHashSet();

    /**
     * The current life-cycle state of the Sirius represented by this entry.
     * If <code>null</code>, the entry is new and no status has been computed
     * yet.
     */
    private ViewpointState state;

    /**
     * Any diagnostics related to this entry, in particular errors and warnings
     * which can explain the state of the entry.
     */
    private final Collection<Diagnostic> diagnostics = Lists.newArrayList();

    /**
     * Creates a new entry representing a Sirius.
     * 
     * @param viewpoint
     *            the Sirius this entry represents.
     */
    public Entry(Viewpoint viewpoint) {
        this.viewpoint = Preconditions.checkNotNull(viewpoint);
        Preconditions.checkState(viewpoint.eResource() != null);
        Option<URI> id = new ViewpointQuery(this.viewpoint).getViewpointURI();
        assert id.some();
        this.identifier = id.get();
        this.state = ViewpointState.UNDEFINED;
        fillDepdendencies(this.conflicts, viewpoint.getConflicts());
        fillDepdendencies(this.reused, viewpoint.getReuses());
        fillDepdendencies(this.customizes, viewpoint.getCustomizes());
    }

    /**
     * Returns the logical Sirius URI provided by this entry.
     * 
     * @return the logical Sirius URI provided by this entry.
     */
    public URI getLogicalURI() {
        return identifier;
    }

    /**
     * Returns the concrete Sirius instance which implements this entry's
     * logical URI.
     * 
     * @return the concrete Sirius instance implementing this entry.
     */
    public Viewpoint getSirius() {
        return viewpoint;
    }

    /**
     * Returns the concrete resource from which this entry's implementation
     * comes from.
     * 
     * @return the concrete resource from which this entry's implementation
     *         comes from.
     */
    public Resource getResource() {
        return viewpoint.eResource();
    }

    /**
     * Returns the current state of this entry.
     * 
     * @return the current state of this entry.
     */
    public ViewpointState getState() {
        return state;
    }

    /**
     * Sets the state of this entry.
     * 
     * @param state
     *            the new state for this entry.
     */
    public void setState(ViewpointState state) {
        this.state = Preconditions.checkNotNull(state);
    }

    /**
     * Returns the diagnostics associated to this entry. Clients may modify the
     * list.
     * 
     * @return the diagnostics associated to this entry.
     */
    public Collection<Diagnostic> getDiagnostics() {
        return diagnostics;
    }

    /**
     * Returns the entries of all the Viewpoints this one depends on in any way,
     * i.e. all the Viewpoints which must be present and valid for this one to
     * be usable.
     * 
     * @return all the dependencies of the Sirius represented by this entry.
     */
    public Set<URI> getDeclaredDependencies() {
        Set<URI> result = Sets.newLinkedHashSet();
        /*
         * Note that Sirius we declare a conflict with are not part of our
         * dependencies, because the whole point is that this Sirius should
         * be usable even if they are not present.
         */
        result.addAll(reused);
        result.addAll(customizes);
        return Collections.unmodifiableSet(result);
    }

    private void fillDepdendencies(Set<URI> uris, List<URI> declared) {
        for (URI uri : declared) {
            try {
                if (ViewpointURIQuery.isValidViewpointURI(uri)) {
                    uris.add(uri);
                } else {
                    // TODO: the declared URI is syntactically valid as a URI,
                    // but is not a Sirius URI
                    // report warning and continue.
                }
            } catch (IllegalArgumentException e) {
                // TODO: the declared URI's syntax is invalid, report warning
                // and continue.
            }
        }
    }

    /**
     * Returns the set of actual physical dependencies of this Sirius,
     * ignoring the Sirius environment.
     * 
     * @return the set of actual physical dependencies of this Sirius.
     */
    public Set<URI> getActualDependencies() {
        Set<URI> result = Sets.newHashSet();
        EObjectQuery q = new EObjectQuery(viewpoint);
        result.addAll(q.getResolvedDependencies());
        result.addAll(q.getUnresolvedDependencies());
        result.remove(URIQuery.VIEWPOINT_ENVIRONMENT_QUERY);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return identifier.toString();
    }
}
