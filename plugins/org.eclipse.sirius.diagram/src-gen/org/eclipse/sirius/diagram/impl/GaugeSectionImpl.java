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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.impl.CustomizableImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gauge Section</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getMin <em>Min
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getMax <em>Max
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getValue <em>
 * Value</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getLabel <em>
 * Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 * </p>
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
    protected Integer min = MIN_EDEFAULT;

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
    protected Integer max = MAX_EDEFAULT;

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
    protected Integer value = VALUE_EDEFAULT;

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
    protected String label = LABEL_EDEFAULT;

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
    public Integer getMin() {
        return min;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMin(Integer newMin) {
        Integer oldMin = min;
        min = newMin;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__MIN, oldMin, min));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getMax() {
        return max;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMax(Integer newMax) {
        Integer oldMax = max;
        max = newMax;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__MAX, oldMax, max));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Integer getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setValue(Integer newValue) {
        Integer oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__VALUE, oldValue, value));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLabel() {
        return label;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabel(String newLabel) {
        String oldLabel = label;
        label = newLabel;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__LABEL, oldLabel, label));
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
                NotificationChain msgs = oldBackgroundColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, null, null);
                if (newBackgroundColor.eInternalContainer() == null) {
                    msgs = newBackgroundColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, oldBackgroundColor, newBackgroundColor);
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
                msgs = ((InternalEObject) backgroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, null, msgs);
            if (newBackgroundColor != null)
                msgs = ((InternalEObject) newBackgroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, null, msgs);
            msgs = basicSetBackgroundColor(newBackgroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR, newBackgroundColor, newBackgroundColor));
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
                NotificationChain msgs = oldForegroundColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, null, null);
                if (newForegroundColor.eInternalContainer() == null) {
                    msgs = newForegroundColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, oldForegroundColor, newForegroundColor);
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
                msgs = ((InternalEObject) foregroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, null, msgs);
            if (newForegroundColor != null)
                msgs = ((InternalEObject) newForegroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, null, msgs);
            msgs = basicSetForegroundColor(newForegroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR, newForegroundColor, newForegroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return basicSetBackgroundColor(null, msgs);
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case DiagramPackage.GAUGE_SECTION__MIN:
            return getMin();
        case DiagramPackage.GAUGE_SECTION__MAX:
            return getMax();
        case DiagramPackage.GAUGE_SECTION__VALUE:
            return getValue();
        case DiagramPackage.GAUGE_SECTION__LABEL:
            return getLabel();
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            if (resolve)
                return getBackgroundColor();
            return basicGetBackgroundColor();
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
            setMin(MIN_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__MAX:
            setMax(MAX_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__LABEL:
            setLabel(LABEL_EDEFAULT);
            return;
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) null);
            return;
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case DiagramPackage.GAUGE_SECTION__MIN:
            return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
        case DiagramPackage.GAUGE_SECTION__MAX:
            return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
        case DiagramPackage.GAUGE_SECTION__VALUE:
            return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
        case DiagramPackage.GAUGE_SECTION__LABEL:
            return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
        case DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return backgroundColor != null;
        case DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        result.append(" (min: ");
        result.append(min);
        result.append(", max: ");
        result.append(max);
        result.append(", value: ");
        result.append(value);
        result.append(", label: ");
        result.append(label);
        result.append(')');
        return result.toString();
    }

} // GaugeSectionImpl
