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
import org.eclipse.sirius.properties.TextDescription;
import org.eclipse.sirius.properties.TextOverrideDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Text Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.TextOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.TextOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenTextExpression
 * <em>Filter Conditional Styles From Overridden Text Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextOverrideDescriptionImpl extends AbstractTextDescriptionImpl implements TextOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected TextDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenTextExpression() <em>Filter Conditional
     * Styles From Overridden Text Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenTextExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenTextExpression() <em>Filter Conditional
     * Styles From Overridden Text Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenTextExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenTextExpression = TextOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TextOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.TEXT_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (TextDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public TextDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(TextDescription newOverrides) {
        TextDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenTextExpression() {
        return filterConditionalStylesFromOverriddenTextExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenTextExpression(String newFilterConditionalStylesFromOverriddenTextExpression) {
        String oldFilterConditionalStylesFromOverriddenTextExpression = filterConditionalStylesFromOverriddenTextExpression;
        filterConditionalStylesFromOverriddenTextExpression = newFilterConditionalStylesFromOverriddenTextExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenTextExpression, filterConditionalStylesFromOverriddenTextExpression));
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
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenTextExpression();
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
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((TextDescription) newValue);
            return;
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION:
            setFilterConditionalStylesFromOverriddenTextExpression((String) newValue);
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
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((TextDescription) null);
            return;
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION:
            setFilterConditionalStylesFromOverriddenTextExpression(TextOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION:
            return TextOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenTextExpression != null
                    : !TextOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenTextExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenTextExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenTextExpression);
        result.append(')');
        return result.toString();
    }

} // TextOverrideDescriptionImpl
