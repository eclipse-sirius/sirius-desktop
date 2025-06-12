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

import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Call Message</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.CallMessage#getOperation <em>Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCallMessage()
 * @model
 * @generated
 */
public interface CallMessage extends Message {
    /**
     * Returns the value of the '<em><b>Operation</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Operation</em>' reference.
     * @see #setOperation(EOperation)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCallMessage_Operation()
     * @model required="true"
     * @generated
     */
    EOperation getOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.CallMessage#getOperation
     * <em>Operation</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Operation</em>' reference.
     * @see #getOperation()
     * @generated
     */
    void setOperation(EOperation value);

} // CallMessage
