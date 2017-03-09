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
import org.eclipse.sirius.properties.HyperlinkDescription;
import org.eclipse.sirius.properties.HyperlinkOverrideDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Hyperlink Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenHyperlinkExpression
 * <em>Filter Conditional Styles From Overridden Hyperlink Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl#getFilterActionsFromOverriddenHyperlinkExpression
 * <em>Filter Actions From Overridden Hyperlink Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HyperlinkOverrideDescriptionImpl extends AbstractHyperlinkDescriptionImpl implements HyperlinkOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected HyperlinkDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenHyperlinkExpression() <em>Filter
     * Conditional Styles From Overridden Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenHyperlinkExpression() <em>Filter
     * Conditional Styles From Overridden Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenHyperlinkExpression = HyperlinkOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getFilterActionsFromOverriddenHyperlinkExpression() <em>Filter Actions From
     * Overridden Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromOverriddenHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterActionsFromOverriddenHyperlinkExpression() <em>Filter Actions From
     * Overridden Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterActionsFromOverriddenHyperlinkExpression()
     * @generated
     * @ordered
     */
    protected String filterActionsFromOverriddenHyperlinkExpression = HyperlinkOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected HyperlinkOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.HYPERLINK_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HyperlinkDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (HyperlinkDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public HyperlinkDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(HyperlinkDescription newOverrides) {
        HyperlinkDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenHyperlinkExpression() {
        return filterConditionalStylesFromOverriddenHyperlinkExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenHyperlinkExpression(String newFilterConditionalStylesFromOverriddenHyperlinkExpression) {
        String oldFilterConditionalStylesFromOverriddenHyperlinkExpression = filterConditionalStylesFromOverriddenHyperlinkExpression;
        filterConditionalStylesFromOverriddenHyperlinkExpression = newFilterConditionalStylesFromOverriddenHyperlinkExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenHyperlinkExpression, filterConditionalStylesFromOverriddenHyperlinkExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterActionsFromOverriddenHyperlinkExpression() {
        return filterActionsFromOverriddenHyperlinkExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterActionsFromOverriddenHyperlinkExpression(String newFilterActionsFromOverriddenHyperlinkExpression) {
        String oldFilterActionsFromOverriddenHyperlinkExpression = filterActionsFromOverriddenHyperlinkExpression;
        filterActionsFromOverriddenHyperlinkExpression = newFilterActionsFromOverriddenHyperlinkExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION,
                    oldFilterActionsFromOverriddenHyperlinkExpression, filterActionsFromOverriddenHyperlinkExpression));
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
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenHyperlinkExpression();
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            return getFilterActionsFromOverriddenHyperlinkExpression();
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
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((HyperlinkDescription) newValue);
            return;
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            setFilterConditionalStylesFromOverriddenHyperlinkExpression((String) newValue);
            return;
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            setFilterActionsFromOverriddenHyperlinkExpression((String) newValue);
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
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((HyperlinkDescription) null);
            return;
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            setFilterConditionalStylesFromOverriddenHyperlinkExpression(HyperlinkOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT);
            return;
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            setFilterActionsFromOverriddenHyperlinkExpression(HyperlinkOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            return HyperlinkOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenHyperlinkExpression != null
                    : !HyperlinkOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenHyperlinkExpression);
        case PropertiesPackage.HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION:
            return HyperlinkOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT == null ? filterActionsFromOverriddenHyperlinkExpression != null
                    : !HyperlinkOverrideDescriptionImpl.FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION_EDEFAULT.equals(filterActionsFromOverriddenHyperlinkExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenHyperlinkExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenHyperlinkExpression);
        result.append(", filterActionsFromOverriddenHyperlinkExpression: "); //$NON-NLS-1$
        result.append(filterActionsFromOverriddenHyperlinkExpression);
        result.append(')');
        return result.toString();
    }

} // HyperlinkOverrideDescriptionImpl
