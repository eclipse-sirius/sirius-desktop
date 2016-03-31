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
import org.eclipse.sirius.diagram.ContainerShape;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Shape Container Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShapeContainerStyleImpl extends ContainerStyleImpl implements ShapeContainerStyle {
    /**
     * The default value of the '{@link #getShape() <em>Shape</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected static final ContainerShape SHAPE_EDEFAULT = ContainerShape.PARALLELOGRAM_LITERAL;

    /**
     * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected ContainerShape shape = ShapeContainerStyleImpl.SHAPE_EDEFAULT;

    /**
     * The default value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues BACKGROUND_COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "209,209,209"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor = ShapeContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ShapeContainerStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.SHAPE_CONTAINER_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerShape getShape() {
        return shape;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setShape(ContainerShape newShape) {
        ContainerShape oldShape = shape;
        shape = newShape == null ? ShapeContainerStyleImpl.SHAPE_EDEFAULT : newShape;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.SHAPE_CONTAINER_STYLE__SHAPE, oldShape, shape));
        }
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
        case DiagramPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            return getShape();
        case DiagramPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
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
        case DiagramPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            setShape((ContainerShape) newValue);
            return;
        case DiagramPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
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
        case DiagramPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            setShape(ShapeContainerStyleImpl.SHAPE_EDEFAULT);
            return;
        case DiagramPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor(ShapeContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT);
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
        case DiagramPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            return shape != ShapeContainerStyleImpl.SHAPE_EDEFAULT;
        case DiagramPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
            return ShapeContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT == null ? backgroundColor != null : !ShapeContainerStyleImpl.BACKGROUND_COLOR_EDEFAULT.equals(backgroundColor);
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
        result.append(" (shape: "); //$NON-NLS-1$
        result.append(shape);
        result.append(", backgroundColor: "); //$NON-NLS-1$
        result.append(backgroundColor);
        result.append(')');
        return result.toString();
    }

} // ShapeContainerStyleImpl
