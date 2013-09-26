/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.featureExtensions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionUI;
import org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * Class able to manage a set of featureExtensions to provides the usual apects
 * services using the Eclipse environment.
 * 
 * @author mchauvin
 */
public class FeatureExtensionsUIManagerImpl implements FeatureExtensionsUIManager {

    Map<String, FeatureExtensionUI> aspects = new HashMap<String, FeatureExtensionUI>();

    /**
     * Init a default manager implementation.
     * 
     * @return a default manager implementation
     */
    public static FeatureExtensionsUIManager init() {
        final FeatureExtensionsUIManagerImpl manager = new FeatureExtensionsUIManagerImpl();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<FeatureExtensionUI> parsedAspects = EclipseUtil.getExtensionPlugins(FeatureExtensionUI.class, FeatureExtensionsUIManager.ID, FeatureExtensionsUIManager.CLASS_ATTRIBUTE);
            for (final FeatureExtensionUI aspect : parsedAspects) {
                manager.enableAspectUI(aspect);
            }
        }
        return manager;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionUIServices#provideNewChildDescriptors()
     */
    public Collection<CommandParameter> provideNewChildDescriptors() {
        final Collection<CommandParameter> result = new ArrayList<CommandParameter>();
        for (final FeatureExtensionUI aspect : aspects.values()) {
            result.addAll(aspect.getServices().provideNewChildDescriptors());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionUIServices#createAdapterFactory()
     */
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory composed = new ComposedAdapterFactory();
        for (final FeatureExtensionUI aspect : aspects.values()) {
            composed.addAdapterFactory(aspect.getServices().createAdapterFactory());
        }
        return composed;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager#enableAspectUI(org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionUI)
     */
    public void enableAspectUI(final FeatureExtensionUI aspect) {
        aspects.put(aspect.getName(), aspect);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionsUIManager#disableAspectUI(org.eclipse.sirius.ui.business.api.featureExtensions.FeatureExtensionUI)
     */
    public void disableAspectUI(final FeatureExtensionUI aspect) {
        aspects.remove(aspect.getName());
    }
}
