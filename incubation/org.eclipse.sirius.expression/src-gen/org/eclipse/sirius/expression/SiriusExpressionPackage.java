/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sirius Expression Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An ExpressionPackage is the root concept of the expression metamodel. This metamodel is used to define the link between expressions and the variables that could be used by the expressions.An ExpressionPackage defines groups of ExpressionClasses.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionPackage#getExpressionClasses <em>Expression Classes</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusExpressionPackage#getEPackage <em>EPackage</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionPackage()
 * @model
 * @generated
 */
public interface SiriusExpressionPackage extends EObject {
	/**
	 * Returns the value of the '<em><b>Expression Classes</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.sirius.expression.SiriusExpressionClass}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References expression classes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expression Classes</em>' containment reference list.
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionPackage_ExpressionClasses()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<SiriusExpressionClass> getExpressionClasses();

	/**
	 * Returns the value of the '<em><b>EPackage</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * References the ePackage of the EClasses defining expressions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>EPackage</em>' reference.
	 * @see #setEPackage(EPackage)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusExpressionPackage_EPackage()
	 * @model
	 * @generated
	 */
	EPackage getEPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusExpressionPackage#getEPackage <em>EPackage</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EPackage</em>' reference.
	 * @see #getEPackage()
	 * @generated
	 */
	void setEPackage(EPackage value);

} // SiriusExpressionPackage
