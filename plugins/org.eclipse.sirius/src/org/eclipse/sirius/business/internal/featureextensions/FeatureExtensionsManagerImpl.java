/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.featureextensions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.featureextensions.FeatureExtension;
import org.eclipse.sirius.business.api.featureextensions.FeatureExtensionsManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Class able to manage a set of featureExtensions to provides the usual fetaure extension services using the Eclipse
 * environment.
 * 
 * @author mchauvin
 */
public class FeatureExtensionsManagerImpl implements FeatureExtensionsManager {

    Map<String, FeatureExtension> featureExtensions = new HashMap<String, FeatureExtension>();

    /**
     * Init a default manager implementation.
     * 
     * @return a default manager implementation
     */
    public static FeatureExtensionsManager init() {
        final FeatureExtensionsManagerImpl manager = new FeatureExtensionsManagerImpl();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<FeatureExtension> parsedFeatureExtensions = EclipseUtil.getExtensionPlugins(FeatureExtension.class, FeatureExtensionsManager.ID, FeatureExtensionsManager.CLASS_ATTRIBUTE);
            for (final FeatureExtension featureExtension : parsedFeatureExtensions) {
                manager.enableFeatureExtension(featureExtension);
            }
        }
        return manager;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionsManager#disableFeatureExtension(org.eclipse.sirius.business.api.featureextensions.FeatureExtension)
     */
    @Override
    public void disableFeatureExtension(final FeatureExtension featureExtension) {
        featureExtensions.remove(featureExtension.getName());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.dialect.DialectManager#enableDialect(org.eclipse.sirius.business.api.dialect.Dialect)
     */
    @Override
    public void enableFeatureExtension(final FeatureExtension featureExtension) {
        featureExtensions.put(featureExtension.getName(), featureExtension);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionServices#getPerspective(java.lang.Class)
     */
    @Override
    public <T extends FeatureExtensionDescription> List<T> getFeatureExtensionDescriptions(final Viewpoint viewpoint, final Class<T> clazz) {
        for (final FeatureExtension featureExtension : featureExtensions.values()) {
            final List<T> perspectives = featureExtension.getServices().getFeatureExtensionDescriptions(viewpoint, clazz);
            if (perspectives != null) {
                return perspectives;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionServices#saveFeatureExtensionData(java.lang.String,
     *      org.eclipse.sirius.business.api.session.Session, org.eclipse.emf.ecore.EObject,
     *      org.eclipse.sirius.viewpoint.DFeatureExtension)
     */
    @Override
    public void saveFeatureExtensionData(final String id, final Session session, final EObject associatedInstance, final DFeatureExtension data) {
        final FeatureExtension featureExtension = featureExtensions.get(id);
        if (featureExtension != null) {
            featureExtension.getServices().saveFeatureExtensionData(id, session, associatedInstance, data);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.featureextensions.FeatureExtensionServices#retrieveFeatureExtensionData(java.lang.String,
     *      org.eclipse.sirius.business.api.session.Session)
     */
    @Override
    public Collection<DFeatureExtension> retrieveFeatureExtensionData(final String id, final Session session) {
        final FeatureExtension featureExtension = featureExtensions.get(id);
        if (featureExtension != null) {
            return featureExtension.getServices().retrieveFeatureExtensionData(id, session);
        }
        return null;
    }

}
