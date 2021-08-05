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
package org.eclipse.sirius.diagram.description.concern.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.concern.ConcernDescription;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernSet;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.concern.impl.ConcernSetImpl#getOwnedConcernDescriptions <em>Owned
 * Concern Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConcernSetImpl extends DocumentedElementImpl implements ConcernSet {
    /**
     * The cached value of the '{@link #getOwnedConcernDescriptions() <em>Owned Concern Descriptions</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedConcernDescriptions()
     * @generated
     * @ordered
     */
    protected EList<ConcernDescription> ownedConcernDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ConcernSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ConcernPackage.Literals.CONCERN_SET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ConcernDescription> getOwnedConcernDescriptions() {
        if (ownedConcernDescriptions == null) {
            ownedConcernDescriptions = new EObjectContainmentEList.Resolving<>(ConcernDescription.class, this, ConcernPackage.CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS);
        }
        return ownedConcernDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ConcernPackage.CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS:
            return ((InternalEList<?>) getOwnedConcernDescriptions()).basicRemove(otherEnd, msgs);
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
        case ConcernPackage.CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS:
            return getOwnedConcernDescriptions();
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
        case ConcernPackage.CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS:
            getOwnedConcernDescriptions().clear();
            getOwnedConcernDescriptions().addAll((Collection<? extends ConcernDescription>) newValue);
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
        case ConcernPackage.CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS:
            getOwnedConcernDescriptions().clear();
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
        case ConcernPackage.CONCERN_SET__OWNED_CONCERN_DESCRIPTIONS:
            return ownedConcernDescriptions != null && !ownedConcernDescriptions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ConcernSetImpl
