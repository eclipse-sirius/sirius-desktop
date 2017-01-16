/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.properties.core.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Utility class used to store the Sirius description and its matching EEF
 * description.
 * 
 * @author sbegaudeau
 */
public class DescriptionCache {
    /**
     * The map containing the EEF object for a Sirius one.
     */
    private Map<Object, Object> sirius2eef = new HashMap<>();

    /**
     * The map containing the Sirius object for an EEF one.
     */
    private Map<Object, Object> eef2sirius = new HashMap<>();

    /**
     * Puts the Sirius description and its matching EEF description.
     * 
     * @param siriusDescription
     *            The Sirius description
     * @param eefDescription
     *            The EEF Description
     */
    public void put(Object siriusDescription, Object eefDescription) {
        this.sirius2eef.put(siriusDescription, eefDescription);
        this.eef2sirius.put(eefDescription, siriusDescription);
    }

    /**
     * Returns the Sirius description matching the given EEF one.
     * 
     * @param eefDescription
     *            The EEF description
     * @return The Sirius description matching the given EEF one or
     *         <code>null</code> if none matched
     */
    public Object getSiriusDescription(Object eefDescription) {
        return this.eef2sirius.get(eefDescription);
    }

    /**
     * Returns all the Sirius descriptions of the cache.
     * 
     * @return All the Sirius descriptions of the cache.
     */
    public Set<Object> getAllSiriusDescriptions() {
        return Collections.unmodifiableSet(this.sirius2eef.keySet());
    }

    /**
     * Returns the EEF description matching the given Sirius one.
     * 
     * @param siriusDescription
     *            The Sirius description
     * @return The EEF description matching the given Sirius one or
     *         <code>null</code> if none matched
     */
    public Object getEEFDescription(Object siriusDescription) {
        return this.sirius2eef.get(siriusDescription);
    }

    /**
     * Returns all the EEF descriptions of the cache.
     * 
     * @return All the EEF descriptions of the cache.
     */
    public Set<Object> getAllEEFDescriptions() {
        return Collections.unmodifiableSet(this.eef2sirius.keySet());
    }
}
