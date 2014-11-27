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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
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
    protected int labelSize = LABEL_SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelFormat() <em>Label Format</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected static final FontFormat LABEL_FORMAT_EDEFAULT = FontFormat.NORMAL_LITERAL;

    /**
     * The cached value of the '{@link #getLabelFormat() <em>Label Format</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected FontFormat labelFormat = LABEL_FORMAT_EDEFAULT;

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
    protected boolean showIcon = SHOW_ICON_EDEFAULT;

    /**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected static final String ICON_PATH_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected String iconPath = ICON_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelColor() <em>Label Color</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues LABEL_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "0,0,0");

    /**
     * The cached value of the '{@link #getLabelColor() <em>Label Color</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected RGBValues labelColor = LABEL_COLOR_EDEFAULT;

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
    public int getLabelSize() {
        return labelSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelSize(int newLabelSize) {
        int oldLabelSize = labelSize;
        labelSize = newLabelSize;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE, oldLabelSize, labelSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FontFormat getLabelFormat() {
        return labelFormat;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelFormat(FontFormat newLabelFormat) {
        FontFormat oldLabelFormat = labelFormat;
        labelFormat = newLabelFormat == null ? LABEL_FORMAT_EDEFAULT : newLabelFormat;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT, oldLabelFormat, labelFormat));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isShowIcon() {
        return showIcon;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setShowIcon(boolean newShowIcon) {
        boolean oldShowIcon = showIcon;
        showIcon = newShowIcon;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON, oldShowIcon, showIcon));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getLabelColor() {
        return labelColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelColor(RGBValues newLabelColor) {
        RGBValues oldLabelColor = labelColor;
        labelColor = newLabelColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR, oldLabelColor, labelColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setIconPath(String newIconPath) {
        String oldIconPath = iconPath;
        iconPath = newIconPath;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH, oldIconPath, iconPath));
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
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            setLabelFormat((FontFormat) newValue);
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
            setLabelSize(LABEL_SIZE_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            setLabelFormat(LABEL_FORMAT_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            setShowIcon(SHOW_ICON_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
            setIconPath(ICON_PATH_EDEFAULT);
            return;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            setLabelColor(LABEL_COLOR_EDEFAULT);
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
            return labelSize != LABEL_SIZE_EDEFAULT;
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
            return labelFormat != LABEL_FORMAT_EDEFAULT;
        case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
            return showIcon != SHOW_ICON_EDEFAULT;
        case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
            return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
        case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
            return LABEL_COLOR_EDEFAULT == null ? labelColor != null : !LABEL_COLOR_EDEFAULT.equals(labelColor);
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
        result.append(" (labelSize: ");
        result.append(labelSize);
        result.append(", labelFormat: ");
        result.append(labelFormat);
        result.append(", showIcon: ");
        result.append(showIcon);
        result.append(", iconPath: ");
        result.append(iconPath);
        result.append(", labelColor: ");
        result.append(labelColor);
        result.append(')');
        return result.toString();
    }

} // BasicLabelStyleImpl
