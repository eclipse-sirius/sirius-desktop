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
package org.eclipse.sirius.viewpoint.description.style.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Basic Label Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl#isShowIcon
 * <em>Show Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl#getLabelColor
 * <em>Label Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BasicLabelStyleDescriptionImpl extends MinimalEObjectImpl.Container implements BasicLabelStyleDescription {

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
    protected int labelSize = BasicLabelStyleDescriptionImpl.LABEL_SIZE_EDEFAULT;

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
    protected boolean showIcon = BasicLabelStyleDescriptionImpl.SHOW_ICON_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = "feature:name"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = BasicLabelStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabelColor() <em>Label Color</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected ColorDescription labelColor;

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
    protected String iconPath = BasicLabelStyleDescriptionImpl.ICON_PATH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BasicLabelStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.BASIC_LABEL_STYLE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE, oldLabelSize, labelSize));
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
            labelFormat = new EDataTypeUniqueEList<FontFormat>(FontFormat.class, this, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT);
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON, oldShowIcon, showIcon));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColorDescription getLabelColor() {
        if (labelColor != null && labelColor.eIsProxy()) {
            InternalEObject oldLabelColor = (InternalEObject) labelColor;
            labelColor = (ColorDescription) eResolveProxy(oldLabelColor);
            if (labelColor != oldLabelColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
                }
            }
        }
        return labelColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription basicGetLabelColor() {
        return labelColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLabelColor(ColorDescription newLabelColor) {
        ColorDescription oldLabelColor = labelColor;
        labelColor = newLabelColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
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
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
            return getLabelSize();
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
            return getLabelFormat();
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
            return isShowIcon();
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
            if (resolve) {
                return getLabelColor();
            }
            return basicGetLabelColor();
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
            return getIconPath();
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
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
            getLabelFormat().clear();
            getLabelFormat().addAll((Collection<? extends FontFormat>) newValue);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) newValue);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
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
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize(BasicLabelStyleDescriptionImpl.LABEL_SIZE_EDEFAULT);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
            getLabelFormat().clear();
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon(BasicLabelStyleDescriptionImpl.SHOW_ICON_EDEFAULT);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(BasicLabelStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) null);
            return;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath(BasicLabelStyleDescriptionImpl.ICON_PATH_EDEFAULT);
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
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
            return labelSize != BasicLabelStyleDescriptionImpl.LABEL_SIZE_EDEFAULT;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
            return labelFormat != null && !labelFormat.isEmpty();
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
            return showIcon != BasicLabelStyleDescriptionImpl.SHOW_ICON_EDEFAULT;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return BasicLabelStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !BasicLabelStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
            return labelColor != null;
        case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
            return BasicLabelStyleDescriptionImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !BasicLabelStyleDescriptionImpl.ICON_PATH_EDEFAULT.equals(iconPath);
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
        result.append(", labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(')');
        return result.toString();
    }
} // BasicLabelStyleDescriptionImpl
