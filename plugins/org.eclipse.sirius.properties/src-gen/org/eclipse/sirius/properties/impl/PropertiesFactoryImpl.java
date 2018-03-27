/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonOverrideDescription;
import org.eclipse.sirius.properties.ButtonWidgetConditionalStyle;
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.Category;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxOverrideDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ContainerOverrideDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.CustomOverrideDescription;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.CustomWidgetStyle;
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.DialogModelOperation;
import org.eclipse.sirius.properties.DynamicMappingForDescription;
import org.eclipse.sirius.properties.DynamicMappingForOverrideDescription;
import org.eclipse.sirius.properties.DynamicMappingIfDescription;
import org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription;
import org.eclipse.sirius.properties.EditSupport;
import org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.GroupConditionalStyle;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupOverrideDescription;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkOverrideDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelOverrideDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListOverrideDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageOverrideDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioOverrideDescription;
import org.eclipse.sirius.properties.RadioWidgetConditionalStyle;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectOverrideDescription;
import org.eclipse.sirius.properties.SelectWidgetConditionalStyle;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextAreaOverrideDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextOverrideDescription;
import org.eclipse.sirius.properties.TextWidgetConditionalStyle;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.properties.TitleBarStyle;
import org.eclipse.sirius.properties.ToggleStyle;
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.properties.WizardModelOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class PropertiesFactoryImpl extends EFactoryImpl implements PropertiesFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static PropertiesFactory init() {
        try {
            PropertiesFactory thePropertiesFactory = (PropertiesFactory) EPackage.Registry.INSTANCE.getEFactory(PropertiesPackage.eNS_URI);
            if (thePropertiesFactory != null) {
                return thePropertiesFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PropertiesFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PropertiesFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION:
            return createViewExtensionDescription();
        case PropertiesPackage.CATEGORY:
            return createCategory();
        case PropertiesPackage.PAGE_DESCRIPTION:
            return createPageDescription();
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION:
            return createPageOverrideDescription();
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION:
            return createPageValidationSetDescription();
        case PropertiesPackage.PROPERTY_VALIDATION_RULE:
            return createPropertyValidationRule();
        case PropertiesPackage.TOOLBAR_ACTION:
            return createToolbarAction();
        case PropertiesPackage.GROUP_DESCRIPTION:
            return createGroupDescription();
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION:
            return createGroupOverrideDescription();
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION:
            return createGroupValidationSetDescription();
        case PropertiesPackage.CONTAINER_DESCRIPTION:
            return createContainerDescription();
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION:
            return createContainerOverrideDescription();
        case PropertiesPackage.FILL_LAYOUT_DESCRIPTION:
            return createFillLayoutDescription();
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION:
            return createGridLayoutDescription();
        case PropertiesPackage.TEXT_DESCRIPTION:
            return createTextDescription();
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION:
            return createTextOverrideDescription();
        case PropertiesPackage.BUTTON_DESCRIPTION:
            return createButtonDescription();
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION:
            return createButtonOverrideDescription();
        case PropertiesPackage.LABEL_DESCRIPTION:
            return createLabelDescription();
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION:
            return createLabelOverrideDescription();
        case PropertiesPackage.CHECKBOX_DESCRIPTION:
            return createCheckboxDescription();
        case PropertiesPackage.CHECKBOX_OVERRIDE_DESCRIPTION:
            return createCheckboxOverrideDescription();
        case PropertiesPackage.SELECT_DESCRIPTION:
            return createSelectDescription();
        case PropertiesPackage.SELECT_OVERRIDE_DESCRIPTION:
            return createSelectOverrideDescription();
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_DESCRIPTION:
            return createDynamicMappingForDescription();
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION:
            return createDynamicMappingForOverrideDescription();
        case PropertiesPackage.DYNAMIC_MAPPING_IF_DESCRIPTION:
            return createDynamicMappingIfDescription();
        case PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION:
            return createDynamicMappingIfOverrideDescription();
        case PropertiesPackage.TEXT_AREA_DESCRIPTION:
            return createTextAreaDescription();
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION:
            return createTextAreaOverrideDescription();
        case PropertiesPackage.RADIO_DESCRIPTION:
            return createRadioDescription();
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION:
            return createRadioOverrideDescription();
        case PropertiesPackage.LIST_DESCRIPTION:
            return createListDescription();
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION:
            return createListOverrideDescription();
        case PropertiesPackage.OPERATION_DESCRIPTION:
            return createOperationDescription();
        case PropertiesPackage.CUSTOM_DESCRIPTION:
            return createCustomDescription();
        case PropertiesPackage.CUSTOM_OVERRIDE_DESCRIPTION:
            return createCustomOverrideDescription();
        case PropertiesPackage.CUSTOM_EXPRESSION:
            return createCustomExpression();
        case PropertiesPackage.CUSTOM_OPERATION:
            return createCustomOperation();
        case PropertiesPackage.HYPERLINK_DESCRIPTION:
            return createHyperlinkDescription();
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION:
            return createHyperlinkOverrideDescription();
        case PropertiesPackage.WIDGET_STYLE:
            return createWidgetStyle();
        case PropertiesPackage.TEXT_WIDGET_STYLE:
            return createTextWidgetStyle();
        case PropertiesPackage.LABEL_WIDGET_STYLE:
            return createLabelWidgetStyle();
        case PropertiesPackage.CHECKBOX_WIDGET_STYLE:
            return createCheckboxWidgetStyle();
        case PropertiesPackage.RADIO_WIDGET_STYLE:
            return createRadioWidgetStyle();
        case PropertiesPackage.BUTTON_WIDGET_STYLE:
            return createButtonWidgetStyle();
        case PropertiesPackage.SELECT_WIDGET_STYLE:
            return createSelectWidgetStyle();
        case PropertiesPackage.CUSTOM_WIDGET_STYLE:
            return createCustomWidgetStyle();
        case PropertiesPackage.LIST_WIDGET_STYLE:
            return createListWidgetStyle();
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE:
            return createHyperlinkWidgetStyle();
        case PropertiesPackage.GROUP_STYLE:
            return createGroupStyle();
        case PropertiesPackage.TEXT_WIDGET_CONDITIONAL_STYLE:
            return createTextWidgetConditionalStyle();
        case PropertiesPackage.LABEL_WIDGET_CONDITIONAL_STYLE:
            return createLabelWidgetConditionalStyle();
        case PropertiesPackage.CHECKBOX_WIDGET_CONDITIONAL_STYLE:
            return createCheckboxWidgetConditionalStyle();
        case PropertiesPackage.RADIO_WIDGET_CONDITIONAL_STYLE:
            return createRadioWidgetConditionalStyle();
        case PropertiesPackage.BUTTON_WIDGET_CONDITIONAL_STYLE:
            return createButtonWidgetConditionalStyle();
        case PropertiesPackage.SELECT_WIDGET_CONDITIONAL_STYLE:
            return createSelectWidgetConditionalStyle();
        case PropertiesPackage.CUSTOM_WIDGET_CONDITIONAL_STYLE:
            return createCustomWidgetConditionalStyle();
        case PropertiesPackage.LIST_WIDGET_CONDITIONAL_STYLE:
            return createListWidgetConditionalStyle();
        case PropertiesPackage.WIDGET_ACTION:
            return createWidgetAction();
        case PropertiesPackage.HYPERLINK_WIDGET_CONDITIONAL_STYLE:
            return createHyperlinkWidgetConditionalStyle();
        case PropertiesPackage.GROUP_CONDITIONAL_STYLE:
            return createGroupConditionalStyle();
        case PropertiesPackage.DIALOG_MODEL_OPERATION:
            return createDialogModelOperation();
        case PropertiesPackage.DIALOG_BUTTON:
            return createDialogButton();
        case PropertiesPackage.WIZARD_MODEL_OPERATION:
            return createWizardModelOperation();
        case PropertiesPackage.EDIT_SUPPORT:
            return createEditSupport();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case PropertiesPackage.FILL_LAYOUT_ORIENTATION:
            return createFILL_LAYOUT_ORIENTATIONFromString(eDataType, initialValue);
        case PropertiesPackage.TOGGLE_STYLE:
            return createToggleStyleFromString(eDataType, initialValue);
        case PropertiesPackage.TITLE_BAR_STYLE:
            return createTitleBarStyleFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case PropertiesPackage.FILL_LAYOUT_ORIENTATION:
            return convertFILL_LAYOUT_ORIENTATIONToString(eDataType, instanceValue);
        case PropertiesPackage.TOGGLE_STYLE:
            return convertToggleStyleToString(eDataType, instanceValue);
        case PropertiesPackage.TITLE_BAR_STYLE:
            return convertTitleBarStyleToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ViewExtensionDescription createViewExtensionDescription() {
        ViewExtensionDescriptionImpl viewExtensionDescription = new ViewExtensionDescriptionImpl();
        return viewExtensionDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Category createCategory() {
        CategoryImpl category = new CategoryImpl();
        return category;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageDescription createPageDescription() {
        PageDescriptionImpl pageDescription = new PageDescriptionImpl();
        return pageDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageOverrideDescription createPageOverrideDescription() {
        PageOverrideDescriptionImpl pageOverrideDescription = new PageOverrideDescriptionImpl();
        return pageOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageValidationSetDescription createPageValidationSetDescription() {
        PageValidationSetDescriptionImpl pageValidationSetDescription = new PageValidationSetDescriptionImpl();
        return pageValidationSetDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PropertyValidationRule createPropertyValidationRule() {
        PropertyValidationRuleImpl propertyValidationRule = new PropertyValidationRuleImpl();
        return propertyValidationRule;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ToolbarAction createToolbarAction() {
        ToolbarActionImpl toolbarAction = new ToolbarActionImpl();
        return toolbarAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupDescription createGroupDescription() {
        GroupDescriptionImpl groupDescription = new GroupDescriptionImpl();
        return groupDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupOverrideDescription createGroupOverrideDescription() {
        GroupOverrideDescriptionImpl groupOverrideDescription = new GroupOverrideDescriptionImpl();
        return groupOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupValidationSetDescription createGroupValidationSetDescription() {
        GroupValidationSetDescriptionImpl groupValidationSetDescription = new GroupValidationSetDescriptionImpl();
        return groupValidationSetDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerDescription createContainerDescription() {
        ContainerDescriptionImpl containerDescription = new ContainerDescriptionImpl();
        return containerDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerOverrideDescription createContainerOverrideDescription() {
        ContainerOverrideDescriptionImpl containerOverrideDescription = new ContainerOverrideDescriptionImpl();
        return containerOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FillLayoutDescription createFillLayoutDescription() {
        FillLayoutDescriptionImpl fillLayoutDescription = new FillLayoutDescriptionImpl();
        return fillLayoutDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GridLayoutDescription createGridLayoutDescription() {
        GridLayoutDescriptionImpl gridLayoutDescription = new GridLayoutDescriptionImpl();
        return gridLayoutDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextDescription createTextDescription() {
        TextDescriptionImpl textDescription = new TextDescriptionImpl();
        return textDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextOverrideDescription createTextOverrideDescription() {
        TextOverrideDescriptionImpl textOverrideDescription = new TextOverrideDescriptionImpl();
        return textOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonDescription createButtonDescription() {
        ButtonDescriptionImpl buttonDescription = new ButtonDescriptionImpl();
        return buttonDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonOverrideDescription createButtonOverrideDescription() {
        ButtonOverrideDescriptionImpl buttonOverrideDescription = new ButtonOverrideDescriptionImpl();
        return buttonOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelDescription createLabelDescription() {
        LabelDescriptionImpl labelDescription = new LabelDescriptionImpl();
        return labelDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelOverrideDescription createLabelOverrideDescription() {
        LabelOverrideDescriptionImpl labelOverrideDescription = new LabelOverrideDescriptionImpl();
        return labelOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CheckboxDescription createCheckboxDescription() {
        CheckboxDescriptionImpl checkboxDescription = new CheckboxDescriptionImpl();
        return checkboxDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CheckboxOverrideDescription createCheckboxOverrideDescription() {
        CheckboxOverrideDescriptionImpl checkboxOverrideDescription = new CheckboxOverrideDescriptionImpl();
        return checkboxOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SelectDescription createSelectDescription() {
        SelectDescriptionImpl selectDescription = new SelectDescriptionImpl();
        return selectDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SelectOverrideDescription createSelectOverrideDescription() {
        SelectOverrideDescriptionImpl selectOverrideDescription = new SelectOverrideDescriptionImpl();
        return selectOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingForDescription createDynamicMappingForDescription() {
        DynamicMappingForDescriptionImpl dynamicMappingForDescription = new DynamicMappingForDescriptionImpl();
        return dynamicMappingForDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingForOverrideDescription createDynamicMappingForOverrideDescription() {
        DynamicMappingForOverrideDescriptionImpl dynamicMappingForOverrideDescription = new DynamicMappingForOverrideDescriptionImpl();
        return dynamicMappingForOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingIfDescription createDynamicMappingIfDescription() {
        DynamicMappingIfDescriptionImpl dynamicMappingIfDescription = new DynamicMappingIfDescriptionImpl();
        return dynamicMappingIfDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingIfOverrideDescription createDynamicMappingIfOverrideDescription() {
        DynamicMappingIfOverrideDescriptionImpl dynamicMappingIfOverrideDescription = new DynamicMappingIfOverrideDescriptionImpl();
        return dynamicMappingIfOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextAreaDescription createTextAreaDescription() {
        TextAreaDescriptionImpl textAreaDescription = new TextAreaDescriptionImpl();
        return textAreaDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextAreaOverrideDescription createTextAreaOverrideDescription() {
        TextAreaOverrideDescriptionImpl textAreaOverrideDescription = new TextAreaOverrideDescriptionImpl();
        return textAreaOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioDescription createRadioDescription() {
        RadioDescriptionImpl radioDescription = new RadioDescriptionImpl();
        return radioDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioOverrideDescription createRadioOverrideDescription() {
        RadioOverrideDescriptionImpl radioOverrideDescription = new RadioOverrideDescriptionImpl();
        return radioOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListDescription createListDescription() {
        ListDescriptionImpl listDescription = new ListDescriptionImpl();
        return listDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListOverrideDescription createListOverrideDescription() {
        ListOverrideDescriptionImpl listOverrideDescription = new ListOverrideDescriptionImpl();
        return listOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationDescription createOperationDescription() {
        OperationDescriptionImpl operationDescription = new OperationDescriptionImpl();
        return operationDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomDescription createCustomDescription() {
        CustomDescriptionImpl customDescription = new CustomDescriptionImpl();
        return customDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomOverrideDescription createCustomOverrideDescription() {
        CustomOverrideDescriptionImpl customOverrideDescription = new CustomOverrideDescriptionImpl();
        return customOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomExpression createCustomExpression() {
        CustomExpressionImpl customExpression = new CustomExpressionImpl();
        return customExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomOperation createCustomOperation() {
        CustomOperationImpl customOperation = new CustomOperationImpl();
        return customOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkDescription createHyperlinkDescription() {
        HyperlinkDescriptionImpl hyperlinkDescription = new HyperlinkDescriptionImpl();
        return hyperlinkDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkOverrideDescription createHyperlinkOverrideDescription() {
        HyperlinkOverrideDescriptionImpl hyperlinkOverrideDescription = new HyperlinkOverrideDescriptionImpl();
        return hyperlinkOverrideDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WidgetStyle createWidgetStyle() {
        WidgetStyleImpl widgetStyle = new WidgetStyleImpl();
        return widgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextWidgetStyle createTextWidgetStyle() {
        TextWidgetStyleImpl textWidgetStyle = new TextWidgetStyleImpl();
        return textWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelWidgetStyle createLabelWidgetStyle() {
        LabelWidgetStyleImpl labelWidgetStyle = new LabelWidgetStyleImpl();
        return labelWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CheckboxWidgetStyle createCheckboxWidgetStyle() {
        CheckboxWidgetStyleImpl checkboxWidgetStyle = new CheckboxWidgetStyleImpl();
        return checkboxWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioWidgetStyle createRadioWidgetStyle() {
        RadioWidgetStyleImpl radioWidgetStyle = new RadioWidgetStyleImpl();
        return radioWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonWidgetStyle createButtonWidgetStyle() {
        ButtonWidgetStyleImpl buttonWidgetStyle = new ButtonWidgetStyleImpl();
        return buttonWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SelectWidgetStyle createSelectWidgetStyle() {
        SelectWidgetStyleImpl selectWidgetStyle = new SelectWidgetStyleImpl();
        return selectWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomWidgetStyle createCustomWidgetStyle() {
        CustomWidgetStyleImpl customWidgetStyle = new CustomWidgetStyleImpl();
        return customWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListWidgetStyle createListWidgetStyle() {
        ListWidgetStyleImpl listWidgetStyle = new ListWidgetStyleImpl();
        return listWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkWidgetStyle createHyperlinkWidgetStyle() {
        HyperlinkWidgetStyleImpl hyperlinkWidgetStyle = new HyperlinkWidgetStyleImpl();
        return hyperlinkWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupStyle createGroupStyle() {
        GroupStyleImpl groupStyle = new GroupStyleImpl();
        return groupStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextWidgetConditionalStyle createTextWidgetConditionalStyle() {
        TextWidgetConditionalStyleImpl textWidgetConditionalStyle = new TextWidgetConditionalStyleImpl();
        return textWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelWidgetConditionalStyle createLabelWidgetConditionalStyle() {
        LabelWidgetConditionalStyleImpl labelWidgetConditionalStyle = new LabelWidgetConditionalStyleImpl();
        return labelWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CheckboxWidgetConditionalStyle createCheckboxWidgetConditionalStyle() {
        CheckboxWidgetConditionalStyleImpl checkboxWidgetConditionalStyle = new CheckboxWidgetConditionalStyleImpl();
        return checkboxWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioWidgetConditionalStyle createRadioWidgetConditionalStyle() {
        RadioWidgetConditionalStyleImpl radioWidgetConditionalStyle = new RadioWidgetConditionalStyleImpl();
        return radioWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonWidgetConditionalStyle createButtonWidgetConditionalStyle() {
        ButtonWidgetConditionalStyleImpl buttonWidgetConditionalStyle = new ButtonWidgetConditionalStyleImpl();
        return buttonWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public SelectWidgetConditionalStyle createSelectWidgetConditionalStyle() {
        SelectWidgetConditionalStyleImpl selectWidgetConditionalStyle = new SelectWidgetConditionalStyleImpl();
        return selectWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomWidgetConditionalStyle createCustomWidgetConditionalStyle() {
        CustomWidgetConditionalStyleImpl customWidgetConditionalStyle = new CustomWidgetConditionalStyleImpl();
        return customWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListWidgetConditionalStyle createListWidgetConditionalStyle() {
        ListWidgetConditionalStyleImpl listWidgetConditionalStyle = new ListWidgetConditionalStyleImpl();
        return listWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WidgetAction createWidgetAction() {
        WidgetActionImpl widgetAction = new WidgetActionImpl();
        return widgetAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkWidgetConditionalStyle createHyperlinkWidgetConditionalStyle() {
        HyperlinkWidgetConditionalStyleImpl hyperlinkWidgetConditionalStyle = new HyperlinkWidgetConditionalStyleImpl();
        return hyperlinkWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupConditionalStyle createGroupConditionalStyle() {
        GroupConditionalStyleImpl groupConditionalStyle = new GroupConditionalStyleImpl();
        return groupConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DialogModelOperation createDialogModelOperation() {
        DialogModelOperationImpl dialogModelOperation = new DialogModelOperationImpl();
        return dialogModelOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DialogButton createDialogButton() {
        DialogButtonImpl dialogButton = new DialogButtonImpl();
        return dialogButton;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WizardModelOperation createWizardModelOperation() {
        WizardModelOperationImpl wizardModelOperation = new WizardModelOperationImpl();
        return wizardModelOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EditSupport createEditSupport() {
        EditSupportImpl editSupport = new EditSupportImpl();
        return editSupport;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public FILL_LAYOUT_ORIENTATION createFILL_LAYOUT_ORIENTATIONFromString(EDataType eDataType, String initialValue) {
        FILL_LAYOUT_ORIENTATION result = FILL_LAYOUT_ORIENTATION.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertFILL_LAYOUT_ORIENTATIONToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToggleStyle createToggleStyleFromString(EDataType eDataType, String initialValue) {
        ToggleStyle result = ToggleStyle.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertToggleStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public TitleBarStyle createTitleBarStyleFromString(EDataType eDataType, String initialValue) {
        TitleBarStyle result = TitleBarStyle.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertTitleBarStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PropertiesPackage getPropertiesPackage() {
        return (PropertiesPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PropertiesPackage getPackage() {
        return PropertiesPackage.eINSTANCE;
    }

} // PropertiesFactoryImpl
