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
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.Message;
import org.eclipse.sirius.sample.interactions.ReturnMessage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Return Message</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.impl.ReturnMessageImpl#getInvocationMessage
 * <em>Invocation Message</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReturnMessageImpl extends MessageImpl implements ReturnMessage {
    /**
     * The cached value of the '{@link #getInvocationMessage()
     * <em>Invocation Message</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getInvocationMessage()
     * @generated
     * @ordered
     */
    protected Message invocationMessage;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ReturnMessageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InteractionsPackage.Literals.RETURN_MESSAGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Message getInvocationMessage() {
        if (invocationMessage != null && invocationMessage.eIsProxy()) {
            InternalEObject oldInvocationMessage = (InternalEObject) invocationMessage;
            invocationMessage = (Message) eResolveProxy(oldInvocationMessage);
            if (invocationMessage != oldInvocationMessage) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InteractionsPackage.RETURN_MESSAGE__INVOCATION_MESSAGE, oldInvocationMessage, invocationMessage));
                }
            }
        }
        return invocationMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Message basicGetInvocationMessage() {
        return invocationMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInvocationMessage(Message newInvocationMessage) {
        Message oldInvocationMessage = invocationMessage;
        invocationMessage = newInvocationMessage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, InteractionsPackage.RETURN_MESSAGE__INVOCATION_MESSAGE, oldInvocationMessage, invocationMessage));
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
        case InteractionsPackage.RETURN_MESSAGE__INVOCATION_MESSAGE:
            if (resolve) {
                return getInvocationMessage();
            }
            return basicGetInvocationMessage();
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
        case InteractionsPackage.RETURN_MESSAGE__INVOCATION_MESSAGE:
            setInvocationMessage((Message) newValue);
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
        case InteractionsPackage.RETURN_MESSAGE__INVOCATION_MESSAGE:
            setInvocationMessage((Message) null);
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
        case InteractionsPackage.RETURN_MESSAGE__INVOCATION_MESSAGE:
            return invocationMessage != null;
        }
        return super.eIsSet(featureID);
    }

} // ReturnMessageImpl
