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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.GroupValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.PropertyValidationRule;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Group Validation Set Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl#getSemanticValidationRules
 * <em>Semantic Validation Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl#getPropertyValidationRules
 * <em>Property Validation Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GroupValidationSetDescriptionImpl extends MinimalEObjectImpl.Container implements GroupValidationSetDescription {
    /**
     * The cached value of the '{@link #getSemanticValidationRules() <em>Semantic Validation Rules</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticValidationRules()
     * @generated
     * @ordered
     */
    protected EList<SemanticValidationRule> semanticValidationRules;

    /**
     * The cached value of the '{@link #getPropertyValidationRules() <em>Property Validation Rules</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPropertyValidationRules()
     * @generated
     * @ordered
     */
    protected EList<PropertyValidationRule> propertyValidationRules;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GroupValidationSetDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.GROUP_VALIDATION_SET_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<SemanticValidationRule> getSemanticValidationRules() {
        if (semanticValidationRules == null) {
            semanticValidationRules = new EObjectContainmentEList<SemanticValidationRule>(SemanticValidationRule.class, this,
                    PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES);
        }
        return semanticValidationRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<PropertyValidationRule> getPropertyValidationRules() {
        if (propertyValidationRules == null) {
            propertyValidationRules = new EObjectContainmentEList<PropertyValidationRule>(PropertyValidationRule.class, this,
                    PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES);
        }
        return propertyValidationRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            return ((InternalEList<?>) getSemanticValidationRules()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES:
            return ((InternalEList<?>) getPropertyValidationRules()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            return getSemanticValidationRules();
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES:
            return getPropertyValidationRules();
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
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            getSemanticValidationRules().clear();
            getSemanticValidationRules().addAll((Collection<? extends SemanticValidationRule>) newValue);
            return;
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES:
            getPropertyValidationRules().clear();
            getPropertyValidationRules().addAll((Collection<? extends PropertyValidationRule>) newValue);
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
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            getSemanticValidationRules().clear();
            return;
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES:
            getPropertyValidationRules().clear();
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
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            return semanticValidationRules != null && !semanticValidationRules.isEmpty();
        case PropertiesPackage.GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES:
            return propertyValidationRules != null && !propertyValidationRules.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // GroupValidationSetDescriptionImpl
