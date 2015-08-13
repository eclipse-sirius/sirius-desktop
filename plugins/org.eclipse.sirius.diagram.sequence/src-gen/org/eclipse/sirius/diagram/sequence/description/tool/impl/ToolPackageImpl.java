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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool;
import org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool;
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
import org.eclipse.sirius.diagram.sequence.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
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
public class ToolPackageImpl extends EPackageImpl implements ToolPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sequenceDiagramToolDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass orderedElementCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass coveringElementCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass instanceRoleCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass lifelineCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass messageCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass executionCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass stateCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass reorderToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass instanceRoleReorderToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass observationPointCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass interactionUseCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass combinedFragmentCreationToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass operandCreationToolEClass = null;

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
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ToolPackageImpl() {
        super(ToolPackage.eNS_URI, ToolFactory.eINSTANCE);
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
     * This method is used to initialize {@link ToolPackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they
     * should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ToolPackage init() {
        if (ToolPackageImpl.isInited) {
            return (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        }

        // Obtain or create and register package
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.get(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE.get(ToolPackage.eNS_URI)
                : new ToolPackageImpl());

        ToolPackageImpl.isInited = true;

        // Initialize simple dependencies
        DiagramPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        SequencePackageImpl theSequencePackage = (SequencePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI) instanceof SequencePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(SequencePackage.eNS_URI) : SequencePackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        OrderingPackageImpl theOrderingPackage = (OrderingPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI) instanceof OrderingPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(OrderingPackage.eNS_URI) : OrderingPackage.eINSTANCE);
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TemplatePackage.eNS_URI) : TemplatePackage.eINSTANCE);

        // Create package meta-data objects
        theToolPackage.createPackageContents();
        theSequencePackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theOrderingPackage.createPackageContents();
        theTemplatePackage.createPackageContents();

        // Initialize created meta-data
        theToolPackage.initializePackageContents();
        theSequencePackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theOrderingPackage.initializePackageContents();
        theTemplatePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theToolPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ToolPackage.eNS_URI, theToolPackage);
        return theToolPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSequenceDiagramToolDescription() {
        return sequenceDiagramToolDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOrderedElementCreationTool() {
        return orderedElementCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getOrderedElementCreationTool_StartingEndPredecessor() {
        return (EReference) orderedElementCreationToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getOrderedElementCreationTool_FinishingEndPredecessor() {
        return (EReference) orderedElementCreationToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCoveringElementCreationTool() {
        return coveringElementCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCoveringElementCreationTool_CoveredLifelines() {
        return (EReference) coveringElementCreationToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInstanceRoleCreationTool() {
        return instanceRoleCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInstanceRoleCreationTool_Predecessor() {
        return (EReference) instanceRoleCreationToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLifelineCreationTool() {
        return lifelineCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getMessageCreationTool() {
        return messageCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getExecutionCreationTool() {
        return executionCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStateCreationTool() {
        return stateCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getReorderTool() {
        return reorderToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getReorderTool_Mappings() {
        return (EReference) reorderToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getReorderTool_StartingEndPredecessorBefore() {
        return (EReference) reorderToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getReorderTool_StartingEndPredecessorAfter() {
        return (EReference) reorderToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getReorderTool_FinishingEndPredecessorBefore() {
        return (EReference) reorderToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getReorderTool_FinishingEndPredecessorAfter() {
        return (EReference) reorderToolEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getReorderTool_OnEventMovedOperation() {
        return (EReference) reorderToolEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInstanceRoleReorderTool() {
        return instanceRoleReorderToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInstanceRoleReorderTool_Mappings() {
        return (EReference) instanceRoleReorderToolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInstanceRoleReorderTool_PredecessorBefore() {
        return (EReference) instanceRoleReorderToolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInstanceRoleReorderTool_PredecessorAfter() {
        return (EReference) instanceRoleReorderToolEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInstanceRoleReorderTool_InstanceRoleMoved() {
        return (EReference) instanceRoleReorderToolEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getObservationPointCreationTool() {
        return observationPointCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInteractionUseCreationTool() {
        return interactionUseCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCombinedFragmentCreationTool() {
        return combinedFragmentCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOperandCreationTool() {
        return operandCreationToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ToolFactory getToolFactory() {
        return (ToolFactory) getEFactoryInstance();
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
        sequenceDiagramToolDescriptionEClass = createEClass(ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION);

        orderedElementCreationToolEClass = createEClass(ToolPackage.ORDERED_ELEMENT_CREATION_TOOL);
        createEReference(orderedElementCreationToolEClass, ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR);
        createEReference(orderedElementCreationToolEClass, ToolPackage.ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR);

        coveringElementCreationToolEClass = createEClass(ToolPackage.COVERING_ELEMENT_CREATION_TOOL);
        createEReference(coveringElementCreationToolEClass, ToolPackage.COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES);

        instanceRoleCreationToolEClass = createEClass(ToolPackage.INSTANCE_ROLE_CREATION_TOOL);
        createEReference(instanceRoleCreationToolEClass, ToolPackage.INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR);

        lifelineCreationToolEClass = createEClass(ToolPackage.LIFELINE_CREATION_TOOL);

        messageCreationToolEClass = createEClass(ToolPackage.MESSAGE_CREATION_TOOL);

        executionCreationToolEClass = createEClass(ToolPackage.EXECUTION_CREATION_TOOL);

        stateCreationToolEClass = createEClass(ToolPackage.STATE_CREATION_TOOL);

        interactionUseCreationToolEClass = createEClass(ToolPackage.INTERACTION_USE_CREATION_TOOL);

        combinedFragmentCreationToolEClass = createEClass(ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL);

        operandCreationToolEClass = createEClass(ToolPackage.OPERAND_CREATION_TOOL);

        observationPointCreationToolEClass = createEClass(ToolPackage.OBSERVATION_POINT_CREATION_TOOL);

        reorderToolEClass = createEClass(ToolPackage.REORDER_TOOL);
        createEReference(reorderToolEClass, ToolPackage.REORDER_TOOL__MAPPINGS);
        createEReference(reorderToolEClass, ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE);
        createEReference(reorderToolEClass, ToolPackage.REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER);
        createEReference(reorderToolEClass, ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE);
        createEReference(reorderToolEClass, ToolPackage.REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER);
        createEReference(reorderToolEClass, ToolPackage.REORDER_TOOL__ON_EVENT_MOVED_OPERATION);

        instanceRoleReorderToolEClass = createEClass(ToolPackage.INSTANCE_ROLE_REORDER_TOOL);
        createEReference(instanceRoleReorderToolEClass, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__MAPPINGS);
        createEReference(instanceRoleReorderToolEClass, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE);
        createEReference(instanceRoleReorderToolEClass, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER);
        createEReference(instanceRoleReorderToolEClass, ToolPackage.INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED);
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
        setName(ToolPackage.eNAME);
        setNsPrefix(ToolPackage.eNS_PREFIX);
        setNsURI(ToolPackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        org.eclipse.sirius.diagram.description.tool.ToolPackage theToolPackage_1 = (org.eclipse.sirius.diagram.description.tool.ToolPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.tool.ToolPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.tool.ToolPackage theToolPackage_2 = (org.eclipse.sirius.viewpoint.description.tool.ToolPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        instanceRoleCreationToolEClass.getESuperTypes().add(theToolPackage_1.getNodeCreationDescription());
        instanceRoleCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        lifelineCreationToolEClass.getESuperTypes().add(theToolPackage_1.getContainerCreationDescription());
        lifelineCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        messageCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        messageCreationToolEClass.getESuperTypes().add(theToolPackage_1.getEdgeCreationDescription());
        messageCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        executionCreationToolEClass.getESuperTypes().add(theToolPackage_1.getNodeCreationDescription());
        executionCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        executionCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        stateCreationToolEClass.getESuperTypes().add(theToolPackage_1.getNodeCreationDescription());
        stateCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        stateCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        interactionUseCreationToolEClass.getESuperTypes().add(theToolPackage_1.getContainerCreationDescription());
        interactionUseCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        interactionUseCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        interactionUseCreationToolEClass.getESuperTypes().add(this.getCoveringElementCreationTool());
        combinedFragmentCreationToolEClass.getESuperTypes().add(theToolPackage_1.getContainerCreationDescription());
        combinedFragmentCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        combinedFragmentCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        combinedFragmentCreationToolEClass.getESuperTypes().add(this.getCoveringElementCreationTool());
        operandCreationToolEClass.getESuperTypes().add(theToolPackage_1.getContainerCreationDescription());
        operandCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        operandCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        observationPointCreationToolEClass.getESuperTypes().add(theToolPackage_1.getNodeCreationDescription());
        observationPointCreationToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        observationPointCreationToolEClass.getESuperTypes().add(this.getOrderedElementCreationTool());
        reorderToolEClass.getESuperTypes().add(theToolPackage_2.getAbstractToolDescription());
        reorderToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());
        instanceRoleReorderToolEClass.getESuperTypes().add(theToolPackage_2.getAbstractToolDescription());
        instanceRoleReorderToolEClass.getESuperTypes().add(this.getSequenceDiagramToolDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(sequenceDiagramToolDescriptionEClass, SequenceDiagramToolDescription.class,
                "SequenceDiagramToolDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(orderedElementCreationToolEClass, OrderedElementCreationTool.class,
                "OrderedElementCreationTool", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getOrderedElementCreationTool_StartingEndPredecessor(),
                theDescriptionPackage.getMessageEndVariable(),
                null,
                "startingEndPredecessor", null, 0, 1, OrderedElementCreationTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getOrderedElementCreationTool_FinishingEndPredecessor(),
                theDescriptionPackage.getMessageEndVariable(),
                null,
                "finishingEndPredecessor", null, 0, 1, OrderedElementCreationTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(coveringElementCreationToolEClass, CoveringElementCreationTool.class,
                "CoveringElementCreationTool", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCoveringElementCreationTool_CoveredLifelines(),
                theDescriptionPackage.getCoveredLifelinesVariable(),
                null,
                "coveredLifelines", null, 1, 1, CoveringElementCreationTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(instanceRoleCreationToolEClass, InstanceRoleCreationTool.class,
                "InstanceRoleCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInstanceRoleCreationTool_Predecessor(),
                theToolPackage_2.getElementVariable(),
                null,
                "predecessor", null, 0, 1, InstanceRoleCreationTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(lifelineCreationToolEClass, LifelineCreationTool.class, "LifelineCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(messageCreationToolEClass, MessageCreationTool.class, "MessageCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(executionCreationToolEClass, ExecutionCreationTool.class, "ExecutionCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(stateCreationToolEClass, StateCreationTool.class, "StateCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(interactionUseCreationToolEClass, InteractionUseCreationTool.class,
                "InteractionUseCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(combinedFragmentCreationToolEClass, CombinedFragmentCreationTool.class,
                "CombinedFragmentCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(operandCreationToolEClass, OperandCreationTool.class, "OperandCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(observationPointCreationToolEClass, ObservationPointCreationTool.class,
                "ObservationPointCreationTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(reorderToolEClass, ReorderTool.class, "ReorderTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getReorderTool_Mappings(),
                theDescriptionPackage.getEventMapping(),
                null,
                "mappings", null, 0, -1, ReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getReorderTool_StartingEndPredecessorBefore(),
                theDescriptionPackage.getMessageEndVariable(),
                null,
                "startingEndPredecessorBefore", null, 0, 1, ReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getReorderTool_StartingEndPredecessorAfter(),
                theDescriptionPackage.getMessageEndVariable(),
                null,
                "startingEndPredecessorAfter", null, 0, 1, ReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getReorderTool_FinishingEndPredecessorBefore(),
                theDescriptionPackage.getMessageEndVariable(),
                null,
                "finishingEndPredecessorBefore", null, 0, 1, ReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getReorderTool_FinishingEndPredecessorAfter(),
                theDescriptionPackage.getMessageEndVariable(),
                null,
                "finishingEndPredecessorAfter", null, 0, 1, ReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getReorderTool_OnEventMovedOperation(),
                theToolPackage_2.getInitialOperation(),
                null,
                "onEventMovedOperation", null, 1, 1, ReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(instanceRoleReorderToolEClass, InstanceRoleReorderTool.class,
                "InstanceRoleReorderTool", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInstanceRoleReorderTool_Mappings(),
                theDescriptionPackage.getInstanceRoleMapping(),
                null,
                "mappings", null, 0, -1, InstanceRoleReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getInstanceRoleReorderTool_PredecessorBefore(),
                theToolPackage_2.getElementVariable(),
                null,
                "predecessorBefore", null, 0, 1, InstanceRoleReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getInstanceRoleReorderTool_PredecessorAfter(),
                theToolPackage_2.getElementVariable(),
                null,
                "predecessorAfter", null, 0, 1, InstanceRoleReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getInstanceRoleReorderTool_InstanceRoleMoved(),
                theToolPackage_2.getInitialOperation(),
                null,
                "instanceRoleMoved", null, 1, 1, InstanceRoleReorderTool.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Create annotations
        // toolVariable
        createToolVariableAnnotations();
    }

    /**
     * Initializes the annotations for <b>toolVariable</b>. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createToolVariableAnnotations() {
        String source = "toolVariable"; //$NON-NLS-1$
        addAnnotation(getOrderedElementCreationTool_StartingEndPredecessor(), source, new String[] { "name", "startingEndPredecessor" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getOrderedElementCreationTool_FinishingEndPredecessor(), source, new String[] { "name", "finishingEndPredecessor" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInstanceRoleCreationTool_Predecessor(), source, new String[] { "name", "predecessor" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getReorderTool_StartingEndPredecessorBefore(), source, new String[] { "name", "startingEndPredecessorBefore" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getReorderTool_StartingEndPredecessorAfter(), source, new String[] { "name", "startingEndPredecessorAfter" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getReorderTool_FinishingEndPredecessorBefore(), source, new String[] { "name", "finishingEndPredecessorBefore" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getReorderTool_FinishingEndPredecessorAfter(), source, new String[] { "name", "finishingEndPredecessorAfter" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInstanceRoleReorderTool_PredecessorBefore(), source, new String[] { "name", "predecessorBefore" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getInstanceRoleReorderTool_PredecessorAfter(), source, new String[] { "name", "predecessorAfter" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // ToolPackageImpl
