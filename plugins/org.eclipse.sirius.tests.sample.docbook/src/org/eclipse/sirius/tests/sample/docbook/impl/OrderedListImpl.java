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
package org.eclipse.sirius.tests.sample.docbook.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.docbook.DocbookPackage;
import org.eclipse.sirius.tests.sample.docbook.OrderedList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ordered List</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.docbook.impl.OrderedListImpl#getNumeration
 * <em>Numeration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OrderedListImpl extends EObjectImpl implements OrderedList {
    /**
     * The default value of the '{@link #getNumeration() <em>Numeration</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNumeration()
     * @generated
     * @ordered
     */
    protected static final String NUMERATION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNumeration() <em>Numeration</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNumeration()
     * @generated
     * @ordered
     */
    protected String numeration = OrderedListImpl.NUMERATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected OrderedListImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DocbookPackage.Literals.ORDERED_LIST;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getNumeration() {
        return numeration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNumeration(String newNumeration) {
        String oldNumeration = numeration;
        numeration = newNumeration;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DocbookPackage.ORDERED_LIST__NUMERATION, oldNumeration, numeration));
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
        case DocbookPackage.ORDERED_LIST__NUMERATION:
            return getNumeration();
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
        case DocbookPackage.ORDERED_LIST__NUMERATION:
            setNumeration((String) newValue);
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
        case DocbookPackage.ORDERED_LIST__NUMERATION:
            setNumeration(OrderedListImpl.NUMERATION_EDEFAULT);
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
        case DocbookPackage.ORDERED_LIST__NUMERATION:
            return OrderedListImpl.NUMERATION_EDEFAULT == null ? numeration != null : !OrderedListImpl.NUMERATION_EDEFAULT.equals(numeration);
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
        result.append(" (numeration: "); //$NON-NLS-1$
        result.append(numeration);
        result.append(')');
        return result.toString();
    }

} // OrderedListImpl
