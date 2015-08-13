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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.viewpoint.description.ColorStep;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Color Step</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ColorStepImpl#getAssociatedValue
 * <em>Associated Value</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ColorStepImpl#getAssociatedColor
 * <em>Associated Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColorStepImpl extends MinimalEObjectImpl.Container implements ColorStep {
    /**
     * The default value of the '{@link #getAssociatedValue()
     * <em>Associated Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAssociatedValue()
     * @generated
     * @ordered
     */
    protected static final String ASSOCIATED_VALUE_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getAssociatedValue()
     * <em>Associated Value</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAssociatedValue()
     * @generated
     * @ordered
     */
    protected String associatedValue = ColorStepImpl.ASSOCIATED_VALUE_EDEFAULT;

    /**
     * The cached value of the '{@link #getAssociatedColor()
     * <em>Associated Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getAssociatedColor()
     * @generated
     * @ordered
     */
    protected FixedColor associatedColor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ColorStepImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.COLOR_STEP;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getAssociatedValue() {
        return associatedValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAssociatedValue(String newAssociatedValue) {
        String oldAssociatedValue = associatedValue;
        associatedValue = newAssociatedValue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE, oldAssociatedValue, associatedValue));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public FixedColor getAssociatedColor() {
        if (associatedColor != null && associatedColor.eIsProxy()) {
            InternalEObject oldAssociatedColor = (InternalEObject) associatedColor;
            associatedColor = (FixedColor) eResolveProxy(oldAssociatedColor);
            if (associatedColor != oldAssociatedColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR, oldAssociatedColor, associatedColor));
                }
            }
        }
        return associatedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FixedColor basicGetAssociatedColor() {
        return associatedColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setAssociatedColor(FixedColor newAssociatedColor) {
        FixedColor oldAssociatedColor = associatedColor;
        associatedColor = newAssociatedColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR, oldAssociatedColor, associatedColor));
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
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE:
            return getAssociatedValue();
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR:
            if (resolve) {
                return getAssociatedColor();
            }
            return basicGetAssociatedColor();
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
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE:
            setAssociatedValue((String) newValue);
            return;
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR:
            setAssociatedColor((FixedColor) newValue);
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
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE:
            setAssociatedValue(ColorStepImpl.ASSOCIATED_VALUE_EDEFAULT);
            return;
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR:
            setAssociatedColor((FixedColor) null);
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
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_VALUE:
            return ColorStepImpl.ASSOCIATED_VALUE_EDEFAULT == null ? associatedValue != null : !ColorStepImpl.ASSOCIATED_VALUE_EDEFAULT.equals(associatedValue);
        case DescriptionPackage.COLOR_STEP__ASSOCIATED_COLOR:
            return associatedColor != null;
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
        result.append(" (associatedValue: "); //$NON-NLS-1$
        result.append(associatedValue);
        result.append(')');
        return result.toString();
    }

} // ColorStepImpl
