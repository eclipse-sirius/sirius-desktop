/**
 * Copyright (c) 2015 Obeo.
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

import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.DynamicMappingCase;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.DynamicMappingSwitch;
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PropertiesFactory;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.ViewExtensionDescription;
import org.eclipse.sirius.properties.WidgetDescription;

import org.eclipse.sirius.viewpoint.ViewpointPackage;

import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PropertiesPackageImpl extends EPackageImpl implements PropertiesPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass viewExtensionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass pageDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass groupDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass containerDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass widgetDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass textDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass labelDescriptionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicMappingForEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicMappingSwitchEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass dynamicMappingCaseEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.properties.PropertiesPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PropertiesPackageImpl() {
        super(eNS_URI, PropertiesFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link PropertiesPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PropertiesPackage init() {
        if (isInited) return (PropertiesPackage)EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);

        // Obtain or create and register package
        PropertiesPackageImpl thePropertiesPackage = (PropertiesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PropertiesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PropertiesPackageImpl());

        isInited = true;

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getViewExtensionDescription() {
        return viewExtensionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getViewExtensionDescription_Identifier() {
        return (EAttribute)viewExtensionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getViewExtensionDescription_Metamodels() {
        return (EReference)viewExtensionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getViewExtensionDescription_Pages() {
        return (EReference)viewExtensionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getViewExtensionDescription_DefaultPage() {
        return (EReference)viewExtensionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getViewExtensionDescription_LabelExpression() {
        return (EAttribute)viewExtensionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getViewExtensionDescription_Groups() {
        return (EReference)viewExtensionDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getPageDescription() {
        return pageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPageDescription_Identifier() {
        return (EAttribute)pageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPageDescription_LabelExpression() {
        return (EAttribute)pageDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPageDescription_DomainClass() {
        return (EAttribute)pageDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getPageDescription_SemanticCandidateExpression() {
        return (EAttribute)pageDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getPageDescription_Groups() {
        return (EReference)pageDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getGroupDescription() {
        return groupDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGroupDescription_Identifier() {
        return (EAttribute)groupDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGroupDescription_LabelExpression() {
        return (EAttribute)groupDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGroupDescription_DomainClass() {
        return (EAttribute)groupDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getGroupDescription_SemanticCandidateExpression() {
        return (EAttribute)groupDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGroupDescription_Container() {
        return (EReference)groupDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getContainerDescription() {
        return containerDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getContainerDescription_Identifier() {
        return (EAttribute)containerDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContainerDescription_Widgets() {
        return (EReference)containerDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContainerDescription_DynamicMappings() {
        return (EReference)containerDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getWidgetDescription() {
        return widgetDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWidgetDescription_Identifier() {
        return (EAttribute)widgetDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getWidgetDescription_LabelExpression() {
        return (EAttribute)widgetDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getTextDescription() {
        return textDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTextDescription_ValueExpression() {
        return (EAttribute)textDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTextDescription_InitialOperation() {
        return (EReference)textDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLabelDescription() {
        return labelDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicMappingFor() {
        return dynamicMappingForEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicMappingFor_Iterator() {
        return (EAttribute)dynamicMappingForEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicMappingFor_DomainClassExpression() {
        return (EAttribute)dynamicMappingForEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicMappingFor_Switch() {
        return (EReference)dynamicMappingForEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicMappingSwitch() {
        return dynamicMappingSwitchEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicMappingSwitch_SwitchExpression() {
        return (EAttribute)dynamicMappingSwitchEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicMappingSwitch_Cases() {
        return (EReference)dynamicMappingSwitchEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getDynamicMappingCase() {
        return dynamicMappingCaseEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getDynamicMappingCase_CaseExpression() {
        return (EAttribute)dynamicMappingCaseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDynamicMappingCase_Widget() {
        return (EReference)dynamicMappingCaseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PropertiesFactory getPropertiesFactory() {
        return (PropertiesFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        viewExtensionDescriptionEClass = createEClass(VIEW_EXTENSION_DESCRIPTION);
        createEAttribute(viewExtensionDescriptionEClass, VIEW_EXTENSION_DESCRIPTION__IDENTIFIER);
        createEReference(viewExtensionDescriptionEClass, VIEW_EXTENSION_DESCRIPTION__METAMODELS);
        createEReference(viewExtensionDescriptionEClass, VIEW_EXTENSION_DESCRIPTION__PAGES);
        createEReference(viewExtensionDescriptionEClass, VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE);
        createEAttribute(viewExtensionDescriptionEClass, VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION);
        createEReference(viewExtensionDescriptionEClass, VIEW_EXTENSION_DESCRIPTION__GROUPS);

        pageDescriptionEClass = createEClass(PAGE_DESCRIPTION);
        createEAttribute(pageDescriptionEClass, PAGE_DESCRIPTION__IDENTIFIER);
        createEAttribute(pageDescriptionEClass, PAGE_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(pageDescriptionEClass, PAGE_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(pageDescriptionEClass, PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEReference(pageDescriptionEClass, PAGE_DESCRIPTION__GROUPS);

        groupDescriptionEClass = createEClass(GROUP_DESCRIPTION);
        createEAttribute(groupDescriptionEClass, GROUP_DESCRIPTION__IDENTIFIER);
        createEAttribute(groupDescriptionEClass, GROUP_DESCRIPTION__LABEL_EXPRESSION);
        createEAttribute(groupDescriptionEClass, GROUP_DESCRIPTION__DOMAIN_CLASS);
        createEAttribute(groupDescriptionEClass, GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION);
        createEReference(groupDescriptionEClass, GROUP_DESCRIPTION__CONTAINER);

        containerDescriptionEClass = createEClass(CONTAINER_DESCRIPTION);
        createEAttribute(containerDescriptionEClass, CONTAINER_DESCRIPTION__IDENTIFIER);
        createEReference(containerDescriptionEClass, CONTAINER_DESCRIPTION__WIDGETS);
        createEReference(containerDescriptionEClass, CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS);

        widgetDescriptionEClass = createEClass(WIDGET_DESCRIPTION);
        createEAttribute(widgetDescriptionEClass, WIDGET_DESCRIPTION__IDENTIFIER);
        createEAttribute(widgetDescriptionEClass, WIDGET_DESCRIPTION__LABEL_EXPRESSION);

        textDescriptionEClass = createEClass(TEXT_DESCRIPTION);
        createEAttribute(textDescriptionEClass, TEXT_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(textDescriptionEClass, TEXT_DESCRIPTION__INITIAL_OPERATION);

        labelDescriptionEClass = createEClass(LABEL_DESCRIPTION);

        dynamicMappingForEClass = createEClass(DYNAMIC_MAPPING_FOR);
        createEAttribute(dynamicMappingForEClass, DYNAMIC_MAPPING_FOR__ITERATOR);
        createEAttribute(dynamicMappingForEClass, DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION);
        createEReference(dynamicMappingForEClass, DYNAMIC_MAPPING_FOR__SWITCH);

        dynamicMappingSwitchEClass = createEClass(DYNAMIC_MAPPING_SWITCH);
        createEAttribute(dynamicMappingSwitchEClass, DYNAMIC_MAPPING_SWITCH__SWITCH_EXPRESSION);
        createEReference(dynamicMappingSwitchEClass, DYNAMIC_MAPPING_SWITCH__CASES);

        dynamicMappingCaseEClass = createEClass(DYNAMIC_MAPPING_CASE);
        createEAttribute(dynamicMappingCaseEClass, DYNAMIC_MAPPING_CASE__CASE_EXPRESSION);
        createEReference(dynamicMappingCaseEClass, DYNAMIC_MAPPING_CASE__WIDGET);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage)EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage)EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        viewExtensionDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getExtension());
        textDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());
        labelDescriptionEClass.getESuperTypes().add(this.getWidgetDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(viewExtensionDescriptionEClass, ViewExtensionDescription.class, "ViewExtensionDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getViewExtensionDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, ViewExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewExtensionDescription_Metamodels(), theEcorePackage.getEPackage(), null, "metamodels", null, 0, -1, ViewExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewExtensionDescription_Pages(), this.getPageDescription(), null, "pages", null, 0, -1, ViewExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewExtensionDescription_DefaultPage(), this.getPageDescription(), null, "defaultPage", null, 0, 1, ViewExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getViewExtensionDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, ViewExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getViewExtensionDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, ViewExtensionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pageDescriptionEClass, PageDescription.class, "PageDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPageDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, PageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPageDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, PageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPageDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, PageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPageDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, PageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPageDescription_Groups(), this.getGroupDescription(), null, "groups", null, 0, -1, PageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(groupDescriptionEClass, GroupDescription.class, "GroupDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGroupDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, GroupDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroupDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, GroupDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroupDescription_DomainClass(), theDescriptionPackage.getTypeName(), "domainClass", null, 0, 1, GroupDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGroupDescription_SemanticCandidateExpression(), theDescriptionPackage.getInterpretedExpression(), "semanticCandidateExpression", null, 0, 1, GroupDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGroupDescription_Container(), this.getContainerDescription(), null, "container", null, 0, 1, GroupDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerDescriptionEClass, ContainerDescription.class, "ContainerDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainerDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, ContainerDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDescription_Widgets(), this.getWidgetDescription(), null, "widgets", null, 0, -1, ContainerDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContainerDescription_DynamicMappings(), this.getDynamicMappingFor(), null, "dynamicMappings", null, 0, -1, ContainerDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(widgetDescriptionEClass, WidgetDescription.class, "WidgetDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWidgetDescription_Identifier(), ecorePackage.getEString(), "identifier", null, 1, 1, WidgetDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getWidgetDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", null, 0, 1, WidgetDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(textDescriptionEClass, TextDescription.class, "TextDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTextDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", null, 0, 1, TextDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTextDescription_InitialOperation(), theToolPackage.getInitialOperation(), null, "initialOperation", null, 1, 1, TextDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelDescriptionEClass, LabelDescription.class, "LabelDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dynamicMappingForEClass, DynamicMappingFor.class, "DynamicMappingFor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDynamicMappingFor_Iterator(), theEcorePackage.getEString(), "iterator", null, 1, 1, DynamicMappingFor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDynamicMappingFor_DomainClassExpression(), theEcorePackage.getEString(), "domainClassExpression", null, 1, 1, DynamicMappingFor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDynamicMappingFor_Switch(), this.getDynamicMappingSwitch(), null, "switch", null, 1, 1, DynamicMappingFor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dynamicMappingSwitchEClass, DynamicMappingSwitch.class, "DynamicMappingSwitch", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDynamicMappingSwitch_SwitchExpression(), theEcorePackage.getEString(), "switchExpression", null, 1, 1, DynamicMappingSwitch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDynamicMappingSwitch_Cases(), this.getDynamicMappingCase(), null, "cases", null, 1, -1, DynamicMappingSwitch.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dynamicMappingCaseEClass, DynamicMappingCase.class, "DynamicMappingCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getDynamicMappingCase_CaseExpression(), theEcorePackage.getEString(), "caseExpression", null, 1, 1, DynamicMappingCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDynamicMappingCase_Widget(), this.getWidgetDescription(), null, "widget", null, 1, 1, DynamicMappingCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //PropertiesPackageImpl
