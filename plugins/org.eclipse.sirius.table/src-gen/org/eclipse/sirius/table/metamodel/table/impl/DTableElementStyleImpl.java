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
package org.eclipse.sirius.table.metamodel.table.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>DTable Element Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl#isDefaultForegroundStyle
 * <em>Default Foreground Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl#isDefaultBackgroundStyle
 * <em>Default Background Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DTableElementStyleImpl extends MinimalEObjectImpl.Container implements DTableElementStyle {

    /**
     * The default value of the '{@link #getLabelSize() <em>Label Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected static final int LABEL_SIZE_EDEFAULT = 8;

    /**
     * The cached value of the '{@link #getLabelSize() <em>Label Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected int labelSize = DTableElementStyleImpl.LABEL_SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabelFormat() <em>Label Format</em>}'
     * attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected EList<FontFormat> labelFormat;

    /**
     * The default value of the '{@link #isDefaultForegroundStyle()
     * <em>Default Foreground Style</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isDefaultForegroundStyle()
     * @generated
     * @ordered
     */
    protected static final boolean DEFAULT_FOREGROUND_STYLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDefaultForegroundStyle()
     * <em>Default Foreground Style</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isDefaultForegroundStyle()
     * @generated
     * @ordered
     */
    protected boolean defaultForegroundStyle = DTableElementStyleImpl.DEFAULT_FOREGROUND_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #isDefaultBackgroundStyle()
     * <em>Default Background Style</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isDefaultBackgroundStyle()
     * @generated
     * @ordered
     */
    protected static final boolean DEFAULT_BACKGROUND_STYLE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isDefaultBackgroundStyle()
     * <em>Default Background Style</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #isDefaultBackgroundStyle()
     * @generated
     * @ordered
     */
    protected boolean defaultBackgroundStyle = DTableElementStyleImpl.DEFAULT_BACKGROUND_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues FOREGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "0,0,0"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getForegroundColor()
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getForegroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues foregroundColor = DTableElementStyleImpl.FOREGROUND_COLOR_EDEFAULT;

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
    protected RGBValues backgroundColor = DTableElementStyleImpl.BACKGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected DTableElementStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TablePackage.Literals.DTABLE_ELEMENT_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getLabelSize() {
        return labelSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabelSize(int newLabelSize) {
        int oldLabelSize = labelSize;
        labelSize = newLabelSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE, oldLabelSize, labelSize));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<FontFormat> getLabelFormat() {
        if (labelFormat == null) {
            labelFormat = new EDataTypeUniqueEList<FontFormat>(FontFormat.class, this, TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT);
        }
        return labelFormat;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isDefaultForegroundStyle() {
        return defaultForegroundStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDefaultForegroundStyle(boolean newDefaultForegroundStyle) {
        boolean oldDefaultForegroundStyle = defaultForegroundStyle;
        defaultForegroundStyle = newDefaultForegroundStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE, oldDefaultForegroundStyle, defaultForegroundStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isDefaultBackgroundStyle() {
        return defaultBackgroundStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setDefaultBackgroundStyle(boolean newDefaultBackgroundStyle) {
        boolean oldDefaultBackgroundStyle = defaultBackgroundStyle;
        defaultBackgroundStyle = newDefaultBackgroundStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE, oldDefaultBackgroundStyle, defaultBackgroundStyle));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR, oldForegroundColor, foregroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE:
            return getLabelSize();
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT:
            return getLabelFormat();
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE:
            return isDefaultForegroundStyle();
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE:
            return isDefaultBackgroundStyle();
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
            return getForegroundColor();
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
            return getBackgroundColor();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT:
            getLabelFormat().clear();
            getLabelFormat().addAll((Collection<? extends FontFormat>) newValue);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE:
            setDefaultForegroundStyle((Boolean) newValue);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE:
            setDefaultBackgroundStyle((Boolean) newValue);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
            setForegroundColor((RGBValues) newValue);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) newValue);
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
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE:
            setLabelSize(DTableElementStyleImpl.LABEL_SIZE_EDEFAULT);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT:
            getLabelFormat().clear();
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE:
            setDefaultForegroundStyle(DTableElementStyleImpl.DEFAULT_FOREGROUND_STYLE_EDEFAULT);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE:
            setDefaultBackgroundStyle(DTableElementStyleImpl.DEFAULT_BACKGROUND_STYLE_EDEFAULT);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
            setForegroundColor(DTableElementStyleImpl.FOREGROUND_COLOR_EDEFAULT);
            return;
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
            setBackgroundColor(DTableElementStyleImpl.BACKGROUND_COLOR_EDEFAULT);
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
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE:
            return labelSize != DTableElementStyleImpl.LABEL_SIZE_EDEFAULT;
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT:
            return labelFormat != null && !labelFormat.isEmpty();
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE:
            return defaultForegroundStyle != DTableElementStyleImpl.DEFAULT_FOREGROUND_STYLE_EDEFAULT;
        case TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE:
            return defaultBackgroundStyle != DTableElementStyleImpl.DEFAULT_BACKGROUND_STYLE_EDEFAULT;
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
            return DTableElementStyleImpl.FOREGROUND_COLOR_EDEFAULT == null ? foregroundColor != null : !DTableElementStyleImpl.FOREGROUND_COLOR_EDEFAULT.equals(foregroundColor);
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
            return DTableElementStyleImpl.BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !DTableElementStyleImpl.BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
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
        result.append(" (labelSize: "); //$NON-NLS-1$
        result.append(labelSize);
        result.append(", labelFormat: "); //$NON-NLS-1$
        result.append(labelFormat);
        result.append(", defaultForegroundStyle: "); //$NON-NLS-1$
        result.append(defaultForegroundStyle);
        result.append(", defaultBackgroundStyle: "); //$NON-NLS-1$
        result.append(defaultBackgroundStyle);
        result.append(", foregroundColor: "); //$NON-NLS-1$
        result.append(foregroundColor);
        result.append(", backgroundColor: "); //$NON-NLS-1$
        result.append(backgroundColor);
        result.append(')');
        return result.toString();
    }
} // DTableElementStyleImpl
