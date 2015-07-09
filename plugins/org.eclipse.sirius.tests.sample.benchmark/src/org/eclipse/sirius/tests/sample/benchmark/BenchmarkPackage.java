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
package org.eclipse.sirius.tests.sample.benchmark;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkFactory
 * @model kind="package"
 * @generated
 */
public interface BenchmarkPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "benchmark"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/tests/sample/benchmark"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "benchmark"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    BenchmarkPackage eINSTANCE = org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.NamedElementImpl
     * <em>Named Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.NamedElementImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getNamedElement()
     * @generated
     */
    int NAMED_ELEMENT = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__NAME = 0;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__PROPERTIES = 1;

    /**
     * The number of structural features of the '<em>Named Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl
     * <em>Scenario</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getScenario()
     * @generated
     */
    int SCENARIO = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCENARIO__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCENARIO__PROPERTIES = NAMED_ELEMENT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Input Data</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCENARIO__INPUT_DATA = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Test Cases</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCENARIO__TEST_CASES = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variants</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCENARIO__VARIANTS = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Scenario</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SCENARIO_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TestCaseImpl
     * <em>Test Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.TestCaseImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getTestCase()
     * @generated
     */
    int TEST_CASE = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEST_CASE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEST_CASE__PROPERTIES = NAMED_ELEMENT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Input Data</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEST_CASE__INPUT_DATA = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Results</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEST_CASE__RESULTS = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Test Case</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEST_CASE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.InputDataImpl
     * <em>Input Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.InputDataImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getInputData()
     * @generated
     */
    int INPUT_DATA = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_DATA__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_DATA__PROPERTIES = NAMED_ELEMENT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Tests</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_DATA__TESTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Input Data</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INPUT_DATA_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.PropertyImpl
     * <em>Property</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.PropertyImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getProperty()
     * @generated
     */
    int PROPERTY = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY__VALUE = 1;

    /**
     * The number of structural features of the '<em>Property</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PROPERTY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl
     * <em>Time Result</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getTimeResult()
     * @generated
     */
    int TIME_RESULT = 5;

    /**
     * The feature id for the '<em><b>Elapsed Time</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TIME_RESULT__ELAPSED_TIME = 0;

    /**
     * The feature id for the '<em><b>Elapsed Max Time</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TIME_RESULT__ELAPSED_MAX_TIME = 1;

    /**
     * The feature id for the '<em><b>Variant</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TIME_RESULT__VARIANT = 2;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TIME_RESULT__PROPERTIES = 3;

    /**
     * The number of structural features of the '<em>Time Result</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TIME_RESULT_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.impl.VariantImpl
     * <em>Variant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.VariantImpl
     * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getVariant()
     * @generated
     */
    int VARIANT = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIANT__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Properties</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIANT__PROPERTIES = NAMED_ELEMENT__PROPERTIES;

    /**
     * The feature id for the '<em><b>Results</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIANT__RESULTS = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Variant</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIANT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Scenario
     * <em>Scenario</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Scenario</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Scenario
     * @generated
     */
    EClass getScenario();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Scenario#getInputData
     * <em>Input Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Input Data</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Scenario#getInputData()
     * @see #getScenario()
     * @generated
     */
    EReference getScenario_InputData();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Scenario#getTestCases
     * <em>Test Cases</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Test Cases</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Scenario#getTestCases()
     * @see #getScenario()
     * @generated
     */
    EReference getScenario_TestCases();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Scenario#getVariants
     * <em>Variants</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Variants</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Scenario#getVariants()
     * @see #getScenario()
     * @generated
     */
    EReference getScenario_Variants();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TestCase
     * <em>Test Case</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Test Case</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TestCase
     * @generated
     */
    EClass getTestCase();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TestCase#getInputData
     * <em>Input Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Input Data</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TestCase#getInputData()
     * @see #getTestCase()
     * @generated
     */
    EReference getTestCase_InputData();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TestCase#getResults
     * <em>Results</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Results</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TestCase#getResults()
     * @see #getTestCase()
     * @generated
     */
    EReference getTestCase_Results();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.InputData
     * <em>Input Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Input Data</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.InputData
     * @generated
     */
    EClass getInputData();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.InputData#getTests
     * <em>Tests</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Tests</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.InputData#getTests()
     * @see #getInputData()
     * @generated
     */
    EReference getInputData_Tests();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Property
     * <em>Property</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Property</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Property
     * @generated
     */
    EClass getProperty();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Property#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Property#getName()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Property#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Property#getValue()
     * @see #getProperty()
     * @generated
     */
    EAttribute getProperty_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.NamedElement
     * <em>Named Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Named Element</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.NamedElement
     * @generated
     */
    EClass getNamedElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.benchmark.NamedElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.NamedElement#getName()
     * @see #getNamedElement()
     * @generated
     */
    EAttribute getNamedElement_Name();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.NamedElement#getProperties
     * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Properties</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.NamedElement#getProperties()
     * @see #getNamedElement()
     * @generated
     */
    EReference getNamedElement_Properties();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult
     * <em>Time Result</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Time Result</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TimeResult
     * @generated
     */
    EClass getTimeResult();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedTime
     * <em>Elapsed Time</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Elapsed Time</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedTime()
     * @see #getTimeResult()
     * @generated
     */
    EAttribute getTimeResult_ElapsedTime();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedMaxTime
     * <em>Elapsed Max Time</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Elapsed Max Time</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedMaxTime()
     * @see #getTimeResult()
     * @generated
     */
    EAttribute getTimeResult_ElapsedMaxTime();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getVariant
     * <em>Variant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Variant</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TimeResult#getVariant()
     * @see #getTimeResult()
     * @generated
     */
    EReference getTimeResult_Variant();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getProperties
     * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Properties</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.TimeResult#getProperties()
     * @see #getTimeResult()
     * @generated
     */
    EReference getTimeResult_Properties();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Variant
     * <em>Variant</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Variant</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Variant
     * @generated
     */
    EClass getVariant();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Variant#getResults
     * <em>Results</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Results</em>'.
     * @see org.eclipse.sirius.tests.sample.benchmark.Variant#getResults()
     * @see #getVariant()
     * @generated
     */
    EReference getVariant_Results();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    BenchmarkFactory getBenchmarkFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl
         * <em>Scenario</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getScenario()
         * @generated
         */
        EClass SCENARIO = eINSTANCE.getScenario();

        /**
         * The meta object literal for the '<em><b>Input Data</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SCENARIO__INPUT_DATA = eINSTANCE.getScenario_InputData();

        /**
         * The meta object literal for the '<em><b>Test Cases</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SCENARIO__TEST_CASES = eINSTANCE.getScenario_TestCases();

        /**
         * The meta object literal for the '<em><b>Variants</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SCENARIO__VARIANTS = eINSTANCE.getScenario_Variants();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TestCaseImpl
         * <em>Test Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.TestCaseImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getTestCase()
         * @generated
         */
        EClass TEST_CASE = eINSTANCE.getTestCase();

        /**
         * The meta object literal for the '<em><b>Input Data</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEST_CASE__INPUT_DATA = eINSTANCE.getTestCase_InputData();

        /**
         * The meta object literal for the '<em><b>Results</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEST_CASE__RESULTS = eINSTANCE.getTestCase_Results();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.InputDataImpl
         * <em>Input Data</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.InputDataImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getInputData()
         * @generated
         */
        EClass INPUT_DATA = eINSTANCE.getInputData();

        /**
         * The meta object literal for the '<em><b>Tests</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INPUT_DATA__TESTS = eINSTANCE.getInputData_Tests();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.PropertyImpl
         * <em>Property</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.PropertyImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getProperty()
         * @generated
         */
        EClass PROPERTY = eINSTANCE.getProperty();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PROPERTY__VALUE = eINSTANCE.getProperty_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.NamedElementImpl
         * <em>Named Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.NamedElementImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getNamedElement()
         * @generated
         */
        EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NAMED_ELEMENT__PROPERTIES = eINSTANCE.getNamedElement_Properties();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl
         * <em>Time Result</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.TimeResultImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getTimeResult()
         * @generated
         */
        EClass TIME_RESULT = eINSTANCE.getTimeResult();

        /**
         * The meta object literal for the '<em><b>Elapsed Time</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TIME_RESULT__ELAPSED_TIME = eINSTANCE.getTimeResult_ElapsedTime();

        /**
         * The meta object literal for the '<em><b>Elapsed Max Time</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TIME_RESULT__ELAPSED_MAX_TIME = eINSTANCE.getTimeResult_ElapsedMaxTime();

        /**
         * The meta object literal for the '<em><b>Variant</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TIME_RESULT__VARIANT = eINSTANCE.getTimeResult_Variant();

        /**
         * The meta object literal for the '<em><b>Properties</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TIME_RESULT__PROPERTIES = eINSTANCE.getTimeResult_Properties();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.benchmark.impl.VariantImpl
         * <em>Variant</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.VariantImpl
         * @see org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkPackageImpl#getVariant()
         * @generated
         */
        EClass VARIANT = eINSTANCE.getVariant();

        /**
         * The meta object literal for the '<em><b>Results</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VARIANT__RESULTS = eINSTANCE.getVariant_Results();

    }

} // BenchmarkPackage
