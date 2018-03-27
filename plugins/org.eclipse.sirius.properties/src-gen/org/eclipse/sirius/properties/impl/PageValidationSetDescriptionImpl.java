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
import org.eclipse.sirius.properties.PageValidationSetDescription;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Page Validation Set Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl#getSemanticValidationRules
 * <em>Semantic Validation Rules</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageValidationSetDescriptionImpl extends MinimalEObjectImpl.Container implements PageValidationSetDescription {
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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PageValidationSetDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.PAGE_VALIDATION_SET_DESCRIPTION;
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
                    PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES);
        }
        return semanticValidationRules;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            return ((InternalEList<?>) getSemanticValidationRules()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            return getSemanticValidationRules();
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
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            getSemanticValidationRules().clear();
            getSemanticValidationRules().addAll((Collection<? extends SemanticValidationRule>) newValue);
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
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            getSemanticValidationRules().clear();
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
        case PropertiesPackage.PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES:
            return semanticValidationRules != null && !semanticValidationRules.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // PageValidationSetDescriptionImpl
