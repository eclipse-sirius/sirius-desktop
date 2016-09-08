/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.PropertiestestsPackage;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestElement;
import org.eclipse.sirius.tests.rcptt.properties.propertiestests.TestRoot;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Test Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.rcptt.properties.propertiestests.impl.TestRootImpl#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestRootImpl extends MinimalEObjectImpl.Container implements TestRoot {
    /**
     * The cached value of the '{@link #getElements() <em>Elements</em>}' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getElements()
     * @generated
     * @ordered
     */
    protected EList<TestElement> elements;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected TestRootImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiestestsPackage.Literals.TEST_ROOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<TestElement> getElements() {
        if (elements == null) {
            elements = new EObjectContainmentEList<TestElement>(TestElement.class, this, PropertiestestsPackage.TEST_ROOT__ELEMENTS);
        }
        return elements;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ROOT__ELEMENTS:
                return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ROOT__ELEMENTS:
                return getElements();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ROOT__ELEMENTS:
                getElements().clear();
                getElements().addAll((Collection<? extends TestElement>)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ROOT__ELEMENTS:
                getElements().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case PropertiestestsPackage.TEST_ROOT__ELEMENTS:
                return elements != null && !elements.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // TestRootImpl
