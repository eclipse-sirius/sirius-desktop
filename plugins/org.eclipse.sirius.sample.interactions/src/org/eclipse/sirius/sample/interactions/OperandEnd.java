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
 * <em><b>Operand End</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.OperandEnd#getOwner <em>
 * Owner</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getOperandEnd()
 * @model
 * @generated
 */
public interface OperandEnd extends AbstractEnd {
    /**
     * Returns the value of the '<em><b>Owner</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owner</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owner</em>' reference.
     * @see #setOwner(Operand)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getOperandEnd_Owner()
     * @model required="true"
     * @generated
     */
    Operand getOwner();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.OperandEnd#getOwner
     * <em>Owner</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owner</em>' reference.
     * @see #getOwner()
     * @generated
     */
    void setOwner(Operand value);

} // OperandEnd
