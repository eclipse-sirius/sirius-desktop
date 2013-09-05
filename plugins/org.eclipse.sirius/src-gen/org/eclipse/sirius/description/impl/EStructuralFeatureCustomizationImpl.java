/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.EStructuralFeatureCustomization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>EStructural Feature Customization</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.impl.EStructuralFeatureCustomizationImpl#getAppliedOn
 * <em>Applied On</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class EStructuralFeatureCustomizationImpl extends EObjectImpl implements EStructuralFeatureCustomization {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.ESTRUCTURAL_FEATURE_CUSTOMIZATION__APPLIED_ON:
            return getAppliedOn();
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
        }
        return super.eIsSet(featureID);
    }

} // EStructuralFeatureCustomizationImpl
