/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.description.tool.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.GateCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.LifelineCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.SequenceDiagramToolDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage
 * @generated
 */
public class ToolSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static ToolPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ToolSwitch() {
        if (ToolSwitch.modelPackage == null) {
            ToolSwitch.modelPackage = ToolPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == ToolSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION: {
            SequenceDiagramToolDescription sequenceDiagramToolDescription = (SequenceDiagramToolDescription) theEObject;
            T result = caseSequenceDiagramToolDescription(sequenceDiagramToolDescription);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.ORDERED_ELEMENT_CREATION_TOOL: {
            OrderedElementCreationTool orderedElementCreationTool = (OrderedElementCreationTool) theEObject;
            T result = caseOrderedElementCreationTool(orderedElementCreationTool);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.COVERING_ELEMENT_CREATION_TOOL: {
            CoveringElementCreationTool coveringElementCreationTool = (CoveringElementCreationTool) theEObject;
            T result = caseCoveringElementCreationTool(coveringElementCreationTool);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL: {
            InstanceRoleCreationTool instanceRoleCreationTool = (InstanceRoleCreationTool) theEObject;
            T result = caseInstanceRoleCreationTool(instanceRoleCreationTool);
            if (result == null) {
                result = caseNodeCreationDescription(instanceRoleCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(instanceRoleCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(instanceRoleCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(instanceRoleCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(instanceRoleCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(instanceRoleCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(instanceRoleCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.LIFELINE_CREATION_TOOL: {
            LifelineCreationTool lifelineCreationTool = (LifelineCreationTool) theEObject;
            T result = caseLifelineCreationTool(lifelineCreationTool);
            if (result == null) {
                result = caseContainerCreationDescription(lifelineCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(lifelineCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(lifelineCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(lifelineCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(lifelineCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(lifelineCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(lifelineCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.MESSAGE_CREATION_TOOL: {
            MessageCreationTool messageCreationTool = (MessageCreationTool) theEObject;
            T result = caseMessageCreationTool(messageCreationTool);
            if (result == null) {
                result = caseSequenceDiagramToolDescription(messageCreationTool);
            }
            if (result == null) {
                result = caseEdgeCreationDescription(messageCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(messageCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(messageCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(messageCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(messageCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(messageCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(messageCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.EXECUTION_CREATION_TOOL: {
            ExecutionCreationTool executionCreationTool = (ExecutionCreationTool) theEObject;
            T result = caseExecutionCreationTool(executionCreationTool);
            if (result == null) {
                result = caseNodeCreationDescription(executionCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(executionCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(executionCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(executionCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(executionCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(executionCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(executionCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(executionCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.STATE_CREATION_TOOL: {
            StateCreationTool stateCreationTool = (StateCreationTool) theEObject;
            T result = caseStateCreationTool(stateCreationTool);
            if (result == null) {
                result = caseNodeCreationDescription(stateCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(stateCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(stateCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(stateCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(stateCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(stateCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(stateCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(stateCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INTERACTION_USE_CREATION_TOOL: {
            InteractionUseCreationTool interactionUseCreationTool = (InteractionUseCreationTool) theEObject;
            T result = caseInteractionUseCreationTool(interactionUseCreationTool);
            if (result == null) {
                result = caseContainerCreationDescription(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseCoveringElementCreationTool(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(interactionUseCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(interactionUseCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL: {
            CombinedFragmentCreationTool combinedFragmentCreationTool = (CombinedFragmentCreationTool) theEObject;
            T result = caseCombinedFragmentCreationTool(combinedFragmentCreationTool);
            if (result == null) {
                result = caseContainerCreationDescription(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseCoveringElementCreationTool(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(combinedFragmentCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.OPERAND_CREATION_TOOL: {
            OperandCreationTool operandCreationTool = (OperandCreationTool) theEObject;
            T result = caseOperandCreationTool(operandCreationTool);
            if (result == null) {
                result = caseContainerCreationDescription(operandCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(operandCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(operandCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(operandCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(operandCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(operandCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(operandCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(operandCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.OBSERVATION_POINT_CREATION_TOOL: {
            ObservationPointCreationTool observationPointCreationTool = (ObservationPointCreationTool) theEObject;
            T result = caseObservationPointCreationTool(observationPointCreationTool);
            if (result == null) {
                result = caseNodeCreationDescription(observationPointCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(observationPointCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(observationPointCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(observationPointCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(observationPointCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(observationPointCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(observationPointCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(observationPointCreationTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.REORDER_TOOL: {
            ReorderTool reorderTool = (ReorderTool) theEObject;
            T result = caseReorderTool(reorderTool);
            if (result == null) {
                result = caseAbstractToolDescription(reorderTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(reorderTool);
            }
            if (result == null) {
                result = caseToolEntry(reorderTool);
            }
            if (result == null) {
                result = caseDocumentedElement(reorderTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(reorderTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL: {
            InstanceRoleReorderTool instanceRoleReorderTool = (InstanceRoleReorderTool) theEObject;
            T result = caseInstanceRoleReorderTool(instanceRoleReorderTool);
            if (result == null) {
                result = caseAbstractToolDescription(instanceRoleReorderTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(instanceRoleReorderTool);
            }
            if (result == null) {
                result = caseToolEntry(instanceRoleReorderTool);
            }
            if (result == null) {
                result = caseDocumentedElement(instanceRoleReorderTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(instanceRoleReorderTool);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case ToolPackage.GATE_CREATION_TOOL: {
            GateCreationTool gateCreationTool = (GateCreationTool) theEObject;
            T result = caseGateCreationTool(gateCreationTool);
            if (result == null) {
                result = caseNodeCreationDescription(gateCreationTool);
            }
            if (result == null) {
                result = caseSequenceDiagramToolDescription(gateCreationTool);
            }
            if (result == null) {
                result = caseOrderedElementCreationTool(gateCreationTool);
            }
            if (result == null) {
                result = caseMappingBasedToolDescription(gateCreationTool);
            }
            if (result == null) {
                result = caseAbstractToolDescription(gateCreationTool);
            }
            if (result == null) {
                result = caseToolEntry(gateCreationTool);
            }
            if (result == null) {
                result = caseDocumentedElement(gateCreationTool);
            }
            if (result == null) {
                result = caseIdentifiedElement(gateCreationTool);
            }
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
     * Returns the result of interpreting the object as an instance of '<em>Sequence Diagram Tool Description</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sequence Diagram Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSequenceDiagramToolDescription(SequenceDiagramToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ordered Element Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ordered Element Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOrderedElementCreationTool(OrderedElementCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Covering Element Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Covering Element Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCoveringElementCreationTool(CoveringElementCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Role Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Role Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInstanceRoleCreationTool(InstanceRoleCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lifeline Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lifeline Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLifelineCreationTool(LifelineCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessageCreationTool(MessageCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Execution Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Execution Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExecutionCreationTool(ExecutionCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStateCreationTool(StateCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Reorder Tool</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Reorder Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReorderTool(ReorderTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Role Reorder Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Role Reorder Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInstanceRoleReorderTool(InstanceRoleReorderTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gate Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gate Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGateCreationTool(GateCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Observation Point Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Observation Point Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseObservationPointCreationTool(ObservationPointCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interaction Use Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interaction Use Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionUseCreationTool(InteractionUseCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Combined Fragment Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Combined Fragment Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCombinedFragmentCreationTool(CombinedFragmentCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operand Creation Tool</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operand Creation Tool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperandCreationTool(OperandCreationTool object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Entry</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseToolEntry(ToolEntry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Tool Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractToolDescription(AbstractToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Mapping Based Tool Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Mapping Based Tool Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMappingBasedToolDescription(MappingBasedToolDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeCreationDescription(NodeCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerCreationDescription(ContainerCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Creation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Creation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeCreationDescription(EdgeCreationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // ToolSwitch
