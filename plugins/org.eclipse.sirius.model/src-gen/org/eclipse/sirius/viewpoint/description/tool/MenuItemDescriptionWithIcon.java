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

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Menu Item Description With Icon</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionWithIcon#getIcon <em>Icon</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMenuItemDescriptionWithIcon()
 * @model abstract="true"
 * @generated
 */
public interface MenuItemDescriptionWithIcon extends MenuItemDescription {
    /**
     * Returns the value of the '<em><b>Icon</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Icon</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Icon</em>' attribute.
     * @see #setIcon(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMenuItemDescriptionWithIcon_Icon()
     * @model dataType="org.eclipse.sirius.viewpoint.description.ImagePath"
     * @generated
     */
    String getIcon();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionWithIcon#getIcon
     * <em>Icon</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Icon</em>' attribute.
     * @see #getIcon()
     * @generated
     */
    void setIcon(String value);

} // MenuItemDescriptionWithIcon
