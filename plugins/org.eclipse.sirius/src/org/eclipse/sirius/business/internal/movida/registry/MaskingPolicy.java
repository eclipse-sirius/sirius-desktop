/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.movida.registry;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.componentization.ViewpointResourceHandler;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
/**
 * The masking policy keeps track of which of the loaded resources are masked
 * and which actually contribute visible Sirius definitions. Only Viewpoints
 * defined in resources which are not masked are visible in the registry. It
 * must be notified by the registry each time a resource has been
 * {@link #resourceLoaded(Resource) loaded} or is about to be
 * {@link #aboutToUnload(Resource) unloaded}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class MaskingPolicy {
    /**
     * A simple class to communicate to clients which resources where newly
     * masked or unmasked.
     */
    public static class MaskingChange {
        Set<Resource> masked = Sets.newHashSet();

        Set<Resource> unmasked = Sets.newHashSet();

        /**
         * Checks the internal consistency of this object.
         * 
         * @return <code>true</code> if the change looks consistent.
         */
        public boolean isConsistent() {
            return Sets.intersection(masked, unmasked).isEmpty();
        }
    }

    /**
     * A simple descriptor of the concrete implementation of a logical Sirius
     * by a specific resource. We use such an descriptor instead of referencing
     * the Viewpoints themselves so that the descriptors stay usable event when
     * the Sirius's resource has been unloaded.
     */
    private static class SiriusImplementation {
        /** The logical Sirius URI. */
        private final URI logicalURI;

        /** The resource which provides the logical Sirius. */
        private final Resource provider;

        public SiriusImplementation(URI logicalURI, Resource provider) {
            this.logicalURI = Preconditions.checkNotNull(logicalURI);
            this.provider = Preconditions.checkNotNull(provider);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return Objects.hashCode(logicalURI, provider);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SiriusImplementation) {
                SiriusImplementation that = (SiriusImplementation) obj;
                return Objects.equal(this.logicalURI, that.logicalURI) && Objects.equal(this.provider, that.provider);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return logicalURI.toString() + " (" + provider.getURI().toString() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * The comparator used to order concurrent implementations of the same
     * logical Sirius URI.
     */
    private Ordering<SiriusImplementation> viewpointComparator;

    /**
     * All the Viewpoints from all the loaded VSMs, whether they are visible or
     * not, organized by their Sirius URI. For each logical Sirius URI,
     * the providers are ordered using <code>viewpointComparator</code>, putting
     * the higher priority at the beginning.
     */
    private final ListMultimap<URI, SiriusImplementation> viewpointImplementations = ArrayListMultimap.create();

    /**
     * For each loaded resource A, gives the sum of the number of Viewpoints
     * from another resource which masks an equivalent Sirius from the
     * resource A. A resource (and all the Viewpoints it defines) is visible if
     * and only if this sum is 0.
     */
    private final LoadingCache<Resource, Integer> score = CacheBuilder.newBuilder().<Resource, Integer>build(CacheLoader.from(Functions.constant(0)));

    /**
     * The handler used to find Sirius instances inside loaded resources.
     */
    private final ViewpointResourceHandler resourceHandler;

    /**
     * Constructor.
     * 
     * @param comparator
     *            the comparator used to determining the priority between
     *            resource for masking computation.
     * @param resourceHandler
     *            the handler used to find Sirius instances inside loaded
     *            resources.
     */
    public MaskingPolicy(Comparator<URI> comparator, ViewpointResourceHandler resourceHandler) {
        this.viewpointComparator = Ordering.from(comparator).onResultOf(new Function<SiriusImplementation, URI>() {
            public URI apply(SiriusImplementation vp) {
                // The ordering used for masking depends only on the resource's
                // priority (as defined by the supplied comparator).
                return vp.provider.getURI();
            }
        }).reverse(); // reverse to have the highest priority elements first.
        this.resourceHandler = Preconditions.checkNotNull(resourceHandler);
    }

    /**
     * This method must be called by the registry right after a new resource has
     * been loaded in the resource set.
     * 
     * @param loaded
     *            the newly loaded resource.
     * @return a description of which resources are newly masked or unmasked by
     *         the change.
     */
    public synchronized MaskingChange resourceLoaded(Resource loaded) {
        Preconditions.checkNotNull(loaded);
        Preconditions.checkState(loaded.isLoaded(), "The resource is not loaded: " + loaded);

        MaskingChange change = new MaskingChange();
        for (Viewpoint viewpoint : resourceHandler.collectViewpointDefinitions(loaded)) {
            Option<URI> uri = new ViewpointQuery(viewpoint).getViewpointURI();
            Preconditions.checkState(uri.some(), "Could not identify logical Sirius URI for Sirius " + viewpoint);
            SiriusImplementation vi = new SiriusImplementation(uri.get(), loaded);
            List<SiriusImplementation> implementations = viewpointImplementations.get(vi.logicalURI);
            int insertionPoint = insertSorted(implementations, vi);
            if (insertionPoint == -1) {
                MaskingChange result = new MaskingChange();
                result.masked.add(loaded);
                return result;
            }

            updateScore(loaded, insertionPoint, change);
            // All viewpoints after the insertion point get their resource's
            // score increased by 1.
            for (int i = insertionPoint + 1; i < implementations.size(); i++) {
                updateScore(implementations.get(i).provider, 1, change);
            }
        }
        return change;
    }

    /**
     * This method must be called by the registry right before it unloads a
     * resource from the resource set. The resource should still be loaded and
     * its content accessible when the method is called.
     * <p>
     * Assumes the resource will actually get unloaded right after this call; if
     * it is not the case, the internal state of the <code>MaskingPolicy</code>
     * might get corrupted.
     * 
     * @param unloaded
     *            the resource which will get unloaded, but is still accessible
     *            at this time.
     * @return a description of which resources are newly masked or unmasked by
     *         the change.
     */
    public synchronized MaskingChange aboutToUnload(final Resource unloaded) {
        Preconditions.checkNotNull(unloaded);
        Preconditions.checkState(unloaded.isLoaded(), "The resource is not loaded: " + unloaded);

        MaskingChange change = new MaskingChange();
        Set<SiriusImplementation> viewpointsFromUnloadedResource = ImmutableSet.copyOf(Iterables.filter(this.viewpointImplementations.values(), new Predicate<SiriusImplementation>() {
            public boolean apply(SiriusImplementation input) {
                return input.provider == unloaded;
            }
        }));
        for (SiriusImplementation vi : viewpointsFromUnloadedResource) {
            List<SiriusImplementation> providers = viewpointImplementations.get(vi.logicalURI);
            int index = removeSorted(providers, vi);

            // All viewpoints which were after the removed one get their
            // resource's score decreased by 1.
            for (int i = index; i < providers.size(); i++) {
                updateScore(providers.get(i).provider, -1, change);
            }
        }
        score.invalidate(unloaded);
        change.masked.add(unloaded);
        assert change.isConsistent();
        return change;
    }

    /**
     * Inserts the viewpoint into the sorted list of providers, keeping the
     * whole list sorted (by viewpointComparator).
     * 
     * @param providers
     *            the list of existing implementers of the logical Sirius.
     *            The list must be sorted according to viewpointComparator.
     * @param viewpoint
     *            a new concrete implementer of the same logical Sirius.
     * @return the index in the list at which the new Sirius has been
     *         inserted to keep the whole properly sorted.
     */
    private int insertSorted(List<SiriusImplementation> providers, SiriusImplementation viewpoint) {
        assert viewpointComparator.isOrdered(providers);
        int x = viewpointComparator.binarySearch(providers, viewpoint);
        if (x >= 0) {
            // This means two VSMs from the same source (and thus the same
            // priority) define the same logical Sirius. This case is not
            // supported: the first one wins and the second VSM (this one) is
            // ignored/masked.
            return -1;
        } else {
            assert x < 0 : "The viewpoint should not already be present in the list";
            int insertionPoint = -x - 1; // See Collections.binarySearch(().
            providers.add(insertionPoint, viewpoint);
            assert viewpointComparator.isOrdered(providers);
            return insertionPoint;
        }
    }

    /**
     * Removes the viewpoint from the sorted list of providers.
     * 
     * @param providers
     *            the list of existing implementers of the logical Sirius.
     *            The list must be sorted according to viewpointComparator.
     * @param viewpoint
     *            a concrete implementer of the logical Sirius to be removed.
     * @return the index in the list at which the Sirius removed was before.
     */
    private int removeSorted(List<SiriusImplementation> providers, SiriusImplementation viewpoint) {
        assert providers != null && providers.contains(viewpoint) : "Trying to remove unknown viewpoint implementation " + viewpoint;
        assert viewpointComparator.isOrdered(providers);
        int index = viewpointComparator.binarySearch(providers, viewpoint);
        providers.remove(viewpoint);
        return index;
    }

    private void updateScore(Resource res, int delta, MaskingChange change) {
        int oldValue;
        try {
            oldValue = score.get(res);
        } catch (ExecutionException e) {
            oldValue = 0;
        }
        int newValue = oldValue + delta;
        assert newValue >= 0;
        score.put(res, newValue);
        if (oldValue == 0 && newValue > 0) {
            change.masked.add(res);
        } else if (oldValue >= 0 && newValue == 0) {
            change.unmasked.add(res);
        }
    }

    /**
     * Tests whether the specified resource is masked.
     * 
     * @param res
     *            a resource loaded in the registry's resource set.
     * @return <code>true</code> iff the resource is masked by at least one
     *         another resource.
     */
    public synchronized boolean isMasked(Resource res) {
        return score.asMap().containsKey(res) && score.getIfPresent(res).intValue() > 0;
    }

    /**
     * Finds all the resources which are not masked by any other.
     * 
     * @return all the unmasked resources.
     */
    public synchronized Set<Resource> getUnmaskedResources() {
        Set<Resource> unmasked = Sets.newHashSet();
        for (Resource resource : score.asMap().keySet()) {
            if (!isMasked(resource)) {
                unmasked.add(resource);
            }
        }
        return ImmutableSet.copyOf(unmasked);
    }

    /**
     * Finds all the resources which are masked by at least one another.
     * 
     * @return all the loaded but masked resources.
     */
    public synchronized Set<Resource> getMaskedResources() {
        return ImmutableSet.copyOf(Sets.difference(score.asMap().keySet(), getUnmaskedResources()));
    }
}
