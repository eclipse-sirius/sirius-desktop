/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Message Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.MessageMappingImpl#getSendingEndFinderExpression
 * <em>Sending End Finder Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.MessageMappingImpl#getReceivingEndFinderExpression
 * <em>Receiving End Finder Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MessageMappingImpl extends EdgeMappingImpl implements MessageMapping {
    /**
     * The default value of the '{@link #getSendingEndFinderExpression()
     * <em>Sending End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSendingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String SENDING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSendingEndFinderExpression()
     * <em>Sending End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getSendingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String sendingEndFinderExpression = MessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getReceivingEndFinderExpression()
     * <em>Receiving End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getReceivingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String RECEIVING_END_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getReceivingEndFinderExpression()
     * <em>Receiving End Finder Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getReceivingEndFinderExpression()
     * @generated
     * @ordered
     */
    protected String receivingEndFinderExpression = MessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.MESSAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getSendingEndFinderExpression() {
        return sendingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSendingEndFinderExpression(String newSendingEndFinderExpression) {
        String oldSendingEndFinderExpression = sendingEndFinderExpression;
        sendingEndFinderExpression = newSendingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION, oldSendingEndFinderExpression, sendingEndFinderExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getReceivingEndFinderExpression() {
        return receivingEndFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setReceivingEndFinderExpression(String newReceivingEndFinderExpression) {
        String oldReceivingEndFinderExpression = receivingEndFinderExpression;
        receivingEndFinderExpression = newReceivingEndFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION, oldReceivingEndFinderExpression, receivingEndFinderExpression));
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
        case DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            return getSendingEndFinderExpression();
        case DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            return getReceivingEndFinderExpression();
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
        case DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            setSendingEndFinderExpression((String) newValue);
            return;
        case DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            setReceivingEndFinderExpression((String) newValue);
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
        case DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            setSendingEndFinderExpression(MessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            setReceivingEndFinderExpression(MessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION:
            return MessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT == null ? sendingEndFinderExpression != null : !MessageMappingImpl.SENDING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(sendingEndFinderExpression);
        case DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION:
            return MessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT == null ? receivingEndFinderExpression != null : !MessageMappingImpl.RECEIVING_END_FINDER_EXPRESSION_EDEFAULT
                    .equals(receivingEndFinderExpression);
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
        result.append(" (sendingEndFinderExpression: "); //$NON-NLS-1$
        result.append(sendingEndFinderExpression);
        result.append(", receivingEndFinderExpression: "); //$NON-NLS-1$
        result.append(receivingEndFinderExpression);
        result.append(')');
        return result.toString();
    }

} // MessageMappingImpl
