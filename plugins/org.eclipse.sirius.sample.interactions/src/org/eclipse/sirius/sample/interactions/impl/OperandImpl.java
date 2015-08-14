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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.OperandEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Operand</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.OperandImpl#getName
 * <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.OperandImpl#getStart
 * <em>Start</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperandImpl extends EObjectImpl implements Operand {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = OperandImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getStart() <em>Start</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStart()
     * @generated
     * @ordered
     */
    protected OperandEnd start;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected OperandImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.OPERAND;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.OPERAND__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperandEnd getStart() {
        if (start != null && start.eIsProxy()) {
            InternalEObject oldStart = (InternalEObject) start;
            start = (OperandEnd) eResolveProxy(oldStart);
            if (start != oldStart) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.OPERAND__START, oldStart, start));
                }
            }
        }
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public OperandEnd basicGetStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStart(OperandEnd newStart) {
        OperandEnd oldStart = start;
        start = newStart;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.OPERAND__START, oldStart, start));
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
        case InteractionsPackage.OPERAND__NAME:
            return getName();
        case InteractionsPackage.OPERAND__START:
            if (resolve) {
                return getStart();
            }
            return basicGetStart();
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
        case InteractionsPackage.OPERAND__NAME:
            setName((String) newValue);
            return;
        case InteractionsPackage.OPERAND__START:
            setStart((OperandEnd) newValue);
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
        case InteractionsPackage.OPERAND__NAME:
            setName(OperandImpl.NAME_EDEFAULT);
            return;
        case InteractionsPackage.OPERAND__START:
            setStart((OperandEnd) null);
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
        case InteractionsPackage.OPERAND__NAME:
            return OperandImpl.NAME_EDEFAULT == null ? name != null : !OperandImpl.NAME_EDEFAULT.equals(name);
        case InteractionsPackage.OPERAND__START:
            return start != null;
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // OperandImpl
