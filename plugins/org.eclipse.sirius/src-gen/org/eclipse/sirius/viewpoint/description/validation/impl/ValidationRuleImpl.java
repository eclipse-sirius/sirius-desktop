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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.impl.IdentifiedElementImpl;
import org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL;
import org.eclipse.sirius.viewpoint.description.validation.RuleAudit;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rule</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl#getLevel
 * <em>Level</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl#getMessage
 * <em>Message</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl#getAudits
 * <em>Audits</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl#getFixes
 * <em>Fixes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ValidationRuleImpl extends IdentifiedElementImpl implements ValidationRule {
    /**
     * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLevel()
     * @generated
     * @ordered
     */
    protected static final ERROR_LEVEL LEVEL_EDEFAULT = ERROR_LEVEL.INFO_LITERAL;

    /**
     * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLevel()
     * @generated
     * @ordered
     */
    protected ERROR_LEVEL level = ValidationRuleImpl.LEVEL_EDEFAULT;

    /**
     * The default value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected static final String MESSAGE_EDEFAULT = "The element has...";

    /**
     * The cached value of the '{@link #getMessage() <em>Message</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMessage()
     * @generated
     * @ordered
     */
    protected String message = ValidationRuleImpl.MESSAGE_EDEFAULT;

    /**
     * The cached value of the '{@link #getAudits() <em>Audits</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAudits()
     * @generated
     * @ordered
     */
    protected EList<RuleAudit> audits;

    /**
     * The cached value of the '{@link #getFixes() <em>Fixes</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFixes()
     * @generated
     * @ordered
     */
    protected EList<ValidationFix> fixes;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ValidationRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ValidationPackage.Literals.VALIDATION_RULE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ERROR_LEVEL getLevel() {
        return level;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLevel(ERROR_LEVEL newLevel) {
        ERROR_LEVEL oldLevel = level;
        level = newLevel == null ? ValidationRuleImpl.LEVEL_EDEFAULT : newLevel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ValidationPackage.VALIDATION_RULE__LEVEL, oldLevel, level));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMessage(String newMessage) {
        String oldMessage = message;
        message = newMessage;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ValidationPackage.VALIDATION_RULE__MESSAGE, oldMessage, message));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<RuleAudit> getAudits() {
        if (audits == null) {
            audits = new EObjectContainmentEList.Resolving<RuleAudit>(RuleAudit.class, this, ValidationPackage.VALIDATION_RULE__AUDITS);
        }
        return audits;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ValidationFix> getFixes() {
        if (fixes == null) {
            fixes = new EObjectContainmentEList.Resolving<ValidationFix>(ValidationFix.class, this, ValidationPackage.VALIDATION_RULE__FIXES);
        }
        return fixes;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean checkRule(EObject eObj) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getMessage(EObject eObj) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ValidationPackage.VALIDATION_RULE__AUDITS:
            return ((InternalEList<?>) getAudits()).basicRemove(otherEnd, msgs);
        case ValidationPackage.VALIDATION_RULE__FIXES:
            return ((InternalEList<?>) getFixes()).basicRemove(otherEnd, msgs);
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
        case ValidationPackage.VALIDATION_RULE__LEVEL:
            return getLevel();
        case ValidationPackage.VALIDATION_RULE__MESSAGE:
            return getMessage();
        case ValidationPackage.VALIDATION_RULE__AUDITS:
            return getAudits();
        case ValidationPackage.VALIDATION_RULE__FIXES:
            return getFixes();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ValidationPackage.VALIDATION_RULE__LEVEL:
            setLevel((ERROR_LEVEL) newValue);
            return;
        case ValidationPackage.VALIDATION_RULE__MESSAGE:
            setMessage((String) newValue);
            return;
        case ValidationPackage.VALIDATION_RULE__AUDITS:
            getAudits().clear();
            getAudits().addAll((Collection<? extends RuleAudit>) newValue);
            return;
        case ValidationPackage.VALIDATION_RULE__FIXES:
            getFixes().clear();
            getFixes().addAll((Collection<? extends ValidationFix>) newValue);
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
        case ValidationPackage.VALIDATION_RULE__LEVEL:
            setLevel(ValidationRuleImpl.LEVEL_EDEFAULT);
            return;
        case ValidationPackage.VALIDATION_RULE__MESSAGE:
            setMessage(ValidationRuleImpl.MESSAGE_EDEFAULT);
            return;
        case ValidationPackage.VALIDATION_RULE__AUDITS:
            getAudits().clear();
            return;
        case ValidationPackage.VALIDATION_RULE__FIXES:
            getFixes().clear();
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
        case ValidationPackage.VALIDATION_RULE__LEVEL:
            return level != ValidationRuleImpl.LEVEL_EDEFAULT;
        case ValidationPackage.VALIDATION_RULE__MESSAGE:
            return ValidationRuleImpl.MESSAGE_EDEFAULT == null ? message != null : !ValidationRuleImpl.MESSAGE_EDEFAULT.equals(message);
        case ValidationPackage.VALIDATION_RULE__AUDITS:
            return audits != null && !audits.isEmpty();
        case ValidationPackage.VALIDATION_RULE__FIXES:
            return fixes != null && !fixes.isEmpty();
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
        result.append(" (level: "); //$NON-NLS-1$
        result.append(level);
        result.append(", message: "); //$NON-NLS-1$
        result.append(message);
        result.append(')');
        return result.toString();
    }

} // ValidationRuleImpl
