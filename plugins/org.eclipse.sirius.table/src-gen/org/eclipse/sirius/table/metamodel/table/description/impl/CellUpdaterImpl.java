/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table.description.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.sirius.table.metamodel.table.description.CellEditorTool;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.description.CreateCellTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Cell Updater</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl#getDirectEdit <em>Direct
 * Edit</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl#getCanEdit <em>Can
 * Edit</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl#getCellEditor <em>Cell
 * Editor</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CellUpdaterImpl extends MinimalEObjectImpl.Container implements CellUpdater {
    /**
     * The cached value of the '{@link #getDirectEdit() <em>Direct Edit</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getDirectEdit()
     * @generated
     * @ordered
     */
    protected LabelEditTool directEdit;

    /**
     * The default value of the '{@link #getCanEdit() <em>Can Edit</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getCanEdit()
     * @generated
     * @ordered
     */
    protected static final String CAN_EDIT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getCanEdit() <em>Can Edit</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getCanEdit()
     * @generated
     * @ordered
     */
    protected String canEdit = CellUpdaterImpl.CAN_EDIT_EDEFAULT;

    /**
     * The cached value of the '{@link #getCellEditor() <em>Cell Editor</em>}' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getCellEditor()
     * @generated
     * @ordered
     */
    protected CellEditorTool cellEditor;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected CellUpdaterImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return DescriptionPackage.Literals.CELL_UPDATER;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelEditTool getDirectEdit() {
        return directEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetDirectEdit(LabelEditTool newDirectEdit, NotificationChain msgs) {
        LabelEditTool oldDirectEdit = directEdit;
        directEdit = newDirectEdit;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.CELL_UPDATER__DIRECT_EDIT, oldDirectEdit, newDirectEdit);
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
    public void setDirectEdit(LabelEditTool newDirectEdit) {
        if (newDirectEdit != directEdit) {
            NotificationChain msgs = null;
            if (directEdit != null) {
                msgs = ((InternalEObject) directEdit).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CELL_UPDATER__DIRECT_EDIT, null, msgs);
            }
            if (newDirectEdit != null) {
                msgs = ((InternalEObject) newDirectEdit).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CELL_UPDATER__DIRECT_EDIT, null, msgs);
            }
            msgs = basicSetDirectEdit(newDirectEdit, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CELL_UPDATER__DIRECT_EDIT, newDirectEdit, newDirectEdit));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getCanEdit() {
        return canEdit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCanEdit(String newCanEdit) {
        String oldCanEdit = canEdit;
        canEdit = newCanEdit;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CELL_UPDATER__CAN_EDIT, oldCanEdit, canEdit));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CellEditorTool getCellEditor() {
        return cellEditor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetCellEditor(CellEditorTool newCellEditor, NotificationChain msgs) {
        CellEditorTool oldCellEditor = cellEditor;
        cellEditor = newCellEditor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DescriptionPackage.CELL_UPDATER__CELL_EDITOR, oldCellEditor, newCellEditor);
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
    public void setCellEditor(CellEditorTool newCellEditor) {
        if (newCellEditor != cellEditor) {
            NotificationChain msgs = null;
            if (cellEditor != null) {
                msgs = ((InternalEObject) cellEditor).eInverseRemove(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CELL_UPDATER__CELL_EDITOR, null, msgs);
            }
            if (newCellEditor != null) {
                msgs = ((InternalEObject) newCellEditor).eInverseAdd(this, InternalEObject.EOPPOSITE_FEATURE_BASE - DescriptionPackage.CELL_UPDATER__CELL_EDITOR, null, msgs);
            }
            msgs = basicSetCellEditor(newCellEditor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, DescriptionPackage.CELL_UPDATER__CELL_EDITOR, newCellEditor, newCellEditor));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabelComputationExpression() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CreateCellTool getCreateCell() {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case DescriptionPackage.CELL_UPDATER__DIRECT_EDIT:
            return basicSetDirectEdit(null, msgs);
        case DescriptionPackage.CELL_UPDATER__CELL_EDITOR:
            return basicSetCellEditor(null, msgs);
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
        case DescriptionPackage.CELL_UPDATER__DIRECT_EDIT:
            return getDirectEdit();
        case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
            return getCanEdit();
        case DescriptionPackage.CELL_UPDATER__CELL_EDITOR:
            return getCellEditor();
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
        case DescriptionPackage.CELL_UPDATER__DIRECT_EDIT:
            setDirectEdit((LabelEditTool) newValue);
            return;
        case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
            setCanEdit((String) newValue);
            return;
        case DescriptionPackage.CELL_UPDATER__CELL_EDITOR:
            setCellEditor((CellEditorTool) newValue);
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
        case DescriptionPackage.CELL_UPDATER__DIRECT_EDIT:
            setDirectEdit((LabelEditTool) null);
            return;
        case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
            setCanEdit(CellUpdaterImpl.CAN_EDIT_EDEFAULT);
            return;
        case DescriptionPackage.CELL_UPDATER__CELL_EDITOR:
            setCellEditor((CellEditorTool) null);
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
        case DescriptionPackage.CELL_UPDATER__DIRECT_EDIT:
            return directEdit != null;
        case DescriptionPackage.CELL_UPDATER__CAN_EDIT:
            return CellUpdaterImpl.CAN_EDIT_EDEFAULT == null ? canEdit != null : !CellUpdaterImpl.CAN_EDIT_EDEFAULT.equals(canEdit);
        case DescriptionPackage.CELL_UPDATER__CELL_EDITOR:
            return cellEditor != null;
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
        result.append(" (canEdit: "); //$NON-NLS-1$
        result.append(canEdit);
        result.append(')');
        return result.toString();
    }

} // CellUpdaterImpl
