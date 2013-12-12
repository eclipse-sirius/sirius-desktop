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
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.ContainerShape;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ShapeContainerStyle;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Shape Container Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.ShapeContainerStyleImpl#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.impl.ShapeContainerStyleImpl#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
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
    protected ContainerShape shape = SHAPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBackgroundColor()
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getBackgroundColor()
     * @generated
     * @ordered
     */
    protected RGBValues backgroundColor;

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
        return ViewpointPackage.Literals.SHAPE_CONTAINER_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerShape getShape() {
        return shape;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setShape(ContainerShape newShape) {
        ContainerShape oldShape = shape;
        shape = newShape == null ? SHAPE_EDEFAULT : newShape;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.SHAPE_CONTAINER_STYLE__SHAPE, oldShape, shape));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getBackgroundColor() {
        if (backgroundColor != null && backgroundColor.eIsProxy()) {
            InternalEObject oldBackgroundColor = (InternalEObject) backgroundColor;
            backgroundColor = (RGBValues) eResolveProxy(oldBackgroundColor);
            if (backgroundColor != oldBackgroundColor) {
                InternalEObject newBackgroundColor = (InternalEObject) backgroundColor;
                NotificationChain msgs = oldBackgroundColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, null, null);
                if (newBackgroundColor.eInternalContainer() == null) {
                    msgs = newBackgroundColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, backgroundColor));
            }
        }
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetBackgroundColor() {
        return backgroundColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetBackgroundColor(RGBValues newBackgroundColor, NotificationChain msgs) {
        RGBValues oldBackgroundColor = backgroundColor;
        backgroundColor = newBackgroundColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, oldBackgroundColor, newBackgroundColor);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBackgroundColor(RGBValues newBackgroundColor) {
        if (newBackgroundColor != backgroundColor) {
            NotificationChain msgs = null;
            if (backgroundColor != null)
                msgs = ((InternalEObject) backgroundColor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
            if (newBackgroundColor != null)
                msgs = ((InternalEObject) newBackgroundColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, null, msgs);
            msgs = basicSetBackgroundColor(newBackgroundColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR, newBackgroundColor, newBackgroundColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
            return basicSetBackgroundColor(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            return getShape();
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
            if (resolve)
                return getBackgroundColor();
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
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            setShape((ContainerShape) newValue);
            return;
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
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
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            setShape(SHAPE_EDEFAULT);
            return;
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
            setBackgroundColor((RGBValues) null);
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
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__SHAPE:
            return shape != SHAPE_EDEFAULT;
        case ViewpointPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR:
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (shape: ");
        result.append(shape);
        result.append(')');
        return result.toString();
    }

} // ShapeContainerStyleImpl
