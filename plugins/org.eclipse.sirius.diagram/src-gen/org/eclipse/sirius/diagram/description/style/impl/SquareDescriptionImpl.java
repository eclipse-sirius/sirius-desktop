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
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Square Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.SquareDescriptionImpl#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.SquareDescriptionImpl#getHeight
 * <em>Height</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.SquareDescriptionImpl#getColor
 * <em>Color</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SquareDescriptionImpl extends NodeStyleDescriptionImpl implements SquareDescription {
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
    protected Integer width = SquareDescriptionImpl.WIDTH_EDEFAULT;

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
    protected Integer height = SquareDescriptionImpl.HEIGHT_EDEFAULT;

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
    protected SquareDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.SQUARE_DESCRIPTION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SQUARE_DESCRIPTION__WIDTH, oldWidth, width));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SQUARE_DESCRIPTION__HEIGHT, oldHeight, height));
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
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, StylePackage.SQUARE_DESCRIPTION__COLOR, oldColor, color));
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
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.SQUARE_DESCRIPTION__COLOR, oldColor, color));
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
        case StylePackage.SQUARE_DESCRIPTION__WIDTH:
            return getWidth();
        case StylePackage.SQUARE_DESCRIPTION__HEIGHT:
            return getHeight();
        case StylePackage.SQUARE_DESCRIPTION__COLOR:
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
        case StylePackage.SQUARE_DESCRIPTION__WIDTH:
            setWidth((Integer) newValue);
            return;
        case StylePackage.SQUARE_DESCRIPTION__HEIGHT:
            setHeight((Integer) newValue);
            return;
        case StylePackage.SQUARE_DESCRIPTION__COLOR:
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
        case StylePackage.SQUARE_DESCRIPTION__WIDTH:
            setWidth(SquareDescriptionImpl.WIDTH_EDEFAULT);
            return;
        case StylePackage.SQUARE_DESCRIPTION__HEIGHT:
            setHeight(SquareDescriptionImpl.HEIGHT_EDEFAULT);
            return;
        case StylePackage.SQUARE_DESCRIPTION__COLOR:
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
        case StylePackage.SQUARE_DESCRIPTION__WIDTH:
            return SquareDescriptionImpl.WIDTH_EDEFAULT == null ? width != null : !SquareDescriptionImpl.WIDTH_EDEFAULT.equals(width);
        case StylePackage.SQUARE_DESCRIPTION__HEIGHT:
            return SquareDescriptionImpl.HEIGHT_EDEFAULT == null ? height != null : !SquareDescriptionImpl.HEIGHT_EDEFAULT.equals(height);
        case StylePackage.SQUARE_DESCRIPTION__COLOR:
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
        result.append(" (width: "); //$NON-NLS-1$
        result.append(width);
        result.append(", height: "); //$NON-NLS-1$
        result.append(height);
        result.append(')');
        return result.toString();
    }

} // SquareDescriptionImpl
