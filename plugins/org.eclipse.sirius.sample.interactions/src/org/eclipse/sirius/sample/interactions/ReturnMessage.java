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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Return Message</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.ReturnMessage#getInvocationMessage <em>Invocation
 * Message</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getReturnMessage()
 * @model
 * @generated
 */
public interface ReturnMessage extends Message {
    /**
     * Returns the value of the '<em><b>Invocation Message</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Invocation Message</em>' reference.
     * @see #setInvocationMessage(Message)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getReturnMessage_InvocationMessage()
     * @model required="true"
     * @generated
     */
    Message getInvocationMessage();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.ReturnMessage#getInvocationMessage
     * <em>Invocation Message</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Invocation Message</em>' reference.
     * @see #getInvocationMessage()
     * @generated
     */
    void setInvocationMessage(Message value);

} // ReturnMessage
