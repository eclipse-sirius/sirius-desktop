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
import org.eclipse.sirius.properties.DialogButton;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Dialog Button</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogButtonImpl#getLabelExpression <em>Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogButtonImpl#getIsEnabledExpression <em>Is Enabled
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogButtonImpl#getInitialOperation <em>Initial Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogButtonImpl#isDefault <em>Default</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogButtonImpl#isCloseDialogOnClick <em>Close Dialog On
 * Click</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DialogButtonImpl#isRollbackChangesOnClose <em>Rollback Changes On
 * Close</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DialogButtonImpl extends MinimalEObjectImpl.Container implements DialogButton {
    /**
     * The default value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = DialogButtonImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getIsEnabledExpression() <em>Is Enabled Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIsEnabledExpression()
     * @generated
     * @ordered
     */
    protected static final String IS_ENABLED_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIsEnabledExpression() <em>Is Enabled Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIsEnabledExpression()
     * @generated
     * @ordered
     */
    protected String isEnabledExpression = DialogButtonImpl.IS_ENABLED_EXPRESSION_EDEFAULT;

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
     * The default value of the '{@link #isDefault() <em>Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isDefault()
     * @generated
     * @ordered
     */
    protected static final boolean DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDefault() <em>Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isDefault()
     * @generated
     * @ordered
     */
    protected boolean default_ = DialogButtonImpl.DEFAULT_EDEFAULT;

    /**
     * The default value of the '{@link #isCloseDialogOnClick() <em>Close Dialog On Click</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isCloseDialogOnClick()
     * @generated
     * @ordered
     */
    protected static final boolean CLOSE_DIALOG_ON_CLICK_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isCloseDialogOnClick() <em>Close Dialog On Click</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isCloseDialogOnClick()
     * @generated
     * @ordered
     */
    protected boolean closeDialogOnClick = DialogButtonImpl.CLOSE_DIALOG_ON_CLICK_EDEFAULT;

    /**
     * The default value of the '{@link #isRollbackChangesOnClose() <em>Rollback Changes On Close</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isRollbackChangesOnClose()
     * @generated
     * @ordered
     */
    protected static final boolean ROLLBACK_CHANGES_ON_CLOSE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRollbackChangesOnClose() <em>Rollback Changes On Close</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isRollbackChangesOnClose()
     * @generated
     * @ordered
     */
    protected boolean rollbackChangesOnClose = DialogButtonImpl.ROLLBACK_CHANGES_ON_CLOSE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DialogButtonImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.DIALOG_BUTTON;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getIsEnabledExpression() {
        return isEnabledExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIsEnabledExpression(String newIsEnabledExpression) {
        String oldIsEnabledExpression = isEnabledExpression;
        isEnabledExpression = newIsEnabledExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__IS_ENABLED_EXPRESSION, oldIsEnabledExpression, isEnabledExpression));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION, oldInitialOperation, newInitialOperation);
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
                msgs = ((InternalEObject) initialOperation).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION, null, msgs);
            }
            if (newInitialOperation != null) {
                msgs = ((InternalEObject) newInitialOperation).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION, null, msgs);
            }
            msgs = basicSetInitialOperation(newInitialOperation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION, newInitialOperation, newInitialOperation));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isDefault() {
        return default_;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setDefault(boolean newDefault) {
        boolean oldDefault = default_;
        default_ = newDefault;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__DEFAULT, oldDefault, default_));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isCloseDialogOnClick() {
        return closeDialogOnClick;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCloseDialogOnClick(boolean newCloseDialogOnClick) {
        boolean oldCloseDialogOnClick = closeDialogOnClick;
        closeDialogOnClick = newCloseDialogOnClick;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK, oldCloseDialogOnClick, closeDialogOnClick));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isRollbackChangesOnClose() {
        return rollbackChangesOnClose;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRollbackChangesOnClose(boolean newRollbackChangesOnClose) {
        boolean oldRollbackChangesOnClose = rollbackChangesOnClose;
        rollbackChangesOnClose = newRollbackChangesOnClose;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE, oldRollbackChangesOnClose, rollbackChangesOnClose));
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
        case PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION:
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
        case PropertiesPackage.DIALOG_BUTTON__LABEL_EXPRESSION:
            return getLabelExpression();
        case PropertiesPackage.DIALOG_BUTTON__IS_ENABLED_EXPRESSION:
            return getIsEnabledExpression();
        case PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION:
            return getInitialOperation();
        case PropertiesPackage.DIALOG_BUTTON__DEFAULT:
            return isDefault();
        case PropertiesPackage.DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK:
            return isCloseDialogOnClick();
        case PropertiesPackage.DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE:
            return isRollbackChangesOnClose();
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
        case PropertiesPackage.DIALOG_BUTTON__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case PropertiesPackage.DIALOG_BUTTON__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression((String) newValue);
            return;
        case PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) newValue);
            return;
        case PropertiesPackage.DIALOG_BUTTON__DEFAULT:
            setDefault((Boolean) newValue);
            return;
        case PropertiesPackage.DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK:
            setCloseDialogOnClick((Boolean) newValue);
            return;
        case PropertiesPackage.DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE:
            setRollbackChangesOnClose((Boolean) newValue);
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
        case PropertiesPackage.DIALOG_BUTTON__LABEL_EXPRESSION:
            setLabelExpression(DialogButtonImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.DIALOG_BUTTON__IS_ENABLED_EXPRESSION:
            setIsEnabledExpression(DialogButtonImpl.IS_ENABLED_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION:
            setInitialOperation((InitialOperation) null);
            return;
        case PropertiesPackage.DIALOG_BUTTON__DEFAULT:
            setDefault(DialogButtonImpl.DEFAULT_EDEFAULT);
            return;
        case PropertiesPackage.DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK:
            setCloseDialogOnClick(DialogButtonImpl.CLOSE_DIALOG_ON_CLICK_EDEFAULT);
            return;
        case PropertiesPackage.DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE:
            setRollbackChangesOnClose(DialogButtonImpl.ROLLBACK_CHANGES_ON_CLOSE_EDEFAULT);
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
        case PropertiesPackage.DIALOG_BUTTON__LABEL_EXPRESSION:
            return DialogButtonImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !DialogButtonImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case PropertiesPackage.DIALOG_BUTTON__IS_ENABLED_EXPRESSION:
            return DialogButtonImpl.IS_ENABLED_EXPRESSION_EDEFAULT == null ? isEnabledExpression != null : !DialogButtonImpl.IS_ENABLED_EXPRESSION_EDEFAULT.equals(isEnabledExpression);
        case PropertiesPackage.DIALOG_BUTTON__INITIAL_OPERATION:
            return initialOperation != null;
        case PropertiesPackage.DIALOG_BUTTON__DEFAULT:
            return default_ != DialogButtonImpl.DEFAULT_EDEFAULT;
        case PropertiesPackage.DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK:
            return closeDialogOnClick != DialogButtonImpl.CLOSE_DIALOG_ON_CLICK_EDEFAULT;
        case PropertiesPackage.DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE:
            return rollbackChangesOnClose != DialogButtonImpl.ROLLBACK_CHANGES_ON_CLOSE_EDEFAULT;
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
        result.append(" (labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", isEnabledExpression: "); //$NON-NLS-1$
        result.append(isEnabledExpression);
        result.append(", default: "); //$NON-NLS-1$
        result.append(default_);
        result.append(", closeDialogOnClick: "); //$NON-NLS-1$
        result.append(closeDialogOnClick);
        result.append(", rollbackChangesOnClose: "); //$NON-NLS-1$
        result.append(rollbackChangesOnClose);
        result.append(')');
        return result.toString();
    }

} // DialogButtonImpl
