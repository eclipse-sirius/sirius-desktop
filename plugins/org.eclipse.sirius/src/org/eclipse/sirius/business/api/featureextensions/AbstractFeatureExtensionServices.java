/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.featureextensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Lists;

/**
 * Base implementation to subclass.
 * 
 * @author mchauvin
 */
public abstract class AbstractFeatureExtensionServices implements FeatureExtensionServices {

    /**
     * get the class which extends {@link FeatureExtensionDescription}.
     * 
     * @return the class
     */
    protected abstract Class<? extends FeatureExtensionDescription> getFeatureExtensionDescriptionClass();

    /**
     * get the class which extends {@link FeatureExtensionDescription}.
     * 
     * @return the class
     */
    protected abstract Class<? extends DFeatureExtension> getFeatureExtensionClass();

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionServices#getFeatureExtensionDescriptions(org.eclipse.sirius.viewpoint.description.Viewpoint,
     *      java.lang.Class)
     */
    public <T extends FeatureExtensionDescription> List<T> getFeatureExtensionDescriptions(final Viewpoint viewpoint, final Class<T> clazz) {
        if (getFeatureExtensionDescriptionClass().isAssignableFrom(clazz)) {
            final List<? super FeatureExtensionDescription> descs = Lists.newArrayList();
            for (FeatureExtensionDescription desc : viewpoint.getOwnedFeatureExtensions()) {
                if (getFeatureExtensionDescriptionClass().isInstance(desc)) {
                    descs.add(desc);
                }
            }
            return (List<T>) descs;
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionServices#retrieveFeatureExtensionData(java.lang.String,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    public Collection<DFeatureExtension> retrieveFeatureExtensionData(final String id, final Session session) {
        final Collection<EObject> data = session.getServices().getCustomData(CustomDataConstants.DFEATUREEXTENSION, null);
        final Collection<DFeatureExtension> conformanceExtensions = new ArrayList<DFeatureExtension>();
        for (final EObject pieceOfData : data) {
            if (getFeatureExtensionClass().isInstance(pieceOfData)) {
                conformanceExtensions.add((DFeatureExtension) pieceOfData);
            }
        }
        return conformanceExtensions;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionServices#saveFeatureExtensionData(java.lang.String,
     *      org.eclipse.sirius.business.api.session.Session,
     *      org.eclipse.emf.ecore.EObject,
     *      org.eclipse.sirius.viewpoint.DFeatureExtension)
     */
    public void saveFeatureExtensionData(final String id, final Session session, final EObject associatedInstance, final DFeatureExtension data) {
        session.getServices().putCustomData(CustomDataConstants.DFEATUREEXTENSION, associatedInstance, data);
    }

}
