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

    // CHECKSTYLE:ON

    private Messages() {
        // Prevents instantiation.
    }
}
