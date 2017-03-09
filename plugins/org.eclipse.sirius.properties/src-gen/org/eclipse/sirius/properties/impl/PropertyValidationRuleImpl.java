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

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.properties.WidgetDescription;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationRuleImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Property Validation Rule</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PropertyValidationRuleImpl extends ValidationRuleImpl implements PropertyValidationRule {
    /**
     * The cached value of the '{@link #getTargets() <em>Targets</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargets()
     * @generated
     * @ordered
     */
    protected EList<WidgetDescription> targets;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PropertyValidationRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.PROPERTY_VALIDATION_RULE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<WidgetDescription> getTargets() {
        if (targets == null) {
            targets = new EObjectResolvingEList<WidgetDescription>(WidgetDescription.class, this, PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS);
        }
        return targets;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS:
            return getTargets();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS:
            getTargets().clear();
            getTargets().addAll((Collection<? extends WidgetDescription>) newValue);
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
        case PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS:
            getTargets().clear();
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
        case PropertiesPackage.PROPERTY_VALIDATION_RULE__TARGETS:
            return targets != null && !targets.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // PropertyValidationRuleImpl
