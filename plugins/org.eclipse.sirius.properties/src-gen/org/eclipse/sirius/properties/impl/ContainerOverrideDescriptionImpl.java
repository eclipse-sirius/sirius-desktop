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
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.ContainerOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Container Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ContainerOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ContainerOverrideDescriptionImpl#getFilterControlsFromOverriddenContainerExpression
 * <em>Filter Controls From Overridden Container Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContainerOverrideDescriptionImpl extends AbstractContainerDescriptionImpl implements ContainerOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected ContainerDescription overrides;

    /**
     * The default value of the '{@link #getFilterControlsFromOverriddenContainerExpression() <em>Filter Controls From
     * Overridden Container Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromOverriddenContainerExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterControlsFromOverriddenContainerExpression() <em>Filter Controls From
     * Overridden Container Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromOverriddenContainerExpression()
     * @generated
     * @ordered
     */
    protected String filterControlsFromOverriddenContainerExpression = ContainerOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ContainerOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CONTAINER_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (ContainerDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public ContainerDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(ContainerDescription newOverrides) {
        ContainerDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterControlsFromOverriddenContainerExpression() {
        return filterControlsFromOverriddenContainerExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterControlsFromOverriddenContainerExpression(String newFilterControlsFromOverriddenContainerExpression) {
        String oldFilterControlsFromOverriddenContainerExpression = filterControlsFromOverriddenContainerExpression;
        filterControlsFromOverriddenContainerExpression = newFilterControlsFromOverriddenContainerExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION,
                    oldFilterControlsFromOverriddenContainerExpression, filterControlsFromOverriddenContainerExpression));
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
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION:
            return getFilterControlsFromOverriddenContainerExpression();
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
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ContainerDescription) newValue);
            return;
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION:
            setFilterControlsFromOverriddenContainerExpression((String) newValue);
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
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ContainerDescription) null);
            return;
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION:
            setFilterControlsFromOverriddenContainerExpression(ContainerOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION:
            return ContainerOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION_EDEFAULT == null ? filterControlsFromOverriddenContainerExpression != null
                    : !ContainerOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION_EDEFAULT.equals(filterControlsFromOverriddenContainerExpression);
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
        result.append(" (filterControlsFromOverriddenContainerExpression: "); //$NON-NLS-1$
        result.append(filterControlsFromOverriddenContainerExpression);
        result.append(')');
        return result.toString();
    }

} // ContainerOverrideDescriptionImpl
