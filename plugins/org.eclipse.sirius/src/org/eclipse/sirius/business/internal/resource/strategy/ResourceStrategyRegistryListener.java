/*******************************************************************************
 * Copyright (c) 2016, 2021 Obeo.
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
package org.eclipse.sirius.business.internal.resource.strategy;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategy;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategyRegistry;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * This listener will allow us to be aware of contribution changes against the resourceStrategy extension point.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class ResourceStrategyRegistryListener implements IRegistryEventListener {

    /** Name of the extension point to parse for extensions. */
    public static final String RESOURCE_STRATEGY_EXTENSION_POINT = SiriusPlugin.ID + ".resourceStrategy"; //$NON-NLS-1$

    /** Name of the extension point's "resourceStrategy" tag. */
    private static final String RESOURCE_STRATEGY_TAG_EXTENSION = "resourceStrategy"; //$NON-NLS-1$

    /** Name of the resourceStrategy extension point's tag "class" attribute. */
    private static final String RESOURCE_STRATEGY_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /**
     * Register this listener and parse initial contributions.
     */
    public void init() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addListener(this, RESOURCE_STRATEGY_EXTENSION_POINT);
        parseInitialContributions();
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (RESOURCE_STRATEGY_TAG_EXTENSION.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(RESOURCE_STRATEGY_CLASS_ATTRIBUTE);
                    Collection<ResourceStrategy> resourceStrategies = ResourceStrategyRegistry.getInstance().getProvidedResourceStrategies();
                    for (ResourceStrategy resourceStrategy : resourceStrategies) {
                        if (extensionClassName.equals(resourceStrategy.getClass().getName())) {
                            ResourceStrategyRegistry.getInstance().removeResourceStrategy(resourceStrategy);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Though this listener reacts to the extension point changes, there could have been contributions before it's been
     * registered. This will parse these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(RESOURCE_STRATEGY_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * Parses a single extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the registry.
     */
    private void parseExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            try {
                Object contribution = elem.createExecutableExtension(RESOURCE_STRATEGY_CLASS_ATTRIBUTE); // $NON-NLS-1$
                if (contribution instanceof ResourceStrategy) {
                    ResourceStrategyRegistry.getInstance().addResourceStrategy((ResourceStrategy) contribution);
                }
            } catch (CoreException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(Status.WARNING, SiriusPlugin.ID, Messages.AbstractSiriusMigrationService_contributionInstantiationErrorMsg, e));
            }
        }
    }

    /**
     * Remove this listener and flush the associated registry.
     */
    public void dispose() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeListener(this);
        ResourceStrategyRegistry.getInstance().dispose();
    }

}
