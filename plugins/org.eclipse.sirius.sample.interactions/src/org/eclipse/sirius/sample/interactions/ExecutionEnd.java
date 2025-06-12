/**
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.interactions;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Execution End</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.ExecutionEnd#getExecution <em>Execution</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecutionEnd()
 * @model
 * @generated
 */
public interface ExecutionEnd extends AbstractEnd {
    /**
     * Returns the value of the '<em><b>Execution</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Execution</em>' reference.
     * @see #setExecution(Execution)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecutionEnd_Execution()
     * @model required="true"
     * @generated
     */
    Execution getExecution();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.ExecutionEnd#getExecution
     * <em>Execution</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Execution</em>' reference.
     * @see #getExecution()
     * @generated
     */
    void setExecution(Execution value);

} // ExecutionEnd
