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
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.MessageEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Message End</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.impl.MessageEndImpl#getMessage <em>Message</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MessageEndImpl extends AbstractEndImpl implements MessageEnd {
    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected Message message;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MessageEndImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.MESSAGE_END;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Message getMessage() {
        if (message != null && message.eIsProxy()) {
            InternalEObject oldMessage = (InternalEObject) message;
            message = (Message) eResolveProxy(oldMessage);
            if (message != oldMessage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.MESSAGE_END__MESSAGE, oldMessage, message));
            }
        }
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Message basicGetMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMessage(Message newMessage) {
        Message oldMessage = message;
        message = newMessage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.MESSAGE_END__MESSAGE, oldMessage, message));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case InteractionsPackage.MESSAGE_END__MESSAGE:
            if (resolve)
                return getMessage();
            return basicGetMessage();
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
        case InteractionsPackage.MESSAGE_END__MESSAGE:
            setMessage((Message) newValue);
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
        case InteractionsPackage.MESSAGE_END__MESSAGE:
            setMessage((Message) null);
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
        case InteractionsPackage.MESSAGE_END__MESSAGE:
            return message != null;
        }
        return super.eIsSet(featureID);
    }

} // MessageEndImpl
