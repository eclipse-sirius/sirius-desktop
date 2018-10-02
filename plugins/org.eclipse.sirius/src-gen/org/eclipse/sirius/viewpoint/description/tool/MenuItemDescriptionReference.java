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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Menu Item Description Reference</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference#getItem <em>Item</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMenuItemDescriptionReference()
 * @model
 * @generated
 */
public interface MenuItemDescriptionReference extends MenuItemOrRef {
    /**
     * Returns the value of the '<em><b>Item</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Item</em>' reference isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Item</em>' reference.
     * @see #setItem(MenuItemDescription)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMenuItemDescriptionReference_Item()
     * @model required="true"
     * @generated
     */
    MenuItemDescription getItem();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference#getItem
     * <em>Item</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Item</em>' reference.
     * @see #getItem()
     * @generated
     */
    void setItem(MenuItemDescription value);

} // MenuItemDescriptionReference
