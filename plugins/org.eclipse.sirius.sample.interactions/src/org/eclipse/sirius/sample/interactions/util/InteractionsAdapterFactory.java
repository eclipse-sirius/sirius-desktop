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
package org.eclipse.sirius.sample.interactions.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage
 * @generated
 */
public class InteractionsAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static InteractionsPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public InteractionsAdapterFactory() {
        if (InteractionsAdapterFactory.modelPackage == null) {
            InteractionsAdapterFactory.modelPackage = InteractionsPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == InteractionsAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == InteractionsAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected InteractionsSwitch<Adapter> modelSwitch = new InteractionsSwitch<Adapter>() {
        @Override
        public Adapter caseModel(Model object) {
            return createModelAdapter();
        }

        @Override
        public Adapter caseInteraction(Interaction object) {
            return createInteractionAdapter();
        }

        @Override
        public Adapter caseParticipant(Participant object) {
            return createParticipantAdapter();
        }

        @Override
        public Adapter caseMessage(Message object) {
            return createMessageAdapter();
        }

        @Override
        public Adapter caseCallMessage(CallMessage object) {
            return createCallMessageAdapter();
        }

        @Override
        public Adapter caseFeatureAccessMessage(FeatureAccessMessage object) {
            return createFeatureAccessMessageAdapter();
        }

        @Override
        public Adapter caseCreateParticipantMessage(CreateParticipantMessage object) {
            return createCreateParticipantMessageAdapter();
        }

        @Override
        public Adapter caseDestroyParticipantMessage(DestroyParticipantMessage object) {
            return createDestroyParticipantMessageAdapter();
        }

        @Override
        public Adapter caseReturnMessage(ReturnMessage object) {
            return createReturnMessageAdapter();
        }

        @Override
        public Adapter caseExecution(Execution object) {
            return createExecutionAdapter();
        }

        @Override
        public Adapter caseState(State object) {
            return createStateAdapter();
        }

        @Override
        public Adapter caseInteractionUse(InteractionUse object) {
            return createInteractionUseAdapter();
        }

        @Override
        public Adapter caseCombinedFragment(CombinedFragment object) {
            return createCombinedFragmentAdapter();
        }

        @Override
        public Adapter caseOperand(Operand object) {
            return createOperandAdapter();
        }

        @Override
        public Adapter caseAbstractEnd(AbstractEnd object) {
            return createAbstractEndAdapter();
        }

        @Override
        public Adapter caseMessageEnd(MessageEnd object) {
            return createMessageEndAdapter();
        }

        @Override
        public Adapter caseExecutionEnd(ExecutionEnd object) {
            return createExecutionEndAdapter();
        }

        @Override
        public Adapter caseStateEnd(StateEnd object) {
            return createStateEndAdapter();
        }

        @Override
        public Adapter caseInteractionUseEnd(InteractionUseEnd object) {
            return createInteractionUseEndAdapter();
        }

        @Override
        public Adapter caseCombinedFragmentEnd(CombinedFragmentEnd object) {
            return createCombinedFragmentEndAdapter();
        }

        @Override
        public Adapter caseOperandEnd(OperandEnd object) {
            return createOperandEndAdapter();
        }

        @Override
        public Adapter caseMixEnd(MixEnd object) {
            return createMixEndAdapter();
        }

        @Override
        public Adapter caseConstraint(Constraint object) {
            return createConstraintAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Model <em>Model</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Model
     * @generated
     */
    public Adapter createModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Interaction
     * <em>Interaction</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Interaction
     * @generated
     */
    public Adapter createInteractionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Participant
     * <em>Participant</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Participant
     * @generated
     */
    public Adapter createParticipantAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Message <em>Message</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Message
     * @generated
     */
    public Adapter createMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.CallMessage
     * <em>Call Message</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.CallMessage
     * @generated
     */
    public Adapter createCallMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.FeatureAccessMessage
     * <em>Feature Access Message</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.FeatureAccessMessage
     * @generated
     */
    public Adapter createFeatureAccessMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.CreateParticipantMessage
     * <em>Create Participant Message</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.CreateParticipantMessage
     * @generated
     */
    public Adapter createCreateParticipantMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.DestroyParticipantMessage
     * <em>Destroy Participant Message</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.DestroyParticipantMessage
     * @generated
     */
    public Adapter createDestroyParticipantMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.ReturnMessage
     * <em>Return Message</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.ReturnMessage
     * @generated
     */
    public Adapter createReturnMessageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Execution
     * <em>Execution</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Execution
     * @generated
     */
    public Adapter createExecutionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.State <em>State</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.State
     * @generated
     */
    public Adapter createStateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUse
     * <em>Interaction Use</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.InteractionUse
     * @generated
     */
    public Adapter createInteractionUseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragment
     * <em>Combined Fragment</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragment
     * @generated
     */
    public Adapter createCombinedFragmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Operand <em>Operand</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Operand
     * @generated
     */
    public Adapter createOperandAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.AbstractEnd
     * <em>Abstract End</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.AbstractEnd
     * @generated
     */
    public Adapter createAbstractEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.MessageEnd
     * <em>Message End</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.MessageEnd
     * @generated
     */
    public Adapter createMessageEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.ExecutionEnd
     * <em>Execution End</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.ExecutionEnd
     * @generated
     */
    public Adapter createExecutionEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.StateEnd
     * <em>State End</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.StateEnd
     * @generated
     */
    public Adapter createStateEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.InteractionUseEnd
     * <em>Interaction Use End</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.InteractionUseEnd
     * @generated
     */
    public Adapter createInteractionUseEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.CombinedFragmentEnd
     * <em>Combined Fragment End</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.CombinedFragmentEnd
     * @generated
     */
    public Adapter createCombinedFragmentEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.OperandEnd
     * <em>Operand End</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.OperandEnd
     * @generated
     */
    public Adapter createOperandEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.MixEnd <em>Mix End</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.MixEnd
     * @generated
     */
    public Adapter createMixEndAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.sample.interactions.Constraint
     * <em>Constraint</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.sample.interactions.Constraint
     * @generated
     */
    public Adapter createConstraintAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // InteractionsAdapterFactory
