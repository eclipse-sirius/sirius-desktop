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
import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Drag Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> (deprecated) Tool that describes a Drag & Drop operation. The Source of this Drag & Drop
 * will be the Tree item Mapping on which the tool has been defined. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getOldContainer <em>Old Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getNewContainer <em>New Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getElement <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getNewViewContainer <em>New View Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getContainers <em>Containers</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getDragSourceType <em>Drag Source Type</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getPrecedingSiblings <em>Preceding
 * Siblings</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool()
 * @model
 * @generated
 */
@Deprecated
public interface TreeItemDragTool extends MappingBasedToolDescription, TreeItemTool {
    /**
     * Returns the value of the '<em><b>Old Container</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Old Container</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Old Container</em>' containment reference.
     * @see #setOldContainer(DropContainerVariable)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_OldContainer()
     * @model containment="true" required="true"
     * @generated
     */
    DropContainerVariable getOldContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getOldContainer <em>Old
     * Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Old Container</em>' containment reference.
     * @see #getOldContainer()
     * @generated
     */
    void setOldContainer(DropContainerVariable value);

    /**
     * Returns the value of the '<em><b>New Container</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>New Container</em>' containment reference isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>New Container</em>' containment reference.
     * @see #setNewContainer(DropContainerVariable)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_NewContainer()
     * @model containment="true" required="true"
     * @generated
     */
    DropContainerVariable getNewContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getNewContainer <em>New
     * Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>New Container</em>' containment reference.
     * @see #getNewContainer()
     * @generated
     */
    void setNewContainer(DropContainerVariable value);

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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_Element()
     * @model containment="true" required="true"
     * @generated
     */
    ElementDropVariable getElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getElement <em>Element</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Element</em>' containment reference.
     * @see #getElement()
     * @generated
     */
    void setElement(ElementDropVariable value);

    /**
     * Returns the value of the '<em><b>New View Container</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>New View Container</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>New View Container</em>' containment reference.
     * @see #setNewViewContainer(ContainerViewVariable)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_NewViewContainer()
     * @model containment="true" required="true"
     * @generated
     */
    ContainerViewVariable getNewViewContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getNewViewContainer <em>New
     * View Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>New View Container</em>' containment reference.
     * @see #getNewViewContainer()
     * @generated
     */
    void setNewViewContainer(ContainerViewVariable value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    TreeItemMapping getBestTreeItemMapping();

    /**
     * Returns the value of the '<em><b>Containers</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.tree.description.TreeItemMappingContainer}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the value of the '<em>Containers</em>' reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_Containers()
     * @model required="true"
     * @generated
     */
    EList<TreeItemMappingContainer> getContainers();

    /**
     * Returns the value of the '<em><b>Drag Source Type</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.sirius.tree.description.TreeDragSource}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Drag Source Type</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Drag Source Type</em>' attribute.
     * @see org.eclipse.sirius.tree.description.TreeDragSource
     * @see #setDragSourceType(TreeDragSource)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_DragSourceType()
     * @model required="true"
     * @generated
     */
    TreeDragSource getDragSourceType();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getDragSourceType <em>Drag
     * Source Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Drag Source Type</em>' attribute.
     * @see org.eclipse.sirius.tree.description.TreeDragSource
     * @see #getDragSourceType()
     * @generated
     */
    void setDragSourceType(TreeDragSource value);

    /**
     * Returns the value of the '<em><b>Preceding Siblings</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Preceding Siblings</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Preceding Siblings</em>' containment reference.
     * @see #setPrecedingSiblings(PrecedingSiblingsVariables)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemDragTool_PrecedingSiblings()
     * @model containment="true" required="true"
     * @generated
     */
    PrecedingSiblingsVariables getPrecedingSiblings();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemDragTool#getPrecedingSiblings
     * <em>Preceding Siblings</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Preceding Siblings</em>' containment reference.
     * @see #getPrecedingSiblings()
     * @generated
     */
    void setPrecedingSiblings(PrecedingSiblingsVariables value);

} // TreeItemDragTool
