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
import org.eclipse.sirius.viewpoint.description.tool.MoveElement;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Move Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MoveElementImpl#getNewContainerExpression
 * <em>New Container Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MoveElementImpl#getFeatureName
 * <em>Feature Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MoveElementImpl extends ContainerModelOperationImpl implements MoveElement {
    /**
     * The default value of the '{@link #getNewContainerExpression()
     * <em>New Container Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getNewContainerExpression()
     * @generated
     * @ordered
     */
    protected static final String NEW_CONTAINER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getNewContainerExpression()
     * <em>New Container Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getNewContainerExpression()
     * @generated
     * @ordered
     */
    protected String newContainerExpression = MoveElementImpl.NEW_CONTAINER_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFeatureName() <em>Feature Name</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected static final String FEATURE_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFeatureName() <em>Feature Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFeatureName()
     * @generated
     * @ordered
     */
    protected String featureName = MoveElementImpl.FEATURE_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MoveElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.MOVE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getNewContainerExpression() {
        return newContainerExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setNewContainerExpression(String newNewContainerExpression) {
        String oldNewContainerExpression = newContainerExpression;
        newContainerExpression = newNewContainerExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION, oldNewContainerExpression, newContainerExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFeatureName() {
        return featureName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFeatureName(String newFeatureName) {
        String oldFeatureName = featureName;
        featureName = newFeatureName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.MOVE_ELEMENT__FEATURE_NAME, oldFeatureName, featureName));
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
        case ToolPackage.MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION:
            return getNewContainerExpression();
        case ToolPackage.MOVE_ELEMENT__FEATURE_NAME:
            return getFeatureName();
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
        case ToolPackage.MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION:
            setNewContainerExpression((String) newValue);
            return;
        case ToolPackage.MOVE_ELEMENT__FEATURE_NAME:
            setFeatureName((String) newValue);
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
        case ToolPackage.MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION:
            setNewContainerExpression(MoveElementImpl.NEW_CONTAINER_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.MOVE_ELEMENT__FEATURE_NAME:
            setFeatureName(MoveElementImpl.FEATURE_NAME_EDEFAULT);
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
        case ToolPackage.MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION:
            return MoveElementImpl.NEW_CONTAINER_EXPRESSION_EDEFAULT == null ? newContainerExpression != null : !MoveElementImpl.NEW_CONTAINER_EXPRESSION_EDEFAULT.equals(newContainerExpression);
        case ToolPackage.MOVE_ELEMENT__FEATURE_NAME:
            return MoveElementImpl.FEATURE_NAME_EDEFAULT == null ? featureName != null : !MoveElementImpl.FEATURE_NAME_EDEFAULT.equals(featureName);
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
        result.append(" (newContainerExpression: "); //$NON-NLS-1$
        result.append(newContainerExpression);
        result.append(", featureName: "); //$NON-NLS-1$
        result.append(featureName);
        result.append(')');
        return result.toString();
    }

} // MoveElementImpl
