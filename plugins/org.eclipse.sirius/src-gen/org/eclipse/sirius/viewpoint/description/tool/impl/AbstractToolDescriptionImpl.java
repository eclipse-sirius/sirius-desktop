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
package org.eclipse.sirius.viewpoint.description.tool.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Tool Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#getPrecondition
 * <em>Precondition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#isForceRefresh
 * <em>Force Refresh</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#getElementsToSelect
 * <em>Elements To Select</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl#isInverseSelectionOrder
 * <em>Inverse Selection Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractToolDescriptionImpl extends DocumentedElementImpl implements AbstractToolDescription {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = AbstractToolDescriptionImpl.NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabel()
     * @generated
     * @ordered
     */
    protected String label = AbstractToolDescriptionImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getPrecondition()
     * <em>Precondition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected String precondition = AbstractToolDescriptionImpl.PRECONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #isForceRefresh()
     * <em>Force Refresh</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected static final boolean FORCE_REFRESH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isForceRefresh() <em>Force Refresh</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isForceRefresh()
     * @generated
     * @ordered
     */
    protected boolean forceRefresh = AbstractToolDescriptionImpl.FORCE_REFRESH_EDEFAULT;

    /**
     * The cached value of the '{@link #getFilters() <em>Filters</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFilters()
     * @generated
     * @ordered
     */
    protected EList<ToolFilterDescription> filters;

    /**
     * The default value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected static final String ELEMENTS_TO_SELECT_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getElementsToSelect()
     * <em>Elements To Select</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToSelect()
     * @generated
     * @ordered
     */
    protected String elementsToSelect = AbstractToolDescriptionImpl.ELEMENTS_TO_SELECT_EDEFAULT;

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
    protected boolean inverseSelectionOrder = AbstractToolDescriptionImpl.INVERSE_SELECTION_ORDER_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected AbstractToolDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME, oldName, name));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL, oldLabel, label));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getPrecondition() {
        return precondition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setPrecondition(String newPrecondition) {
        String oldPrecondition = precondition;
        precondition = newPrecondition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION, oldPrecondition, precondition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isForceRefresh() {
        return forceRefresh;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setForceRefresh(boolean newForceRefresh) {
        boolean oldForceRefresh = forceRefresh;
        forceRefresh = newForceRefresh;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH, oldForceRefresh, forceRefresh));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ToolFilterDescription> getFilters() {
        if (filters == null) {
            filters = new EObjectContainmentEList.Resolving<ToolFilterDescription>(ToolFilterDescription.class, this, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS);
        }
        return filters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getElementsToSelect() {
        return elementsToSelect;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setElementsToSelect(String newElementsToSelect) {
        String oldElementsToSelect = elementsToSelect;
        elementsToSelect = newElementsToSelect;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT, oldElementsToSelect, elementsToSelect));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER, oldInverseSelectionOrder, inverseSelectionOrder));
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
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
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
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME:
            return getName();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL:
            return getLabel();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
            return getPrecondition();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH:
            return isForceRefresh();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
            return getFilters();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
            return getElementsToSelect();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER:
            return isInverseSelectionOrder();
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
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME:
            setName((String) newValue);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL:
            setLabel((String) newValue);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
            setPrecondition((String) newValue);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH:
            setForceRefresh((Boolean) newValue);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
            getFilters().clear();
            getFilters().addAll((Collection<? extends ToolFilterDescription>) newValue);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
            setElementsToSelect((String) newValue);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder((Boolean) newValue);
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
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME:
            setName(AbstractToolDescriptionImpl.NAME_EDEFAULT);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL:
            setLabel(AbstractToolDescriptionImpl.LABEL_EDEFAULT);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
            setPrecondition(AbstractToolDescriptionImpl.PRECONDITION_EDEFAULT);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH:
            setForceRefresh(AbstractToolDescriptionImpl.FORCE_REFRESH_EDEFAULT);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
            getFilters().clear();
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
            setElementsToSelect(AbstractToolDescriptionImpl.ELEMENTS_TO_SELECT_EDEFAULT);
            return;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER:
            setInverseSelectionOrder(AbstractToolDescriptionImpl.INVERSE_SELECTION_ORDER_EDEFAULT);
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
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME:
            return AbstractToolDescriptionImpl.NAME_EDEFAULT == null ? name != null : !AbstractToolDescriptionImpl.NAME_EDEFAULT.equals(name);
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL:
            return AbstractToolDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !AbstractToolDescriptionImpl.LABEL_EDEFAULT.equals(label);
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION:
            return AbstractToolDescriptionImpl.PRECONDITION_EDEFAULT == null ? precondition != null : !AbstractToolDescriptionImpl.PRECONDITION_EDEFAULT.equals(precondition);
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH:
            return forceRefresh != AbstractToolDescriptionImpl.FORCE_REFRESH_EDEFAULT;
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS:
            return filters != null && !filters.isEmpty();
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT:
            return AbstractToolDescriptionImpl.ELEMENTS_TO_SELECT_EDEFAULT == null ? elementsToSelect != null : !AbstractToolDescriptionImpl.ELEMENTS_TO_SELECT_EDEFAULT.equals(elementsToSelect);
        case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER:
            return inverseSelectionOrder != AbstractToolDescriptionImpl.INVERSE_SELECTION_ORDER_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == IdentifiedElement.class) {
            switch (derivedFeatureID) {
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME:
                return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
            case ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL:
                return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == IdentifiedElement.class) {
            switch (baseFeatureID) {
            case DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;
            case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
                return ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", precondition: "); //$NON-NLS-1$
        result.append(precondition);
        result.append(", forceRefresh: "); //$NON-NLS-1$
        result.append(forceRefresh);
        result.append(", elementsToSelect: "); //$NON-NLS-1$
        result.append(elementsToSelect);
        result.append(", inverseSelectionOrder: "); //$NON-NLS-1$
        result.append(inverseSelectionOrder);
        result.append(')');
        return result.toString();
    }

} // AbstractToolDescriptionImpl
