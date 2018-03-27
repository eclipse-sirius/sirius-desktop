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
import org.eclipse.sirius.properties.CustomExpression;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Custom Expression</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.CustomExpressionImpl#getDocumentation <em>Documentation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.CustomExpressionImpl#getCustomExpression <em>Custom Expression</em>}
 * </li>
 * </ul>
 *
 * @generated
 */
public class CustomExpressionImpl extends IdentifiedElementImpl implements CustomExpression {
    /**
     * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getDocumentation()
     * @generated
     * @ordered
     */
    protected String documentation = CustomExpressionImpl.DOCUMENTATION_EDEFAULT;

    /**
     * The default value of the '{@link #getCustomExpression() <em>Custom Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCustomExpression()
     * @generated
     * @ordered
     */
    protected static final String CUSTOM_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCustomExpression() <em>Custom Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
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
    public String getDocumentation() {
        return documentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDocumentation(String newDocumentation) {
        String oldDocumentation = documentation;
        documentation = newDocumentation;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION, oldDocumentation, documentation));
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
        case PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION:
            return getDocumentation();
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
        case PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION:
            setDocumentation((String) newValue);
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
        case PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION:
            setDocumentation(CustomExpressionImpl.DOCUMENTATION_EDEFAULT);
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
        case PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION:
            return CustomExpressionImpl.DOCUMENTATION_EDEFAULT == null ? documentation != null : !CustomExpressionImpl.DOCUMENTATION_EDEFAULT.equals(documentation);
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
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == DocumentedElement.class) {
            switch (derivedFeatureID) {
            case PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION:
                return DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == DocumentedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION:
                return PropertiesPackage.CUSTOM_EXPRESSION__DOCUMENTATION;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (documentation: "); //$NON-NLS-1$
        result.append(documentation);
        result.append(", customExpression: "); //$NON-NLS-1$
        result.append(customExpression);
        result.append(')');
        return result.toString();
    }

} // CustomExpressionImpl
