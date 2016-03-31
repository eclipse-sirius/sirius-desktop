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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.filter.Filter;
import org.eclipse.sirius.diagram.description.filter.FilterKind;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Filter</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.filter.impl.FilterImpl#getFilterKind
 * <em>Filter Kind</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class FilterImpl extends MinimalEObjectImpl.Container implements Filter {
    /**
     * The default value of the '{@link #getFilterKind() <em>Filter Kind</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterKind()
     * @generated
     * @ordered
     */
    protected static final FilterKind FILTER_KIND_EDEFAULT = FilterKind.HIDE_LITERAL;

    /**
     * The cached value of the '{@link #getFilterKind() <em>Filter Kind</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getFilterKind()
     * @generated
     * @ordered
     */
    protected FilterKind filterKind = FilterImpl.FILTER_KIND_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FilterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return FilterPackage.Literals.FILTER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FilterKind getFilterKind() {
        return filterKind;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFilterKind(FilterKind newFilterKind) {
        FilterKind oldFilterKind = filterKind;
        filterKind = newFilterKind == null ? FilterImpl.FILTER_KIND_EDEFAULT : newFilterKind;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, FilterPackage.FILTER__FILTER_KIND, oldFilterKind, filterKind));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isVisible(DDiagramElement element) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case FilterPackage.FILTER__FILTER_KIND:
            return getFilterKind();
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
        case FilterPackage.FILTER__FILTER_KIND:
            setFilterKind((FilterKind) newValue);
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
        case FilterPackage.FILTER__FILTER_KIND:
            setFilterKind(FilterImpl.FILTER_KIND_EDEFAULT);
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
        case FilterPackage.FILTER__FILTER_KIND:
            return filterKind != FilterImpl.FILTER_KIND_EDEFAULT;
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
        result.append(" (filterKind: "); //$NON-NLS-1$
        result.append(filterKind);
        result.append(')');
        return result.toString();
    }

} // FilterImpl
