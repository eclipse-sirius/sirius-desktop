/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.internal.pages;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.ui.editor.Messages;
import org.eclipse.sirius.ui.editor.SessionEditorPlugin;
import org.eclipse.sirius.ui.editor.api.pages.PageProvider;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry;

/**
 * This registry dynamically update providers in the given
 * {@link PageProviderRegistry} when plugins providing some are
 * activated/deactivated.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class PluginPageProviderRegistry implements IRegistryEventListener {
    /**
     * Aird editor page provider extension point's id.
     */
    public static final String PAGE_PROVIDER_EXTENSION_POINT_ID = "org.eclipse.sirius.ui.editor.sessionEditorPageProvider"; //$NON-NLS-1$

    /**
     * Page provider element's name.
     */
    private static final String PAGE_PROVIDER_ELEMENT_NAME = "pageProvider"; //$NON-NLS-1$

    /**
     * Page provider's class attribute's name.
     */
    private static final String PAGE_PROVIDER_CLASS_ATTRIBUTE_NAME = "class"; //$NON-NLS-1$

    /**
     * The registry containing all {@link PageProvider} registered.
     */
    private PageProviderRegistry pageRegistry;

    /**
     * A map of {@link IExtension} currently provided by plugins to the
     * {@link PageProvider} they provide.
     */
    private Map<IExtension, List<PageProvider>> extensionToPageProvider;

    /**
     * Initialize this registry with a {@link PageProviderRegistry}.
     * 
     * @param thePageRegistry
     *            the page registry providing pages from registered
     *            {@link PageProvider}.
     */
    public PluginPageProviderRegistry(PageProviderRegistry thePageRegistry) {
        this.pageRegistry = thePageRegistry;
        extensionToPageProvider = new HashMap<>();
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(PAGE_PROVIDER_EXTENSION_POINT_ID).getExtensions()) {
            parseExtension(extension);
        }
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    /**
     * Parse extension. If the extension corresponds to the extension point
     * identified by
     * {@link PluginPageProviderRegistry#PAGE_PROVIDER_EXTENSION_POINT_ID}, then
     * all {@link PageProvider} it provides are registered to the
     * {@link PageProviderRegistry} instance.
     * 
     * @param extension
     *            the extension to parse.
     */
    private void parseExtension(IExtension extension) {
        List<PageProvider> pageProviders = extensionToPageProvider.get(extension);
        if (pageProviders == null) {
            pageProviders = new ArrayList<>();
            extensionToPageProvider.put(extension, pageProviders);
        }
        IConfigurationElement[] configurationElements = extension.getConfigurationElements();
        for (IConfigurationElement configurationElement : configurationElements) {
            if (PAGE_PROVIDER_ELEMENT_NAME.equals(configurationElement.getName())) {
                try {
                    Object newInstance = configurationElement.createExecutableExtension(PAGE_PROVIDER_CLASS_ATTRIBUTE_NAME);
                    if (PageProvider.class.isAssignableFrom(newInstance.getClass())) {
                        PageProvider pageProvider = (PageProvider) newInstance;
                        pageProviders.add(pageProvider);
                        pageRegistry.addPageProvider(pageProvider);
                    } else {
                        SessionEditorPlugin.getPlugin().error(MessageFormat.format(Messages.PluginPageProviderRegistry_badClassType, newInstance.getClass().getName()), null);
                    }
                } catch (CoreException e) {
                    SessionEditorPlugin.getPlugin().error(MessageFormat.format(Messages.PluginPageProviderRegistry_classInitialization, configurationElement.getName()), e);
                }
            }
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            List<PageProvider> pageProviders = extensionToPageProvider.get(extension);

            if (pageProviders != null) {
                for (PageProvider pageProvider : pageProviders) {
                    pageRegistry.removePageProvider(pageProvider);
                }
            }
            extensionToPageProvider.put(extension, null);
        }

    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {

    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {

    }

}
