/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sirius Expression Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Holds expression descriptions and variables.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionClass#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionClass#getExpressionDescriptions <em>Expression Descriptions</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionClass#getEClass <em>EClass</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionClass()
 * @model
 * @generated
 */
public interface SiriusExpressionClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.sirius.expression.SiriusVariable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the variables. Variables are global as they can be referenced by any expression description.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionClass_Variables()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SiriusVariable> getVariables();

	/**
	 * Returns the value of the '<em><b>Expression Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.sirius.expression.SiriusExpressionDescription}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the expressions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression Descriptions</em>' containment reference list.
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionClass_ExpressionDescriptions()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SiriusExpressionDescription> getExpressionDescriptions();

	/**
	 * Returns the value of the '<em><b>EClass</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EClass</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EClass</em>' reference.
	 * @see #setEClass(EClass)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionClass_EClass()
	 * @model
	 * @generated
	 */
	EClass getEClass();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusExpressionClass#getEClass <em>EClass</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EClass</em>' reference.
	 * @see #getEClass()
	 * @generated
	 */
	void setEClass(EClass value);

} // SiriusExpressionClass
