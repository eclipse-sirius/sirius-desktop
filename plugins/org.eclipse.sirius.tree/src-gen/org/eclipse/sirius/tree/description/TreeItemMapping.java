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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Mapping</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getDomainClass <em>Domain Class</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getPreconditionExpression <em>Precondition
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getSemanticCandidatesExpression <em>Semantic
 * Candidates Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getReusedTreeItemMappings <em>Reused Tree Item
 * Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getAllSubMappings <em>All Sub Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getSpecialize <em>Specialize</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getDelete <em>Delete</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getCreate <em>Create</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getDndTools <em>Dnd Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemMapping#getPopupMenus <em>Popup Menus</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping()
 * @model
 * @generated
 */
public interface TreeItemMapping extends TreeMapping, StyleUpdater, TreeItemUpdater, TreeItemMappingContainer {
    /**
     * Returns the value of the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Domain Class</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Domain Class</em>' attribute.
     * @see #setDomainClass(String)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemMapping#getDomainClass <em>Domain
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Domain Class</em>' attribute.
     * @see #getDomainClass()
     * @generated
     */
    void setDomainClass(String value);

    /**
     * Returns the value of the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precondition Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The elements that are represented by this mapping. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_PreconditionExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an
     *        EObject.'" annotation= "http://www.eclipse.org/sirius/interpreted/expression/variables
     *        view='tree.DTreeElement | current DTreeElement.' containerView='ecore.EObject | container of the current
     *        DTreeElement (variable is available if container is not null).' container='ecore.EObject | semantic target
     *        of $containerView (if it is a DSemanticDecorator).'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemMapping#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>' attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The elements that are represented by this mapping. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Semantic Candidates Expression</em>' attribute.
     * @see #setSemanticCandidatesExpression(String)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_SemanticCandidatesExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a Collection<EObject> or an
     *        EObject.'" annotation= "http://www.eclipse.org/sirius/interpreted/expression/variables
     *        view='tree.DTreeElement | current DTreeElement.' containerView='ecore.EObject | container of the current
     *        DTreeElement (variable is available if container is not null).' container='ecore.EObject | semantic target
     *        of $containerView (if it is a DSemanticDecorator).'"
     * @generated
     */
    String getSemanticCandidatesExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Candidates Expression</em>' attribute.
     * @see #getSemanticCandidatesExpression()
     * @generated
     */
    void setSemanticCandidatesExpression(String value);

    /**
     * Returns the value of the '<em><b>Reused Tree Item Mappings</b></em>' reference list. The list contents are of
     * type {@link org.eclipse.sirius.tree.description.TreeItemMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reused Tree Item Mappings</em>' reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reused Tree Item Mappings</em>' reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_ReusedTreeItemMappings()
     * @model
     * @generated
     */
    EList<TreeItemMapping> getReusedTreeItemMappings();

    /**
     * Returns the value of the '<em><b>All Sub Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeItemMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>All Sub Mappings</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>All Sub Mappings</em>' reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_AllSubMappings()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<TreeItemMapping> getAllSubMappings();

    /**
     * Returns the value of the '<em><b>Specialize</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Specialize</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Specialize</em>' reference.
     * @see #setSpecialize(TreeItemMapping)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_Specialize()
     * @model
     * @generated
     */
    TreeItemMapping getSpecialize();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemMapping#getSpecialize
     * <em>Specialize</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Specialize</em>' reference.
     * @see #getSpecialize()
     * @generated
     */
    void setSpecialize(TreeItemMapping value);

    /**
     * Returns the value of the '<em><b>Delete</b></em>' containment reference. It is bidirectional and its opposite is
     * '{@link org.eclipse.sirius.tree.description.TreeItemDeletionTool#getMapping <em>Mapping</em>}'. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delete</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Delete</em>' containment reference.
     * @see #setDelete(TreeItemDeletionTool)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_Delete()
     * @see org.eclipse.sirius.tree.description.TreeItemDeletionTool#getMapping
     * @model opposite="mapping" containment="true"
     * @generated
     */
    TreeItemDeletionTool getDelete();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemMapping#getDelete <em>Delete</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Delete</em>' containment reference.
     * @see #getDelete()
     * @generated
     */
    void setDelete(TreeItemDeletionTool value);

    /**
     * Returns the value of the '<em><b>Create</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeItemCreationTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Create</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Create</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_Create()
     * @model containment="true"
     * @generated
     */
    EList<TreeItemCreationTool> getCreate();

    /**
     * Returns the value of the '<em><b>Dnd Tools</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeItemDragTool}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dnd Tools</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Dnd Tools</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_DndTools()
     * @model containment="true"
     * @generated
     */
    @Deprecated
    EList<TreeItemDragTool> getDndTools();

    /**
     * Returns the value of the '<em><b>Popup Menus</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreePopupMenu}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Popup Menus</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Popup Menus</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemMapping_PopupMenus()
     * @model containment="true"
     * @generated
     */
    EList<TreePopupMenu> getPopupMenus();

} // TreeItemMapping
