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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Flat Container Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl#getBackgroundStyle
 * <em>Background Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl#getForegroundColor
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
    protected static final BackgroundStyle BACKGROUND_STYLE_EDEFAULT = BackgroundStyle.GRADIENT_LEFT_TO_RIGHT;

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
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected Color backgroundColor;

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected Color foregroundColor;

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
        return MigrationmodelerPackage.Literals.FLAT_CONTAINER_STYLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE, oldBackgroundStyle, backgroundStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetBackgroundColor(Color newBackgroundColor, NotificationChain msgs) {
        Color oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, newBackgroundColor);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(Color newBackgroundColor) {
        if (newBackgroundColor != backgroundColor) {
            NotificationChain msgs = null;
            if (backgroundColor != null) {
                msgs = ((InternalEObject) backgroundColor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
            }
            if (newBackgroundColor != null) {
                msgs = ((InternalEObject) newBackgroundColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
            }
            msgs = basicSetBackgroundColor(newBackgroundColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR, newBackgroundColor, newBackgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color getForegroundColor() {
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetForegroundColor(Color newForegroundColor, NotificationChain msgs) {
        Color oldForegroundColor = foregroundColor;
        foregroundColor = newForegroundColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, oldForegroundColor, newForegroundColor);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundColor(Color newForegroundColor) {
        if (newForegroundColor != foregroundColor) {
            NotificationChain msgs = null;
            if (foregroundColor != null) {
                msgs = ((InternalEObject) foregroundColor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, null, msgs);
            }
            if (newForegroundColor != null) {
                msgs = ((InternalEObject) newForegroundColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, null, msgs);
            }
            msgs = basicSetForegroundColor(newForegroundColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR, newForegroundColor, newForegroundColor));
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
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return basicSetBackgroundColor(null, msgs);
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
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
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            return getBackgroundStyle();
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return getBackgroundColor();
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
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
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            setBackgroundStyle((BackgroundStyle) newValue);
            return;
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((Color) newValue);
            return;
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            setForegroundColor((Color) newValue);
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
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            setBackgroundStyle(FlatContainerStyleImpl.BACKGROUND_STYLE_EDEFAULT);
            return;
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((Color) null);
            return;
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
            setForegroundColor((Color) null);
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
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE:
            return backgroundStyle != FlatContainerStyleImpl.BACKGROUND_STYLE_EDEFAULT;
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR:
            return backgroundColor != null;
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR:
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
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (backgroundStyle: "); //$NON-NLS-1$
        result.append(backgroundStyle);
        result.append(')');
        return result.toString();
    }

} // FlatContainerStyleImpl
