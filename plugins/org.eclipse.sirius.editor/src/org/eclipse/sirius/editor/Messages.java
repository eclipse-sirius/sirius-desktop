/*******************************************************************************
 * Copyright (c) 2015, 2019 Obeo.
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
package org.eclipse.sirius.editor;

import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.ext.base.I18N;
import org.eclipse.sirius.ext.base.I18N.TranslatableMessage;

/**
 * Helper class to obtains translated strings.
 * 
 * @author Florian Barbin
 *
 */
public final class Messages {

    static {
        I18N.initializeMessages(Messages.class, SiriusEditorPlugin.INSTANCE);
    }

    // CHECKSTYLE:OFF

    @TranslatableMessage
    public static String ServiceNavigator_targetInitialization_error;

    @TranslatableMessage
    public static String ServiceNavigator_serviceNavigationDialog_title;

    @TranslatableMessage
    public static String ServiceNavigator_serviceNavigationDialog_description;

    @TranslatableMessage
    public static String AddOptionOverridePropertySection_overrideButtonLabel;

    @TranslatableMessage
    public static String LayoutOptionValue_defaultLabel;

    @TranslatableMessage
    public static String CustomSiriusEditor_failedNavigationTitle;

    @TranslatableMessage
    public static String CustomSiriusEditor_failedNavigationMessage;

    @TranslatableMessage
    public static String CustomSiriusEditor_failedNavigationExceptionMessage;

    @TranslatableMessage
    public static String AddOptionOverridePropertySection_dialogTitle;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_optionNameColumnLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_optionTypeColumnLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_optionDefaultValueColumnLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_tableLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_filteringLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_optionDescriptionLabel;

    @TranslatableMessage
    public static String CustomLayoutConfigurationDescriptionPropertySection_noLayoutAlgorithmProviderDescription;

    @TranslatableMessage
    public static String CustomLayoutConfigurationDescriptionPropertySection_noLayoutAlgorithmProviderName;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_optionTargetColumnLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_typeEnumLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_typeEnumSetLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_typeStringLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_typeBooleanLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_typeDoubleLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_typeIntegerLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_targetParentLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_targetNodeLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_targetEdgeLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_targePortLabel;

    @TranslatableMessage
    public static String OptionOverrideEditorDialog_targetLabelLabel;

    // CHECKSTYLE:ON
    private Messages() {
        // Prevents instanciation.
    }
}
