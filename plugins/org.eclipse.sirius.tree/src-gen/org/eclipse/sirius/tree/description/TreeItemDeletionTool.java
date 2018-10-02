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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Deletion Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDeletionTool#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDeletionTool()
 * @model
 * @generated
 */
public interface TreeItemDeletionTool extends TreeItemTool {
    /**
     * Returns the value of the '<em><b>Mapping</b></em>' container reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.tree.description.TreeItemMapping#getDelete <em>Delete</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapping</em>' container reference.
     * @see #setMapping(TreeItemMapping)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDeletionTool_Mapping()
     * @see org.eclipse.sirius.tree.description.TreeItemMapping#getDelete
     * @model opposite="delete" required="true" transient="false"
     * @generated
     */
    TreeItemMapping getMapping();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDeletionTool#getMapping
     * <em>Mapping</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mapping</em>' container reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(TreeItemMapping value);

} // TreeItemDeletionTool
