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
package org.eclipse.sirius.tree.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tree.description.DescriptionPackage;
import org.eclipse.sirius.tree.description.TreeItemStyleDescription;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tree Item Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#isShowIcon
 * <em>Show Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getLabelColor
 * <em>Label Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getLabelAlignment
 * <em>Label Alignment</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tree.description.impl.TreeItemStyleDescriptionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TreeItemStyleDescriptionImpl extends EObjectImpl implements TreeItemStyleDescription {
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
     * The default value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = "feature:name";

    /**
     * The cached value of the '{@link #getLabelExpression()
     * <em>Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = LABEL_EXPRESSION_EDEFAULT;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TreeItemStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.TREE_ITEM_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLabelExpression() {
        return labelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLabelExpression(String newLabelExpression) {
        String oldLabelExpression = labelExpression;
        labelExpression = newLabelExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT, oldLabelAlignment, labelAlignment));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription getLabelColor() {
        if (labelColor != null && labelColor.eIsProxy()) {
            InternalEObject oldLabelColor = (InternalEObject) labelColor;
            labelColor = (ColorDescription) eResolveProxy(oldLabelColor);
            if (labelColor != oldLabelColor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
    public void setLabelColor(ColorDescription newLabelColor) {
        ColorDescription oldLabelColor = labelColor;
        labelColor = newLabelColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE, oldLabelSize, labelSize));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT, oldLabelFormat, labelFormat));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON, oldShowIcon, showIcon));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (ColorDescription) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
    public void setBackgroundColor(ColorDescription newBackgroundColor) {
        ColorDescription oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE:
            return getLabelSize();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT:
            return getLabelFormat();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON:
            return isShowIcon();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR:
            if (resolve)
                return getLabelColor();
            return basicGetLabelColor();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH:
            return getIconPath();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            return getLabelAlignment();
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            if (resolve)
                return getBackgroundColor();
            return basicGetBackgroundColor();
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
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT:
            setLabelFormat((FontFormat) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            setLabelAlignment((LabelAlignment) newValue);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) newValue);
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
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize(LABEL_SIZE_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT:
            setLabelFormat(LABEL_FORMAT_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon(SHOW_ICON_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) null);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath(ICON_PATH_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            setLabelAlignment(LABEL_ALIGNMENT_EDEFAULT);
            return;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) null);
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
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE:
            return labelSize != LABEL_SIZE_EDEFAULT;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT:
            return labelFormat != LABEL_FORMAT_EDEFAULT;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON:
            return showIcon != SHOW_ICON_EDEFAULT;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR:
            return labelColor != null;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH:
            return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            return labelAlignment != LABEL_ALIGNMENT_EDEFAULT;
        case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__BACKGROUND_COLOR:
            return backgroundColor != null;
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
        if (baseClass == BasicLabelStyleDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE:
                return StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT:
                return StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON:
                return StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION:
                return StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR:
                return StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH:
                return StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyleDescription.class) {
            switch (derivedFeatureID) {
            case DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
                return StylePackage.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT;
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
        if (baseClass == BasicLabelStyleDescription.class) {
            switch (baseFeatureID) {
            case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_SIZE;
            case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_FORMAT;
            case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__SHOW_ICON;
            case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_EXPRESSION;
            case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_COLOR;
            case StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__ICON_PATH;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyleDescription.class) {
            switch (baseFeatureID) {
            case StylePackage.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
                return DescriptionPackage.TREE_ITEM_STYLE_DESCRIPTION__LABEL_ALIGNMENT;
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
        result.append(", labelExpression: ");
        result.append(labelExpression);
        result.append(", iconPath: ");
        result.append(iconPath);
        result.append(", labelAlignment: ");
        result.append(labelAlignment);
        result.append(')');
        return result.toString();
    }

} // TreeItemStyleDescriptionImpl
