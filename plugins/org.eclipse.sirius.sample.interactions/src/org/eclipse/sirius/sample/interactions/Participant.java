/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.sample.interactions;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Participant</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named and typed instance which particpates in the interaction.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.sirius.sample.interactions.Participant#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getParticipant()
 * @model
 * @generated
 */
public interface Participant extends AbstractEndContext {
    /**
     * Returns the value of the '<em><b>Type</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Type</em>' reference.
     * @see #setType(EClass)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getParticipant_Type()
     * @model required="true"
     * @generated
     */
    EClass getType();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.Participant#getType <em>Type</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' reference.
     * @see #getType()
     * @generated
     */
    void setType(EClass value);

} // Participant
