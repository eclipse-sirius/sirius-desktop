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
package org.eclipse.sirius.diagram.description.style.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bordered Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.BorderedStyleDescriptionImpl#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.BorderedStyleDescriptionImpl#getBorderColor
 * <em>Border Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BorderedStyleDescriptionImpl extends MinimalEObjectImpl.Container implements BorderedStyleDescription {
    /**
     * The default value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "0";

    /**
     * The cached value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String borderSizeComputationExpression = BorderedStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected ColorDescription borderColor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BorderedStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.BORDERED_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getBorderSizeComputationExpression() {
        return borderSizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBorderSizeComputationExpression(String newBorderSizeComputationExpression) {
        String oldBorderSizeComputationExpression = borderSizeComputationExpression;
        borderSizeComputationExpression = newBorderSizeComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION, oldBorderSizeComputationExpression,
                    borderSizeComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColorDescription getBorderColor() {
        if (borderColor != null && borderColor.eIsProxy()) {
            InternalEObject oldBorderColor = (InternalEObject) borderColor;
            borderColor = (ColorDescription) eResolveProxy(oldBorderColor);
            if (borderColor != oldBorderColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR, oldBorderColor, borderColor));
                }
            }
        }
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription basicGetBorderColor() {
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBorderColor(ColorDescription newBorderColor) {
        ColorDescription oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR, oldBorderColor, borderColor));
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
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return getBorderSizeComputationExpression();
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR:
            if (resolve) {
                return getBorderColor();
            }
            return basicGetBorderColor();
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
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression((String) newValue);
            return;
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR:
            setBorderColor((ColorDescription) newValue);
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
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression(BorderedStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR:
            setBorderColor((ColorDescription) null);
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
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return BorderedStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? borderSizeComputationExpression != null
                    : !BorderedStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(borderSizeComputationExpression);
        case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR:
            return borderColor != null;
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
        result.append(" (borderSizeComputationExpression: ");
        result.append(borderSizeComputationExpression);
        result.append(')');
        return result.toString();
    }

} // BorderedStyleDescriptionImpl
