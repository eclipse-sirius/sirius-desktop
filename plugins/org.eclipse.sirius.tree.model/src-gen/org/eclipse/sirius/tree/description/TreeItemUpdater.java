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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Updater</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemUpdater#getDirectEdit <em>Direct Edit</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemUpdater()
 * @model
 * @generated
 */
public interface TreeItemUpdater extends EObject {
    /**
     * Returns the value of the '<em><b>Direct Edit</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Direct Edit</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Direct Edit</em>' containment reference.
     * @see #setDirectEdit(TreeItemEditionTool)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemUpdater_DirectEdit()
     * @model containment="true"
     * @generated
     */
    TreeItemEditionTool getDirectEdit();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemUpdater#getDirectEdit <em>Direct
     * Edit</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Direct Edit</em>' containment reference.
     * @see #getDirectEdit()
     * @generated
     */
    void setDirectEdit(TreeItemEditionTool value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     * @generated
     */
    String getLabelComputationExpression();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    TreeItemCreationTool getCreateTreeItem();

} // TreeItemUpdater
