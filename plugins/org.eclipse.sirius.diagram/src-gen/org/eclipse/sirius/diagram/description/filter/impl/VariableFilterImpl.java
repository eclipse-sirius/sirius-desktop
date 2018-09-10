/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.filter.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.VariableFilter;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Variable Filter</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.filter.impl.VariableFilterImpl#getOwnedVariables <em>Owned
 * Variables</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.filter.impl.VariableFilterImpl#getSemanticConditionExpression
 * <em>Semantic Condition Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VariableFilterImpl extends FilterImpl implements VariableFilter {
    /**
     * The cached value of the '{@link #getOwnedVariables() <em>Owned Variables</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getOwnedVariables()
     * @generated
     * @ordered
     */
    protected EList<InteractiveVariableDescription> ownedVariables;

    /**
     * The default value of the '{@link #getSemanticConditionExpression() <em>Semantic Condition Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticConditionExpression()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_CONDITION_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getSemanticConditionExpression() <em>Semantic Condition Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSemanticConditionExpression()
     * @generated
     * @ordered
     */
    protected String semanticConditionExpression = VariableFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected VariableFilterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FilterPackage.Literals.VARIABLE_FILTER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<InteractiveVariableDescription> getOwnedVariables() {
        if (ownedVariables == null) {
            ownedVariables = new EObjectContainmentEList.Resolving<InteractiveVariableDescription>(InteractiveVariableDescription.class, this, FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES);
        }
        return ownedVariables;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSemanticConditionExpression() {
        return semanticConditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSemanticConditionExpression(String newSemanticConditionExpression) {
        String oldSemanticConditionExpression = semanticConditionExpression;
        semanticConditionExpression = newSemanticConditionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION, oldSemanticConditionExpression, semanticConditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES:
            return ((InternalEList<?>) getOwnedVariables()).basicRemove(otherEnd, msgs);
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
        case FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES:
            return getOwnedVariables();
        case FilterPackage.VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            return getSemanticConditionExpression();
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
        case FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES:
            getOwnedVariables().clear();
            getOwnedVariables().addAll((Collection<? extends InteractiveVariableDescription>) newValue);
            return;
        case FilterPackage.VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            setSemanticConditionExpression((String) newValue);
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
        case FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES:
            getOwnedVariables().clear();
            return;
        case FilterPackage.VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            setSemanticConditionExpression(VariableFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT);
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
        case FilterPackage.VARIABLE_FILTER__OWNED_VARIABLES:
            return ownedVariables != null && !ownedVariables.isEmpty();
        case FilterPackage.VARIABLE_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            return VariableFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT == null ? semanticConditionExpression != null
                    : !VariableFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT.equals(semanticConditionExpression);
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
        result.append(" (semanticConditionExpression: "); //$NON-NLS-1$
        result.append(semanticConditionExpression);
        result.append(')');
        return result.toString();
    }

} // VariableFilterImpl
