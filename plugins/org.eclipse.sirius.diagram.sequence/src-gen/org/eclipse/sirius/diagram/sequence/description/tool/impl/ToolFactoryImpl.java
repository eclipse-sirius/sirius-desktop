/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence.description.tool.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.tool.MessageCreationToolSpec;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.LifelineCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool;
import org.eclipse.sirius.diagram.sequence.description.tool.SequenceDiagramToolDescription;
import org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ToolFactoryImpl extends EFactoryImpl implements ToolFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static ToolFactory init() {
        try {
            ToolFactory theToolFactory = (ToolFactory) EPackage.Registry.INSTANCE.getEFactory(ToolPackage.eNS_URI);
            if (theToolFactory != null) {
                return theToolFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ToolFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ToolFactoryImpl() {
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
        case ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION:
            return createSequenceDiagramToolDescription();
        case ToolPackage.INSTANCE_ROLE_CREATION_TOOL:
            return createInstanceRoleCreationTool();
        case ToolPackage.LIFELINE_CREATION_TOOL:
            return createLifelineCreationTool();
        case ToolPackage.MESSAGE_CREATION_TOOL:
            return createMessageCreationTool();
        case ToolPackage.EXECUTION_CREATION_TOOL:
            return createExecutionCreationTool();
        case ToolPackage.STATE_CREATION_TOOL:
            return createStateCreationTool();
        case ToolPackage.INTERACTION_USE_CREATION_TOOL:
            return createInteractionUseCreationTool();
        case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL:
            return createCombinedFragmentCreationTool();
        case ToolPackage.OPERAND_CREATION_TOOL:
            return createOperandCreationTool();
        case ToolPackage.OBSERVATION_POINT_CREATION_TOOL:
            return createObservationPointCreationTool();
        case ToolPackage.REORDER_TOOL:
            return createReorderTool();
        case ToolPackage.INSTANCE_ROLE_REORDER_TOOL:
            return createInstanceRoleReorderTool();
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
    public SequenceDiagramToolDescription createSequenceDiagramToolDescription() {
        SequenceDiagramToolDescriptionImpl sequenceDiagramToolDescription = new SequenceDiagramToolDescriptionImpl();
        return sequenceDiagramToolDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @noy-generated
     */
    @Override
    public InstanceRoleCreationTool createInstanceRoleCreationTool() {
        InstanceRoleCreationToolImpl instanceRoleCreationTool = new InstanceRoleCreationToolImpl();
        final InitialNodeCreationOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        instanceRoleCreationTool.setInitialOperation(initialOperation);
        return instanceRoleCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public LifelineCreationTool createLifelineCreationTool() {
        LifelineCreationToolImpl lifelineCreationTool = new LifelineCreationToolImpl();
        return lifelineCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated Create a MessageCreationToolSpec instead.
     */
    @Override
    public MessageCreationTool createMessageCreationTool() {
        MessageCreationToolImpl messageCreationTool = new MessageCreationToolSpec();
        final InitEdgeCreationOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitEdgeCreationOperation();
        messageCreationTool.setInitialOperation(initialOperation);
        return messageCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ExecutionCreationTool createExecutionCreationTool() {
        ExecutionCreationToolImpl executionCreationTool = new ExecutionCreationToolImpl();
        final InitialNodeCreationOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        executionCreationTool.setInitialOperation(initialOperation);
        return executionCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public StateCreationTool createStateCreationTool() {
        StateCreationToolImpl stateCreationTool = new StateCreationToolImpl();
        final InitialNodeCreationOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        stateCreationTool.setInitialOperation(initialOperation);
        return stateCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ReorderTool createReorderTool() {
        ReorderToolImpl reorderTool = new ReorderToolImpl();
        InitialOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        reorderTool.setOnEventMovedOperation(initialOperation);
        return reorderTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public InstanceRoleReorderTool createInstanceRoleReorderTool() {
        InstanceRoleReorderToolImpl instanceRoleReorderTool = new InstanceRoleReorderToolImpl();
        InitialOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialOperation();
        instanceRoleReorderTool.setInstanceRoleMoved(initialOperation);
        return instanceRoleReorderTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ObservationPointCreationTool createObservationPointCreationTool() {
        ObservationPointCreationToolImpl observationPointCreationTool = new ObservationPointCreationToolImpl();
        return observationPointCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public InteractionUseCreationTool createInteractionUseCreationTool() {
        InteractionUseCreationToolImpl interactionUseCreationTool = new InteractionUseCreationToolImpl();
        InitialNodeCreationOperation initalOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        interactionUseCreationTool.setInitialOperation(initalOperation);
        return interactionUseCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CombinedFragmentCreationTool createCombinedFragmentCreationTool() {
        CombinedFragmentCreationToolImpl combinedFragmentCreationTool = new CombinedFragmentCreationToolImpl();
        InitialNodeCreationOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        combinedFragmentCreationTool.setInitialOperation(initialOperation);
        return combinedFragmentCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public OperandCreationTool createOperandCreationTool() {
        OperandCreationToolImpl operandCreationTool = new OperandCreationToolImpl();
        InitialNodeCreationOperation initialOperation = org.eclipse.sirius.viewpoint.description.tool.ToolFactory.eINSTANCE.createInitialNodeCreationOperation();
        operandCreationTool.setInitialOperation(initialOperation);
        return operandCreationTool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ToolPackage getToolPackage() {
        return (ToolPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ToolPackage getPackage() {
        return ToolPackage.eINSTANCE;
    }

} // ToolFactoryImpl
