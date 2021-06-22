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
import org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Popup Menu</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreePopupMenu#getMenuItemDescriptions <em>Menu Item Descriptions</em>}
 * </li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreePopupMenu()
 * @model
 * @generated
 */
public interface TreePopupMenu extends AbstractToolDescription {

    /**
     * Returns the value of the '<em><b>Menu Item Descriptions</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Menu Item Descriptions</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Menu Item Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreePopupMenu_MenuItemDescriptions()
     * @model containment="true"
     * @generated
     */
    EList<MenuItemOrRef> getMenuItemDescriptions();
} // TreePopupMenu
