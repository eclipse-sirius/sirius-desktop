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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonWidgetConditionalStyle;
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ControlDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.CustomWidgetConditionalStyle;
import org.eclipse.sirius.properties.CustomWidgetStyle;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.EditSupport;
import org.eclipse.sirius.properties.FillLayoutDescription;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.GroupConditionalStyle;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupStyle;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle;
import org.eclipse.sirius.properties.HyperlinkWidgetStyle;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetConditionalStyle;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.LayoutDescription;
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListWidgetConditionalStyle;
import org.eclipse.sirius.properties.ListWidgetStyle;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
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
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetAction;
import org.eclipse.sirius.properties.WidgetConditionalStyle;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.properties.WidgetStyle;
import org.eclipse.sirius.viewpoint.description.Extension;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage
 * @generated
 */
public class PropertiesSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static PropertiesPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public PropertiesSwitch() {
        if (PropertiesSwitch.modelPackage == null) {
            PropertiesSwitch.modelPackage = PropertiesPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == PropertiesSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case PropertiesPackage.VIEW_EXTENSION_DESCRIPTION: {
            ViewExtensionDescription viewExtensionDescription = (ViewExtensionDescription) theEObject;
            T result = caseViewExtensionDescription(viewExtensionDescription);
            if (result == null) {
                result = caseExtension(viewExtensionDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.PAGE_DESCRIPTION: {
            PageDescription pageDescription = (PageDescription) theEObject;
            T result = casePageDescription(pageDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION: {
            PageValidationSetDescription pageValidationSetDescription = (PageValidationSetDescription) theEObject;
            T result = casePageValidationSetDescription(pageValidationSetDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.PROPERTY_VALIDATION_RULE: {
            PropertyValidationRule propertyValidationRule = (PropertyValidationRule) theEObject;
            T result = casePropertyValidationRule(propertyValidationRule);
            if (result == null) {
                result = caseValidationRule(propertyValidationRule);
            }
            if (result == null) {
                result = caseIdentifiedElement(propertyValidationRule);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GROUP_DESCRIPTION: {
            GroupDescription groupDescription = (GroupDescription) theEObject;
            T result = caseGroupDescription(groupDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION: {
            GroupValidationSetDescription groupValidationSetDescription = (GroupValidationSetDescription) theEObject;
            T result = caseGroupValidationSetDescription(groupValidationSetDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CONTROL_DESCRIPTION: {
            ControlDescription controlDescription = (ControlDescription) theEObject;
            T result = caseControlDescription(controlDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CONTAINER_DESCRIPTION: {
            ContainerDescription containerDescription = (ContainerDescription) theEObject;
            T result = caseContainerDescription(containerDescription);
            if (result == null) {
                result = caseControlDescription(containerDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LAYOUT_DESCRIPTION: {
            LayoutDescription layoutDescription = (LayoutDescription) theEObject;
            T result = caseLayoutDescription(layoutDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.FILL_LAYOUT_DESCRIPTION: {
            FillLayoutDescription fillLayoutDescription = (FillLayoutDescription) theEObject;
            T result = caseFillLayoutDescription(fillLayoutDescription);
            if (result == null) {
                result = caseLayoutDescription(fillLayoutDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION: {
            GridLayoutDescription gridLayoutDescription = (GridLayoutDescription) theEObject;
            T result = caseGridLayoutDescription(gridLayoutDescription);
            if (result == null) {
                result = caseLayoutDescription(gridLayoutDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.WIDGET_DESCRIPTION: {
            WidgetDescription widgetDescription = (WidgetDescription) theEObject;
            T result = caseWidgetDescription(widgetDescription);
            if (result == null) {
                result = caseControlDescription(widgetDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_DESCRIPTION: {
            TextDescription textDescription = (TextDescription) theEObject;
            T result = caseTextDescription(textDescription);
            if (result == null) {
                result = caseWidgetDescription(textDescription);
            }
            if (result == null) {
                result = caseControlDescription(textDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.BUTTON_DESCRIPTION: {
            ButtonDescription buttonDescription = (ButtonDescription) theEObject;
            T result = caseButtonDescription(buttonDescription);
            if (result == null) {
                result = caseWidgetDescription(buttonDescription);
            }
            if (result == null) {
                result = caseControlDescription(buttonDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LABEL_DESCRIPTION: {
            LabelDescription labelDescription = (LabelDescription) theEObject;
            T result = caseLabelDescription(labelDescription);
            if (result == null) {
                result = caseWidgetDescription(labelDescription);
            }
            if (result == null) {
                result = caseControlDescription(labelDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CHECKBOX_DESCRIPTION: {
            CheckboxDescription checkboxDescription = (CheckboxDescription) theEObject;
            T result = caseCheckboxDescription(checkboxDescription);
            if (result == null) {
                result = caseWidgetDescription(checkboxDescription);
            }
            if (result == null) {
                result = caseControlDescription(checkboxDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.SELECT_DESCRIPTION: {
            SelectDescription selectDescription = (SelectDescription) theEObject;
            T result = caseSelectDescription(selectDescription);
            if (result == null) {
                result = caseWidgetDescription(selectDescription);
            }
            if (result == null) {
                result = caseControlDescription(selectDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_FOR: {
            DynamicMappingFor dynamicMappingFor = (DynamicMappingFor) theEObject;
            T result = caseDynamicMappingFor(dynamicMappingFor);
            if (result == null) {
                result = caseControlDescription(dynamicMappingFor);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_IF: {
            DynamicMappingIf dynamicMappingIf = (DynamicMappingIf) theEObject;
            T result = caseDynamicMappingIf(dynamicMappingIf);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_AREA_DESCRIPTION: {
            TextAreaDescription textAreaDescription = (TextAreaDescription) theEObject;
            T result = caseTextAreaDescription(textAreaDescription);
            if (result == null) {
                result = caseTextDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseWidgetDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseControlDescription(textAreaDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.RADIO_DESCRIPTION: {
            RadioDescription radioDescription = (RadioDescription) theEObject;
            T result = caseRadioDescription(radioDescription);
            if (result == null) {
                result = caseWidgetDescription(radioDescription);
            }
            if (result == null) {
                result = caseControlDescription(radioDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LIST_DESCRIPTION: {
            ListDescription listDescription = (ListDescription) theEObject;
            T result = caseListDescription(listDescription);
            if (result == null) {
                result = caseWidgetDescription(listDescription);
            }
            if (result == null) {
                result = caseControlDescription(listDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.OPERATION_DESCRIPTION: {
            OperationDescription operationDescription = (OperationDescription) theEObject;
            T result = caseOperationDescription(operationDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_DESCRIPTION: {
            CustomDescription customDescription = (CustomDescription) theEObject;
            T result = caseCustomDescription(customDescription);
            if (result == null) {
                result = caseWidgetDescription(customDescription);
            }
            if (result == null) {
                result = caseControlDescription(customDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_EXPRESSION: {
            CustomExpression customExpression = (CustomExpression) theEObject;
            T result = caseCustomExpression(customExpression);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_OPERATION: {
            CustomOperation customOperation = (CustomOperation) theEObject;
            T result = caseCustomOperation(customOperation);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.HYPERLINK_DESCRIPTION: {
            HyperlinkDescription hyperlinkDescription = (HyperlinkDescription) theEObject;
            T result = caseHyperlinkDescription(hyperlinkDescription);
            if (result == null) {
                result = caseWidgetDescription(hyperlinkDescription);
            }
            if (result == null) {
                result = caseControlDescription(hyperlinkDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.WIDGET_STYLE: {
            WidgetStyle widgetStyle = (WidgetStyle) theEObject;
            T result = caseWidgetStyle(widgetStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_WIDGET_STYLE: {
            TextWidgetStyle textWidgetStyle = (TextWidgetStyle) theEObject;
            T result = caseTextWidgetStyle(textWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(textWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LABEL_WIDGET_STYLE: {
            LabelWidgetStyle labelWidgetStyle = (LabelWidgetStyle) theEObject;
            T result = caseLabelWidgetStyle(labelWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(labelWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CHECKBOX_WIDGET_STYLE: {
            CheckboxWidgetStyle checkboxWidgetStyle = (CheckboxWidgetStyle) theEObject;
            T result = caseCheckboxWidgetStyle(checkboxWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(checkboxWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.RADIO_WIDGET_STYLE: {
            RadioWidgetStyle radioWidgetStyle = (RadioWidgetStyle) theEObject;
            T result = caseRadioWidgetStyle(radioWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(radioWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.BUTTON_WIDGET_STYLE: {
            ButtonWidgetStyle buttonWidgetStyle = (ButtonWidgetStyle) theEObject;
            T result = caseButtonWidgetStyle(buttonWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(buttonWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.SELECT_WIDGET_STYLE: {
            SelectWidgetStyle selectWidgetStyle = (SelectWidgetStyle) theEObject;
            T result = caseSelectWidgetStyle(selectWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(selectWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_WIDGET_STYLE: {
            CustomWidgetStyle customWidgetStyle = (CustomWidgetStyle) theEObject;
            T result = caseCustomWidgetStyle(customWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(customWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LIST_WIDGET_STYLE: {
            ListWidgetStyle listWidgetStyle = (ListWidgetStyle) theEObject;
            T result = caseListWidgetStyle(listWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(listWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.HYPERLINK_WIDGET_STYLE: {
            HyperlinkWidgetStyle hyperlinkWidgetStyle = (HyperlinkWidgetStyle) theEObject;
            T result = caseHyperlinkWidgetStyle(hyperlinkWidgetStyle);
            if (result == null) {
                result = caseWidgetStyle(hyperlinkWidgetStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GROUP_STYLE: {
            GroupStyle groupStyle = (GroupStyle) theEObject;
            T result = caseGroupStyle(groupStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.WIDGET_CONDITIONAL_STYLE: {
            WidgetConditionalStyle widgetConditionalStyle = (WidgetConditionalStyle) theEObject;
            T result = caseWidgetConditionalStyle(widgetConditionalStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_WIDGET_CONDITIONAL_STYLE: {
            TextWidgetConditionalStyle textWidgetConditionalStyle = (TextWidgetConditionalStyle) theEObject;
            T result = caseTextWidgetConditionalStyle(textWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(textWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LABEL_WIDGET_CONDITIONAL_STYLE: {
            LabelWidgetConditionalStyle labelWidgetConditionalStyle = (LabelWidgetConditionalStyle) theEObject;
            T result = caseLabelWidgetConditionalStyle(labelWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(labelWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CHECKBOX_WIDGET_CONDITIONAL_STYLE: {
            CheckboxWidgetConditionalStyle checkboxWidgetConditionalStyle = (CheckboxWidgetConditionalStyle) theEObject;
            T result = caseCheckboxWidgetConditionalStyle(checkboxWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(checkboxWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.RADIO_WIDGET_CONDITIONAL_STYLE: {
            RadioWidgetConditionalStyle radioWidgetConditionalStyle = (RadioWidgetConditionalStyle) theEObject;
            T result = caseRadioWidgetConditionalStyle(radioWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(radioWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.BUTTON_WIDGET_CONDITIONAL_STYLE: {
            ButtonWidgetConditionalStyle buttonWidgetConditionalStyle = (ButtonWidgetConditionalStyle) theEObject;
            T result = caseButtonWidgetConditionalStyle(buttonWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(buttonWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.SELECT_WIDGET_CONDITIONAL_STYLE: {
            SelectWidgetConditionalStyle selectWidgetConditionalStyle = (SelectWidgetConditionalStyle) theEObject;
            T result = caseSelectWidgetConditionalStyle(selectWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(selectWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_WIDGET_CONDITIONAL_STYLE: {
            CustomWidgetConditionalStyle customWidgetConditionalStyle = (CustomWidgetConditionalStyle) theEObject;
            T result = caseCustomWidgetConditionalStyle(customWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(customWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LIST_WIDGET_CONDITIONAL_STYLE: {
            ListWidgetConditionalStyle listWidgetConditionalStyle = (ListWidgetConditionalStyle) theEObject;
            T result = caseListWidgetConditionalStyle(listWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(listWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.WIDGET_ACTION: {
            WidgetAction widgetAction = (WidgetAction) theEObject;
            T result = caseWidgetAction(widgetAction);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.HYPERLINK_WIDGET_CONDITIONAL_STYLE: {
            HyperlinkWidgetConditionalStyle hyperlinkWidgetConditionalStyle = (HyperlinkWidgetConditionalStyle) theEObject;
            T result = caseHyperlinkWidgetConditionalStyle(hyperlinkWidgetConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(hyperlinkWidgetConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GROUP_CONDITIONAL_STYLE: {
            GroupConditionalStyle groupConditionalStyle = (GroupConditionalStyle) theEObject;
            T result = caseGroupConditionalStyle(groupConditionalStyle);
            if (result == null) {
                result = caseWidgetConditionalStyle(groupConditionalStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.EDIT_SUPPORT: {
            EditSupport editSupport = (EditSupport) theEObject;
            T result = caseEditSupport(editSupport);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>View Extension Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>View Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseViewExtensionDescription(ViewExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Page Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Page Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePageDescription(PageDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Page Validation Set Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Page Validation Set Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePageValidationSetDescription(PageValidationSetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Property Validation Rule</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Property Validation Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePropertyValidationRule(PropertyValidationRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Group Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Group Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupDescription(GroupDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Group Validation Set Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Group Validation Set Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupValidationSetDescription(GroupValidationSetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Control Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Control Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseControlDescription(ControlDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerDescription(ContainerDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Layout Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Layout Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayoutDescription(LayoutDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Fill Layout Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Fill Layout Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFillLayoutDescription(FillLayoutDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Grid Layout Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Grid Layout Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGridLayoutDescription(GridLayoutDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Widget Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Widget Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetDescription(WidgetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Text Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Text Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextDescription(TextDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Button Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Button Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonDescription(ButtonDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelDescription(LabelDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Checkbox Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Checkbox Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxDescription(CheckboxDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Select Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Select Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectDescription(SelectDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Dynamic Mapping For</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Dynamic Mapping For</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicMappingFor(DynamicMappingFor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Dynamic Mapping If</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Dynamic Mapping If</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicMappingIf(DynamicMappingIf object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Text Area Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Text Area Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextAreaDescription(TextAreaDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Radio Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Radio Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioDescription(RadioDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>List Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>List Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListDescription(ListDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Operation Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Operation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperationDescription(OperationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Description</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomDescription(CustomDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Expression</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomExpression(CustomExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Operation</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomOperation(CustomOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hyperlink Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hyperlink Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkDescription(HyperlinkDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Widget Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetStyle(WidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Text Widget Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Text Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextWidgetStyle(TextWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Widget Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelWidgetStyle(LabelWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Checkbox Widget Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Checkbox Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxWidgetStyle(CheckboxWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Radio Widget Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Radio Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioWidgetStyle(RadioWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Button Widget Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Button Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonWidgetStyle(ButtonWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Select Widget Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Select Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectWidgetStyle(SelectWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Widget Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomWidgetStyle(CustomWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>List Widget Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>List Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListWidgetStyle(ListWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hyperlink Widget Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hyperlink Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkWidgetStyle(HyperlinkWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Group Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Group Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupStyle(GroupStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetConditionalStyle(WidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Text Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Text Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextWidgetConditionalStyle(TextWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelWidgetConditionalStyle(LabelWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Checkbox Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Checkbox Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxWidgetConditionalStyle(CheckboxWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Radio Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Radio Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioWidgetConditionalStyle(RadioWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Button Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Button Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonWidgetConditionalStyle(ButtonWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Select Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Select Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectWidgetConditionalStyle(SelectWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomWidgetConditionalStyle(CustomWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>List Widget Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>List Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListWidgetConditionalStyle(ListWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Widget Action</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Widget Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetAction(WidgetAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hyperlink Widget Conditional Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hyperlink Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkWidgetConditionalStyle(HyperlinkWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Group Conditional Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Group Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupConditionalStyle(GroupConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edit Support</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edit Support</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEditSupport(EditSupport object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Extension</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtension(Extension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Identified Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Rule</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValidationRule(ValidationRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // PropertiesSwitch
