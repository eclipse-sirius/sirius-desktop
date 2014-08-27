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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Gauge Section</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl#getMin
 * <em>Min</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl#getMax
 * <em>Max</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl#getValue
 * <em>Value</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GaugeSectionImpl extends EObjectImpl implements GaugeSection {
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
        return MigrationmodelerPackage.Literals.GAUGE_SECTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__MIN, oldMin, min));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__MAX, oldMax, max));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__VALUE, oldValue, value));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__LABEL, oldLabel, label));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR, oldBackgroundColor, newBackgroundColor);
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
    public void setBackgroundColor(Color newBackgroundColor) {
        if (newBackgroundColor != backgroundColor) {
            NotificationChain msgs = null;
            if (backgroundColor != null)
                msgs = ((InternalEObject) backgroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR, null, msgs);
            if (newBackgroundColor != null)
                msgs = ((InternalEObject) newBackgroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR, null, msgs);
            msgs = basicSetBackgroundColor(newBackgroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR, newBackgroundColor, newBackgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR, oldForegroundColor, newForegroundColor);
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
    public void setForegroundColor(Color newForegroundColor) {
        if (newForegroundColor != foregroundColor) {
            NotificationChain msgs = null;
            if (foregroundColor != null)
                msgs = ((InternalEObject) foregroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR, null, msgs);
            if (newForegroundColor != null)
                msgs = ((InternalEObject) newForegroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR, null, msgs);
            msgs = basicSetForegroundColor(newForegroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR, newForegroundColor, newForegroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return basicSetBackgroundColor(null, msgs);
        case MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case MigrationmodelerPackage.GAUGE_SECTION__MIN:
            return getMin();
        case MigrationmodelerPackage.GAUGE_SECTION__MAX:
            return getMax();
        case MigrationmodelerPackage.GAUGE_SECTION__VALUE:
            return getValue();
        case MigrationmodelerPackage.GAUGE_SECTION__LABEL:
            return getLabel();
        case MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return getBackgroundColor();
        case MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case MigrationmodelerPackage.GAUGE_SECTION__MIN:
            setMin((Integer) newValue);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__MAX:
            setMax((Integer) newValue);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__VALUE:
            setValue((Integer) newValue);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__LABEL:
            setLabel((String) newValue);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            setBackgroundColor((Color) newValue);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case MigrationmodelerPackage.GAUGE_SECTION__MIN:
            setMin(MIN_EDEFAULT);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__MAX:
            setMax(MAX_EDEFAULT);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__VALUE:
            setValue(VALUE_EDEFAULT);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__LABEL:
            setLabel(LABEL_EDEFAULT);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            setBackgroundColor((Color) null);
            return;
        case MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
        case MigrationmodelerPackage.GAUGE_SECTION__MIN:
            return MIN_EDEFAULT == null ? min != null : !MIN_EDEFAULT.equals(min);
        case MigrationmodelerPackage.GAUGE_SECTION__MAX:
            return MAX_EDEFAULT == null ? max != null : !MAX_EDEFAULT.equals(max);
        case MigrationmodelerPackage.GAUGE_SECTION__VALUE:
            return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
        case MigrationmodelerPackage.GAUGE_SECTION__LABEL:
            return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
        case MigrationmodelerPackage.GAUGE_SECTION__BACKGROUND_COLOR:
            return backgroundColor != null;
        case MigrationmodelerPackage.GAUGE_SECTION__FOREGROUND_COLOR:
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
