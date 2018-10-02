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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Table Tool</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableTool#getVariables <em>Variables</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.description.TableTool#getFirstModelOperation <em>First Model
 * Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableTool()
 * @model
 * @generated
 */
public interface TableTool extends EObject {
    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableVariable}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variables</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableTool_Variables()
     * @model containment="true"
     * @generated
     */
    EList<TableVariable> getVariables();

    /**
     * Returns the value of the '<em><b>First Model Operation</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>First Model Operation</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>First Model Operation</em>' containment reference.
     * @see #setFirstModelOperation(ModelOperation)
     * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage#getTableTool_FirstModelOperation()
     * @model containment="true" required="true"
     * @generated
     */
    ModelOperation getFirstModelOperation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.description.TableTool#getFirstModelOperation <em>First Model
     * Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>First Model Operation</em>' containment reference.
     * @see #getFirstModelOperation()
     * @generated
     */
    void setFirstModelOperation(ModelOperation value);

} // TableTool
