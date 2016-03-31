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
package org.eclipse.sirius.diagram.description.tool.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.tool.CreateEdgeView;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Create Edge View</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.CreateEdgeViewImpl#getSourceExpression
 * <em>Source Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.impl.CreateEdgeViewImpl#getTargetExpression
 * <em>Target Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CreateEdgeViewImpl extends CreateViewImpl implements CreateEdgeView {
    /**
     * The default value of the '{@link #getSourceExpression()
     * <em>Source Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSourceExpression()
     * @generated
     * @ordered
     */
    protected static final String SOURCE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSourceExpression()
     * <em>Source Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getSourceExpression()
     * @generated
     * @ordered
     */
    protected String sourceExpression = CreateEdgeViewImpl.SOURCE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetExpression()
     * <em>Target Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargetExpression()
     * @generated
     * @ordered
     */
    protected static final String TARGET_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetExpression()
     * <em>Target Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargetExpression()
     * @generated
     * @ordered
     */
    protected String targetExpression = CreateEdgeViewImpl.TARGET_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CreateEdgeViewImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.CREATE_EDGE_VIEW;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSourceExpression() {
        return sourceExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSourceExpression(String newSourceExpression) {
        String oldSourceExpression = sourceExpression;
        sourceExpression = newSourceExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_EDGE_VIEW__SOURCE_EXPRESSION, oldSourceExpression, sourceExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTargetExpression() {
        return targetExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetExpression(String newTargetExpression) {
        String oldTargetExpression = targetExpression;
        targetExpression = newTargetExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.CREATE_EDGE_VIEW__TARGET_EXPRESSION, oldTargetExpression, targetExpression));
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
        case ToolPackage.CREATE_EDGE_VIEW__SOURCE_EXPRESSION:
            return getSourceExpression();
        case ToolPackage.CREATE_EDGE_VIEW__TARGET_EXPRESSION:
            return getTargetExpression();
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
        case ToolPackage.CREATE_EDGE_VIEW__SOURCE_EXPRESSION:
            setSourceExpression((String) newValue);
            return;
        case ToolPackage.CREATE_EDGE_VIEW__TARGET_EXPRESSION:
            setTargetExpression((String) newValue);
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
        case ToolPackage.CREATE_EDGE_VIEW__SOURCE_EXPRESSION:
            setSourceExpression(CreateEdgeViewImpl.SOURCE_EXPRESSION_EDEFAULT);
            return;
        case ToolPackage.CREATE_EDGE_VIEW__TARGET_EXPRESSION:
            setTargetExpression(CreateEdgeViewImpl.TARGET_EXPRESSION_EDEFAULT);
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
        case ToolPackage.CREATE_EDGE_VIEW__SOURCE_EXPRESSION:
            return CreateEdgeViewImpl.SOURCE_EXPRESSION_EDEFAULT == null ? sourceExpression != null : !CreateEdgeViewImpl.SOURCE_EXPRESSION_EDEFAULT.equals(sourceExpression);
        case ToolPackage.CREATE_EDGE_VIEW__TARGET_EXPRESSION:
            return CreateEdgeViewImpl.TARGET_EXPRESSION_EDEFAULT == null ? targetExpression != null : !CreateEdgeViewImpl.TARGET_EXPRESSION_EDEFAULT.equals(targetExpression);
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
        result.append(" (sourceExpression: "); //$NON-NLS-1$
        result.append(sourceExpression);
        result.append(", targetExpression: "); //$NON-NLS-1$
        result.append(targetExpression);
        result.append(')');
        return result.toString();
    }

} // CreateEdgeViewImpl
