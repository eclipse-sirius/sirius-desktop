/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Create Edge View</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getSourceExpression
 * <em>Source Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getTargetExpression
 * <em>Target Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getCreateEdgeView()
 * @model
 * @generated
 */
public interface CreateEdgeView extends CreateView {
    /**
     * Returns the value of the '<em><b>Source Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source Expression</em>' attribute.
     * @see #setSourceExpression(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getCreateEdgeView_SourceExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     * @generated
     */
    String getSourceExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getSourceExpression
     * <em>Source Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Expression</em>' attribute.
     * @see #getSourceExpression()
     * @generated
     */
    void setSourceExpression(String value);

    /**
     * Returns the value of the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target Expression</em>' attribute.
     * @see #setTargetExpression(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getCreateEdgeView_TargetExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     * @generated
     */
    String getTargetExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getTargetExpression
     * <em>Target Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Expression</em>' attribute.
     * @see #getTargetExpression()
     * @generated
     */
    void setTargetExpression(String value);

} // CreateEdgeView
