/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.common.modelingproject.resourcelistener;

import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener.DefaultModelingProjectResourceListener;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.resourcelistener.IModelingProjectResourceListener;

import com.google.common.collect.Lists;

/**
 * Registry mainiting a list of all contributed
 * {@link IModelingProjectResourceListener}s.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public final class ModelingProjectResourceListenerRegistry {

    private static final String EXTENSION_POINT = "org.eclipse.sirius.ui.modelingprojectresourcelistener"; //$NON-NLS-1$

    private static final Object TAG_EXTENSION = "resourcelistener"; //$NON-NLS-1$

    /**
     * The list of all contributed {@link IModelingProjectResourceListener}s.
     */
    private static List<IModelingProjectResourceListener> registeredModelingProjectResourceListeners = Lists.newArrayList();

    private static IModelingProjectResourceListener defaultModelingProjectResourceListener;

    private static boolean initalContributionParsed;

    /**
     * Avoid instantiation.
     */
    private ModelingProjectResourceListenerRegistry() {
    }

    /**
     * Returns the {@link IModelingProjectResourceListener} to use.
     * 
     * @return the {@link IModelingProjectResourceListener} to use
     */
    public static IModelingProjectResourceListener getModelingProjectResourceListener() {
        if (!initalContributionParsed) {
            parseInitialContributions();
            initalContributionParsed = true;
        }
        if (registeredModelingProjectResourceListeners.iterator().hasNext()) {
            // If any modelingprojectresourcelistener contributed, returns the
            // last registered
            return registeredModelingProjectResourceListeners.get(registeredModelingProjectResourceListeners.size() - 1);
        } else {
            // If none contributed, return default
            return getDefaultModelingProjectResourceListener();
        }
    }

    /**
     * Returns the {@link IModelingProjectResourceListener} to use if none
     * contributed.
     * 
     * @return the {@link IModelingProjectResourceListener} to use if none
     *         contributed
     */
    private static IModelingProjectResourceListener getDefaultModelingProjectResourceListener() {
        if (defaultModelingProjectResourceListener == null) {
            defaultModelingProjectResourceListener = new DefaultModelingProjectResourceListener();
        }
        return defaultModelingProjectResourceListener;
    }

    private static void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * Parses a single extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the
     *            registry.
     */
    private static void parseExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            if (TAG_EXTENSION.equals(elem.getName())) {
                try {
                    registeredModelingProjectResourceListeners.add(new ModelingProjectResourceListenerDescriptor(elem).getModelingProjectResourceListener());
                } catch (IllegalArgumentException e) {
                    // FIXME run failure
                }
            }
        }
    }
}
