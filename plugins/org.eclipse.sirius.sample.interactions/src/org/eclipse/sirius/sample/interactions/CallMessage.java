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

import org.eclipse.emf.ecore.EOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Call Message</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.CallMessage#getOperation
 * <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCallMessage()
 * @model
 * @generated
 */
public interface CallMessage extends Message {
    /**
     * Returns the value of the '<em><b>Operation</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Operation</em>' reference.
     * @see #setOperation(EOperation)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getCallMessage_Operation()
     * @model required="true"
     * @generated
     */
    EOperation getOperation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.CallMessage#getOperation
     * <em>Operation</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Operation</em>' reference.
     * @see #getOperation()
     * @generated
     */
    void setOperation(EOperation value);

} // CallMessage
