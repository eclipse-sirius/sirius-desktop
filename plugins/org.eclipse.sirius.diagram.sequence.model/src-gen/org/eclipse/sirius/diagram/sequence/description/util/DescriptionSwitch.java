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
package org.eclipse.sirius.diagram.sequence.description.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.DragAndDropTargetDescription;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.IEdgeMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping;
import org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable;
import org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.EventMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.description.GateMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.InteractionContainerMapping;
import org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping;
import org.eclipse.sirius.diagram.sequence.description.OperandMapping;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.description.StateMapping;
import org.eclipse.sirius.viewpoint.description.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;
import org.eclipse.sirius.viewpoint.description.PasteTargetDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage
 * @generated
 */
public class DescriptionSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionSwitch() {
        if (DescriptionSwitch.modelPackage == null) {
            DescriptionSwitch.modelPackage = DescriptionPackage.eINSTANCE;
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
        if (theEClass.eContainer() == DescriptionSwitch.modelPackage) {
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
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION: {
            SequenceDiagramDescription sequenceDiagramDescription = (SequenceDiagramDescription) theEObject;
            T result = caseSequenceDiagramDescription(sequenceDiagramDescription);
            if (result == null) {
                result = caseDiagramDescription(sequenceDiagramDescription);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(sequenceDiagramDescription);
            }
            if (result == null) {
                result = caseRepresentationDescription(sequenceDiagramDescription);
            }
            if (result == null) {
                result = casePasteTargetDescription(sequenceDiagramDescription);
            }
            if (result == null) {
                result = caseDocumentedElement(sequenceDiagramDescription);
            }
            if (result == null) {
                result = caseEndUserDocumentedElement(sequenceDiagramDescription);
            }
            if (result == null) {
                result = caseIdentifiedElement(sequenceDiagramDescription);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.INSTANCE_ROLE_MAPPING: {
            InstanceRoleMapping instanceRoleMapping = (InstanceRoleMapping) theEObject;
            T result = caseInstanceRoleMapping(instanceRoleMapping);
            if (result == null) {
                result = caseNodeMapping(instanceRoleMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(instanceRoleMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(instanceRoleMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(instanceRoleMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(instanceRoleMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(instanceRoleMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(instanceRoleMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(instanceRoleMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EVENT_MAPPING: {
            EventMapping eventMapping = (EventMapping) theEObject;
            T result = caseEventMapping(eventMapping);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DELIMITED_EVENT_MAPPING: {
            DelimitedEventMapping delimitedEventMapping = (DelimitedEventMapping) theEObject;
            T result = caseDelimitedEventMapping(delimitedEventMapping);
            if (result == null) {
                result = caseEventMapping(delimitedEventMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.EXECUTION_MAPPING: {
            ExecutionMapping executionMapping = (ExecutionMapping) theEObject;
            T result = caseExecutionMapping(executionMapping);
            if (result == null) {
                result = caseNodeMapping(executionMapping);
            }
            if (result == null) {
                result = caseDelimitedEventMapping(executionMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(executionMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(executionMapping);
            }
            if (result == null) {
                result = caseEventMapping(executionMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(executionMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(executionMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(executionMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(executionMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(executionMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.STATE_MAPPING: {
            StateMapping stateMapping = (StateMapping) theEObject;
            T result = caseStateMapping(stateMapping);
            if (result == null) {
                result = caseNodeMapping(stateMapping);
            }
            if (result == null) {
                result = caseDelimitedEventMapping(stateMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(stateMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(stateMapping);
            }
            if (result == null) {
                result = caseEventMapping(stateMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(stateMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(stateMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(stateMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(stateMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(stateMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.END_OF_LIFE_MAPPING: {
            EndOfLifeMapping endOfLifeMapping = (EndOfLifeMapping) theEObject;
            T result = caseEndOfLifeMapping(endOfLifeMapping);
            if (result == null) {
                result = caseNodeMapping(endOfLifeMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(endOfLifeMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(endOfLifeMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(endOfLifeMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(endOfLifeMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(endOfLifeMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(endOfLifeMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(endOfLifeMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.MESSAGE_MAPPING: {
            MessageMapping messageMapping = (MessageMapping) theEObject;
            T result = caseMessageMapping(messageMapping);
            if (result == null) {
                result = caseEdgeMapping(messageMapping);
            }
            if (result == null) {
                result = caseEventMapping(messageMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(messageMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(messageMapping);
            }
            if (result == null) {
                result = caseIEdgeMapping(messageMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(messageMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(messageMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(messageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.BASIC_MESSAGE_MAPPING: {
            BasicMessageMapping basicMessageMapping = (BasicMessageMapping) theEObject;
            T result = caseBasicMessageMapping(basicMessageMapping);
            if (result == null) {
                result = caseMessageMapping(basicMessageMapping);
            }
            if (result == null) {
                result = caseEdgeMapping(basicMessageMapping);
            }
            if (result == null) {
                result = caseEventMapping(basicMessageMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(basicMessageMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(basicMessageMapping);
            }
            if (result == null) {
                result = caseIEdgeMapping(basicMessageMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(basicMessageMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(basicMessageMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(basicMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.RETURN_MESSAGE_MAPPING: {
            ReturnMessageMapping returnMessageMapping = (ReturnMessageMapping) theEObject;
            T result = caseReturnMessageMapping(returnMessageMapping);
            if (result == null) {
                result = caseMessageMapping(returnMessageMapping);
            }
            if (result == null) {
                result = caseEdgeMapping(returnMessageMapping);
            }
            if (result == null) {
                result = caseEventMapping(returnMessageMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(returnMessageMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(returnMessageMapping);
            }
            if (result == null) {
                result = caseIEdgeMapping(returnMessageMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(returnMessageMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(returnMessageMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(returnMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.CREATION_MESSAGE_MAPPING: {
            CreationMessageMapping creationMessageMapping = (CreationMessageMapping) theEObject;
            T result = caseCreationMessageMapping(creationMessageMapping);
            if (result == null) {
                result = caseMessageMapping(creationMessageMapping);
            }
            if (result == null) {
                result = caseEdgeMapping(creationMessageMapping);
            }
            if (result == null) {
                result = caseEventMapping(creationMessageMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(creationMessageMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(creationMessageMapping);
            }
            if (result == null) {
                result = caseIEdgeMapping(creationMessageMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(creationMessageMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(creationMessageMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(creationMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.DESTRUCTION_MESSAGE_MAPPING: {
            DestructionMessageMapping destructionMessageMapping = (DestructionMessageMapping) theEObject;
            T result = caseDestructionMessageMapping(destructionMessageMapping);
            if (result == null) {
                result = caseMessageMapping(destructionMessageMapping);
            }
            if (result == null) {
                result = caseEdgeMapping(destructionMessageMapping);
            }
            if (result == null) {
                result = caseEventMapping(destructionMessageMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(destructionMessageMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(destructionMessageMapping);
            }
            if (result == null) {
                result = caseIEdgeMapping(destructionMessageMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(destructionMessageMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(destructionMessageMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(destructionMessageMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.MESSAGE_END_VARIABLE: {
            MessageEndVariable messageEndVariable = (MessageEndVariable) theEObject;
            T result = caseMessageEndVariable(messageEndVariable);
            if (result == null) {
                result = caseAbstractVariable(messageEndVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COVERED_LIFELINES_VARIABLE: {
            CoveredLifelinesVariable coveredLifelinesVariable = (CoveredLifelinesVariable) theEObject;
            T result = caseCoveredLifelinesVariable(coveredLifelinesVariable);
            if (result == null) {
                result = caseAbstractVariable(coveredLifelinesVariable);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.FRAME_MAPPING: {
            FrameMapping frameMapping = (FrameMapping) theEObject;
            T result = caseFrameMapping(frameMapping);
            if (result == null) {
                result = caseContainerMapping(frameMapping);
            }
            if (result == null) {
                result = caseDelimitedEventMapping(frameMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(frameMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(frameMapping);
            }
            if (result == null) {
                result = caseEventMapping(frameMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(frameMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(frameMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(frameMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(frameMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(frameMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.INTERACTION_USE_MAPPING: {
            InteractionUseMapping interactionUseMapping = (InteractionUseMapping) theEObject;
            T result = caseInteractionUseMapping(interactionUseMapping);
            if (result == null) {
                result = caseFrameMapping(interactionUseMapping);
            }
            if (result == null) {
                result = caseContainerMapping(interactionUseMapping);
            }
            if (result == null) {
                result = caseDelimitedEventMapping(interactionUseMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(interactionUseMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(interactionUseMapping);
            }
            if (result == null) {
                result = caseEventMapping(interactionUseMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(interactionUseMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(interactionUseMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(interactionUseMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(interactionUseMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(interactionUseMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.COMBINED_FRAGMENT_MAPPING: {
            CombinedFragmentMapping combinedFragmentMapping = (CombinedFragmentMapping) theEObject;
            T result = caseCombinedFragmentMapping(combinedFragmentMapping);
            if (result == null) {
                result = caseFrameMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseContainerMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseDelimitedEventMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseEventMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(combinedFragmentMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(combinedFragmentMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(combinedFragmentMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.OPERAND_MAPPING: {
            OperandMapping operandMapping = (OperandMapping) theEObject;
            T result = caseOperandMapping(operandMapping);
            if (result == null) {
                result = caseContainerMapping(operandMapping);
            }
            if (result == null) {
                result = caseDelimitedEventMapping(operandMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(operandMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(operandMapping);
            }
            if (result == null) {
                result = caseEventMapping(operandMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(operandMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(operandMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(operandMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(operandMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(operandMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.OBSERVATION_POINT_MAPPING: {
            ObservationPointMapping observationPointMapping = (ObservationPointMapping) theEObject;
            T result = caseObservationPointMapping(observationPointMapping);
            if (result == null) {
                result = caseNodeMapping(observationPointMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(observationPointMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(observationPointMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(observationPointMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(observationPointMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(observationPointMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(observationPointMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(observationPointMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.INTERACTION_CONTAINER_MAPPING: {
            InteractionContainerMapping interactionContainerMapping = (InteractionContainerMapping) theEObject;
            T result = caseInteractionContainerMapping(interactionContainerMapping);
            if (result == null) {
                result = caseContainerMapping(interactionContainerMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(interactionContainerMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(interactionContainerMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(interactionContainerMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(interactionContainerMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(interactionContainerMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(interactionContainerMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(interactionContainerMapping);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DescriptionPackage.GATE_MAPPING: {
            GateMapping gateMapping = (GateMapping) theEObject;
            T result = caseGateMapping(gateMapping);
            if (result == null) {
                result = caseNodeMapping(gateMapping);
            }
            if (result == null) {
                result = caseAbstractNodeMapping(gateMapping);
            }
            if (result == null) {
                result = caseDragAndDropTargetDescription(gateMapping);
            }
            if (result == null) {
                result = caseDiagramElementMapping(gateMapping);
            }
            if (result == null) {
                result = caseDocumentedElement(gateMapping);
            }
            if (result == null) {
                result = caseRepresentationElementMapping(gateMapping);
            }
            if (result == null) {
                result = casePasteTargetDescription(gateMapping);
            }
            if (result == null) {
                result = caseIdentifiedElement(gateMapping);
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
     * Returns the result of interpreting the object as an instance of '<em>Sequence Diagram Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sequence Diagram Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSequenceDiagramDescription(SequenceDiagramDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Instance Role Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Instance Role Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInstanceRoleMapping(InstanceRoleMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Event Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEventMapping(EventMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delimited Event Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delimited Event Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDelimitedEventMapping(DelimitedEventMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Execution Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Execution Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseExecutionMapping(ExecutionMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStateMapping(StateMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Of Life Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Of Life Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndOfLifeMapping(EndOfLifeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessageMapping(MessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Basic Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Basic Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicMessageMapping(BasicMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Return Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Return Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseReturnMessageMapping(ReturnMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Creation Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Creation Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCreationMessageMapping(CreationMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Destruction Message Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Destruction Message Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDestructionMessageMapping(DestructionMessageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Message End Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message End Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMessageEndVariable(MessageEndVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Covered Lifelines Variable</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Covered Lifelines Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCoveredLifelinesVariable(CoveredLifelinesVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Frame Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Frame Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFrameMapping(FrameMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interaction Use Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interaction Use Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionUseMapping(InteractionUseMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Combined Fragment Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Combined Fragment Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCombinedFragmentMapping(CombinedFragmentMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Operand Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operand Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOperandMapping(OperandMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Observation Point Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Observation Point Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseObservationPointMapping(ObservationPointMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Interaction Container Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Interaction Container Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseInteractionContainerMapping(InteractionContainerMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gate Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gate Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGateMapping(GateMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Drag And Drop Target Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Drag And Drop Target Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDragAndDropTargetDescription(DragAndDropTargetDescription object) {
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
     * Returns the result of interpreting the object as an instance of '<em>End User Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End User Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndUserDocumentedElement(EndUserDocumentedElement object) {
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
     * Returns the result of interpreting the object as an instance of '<em>Representation Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationDescription(RepresentationDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Paste Target Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Paste Target Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T casePasteTargetDescription(PasteTargetDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Description</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramDescription(DiagramDescription object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Representation Element Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation Element Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepresentationElementMapping(RepresentationElementMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Diagram Element Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Diagram Element Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramElementMapping(DiagramElementMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Node Mapping</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Node Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractNodeMapping(AbstractNodeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeMapping(NodeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IEdge Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IEdge Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIEdgeMapping(IEdgeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Mapping</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeMapping(EdgeMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Variable</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractVariable(AbstractVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Mapping</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerMapping(ContainerMapping object) {
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

} // DescriptionSwitch
