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
package org.eclipse.sirius.viewpoint.description.validation.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Semantic Validation Rule</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.SemanticValidationRuleImpl#getTargetClass
 * <em>Target Class</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SemanticValidationRuleImpl extends ValidationRuleImpl implements SemanticValidationRule {
    /**
     * The default value of the '{@link #getTargetClass() <em>Target Class</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetClass()
     * @generated
     * @ordered
     */
    protected static final String TARGET_CLASS_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTargetClass() <em>Target Class</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetClass()
     * @generated
     * @ordered
     */
    protected String targetClass = SemanticValidationRuleImpl.TARGET_CLASS_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SemanticValidationRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ValidationPackage.Literals.SEMANTIC_VALIDATION_RULE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getTargetClass() {
        return targetClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTargetClass(String newTargetClass) {
        String oldTargetClass = targetClass;
        targetClass = newTargetClass;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ValidationPackage.SEMANTIC_VALIDATION_RULE__TARGET_CLASS, oldTargetClass, targetClass));
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
        case ValidationPackage.SEMANTIC_VALIDATION_RULE__TARGET_CLASS:
            return getTargetClass();
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
        case ValidationPackage.SEMANTIC_VALIDATION_RULE__TARGET_CLASS:
            setTargetClass((String) newValue);
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
        case ValidationPackage.SEMANTIC_VALIDATION_RULE__TARGET_CLASS:
            setTargetClass(SemanticValidationRuleImpl.TARGET_CLASS_EDEFAULT);
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
        case ValidationPackage.SEMANTIC_VALIDATION_RULE__TARGET_CLASS:
            return SemanticValidationRuleImpl.TARGET_CLASS_EDEFAULT == null ? targetClass != null : !SemanticValidationRuleImpl.TARGET_CLASS_EDEFAULT.equals(targetClass);
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
        result.append(" (targetClass: "); //$NON-NLS-1$
        result.append(targetClass);
        result.append(')');
        return result.toString();
    }

} // SemanticValidationRuleImpl
