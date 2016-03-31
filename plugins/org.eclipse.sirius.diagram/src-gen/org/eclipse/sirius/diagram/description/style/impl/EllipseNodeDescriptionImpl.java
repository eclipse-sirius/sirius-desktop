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
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ellipse Node Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EllipseNodeDescriptionImpl#getColor
 * <em>Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EllipseNodeDescriptionImpl#getHorizontalDiameterComputationExpression
 * <em>Horizontal Diameter Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.EllipseNodeDescriptionImpl#getVerticalDiameterComputationExpression
 * <em>Vertical Diameter Computation Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EllipseNodeDescriptionImpl extends NodeStyleDescriptionImpl implements EllipseNodeDescription {
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
     * The default value of the '
     * {@link #getHorizontalDiameterComputationExpression()
     * <em>Horizontal Diameter Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHorizontalDiameterComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '
     * {@link #getHorizontalDiameterComputationExpression()
     * <em>Horizontal Diameter Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHorizontalDiameterComputationExpression()
     * @generated
     * @ordered
     */
    protected String horizontalDiameterComputationExpression = EllipseNodeDescriptionImpl.HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '
     * {@link #getVerticalDiameterComputationExpression()
     * <em>Vertical Diameter Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVerticalDiameterComputationExpression()
     * @generated
     * @ordered
     */
    protected static final String VERTICAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT = "0"; //$NON-NLS-1$

    /**
     * The cached value of the '
     * {@link #getVerticalDiameterComputationExpression()
     * <em>Vertical Diameter Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getVerticalDiameterComputationExpression()
     * @generated
     * @ordered
     */
    protected String verticalDiameterComputationExpression = EllipseNodeDescriptionImpl.VERTICAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EllipseNodeDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.ELLIPSE_NODE_DESCRIPTION;
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR, oldColor, color));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR, oldColor, color));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getHorizontalDiameterComputationExpression() {
        return horizontalDiameterComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHorizontalDiameterComputationExpression(String newHorizontalDiameterComputationExpression) {
        String oldHorizontalDiameterComputationExpression = horizontalDiameterComputationExpression;
        horizontalDiameterComputationExpression = newHorizontalDiameterComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION, oldHorizontalDiameterComputationExpression,
                    horizontalDiameterComputationExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getVerticalDiameterComputationExpression() {
        return verticalDiameterComputationExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setVerticalDiameterComputationExpression(String newVerticalDiameterComputationExpression) {
        String oldVerticalDiameterComputationExpression = verticalDiameterComputationExpression;
        verticalDiameterComputationExpression = newVerticalDiameterComputationExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION, oldVerticalDiameterComputationExpression,
                    verticalDiameterComputationExpression));
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
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR:
            if (resolve) {
                return getColor();
            }
            return basicGetColor();
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION:
            return getHorizontalDiameterComputationExpression();
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION:
            return getVerticalDiameterComputationExpression();
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
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR:
            setColor((ColorDescription) newValue);
            return;
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION:
            setHorizontalDiameterComputationExpression((String) newValue);
            return;
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION:
            setVerticalDiameterComputationExpression((String) newValue);
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
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR:
            setColor((ColorDescription) null);
            return;
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION:
            setHorizontalDiameterComputationExpression(EllipseNodeDescriptionImpl.HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION:
            setVerticalDiameterComputationExpression(EllipseNodeDescriptionImpl.VERTICAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT);
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
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR:
            return color != null;
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION:
            return EllipseNodeDescriptionImpl.HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT == null ? horizontalDiameterComputationExpression != null
                    : !EllipseNodeDescriptionImpl.HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT.equals(horizontalDiameterComputationExpression);
        case StylePackage.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION:
            return EllipseNodeDescriptionImpl.VERTICAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT == null ? verticalDiameterComputationExpression != null
                    : !EllipseNodeDescriptionImpl.VERTICAL_DIAMETER_COMPUTATION_EXPRESSION_EDEFAULT.equals(verticalDiameterComputationExpression);
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
        result.append(" (horizontalDiameterComputationExpression: "); //$NON-NLS-1$
        result.append(horizontalDiameterComputationExpression);
        result.append(", verticalDiameterComputationExpression: "); //$NON-NLS-1$
        result.append(verticalDiameterComputationExpression);
        result.append(')');
        return result.toString();
    }

} // EllipseNodeDescriptionImpl
