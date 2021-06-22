/**
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.table.metamodel.table.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Cell Editor Tool</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CellEditorToolImpl#getQualifiedClassName
 * <em>Qualified Class Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CellEditorToolImpl extends TableToolImpl implements CellEditorTool {
    /**
     * The default value of the '{@link #getQualifiedClassName() <em>Qualified Class Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getQualifiedClassName()
     * @generated
     * @ordered
     */
    protected static final String QUALIFIED_CLASS_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getQualifiedClassName() <em>Qualified Class Name</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getQualifiedClassName()
     * @generated
     * @ordered
     */
    protected String qualifiedClassName = CellEditorToolImpl.QUALIFIED_CLASS_NAME_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CellEditorToolImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CELL_EDITOR_TOOL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setQualifiedClassName(String newQualifiedClassName) {
        String oldQualifiedClassName = qualifiedClassName;
        qualifiedClassName = newQualifiedClassName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CELL_EDITOR_TOOL__QUALIFIED_CLASS_NAME, oldQualifiedClassName, qualifiedClassName));
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
        case DescriptionPackage.CELL_EDITOR_TOOL__QUALIFIED_CLASS_NAME:
            return getQualifiedClassName();
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
        case DescriptionPackage.CELL_EDITOR_TOOL__QUALIFIED_CLASS_NAME:
            setQualifiedClassName((String) newValue);
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
        case DescriptionPackage.CELL_EDITOR_TOOL__QUALIFIED_CLASS_NAME:
            setQualifiedClassName(CellEditorToolImpl.QUALIFIED_CLASS_NAME_EDEFAULT);
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
        case DescriptionPackage.CELL_EDITOR_TOOL__QUALIFIED_CLASS_NAME:
            return CellEditorToolImpl.QUALIFIED_CLASS_NAME_EDEFAULT == null ? qualifiedClassName != null : !CellEditorToolImpl.QUALIFIED_CLASS_NAME_EDEFAULT.equals(qualifiedClassName);
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

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (qualifiedClassName: "); //$NON-NLS-1$
        result.append(qualifiedClassName);
        result.append(')');
        return result.toString();
    }

} // CellEditorToolImpl
