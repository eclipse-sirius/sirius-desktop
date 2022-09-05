/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.api.refresh;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This registry is intended to provide a set of {@link RepresentationTimeStampInformationSupplier} that allows to
 * decide if the representation time stamp should NOT be updated..
 * 
 * @author lfasani
 */
public final class RepresentationTimeStampInformationSupplierRegistry {
    /**
     * Unique instance of the registry.
     */
    public static final RepresentationTimeStampInformationSupplierRegistry INSTANCE = new RepresentationTimeStampInformationSupplierRegistry();

    private Set<RepresentationTimeStampInformationSupplier> representationTimeStampInformationSupplier = new LinkedHashSet<>();

    private RepresentationTimeStampInformationSupplierRegistry() {
    }

    /**
     * Add the {@link RepresentationTimeStampInformationSupplier} in the registry.
     * 
     * @param supplier
     *            the supplier to add
     */
    public void add(RepresentationTimeStampInformationSupplier supplier) {
        representationTimeStampInformationSupplier.add(supplier);
    }

    /**
     * Remove the {@link RepresentationTimeStampInformationSupplier} in the registry.
     * 
     * @param supplier
     *            the supplier to remove
     */
    public void remove(RepresentationTimeStampInformationSupplier supplier) {
        representationTimeStampInformationSupplier.add(supplier);
    }

    /**
     * Get the registered set of {@link RepresentationTimeStampInformationSupplier}.
     * 
     * @return the set of {@link RepresentationTimeStampInformationSupplier}.
     */
    public Set<RepresentationTimeStampInformationSupplier> getRepresentationTimeStampInformationSuppliers() {
        return representationTimeStampInformationSupplier;
    }
}
