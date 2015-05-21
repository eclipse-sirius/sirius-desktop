/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Test Case</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.TestCaseImpl#getRepresentations
 * <em>Representations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestCaseImpl extends EObjectImpl implements TestCase {
    /**
     * The cached value of the '{@link #getRepresentations()
     * <em>Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRepresentations()
     * @generated
     * @ordered
     */
    protected EList<Representation> representations;

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
        return MigrationmodelerPackage.Literals.TEST_CASE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Representation> getRepresentations() {
        if (representations == null) {
            representations = new EObjectContainmentEList<Representation>(Representation.class, this, MigrationmodelerPackage.TEST_CASE__REPRESENTATIONS);
        }
        return representations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.TEST_CASE__REPRESENTATIONS:
            return ((InternalEList<?>) getRepresentations()).basicRemove(otherEnd, msgs);
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
        case MigrationmodelerPackage.TEST_CASE__REPRESENTATIONS:
            return getRepresentations();
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
        case MigrationmodelerPackage.TEST_CASE__REPRESENTATIONS:
            getRepresentations().clear();
            getRepresentations().addAll((Collection<? extends Representation>) newValue);
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
        case MigrationmodelerPackage.TEST_CASE__REPRESENTATIONS:
            getRepresentations().clear();
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
        case MigrationmodelerPackage.TEST_CASE__REPRESENTATIONS:
            return representations != null && !representations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // TestCaseImpl
