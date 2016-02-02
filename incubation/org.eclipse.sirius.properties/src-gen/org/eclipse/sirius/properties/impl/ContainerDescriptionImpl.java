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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.sirius.properties.ContainerDescription;
import org.eclipse.sirius.properties.DynamicMappingFor;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.WidgetDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Container Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getIdentifier
 * <em>Identifier</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getWidgets
 * <em>Widgets</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl#getDynamicMappings
 * <em>Dynamic Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerDescriptionImpl extends MinimalEObjectImpl.Container implements ContainerDescription {
    /**
     * The default value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected static final String IDENTIFIER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIdentifier()
     * @generated
     * @ordered
     */
    protected String identifier = ContainerDescriptionImpl.IDENTIFIER_EDEFAULT;

    /**
     * The cached value of the '{@link #getWidgets() <em>Widgets</em>}'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getWidgets()
     * @generated
     * @ordered
     */
    protected EList<WidgetDescription> widgets;

    /**
     * The cached value of the '{@link #getDynamicMappings()
     * <em>Dynamic Mappings</em>}' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDynamicMappings()
     * @generated
     * @ordered
     */
    protected EList<DynamicMappingFor> dynamicMappings;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ContainerDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.CONTAINER_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIdentifier(String newIdentifier) {
        String oldIdentifier = identifier;
        identifier = newIdentifier;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.CONTAINER_DESCRIPTION__IDENTIFIER, oldIdentifier, identifier));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<WidgetDescription> getWidgets() {
        if (widgets == null) {
            widgets = new EObjectContainmentEList<WidgetDescription>(WidgetDescription.class, this, PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS);
        }
        return widgets;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<DynamicMappingFor> getDynamicMappings() {
        if (dynamicMappings == null) {
            dynamicMappings = new EObjectContainmentEList<DynamicMappingFor>(DynamicMappingFor.class, this, PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS);
        }
        return dynamicMappings;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS:
            return ((InternalEList<?>) getWidgets()).basicRemove(otherEnd, msgs);
        case PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS:
            return ((InternalEList<?>) getDynamicMappings()).basicRemove(otherEnd, msgs);
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__IDENTIFIER:
            return getIdentifier();
        case PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS:
            return getWidgets();
        case PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS:
            return getDynamicMappings();
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__IDENTIFIER:
            setIdentifier((String) newValue);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS:
            getWidgets().clear();
            getWidgets().addAll((Collection<? extends WidgetDescription>) newValue);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS:
            getDynamicMappings().clear();
            getDynamicMappings().addAll((Collection<? extends DynamicMappingFor>) newValue);
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__IDENTIFIER:
            setIdentifier(ContainerDescriptionImpl.IDENTIFIER_EDEFAULT);
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS:
            getWidgets().clear();
            return;
        case PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS:
            getDynamicMappings().clear();
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
        case PropertiesPackage.CONTAINER_DESCRIPTION__IDENTIFIER:
            return ContainerDescriptionImpl.IDENTIFIER_EDEFAULT == null ? identifier != null : !ContainerDescriptionImpl.IDENTIFIER_EDEFAULT.equals(identifier);
        case PropertiesPackage.CONTAINER_DESCRIPTION__WIDGETS:
            return widgets != null && !widgets.isEmpty();
        case PropertiesPackage.CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS:
            return dynamicMappings != null && !dynamicMappings.isEmpty();
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
        result.append(" (identifier: ");
        result.append(identifier);
        result.append(')');
        return result.toString();
    }

} // ContainerDescriptionImpl
