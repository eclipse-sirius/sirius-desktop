/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.extender;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.sirius.business.api.extender.MetamodelDescriptorProvider;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.description.RepresentationDescription;
import org.eclipse.sirius.description.RepresentationExtensionDescription;
import org.eclipse.sirius.description.Sirius;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;

/**
 * Provider for Ecore metamodels.
 * 
 * @author cbrun
 * 
 */
public class EcoreIntrinsicMetamodelDescriptorProvider implements MetamodelDescriptorProvider {
    /**
     * 
     * {@inheritDoc}
     */
    public Collection<MetamodelDescriptor> provides(final Sirius vp) {
        final Collection<MetamodelDescriptor> result = new ArrayList<MetamodelDescriptor>();

        for (final RepresentationDescription desc : new SiriusQuery(vp).getAllRepresentationDescriptions()) {
            for (final EPackage pak : desc.getMetamodel()) {
                result.add(getMetamodelDescriptor(pak));
            }
        }

        for (final RepresentationExtensionDescription ext : vp.getOwnedRepresentationExtensions()) {
            for (final EPackage pak : ext.getMetamodel()) {
                result.add(getMetamodelDescriptor(pak));
            }
        }

        return result;
    }

    private MetamodelDescriptor getMetamodelDescriptor(final EPackage pak) {
        final String nsURI = pak.getNsURI();
        final EPackage registeredPackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        if (registeredPackage != null) {
            return new EcoreMetamodelDescriptor(registeredPackage);
        } else {
            return new EcoreMetamodelDescriptor(pak);
        }
    }

}
