/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.ui.business.internal.views.properties.tabbed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class will contain all sessionFactory extension that have been parsed
 * from the extension point.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class LabelProviderProviderRegistry {

    /** List of extensions created from the extension point contributions. */
    private static final List<LabelProviderProviderDescriptor> EXTENSIONS = new ArrayList<LabelProviderProviderDescriptor>();

    private static final Map<String, LabelProviderProviderDescriptor> ID_TO_DESCRITPOR_MAP = new HashMap<String, LabelProviderProviderDescriptor>();

    /**
     * Utility classes don't need a default constructor.
     */
    private LabelProviderProviderRegistry() {
        // hides constructor
    }

    /**
     * Adds an extension to the registry.
     * 
     * @param extension
     *            The extension that is to be added to the registry.
     */
    public static void addExtension(LabelProviderProviderDescriptor extension) {
        EXTENSIONS.add(extension);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param id
     *            Id of the {@link SessionFactoryDescriptor} extension to be
     *            removed from the registry.
     */
    public static void removeExtension(String id) {
        for (LabelProviderProviderDescriptor extension : getRegisteredExtensions()) {
            if (extension.getId().equals(id)) {
                EXTENSIONS.remove(extension);
                ID_TO_DESCRITPOR_MAP.remove(extension.getId());
            }
        }
    }

    /**
     * Removes all extensions from the registry. This will be called at plug-in
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
    public static List<LabelProviderProviderDescriptor> getRegisteredExtensions() {
        return new ArrayList<LabelProviderProviderDescriptor>(EXTENSIONS);
    }

}
