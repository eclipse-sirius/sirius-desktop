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
package org.eclipse.sirius.diagram.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Additional Layer</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.AdditionalLayerImpl#isActiveByDefault
 * <em>Active By Default</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.impl.AdditionalLayerImpl#isOptional
 * <em>Optional</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdditionalLayerImpl extends LayerImpl implements AdditionalLayer {
    /**
     * The default value of the '{@link #isActiveByDefault()
     * <em>Active By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isActiveByDefault()
     * @generated
     * @ordered
     */
    protected static final boolean ACTIVE_BY_DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isActiveByDefault()
     * <em>Active By Default</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isActiveByDefault()
     * @generated
     * @ordered
     */
    protected boolean activeByDefault = AdditionalLayerImpl.ACTIVE_BY_DEFAULT_EDEFAULT;

    /**
     * The default value of the '{@link #isOptional() <em>Optional</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected static final boolean OPTIONAL_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isOptional() <em>Optional</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isOptional()
     * @generated
     * @ordered
     */
    protected boolean optional = AdditionalLayerImpl.OPTIONAL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AdditionalLayerImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.ADDITIONAL_LAYER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isActiveByDefault() {
        return activeByDefault;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setActiveByDefault(boolean newActiveByDefault) {
        boolean oldActiveByDefault = activeByDefault;
        activeByDefault = newActiveByDefault;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT, oldActiveByDefault, activeByDefault));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isOptional() {
        return optional;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setOptional(boolean newOptional) {
        boolean oldOptional = optional;
        optional = newOptional;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL, oldOptional, optional));
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
        case DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT:
            return isActiveByDefault();
        case DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL:
            return isOptional();
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
        case DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT:
            setActiveByDefault((Boolean) newValue);
            return;
        case DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL:
            setOptional((Boolean) newValue);
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
        case DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT:
            setActiveByDefault(AdditionalLayerImpl.ACTIVE_BY_DEFAULT_EDEFAULT);
            return;
        case DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL:
            setOptional(AdditionalLayerImpl.OPTIONAL_EDEFAULT);
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
        case DescriptionPackage.ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT:
            return activeByDefault != AdditionalLayerImpl.ACTIVE_BY_DEFAULT_EDEFAULT;
        case DescriptionPackage.ADDITIONAL_LAYER__OPTIONAL:
            return optional != AdditionalLayerImpl.OPTIONAL_EDEFAULT;
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
        result.append(" (activeByDefault: "); //$NON-NLS-1$
        result.append(activeByDefault);
        result.append(", optional: "); //$NON-NLS-1$
        result.append(optional);
        result.append(')');
        return result.toString();
    }

} // AdditionalLayerImpl
