/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.validation.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>View Validation Rule</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.impl.ViewValidationRuleImpl#getTargets
 * <em>Targets</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViewValidationRuleImpl extends ValidationRuleImpl implements ViewValidationRule {
    /**
     * The cached value of the '{@link #getTargets() <em>Targets</em>}' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getTargets()
     * @generated
     * @ordered
     */
    protected EList<RepresentationElementMapping> targets;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ViewValidationRuleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ValidationPackage.Literals.VIEW_VALIDATION_RULE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RepresentationElementMapping> getTargets() {
        if (targets == null) {
            targets = new EObjectResolvingEList<>(RepresentationElementMapping.class, this, ValidationPackage.VIEW_VALIDATION_RULE__TARGETS);
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
        case ValidationPackage.VIEW_VALIDATION_RULE__TARGETS:
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
        case ValidationPackage.VIEW_VALIDATION_RULE__TARGETS:
            getTargets().clear();
            getTargets().addAll((Collection<? extends RepresentationElementMapping>) newValue);
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
        case ValidationPackage.VIEW_VALIDATION_RULE__TARGETS:
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
        case ValidationPackage.VIEW_VALIDATION_RULE__TARGETS:
            return targets != null && !targets.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ViewValidationRuleImpl
