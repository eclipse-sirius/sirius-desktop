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
package org.eclipse.sirius.sample.interactions.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.sample.interactions.AbstractEnd;
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
import org.eclipse.sirius.sample.interactions.Message;
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
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class InteractionsPackageImpl extends EPackageImpl implements InteractionsPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass modelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass interactionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass participantEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass messageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass callMessageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass featureAccessMessageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass createParticipantMessageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass destroyParticipantMessageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass returnMessageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass executionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass stateEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass interactionUseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass combinedFragmentEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass operandEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass messageEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass executionEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass stateEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass interactionUseEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass combinedFragmentEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass operandEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass mixEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass constraintEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private InteractionsPackageImpl() {
        super(eNS_URI, InteractionsFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link InteractionsPackage#eINSTANCE} when that field is accessed. Clients
     * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static InteractionsPackage init() {
        if (isInited)
            return (InteractionsPackage) EPackage.Registry.INSTANCE.getEPackage(InteractionsPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredInteractionsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        InteractionsPackageImpl theInteractionsPackage = registeredInteractionsPackage instanceof InteractionsPackageImpl ? (InteractionsPackageImpl) registeredInteractionsPackage
                : new InteractionsPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theInteractionsPackage.createPackageContents();

        // Initialize created meta-data
        theInteractionsPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theInteractionsPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(InteractionsPackage.eNS_URI, theInteractionsPackage);
        return theInteractionsPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getModel() {
        return modelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getModel_Name() {
        return (EAttribute) modelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getModel_OwnedInteractions() {
        return (EReference) modelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInteraction() {
        return interactionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInteraction_Name() {
        return (EAttribute) interactionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_Participants() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_Messages() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_Executions() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_States() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_InteractionUses() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_CombinedFragments() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_Ends() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteraction_Constraints() {
        return (EReference) interactionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getParticipant() {
        return participantEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getParticipant_Name() {
        return (EAttribute) participantEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getParticipant_Type() {
        return (EReference) participantEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMessage() {
        return messageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMessage_Name() {
        return (EAttribute) messageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMessage_SendingEnd() {
        return (EReference) messageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMessage_ReceivingEnd() {
        return (EReference) messageEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCallMessage() {
        return callMessageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCallMessage_Operation() {
        return (EReference) callMessageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFeatureAccessMessage() {
        return featureAccessMessageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFeatureAccessMessage_IsWrite() {
        return (EAttribute) featureAccessMessageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFeatureAccessMessage_Feature() {
        return (EReference) featureAccessMessageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCreateParticipantMessage() {
        return createParticipantMessageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDestroyParticipantMessage() {
        return destroyParticipantMessageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getReturnMessage() {
        return returnMessageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getReturnMessage_InvocationMessage() {
        return (EReference) returnMessageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExecution() {
        return executionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getExecution_Name() {
        return (EAttribute) executionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getExecution_Owner() {
        return (EReference) executionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getExecution_Start() {
        return (EReference) executionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getExecution_End() {
        return (EReference) executionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getState() {
        return stateEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getState_Name() {
        return (EAttribute) stateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getState_Owner() {
        return (EReference) stateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getState_Start() {
        return (EReference) stateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getState_End() {
        return (EReference) stateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInteractionUse() {
        return interactionUseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getInteractionUse_Type() {
        return (EAttribute) interactionUseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteractionUse_Interaction() {
        return (EReference) interactionUseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteractionUse_CoveredParticipants() {
        return (EReference) interactionUseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteractionUse_Start() {
        return (EReference) interactionUseEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteractionUse_Finish() {
        return (EReference) interactionUseEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCombinedFragment() {
        return combinedFragmentEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCombinedFragment_Operator() {
        return (EAttribute) combinedFragmentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCombinedFragment_CoveredParticipants() {
        return (EReference) combinedFragmentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCombinedFragment_Start() {
        return (EReference) combinedFragmentEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCombinedFragment_Finish() {
        return (EReference) combinedFragmentEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCombinedFragment_OwnedOperands() {
        return (EReference) combinedFragmentEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getOperand() {
        return operandEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getOperand_Name() {
        return (EAttribute) operandEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getOperand_Start() {
        return (EReference) operandEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractEnd() {
        return abstractEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractEnd_Name() {
        return (EAttribute) abstractEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractEnd_Context() {
        return (EReference) abstractEndEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMessageEnd() {
        return messageEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMessageEnd_Message() {
        return (EReference) messageEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExecutionEnd() {
        return executionEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getExecutionEnd_Execution() {
        return (EReference) executionEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getStateEnd() {
        return stateEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getStateEnd_State() {
        return (EReference) stateEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getInteractionUseEnd() {
        return interactionUseEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getInteractionUseEnd_Owner() {
        return (EReference) interactionUseEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCombinedFragmentEnd() {
        return combinedFragmentEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getCombinedFragmentEnd_Owner() {
        return (EReference) combinedFragmentEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getOperandEnd() {
        return operandEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getOperandEnd_Owner() {
        return (EReference) operandEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMixEnd() {
        return mixEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConstraint() {
        return constraintEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getConstraint_Expression() {
        return (EAttribute) constraintEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getConstraint_ConstrainedEnds() {
        return (EReference) constraintEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public InteractionsFactory getInteractionsFactory() {
        return (InteractionsFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        modelEClass = createEClass(MODEL);
        createEAttribute(modelEClass, MODEL__NAME);
        createEReference(modelEClass, MODEL__OWNED_INTERACTIONS);

        interactionEClass = createEClass(INTERACTION);
        createEAttribute(interactionEClass, INTERACTION__NAME);
        createEReference(interactionEClass, INTERACTION__PARTICIPANTS);
        createEReference(interactionEClass, INTERACTION__MESSAGES);
        createEReference(interactionEClass, INTERACTION__EXECUTIONS);
        createEReference(interactionEClass, INTERACTION__STATES);
        createEReference(interactionEClass, INTERACTION__INTERACTION_USES);
        createEReference(interactionEClass, INTERACTION__COMBINED_FRAGMENTS);
        createEReference(interactionEClass, INTERACTION__ENDS);
        createEReference(interactionEClass, INTERACTION__CONSTRAINTS);

        participantEClass = createEClass(PARTICIPANT);
        createEAttribute(participantEClass, PARTICIPANT__NAME);
        createEReference(participantEClass, PARTICIPANT__TYPE);

        messageEClass = createEClass(MESSAGE);
        createEAttribute(messageEClass, MESSAGE__NAME);
        createEReference(messageEClass, MESSAGE__SENDING_END);
        createEReference(messageEClass, MESSAGE__RECEIVING_END);

        callMessageEClass = createEClass(CALL_MESSAGE);
        createEReference(callMessageEClass, CALL_MESSAGE__OPERATION);

        featureAccessMessageEClass = createEClass(FEATURE_ACCESS_MESSAGE);
        createEAttribute(featureAccessMessageEClass, FEATURE_ACCESS_MESSAGE__IS_WRITE);
        createEReference(featureAccessMessageEClass, FEATURE_ACCESS_MESSAGE__FEATURE);

        createParticipantMessageEClass = createEClass(CREATE_PARTICIPANT_MESSAGE);

        destroyParticipantMessageEClass = createEClass(DESTROY_PARTICIPANT_MESSAGE);

        returnMessageEClass = createEClass(RETURN_MESSAGE);
        createEReference(returnMessageEClass, RETURN_MESSAGE__INVOCATION_MESSAGE);

        executionEClass = createEClass(EXECUTION);
        createEAttribute(executionEClass, EXECUTION__NAME);
        createEReference(executionEClass, EXECUTION__OWNER);
        createEReference(executionEClass, EXECUTION__START);
        createEReference(executionEClass, EXECUTION__END);

        stateEClass = createEClass(STATE);
        createEAttribute(stateEClass, STATE__NAME);
        createEReference(stateEClass, STATE__OWNER);
        createEReference(stateEClass, STATE__START);
        createEReference(stateEClass, STATE__END);

        interactionUseEClass = createEClass(INTERACTION_USE);
        createEAttribute(interactionUseEClass, INTERACTION_USE__TYPE);
        createEReference(interactionUseEClass, INTERACTION_USE__INTERACTION);
        createEReference(interactionUseEClass, INTERACTION_USE__COVERED_PARTICIPANTS);
        createEReference(interactionUseEClass, INTERACTION_USE__START);
        createEReference(interactionUseEClass, INTERACTION_USE__FINISH);

        combinedFragmentEClass = createEClass(COMBINED_FRAGMENT);
        createEAttribute(combinedFragmentEClass, COMBINED_FRAGMENT__OPERATOR);
        createEReference(combinedFragmentEClass, COMBINED_FRAGMENT__COVERED_PARTICIPANTS);
        createEReference(combinedFragmentEClass, COMBINED_FRAGMENT__START);
        createEReference(combinedFragmentEClass, COMBINED_FRAGMENT__FINISH);
        createEReference(combinedFragmentEClass, COMBINED_FRAGMENT__OWNED_OPERANDS);

        operandEClass = createEClass(OPERAND);
        createEAttribute(operandEClass, OPERAND__NAME);
        createEReference(operandEClass, OPERAND__START);

        abstractEndEClass = createEClass(ABSTRACT_END);
        createEAttribute(abstractEndEClass, ABSTRACT_END__NAME);
        createEReference(abstractEndEClass, ABSTRACT_END__CONTEXT);

        messageEndEClass = createEClass(MESSAGE_END);
        createEReference(messageEndEClass, MESSAGE_END__MESSAGE);

        executionEndEClass = createEClass(EXECUTION_END);
        createEReference(executionEndEClass, EXECUTION_END__EXECUTION);

        stateEndEClass = createEClass(STATE_END);
        createEReference(stateEndEClass, STATE_END__STATE);

        interactionUseEndEClass = createEClass(INTERACTION_USE_END);
        createEReference(interactionUseEndEClass, INTERACTION_USE_END__OWNER);

        combinedFragmentEndEClass = createEClass(COMBINED_FRAGMENT_END);
        createEReference(combinedFragmentEndEClass, COMBINED_FRAGMENT_END__OWNER);

        operandEndEClass = createEClass(OPERAND_END);
        createEReference(operandEndEClass, OPERAND_END__OWNER);

        mixEndEClass = createEClass(MIX_END);

        constraintEClass = createEClass(CONSTRAINT);
        createEAttribute(constraintEClass, CONSTRAINT__EXPRESSION);
        createEReference(constraintEClass, CONSTRAINT__CONSTRAINED_ENDS);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        callMessageEClass.getESuperTypes().add(this.getMessage());
        featureAccessMessageEClass.getESuperTypes().add(this.getMessage());
        createParticipantMessageEClass.getESuperTypes().add(this.getMessage());
        destroyParticipantMessageEClass.getESuperTypes().add(this.getMessage());
        returnMessageEClass.getESuperTypes().add(this.getMessage());
        messageEndEClass.getESuperTypes().add(this.getAbstractEnd());
        executionEndEClass.getESuperTypes().add(this.getAbstractEnd());
        stateEndEClass.getESuperTypes().add(this.getAbstractEnd());
        interactionUseEndEClass.getESuperTypes().add(this.getAbstractEnd());
        combinedFragmentEndEClass.getESuperTypes().add(this.getAbstractEnd());
        operandEndEClass.getESuperTypes().add(this.getAbstractEnd());
        mixEndEClass.getESuperTypes().add(this.getExecutionEnd());
        mixEndEClass.getESuperTypes().add(this.getMessageEnd());

        // Initialize classes and features; add operations and parameters
        initEClass(modelEClass, Model.class, "Model", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getModel_Name(), ecorePackage.getEString(), "name", null, 1, 1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, //$NON-NLS-1$
                IS_ORDERED);
        initEReference(getModel_OwnedInteractions(), this.getInteraction(), null, "ownedInteractions", null, 0, -1, Model.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getInteraction_Name(), ecorePackage.getEString(), "name", null, 0, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$
                !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_Participants(), this.getParticipant(), null, "participants", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
        initEReference(getInteraction_Messages(), this.getMessage(), null, "messages", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_Executions(), this.getExecution(), null, "executions", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_States(), this.getState(), null, "states", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_InteractionUses(), this.getInteractionUse(), null, "interactionUses", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_CombinedFragments(), this.getCombinedFragment(), null, "combinedFragments", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_Ends(), this.getAbstractEnd(), null, "ends", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteraction_Constraints(), this.getConstraint(), null, "constraints", null, 0, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(participantEClass, Participant.class, "Participant", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getParticipant_Name(), ecorePackage.getEString(), "name", null, 1, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$
                !IS_DERIVED, IS_ORDERED);
        initEReference(getParticipant_Type(), theEcorePackage.getEClass(), null, "type", null, 1, 1, Participant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(messageEClass, Message.class, "Message", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getMessage_Name(), ecorePackage.getEString(), "name", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, //$NON-NLS-1$
                IS_ORDERED);
        initEReference(getMessage_SendingEnd(), this.getMessageEnd(), null, "sendingEnd", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getMessage_ReceivingEnd(), this.getMessageEnd(), null, "receivingEnd", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(callMessageEClass, CallMessage.class, "CallMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCallMessage_Operation(), theEcorePackage.getEOperation(), null, "operation", null, 1, 1, CallMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(featureAccessMessageEClass, FeatureAccessMessage.class, "FeatureAccessMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFeatureAccessMessage_IsWrite(), ecorePackage.getEBoolean(), "isWrite", null, 1, 1, FeatureAccessMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFeatureAccessMessage_Feature(), theEcorePackage.getEStructuralFeature(), null, "feature", null, 1, 1, FeatureAccessMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(createParticipantMessageEClass, CreateParticipantMessage.class, "CreateParticipantMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(destroyParticipantMessageEClass, DestroyParticipantMessage.class, "DestroyParticipantMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(returnMessageEClass, ReturnMessage.class, "ReturnMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getReturnMessage_InvocationMessage(), this.getMessage(), null, "invocationMessage", null, 1, 1, ReturnMessage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(executionEClass, Execution.class, "Execution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getExecution_Name(), theEcorePackage.getEString(), "name", null, 1, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$
                !IS_DERIVED, IS_ORDERED);
        initEReference(getExecution_Owner(), this.getParticipant(), null, "owner", null, 1, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecution_Start(), this.getExecutionEnd(), null, "start", null, 1, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getExecution_End(), this.getExecutionEnd(), null, "end", null, 1, 1, Execution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stateEClass, State.class, "State", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getState_Name(), theEcorePackage.getEString(), "name", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, //$NON-NLS-1$
                IS_ORDERED);
        initEReference(getState_Owner(), this.getParticipant(), null, "owner", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, //$NON-NLS-1$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getState_Start(), this.getStateEnd(), null, "start", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, //$NON-NLS-1$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getState_End(), this.getStateEnd(), null, "end", null, 1, 1, State.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, //$NON-NLS-1$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(interactionUseEClass, InteractionUse.class, "InteractionUse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getInteractionUse_Type(), ecorePackage.getEString(), "type", "\"ref\"", 1, 1, InteractionUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$//$NON-NLS-2$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteractionUse_Interaction(), this.getInteraction(), null, "interaction", null, 1, 1, InteractionUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteractionUse_CoveredParticipants(), this.getParticipant(), null, "coveredParticipants", null, 1, -1, InteractionUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteractionUse_Start(), this.getInteractionUseEnd(), null, "start", null, 1, 1, InteractionUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getInteractionUse_Finish(), this.getInteractionUseEnd(), null, "finish", null, 1, 1, InteractionUse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(combinedFragmentEClass, CombinedFragment.class, "CombinedFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCombinedFragment_Operator(), ecorePackage.getEString(), "operator", "\"opt\"", 1, 1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$//$NON-NLS-2$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCombinedFragment_CoveredParticipants(), this.getParticipant(), null, "coveredParticipants", null, 1, -1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCombinedFragment_Start(), this.getCombinedFragmentEnd(), null, "start", null, 1, 1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCombinedFragment_Finish(), this.getCombinedFragmentEnd(), null, "finish", null, 1, 1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getCombinedFragment_OwnedOperands(), this.getOperand(), null, "ownedOperands", null, 1, -1, CombinedFragment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(operandEClass, Operand.class, "Operand", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getOperand_Name(), ecorePackage.getEString(), "name", null, 1, 1, Operand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, //$NON-NLS-1$
                IS_ORDERED);
        initEReference(getOperand_Start(), this.getOperandEnd(), null, "start", null, 1, 1, Operand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractEndEClass, AbstractEnd.class, "AbstractEnd", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractEnd_Name(), theEcorePackage.getEString(), "name", null, 1, 1, AbstractEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$
                !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractEnd_Context(), this.getParticipant(), null, "context", null, 0, 1, AbstractEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(messageEndEClass, MessageEnd.class, "MessageEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getMessageEnd_Message(), this.getMessage(), null, "message", null, 1, 1, MessageEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(executionEndEClass, ExecutionEnd.class, "ExecutionEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getExecutionEnd_Execution(), this.getExecution(), null, "execution", null, 1, 1, ExecutionEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(stateEndEClass, StateEnd.class, "StateEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getStateEnd_State(), this.getState(), null, "state", null, 1, 1, StateEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, //$NON-NLS-1$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(interactionUseEndEClass, InteractionUseEnd.class, "InteractionUseEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getInteractionUseEnd_Owner(), this.getInteractionUse(), null, "owner", null, 1, 1, InteractionUseEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(combinedFragmentEndEClass, CombinedFragmentEnd.class, "CombinedFragmentEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getCombinedFragmentEnd_Owner(), this.getCombinedFragment(), null, "owner", null, 1, 1, CombinedFragmentEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(operandEndEClass, OperandEnd.class, "OperandEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getOperandEnd_Owner(), this.getOperand(), null, "owner", null, 1, 1, OperandEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(mixEndEClass, MixEnd.class, "MixEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(constraintEClass, Constraint.class, "Constraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getConstraint_Expression(), ecorePackage.getEString(), "expression", null, 1, 1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$
                !IS_DERIVED, IS_ORDERED);
        initEReference(getConstraint_ConstrainedEnds(), this.getAbstractEnd(), null, "constrainedEnds", null, 0, -1, Constraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, //$NON-NLS-1$
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // InteractionsPackageImpl
