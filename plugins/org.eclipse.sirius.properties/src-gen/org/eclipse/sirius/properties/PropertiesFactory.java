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
package org.eclipse.sirius.properties;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage
 * @generated
 */
public interface PropertiesFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PropertiesFactory eINSTANCE = org.eclipse.sirius.properties.impl.PropertiesFactoryImpl.init();

    /**
     * Returns a new object of class '<em>View Extension Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>View Extension Description</em>'.
     * @generated
     */
    ViewExtensionDescription createViewExtensionDescription();

    /**
     * Returns a new object of class '<em>Category</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Category</em>'.
     * @generated
     */
    Category createCategory();

    /**
     * Returns a new object of class '<em>Page Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Page Description</em>'.
     * @generated
     */
    PageDescription createPageDescription();

    /**
     * Returns a new object of class '<em>Page Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Page Override Description</em>'.
     * @generated
     */
    PageOverrideDescription createPageOverrideDescription();

    /**
     * Returns a new object of class '<em>Page Validation Set Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Page Validation Set Description</em>'.
     * @generated
     */
    PageValidationSetDescription createPageValidationSetDescription();

    /**
     * Returns a new object of class '<em>Property Validation Rule</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Property Validation Rule</em>'.
     * @generated
     */
    PropertyValidationRule createPropertyValidationRule();

    /**
     * Returns a new object of class '<em>Toolbar Action</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Toolbar Action</em>'.
     * @generated
     */
    ToolbarAction createToolbarAction();

    /**
     * Returns a new object of class '<em>Group Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Group Description</em>'.
     * @generated
     */
    GroupDescription createGroupDescription();

    /**
     * Returns a new object of class '<em>Group Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Group Override Description</em>'.
     * @generated
     */
    GroupOverrideDescription createGroupOverrideDescription();

    /**
     * Returns a new object of class '<em>Group Validation Set Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Group Validation Set Description</em>'.
     * @generated
     */
    GroupValidationSetDescription createGroupValidationSetDescription();

    /**
     * Returns a new object of class '<em>Container Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Description</em>'.
     * @generated
     */
    ContainerDescription createContainerDescription();

    /**
     * Returns a new object of class '<em>Container Override Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Container Override Description</em>'.
     * @generated
     */
    ContainerOverrideDescription createContainerOverrideDescription();

    /**
     * Returns a new object of class '<em>Fill Layout Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Fill Layout Description</em>'.
     * @generated
     */
    FillLayoutDescription createFillLayoutDescription();

    /**
     * Returns a new object of class '<em>Grid Layout Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Grid Layout Description</em>'.
     * @generated
     */
    GridLayoutDescription createGridLayoutDescription();

    /**
     * Returns a new object of class '<em>Text Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Text Description</em>'.
     * @generated
     */
    TextDescription createTextDescription();

    /**
     * Returns a new object of class '<em>Text Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Text Override Description</em>'.
     * @generated
     */
    TextOverrideDescription createTextOverrideDescription();

    /**
     * Returns a new object of class '<em>Button Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Button Description</em>'.
     * @generated
     */
    ButtonDescription createButtonDescription();

    /**
     * Returns a new object of class '<em>Button Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Button Override Description</em>'.
     * @generated
     */
    ButtonOverrideDescription createButtonOverrideDescription();

    /**
     * Returns a new object of class '<em>Label Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Description</em>'.
     * @generated
     */
    LabelDescription createLabelDescription();

    /**
     * Returns a new object of class '<em>Label Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Label Override Description</em>'.
     * @generated
     */
    LabelOverrideDescription createLabelOverrideDescription();

    /**
     * Returns a new object of class '<em>Checkbox Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Checkbox Description</em>'.
     * @generated
     */
    CheckboxDescription createCheckboxDescription();

    /**
     * Returns a new object of class '<em>Checkbox Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Checkbox Override Description</em>'.
     * @generated
     */
    CheckboxOverrideDescription createCheckboxOverrideDescription();

    /**
     * Returns a new object of class '<em>Select Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Select Description</em>'.
     * @generated
     */
    SelectDescription createSelectDescription();

    /**
     * Returns a new object of class '<em>Select Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Select Override Description</em>'.
     * @generated
     */
    SelectOverrideDescription createSelectOverrideDescription();

    /**
     * Returns a new object of class '<em>Dynamic Mapping For Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Dynamic Mapping For Description</em>'.
     * @generated
     */
    DynamicMappingForDescription createDynamicMappingForDescription();

    /**
     * Returns a new object of class '<em>Dynamic Mapping For Override Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Dynamic Mapping For Override Description</em>'.
     * @generated
     */
    DynamicMappingForOverrideDescription createDynamicMappingForOverrideDescription();

    /**
     * Returns a new object of class '<em>Dynamic Mapping If Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Dynamic Mapping If Description</em>'.
     * @generated
     */
    DynamicMappingIfDescription createDynamicMappingIfDescription();

    /**
     * Returns a new object of class '<em>Dynamic Mapping If Override Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Dynamic Mapping If Override Description</em>'.
     * @generated
     */
    DynamicMappingIfOverrideDescription createDynamicMappingIfOverrideDescription();

    /**
     * Returns a new object of class '<em>Text Area Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Text Area Description</em>'.
     * @generated
     */
    TextAreaDescription createTextAreaDescription();

    /**
     * Returns a new object of class '<em>Text Area Override Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Text Area Override Description</em>'.
     * @generated
     */
    TextAreaOverrideDescription createTextAreaOverrideDescription();

    /**
     * Returns a new object of class '<em>Radio Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Radio Description</em>'.
     * @generated
     */
    RadioDescription createRadioDescription();

    /**
     * Returns a new object of class '<em>Radio Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Radio Override Description</em>'.
     * @generated
     */
    RadioOverrideDescription createRadioOverrideDescription();

    /**
     * Returns a new object of class '<em>List Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>List Description</em>'.
     * @generated
     */
    ListDescription createListDescription();

    /**
     * Returns a new object of class '<em>List Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>List Override Description</em>'.
     * @generated
     */
    ListOverrideDescription createListOverrideDescription();

    /**
     * Returns a new object of class '<em>Operation Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Operation Description</em>'.
     * @generated
     */
    OperationDescription createOperationDescription();

    /**
     * Returns a new object of class '<em>Custom Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Custom Description</em>'.
     * @generated
     */
    CustomDescription createCustomDescription();

    /**
     * Returns a new object of class '<em>Custom Override Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Custom Override Description</em>'.
     * @generated
     */
    CustomOverrideDescription createCustomOverrideDescription();

    /**
     * Returns a new object of class '<em>Custom Expression</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Custom Expression</em>'.
     * @generated
     */
    CustomExpression createCustomExpression();

    /**
     * Returns a new object of class '<em>Custom Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Custom Operation</em>'.
     * @generated
     */
    CustomOperation createCustomOperation();

    /**
     * Returns a new object of class '<em>Hyperlink Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Hyperlink Description</em>'.
     * @generated
     */
    HyperlinkDescription createHyperlinkDescription();

    /**
     * Returns a new object of class '<em>Hyperlink Override Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Hyperlink Override Description</em>'.
     * @generated
     */
    HyperlinkOverrideDescription createHyperlinkOverrideDescription();

    /**
     * Returns a new object of class '<em>Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Widget Style</em>'.
     * @generated
     */
    WidgetStyle createWidgetStyle();

    /**
     * Returns a new object of class '<em>Text Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Text Widget Style</em>'.
     * @generated
     */
    TextWidgetStyle createTextWidgetStyle();

    /**
     * Returns a new object of class '<em>Label Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Widget Style</em>'.
     * @generated
     */
    LabelWidgetStyle createLabelWidgetStyle();

    /**
     * Returns a new object of class '<em>Checkbox Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Checkbox Widget Style</em>'.
     * @generated
     */
    CheckboxWidgetStyle createCheckboxWidgetStyle();

    /**
     * Returns a new object of class '<em>Radio Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Radio Widget Style</em>'.
     * @generated
     */
    RadioWidgetStyle createRadioWidgetStyle();

    /**
     * Returns a new object of class '<em>Button Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Button Widget Style</em>'.
     * @generated
     */
    ButtonWidgetStyle createButtonWidgetStyle();

    /**
     * Returns a new object of class '<em>Select Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Select Widget Style</em>'.
     * @generated
     */
    SelectWidgetStyle createSelectWidgetStyle();

    /**
     * Returns a new object of class '<em>Custom Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Custom Widget Style</em>'.
     * @generated
     */
    CustomWidgetStyle createCustomWidgetStyle();

    /**
     * Returns a new object of class '<em>List Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>List Widget Style</em>'.
     * @generated
     */
    ListWidgetStyle createListWidgetStyle();

    /**
     * Returns a new object of class '<em>Hyperlink Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Hyperlink Widget Style</em>'.
     * @generated
     */
    HyperlinkWidgetStyle createHyperlinkWidgetStyle();

    /**
     * Returns a new object of class '<em>Group Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Group Style</em>'.
     * @generated
     */
    GroupStyle createGroupStyle();

    /**
     * Returns a new object of class '<em>Text Widget Conditional Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Text Widget Conditional Style</em>'.
     * @generated
     */
    TextWidgetConditionalStyle createTextWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Label Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Label Widget Conditional Style</em>'.
     * @generated
     */
    LabelWidgetConditionalStyle createLabelWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Checkbox Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Checkbox Widget Conditional Style</em>'.
     * @generated
     */
    CheckboxWidgetConditionalStyle createCheckboxWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Radio Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Radio Widget Conditional Style</em>'.
     * @generated
     */
    RadioWidgetConditionalStyle createRadioWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Button Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Button Widget Conditional Style</em>'.
     * @generated
     */
    ButtonWidgetConditionalStyle createButtonWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Select Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Select Widget Conditional Style</em>'.
     * @generated
     */
    SelectWidgetConditionalStyle createSelectWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Custom Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Custom Widget Conditional Style</em>'.
     * @generated
     */
    CustomWidgetConditionalStyle createCustomWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>List Widget Conditional Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>List Widget Conditional Style</em>'.
     * @generated
     */
    ListWidgetConditionalStyle createListWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Widget Action</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Widget Action</em>'.
     * @generated
     */
    WidgetAction createWidgetAction();

    /**
     * Returns a new object of class '<em>Hyperlink Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Hyperlink Widget Conditional Style</em>'.
     * @generated
     */
    HyperlinkWidgetConditionalStyle createHyperlinkWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Group Conditional Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Group Conditional Style</em>'.
     * @generated
     */
    GroupConditionalStyle createGroupConditionalStyle();

    /**
     * Returns a new object of class '<em>Dialog Model Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Dialog Model Operation</em>'.
     * @generated
     */
    DialogModelOperation createDialogModelOperation();

    /**
     * Returns a new object of class '<em>Dialog Button</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Dialog Button</em>'.
     * @generated
     */
    DialogButton createDialogButton();

    /**
     * Returns a new object of class '<em>Wizard Model Operation</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Wizard Model Operation</em>'.
     * @generated
     */
    WizardModelOperation createWizardModelOperation();

    /**
     * Returns a new object of class '<em>Edit Support</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edit Support</em>'.
     * @generated
     */
    EditSupport createEditSupport();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    PropertiesPackage getPropertiesPackage();

} // PropertiesFactory
