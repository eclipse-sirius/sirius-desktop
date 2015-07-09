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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Scenario</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.benchmark.Scenario#getInputData
 * <em>Input Data</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.benchmark.Scenario#getTestCases
 * <em>Test Cases</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.benchmark.Scenario#getVariants
 * <em>Variants</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getScenario()
 * @model
 * @generated
 */
public interface Scenario extends NamedElement {
    /**
     * Returns the value of the '<em><b>Input Data</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.benchmark.InputData}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Data</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Input Data</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getScenario_InputData()
     * @model containment="true"
     * @generated
     */
    EList<InputData> getInputData();

    /**
     * Returns the value of the '<em><b>Test Cases</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.benchmark.TestCase}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Test Cases</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Test Cases</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getScenario_TestCases()
     * @model containment="true"
     * @generated
     */
    EList<TestCase> getTestCases();

    /**
     * Returns the value of the '<em><b>Variants</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.benchmark.Variant}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variants</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Variants</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getScenario_Variants()
     * @model containment="true"
     * @generated
     */
    EList<Variant> getVariants();

} // Scenario
