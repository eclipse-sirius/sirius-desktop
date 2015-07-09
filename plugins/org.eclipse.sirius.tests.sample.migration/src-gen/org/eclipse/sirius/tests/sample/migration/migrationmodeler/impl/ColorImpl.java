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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Color</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl#getRed
 * <em>Red</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl#getGreen
 * <em>Green</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl#getBlue
 * <em>Blue</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColorImpl extends EObjectImpl implements Color {
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
    protected int red = ColorImpl.RED_EDEFAULT;

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
    protected int green = ColorImpl.GREEN_EDEFAULT;

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
    protected int blue = ColorImpl.BLUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ColorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.COLOR;
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.COLOR__RED, oldRed, red));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.COLOR__GREEN, oldGreen, green));
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
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.COLOR__BLUE, oldBlue, blue));
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
        case MigrationmodelerPackage.COLOR__RED:
            return getRed();
        case MigrationmodelerPackage.COLOR__GREEN:
            return getGreen();
        case MigrationmodelerPackage.COLOR__BLUE:
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
        case MigrationmodelerPackage.COLOR__RED:
            setRed((Integer) newValue);
            return;
        case MigrationmodelerPackage.COLOR__GREEN:
            setGreen((Integer) newValue);
            return;
        case MigrationmodelerPackage.COLOR__BLUE:
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
        case MigrationmodelerPackage.COLOR__RED:
            setRed(ColorImpl.RED_EDEFAULT);
            return;
        case MigrationmodelerPackage.COLOR__GREEN:
            setGreen(ColorImpl.GREEN_EDEFAULT);
            return;
        case MigrationmodelerPackage.COLOR__BLUE:
            setBlue(ColorImpl.BLUE_EDEFAULT);
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
        case MigrationmodelerPackage.COLOR__RED:
            return red != ColorImpl.RED_EDEFAULT;
        case MigrationmodelerPackage.COLOR__GREEN:
            return green != ColorImpl.GREEN_EDEFAULT;
        case MigrationmodelerPackage.COLOR__BLUE:
            return blue != ColorImpl.BLUE_EDEFAULT;
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

} // ColorImpl
