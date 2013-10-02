/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>RGB Values</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.RGBValuesImpl#getRed <em>Red
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.RGBValuesImpl#getGreen <em>Green
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.impl.RGBValuesImpl#getBlue <em>Blue
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RGBValuesImpl extends EObjectImpl implements RGBValues {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * The default value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected static final int RED_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected int red = RED_EDEFAULT;

    /**
     * The default value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected static final int GREEN_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected int green = GREEN_EDEFAULT;

    /**
     * The default value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected static final int BLUE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected int blue = BLUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected RGBValuesImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewpointPackage.Literals.RGB_VALUES;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getRed() {
        return red;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setRed(int newRed) {
        int oldRed = red;
        red = newRed;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.RGB_VALUES__RED, oldRed, red));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getGreen() {
        return green;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setGreen(int newGreen) {
        int oldGreen = green;
        green = newGreen;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.RGB_VALUES__GREEN, oldGreen, green));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getBlue() {
        return blue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBlue(int newBlue) {
        int oldBlue = blue;
        blue = newBlue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ViewpointPackage.RGB_VALUES__BLUE, oldBlue, blue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewpointPackage.RGB_VALUES__RED:
            return getRed();
        case ViewpointPackage.RGB_VALUES__GREEN:
            return getGreen();
        case ViewpointPackage.RGB_VALUES__BLUE:
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
        case ViewpointPackage.RGB_VALUES__RED:
            setRed((Integer) newValue);
            return;
        case ViewpointPackage.RGB_VALUES__GREEN:
            setGreen((Integer) newValue);
            return;
        case ViewpointPackage.RGB_VALUES__BLUE:
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
        case ViewpointPackage.RGB_VALUES__RED:
            setRed(RED_EDEFAULT);
            return;
        case ViewpointPackage.RGB_VALUES__GREEN:
            setGreen(GREEN_EDEFAULT);
            return;
        case ViewpointPackage.RGB_VALUES__BLUE:
            setBlue(BLUE_EDEFAULT);
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
        case ViewpointPackage.RGB_VALUES__RED:
            return red != RED_EDEFAULT;
        case ViewpointPackage.RGB_VALUES__GREEN:
            return green != GREEN_EDEFAULT;
        case ViewpointPackage.RGB_VALUES__BLUE:
            return blue != BLUE_EDEFAULT;
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
        result.append(" (red: ");
        result.append(red);
        result.append(", green: ");
        result.append(green);
        result.append(", blue: ");
        result.append(blue);
        result.append(')');
        return result.toString();
    }

} // RGBValuesImpl
