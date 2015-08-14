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

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage;
import org.eclipse.sirius.tests.sample.benchmark.InputData;
import org.eclipse.sirius.tests.sample.benchmark.TestCase;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Input Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.InputDataImpl#getTests
 * <em>Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputDataImpl extends NamedElementImpl implements InputData {
    /**
     * The cached value of the '{@link #getTests() <em>Tests</em>}' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTests()
     * @generated
     * @ordered
     */
    protected EList<TestCase> tests;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected InputDataImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BenchmarkPackage.Literals.INPUT_DATA;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TestCase> getTests() {
        if (tests == null) {
            tests = new EObjectWithInverseResolvingEList<TestCase>(TestCase.class, this, BenchmarkPackage.INPUT_DATA__TESTS, BenchmarkPackage.TEST_CASE__INPUT_DATA);
        }
        return tests;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BenchmarkPackage.INPUT_DATA__TESTS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) getTests()).basicAdd(otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BenchmarkPackage.INPUT_DATA__TESTS:
            return ((InternalEList<?>) getTests()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case BenchmarkPackage.INPUT_DATA__TESTS:
            return getTests();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case BenchmarkPackage.INPUT_DATA__TESTS:
            getTests().clear();
            getTests().addAll((Collection<? extends TestCase>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case BenchmarkPackage.INPUT_DATA__TESTS:
            getTests().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case BenchmarkPackage.INPUT_DATA__TESTS:
            return tests != null && !tests.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // InputDataImpl
