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
 *
 * @generated
 */
public class PropertiestestsPackageImpl extends EPackageImpl implements PropertiestestsPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass testRootEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass testElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
        super(PropertiestestsPackage.eNS_URI, PropertiestestsFactory.eINSTANCE);
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
     * This method is used to initialize
     * {@link PropertiestestsPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access
     * that field to obtain the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PropertiestestsPackage init() {
        if (PropertiestestsPackageImpl.isInited) {
            return (PropertiestestsPackage) EPackage.Registry.INSTANCE.getEPackage(PropertiestestsPackage.eNS_URI);
        }

        // Obtain or create and register package
        PropertiestestsPackageImpl thePropertiestestsPackage = (PropertiestestsPackageImpl) (EPackage.Registry.INSTANCE.get(PropertiestestsPackage.eNS_URI) instanceof PropertiestestsPackageImpl
                ? EPackage.Registry.INSTANCE.get(PropertiestestsPackage.eNS_URI) : new PropertiestestsPackageImpl());

        PropertiestestsPackageImpl.isInited = true;

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
     *
     * @generated
     */
    @Override
    public EClass getTestRoot() {
        return testRootEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTestRoot_Elements() {
        return (EReference) testRootEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTestElement() {
        return testElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_StringAttribute() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_StringAttributes() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_IntAttribute() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_IntAttributes() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_BooleanAttribute() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_BooleanAttributes() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_EnumAttribute() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTestElement_EnumAttributes() {
        return (EAttribute) testElementEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTestElement_ContainmentReference() {
        return (EReference) testElementEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTestElement_ContainmentReferences() {
        return (EReference) testElementEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTestElement_Reference() {
        return (EReference) testElementEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTestElement_References() {
        return (EReference) testElementEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getTestEnum() {
        return testEnumEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PropertiestestsFactory getPropertiestestsFactory() {
        return (PropertiestestsFactory) getEFactoryInstance();
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
        testRootEClass = createEClass(PropertiestestsPackage.TEST_ROOT);
        createEReference(testRootEClass, PropertiestestsPackage.TEST_ROOT__ELEMENTS);

        testElementEClass = createEClass(PropertiestestsPackage.TEST_ELEMENT);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTE);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__STRING_ATTRIBUTES);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTE);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__INT_ATTRIBUTES);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTE);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__BOOLEAN_ATTRIBUTES);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTE);
        createEAttribute(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__ENUM_ATTRIBUTES);
        createEReference(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCE);
        createEReference(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__CONTAINMENT_REFERENCES);
        createEReference(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__REFERENCE);
        createEReference(testElementEClass, PropertiestestsPackage.TEST_ELEMENT__REFERENCES);

        // Create enums
        testEnumEEnum = createEEnum(PropertiestestsPackage.TEST_ENUM);
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
        setName(PropertiestestsPackage.eNAME);
        setNsPrefix(PropertiestestsPackage.eNS_PREFIX);
        setNsURI(PropertiestestsPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(testRootEClass, TestRoot.class, "TestRoot", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTestRoot_Elements(), this.getTestElement(), null, "elements", null, 0, -1, TestRoot.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE,
                EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(testElementEClass, TestElement.class, "TestElement", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTestElement_StringAttribute(), ecorePackage.getEString(), "stringAttribute", null, 0, 1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_StringAttributes(), ecorePackage.getEString(), "stringAttributes", null, 0, -1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_IntAttribute(), ecorePackage.getEInt(), "intAttribute", null, 0, 1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_IntAttributes(), ecorePackage.getEInt(), "intAttributes", null, 0, -1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_BooleanAttribute(), ecorePackage.getEBoolean(), "booleanAttribute", null, 0, 1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_BooleanAttributes(), ecorePackage.getEBoolean(), "booleanAttributes", null, 0, -1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_EnumAttribute(), this.getTestEnum(), "enumAttribute", null, 0, 1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTestElement_EnumAttributes(), this.getTestEnum(), "enumAttributes", null, 0, -1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTestElement_ContainmentReference(), this.getTestElement(), null, "containmentReference", null, 0, 1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getTestElement_ContainmentReferences(), this.getTestElement(), null, "containmentReferences", null, 0, -1, TestElement.class, !EPackageImpl.IS_TRANSIENT,
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getTestElement_Reference(), this.getTestElement(), null, "reference", null, 0, 1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getTestElement_References(), this.getTestElement(), null, "references", null, 0, -1, TestElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE,
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(testEnumEEnum, TestEnum.class, "TestEnum");
        addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL1);
        addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL2);
        addEEnumLiteral(testEnumEEnum, TestEnum.LITERAL3);

        // Create resource
        createResource(PropertiestestsPackage.eNS_URI);
    }

} // PropertiestestsPackageImpl
