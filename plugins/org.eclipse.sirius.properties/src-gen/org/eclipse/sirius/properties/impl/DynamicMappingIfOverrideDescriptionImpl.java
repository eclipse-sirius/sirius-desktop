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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.properties.DynamicMappingIfDescription;
import org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Dynamic Mapping If Override
 * Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.DynamicMappingIfOverrideDescriptionImpl#getOverrides
 * <em>Overrides</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicMappingIfOverrideDescriptionImpl extends AbstractDynamicMappingIfDescriptionImpl implements DynamicMappingIfOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected DynamicMappingIfDescription overrides;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DynamicMappingIfOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingIfDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (DynamicMappingIfDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
                }
            }
        }
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DynamicMappingIfDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(DynamicMappingIfDescription newOverrides) {
        DynamicMappingIfDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((DynamicMappingIfDescription) newValue);
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((DynamicMappingIfDescription) null);
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
        case PropertiesPackage.DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        }
        return super.eIsSet(featureID);
    }

} // DynamicMappingIfOverrideDescriptionImpl
