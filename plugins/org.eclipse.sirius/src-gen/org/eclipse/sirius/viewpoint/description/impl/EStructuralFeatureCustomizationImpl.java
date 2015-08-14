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
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>EStructural Feature Customization</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EStructuralFeatureCustomizationImpl#getAppliedOn
 * <em>Applied On</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.EStructuralFeatureCustomizationImpl#isApplyOnAll
 * <em>Apply On All</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EStructuralFeatureCustomizationImpl extends MinimalEObjectImpl.Container implements EStructuralFeatureCustomization {
    /**
     * The cached value of the '{@link #getAppliedOn() <em>Applied On</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getAppliedOn()
     * @generated
     * @ordered
     */
    protected EList<EObject> appliedOn;

    /**
     * The default value of the '{@link #isApplyOnAll() <em>Apply On All</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isApplyOnAll()
     * @generated
     * @ordered
     */
    protected static final boolean APPLY_ON_ALL_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isApplyOnAll() <em>Apply On All</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isApplyOnAll()
     * @generated
     * @ordered
     */
    protected boolean applyOnAll = EStructuralFeatureCustomizationImpl.APPLY_ON_ALL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EStructuralFeatureCustomizationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ESTRUCTURAL_FEATURE_CUSTOMIZATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getAppliedOn() {
        if (appliedOn == null) {
            appliedOn = new EObjectResolvingEList<EObject>(EObject.class, this, DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON);
        }
        return appliedOn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isApplyOnAll() {
        return applyOnAll;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setApplyOnAll(boolean newApplyOnAll) {
        boolean oldApplyOnAll = applyOnAll;
        applyOnAll = newApplyOnAll;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL, oldApplyOnAll, applyOnAll));
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
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON:
            return getAppliedOn();
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL:
            return isApplyOnAll();
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
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON:
            getAppliedOn().clear();
            getAppliedOn().addAll((Collection<? extends EObject>) newValue);
            return;
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL:
            setApplyOnAll((Boolean) newValue);
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
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON:
            getAppliedOn().clear();
            return;
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL:
            setApplyOnAll(EStructuralFeatureCustomizationImpl.APPLY_ON_ALL_EDEFAULT);
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
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON:
            return appliedOn != null && !appliedOn.isEmpty();
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLY_ON_ALL:
            return applyOnAll != EStructuralFeatureCustomizationImpl.APPLY_ON_ALL_EDEFAULT;
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
        result.append(" (applyOnAll: "); //$NON-NLS-1$
        result.append(applyOnAll);
        result.append(')');
        return result.toString();
    }

} // EStructuralFeatureCustomizationImpl
