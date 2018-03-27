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
import org.eclipse.sirius.properties.ButtonDescription;
import org.eclipse.sirius.properties.ButtonOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Button Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ButtonOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenButtonExpression
 * <em>Filter Conditional Styles From Overridden Button Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ButtonOverrideDescriptionImpl extends AbstractButtonDescriptionImpl implements ButtonOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected ButtonDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenButtonExpression() <em>Filter
     * Conditional Styles From Overridden Button Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromOverriddenButtonExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenButtonExpression() <em>Filter
     * Conditional Styles From Overridden Button Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromOverriddenButtonExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenButtonExpression = ButtonOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ButtonOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.BUTTON_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ButtonDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (ButtonDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public ButtonDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(ButtonDescription newOverrides) {
        ButtonDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenButtonExpression() {
        return filterConditionalStylesFromOverriddenButtonExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenButtonExpression(String newFilterConditionalStylesFromOverriddenButtonExpression) {
        String oldFilterConditionalStylesFromOverriddenButtonExpression = filterConditionalStylesFromOverriddenButtonExpression;
        filterConditionalStylesFromOverriddenButtonExpression = newFilterConditionalStylesFromOverriddenButtonExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenButtonExpression, filterConditionalStylesFromOverriddenButtonExpression));
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
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenButtonExpression();
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
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ButtonDescription) newValue);
            return;
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION:
            setFilterConditionalStylesFromOverriddenButtonExpression((String) newValue);
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
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ButtonDescription) null);
            return;
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION:
            setFilterConditionalStylesFromOverriddenButtonExpression(ButtonOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION:
            return ButtonOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenButtonExpression != null
                    : !ButtonOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenButtonExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenButtonExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenButtonExpression);
        result.append(')');
        return result.toString();
    }

} // ButtonOverrideDescriptionImpl
