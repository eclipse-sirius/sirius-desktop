/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.tool.For;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>For</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ForImpl#getExpression
 * <em>Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ForImpl#getIteratorName
 * <em>Iterator Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForImpl extends ContainerModelOperationImpl implements For {
    /**
     * The default value of the '{@link #getExpression() <em>Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected static final String EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getExpression() <em>Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExpression()
     * @generated
     * @ordered
     */
    protected String expression = ForImpl.EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getIteratorName()
     * <em>Iterator Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getIteratorName()
     * @generated
     * @ordered
     */
    protected static final String ITERATOR_NAME_EDEFAULT = "i"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIteratorName()
     * <em>Iterator Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getIteratorName()
     * @generated
     * @ordered
     */
    protected String iteratorName = ForImpl.ITERATOR_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ForImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.FOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getExpression() {
        return expression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setExpression(String newExpression) {
        String oldExpression = expression;
        expression = newExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.FOR__EXPRESSION, oldExpression, expression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIteratorName() {
        return iteratorName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIteratorName(String newIteratorName) {
        String oldIteratorName = iteratorName;
        iteratorName = newIteratorName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.FOR__ITERATOR_NAME, oldIteratorName, iteratorName));
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
        case ToolPackage.FOR__EXPRESSION:
            return getExpression();
        case ToolPackage.FOR__ITERATOR_NAME:
            return getIteratorName();
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
        case ToolPackage.FOR__EXPRESSION:
            setExpression((String) newValue);
            return;
        case ToolPackage.FOR__ITERATOR_NAME:
            setIteratorName((String) newValue);
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
        case ToolPackage.FOR__EXPRESSION:
            setExpression(ForImpl.EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.FOR__ITERATOR_NAME:
            setIteratorName(ForImpl.ITERATOR_NAME_EDEFAULT);
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
        case ToolPackage.FOR__EXPRESSION:
            return ForImpl.EXPRESSION_EDEFAULT == null ? expression != null : !ForImpl.EXPRESSION_EDEFAULT.equals(expression);
        case ToolPackage.FOR__ITERATOR_NAME:
            return ForImpl.ITERATOR_NAME_EDEFAULT == null ? iteratorName != null : !ForImpl.ITERATOR_NAME_EDEFAULT.equals(iteratorName);
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
        result.append(" (expression: "); //$NON-NLS-1$
        result.append(expression);
        result.append(", iteratorName: "); //$NON-NLS-1$
        result.append(iteratorName);
        result.append(')');
        return result.toString();
    }

} // ForImpl
