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
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener;
import org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Filter Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl#getPrecondition
 * <em>Precondition</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl#getElementsToListen
 * <em>Elements To Listen</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl#getListeners
 * <em>Listeners</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ToolFilterDescriptionImpl extends MinimalEObjectImpl.Container implements ToolFilterDescription {
    /**
     * The default value of the '{@link #getPrecondition()
     * <em>Precondition</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPrecondition()
     * @generated
     * @ordered
     */
    protected String precondition = ToolFilterDescriptionImpl.PRECONDITION_EDEFAULT;

    /**
     * The default value of the '{@link #getElementsToListen()
     * <em>Elements To Listen</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToListen()
     * @generated
     * @ordered
     */
    protected static final String ELEMENTS_TO_LISTEN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getElementsToListen()
     * <em>Elements To Listen</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getElementsToListen()
     * @generated
     * @ordered
     */
    protected String elementsToListen = ToolFilterDescriptionImpl.ELEMENTS_TO_LISTEN_EDEFAULT;

    /**
     * The cached value of the '{@link #getListeners() <em>Listeners</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getListeners()
     * @generated
     * @ordered
     */
    protected EList<FeatureChangeListener> listeners;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ToolFilterDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ToolPackage.Literals.TOOL_FILTER_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.TOOL_FILTER_DESCRIPTION__PRECONDITION, oldPrecondition, precondition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getElementsToListen() {
        return elementsToListen;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setElementsToListen(String newElementsToListen) {
        String oldElementsToListen = elementsToListen;
        elementsToListen = newElementsToListen;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ToolPackage.TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN, oldElementsToListen, elementsToListen));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<FeatureChangeListener> getListeners() {
        if (listeners == null) {
            listeners = new EObjectContainmentEList.Resolving<FeatureChangeListener>(FeatureChangeListener.class, this, ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS);
        }
        return listeners;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS:
            return ((InternalEList<?>) getListeners()).basicRemove(otherEnd, msgs);
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
        case ToolPackage.TOOL_FILTER_DESCRIPTION__PRECONDITION:
            return getPrecondition();
        case ToolPackage.TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN:
            return getElementsToListen();
        case ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS:
            return getListeners();
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
        case ToolPackage.TOOL_FILTER_DESCRIPTION__PRECONDITION:
            setPrecondition((String) newValue);
            return;
        case ToolPackage.TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN:
            setElementsToListen((String) newValue);
            return;
        case ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS:
            getListeners().clear();
            getListeners().addAll((Collection<? extends FeatureChangeListener>) newValue);
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
        case ToolPackage.TOOL_FILTER_DESCRIPTION__PRECONDITION:
            setPrecondition(ToolFilterDescriptionImpl.PRECONDITION_EDEFAULT);
            return;
        case ToolPackage.TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN:
            setElementsToListen(ToolFilterDescriptionImpl.ELEMENTS_TO_LISTEN_EDEFAULT);
            return;
        case ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS:
            getListeners().clear();
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
        case ToolPackage.TOOL_FILTER_DESCRIPTION__PRECONDITION:
            return ToolFilterDescriptionImpl.PRECONDITION_EDEFAULT == null ? precondition != null : !ToolFilterDescriptionImpl.PRECONDITION_EDEFAULT.equals(precondition);
        case ToolPackage.TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN:
            return ToolFilterDescriptionImpl.ELEMENTS_TO_LISTEN_EDEFAULT == null ? elementsToListen != null : !ToolFilterDescriptionImpl.ELEMENTS_TO_LISTEN_EDEFAULT.equals(elementsToListen);
        case ToolPackage.TOOL_FILTER_DESCRIPTION__LISTENERS:
            return listeners != null && !listeners.isEmpty();
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
        result.append(" (precondition: "); //$NON-NLS-1$
        result.append(precondition);
        result.append(", elementsToListen: "); //$NON-NLS-1$
        result.append(elementsToListen);
        result.append(')');
        return result.toString();
    }

} // ToolFilterDescriptionImpl
