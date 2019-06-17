/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Bundled Image Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.BundledImageDescriptionImpl#getShape
 * <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.BundledImageDescriptionImpl#getColor
 * <em>Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.impl.BundledImageDescriptionImpl#getProvidedShapeID
 * <em>Provided Shape ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BundledImageDescriptionImpl extends NodeStyleDescriptionImpl implements BundledImageDescription {
    /**
     * The default value of the '{@link #getShape() <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected static final BundledImageShape SHAPE_EDEFAULT = BundledImageShape.SQUARE_LITERAL;

    /**
     * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected BundledImageShape shape = BundledImageDescriptionImpl.SHAPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected ColorDescription color;

    /**
     * The default value of the '{@link #getProvidedShapeID() <em>Provided Shape ID</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getProvidedShapeID()
     * @generated
     * @ordered
     */
    protected static final String PROVIDED_SHAPE_ID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProvidedShapeID() <em>Provided Shape ID</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getProvidedShapeID()
     * @generated
     * @ordered
     */
    protected String providedShapeID = BundledImageDescriptionImpl.PROVIDED_SHAPE_ID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BundledImageDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.BUNDLED_IMAGE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BundledImageShape getShape() {
        return shape;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setShape(BundledImageShape newShape) {
        BundledImageShape oldShape = shape;
        shape = newShape == null ? BundledImageDescriptionImpl.SHAPE_EDEFAULT : newShape;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE, oldShape, shape));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR, oldColor, color));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR, oldColor, color));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getProvidedShapeID() {
        return providedShapeID;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setProvidedShapeID(String newProvidedShapeID) {
        String oldProvidedShapeID = providedShapeID;
        providedShapeID = newProvidedShapeID;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID, oldProvidedShapeID, providedShapeID));
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
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE:
            return getShape();
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR:
            if (resolve) {
                return getColor();
            }
            return basicGetColor();
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID:
            return getProvidedShapeID();
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
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE:
            setShape((BundledImageShape) newValue);
            return;
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR:
            setColor((ColorDescription) newValue);
            return;
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID:
            setProvidedShapeID((String) newValue);
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
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE:
            setShape(BundledImageDescriptionImpl.SHAPE_EDEFAULT);
            return;
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR:
            setColor((ColorDescription) null);
            return;
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID:
            setProvidedShapeID(BundledImageDescriptionImpl.PROVIDED_SHAPE_ID_EDEFAULT);
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
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE:
            return shape != BundledImageDescriptionImpl.SHAPE_EDEFAULT;
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR:
            return color != null;
        case StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID:
            return BundledImageDescriptionImpl.PROVIDED_SHAPE_ID_EDEFAULT == null ? providedShapeID != null : !BundledImageDescriptionImpl.PROVIDED_SHAPE_ID_EDEFAULT.equals(providedShapeID);
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (shape: "); //$NON-NLS-1$
        result.append(shape);
        result.append(", providedShapeID: "); //$NON-NLS-1$
        result.append(providedShapeID);
        result.append(')');
        return result.toString();
    }

} // BundledImageDescriptionImpl
