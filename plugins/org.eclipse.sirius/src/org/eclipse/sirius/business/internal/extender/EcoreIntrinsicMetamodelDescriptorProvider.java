/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

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
    public Collection<MetamodelDescriptor> provides(final Collection<Viewpoint> vps) {
        final Collection<MetamodelDescriptor> result = new ArrayList<MetamodelDescriptor>();

        for (Viewpoint vp : vps) {
            for (final RepresentationDescription desc : new ViewpointQuery(vp).getAllRepresentationDescriptions()) {
                for (final EPackage pak : desc.getMetamodel()) {
                    result.add(getMetamodelDescriptor(pak));
                }
            }
            
            for (final RepresentationExtensionDescription ext : vp.getOwnedRepresentationExtensions()) {
                for (final EPackage pak : ext.getMetamodel()) {
                    result.add(getMetamodelDescriptor(pak));
                }
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
