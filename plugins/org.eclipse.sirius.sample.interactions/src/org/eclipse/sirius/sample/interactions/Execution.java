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
 * <em><b>Execution</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents an interval of time in which a
 * particapant is active executing some code. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.Execution#getName <em>Name
 * </em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Execution#getOwner <em>
 * Owner</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Execution#getStart <em>
 * Start</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Execution#getEnd <em>End
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecution()
 * @model
 * @generated
 */
public interface Execution extends EObject {
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
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecution_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getName
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
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecution_Owner()
     * @model required="true"
     * @generated
     */
    Participant getOwner();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getOwner
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
     * @see #setStart(ExecutionEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecution_Start()
     * @model required="true"
     * @generated
     */
    ExecutionEnd getStart();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getStart
     * <em>Start</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Start</em>' reference.
     * @see #getStart()
     * @generated
     */
    void setStart(ExecutionEnd value);

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
     * @see #setEnd(ExecutionEnd)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getExecution_End()
     * @model required="true"
     * @generated
     */
    ExecutionEnd getEnd();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.sample.interactions.Execution#getEnd
     * <em>End</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>End</em>' reference.
     * @see #getEnd()
     * @generated
     */
    void setEnd(ExecutionEnd value);

} // Execution
