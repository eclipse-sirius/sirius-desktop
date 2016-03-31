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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Flat Container Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl#getBackgroundStyle
 * <em>Background Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlatContainerStyleImpl extends ContainerStyleImpl implements FlatContainerStyle {
    /**
     * The default value of the '{@link #getBackgroundStyle()
     * <em>Background Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundStyle()
     * @generated
     * @ordered
     */
    protected static final BackgroundStyle BACKGROUND_STYLE_EDEFAULT = BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL;

    /**
     * The cached value of the '{@link #getBackgroundStyle()
     * <em>Background Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundStyle()
     * @generated
     * @ordered
     */
    protected BackgroundStyle backgroundStyle = FlatContainerStyleImpl.BACKGROUND_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues BACKGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "255,255,255"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor = FlatContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues FOREGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "209,209,209"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues foregroundColor = FlatContainerStyleImpl.FOREGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected FlatContainerStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.FLAT_CONTAINER_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BackgroundStyle getBackgroundStyle() {
        return backgroundStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundStyle(BackgroundStyle newBackgroundStyle) {
        BackgroundStyle oldBackgroundStyle = backgroundStyle;
        backgroundStyle = newBackgroundStyle == null ? FlatContainerStyleImpl.BACKGROUND_STYLE_EDEFAULT : newBackgroundStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE, oldBackgroundStyle, backgroundStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(RGBValues newBackgroundColor) {
        RGBValues oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getForegroundColor() {
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundColor(RGBValues newForegroundColor) {
        RGBValues oldForegroundColor = foregroundColor;
        foregroundColor = newForegroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
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
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            return getBackgroundStyle();
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return getBackgroundColor();
        case DiagramPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            return getForegroundColor();
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
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            setBackgroundStyle((BackgroundStyle) newValue);
            return;
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) newValue);
            return;
        case DiagramPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            setForegroundColor((RGBValues) newValue);
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
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            setBackgroundStyle(FlatContainerStyleImpl.BACKGROUND_STYLE_EDEFAULT);
            return;
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor(FlatContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT);
            return;
        case DiagramPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            setForegroundColor(FlatContainerStyleImpl.FOREGROUND_COLOR_EDEFAULT);
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
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            return backgroundStyle != FlatContainerStyleImpl.BACKGROUND_STYLE_EDEFAULT;
        case DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return FlatContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !FlatContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
        case DiagramPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            return FlatContainerStyleImpl.FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !FlatContainerStyleImpl.FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
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
        result.append(" (backgroundStyle: "); //$NON-NLS-1$
        result.append(backgroundStyle);
        result.append(", backgroundColor: "); //$NON-NLS-1$
        result.append(backgroundColor);
        result.append(", foregroundColor: "); //$NON-NLS-1$
        result.append(foregroundColor);
        result.append(')');
        return result.toString();
    }

} // FlatContainerStyleImpl
