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
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Return Message Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.description.impl.ReturnMessageMappingImpl#getInvocationMessageFinderExpression
 * <em>Invocation Message Finder Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReturnMessageMappingImpl extends MessageMappingImpl implements ReturnMessageMapping {
    /**
     * The default value of the '{@link #getInvocationMessageFinderExpression()
     * <em>Invocation Message Finder Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInvocationMessageFinderExpression()
     * @generated
     * @ordered
     */
    protected static final String INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getInvocationMessageFinderExpression()
     * <em>Invocation Message Finder Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInvocationMessageFinderExpression()
     * @generated
     * @ordered
     */
    protected String invocationMessageFinderExpression = ReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ReturnMessageMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.RETURN_MESSAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getInvocationMessageFinderExpression() {
        return invocationMessageFinderExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInvocationMessageFinderExpression(String newInvocationMessageFinderExpression) {
        String oldInvocationMessageFinderExpression = invocationMessageFinderExpression;
        invocationMessageFinderExpression = newInvocationMessageFinderExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION, oldInvocationMessageFinderExpression,
                    invocationMessageFinderExpression));
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
        case DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            return getInvocationMessageFinderExpression();
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
        case DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            setInvocationMessageFinderExpression((String) newValue);
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
        case DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            setInvocationMessageFinderExpression(ReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT);
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
        case DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION:
            return ReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT == null ? invocationMessageFinderExpression != null
                    : !ReturnMessageMappingImpl.INVOCATION_MESSAGE_FINDER_EXPRESSION_EDEFAULT.equals(invocationMessageFinderExpression);
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
        result.append(" (invocationMessageFinderExpression: "); //$NON-NLS-1$
        result.append(invocationMessageFinderExpression);
        result.append(')');
        return result.toString();
    }

} // ReturnMessageMappingImpl
