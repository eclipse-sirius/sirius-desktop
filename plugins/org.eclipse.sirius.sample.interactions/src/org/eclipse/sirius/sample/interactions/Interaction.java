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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Interaction</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Describes the interactions between several participants over time. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getParticipants <em>Participants</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getMessages <em>Messages</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getExecutions <em>Executions</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getStates <em>States</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getInteractionUses <em>Interaction Uses</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getCombinedFragments <em>Combined Fragments</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getEnds <em>Ends</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getConstraints <em>Constraints</em>}</li>
 * <li>{@link org.eclipse.sirius.sample.interactions.Interaction#getOwnedGates <em>Owned Gates</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction()
 * @model
 * @generated
 */
public interface Interaction extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.Interaction#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Participants</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Participant}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Participants</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_Participants()
     * @model containment="true" ordered="false"
     * @generated
     */
    EList<Participant> getParticipants();

    /**
     * Returns the value of the '<em><b>Messages</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Message}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Messages</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_Messages()
     * @model containment="true"
     * @generated
     */
    EList<Message> getMessages();

    /**
     * Returns the value of the '<em><b>Executions</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Execution}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Executions</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_Executions()
     * @model containment="true"
     * @generated
     */
    EList<Execution> getExecutions();

    /**
     * Returns the value of the '<em><b>States</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.State}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>States</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_States()
     * @model containment="true"
     * @generated
     */
    EList<State> getStates();

    /**
     * Returns the value of the '<em><b>Interaction Uses</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.sample.interactions.InteractionUse}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Interaction Uses</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_InteractionUses()
     * @model containment="true"
     * @generated
     */
    EList<InteractionUse> getInteractionUses();

    /**
     * Returns the value of the '<em><b>Combined Fragments</b></em>' containment reference list. The list contents are
     * of type {@link org.eclipse.sirius.sample.interactions.CombinedFragment}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Combined Fragments</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_CombinedFragments()
     * @model containment="true"
     * @generated
     */
    EList<CombinedFragment> getCombinedFragments();

    /**
     * Returns the value of the '<em><b>Ends</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.AbstractEnd}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Ends</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_Ends()
     * @model containment="true"
     * @generated
     */
    EList<AbstractEnd> getEnds();

    /**
     * Returns the value of the '<em><b>Constraints</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Constraint}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Constraints</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_Constraints()
     * @model containment="true"
     * @generated
     */
    EList<Constraint> getConstraints();

    /**
     * Returns the value of the '<em><b>Owned Gates</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.sample.interactions.Gate}. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Gates</em>' containment reference list.
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteraction_OwnedGates()
     * @model containment="true"
     * @generated
     */
    EList<Gate> getOwnedGates();

} // Interaction
