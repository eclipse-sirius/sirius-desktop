/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree;

import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Item Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.TreeItemStyle#getBackgroundColor <em>Background Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.TreePackage#getTreeItemStyle()
 * @model
 * @generated
 */
public interface TreeItemStyle extends Style, LabelStyle {
    /**
     * Returns the value of the '<em><b>Background Color</b></em>' attribute. The default value is
     * <code>"255,255,255"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Background Color</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Background Color</em>' attribute.
     * @see #setBackgroundColor(RGBValues)
     * @see org.eclipse.sirius.tree.TreePackage#getTreeItemStyle_BackgroundColor()
     * @model default="255,255,255" dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getBackgroundColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.TreeItemStyle#getBackgroundColor <em>Background
     * Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' attribute.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(RGBValues value);

} // TreeItemStyle
