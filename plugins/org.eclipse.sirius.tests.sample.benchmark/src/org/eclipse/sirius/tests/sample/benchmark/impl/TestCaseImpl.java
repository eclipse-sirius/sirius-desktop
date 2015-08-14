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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage;
import org.eclipse.sirius.tests.sample.benchmark.InputData;
import org.eclipse.sirius.tests.sample.benchmark.TestCase;
import org.eclipse.sirius.tests.sample.benchmark.TimeResult;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Test Case</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TestCaseImpl#getInputData
 * <em>Input Data</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.TestCaseImpl#getResults
 * <em>Results</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestCaseImpl extends NamedElementImpl implements TestCase {
    /**
     * The cached value of the '{@link #getInputData() <em>Input Data</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getInputData()
     * @generated
     * @ordered
     */
    protected InputData inputData;

    /**
     * The cached value of the '{@link #getResults() <em>Results</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getResults()
     * @generated
     * @ordered
     */
    protected EList<TimeResult> results;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TestCaseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BenchmarkPackage.Literals.TEST_CASE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InputData getInputData() {
        if (inputData != null && inputData.eIsProxy()) {
            InternalEObject oldInputData = (InternalEObject) inputData;
            inputData = (InputData) eResolveProxy(oldInputData);
            if (inputData != oldInputData) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, BenchmarkPackage.TEST_CASE__INPUT_DATA, oldInputData, inputData));
            }
        }
        return inputData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public InputData basicGetInputData() {
        return inputData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetInputData(InputData newInputData, NotificationChain msgs) {
        InputData oldInputData = inputData;
        inputData = newInputData;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, BenchmarkPackage.TEST_CASE__INPUT_DATA, oldInputData, newInputData);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setInputData(InputData newInputData) {
        if (newInputData != inputData) {
            NotificationChain msgs = null;
            if (inputData != null)
                msgs = ((InternalEObject) inputData).eInverseRemove(this, BenchmarkPackage.INPUT_DATA__TESTS, InputData.class, msgs);
            if (newInputData != null)
                msgs = ((InternalEObject) newInputData).eInverseAdd(this, BenchmarkPackage.INPUT_DATA__TESTS, InputData.class, msgs);
            msgs = basicSetInputData(newInputData, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, BenchmarkPackage.TEST_CASE__INPUT_DATA, newInputData, newInputData));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TimeResult> getResults() {
        if (results == null) {
            results = new EObjectContainmentEList<TimeResult>(TimeResult.class, this, BenchmarkPackage.TEST_CASE__RESULTS);
        }
        return results;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BenchmarkPackage.TEST_CASE__INPUT_DATA:
            if (inputData != null)
                msgs = ((InternalEObject) inputData).eInverseRemove(this, BenchmarkPackage.INPUT_DATA__TESTS, InputData.class, msgs);
            return basicSetInputData((InputData) otherEnd, msgs);
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
        case BenchmarkPackage.TEST_CASE__INPUT_DATA:
            return basicSetInputData(null, msgs);
        case BenchmarkPackage.TEST_CASE__RESULTS:
            return ((InternalEList<?>) getResults()).basicRemove(otherEnd, msgs);
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
        case BenchmarkPackage.TEST_CASE__INPUT_DATA:
            if (resolve)
                return getInputData();
            return basicGetInputData();
        case BenchmarkPackage.TEST_CASE__RESULTS:
            return getResults();
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
        case BenchmarkPackage.TEST_CASE__INPUT_DATA:
            setInputData((InputData) newValue);
            return;
        case BenchmarkPackage.TEST_CASE__RESULTS:
            getResults().clear();
            getResults().addAll((Collection<? extends TimeResult>) newValue);
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
        case BenchmarkPackage.TEST_CASE__INPUT_DATA:
            setInputData((InputData) null);
            return;
        case BenchmarkPackage.TEST_CASE__RESULTS:
            getResults().clear();
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
        case BenchmarkPackage.TEST_CASE__INPUT_DATA:
            return inputData != null;
        case BenchmarkPackage.TEST_CASE__RESULTS:
            return results != null && !results.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // TestCaseImpl
