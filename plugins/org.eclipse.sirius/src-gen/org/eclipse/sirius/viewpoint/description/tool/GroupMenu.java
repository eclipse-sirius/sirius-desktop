/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Group Menu</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.GroupMenu#getLocationURI <em>Location URI</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.GroupMenu#getPopupMenus <em>Popup Menus</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.GroupMenu#getItemDescriptions <em>Item
 * Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getGroupMenu()
 * @model
 * @generated
 */
public interface GroupMenu extends MenuItemDescription {
    /**
     * Returns the value of the '<em><b>Location URI</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> A <code>URI</code> specification that defines
     * the insertion point at which the group will be added.
     *
     * The format for the URI is comprised of two basic parts: * Scheme: One of "menu", "tabbar". Indicates the type of
     * the manager used to handle the contributions. * Id: This is either the id of an existing menu or tabbar menu <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Location URI</em>' attribute.
     * @see #setLocationURI(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getGroupMenu_LocationURI()
     * @model default="" unique="false"
     * @generated
     */
    String getLocationURI();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.GroupMenu#getLocationURI <em>Location
     * URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Location URI</em>' attribute.
     * @see #getLocationURI()
     * @generated
     */
    void setLocationURI(String value);

    /**
     * Returns the value of the '<em><b>Popup Menus</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Popup Menus</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Popup Menus</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getGroupMenu_PopupMenus()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<PopupMenu> getPopupMenus();

    /**
     * Returns the value of the '<em><b>Item Descriptions</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Item Descriptions</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Item Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getGroupMenu_ItemDescriptions()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<GroupMenuItem> getItemDescriptions();

} // GroupMenu
