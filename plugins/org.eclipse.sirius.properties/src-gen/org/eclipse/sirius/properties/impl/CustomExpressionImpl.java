/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Custom Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.CustomExpressionImpl#getIdentifier
 * <em>Identifier</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.CustomExpressionImpl#getCustomExpression
 * <em>Custom Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomExpressionImpl extends MinimalEObjectImpl.Container implements CustomExpression {
    /**
     * The default value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected static final String IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected String identifier = CustomExpressionImpl.IDENTIFIER_EDEFAULT;

    /**
     * The default value of the '{@link #getCustomExpression()
     * <em>Custom Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getCustomExpression()
     * @generated
     * @ordered
     */
    protected static final String CUSTOM_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCustomExpression()
     * <em>Custom Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getCustomExpression()
     * @generated
     * @ordered
     */
    protected String customExpression = CustomExpressionImpl.CUSTOM_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CustomExpressionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CUSTOM_EXPRESSION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = identifier;
        identifier = newIdentifier;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CUSTOM_EXPRESSION__IDENTIFIER, oldIdentifier, identifier));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCustomExpression() {
        return customExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCustomExpression(String newCustomExpression) {
        String oldCustomExpression = customExpression;
        customExpression = newCustomExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION, oldCustomExpression, customExpression));
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
        case PropertiesPackage.CUSTOM_EXPRESSION__IDENTIFIER:
            return getIdentifier();
        case PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION:
            return getCustomExpression();
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
        case PropertiesPackage.CUSTOM_EXPRESSION__IDENTIFIER:
            setIdentifier((String) newValue);
            return;
        case PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION:
            setCustomExpression((String) newValue);
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
        case PropertiesPackage.CUSTOM_EXPRESSION__IDENTIFIER:
            setIdentifier(CustomExpressionImpl.IDENTIFIER_EDEFAULT);
            return;
        case PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION:
            setCustomExpression(CustomExpressionImpl.CUSTOM_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.CUSTOM_EXPRESSION__IDENTIFIER:
            return CustomExpressionImpl.IDENTIFIER_EDEFAULT == null ? identifier != null : !CustomExpressionImpl.IDENTIFIER_EDEFAULT.equals(identifier);
        case PropertiesPackage.CUSTOM_EXPRESSION__CUSTOM_EXPRESSION:
            return CustomExpressionImpl.CUSTOM_EXPRESSION_EDEFAULT == null ? customExpression != null : !CustomExpressionImpl.CUSTOM_EXPRESSION_EDEFAULT.equals(customExpression);
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
        result.append(" (identifier: "); //$NON-NLS-1$
        result.append(identifier);
        result.append(", customExpression: "); //$NON-NLS-1$
        result.append(customExpression);
        result.append(')');
        return result.toString();
    }

} // CustomExpressionImpl
