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

import org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable;
import org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable;
import org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Item Container Drop Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Tool that describes a Drag & Drop operation. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getOldContainer <em>Old Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getNewContainer <em>New Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getElement <em>Element</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getNewViewContainer <em>New View
 * Container</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getPrecedingSiblings <em>Preceding
 * Siblings</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getDragSource <em>Drag Source</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool()
 * @model annotation="http://www.eclipse.org/sirius/interpreted/expression/variables_precondition
 *        newContainer='ecore.EObject | the new container.'"
 * @generated
 */
public interface TreeItemContainerDropTool extends MappingBasedToolDescription, TreeItemTool {
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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool_OldContainer()
     * @model containment="true" required="true"
     * @generated
     */
    DropContainerVariable getOldContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getOldContainer
     * <em>Old Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool_NewContainer()
     * @model containment="true" required="true"
     * @generated
     */
    DropContainerVariable getNewContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getNewContainer
     * <em>New Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool_Element()
     * @model containment="true" required="true"
     * @generated
     */
    ElementDropVariable getElement();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getElement
     * <em>Element</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool_NewViewContainer()
     * @model containment="true" required="true"
     * @generated
     */
    ContainerViewVariable getNewViewContainer();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getNewViewContainer
     * <em>New View Container</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>New View Container</em>' containment reference.
     * @see #getNewViewContainer()
     * @generated
     */
    void setNewViewContainer(ContainerViewVariable value);

    /**
     * Returns the value of the '<em><b>Preceding Siblings</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Preceding Siblings</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Preceding Siblings</em>' containment reference.
     * @see #setPrecedingSiblings(PrecedingSiblingsVariables)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool_PrecedingSiblings()
     * @model containment="true" required="true"
     * @generated
     */
    PrecedingSiblingsVariables getPrecedingSiblings();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getPrecedingSiblings
     * <em>Preceding Siblings</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Preceding Siblings</em>' containment reference.
     * @see #getPrecedingSiblings()
     * @generated
     */
    void setPrecedingSiblings(PrecedingSiblingsVariables value);

    /**
     * Returns the value of the '<em><b>Drag Source</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.sirius.tree.description.TreeDragSource}. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Authorized sources of the drag. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Drag Source</em>' attribute.
     * @see org.eclipse.sirius.tree.description.TreeDragSource
     * @see #setDragSource(TreeDragSource)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeItemContainerDropTool_DragSource()
     * @model required="true"
     * @generated
     */
    TreeDragSource getDragSource();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeItemContainerDropTool#getDragSource
     * <em>Drag Source</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Drag Source</em>' attribute.
     * @see org.eclipse.sirius.tree.description.TreeDragSource
     * @see #getDragSource()
     * @generated
     */
    void setDragSource(TreeDragSource value);

} // TreeItemContainerDropTool
