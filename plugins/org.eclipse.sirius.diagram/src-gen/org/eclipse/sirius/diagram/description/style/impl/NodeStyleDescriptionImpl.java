/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Node Style Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getBorderColor <em>Border
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getBorderLineStyle <em>Border
 * Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getLabelSize <em>Label
 * Size</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getLabelFormat <em>Label
 * Format</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#isShowIcon <em>Show
 * Icon</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getLabelExpression <em>Label
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getLabelColor <em>Label
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getIconPath <em>Icon
 * Path</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getLabelAlignment <em>Label
 * Alignment</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getTooltipExpression
 * <em>Tooltip Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#isHideLabelByDefault <em>Hide
 * Label By Default</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getSizeComputationExpression
 * <em>Size Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getLabelPosition <em>Label
 * Position</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getResizeKind <em>Resize
 * Kind</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.NodeStyleDescriptionImpl#getForbiddenSides <em>Forbidden
 * Sides</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NodeStyleDescriptionImpl extends MinimalEObjectImpl.Container implements NodeStyleDescription {

    /**
     * The default value of the '{@link #getBorderSizeComputationExpression() <em>Border Size Computation
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBorderSizeComputationExpression() <em>Border Size Computation
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String borderSizeComputationExpression = NodeStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected ColorDescription borderColor;

    /**
     * The default value of the '{@link #getBorderLineStyle() <em>Border Line Style</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderLineStyle()
     * @generated
     * @ordered
     */
    protected static final LineStyle BORDER_LINE_STYLE_EDEFAULT = LineStyle.SOLID_LITERAL;

    /**
     * The cached value of the '{@link #getBorderLineStyle() <em>Border Line Style</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getBorderLineStyle()
     * @generated
     * @ordered
     */
    protected LineStyle borderLineStyle = NodeStyleDescriptionImpl.BORDER_LINE_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelSize() <em>Label Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected static final int LABEL_SIZE_EDEFAULT = 8;

    /**
     * The cached value of the '{@link #getLabelSize() <em>Label Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelSize()
     * @generated
     * @ordered
     */
    protected int labelSize = NodeStyleDescriptionImpl.LABEL_SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabelFormat() <em>Label Format</em>}' attribute list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getLabelFormat()
     * @generated
     * @ordered
     */
    protected EList<FontFormat> labelFormat;

    /**
     * The default value of the '{@link #isShowIcon() <em>Show Icon</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isShowIcon()
     * @generated
     * @ordered
     */
    protected static final boolean SHOW_ICON_EDEFAULT = true;

    /**
     * The cached value of the '{@link #isShowIcon() <em>Show Icon</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isShowIcon()
     * @generated
     * @ordered
     */
    protected boolean showIcon = NodeStyleDescriptionImpl.SHOW_ICON_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String LABEL_EXPRESSION_EDEFAULT = "feature:name"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLabelExpression() <em>Label Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelExpression()
     * @generated
     * @ordered
     */
    protected String labelExpression = NodeStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getLabelColor() <em>Label Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected ColorDescription labelColor;

    /**
     * The default value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected static final String ICON_PATH_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getIconPath() <em>Icon Path</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getIconPath()
     * @generated
     * @ordered
     */
    protected String iconPath = NodeStyleDescriptionImpl.ICON_PATH_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelAlignment() <em>Label Alignment</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelAlignment()
     * @generated
     * @ordered
     */
    protected static final LabelAlignment LABEL_ALIGNMENT_EDEFAULT = LabelAlignment.CENTER;

    /**
     * The cached value of the '{@link #getLabelAlignment() <em>Label Alignment</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getLabelAlignment()
     * @generated
     * @ordered
     */
    protected LabelAlignment labelAlignment = NodeStyleDescriptionImpl.LABEL_ALIGNMENT_EDEFAULT;

    /**
     * The default value of the '{@link #getTooltipExpression() <em>Tooltip Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected static final String TOOLTIP_EXPRESSION_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getTooltipExpression() <em>Tooltip Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTooltipExpression()
     * @generated
     * @ordered
     */
    protected String tooltipExpression = NodeStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #isHideLabelByDefault() <em>Hide Label By Default</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isHideLabelByDefault()
     * @generated
     * @ordered
     */
    protected static final boolean HIDE_LABEL_BY_DEFAULT_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isHideLabelByDefault() <em>Hide Label By Default</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isHideLabelByDefault()
     * @generated
     * @ordered
     */
    protected boolean hideLabelByDefault = NodeStyleDescriptionImpl.HIDE_LABEL_BY_DEFAULT_EDEFAULT;

    /**
     * The default value of the '{@link #getSizeComputationExpression() <em>Size Computation Expression</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "3"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getSizeComputationExpression() <em>Size Computation Expression</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String sizeComputationExpression = NodeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelPosition() <em>Label Position</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
    protected static final LabelPosition LABEL_POSITION_EDEFAULT = LabelPosition.BORDER_LITERAL;

    /**
     * The cached value of the '{@link #getLabelPosition() <em>Label Position</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getLabelPosition()
     * @generated
     * @ordered
     */
    protected LabelPosition labelPosition = NodeStyleDescriptionImpl.LABEL_POSITION_EDEFAULT;

    /**
     * The default value of the '{@link #getResizeKind() <em>Resize Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getResizeKind()
     * @generated
     * @ordered
     */
    protected static final ResizeKind RESIZE_KIND_EDEFAULT = ResizeKind.NONE_LITERAL;

    /**
     * The cached value of the '{@link #getResizeKind() <em>Resize Kind</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getResizeKind()
     * @generated
     * @ordered
     */
    protected ResizeKind resizeKind = NodeStyleDescriptionImpl.RESIZE_KIND_EDEFAULT;

    /**
     * The cached value of the '{@link #getForbiddenSides() <em>Forbidden Sides</em>}' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getForbiddenSides()
     * @generated
     * @ordered
     */
    protected EList<Side> forbiddenSides;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.NODE_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getBorderSizeComputationExpression() {
        return borderSizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderSizeComputationExpression(String newBorderSizeComputationExpression) {
        String oldBorderSizeComputationExpression = borderSizeComputationExpression;
        borderSizeComputationExpression = newBorderSizeComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION, oldBorderSizeComputationExpression,
                    borderSizeComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getBorderColor() {
        if (borderColor != null && borderColor.eIsProxy()) {
            InternalEObject oldBorderColor = (InternalEObject) borderColor;
            borderColor = (ColorDescription) eResolveProxy(oldBorderColor);
            if (borderColor != oldBorderColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR, oldBorderColor, borderColor));
                }
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
    @Override
    public void setBorderColor(ColorDescription newBorderColor) {
        ColorDescription oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR, oldBorderColor, borderColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LineStyle getBorderLineStyle() {
        return borderLineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderLineStyle(LineStyle newBorderLineStyle) {
        LineStyle oldBorderLineStyle = borderLineStyle;
        borderLineStyle = newBorderLineStyle == null ? NodeStyleDescriptionImpl.BORDER_LINE_STYLE_EDEFAULT : newBorderLineStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE, oldBorderLineStyle, borderLineStyle));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE, oldLabelSize, labelSize));
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
            labelFormat = new EDataTypeUniqueEList<FontFormat>(FontFormat.class, this, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT);
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON, oldShowIcon, showIcon));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR, oldLabelColor, labelColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH, oldIconPath, iconPath));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelAlignment getLabelAlignment() {
        return labelAlignment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelAlignment(LabelAlignment newLabelAlignment) {
        LabelAlignment oldLabelAlignment = labelAlignment;
        labelAlignment = newLabelAlignment == null ? NodeStyleDescriptionImpl.LABEL_ALIGNMENT_EDEFAULT : newLabelAlignment;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT, oldLabelAlignment, labelAlignment));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getTooltipExpression() {
        return tooltipExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTooltipExpression(String newTooltipExpression) {
        String oldTooltipExpression = tooltipExpression;
        tooltipExpression = newTooltipExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION, oldTooltipExpression, tooltipExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getSizeComputationExpression() {
        return sizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSizeComputationExpression(String newSizeComputationExpression) {
        String oldSizeComputationExpression = sizeComputationExpression;
        sizeComputationExpression = newSizeComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION, oldSizeComputationExpression, sizeComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelPosition getLabelPosition() {
        return labelPosition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelPosition(LabelPosition newLabelPosition) {
        LabelPosition oldLabelPosition = labelPosition;
        labelPosition = newLabelPosition == null ? NodeStyleDescriptionImpl.LABEL_POSITION_EDEFAULT : newLabelPosition;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION, oldLabelPosition, labelPosition));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isHideLabelByDefault() {
        return hideLabelByDefault;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHideLabelByDefault(boolean newHideLabelByDefault) {
        boolean oldHideLabelByDefault = hideLabelByDefault;
        hideLabelByDefault = newHideLabelByDefault;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT, oldHideLabelByDefault, hideLabelByDefault));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResizeKind getResizeKind() {
        return resizeKind;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setResizeKind(ResizeKind newResizeKind) {
        ResizeKind oldResizeKind = resizeKind;
        resizeKind = newResizeKind == null ? NodeStyleDescriptionImpl.RESIZE_KIND_EDEFAULT : newResizeKind;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND, oldResizeKind, resizeKind));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<Side> getForbiddenSides() {
        if (forbiddenSides == null) {
            forbiddenSides = new EDataTypeUniqueEList<Side>(Side.class, this, StylePackage.NODE_STYLE_DESCRIPTION__FORBIDDEN_SIDES);
        }
        return forbiddenSides;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return getBorderSizeComputationExpression();
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR:
            if (resolve) {
                return getBorderColor();
            }
            return basicGetBorderColor();
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE:
            return getBorderLineStyle();
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE:
            return getLabelSize();
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT:
            return getLabelFormat();
        case StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON:
            return isShowIcon();
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return getLabelExpression();
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR:
            if (resolve) {
                return getLabelColor();
            }
            return basicGetLabelColor();
        case StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH:
            return getIconPath();
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            return getLabelAlignment();
        case StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            return getTooltipExpression();
        case StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
            return isHideLabelByDefault();
        case StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            return getSizeComputationExpression();
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION:
            return getLabelPosition();
        case StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND:
            return getResizeKind();
        case StylePackage.NODE_STYLE_DESCRIPTION__FORBIDDEN_SIDES:
            return getForbiddenSides();
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
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression((String) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR:
            setBorderColor((ColorDescription) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE:
            setBorderLineStyle((LineStyle) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize((Integer) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT:
            getLabelFormat().clear();
            getLabelFormat().addAll((Collection<? extends FontFormat>) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon((Boolean) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath((String) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            setLabelAlignment((LabelAlignment) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression((String) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
            setHideLabelByDefault((Boolean) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            setSizeComputationExpression((String) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION:
            setLabelPosition((LabelPosition) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND:
            setResizeKind((ResizeKind) newValue);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__FORBIDDEN_SIDES:
            getForbiddenSides().clear();
            getForbiddenSides().addAll((Collection<? extends Side>) newValue);
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
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression(NodeStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR:
            setBorderColor((ColorDescription) null);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE:
            setBorderLineStyle(NodeStyleDescriptionImpl.BORDER_LINE_STYLE_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE:
            setLabelSize(NodeStyleDescriptionImpl.LABEL_SIZE_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT:
            getLabelFormat().clear();
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON:
            setShowIcon(NodeStyleDescriptionImpl.SHOW_ICON_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            setLabelExpression(NodeStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR:
            setLabelColor((ColorDescription) null);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH:
            setIconPath(NodeStyleDescriptionImpl.ICON_PATH_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            setLabelAlignment(NodeStyleDescriptionImpl.LABEL_ALIGNMENT_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            setTooltipExpression(NodeStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
            setHideLabelByDefault(NodeStyleDescriptionImpl.HIDE_LABEL_BY_DEFAULT_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            setSizeComputationExpression(NodeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION:
            setLabelPosition(NodeStyleDescriptionImpl.LABEL_POSITION_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND:
            setResizeKind(NodeStyleDescriptionImpl.RESIZE_KIND_EDEFAULT);
            return;
        case StylePackage.NODE_STYLE_DESCRIPTION__FORBIDDEN_SIDES:
            getForbiddenSides().clear();
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
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return NodeStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? borderSizeComputationExpression != null
                    : !NodeStyleDescriptionImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(borderSizeComputationExpression);
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR:
            return borderColor != null;
        case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE:
            return borderLineStyle != NodeStyleDescriptionImpl.BORDER_LINE_STYLE_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE:
            return labelSize != NodeStyleDescriptionImpl.LABEL_SIZE_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT:
            return labelFormat != null && !labelFormat.isEmpty();
        case StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON:
            return showIcon != NodeStyleDescriptionImpl.SHOW_ICON_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION:
            return NodeStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !NodeStyleDescriptionImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR:
            return labelColor != null;
        case StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH:
            return NodeStyleDescriptionImpl.ICON_PATH_EDEFAULT == null ? iconPath != null : !NodeStyleDescriptionImpl.ICON_PATH_EDEFAULT.equals(iconPath);
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
            return labelAlignment != NodeStyleDescriptionImpl.LABEL_ALIGNMENT_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
            return NodeStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT == null ? tooltipExpression != null : !NodeStyleDescriptionImpl.TOOLTIP_EXPRESSION_EDEFAULT.equals(tooltipExpression);
        case StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
            return hideLabelByDefault != NodeStyleDescriptionImpl.HIDE_LABEL_BY_DEFAULT_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION:
            return NodeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? sizeComputationExpression != null
                    : !NodeStyleDescriptionImpl.SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(sizeComputationExpression);
        case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION:
            return labelPosition != NodeStyleDescriptionImpl.LABEL_POSITION_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND:
            return resizeKind != NodeStyleDescriptionImpl.RESIZE_KIND_EDEFAULT;
        case StylePackage.NODE_STYLE_DESCRIPTION__FORBIDDEN_SIDES:
            return forbiddenSides != null && !forbiddenSides.isEmpty();
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
            case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION:
                return StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;
            case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR:
                return StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR;
            case StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE:
                return StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_LINE_STYLE;
            default:
                return -1;
            }
        }
        if (baseClass == BasicLabelStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;
            case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;
            case StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;
            case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;
            case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;
            case StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT;
            default:
                return -1;
            }
        }
        if (baseClass == TooltipStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
                return org.eclipse.sirius.viewpoint.description.style.StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == HideLabelCapabilityStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
                return StylePackage.HIDE_LABEL_CAPABILITY_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;
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
                return StylePackage.NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;
            case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR:
                return StylePackage.NODE_STYLE_DESCRIPTION__BORDER_COLOR;
            case StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_LINE_STYLE:
                return StylePackage.NODE_STYLE_DESCRIPTION__BORDER_LINE_STYLE;
            default:
                return -1;
            }
        }
        if (baseClass == BasicLabelStyleDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE:
                return StylePackage.NODE_STYLE_DESCRIPTION__LABEL_SIZE;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT:
                return StylePackage.NODE_STYLE_DESCRIPTION__LABEL_FORMAT;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON:
                return StylePackage.NODE_STYLE_DESCRIPTION__SHOW_ICON;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION:
                return StylePackage.NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR:
                return StylePackage.NODE_STYLE_DESCRIPTION__LABEL_COLOR;
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH:
                return StylePackage.NODE_STYLE_DESCRIPTION__ICON_PATH;
            default:
                return -1;
            }
        }
        if (baseClass == LabelStyleDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT:
                return StylePackage.NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;
            default:
                return -1;
            }
        }
        if (baseClass == TooltipStyleDescription.class) {
            switch (baseFeatureID) {
            case org.eclipse.sirius.viewpoint.description.style.StylePackage.TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION:
                return StylePackage.NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;
            default:
                return -1;
            }
        }
        if (baseClass == HideLabelCapabilityStyleDescription.class) {
            switch (baseFeatureID) {
            case StylePackage.HIDE_LABEL_CAPABILITY_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT:
                return StylePackage.NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;
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
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (borderSizeComputationExpression: "); //$NON-NLS-1$
        result.append(borderSizeComputationExpression);
        result.append(", borderLineStyle: "); //$NON-NLS-1$
        result.append(borderLineStyle);
        result.append(", labelSize: "); //$NON-NLS-1$
        result.append(labelSize);
        result.append(", labelFormat: "); //$NON-NLS-1$
        result.append(labelFormat);
        result.append(", showIcon: "); //$NON-NLS-1$
        result.append(showIcon);
        result.append(", labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(", iconPath: "); //$NON-NLS-1$
        result.append(iconPath);
        result.append(", labelAlignment: "); //$NON-NLS-1$
        result.append(labelAlignment);
        result.append(", tooltipExpression: "); //$NON-NLS-1$
        result.append(tooltipExpression);
        result.append(", hideLabelByDefault: "); //$NON-NLS-1$
        result.append(hideLabelByDefault);
        result.append(", sizeComputationExpression: "); //$NON-NLS-1$
        result.append(sizeComputationExpression);
        result.append(", labelPosition: "); //$NON-NLS-1$
        result.append(labelPosition);
        result.append(", resizeKind: "); //$NON-NLS-1$
        result.append(resizeKind);
        result.append(", forbiddenSides: "); //$NON-NLS-1$
        result.append(forbiddenSides);
        result.append(')');
        return result.toString();
    }
} // NodeStyleDescriptionImpl
