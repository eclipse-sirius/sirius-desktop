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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.CheckboxDescription;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingIf;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.MultipleReferencesDescription;
import org.eclipse.sirius.properties.OperationDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.SelectDescription;
import org.eclipse.sirius.properties.SingleReferenceDescription;
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class PropertiesPackageImpl extends EPackageImpl implements PropertiesPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass viewExtensionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pageDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass groupDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass widgetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass textDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass buttonDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass labelDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass checkboxDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass selectDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dynamicMappingForEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dynamicMappingIfEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass textAreaDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass radioDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass singleReferenceDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass multipleReferencesDescriptionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.properties.PropertiesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PropertiesPackageImpl() {
        super(PropertiesPackage.eNS_URI, PropertiesFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link PropertiesPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PropertiesPackage init() {
        if (PropertiesPackageImpl.isInited) {
            return (PropertiesPackage) EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);
        }

        // Obtain or create and register package
        PropertiesPackageImpl thePropertiesPackage = (PropertiesPackageImpl) (EPackage.Registry.INSTANCE.get(PropertiesPackage.eNS_URI) instanceof PropertiesPackageImpl ? EPackage.Registry.INSTANCE
                .get(PropertiesPackage.eNS_URI) : new PropertiesPackageImpl());

        PropertiesPackageImpl.isInited = true;

        // Initialize simple dependencies
        ViewpointPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thePropertiesPackage.createPackageContents();

        // Initialize created meta-data
        thePropertiesPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePropertiesPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(PropertiesPackage.eNS_URI, thePropertiesPackage);
        return thePropertiesPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getViewExtensionDescription() {
        return viewExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getViewExtensionDescription_Identifier() {
        return (EAttribute) viewExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Metamodels() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Pages() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_DefaultPage() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getViewExtensionDescription_LabelExpression() {
        return (EAttribute) viewExtensionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getViewExtensionDescription_Groups() {
        return (EReference) viewExtensionDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getPageDescription() {
        return pageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPageDescription_Identifier() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPageDescription_LabelExpression() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPageDescription_DomainClass() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getPageDescription_SemanticCandidateExpression() {
        return (EAttribute) pageDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getPageDescription_Groups() {
        return (EReference) pageDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getGroupDescription() {
        return groupDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_Identifier() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_LabelExpression() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_DomainClass() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getGroupDescription_SemanticCandidateExpression() {
        return (EAttribute) groupDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getGroupDescription_Container() {
        return (EReference) groupDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getContainerDescription() {
        return containerDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getContainerDescription_Identifier() {
        return (EAttribute) containerDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getContainerDescription_Widgets() {
        return (EReference) containerDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getContainerDescription_DynamicMappings() {
        return (EReference) containerDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getWidgetDescription() {
        return widgetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getWidgetDescription_Identifier() {
        return (EAttribute) widgetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getWidgetDescription_LabelExpression() {
        return (EAttribute) widgetDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getWidgetDescription_HelpExpression() {
        return (EAttribute) widgetDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTextDescription() {
        return textDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTextDescription_ValueExpression() {
        return (EAttribute) textDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTextDescription_InitialOperation() {
        return (EReference) textDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getButtonDescription() {
        return buttonDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getButtonDescription_ButtonLabelExpression() {
        return (EAttribute) buttonDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getButtonDescription_InitialOperation() {
        return (EReference) buttonDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLabelDescription() {
        return labelDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLabelDescription_BodyExpression() {
        return (EAttribute) labelDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCheckboxDescription() {
        return checkboxDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCheckboxDescription_ValueExpression() {
        return (EAttribute) checkboxDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCheckboxDescription_InitialOperation() {
        return (EReference) checkboxDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSelectDescription() {
        return selectDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSelectDescription_ValueExpression() {
        return (EAttribute) selectDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSelectDescription_InitialOperation() {
        return (EReference) selectDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSelectDescription_CandidatesExpression() {
        return (EAttribute) selectDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSelectDescription_CandidateDisplayExpression() {
        return (EAttribute) selectDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDynamicMappingFor() {
        return dynamicMappingForEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingFor_Iterator() {
        return (EAttribute) dynamicMappingForEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingFor_DomainClassExpression() {
        return (EAttribute) dynamicMappingForEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDynamicMappingFor_Ifs() {
        return (EReference) dynamicMappingForEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDynamicMappingIf() {
        return dynamicMappingIfEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDynamicMappingIf_PredicateExpression() {
        return (EAttribute) dynamicMappingIfEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDynamicMappingIf_Widget() {
        return (EReference) dynamicMappingIfEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTextAreaDescription() {
        return textAreaDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTextAreaDescription_LineCount() {
        return (EAttribute) textAreaDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRadioDescription() {
        return radioDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_ValueExpression() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRadioDescription_InitialOperation() {
        return (EReference) radioDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_CandidatesExpression() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRadioDescription_CandidateDisplayExpression() {
        return (EAttribute) radioDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSingleReferenceDescription() {
        return singleReferenceDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSingleReferenceDescription_ValueExpression() {
        return (EAttribute) singleReferenceDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSingleReferenceDescription_DisplayExpression() {
        return (EAttribute) singleReferenceDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSingleReferenceDescription_CreateOperation() {
        return (EReference) singleReferenceDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSingleReferenceDescription_SearchOperation() {
        return (EReference) singleReferenceDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSingleReferenceDescription_UnsetOperation() {
        return (EReference) singleReferenceDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSingleReferenceDescription_OnClickOperation() {
        return (EReference) singleReferenceDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOperationDescription() {
        return operationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getOperationDescription_InitialOperation() {
        return (EReference) operationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMultipleReferencesDescription() {
        return multipleReferencesDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMultipleReferencesDescription_ValueExpression() {
        return (EAttribute) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMultipleReferencesDescription_DisplayExpression() {
        return (EAttribute) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMultipleReferencesDescription_CreateOperation() {
        return (EReference) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMultipleReferencesDescription_SearchOperation() {
        return (EReference) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMultipleReferencesDescription_UnsetOperation() {
        return (EReference) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMultipleReferencesDescription_OnClickOperation() {
        return (EReference) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMultipleReferencesDescription_UpOperation() {
        return (EReference) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getMultipleReferencesDescription_DownOperation() {
        return (EReference) multipleReferencesDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public PropertiesFactory getPropertiesFactory() {
        return (PropertiesFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        viewExtensionDescriptionEClass = createEClass(PropertiesPackage.VIEW_EXTENSION_DESCRIPTION);
        createEAttribute(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__IDENTIFIER);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__PAGES);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE);
        createEAttribute(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION);
        createEReference(viewExtensionDescriptionEClass, PropertiesPackage.VIEW_EXTENSION_DESCRIPTION__GROUPS);

        pageDescriptionEClass = createEClass(PropertiesPackage.PAGE_DESCRIPTION);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__IDENTIFIER);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEReference(pageDescriptionEClass, PropertiesPackage.PAGE_DESCRIPTION__GROUPS);

        groupDescriptionEClass = createEClass(PropertiesPackage.GROUP_DESCRIPTION);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__IDENTIFIER);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEReference(groupDescriptionEClass, PropertiesPackage.GROUP_DESCRIPTION__CONTAINER);

        containerDescriptionEClass = createEClass(PropertiesPackage.CONTAINER_DESCRIPTION);
        createEAttribute(containerDescriptionEClass, PropertiesPackage.CONTAINER_DESCRIPTION__IDENTIFIER);
        createEReference(containerDescriptionEClass, PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS);
        createEReference(containerDescriptionEClass, PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS);

        widgetDescriptionEClass = createEClass(PropertiesPackage.WIDGET_DESCRIPTION);
        createEAttribute(widgetDescriptionEClass, PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER);
        createEAttribute(widgetDescriptionEClass, PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(widgetDescriptionEClass, PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION);

        textDescriptionEClass = createEClass(PropertiesPackage.TEXT_DESCRIPTION);
        createEAttribute(textDescriptionEClass, PropertiesPackage.TEXT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(textDescriptionEClass, PropertiesPackage.TEXT_DESCRIPTION__INITIAL_OPERATION);

        buttonDescriptionEClass = createEClass(PropertiesPackage.BUTTON_DESCRIPTION);
        createEAttribute(buttonDescriptionEClass, PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION);
        createEReference(buttonDescriptionEClass, PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION);

        labelDescriptionEClass = createEClass(PropertiesPackage.LABEL_DESCRIPTION);
        createEAttribute(labelDescriptionEClass, PropertiesPackage.LABEL_DESCRIPTION__BODY_EXPRESSION);

        checkboxDescriptionEClass = createEClass(PropertiesPackage.CHECKBOX_DESCRIPTION);
        createEAttribute(checkboxDescriptionEClass, PropertiesPackage.CHECKBOX_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(checkboxDescriptionEClass, PropertiesPackage.CHECKBOX_DESCRIPTION__INITIAL_OPERATION);

        selectDescriptionEClass = createEClass(PropertiesPackage.SELECT_DESCRIPTION);
        createEAttribute(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(selectDescriptionEClass, PropertiesPackage.SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION);

        dynamicMappingForEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_FOR);
        createEAttribute(dynamicMappingForEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR__ITERATOR);
        createEAttribute(dynamicMappingForEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION);
        createEReference(dynamicMappingForEClass, PropertiesPackage.DYNAMIC_MAPPING_FOR__IFS);

        dynamicMappingIfEClass = createEClass(PropertiesPackage.DYNAMIC_MAPPING_IF);
        createEAttribute(dynamicMappingIfEClass, PropertiesPackage.DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION);
        createEReference(dynamicMappingIfEClass, PropertiesPackage.DYNAMIC_MAPPING_IF__WIDGET);

        textAreaDescriptionEClass = createEClass(PropertiesPackage.TEXT_AREA_DESCRIPTION);
        createEAttribute(textAreaDescriptionEClass, PropertiesPackage.TEXT_AREA_DESCRIPTION__LINE_COUNT);

        radioDescriptionEClass = createEClass(PropertiesPackage.RADIO_DESCRIPTION);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__INITIAL_OPERATION);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__CANDIDATES_EXPRESSION);
        createEAttribute(radioDescriptionEClass, PropertiesPackage.RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION);

        singleReferenceDescriptionEClass = createEClass(PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION);
        createEAttribute(singleReferenceDescriptionEClass, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(singleReferenceDescriptionEClass, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(singleReferenceDescriptionEClass, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION);
        createEReference(singleReferenceDescriptionEClass, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION);
        createEReference(singleReferenceDescriptionEClass, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION);
        createEReference(singleReferenceDescriptionEClass, PropertiesPackage.SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION);

        operationDescriptionEClass = createEClass(PropertiesPackage.OPERATION_DESCRIPTION);
        createEReference(operationDescriptionEClass, PropertiesPackage.OPERATION_DESCRIPTION__INITIAL_OPERATION);

        multipleReferencesDescriptionEClass = createEClass(PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION);
        createEAttribute(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION);
        createEAttribute(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION);
        createEReference(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION);
        createEReference(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION);
        createEReference(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION);
        createEReference(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION);
        createEReference(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION);
        createEReference(multipleReferencesDescriptionEClass, PropertiesPackage.MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(PropertiesPackage.eNAME);
        setNsPrefix(PropertiesPackage.eNS_PREFIX);
        setNsURI(PropertiesPackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        viewExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getExtension());
        textDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        buttonDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        labelDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        checkboxDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        selectDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        textAreaDescriptionEClass.getESuperTypes().add(this.getTextDescription());
        radioDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        singleReferenceDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        multipleReferencesDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(viewExtensionDescriptionEClass, ViewExtensionDescription.class, "ViewExtensionDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE,
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getViewExtensionDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Metamodels(), theEcorePackage.getEPackage(), null, "metamodels", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Pages(), this.getPageDescription(), null, "pages", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_DefaultPage(), this.getPageDescription(), null, "defaultPage", null, 0, 1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getViewExtensionDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, ViewExtensionDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getViewExtensionDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, ViewExtensionDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(pageDescriptionEClass, PageDescription.class, "PageDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPageDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPageDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, PageDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getPageDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, PageDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(groupDescriptionEClass, GroupDescription.class, "GroupDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGroupDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGroupDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, GroupDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGroupDescription_Container(), this.getContainerDescription(), null, "container", null, 0, 1, GroupDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(containerDescriptionEClass, ContainerDescription.class, "ContainerDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainerDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, ContainerDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getContainerDescription_Widgets(), this.getWidgetDescription(), null, "widgets", null, 0, -1, ContainerDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getContainerDescription_DynamicMappings(), this.getDynamicMappingFor(), null, "dynamicMappings", null, 0, -1, ContainerDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(widgetDescriptionEClass, WidgetDescription.class, "WidgetDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWidgetDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, WidgetDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getWidgetDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, WidgetDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getWidgetDescription_HelpExpression(), theDescriptionPackage.getInterpretedExpression(), "helpExpression", null, 0, 1, WidgetDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(textDescriptionEClass, TextDescription.class, "TextDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTextDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, TextDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTextDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, TextDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(buttonDescriptionEClass, ButtonDescription.class, "ButtonDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getButtonDescription_ButtonLabelExpression(), theDescriptionPackage.getInterpretedExpression(), "buttonLabelExpression", null, 0, 1, ButtonDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getButtonDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, ButtonDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(labelDescriptionEClass, LabelDescription.class, "LabelDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelDescription_BodyExpression(), theDescriptionPackage.getInterpretedExpression(), "bodyExpression", null, 0, 1, LabelDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(checkboxDescriptionEClass, CheckboxDescription.class, "CheckboxDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCheckboxDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, CheckboxDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getCheckboxDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, CheckboxDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(selectDescriptionEClass, SelectDescription.class, "SelectDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSelectDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, SelectDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getSelectDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, SelectDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 0, 1, SelectDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getSelectDescription_CandidateDisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "candidateDisplayExpression", null, 0, 1, SelectDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dynamicMappingForEClass, DynamicMappingFor.class, "DynamicMappingFor", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDynamicMappingFor_Iterator(), theEcorePackage.getEString(), "iterator", null, 1, 1, DynamicMappingFor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDynamicMappingFor_DomainClassExpression(), theEcorePackage.getEString(), "domainClassExpression", null, 1, 1, DynamicMappingFor.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDynamicMappingFor_Ifs(), this.getDynamicMappingIf(), null, "ifs", null, 1, -1, DynamicMappingFor.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dynamicMappingIfEClass, DynamicMappingIf.class, "DynamicMappingIf", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDynamicMappingIf_PredicateExpression(), theEcorePackage.getEString(), "predicateExpression", null, 1, 1, DynamicMappingIf.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDynamicMappingIf_Widget(), this.getWidgetDescription(), null, "widget", null, 1, 1, DynamicMappingIf.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(textAreaDescriptionEClass, TextAreaDescription.class, "TextAreaDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTextAreaDescription_LineCount(), ecorePackage.getEInt(), "lineCount", "5", 0, 1, TextAreaDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(radioDescriptionEClass, RadioDescription.class, "RadioDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRadioDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getRadioDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, RadioDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getRadioDescription_CandidatesExpression(), theDescriptionPackage.getInterpretedExpression(), "candidatesExpression", null, 0, 1, RadioDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getRadioDescription_CandidateDisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "candidateDisplayExpression", null, 0, 1, RadioDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(singleReferenceDescriptionEClass, SingleReferenceDescription.class, "SingleReferenceDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE,
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSingleReferenceDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, SingleReferenceDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getSingleReferenceDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, SingleReferenceDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getSingleReferenceDescription_CreateOperation(), this.getOperationDescription(), null, "createOperation", null, 0, 1, SingleReferenceDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getSingleReferenceDescription_SearchOperation(), this.getOperationDescription(), null, "searchOperation", null, 0, 1, SingleReferenceDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getSingleReferenceDescription_UnsetOperation(), this.getOperationDescription(), null, "unsetOperation", null, 0, 1, SingleReferenceDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getSingleReferenceDescription_OnClickOperation(), this.getOperationDescription(), null, "onClickOperation", null, 0, 1, SingleReferenceDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(operationDescriptionEClass, OperationDescription.class, "OperationDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getOperationDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, OperationDescription.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(multipleReferencesDescriptionEClass, MultipleReferencesDescription.class, "MultipleReferencesDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE,
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getMultipleReferencesDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getMultipleReferencesDescription_DisplayExpression(), theDescriptionPackage.getInterpretedExpression(), "displayExpression", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getMultipleReferencesDescription_CreateOperation(), this.getOperationDescription(), null, "createOperation", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getMultipleReferencesDescription_SearchOperation(), this.getOperationDescription(), null, "searchOperation", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getMultipleReferencesDescription_UnsetOperation(), this.getOperationDescription(), null, "unsetOperation", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getMultipleReferencesDescription_OnClickOperation(), this.getOperationDescription(), null, "onClickOperation", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getMultipleReferencesDescription_UpOperation(), this.getOperationDescription(), null, "upOperation", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getMultipleReferencesDescription_DownOperation(), this.getOperationDescription(), null, "downOperation", null, 0, 1, MultipleReferencesDescription.class,
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        // Create resource
        createResource(PropertiesPackage.eNS_URI);
    }

} // PropertiesPackageImpl
