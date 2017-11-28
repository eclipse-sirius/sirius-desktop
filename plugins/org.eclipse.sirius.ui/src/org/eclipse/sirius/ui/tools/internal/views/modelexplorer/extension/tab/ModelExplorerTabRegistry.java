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
package org.eclipse.sirius.ui.tools.internal.views.modelexplorer.extension.tab;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.sirius.common.tools.api.util.StringUtil;

import com.google.common.collect.Lists;

/**
 * Registry containing all  model explorer tab  extensions that have been parsed from
 * the
 * {@link ModelExplorerTabRegistryListener#MODEL_EXPLORER_TAB_EXTENSION_POINT}
 * extension point.
 * 
 * @author mporhel
 * 
 */
public final class ModelExplorerTabRegistry {

    /**
     * The registered {@link ModelExplorerTabDescriptor}s.
     */
    private static final Collection<ModelExplorerTabDescriptor> EXTENSIONS = new ArrayList<>();

    /**
     * Utility classes don't need a default constructor.
     */
    private ModelExplorerTabRegistry() {

    }

    /**
     * Adds an extension to the registry, with the given behavior.
     * 
     * @param extension
     *            The extension that is to be added to the registry
     */
    public static void addExtension(ModelExplorerTabDescriptor extension) {
        EXTENSIONS.add(extension);
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
    public static Collection<ModelExplorerTabDescriptor> getRegisteredExtensions() {
        return Lists.newArrayList(EXTENSIONS);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param extensionClassName
     *            Qualified class name of the sync element which corresponding
     *            phantom is to be removed from the registry.
     */
    public static void removeExtension(String extensionClassName) {
        for (ModelExplorerTabDescriptor extension : getRegisteredExtensions()) {
            if (extension.getExtensionClassName().equals(extensionClassName)) {
                EXTENSIONS.remove(extension);
            }
        }
    }

    /**
     * Get the extension tab with the given id.
     * 
     * @param id
     *            the requested id.
     * 
     * @return the tab extension with the requested id if it exists.
     */
    public static ModelExplorerTabDescriptor getRegisteredExtension(String id) {
        for (ModelExplorerTabDescriptor desc : EXTENSIONS) {
            if (!StringUtil.isEmpty(desc.getId()) && desc.getId().equals(id)) {
                return desc;
            }
        }
        return null;
    }
}
