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
package org.eclipse.sirius.diagram.sequence.ordering.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.diagram.sequence.impl.SequencePackageImpl;
import org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEnd;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingFactory;
import org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage;
import org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd;
import org.eclipse.sirius.diagram.sequence.template.TemplatePackage;
import org.eclipse.sirius.diagram.sequence.template.impl.TemplatePackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class OrderingPackageImpl extends EPackageImpl implements OrderingPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass eventEndsOrderingEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass eventEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass singleEventEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass compoundEventEndEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass instanceRolesOrderingEClass = null;

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
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private OrderingPackageImpl() {
        super(OrderingPackage.eNS_URI, OrderingFactory.eINSTANCE);
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
     * This method is used to initialize {@link OrderingPackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static OrderingPackage init() {
        if (OrderingPackageImpl.isInited) {
            return (OrderingPackage) EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI);
        }

        // Obtain or create and register package
        OrderingPackageImpl theOrderingPackage = (OrderingPackageImpl) (EPackage.Registry.INSTANCE.get(OrderingPackage.eNS_URI) instanceof OrderingPackageImpl ? EPackage.Registry.INSTANCE
                .get(OrderingPackage.eNS_URI) : new OrderingPackageImpl());

        OrderingPackageImpl.isInited = true;

        // Initialize simple dependencies
        DiagramPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        SequencePackageImpl theSequencePackage = (SequencePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI) instanceof SequencePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(SequencePackage.eNS_URI) : SequencePackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TemplatePackage.eNS_URI) : TemplatePackage.eINSTANCE);

        // Create package meta-data objects
        theOrderingPackage.createPackageContents();
        theSequencePackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theToolPackage.createPackageContents();
        theTemplatePackage.createPackageContents();

        // Initialize created meta-data
        theOrderingPackage.initializePackageContents();
        theSequencePackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theTemplatePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theOrderingPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(OrderingPackage.eNS_URI, theOrderingPackage);
        return theOrderingPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEventEndsOrdering() {
        return eventEndsOrderingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEventEndsOrdering_SequenceDiagram() {
        return (EReference) eventEndsOrderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEventEndsOrdering_EventEnds() {
        return (EReference) eventEndsOrderingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getEventEnd() {
        return eventEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getEventEnd_SemanticEnd() {
        return (EReference) eventEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSingleEventEnd() {
        return singleEventEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getSingleEventEnd_Start() {
        return (EAttribute) singleEventEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSingleEventEnd_SemanticEvent() {
        return (EReference) singleEventEndEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCompoundEventEnd() {
        return compoundEventEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCompoundEventEnd_EventEnds() {
        return (EReference) compoundEventEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getInstanceRolesOrdering() {
        return instanceRolesOrderingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getInstanceRolesOrdering_SemanticInstanceRoles() {
        return (EReference) instanceRolesOrderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public OrderingFactory getOrderingFactory() {
        return (OrderingFactory) getEFactoryInstance();
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
        eventEndsOrderingEClass = createEClass(OrderingPackage.EVENT_ENDS_ORDERING);
        createEReference(eventEndsOrderingEClass, OrderingPackage.EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM);
        createEReference(eventEndsOrderingEClass, OrderingPackage.EVENT_ENDS_ORDERING__EVENT_ENDS);

        eventEndEClass = createEClass(OrderingPackage.EVENT_END);
        createEReference(eventEndEClass, OrderingPackage.EVENT_END__SEMANTIC_END);

        singleEventEndEClass = createEClass(OrderingPackage.SINGLE_EVENT_END);
        createEAttribute(singleEventEndEClass, OrderingPackage.SINGLE_EVENT_END__START);
        createEReference(singleEventEndEClass, OrderingPackage.SINGLE_EVENT_END__SEMANTIC_EVENT);

        compoundEventEndEClass = createEClass(OrderingPackage.COMPOUND_EVENT_END);
        createEReference(compoundEventEndEClass, OrderingPackage.COMPOUND_EVENT_END__EVENT_ENDS);

        instanceRolesOrderingEClass = createEClass(OrderingPackage.INSTANCE_ROLES_ORDERING);
        createEReference(instanceRolesOrderingEClass, OrderingPackage.INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES);
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
        setName(OrderingPackage.eNAME);
        setNsPrefix(OrderingPackage.eNS_PREFIX);
        setNsURI(OrderingPackage.eNS_URI);

        // Obtain other dependent packages
        SequencePackage theSequencePackage = (SequencePackage) EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        singleEventEndEClass.getESuperTypes().add(this.getEventEnd());
        compoundEventEndEClass.getESuperTypes().add(this.getEventEnd());

        // Initialize classes and features; add operations and parameters
        initEClass(eventEndsOrderingEClass, EventEndsOrdering.class, "EventEndsOrdering", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getEventEndsOrdering_SequenceDiagram(),
                theSequencePackage.getSequenceDDiagram(),
                null,
                "sequenceDiagram", null, 0, 1, EventEndsOrdering.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getEventEndsOrdering_EventEnds(),
                this.getEventEnd(),
                null,
                "eventEnds", null, 0, -1, EventEndsOrdering.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(eventEndEClass, EventEnd.class, "EventEnd", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getEventEnd_SemanticEnd(),
                theEcorePackage.getEObject(),
                null,
                "semanticEnd", null, 1, 1, EventEnd.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(singleEventEndEClass, SingleEventEnd.class, "SingleEventEnd", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(
                getSingleEventEnd_Start(),
                theEcorePackage.getEBoolean(),
                "start", null, 1, 1, SingleEventEnd.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSingleEventEnd_SemanticEvent(),
                theEcorePackage.getEObject(),
                null,
                "semanticEvent", null, 1, 1, SingleEventEnd.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(compoundEventEndEClass, CompoundEventEnd.class, "CompoundEventEnd", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getCompoundEventEnd_EventEnds(),
                this.getSingleEventEnd(),
                null,
                "eventEnds", null, 1, -1, CompoundEventEnd.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        addEOperation(compoundEventEndEClass, theEcorePackage.getEObject(), "getSemanticEvents", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(instanceRolesOrderingEClass, InstanceRolesOrdering.class, "InstanceRolesOrdering", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getInstanceRolesOrdering_SemanticInstanceRoles(),
                theEcorePackage.getEObject(),
                null,
                "semanticInstanceRoles", null, 0, -1, InstanceRolesOrdering.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
    }

} // OrderingPackageImpl
