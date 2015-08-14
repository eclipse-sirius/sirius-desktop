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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Basic Label Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl#isShowIcon
 * <em>Show Icon</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.BasicLabelStyleImpl#getLabelColor
 * <em>Label Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BasicLabelStyleImpl extends CustomizableImpl implements BasicLabelStyle {

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
    protected int labelSize = BasicLabelStyleImpl.LABEL_SIZE_EDEFAULT;

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
     * The default value of the '{@link #isShowIcon() <em>Show Icon</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isShowIcon()
     * @generated
     * @ordered
     */
    protected static final boolean SHOW_ICON_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isShowIcon() <em>Show Icon</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isShowIcon()
     * @generated
     * @ordered
     */
    protected boolean showIcon = BasicLabelStyleImpl.SHOW_ICON_EDEFAULT;

    /**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected static final String ICON_PATH_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected String iconPath = BasicLabelStyleImpl.ICON_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelColor() <em>Label Color</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues LABEL_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "0,0,0"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLabelColor() <em>Label Color</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected RGBValues labelColor = BasicLabelStyleImpl.LABEL_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BasicLabelStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.BASIC_LABEL_STYLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE, oldLabelSize, labelSize));
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
            labelFormat = new EDataTypeUniqueEList<FontFormat>(FontFormat.class, this, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT);
        }
        return labelFormat;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean isShowIcon() {
        return showIcon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setShowIcon(boolean newShowIcon) {
        boolean oldShowIcon = showIcon;
        showIcon = newShowIcon;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON, oldShowIcon, showIcon));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getIconPath() {
        return iconPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH, oldIconPath, iconPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RGBValues getLabelColor() {
        return labelColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabelColor(RGBValues newLabelColor) {
        RGBValues oldLabelColor = labelColor;
        labelColor = newLabelColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR, oldLabelColor, labelColor));
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
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            return getLabelSize();
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            return getLabelFormat();
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            return isShowIcon();
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
            return getIconPath();
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            return getLabelColor();
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
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            getLabelFormat().clear();
            getLabelFormat().addAll((Collection<? extends FontFormat>) newValue);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            setLabelColor((RGBValues) newValue);
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
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            setLabelSize(BasicLabelStyleImpl.LABEL_SIZE_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            getLabelFormat().clear();
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            setShowIcon(BasicLabelStyleImpl.SHOW_ICON_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
            setIconPath(BasicLabelStyleImpl.ICON_PATH_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            setLabelColor(BasicLabelStyleImpl.LABEL_COLOR_EDEFAULT);
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
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            return labelSize != BasicLabelStyleImpl.LABEL_SIZE_EDEFAULT;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            return labelFormat != null && !labelFormat.isEmpty();
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            return showIcon != BasicLabelStyleImpl.SHOW_ICON_EDEFAULT;
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
            return BasicLabelStyleImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !BasicLabelStyleImpl.ICON_PATH_EDEFAULT.equals(iconPath);
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            return BasicLabelStyleImpl.LABEL_COLOR_EDEFAULT == null ? labelColor != null : !BasicLabelStyleImpl.LABEL_COLOR_EDEFAULT.equals(labelColor);
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
        result.append(", showIcon: "); //$NON-NLS-1$
        result.append(showIcon);
        result.append(", iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(", labelColor: "); //$NON-NLS-1$
        result.append(labelColor);
        result.append(')');
        return result.toString();
    }
} // BasicLabelStyleImpl
