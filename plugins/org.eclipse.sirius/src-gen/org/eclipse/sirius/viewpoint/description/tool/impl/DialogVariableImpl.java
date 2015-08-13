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
import org.eclipse.sirius.viewpoint.description.tool.DialogVariable;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dialog Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DialogVariableImpl#getDialogPrompt
 * <em>Dialog Prompt</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DialogVariableImpl extends AbstractVariableImpl implements DialogVariable {
    /**
     * The default value of the '{@link #getDialogPrompt()
     * <em>Dialog Prompt</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDialogPrompt()
     * @generated
     * @ordered
     */
    protected static final String DIALOG_PROMPT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getDialogPrompt()
     * <em>Dialog Prompt</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getDialogPrompt()
     * @generated
     * @ordered
     */
    protected String dialogPrompt = DialogVariableImpl.DIALOG_PROMPT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DialogVariableImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.DIALOG_VARIABLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getDialogPrompt() {
        return dialogPrompt;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDialogPrompt(String newDialogPrompt) {
        String oldDialogPrompt = dialogPrompt;
        dialogPrompt = newDialogPrompt;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.DIALOG_VARIABLE__DIALOG_PROMPT, oldDialogPrompt, dialogPrompt));
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
        case ToolPackage.DIALOG_VARIABLE__DIALOG_PROMPT:
            return getDialogPrompt();
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
        case ToolPackage.DIALOG_VARIABLE__DIALOG_PROMPT:
            setDialogPrompt((String) newValue);
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
        case ToolPackage.DIALOG_VARIABLE__DIALOG_PROMPT:
            setDialogPrompt(DialogVariableImpl.DIALOG_PROMPT_EDEFAULT);
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
        case ToolPackage.DIALOG_VARIABLE__DIALOG_PROMPT:
            return DialogVariableImpl.DIALOG_PROMPT_EDEFAULT == null ? dialogPrompt != null : !DialogVariableImpl.DIALOG_PROMPT_EDEFAULT.equals(dialogPrompt);
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
        result.append(" (dialogPrompt: "); //$NON-NLS-1$
        result.append(dialogPrompt);
        result.append(')');
        return result.toString();
    }

} // DialogVariableImpl
