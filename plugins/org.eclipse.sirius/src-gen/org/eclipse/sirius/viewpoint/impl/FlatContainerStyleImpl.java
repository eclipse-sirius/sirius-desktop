/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.BackgroundStyle;
import org.eclipse.sirius.viewpoint.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Flat Container Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl#getBackgroundStyle
 * <em>Background Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.FlatContainerStyleImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 * </p>
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
    protected BackgroundStyle backgroundStyle = BACKGROUND_STYLE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor;

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues foregroundColor;

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
        return ViewpointPackage.Literals.FLAT_CONTAINER_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (RGBValues) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                InternalEObject newBackgroundColor = (InternalEObject) backgroundColor;
                NotificationChain msgs = oldBackgroundColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, null, null);
                if (newBackgroundColor.eInternalContainer() == null) {
                    msgs = newBackgroundColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
            }
        }
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBackgroundColor(RGBValues newBackgroundColor, NotificationChain msgs) {
        RGBValues oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, newBackgroundColor);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBackgroundColor(RGBValues newBackgroundColor) {
        if (newBackgroundColor != backgroundColor) {
            NotificationChain msgs = null;
            if (backgroundColor != null)
                msgs = ((InternalEObject) backgroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
            if (newBackgroundColor != null)
                msgs = ((InternalEObject) newBackgroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
            msgs = basicSetBackgroundColor(newBackgroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, newBackgroundColor, newBackgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BackgroundStyle getBackgroundStyle() {
        return backgroundStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBackgroundStyle(BackgroundStyle newBackgroundStyle) {
        BackgroundStyle oldBackgroundStyle = backgroundStyle;
        backgroundStyle = newBackgroundStyle == null ? BACKGROUND_STYLE_EDEFAULT : newBackgroundStyle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE, oldBackgroundStyle, backgroundStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getForegroundColor() {
        if (foregroundColor != null && foregroundColor.eIsProxy()) {
            InternalEObject oldForegroundColor = (InternalEObject) foregroundColor;
            foregroundColor = (RGBValues) eResolveProxy(oldForegroundColor);
            if (foregroundColor != oldForegroundColor) {
                InternalEObject newForegroundColor = (InternalEObject) foregroundColor;
                NotificationChain msgs = oldForegroundColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, null, null);
                if (newForegroundColor.eInternalContainer() == null) {
                    msgs = newForegroundColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
            }
        }
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetForegroundColor() {
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetForegroundColor(RGBValues newForegroundColor, NotificationChain msgs) {
        RGBValues oldForegroundColor = foregroundColor;
        foregroundColor = newForegroundColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, oldForegroundColor, newForegroundColor);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setForegroundColor(RGBValues newForegroundColor) {
        if (newForegroundColor != foregroundColor) {
            NotificationChain msgs = null;
            if (foregroundColor != null)
                msgs = ((InternalEObject) foregroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, null, msgs);
            if (newForegroundColor != null)
                msgs = ((InternalEObject) newForegroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, null, msgs);
            msgs = basicSetForegroundColor(newForegroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, newForegroundColor, newForegroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return basicSetBackgroundColor(null, msgs);
        case ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            return basicSetForegroundColor(null, msgs);
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
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            return getBackgroundStyle();
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            if (resolve)
                return getBackgroundColor();
            return basicGetBackgroundColor();
        case ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            if (resolve)
                return getForegroundColor();
            return basicGetForegroundColor();
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
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            setBackgroundStyle((BackgroundStyle) newValue);
            return;
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) newValue);
            return;
        case ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
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
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            setBackgroundStyle(BACKGROUND_STYLE_EDEFAULT);
            return;
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) null);
            return;
        case ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            setForegroundColor((RGBValues) null);
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
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            return backgroundStyle != BACKGROUND_STYLE_EDEFAULT;
        case ViewpointPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return backgroundColor != null;
        case ViewpointPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            return foregroundColor != null;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (backgroundStyle: ");
        result.append(backgroundStyle);
        result.append(')');
        return result.toString();
    }

} // FlatContainerStyleImpl
