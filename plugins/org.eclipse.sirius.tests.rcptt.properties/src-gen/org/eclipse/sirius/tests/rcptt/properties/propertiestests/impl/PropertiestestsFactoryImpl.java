/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsFactory;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestEnum;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class PropertiestestsFactoryImpl extends EFactoryImpl implements PropertiestestsFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public static PropertiestestsFactory init() {
        try {
            PropertiestestsFactory thePropertiestestsFactory = (PropertiestestsFactory)EPackage.Registry.INSTANCE.getEFactory(PropertiestestsPackage.eNS_URI);
            if (thePropertiestestsFactory != null) {
                return thePropertiestestsFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PropertiestestsFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public PropertiestestsFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case PropertiestestsPackage.TEST_ROOT: return createTestRoot();
            case PropertiestestsPackage.TEST_ELEMENT: return createTestElement();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case PropertiestestsPackage.TEST_ENUM:
                return createTestEnumFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case PropertiestestsPackage.TEST_ENUM:
                return convertTestEnumToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TestRoot createTestRoot() {
        TestRootImpl testRoot = new TestRootImpl();
        return testRoot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TestElement createTestElement() {
        TestElementImpl testElement = new TestElementImpl();
        return testElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public TestEnum createTestEnumFromString(EDataType eDataType, String initialValue) {
        TestEnum result = TestEnum.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public String convertTestEnumToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public PropertiestestsPackage getPropertiestestsPackage() {
        return (PropertiestestsPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PropertiestestsPackage getPackage() {
        return PropertiestestsPackage.eINSTANCE;
    }

} // PropertiestestsFactoryImpl
