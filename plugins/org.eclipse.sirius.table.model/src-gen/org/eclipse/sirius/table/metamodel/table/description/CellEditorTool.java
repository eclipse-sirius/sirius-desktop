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
package org.eclipse.sirius.table.metamodel.table.description;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Cell Editor Tool</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.CellEditorTool#getQualifiedClassName <em>Qualified
 * Class Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCellEditorTool()
 * @model
 * @generated
 */
public interface CellEditorTool extends TableTool {
    /**
     * Returns the value of the '<em><b>Qualified Class Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The qualified name of the CellEditorFactory class to provide a
     * CellEditor. This class should inherit from "org.eclipse.sirius.table.ui.tools.api.editor.ITableCellEditorFactory"
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Qualified Class Name</em>' attribute.
     * @see #setQualifiedClassName(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getCellEditorTool_QualifiedClassName()
     * @model required="true"
     * @generated
     */
    String getQualifiedClassName();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.CellEditorTool#getQualifiedClassName <em>Qualified
     * Class Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Qualified Class Name</em>' attribute.
     * @see #getQualifiedClassName()
     * @generated
     */
    void setQualifiedClassName(String value);

} // CellEditorTool
