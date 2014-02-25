/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.image;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A registry of contributed {@link ImageSelectorDescriptor}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class ImageSelectorDescriptorRegistry {

    /** List of extensions contributed. */
    private static final List<ImageSelectorDescriptor> EXTENSIONS = new ArrayList<ImageSelectorDescriptor>();

    private static final Map<String, ImageSelectorDescriptor> ID_TO_DESCRITPOR_MAP = new HashMap<String, ImageSelectorDescriptor>();

    /**
     * Utility classes don't need a default constructor.
     */
    private ImageSelectorDescriptorRegistry() {
    }

    /**
     * Adds an extension to the registry.
     * 
     * @param extension
     *            The extension that is to be added to the registry.
     */
    public static void addExtension(ImageSelectorDescriptor extension) {
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
    public static List<ImageSelectorDescriptor> getRegisteredExtensions() {
        return new ArrayList<ImageSelectorDescriptor>(EXTENSIONS);
    }

    /**
     * Removes a phantom from the registry.
     * 
     * @param id
     *            Id of the {@link ImageSelectorDescriptor} extension
     *            to be removed from the registry.
     */
    public static void removeExtension(String id) {
        for (ImageSelectorDescriptor extension : getRegisteredExtensions()) {
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
    public static ImageSelectorDescriptor getDescriptorFromId(String id) {
        return ID_TO_DESCRITPOR_MAP.get(id);
    }

}
