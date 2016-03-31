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
import org.eclipse.sirius.diagram.ContainerShape;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Shape Container Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ShapeContainerStyleDescriptionImpl#getWidthComputationExpression
 * <em>Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ShapeContainerStyleDescriptionImpl#getHeightComputationExpression
 * <em>Height Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ShapeContainerStyleDescriptionImpl#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.ShapeContainerStyleDescriptionImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ShapeContainerStyleDescriptionImpl extends ContainerStyleDescriptionImpl implements ShapeContainerStyleDescription {
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
    protected String widthComputationExpression = ShapeContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT;

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
    protected String heightComputationExpression = ShapeContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT;

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
    protected ContainerShape shape = ShapeContainerStyleDescriptionImpl.SHAPE_EDEFAULT;

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
    protected ShapeContainerStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.SHAPE_CONTAINER_STYLE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION, oldWidthComputationExpression,
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION, oldHeightComputationExpression,
                    heightComputationExpression));
        }
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
        shape = newShape == null ? ShapeContainerStyleDescriptionImpl.SHAPE_EDEFAULT : newShape;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE, oldShape, shape));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
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
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return getWidthComputationExpression();
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return getHeightComputationExpression();
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE:
            return getShape();
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
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
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression((String) newValue);
            return;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression((String) newValue);
            return;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE:
            setShape((ContainerShape) newValue);
            return;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
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
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            setWidthComputationExpression(ShapeContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            setHeightComputationExpression(ShapeContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT);
            return;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE:
            setShape(ShapeContainerStyleDescriptionImpl.SHAPE_EDEFAULT);
            return;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
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
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
            return ShapeContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT == null ? widthComputationExpression != null
                    : !ShapeContainerStyleDescriptionImpl.WIDTH_COMPUTATION_EXPRESSION_EDEFAULT.equals(widthComputationExpression);
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
            return ShapeContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT == null ? heightComputationExpression != null
                    : !ShapeContainerStyleDescriptionImpl.HEIGHT_COMPUTATION_EXPRESSION_EDEFAULT.equals(heightComputationExpression);
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE:
            return shape != ShapeContainerStyleDescriptionImpl.SHAPE_EDEFAULT;
        case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR:
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
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == SizeComputationContainerStyleDescription.class) {
            switch (derivedFeatureID) {
            case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
                return StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION;
            case StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
                return StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION;
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
        if (baseClass == SizeComputationContainerStyleDescription.class) {
            switch (baseFeatureID) {
            case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION:
                return StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION;
            case StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION:
                return StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION;
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

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (widthComputationExpression: "); //$NON-NLS-1$
        result.append(widthComputationExpression);
        result.append(", heightComputationExpression: "); //$NON-NLS-1$
        result.append(heightComputationExpression);
        result.append(", shape: "); //$NON-NLS-1$
        result.append(shape);
        result.append(')');
        return result.toString();
    }

} // ShapeContainerStyleDescriptionImpl
