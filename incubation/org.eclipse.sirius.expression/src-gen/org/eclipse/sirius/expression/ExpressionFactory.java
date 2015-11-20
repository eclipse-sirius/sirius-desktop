/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.sirius.expression.ExpressionPackage
 * @generated
 */
public interface ExpressionFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExpressionFactory eINSTANCE = org.eclipse.sirius.expression.impl.ExpressionFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Sirius Expression Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sirius Expression Package</em>'.
	 * @generated
	 */
	SiriusExpressionPackage createSiriusExpressionPackage();

	/**
	 * Returns a new object of class '<em>Sirius Expression Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sirius Expression Description</em>'.
	 * @generated
	 */
	SiriusExpressionDescription createSiriusExpressionDescription();

	/**
	 * Returns a new object of class '<em>Sirius Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sirius Variable</em>'.
	 * @generated
	 */
	SiriusVariable createSiriusVariable();

	/**
	 * Returns a new object of class '<em>Sirius Expression Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sirius Expression Class</em>'.
	 * @generated
	 */
	SiriusExpressionClass createSiriusExpressionClass();

	/**
	 * Returns a new object of class '<em>Sirius Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sirius Parameter</em>'.
	 * @generated
	 */
	SiriusParameter createSiriusParameter();

	/**
	 * Returns a new object of class '<em>User Defined Variable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>User Defined Variable</em>'.
	 * @generated
	 */
	UserDefinedVariable createUserDefinedVariable();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExpressionPackage getExpressionPackage();

} //ExpressionFactory
