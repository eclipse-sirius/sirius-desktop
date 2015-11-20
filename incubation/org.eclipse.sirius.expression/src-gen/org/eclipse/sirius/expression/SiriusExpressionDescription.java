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
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sirius Expression Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines an expression.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getVariableContainers <em>Variable Containers</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription()
 * @model
 * @generated
 */
public interface SiriusExpressionDescription extends EObject {
	/**
	 * Returns the value of the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the return type lower bound.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lower Bound</em>' attribute.
	 * @see #setLowerBound(int)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription_LowerBound()
	 * @model
	 * @generated
	 */
	int getLowerBound();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getLowerBound <em>Lower Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lower Bound</em>' attribute.
	 * @see #getLowerBound()
	 * @generated
	 */
	void setLowerBound(int value);

	/**
	 * Returns the value of the '<em><b>Upper Bound</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the return type upper bound.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Upper Bound</em>' attribute.
	 * @see #setUpperBound(int)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription_UpperBound()
	 * @model default="1"
	 * @generated
	 */
	int getUpperBound();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getUpperBound <em>Upper Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Upper Bound</em>' attribute.
	 * @see #getUpperBound()
	 * @generated
	 */
	void setUpperBound(int value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References an expression from another metamodel.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression</em>' reference.
	 * @see #setExpression(EStructuralFeature)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription_Expression()
	 * @model
	 * @generated
	 */
	EStructuralFeature getExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getExpression <em>Expression</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(EStructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.sirius.expression.SiriusParameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the variables associated to an expression description.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameters</em>' containment reference list.
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription_Parameters()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SiriusParameter> getParameters();

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the return type of an expression.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Return Type</em>' reference.
	 * @see #setReturnType(EClassifier)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription_ReturnType()
	 * @model
	 * @generated
	 */
	EClassifier getReturnType();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusExpressionDescription#getReturnType <em>Return Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' reference.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(EClassifier value);

	/**
	 * Returns the value of the '<em><b>Variable Containers</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References contextable elements to get the associated context variables.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Variable Containers</em>' reference list.
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionDescription_VariableContainers()
	 * @model
	 * @generated
	 */
	EList<EClass> getVariableContainers();

} // SiriusExpressionDescription
