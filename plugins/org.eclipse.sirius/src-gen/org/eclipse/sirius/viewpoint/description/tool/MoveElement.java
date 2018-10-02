/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Move Element</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Move the element of the current context to another container. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.MoveElement#getNewContainerExpression <em>New Container
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.MoveElement#getFeatureName <em>Feature Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMoveElement()
 * @model
 * @generated
 */
public interface MoveElement extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>New Container Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Expression computing the new container. <!-- end-model-doc -->
     *
     * @return the value of the '<em>New Container Expression</em>' attribute.
     * @see #setNewContainerExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMoveElement_NewContainerExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     * @generated
     */
    String getNewContainerExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.MoveElement#getNewContainerExpression
     * <em>New Container Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>New Container Expression</em>' attribute.
     * @see #getNewContainerExpression()
     * @generated
     */
    void setNewContainerExpression(String value);

    /**
     * Returns the value of the '<em><b>Feature Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The name of the reference in the new container to put the element in. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Feature Name</em>' attribute.
     * @see #setFeatureName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getMoveElement_FeatureName()
     * @model dataType="org.eclipse.sirius.viewpoint.description.FeatureName" required="true"
     * @generated
     */
    String getFeatureName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.MoveElement#getFeatureName
     * <em>Feature Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Feature Name</em>' attribute.
     * @see #getFeatureName()
     * @generated
     */
    void setFeatureName(String value);

} // MoveElement
