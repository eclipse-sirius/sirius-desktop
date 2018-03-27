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
import org.eclipse.sirius.properties.ListDescription;
import org.eclipse.sirius.properties.ListOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>List Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenListExpression
 * <em>Filter Conditional Styles From Overridden List Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl#getFilterActionsFromOverriddenListExpression
 * <em>Filter Actions From Overridden List Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListOverrideDescriptionImpl extends AbstractListDescriptionImpl implements ListOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected ListDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenListExpression() <em>Filter Conditional
     * Styles From Overridden List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenListExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenListExpression() <em>Filter Conditional
     * Styles From Overridden List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenListExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenListExpression = ListOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterActionsFromOverriddenListExpression() <em>Filter Actions From
     * Overridden List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromOverriddenListExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterActionsFromOverriddenListExpression() <em>Filter Actions From
     * Overridden List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromOverriddenListExpression()
     * @generated
     * @ordered
     */
    protected String filterActionsFromOverriddenListExpression = ListOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ListOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.LIST_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ListDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (ListDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public ListDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(ListDescription newOverrides) {
        ListDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenListExpression() {
        return filterConditionalStylesFromOverriddenListExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenListExpression(String newFilterConditionalStylesFromOverriddenListExpression) {
        String oldFilterConditionalStylesFromOverriddenListExpression = filterConditionalStylesFromOverriddenListExpression;
        filterConditionalStylesFromOverriddenListExpression = newFilterConditionalStylesFromOverriddenListExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenListExpression, filterConditionalStylesFromOverriddenListExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterActionsFromOverriddenListExpression() {
        return filterActionsFromOverriddenListExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterActionsFromOverriddenListExpression(String newFilterActionsFromOverriddenListExpression) {
        String oldFilterActionsFromOverriddenListExpression = filterActionsFromOverriddenListExpression;
        filterActionsFromOverriddenListExpression = newFilterActionsFromOverriddenListExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION,
                    oldFilterActionsFromOverriddenListExpression, filterActionsFromOverriddenListExpression));
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
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenListExpression();
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION:
            return getFilterActionsFromOverriddenListExpression();
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
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ListDescription) newValue);
            return;
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION:
            setFilterConditionalStylesFromOverriddenListExpression((String) newValue);
            return;
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION:
            setFilterActionsFromOverriddenListExpression((String) newValue);
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
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ListDescription) null);
            return;
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION:
            setFilterConditionalStylesFromOverriddenListExpression(ListOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION:
            setFilterActionsFromOverriddenListExpression(ListOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION:
            return ListOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenListExpression != null
                    : !ListOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenListExpression);
        case PropertiesPackage.LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION:
            return ListOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT == null ? filterActionsFromOverriddenListExpression != null
                    : !ListOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION_EDEFAULT.equals(filterActionsFromOverriddenListExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenListExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenListExpression);
        result.append(", filterActionsFromOverriddenListExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromOverriddenListExpression);
        result.append(')');
        return result.toString();
    }

} // ListOverrideDescriptionImpl
