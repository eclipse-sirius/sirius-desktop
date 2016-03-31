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

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Composite Filter Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.CompositeFilterDescriptionImpl#getFilters
 * <em>Filters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CompositeFilterDescriptionImpl extends FilterDescriptionImpl implements CompositeFilterDescription {
    /**
     * The cached value of the '{@link #getFilters() <em>Filters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilters()
     * @generated
     * @ordered
     */
    protected EList<Filter> filters;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CompositeFilterDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FilterPackage.Literals.COMPOSITE_FILTER_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Filter> getFilters() {
        if (filters == null) {
            filters = new EObjectContainmentEList.Resolving<Filter>(Filter.class, this, FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS);
        }
        return filters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS:
            return ((InternalEList<?>) getFilters()).basicRemove(otherEnd, msgs);
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
        case FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS:
            return getFilters();
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
        case FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS:
            getFilters().clear();
            getFilters().addAll((Collection<? extends Filter>) newValue);
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
        case FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS:
            getFilters().clear();
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
        case FilterPackage.COMPOSITE_FILTER_DESCRIPTION__FILTERS:
            return filters != null && !filters.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // CompositeFilterDescriptionImpl
