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
package org.eclipse.sirius.diagram.sequence.template.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.sequence.template.TMessageStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TMessage Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl#getStrokeColor
 * <em>Stroke Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl#getLineStyle
 * <em>Line Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl#getSourceArrow
 * <em>Source Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl#getTargetArrow
 * <em>Target Arrow</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TMessageStyleImpl#getLabelExpression
 * <em>Label Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TMessageStyleImpl extends TTransformerImpl implements TMessageStyle {
    /**
     * The cached value of the '{@link #getStrokeColor() <em>Stroke Color</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getStrokeColor()
     * @generated
     * @ordered
     */
    protected ColorDescription strokeColor;

    /**
     * The default value of the '{@link #getLineStyle() <em>Line Style</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected static final LineStyle LINE_STYLE_EDEFAULT = LineStyle.SOLID_LITERAL;

    /**
     * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected LineStyle lineStyle = TMessageStyleImpl.LINE_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceArrow() <em>Source Arrow</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourceArrow()
     * @generated
     * @ordered
     */
    protected static final EdgeArrows SOURCE_ARROW_EDEFAULT = EdgeArrows.NO_DECORATION_LITERAL;

    /**
     * The cached value of the '{@link #getSourceArrow() <em>Source Arrow</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getSourceArrow()
     * @generated
     * @ordered
     */
    protected EdgeArrows sourceArrow = TMessageStyleImpl.SOURCE_ARROW_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetArrow() <em>Target Arrow</em>}
     * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetArrow()
     * @generated
     * @ordered
     */
    protected static final EdgeArrows TARGET_ARROW_EDEFAULT = EdgeArrows.INPUT_ARROW_LITERAL;

    /**
     * The cached value of the '{@link #getTargetArrow() <em>Target Arrow</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTargetArrow()
     * @generated
     * @ordered
     */
    protected EdgeArrows targetArrow = TMessageStyleImpl.TARGET_ARROW_EDEFAULT;

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
    protected String labelExpression = TMessageStyleImpl.LABEL_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TMessageStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TMESSAGE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColorDescription getStrokeColor() {
        if (strokeColor != null && strokeColor.eIsProxy()) {
            InternalEObject oldStrokeColor = (InternalEObject) strokeColor;
            strokeColor = (ColorDescription) eResolveProxy(oldStrokeColor);
            if (strokeColor != oldStrokeColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR, oldStrokeColor, strokeColor));
                }
            }
        }
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ColorDescription basicGetStrokeColor() {
        return strokeColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStrokeColor(ColorDescription newStrokeColor) {
        ColorDescription oldStrokeColor = strokeColor;
        strokeColor = newStrokeColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR, oldStrokeColor, strokeColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LineStyle getLineStyle() {
        return lineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setLineStyle(LineStyle newLineStyle) {
        LineStyle oldLineStyle = lineStyle;
        lineStyle = newLineStyle == null ? TMessageStyleImpl.LINE_STYLE_EDEFAULT : newLineStyle;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_STYLE__LINE_STYLE, oldLineStyle, lineStyle));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EdgeArrows getSourceArrow() {
        return sourceArrow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setSourceArrow(EdgeArrows newSourceArrow) {
        EdgeArrows oldSourceArrow = sourceArrow;
        sourceArrow = newSourceArrow == null ? TMessageStyleImpl.SOURCE_ARROW_EDEFAULT : newSourceArrow;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW, oldSourceArrow, sourceArrow));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EdgeArrows getTargetArrow() {
        return targetArrow;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setTargetArrow(EdgeArrows newTargetArrow) {
        EdgeArrows oldTargetArrow = targetArrow;
        targetArrow = newTargetArrow == null ? TMessageStyleImpl.TARGET_ARROW_EDEFAULT : newTargetArrow;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW, oldTargetArrow, targetArrow));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION, oldLabelExpression, labelExpression));
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
        case TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR:
            if (resolve) {
                return getStrokeColor();
            }
            return basicGetStrokeColor();
        case TemplatePackage.TMESSAGE_STYLE__LINE_STYLE:
            return getLineStyle();
        case TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW:
            return getSourceArrow();
        case TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW:
            return getTargetArrow();
        case TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION:
            return getLabelExpression();
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
        case TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR:
            setStrokeColor((ColorDescription) newValue);
            return;
        case TemplatePackage.TMESSAGE_STYLE__LINE_STYLE:
            setLineStyle((LineStyle) newValue);
            return;
        case TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW:
            setSourceArrow((EdgeArrows) newValue);
            return;
        case TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW:
            setTargetArrow((EdgeArrows) newValue);
            return;
        case TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION:
            setLabelExpression((String) newValue);
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
        case TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR:
            setStrokeColor((ColorDescription) null);
            return;
        case TemplatePackage.TMESSAGE_STYLE__LINE_STYLE:
            setLineStyle(TMessageStyleImpl.LINE_STYLE_EDEFAULT);
            return;
        case TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW:
            setSourceArrow(TMessageStyleImpl.SOURCE_ARROW_EDEFAULT);
            return;
        case TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW:
            setTargetArrow(TMessageStyleImpl.TARGET_ARROW_EDEFAULT);
            return;
        case TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION:
            setLabelExpression(TMessageStyleImpl.LABEL_EXPRESSION_EDEFAULT);
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
        case TemplatePackage.TMESSAGE_STYLE__STROKE_COLOR:
            return strokeColor != null;
        case TemplatePackage.TMESSAGE_STYLE__LINE_STYLE:
            return lineStyle != TMessageStyleImpl.LINE_STYLE_EDEFAULT;
        case TemplatePackage.TMESSAGE_STYLE__SOURCE_ARROW:
            return sourceArrow != TMessageStyleImpl.SOURCE_ARROW_EDEFAULT;
        case TemplatePackage.TMESSAGE_STYLE__TARGET_ARROW:
            return targetArrow != TMessageStyleImpl.TARGET_ARROW_EDEFAULT;
        case TemplatePackage.TMESSAGE_STYLE__LABEL_EXPRESSION:
            return TMessageStyleImpl.LABEL_EXPRESSION_EDEFAULT == null ? labelExpression != null : !TMessageStyleImpl.LABEL_EXPRESSION_EDEFAULT.equals(labelExpression);
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
        result.append(" (lineStyle: "); //$NON-NLS-1$
        result.append(lineStyle);
        result.append(", sourceArrow: "); //$NON-NLS-1$
        result.append(sourceArrow);
        result.append(", targetArrow: "); //$NON-NLS-1$
        result.append(targetArrow);
        result.append(", labelExpression: "); //$NON-NLS-1$
        result.append(labelExpression);
        result.append(')');
        return result.toString();
    }

} // TMessageStyleImpl
