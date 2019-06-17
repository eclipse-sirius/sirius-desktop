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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Bundled Image</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.BundledImageImpl#getShape <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.BundledImageImpl#getColor <em>Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.BundledImageImpl#getProvidedShapeID <em>Provided Shape ID</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BundledImageImpl extends NodeStyleImpl implements BundledImage {
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
    protected BundledImageShape shape = BundledImageImpl.SHAPE_EDEFAULT;

    /**
     * The default value of the '{@link #getColor() <em>Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "0,0,0"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected RGBValues color = BundledImageImpl.COLOR_EDEFAULT;

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
    protected String providedShapeID = BundledImageImpl.PROVIDED_SHAPE_ID_EDEFAULT;

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
        shape = newShape == null ? BundledImageImpl.SHAPE_EDEFAULT : newShape;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BUNDLED_IMAGE__SHAPE, oldShape, shape));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RGBValues getColor() {
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setColor(RGBValues newColor) {
        RGBValues oldColor = color;
        color = newColor;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BUNDLED_IMAGE__COLOR, oldColor, color));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.BUNDLED_IMAGE__PROVIDED_SHAPE_ID, oldProvidedShapeID, providedShapeID));
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            return getShape();
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            return getColor();
        case DiagramPackage.BUNDLED_IMAGE__PROVIDED_SHAPE_ID:
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            setShape((BundledImageShape) newValue);
            return;
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            setColor((RGBValues) newValue);
            return;
        case DiagramPackage.BUNDLED_IMAGE__PROVIDED_SHAPE_ID:
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
        case DiagramPackage.BUNDLED_IMAGE__SHAPE:
            setShape(BundledImageImpl.SHAPE_EDEFAULT);
            return;
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            setColor(BundledImageImpl.COLOR_EDEFAULT);
            return;
        case DiagramPackage.BUNDLED_IMAGE__PROVIDED_SHAPE_ID:
            setProvidedShapeID(BundledImageImpl.PROVIDED_SHAPE_ID_EDEFAULT);
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
            return shape != BundledImageImpl.SHAPE_EDEFAULT;
        case DiagramPackage.BUNDLED_IMAGE__COLOR:
            return BundledImageImpl.COLOR_EDEFAULT == null ? color != null : !BundledImageImpl.COLOR_EDEFAULT.equals(color);
        case DiagramPackage.BUNDLED_IMAGE__PROVIDED_SHAPE_ID:
            return BundledImageImpl.PROVIDED_SHAPE_ID_EDEFAULT == null ? providedShapeID != null : !BundledImageImpl.PROVIDED_SHAPE_ID_EDEFAULT.equals(providedShapeID);
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
        result.append(", color: "); //$NON-NLS-1$
        result.append(color);
        result.append(", providedShapeID: "); //$NON-NLS-1$
        result.append(providedShapeID);
        result.append(')');
        return result.toString();
    }

} // BundledImageImpl
