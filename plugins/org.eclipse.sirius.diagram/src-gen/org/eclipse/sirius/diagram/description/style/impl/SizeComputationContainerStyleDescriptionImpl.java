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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Size Computation Container Style Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.SizeComputationContainerStyleDescriptionImpl#getWidthComputationExpression
 * <em>Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.SizeComputationContainerStyleDescriptionImpl#getHeightComputationExpression
 * <em>Height Computation Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class SizeComputationContainerStyleDescriptionImpl extends MinimalEObjectImpl.Container implements SizeComputationContainerStyleDescription {
    /**
     * The default value of the '{@link #getWidthComputationExpression()
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String WIDTH_COMPUTATION_EXPRESSION_EDEFAULT = "-1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getWidthComputationExpression()
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected String widthComputationExpression = SizeComputationContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getHeightComputationExpression()
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHeightComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT = "-1"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getHeightComputationExpression()
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHeightComputationExpression()
     * @generated
     * @ordered
     */
    protected String heightComputationExpression = SizeComputationContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SizeComputationContainerStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getWidthComputationExpression() {
        return widthComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWidthComputationExpression(String newWidthComputationExpression) {
        String oldWidthComputationExpression = widthComputationExpression;
        widthComputationExpression = newWidthComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION, oldWidthComputationExpression,
                    widthComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getHeightComputationExpression() {
        return heightComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHeightComputationExpression(String newHeightComputationExpression) {
        String oldHeightComputationExpression = heightComputationExpression;
        heightComputationExpression = newHeightComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION, oldHeightComputationExpression,
                    heightComputationExpression));
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
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return getWidthComputationExpression();
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return getHeightComputationExpression();
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
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression((String) newValue);
            return;
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression((String) newValue);
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
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression(SizeComputationContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression(SizeComputationContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT);
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
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return SizeComputationContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT == null ? widthComputationExpression != null
                    : !SizeComputationContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT.equals(widthComputationExpression);
        case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return SizeComputationContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT == null ? heightComputationExpression != null
                    : !SizeComputationContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT.equals(heightComputationExpression);
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
        result.append(" (widthComputationExpression: "); //$NON-NLS-1$
        result.append(widthComputationExpression);
        result.append(", heightComputationExpression: "); //$NON-NLS-1$
        result.append(heightComputationExpression);
        result.append(')');
        return result.toString();
    }

} // SizeComputationContainerStyleDescriptionImpl
