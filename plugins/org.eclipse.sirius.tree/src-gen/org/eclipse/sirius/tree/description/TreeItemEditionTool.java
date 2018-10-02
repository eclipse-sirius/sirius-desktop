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
import org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Edition Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getMask <em>Mask</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getMapping <em>Mapping</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getElement <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemEditionTool()
 * @model
 * @generated
 */
public interface TreeItemEditionTool extends TreeItemTool {
    /**
     * Returns the value of the '<em><b>Mask</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mask</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mask</em>' containment reference.
     * @see #setMask(EditMaskVariables)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemEditionTool_Mask()
     * @model containment="true" required="true"
     * @generated
     */
    EditMaskVariables getMask();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getMask <em>Mask</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mask</em>' containment reference.
     * @see #getMask()
     * @generated
     */
    void setMask(EditMaskVariables value);

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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemEditionTool_Mapping()
     * @model
     * @generated
     */
    EList<TreeItemMapping> getMapping();

    /**
     * Returns the value of the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Element</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Element</em>' containment reference.
     * @see #setElement(ElementDropVariable)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemEditionTool_Element()
     * @model containment="true" required="true"
     * @generated
     */
    ElementDropVariable getElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementDropVariable value);

    /**
     * Returns the value of the '<em><b>Root</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Root</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Root</em>' containment reference.
     * @see #setRoot(ElementDropVariable)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemEditionTool_Root()
     * @model containment="true" required="true"
     * @generated
     */
    ElementDropVariable getRoot();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemEditionTool#getRoot <em>Root</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Root</em>' containment reference.
     * @see #getRoot()
     * @generated
     */
    void setRoot(ElementDropVariable value);

} // TreeItemEditionTool
