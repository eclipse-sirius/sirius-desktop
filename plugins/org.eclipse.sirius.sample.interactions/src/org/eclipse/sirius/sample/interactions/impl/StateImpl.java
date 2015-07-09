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
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.sample.interactions.State;
import org.eclipse.sirius.sample.interactions.StateEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.StateImpl#getName <em>
 * Name</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.StateImpl#getOwner
 * <em>Owner</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.StateImpl#getStart
 * <em>Start</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.StateImpl#getEnd <em>
 * End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateImpl extends EObjectImpl implements State {
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
    protected String name = StateImpl.NAME_EDEFAULT;

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
    protected StateEnd start;

    /**
     * The cached value of the '{@link #getEnd() <em>End</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getEnd()
     * @generated
     * @ordered
     */
    protected StateEnd end;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected StateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.STATE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.STATE__NAME, oldName, name));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.STATE__OWNER, oldOwner, owner));
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
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.STATE__OWNER, oldOwner, owner));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StateEnd getStart() {
        if (start != null && start.eIsProxy()) {
            InternalEObject oldStart = (InternalEObject) start;
            start = (StateEnd) eResolveProxy(oldStart);
            if (start != oldStart) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.STATE__START, oldStart, start));
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
    public StateEnd basicGetStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStart(StateEnd newStart) {
        StateEnd oldStart = start;
        start = newStart;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.STATE__START, oldStart, start));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StateEnd getEnd() {
        if (end != null && end.eIsProxy()) {
            InternalEObject oldEnd = (InternalEObject) end;
            end = (StateEnd) eResolveProxy(oldEnd);
            if (end != oldEnd) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.STATE__END, oldEnd, end));
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
    public StateEnd basicGetEnd() {
        return end;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEnd(StateEnd newEnd) {
        StateEnd oldEnd = end;
        end = newEnd;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.STATE__END, oldEnd, end));
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
        case InteractionsPackage.STATE__NAME:
            return getName();
        case InteractionsPackage.STATE__OWNER:
            if (resolve) {
                return getOwner();
            }
            return basicGetOwner();
        case InteractionsPackage.STATE__START:
            if (resolve) {
                return getStart();
            }
            return basicGetStart();
        case InteractionsPackage.STATE__END:
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
        case InteractionsPackage.STATE__NAME:
            setName((String) newValue);
            return;
        case InteractionsPackage.STATE__OWNER:
            setOwner((Participant) newValue);
            return;
        case InteractionsPackage.STATE__START:
            setStart((StateEnd) newValue);
            return;
        case InteractionsPackage.STATE__END:
            setEnd((StateEnd) newValue);
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
        case InteractionsPackage.STATE__NAME:
            setName(StateImpl.NAME_EDEFAULT);
            return;
        case InteractionsPackage.STATE__OWNER:
            setOwner((Participant) null);
            return;
        case InteractionsPackage.STATE__START:
            setStart((StateEnd) null);
            return;
        case InteractionsPackage.STATE__END:
            setEnd((StateEnd) null);
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
        case InteractionsPackage.STATE__NAME:
            return StateImpl.NAME_EDEFAULT == null ? name != null : !StateImpl.NAME_EDEFAULT.equals(name);
        case InteractionsPackage.STATE__OWNER:
            return owner != null;
        case InteractionsPackage.STATE__START:
            return start != null;
        case InteractionsPackage.STATE__END:
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

} // StateImpl
