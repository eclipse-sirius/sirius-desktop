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
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription;
import org.eclipse.sirius.diagram.description.style.StylePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rounded Corner Style Description</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.RoundedCornerStyleDescriptionImpl#getArcWidth
 * <em>Arc Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.impl.RoundedCornerStyleDescriptionImpl#getArcHeight
 * <em>Arc Height</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class RoundedCornerStyleDescriptionImpl extends MinimalEObjectImpl.Container implements RoundedCornerStyleDescription {
    /**
     * The default value of the '{@link #getArcWidth() <em>Arc Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getArcWidth()
     * @generated
     * @ordered
     */
    protected static final Integer ARC_WIDTH_EDEFAULT = new Integer(10);

    /**
     * The cached value of the '{@link #getArcWidth() <em>Arc Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getArcWidth()
     * @generated
     * @ordered
     */
    protected Integer arcWidth = RoundedCornerStyleDescriptionImpl.ARC_WIDTH_EDEFAULT;

    /**
     * The default value of the '{@link #getArcHeight() <em>Arc Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getArcHeight()
     * @generated
     * @ordered
     */
    protected static final Integer ARC_HEIGHT_EDEFAULT = new Integer(10);

    /**
     * The cached value of the '{@link #getArcHeight() <em>Arc Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getArcHeight()
     * @generated
     * @ordered
     */
    protected Integer arcHeight = RoundedCornerStyleDescriptionImpl.ARC_HEIGHT_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RoundedCornerStyleDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StylePackage.Literals.ROUNDED_CORNER_STYLE_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getArcWidth() {
        return arcWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setArcWidth(Integer newArcWidth) {
        Integer oldArcWidth = arcWidth;
        arcWidth = newArcWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH, oldArcWidth, arcWidth));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Integer getArcHeight() {
        return arcHeight;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setArcHeight(Integer newArcHeight) {
        Integer oldArcHeight = arcHeight;
        arcHeight = newArcHeight;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT, oldArcHeight, arcHeight));
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
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH:
            return getArcWidth();
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT:
            return getArcHeight();
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
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH:
            setArcWidth((Integer) newValue);
            return;
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT:
            setArcHeight((Integer) newValue);
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
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH:
            setArcWidth(RoundedCornerStyleDescriptionImpl.ARC_WIDTH_EDEFAULT);
            return;
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT:
            setArcHeight(RoundedCornerStyleDescriptionImpl.ARC_HEIGHT_EDEFAULT);
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
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH:
            return RoundedCornerStyleDescriptionImpl.ARC_WIDTH_EDEFAULT == null ? arcWidth != null : !RoundedCornerStyleDescriptionImpl.ARC_WIDTH_EDEFAULT.equals(arcWidth);
        case StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT:
            return RoundedCornerStyleDescriptionImpl.ARC_HEIGHT_EDEFAULT == null ? arcHeight != null : !RoundedCornerStyleDescriptionImpl.ARC_HEIGHT_EDEFAULT.equals(arcHeight);
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
        result.append(" (arcWidth: "); //$NON-NLS-1$
        result.append(arcWidth);
        result.append(", arcHeight: "); //$NON-NLS-1$
        result.append(arcHeight);
        result.append(')');
        return result.toString();
    }

} // RoundedCornerStyleDescriptionImpl
