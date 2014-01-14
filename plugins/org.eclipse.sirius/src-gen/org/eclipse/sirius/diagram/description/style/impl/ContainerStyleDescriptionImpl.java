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
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Container Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getBorderColor
 * <em>Border Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#isShowIcon
 * <em>Show Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getLabelColor
 * <em>Label Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getIconPath
 * <em>Icon Path</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getLabelAlignment
 * <em>Label Alignment</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#getTooltipExpression
 * <em>Tooltip Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ContainerStyleDescriptionImpl#isRoundedCorner
 * <em>Rounded Corner</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ContainerStyleDescriptionImpl extends RoundedCornerStyleDescriptionImpl implements ContainerStyleDescription {
    /**
     * The default value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "0";

    /**
     * The cached value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String borderSizeComputationExpression = BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected ColorDescription borderColor;

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
     * The default value of the '{@link #getTooltipExpression()
     * <em>Tooltip Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected static final String TOOLTIP_EXPRESSION_EDEFAULT = "";

    /**
     * The cached value of the '{@link #getTooltipExpression()
     * <em>Tooltip Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected String tooltipExpression = TOOLTIP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isRoundedCorner()
     * <em>Rounded Corner</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isRoundedCorner()
     * @generated
     * @ordered
     */
    protected static final boolean ROUNDED_CORNER_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isRoundedCorner()
     * <em>Rounded Corner</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #isRoundedCorner()
     * @generated
     * @ordered
     */
    protected boolean roundedCorner = ROUNDED_CORNER_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ContainerStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.CONTAINER_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getBorderSizeComputationExpression() {
        return borderSizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBorderSizeComputationExpression(String newBorderSizeComputationExpression) {
        String oldBorderSizeComputationExpression = borderSizeComputationExpression;
        borderSizeComputationExpression = newBorderSizeComputationExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION, oldBorderSizeComputationExpression,
                    borderSizeComputationExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription getBorderColor() {
        if (borderColor != null && borderColor.eIsProxy()) {
            InternalEObject oldBorderColor = (InternalEObject) borderColor;
            borderColor = (ColorDescription) eResolveProxy(oldBorderColor);
            if (borderColor != oldBorderColor) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR, oldBorderColor, borderColor));
            }
        }
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription basicGetBorderColor() {
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBorderColor(ColorDescription newBorderColor) {
        ColorDescription oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR, oldBorderColor, borderColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE, oldLabelSize, labelSize));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT, oldLabelFormat, labelFormat));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON, oldShowIcon, showIcon));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT, oldLabelAlignment, labelAlignment));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getTooltipExpression() {
        return tooltipExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTooltipExpression(String newTooltipExpression) {
        String oldTooltipExpression = tooltipExpression;
        tooltipExpression = newTooltipExpression;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION, oldTooltipExpression, tooltipExpression));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isRoundedCorner() {
        return roundedCorner;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRoundedCorner(boolean newRoundedCorner) {
        boolean oldRoundedCorner = roundedCorner;
        roundedCorner = newRoundedCorner;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER, oldRoundedCorner, roundedCorner));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return getBorderSizeComputationExpression();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR:
            if (resolve)
                return getBorderColor();
            return basicGetBorderColor();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE:
            return getLabelSize();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT:
            return getLabelFormat();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON:
            return isShowIcon();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR:
            if (resolve)
                return getLabelColor();
            return basicGetLabelColor();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH:
            return getIconPath();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            return getLabelAlignment();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            return getTooltipExpression();
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER:
            return isRoundedCorner();
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
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression((String) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR:
            setBorderColor((ColorDescription) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT:
            setLabelFormat((FontFormat) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            setLabelAlignment((LabelAlignment) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression((String) newValue);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER:
            setRoundedCorner((Boolean) newValue);
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
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression(BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR:
            setBorderColor((ColorDescription) null);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize(LABEL_SIZE_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT:
            setLabelFormat(LABEL_FORMAT_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon(SHOW_ICON_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(LABEL_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) null);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath(ICON_PATH_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            setLabelAlignment(LABEL_ALIGNMENT_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression(TOOLTIP_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER:
            setRoundedCorner(ROUNDED_CORNER_EDEFAULT);
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
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? borderSizeComputationExpression != null : !BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(borderSizeComputationExpression);
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR:
            return borderColor != null;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE:
            return labelSize != LABEL_SIZE_EDEFAULT;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT:
            return labelFormat != LABEL_FORMAT_EDEFAULT;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON:
            return showIcon != SHOW_ICON_EDEFAULT;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR:
            return labelColor != null;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH:
            return ICON_PATH_EDEFAULT == null ? iconPath != null : !ICON_PATH_EDEFAULT.equals(iconPath);
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            return labelAlignment != LABEL_ALIGNMENT_EDEFAULT;
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            return TOOLTIP_EXPRESSION_EDEFAULT == null ? tooltipExpression != null : !TOOLTIP_EXPRESSION_EDEFAULT.equals(tooltipExpression);
        case StylePackage.CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER:
            return roundedCorner != ROUNDED_CORNER_EDEFAULT;
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
        if (baseClass == BorderedStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
                return StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR:
                return StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR;
            default:
                return -1;
            }
        }
        if (baseClass == BasicLabelStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT;
            default:
                return -1;
            }
        }
        if (baseClass == TooltipStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;
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
        if (baseClass == BorderedStyleDescription.class) {
            switch (baseFeatureID) {
            case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;
            case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR;
            default:
                return -1;
            }
        }
        if (baseClass == BasicLabelStyleDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__SHOW_ICON;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__ICON_PATH;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyleDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT;
            default:
                return -1;
            }
        }
        if (baseClass == TooltipStyleDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
                return StylePackage.CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;
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
        result.append(" (borderSizeComputationExpression: ");
        result.append(borderSizeComputationExpression);
        result.append(", labelSize: ");
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
        result.append(", tooltipExpression: ");
        result.append(tooltipExpression);
        result.append(", roundedCorner: ");
        result.append(roundedCorner);
        result.append(')');
        return result.toString();
    }

} // ContainerStyleDescriptionImpl
