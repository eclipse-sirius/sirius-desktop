/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.decoration;

import java.util.HashSet;
import java.util.Set;

/**
 * Manage registered {@link SiriusDecorationDescriptorProvider}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class SiriusDecorationProviderRegistry {

    /**
     * The unique instance of {@link SiriusDecorationProviderRegistry}.
     */
    public static final SiriusDecorationProviderRegistry INSTANCE = new SiriusDecorationProviderRegistry();

    private Set<SiriusDecorationDescriptorProvider> decorationDescriptorProviders = new HashSet<>();

    private SiriusDecorationProviderRegistry() {
    }

    /**
     * Add a {@link SiriusDecorationDescriptorProvider}.
     * 
     * @param decorationDescriptorProvider
     *            the {@link SiriusDecorationDescriptorProvider} to add
     */
    public void addSiriusDecorationDescriptorProvider(SiriusDecorationDescriptorProvider decorationDescriptorProvider) {
        decorationDescriptorProviders.add(decorationDescriptorProvider);
    }

    /**
     * Remove a {@link SiriusDecorationDescriptorProvider} from the registry.
     * 
     * @param decorationDescriptorProvider
     *            the {@link SiriusDecorationDescriptorProvider} to remove
     */
    public void removeSiriusDecorationDescriptorProvider(SiriusDecorationDescriptorProvider decorationDescriptorProvider) {
        decorationDescriptorProviders.remove(decorationDescriptorProvider);
    }

    /**
     * Remove all registered {@link SiriusDecorationDescriptorProvider}s.
     */
    public void clear() {
        decorationDescriptorProviders.clear();
    }

    public Set<SiriusDecorationDescriptorProvider> getDecorationDescriptorProviders() {
        return decorationDescriptorProviders;
    }

}
