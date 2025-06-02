/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Participant;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Participant</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.sample.interactions.impl.ParticipantImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ParticipantImpl extends AbstractEndContextImpl implements Participant {
    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected EClass type;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    protected ParticipantImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.PARTICIPANT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EClass getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject) type;
            type = (EClass) eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.PARTICIPANT__TYPE, oldType, type));
            }
        }
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    public EClass basicGetType() {
        return type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(EClass newType) {
        EClass oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.PARTICIPANT__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.PARTICIPANT__TYPE:
            if (resolve)
                return getType();
            return basicGetType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case InteractionsPackage.PARTICIPANT__TYPE:
            setType((EClass) newValue);
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
        case InteractionsPackage.PARTICIPANT__TYPE:
            setType((EClass) null);
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
        case InteractionsPackage.PARTICIPANT__TYPE:
            return type != null;
        }
        return super.eIsSet(featureID);
    }

} // ParticipantImpl
