/**
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.interactions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.sample.interactions.Gate;
import org.eclipse.sirius.sample.interactions.GateEnd;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Gate</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.GateImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.GateImpl#getStart <em>Start</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.GateImpl#getEnd <em>End</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GateImpl extends EObjectImpl implements Gate {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getStart() <em>Start</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getStart()
     * @generated
     * @ordered
     */
    protected GateEnd start;

    /**
     * The cached value of the '{@link #getEnd() <em>End</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEnd()
     * @generated
     * @ordered
     */
    protected GateEnd end;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.GATE;
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
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.GATE__NAME, oldName, name));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GateEnd getStart() {
        if (start != null && start.eIsProxy()) {
            InternalEObject oldStart = (InternalEObject) start;
            start = (GateEnd) eResolveProxy(oldStart);
            if (start != oldStart) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.GATE__START, oldStart, start));
            }
        }
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public GateEnd basicGetStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStart(GateEnd newStart) {
        GateEnd oldStart = start;
        start = newStart;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.GATE__START, oldStart, start));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GateEnd getEnd() {
        if (end != null && end.eIsProxy()) {
            InternalEObject oldEnd = (InternalEObject) end;
            end = (GateEnd) eResolveProxy(oldEnd);
            if (end != oldEnd) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.GATE__END, oldEnd, end));
            }
        }
        return end;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public GateEnd basicGetEnd() {
        return end;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEnd(GateEnd newEnd) {
        GateEnd oldEnd = end;
        end = newEnd;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.GATE__END, oldEnd, end));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.GATE__NAME:
            return getName();
        case InteractionsPackage.GATE__START:
            if (resolve)
                return getStart();
            return basicGetStart();
        case InteractionsPackage.GATE__END:
            if (resolve)
                return getEnd();
            return basicGetEnd();
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
        case InteractionsPackage.GATE__NAME:
            setName((String) newValue);
            return;
        case InteractionsPackage.GATE__START:
            setStart((GateEnd) newValue);
            return;
        case InteractionsPackage.GATE__END:
            setEnd((GateEnd) newValue);
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
        case InteractionsPackage.GATE__NAME:
            setName(NAME_EDEFAULT);
            return;
        case InteractionsPackage.GATE__START:
            setStart((GateEnd) null);
            return;
        case InteractionsPackage.GATE__END:
            setEnd((GateEnd) null);
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
        case InteractionsPackage.GATE__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
        case InteractionsPackage.GATE__START:
            return start != null;
        case InteractionsPackage.GATE__END:
            return end != null;
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
        if (eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // GateImpl
