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
package org.eclipse.sirius.viewpoint.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.FixedColor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Fixed Color</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl#getRed
 * <em>Red</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl#getGreen
 * <em>Green</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.FixedColorImpl#getBlue
 * <em>Blue</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FixedColorImpl extends ColorDescriptionImpl implements FixedColor {
    /**
     * The default value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected static final int RED_EDEFAULT = 125;

    /**
     * The cached value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected int red = FixedColorImpl.RED_EDEFAULT;

    /**
     * The default value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected static final int GREEN_EDEFAULT = 125;

    /**
     * The cached value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected int green = FixedColorImpl.GREEN_EDEFAULT;

    /**
     * The default value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected static final int BLUE_EDEFAULT = 125;

    /**
     * The cached value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected int blue = FixedColorImpl.BLUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FixedColorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.FIXED_COLOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getRed() {
        return red;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRed(int newRed) {
        int oldRed = red;
        red = newRed;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FIXED_COLOR__RED, oldRed, red));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getGreen() {
        return green;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setGreen(int newGreen) {
        int oldGreen = green;
        green = newGreen;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FIXED_COLOR__GREEN, oldGreen, green));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getBlue() {
        return blue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBlue(int newBlue) {
        int oldBlue = blue;
        blue = newBlue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.FIXED_COLOR__BLUE, oldBlue, blue));
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
        case DescriptionPackage.FIXED_COLOR__RED:
            return getRed();
        case DescriptionPackage.FIXED_COLOR__GREEN:
            return getGreen();
        case DescriptionPackage.FIXED_COLOR__BLUE:
            return getBlue();
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
        case DescriptionPackage.FIXED_COLOR__RED:
            setRed((Integer) newValue);
            return;
        case DescriptionPackage.FIXED_COLOR__GREEN:
            setGreen((Integer) newValue);
            return;
        case DescriptionPackage.FIXED_COLOR__BLUE:
            setBlue((Integer) newValue);
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
        case DescriptionPackage.FIXED_COLOR__RED:
            setRed(FixedColorImpl.RED_EDEFAULT);
            return;
        case DescriptionPackage.FIXED_COLOR__GREEN:
            setGreen(FixedColorImpl.GREEN_EDEFAULT);
            return;
        case DescriptionPackage.FIXED_COLOR__BLUE:
            setBlue(FixedColorImpl.BLUE_EDEFAULT);
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
        case DescriptionPackage.FIXED_COLOR__RED:
            return red != FixedColorImpl.RED_EDEFAULT;
        case DescriptionPackage.FIXED_COLOR__GREEN:
            return green != FixedColorImpl.GREEN_EDEFAULT;
        case DescriptionPackage.FIXED_COLOR__BLUE:
            return blue != FixedColorImpl.BLUE_EDEFAULT;
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
        result.append(" (red: "); //$NON-NLS-1$
        result.append(red);
        result.append(", green: "); //$NON-NLS-1$
        result.append(green);
        result.append(", blue: "); //$NON-NLS-1$
        result.append(blue);
        result.append(')');
        return result.toString();
    }

} // FixedColorImpl
