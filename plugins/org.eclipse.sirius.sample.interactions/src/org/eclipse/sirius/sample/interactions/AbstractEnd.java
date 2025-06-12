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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract End</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> All the atomic events (sending of a message, start and finish of an execution...) are
 * represented by AbstractEnds and stored in chronological order in the interaction. The ordering must be total. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.AbstractEnd#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.AbstractEnd#getContext <em>Context</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getAbstractEnd()
 * @model abstract="true"
 * @generated
 */
public interface AbstractEnd extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getAbstractEnd_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.AbstractEnd#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Context</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Context</em>' reference.
     * @see #setContext(Participant)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getAbstractEnd_Context()
     * @model
     * @generated
     */
    Participant getContext();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.AbstractEnd#getContext <em>Context</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Context</em>' reference.
     * @see #getContext()
     * @generated
     */
    void setContext(Participant value);

} // AbstractEnd
