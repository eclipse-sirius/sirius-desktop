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
import org.eclipse.sirius.properties.PageDescription;
import org.eclipse.sirius.properties.PageOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Page Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl#getFilterGroupsFromOverriddenPageExpression
 * <em>Filter Groups From Overridden Page Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl#getFilterValidationRulesFromOverriddenPageExpression
 * <em>Filter Validation Rules From Overridden Page Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageOverrideDescriptionImpl extends AbstractPageDescriptionImpl implements PageOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected PageDescription overrides;

    /**
     * The default value of the '{@link #getFilterGroupsFromOverriddenPageExpression() <em>Filter Groups From Overridden
     * Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterGroupsFromOverriddenPageExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterGroupsFromOverriddenPageExpression() <em>Filter Groups From Overridden
     * Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterGroupsFromOverriddenPageExpression()
     * @generated
     * @ordered
     */
    protected String filterGroupsFromOverriddenPageExpression = PageOverrideDescriptionImpl.FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterValidationRulesFromOverriddenPageExpression() <em>Filter Validation
     * Rules From Overridden Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromOverriddenPageExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterValidationRulesFromOverriddenPageExpression() <em>Filter Validation
     * Rules From Overridden Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterValidationRulesFromOverriddenPageExpression()
     * @generated
     * @ordered
     */
    protected String filterValidationRulesFromOverriddenPageExpression = PageOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PageOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.PAGE_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PageDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (PageDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public PageDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(PageDescription newOverrides) {
        PageDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterGroupsFromOverriddenPageExpression() {
        return filterGroupsFromOverriddenPageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterGroupsFromOverriddenPageExpression(String newFilterGroupsFromOverriddenPageExpression) {
        String oldFilterGroupsFromOverriddenPageExpression = filterGroupsFromOverriddenPageExpression;
        filterGroupsFromOverriddenPageExpression = newFilterGroupsFromOverriddenPageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION,
                    oldFilterGroupsFromOverriddenPageExpression, filterGroupsFromOverriddenPageExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterValidationRulesFromOverriddenPageExpression() {
        return filterValidationRulesFromOverriddenPageExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterValidationRulesFromOverriddenPageExpression(String newFilterValidationRulesFromOverriddenPageExpression) {
        String oldFilterValidationRulesFromOverriddenPageExpression = filterValidationRulesFromOverriddenPageExpression;
        filterValidationRulesFromOverriddenPageExpression = newFilterValidationRulesFromOverriddenPageExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION,
                    oldFilterValidationRulesFromOverriddenPageExpression, filterValidationRulesFromOverriddenPageExpression));
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
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            return getFilterGroupsFromOverriddenPageExpression();
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            return getFilterValidationRulesFromOverriddenPageExpression();
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
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((PageDescription) newValue);
            return;
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            setFilterGroupsFromOverriddenPageExpression((String) newValue);
            return;
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            setFilterValidationRulesFromOverriddenPageExpression((String) newValue);
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
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((PageDescription) null);
            return;
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            setFilterGroupsFromOverriddenPageExpression(PageOverrideDescriptionImpl.FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            setFilterValidationRulesFromOverriddenPageExpression(PageOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            return PageOverrideDescriptionImpl.FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT == null ? filterGroupsFromOverriddenPageExpression != null
                    : !PageOverrideDescriptionImpl.FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT.equals(filterGroupsFromOverriddenPageExpression);
        case PropertiesPackage.PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION:
            return PageOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT == null ? filterValidationRulesFromOverriddenPageExpression != null
                    : !PageOverrideDescriptionImpl.FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION_EDEFAULT.equals(filterValidationRulesFromOverriddenPageExpression);
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
        result.append(" (filterGroupsFromOverriddenPageExpression: "); //$NON-NLS-1$
        result.append(filterGroupsFromOverriddenPageExpression);
        result.append(", filterValidationRulesFromOverriddenPageExpression: "); //$NON-NLS-1$
        result.append(filterValidationRulesFromOverriddenPageExpression);
        result.append(')');
        return result.toString();
    }

} // PageOverrideDescriptionImpl
