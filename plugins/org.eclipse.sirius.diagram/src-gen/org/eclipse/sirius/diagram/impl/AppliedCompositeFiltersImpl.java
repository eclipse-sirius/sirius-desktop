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
package org.eclipse.sirius.diagram.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.filter.CompositeFilterDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Applied Composite Filters</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl#getCompositeFilterDescriptions
 * <em>Composite Filter Descriptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AppliedCompositeFiltersImpl extends MinimalEObjectImpl.Container implements AppliedCompositeFilters {
    /**
     * The cached value of the '{@link #getCompositeFilterDescriptions()
     * <em>Composite Filter Descriptions</em>}' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCompositeFilterDescriptions()
     * @generated
     * @ordered
     */
    protected EList<CompositeFilterDescription> compositeFilterDescriptions;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AppliedCompositeFiltersImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.APPLIED_COMPOSITE_FILTERS;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<CompositeFilterDescription> getCompositeFilterDescriptions() {
        if (compositeFilterDescriptions == null) {
            compositeFilterDescriptions = new EObjectResolvingEList<CompositeFilterDescription>(CompositeFilterDescription.class, this,
                    DiagramPackage.APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS);
        }
        return compositeFilterDescriptions;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DiagramPackage.APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS:
            return getCompositeFilterDescriptions();
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
        case DiagramPackage.APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS:
            getCompositeFilterDescriptions().clear();
            getCompositeFilterDescriptions().addAll((Collection<? extends CompositeFilterDescription>) newValue);
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
        case DiagramPackage.APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS:
            getCompositeFilterDescriptions().clear();
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
        case DiagramPackage.APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS:
            return compositeFilterDescriptions != null && !compositeFilterDescriptions.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // AppliedCompositeFiltersImpl
