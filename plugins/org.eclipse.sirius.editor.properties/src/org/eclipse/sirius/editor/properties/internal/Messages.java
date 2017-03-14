/*******************************************************************************
 * Copyright (c) 2016, 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.internal;

import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author mbats
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusEditorPropertiesPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF
    @TranslatableMessage
    public static String CreateWidgetForAllFeaturesCommand_text;

    @TranslatableMessage
    public static String CreateWidgetFromDomainClassMenuBuilder_label;

    @TranslatableMessage
    public static String DefaultMonolineTextDescriptionFactory_name;

    @TranslatableMessage
    public static String DefaultMonolineTextDescriptionFactory_widgetLabel;

    @TranslatableMessage
    public static String DefaultMultilineTextDescriptionFactory_name;

    @TranslatableMessage
    public static String DefaultMultilineTextDescriptionFactory_widgetLabel;

    @TranslatableMessage
    public static String DefaultBooleanDescriptionFactory_name;

    @TranslatableMessage
    public static String DefaultBooleanDescriptionFactory_widgetLabel;

    @TranslatableMessage
    public static String DefaultEnumerationDescriptionFactory_name;

    @TranslatableMessage
    public static String DefaultEnumerationDescriptionFactory_widgetLabel;

    @TranslatableMessage
    public static String DefaultMultivaluedEAttributeDescriptionFactory_name;

    @TranslatableMessage
    public static String DefaultMultivaluedEAttributeDescriptionFactory_widgetLabel;

    @TranslatableMessage
    public static String LayoutMenuBuilder_label;

    @TranslatableMessage
    public static String OverridesMenuBuilder_label;

    @TranslatableMessage
    public static String PropertiesMenuBuilder_label;

    @TranslatableMessage
    public static String PropertiesMenuBuilder_NewPropertiesView_label;

    @TranslatableMessage
    public static String PropertiesMenuBuilder_ExtendPropertiesView_label;

    @TranslatableMessage
    public static String PropertiesMenuBuilder_ImportPropertiesView_label;

    @TranslatableMessage
    public static String WidgetMenuBuilder_label;

    @TranslatableMessage
    public static String StyleMenuBuilder_label;

    @TranslatableMessage
    public static String ConditionalStyleMenuBuilder_label;

    @TranslatableMessage
    public static String ContainerMenuBuilder_label;

    @TranslatableMessage
    public static String ValidationMenuBuilder_label;

    @TranslatableMessage
    public static String DynamicMappingMenuBuilder_label;

    @TranslatableMessage
    public static String PageMenuBuilder_label;

    @TranslatableMessage
    public static String GroupMenuBuilder_label;

    @TranslatableMessage
    public static String CategoryMenuBuilder_label;

    @TranslatableMessage
    public static String WidgetActionMenuBuilder_label;

    @TranslatableMessage
    public static String CustomOperationMenuBuilder_label;

    @TranslatableMessage
    public static String CustomExpressionMenuBuilder_label;

    @TranslatableMessage
    public static String DialogButtonMenuBuilder_label;

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
