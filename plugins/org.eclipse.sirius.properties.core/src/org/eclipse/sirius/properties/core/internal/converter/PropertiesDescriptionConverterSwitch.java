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

import java.util.Optional;

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
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionConverter;
import org.eclipse.sirius.properties.core.api.DefaultDescriptionWithInitialOperationConverter;
import org.eclipse.sirius.properties.core.api.DefaultStyleDescriptionConverter;
import org.eclipse.sirius.properties.core.api.IDescriptionConverter;
import org.eclipse.sirius.properties.util.PropertiesSwitch;

/**
 * This switch is used to retrieve the proper {@link IDescriptionConverter} for each class of the properties meta-model.
 * 
 * @author sbegaudeau
 */
public class PropertiesDescriptionConverterSwitch extends PropertiesSwitch<Optional<IDescriptionConverter>> {

    @Override
    public Optional<IDescriptionConverter> casePageDescription(PageDescription object) {
        return Optional.of(new PageDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseGroupDescription(GroupDescription object) {
        return Optional.of(new GroupDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseGroupStyle(GroupStyle object) {
        return Optional.of(new GroupStyleDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseGroupConditionalStyle(GroupConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(GroupConditionalStyle.class, EefPackage.Literals.EEF_GROUP_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> casePropertyValidationRule(PropertyValidationRule object) {
        return Optional.of(new PropertyValidationRuleDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseContainerDescription(ContainerDescription object) {
        return Optional.of(new DefaultDescriptionConverter<>(ContainerDescription.class, EefPackage.Literals.EEF_CONTAINER_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionConverter> caseFillLayoutDescription(FillLayoutDescription object) {
        return Optional.of(new FillLayoutDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseGridLayoutDescription(GridLayoutDescription object) {
        return Optional.of(new DefaultDescriptionConverter<>(GridLayoutDescription.class, EefPackage.Literals.EEF_GRID_LAYOUT_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionConverter> caseDynamicMappingForDescription(DynamicMappingForDescription object) {
        return Optional.of(new DefaultDescriptionConverter<>(DynamicMappingForDescription.class, EefPackage.Literals.EEF_DYNAMIC_MAPPING_FOR));
    }

    @Override
    public Optional<IDescriptionConverter> caseDynamicMappingIfDescription(DynamicMappingIfDescription object) {
        return Optional.of(new DefaultDescriptionConverter<>(DynamicMappingIfDescription.class, EefPackage.Literals.EEF_DYNAMIC_MAPPING_IF));
    }

    @Override
    public Optional<IDescriptionConverter> caseTextDescription(TextDescription object) {
        return Optional.of(new TextDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseTextAreaDescription(TextAreaDescription object) {
        return Optional.of(new TextDescriptionConverter());
    }

    @Override
    public Optional<IDescriptionConverter> caseTextWidgetStyle(TextWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(TextWidgetStyle.class, EefPackage.Literals.EEF_TEXT_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseTextWidgetConditionalStyle(TextWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(TextWidgetConditionalStyle.class, EefPackage.Literals.EEF_TEXT_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseLabelDescription(LabelDescription object) {
        return Optional.of(new DefaultDescriptionConverter<>(LabelDescription.class, EefPackage.Literals.EEF_LABEL_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionConverter> caseLabelWidgetStyle(LabelWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(LabelWidgetStyle.class, EefPackage.Literals.EEF_LABEL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseLabelWidgetConditionalStyle(LabelWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(LabelWidgetConditionalStyle.class, EefPackage.Literals.EEF_LABEL_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseCheckboxDescription(CheckboxDescription object) {
        return Optional.of(new DefaultDescriptionWithInitialOperationConverter<>(CheckboxDescription.class, EefPackage.Literals.EEF_CHECKBOX_DESCRIPTION,
                EefPackage.Literals.EEF_CHECKBOX_DESCRIPTION__EDIT_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseCheckboxWidgetStyle(CheckboxWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(CheckboxWidgetStyle.class, EefPackage.Literals.EEF_CHECKBOX_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseCheckboxWidgetConditionalStyle(CheckboxWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(CheckboxWidgetConditionalStyle.class, EefPackage.Literals.EEF_CHECKBOX_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseHyperlinkDescription(HyperlinkDescription object) {
        return Optional.of(new DefaultDescriptionWithInitialOperationConverter<>(HyperlinkDescription.class, EefPackage.Literals.EEF_HYPERLINK_DESCRIPTION,
                EefPackage.Literals.EEF_HYPERLINK_DESCRIPTION__ON_CLICK_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseHyperlinkWidgetStyle(HyperlinkWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(HyperlinkWidgetStyle.class, EefPackage.Literals.EEF_HYPERLINK_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseHyperlinkWidgetConditionalStyle(HyperlinkWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(HyperlinkWidgetConditionalStyle.class, EefPackage.Literals.EEF_HYPERLINK_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseButtonDescription(ButtonDescription object) {
        return Optional.of(new DefaultDescriptionWithInitialOperationConverter<>(ButtonDescription.class, EefPackage.Literals.EEF_BUTTON_DESCRIPTION,
                EefPackage.Literals.EEF_BUTTON_DESCRIPTION__PUSH_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseButtonWidgetStyle(ButtonWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(ButtonWidgetStyle.class, EefPackage.Literals.EEF_BUTTON_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseButtonWidgetConditionalStyle(ButtonWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(ButtonWidgetConditionalStyle.class, EefPackage.Literals.EEF_BUTTON_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseRadioDescription(RadioDescription object) {
        return Optional.of(
                new DefaultDescriptionWithInitialOperationConverter<>(RadioDescription.class, EefPackage.Literals.EEF_RADIO_DESCRIPTION, EefPackage.Literals.EEF_RADIO_DESCRIPTION__EDIT_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseRadioWidgetStyle(RadioWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(RadioWidgetStyle.class, EefPackage.Literals.EEF_RADIO_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseRadioWidgetConditionalStyle(RadioWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(RadioWidgetConditionalStyle.class, EefPackage.Literals.EEF_RADIO_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseSelectDescription(SelectDescription object) {
        return Optional.of(new DefaultDescriptionWithInitialOperationConverter<>(SelectDescription.class, EefPackage.Literals.EEF_SELECT_DESCRIPTION,
                EefPackage.Literals.EEF_SELECT_DESCRIPTION__EDIT_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseSelectWidgetStyle(SelectWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(SelectWidgetStyle.class, EefPackage.Literals.EEF_SELECT_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseSelectWidgetConditionalStyle(SelectWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(SelectWidgetConditionalStyle.class, EefPackage.Literals.EEF_SELECT_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseListDescription(ListDescription object) {
        return Optional.of(
                new DefaultDescriptionWithInitialOperationConverter<>(ListDescription.class, EefPackage.Literals.EEF_LIST_DESCRIPTION, EefPackage.Literals.EEF_LIST_DESCRIPTION__ON_CLICK_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseWidgetAction(WidgetAction object) {
        return Optional.of(new DefaultDescriptionWithInitialOperationConverter<>(WidgetAction.class, EefPackage.Literals.EEF_WIDGET_ACTION, EefPackage.Literals.EEF_WIDGET_ACTION__ACTION_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseListWidgetStyle(ListWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(ListWidgetStyle.class, EefPackage.Literals.EEF_LIST_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseListWidgetConditionalStyle(ListWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(ListWidgetConditionalStyle.class, EefPackage.Literals.EEF_LIST_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseCustomDescription(CustomDescription object) {
        return Optional.of(new CustomWidgetDescriptionConverter<>(CustomDescription.class, EefPackage.Literals.EEF_CUSTOM_WIDGET_DESCRIPTION));
    }

    @Override
    public Optional<IDescriptionConverter> caseCustomExpression(CustomExpression object) {
        return Optional.of(new DefaultDescriptionConverter<>(CustomExpression.class, EefPackage.Literals.EEF_CUSTOM_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseCustomOperation(CustomOperation object) {
        return Optional.of(
                new DefaultDescriptionWithInitialOperationConverter<>(CustomOperation.class, EefPackage.Literals.EEF_CUSTOM_EXPRESSION, EefPackage.Literals.EEF_CUSTOM_EXPRESSION__CUSTOM_EXPRESSION));
    }

    @Override
    public Optional<IDescriptionConverter> caseCustomWidgetStyle(CustomWidgetStyle object) {
        return Optional.of(new DefaultStyleDescriptionConverter<>(CustomWidgetStyle.class, EefPackage.Literals.EEF_CUSTOM_WIDGET_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseCustomWidgetConditionalStyle(CustomWidgetConditionalStyle object) {
        return Optional.of(new DefaultDescriptionConverter<>(CustomWidgetConditionalStyle.class, EefPackage.Literals.EEF_CUSTOM_WIDGET_CONDITIONAL_STYLE));
    }

    @Override
    public Optional<IDescriptionConverter> caseToolbarAction(ToolbarAction object) {
        return Optional
                .of(new DefaultDescriptionWithInitialOperationConverter<>(ToolbarAction.class, EefPackage.Literals.EEF_TOOLBAR_ACTION, EefPackage.Literals.EEF_TOOLBAR_ACTION__ACTION_EXPRESSION));
    }
}
