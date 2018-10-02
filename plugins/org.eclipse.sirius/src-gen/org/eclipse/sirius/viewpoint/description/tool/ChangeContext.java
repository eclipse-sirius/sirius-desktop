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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Change Context</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This operation allows to change the execution context. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext#getBrowseExpression <em>Browse
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getChangeContext()
 * @model
 * @generated
 */
public interface ChangeContext extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Browse Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> Expression to browse to a new context (kind of a GOTO for models). <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Browse Expression</em>' attribute.
     * @see #setBrowseExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getChangeContext_BrowseExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation =
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an EObject.'"
     * @generated
     */
    String getBrowseExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext#getBrowseExpression
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Browse Expression</em>' attribute.
     * @see #getBrowseExpression()
     * @generated
     */
    void setBrowseExpression(String value);

} // ChangeContext
