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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
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
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public PropertiesSwitch() {
        if (PropertiesSwitch.modelPackage == null) {
            PropertiesSwitch.modelPackage = PropertiesPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
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
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
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
                result = caseIdentifiedElement(viewExtensionDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(viewExtensionDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CATEGORY: {
            Category category = (Category) theEObject;
            T result = caseCategory(category);
            if (result == null) {
                result = caseIdentifiedElement(category);
            }
            if (result == null) {
                result = caseDocumentedElement(category);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_OVERRIDE_DESCRIPTION: {
            AbstractOverrideDescription abstractOverrideDescription = (AbstractOverrideDescription) theEObject;
            T result = caseAbstractOverrideDescription(abstractOverrideDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION: {
            AbstractPageDescription abstractPageDescription = (AbstractPageDescription) theEObject;
            T result = caseAbstractPageDescription(abstractPageDescription);
            if (result == null) {
                result = caseIdentifiedElement(abstractPageDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractPageDescription);
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
                result = caseAbstractPageDescription(pageDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(pageDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(pageDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION: {
            PageOverrideDescription pageOverrideDescription = (PageOverrideDescription) theEObject;
            T result = casePageOverrideDescription(pageOverrideDescription);
            if (result == null) {
                result = caseAbstractPageDescription(pageOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(pageOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(pageOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(pageOverrideDescription);
            }
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
        case PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION: {
            AbstractGroupDescription abstractGroupDescription = (AbstractGroupDescription) theEObject;
            T result = caseAbstractGroupDescription(abstractGroupDescription);
            if (result == null) {
                result = caseIdentifiedElement(abstractGroupDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractGroupDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TOOLBAR_ACTION: {
            ToolbarAction toolbarAction = (ToolbarAction) theEObject;
            T result = caseToolbarAction(toolbarAction);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GROUP_DESCRIPTION: {
            GroupDescription groupDescription = (GroupDescription) theEObject;
            T result = caseGroupDescription(groupDescription);
            if (result == null) {
                result = caseAbstractGroupDescription(groupDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(groupDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(groupDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION: {
            GroupOverrideDescription groupOverrideDescription = (GroupOverrideDescription) theEObject;
            T result = caseGroupOverrideDescription(groupOverrideDescription);
            if (result == null) {
                result = caseAbstractGroupDescription(groupOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(groupOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(groupOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(groupOverrideDescription);
            }
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
        case PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION: {
            AbstractControlDescription abstractControlDescription = (AbstractControlDescription) theEObject;
            T result = caseAbstractControlDescription(abstractControlDescription);
            if (result == null) {
                result = caseIdentifiedElement(abstractControlDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractControlDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CONTROL_DESCRIPTION: {
            ControlDescription controlDescription = (ControlDescription) theEObject;
            T result = caseControlDescription(controlDescription);
            if (result == null) {
                result = caseAbstractControlDescription(controlDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(controlDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(controlDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION: {
            AbstractContainerDescription abstractContainerDescription = (AbstractContainerDescription) theEObject;
            T result = caseAbstractContainerDescription(abstractContainerDescription);
            if (result == null) {
                result = caseAbstractControlDescription(abstractContainerDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractContainerDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractContainerDescription);
            }
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
                result = caseAbstractContainerDescription(containerDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(containerDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(containerDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION: {
            ContainerOverrideDescription containerOverrideDescription = (ContainerOverrideDescription) theEObject;
            T result = caseContainerOverrideDescription(containerOverrideDescription);
            if (result == null) {
                result = caseAbstractContainerDescription(containerOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(containerOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(containerOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(containerOverrideDescription);
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
        case PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION: {
            AbstractWidgetDescription abstractWidgetDescription = (AbstractWidgetDescription) theEObject;
            T result = caseAbstractWidgetDescription(abstractWidgetDescription);
            if (result == null) {
                result = caseAbstractControlDescription(abstractWidgetDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractWidgetDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractWidgetDescription);
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
                result = caseAbstractControlDescription(widgetDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(widgetDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(widgetDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION: {
            AbstractTextDescription abstractTextDescription = (AbstractTextDescription) theEObject;
            T result = caseAbstractTextDescription(abstractTextDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractTextDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractTextDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractTextDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractTextDescription);
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
                result = caseAbstractTextDescription(textDescription);
            }
            if (result == null) {
                result = caseWidgetDescription(textDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(textDescription);
            }
            if (result == null) {
                result = caseControlDescription(textDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(textDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(textDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(textDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION: {
            TextOverrideDescription textOverrideDescription = (TextOverrideDescription) theEObject;
            T result = caseTextOverrideDescription(textOverrideDescription);
            if (result == null) {
                result = caseAbstractTextDescription(textOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(textOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(textOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(textOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(textOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(textOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION: {
            AbstractButtonDescription abstractButtonDescription = (AbstractButtonDescription) theEObject;
            T result = caseAbstractButtonDescription(abstractButtonDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractButtonDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractButtonDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractButtonDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractButtonDescription);
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
                result = caseAbstractButtonDescription(buttonDescription);
            }
            if (result == null) {
                result = caseControlDescription(buttonDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(buttonDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(buttonDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(buttonDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(buttonDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION: {
            ButtonOverrideDescription buttonOverrideDescription = (ButtonOverrideDescription) theEObject;
            T result = caseButtonOverrideDescription(buttonOverrideDescription);
            if (result == null) {
                result = caseAbstractButtonDescription(buttonOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(buttonOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(buttonOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(buttonOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(buttonOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(buttonOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION: {
            AbstractLabelDescription abstractLabelDescription = (AbstractLabelDescription) theEObject;
            T result = caseAbstractLabelDescription(abstractLabelDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractLabelDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractLabelDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractLabelDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractLabelDescription);
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
                result = caseAbstractLabelDescription(labelDescription);
            }
            if (result == null) {
                result = caseControlDescription(labelDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(labelDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(labelDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(labelDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(labelDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION: {
            LabelOverrideDescription labelOverrideDescription = (LabelOverrideDescription) theEObject;
            T result = caseLabelOverrideDescription(labelOverrideDescription);
            if (result == null) {
                result = caseAbstractLabelDescription(labelOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(labelOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(labelOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(labelOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(labelOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(labelOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION: {
            AbstractCheckboxDescription abstractCheckboxDescription = (AbstractCheckboxDescription) theEObject;
            T result = caseAbstractCheckboxDescription(abstractCheckboxDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractCheckboxDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractCheckboxDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractCheckboxDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractCheckboxDescription);
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
                result = caseAbstractCheckboxDescription(checkboxDescription);
            }
            if (result == null) {
                result = caseControlDescription(checkboxDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(checkboxDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(checkboxDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(checkboxDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(checkboxDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CHECKBOX_OVERRIDE_DESCRIPTION: {
            CheckboxOverrideDescription checkboxOverrideDescription = (CheckboxOverrideDescription) theEObject;
            T result = caseCheckboxOverrideDescription(checkboxOverrideDescription);
            if (result == null) {
                result = caseAbstractCheckboxDescription(checkboxOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(checkboxOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(checkboxOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(checkboxOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(checkboxOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(checkboxOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION: {
            AbstractSelectDescription abstractSelectDescription = (AbstractSelectDescription) theEObject;
            T result = caseAbstractSelectDescription(abstractSelectDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractSelectDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractSelectDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractSelectDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractSelectDescription);
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
                result = caseAbstractSelectDescription(selectDescription);
            }
            if (result == null) {
                result = caseControlDescription(selectDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(selectDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(selectDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(selectDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(selectDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.SELECT_OVERRIDE_DESCRIPTION: {
            SelectOverrideDescription selectOverrideDescription = (SelectOverrideDescription) theEObject;
            T result = caseSelectOverrideDescription(selectOverrideDescription);
            if (result == null) {
                result = caseAbstractSelectDescription(selectOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(selectOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(selectOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(selectOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(selectOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(selectOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION: {
            AbstractDynamicMappingForDescription abstractDynamicMappingForDescription = (AbstractDynamicMappingForDescription) theEObject;
            T result = caseAbstractDynamicMappingForDescription(abstractDynamicMappingForDescription);
            if (result == null) {
                result = caseAbstractControlDescription(abstractDynamicMappingForDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractDynamicMappingForDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractDynamicMappingForDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_DESCRIPTION: {
            DynamicMappingForDescription dynamicMappingForDescription = (DynamicMappingForDescription) theEObject;
            T result = caseDynamicMappingForDescription(dynamicMappingForDescription);
            if (result == null) {
                result = caseControlDescription(dynamicMappingForDescription);
            }
            if (result == null) {
                result = caseAbstractDynamicMappingForDescription(dynamicMappingForDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(dynamicMappingForDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(dynamicMappingForDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(dynamicMappingForDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION: {
            DynamicMappingForOverrideDescription dynamicMappingForOverrideDescription = (DynamicMappingForOverrideDescription) theEObject;
            T result = caseDynamicMappingForOverrideDescription(dynamicMappingForOverrideDescription);
            if (result == null) {
                result = caseAbstractDynamicMappingForDescription(dynamicMappingForOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(dynamicMappingForOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(dynamicMappingForOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(dynamicMappingForOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(dynamicMappingForOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION: {
            AbstractDynamicMappingIfDescription abstractDynamicMappingIfDescription = (AbstractDynamicMappingIfDescription) theEObject;
            T result = caseAbstractDynamicMappingIfDescription(abstractDynamicMappingIfDescription);
            if (result == null) {
                result = caseIdentifiedElement(abstractDynamicMappingIfDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_IF_DESCRIPTION: {
            DynamicMappingIfDescription dynamicMappingIfDescription = (DynamicMappingIfDescription) theEObject;
            T result = caseDynamicMappingIfDescription(dynamicMappingIfDescription);
            if (result == null) {
                result = caseAbstractDynamicMappingIfDescription(dynamicMappingIfDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(dynamicMappingIfDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION: {
            DynamicMappingIfOverrideDescription dynamicMappingIfOverrideDescription = (DynamicMappingIfOverrideDescription) theEObject;
            T result = caseDynamicMappingIfOverrideDescription(dynamicMappingIfOverrideDescription);
            if (result == null) {
                result = caseAbstractDynamicMappingIfDescription(dynamicMappingIfOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(dynamicMappingIfOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(dynamicMappingIfOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION: {
            AbstractTextAreaDescription abstractTextAreaDescription = (AbstractTextAreaDescription) theEObject;
            T result = caseAbstractTextAreaDescription(abstractTextAreaDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractTextAreaDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractTextAreaDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractTextAreaDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractTextAreaDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_AREA_DESCRIPTION: {
            TextAreaDescription textAreaDescription = (TextAreaDescription) theEObject;
            T result = caseTextAreaDescription(textAreaDescription);
            if (result == null) {
                result = caseAbstractTextAreaDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseWidgetDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseControlDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(textAreaDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(textAreaDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(textAreaDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION: {
            TextAreaOverrideDescription textAreaOverrideDescription = (TextAreaOverrideDescription) theEObject;
            T result = caseTextAreaOverrideDescription(textAreaOverrideDescription);
            if (result == null) {
                result = caseAbstractTextAreaDescription(textAreaOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(textAreaOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(textAreaOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(textAreaOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(textAreaOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(textAreaOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION: {
            AbstractRadioDescription abstractRadioDescription = (AbstractRadioDescription) theEObject;
            T result = caseAbstractRadioDescription(abstractRadioDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractRadioDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractRadioDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractRadioDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractRadioDescription);
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
                result = caseAbstractRadioDescription(radioDescription);
            }
            if (result == null) {
                result = caseControlDescription(radioDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(radioDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(radioDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(radioDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(radioDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION: {
            RadioOverrideDescription radioOverrideDescription = (RadioOverrideDescription) theEObject;
            T result = caseRadioOverrideDescription(radioOverrideDescription);
            if (result == null) {
                result = caseAbstractRadioDescription(radioOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(radioOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(radioOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(radioOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(radioOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(radioOverrideDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_LIST_DESCRIPTION: {
            AbstractListDescription abstractListDescription = (AbstractListDescription) theEObject;
            T result = caseAbstractListDescription(abstractListDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractListDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractListDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractListDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractListDescription);
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
                result = caseAbstractListDescription(listDescription);
            }
            if (result == null) {
                result = caseControlDescription(listDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(listDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(listDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(listDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(listDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION: {
            ListOverrideDescription listOverrideDescription = (ListOverrideDescription) theEObject;
            T result = caseListOverrideDescription(listOverrideDescription);
            if (result == null) {
                result = caseAbstractListDescription(listOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(listOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(listOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(listOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(listOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(listOverrideDescription);
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
        case PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION: {
            AbstractCustomDescription abstractCustomDescription = (AbstractCustomDescription) theEObject;
            T result = caseAbstractCustomDescription(abstractCustomDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractCustomDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractCustomDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractCustomDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractCustomDescription);
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
                result = caseAbstractCustomDescription(customDescription);
            }
            if (result == null) {
                result = caseControlDescription(customDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(customDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(customDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(customDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(customDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_OVERRIDE_DESCRIPTION: {
            CustomOverrideDescription customOverrideDescription = (CustomOverrideDescription) theEObject;
            T result = caseCustomOverrideDescription(customOverrideDescription);
            if (result == null) {
                result = caseAbstractCustomDescription(customOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(customOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(customOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(customOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(customOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(customOverrideDescription);
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
                result = caseIdentifiedElement(customExpression);
            }
            if (result == null) {
                result = caseDocumentedElement(customExpression);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.CUSTOM_OPERATION: {
            CustomOperation customOperation = (CustomOperation) theEObject;
            T result = caseCustomOperation(customOperation);
            if (result == null) {
                result = caseIdentifiedElement(customOperation);
            }
            if (result == null) {
                result = caseDocumentedElement(customOperation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION: {
            AbstractHyperlinkDescription abstractHyperlinkDescription = (AbstractHyperlinkDescription) theEObject;
            T result = caseAbstractHyperlinkDescription(abstractHyperlinkDescription);
            if (result == null) {
                result = caseAbstractWidgetDescription(abstractHyperlinkDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(abstractHyperlinkDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractHyperlinkDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(abstractHyperlinkDescription);
            }
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
                result = caseAbstractHyperlinkDescription(hyperlinkDescription);
            }
            if (result == null) {
                result = caseControlDescription(hyperlinkDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(hyperlinkDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(hyperlinkDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(hyperlinkDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(hyperlinkDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION: {
            HyperlinkOverrideDescription hyperlinkOverrideDescription = (HyperlinkOverrideDescription) theEObject;
            T result = caseHyperlinkOverrideDescription(hyperlinkOverrideDescription);
            if (result == null) {
                result = caseAbstractHyperlinkDescription(hyperlinkOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractOverrideDescription(hyperlinkOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractWidgetDescription(hyperlinkOverrideDescription);
            }
            if (result == null) {
                result = caseAbstractControlDescription(hyperlinkOverrideDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(hyperlinkOverrideDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(hyperlinkOverrideDescription);
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
        case PropertiesPackage.DIALOG_MODEL_OPERATION: {
            DialogModelOperation dialogModelOperation = (DialogModelOperation) theEObject;
            T result = caseDialogModelOperation(dialogModelOperation);
            if (result == null) {
                result = caseModelOperation(dialogModelOperation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.DIALOG_BUTTON: {
            DialogButton dialogButton = (DialogButton) theEObject;
            T result = caseDialogButton(dialogButton);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case PropertiesPackage.WIZARD_MODEL_OPERATION: {
            WizardModelOperation wizardModelOperation = (WizardModelOperation) theEObject;
            T result = caseWizardModelOperation(wizardModelOperation);
            if (result == null) {
                result = caseModelOperation(wizardModelOperation);
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
     * Returns the result of interpreting the object as an instance of '<em>View Extension Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>View Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseViewExtensionDescription(ViewExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Category</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Category</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCategory(Category object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractOverrideDescription(AbstractOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Page Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Page Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractPageDescription(AbstractPageDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Page Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Page Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePageDescription(PageDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Page Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Page Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePageOverrideDescription(PageOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Page Validation Set Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Page Validation Set Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePageValidationSetDescription(PageValidationSetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Property Validation Rule</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Property Validation Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePropertyValidationRule(PropertyValidationRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Group Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Group Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractGroupDescription(AbstractGroupDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Toolbar Action</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Toolbar Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolbarAction(ToolbarAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupDescription(GroupDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupOverrideDescription(GroupOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Validation Set Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Validation Set Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupValidationSetDescription(GroupValidationSetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Control Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Control Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractControlDescription(AbstractControlDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Control Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Control Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseControlDescription(ControlDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Container Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Container Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractContainerDescription(AbstractContainerDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerDescription(ContainerDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerOverrideDescription(ContainerOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Layout Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Layout Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLayoutDescription(LayoutDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fill Layout Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fill Layout Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFillLayoutDescription(FillLayoutDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Grid Layout Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Grid Layout Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGridLayoutDescription(GridLayoutDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Widget Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Widget Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractWidgetDescription(AbstractWidgetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetDescription(WidgetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Text Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Text Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractTextDescription(AbstractTextDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextDescription(TextDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextOverrideDescription(TextOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Button Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Button Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractButtonDescription(AbstractButtonDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Button Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Button Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonDescription(ButtonDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Button Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Button Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonOverrideDescription(ButtonOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Label Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Label Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractLabelDescription(AbstractLabelDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelDescription(LabelDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelOverrideDescription(LabelOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Checkbox Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Checkbox Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractCheckboxDescription(AbstractCheckboxDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Checkbox Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Checkbox Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxDescription(CheckboxDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Checkbox Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Checkbox Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxOverrideDescription(CheckboxOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Select Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Select Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractSelectDescription(AbstractSelectDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectDescription(SelectDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectOverrideDescription(SelectOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Dynamic Mapping For
     * Description</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Dynamic Mapping For
     *         Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractDynamicMappingForDescription(AbstractDynamicMappingForDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Mapping For Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Mapping For Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicMappingForDescription(DynamicMappingForDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Mapping For Override
     * Description</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Mapping For Override
     *         Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicMappingForOverrideDescription(DynamicMappingForOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Dynamic Mapping If
     * Description</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Dynamic Mapping If
     *         Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractDynamicMappingIfDescription(AbstractDynamicMappingIfDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Mapping If Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Mapping If Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicMappingIfDescription(DynamicMappingIfDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dynamic Mapping If Override
     * Description</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dynamic Mapping If Override
     *         Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDynamicMappingIfOverrideDescription(DynamicMappingIfOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Text Area Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Text Area Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractTextAreaDescription(AbstractTextAreaDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Area Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Area Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextAreaDescription(TextAreaDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Area Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Area Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextAreaOverrideDescription(TextAreaOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Radio Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Radio Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractRadioDescription(AbstractRadioDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Radio Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Radio Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioDescription(RadioDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Radio Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Radio Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioOverrideDescription(RadioOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract List Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract List Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractListDescription(AbstractListDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>List Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>List Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListDescription(ListDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>List Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>List Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListOverrideDescription(ListOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperationDescription(OperationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Custom Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Custom Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractCustomDescription(AbstractCustomDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomDescription(CustomDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomOverrideDescription(CustomOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Expression</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Expression</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomExpression(CustomExpression object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Operation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomOperation(CustomOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Hyperlink Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Hyperlink Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractHyperlinkDescription(AbstractHyperlinkDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hyperlink Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hyperlink Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkDescription(HyperlinkDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hyperlink Override Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hyperlink Override Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkOverrideDescription(HyperlinkOverrideDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetStyle(WidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Widget Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextWidgetStyle(TextWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelWidgetStyle(LabelWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Checkbox Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Checkbox Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxWidgetStyle(CheckboxWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Radio Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Radio Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioWidgetStyle(RadioWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Button Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Button Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonWidgetStyle(ButtonWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectWidgetStyle(SelectWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomWidgetStyle(CustomWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>List Widget Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>List Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListWidgetStyle(ListWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hyperlink Widget Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hyperlink Widget Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkWidgetStyle(HyperlinkWidgetStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupStyle(GroupStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetConditionalStyle(WidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Text Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTextWidgetConditionalStyle(TextWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelWidgetConditionalStyle(LabelWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Checkbox Widget Conditional Style</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Checkbox Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCheckboxWidgetConditionalStyle(CheckboxWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Radio Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Radio Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRadioWidgetConditionalStyle(RadioWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Button Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Button Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseButtonWidgetConditionalStyle(ButtonWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Select Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Select Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectWidgetConditionalStyle(SelectWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomWidgetConditionalStyle(CustomWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>List Widget Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>List Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseListWidgetConditionalStyle(ListWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Widget Action</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Widget Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWidgetAction(WidgetAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hyperlink Widget Conditional Style</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hyperlink Widget Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHyperlinkWidgetConditionalStyle(HyperlinkWidgetConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Group Conditional Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group Conditional Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroupConditionalStyle(GroupConditionalStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dialog Model Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dialog Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDialogModelOperation(DialogModelOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dialog Button</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dialog Button</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDialogButton(DialogButton object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Wizard Model Operation</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Wizard Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWizardModelOperation(WizardModelOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edit Support</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edit Support</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEditSupport(EditSupport object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Extension</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExtension(Extension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Rule</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Rule</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseValidationRule(ValidationRule object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Operation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Operation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelOperation(ModelOperation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // PropertiesSwitch
