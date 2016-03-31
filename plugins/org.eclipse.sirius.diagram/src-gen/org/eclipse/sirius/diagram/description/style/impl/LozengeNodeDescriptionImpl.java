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
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Lozenge Node Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.LozengeNodeDescriptionImpl#getWidthComputationExpression
 * <em>Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.LozengeNodeDescriptionImpl#getHeightComputationExpression
 * <em>Height Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.LozengeNodeDescriptionImpl#getColor
 * <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LozengeNodeDescriptionImpl extends NodeStyleDescriptionImpl implements LozengeNodeDescription {
    /**
     * The default value of the '{@link #getWidthComputationExpression()
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String WIDTH_COMPUTATION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getWidthComputationExpression()
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getWidthComputationExpression()
     * @generated
     * @ordered
     */
    protected String widthComputationExpression = LozengeNodeDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getHeightComputationExpression()
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHeightComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeightComputationExpression()
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getHeightComputationExpression()
     * @generated
     * @ordered
     */
    protected String heightComputationExpression = LozengeNodeDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected ColorDescription color;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected LozengeNodeDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.LOZENGE_NODE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION, oldWidthComputationExpression, widthComputationExpression));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION, oldHeightComputationExpression, heightComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ColorDescription getColor() {
        if (color != null && color.eIsProxy()) {
            InternalEObject oldColor = (InternalEObject) color;
            color = (ColorDescription) eResolveProxy(oldColor);
            if (color != oldColor) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR, oldColor, color));
                }
            }
        }
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ColorDescription basicGetColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setColor(ColorDescription newColor) {
        ColorDescription oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR, oldColor, color));
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
        case StylePackage.LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return getWidthComputationExpression();
        case StylePackage.LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return getHeightComputationExpression();
        case StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR:
            if (resolve) {
                return getColor();
            }
            return basicGetColor();
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
        case StylePackage.LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression((String) newValue);
            return;
        case StylePackage.LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression((String) newValue);
            return;
        case StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR:
            setColor((ColorDescription) newValue);
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
        case StylePackage.LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression(LozengeNodeDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression(LozengeNodeDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR:
            setColor((ColorDescription) null);
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
        case StylePackage.LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return LozengeNodeDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT == null ? widthComputationExpression != null
                    : !LozengeNodeDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT.equals(widthComputationExpression);
        case StylePackage.LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return LozengeNodeDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT == null ? heightComputationExpression != null
                    : !LozengeNodeDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT.equals(heightComputationExpression);
        case StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR:
            return color != null;
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

} // LozengeNodeDescriptionImpl
