/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.benchmark;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Input Data</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.tests.sample.benchmark.InputData#getTests <em>
 * Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getInputData()
 * @model
 * @generated
 */
public interface InputData extends NamedElement {
    /**
     * Returns the value of the '<em><b>Tests</b></em>' reference list. The list
     * contents are of type
     * {@link org.eclipse.sirius.tests.sample.benchmark.TestCase}. It is
     * bidirectional and its opposite is '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TestCase#getInputData
     * <em>Input Data</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tests</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Tests</em>' reference list.
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getInputData_Tests()
     * @see org.eclipse.sirius.tests.sample.benchmark.TestCase#getInputData
     * @model opposite="inputData"
     * @generated
     */
    EList<TestCase> getTests();

} // InputData
