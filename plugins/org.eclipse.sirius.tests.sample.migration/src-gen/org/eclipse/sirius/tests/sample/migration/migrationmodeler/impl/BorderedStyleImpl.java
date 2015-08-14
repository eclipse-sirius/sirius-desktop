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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Bordered Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedStyleImpl#getBorderSize
 * <em>Border Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedStyleImpl#getBorderColor
 * <em>Border Color</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BorderedStyleImpl extends EObjectImpl implements BorderedStyle {
    /**
     * The default value of the '{@link #getBorderSize() <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected static final int BORDER_SIZE_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getBorderSize() <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected int borderSize = BorderedStyleImpl.BORDER_SIZE_EDEFAULT;

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected Color borderColor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected BorderedStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MigrationmodelerPackage.Literals.BORDERED_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getBorderSize() {
        return borderSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderSize(int newBorderSize) {
        int oldBorderSize = borderSize;
        borderSize = newBorderSize;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE, oldBorderSize, borderSize));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetBorderColor(Color newBorderColor, NotificationChain msgs) {
        Color oldBorderColor = borderColor;
        borderColor = newBorderColor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR, oldBorderColor, newBorderColor);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderColor(Color newBorderColor) {
        if (newBorderColor != borderColor) {
            NotificationChain msgs = null;
            if (borderColor != null) {
                msgs = ((InternalEObject) borderColor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR, null, msgs);
            }
            if (newBorderColor != null) {
                msgs = ((InternalEObject) newBorderColor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR, null, msgs);
            }
            msgs = basicSetBorderColor(newBorderColor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR, newBorderColor, newBorderColor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR:
            return basicSetBorderColor(null, msgs);
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
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE:
            return getBorderSize();
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR:
            return getBorderColor();
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
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE:
            setBorderSize((Integer) newValue);
            return;
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR:
            setBorderColor((Color) newValue);
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
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE:
            setBorderSize(BorderedStyleImpl.BORDER_SIZE_EDEFAULT);
            return;
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR:
            setBorderColor((Color) null);
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
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_SIZE:
            return borderSize != BorderedStyleImpl.BORDER_SIZE_EDEFAULT;
        case MigrationmodelerPackage.BORDERED_STYLE__BORDER_COLOR:
            return borderColor != null;
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
        result.append(" (borderSize: "); //$NON-NLS-1$
        result.append(borderSize);
        result.append(')');
        return result.toString();
    }

} // BorderedStyleImpl
