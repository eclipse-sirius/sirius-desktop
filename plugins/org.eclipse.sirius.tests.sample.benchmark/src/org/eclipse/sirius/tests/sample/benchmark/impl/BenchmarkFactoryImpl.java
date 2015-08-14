/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.benchmark.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkFactory;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage;
import org.eclipse.sirius.tests.sample.benchmark.InputData;
import org.eclipse.sirius.tests.sample.benchmark.NamedElement;
import org.eclipse.sirius.tests.sample.benchmark.Property;
import org.eclipse.sirius.tests.sample.benchmark.Scenario;
import org.eclipse.sirius.tests.sample.benchmark.TestCase;
import org.eclipse.sirius.tests.sample.benchmark.TimeResult;
import org.eclipse.sirius.tests.sample.benchmark.Variant;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class BenchmarkFactoryImpl extends EFactoryImpl implements BenchmarkFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static BenchmarkFactory init() {
        try {
            BenchmarkFactory theBenchmarkFactory = (BenchmarkFactory) EPackage.Registry.INSTANCE.getEFactory(BenchmarkPackage.eNS_URI);
            if (theBenchmarkFactory != null) {
                return theBenchmarkFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new BenchmarkFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public BenchmarkFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case BenchmarkPackage.SCENARIO:
            return createScenario();
        case BenchmarkPackage.TEST_CASE:
            return createTestCase();
        case BenchmarkPackage.INPUT_DATA:
            return createInputData();
        case BenchmarkPackage.PROPERTY:
            return createProperty();
        case BenchmarkPackage.NAMED_ELEMENT:
            return createNamedElement();
        case BenchmarkPackage.TIME_RESULT:
            return createTimeResult();
        case BenchmarkPackage.VARIANT:
            return createVariant();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Scenario createScenario() {
        ScenarioImpl scenario = new ScenarioImpl();
        return scenario;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TestCase createTestCase() {
        TestCaseImpl testCase = new TestCaseImpl();
        return testCase;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InputData createInputData() {
        InputDataImpl inputData = new InputDataImpl();
        return inputData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Property createProperty() {
        PropertyImpl property = new PropertyImpl();
        return property;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NamedElement createNamedElement() {
        NamedElementImpl namedElement = new NamedElementImpl();
        return namedElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TimeResult createTimeResult() {
        TimeResultImpl timeResult = new TimeResultImpl();
        return timeResult;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Variant createVariant() {
        VariantImpl variant = new VariantImpl();
        return variant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BenchmarkPackage getBenchmarkPackage() {
        return (BenchmarkPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static BenchmarkPackage getPackage() {
        return BenchmarkPackage.eINSTANCE;
    }

} // BenchmarkFactoryImpl
