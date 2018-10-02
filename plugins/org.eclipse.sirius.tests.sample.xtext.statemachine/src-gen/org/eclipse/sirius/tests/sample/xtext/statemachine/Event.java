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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.tests.sample.xtext.statemachine.Event#getCode <em>Code</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachinePackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends NamedElement
{
  /**
   * Returns the value of the '<em><b>Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Code</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Code</em>' attribute.
   * @see #setCode(String)
   * @see org.eclipse.sirius.tests.sample.xtext.statemachine.StatemachinePackage#getEvent_Code()
   * @model
   * @generated
   */
  String getCode();

  /**
   * Sets the value of the '{@link org.eclipse.sirius.tests.sample.xtext.statemachine.Event#getCode <em>Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Code</em>' attribute.
   * @see #getCode()
   * @generated
   */
  void setCode(String value);

} // Event
