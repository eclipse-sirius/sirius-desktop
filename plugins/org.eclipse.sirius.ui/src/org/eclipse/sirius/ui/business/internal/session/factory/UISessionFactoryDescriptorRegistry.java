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
package org.eclipse.sirius.ui.business.internal.session.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Registry for {@link UISessionFactoryDescriptor}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class UISessionFactoryDescriptorRegistry {

    /** List of extensions contributed. */
    private static final List<UISessionFactoryDescriptor> EXTENSIONS = new ArrayList<UISessionFactoryDescriptor>();

    private static final Map<String, UISessionFactoryDescriptor> ID_TO_DESCRITPOR_MAP = new HashMap<String, UISessionFactoryDescriptor>();

    /**
     * Utility classes don't need a default constructor.
     */
    private UISessionFactoryDescriptorRegistry() {
    }

    /**
     * Adds an extension to the registry.
     * 
     * @param extension
     *            The extension that is to be added to the registry.
     */
    public static void addExtension(UISessionFactoryDescriptor extension) {
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
    public static List<UISessionFactoryDescriptor> getRegisteredExtensions() {
        return new ArrayList<UISessionFactoryDescriptor>(EXTENSIONS);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param id
     *            Id of the {@link UISessionFactoryDescriptor} extension to be
     *            removed from the registry.
     */
    public static void removeExtension(String id) {
        for (UISessionFactoryDescriptor extension : getRegisteredExtensions()) {
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
    public static UISessionFactoryDescriptor getDescriptorFromId(String id) {
        return ID_TO_DESCRITPOR_MAP.get(id);
    }
}
