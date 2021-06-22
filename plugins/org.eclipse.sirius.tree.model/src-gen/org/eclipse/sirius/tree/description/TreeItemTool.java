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
package org.eclipse.sirius.tree.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Tool</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemTool#getFirstModelOperation <em>First Model
 * Operation</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemTool#getVariables <em>Variables</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemTool()
 * @model abstract="true"
 * @generated
 */
public interface TreeItemTool extends AbstractToolDescription {
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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemTool_FirstModelOperation()
     * @model containment="true"
     * @generated
     */
    ModelOperation getFirstModelOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemTool#getFirstModelOperation <em>First
     * Model Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>First Model Operation</em>' containment reference.
     * @see #getFirstModelOperation()
     * @generated
     */
    void setFirstModelOperation(ModelOperation value);

    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeVariable}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variables</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemTool_Variables()
     * @model containment="true"
     * @generated
     */
    EList<TreeVariable> getVariables();

} // TreeItemTool
