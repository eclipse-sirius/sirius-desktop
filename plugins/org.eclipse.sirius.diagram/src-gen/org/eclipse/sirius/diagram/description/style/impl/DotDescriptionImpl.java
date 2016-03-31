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
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Dot Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.DotDescriptionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.DotDescriptionImpl#getStrokeSizeComputationExpression
 * <em>Stroke Size Computation Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DotDescriptionImpl extends NodeStyleDescriptionImpl implements DotDescription {
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
    protected String strokeSizeComputationExpression = DotDescriptionImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DotDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.DOT_DESCRIPTION;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION, oldStrokeSizeComputationExpression,
                    strokeSizeComputationExpression));
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
        case StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR:
            if (resolve) {
                return getBackgroundColor();
            }
            return basicGetBackgroundColor();
        case StylePackage.DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION:
            return getStrokeSizeComputationExpression();
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
        case StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) newValue);
            return;
        case StylePackage.DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION:
            setStrokeSizeComputationExpression((String) newValue);
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
        case StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR:
            setBackgroundColor((ColorDescription) null);
            return;
        case StylePackage.DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION:
            setStrokeSizeComputationExpression(DotDescriptionImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT);
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
        case StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR:
            return backgroundColor != null;
        case StylePackage.DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION:
            return DotDescriptionImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT == null ? strokeSizeComputationExpression != null
                    : !DotDescriptionImpl.STROKE_SIZE_COMPUTATION_EXPRESSION_EDEFAULT.equals(strokeSizeComputationExpression);
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
        result.append(')');
        return result.toString();
    }

} // DotDescriptionImpl
