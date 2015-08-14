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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Time Result</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedTime
 * <em>Elapsed Time</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedMaxTime
 * <em>Elapsed Max Time</em>}</li>
 * <li>{@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getVariant
 * <em>Variant</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getProperties
 * <em>Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getTimeResult()
 * @model
 * @generated
 */
public interface TimeResult extends EObject {
    /**
     * Returns the value of the '<em><b>Elapsed Time</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elapsed Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Elapsed Time</em>' attribute.
     * @see #setElapsedTime(long)
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getTimeResult_ElapsedTime()
     * @model
     * @generated
     */
    long getElapsedTime();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedTime
     * <em>Elapsed Time</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Elapsed Time</em>' attribute.
     * @see #getElapsedTime()
     * @generated
     */
    void setElapsedTime(long value);

    /**
     * Returns the value of the '<em><b>Elapsed Max Time</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elapsed Max Time</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Elapsed Max Time</em>' attribute.
     * @see #setElapsedMaxTime(long)
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getTimeResult_ElapsedMaxTime()
     * @model
     * @generated
     */
    long getElapsedMaxTime();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getElapsedMaxTime
     * <em>Elapsed Max Time</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Elapsed Max Time</em>' attribute.
     * @see #getElapsedMaxTime()
     * @generated
     */
    void setElapsedMaxTime(long value);

    /**
     * Returns the value of the '<em><b>Variant</b></em>' reference. It is
     * bidirectional and its opposite is '
     * {@link org.eclipse.sirius.tests.sample.benchmark.Variant#getResults
     * <em>Results</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variant</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Variant</em>' reference.
     * @see #setVariant(Variant)
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getTimeResult_Variant()
     * @see org.eclipse.sirius.tests.sample.benchmark.Variant#getResults
     * @model opposite="results"
     * @generated
     */
    Variant getVariant();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.benchmark.TimeResult#getVariant
     * <em>Variant</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Variant</em>' reference.
     * @see #getVariant()
     * @generated
     */
    void setVariant(Variant value);

    /**
     * Returns the value of the '<em><b>Properties</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.benchmark.Property}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Properties</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Properties</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage#getTimeResult_Properties()
     * @model containment="true"
     * @generated
     */
    EList<Property> getProperties();

} // TimeResult
