/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.properties.GridLayoutDescription;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Grid Layout Description</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl#getNumberOfColumns <em>Number Of
 * Columns</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl#isMakeColumnsWithEqualWidth <em>Make Columns
 * With Equal Width</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GridLayoutDescriptionImpl extends LayoutDescriptionImpl implements GridLayoutDescription {
    /**
     * The default value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getNumberOfColumns()
     * @generated
     * @ordered
     */
    protected static final int NUMBER_OF_COLUMNS_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getNumberOfColumns() <em>Number Of Columns</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getNumberOfColumns()
     * @generated
     * @ordered
     */
    protected int numberOfColumns = GridLayoutDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT;

    /**
     * The default value of the '{@link #isMakeColumnsWithEqualWidth() <em>Make Columns With Equal Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isMakeColumnsWithEqualWidth()
     * @generated
     * @ordered
     */
    protected static final boolean MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isMakeColumnsWithEqualWidth() <em>Make Columns With Equal Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #isMakeColumnsWithEqualWidth()
     * @generated
     * @ordered
     */
    protected boolean makeColumnsWithEqualWidth = GridLayoutDescriptionImpl.MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected GridLayoutDescriptionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PropertiesPackage.Literals.GRID_LAYOUT_DESCRIPTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setNumberOfColumns(int newNumberOfColumns) {
        int oldNumberOfColumns = numberOfColumns;
        numberOfColumns = newNumberOfColumns;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS, oldNumberOfColumns, numberOfColumns));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isMakeColumnsWithEqualWidth() {
        return makeColumnsWithEqualWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMakeColumnsWithEqualWidth(boolean newMakeColumnsWithEqualWidth) {
        boolean oldMakeColumnsWithEqualWidth = makeColumnsWithEqualWidth;
        makeColumnsWithEqualWidth = newMakeColumnsWithEqualWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH, oldMakeColumnsWithEqualWidth, makeColumnsWithEqualWidth));
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
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
            return getNumberOfColumns();
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
            return isMakeColumnsWithEqualWidth();
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
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
            setNumberOfColumns((Integer) newValue);
            return;
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
            setMakeColumnsWithEqualWidth((Boolean) newValue);
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
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
            setNumberOfColumns(GridLayoutDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT);
            return;
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
            setMakeColumnsWithEqualWidth(GridLayoutDescriptionImpl.MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT);
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
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS:
            return numberOfColumns != GridLayoutDescriptionImpl.NUMBER_OF_COLUMNS_EDEFAULT;
        case PropertiesPackage.GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH:
            return makeColumnsWithEqualWidth != GridLayoutDescriptionImpl.MAKE_COLUMNS_WITH_EQUAL_WIDTH_EDEFAULT;
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
        result.append(" (numberOfColumns: "); //$NON-NLS-1$
        result.append(numberOfColumns);
        result.append(", makeColumnsWithEqualWidth: "); //$NON-NLS-1$
        result.append(makeColumnsWithEqualWidth);
        result.append(')');
        return result.toString();
    }

} // GridLayoutDescriptionImpl
