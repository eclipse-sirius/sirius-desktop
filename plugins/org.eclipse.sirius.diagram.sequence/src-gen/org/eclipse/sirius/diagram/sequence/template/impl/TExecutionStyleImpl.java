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
import org.eclipse.sirius.diagram.sequence.template.TExecutionStyle;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>TExecution Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl#getBorderColor
 * <em>Border Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.sequence.template.impl.TExecutionStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TExecutionStyleImpl extends TTransformerImpl implements TExecutionStyle {
    /**
     * The default value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBorderSizeComputationExpression()
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String borderSizeComputationExpression = TExecutionStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

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
    protected TExecutionStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.TEXECUTION_STYLE;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION, oldBorderSizeComputationExpression,
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR, oldBorderColor, borderColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR, oldBorderColor, borderColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ColorDescription getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (ColorDescription) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
                }
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
    @Override
    public void setBackgroundColor(ColorDescription newBackgroundColor) {
        ColorDescription oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
        case TemplatePackage.TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return getBorderSizeComputationExpression();
        case TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR:
            if (resolve) {
                return getBorderColor();
            }
            return basicGetBorderColor();
        case TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR:
            if (resolve) {
                return getBackgroundColor();
            }
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
        case TemplatePackage.TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression((String) newValue);
            return;
        case TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR:
            setBorderColor((ColorDescription) newValue);
            return;
        case TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR:
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
        case TemplatePackage.TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            setBorderSizeComputationExpression(TExecutionStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR:
            setBorderColor((ColorDescription) null);
            return;
        case TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR:
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
        case TemplatePackage.TEXECUTION_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION:
            return TExecutionStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? borderSizeComputationExpression != null : !TExecutionStyleImpl.BORDER_SIZE_COMPUTATION_EXPRESSION_EDEFAULT
                    .equals(borderSizeComputationExpression);
        case TemplatePackage.TEXECUTION_STYLE__BORDER_COLOR:
            return borderColor != null;
        case TemplatePackage.TEXECUTION_STYLE__BACKGROUND_COLOR:
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
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (borderSizeComputationExpression: "); //$NON-NLS-1$
        result.append(borderSizeComputationExpression);
        result.append(')');
        return result.toString();
    }

} // TExecutionStyleImpl
