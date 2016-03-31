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
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.CustomizableImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gauge Section</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getMin
 * <em>Min</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getMax
 * <em>Max</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getValue
 * <em>Value</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GaugeSectionImpl extends CustomizableImpl implements GaugeSection {
    /**
     * The default value of the '{@link #getMin() <em>Min</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected static final Integer MIN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMin() <em>Min</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMin()
     * @generated
     * @ordered
     */
    protected Integer min = GaugeSectionImpl.MIN_EDEFAULT;

    /**
     * The default value of the '{@link #getMax() <em>Max</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected static final Integer MAX_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMax() <em>Max</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMax()
     * @generated
     * @ordered
     */
    protected Integer max = GaugeSectionImpl.MAX_EDEFAULT;

    /**
     * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected static final Integer VALUE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected Integer value = GaugeSectionImpl.VALUE_EDEFAULT;

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
    protected String label = GaugeSectionImpl.LABEL_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues BACKGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "0,0,0"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor = GaugeSectionImpl.BACKGROUND_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues FOREGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "138,226,52"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues foregroundColor = GaugeSectionImpl.FOREGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GaugeSectionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.GAUGE_SECTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getMin() {
        return min;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMin(Integer newMin) {
        Integer oldMin = min;
        min = newMin;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__MIN, oldMin, min));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMax(Integer newMax) {
        Integer oldMax = max;
        max = newMax;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__MAX, oldMax, max));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValue(Integer newValue) {
        Integer oldValue = value;
        value = newValue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__VALUE, oldValue, value));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__LABEL, oldLabel, label));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
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
        case DiagramPackage.GAUGE_SECTION__MIN:
            return getMin();
        case DiagramPackage.GAUGE_SECTION__MAX:
            return getMax();
        case DiagramPackage.GAUGE_SECTION__VALUE:
            return getValue();
        case DiagramPackage.GAUGE_SECTION__LABEL:
            return getLabel();
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return getBackgroundColor();
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case DiagramPackage.GAUGE_SECTION__MIN:
            setMin((Integer) newValue);
            return;
        case DiagramPackage.GAUGE_SECTION__MAX:
            setMax((Integer) newValue);
            return;
        case DiagramPackage.GAUGE_SECTION__VALUE:
            setValue((Integer) newValue);
            return;
        case DiagramPackage.GAUGE_SECTION__LABEL:
            setLabel((String) newValue);
            return;
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) newValue);
            return;
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case DiagramPackage.GAUGE_SECTION__MIN:
            setMin(GaugeSectionImpl.MIN_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__MAX:
            setMax(GaugeSectionImpl.MAX_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__VALUE:
            setValue(GaugeSectionImpl.VALUE_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__LABEL:
            setLabel(GaugeSectionImpl.LABEL_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            setBackgroundColor(GaugeSectionImpl.BACKGROUND_COLOR_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
            setForegroundColor(GaugeSectionImpl.FOREGROUND_COLOR_EDEFAULT);
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
        case DiagramPackage.GAUGE_SECTION__MIN:
            return GaugeSectionImpl.MIN_EDEFAULT == null ? min != null : !GaugeSectionImpl.MIN_EDEFAULT.equals(min);
        case DiagramPackage.GAUGE_SECTION__MAX:
            return GaugeSectionImpl.MAX_EDEFAULT == null ? max != null : !GaugeSectionImpl.MAX_EDEFAULT.equals(max);
        case DiagramPackage.GAUGE_SECTION__VALUE:
            return GaugeSectionImpl.VALUE_EDEFAULT == null ? value != null : !GaugeSectionImpl.VALUE_EDEFAULT.equals(value);
        case DiagramPackage.GAUGE_SECTION__LABEL:
            return GaugeSectionImpl.LABEL_EDEFAULT == null ? label != null : !GaugeSectionImpl.LABEL_EDEFAULT.equals(label);
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return GaugeSectionImpl.BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !GaugeSectionImpl.BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
            return GaugeSectionImpl.FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !GaugeSectionImpl.FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
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
        result.append(" (min: "); //$NON-NLS-1$
        result.append(min);
        result.append(", max: "); //$NON-NLS-1$
        result.append(max);
        result.append(", value: "); //$NON-NLS-1$
        result.append(value);
        result.append(", label: "); //$NON-NLS-1$
        result.append(label);
        result.append(", backgroundColor: "); //$NON-NLS-1$
        result.append(backgroundColor);
        result.append(", foregroundColor: "); //$NON-NLS-1$
        result.append(foregroundColor);
        result.append(')');
        return result.toString();
    }

} // GaugeSectionImpl
