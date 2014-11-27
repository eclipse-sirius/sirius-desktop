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
package org.eclipse.sirius.tree.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.tree.TreeItemStyle;
import org.eclipse.sirius.tree.TreePackage;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.StyleImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Item Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#getLabelSize <em>
 * Label Size</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#getLabelFormat <em>
 * Label Format</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#isShowIcon <em>Show
 * Icon</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#getIconPath <em>
 * Icon Path</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#getLabelColor <em>
 * Label Color</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#getLabelAlignment
 * <em>Label Alignment</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.impl.TreeItemStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeItemStyleImpl extends StyleImpl implements TreeItemStyle {
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
     * The default value of the '{@link #getLabelAlignment()
     * <em>Label Alignment</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelAlignment()
     * @generated
     * @ordered
     */
    protected static final LabelAlignment LABEL_ALIGNMENT_EDEFAULT = LabelAlignment.CENTER;

    /**
     * The cached value of the '{@link #getLabelAlignment()
     * <em>Label Alignment</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelAlignment()
     * @generated
     * @ordered
     */
    protected LabelAlignment labelAlignment = LABEL_ALIGNMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues BACKGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "255,255,255");

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor = BACKGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeItemStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TreePackage.Literals.TREE_ITEM_STYLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__LABEL_SIZE, oldLabelSize, labelSize));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT, oldLabelFormat, labelFormat));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__SHOW_ICON, oldShowIcon, showIcon));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LabelAlignment getLabelAlignment() {
        return labelAlignment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelAlignment(LabelAlignment newLabelAlignment) {
        LabelAlignment oldLabelAlignment = labelAlignment;
        labelAlignment = newLabelAlignment == null ? LABEL_ALIGNMENT_EDEFAULT : newLabelAlignment;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT, oldLabelAlignment, labelAlignment));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__ICON_PATH, oldIconPath, iconPath));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBackgroundColor(RGBValues newBackgroundColor) {
        RGBValues oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_ITEM_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case TreePackage.TREE_ITEM_STYLE__LABEL_SIZE:
            return getLabelSize();
        case TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT:
            return getLabelFormat();
        case TreePackage.TREE_ITEM_STYLE__SHOW_ICON:
            return isShowIcon();
        case TreePackage.TREE_ITEM_STYLE__ICON_PATH:
            return getIconPath();
        case TreePackage.TREE_ITEM_STYLE__LABEL_COLOR:
            return getLabelColor();
        case TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT:
            return getLabelAlignment();
        case TreePackage.TREE_ITEM_STYLE__BACKGROUND_COLOR:
            return getBackgroundColor();
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
        case TreePackage.TREE_ITEM_STYLE__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT:
            setLabelFormat((FontFormat) newValue);
            return;
        case TreePackage.TREE_ITEM_STYLE__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case TreePackage.TREE_ITEM_STYLE__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case TreePackage.TREE_ITEM_STYLE__LABEL_COLOR:
            setLabelColor((RGBValues) newValue);
            return;
        case TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT:
            setLabelAlignment((LabelAlignment) newValue);
            return;
        case TreePackage.TREE_ITEM_STYLE__BACKGROUND_COLOR:
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
        case TreePackage.TREE_ITEM_STYLE__LABEL_SIZE:
            setLabelSize(LABEL_SIZE_EDEFAULT);
            return;
        case TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT:
            setLabelFormat(LABEL_FORMAT_EDEFAULT);
            return;
        case TreePackage.TREE_ITEM_STYLE__SHOW_ICON:
            setShowIcon(SHOW_ICON_EDEFAULT);
            return;
        case TreePackage.TREE_ITEM_STYLE__ICON_PATH:
            setIconPath(ICON_PATH_EDEFAULT);
            return;
        case TreePackage.TREE_ITEM_STYLE__LABEL_COLOR:
            setLabelColor(LABEL_COLOR_EDEFAULT);
            return;
        case TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT:
            setLabelAlignment(LABEL_ALIGNMENT_EDEFAULT);
            return;
        case TreePackage.TREE_ITEM_STYLE__BACKGROUND_COLOR:
            setBackgroundColor(BACKGROUND_COLOR_EDEFAULT);
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
        case TreePackage.TREE_ITEM_STYLE__LABEL_SIZE:
            return labelSize != LABEL_SIZE_EDEFAULT;
        case TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT:
            return labelFormat != LABEL_FORMAT_EDEFAULT;
        case TreePackage.TREE_ITEM_STYLE__SHOW_ICON:
            return showIcon != SHOW_ICON_EDEFAULT;
        case TreePackage.TREE_ITEM_STYLE__ICON_PATH:
            return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
        case TreePackage.TREE_ITEM_STYLE__LABEL_COLOR:
            return LABEL_COLOR_EDEFAULT == null ? labelColor != null : !LABEL_COLOR_EDEFAULT.equals(labelColor);
        case TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT:
            return labelAlignment != LABEL_ALIGNMENT_EDEFAULT;
        case TreePackage.TREE_ITEM_STYLE__BACKGROUND_COLOR:
            return BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == BasicLabelStyle.class) {
            switch (derivedFeatureID) {
            case TreePackage.TREE_ITEM_STYLE__LABEL_SIZE:
                return ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;
            case TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT:
                return ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;
            case TreePackage.TREE_ITEM_STYLE__SHOW_ICON:
                return ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;
            case TreePackage.TREE_ITEM_STYLE__ICON_PATH:
                return ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;
            case TreePackage.TREE_ITEM_STYLE__LABEL_COLOR:
                return ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyle.class) {
            switch (derivedFeatureID) {
            case TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT:
                return ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == BasicLabelStyle.class) {
            switch (baseFeatureID) {
            case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE:
                return TreePackage.TREE_ITEM_STYLE__LABEL_SIZE;
            case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT:
                return TreePackage.TREE_ITEM_STYLE__LABEL_FORMAT;
            case ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON:
                return TreePackage.TREE_ITEM_STYLE__SHOW_ICON;
            case ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH:
                return TreePackage.TREE_ITEM_STYLE__ICON_PATH;
            case ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR:
                return TreePackage.TREE_ITEM_STYLE__LABEL_COLOR;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyle.class) {
            switch (baseFeatureID) {
            case ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT:
                return TreePackage.TREE_ITEM_STYLE__LABEL_ALIGNMENT;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(", labelAlignment: ");
        result.append(labelAlignment);
        result.append(", backgroundColor: ");
        result.append(backgroundColor);
        result.append(')');
        return result.toString();
    }

} // TreeItemStyleImpl
