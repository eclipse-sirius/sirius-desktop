/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.description.contribution.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.contribution.ContributionPoint;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Point</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionPointImpl#getOrigin
 * <em>Origin</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.contribution.impl.ContributionPointImpl#getContributed
 * <em>Contributed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionPointImpl extends EObjectImpl implements ContributionPoint {
    /**
     * The default value of the '{@link #getOrigin() <em>Origin</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOrigin()
     * @generated
     * @ordered
     */
    protected static final String ORIGIN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getOrigin() <em>Origin</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOrigin()
     * @generated
     * @ordered
     */
    protected String origin = ContributionPointImpl.ORIGIN_EDEFAULT;

    /**
     * The cached value of the '{@link #getContributed() <em>Contributed</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getContributed()
     * @generated
     * @ordered
     */
    protected EObject contributed;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContributionPointImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ContributionPackage.Literals.CONTRIBUTION_POINT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getOrigin() {
        return origin;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOrigin(String newOrigin) {
        String oldOrigin = origin;
        origin = newOrigin;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION_POINT__ORIGIN, oldOrigin, origin));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getContributed() {
        if (contributed != null && contributed.eIsProxy()) {
            InternalEObject oldContributed = (InternalEObject) contributed;
            contributed = eResolveProxy(oldContributed);
            if (contributed != oldContributed) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED, oldContributed, contributed));
                }
            }
        }
        return contributed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetContributed() {
        return contributed;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setContributed(EObject newContributed) {
        EObject oldContributed = contributed;
        contributed = newContributed;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED, oldContributed, contributed));
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
        case ContributionPackage.CONTRIBUTION_POINT__ORIGIN:
            return getOrigin();
        case ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED:
            if (resolve) {
                return getContributed();
            }
            return basicGetContributed();
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
        case ContributionPackage.CONTRIBUTION_POINT__ORIGIN:
            setOrigin((String) newValue);
            return;
        case ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED:
            setContributed((EObject) newValue);
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
        case ContributionPackage.CONTRIBUTION_POINT__ORIGIN:
            setOrigin(ContributionPointImpl.ORIGIN_EDEFAULT);
            return;
        case ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED:
            setContributed((EObject) null);
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
        case ContributionPackage.CONTRIBUTION_POINT__ORIGIN:
            return ContributionPointImpl.ORIGIN_EDEFAULT == null ? origin != null : !ContributionPointImpl.ORIGIN_EDEFAULT.equals(origin);
        case ContributionPackage.CONTRIBUTION_POINT__CONTRIBUTED:
            return contributed != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (origin: "); //$NON-NLS-1$
        result.append(origin);
        result.append(')');
        return result.toString();
    }

} // ContributionPointImpl
