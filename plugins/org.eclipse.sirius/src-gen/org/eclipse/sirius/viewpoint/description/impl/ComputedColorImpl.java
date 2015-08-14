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
import org.eclipse.sirius.viewpoint.description.ComputedColor;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Computed Color</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl#getRed
 * <em>Red</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl#getGreen
 * <em>Green</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.impl.ComputedColorImpl#getBlue
 * <em>Blue</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComputedColorImpl extends UserColorImpl implements ComputedColor {
    /**
     * The default value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected static final String RED_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getRed() <em>Red</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getRed()
     * @generated
     * @ordered
     */
    protected String red = ComputedColorImpl.RED_EDEFAULT;

    /**
     * The default value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected static final String GREEN_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getGreen() <em>Green</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getGreen()
     * @generated
     * @ordered
     */
    protected String green = ComputedColorImpl.GREEN_EDEFAULT;

    /**
     * The default value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected static final String BLUE_EDEFAULT = ""; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBlue() <em>Blue</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBlue()
     * @generated
     * @ordered
     */
    protected String blue = ComputedColorImpl.BLUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ComputedColorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.COMPUTED_COLOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getRed() {
        return red;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setRed(String newRed) {
        String oldRed = red;
        red = newRed;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COMPUTED_COLOR__RED, oldRed, red));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getGreen() {
        return green;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setGreen(String newGreen) {
        String oldGreen = green;
        green = newGreen;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COMPUTED_COLOR__GREEN, oldGreen, green));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getBlue() {
        return blue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setBlue(String newBlue) {
        String oldBlue = blue;
        blue = newBlue;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COMPUTED_COLOR__BLUE, oldBlue, blue));
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
        case DescriptionPackage.COMPUTED_COLOR__RED:
            return getRed();
        case DescriptionPackage.COMPUTED_COLOR__GREEN:
            return getGreen();
        case DescriptionPackage.COMPUTED_COLOR__BLUE:
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
        case DescriptionPackage.COMPUTED_COLOR__RED:
            setRed((String) newValue);
            return;
        case DescriptionPackage.COMPUTED_COLOR__GREEN:
            setGreen((String) newValue);
            return;
        case DescriptionPackage.COMPUTED_COLOR__BLUE:
            setBlue((String) newValue);
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
        case DescriptionPackage.COMPUTED_COLOR__RED:
            setRed(ComputedColorImpl.RED_EDEFAULT);
            return;
        case DescriptionPackage.COMPUTED_COLOR__GREEN:
            setGreen(ComputedColorImpl.GREEN_EDEFAULT);
            return;
        case DescriptionPackage.COMPUTED_COLOR__BLUE:
            setBlue(ComputedColorImpl.BLUE_EDEFAULT);
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
        case DescriptionPackage.COMPUTED_COLOR__RED:
            return ComputedColorImpl.RED_EDEFAULT == null ? red != null : !ComputedColorImpl.RED_EDEFAULT.equals(red);
        case DescriptionPackage.COMPUTED_COLOR__GREEN:
            return ComputedColorImpl.GREEN_EDEFAULT == null ? green != null : !ComputedColorImpl.GREEN_EDEFAULT.equals(green);
        case DescriptionPackage.COMPUTED_COLOR__BLUE:
            return ComputedColorImpl.BLUE_EDEFAULT == null ? blue != null : !ComputedColorImpl.BLUE_EDEFAULT.equals(blue);
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

} // ComputedColorImpl
