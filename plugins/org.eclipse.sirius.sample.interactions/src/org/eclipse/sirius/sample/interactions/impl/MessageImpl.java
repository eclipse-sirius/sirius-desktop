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
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.MessageEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Message</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.MessageImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.MessageImpl#getSendingEnd
 * <em>Sending End</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.MessageImpl#getReceivingEnd
 * <em>Receiving End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MessageImpl extends EObjectImpl implements Message {
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
    protected String name = MessageImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getSendingEnd() <em>Sending End</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSendingEnd()
     * @generated
     * @ordered
     */
    protected MessageEnd sendingEnd;

    /**
     * The cached value of the '{@link #getReceivingEnd()
     * <em>Receiving End</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getReceivingEnd()
     * @generated
     * @ordered
     */
    protected MessageEnd receivingEnd;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MessageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.MESSAGE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.MESSAGE__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEnd getSendingEnd() {
        if (sendingEnd != null && sendingEnd.eIsProxy()) {
            InternalEObject oldSendingEnd = (InternalEObject) sendingEnd;
            sendingEnd = (MessageEnd) eResolveProxy(oldSendingEnd);
            if (sendingEnd != oldSendingEnd) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.MESSAGE__SENDING_END, oldSendingEnd, sendingEnd));
                }
            }
        }
        return sendingEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MessageEnd basicGetSendingEnd() {
        return sendingEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSendingEnd(MessageEnd newSendingEnd) {
        MessageEnd oldSendingEnd = sendingEnd;
        sendingEnd = newSendingEnd;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.MESSAGE__SENDING_END, oldSendingEnd, sendingEnd));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEnd getReceivingEnd() {
        if (receivingEnd != null && receivingEnd.eIsProxy()) {
            InternalEObject oldReceivingEnd = (InternalEObject) receivingEnd;
            receivingEnd = (MessageEnd) eResolveProxy(oldReceivingEnd);
            if (receivingEnd != oldReceivingEnd) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.MESSAGE__RECEIVING_END, oldReceivingEnd, receivingEnd));
                }
            }
        }
        return receivingEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MessageEnd basicGetReceivingEnd() {
        return receivingEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setReceivingEnd(MessageEnd newReceivingEnd) {
        MessageEnd oldReceivingEnd = receivingEnd;
        receivingEnd = newReceivingEnd;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.MESSAGE__RECEIVING_END, oldReceivingEnd, receivingEnd));
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
        case InteractionsPackage.MESSAGE__NAME:
            return getName();
        case InteractionsPackage.MESSAGE__SENDING_END:
            if (resolve) {
                return getSendingEnd();
            }
            return basicGetSendingEnd();
        case InteractionsPackage.MESSAGE__RECEIVING_END:
            if (resolve) {
                return getReceivingEnd();
            }
            return basicGetReceivingEnd();
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
        case InteractionsPackage.MESSAGE__NAME:
            setName((String) newValue);
            return;
        case InteractionsPackage.MESSAGE__SENDING_END:
            setSendingEnd((MessageEnd) newValue);
            return;
        case InteractionsPackage.MESSAGE__RECEIVING_END:
            setReceivingEnd((MessageEnd) newValue);
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
        case InteractionsPackage.MESSAGE__NAME:
            setName(MessageImpl.NAME_EDEFAULT);
            return;
        case InteractionsPackage.MESSAGE__SENDING_END:
            setSendingEnd((MessageEnd) null);
            return;
        case InteractionsPackage.MESSAGE__RECEIVING_END:
            setReceivingEnd((MessageEnd) null);
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
        case InteractionsPackage.MESSAGE__NAME:
            return MessageImpl.NAME_EDEFAULT == null ? name != null : !MessageImpl.NAME_EDEFAULT.equals(name);
        case InteractionsPackage.MESSAGE__SENDING_END:
            return sendingEnd != null;
        case InteractionsPackage.MESSAGE__RECEIVING_END:
            return receivingEnd != null;
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

} // MessageImpl
