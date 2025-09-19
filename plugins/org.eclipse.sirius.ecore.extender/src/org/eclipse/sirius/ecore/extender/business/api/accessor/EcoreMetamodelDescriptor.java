/*******************************************************************************
 * Copyright (c) 2007, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import java.util.Objects;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * A descriptors for EPackages.
 * 
 * @author cbrun
 * 
 */
public class EcoreMetamodelDescriptor implements MetamodelDescriptor {

    private String nsURI;

    private EPackage ePackage;

    /**
     * Create a new Ecore descriptor from an nsURI.
     * 
     * @param nsURI
     *            the EPackage nsURI.
     */
    public EcoreMetamodelDescriptor(final String nsURI) {
        this.nsURI = nsURI;
    }

    /**
     * Create a new Ecore descriptor from an EPackage.
     * 
     * @param ePackage
     *            the EPackage.
     */
    public EcoreMetamodelDescriptor(final EPackage ePackage) {
        this.ePackage = ePackage;
    }

    /**
     * Return the EPackage corresponding to the descriptor, null if not found.
     * 
     * @return the EPackage corresponding to the descriptor, null if not found.
     */
    public EPackage resolve() {
        EPackage result = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        if (ePackage != null && ePackage.getNsURI() != null) {
            if (isInPlugin(ePackage) && EPackage.Registry.INSTANCE.getEPackage(ePackage.getNsURI()) != null) {
                result = EPackage.Registry.INSTANCE.getEPackage(ePackage.getNsURI());
            } else {
                result = ePackage;
            }
        }
        return result;

    }

    private boolean isInPlugin(final EPackage package1) {
        Resource package1Resource = package1.eResource();
        if (package1Resource != null && package1Resource.getURI() != null) {
            return package1Resource.getURI().isPlatformPlugin();
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ePackage == null) ? 0 : ePackage.hashCode());
        result = prime * result + ((nsURI == null) ? 0 : nsURI.hashCode());
        return result;
    }

    // CHECKSTYLE:OFF
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EcoreMetamodelDescriptor other = (EcoreMetamodelDescriptor) obj;
        return ePackage == other.ePackage && Objects.equals(nsURI, other.nsURI);
        // CHECKSTYLE:ON
    }
}
