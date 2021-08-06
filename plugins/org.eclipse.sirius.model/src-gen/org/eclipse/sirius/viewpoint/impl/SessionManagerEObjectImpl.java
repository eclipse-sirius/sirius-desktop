/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Session Manager EObject</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.SessionManagerEObjectImpl#getOwnedSessions <em>Owned Sessions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SessionManagerEObjectImpl extends MinimalEObjectImpl.Container implements SessionManagerEObject {
    /**
     * The cached value of the '{@link #getOwnedSessions() <em>Owned Sessions</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedSessions()
     * @generated
     * @ordered
     */
    protected EList<DAnalysisSessionEObject> ownedSessions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SessionManagerEObjectImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.SESSION_MANAGER_EOBJECT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DAnalysisSessionEObject> getOwnedSessions() {
        if (ownedSessions == null) {
            ownedSessions = new EObjectContainmentEList.Resolving<DAnalysisSessionEObject>(DAnalysisSessionEObject.class, this, ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS);
        }
        return ownedSessions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS:
            return ((InternalEList<?>) getOwnedSessions()).basicRemove(otherEnd, msgs);
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
        case ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS:
            return getOwnedSessions();
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
        case ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS:
            getOwnedSessions().clear();
            getOwnedSessions().addAll((Collection<? extends DAnalysisSessionEObject>) newValue);
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
        case ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS:
            getOwnedSessions().clear();
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
        case ViewpointPackage.SESSION_MANAGER_EOBJECT__OWNED_SESSIONS:
            return ownedSessions != null && !ownedSessions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // SessionManagerEObjectImpl
