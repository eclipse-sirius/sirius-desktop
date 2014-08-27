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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage
 * @generated
 */
public interface BenchmarkFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    BenchmarkFactory eINSTANCE = org.eclipse.sirius.tests.sample.benchmark.impl.BenchmarkFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Scenario</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Scenario</em>'.
     * @generated
     */
    Scenario createScenario();

    /**
     * Returns a new object of class '<em>Test Case</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Test Case</em>'.
     * @generated
     */
    TestCase createTestCase();

    /**
     * Returns a new object of class '<em>Input Data</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Input Data</em>'.
     * @generated
     */
    InputData createInputData();

    /**
     * Returns a new object of class '<em>Property</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Property</em>'.
     * @generated
     */
    Property createProperty();

    /**
     * Returns a new object of class '<em>Named Element</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Named Element</em>'.
     * @generated
     */
    NamedElement createNamedElement();

    /**
     * Returns a new object of class '<em>Time Result</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Time Result</em>'.
     * @generated
     */
    TimeResult createTimeResult();

    /**
     * Returns a new object of class '<em>Variant</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Variant</em>'.
     * @generated
     */
    Variant createVariant();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    BenchmarkPackage getBenchmarkPackage();

} // BenchmarkFactory
