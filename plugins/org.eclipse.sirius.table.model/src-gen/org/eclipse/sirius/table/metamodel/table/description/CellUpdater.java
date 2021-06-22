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
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Cell Updater</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getDirectEdit <em>Direct Edit</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getCanEdit <em>Can Edit</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getCellEditor <em>Cell Editor</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCellUpdater()
 * @model
 * @generated
 */
public interface CellUpdater extends EObject {
    /**
     * Returns the value of the '<em><b>Direct Edit</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Direct Edit</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Direct Edit</em>' containment reference.
     * @see #setDirectEdit(LabelEditTool)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCellUpdater_DirectEdit()
     * @model containment="true"
     * @generated
     */
    LabelEditTool getDirectEdit();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getDirectEdit
     * <em>Direct Edit</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Direct Edit</em>' containment reference.
     * @see #getDirectEdit()
     * @generated
     */
    void setDirectEdit(LabelEditTool value);

    /**
     * Returns the value of the '<em><b>Can Edit</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Can Edit</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Can Edit</em>' attribute.
     * @see #setCanEdit(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCellUpdater_CanEdit()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getCanEdit();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getCanEdit <em>Can
     * Edit</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Can Edit</em>' attribute.
     * @see #getCanEdit()
     * @generated
     */
    void setCanEdit(String value);

    /**
     * Returns the value of the '<em><b>Cell Editor</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Cell Editor</em>' containment reference.
     * @see #setCellEditor(CellEditorTool)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCellUpdater_CellEditor()
     * @model containment="true"
     * @generated
     */
    CellEditorTool getCellEditor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getCellEditor
     * <em>Cell Editor</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Cell Editor</em>' containment reference.
     * @see #getCellEditor()
     * @generated
     */
    void setCellEditor(CellEditorTool value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     * @generated
     */
    String getLabelComputationExpression();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    CreateCellTool getCreateCell();

} // CellUpdater
