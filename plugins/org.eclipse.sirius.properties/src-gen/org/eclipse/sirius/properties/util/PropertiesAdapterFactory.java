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
package org.eclipse.sirius.properties.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.AbstractButtonDescription;
import org.eclipse.sirius.properties.AbstractCheckboxDescription;
import org.eclipse.sirius.properties.AbstractContainerDescription;
import org.eclipse.sirius.properties.AbstractControlDescription;
import org.eclipse.sirius.properties.AbstractCustomDescription;
import org.eclipse.sirius.properties.AbstractDynamicMappingForDescription;
import org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription;
import org.eclipse.sirius.properties.AbstractGroupDescription;
import org.eclipse.sirius.properties.AbstractHyperlinkDescription;
import org.eclipse.sirius.properties.AbstractLabelDescription;
import org.eclipse.sirius.properties.AbstractListDescription;
import org.eclipse.sirius.properties.AbstractOverrideDescription;
import org.eclipse.sirius.properties.AbstractPageDescription;
import org.eclipse.sirius.properties.AbstractRadioDescription;
import org.eclipse.sirius.properties.AbstractSelectDescription;
import org.eclipse.sirius.properties.AbstractTextAreaDescription;
import org.eclipse.sirius.properties.AbstractTextDescription;
import org.eclipse.sirius.properties.AbstractWidgetDescription;
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
import org.eclipse.sirius.properties.ControlDescription;
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
import org.eclipse.sirius.properties.LayoutDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListOverrideDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageOverrideDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
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
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.WidgetConditionalStyle;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.properties.WizardModelOperation;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.Extension;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage
 * @generated
 */
public class PropertiesAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static PropertiesPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PropertiesAdapterFactory() {
        if (PropertiesAdapterFactory.modelPackage == null) {
            PropertiesAdapterFactory.modelPackage = PropertiesPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == PropertiesAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == PropertiesAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PropertiesSwitch<Adapter> modelSwitch = new PropertiesSwitch<Adapter>() {
        @Override
        public Adapter caseViewExtensionDescription(ViewExtensionDescription object) {
            return createViewExtensionDescriptionAdapter();
        }

        @Override
        public Adapter caseCategory(Category object) {
            return createCategoryAdapter();
        }

        @Override
        public Adapter caseAbstractOverrideDescription(AbstractOverrideDescription object) {
            return createAbstractOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractPageDescription(AbstractPageDescription object) {
            return createAbstractPageDescriptionAdapter();
        }

        @Override
        public Adapter casePageDescription(PageDescription object) {
            return createPageDescriptionAdapter();
        }

        @Override
        public Adapter casePageOverrideDescription(PageOverrideDescription object) {
            return createPageOverrideDescriptionAdapter();
        }

        @Override
        public Adapter casePageValidationSetDescription(PageValidationSetDescription object) {
            return createPageValidationSetDescriptionAdapter();
        }

        @Override
        public Adapter casePropertyValidationRule(PropertyValidationRule object) {
            return createPropertyValidationRuleAdapter();
        }

        @Override
        public Adapter caseAbstractGroupDescription(AbstractGroupDescription object) {
            return createAbstractGroupDescriptionAdapter();
        }

        @Override
        public Adapter caseToolbarAction(ToolbarAction object) {
            return createToolbarActionAdapter();
        }

        @Override
        public Adapter caseGroupDescription(GroupDescription object) {
            return createGroupDescriptionAdapter();
        }

        @Override
        public Adapter caseGroupOverrideDescription(GroupOverrideDescription object) {
            return createGroupOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseGroupValidationSetDescription(GroupValidationSetDescription object) {
            return createGroupValidationSetDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractControlDescription(AbstractControlDescription object) {
            return createAbstractControlDescriptionAdapter();
        }

        @Override
        public Adapter caseControlDescription(ControlDescription object) {
            return createControlDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractContainerDescription(AbstractContainerDescription object) {
            return createAbstractContainerDescriptionAdapter();
        }

        @Override
        public Adapter caseContainerDescription(ContainerDescription object) {
            return createContainerDescriptionAdapter();
        }

        @Override
        public Adapter caseContainerOverrideDescription(ContainerOverrideDescription object) {
            return createContainerOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseLayoutDescription(LayoutDescription object) {
            return createLayoutDescriptionAdapter();
        }

        @Override
        public Adapter caseFillLayoutDescription(FillLayoutDescription object) {
            return createFillLayoutDescriptionAdapter();
        }

        @Override
        public Adapter caseGridLayoutDescription(GridLayoutDescription object) {
            return createGridLayoutDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractWidgetDescription(AbstractWidgetDescription object) {
            return createAbstractWidgetDescriptionAdapter();
        }

        @Override
        public Adapter caseWidgetDescription(WidgetDescription object) {
            return createWidgetDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractTextDescription(AbstractTextDescription object) {
            return createAbstractTextDescriptionAdapter();
        }

        @Override
        public Adapter caseTextDescription(TextDescription object) {
            return createTextDescriptionAdapter();
        }

        @Override
        public Adapter caseTextOverrideDescription(TextOverrideDescription object) {
            return createTextOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractButtonDescription(AbstractButtonDescription object) {
            return createAbstractButtonDescriptionAdapter();
        }

        @Override
        public Adapter caseButtonDescription(ButtonDescription object) {
            return createButtonDescriptionAdapter();
        }

        @Override
        public Adapter caseButtonOverrideDescription(ButtonOverrideDescription object) {
            return createButtonOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractLabelDescription(AbstractLabelDescription object) {
            return createAbstractLabelDescriptionAdapter();
        }

        @Override
        public Adapter caseLabelDescription(LabelDescription object) {
            return createLabelDescriptionAdapter();
        }

        @Override
        public Adapter caseLabelOverrideDescription(LabelOverrideDescription object) {
            return createLabelOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractCheckboxDescription(AbstractCheckboxDescription object) {
            return createAbstractCheckboxDescriptionAdapter();
        }

        @Override
        public Adapter caseCheckboxDescription(CheckboxDescription object) {
            return createCheckboxDescriptionAdapter();
        }

        @Override
        public Adapter caseCheckboxOverrideDescription(CheckboxOverrideDescription object) {
            return createCheckboxOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractSelectDescription(AbstractSelectDescription object) {
            return createAbstractSelectDescriptionAdapter();
        }

        @Override
        public Adapter caseSelectDescription(SelectDescription object) {
            return createSelectDescriptionAdapter();
        }

        @Override
        public Adapter caseSelectOverrideDescription(SelectOverrideDescription object) {
            return createSelectOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractDynamicMappingForDescription(AbstractDynamicMappingForDescription object) {
            return createAbstractDynamicMappingForDescriptionAdapter();
        }

        @Override
        public Adapter caseDynamicMappingForDescription(DynamicMappingForDescription object) {
            return createDynamicMappingForDescriptionAdapter();
        }

        @Override
        public Adapter caseDynamicMappingForOverrideDescription(DynamicMappingForOverrideDescription object) {
            return createDynamicMappingForOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractDynamicMappingIfDescription(AbstractDynamicMappingIfDescription object) {
            return createAbstractDynamicMappingIfDescriptionAdapter();
        }

        @Override
        public Adapter caseDynamicMappingIfDescription(DynamicMappingIfDescription object) {
            return createDynamicMappingIfDescriptionAdapter();
        }

        @Override
        public Adapter caseDynamicMappingIfOverrideDescription(DynamicMappingIfOverrideDescription object) {
            return createDynamicMappingIfOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractTextAreaDescription(AbstractTextAreaDescription object) {
            return createAbstractTextAreaDescriptionAdapter();
        }

        @Override
        public Adapter caseTextAreaDescription(TextAreaDescription object) {
            return createTextAreaDescriptionAdapter();
        }

        @Override
        public Adapter caseTextAreaOverrideDescription(TextAreaOverrideDescription object) {
            return createTextAreaOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractRadioDescription(AbstractRadioDescription object) {
            return createAbstractRadioDescriptionAdapter();
        }

        @Override
        public Adapter caseRadioDescription(RadioDescription object) {
            return createRadioDescriptionAdapter();
        }

        @Override
        public Adapter caseRadioOverrideDescription(RadioOverrideDescription object) {
            return createRadioOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractListDescription(AbstractListDescription object) {
            return createAbstractListDescriptionAdapter();
        }

        @Override
        public Adapter caseListDescription(ListDescription object) {
            return createListDescriptionAdapter();
        }

        @Override
        public Adapter caseListOverrideDescription(ListOverrideDescription object) {
            return createListOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseOperationDescription(OperationDescription object) {
            return createOperationDescriptionAdapter();
        }

        @Override
        public Adapter caseAbstractCustomDescription(AbstractCustomDescription object) {
            return createAbstractCustomDescriptionAdapter();
        }

        @Override
        public Adapter caseCustomDescription(CustomDescription object) {
            return createCustomDescriptionAdapter();
        }

        @Override
        public Adapter caseCustomOverrideDescription(CustomOverrideDescription object) {
            return createCustomOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseCustomExpression(CustomExpression object) {
            return createCustomExpressionAdapter();
        }

        @Override
        public Adapter caseCustomOperation(CustomOperation object) {
            return createCustomOperationAdapter();
        }

        @Override
        public Adapter caseAbstractHyperlinkDescription(AbstractHyperlinkDescription object) {
            return createAbstractHyperlinkDescriptionAdapter();
        }

        @Override
        public Adapter caseHyperlinkDescription(HyperlinkDescription object) {
            return createHyperlinkDescriptionAdapter();
        }

        @Override
        public Adapter caseHyperlinkOverrideDescription(HyperlinkOverrideDescription object) {
            return createHyperlinkOverrideDescriptionAdapter();
        }

        @Override
        public Adapter caseWidgetStyle(WidgetStyle object) {
            return createWidgetStyleAdapter();
        }

        @Override
        public Adapter caseTextWidgetStyle(TextWidgetStyle object) {
            return createTextWidgetStyleAdapter();
        }

        @Override
        public Adapter caseLabelWidgetStyle(LabelWidgetStyle object) {
            return createLabelWidgetStyleAdapter();
        }

        @Override
        public Adapter caseCheckboxWidgetStyle(CheckboxWidgetStyle object) {
            return createCheckboxWidgetStyleAdapter();
        }

        @Override
        public Adapter caseRadioWidgetStyle(RadioWidgetStyle object) {
            return createRadioWidgetStyleAdapter();
        }

        @Override
        public Adapter caseButtonWidgetStyle(ButtonWidgetStyle object) {
            return createButtonWidgetStyleAdapter();
        }

        @Override
        public Adapter caseSelectWidgetStyle(SelectWidgetStyle object) {
            return createSelectWidgetStyleAdapter();
        }

        @Override
        public Adapter caseCustomWidgetStyle(CustomWidgetStyle object) {
            return createCustomWidgetStyleAdapter();
        }

        @Override
        public Adapter caseListWidgetStyle(ListWidgetStyle object) {
            return createListWidgetStyleAdapter();
        }

        @Override
        public Adapter caseHyperlinkWidgetStyle(HyperlinkWidgetStyle object) {
            return createHyperlinkWidgetStyleAdapter();
        }

        @Override
        public Adapter caseGroupStyle(GroupStyle object) {
            return createGroupStyleAdapter();
        }

        @Override
        public Adapter caseWidgetConditionalStyle(WidgetConditionalStyle object) {
            return createWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseTextWidgetConditionalStyle(TextWidgetConditionalStyle object) {
            return createTextWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseLabelWidgetConditionalStyle(LabelWidgetConditionalStyle object) {
            return createLabelWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseCheckboxWidgetConditionalStyle(CheckboxWidgetConditionalStyle object) {
            return createCheckboxWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseRadioWidgetConditionalStyle(RadioWidgetConditionalStyle object) {
            return createRadioWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseButtonWidgetConditionalStyle(ButtonWidgetConditionalStyle object) {
            return createButtonWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseSelectWidgetConditionalStyle(SelectWidgetConditionalStyle object) {
            return createSelectWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseCustomWidgetConditionalStyle(CustomWidgetConditionalStyle object) {
            return createCustomWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseListWidgetConditionalStyle(ListWidgetConditionalStyle object) {
            return createListWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseWidgetAction(WidgetAction object) {
            return createWidgetActionAdapter();
        }

        @Override
        public Adapter caseHyperlinkWidgetConditionalStyle(HyperlinkWidgetConditionalStyle object) {
            return createHyperlinkWidgetConditionalStyleAdapter();
        }

        @Override
        public Adapter caseGroupConditionalStyle(GroupConditionalStyle object) {
            return createGroupConditionalStyleAdapter();
        }

        @Override
        public Adapter caseDialogModelOperation(DialogModelOperation object) {
            return createDialogModelOperationAdapter();
        }

        @Override
        public Adapter caseDialogButton(DialogButton object) {
            return createDialogButtonAdapter();
        }

        @Override
        public Adapter caseWizardModelOperation(WizardModelOperation object) {
            return createWizardModelOperationAdapter();
        }

        @Override
        public Adapter caseEditSupport(EditSupport object) {
            return createEditSupportAdapter();
        }

        @Override
        public Adapter caseExtension(Extension object) {
            return createExtensionAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseValidationRule(ValidationRule object) {
            return createValidationRuleAdapter();
        }

        @Override
        public Adapter caseModelOperation(ModelOperation object) {
            return createModelOperationAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ViewExtensionDescription
     * <em>View Extension Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription
     * @generated
     */
    public Adapter createViewExtensionDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.Category <em>Category</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.Category
     * @generated
     */
    public Adapter createCategoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractOverrideDescription
     * <em>Abstract Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractOverrideDescription
     * @generated
     */
    public Adapter createAbstractOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractPageDescription
     * <em>Abstract Page Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractPageDescription
     * @generated
     */
    public Adapter createAbstractPageDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.PageDescription <em>Page
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.PageDescription
     * @generated
     */
    public Adapter createPageDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.PageOverrideDescription
     * <em>Page Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.PageOverrideDescription
     * @generated
     */
    public Adapter createPageOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.PageValidationSetDescription
     * <em>Page Validation Set Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.PageValidationSetDescription
     * @generated
     */
    public Adapter createPageValidationSetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.PropertyValidationRule
     * <em>Property Validation Rule</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.PropertyValidationRule
     * @generated
     */
    public Adapter createPropertyValidationRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractGroupDescription
     * <em>Abstract Group Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription
     * @generated
     */
    public Adapter createAbstractGroupDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ToolbarAction <em>Toolbar
     * Action</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ToolbarAction
     * @generated
     */
    public Adapter createToolbarActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GroupDescription <em>Group
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GroupDescription
     * @generated
     */
    public Adapter createGroupDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GroupOverrideDescription
     * <em>Group Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GroupOverrideDescription
     * @generated
     */
    public Adapter createGroupOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GroupValidationSetDescription
     * <em>Group Validation Set Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription
     * @generated
     */
    public Adapter createGroupValidationSetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractControlDescription
     * <em>Abstract Control Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractControlDescription
     * @generated
     */
    public Adapter createAbstractControlDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ControlDescription <em>Control
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ControlDescription
     * @generated
     */
    public Adapter createControlDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractContainerDescription
     * <em>Abstract Container Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractContainerDescription
     * @generated
     */
    public Adapter createAbstractContainerDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ContainerDescription
     * <em>Container Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ContainerDescription
     * @generated
     */
    public Adapter createContainerDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ContainerOverrideDescription
     * <em>Container Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ContainerOverrideDescription
     * @generated
     */
    public Adapter createContainerOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.LayoutDescription <em>Layout
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.LayoutDescription
     * @generated
     */
    public Adapter createLayoutDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.FillLayoutDescription <em>Fill
     * Layout Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.FillLayoutDescription
     * @generated
     */
    public Adapter createFillLayoutDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GridLayoutDescription <em>Grid
     * Layout Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GridLayoutDescription
     * @generated
     */
    public Adapter createGridLayoutDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractWidgetDescription
     * <em>Abstract Widget Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractWidgetDescription
     * @generated
     */
    public Adapter createAbstractWidgetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.WidgetDescription <em>Widget
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.WidgetDescription
     * @generated
     */
    public Adapter createWidgetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractTextDescription
     * <em>Abstract Text Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractTextDescription
     * @generated
     */
    public Adapter createAbstractTextDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextDescription <em>Text
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextDescription
     * @generated
     */
    public Adapter createTextDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextOverrideDescription
     * <em>Text Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextOverrideDescription
     * @generated
     */
    public Adapter createTextOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractButtonDescription
     * <em>Abstract Button Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription
     * @generated
     */
    public Adapter createAbstractButtonDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ButtonDescription <em>Button
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ButtonDescription
     * @generated
     */
    public Adapter createButtonDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ButtonOverrideDescription
     * <em>Button Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ButtonOverrideDescription
     * @generated
     */
    public Adapter createButtonOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractLabelDescription
     * <em>Abstract Label Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription
     * @generated
     */
    public Adapter createAbstractLabelDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.LabelDescription <em>Label
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.LabelDescription
     * @generated
     */
    public Adapter createLabelDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.LabelOverrideDescription
     * <em>Label Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.LabelOverrideDescription
     * @generated
     */
    public Adapter createLabelOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractCheckboxDescription
     * <em>Abstract Checkbox Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription
     * @generated
     */
    public Adapter createAbstractCheckboxDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CheckboxDescription
     * <em>Checkbox Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CheckboxDescription
     * @generated
     */
    public Adapter createCheckboxDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CheckboxOverrideDescription
     * <em>Checkbox Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CheckboxOverrideDescription
     * @generated
     */
    public Adapter createCheckboxOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractSelectDescription
     * <em>Abstract Select Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription
     * @generated
     */
    public Adapter createAbstractSelectDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.SelectDescription <em>Select
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.SelectDescription
     * @generated
     */
    public Adapter createSelectDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.SelectOverrideDescription
     * <em>Select Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.SelectOverrideDescription
     * @generated
     */
    public Adapter createSelectOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription <em>Abstract Dynamic Mapping For
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription
     * @generated
     */
    public Adapter createAbstractDynamicMappingForDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.DynamicMappingForDescription
     * <em>Dynamic Mapping For Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.DynamicMappingForDescription
     * @generated
     */
    public Adapter createDynamicMappingForDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription <em>Dynamic Mapping For Override
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.DynamicMappingForOverrideDescription
     * @generated
     */
    public Adapter createDynamicMappingForOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription <em>Abstract Dynamic Mapping If
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription
     * @generated
     */
    public Adapter createAbstractDynamicMappingIfDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.DynamicMappingIfDescription
     * <em>Dynamic Mapping If Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.DynamicMappingIfDescription
     * @generated
     */
    public Adapter createDynamicMappingIfDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription <em>Dynamic Mapping If Override
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription
     * @generated
     */
    public Adapter createDynamicMappingIfOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription
     * <em>Abstract Text Area Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription
     * @generated
     */
    public Adapter createAbstractTextAreaDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextAreaDescription <em>Text
     * Area Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextAreaDescription
     * @generated
     */
    public Adapter createTextAreaDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextAreaOverrideDescription
     * <em>Text Area Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextAreaOverrideDescription
     * @generated
     */
    public Adapter createTextAreaOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractRadioDescription
     * <em>Abstract Radio Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription
     * @generated
     */
    public Adapter createAbstractRadioDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.RadioDescription <em>Radio
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.RadioDescription
     * @generated
     */
    public Adapter createRadioDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.RadioOverrideDescription
     * <em>Radio Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.RadioOverrideDescription
     * @generated
     */
    public Adapter createRadioOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractListDescription
     * <em>Abstract List Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractListDescription
     * @generated
     */
    public Adapter createAbstractListDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ListDescription <em>List
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ListDescription
     * @generated
     */
    public Adapter createListDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ListOverrideDescription
     * <em>List Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ListOverrideDescription
     * @generated
     */
    public Adapter createListOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.OperationDescription
     * <em>Operation Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.OperationDescription
     * @generated
     */
    public Adapter createOperationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractCustomDescription
     * <em>Abstract Custom Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription
     * @generated
     */
    public Adapter createAbstractCustomDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CustomDescription <em>Custom
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CustomDescription
     * @generated
     */
    public Adapter createCustomDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CustomOverrideDescription
     * <em>Custom Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CustomOverrideDescription
     * @generated
     */
    public Adapter createCustomOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CustomExpression <em>Custom
     * Expression</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CustomExpression
     * @generated
     */
    public Adapter createCustomExpressionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CustomOperation <em>Custom
     * Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CustomOperation
     * @generated
     */
    public Adapter createCustomOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription
     * <em>Abstract Hyperlink Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription
     * @generated
     */
    public Adapter createAbstractHyperlinkDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.HyperlinkDescription
     * <em>Hyperlink Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.HyperlinkDescription
     * @generated
     */
    public Adapter createHyperlinkDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription
     * <em>Hyperlink Override Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.HyperlinkOverrideDescription
     * @generated
     */
    public Adapter createHyperlinkOverrideDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.WidgetStyle <em>Widget
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.WidgetStyle
     * @generated
     */
    public Adapter createWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextWidgetStyle <em>Text
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextWidgetStyle
     * @generated
     */
    public Adapter createTextWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.LabelWidgetStyle <em>Label
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle
     * @generated
     */
    public Adapter createLabelWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CheckboxWidgetStyle
     * <em>Checkbox Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CheckboxWidgetStyle
     * @generated
     */
    public Adapter createCheckboxWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.RadioWidgetStyle <em>Radio
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.RadioWidgetStyle
     * @generated
     */
    public Adapter createRadioWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ButtonWidgetStyle <em>Button
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ButtonWidgetStyle
     * @generated
     */
    public Adapter createButtonWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.SelectWidgetStyle <em>Select
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.SelectWidgetStyle
     * @generated
     */
    public Adapter createSelectWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CustomWidgetStyle <em>Custom
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CustomWidgetStyle
     * @generated
     */
    public Adapter createCustomWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ListWidgetStyle <em>List
     * Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ListWidgetStyle
     * @generated
     */
    public Adapter createListWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.HyperlinkWidgetStyle
     * <em>Hyperlink Widget Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle
     * @generated
     */
    public Adapter createHyperlinkWidgetStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GroupStyle <em>Group
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GroupStyle
     * @generated
     */
    public Adapter createGroupStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.WidgetConditionalStyle
     * <em>Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.WidgetConditionalStyle
     * @generated
     */
    public Adapter createWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.TextWidgetConditionalStyle
     * <em>Text Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.TextWidgetConditionalStyle
     * @generated
     */
    public Adapter createTextWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.LabelWidgetConditionalStyle
     * <em>Label Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.LabelWidgetConditionalStyle
     * @generated
     */
    public Adapter createLabelWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle
     * <em>Checkbox Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle
     * @generated
     */
    public Adapter createCheckboxWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.RadioWidgetConditionalStyle
     * <em>Radio Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.RadioWidgetConditionalStyle
     * @generated
     */
    public Adapter createRadioWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ButtonWidgetConditionalStyle
     * <em>Button Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ButtonWidgetConditionalStyle
     * @generated
     */
    public Adapter createButtonWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.SelectWidgetConditionalStyle
     * <em>Select Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.SelectWidgetConditionalStyle
     * @generated
     */
    public Adapter createSelectWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.CustomWidgetConditionalStyle
     * <em>Custom Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.CustomWidgetConditionalStyle
     * @generated
     */
    public Adapter createCustomWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.ListWidgetConditionalStyle
     * <em>List Widget Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.ListWidgetConditionalStyle
     * @generated
     */
    public Adapter createListWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.WidgetAction <em>Widget
     * Action</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.WidgetAction
     * @generated
     */
    public Adapter createWidgetActionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle <em>Hyperlink Widget Conditional
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle
     * @generated
     */
    public Adapter createHyperlinkWidgetConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.GroupConditionalStyle
     * <em>Group Conditional Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.GroupConditionalStyle
     * @generated
     */
    public Adapter createGroupConditionalStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.DialogModelOperation
     * <em>Dialog Model Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.DialogModelOperation
     * @generated
     */
    public Adapter createDialogModelOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.DialogButton <em>Dialog
     * Button</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.DialogButton
     * @generated
     */
    public Adapter createDialogButtonAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.WizardModelOperation
     * <em>Wizard Model Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.WizardModelOperation
     * @generated
     */
    public Adapter createWizardModelOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.properties.EditSupport <em>Edit
     * Support</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.properties.EditSupport
     * @generated
     */
    public Adapter createEditSupportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.Extension
     * <em>Extension</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.Extension
     * @generated
     */
    public Adapter createExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule <em>Rule</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore cases; it's useful to ignore a case
     * when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationRule
     * @generated
     */
    public Adapter createValidationRuleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * <em>Model Operation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * @generated
     */
    public Adapter createModelOperationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // PropertiesAdapterFactory
