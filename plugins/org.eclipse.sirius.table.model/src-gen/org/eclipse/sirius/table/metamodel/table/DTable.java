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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTable</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTable#getColumns <em>Columns</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTable#getHeaderColumnWidth <em>Header Column Width</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTable#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTable()
 * @model
 * @generated
 */
public interface DTable extends DRepresentation, LineContainer {

    /**
     * Returns the value of the '<em><b>Columns</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn}. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.table.metamodel.table.DColumn#getTable <em>Table</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Columns</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Columns</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTable_Columns()
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getTable
     * @model opposite="table" containment="true"
     * @generated
     */
    EList<DColumn> getColumns();

    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(TableDescription)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTable_Description()
     * @model
     * @generated
     */
    TableDescription getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DTable#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(TableDescription value);

    /**
     * Returns the value of the '<em><b>Header Column Width</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Header Column Width</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Header Column Width</em>' attribute.
     * @see #setHeaderColumnWidth(int)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTable_HeaderColumnWidth()
     * @model
     * @generated
     */
    int getHeaderColumnWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DTable#getHeaderColumnWidth <em>Header
     * Column Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Header Column Width</em>' attribute.
     * @see #getHeaderColumnWidth()
     * @generated
     */
    void setHeaderColumnWidth(int value);
} // DTable
