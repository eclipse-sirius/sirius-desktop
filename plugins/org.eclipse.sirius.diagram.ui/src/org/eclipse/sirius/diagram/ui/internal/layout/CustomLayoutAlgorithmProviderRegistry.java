/*******************************************************************************
 * Copyright (c) 2018 Obeo
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
package org.eclipse.sirius.diagram.ui.internal.layout;

import java.text.MessageFormat;
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
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithm;
import org.eclipse.sirius.diagram.ui.api.layout.CustomLayoutAlgorithmProvider;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;

/**
 * This registry dynamically update providers in the given custom layout algorithm registry when plugins providing some
 * are activated/deactivated.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class CustomLayoutAlgorithmProviderRegistry implements IRegistryEventListener {
    /**
     * Custom layout algorithm provider extension point's id.
     */
    public static final String LAYOUT_ALGORITHM_PROVIDER_EXTENSION_POINT_ID = "org.eclipse.sirius.diagram.ui.customLayoutAlgorithmProvider"; //$NON-NLS-1$

    /**
     * Page provider element's name.
     */
    private static final String LAYOUT_ALGORITHM_PROVIDER_ELEMENT_NAME = "customLayoutProvider"; //$NON-NLS-1$

    /**
     * Page provider's class attribute's name.
     */
    private static final String LAYOUT_ALGORITHM_PROVIDER_CLASS_ATTRIBUTE_NAME = "class"; //$NON-NLS-1$

    /**
     * A map of {@link IExtension} currently provided by plugins to the {@link CustomLayoutAlgorithmProvider} they
     * provide.
     */
    private Map<IExtension, CustomLayoutAlgorithmProvider> extensionToLayoutAlgorithmProvider;

    /**
     * A registry containing all layout providers that can be specified directly in the VSM. A layout provider provides
     * a layout algorithm that can be used when doing an arrange all on a Sirius diagram.
     */
    private Map<String, CustomLayoutAlgorithm> layoutProviderRegistry;

    /**
     * Initialize this registry with a {@link PageProviderRegistry}.
     * 
     * @param thePageRegistry
     *            the page registry providing pages from registered {@link CustomLayoutAlgorithmProvider}.
     */
    public CustomLayoutAlgorithmProviderRegistry(Map<String, CustomLayoutAlgorithm> layoutProviderRegistry) {
        this.layoutProviderRegistry = layoutProviderRegistry;
        extensionToLayoutAlgorithmProvider = new HashMap<>();
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(LAYOUT_ALGORITHM_PROVIDER_EXTENSION_POINT_ID).getExtensions()) {
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
     * Parse extension. If the extension corresponds to the extension point identified by
     * {@link CustomLayoutAlgorithmProviderRegistry#LAYOUT_ALGORITHM_PROVIDER_EXTENSION_POINT_ID}, then all
     * {@link CustomLayoutAlgorithmProvider} it provides are registered to the layout provider registry instance.
     * 
     * @param extension
     *            the extension to parse.
     */
    private void parseExtension(IExtension extension) {
        CustomLayoutAlgorithmProvider layoutAlgorithmProvider = extensionToLayoutAlgorithmProvider.get(extension);
        if (layoutAlgorithmProvider == null) {

            IConfigurationElement[] configurationElements = extension.getConfigurationElements();
            for (IConfigurationElement configurationElement : configurationElements) {
                if (LAYOUT_ALGORITHM_PROVIDER_ELEMENT_NAME.equals(configurationElement.getName())) {
                    try {
                        Object newInstance = configurationElement.createExecutableExtension(LAYOUT_ALGORITHM_PROVIDER_CLASS_ATTRIBUTE_NAME);
                        if (CustomLayoutAlgorithmProvider.class.isAssignableFrom(newInstance.getClass())) {
                            CustomLayoutAlgorithmProvider CustomAlgorithmProvider = (CustomLayoutAlgorithmProvider) newInstance;
                            List<CustomLayoutAlgorithm> customLayoutAlgorithms = CustomAlgorithmProvider.getCustomLayoutAlgorithms();
                            for (CustomLayoutAlgorithm customLayoutAlgorithm : customLayoutAlgorithms) {
                                layoutProviderRegistry.put(customLayoutAlgorithm.getId(), customLayoutAlgorithm);
                            }
                        } else {
                            DiagramUIPlugin.getPlugin().error(MessageFormat.format(Messages.LayoutAlgorithmProviderRegistry_badClassType, newInstance.getClass().getName()), null);
                        }
                    } catch (CoreException e) {
                        DiagramUIPlugin.getPlugin().error(MessageFormat.format(Messages.LayoutAlgorithmProviderRegistry_classInitialization, configurationElement.getName()), e);
                    }
                }
            }
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            CustomLayoutAlgorithmProvider layoutAlgorithmProvider = extensionToLayoutAlgorithmProvider.get(extension);

            if (layoutAlgorithmProvider != null) {
                List<CustomLayoutAlgorithm> customLayoutAlgorithms = layoutAlgorithmProvider.getCustomLayoutAlgorithms();
                for (CustomLayoutAlgorithm customLayoutAlgorithm : customLayoutAlgorithms) {
                    layoutProviderRegistry.remove(customLayoutAlgorithm.getId());
                }
            }
            extensionToLayoutAlgorithmProvider.remove(extension);
        }

    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {

    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {

    }

}
