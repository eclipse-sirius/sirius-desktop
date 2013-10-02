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
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.Unset;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Unset</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.UnsetImpl#getFeatureName
 * <em>Feature Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.UnsetImpl#getElementExpression
 * <em>Element Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class UnsetImpl extends ContainerModelOperationImpl implements Unset {
    /**
     * The default value of the '{@link #getFeatureName() <em>Feature Name</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_NAME_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getFeatureName() <em>Feature Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected String featureName = FEATURE_NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getElementExpression()
     * <em>Element Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementExpression()
     * @generated
     * @ordered
     */
    protected static final String ELEMENT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getElementExpression()
     * <em>Element Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementExpression()
     * @generated
     * @ordered
     */
    protected String elementExpression = ELEMENT_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected UnsetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.UNSET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setFeatureName(String newFeatureName) {
        String oldFeatureName = featureName;
        featureName = newFeatureName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.UNSET__FEATURE_NAME, oldFeatureName, featureName));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getElementExpression() {
        return elementExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setElementExpression(String newElementExpression) {
        String oldElementExpression = elementExpression;
        elementExpression = newElementExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.UNSET__ELEMENT_EXPRESSION, oldElementExpression, elementExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ToolPackage.UNSET__FEATURE_NAME:
            return getFeatureName();
        case ToolPackage.UNSET__ELEMENT_EXPRESSION:
            return getElementExpression();
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
        case ToolPackage.UNSET__FEATURE_NAME:
            setFeatureName((String) newValue);
            return;
        case ToolPackage.UNSET__ELEMENT_EXPRESSION:
            setElementExpression((String) newValue);
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
        case ToolPackage.UNSET__FEATURE_NAME:
            setFeatureName(FEATURE_NAME_EDEFAULT);
            return;
        case ToolPackage.UNSET__ELEMENT_EXPRESSION:
            setElementExpression(ELEMENT_EXPRESSION_EDEFAULT);
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
        case ToolPackage.UNSET__FEATURE_NAME:
            return FEATURE_NAME_EDEFAULT == null ? featureName != null : !FEATURE_NAME_EDEFAULT.equals(featureName);
        case ToolPackage.UNSET__ELEMENT_EXPRESSION:
            return ELEMENT_EXPRESSION_EDEFAULT == null ? elementExpression != null : !ELEMENT_EXPRESSION_EDEFAULT.equals(elementExpression);
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (featureName: ");
        result.append(featureName);
        result.append(", elementExpression: ");
        result.append(elementExpression);
        result.append(')');
        return result.toString();
    }

} // UnsetImpl
