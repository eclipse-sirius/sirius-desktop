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
package org.eclipse.sirius.business.internal.helper.delete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A registry for {@link IDeleteHookDescriptor} extensions.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class DeleteHookDescriptorRegistry {

    /** List of extensions contributed. */
    private static final List<IDeleteHookDescriptor> EXTENSIONS = new ArrayList<IDeleteHookDescriptor>();

    private static final Map<String, IDeleteHookDescriptor> ID_TO_DESCRITPOR_MAP = new HashMap<String, IDeleteHookDescriptor>();

    /**
     * Utility classes don't need a default constructor.
     */
    private DeleteHookDescriptorRegistry() {
    }

    /**
     * Adds an extension to the registry.
     * 
     * @param extension
     *            The extension that is to be added to the registry.
     */
    public static void addExtension(IDeleteHookDescriptor extension) {
        EXTENSIONS.add(extension);
        ID_TO_DESCRITPOR_MAP.put(extension.getId(), extension);
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin
     * stopping.
     */
    public static void clearRegistry() {
        EXTENSIONS.clear();
        ID_TO_DESCRITPOR_MAP.clear();
    }

    /**
     * Returns a copy of the registered extensions list.
     * 
     * @return A copy of the registered extensions list.
     */
    public static List<IDeleteHookDescriptor> getRegisteredExtensions() {
        return new ArrayList<IDeleteHookDescriptor>(EXTENSIONS);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param id
     *            Id of the {@link IDeleteHookDescriptor} extension to be
     *            removed from the registry.
     */
    public static void removeExtension(String id) {
        for (IDeleteHookDescriptor extension : getRegisteredExtensions()) {
            if (extension.getId().equals(id)) {
                EXTENSIONS.remove(extension);
                ID_TO_DESCRITPOR_MAP.remove(extension.getId());
            }
        }
    }

    /**
     * Get a descriptor from its id.
     * 
     * @param id
     *            Id of the descriptor to return.
     * @return the descriptor corresponding to the id parameter
     */
    public static IDeleteHookDescriptor getDescriptorFromId(String id) {
        return ID_TO_DESCRITPOR_MAP.get(id);
    }
}
