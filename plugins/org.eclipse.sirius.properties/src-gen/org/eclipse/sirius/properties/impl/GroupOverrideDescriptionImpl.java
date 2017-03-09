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
import org.eclipse.sirius.properties.GroupDescription;
import org.eclipse.sirius.properties.GroupOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl#getFilterControlsFromOverriddenGroupExpression
 * <em>Filter Controls From Overridden Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl#getFilterValidationRulesFromOverriddenGroupExpression
 * <em>Filter Validation Rules From Overridden Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenGroupExpression
 * <em>Filter Conditional Styles From Overridden Group Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupOverrideDescriptionImpl extends AbstractGroupDescriptionImpl implements GroupOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected GroupDescription overrides;

    /**
     * The default value of the '{@link #getFilterControlsFromOverriddenGroupExpression() <em>Filter Controls From
     * Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromOverriddenGroupExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterControlsFromOverriddenGroupExpression() <em>Filter Controls From
     * Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterControlsFromOverriddenGroupExpression()
     * @generated
     * @ordered
     */
    protected String filterControlsFromOverriddenGroupExpression = GroupOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterValidationRulesFromOverriddenGroupExpression() <em>Filter Validation
     * Rules From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromOverriddenGroupExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterValidationRulesFromOverriddenGroupExpression() <em>Filter Validation
     * Rules From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromOverriddenGroupExpression()
     * @generated
     * @ordered
     */
    protected String filterValidationRulesFromOverriddenGroupExpression = GroupOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenGroupExpression() <em>Filter
     * Conditional Styles From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromOverriddenGroupExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenGroupExpression() <em>Filter Conditional
     * Styles From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenGroupExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenGroupExpression = GroupOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GroupOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.GROUP_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GroupDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (GroupDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public GroupDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(GroupDescription newOverrides) {
        GroupDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterControlsFromOverriddenGroupExpression() {
        return filterControlsFromOverriddenGroupExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterControlsFromOverriddenGroupExpression(String newFilterControlsFromOverriddenGroupExpression) {
        String oldFilterControlsFromOverriddenGroupExpression = filterControlsFromOverriddenGroupExpression;
        filterControlsFromOverriddenGroupExpression = newFilterControlsFromOverriddenGroupExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION,
                    oldFilterControlsFromOverriddenGroupExpression, filterControlsFromOverriddenGroupExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterValidationRulesFromOverriddenGroupExpression() {
        return filterValidationRulesFromOverriddenGroupExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterValidationRulesFromOverriddenGroupExpression(String newFilterValidationRulesFromOverriddenGroupExpression) {
        String oldFilterValidationRulesFromOverriddenGroupExpression = filterValidationRulesFromOverriddenGroupExpression;
        filterValidationRulesFromOverriddenGroupExpression = newFilterValidationRulesFromOverriddenGroupExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION,
                    oldFilterValidationRulesFromOverriddenGroupExpression, filterValidationRulesFromOverriddenGroupExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenGroupExpression() {
        return filterConditionalStylesFromOverriddenGroupExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenGroupExpression(String newFilterConditionalStylesFromOverriddenGroupExpression) {
        String oldFilterConditionalStylesFromOverriddenGroupExpression = filterConditionalStylesFromOverriddenGroupExpression;
        filterConditionalStylesFromOverriddenGroupExpression = newFilterConditionalStylesFromOverriddenGroupExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenGroupExpression, filterConditionalStylesFromOverriddenGroupExpression));
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
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            return getFilterControlsFromOverriddenGroupExpression();
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            return getFilterValidationRulesFromOverriddenGroupExpression();
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenGroupExpression();
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
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((GroupDescription) newValue);
            return;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            setFilterControlsFromOverriddenGroupExpression((String) newValue);
            return;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            setFilterValidationRulesFromOverriddenGroupExpression((String) newValue);
            return;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            setFilterConditionalStylesFromOverriddenGroupExpression((String) newValue);
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
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((GroupDescription) null);
            return;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            setFilterControlsFromOverriddenGroupExpression(GroupOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            setFilterValidationRulesFromOverriddenGroupExpression(GroupOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            setFilterConditionalStylesFromOverriddenGroupExpression(GroupOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            return GroupOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT == null ? filterControlsFromOverriddenGroupExpression != null
                    : !GroupOverrideDescriptionImpl.FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT.equals(filterControlsFromOverriddenGroupExpression);
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            return GroupOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT == null ? filterValidationRulesFromOverriddenGroupExpression != null
                    : !GroupOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT.equals(filterValidationRulesFromOverriddenGroupExpression);
        case PropertiesPackage.GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION:
            return GroupOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenGroupExpression != null
                    : !GroupOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenGroupExpression);
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
        result.append(" (filterControlsFromOverriddenGroupExpression: "); //$NON-NLS-1$
        result.append(filterControlsFromOverriddenGroupExpression);
        result.append(", filterValidationRulesFromOverriddenGroupExpression: "); //$NON-NLS-1$
        result.append(filterValidationRulesFromOverriddenGroupExpression);
        result.append(", filterConditionalStylesFromOverriddenGroupExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenGroupExpression);
        result.append(')');
        return result.toString();
    }

} // GroupOverrideDescriptionImpl
