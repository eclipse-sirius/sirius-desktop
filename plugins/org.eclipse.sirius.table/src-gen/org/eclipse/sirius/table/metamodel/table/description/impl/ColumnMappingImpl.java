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
package org.eclipse.sirius.table.metamodel.table.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Column Mapping</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ColumnMappingImpl#getHeaderLabelExpression
 * <em>Header Label Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ColumnMappingImpl#getInitialWidth
 * <em>Initial Width</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ColumnMappingImpl extends TableMappingImpl implements ColumnMapping {
    /**
     * The default value of the '{@link #getHeaderLabelExpression()
     * <em>Header Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHeaderLabelExpression()
     * @generated
     * @ordered
     */
    protected static final String HEADER_LABEL_EXPRESSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHeaderLabelExpression()
     * <em>Header Label Expression</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getHeaderLabelExpression()
     * @generated
     * @ordered
     */
    protected String headerLabelExpression = ColumnMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT;

    /**
     * The default value of the '{@link #getInitialWidth()
     * <em>Initial Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getInitialWidth()
     * @generated
     * @ordered
     */
    protected static final int INITIAL_WIDTH_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getInitialWidth()
     * <em>Initial Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getInitialWidth()
     * @generated
     * @ordered
     */
    protected int initialWidth = ColumnMappingImpl.INITIAL_WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ColumnMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.COLUMN_MAPPING;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getHeaderLabelExpression() {
        return headerLabelExpression;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setHeaderLabelExpression(String newHeaderLabelExpression) {
        String oldHeaderLabelExpression = headerLabelExpression;
        headerLabelExpression = newHeaderLabelExpression;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION, oldHeaderLabelExpression, headerLabelExpression));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public int getInitialWidth() {
        return initialWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setInitialWidth(int newInitialWidth) {
        int oldInitialWidth = initialWidth;
        initialWidth = newInitialWidth;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH, oldInitialWidth, initialWidth));
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
        case DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION:
            return getHeaderLabelExpression();
        case DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH:
            return getInitialWidth();
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
        case DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION:
            setHeaderLabelExpression((String) newValue);
            return;
        case DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH:
            setInitialWidth((Integer) newValue);
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
        case DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION:
            setHeaderLabelExpression(ColumnMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT);
            return;
        case DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH:
            setInitialWidth(ColumnMappingImpl.INITIAL_WIDTH_EDEFAULT);
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
        case DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION:
            return ColumnMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT == null ? headerLabelExpression != null : !ColumnMappingImpl.HEADER_LABEL_EXPRESSION_EDEFAULT.equals(headerLabelExpression);
        case DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH:
            return initialWidth != ColumnMappingImpl.INITIAL_WIDTH_EDEFAULT;
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
        result.append(" (headerLabelExpression: "); //$NON-NLS-1$
        result.append(headerLabelExpression);
        result.append(", initialWidth: "); //$NON-NLS-1$
        result.append(initialWidth);
        result.append(')');
        return result.toString();
    }

} // ColumnMappingImpl
