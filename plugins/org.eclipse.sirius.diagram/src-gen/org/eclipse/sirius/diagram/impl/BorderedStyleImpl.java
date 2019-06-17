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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.impl.StyleImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Bordered Style</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl#getBorderSize <em>Border Size</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl#getBorderSizeComputationExpression <em>Border Size
 * Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl#getBorderColor <em>Border Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl#getBorderLineStyle <em>Border Line Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BorderedStyleImpl extends StyleImpl implements BorderedStyle {
    /**
     * The default value of the '{@link #getBorderSize() <em>Border Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected static final Integer BORDER_SIZE_EDEFAULT = new Integer(0);

    /**
     * The cached value of the '{@link #getBorderSize() <em>Border Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected Integer borderSize = BorderedStyleImpl.BORDER_SIZE_EDEFAULT;

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
    protected String borderSizeComputationExpression = BorderedStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues BORDER_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "0,0,0"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected RGBValues borderColor = BorderedStyleImpl.BORDER_COLOR_EDEFAULT;

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
    protected LineStyle borderLineStyle = BorderedStyleImpl.BORDER_LINE_STYLE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BorderedStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.BORDERED_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getBorderSize() {
        return borderSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderSize(Integer newBorderSize) {
        Integer oldBorderSize = borderSize;
        borderSize = newBorderSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BORDERED_STYLE__BORDER_SIZE, oldBorderSize, borderSize));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION, oldBorderSizeComputationExpression,
                    borderSizeComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getBorderColor() {
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderColor(RGBValues newBorderColor) {
        RGBValues oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BORDERED_STYLE__BORDER_COLOR, oldBorderColor, borderColor));
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
        borderLineStyle = newBorderLineStyle == null ? BorderedStyleImpl.BORDER_LINE_STYLE_EDEFAULT : newBorderLineStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BORDERED_STYLE__BORDER_LINE_STYLE, oldBorderLineStyle, borderLineStyle));
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
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE:
            return getBorderSize();
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return getBorderSizeComputationExpression();
        case DiagramPackage.BORDERED_STYLE__BORDER_COLOR:
            return getBorderColor();
        case DiagramPackage.BORDERED_STYLE__BORDER_LINE_STYLE:
            return getBorderLineStyle();
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
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE:
            setBorderSize((Integer) newValue);
            return;
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression((String) newValue);
            return;
        case DiagramPackage.BORDERED_STYLE__BORDER_COLOR:
            setBorderColor((RGBValues) newValue);
            return;
        case DiagramPackage.BORDERED_STYLE__BORDER_LINE_STYLE:
            setBorderLineStyle((LineStyle) newValue);
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
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE:
            setBorderSize(BorderedStyleImpl.BORDER_SIZE_EDEFAULT);
            return;
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression(BorderedStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case DiagramPackage.BORDERED_STYLE__BORDER_COLOR:
            setBorderColor(BorderedStyleImpl.BORDER_COLOR_EDEFAULT);
            return;
        case DiagramPackage.BORDERED_STYLE__BORDER_LINE_STYLE:
            setBorderLineStyle(BorderedStyleImpl.BORDER_LINE_STYLE_EDEFAULT);
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
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE:
            return BorderedStyleImpl.BORDER_SIZE_EDEFAULT == null ? borderSize != null : !BorderedStyleImpl.BORDER_SIZE_EDEFAULT.equals(borderSize);
        case DiagramPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return BorderedStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? borderSizeComputationExpression != null
                    : !BorderedStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(borderSizeComputationExpression);
        case DiagramPackage.BORDERED_STYLE__BORDER_COLOR:
            return BorderedStyleImpl.BORDER_COLOR_EDEFAULT == null ? borderColor != null : !BorderedStyleImpl.BORDER_COLOR_EDEFAULT.equals(borderColor);
        case DiagramPackage.BORDERED_STYLE__BORDER_LINE_STYLE:
            return borderLineStyle != BorderedStyleImpl.BORDER_LINE_STYLE_EDEFAULT;
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (borderSize: "); //$NON-NLS-1$
        result.append(borderSize);
        result.append(", borderSizeComputationExpression: "); //$NON-NLS-1$
        result.append(borderSizeComputationExpression);
        result.append(", borderColor: "); //$NON-NLS-1$
        result.append(borderColor);
        result.append(", borderLineStyle: "); //$NON-NLS-1$
        result.append(borderLineStyle);
        result.append(')');
        return result.toString();
    }

} // BorderedStyleImpl
