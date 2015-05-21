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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bordered</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedImpl#getBorderedRepresentations
 * <em>Bordered Representations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BorderedImpl extends AbstractNodeImpl implements Bordered {
    /**
     * The cached value of the '{@link #getBorderedRepresentations()
     * <em>Bordered Representations</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderedRepresentations()
     * @generated
     * @ordered
     */
    protected EList<BorderedRepresentation> borderedRepresentations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BorderedImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.BORDERED;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<BorderedRepresentation> getBorderedRepresentations() {
        if (borderedRepresentations == null) {
            borderedRepresentations = new EObjectContainmentEList<BorderedRepresentation>(BorderedRepresentation.class, this, MigrationmodelerPackage.BORDERED__BORDERED_REPRESENTATIONS);
        }
        return borderedRepresentations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.BORDERED__BORDERED_REPRESENTATIONS:
            return ((InternalEList<?>) getBorderedRepresentations()).basicRemove(otherEnd, msgs);
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
        case MigrationmodelerPackage.BORDERED__BORDERED_REPRESENTATIONS:
            return getBorderedRepresentations();
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
        case MigrationmodelerPackage.BORDERED__BORDERED_REPRESENTATIONS:
            getBorderedRepresentations().clear();
            getBorderedRepresentations().addAll((Collection<? extends BorderedRepresentation>) newValue);
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
        case MigrationmodelerPackage.BORDERED__BORDERED_REPRESENTATIONS:
            getBorderedRepresentations().clear();
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
        case MigrationmodelerPackage.BORDERED__BORDERED_REPRESENTATIONS:
            return borderedRepresentations != null && !borderedRepresentations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // BorderedImpl
