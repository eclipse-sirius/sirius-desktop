/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.filter.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.MappingFilter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Mapping Filter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl#getMappings
 * <em>Mappings</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl#getSemanticConditionExpression
 * <em>Semantic Condition Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.MappingFilterImpl#getViewConditionExpression
 * <em>View Condition Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MappingFilterImpl extends FilterImpl implements MappingFilter {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<DiagramElementMapping> mappings;

    /**
     * The default value of the '{@link #getSemanticConditionExpression()
     * <em>Semantic Condition Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getSemanticConditionExpression()
     * @generated
     * @ordered
     */
    protected static final String SEMANTIC_CONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getSemanticConditionExpression()
     * <em>Semantic Condition Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getSemanticConditionExpression()
     * @generated
     * @ordered
     */
    protected String semanticConditionExpression = MappingFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getViewConditionExpression()
     * <em>View Condition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getViewConditionExpression()
     * @generated
     * @ordered
     */
    protected static final String VIEW_CONDITION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getViewConditionExpression()
     * <em>View Condition Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getViewConditionExpression()
     * @generated
     * @ordered
     */
    protected String viewConditionExpression = MappingFilterImpl.VIEW_CONDITION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MappingFilterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FilterPackage.Literals.MAPPING_FILTER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<DiagramElementMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectResolvingEList<DiagramElementMapping>(DiagramElementMapping.class, this, FilterPackage.MAPPING_FILTER__MAPPINGS);
        }
        return mappings;
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
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION, oldSemanticConditionExpression, semanticConditionExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getViewConditionExpression() {
        return viewConditionExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setViewConditionExpression(String newViewConditionExpression) {
        String oldViewConditionExpression = viewConditionExpression;
        viewConditionExpression = newViewConditionExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.MAPPING_FILTER__VIEW_CONDITION_EXPRESSION, oldViewConditionExpression, viewConditionExpression));
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
        case FilterPackage.MAPPING_FILTER__MAPPINGS:
            return getMappings();
        case FilterPackage.MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            return getSemanticConditionExpression();
        case FilterPackage.MAPPING_FILTER__VIEW_CONDITION_EXPRESSION:
            return getViewConditionExpression();
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
        case FilterPackage.MAPPING_FILTER__MAPPINGS:
            getMappings().clear();
            getMappings().addAll((Collection<? extends DiagramElementMapping>) newValue);
            return;
        case FilterPackage.MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            setSemanticConditionExpression((String) newValue);
            return;
        case FilterPackage.MAPPING_FILTER__VIEW_CONDITION_EXPRESSION:
            setViewConditionExpression((String) newValue);
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
        case FilterPackage.MAPPING_FILTER__MAPPINGS:
            getMappings().clear();
            return;
        case FilterPackage.MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            setSemanticConditionExpression(MappingFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT);
            return;
        case FilterPackage.MAPPING_FILTER__VIEW_CONDITION_EXPRESSION:
            setViewConditionExpression(MappingFilterImpl.VIEW_CONDITION_EXPRESSION_EDEFAULT);
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
        case FilterPackage.MAPPING_FILTER__MAPPINGS:
            return mappings != null && !mappings.isEmpty();
        case FilterPackage.MAPPING_FILTER__SEMANTIC_CONDITION_EXPRESSION:
            return MappingFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT == null ? semanticConditionExpression != null
                    : !MappingFilterImpl.SEMANTIC_CONDITION_EXPRESSION_EDEFAULT.equals(semanticConditionExpression);
        case FilterPackage.MAPPING_FILTER__VIEW_CONDITION_EXPRESSION:
            return MappingFilterImpl.VIEW_CONDITION_EXPRESSION_EDEFAULT == null ? viewConditionExpression != null
                    : !MappingFilterImpl.VIEW_CONDITION_EXPRESSION_EDEFAULT.equals(viewConditionExpression);
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
        result.append(", viewConditionExpression: "); //$NON-NLS-1$
        result.append(viewConditionExpression);
        result.append(')');
        return result.toString();
    }

} // MappingFilterImpl
