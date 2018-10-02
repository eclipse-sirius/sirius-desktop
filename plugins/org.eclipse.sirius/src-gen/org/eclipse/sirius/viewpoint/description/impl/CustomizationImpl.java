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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.Customization;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IVSMElementCustomization;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Customization</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.impl.CustomizationImpl#getVsmElementCustomizations <em>Vsm
 * Element Customizations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CustomizationImpl extends MinimalEObjectImpl.Container implements Customization {
    /**
     * The cached value of the '{@link #getVsmElementCustomizations() <em>Vsm Element Customizations</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVsmElementCustomizations()
     * @generated
     * @ordered
     */
    protected EList<IVSMElementCustomization> vsmElementCustomizations;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CustomizationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CUSTOMIZATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<IVSMElementCustomization> getVsmElementCustomizations() {
        if (vsmElementCustomizations == null) {
            vsmElementCustomizations = new EObjectContainmentEList.Resolving<IVSMElementCustomization>(IVSMElementCustomization.class, this,
                    DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS);
        }
        return vsmElementCustomizations;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS:
            return ((InternalEList<?>) getVsmElementCustomizations()).basicRemove(otherEnd, msgs);
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
        case DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS:
            return getVsmElementCustomizations();
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
        case DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS:
            getVsmElementCustomizations().clear();
            getVsmElementCustomizations().addAll((Collection<? extends IVSMElementCustomization>) newValue);
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
        case DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS:
            getVsmElementCustomizations().clear();
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
        case DescriptionPackage.CUSTOMIZATION__VSM_ELEMENT_CUSTOMIZATIONS:
            return vsmElementCustomizations != null && !vsmElementCustomizations.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // CustomizationImpl
