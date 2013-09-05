/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.tools.internal.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import org.eclipse.sirius.common.ui.tools.api.util.ISaveDialogExtension;

/**
 * Registry containing all Lock Strategy extensions that have been parsed from
 * the {@link ISaveDialogExtensionRegistryListener#SAVE_DIALOG_EXTENSION_POINT}
 * extension point.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public final class ISaveDialogExtensionRegistry {

    /**
     * The registered {@link ISaveDialogExtension}s.
     */
    private static final Map<ISaveDialogExtension, Collection<ISaveDialogExtensionDescriptor>> EXTENSIONS = Maps.newHashMap();

    /**
     * Utility classes don't need a default constructor.
     */
    private ISaveDialogExtensionRegistry() {

    }

    /**
     * Adds an extension to the registry, with the given behavior.
     * 
     * @param extension
     *            The extension that is to be added to the registry
     */
    public static void addExtension(ISaveDialogExtensionDescriptor extension) {

        ISaveDialogExtension behavior = extension.getSaveDialogExtension();
        if (EXTENSIONS.get(behavior) == null) {
            EXTENSIONS.put(behavior, new HashSet<ISaveDialogExtensionDescriptor>());
        }
        EXTENSIONS.get(behavior).add(extension);
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin
     * stopping.
     */
    public static void clearRegistry() {
        EXTENSIONS.clear();
    }

    /**
     * Returns a copy of the registered extensions list.
     * 
     * @return A copy of the registered extensions list.
     */
    public static Collection<ISaveDialogExtensionDescriptor> getRegisteredExtensions() {
        Set<ISaveDialogExtensionDescriptor> registeredExtensions = Sets.newHashSet();
        for (Collection<ISaveDialogExtensionDescriptor> extensions : EXTENSIONS.values()) {
            registeredExtensions.addAll(extensions);
        }
        return registeredExtensions;
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param extensionClassName
     *            Qualified class name of the sync element which corresponding
     *            phantom is to be removed from the registry.
     */
    public static void removeExtension(String extensionClassName) {
        for (ISaveDialogExtensionDescriptor extension : getRegisteredExtensions()) {
            if (extension.getExtensionClassName().equals(extensionClassName)) {
                EXTENSIONS.get(extension.getSaveDialogExtension()).clear();
            }
        }
    }
}
