/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User Defined Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.UserDefinedVariable#getValueExpression <em>Value Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.expression.ExpressionPackage#getUserDefinedVariable()
 * @model
 * @generated
 */
public interface UserDefinedVariable extends SiriusVariable {
	/**
	 * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Expression</em>' attribute.
	 * @see #setValueExpression(String)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getUserDefinedVariable_ValueExpression()
	 * @model dataType="org.eclipse.sirius.expression.Expression"
	 * @generated
	 */
	String getValueExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.UserDefinedVariable#getValueExpression <em>Value Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Expression</em>' attribute.
	 * @see #getValueExpression()
	 * @generated
	 */
	void setValueExpression(String value);

} // UserDefinedVariable
