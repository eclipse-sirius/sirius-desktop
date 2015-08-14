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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage
 * @generated
 */
public interface InteractionsFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    InteractionsFactory eINSTANCE = org.eclipse.sirius.sample.interactions.impl.InteractionsFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Model</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Model</em>'.
     * @generated
     */
    Model createModel();

    /**
     * Returns a new object of class '<em>Interaction</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Interaction</em>'.
     * @generated
     */
    Interaction createInteraction();

    /**
     * Returns a new object of class '<em>Participant</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Participant</em>'.
     * @generated
     */
    Participant createParticipant();

    /**
     * Returns a new object of class '<em>Call Message</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Call Message</em>'.
     * @generated
     */
    CallMessage createCallMessage();

    /**
     * Returns a new object of class '<em>Feature Access Message</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Feature Access Message</em>'.
     * @generated
     */
    FeatureAccessMessage createFeatureAccessMessage();

    /**
     * Returns a new object of class '<em>Create Participant Message</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Participant Message</em>'.
     * @generated
     */
    CreateParticipantMessage createCreateParticipantMessage();

    /**
     * Returns a new object of class '<em>Destroy Participant Message</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Destroy Participant Message</em>'.
     * @generated
     */
    DestroyParticipantMessage createDestroyParticipantMessage();

    /**
     * Returns a new object of class '<em>Return Message</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Return Message</em>'.
     * @generated
     */
    ReturnMessage createReturnMessage();

    /**
     * Returns a new object of class '<em>Execution</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Execution</em>'.
     * @generated
     */
    Execution createExecution();

    /**
     * Returns a new object of class '<em>State</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>State</em>'.
     * @generated
     */
    State createState();

    /**
     * Returns a new object of class '<em>Interaction Use</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Interaction Use</em>'.
     * @generated
     */
    InteractionUse createInteractionUse();

    /**
     * Returns a new object of class '<em>Combined Fragment</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Combined Fragment</em>'.
     * @generated
     */
    CombinedFragment createCombinedFragment();

    /**
     * Returns a new object of class '<em>Operand</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Operand</em>'.
     * @generated
     */
    Operand createOperand();

    /**
     * Returns a new object of class '<em>Message End</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Message End</em>'.
     * @generated
     */
    MessageEnd createMessageEnd();

    /**
     * Returns a new object of class '<em>Execution End</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Execution End</em>'.
     * @generated
     */
    ExecutionEnd createExecutionEnd();

    /**
     * Returns a new object of class '<em>State End</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>State End</em>'.
     * @generated
     */
    StateEnd createStateEnd();

    /**
     * Returns a new object of class '<em>Interaction Use End</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Interaction Use End</em>'.
     * @generated
     */
    InteractionUseEnd createInteractionUseEnd();

    /**
     * Returns a new object of class '<em>Combined Fragment End</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Combined Fragment End</em>'.
     * @generated
     */
    CombinedFragmentEnd createCombinedFragmentEnd();

    /**
     * Returns a new object of class '<em>Operand End</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Operand End</em>'.
     * @generated
     */
    OperandEnd createOperandEnd();

    /**
     * Returns a new object of class '<em>Mix End</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Mix End</em>'.
     * @generated
     */
    MixEnd createMixEnd();

    /**
     * Returns a new object of class '<em>Constraint</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Constraint</em>'.
     * @generated
     */
    Constraint createConstraint();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    InteractionsPackage getInteractionsPackage();

} // InteractionsFactory
