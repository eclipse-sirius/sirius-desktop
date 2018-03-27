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
import org.eclipse.sirius.properties.DynamicMappingForDescription;
import org.eclipse.sirius.properties.DynamicMappingForOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Dynamic Mapping For Override
 * Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.DynamicMappingForOverrideDescriptionImpl#getOverrides
 * <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.DynamicMappingForOverrideDescriptionImpl#getFilterIfsFromOverriddenDynamicMappingForExpression
 * <em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DynamicMappingForOverrideDescriptionImpl extends AbstractDynamicMappingForDescriptionImpl implements DynamicMappingForOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected DynamicMappingForDescription overrides;

    /**
     * The default value of the '{@link #getFilterIfsFromOverriddenDynamicMappingForExpression() <em>Filter Ifs From
     * Overridden Dynamic Mapping For Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterIfsFromOverriddenDynamicMappingForExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterIfsFromOverriddenDynamicMappingForExpression() <em>Filter Ifs From
     * Overridden Dynamic Mapping For Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterIfsFromOverriddenDynamicMappingForExpression()
     * @generated
     * @ordered
     */
    protected String filterIfsFromOverriddenDynamicMappingForExpression = DynamicMappingForOverrideDescriptionImpl.FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DynamicMappingForOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DynamicMappingForDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (DynamicMappingForDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public DynamicMappingForDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(DynamicMappingForDescription newOverrides) {
        DynamicMappingForDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterIfsFromOverriddenDynamicMappingForExpression() {
        return filterIfsFromOverriddenDynamicMappingForExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterIfsFromOverriddenDynamicMappingForExpression(String newFilterIfsFromOverriddenDynamicMappingForExpression) {
        String oldFilterIfsFromOverriddenDynamicMappingForExpression = filterIfsFromOverriddenDynamicMappingForExpression;
        filterIfsFromOverriddenDynamicMappingForExpression = newFilterIfsFromOverriddenDynamicMappingForExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION,
                    oldFilterIfsFromOverriddenDynamicMappingForExpression, filterIfsFromOverriddenDynamicMappingForExpression));
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION:
            return getFilterIfsFromOverriddenDynamicMappingForExpression();
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((DynamicMappingForDescription) newValue);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION:
            setFilterIfsFromOverriddenDynamicMappingForExpression((String) newValue);
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((DynamicMappingForDescription) null);
            return;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION:
            setFilterIfsFromOverriddenDynamicMappingForExpression(DynamicMappingForOverrideDescriptionImpl.FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION:
            return DynamicMappingForOverrideDescriptionImpl.FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT == null ? filterIfsFromOverriddenDynamicMappingForExpression != null
                    : !DynamicMappingForOverrideDescriptionImpl.FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION_EDEFAULT.equals(filterIfsFromOverriddenDynamicMappingForExpression);
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
        result.append(" (filterIfsFromOverriddenDynamicMappingForExpression: "); //$NON-NLS-1$
        result.append(filterIfsFromOverriddenDynamicMappingForExpression);
        result.append(')');
        return result.toString();
    }

} // DynamicMappingForOverrideDescriptionImpl
