/**
 * Copyright (c) 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.UIState;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>UI State</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#isInverseSelectionOrder
 * <em>Inverse Selection Order</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.UIStateImpl#getElementsToSelect
 * <em>Elements To Select</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UIStateImpl extends MinimalEObjectImpl.Container implements UIState {
    /**
     * The default value of the '{@link #isInverseSelectionOrder()
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected static final boolean INVERSE_SELECTION_ORDER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isInverseSelectionOrder()
     * <em>Inverse Selection Order</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isInverseSelectionOrder()
     * @generated
     * @ordered
     */
    protected boolean inverseSelectionOrder = UIStateImpl.INVERSE_SELECTION_ORDER_EDEFAULT;

    /**
     * The cached value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected EList<EObject> elementsToSelect;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected UIStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.UI_STATE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isInverseSelectionOrder() {
        return inverseSelectionOrder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInverseSelectionOrder(boolean newInverseSelectionOrder) {
        boolean oldInverseSelectionOrder = inverseSelectionOrder;
        inverseSelectionOrder = newInverseSelectionOrder;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER, oldInverseSelectionOrder, inverseSelectionOrder));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getElementsToSelect() {
        if (elementsToSelect == null) {
            elementsToSelect = new EObjectEList.Unsettable<EObject>(EObject.class, this, ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT);
        }
        return elementsToSelect;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void unsetElementsToSelect() {
        if (elementsToSelect != null) {
            ((InternalEList.Unsettable<?>) elementsToSelect).unset();
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isSetElementsToSelect() {
        return elementsToSelect != null && ((InternalEList.Unsettable<?>) elementsToSelect).isSet();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            return isInverseSelectionOrder();
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            return getElementsToSelect();
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder((Boolean) newValue);
            return;
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            getElementsToSelect().clear();
            getElementsToSelect().addAll((Collection<? extends EObject>) newValue);
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder(UIStateImpl.INVERSE_SELECTION_ORDER_EDEFAULT);
            return;
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            unsetElementsToSelect();
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
        case ViewpointPackage.UI_STATE__INVERSE_SELECTION_ORDER:
            return inverseSelectionOrder != UIStateImpl.INVERSE_SELECTION_ORDER_EDEFAULT;
        case ViewpointPackage.UI_STATE__ELEMENTS_TO_SELECT:
            return isSetElementsToSelect();
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
        result.append(" (inverseSelectionOrder: "); //$NON-NLS-1$
        result.append(inverseSelectionOrder);
        result.append(')');
        return result.toString();
    }

} // UIStateImpl
