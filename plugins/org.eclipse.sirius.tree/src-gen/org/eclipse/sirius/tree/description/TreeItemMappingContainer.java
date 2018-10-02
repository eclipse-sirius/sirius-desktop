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
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Mapping Container</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMappingContainer#getSubItemMappings <em>Sub Item
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMappingContainer#getDropTools <em>Drop Tools</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMappingContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface TreeItemMappingContainer extends EObject {
    /**
     * Returns the value of the '<em><b>Sub Item Mappings</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.tree.description.TreeItemMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Item Mappings</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sub Item Mappings</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMappingContainer_SubItemMappings()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<TreeItemMapping> getSubItemMappings();

    /**
     * Returns the value of the '<em><b>Drop Tools</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Drop Tools</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Drop Tools</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMappingContainer_DropTools()
     * @model containment="true"
     * @generated
     */
    EList<TreeItemContainerDropTool> getDropTools();

} // TreeItemMappingContainer
