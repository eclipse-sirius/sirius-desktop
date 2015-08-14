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
 * <em><b>Message End</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.MessageEnd#getMessage <em>
 * Message</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessageEnd()
 * @model
 * @generated
 */
public interface MessageEnd extends AbstractEnd {
    /**
     * Returns the value of the '<em><b>Message</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Message</em>' reference.
     * @see #setMessage(Message)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessageEnd_Message()
     * @model required="true"
     * @generated
     */
    Message getMessage();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.MessageEnd#getMessage
     * <em>Message</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Message</em>' reference.
     * @see #getMessage()
     * @generated
     */
    void setMessage(Message value);

} // MessageEnd
