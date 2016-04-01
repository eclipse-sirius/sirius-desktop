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
import org.eclipse.sirius.properties.ButtonWidgetStyle;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.CheckboxWidgetStyle;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.CustomDescription;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.CustomOperation;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelWidgetStyle;
import org.eclipse.sirius.properties.MultipleReferencesDescription;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioWidgetStyle;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SelectWidgetStyle;
import org.eclipse.sirius.properties.SingleReferenceDescription;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextWidgetStyle;
import org.eclipse.sirius.properties.ViewExtensionDescription;
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
        case PropertiesPackage.CONTAINER_DESCRIPTION: {
            ContainerDescription containerDescription = (ContainerDescription) theEObject;
            T result = caseContainerDescription(containerDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.WIDGET_DESCRIPTION: {
            WidgetDescription widgetDescription = (WidgetDescription) theEObject;
            T result = caseWidgetDescription(widgetDescription);
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
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_FOR: {
            DynamicMappingFor dynamicMappingFor = (DynamicMappingFor) theEObject;
            T result = caseDynamicMappingFor(dynamicMappingFor);
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
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION: {
            SingleReferenceDescription singleReferenceDescription = (SingleReferenceDescription) theEObject;
            T result = caseSingleReferenceDescription(singleReferenceDescription);
            if (result == null) {
                result = caseWidgetDescription(singleReferenceDescription);
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
        case PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION: {
            MultipleReferencesDescription multipleReferencesDescription = (MultipleReferencesDescription) theEObject;
            T result = caseMultipleReferencesDescription(multipleReferencesDescription);
            if (result == null) {
                result = caseWidgetDescription(multipleReferencesDescription);
            }
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
     * <em>Single Reference Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Single Reference Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSingleReferenceDescription(SingleReferenceDescription object) {
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
     * <em>Multiple References Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Multiple References Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMultipleReferencesDescription(MultipleReferencesDescription object) {
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
