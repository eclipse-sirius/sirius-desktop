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
 * <em><b>State</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A state transition of a participant. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.State#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.State#getOwner <em>Owner
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.State#getStart <em>Start
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.State#getEnd <em>End</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getState()
 * @model
 * @generated
 */
public interface State extends EObject {
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
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getState_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.State#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

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
     * @see #setOwner(Participant)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getState_Owner()
     * @model required="true"
     * @generated
     */
    Participant getOwner();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.State#getOwner
     * <em>Owner</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owner</em>' reference.
     * @see #getOwner()
     * @generated
     */
    void setOwner(Participant value);

    /**
     * Returns the value of the '<em><b>Start</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Start</em>' reference.
     * @see #setStart(StateEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getState_Start()
     * @model required="true"
     * @generated
     */
    StateEnd getStart();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.State#getStart
     * <em>Start</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Start</em>' reference.
     * @see #getStart()
     * @generated
     */
    void setStart(StateEnd value);

    /**
     * Returns the value of the '<em><b>End</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>End</em>' reference.
     * @see #setEnd(StateEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getState_End()
     * @model required="true"
     * @generated
     */
    StateEnd getEnd();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.State#getEnd <em>End</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>End</em>' reference.
     * @see #getEnd()
     * @generated
     */
    void setEnd(StateEnd value);

} // State
