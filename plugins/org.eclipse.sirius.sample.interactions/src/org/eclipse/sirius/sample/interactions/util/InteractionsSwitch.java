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

import java.util.List;

import org.eclipse.emf.ecore.EClass;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage
 * @generated
 */
public class InteractionsSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static InteractionsPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public InteractionsSwitch() {
        if (InteractionsSwitch.modelPackage == null) {
            InteractionsSwitch.modelPackage = InteractionsPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == InteractionsSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case InteractionsPackage.MODEL: {
            Model model = (Model) theEObject;
            T result = caseModel(model);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.INTERACTION: {
            Interaction interaction = (Interaction) theEObject;
            T result = caseInteraction(interaction);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.PARTICIPANT: {
            Participant participant = (Participant) theEObject;
            T result = caseParticipant(participant);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.MESSAGE: {
            Message message = (Message) theEObject;
            T result = caseMessage(message);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.CALL_MESSAGE: {
            CallMessage callMessage = (CallMessage) theEObject;
            T result = caseCallMessage(callMessage);
            if (result == null) {
                result = caseMessage(callMessage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.FEATURE_ACCESS_MESSAGE: {
            FeatureAccessMessage featureAccessMessage = (FeatureAccessMessage) theEObject;
            T result = caseFeatureAccessMessage(featureAccessMessage);
            if (result == null) {
                result = caseMessage(featureAccessMessage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.CREATE_PARTICIPANT_MESSAGE: {
            CreateParticipantMessage createParticipantMessage = (CreateParticipantMessage) theEObject;
            T result = caseCreateParticipantMessage(createParticipantMessage);
            if (result == null) {
                result = caseMessage(createParticipantMessage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.DESTROY_PARTICIPANT_MESSAGE: {
            DestroyParticipantMessage destroyParticipantMessage = (DestroyParticipantMessage) theEObject;
            T result = caseDestroyParticipantMessage(destroyParticipantMessage);
            if (result == null) {
                result = caseMessage(destroyParticipantMessage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.RETURN_MESSAGE: {
            ReturnMessage returnMessage = (ReturnMessage) theEObject;
            T result = caseReturnMessage(returnMessage);
            if (result == null) {
                result = caseMessage(returnMessage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.EXECUTION: {
            Execution execution = (Execution) theEObject;
            T result = caseExecution(execution);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.STATE: {
            State state = (State) theEObject;
            T result = caseState(state);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.INTERACTION_USE: {
            InteractionUse interactionUse = (InteractionUse) theEObject;
            T result = caseInteractionUse(interactionUse);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.COMBINED_FRAGMENT: {
            CombinedFragment combinedFragment = (CombinedFragment) theEObject;
            T result = caseCombinedFragment(combinedFragment);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.OPERAND: {
            Operand operand = (Operand) theEObject;
            T result = caseOperand(operand);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.ABSTRACT_END: {
            AbstractEnd abstractEnd = (AbstractEnd) theEObject;
            T result = caseAbstractEnd(abstractEnd);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.MESSAGE_END: {
            MessageEnd messageEnd = (MessageEnd) theEObject;
            T result = caseMessageEnd(messageEnd);
            if (result == null) {
                result = caseAbstractEnd(messageEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.EXECUTION_END: {
            ExecutionEnd executionEnd = (ExecutionEnd) theEObject;
            T result = caseExecutionEnd(executionEnd);
            if (result == null) {
                result = caseAbstractEnd(executionEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.STATE_END: {
            StateEnd stateEnd = (StateEnd) theEObject;
            T result = caseStateEnd(stateEnd);
            if (result == null) {
                result = caseAbstractEnd(stateEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.INTERACTION_USE_END: {
            InteractionUseEnd interactionUseEnd = (InteractionUseEnd) theEObject;
            T result = caseInteractionUseEnd(interactionUseEnd);
            if (result == null) {
                result = caseAbstractEnd(interactionUseEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.COMBINED_FRAGMENT_END: {
            CombinedFragmentEnd combinedFragmentEnd = (CombinedFragmentEnd) theEObject;
            T result = caseCombinedFragmentEnd(combinedFragmentEnd);
            if (result == null) {
                result = caseAbstractEnd(combinedFragmentEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.OPERAND_END: {
            OperandEnd operandEnd = (OperandEnd) theEObject;
            T result = caseOperandEnd(operandEnd);
            if (result == null) {
                result = caseAbstractEnd(operandEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.MIX_END: {
            MixEnd mixEnd = (MixEnd) theEObject;
            T result = caseMixEnd(mixEnd);
            if (result == null) {
                result = caseExecutionEnd(mixEnd);
            }
            if (result == null) {
                result = caseMessageEnd(mixEnd);
            }
            if (result == null) {
                result = caseAbstractEnd(mixEnd);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case InteractionsPackage.CONSTRAINT: {
            Constraint constraint = (Constraint) theEObject;
            T result = caseConstraint(constraint);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Model</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModel(Model object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interaction</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interaction</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteraction(Interaction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Participant</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Participant</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseParticipant(Participant object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Message</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Call Message</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Call Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCallMessage(CallMessage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Feature Access Message</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Feature Access Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFeatureAccessMessage(FeatureAccessMessage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Create Participant Message</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Create Participant Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreateParticipantMessage(CreateParticipantMessage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Destroy Participant Message</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Destroy Participant Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDestroyParticipantMessage(DestroyParticipantMessage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Return Message</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Return Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReturnMessage(ReturnMessage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Execution</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Execution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExecution(Execution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>State</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>State</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseState(State object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interaction Use</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interaction Use</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionUse(InteractionUse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Combined Fragment</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Combined Fragment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCombinedFragment(CombinedFragment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Operand</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Operand</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperand(Operand object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract End</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractEnd(AbstractEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Message End</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Message End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessageEnd(MessageEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Execution End</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Execution End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExecutionEnd(ExecutionEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>State End</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>State End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStateEnd(StateEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interaction Use End</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interaction Use End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionUseEnd(InteractionUseEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Combined Fragment End</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Combined Fragment End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCombinedFragmentEnd(CombinedFragmentEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Operand End</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Operand End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperandEnd(OperandEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Mix End</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Mix End</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMixEnd(MixEnd object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Constraint</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConstraint(Constraint object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // InteractionsSwitch
