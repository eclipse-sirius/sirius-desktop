/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.featureextensions;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Stateless services.
 * 
 * @author mchauvin
 */
public interface FeatureExtensionServices {

    /**
     * Get the feature extension description instance from the viewpoint. This
     * method should return null if the input class in not the expected one.
     * 
     * @param <T>
     *            the class which extends {@link Perspective}
     * @param viewpoint
     *            the viewpoint
     * @param clazz
     *            the class expected as return type
     * @return a List of T instances or <code>null</code> if the input class is
     *         not the expected one.
     */
    <T extends FeatureExtensionDescription> List<T> getFeatureExtensionDescriptions(Viewpoint viewpoint, Class<T> clazz);

    /**
     * Save custom data.
     * 
     * @param id
     *            the feature extension name
     * @param session
     *            session used to keep the data.
     * @param associatedInstance
     *            an EObject contained in the resource where the data should be
     *            stored
     * @param data
     *            data to keep.
     */
    void saveFeatureExtensionData(String id, Session session, EObject associatedInstance, DFeatureExtension data);

    /**
     * Retrieve custom data.
     * 
     * @param id
     *            the feature extension name
     * @param session
     *            session used to keep the data.
     * @return the previously persisted data
     */
    Collection<DFeatureExtension> retrieveFeatureExtensionData(String id, Session session);

}
