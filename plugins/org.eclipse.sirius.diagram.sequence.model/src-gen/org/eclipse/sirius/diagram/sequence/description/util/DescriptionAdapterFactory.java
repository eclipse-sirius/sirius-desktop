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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage
 * @generated
 */
public class DescriptionAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DescriptionPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DescriptionAdapterFactory() {
        if (DescriptionAdapterFactory.modelPackage == null) {
            DescriptionAdapterFactory.modelPackage = DescriptionPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == DescriptionAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == DescriptionAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DescriptionSwitch<Adapter> modelSwitch = new DescriptionSwitch<>() {
        @Override
        public Adapter caseSequenceDiagramDescription(SequenceDiagramDescription object) {
            return createSequenceDiagramDescriptionAdapter();
        }

        @Override
        public Adapter caseInstanceRoleMapping(InstanceRoleMapping object) {
            return createInstanceRoleMappingAdapter();
        }

        @Override
        public Adapter caseEventMapping(EventMapping object) {
            return createEventMappingAdapter();
        }

        @Override
        public Adapter caseDelimitedEventMapping(DelimitedEventMapping object) {
            return createDelimitedEventMappingAdapter();
        }

        @Override
        public Adapter caseExecutionMapping(ExecutionMapping object) {
            return createExecutionMappingAdapter();
        }

        @Override
        public Adapter caseStateMapping(StateMapping object) {
            return createStateMappingAdapter();
        }

        @Override
        public Adapter caseEndOfLifeMapping(EndOfLifeMapping object) {
            return createEndOfLifeMappingAdapter();
        }

        @Override
        public Adapter caseMessageMapping(MessageMapping object) {
            return createMessageMappingAdapter();
        }

        @Override
        public Adapter caseBasicMessageMapping(BasicMessageMapping object) {
            return createBasicMessageMappingAdapter();
        }

        @Override
        public Adapter caseReturnMessageMapping(ReturnMessageMapping object) {
            return createReturnMessageMappingAdapter();
        }

        @Override
        public Adapter caseCreationMessageMapping(CreationMessageMapping object) {
            return createCreationMessageMappingAdapter();
        }

        @Override
        public Adapter caseDestructionMessageMapping(DestructionMessageMapping object) {
            return createDestructionMessageMappingAdapter();
        }

        @Override
        public Adapter caseMessageEndVariable(MessageEndVariable object) {
            return createMessageEndVariableAdapter();
        }

        @Override
        public Adapter caseCoveredLifelinesVariable(CoveredLifelinesVariable object) {
            return createCoveredLifelinesVariableAdapter();
        }

        @Override
        public Adapter caseFrameMapping(FrameMapping object) {
            return createFrameMappingAdapter();
        }

        @Override
        public Adapter caseInteractionUseMapping(InteractionUseMapping object) {
            return createInteractionUseMappingAdapter();
        }

        @Override
        public Adapter caseCombinedFragmentMapping(CombinedFragmentMapping object) {
            return createCombinedFragmentMappingAdapter();
        }

        @Override
        public Adapter caseOperandMapping(OperandMapping object) {
            return createOperandMappingAdapter();
        }

        @Override
        public Adapter caseObservationPointMapping(ObservationPointMapping object) {
            return createObservationPointMappingAdapter();
        }

        @Override
        public Adapter caseInteractionContainerMapping(InteractionContainerMapping object) {
            return createInteractionContainerMappingAdapter();
        }

        @Override
        public Adapter caseGateMapping(GateMapping object) {
            return createGateMappingAdapter();
        }

        @Override
        public Adapter caseDragAndDropTargetDescription(DragAndDropTargetDescription object) {
            return createDragAndDropTargetDescriptionAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseEndUserDocumentedElement(EndUserDocumentedElement object) {
            return createEndUserDocumentedElementAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseRepresentationDescription(RepresentationDescription object) {
            return createRepresentationDescriptionAdapter();
        }

        @Override
        public Adapter casePasteTargetDescription(PasteTargetDescription object) {
            return createPasteTargetDescriptionAdapter();
        }

        @Override
        public Adapter caseDiagramDescription(DiagramDescription object) {
            return createDiagramDescriptionAdapter();
        }

        @Override
        public Adapter caseRepresentationElementMapping(RepresentationElementMapping object) {
            return createRepresentationElementMappingAdapter();
        }

        @Override
        public Adapter caseDiagramElementMapping(DiagramElementMapping object) {
            return createDiagramElementMappingAdapter();
        }

        @Override
        public Adapter caseAbstractNodeMapping(AbstractNodeMapping object) {
            return createAbstractNodeMappingAdapter();
        }

        @Override
        public Adapter caseNodeMapping(NodeMapping object) {
            return createNodeMappingAdapter();
        }

        @Override
        public Adapter caseIEdgeMapping(IEdgeMapping object) {
            return createIEdgeMappingAdapter();
        }

        @Override
        public Adapter caseEdgeMapping(EdgeMapping object) {
            return createEdgeMappingAdapter();
        }

        @Override
        public Adapter caseAbstractVariable(AbstractVariable object) {
            return createAbstractVariableAdapter();
        }

        @Override
        public Adapter caseContainerMapping(ContainerMapping object) {
            return createContainerMappingAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription <em>Sequence Diagram
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription
     * @generated
     */
    public Adapter createSequenceDiagramDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping <em>Instance Role Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping
     * @generated
     */
    public Adapter createInstanceRoleMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.description.EventMapping
     * <em>Event Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.EventMapping
     * @generated
     */
    public Adapter createEventMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping <em>Delimited Event Mapping</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping
     * @generated
     */
    public Adapter createDelimitedEventMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.ExecutionMapping <em>Execution Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.ExecutionMapping
     * @generated
     */
    public Adapter createExecutionMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.description.StateMapping
     * <em>State Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.StateMapping
     * @generated
     */
    public Adapter createStateMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping <em>End Of Life Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping
     * @generated
     */
    public Adapter createEndOfLifeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.MessageMapping <em>Message Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.MessageMapping
     * @generated
     */
    public Adapter createMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping <em>Basic Message Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping
     * @generated
     */
    public Adapter createBasicMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping <em>Return Message Mapping</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping
     * @generated
     */
    public Adapter createReturnMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping <em>Creation Message
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping
     * @generated
     */
    public Adapter createCreationMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping <em>Destruction Message
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping
     * @generated
     */
    public Adapter createDestructionMessageMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.MessageEndVariable <em>Message End Variable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.MessageEndVariable
     * @generated
     */
    public Adapter createMessageEndVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable <em>Covered Lifelines
     * Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable
     * @generated
     */
    public Adapter createCoveredLifelinesVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.description.FrameMapping
     * <em>Frame Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.FrameMapping
     * @generated
     */
    public Adapter createFrameMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping <em>Interaction Use Mapping</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping
     * @generated
     */
    public Adapter createInteractionUseMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping <em>Combined Fragment
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping
     * @generated
     */
    public Adapter createCombinedFragmentMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.diagram.sequence.description.OperandMapping <em>Operand Mapping</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.OperandMapping
     * @generated
     */
    public Adapter createOperandMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping <em>Observation Point
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping
     * @generated
     */
    public Adapter createObservationPointMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.sequence.description.InteractionContainerMapping <em>Interaction Container
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.InteractionContainerMapping
     * @generated
     */
    public Adapter createInteractionContainerMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.sequence.description.GateMapping
     * <em>Gate Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.sequence.description.GateMapping
     * @generated
     */
    public Adapter createGateMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement <em>End User Documented Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement
     * @generated
     */
    public Adapter createEndUserDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.diagram.description.DragAndDropTargetDescription <em>Drag And Drop Target
     * Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DragAndDropTargetDescription
     * @generated
     */
    public Adapter createDragAndDropTargetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationDescription <em>Representation Description</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationDescription
     * @generated
     */
    public Adapter createRepresentationDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.PasteTargetDescription <em>Paste Target Description</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.PasteTargetDescription
     * @generated
     */
    public Adapter createPasteTargetDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.DiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription
     * @generated
     */
    public Adapter createDiagramDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class
     * '{@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping <em>Representation Element
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.RepresentationElementMapping
     * @generated
     */
    public Adapter createRepresentationElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping
     * @generated
     */
    public Adapter createDiagramElementMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.AbstractNodeMapping
     * <em>Abstract Node Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.AbstractNodeMapping
     * @generated
     */
    public Adapter createAbstractNodeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.NodeMapping <em>Node
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.NodeMapping
     * @generated
     */
    public Adapter createNodeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.IEdgeMapping
     * <em>IEdge Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.IEdgeMapping
     * @generated
     */
    public Adapter createIEdgeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.EdgeMapping <em>Edge
     * Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping
     * @generated
     */
    public Adapter createEdgeMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.AbstractVariable
     * <em>Abstract Variable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.AbstractVariable
     * @generated
     */
    public Adapter createAbstractVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.description.ContainerMapping
     * <em>Container Mapping</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping
     * @generated
     */
    public Adapter createContainerMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DescriptionAdapterFactory
