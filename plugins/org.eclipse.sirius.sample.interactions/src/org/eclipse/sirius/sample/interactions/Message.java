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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Message</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A message sent from a participant to another (or
 * itself). <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.Message#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Message#getSendingEnd <em>
 * Sending End</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Message#getReceivingEnd
 * <em>Receiving End</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessage()
 * @model abstract="true"
 * @generated
 */
public interface Message extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessage_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Message#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Sending End</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sending End</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sending End</em>' reference.
     * @see #setSendingEnd(MessageEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessage_SendingEnd()
     * @model required="true"
     * @generated
     */
    MessageEnd getSendingEnd();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Message#getSendingEnd
     * <em>Sending End</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Sending End</em>' reference.
     * @see #getSendingEnd()
     * @generated
     */
    void setSendingEnd(MessageEnd value);

    /**
     * Returns the value of the '<em><b>Receiving End</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Receiving End</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Receiving End</em>' reference.
     * @see #setReceivingEnd(MessageEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getMessage_ReceivingEnd()
     * @model required="true"
     * @generated
     */
    MessageEnd getReceivingEnd();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Message#getReceivingEnd
     * <em>Receiving End</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Receiving End</em>' reference.
     * @see #getReceivingEnd()
     * @generated
     */
    void setReceivingEnd(MessageEnd value);

} // Message
