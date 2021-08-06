/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.AnnotationEntry;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.Component;
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.DAnnotationEntry;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.DecorationDescriptionsSet;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EAttributeCustomization;
import org.eclipse.sirius.viewpoint.description.EReferenceCustomization;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.Environment;
import org.eclipse.sirius.viewpoint.description.Extension;
import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.sirius.viewpoint.description.GenericDecorationDescription;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;
import org.eclipse.sirius.viewpoint.description.InterpolatedColor;
import org.eclipse.sirius.viewpoint.description.JavaExtension;
import org.eclipse.sirius.viewpoint.description.MetamodelExtensionSetting;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationImportDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationTemplate;
import org.eclipse.sirius.viewpoint.description.SelectionDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.SubVariable;
import org.eclipse.sirius.viewpoint.description.SystemColor;
import org.eclipse.sirius.viewpoint.description.SytemColorsPalette;
import org.eclipse.sirius.viewpoint.description.TypedVariable;
import org.eclipse.sirius.viewpoint.description.UserColor;
import org.eclipse.sirius.viewpoint.description.UserColorsPalette;
import org.eclipse.sirius.viewpoint.description.UserFixedColor;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage
 * @generated
 */
public class DescriptionSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionSwitch() {
        if (DescriptionSwitch.modelPackage == null) {
            DescriptionSwitch.modelPackage = DescriptionPackage.eINSTANCE;
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
        if (theEClass.eContainer() == DescriptionSwitch.modelPackage) {
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
        case DescriptionPackage.GROUP: {
            Group group = (Group) theEObject;
            T result = caseGroup(group);
            if (result == null) {
                result = caseDModelElement(group);
            }
            if (result == null) {
                result = caseDocumentedElement(group);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EXTENSION: {
            Extension extension = (Extension) theEObject;
            T result = caseExtension(extension);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COMPONENT: {
            Component component = (Component) theEObject;
            T result = caseComponent(component);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.VIEWPOINT: {
            Viewpoint viewpoint = (Viewpoint) theEObject;
            T result = caseViewpoint(viewpoint);
            if (result == null) {
                result = caseDocumentedElement(viewpoint);
            }
            if (result == null) {
                result = caseComponent(viewpoint);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(viewpoint);
            }
            if (result == null) {
                result = caseIdentifiedElement(viewpoint);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.FEATURE_EXTENSION_DESCRIPTION: {
            FeatureExtensionDescription featureExtensionDescription = (FeatureExtensionDescription) theEObject;
            T result = caseFeatureExtensionDescription(featureExtensionDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.REPRESENTATION_DESCRIPTION: {
            RepresentationDescription representationDescription = (RepresentationDescription) theEObject;
            T result = caseRepresentationDescription(representationDescription);
            if (result == null) {
                result = caseDocumentedElement(representationDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(representationDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(representationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.REPRESENTATION_TEMPLATE: {
            RepresentationTemplate representationTemplate = (RepresentationTemplate) theEObject;
            T result = caseRepresentationTemplate(representationTemplate);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION: {
            RepresentationImportDescription representationImportDescription = (RepresentationImportDescription) theEObject;
            T result = caseRepresentationImportDescription(representationImportDescription);
            if (result == null) {
                result = caseRepresentationDescription(representationImportDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(representationImportDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(representationImportDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(representationImportDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION: {
            RepresentationExtensionDescription representationExtensionDescription = (RepresentationExtensionDescription) theEObject;
            T result = caseRepresentationExtensionDescription(representationExtensionDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.METAMODEL_EXTENSION_SETTING: {
            MetamodelExtensionSetting metamodelExtensionSetting = (MetamodelExtensionSetting) theEObject;
            T result = caseMetamodelExtensionSetting(metamodelExtensionSetting);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.JAVA_EXTENSION: {
            JavaExtension javaExtension = (JavaExtension) theEObject;
            T result = caseJavaExtension(javaExtension);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING: {
            RepresentationElementMapping representationElementMapping = (RepresentationElementMapping) theEObject;
            T result = caseRepresentationElementMapping(representationElementMapping);
            if (result == null) {
                result = caseIdentifiedElement(representationElementMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ABSTRACT_MAPPING_IMPORT: {
            AbstractMappingImport abstractMappingImport = (AbstractMappingImport) theEObject;
            T result = caseAbstractMappingImport(abstractMappingImport);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DOCUMENTED_ELEMENT: {
            DocumentedElement documentedElement = (DocumentedElement) theEObject;
            T result = caseDocumentedElement(documentedElement);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DMODEL_ELEMENT: {
            DModelElement dModelElement = (DModelElement) theEObject;
            T result = caseDModelElement(dModelElement);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DANNOTATION: {
            DAnnotation dAnnotation = (DAnnotation) theEObject;
            T result = caseDAnnotation(dAnnotation);
            if (result == null) {
                result = caseViewpoint_IdentifiedElement(dAnnotation);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION: {
            ConditionalStyleDescription conditionalStyleDescription = (ConditionalStyleDescription) theEObject;
            T result = caseConditionalStyleDescription(conditionalStyleDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.PASTE_TARGET_DESCRIPTION: {
            PasteTargetDescription pasteTargetDescription = (PasteTargetDescription) theEObject;
            T result = casePasteTargetDescription(pasteTargetDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DECORATION_DESCRIPTIONS_SET: {
            DecorationDescriptionsSet decorationDescriptionsSet = (DecorationDescriptionsSet) theEObject;
            T result = caseDecorationDescriptionsSet(decorationDescriptionsSet);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DECORATION_DESCRIPTION: {
            DecorationDescription decorationDescription = (DecorationDescription) theEObject;
            T result = caseDecorationDescription(decorationDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.SEMANTIC_BASED_DECORATION: {
            SemanticBasedDecoration semanticBasedDecoration = (SemanticBasedDecoration) theEObject;
            T result = caseSemanticBasedDecoration(semanticBasedDecoration);
            if (result == null) {
                result = caseDecorationDescription(semanticBasedDecoration);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.GENERIC_DECORATION_DESCRIPTION: {
            GenericDecorationDescription genericDecorationDescription = (GenericDecorationDescription) theEObject;
            T result = caseGenericDecorationDescription(genericDecorationDescription);
            if (result == null) {
                result = caseDecorationDescription(genericDecorationDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CUSTOMIZATION: {
            Customization customization = (Customization) theEObject;
            T result = caseCustomization(customization);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.IVSM_ELEMENT_CUSTOMIZATION: {
            IVSMElementCustomization ivsmElementCustomization = (IVSMElementCustomization) theEObject;
            T result = caseIVSMElementCustomization(ivsmElementCustomization);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION: {
            VSMElementCustomization vsmElementCustomization = (VSMElementCustomization) theEObject;
            T result = caseVSMElementCustomization(vsmElementCustomization);
            if (result == null) {
                result = caseIVSMElementCustomization(vsmElementCustomization);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE: {
            VSMElementCustomizationReuse vsmElementCustomizationReuse = (VSMElementCustomizationReuse) theEObject;
            T result = caseVSMElementCustomizationReuse(vsmElementCustomizationReuse);
            if (result == null) {
                result = caseIVSMElementCustomization(vsmElementCustomizationReuse);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION: {
            EStructuralFeatureCustomization eStructuralFeatureCustomization = (EStructuralFeatureCustomization) theEObject;
            T result = caseEStructuralFeatureCustomization(eStructuralFeatureCustomization);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EATTRIBUTE_CUSTOMIZATION: {
            EAttributeCustomization eAttributeCustomization = (EAttributeCustomization) theEObject;
            T result = caseEAttributeCustomization(eAttributeCustomization);
            if (result == null) {
                result = caseEStructuralFeatureCustomization(eAttributeCustomization);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EREFERENCE_CUSTOMIZATION: {
            EReferenceCustomization eReferenceCustomization = (EReferenceCustomization) theEObject;
            T result = caseEReferenceCustomization(eReferenceCustomization);
            if (result == null) {
                result = caseEStructuralFeatureCustomization(eReferenceCustomization);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.SELECTION_DESCRIPTION: {
            SelectionDescription selectionDescription = (SelectionDescription) theEObject;
            T result = caseSelectionDescription(selectionDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COLOR_DESCRIPTION: {
            ColorDescription colorDescription = (ColorDescription) theEObject;
            T result = caseColorDescription(colorDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.SYSTEM_COLOR: {
            SystemColor systemColor = (SystemColor) theEObject;
            T result = caseSystemColor(systemColor);
            if (result == null) {
                result = caseFixedColor(systemColor);
            }
            if (result == null) {
                result = caseColorDescription(systemColor);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.INTERPOLATED_COLOR: {
            InterpolatedColor interpolatedColor = (InterpolatedColor) theEObject;
            T result = caseInterpolatedColor(interpolatedColor);
            if (result == null) {
                result = caseColorDescription(interpolatedColor);
            }
            if (result == null) {
                result = caseUserColor(interpolatedColor);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COLOR_STEP: {
            ColorStep colorStep = (ColorStep) theEObject;
            T result = caseColorStep(colorStep);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.FIXED_COLOR: {
            FixedColor fixedColor = (FixedColor) theEObject;
            T result = caseFixedColor(fixedColor);
            if (result == null) {
                result = caseColorDescription(fixedColor);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.USER_FIXED_COLOR: {
            UserFixedColor userFixedColor = (UserFixedColor) theEObject;
            T result = caseUserFixedColor(userFixedColor);
            if (result == null) {
                result = caseFixedColor(userFixedColor);
            }
            if (result == null) {
                result = caseUserColor(userFixedColor);
            }
            if (result == null) {
                result = caseColorDescription(userFixedColor);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.USER_COLOR: {
            UserColor userColor = (UserColor) theEObject;
            T result = caseUserColor(userColor);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ENVIRONMENT: {
            Environment environment = (Environment) theEObject;
            T result = caseEnvironment(environment);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.SYTEM_COLORS_PALETTE: {
            SytemColorsPalette sytemColorsPalette = (SytemColorsPalette) theEObject;
            T result = caseSytemColorsPalette(sytemColorsPalette);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.USER_COLORS_PALETTE: {
            UserColorsPalette userColorsPalette = (UserColorsPalette) theEObject;
            T result = caseUserColorsPalette(userColorsPalette);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ANNOTATION_ENTRY: {
            AnnotationEntry annotationEntry = (AnnotationEntry) theEObject;
            T result = caseAnnotationEntry(annotationEntry);
            if (result == null) {
                result = caseViewpoint_IdentifiedElement(annotationEntry);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT: {
            EndUserDocumentedElement endUserDocumentedElement = (EndUserDocumentedElement) theEObject;
            T result = caseEndUserDocumentedElement(endUserDocumentedElement);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.IDENTIFIED_ELEMENT: {
            IdentifiedElement identifiedElement = (IdentifiedElement) theEObject;
            T result = caseIdentifiedElement(identifiedElement);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COMPUTED_COLOR: {
            ComputedColor computedColor = (ComputedColor) theEObject;
            T result = caseComputedColor(computedColor);
            if (result == null) {
                result = caseUserColor(computedColor);
            }
            if (result == null) {
                result = caseColorDescription(computedColor);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DANNOTATION_ENTRY: {
            DAnnotationEntry dAnnotationEntry = (DAnnotationEntry) theEObject;
            T result = caseDAnnotationEntry(dAnnotationEntry);
            if (result == null) {
                result = caseViewpoint_IdentifiedElement(dAnnotationEntry);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.ABSTRACT_VARIABLE: {
            AbstractVariable abstractVariable = (AbstractVariable) theEObject;
            T result = caseAbstractVariable(abstractVariable);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.SUB_VARIABLE: {
            SubVariable subVariable = (SubVariable) theEObject;
            T result = caseSubVariable(subVariable);
            if (result == null) {
                result = caseAbstractVariable(subVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.INTERACTIVE_VARIABLE_DESCRIPTION: {
            InteractiveVariableDescription interactiveVariableDescription = (InteractiveVariableDescription) theEObject;
            T result = caseInteractiveVariableDescription(interactiveVariableDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.TYPED_VARIABLE: {
            TypedVariable typedVariable = (TypedVariable) theEObject;
            T result = caseTypedVariable(typedVariable);
            if (result == null) {
                result = caseInteractiveVariableDescription(typedVariable);
            }
            if (result == null) {
                result = caseSubVariable(typedVariable);
            }
            if (result == null) {
                result = caseAbstractVariable(typedVariable);
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
     * Returns the result of interpreting the object as an instance of '<em>Group</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGroup(Group object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Component</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComponent(Component object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Viewpoint</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Viewpoint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseViewpoint(Viewpoint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Feature Extension Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Feature Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFeatureExtensionDescription(FeatureExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationDescription(RepresentationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Template</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationTemplate(RepresentationTemplate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Import Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Import Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationImportDescription(RepresentationImportDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Extension Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Extension Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationExtensionDescription(RepresentationExtensionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Metamodel Extension Setting</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Metamodel Extension Setting</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetamodelExtensionSetting(MetamodelExtensionSetting object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Java Extension</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Java Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseJavaExtension(JavaExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Element Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Element Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationElementMapping(RepresentationElementMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Mapping Import</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Mapping Import</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractMappingImport(AbstractMappingImport object) {
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
     * Returns the result of interpreting the object as an instance of '<em>DModel Element</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModelElement(DModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DAnnotation</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DAnnotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnnotation(DAnnotation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Conditional Style Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Conditional Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditionalStyleDescription(ConditionalStyleDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Paste Target Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Paste Target Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePasteTargetDescription(PasteTargetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Decoration Descriptions Set</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Decoration Descriptions Set</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDecorationDescriptionsSet(DecorationDescriptionsSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Decoration Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Decoration Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDecorationDescription(DecorationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Semantic Based Decoration</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Semantic Based Decoration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSemanticBasedDecoration(SemanticBasedDecoration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Generic Decoration Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Generic Decoration Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGenericDecorationDescription(GenericDecorationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Customization</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomization(Customization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IVSM Element Customization</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IVSM Element Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIVSMElementCustomization(IVSMElementCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>VSM Element Customization</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>VSM Element Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVSMElementCustomization(VSMElementCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>VSM Element Customization Reuse</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>VSM Element Customization Reuse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVSMElementCustomizationReuse(VSMElementCustomizationReuse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EStructural Feature Customization</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EStructural Feature Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEStructuralFeatureCustomization(EStructuralFeatureCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EAttribute Customization</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EAttribute Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEAttributeCustomization(EAttributeCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EReference Customization</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EReference Customization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEReferenceCustomization(EReferenceCustomization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Selection Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Selection Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSelectionDescription(SelectionDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Color Description</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Color Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColorDescription(ColorDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>System Color</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>System Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSystemColor(SystemColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interpolated Color</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interpolated Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInterpolatedColor(InterpolatedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Color Step</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Color Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseColorStep(ColorStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fixed Color</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fixed Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFixedColor(FixedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Fixed Color</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Fixed Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserFixedColor(UserFixedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Color</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserColor(UserColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Environment</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Environment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnvironment(Environment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sytem Colors Palette</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sytem Colors Palette</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSytemColorsPalette(SytemColorsPalette object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Colors Palette</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Colors Palette</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserColorsPalette(UserColorsPalette object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Annotation Entry</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Annotation Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAnnotationEntry(AnnotationEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End User Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End User Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndUserDocumentedElement(EndUserDocumentedElement object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Computed Color</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Computed Color</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComputedColor(ComputedColor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DAnnotation Entry</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DAnnotation Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnnotationEntry(DAnnotationEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractVariable(AbstractVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Sub Variable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sub Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSubVariable(SubVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interactive Variable Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interactive Variable Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractiveVariableDescription(InteractiveVariableDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Typed Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Typed Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTypedVariable(TypedVariable object) {
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
    public T caseViewpoint_IdentifiedElement(org.eclipse.sirius.viewpoint.IdentifiedElement object) {
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

} // DescriptionSwitch
