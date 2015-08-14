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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.StyleImpl#getCustomFeatures <em>
 * Custom Features</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.StyleImpl#getDescription <em>
 * Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class StyleImpl extends MinimalEObjectImpl.Container implements Style {
    /**
     * The cached value of the '{@link #getCustomFeatures()
     * <em>Custom Features</em>}' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getCustomFeatures()
     * @generated
     * @ordered
     */
    protected EList<String> customFeatures;

    /**
     * The cached value of the '{@link #getDescription() <em>Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getDescription()
     * @generated
     * @ordered
     */
    protected StyleDescription description;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected StyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<String> getCustomFeatures() {
        if (customFeatures == null) {
            customFeatures = new EDataTypeUniqueEList<String>(String.class, this, ViewpointPackage.STYLE__CUSTOM_FEATURES);
        }
        return customFeatures;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public StyleDescription getDescription() {
        if (description != null && description.eIsProxy()) {
            InternalEObject oldDescription = (InternalEObject) description;
            description = (StyleDescription) eResolveProxy(oldDescription);
            if (description != oldDescription) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.STYLE__DESCRIPTION, oldDescription, description));
                }
            }
        }
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public StyleDescription basicGetDescription() {
        return description;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDescription(StyleDescription newDescription) {
        StyleDescription oldDescription = description;
        description = newDescription;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.STYLE__DESCRIPTION, oldDescription, description));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void refresh() {
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
        case ViewpointPackage.STYLE__CUSTOM_FEATURES:
            return getCustomFeatures();
        case ViewpointPackage.STYLE__DESCRIPTION:
            if (resolve) {
                return getDescription();
            }
            return basicGetDescription();
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
        case ViewpointPackage.STYLE__CUSTOM_FEATURES:
            getCustomFeatures().clear();
            getCustomFeatures().addAll((Collection<? extends String>) newValue);
            return;
        case ViewpointPackage.STYLE__DESCRIPTION:
            setDescription((StyleDescription) newValue);
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
        case ViewpointPackage.STYLE__CUSTOM_FEATURES:
            getCustomFeatures().clear();
            return;
        case ViewpointPackage.STYLE__DESCRIPTION:
            setDescription((StyleDescription) null);
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
        case ViewpointPackage.STYLE__CUSTOM_FEATURES:
            return customFeatures != null && !customFeatures.isEmpty();
        case ViewpointPackage.STYLE__DESCRIPTION:
            return description != null;
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
        if (baseClass == Customizable.class) {
            switch (derivedFeatureID) {
            case ViewpointPackage.STYLE__CUSTOM_FEATURES:
                return ViewpointPackage.CUSTOMIZABLE__CUSTOM_FEATURES;
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
        if (baseClass == Customizable.class) {
            switch (baseFeatureID) {
            case ViewpointPackage.CUSTOMIZABLE__CUSTOM_FEATURES:
                return ViewpointPackage.STYLE__CUSTOM_FEATURES;
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
        result.append(" (customFeatures: "); //$NON-NLS-1$
        result.append(customFeatures);
        result.append(')');
        return result.toString();
    }

} // StyleImpl
