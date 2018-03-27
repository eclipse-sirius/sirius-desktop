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
import org.eclipse.sirius.properties.LabelDescription;
import org.eclipse.sirius.properties.LabelOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Label Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenLabelExpression
 * <em>Filter Conditional Styles From Overridden Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl#getFilterActionsFromOverriddenLabelExpression
 * <em>Filter Actions From Overridden Label Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LabelOverrideDescriptionImpl extends AbstractLabelDescriptionImpl implements LabelOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected LabelDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenLabelExpression() <em>Filter
     * Conditional Styles From Overridden Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getFilterConditionalStylesFromOverriddenLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenLabelExpression() <em>Filter Conditional
     * Styles From Overridden Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenLabelExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenLabelExpression = LabelOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterActionsFromOverriddenLabelExpression() <em>Filter Actions From
     * Overridden Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromOverriddenLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterActionsFromOverriddenLabelExpression() <em>Filter Actions From
     * Overridden Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromOverriddenLabelExpression()
     * @generated
     * @ordered
     */
    protected String filterActionsFromOverriddenLabelExpression = LabelOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected LabelOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.LABEL_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (LabelDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public LabelDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(LabelDescription newOverrides) {
        LabelDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenLabelExpression() {
        return filterConditionalStylesFromOverriddenLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenLabelExpression(String newFilterConditionalStylesFromOverriddenLabelExpression) {
        String oldFilterConditionalStylesFromOverriddenLabelExpression = filterConditionalStylesFromOverriddenLabelExpression;
        filterConditionalStylesFromOverriddenLabelExpression = newFilterConditionalStylesFromOverriddenLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenLabelExpression, filterConditionalStylesFromOverriddenLabelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterActionsFromOverriddenLabelExpression() {
        return filterActionsFromOverriddenLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterActionsFromOverriddenLabelExpression(String newFilterActionsFromOverriddenLabelExpression) {
        String oldFilterActionsFromOverriddenLabelExpression = filterActionsFromOverriddenLabelExpression;
        filterActionsFromOverriddenLabelExpression = newFilterActionsFromOverriddenLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION,
                    oldFilterActionsFromOverriddenLabelExpression, filterActionsFromOverriddenLabelExpression));
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
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenLabelExpression();
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            return getFilterActionsFromOverriddenLabelExpression();
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
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((LabelDescription) newValue);
            return;
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            setFilterConditionalStylesFromOverriddenLabelExpression((String) newValue);
            return;
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            setFilterActionsFromOverriddenLabelExpression((String) newValue);
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
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((LabelDescription) null);
            return;
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            setFilterConditionalStylesFromOverriddenLabelExpression(LabelOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            setFilterActionsFromOverriddenLabelExpression(LabelOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            return LabelOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenLabelExpression != null
                    : !LabelOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenLabelExpression);
        case PropertiesPackage.LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION:
            return LabelOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT == null ? filterActionsFromOverriddenLabelExpression != null
                    : !LabelOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION_EDEFAULT.equals(filterActionsFromOverriddenLabelExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenLabelExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenLabelExpression);
        result.append(", filterActionsFromOverriddenLabelExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromOverriddenLabelExpression);
        result.append(')');
        return result.toString();
    }

} // LabelOverrideDescriptionImpl
