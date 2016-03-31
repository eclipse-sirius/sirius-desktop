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
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gauge Section Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeSectionDescriptionImpl#getMinValueExpression
 * <em>Min Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeSectionDescriptionImpl#getMaxValueExpression
 * <em>Max Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeSectionDescriptionImpl#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeSectionDescriptionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeSectionDescriptionImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.GaugeSectionDescriptionImpl#getLabel
 * <em>Label</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GaugeSectionDescriptionImpl extends MinimalEObjectImpl.Container implements GaugeSectionDescription {
    /**
     * The default value of the '{@link #getMinValueExpression()
     * <em>Min Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMinValueExpression()
     * @generated
     * @ordered
     */
    protected static final String MIN_VALUE_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMinValueExpression()
     * <em>Min Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMinValueExpression()
     * @generated
     * @ordered
     */
    protected String minValueExpression = GaugeSectionDescriptionImpl.MIN_VALUE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getMaxValueExpression()
     * <em>Max Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMaxValueExpression()
     * @generated
     * @ordered
     */
    protected static final String MAX_VALUE_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getMaxValueExpression()
     * <em>Max Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getMaxValueExpression()
     * @generated
     * @ordered
     */
    protected String maxValueExpression = GaugeSectionDescriptionImpl.MAX_VALUE_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getValueExpression()
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected static final String VALUE_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getValueExpression()
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getValueExpression()
     * @generated
     * @ordered
     */
    protected String valueExpression = GaugeSectionDescriptionImpl.VALUE_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription backgroundColor;

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected ColorDescription foregroundColor;

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
    protected String label = GaugeSectionDescriptionImpl.LABEL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GaugeSectionDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.GAUGE_SECTION_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getMinValueExpression() {
        return minValueExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMinValueExpression(String newMinValueExpression) {
        String oldMinValueExpression = minValueExpression;
        minValueExpression = newMinValueExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION, oldMinValueExpression, minValueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getMaxValueExpression() {
        return maxValueExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMaxValueExpression(String newMaxValueExpression) {
        String oldMaxValueExpression = maxValueExpression;
        maxValueExpression = newMaxValueExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION, oldMaxValueExpression, maxValueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getValueExpression() {
        return valueExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValueExpression(String newValueExpression) {
        String oldValueExpression = valueExpression;
        valueExpression = newValueExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION, oldValueExpression, valueExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (ColorDescription) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
                }
            }
        }
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(ColorDescription newBackgroundColor) {
        ColorDescription oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getForegroundColor() {
        if (foregroundColor != null && foregroundColor.eIsProxy()) {
            InternalEObject oldForegroundColor = (InternalEObject) foregroundColor;
            foregroundColor = (ColorDescription) eResolveProxy(oldForegroundColor);
            if (foregroundColor != oldForegroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
                }
            }
        }
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetForegroundColor() {
        return foregroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setForegroundColor(ColorDescription newForegroundColor) {
        ColorDescription oldForegroundColor = foregroundColor;
        foregroundColor = newForegroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.GAUGE_SECTION_DESCRIPTION__LABEL, oldLabel, label));
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
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION:
            return getMinValueExpression();
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION:
            return getMaxValueExpression();
        case StylePackage.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION:
            return getValueExpression();
        case StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR:
            if (resolve) {
                return getBackgroundColor();
            }
            return basicGetBackgroundColor();
        case StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR:
            if (resolve) {
                return getForegroundColor();
            }
            return basicGetForegroundColor();
        case StylePackage.GAUGE_SECTION_DESCRIPTION__LABEL:
            return getLabel();
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
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION:
            setMinValueExpression((String) newValue);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION:
            setMaxValueExpression((String) newValue);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression((String) newValue);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) newValue);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR:
            setForegroundColor((ColorDescription) newValue);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__LABEL:
            setLabel((String) newValue);
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
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION:
            setMinValueExpression(GaugeSectionDescriptionImpl.MIN_VALUE_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION:
            setMaxValueExpression(GaugeSectionDescriptionImpl.MAX_VALUE_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION:
            setValueExpression(GaugeSectionDescriptionImpl.VALUE_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) null);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR:
            setForegroundColor((ColorDescription) null);
            return;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__LABEL:
            setLabel(GaugeSectionDescriptionImpl.LABEL_EDEFAULT);
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
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION:
            return GaugeSectionDescriptionImpl.MIN_VALUE_EXPRESSION_EDEFAULT == null ? minValueExpression != null
                    : !GaugeSectionDescriptionImpl.MIN_VALUE_EXPRESSION_EDEFAULT.equals(minValueExpression);
        case StylePackage.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION:
            return GaugeSectionDescriptionImpl.MAX_VALUE_EXPRESSION_EDEFAULT == null ? maxValueExpression != null
                    : !GaugeSectionDescriptionImpl.MAX_VALUE_EXPRESSION_EDEFAULT.equals(maxValueExpression);
        case StylePackage.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION:
            return GaugeSectionDescriptionImpl.VALUE_EXPRESSION_EDEFAULT == null ? valueExpression != null : !GaugeSectionDescriptionImpl.VALUE_EXPRESSION_EDEFAULT.equals(valueExpression);
        case StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR:
            return backgroundColor != null;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR:
            return foregroundColor != null;
        case StylePackage.GAUGE_SECTION_DESCRIPTION__LABEL:
            return GaugeSectionDescriptionImpl.LABEL_EDEFAULT == null ? label != null : !GaugeSectionDescriptionImpl.LABEL_EDEFAULT.equals(label);
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
        result.append(" (minValueExpression: "); //$NON-NLS-1$
        result.append(minValueExpression);
        result.append(", maxValueExpression: "); //$NON-NLS-1$
        result.append(maxValueExpression);
        result.append(", valueExpression: "); //$NON-NLS-1$
        result.append(valueExpression);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(')');
        return result.toString();
    }

} // GaugeSectionDescriptionImpl
