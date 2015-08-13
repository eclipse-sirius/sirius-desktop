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
package org.eclipse.sirius.diagram.sequence.description.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.BasicMessageMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.CombinedFragmentMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.CreationMessageMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.DestructionMessageMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.EndOfLifeMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.ExecutionMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.InstanceRoleMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.InteractionUseMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.ObservationPointMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.OperandMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.ReturnMessageMappingSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.SequenceDiagramDescriptionSpec;
import org.eclipse.sirius.diagram.sequence.business.internal.metamodel.description.StateMappingSpec;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping;
import org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable;
import org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping;
import org.eclipse.sirius.diagram.sequence.description.OperandMapping;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.description.StateMapping;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionFactoryImpl extends EFactoryImpl implements DescriptionFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static DescriptionFactory init() {
        try {
            DescriptionFactory theDescriptionFactory = (DescriptionFactory) EPackage.Registry.INSTANCE.getEFactory(DescriptionPackage.eNS_URI);
            if (theDescriptionFactory != null) {
                return theDescriptionFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DescriptionFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DescriptionFactoryImpl() {
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
        case DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION:
            return createSequenceDiagramDescription();
        case DescriptionPackage.INSTANCE_ROLE_MAPPING:
            return createInstanceRoleMapping();
        case DescriptionPackage.EXECUTION_MAPPING:
            return createExecutionMapping();
        case DescriptionPackage.STATE_MAPPING:
            return createStateMapping();
        case DescriptionPackage.END_OF_LIFE_MAPPING:
            return createEndOfLifeMapping();
        case DescriptionPackage.BASIC_MESSAGE_MAPPING:
            return createBasicMessageMapping();
        case DescriptionPackage.RETURN_MESSAGE_MAPPING:
            return createReturnMessageMapping();
        case DescriptionPackage.CREATION_MESSAGE_MAPPING:
            return createCreationMessageMapping();
        case DescriptionPackage.DESTRUCTION_MESSAGE_MAPPING:
            return createDestructionMessageMapping();
        case DescriptionPackage.MESSAGE_END_VARIABLE:
            return createMessageEndVariable();
        case DescriptionPackage.COVERED_LIFELINES_VARIABLE:
            return createCoveredLifelinesVariable();
        case DescriptionPackage.INTERACTION_USE_MAPPING:
            return createInteractionUseMapping();
        case DescriptionPackage.COMBINED_FRAGMENT_MAPPING:
            return createCombinedFragmentMapping();
        case DescriptionPackage.OPERAND_MAPPING:
            return createOperandMapping();
        case DescriptionPackage.OBSERVATION_POINT_MAPPING:
            return createObservationPointMapping();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public SequenceDiagramDescription createSequenceDiagramDescription() {
        SequenceDiagramDescriptionSpec sequenceDiagramDescription = new SequenceDiagramDescriptionSpec();
        return sequenceDiagramDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public InstanceRoleMapping createInstanceRoleMapping() {
        InstanceRoleMappingSpec instanceRoleMapping = new InstanceRoleMappingSpec();
        return instanceRoleMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ExecutionMapping createExecutionMapping() {
        ExecutionMappingSpec executionMapping = new ExecutionMappingSpec();
        return executionMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public StateMapping createStateMapping() {
        StateMappingSpec stateMapping = new StateMappingSpec();
        return stateMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public EndOfLifeMapping createEndOfLifeMapping() {
        EndOfLifeMappingSpec endOfLifeMapping = new EndOfLifeMappingSpec();
        return endOfLifeMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public BasicMessageMapping createBasicMessageMapping() {
        BasicMessageMappingSpec basicMessageMapping = new BasicMessageMappingSpec();
        return basicMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ReturnMessageMapping createReturnMessageMapping() {
        ReturnMessageMappingSpec returnMessageMapping = new ReturnMessageMappingSpec();
        return returnMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CreationMessageMapping createCreationMessageMapping() {
        CreationMessageMappingSpec creationMessageMapping = new CreationMessageMappingSpec();
        return creationMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DestructionMessageMapping createDestructionMessageMapping() {
        DestructionMessageMappingSpec destructionMessageMapping = new DestructionMessageMappingSpec();
        return destructionMessageMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MessageEndVariable createMessageEndVariable() {
        MessageEndVariableImpl messageEndVariable = new MessageEndVariableImpl();
        return messageEndVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CoveredLifelinesVariable createCoveredLifelinesVariable() {
        CoveredLifelinesVariableImpl coveredLifelinesVariable = new CoveredLifelinesVariableImpl();
        return coveredLifelinesVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public InteractionUseMapping createInteractionUseMapping() {
        InteractionUseMapping interactionUseMapping = new InteractionUseMappingSpec();
        return interactionUseMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public CombinedFragmentMapping createCombinedFragmentMapping() {
        CombinedFragmentMapping combinedFragmentMapping = new CombinedFragmentMappingSpec();
        return combinedFragmentMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public OperandMapping createOperandMapping() {
        OperandMappingImpl operandMapping = new OperandMappingSpec();
        return operandMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public ObservationPointMapping createObservationPointMapping() {
        ObservationPointMappingImpl observationPointMapping = new ObservationPointMappingSpec();
        return observationPointMapping;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DescriptionPackage getDescriptionPackage() {
        return (DescriptionPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DescriptionPackage getPackage() {
        return DescriptionPackage.eINSTANCE;
    }

} // DescriptionFactoryImpl
