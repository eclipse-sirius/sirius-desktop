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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rule Audit</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.RuleAuditImpl#getAuditExpression
 * <em>Audit Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuleAuditImpl extends MinimalEObjectImpl.Container implements RuleAudit {
    /**
     * The default value of the '{@link #getAuditExpression()
     * <em>Audit Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAuditExpression()
     * @generated
     * @ordered
     */
    protected static final String AUDIT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAuditExpression()
     * <em>Audit Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAuditExpression()
     * @generated
     * @ordered
     */
    protected String auditExpression = RuleAuditImpl.AUDIT_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RuleAuditImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ValidationPackage.Literals.RULE_AUDIT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getAuditExpression() {
        return auditExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAuditExpression(String newAuditExpression) {
        String oldAuditExpression = auditExpression;
        auditExpression = newAuditExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ValidationPackage.RULE_AUDIT__AUDIT_EXPRESSION, oldAuditExpression, auditExpression));
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
        case ValidationPackage.RULE_AUDIT__AUDIT_EXPRESSION:
            return getAuditExpression();
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
        case ValidationPackage.RULE_AUDIT__AUDIT_EXPRESSION:
            setAuditExpression((String) newValue);
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
        case ValidationPackage.RULE_AUDIT__AUDIT_EXPRESSION:
            setAuditExpression(RuleAuditImpl.AUDIT_EXPRESSION_EDEFAULT);
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
        case ValidationPackage.RULE_AUDIT__AUDIT_EXPRESSION:
            return RuleAuditImpl.AUDIT_EXPRESSION_EDEFAULT == null ? auditExpression != null : !RuleAuditImpl.AUDIT_EXPRESSION_EDEFAULT.equals(auditExpression);
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
        result.append(" (auditExpression: "); //$NON-NLS-1$
        result.append(auditExpression);
        result.append(')');
        return result.toString();
    }

} // RuleAuditImpl
