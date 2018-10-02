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

import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Navigation Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeNavigationDescription#getTreeDescription <em>Tree
 * Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeNavigationDescription()
 * @model
 * @generated
 */
public interface TreeNavigationDescription extends RepresentationNavigationDescription {
    /**
     * Returns the value of the '<em><b>Tree Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tree Description</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tree Description</em>' reference.
     * @see #setTreeDescription(TreeDescription)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeNavigationDescription_TreeDescription()
     * @model required="true"
     * @generated
     */
    TreeDescription getTreeDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeNavigationDescription#getTreeDescription
     * <em>Tree Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tree Description</em>' reference.
     * @see #getTreeDescription()
     * @generated
     */
    void setTreeDescription(TreeDescription value);

} // TreeNavigationDescription
