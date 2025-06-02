/*******************************************************************************
 * Copyright (c) 2010, 2025 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.util;

import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DiagramElementMappingQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.AbstractFrame;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.CombinedFragment;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.EndOfLife;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Execution;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Gate;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionUse;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Message;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.Operand;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.State;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the sequence elements's
 * inheritance hierarchy. It supports the call
 * {@link #doSwitch(ISequenceElement) doSwitch(ise)} to invoke the
 * <code>caseXXX</code> method for each class of the model, starting with the
 * actual class of the object and proceeding up the inheritance hierarchy until
 * a non-null result is returned, which is the result of the switch. <!--
 * end-user-doc -->
 * 
 * 
 * @param <T>
 *            the return type.
 * 
 * @author mporhel
 */
public class ISequenceElementSwitch<T> {

    /**
     * Creates an instance of the switch.
     */
    public ISequenceElementSwitch() {

    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param element
     *            the element on which to do the switch.
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     */
    public T doSwitch(ISequenceElement element) {
        DDiagramElement dde = null;
        if (element.getNotationView().getElement() instanceof DDiagramElement) {
            dde = (DDiagramElement) element.getNotationView().getElement();
        }
        if (dde != null) {
            DiagramElementMapping mapping = dde.getDiagramElementMapping();
            if (mapping != null) {
                DiagramElementMapping mappingToCheck = new DiagramElementMappingQuery(mapping).getRootMapping();
                return doSwitch(mappingToCheck, element);
            }
        }
        return null;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param mapping
     *            the considered mapping for the swith (mapping of the element).
     * @param element
     *            the element on which to do the switch.
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     */
    protected T doSwitch(DiagramElementMapping mapping, ISequenceElement element) {
        if (mapping != null && mapping.eClass().getEPackage() == DescriptionPackage.eINSTANCE) {
            return doSwitch(mapping.eClass().getClassifierID(), element);
        } else {
            return defaultCase(element);
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
    // CHECKSTYLE:OFF
    protected T doSwitch(int classifierID, ISequenceElement element) {
        switch (classifierID) {
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION: {
            SequenceDiagram diag = (SequenceDiagram) element;
            T result = caseSequenceDiagram(diag);
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.INSTANCE_ROLE_MAPPING: {
            InstanceRole instanceRole = (InstanceRole) element;
            T result = caseInstanceRole(instanceRole);
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.EVENT_MAPPING: {
            ISequenceEvent event = (ISequenceEvent) element;
            T result = caseEvent(event);
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.DELIMITED_EVENT_MAPPING: {
            ISequenceEvent delimitedEvent = (ISequenceEvent) element;
            T result = caseDelimitedEvent(delimitedEvent);
            if (result == null) {
                result = caseEvent(delimitedEvent);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.EXECUTION_MAPPING: {
            Execution execution = (Execution) element;
            T result = caseExecution(execution);
            if (result == null) {
                result = caseDelimitedEvent(execution);
            }
            if (result == null) {
                result = caseEvent(execution);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.STATE_MAPPING: {
            State state = (State) element;
            T result = caseState(state);
            if (result == null) {
                result = caseDelimitedEvent(state);
            }
            if (result == null) {
                result = caseEvent(state);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.END_OF_LIFE_MAPPING: {
            EndOfLife endOfLife = (EndOfLife) element;
            T result = caseEndOfLife(endOfLife);
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }

        case DescriptionPackage.BASIC_MESSAGE_MAPPING: {
            Message basicMessage = (Message) element;
            T result = caseBasicMessage(basicMessage);
            if (result == null) {
                result = caseMessage(basicMessage);
            }
            return result;
        }
        case DescriptionPackage.RETURN_MESSAGE_MAPPING: {
            Message returnMessage = (Message) element;
            T result = caseReturnMessage(returnMessage);
            if (result == null) {
                result = caseMessage(returnMessage);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.CREATION_MESSAGE_MAPPING: {
            Message creationMessage = (Message) element;
            T result = caseCreationMessage(creationMessage);
            if (result == null) {
                result = caseMessage(creationMessage);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.DESTRUCTION_MESSAGE_MAPPING: {
            Message destructionMessage = (Message) element;
            T result = caseDestructionMessage(destructionMessage);
            if (result == null) {
                result = caseMessage(destructionMessage);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.FRAME_MAPPING: {
            AbstractFrame frame = (AbstractFrame) element;
            T result = caseFrame(frame);
            if (result == null) {
                result = caseDelimitedEvent(frame);
            }
            if (result == null) {
                result = caseEvent(frame);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.INTERACTION_USE_MAPPING: {
            InteractionUse interactionUse = (InteractionUse) element;
            T result = caseInteractionUse(interactionUse);
            if (result == null) {
                result = caseFrame(interactionUse);
            }
            if (result == null) {
                result = caseDelimitedEvent(interactionUse);
            }
            if (result == null) {
                result = caseEvent(interactionUse);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.COMBINED_FRAGMENT_MAPPING: {
            CombinedFragment combinedFragment = (CombinedFragment) element;
            T result = caseCombinedFragment(combinedFragment);
            if (result == null) {
                result = caseFrame(combinedFragment);
            }
            if (result == null) {
                result = caseDelimitedEvent(combinedFragment);
            }
            if (result == null) {
                result = caseEvent(combinedFragment);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.OPERAND_MAPPING: {
            Operand operand = (Operand) element;
            T result = caseOperand(operand);
            if (result == null) {
                result = caseDelimitedEvent(operand);
            }
            if (result == null) {
                result = caseEvent(operand);
            }
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        case DescriptionPackage.GATE_MAPPING: {
            Gate gate = (Gate) element;
            T result = caseGate(gate);
            if (result == null) {
                result = defaultCase(element);
            }
            return result;
        }
        default:
            return defaultCase(element);
        }
    }

    // CHECKSTYLE:ON

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Sequence Diagram Description</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Sequence Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSequenceDiagram(SequenceDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Instance Role </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Instance Role </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInstanceRole(InstanceRole object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Event </em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Event </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEvent(ISequenceEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Delimited Event </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Delimited Event </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDelimitedEvent(ISequenceEvent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Execution </em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Execution </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExecution(Execution object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>State </em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>State </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseState(State object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>End Of Life </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>End Of Life </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndOfLife(EndOfLife object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Message </em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Message </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Basic Message </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Basic Message </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Return Message </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Return Message </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReturnMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Creation Message </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Creation Message </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreationMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Destruction Message </em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Destruction Message </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDestructionMessage(Message object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Frame </em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Frame </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFrame(AbstractFrame object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Interaction Use </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Interaction Use </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionUse(InteractionUse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Combined Fragment </em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Combined Fragment </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCombinedFragment(CombinedFragment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Operand </em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Operand </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperand(Operand object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of ' <em>Gate </em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of ' <em>Execution </em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGate(Gate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of ' <em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of ' <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(ISequenceElement object) {
        return null;
    }

} // DescriptionSwitch
