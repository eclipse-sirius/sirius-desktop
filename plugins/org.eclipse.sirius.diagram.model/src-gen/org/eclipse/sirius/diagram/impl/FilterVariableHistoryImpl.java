/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FilterVariableHistory;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.viewpoint.impl.IdentifiedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Filter Variable History</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl#getOwnedValues <em>Owned Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FilterVariableHistoryImpl extends IdentifiedElementImpl implements FilterVariableHistory {
    /**
     * The cached value of the '{@link #getOwnedValues() <em>Owned Values</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedValues()
     * @generated
     * @ordered
     */
    protected EList<VariableValue> ownedValues;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FilterVariableHistoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.FILTER_VARIABLE_HISTORY;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<VariableValue> getOwnedValues() {
        if (ownedValues == null) {
            ownedValues = new EObjectContainmentEList.Resolving<>(VariableValue.class, this, DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES);
        }
        return ownedValues;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES:
            return ((InternalEList<?>) getOwnedValues()).basicRemove(otherEnd, msgs);
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
        case DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES:
            return getOwnedValues();
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
        case DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES:
            getOwnedValues().clear();
            getOwnedValues().addAll((Collection<? extends VariableValue>) newValue);
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
        case DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES:
            getOwnedValues().clear();
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
        case DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES:
            return ownedValues != null && !ownedValues.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // FilterVariableHistoryImpl
