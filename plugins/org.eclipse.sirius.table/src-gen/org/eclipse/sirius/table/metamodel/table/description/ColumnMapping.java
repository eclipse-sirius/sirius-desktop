/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Column Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getHeaderLabelExpression <em>Header
 * Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getInitialWidth <em>Initial
 * Width</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getColumnMapping()
 * @model
 * @generated
 */
public interface ColumnMapping extends TableMapping {

    /**
     * Returns the value of the '<em><b>Header Label Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Header Label Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Header Label Expression</em>' attribute.
     * @see #setHeaderLabelExpression(String)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getColumnMapping_HeaderLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getHeaderLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getHeaderLabelExpression <em>Header
     * Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Header Label Expression</em>' attribute.
     * @see #getHeaderLabelExpression()
     * @generated
     */
    void setHeaderLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The initial width of the column (calculated if not available). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Initial Width</em>' attribute.
     * @see #setInitialWidth(int)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getColumnMapping_InitialWidth()
     * @model
     * @generated
     */
    int getInitialWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getInitialWidth
     * <em>Initial Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Width</em>' attribute.
     * @see #getInitialWidth()
     * @generated
     */
    void setInitialWidth(int value);
} // ColumnMapping
