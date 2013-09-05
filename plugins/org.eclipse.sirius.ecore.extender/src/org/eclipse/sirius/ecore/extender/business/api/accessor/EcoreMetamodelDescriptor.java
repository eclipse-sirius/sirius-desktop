/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.business.api.accessor;

import org.eclipse.emf.ecore.EPackage;

import com.google.common.base.Objects;

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
        if (package1.eResource() != null && package1.eResource().getURI() != null) {
            return package1.eResource().getURI().isPlatformPlugin();
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ePackage == null) ? 0 : ePackage.hashCode());
        result = prime * result + ((nsURI == null) ? 0 : nsURI.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    // CHECKSTYLE:OFF
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final EcoreMetamodelDescriptor other = (EcoreMetamodelDescriptor) obj;

        return ePackage == other.ePackage && Objects.equal(nsURI, other.nsURI);
        // CHECKSTYLE:ON
    }
}
