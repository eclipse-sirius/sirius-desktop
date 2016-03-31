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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dot</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.DotImpl#getStrokeSizeComputationExpression
 * <em>Stroke Size Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.DotImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DotImpl extends NodeStyleImpl implements Dot {
    /**
     * The default value of the '{@link #getStrokeSizeComputationExpression()
     * <em>Stroke Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStrokeSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT = "2"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getStrokeSizeComputationExpression()
     * <em>Stroke Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getStrokeSizeComputationExpression()
     * @generated
     * @ordered
     */
    protected String strokeSizeComputationExpression = DotImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues BACKGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "136,136,136"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor = DotImpl.BACKGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DotImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.DOT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBackgroundColor(RGBValues newBackgroundColor) {
        RGBValues oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DOT__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getStrokeSizeComputationExpression() {
        return strokeSizeComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStrokeSizeComputationExpression(String newStrokeSizeComputationExpression) {
        String oldStrokeSizeComputationExpression = strokeSizeComputationExpression;
        strokeSizeComputationExpression = newStrokeSizeComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION, oldStrokeSizeComputationExpression, strokeSizeComputationExpression));
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
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            return getStrokeSizeComputationExpression();
        case DiagramPackage.DOT__BACKGROUND_COLOR:
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
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            setStrokeSizeComputationExpression((String) newValue);
            return;
        case DiagramPackage.DOT__BACKGROUND_COLOR:
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
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            setStrokeSizeComputationExpression(DotImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            setBackgroundColor(DotImpl.BACKGROUND_COLOR_EDEFAULT);
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
        case DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION:
            return DotImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? strokeSizeComputationExpression != null
                    : !DotImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(strokeSizeComputationExpression);
        case DiagramPackage.DOT__BACKGROUND_COLOR:
            return DotImpl.BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !DotImpl.BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
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
        result.append(" (strokeSizeComputationExpression: "); //$NON-NLS-1$
        result.append(strokeSizeComputationExpression);
        result.append(", backgroundColor: "); //$NON-NLS-1$
        result.append(backgroundColor);
        result.append(')');
        return result.toString();
    }

} // DotImpl
