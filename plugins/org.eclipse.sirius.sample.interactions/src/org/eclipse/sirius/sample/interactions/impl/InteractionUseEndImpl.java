/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.InteractionUseEnd;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Interaction Use End</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.InteractionUseEndImpl#getOwner
 * <em>Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InteractionUseEndImpl extends AbstractEndImpl implements InteractionUseEnd {
    /**
     * The cached value of the '{@link #getOwner() <em>Owner</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwner()
     * @generated
     * @ordered
     */
    protected InteractionUse owner;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InteractionUseEndImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.INTERACTION_USE_END;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionUse getOwner() {
        if (owner != null && owner.eIsProxy()) {
            InternalEObject oldOwner = (InternalEObject) owner;
            owner = (InteractionUse) eResolveProxy(oldOwner);
            if (owner != oldOwner) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.INTERACTION_USE_END__OWNER, oldOwner, owner));
                }
            }
        }
        return owner;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public InteractionUse basicGetOwner() {
        return owner;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOwner(InteractionUse newOwner) {
        InteractionUse oldOwner = owner;
        owner = newOwner;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.INTERACTION_USE_END__OWNER, oldOwner, owner));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION_USE_END__OWNER:
            if (resolve) {
                return getOwner();
            }
            return basicGetOwner();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case InteractionsPackage.INTERACTION_USE_END__OWNER:
            setOwner((InteractionUse) newValue);
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
        case InteractionsPackage.INTERACTION_USE_END__OWNER:
            setOwner((InteractionUse) null);
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
        case InteractionsPackage.INTERACTION_USE_END__OWNER:
            return owner != null;
        }
        return super.eIsSet(featureID);
    }

} // InteractionUseEndImpl
