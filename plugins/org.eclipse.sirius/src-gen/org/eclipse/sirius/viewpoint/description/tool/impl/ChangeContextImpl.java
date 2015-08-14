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
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Change Context</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ChangeContextImpl#getBrowseExpression
 * <em>Browse Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChangeContextImpl extends ContainerModelOperationImpl implements ChangeContext {
    /**
     * The default value of the '{@link #getBrowseExpression()
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBrowseExpression()
     * @generated
     * @ordered
     */
    protected static final String BROWSE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBrowseExpression()
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBrowseExpression()
     * @generated
     * @ordered
     */
    protected String browseExpression = ChangeContextImpl.BROWSE_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ChangeContextImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.CHANGE_CONTEXT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getBrowseExpression() {
        return browseExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBrowseExpression(String newBrowseExpression) {
        String oldBrowseExpression = browseExpression;
        browseExpression = newBrowseExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION, oldBrowseExpression, browseExpression));
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
        case ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION:
            return getBrowseExpression();
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
        case ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION:
            setBrowseExpression((String) newValue);
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
        case ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION:
            setBrowseExpression(ChangeContextImpl.BROWSE_EXPRESSION_EDEFAULT);
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
        case ToolPackage.CHANGE_CONTEXT__BROWSE_EXPRESSION:
            return ChangeContextImpl.BROWSE_EXPRESSION_EDEFAULT == null ? browseExpression != null : !ChangeContextImpl.BROWSE_EXPRESSION_EDEFAULT.equals(browseExpression);
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
        result.append(" (browseExpression: "); //$NON-NLS-1$
        result.append(browseExpression);
        result.append(')');
        return result.toString();
    }

} // ChangeContextImpl
