/*******************************************************************************
 * Copyright (c) 2017 Obeo.
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
package org.eclipse.sirius.business.internal.helper.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will contain all the tasks extension that have been parsed from
 * the extension point.
 * 
 * @author sbegaudeau
 */
public final class ModelOperationManagerRegistry {
    /** List of extensions created from the extension point contributions. */
    private static final List<ModelOperationManagerDescriptor> EXTENSIONS = new ArrayList<ModelOperationManagerDescriptor>();

    /** Map of the id of the extension and its descriptor. */
    private static final Map<String, ModelOperationManagerDescriptor> ID_TO_DESCRIPTOR_MAP = new HashMap<String, ModelOperationManagerDescriptor>();

    /**
     * The constructor.
     */
    private ModelOperationManagerRegistry() {
        // Prevent instantiation
    }

    /**
     * Adds an extension to the registry.
     * 
     * @param extension
     *            The extension that is to be added to the registry.
     */
    public static void addExtension(ModelOperationManagerDescriptor extension) {
        EXTENSIONS.add(extension);
        ID_TO_DESCRIPTOR_MAP.put(extension.getId(), extension);
    }

    /**
     * Returns a copy of the registered extensions list.
     * 
     * @return A copy of the registered extensions list.
     */
    public static List<ModelOperationManagerDescriptor> getRegisteredExtensions() {
        return new ArrayList<ModelOperationManagerDescriptor>(EXTENSIONS);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param id
     *            Id of the {@link ModelOperationManagerDescriptor} extension to
     *            be removed from the registry.
     */
    public static void removeExtension(String id) {
        for (ModelOperationManagerDescriptor extension : getRegisteredExtensions()) {
            if (extension.getId().equals(id)) {
                EXTENSIONS.remove(extension);
                ID_TO_DESCRIPTOR_MAP.remove(extension.getId());
            }
        }
    }

    /**
     * Removes all extensions from the registry. This will be called when the
     * plugin will stop.
     */
    public static void clearRegistry() {
        EXTENSIONS.clear();
        ID_TO_DESCRIPTOR_MAP.clear();
    }

}
