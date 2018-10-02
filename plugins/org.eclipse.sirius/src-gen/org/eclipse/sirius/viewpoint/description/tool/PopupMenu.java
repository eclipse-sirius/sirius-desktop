/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Popup Menu</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu#getMenuItemDescription <em>Menu Item
 * Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPopupMenu()
 * @model
 * @generated
 */
public interface PopupMenu extends AbstractToolDescription, GroupMenuItem {
    /**
     * Returns the value of the '<em><b>Menu Item Description</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Menu Item Description</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Menu Item Description</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getPopupMenu_MenuItemDescription()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EList<MenuItemDescription> getMenuItemDescription();

} // PopupMenu
