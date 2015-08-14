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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping;
import org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable;
import org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping;
import org.eclipse.sirius.diagram.sequence.description.DescriptionFactory;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping;
import org.eclipse.sirius.diagram.sequence.description.EventMapping;
import org.eclipse.sirius.diagram.sequence.description.ExecutionMapping;
import org.eclipse.sirius.diagram.sequence.description.FrameMapping;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.diagram.sequence.description.MessageMapping;
import org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping;
import org.eclipse.sirius.diagram.sequence.description.OperandMapping;
import org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.diagram.sequence.description.StateMapping;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.diagram.sequence.impl.SequencePackageImpl;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingPackageImpl;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DescriptionPackageImpl extends EPackageImpl implements DescriptionPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sequenceDiagramDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass instanceRoleMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass eventMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass delimitedEventMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass executionMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stateMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass endOfLifeMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass messageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass basicMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass returnMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass creationMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass destructionMessageMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass messageEndVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass coveredLifelinesVariableEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass frameMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass interactionUseMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass combinedFragmentMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operandMappingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass observationPointMappingEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DescriptionPackageImpl() {
        super(DescriptionPackage.eNS_URI, DescriptionFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link DescriptionPackage#eINSTANCE}
     * when that field is accessed. Clients should not invoke it directly.
     * Instead, they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DescriptionPackage init() {
        if (DescriptionPackageImpl.isInited) {
            return (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        }

        // Obtain or create and register package
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.get(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .get(DescriptionPackage.eNS_URI) : new DescriptionPackageImpl());

        DescriptionPackageImpl.isInited = true;

        // Initialize simple dependencies
        DiagramPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        SequencePackageImpl theSequencePackage = (SequencePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI) instanceof SequencePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(SequencePackage.eNS_URI) : SequencePackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        OrderingPackageImpl theOrderingPackage = (OrderingPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI) instanceof OrderingPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(OrderingPackage.eNS_URI) : OrderingPackage.eINSTANCE);
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TemplatePackage.eNS_URI) : TemplatePackage.eINSTANCE);

        // Create package meta-data objects
        theDescriptionPackage.createPackageContents();
        theSequencePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theOrderingPackage.createPackageContents();
        theTemplatePackage.createPackageContents();

        // Initialize created meta-data
        theDescriptionPackage.initializePackageContents();
        theSequencePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theOrderingPackage.initializePackageContents();
        theTemplatePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDescriptionPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DescriptionPackage.eNS_URI, theDescriptionPackage);
        return theDescriptionPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSequenceDiagramDescription() {
        return sequenceDiagramDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSequenceDiagramDescription_EndsOrdering() {
        return (EAttribute) sequenceDiagramDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSequenceDiagramDescription_InstanceRolesOrdering() {
        return (EAttribute) sequenceDiagramDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInstanceRoleMapping() {
        return instanceRoleMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEventMapping() {
        return eventMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDelimitedEventMapping() {
        return delimitedEventMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDelimitedEventMapping_StartingEndFinderExpression() {
        return (EAttribute) delimitedEventMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDelimitedEventMapping_FinishingEndFinderExpression() {
        return (EAttribute) delimitedEventMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getExecutionMapping() {
        return executionMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStateMapping() {
        return stateMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEndOfLifeMapping() {
        return endOfLifeMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMessageMapping() {
        return messageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMessageMapping_SendingEndFinderExpression() {
        return (EAttribute) messageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getMessageMapping_ReceivingEndFinderExpression() {
        return (EAttribute) messageMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBasicMessageMapping() {
        return basicMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getReturnMessageMapping() {
        return returnMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getReturnMessageMapping_InvocationMessageFinderExpression() {
        return (EAttribute) returnMessageMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCreationMessageMapping() {
        return creationMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDestructionMessageMapping() {
        return destructionMessageMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMessageEndVariable() {
        return messageEndVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCoveredLifelinesVariable() {
        return coveredLifelinesVariableEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getFrameMapping() {
        return frameMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFrameMapping_CoveredLifelinesExpression() {
        return (EAttribute) frameMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getFrameMapping_CenterLabelExpression() {
        return (EAttribute) frameMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInteractionUseMapping() {
        return interactionUseMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCombinedFragmentMapping() {
        return combinedFragmentMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOperandMapping() {
        return operandMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getObservationPointMapping() {
        return observationPointMappingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public DescriptionFactory getDescriptionFactory() {
        return (DescriptionFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        sequenceDiagramDescriptionEClass = createEClass(DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION);
        createEAttribute(sequenceDiagramDescriptionEClass, DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING);
        createEAttribute(sequenceDiagramDescriptionEClass, DescriptionPackage.SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING);

        instanceRoleMappingEClass = createEClass(DescriptionPackage.INSTANCE_ROLE_MAPPING);

        eventMappingEClass = createEClass(DescriptionPackage.EVENT_MAPPING);

        delimitedEventMappingEClass = createEClass(DescriptionPackage.DELIMITED_EVENT_MAPPING);
        createEAttribute(delimitedEventMappingEClass, DescriptionPackage.DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION);
        createEAttribute(delimitedEventMappingEClass, DescriptionPackage.DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION);

        executionMappingEClass = createEClass(DescriptionPackage.EXECUTION_MAPPING);

        stateMappingEClass = createEClass(DescriptionPackage.STATE_MAPPING);

        endOfLifeMappingEClass = createEClass(DescriptionPackage.END_OF_LIFE_MAPPING);

        messageMappingEClass = createEClass(DescriptionPackage.MESSAGE_MAPPING);
        createEAttribute(messageMappingEClass, DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION);
        createEAttribute(messageMappingEClass, DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION);

        basicMessageMappingEClass = createEClass(DescriptionPackage.BASIC_MESSAGE_MAPPING);

        returnMessageMappingEClass = createEClass(DescriptionPackage.RETURN_MESSAGE_MAPPING);
        createEAttribute(returnMessageMappingEClass, DescriptionPackage.RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION);

        creationMessageMappingEClass = createEClass(DescriptionPackage.CREATION_MESSAGE_MAPPING);

        destructionMessageMappingEClass = createEClass(DescriptionPackage.DESTRUCTION_MESSAGE_MAPPING);

        messageEndVariableEClass = createEClass(DescriptionPackage.MESSAGE_END_VARIABLE);

        coveredLifelinesVariableEClass = createEClass(DescriptionPackage.COVERED_LIFELINES_VARIABLE);

        frameMappingEClass = createEClass(DescriptionPackage.FRAME_MAPPING);
        createEAttribute(frameMappingEClass, DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION);
        createEAttribute(frameMappingEClass, DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION);

        interactionUseMappingEClass = createEClass(DescriptionPackage.INTERACTION_USE_MAPPING);

        combinedFragmentMappingEClass = createEClass(DescriptionPackage.COMBINED_FRAGMENT_MAPPING);

        operandMappingEClass = createEClass(DescriptionPackage.OPERAND_MAPPING);

        observationPointMappingEClass = createEClass(DescriptionPackage.OBSERVATION_POINT_MAPPING);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(DescriptionPackage.eNAME);
        setNsPrefix(DescriptionPackage.eNS_PREFIX);
        setNsURI(DescriptionPackage.eNS_URI);

        // Obtain other dependent packages
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        org.eclipse.sirius.diagram.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_2 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.tool.ToolPackage theToolPackage_1 = (org.eclipse.sirius.viewpoint.description.tool.ToolPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theToolPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        sequenceDiagramDescriptionEClass.getESuperTypes().add(theDescriptionPackage_1.getDiagramDescription());
        instanceRoleMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getNodeMapping());
        delimitedEventMappingEClass.getESuperTypes().add(this.getEventMapping());
        executionMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getNodeMapping());
        executionMappingEClass.getESuperTypes().add(this.getDelimitedEventMapping());
        stateMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getNodeMapping());
        stateMappingEClass.getESuperTypes().add(this.getDelimitedEventMapping());
        endOfLifeMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getNodeMapping());
        messageMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getEdgeMapping());
        messageMappingEClass.getESuperTypes().add(this.getEventMapping());
        basicMessageMappingEClass.getESuperTypes().add(this.getMessageMapping());
        returnMessageMappingEClass.getESuperTypes().add(this.getMessageMapping());
        creationMessageMappingEClass.getESuperTypes().add(this.getMessageMapping());
        destructionMessageMappingEClass.getESuperTypes().add(this.getMessageMapping());
        messageEndVariableEClass.getESuperTypes().add(theToolPackage_1.getAbstractVariable());
        coveredLifelinesVariableEClass.getESuperTypes().add(theToolPackage_1.getAbstractVariable());
        frameMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getContainerMapping());
        frameMappingEClass.getESuperTypes().add(this.getDelimitedEventMapping());
        interactionUseMappingEClass.getESuperTypes().add(this.getFrameMapping());
        combinedFragmentMappingEClass.getESuperTypes().add(this.getFrameMapping());
        operandMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getContainerMapping());
        operandMappingEClass.getESuperTypes().add(this.getDelimitedEventMapping());
        observationPointMappingEClass.getESuperTypes().add(theDescriptionPackage_1.getNodeMapping());

        // Initialize classes and features; add operations and parameters
        initEClass(sequenceDiagramDescriptionEClass, SequenceDiagramDescription.class,
                "SequenceDiagramDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSequenceDiagramDescription_EndsOrdering(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "endsOrdering", null, 1, 1, SequenceDiagramDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getSequenceDiagramDescription_InstanceRolesOrdering(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "instanceRolesOrdering", null, 1, 1, SequenceDiagramDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(instanceRoleMappingEClass, InstanceRoleMapping.class, "InstanceRoleMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(eventMappingEClass, EventMapping.class, "EventMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(delimitedEventMappingEClass, DelimitedEventMapping.class, "DelimitedEventMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getDelimitedEventMapping_StartingEndFinderExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "startingEndFinderExpression", null, 1, 1, DelimitedEventMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getDelimitedEventMapping_FinishingEndFinderExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "finishingEndFinderExpression", null, 1, 1, DelimitedEventMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(executionMappingEClass, ExecutionMapping.class, "ExecutionMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(stateMappingEClass, StateMapping.class, "StateMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(endOfLifeMappingEClass, EndOfLifeMapping.class, "EndOfLifeMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(messageMappingEClass, MessageMapping.class, "MessageMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getMessageMapping_SendingEndFinderExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "sendingEndFinderExpression", null, 1, 1, MessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getMessageMapping_ReceivingEndFinderExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "receivingEndFinderExpression", null, 1, 1, MessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(basicMessageMappingEClass, BasicMessageMapping.class, "BasicMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(returnMessageMappingEClass, ReturnMessageMapping.class, "ReturnMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getReturnMessageMapping_InvocationMessageFinderExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "invocationMessageFinderExpression", null, 1, 1, ReturnMessageMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(creationMessageMappingEClass, CreationMessageMapping.class,
                "CreationMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(destructionMessageMappingEClass, DestructionMessageMapping.class,
                "DestructionMessageMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(messageEndVariableEClass, MessageEndVariable.class, "MessageEndVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(coveredLifelinesVariableEClass, CoveredLifelinesVariable.class,
                "CoveredLifelinesVariable", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(frameMappingEClass, FrameMapping.class, "FrameMapping", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getFrameMapping_CoveredLifelinesExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "coveredLifelinesExpression", null, 1, 1, FrameMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEAttribute(
                getFrameMapping_CenterLabelExpression(),
                theDescriptionPackage_2.getInterpretedExpression(),
                "centerLabelExpression", null, 0, 1, FrameMapping.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(interactionUseMappingEClass, InteractionUseMapping.class, "InteractionUseMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(combinedFragmentMappingEClass, CombinedFragmentMapping.class,
                "CombinedFragmentMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(operandMappingEClass, OperandMapping.class, "OperandMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(observationPointMappingEClass, ObservationPointMapping.class,
                "ObservationPointMapping", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getSequenceDiagramDescription_EndsOrdering(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSequenceDiagramDescription_InstanceRolesOrdering(), source, new String[] { "returnType", "a Collection<EObject> or an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDelimitedEventMapping_StartingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDelimitedEventMapping_FinishingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getMessageMapping_SendingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getMessageMapping_ReceivingEndFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getReturnMessageMapping_InvocationMessageFinderExpression(), source, new String[] { "returnType", "an EObject." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFrameMapping_CoveredLifelinesExpression(), source, new String[] {
            "returnType", "the list of semantic EObjects representing the lifelines which are semantically covered by the frame." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getFrameMapping_CenterLabelExpression(), source, new String[] { "returnType", "the text to show in the center of the IU" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getSequenceDiagramDescription_EndsOrdering(), source, new String[] { "eventEnds", "a List<EObject> containing the semantic event ends." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSequenceDiagramDescription_InstanceRolesOrdering(), source, new String[] {});
        addAnnotation(getDelimitedEventMapping_StartingEndFinderExpression(), source, new String[] {});
        addAnnotation(getDelimitedEventMapping_FinishingEndFinderExpression(), source, new String[] {});
        addAnnotation(getMessageMapping_SendingEndFinderExpression(), source, new String[] {});
        addAnnotation(getMessageMapping_ReceivingEndFinderExpression(), source, new String[] {});
        addAnnotation(getReturnMessageMapping_InvocationMessageFinderExpression(), source, new String[] {});
        addAnnotation(getFrameMapping_CoveredLifelinesExpression(), source, new String[] {});
        addAnnotation(getFrameMapping_CenterLabelExpression(), source, new String[] {});
    }

} // DescriptionPackageImpl
