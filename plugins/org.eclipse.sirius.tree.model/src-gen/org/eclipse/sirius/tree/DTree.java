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
package org.eclipse.sirius.tree;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tree.description.TreeDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTree</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.DTree#getSemanticElements <em>Semantic Elements</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.DTree#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.TreePackage#getDTree()
 * @model
 * @generated
 */
public interface DTree extends DRepresentation, DTreeItemContainer {

    /**
     * Returns the value of the '<em><b>Semantic Elements</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Elements</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Elements</em>' reference list.
     * @see org.eclipse.sirius.tree.TreePackage#getDTree_SemanticElements()
     * @model
     * @generated
     */
    EList<EObject> getSemanticElements();

    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(TreeDescription)
     * @see org.eclipse.sirius.tree.TreePackage#getDTree_Description()
     * @model required="true"
     * @generated
     */
    TreeDescription getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.DTree#getDescription <em>Description</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(TreeDescription value);
} // DTree
