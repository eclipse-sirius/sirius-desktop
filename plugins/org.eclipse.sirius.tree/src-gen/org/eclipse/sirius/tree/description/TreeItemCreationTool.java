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
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Creation Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemCreationTool#getMapping <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemCreationTool()
 * @model
 * @generated
 */
public interface TreeItemCreationTool extends TreeItemTool, MappingBasedToolDescription {
    /**
     * Returns the value of the '<em><b>Mapping</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeItemMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapping</em>' reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemCreationTool_Mapping()
     * @model
     * @generated
     */
    EList<TreeItemMapping> getMapping();

} // TreeItemCreationTool
