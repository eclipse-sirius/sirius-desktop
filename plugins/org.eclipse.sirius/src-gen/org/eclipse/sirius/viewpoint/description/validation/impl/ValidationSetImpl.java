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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.ValidationSet;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl#getOwnedRules
 * <em>Owned Rules</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl#getReusedRules
 * <em>Reused Rules</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.impl.ValidationSetImpl#getAllRules
 * <em>All Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValidationSetImpl extends DocumentedElementImpl implements ValidationSet {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = ValidationSetImpl.NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getOwnedRules() <em>Owned Rules</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getOwnedRules()
     * @generated
     * @ordered
     */
    protected EList<ValidationRule> ownedRules;

    /**
     * The cached value of the '{@link #getReusedRules() <em>Reused Rules</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getReusedRules()
     * @generated
     * @ordered
     */
    protected EList<ValidationRule> reusedRules;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ValidationSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ValidationPackage.Literals.VALIDATION_SET;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ValidationPackage.VALIDATION_SET__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ValidationRule> getOwnedRules() {
        if (ownedRules == null) {
            ownedRules = new EObjectContainmentEList.Resolving<ValidationRule>(ValidationRule.class, this, ValidationPackage.VALIDATION_SET__OWNED_RULES);
        }
        return ownedRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ValidationRule> getReusedRules() {
        if (reusedRules == null) {
            reusedRules = new EObjectResolvingEList<ValidationRule>(ValidationRule.class, this, ValidationPackage.VALIDATION_SET__REUSED_RULES);
        }
        return reusedRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ValidationRule> getAllRules() {
        // TODO: implement this method to return the 'All Rules' reference list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement
        // org.eclipse.emf.ecore.util.InternalEList and
        // org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of
        // org.eclipse.emf.ecore.util.EcoreEList should be used.
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
        case ValidationPackage.VALIDATION_SET__OWNED_RULES:
            return ((InternalEList<?>) getOwnedRules()).basicRemove(otherEnd, msgs);
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
        case ValidationPackage.VALIDATION_SET__NAME:
            return getName();
        case ValidationPackage.VALIDATION_SET__OWNED_RULES:
            return getOwnedRules();
        case ValidationPackage.VALIDATION_SET__REUSED_RULES:
            return getReusedRules();
        case ValidationPackage.VALIDATION_SET__ALL_RULES:
            return getAllRules();
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
        case ValidationPackage.VALIDATION_SET__NAME:
            setName((String) newValue);
            return;
        case ValidationPackage.VALIDATION_SET__OWNED_RULES:
            getOwnedRules().clear();
            getOwnedRules().addAll((Collection<? extends ValidationRule>) newValue);
            return;
        case ValidationPackage.VALIDATION_SET__REUSED_RULES:
            getReusedRules().clear();
            getReusedRules().addAll((Collection<? extends ValidationRule>) newValue);
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
        case ValidationPackage.VALIDATION_SET__NAME:
            setName(ValidationSetImpl.NAME_EDEFAULT);
            return;
        case ValidationPackage.VALIDATION_SET__OWNED_RULES:
            getOwnedRules().clear();
            return;
        case ValidationPackage.VALIDATION_SET__REUSED_RULES:
            getReusedRules().clear();
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
        case ValidationPackage.VALIDATION_SET__NAME:
            return ValidationSetImpl.NAME_EDEFAULT == null ? name != null : !ValidationSetImpl.NAME_EDEFAULT.equals(name);
        case ValidationPackage.VALIDATION_SET__OWNED_RULES:
            return ownedRules != null && !ownedRules.isEmpty();
        case ValidationPackage.VALIDATION_SET__REUSED_RULES:
            return reusedRules != null && !reusedRules.isEmpty();
        case ValidationPackage.VALIDATION_SET__ALL_RULES:
            return !getAllRules().isEmpty();
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(')');
        return result.toString();
    }

} // ValidationSetImpl
