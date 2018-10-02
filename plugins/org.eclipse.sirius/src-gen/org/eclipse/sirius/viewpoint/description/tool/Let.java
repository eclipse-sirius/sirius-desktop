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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Let</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This operation allows the creation of a new variable. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.Let#getVariableName <em>Variable Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.Let#getValueExpression <em>Value Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getLet()
 * @model
 * @generated
 */
public interface Let extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Variable Name</b></em>' attribute. The default value is <code>"instance"</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Once the variable is created, it will be
     * bound with the name given here and will be available to any contained operation. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Variable Name</em>' attribute.
     * @see #setVariableName(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getLet_VariableName()
     * @model default="instance" required="true"
     * @generated
     */
    String getVariableName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.Let#getVariableName <em>Variable
     * Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Variable Name</em>' attribute.
     * @see #getVariableName()
     * @generated
     */
    void setVariableName(String value);

    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> Expression used to initialize the value of the new variable. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getLet_ValueExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true" annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='any kind of value
     *        which can be manipulated by a Sirius interpreter'"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.Let#getValueExpression <em>Value
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

} // Let
