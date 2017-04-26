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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Ext Reference Override Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceOverrideDescriptionImpl#getOverrides
 * <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceOverrideDescriptionImpl#getFilterConditionalStylesFromOverriddenExtReferenceExpression
 * <em>Filter Conditional Styles From Overridden Ext Reference Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ExtReferenceOverrideDescriptionImpl extends AbstractExtReferenceDescriptionImpl implements ExtReferenceOverrideDescription {
    /**
     * The cached value of the '{@link #getOverrides() <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getOverrides()
     * @generated
     * @ordered
     */
    protected ExtReferenceDescription overrides;

    /**
     * The default value of the '{@link #getFilterConditionalStylesFromOverriddenExtReferenceExpression() <em>Filter
     * Conditional Styles From Overridden Ext Reference Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenExtReferenceExpression()
     * @generated
     * @ordered
     */
    protected static final String FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFilterConditionalStylesFromOverriddenExtReferenceExpression() <em>Filter
     * Conditional Styles From Overridden Ext Reference Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFilterConditionalStylesFromOverriddenExtReferenceExpression()
     * @generated
     * @ordered
     */
    protected String filterConditionalStylesFromOverriddenExtReferenceExpression = ExtReferenceOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ExtReferenceOverrideDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesExtWidgetsReferencePackage.Literals.EXT_REFERENCE_OVERRIDE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExtReferenceDescription getOverrides() {
        if (overrides != null && overrides.eIsProxy()) {
            InternalEObject oldOverrides = (InternalEObject) overrides;
            overrides = (ExtReferenceDescription) eResolveProxy(oldOverrides);
            if (overrides != oldOverrides) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
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
    public ExtReferenceDescription basicGetOverrides() {
        return overrides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOverrides(ExtReferenceDescription newOverrides) {
        ExtReferenceDescription oldOverrides = overrides;
        overrides = newOverrides;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES, oldOverrides, overrides));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getFilterConditionalStylesFromOverriddenExtReferenceExpression() {
        return filterConditionalStylesFromOverriddenExtReferenceExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterConditionalStylesFromOverriddenExtReferenceExpression(String newFilterConditionalStylesFromOverriddenExtReferenceExpression) {
        String oldFilterConditionalStylesFromOverriddenExtReferenceExpression = filterConditionalStylesFromOverriddenExtReferenceExpression;
        filterConditionalStylesFromOverriddenExtReferenceExpression = newFilterConditionalStylesFromOverriddenExtReferenceExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION,
                    oldFilterConditionalStylesFromOverriddenExtReferenceExpression, filterConditionalStylesFromOverriddenExtReferenceExpression));
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES:
            if (resolve) {
                return getOverrides();
            }
            return basicGetOverrides();
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION:
            return getFilterConditionalStylesFromOverriddenExtReferenceExpression();
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ExtReferenceDescription) newValue);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION:
            setFilterConditionalStylesFromOverriddenExtReferenceExpression((String) newValue);
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES:
            setOverrides((ExtReferenceDescription) null);
            return;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION:
            setFilterConditionalStylesFromOverriddenExtReferenceExpression(ExtReferenceOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION_EDEFAULT);
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES:
            return overrides != null;
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION:
            return ExtReferenceOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION_EDEFAULT == null
                    ? filterConditionalStylesFromOverriddenExtReferenceExpression != null
                    : !ExtReferenceOverrideDescriptionImpl.FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION_EDEFAULT
                            .equals(filterConditionalStylesFromOverriddenExtReferenceExpression);
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
        result.append(" (filterConditionalStylesFromOverriddenExtReferenceExpression: "); //$NON-NLS-1$
        result.append(filterConditionalStylesFromOverriddenExtReferenceExpression);
        result.append(')');
        return result.toString();
    }

} // ExtReferenceOverrideDescriptionImpl
