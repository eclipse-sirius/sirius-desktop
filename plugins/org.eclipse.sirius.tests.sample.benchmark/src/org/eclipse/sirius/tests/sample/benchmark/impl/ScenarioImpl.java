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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.benchmark.BenchmarkPackage;
import org.eclipse.sirius.tests.sample.benchmark.InputData;
import org.eclipse.sirius.tests.sample.benchmark.Scenario;
import org.eclipse.sirius.tests.sample.benchmark.TestCase;
import org.eclipse.sirius.tests.sample.benchmark.Variant;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Scenario</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl#getInputData
 * <em>Input Data</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl#getTestCases
 * <em>Test Cases</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.benchmark.impl.ScenarioImpl#getVariants
 * <em>Variants</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioImpl extends NamedElementImpl implements Scenario {
    /**
     * The cached value of the '{@link #getInputData() <em>Input Data</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getInputData()
     * @generated
     * @ordered
     */
    protected EList<InputData> inputData;

    /**
     * The cached value of the '{@link #getTestCases() <em>Test Cases</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTestCases()
     * @generated
     * @ordered
     */
    protected EList<TestCase> testCases;

    /**
     * The cached value of the '{@link #getVariants() <em>Variants</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getVariants()
     * @generated
     * @ordered
     */
    protected EList<Variant> variants;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ScenarioImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return BenchmarkPackage.Literals.SCENARIO;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<InputData> getInputData() {
        if (inputData == null) {
            inputData = new EObjectContainmentEList<InputData>(InputData.class, this, BenchmarkPackage.SCENARIO__INPUT_DATA);
        }
        return inputData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TestCase> getTestCases() {
        if (testCases == null) {
            testCases = new EObjectContainmentEList<TestCase>(TestCase.class, this, BenchmarkPackage.SCENARIO__TEST_CASES);
        }
        return testCases;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<Variant> getVariants() {
        if (variants == null) {
            variants = new EObjectContainmentEList<Variant>(Variant.class, this, BenchmarkPackage.SCENARIO__VARIANTS);
        }
        return variants;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case BenchmarkPackage.SCENARIO__INPUT_DATA:
            return ((InternalEList<?>) getInputData()).basicRemove(otherEnd, msgs);
        case BenchmarkPackage.SCENARIO__TEST_CASES:
            return ((InternalEList<?>) getTestCases()).basicRemove(otherEnd, msgs);
        case BenchmarkPackage.SCENARIO__VARIANTS:
            return ((InternalEList<?>) getVariants()).basicRemove(otherEnd, msgs);
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
        case BenchmarkPackage.SCENARIO__INPUT_DATA:
            return getInputData();
        case BenchmarkPackage.SCENARIO__TEST_CASES:
            return getTestCases();
        case BenchmarkPackage.SCENARIO__VARIANTS:
            return getVariants();
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
        case BenchmarkPackage.SCENARIO__INPUT_DATA:
            getInputData().clear();
            getInputData().addAll((Collection<? extends InputData>) newValue);
            return;
        case BenchmarkPackage.SCENARIO__TEST_CASES:
            getTestCases().clear();
            getTestCases().addAll((Collection<? extends TestCase>) newValue);
            return;
        case BenchmarkPackage.SCENARIO__VARIANTS:
            getVariants().clear();
            getVariants().addAll((Collection<? extends Variant>) newValue);
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
        case BenchmarkPackage.SCENARIO__INPUT_DATA:
            getInputData().clear();
            return;
        case BenchmarkPackage.SCENARIO__TEST_CASES:
            getTestCases().clear();
            return;
        case BenchmarkPackage.SCENARIO__VARIANTS:
            getVariants().clear();
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
        case BenchmarkPackage.SCENARIO__INPUT_DATA:
            return inputData != null && !inputData.isEmpty();
        case BenchmarkPackage.SCENARIO__TEST_CASES:
            return testCases != null && !testCases.isEmpty();
        case BenchmarkPackage.SCENARIO__VARIANTS:
            return variants != null && !variants.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ScenarioImpl
