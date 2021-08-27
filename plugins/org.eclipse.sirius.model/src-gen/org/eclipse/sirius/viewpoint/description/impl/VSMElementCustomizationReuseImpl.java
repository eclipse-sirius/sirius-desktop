/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization;
import org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>VSM Element Customization Reuse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationReuseImpl#getReuse
 * <em>Reuse</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.VSMElementCustomizationReuseImpl#getAppliedOn <em>Applied
 * On</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VSMElementCustomizationReuseImpl extends MinimalEObjectImpl.Container implements VSMElementCustomizationReuse {
    /**
     * The cached value of the '{@link #getReuse() <em>Reuse</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getReuse()
     * @generated
     * @ordered
     */
    protected EList<EStructuralFeatureCustomization> reuse;

    /**
     * The cached value of the '{@link #getAppliedOn() <em>Applied On</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
    protected VSMElementCustomizationReuseImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.VSM_ELEMENT_CUSTOMIZATION_REUSE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EStructuralFeatureCustomization> getReuse() {
        if (reuse == null) {
            reuse = new EObjectResolvingEList<>(EStructuralFeatureCustomization.class, this, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE);
        }
        return reuse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<EObject> getAppliedOn() {
        if (appliedOn == null) {
            appliedOn = new EObjectResolvingEList<>(EObject.class, this, DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON);
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE:
            return getReuse();
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON:
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE:
            getReuse().clear();
            getReuse().addAll((Collection<? extends EStructuralFeatureCustomization>) newValue);
            return;
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON:
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE:
            getReuse().clear();
            return;
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON:
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
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__REUSE:
            return reuse != null && !reuse.isEmpty();
        case DescriptionPackage.VSM_ELEMENT_CUSTOMIZATION_REUSE__APPLIED_ON:
            return appliedOn != null && !appliedOn.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // VSMElementCustomizationReuseImpl
