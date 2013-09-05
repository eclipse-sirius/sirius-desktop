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
package org.eclipse.sirius.business.internal.session.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will contain all sessionFactory extension that have been parsed
 * from the extension point.
 * 
 * @author smonnier
 */
public final class SessionFactoryRegistry {

    /** List of extensions created from the extension point contributions. */
    private static final List<SessionFactoryDescriptor> EXTENSIONS = new ArrayList<SessionFactoryDescriptor>();

    private static final Map<String, SessionFactoryDescriptor> ID_TO_DESCRITPOR_MAP = new HashMap<String, SessionFactoryDescriptor>();

    /**
     * Utility classes don't need a default constructor.
     */
    private SessionFactoryRegistry() {
        // hides constructor
    }

    /**
     * Adds an extension to the registry.
     * 
     * @param extension
     *            The extension that is to be added to the registry.
     */
    public static void addExtension(SessionFactoryDescriptor extension) {
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
    public static List<SessionFactoryDescriptor> getRegisteredExtensions() {
        return new ArrayList<SessionFactoryDescriptor>(EXTENSIONS);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param id
     *            Id of the {@link SessionFactoryDescriptor} extension to be
     *            removed from the registry.
     */
    public static void removeExtension(String id) {
        for (SessionFactoryDescriptor extension : getRegisteredExtensions()) {
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
    public static SessionFactoryDescriptor getDescriptorFromId(String id) {
        return ID_TO_DESCRITPOR_MAP.get(id);
    }

}
