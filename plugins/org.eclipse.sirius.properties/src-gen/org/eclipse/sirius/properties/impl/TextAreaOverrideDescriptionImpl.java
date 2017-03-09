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
import org.eclipse.sirius.properties.TextAreaDescription;
import org.eclipse.sirius.properties.TextAreaOverrideDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Text Area Override Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.TextAreaOverrideDescriptionImpl#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.TextAreaOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenTextAreaExpression
 * <em>Filter Conditional Styles From Overridden Text Area Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TextAreaOverrideDescriptionImpl extends AbstractTextAreaDescriptionImpl implements TextAreaOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected TextAreaDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenTextAreaExpression() <em>Filter
     * Conditional Styles From Overridden Text Area Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenTextAreaExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenTextAreaExpression() <em>Filter
     * Conditional Styles From Overridden Text Area Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenTextAreaExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenTextAreaExpression = TextAreaOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected TextAreaOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.TEXT_AREA_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TextAreaDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (TextAreaDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public TextAreaDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(TextAreaDescription newOverrides) {
        TextAreaDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenTextAreaExpression() {
        return filterConditionalStylesFromOverriddenTextAreaExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenTextAreaExpression(String newFilterConditionalStylesFromOverriddenTextAreaExpression) {
        String oldFilterConditionalStylesFromOverriddenTextAreaExpression = filterConditionalStylesFromOverriddenTextAreaExpression;
        filterConditionalStylesFromOverriddenTextAreaExpression = newFilterConditionalStylesFromOverriddenTextAreaExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenTextAreaExpression, filterConditionalStylesFromOverriddenTextAreaExpression));
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
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenTextAreaExpression();
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
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((TextAreaDescription) newValue);
            return;
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION:
            setFilterConditionalStylesFromOverriddenTextAreaExpression((String) newValue);
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
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((TextAreaDescription) null);
            return;
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION:
            setFilterConditionalStylesFromOverriddenTextAreaExpression(TextAreaOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION_EDEFAULT);
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
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesPackage.TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION:
            return TextAreaOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION_EDEFAULT == null ? filterConditionalStylesFromOverriddenTextAreaExpression != null
                    : !TextAreaOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION_EDEFAULT.equals(filterConditionalStylesFromOverriddenTextAreaExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenTextAreaExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenTextAreaExpression);
        result.append(')');
        return result.toString();
    }

} // TextAreaOverrideDescriptionImpl
