/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsFactory;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class PropertiestestsPackageImpl extends EPackageImpl implements PropertiestestsPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass testRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EClass testElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private EEnum testEnumEEnum = null;

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
     * @see org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PropertiestestsPackageImpl() {
        super(eNS_URI, PropertiestestsFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link PropertiestestsPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PropertiestestsPackage init() {
        if (isInited) return (PropertiestestsPackage)EPackage.Registry.INSTANCE.getEPackage(PropertiestestsPackage.eNS_URI);

        // Obtain or create and register package
        PropertiestestsPackageImpl thePropertiestestsPackage = (PropertiestestsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PropertiestestsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PropertiestestsPackageImpl());

        isInited = true;

        // Create package meta-data objects
        thePropertiestestsPackage.createPackageContents();

        // Initialize created meta-data
        thePropertiestestsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePropertiestestsPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(PropertiestestsPackage.eNS_URI, thePropertiestestsPackage);
        return thePropertiestestsPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTestRoot() {
        return testRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTestRoot_Elements() {
        return (EReference)testRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getTestElement() {
        return testElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_StringAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_StringAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_StringAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_StringAttributeMultiline() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_StringAttributeMultilineMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_IntAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_IntAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_IntAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_BooleanAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_BooleanAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_BooleanAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_EnumAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EAttribute getTestElement_EnumAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_EnumAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTestElement_ContainmentReference() {
        return (EReference)testElementEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTestElement_ContainmentReferences() {
        return (EReference)testElementEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTestElement_ContainmentReferenceMandatory() {
        return (EReference)testElementEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTestElement_Reference() {
        return (EReference)testElementEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EReference getTestElement_References() {
        return (EReference)testElementEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getTestElement_ReferenceMandatory() {
        return (EReference)testElementEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_CharAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(20);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_CharAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(21);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_CharAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(22);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DateAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(23);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DateAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(24);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DateAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(25);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DoubleAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(26);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DoubleAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(27);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DoubleAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(28);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_FloatAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(29);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_FloatAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(30);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_FloatAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(31);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_LongAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(32);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_LongAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(33);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_LongAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(34);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_ShortAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(35);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_ShortAttributes() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(36);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_ShortAttributeMandatory() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(37);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_DerivedAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(38);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_TransientAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(39);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_NonChangeableAttribute() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(40);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getTestElement_OptionalFeature() {
        return (EAttribute)testElementEClass.getEStructuralFeatures().get(41);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EEnum getTestEnum() {
        return testEnumEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PropertiestestsFactory getPropertiestestsFactory() {
        return (PropertiestestsFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
        testRootEClass = createEClass(TEST_ROOT);
        createEReference(testRootEClass, TEST_ROOT__ELEMENTS);

        testElementEClass = createEClass(TEST_ELEMENT);
        createEAttribute(testElementEClass, TEST_ELEMENT__STRING_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__STRING_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__STRING_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE);
        createEAttribute(testElementEClass, TEST_ELEMENT__STRING_ATTRIBUTE_MULTILINE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__INT_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__INT_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__INT_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__BOOLEAN_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__BOOLEAN_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__BOOLEAN_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__ENUM_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__ENUM_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__ENUM_ATTRIBUTE_MANDATORY);
        createEReference(testElementEClass, TEST_ELEMENT__CONTAINMENT_REFERENCE);
        createEReference(testElementEClass, TEST_ELEMENT__CONTAINMENT_REFERENCES);
        createEReference(testElementEClass, TEST_ELEMENT__CONTAINMENT_REFERENCE_MANDATORY);
        createEReference(testElementEClass, TEST_ELEMENT__REFERENCE);
        createEReference(testElementEClass, TEST_ELEMENT__REFERENCES);
        createEReference(testElementEClass, TEST_ELEMENT__REFERENCE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__CHAR_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__CHAR_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__CHAR_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__DATE_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__DATE_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__DATE_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__DOUBLE_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__DOUBLE_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__DOUBLE_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__FLOAT_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__FLOAT_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__FLOAT_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__LONG_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__LONG_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__LONG_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__SHORT_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__SHORT_ATTRIBUTES);
        createEAttribute(testElementEClass, TEST_ELEMENT__SHORT_ATTRIBUTE_MANDATORY);
        createEAttribute(testElementEClass, TEST_ELEMENT__DERIVED_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__TRANSIENT_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__NON_CHANGEABLE_ATTRIBUTE);
        createEAttribute(testElementEClass, TEST_ELEMENT__OPTIONAL_FEATURE);

        // Create enums
        testEnumEEnum = createEEnum(TEST_ENUM);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
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
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(testRootEClass, TestRoot.class, "TestRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTestRoot_Elements(), this.getTestElement(), null, "elements", null, 0, -1, TestRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(testElementEClass, TestElement.class, "TestElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTestElement_StringAttribute(), ecorePackage.getEString(), "stringAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_StringAttributes(), ecorePackage.getEString(), "stringAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_StringAttributeMandatory(), ecorePackage.getEString(), "stringAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_StringAttributeMultiline(), ecorePackage.getEString(), "stringAttributeMultiline", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_StringAttributeMultilineMandatory(), ecorePackage.getEString(), "stringAttributeMultilineMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_IntAttribute(), ecorePackage.getEInt(), "intAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_IntAttributes(), ecorePackage.getEInt(), "intAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_IntAttributeMandatory(), ecorePackage.getEInt(), "intAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_BooleanAttribute(), ecorePackage.getEBoolean(), "booleanAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_BooleanAttributes(), ecorePackage.getEBoolean(), "booleanAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_BooleanAttributeMandatory(), ecorePackage.getEBoolean(), "booleanAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_EnumAttribute(), this.getTestEnum(), "enumAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_EnumAttributes(), this.getTestEnum(), "enumAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_EnumAttributeMandatory(), this.getTestEnum(), "enumAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestElement_ContainmentReference(), this.getTestElement(), null, "containmentReference", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestElement_ContainmentReferences(), this.getTestElement(), null, "containmentReferences", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestElement_ContainmentReferenceMandatory(), this.getTestElement(), null, "containmentReferenceMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestElement_Reference(), this.getTestElement(), null, "reference", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestElement_References(), this.getTestElement(), null, "references", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTestElement_ReferenceMandatory(), this.getTestElement(), null, "referenceMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_CharAttribute(), ecorePackage.getEChar(), "charAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_CharAttributes(), ecorePackage.getEChar(), "charAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_CharAttributeMandatory(), ecorePackage.getEChar(), "charAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DateAttribute(), ecorePackage.getEDate(), "dateAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DateAttributes(), ecorePackage.getEDate(), "dateAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DateAttributeMandatory(), ecorePackage.getEDate(), "dateAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DoubleAttribute(), ecorePackage.getEDouble(), "doubleAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DoubleAttributes(), ecorePackage.getEDouble(), "doubleAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DoubleAttributeMandatory(), ecorePackage.getEDouble(), "doubleAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_FloatAttribute(), ecorePackage.getEFloat(), "floatAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_FloatAttributes(), ecorePackage.getEFloat(), "floatAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_FloatAttributeMandatory(), ecorePackage.getEFloat(), "floatAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_LongAttribute(), ecorePackage.getELong(), "longAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_LongAttributes(), ecorePackage.getELong(), "longAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_LongAttributeMandatory(), ecorePackage.getELong(), "longAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_ShortAttribute(), ecorePackage.getEShort(), "shortAttribute", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_ShortAttributes(), ecorePackage.getEShort(), "shortAttributes", null, 0, -1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_ShortAttributeMandatory(), ecorePackage.getEShort(), "shortAttributeMandatory", null, 1, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_DerivedAttribute(), ecorePackage.getEString(), "derivedAttribute", "", 0, 1, TestElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_TransientAttribute(), ecorePackage.getEString(), "transientAttribute", null, 0, 1, TestElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_NonChangeableAttribute(), ecorePackage.getEString(), "nonChangeableAttribute", "NonChangeable", 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTestElement_OptionalFeature(), ecorePackage.getEString(), "optionalFeature", null, 0, 1, TestElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(testEnumEEnum, TestEnum.class, "TestEnum");
        addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL1);
        addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL2);
        addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL3);

        // Create resource
        createResource(eNS_URI);
    }

} // PropertiestestsPackageImpl
