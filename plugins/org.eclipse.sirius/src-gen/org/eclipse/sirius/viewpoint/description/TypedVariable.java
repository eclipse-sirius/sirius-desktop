/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EDataType;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Typed Variable</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.TypedVariable#getDefaultValueExpression <em>Default Value
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.TypedVariable#getValueType <em>Value Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getTypedVariable()
 * @model
 * @generated
 */
public interface TypedVariable extends InteractiveVariableDescription, SubVariable {
    /**
     * Returns the value of the '<em><b>Default Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> An expression used to define the default variable value. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Default Value Expression</em>' attribute.
     * @see #setDefaultValueExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getTypedVariable_DefaultValueExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='Must return an instance of
     *        type valueType.'"
     * @generated
     */
    String getDefaultValueExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.TypedVariable#getDefaultValueExpression
     * <em>Default Value Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Value Expression</em>' attribute.
     * @see #getDefaultValueExpression()
     * @generated
     */
    void setDefaultValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Value Type</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Type</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The type of the variable value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value Type</em>' reference.
     * @see #setValueType(EDataType)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getTypedVariable_ValueType()
     * @model required="true"
     * @generated
     */
    EDataType getValueType();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.TypedVariable#getValueType <em>Value
     * Type</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Type</em>' reference.
     * @see #getValueType()
     * @generated
     */
    void setValueType(EDataType value);

} // TypedVariable
