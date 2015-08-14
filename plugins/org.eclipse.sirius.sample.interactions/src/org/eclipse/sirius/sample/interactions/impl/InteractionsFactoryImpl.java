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
package org.eclipse.sirius.sample.interactions.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.sample.interactions.CallMessage;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.CombinedFragmentEnd;
import org.eclipse.sirius.sample.interactions.Constraint;
import org.eclipse.sirius.sample.interactions.CreateParticipantMessage;
import org.eclipse.sirius.sample.interactions.DestroyParticipantMessage;
import org.eclipse.sirius.sample.interactions.Execution;
import org.eclipse.sirius.sample.interactions.ExecutionEnd;
import org.eclipse.sirius.sample.interactions.FeatureAccessMessage;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.InteractionUseEnd;
import org.eclipse.sirius.sample.interactions.InteractionsFactory;
import org.eclipse.sirius.sample.interactions.InteractionsPackage;
import org.eclipse.sirius.sample.interactions.MessageEnd;
import org.eclipse.sirius.sample.interactions.MixEnd;
import org.eclipse.sirius.sample.interactions.Model;
import org.eclipse.sirius.sample.interactions.Operand;
import org.eclipse.sirius.sample.interactions.OperandEnd;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.sample.interactions.ReturnMessage;
import org.eclipse.sirius.sample.interactions.State;
import org.eclipse.sirius.sample.interactions.StateEnd;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class InteractionsFactoryImpl extends EFactoryImpl implements InteractionsFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static InteractionsFactory init() {
        try {
            InteractionsFactory theInteractionsFactory = (InteractionsFactory) EPackage.Registry.INSTANCE.getEFactory(InteractionsPackage.eNS_URI);
            if (theInteractionsFactory != null) {
                return theInteractionsFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new InteractionsFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public InteractionsFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case InteractionsPackage.MODEL:
            return createModel();
        case InteractionsPackage.INTERACTION:
            return createInteraction();
        case InteractionsPackage.PARTICIPANT:
            return createParticipant();
        case InteractionsPackage.CALL_MESSAGE:
            return createCallMessage();
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE:
            return createFeatureAccessMessage();
        case InteractionsPackage.CREATE_PARTICIPANT_MESSAGE:
            return createCreateParticipantMessage();
        case InteractionsPackage.DESTROY_PARTICIPANT_MESSAGE:
            return createDestroyParticipantMessage();
        case InteractionsPackage.RETURN_MESSAGE:
            return createReturnMessage();
        case InteractionsPackage.EXECUTION:
            return createExecution();
        case InteractionsPackage.STATE:
            return createState();
        case InteractionsPackage.INTERACTION_USE:
            return createInteractionUse();
        case InteractionsPackage.COMBINED_FRAGMENT:
            return createCombinedFragment();
        case InteractionsPackage.OPERAND:
            return createOperand();
        case InteractionsPackage.MESSAGE_END:
            return createMessageEnd();
        case InteractionsPackage.EXECUTION_END:
            return createExecutionEnd();
        case InteractionsPackage.STATE_END:
            return createStateEnd();
        case InteractionsPackage.INTERACTION_USE_END:
            return createInteractionUseEnd();
        case InteractionsPackage.COMBINED_FRAGMENT_END:
            return createCombinedFragmentEnd();
        case InteractionsPackage.OPERAND_END:
            return createOperandEnd();
        case InteractionsPackage.MIX_END:
            return createMixEnd();
        case InteractionsPackage.CONSTRAINT:
            return createConstraint();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Model createModel() {
        ModelImpl model = new ModelImpl();
        return model;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Interaction createInteraction() {
        InteractionImpl interaction = new InteractionImpl();
        return interaction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Participant createParticipant() {
        ParticipantImpl participant = new ParticipantImpl();
        return participant;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CallMessage createCallMessage() {
        CallMessageImpl callMessage = new CallMessageImpl();
        return callMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FeatureAccessMessage createFeatureAccessMessage() {
        FeatureAccessMessageImpl featureAccessMessage = new FeatureAccessMessageImpl();
        return featureAccessMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CreateParticipantMessage createCreateParticipantMessage() {
        CreateParticipantMessageImpl createParticipantMessage = new CreateParticipantMessageImpl();
        return createParticipantMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DestroyParticipantMessage createDestroyParticipantMessage() {
        DestroyParticipantMessageImpl destroyParticipantMessage = new DestroyParticipantMessageImpl();
        return destroyParticipantMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ReturnMessage createReturnMessage() {
        ReturnMessageImpl returnMessage = new ReturnMessageImpl();
        return returnMessage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Execution createExecution() {
        ExecutionImpl execution = new ExecutionImpl();
        return execution;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public State createState() {
        StateImpl state = new StateImpl();
        return state;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionUse createInteractionUse() {
        InteractionUseImpl interactionUse = new InteractionUseImpl();
        return interactionUse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CombinedFragment createCombinedFragment() {
        CombinedFragmentImpl combinedFragment = new CombinedFragmentImpl();
        return combinedFragment;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Operand createOperand() {
        OperandImpl operand = new OperandImpl();
        return operand;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MessageEnd createMessageEnd() {
        MessageEndImpl messageEnd = new MessageEndImpl();
        return messageEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionEnd createExecutionEnd() {
        ExecutionEndImpl executionEnd = new ExecutionEndImpl();
        return executionEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StateEnd createStateEnd() {
        StateEndImpl stateEnd = new StateEndImpl();
        return stateEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionUseEnd createInteractionUseEnd() {
        InteractionUseEndImpl interactionUseEnd = new InteractionUseEndImpl();
        return interactionUseEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CombinedFragmentEnd createCombinedFragmentEnd() {
        CombinedFragmentEndImpl combinedFragmentEnd = new CombinedFragmentEndImpl();
        return combinedFragmentEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperandEnd createOperandEnd() {
        OperandEndImpl operandEnd = new OperandEndImpl();
        return operandEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MixEnd createMixEnd() {
        MixEndImpl mixEnd = new MixEndImpl();
        return mixEnd;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Constraint createConstraint() {
        ConstraintImpl constraint = new ConstraintImpl();
        return constraint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionsPackage getInteractionsPackage() {
        return (InteractionsPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static InteractionsPackage getPackage() {
        return InteractionsPackage.eINSTANCE;
    }

} // InteractionsFactoryImpl
