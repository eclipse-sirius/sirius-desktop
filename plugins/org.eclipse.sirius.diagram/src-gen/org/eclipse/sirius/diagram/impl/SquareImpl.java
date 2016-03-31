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
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Square</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.impl.SquareImpl#getWidth <em>Width</em>
 * }</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.SquareImpl#getHeight
 * <em>Height</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.impl.SquareImpl#getColor <em>Color</em>
 * }</li>
 * </ul>
 *
 * @generated
 */
public class SquareImpl extends NodeStyleImpl implements Square {
    /**
     * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected static final Integer WIDTH_EDEFAULT = new Integer(0);

    /**
     * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getWidth()
     * @generated
     * @ordered
     */
    protected Integer width = SquareImpl.WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getHeight() <em>Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected static final Integer HEIGHT_EDEFAULT = new Integer(0);

    /**
     * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getHeight()
     * @generated
     * @ordered
     */
    protected Integer height = SquareImpl.HEIGHT_EDEFAULT;

    /**
     * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected static final RGBValues COLOR_EDEFAULT = (RGBValues) ViewpointFactory.eINSTANCE.createFromString(ViewpointPackage.eINSTANCE.getRGBValues(), "136,136,136"); //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected RGBValues color = SquareImpl.COLOR_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected SquareImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DiagramPackage.Literals.SQUARE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getWidth() {
        return width;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setWidth(Integer newWidth) {
        Integer oldWidth = width;
        width = newWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.SQUARE__WIDTH, oldWidth, width));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getHeight() {
        return height;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setHeight(Integer newHeight) {
        Integer oldHeight = height;
        height = newHeight;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.SQUARE__HEIGHT, oldHeight, height));
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
            eNotify(new ENotificationImpl(this, Notification.SET, DiagramPackage.SQUARE__COLOR, oldColor, color));
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
        case DiagramPackage.SQUARE__WIDTH:
            return getWidth();
        case DiagramPackage.SQUARE__HEIGHT:
            return getHeight();
        case DiagramPackage.SQUARE__COLOR:
            return getColor();
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
        case DiagramPackage.SQUARE__WIDTH:
            setWidth((Integer) newValue);
            return;
        case DiagramPackage.SQUARE__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case DiagramPackage.SQUARE__COLOR:
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
        case DiagramPackage.SQUARE__WIDTH:
            setWidth(SquareImpl.WIDTH_EDEFAULT);
            return;
        case DiagramPackage.SQUARE__HEIGHT:
            setHeight(SquareImpl.HEIGHT_EDEFAULT);
            return;
        case DiagramPackage.SQUARE__COLOR:
            setColor(SquareImpl.COLOR_EDEFAULT);
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
        case DiagramPackage.SQUARE__WIDTH:
            return SquareImpl.WIDTH_EDEFAULT == null ? width != null : !SquareImpl.WIDTH_EDEFAULT.equals(width);
        case DiagramPackage.SQUARE__HEIGHT:
            return SquareImpl.HEIGHT_EDEFAULT == null ? height != null : !SquareImpl.HEIGHT_EDEFAULT.equals(height);
        case DiagramPackage.SQUARE__COLOR:
            return SquareImpl.COLOR_EDEFAULT == null ? color != null : !SquareImpl.COLOR_EDEFAULT.equals(color);
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
        result.append(" (width: "); //$NON-NLS-1$
        result.append(width);
        result.append(", height: "); //$NON-NLS-1$
        result.append(height);
        result.append(", color: "); //$NON-NLS-1$
        result.append(color);
        result.append(')');
        return result.toString();
    }

} // SquareImpl
