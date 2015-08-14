/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Return Message</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.sample.interactions.ReturnMessage#getInvocationMessage
 * <em>Invocation Message</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getReturnMessage()
 * @model
 * @generated
 */
public interface ReturnMessage extends Message {
    /**
     * Returns the value of the '<em><b>Invocation Message</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Invocation Message</em>' reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Invocation Message</em>' reference.
     * @see #setInvocationMessage(Message)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getReturnMessage_InvocationMessage()
     * @model required="true"
     * @generated
     */
    Message getInvocationMessage();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.ReturnMessage#getInvocationMessage
     * <em>Invocation Message</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Invocation Message</em>' reference.
     * @see #getInvocationMessage()
     * @generated
     */
    void setInvocationMessage(Message value);

} // ReturnMessage
