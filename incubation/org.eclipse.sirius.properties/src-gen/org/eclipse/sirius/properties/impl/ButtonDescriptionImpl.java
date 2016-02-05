/**
 * Copyright (c) 2015 Obeo.
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
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Button Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getButtonLabelExpression
 * <em>Button Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl#getInitialOperation
 * <em>Initial Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ButtonDescriptionImpl extends WidgetDescriptionImpl implements ButtonDescription {
    /**
     * The default value of the '{@link #getButtonLabelExpression()
     * <em>Button Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getButtonLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String BUTTON_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getButtonLabelExpression()
     * <em>Button Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getButtonLabelExpression()
     * @generated
     * @ordered
     */
    protected String buttonLabelExpression = BUTTON_LABEL_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getInitialOperation()
     * <em>Initial Operation</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
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
    protected ButtonDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.BUTTON_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getButtonLabelExpression() {
        return buttonLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setButtonLabelExpression(String newButtonLabelExpression) {
        String oldButtonLabelExpression = buttonLabelExpression;
        buttonLabelExpression = newButtonLabelExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION, oldButtonLabelExpression, buttonLabelExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setInitialOperation(InitialOperation newInitialOperation) {
        if (newInitialOperation != initialOperation) {
            NotificationChain msgs = null;
            if (initialOperation != null)
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            if (newInitialOperation != null)
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, null, msgs);
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
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
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            return getButtonLabelExpression();
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
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
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            setButtonLabelExpression((String) newValue);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
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
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            setButtonLabelExpression(BUTTON_LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
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
        case PropertiesPackage.BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION:
            return BUTTON_LABEL_EXPRESSION_EDEFAULT == null ? buttonLabelExpression != null : !BUTTON_LABEL_EXPRESSION_EDEFAULT.equals(buttonLabelExpression);
        case PropertiesPackage.BUTTON_DESCRIPTION__INITIAL_OPERATION:
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (buttonLabelExpression: ");
        result.append(buttonLabelExpression);
        result.append(')');
        return result.toString();
    }

} // ButtonDescriptionImpl
