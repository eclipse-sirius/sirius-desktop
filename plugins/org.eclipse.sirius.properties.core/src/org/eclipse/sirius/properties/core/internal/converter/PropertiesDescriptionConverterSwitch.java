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
package org.eclipse.sirius.properties.core.internal.converter;

import org.eclipse.eef.EefPackage;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonWidgetConditionalStyle;
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.CustomWidgetStyle;
import org.eclipse.sirius.properties.DynamicMappingForDescription;
import org.eclipse.sirius.properties.DynamicMappingIfDescription;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.GroupConditionalStyle;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextWidgetConditionalStyle;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionWithInitialOperationConverter;
import org.eclipse.sirius.properties.core.api.DefaultStyleDescriptionConverter;
import org.eclipse.sirius.properties.core.api.IDescriptionConverter;
import org.eclipse.sirius.properties.util.PropertiesSwitch;

/**
 * This switch is used to retrieve the proper {@link IDescriptionConverter} for
 * each class of the properties meta-model.
 * 
 * @author sbegaudeau
 */
public class PropertiesDescriptionConverterSwitch extends PropertiesSwitch<IDescriptionConverter> {

    @Override
    public IDescriptionConverter casePageDescription(PageDescription object) {
        return new PageDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseGroupDescription(GroupDescription object) {
        return new GroupDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseGroupStyle(GroupStyle object) {
        return new GroupStyleDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseGroupConditionalStyle(GroupConditionalStyle object) {
        return new DefaultDescriptionConverter<>(GroupConditionalStyle.class, EefPackage.Literals.EEF_GROUP_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter casePropertyValidationRule(PropertyValidationRule object) {
        return new PropertyValidationRuleDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseContainerDescription(ContainerDescription object) {
        return new DefaultDescriptionConverter<>(ContainerDescription.class, EefPackage.Literals.EEF_CONTAINER_DESCRIPTION);
    }

    @Override
    public IDescriptionConverter caseFillLayoutDescription(FillLayoutDescription object) {
        return new FillLayoutDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseGridLayoutDescription(GridLayoutDescription object) {
        return new DefaultDescriptionConverter<>(GridLayoutDescription.class, EefPackage.Literals.EEF_GRID_LAYOUT_DESCRIPTION);
    }

    @Override
    public IDescriptionConverter caseDynamicMappingForDescription(DynamicMappingForDescription object) {
        return new DefaultDescriptionConverter<>(DynamicMappingForDescription.class, EefPackage.Literals.EEF_DYNAMIC_MAPPING_FOR);
    }

    @Override
    public IDescriptionConverter caseDynamicMappingIfDescription(DynamicMappingIfDescription object) {
        return new DefaultDescriptionConverter<>(DynamicMappingIfDescription.class, EefPackage.Literals.EEF_DYNAMIC_MAPPING_IF);
    }

    @Override
    public IDescriptionConverter caseTextDescription(TextDescription object) {
        return new TextDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseTextAreaDescription(TextAreaDescription object) {
        return new TextDescriptionConverter();
    }

    @Override
    public IDescriptionConverter caseTextWidgetStyle(TextWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(TextWidgetStyle.class, EefPackage.Literals.EEF_TEXT_STYLE);
    }

    @Override
    public IDescriptionConverter caseTextWidgetConditionalStyle(TextWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(TextWidgetConditionalStyle.class, EefPackage.Literals.EEF_TEXT_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseLabelDescription(LabelDescription object) {
        return new DefaultDescriptionConverter<>(LabelDescription.class, EefPackage.Literals.EEF_LABEL_DESCRIPTION);
    }

    @Override
    public IDescriptionConverter caseLabelWidgetStyle(LabelWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(LabelWidgetStyle.class, EefPackage.Literals.EEF_LABEL_STYLE);
    }

    @Override
    public IDescriptionConverter caseLabelWidgetConditionalStyle(LabelWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(LabelWidgetConditionalStyle.class, EefPackage.Literals.EEF_LABEL_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseCheckboxDescription(CheckboxDescription object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(CheckboxDescription.class, EefPackage.Literals.EEF_CHECKBOX_DESCRIPTION,
                EefPackage.Literals.EEF_CHECKBOX_DESCRIPTION__EDIT_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseCheckboxWidgetStyle(CheckboxWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(CheckboxWidgetStyle.class, EefPackage.Literals.EEF_CHECKBOX_STYLE);
    }

    @Override
    public IDescriptionConverter caseCheckboxWidgetConditionalStyle(CheckboxWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(CheckboxWidgetConditionalStyle.class, EefPackage.Literals.EEF_CHECKBOX_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseHyperlinkDescription(HyperlinkDescription object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(HyperlinkDescription.class, EefPackage.Literals.EEF_HYPERLINK_DESCRIPTION,
                EefPackage.Literals.EEF_HYPERLINK_DESCRIPTION__ON_CLICK_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseHyperlinkWidgetStyle(HyperlinkWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(HyperlinkWidgetStyle.class, EefPackage.Literals.EEF_HYPERLINK_STYLE);
    }

    @Override
    public IDescriptionConverter caseHyperlinkWidgetConditionalStyle(HyperlinkWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(HyperlinkWidgetConditionalStyle.class, EefPackage.Literals.EEF_HYPERLINK_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseButtonDescription(ButtonDescription object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(ButtonDescription.class, EefPackage.Literals.EEF_BUTTON_DESCRIPTION, EefPackage.Literals.EEF_BUTTON_DESCRIPTION__PUSH_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseButtonWidgetStyle(ButtonWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(ButtonWidgetStyle.class, EefPackage.Literals.EEF_BUTTON_STYLE);
    }

    @Override
    public IDescriptionConverter caseButtonWidgetConditionalStyle(ButtonWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(ButtonWidgetConditionalStyle.class, EefPackage.Literals.EEF_BUTTON_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseRadioDescription(RadioDescription object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(RadioDescription.class, EefPackage.Literals.EEF_RADIO_DESCRIPTION, EefPackage.Literals.EEF_RADIO_DESCRIPTION__EDIT_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseRadioWidgetStyle(RadioWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(RadioWidgetStyle.class, EefPackage.Literals.EEF_RADIO_STYLE);
    }

    @Override
    public IDescriptionConverter caseRadioWidgetConditionalStyle(RadioWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(RadioWidgetConditionalStyle.class, EefPackage.Literals.EEF_RADIO_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseSelectDescription(SelectDescription object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(SelectDescription.class, EefPackage.Literals.EEF_SELECT_DESCRIPTION, EefPackage.Literals.EEF_SELECT_DESCRIPTION__EDIT_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseSelectWidgetStyle(SelectWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(SelectWidgetStyle.class, EefPackage.Literals.EEF_SELECT_STYLE);
    }

    @Override
    public IDescriptionConverter caseSelectWidgetConditionalStyle(SelectWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(SelectWidgetConditionalStyle.class, EefPackage.Literals.EEF_SELECT_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseListDescription(ListDescription object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(ListDescription.class, EefPackage.Literals.EEF_LIST_DESCRIPTION, EefPackage.Literals.EEF_LIST_DESCRIPTION__ON_CLICK_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseWidgetAction(WidgetAction object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(WidgetAction.class, EefPackage.Literals.EEF_WIDGET_ACTION, EefPackage.Literals.EEF_WIDGET_ACTION__ACTION_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseListWidgetStyle(ListWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(ListWidgetStyle.class, EefPackage.Literals.EEF_LIST_STYLE);
    }

    @Override
    public IDescriptionConverter caseListWidgetConditionalStyle(ListWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(ListWidgetConditionalStyle.class, EefPackage.Literals.EEF_LIST_CONDITIONAL_STYLE);
    }

    @Override
    public IDescriptionConverter caseCustomDescription(CustomDescription object) {
        return new CustomWidgetDescriptionConverter<>(CustomDescription.class, EefPackage.Literals.EEF_CUSTOM_WIDGET_DESCRIPTION);
    }

    @Override
    public IDescriptionConverter caseCustomExpression(CustomExpression object) {
        return new DefaultDescriptionConverter<>(CustomExpression.class, EefPackage.Literals.EEF_CUSTOM_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseCustomOperation(CustomOperation object) {
        return new DefaultDescriptionWithInitialOperationConverter<>(CustomOperation.class, EefPackage.Literals.EEF_CUSTOM_EXPRESSION, EefPackage.Literals.EEF_CUSTOM_EXPRESSION__CUSTOM_EXPRESSION);
    }

    @Override
    public IDescriptionConverter caseCustomWidgetStyle(CustomWidgetStyle object) {
        return new DefaultStyleDescriptionConverter<>(CustomWidgetStyle.class, EefPackage.Literals.EEF_CUSTOM_WIDGET_STYLE);
    }

    @Override
    public IDescriptionConverter caseCustomWidgetConditionalStyle(CustomWidgetConditionalStyle object) {
        return new DefaultDescriptionConverter<>(CustomWidgetConditionalStyle.class, EefPackage.Literals.EEF_CUSTOM_WIDGET_CONDITIONAL_STYLE);
    }
}
