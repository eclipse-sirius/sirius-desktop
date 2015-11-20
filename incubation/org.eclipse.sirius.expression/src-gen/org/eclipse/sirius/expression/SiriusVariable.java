/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.htm
 */
package org.eclipse.sirius.expression;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sirius Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Represents a variable.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.expression.SiriusVariable#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusVariable#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.eclipse.sirius.expression.SiriusVariable#getEType <em>EType</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusVariable()
 * @model
 * @generated
 */
public interface SiriusVariable extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the name of the varriable.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusVariable_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusVariable#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Defines the documentation of the variables.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusVariable_Documentation()
	 * @model
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusVariable#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

	/**
	 * Returns the value of the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EType</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EType</em>' reference.
	 * @see #setEType(EClassifier)
	 * @see org.eclipse.sirius.expression.ExpressionPackage#getSiriusVariable_EType()
	 * @model
	 * @generated
	 */
	EClassifier getEType();

	/**
	 * Sets the value of the '{@link org.eclipse.sirius.expression.SiriusVariable#getEType <em>EType</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EType</em>' reference.
	 * @see #getEType()
	 * @generated
	 */
	void setEType(EClassifier value);

} // SiriusVariable
