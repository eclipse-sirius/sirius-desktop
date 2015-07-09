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
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.ExecutionEnd;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Participant;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Execution</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.ExecutionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.ExecutionImpl#getOwner
 * <em>Owner</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.ExecutionImpl#getStart
 * <em>Start</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.ExecutionImpl#getEnd
 * <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionImpl extends EObjectImpl implements Execution {
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
    protected String name = ExecutionImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwner() <em>Owner</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwner()
     * @generated
     * @ordered
     */
    protected Participant owner;

    /**
     * The cached value of the '{@link #getStart() <em>Start</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStart()
     * @generated
     * @ordered
     */
    protected ExecutionEnd start;

    /**
     * The cached value of the '{@link #getEnd() <em>End</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEnd()
     * @generated
     * @ordered
     */
    protected ExecutionEnd end;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ExecutionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.EXECUTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.EXECUTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Participant getOwner() {
        if (owner != null && owner.eIsProxy()) {
            InternalEObject oldOwner = (InternalEObject) owner;
            owner = (Participant) eResolveProxy(oldOwner);
            if (owner != oldOwner) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.EXECUTION__OWNER, oldOwner, owner));
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
    public Participant basicGetOwner() {
        return owner;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOwner(Participant newOwner) {
        Participant oldOwner = owner;
        owner = newOwner;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.EXECUTION__OWNER, oldOwner, owner));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionEnd getStart() {
        if (start != null && start.eIsProxy()) {
            InternalEObject oldStart = (InternalEObject) start;
            start = (ExecutionEnd) eResolveProxy(oldStart);
            if (start != oldStart) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.EXECUTION__START, oldStart, start));
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
    public ExecutionEnd basicGetStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStart(ExecutionEnd newStart) {
        ExecutionEnd oldStart = start;
        start = newStart;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.EXECUTION__START, oldStart, start));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionEnd getEnd() {
        if (end != null && end.eIsProxy()) {
            InternalEObject oldEnd = (InternalEObject) end;
            end = (ExecutionEnd) eResolveProxy(oldEnd);
            if (end != oldEnd) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.EXECUTION__END, oldEnd, end));
                }
            }
        }
        return end;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ExecutionEnd basicGetEnd() {
        return end;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEnd(ExecutionEnd newEnd) {
        ExecutionEnd oldEnd = end;
        end = newEnd;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.EXECUTION__END, oldEnd, end));
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
        case InteractionsPackage.EXECUTION__NAME:
            return getName();
        case InteractionsPackage.EXECUTION__OWNER:
            if (resolve) {
                return getOwner();
            }
            return basicGetOwner();
        case InteractionsPackage.EXECUTION__START:
            if (resolve) {
                return getStart();
            }
            return basicGetStart();
        case InteractionsPackage.EXECUTION__END:
            if (resolve) {
                return getEnd();
            }
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
        case InteractionsPackage.EXECUTION__NAME:
            setName((String) newValue);
            return;
        case InteractionsPackage.EXECUTION__OWNER:
            setOwner((Participant) newValue);
            return;
        case InteractionsPackage.EXECUTION__START:
            setStart((ExecutionEnd) newValue);
            return;
        case InteractionsPackage.EXECUTION__END:
            setEnd((ExecutionEnd) newValue);
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
        case InteractionsPackage.EXECUTION__NAME:
            setName(ExecutionImpl.NAME_EDEFAULT);
            return;
        case InteractionsPackage.EXECUTION__OWNER:
            setOwner((Participant) null);
            return;
        case InteractionsPackage.EXECUTION__START:
            setStart((ExecutionEnd) null);
            return;
        case InteractionsPackage.EXECUTION__END:
            setEnd((ExecutionEnd) null);
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
        case InteractionsPackage.EXECUTION__NAME:
            return ExecutionImpl.NAME_EDEFAULT == null ? name != null : !ExecutionImpl.NAME_EDEFAULT.equals(name);
        case InteractionsPackage.EXECUTION__OWNER:
            return owner != null;
        case InteractionsPackage.EXECUTION__START:
            return start != null;
        case InteractionsPackage.EXECUTION__END:
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
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // ExecutionImpl
