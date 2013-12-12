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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bundled Image</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.BundledImageImpl#getShape <em>
 * Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.BundledImageImpl#getColor <em>
 * Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BundledImageImpl extends NodeStyleImpl implements BundledImage {
    /**
     * The default value of the '{@link #getShape() <em>Shape</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected static final BundledImageShape SHAPE_EDEFAULT = BundledImageShape.SQUARE_LITERAL;

    /**
     * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected BundledImageShape shape = SHAPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected RGBValues color;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected BundledImageImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.BUNDLED_IMAGE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BundledImageShape getShape() {
        return shape;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setShape(BundledImageShape newShape) {
        BundledImageShape oldShape = shape;
        shape = newShape == null ? SHAPE_EDEFAULT : newShape;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BUNDLED_IMAGE__SHAPE, oldShape, shape));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues getColor() {
        if (color != null && color.eIsProxy()) {
            InternalEObject oldColor = (InternalEObject) color;
            color = (RGBValues) eResolveProxy(oldColor);
            if (color != oldColor) {
                InternalEObject newColor = (InternalEObject) color;
                NotificationChain msgs = oldColor.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.BUNDLED_IMAGE__COLOR, null, null);
                if (newColor.eInternalContainer() == null) {
                    msgs = newColor.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.BUNDLED_IMAGE__COLOR, null, msgs);
                }
                if (msgs != null)
                    msgs.dispatch();
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, DiagramPackage.BUNDLED_IMAGE__COLOR, oldColor, color));
            }
        }
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues basicGetColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetColor(RGBValues newColor, NotificationChain msgs) {
        RGBValues oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiagramPackage.BUNDLED_IMAGE__COLOR, oldColor, newColor);
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
    public void setColor(RGBValues newColor) {
        if (newColor != color) {
            NotificationChain msgs = null;
            if (color != null)
                msgs = ((InternalEObject) color).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.BUNDLED_IMAGE__COLOR, null, msgs);
            if (newColor != null)
                msgs = ((InternalEObject) newColor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiagramPackage.BUNDLED_IMAGE__COLOR, null, msgs);
            msgs = basicSetColor(newColor, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BUNDLED_IMAGE__COLOR, newColor, newColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            return basicSetColor(null, msgs);
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            return getShape();
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            if (resolve)
                return getColor();
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            setShape((BundledImageShape) newValue);
            return;
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            setColor((RGBValues) newValue);
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            setShape(SHAPE_EDEFAULT);
            return;
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            setColor((RGBValues) null);
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            return shape != SHAPE_EDEFAULT;
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (shape: ");
        result.append(shape);
        result.append(')');
        return result.toString();
    }

} // BundledImageImpl
