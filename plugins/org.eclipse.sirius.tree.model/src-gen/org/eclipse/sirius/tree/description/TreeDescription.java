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
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Tree Description</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.TreeDescription#getDomainClass <em>Domain Class</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeDescription#getPreconditionExpression <em>Precondition
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeDescription#getCreateTreeItem <em>Create Tree Item</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeDescription#getOwnedRepresentationCreationDescriptions <em>Owned
 * Representation Creation Descriptions</em>}</li>
 * <li>{@link org.eclipse.sirius.tree.description.TreeDescription#getOwnedRepresentationNavigationDescriptions <em>Owned
 * Representation Navigation Descriptions</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDescription()
 * @model
 * @generated
 */
public interface TreeDescription extends RepresentationDescription, TreeItemMappingContainer {
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
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDescription_DomainClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getDomainClass();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeDescription#getDomainClass <em>Domain
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
     * <!-- end-user-doc --> <!-- begin-model-doc --> The precondition (Acceleo Expression). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Precondition Expression</em>' attribute.
     * @see #setPreconditionExpression(String)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDescription_PreconditionExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='boolean'"
     * @generated
     */
    String getPreconditionExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.TreeDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition Expression</em>' attribute.
     * @see #getPreconditionExpression()
     * @generated
     */
    void setPreconditionExpression(String value);

    /**
     * Returns the value of the '<em><b>Create Tree Item</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.tree.description.TreeItemCreationTool}. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> All tools of the section. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Create Tree Item</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDescription_CreateTreeItem()
     * @model containment="true"
     * @generated
     */
    EList<TreeItemCreationTool> getCreateTreeItem();

    /**
     * Returns the value of the ' <em><b>Owned Representation Creation Descriptions</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription} . <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> All tools of the section. <!-- end-model-doc -->
     *
     * @return the value of the ' <em>Owned Representation Creation Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDescription_OwnedRepresentationCreationDescriptions()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<RepresentationCreationDescription> getOwnedRepresentationCreationDescriptions();

    /**
     * Returns the value of the ' <em><b>Owned Representation Navigation Descriptions</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription} . <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> All navigation tools. <!-- end-model-doc -->
     *
     * @return the value of the ' <em>Owned Representation Navigation Descriptions</em>' containment reference list.
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getTreeDescription_OwnedRepresentationNavigationDescriptions()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<RepresentationNavigationDescription> getOwnedRepresentationNavigationDescriptions();

} // TreeDescription
