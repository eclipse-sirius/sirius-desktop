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
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.RadioDescription;
import org.eclipse.sirius.properties.RadioOverrideDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Radio Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.RadioOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.RadioOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenRadioExpression
 * <em>Filter Conditional Styles From Overridden Radio Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RadioOverrideDescriptionImpl extends AbstractRadioDescriptionImpl implements RadioOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected RadioDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenRadioExpression() <em>Filter
     * Conditional Styles From Overridden Radio Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromOverriddenRadioExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenRadioExpression() <em>Filter Conditional
     * Styles From Overridden Radio Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenRadioExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenRadioExpression = RadioOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RadioOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.RADIO_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RadioDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (RadioDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public RadioDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(RadioDescription newOverrides) {
        RadioDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenRadioExpression() {
        return filterConditionalStylesFromOverriddenRadioExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenRadioExpression(String newFilterConditionalStylesFromOverriddenRadioExpression) {
        String oldFilterConditionalStylesFromOverriddenRadioExpression = filterConditionalStylesFromOverriddenRadioExpression;
        filterConditionalStylesFromOverriddenRadioExpression = newFilterConditionalStylesFromOverriddenRadioExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenRadioExpression, filterConditionalStylesFromOverriddenRadioExpression));
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
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenRadioExpression();
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
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((RadioDescription) newValue);
            return;
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION:
            setFilterConditionalStylesFromOverriddenRadioExpression((String) newValue);
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
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((RadioDescription) null);
            return;
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION:
            setFilterConditionalStylesFromOverriddenRadioExpression(RadioOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION:
            return RadioOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenRadioExpression != null
                    : !RadioOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenRadioExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenRadioExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenRadioExpression);
        result.append(')');
        return result.toString();
    }

} // RadioOverrideDescriptionImpl
