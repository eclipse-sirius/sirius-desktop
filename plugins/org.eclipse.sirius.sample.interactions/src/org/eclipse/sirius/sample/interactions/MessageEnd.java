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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Message End</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.MessageEnd#getMessage <em>Message</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.MessageEnd#getGate <em>Gate</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessageEnd()
 * @model
 * @generated
 */
public interface MessageEnd extends AbstractEnd {
    /**
     * Returns the value of the '<em><b>Message</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Message</em>' reference.
     * @see #setMessage(Message)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessageEnd_Message()
     * @model required="true"
     * @generated
     */
    Message getMessage();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.MessageEnd#getMessage <em>Message</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Message</em>' reference.
     * @see #getMessage()
     * @generated
     */
    void setMessage(Message value);

    /**
     * Returns the value of the '<em><b>Gate</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Gate</em>' reference.
     * @see #setGate(Gate)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessageEnd_Gate()
     * @model
     * @generated
     */
    Gate getGate();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.MessageEnd#getGate <em>Gate</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Gate</em>' reference.
     * @see #getGate()
     * @generated
     */
    void setGate(Gate value);

} // MessageEnd
