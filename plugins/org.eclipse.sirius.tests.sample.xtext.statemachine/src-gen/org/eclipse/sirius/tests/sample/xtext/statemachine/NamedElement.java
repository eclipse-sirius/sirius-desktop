/**
 * ******************************************************************************
 * * Copyright (c) 2018 Obeo.
 * * This program and the accompanying materials
 * * are made available under the terms of the Eclipse Public License 2.0
 * * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * *
 * * Contributors:
 * *    Obeo - initial API and implementation
 * ******************************************************************************
 */
package org.eclipse.sirius.tests.sample.xtext.statemachine;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Named Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.sample.xtext.statemachine.NamedElement#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.sirius.tests.sample.xtext.statemachine.NamedElement#getDisplayname <em>Displayname</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachinePackage#getNamedElement()
 * @model
 * @generated
 */
public interface NamedElement extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachinePackage#getNamedElement_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.sirius.tests.sample.xtext.statemachine.NamedElement#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Displayname</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Displayname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Displayname</em>' attribute.
   * @see #setDisplayname(String)
   * @see org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachinePackage#getNamedElement_Displayname()
   * @model
   * @generated
   */
  String getDisplayname();

  /**
   * Sets the value of the '{@link org.eclipse.sirius.tests.sample.xtext.statemachine.NamedElement#getDisplayname <em>Displayname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Displayname</em>' attribute.
   * @see #getDisplayname()
   * @generated
   */
  void setDisplayname(String value);

} // NamedElement
