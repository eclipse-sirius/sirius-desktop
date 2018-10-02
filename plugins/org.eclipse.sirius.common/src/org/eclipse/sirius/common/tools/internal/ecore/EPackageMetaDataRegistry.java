/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.ecore;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.sirius.common.tools.api.ecore.EPackageMetaData;
import org.eclipse.sirius.common.tools.api.util.StringUtil;

/**
 * A plain registry of {@link EPackageMetaData}, independent on where the source data come from.
 * 
 * @author pcdavid
 */
public class EPackageMetaDataRegistry {

    private final Map<String, EPackageMetaData> entries = new HashMap<>();

    /**
     * Registers a new {@link EPackageMetaData}.
     * 
     * @param data
     *            the {@link EPackageMetaData}
     */
    public synchronized void register(EPackageMetaData data) {
        entries.put(data.getNsURI(), data);
    }

    /**
     * Unregisters the {@link EPackageMetaData} for a specific nsURI. Does nothing if not {@link EPackageMetaData} is
     * registered for this URI.
     * 
     * @param nsURI
     *            the nsURI of the entry to remove.
     */
    public synchronized void unregister(String nsURI) {
        if (!StringUtil.isEmpty(nsURI)) {
            entries.remove(nsURI);
        }
    }

    /**
     * Unregisters all known {@link EPackageMetaData}.
     */
    public synchronized void clear() {
        entries.clear();
    }

    /**
     * Returns the extra data associated to the specified nsURI, if any.
     * 
     * @param nsURI
     *            the nsURI of the EPackage to look for.
     * @return the associated extra data, may be <code>null</code>.
     */
    public synchronized EPackageMetaData getExtraData(String nsURI) {
        return entries.get(nsURI);
    }
}
