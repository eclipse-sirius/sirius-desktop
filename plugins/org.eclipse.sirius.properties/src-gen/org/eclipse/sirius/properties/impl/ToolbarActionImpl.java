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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ToolbarAction;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Toolbar Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ToolbarActionImpl#getTooltipExpression <em>Tooltip
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ToolbarActionImpl#getImageExpression <em>Image Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ToolbarActionImpl#getInitialOperation <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ToolbarActionImpl extends MinimalEObjectImpl.Container implements ToolbarAction {
    /**
     * The default value of the '{@link #getTooltipExpression() <em>Tooltip Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected static final String TOOLTIP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTooltipExpression() <em>Tooltip Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected String tooltipExpression = ToolbarActionImpl.TOOLTIP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getImageExpression() <em>Image Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getImageExpression()
     * @generated
     * @ordered
     */
    protected static final String IMAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getImageExpression() <em>Image Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getImageExpression()
     * @generated
     * @ordered
     */
    protected String imageExpression = ToolbarActionImpl.IMAGE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getInitialOperation() <em>Initial Operation</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInitialOperation()
     * @generated
     * @ordered
     */
    protected InitialOperation initialOperation;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ToolbarActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.TOOLBAR_ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTooltipExpression() {
        return tooltipExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTooltipExpression(String newTooltipExpression) {
        String oldTooltipExpression = tooltipExpression;
        tooltipExpression = newTooltipExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TOOLBAR_ACTION__TOOLTIP_EXPRESSION, oldTooltipExpression, tooltipExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getImageExpression() {
        return imageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setImageExpression(String newImageExpression) {
        String oldImageExpression = imageExpression;
        imageExpression = newImageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TOOLBAR_ACTION__IMAGE_EXPRESSION, oldImageExpression, imageExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InitialOperation getInitialOperation() {
        return initialOperation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetInitialOperation(InitialOperation newInitialOperation, NotificationChain msgs) {
        InitialOperation oldInitialOperation = initialOperation;
        initialOperation = newInitialOperation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null) {
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION:
            return basicSetInitialOperation(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.TOOLBAR_ACTION__TOOLTIP_EXPRESSION:
            return getTooltipExpression();
        case PropertiesPackage.TOOLBAR_ACTION__IMAGE_EXPRESSION:
            return getImageExpression();
        case PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION:
            return getInitialOperation();
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
        case PropertiesPackage.TOOLBAR_ACTION__TOOLTIP_EXPRESSION:
            setTooltipExpression((String) newValue);
            return;
        case PropertiesPackage.TOOLBAR_ACTION__IMAGE_EXPRESSION:
            setImageExpression((String) newValue);
            return;
        case PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
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
        case PropertiesPackage.TOOLBAR_ACTION__TOOLTIP_EXPRESSION:
            setTooltipExpression(ToolbarActionImpl.TOOLTIP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.TOOLBAR_ACTION__IMAGE_EXPRESSION:
            setImageExpression(ToolbarActionImpl.IMAGE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
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
        case PropertiesPackage.TOOLBAR_ACTION__TOOLTIP_EXPRESSION:
            return ToolbarActionImpl.TOOLTIP_EXPRESSION_EDEFAULT == null ? tooltipExpression != null : !ToolbarActionImpl.TOOLTIP_EXPRESSION_EDEFAULT.equals(tooltipExpression);
        case PropertiesPackage.TOOLBAR_ACTION__IMAGE_EXPRESSION:
            return ToolbarActionImpl.IMAGE_EXPRESSION_EDEFAULT == null ? imageExpression != null : !ToolbarActionImpl.IMAGE_EXPRESSION_EDEFAULT.equals(imageExpression);
        case PropertiesPackage.TOOLBAR_ACTION__INITIAL_OPERATION:
            return initialOperation != null;
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
        result.append(" (tooltipExpression: "); //$NON-NLS-1$
        result.append(tooltipExpression);
        result.append(", imageExpression: "); //$NON-NLS-1$
        result.append(imageExpression);
        result.append(')');
        return result.toString();
    }

} // ToolbarActionImpl
