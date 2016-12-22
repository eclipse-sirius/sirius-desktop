/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import java.util.MissingResourceException;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.sirius.editor.properties.internal.SiriusEditorPropertiesPlugin;

/**
 * Utility class for the menu builders of the Properties.
 * 
 * @author sbegaudeau
 */
public final class PropertiesMenuBuilderConstants {
    /**
     * The priority of the widgets menu.
     */
    public static final int WIDGETS;

    /**
     * The priority of the create widget from domain class menu.
     */
    public static final int WIDGETS_FROM_DOMAIN_CLASS;

    /**
     * The priority of the overrides menu.
     */
    public static final int OVERRIDES;

    /**
     * The priority of the layout menu.
     */
    public static final int LAYOUT;

    /**
     * The priority of the style menu.
     */
    public static final int STYLE;

    /**
     * The default priority.
     */
    public static final int DEFAULT_PRIORITY = 100000;

    /**
     * The constructor.
     */
    private PropertiesMenuBuilderConstants() {
        // Prevent instantiation
    }

    static {
        ResourceLocator rl = SiriusEditorPropertiesPlugin.INSTANCE;
        WIDGETS = getPriority(rl, "WidgetsMenuPriority");
        WIDGETS_FROM_DOMAIN_CLASS = getPriority(rl, "WidgetsFromDomainClassMenuPriority");
        OVERRIDES = getPriority(rl, "OverridesMenuPriority");
        LAYOUT = getPriority(rl, "LayoutMenuPriority");
        STYLE = getPriority(rl, "StyleMenuPriority");
    }

    /**
     * Returns the priority of the property with the given id.
     * 
     * @param resourceLocator
     *            The resource locator
     * @param id
     *            The identifier of the property
     * @return The priority of the property or the DEFAULT_PRIORITY in case of
     *         error.
     */
    private static int getPriority(ResourceLocator rl, String id) {
        try {
            return Integer.parseInt(rl.getString(id).trim());
        } catch (NumberFormatException nfe) {
        } catch (MissingResourceException mre) {
        }
        return DEFAULT_PRIORITY;
    }
}
