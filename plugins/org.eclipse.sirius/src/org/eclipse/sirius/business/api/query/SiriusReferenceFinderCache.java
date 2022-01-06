/**
 * Copyright (c) 2022 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.business.api.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * This class allows to use a static cache to retrieve references associated to an element.
 * 
 * @author Glenn Plouhinec
 *
 */
public final class SiriusReferenceFinderCache {

    /**
     * The ReferenceFinderCache singleton.
     */
    public static final SiriusReferenceFinderCache INSTANCE = new SiriusReferenceFinderCache();

    /**
     * The map which associates elements with their impacted DRepresentationDescriptor.
     */
    private final Map<EObject, Collection<DRepresentationDescriptor>> impactedRepDescriptors = new HashMap<>();

    /**
     * Enable or disable the cache.
     */
    private boolean cacheEnabled;

    /**
     * Creates the instance.
     */
    private SiriusReferenceFinderCache() {
    }

    /**
     * Enable the cache. The cache is cleared when enabled or disabled.
     */
    public void enable() {
        if (cacheEnabled) {
            SiriusPlugin.getDefault().warning(Messages.ReferenceFinderCache_warningAlreadyEnabled, null);
        } else {
            cacheEnabled = true;
            impactedRepDescriptors.clear();
        }
    }

    /**
     * Disable the cache. The cache is cleared when enabled or disabled.
     */
    public void disable() {
        if (cacheEnabled) {
            impactedRepDescriptors.clear();
            cacheEnabled = false;
        }
    }

    /**
     * Return true if cache is enabled; false otherwise.
     * 
     * @return <code>true</code> if cache is enabled; <code>false</code> otherwise.
     */
    public boolean isEnabled() {
        return cacheEnabled;
    }

    /**
     * Returns {@code true} if the cache contains a mapping for the specified key and if the cache is enabled.
     * 
     * @param key
     *            key whose presence in this map is to be tested
     * @return <code>true</code> if cache is enabled and if it contains a mapping for the specified key;
     *         <code>false</code> otherwise.
     */
    public boolean containsKey(EObject key) {
        return cacheEnabled && impactedRepDescriptors.containsKey(key);
    }

    /**
     * Put the key and values to the cache.
     * 
     * @param key
     *            key with which the specified value is to be associated
     * @param values
     *            value to be associated with the specified key
     * @return the previous value associated with key, or <code>null</code> if there was no mapping for key or if the
     *         cache is disabled
     */
    public Collection<DRepresentationDescriptor> put(EObject key, Collection<DRepresentationDescriptor> dRepresentationDescriptors) {
        Collection<DRepresentationDescriptor> addedElements = null;
        if (cacheEnabled) {
            addedElements = impactedRepDescriptors.put(key, dRepresentationDescriptors);
        }
        return addedElements;
    }

    /**
     * Get the values associated to a key.
     * 
     * @param key
     *            the key whose associated value is to be returned
     * @return the values found; <code>null</code> if the cache is disabled.
     */
    public Collection<DRepresentationDescriptor> get(Object key) {
        Collection<DRepresentationDescriptor> values = null;
        if (cacheEnabled) {
            values = impactedRepDescriptors.get(key);
        }
        return values;
    }

    /**
     * Remove an entry from the map.
     * 
     * @param key
     *            the key whose mapping is to be removed from the map
     * @return the removed values; <code>null</code> if the cache is disabled.
     */
    public Collection<DRepresentationDescriptor> remove(Object key) {
        Collection<DRepresentationDescriptor> removedElements = null;
        if (cacheEnabled) {
            removedElements = impactedRepDescriptors.remove(key);
        }
        return removedElements;
    }
}
