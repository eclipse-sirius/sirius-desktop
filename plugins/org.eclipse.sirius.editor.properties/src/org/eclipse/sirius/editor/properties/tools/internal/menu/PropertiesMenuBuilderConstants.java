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
     * The priority of the pages menu.
     */
    public static final int PAGES;

    /**
     * The priority of the groups menu.
     */
    public static final int GROUPS;

    /**
     * The priority of the category menu.
     */
    public static final int CATEGORY;

    /**
     * The priority of the widgets menu.
     */
    public static final int WIDGET;

    /**
     * The priority of the create widget from domain class menu.
     */
    public static final int WIDGETS_FROM_DOMAIN_CLASS;

    /**
     * The priority of the container menu.
     */
    public static final int CONTAINER;

    /**
     * The priority of the dynamic mappings menu.
     */
    public static final int DYNAMIC_MAPPINGS;

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
     * The priority of the conditional style menu.
     */
    public static final int CONDITIONAL_STYLE;

    /**
     * The priority of the validation menu.
     */
    public static final int VALIDATION;

    /**
     * The priority of the widget action menu.
     */
    public static final int WIDGET_ACTION;

    /**
     * The priority of the custom expression menu.
     */
    public static final int CUSTOM_EXPRESSION;

    /**
     * The priority of the custom operation menu.
     */
    public static final int CUSTOM_OPERATION;

    /**
     * The priority of the dialog buttons.
     */
    public static final int DIALOG_BUTTON;

    /**
     * The priority of the toolbar action.
     */
    public static final int TOOLBAR_ACTION;

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
        PAGES = getPriority(rl, "PagesMenuPriority");
        GROUPS = getPriority(rl, "GroupsMenuPriority");
        CATEGORY = getPriority(rl, "CategoryMenuPriority");
        WIDGET = getPriority(rl, "WidgetsMenuPriority");
        WIDGETS_FROM_DOMAIN_CLASS = getPriority(rl, "WidgetsFromDomainClassMenuPriority");
        CONTAINER = getPriority(rl, "ContainerMenuPriority");
        DYNAMIC_MAPPINGS = getPriority(rl, "DynamicMappingsMenuPriority");
        OVERRIDES = getPriority(rl, "OverridesMenuPriority");
        LAYOUT = getPriority(rl, "LayoutMenuPriority");
        STYLE = getPriority(rl, "StyleMenuPriority");
        CONDITIONAL_STYLE = getPriority(rl, "ConditionalStyleMenuPriority");
        VALIDATION = getPriority(rl, "ValidationMenuPriority");
        WIDGET_ACTION = getPriority(rl, "WidgetActionMenuPriority");
        CUSTOM_EXPRESSION = getPriority(rl, "CustomExpressionMenuPriority");
        CUSTOM_OPERATION = getPriority(rl, "CustomOperationMenuPriority");
        DIALOG_BUTTON = getPriority(rl, "DialogButtonMenuPriority");
        TOOLBAR_ACTION = getPriority(rl, "ToolbarActionMenuPriority");
    }

    /**
     * Returns the priority of the property with the given id.
     * 
     * @param resourceLocator
     *            The resource locator
     * @param id
     *            The identifier of the property
     * @return The priority of the property or the DEFAULT_PRIORITY in case of error.
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
