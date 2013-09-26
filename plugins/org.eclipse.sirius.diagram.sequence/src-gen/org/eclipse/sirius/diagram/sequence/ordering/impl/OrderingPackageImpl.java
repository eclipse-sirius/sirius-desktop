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
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
        super(eNS_URI, OrderingFactory.eINSTANCE);
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
        if (isInited)
            return (OrderingPackage) EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI);

        // Obtain or create and register package
        OrderingPackageImpl theOrderingPackage = (OrderingPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof OrderingPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                : new OrderingPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ViewpointPackage.eINSTANCE.eClass();

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
    public EClass getEventEndsOrdering() {
        return eventEndsOrderingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEventEndsOrdering_SequenceDiagram() {
        return (EReference) eventEndsOrderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEventEndsOrdering_EventEnds() {
        return (EReference) eventEndsOrderingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEventEnd() {
        return eventEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEventEnd_SemanticEnd() {
        return (EReference) eventEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSingleEventEnd() {
        return singleEventEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSingleEventEnd_Start() {
        return (EAttribute) singleEventEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSingleEventEnd_SemanticEvent() {
        return (EReference) singleEventEndEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCompoundEventEnd() {
        return compoundEventEndEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getCompoundEventEnd_EventEnds() {
        return (EReference) compoundEventEndEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getInstanceRolesOrdering() {
        return instanceRolesOrderingEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getInstanceRolesOrdering_SemanticInstanceRoles() {
        return (EReference) instanceRolesOrderingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        eventEndsOrderingEClass = createEClass(EVENT_ENDS_ORDERING);
        createEReference(eventEndsOrderingEClass, EVENT_ENDS_ORDERING__SEQUENCE_DIAGRAM);
        createEReference(eventEndsOrderingEClass, EVENT_ENDS_ORDERING__EVENT_ENDS);

        eventEndEClass = createEClass(EVENT_END);
        createEReference(eventEndEClass, EVENT_END__SEMANTIC_END);

        singleEventEndEClass = createEClass(SINGLE_EVENT_END);
        createEAttribute(singleEventEndEClass, SINGLE_EVENT_END__START);
        createEReference(singleEventEndEClass, SINGLE_EVENT_END__SEMANTIC_EVENT);

        compoundEventEndEClass = createEClass(COMPOUND_EVENT_END);
        createEReference(compoundEventEndEClass, COMPOUND_EVENT_END__EVENT_ENDS);

        instanceRolesOrderingEClass = createEClass(INSTANCE_ROLES_ORDERING);
        createEReference(instanceRolesOrderingEClass, INSTANCE_ROLES_ORDERING__SEMANTIC_INSTANCE_ROLES);
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
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        SequencePackage theSequencePackage = (SequencePackage) EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        singleEventEndEClass.getESuperTypes().add(this.getEventEnd());
        compoundEventEndEClass.getESuperTypes().add(this.getEventEnd());

        // Initialize classes and features; add operations and parameters
        initEClass(eventEndsOrderingEClass, EventEndsOrdering.class, "EventEndsOrdering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEventEndsOrdering_SequenceDiagram(), theSequencePackage.getSequenceDDiagram(), null, "sequenceDiagram", null, 0, 1, EventEndsOrdering.class, IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEventEndsOrdering_EventEnds(), this.getEventEnd(), null, "eventEnds", null, 0, -1, EventEndsOrdering.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(eventEndEClass, EventEnd.class, "EventEnd", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEventEnd_SemanticEnd(), theEcorePackage.getEObject(), null, "semanticEnd", null, 1, 1, EventEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(singleEventEndEClass, SingleEventEnd.class, "SingleEventEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSingleEventEnd_Start(), theEcorePackage.getEBoolean(), "start", null, 1, 1, SingleEventEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSingleEventEnd_SemanticEvent(), theEcorePackage.getEObject(), null, "semanticEvent", null, 1, 1, SingleEventEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(compoundEventEndEClass, CompoundEventEnd.class, "CompoundEventEnd", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompoundEventEnd_EventEnds(), this.getSingleEventEnd(), null, "eventEnds", null, 1, -1, CompoundEventEnd.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(compoundEventEndEClass, theEcorePackage.getEObject(), "getSemanticEvents", 0, -1, IS_UNIQUE, IS_ORDERED);

        initEClass(instanceRolesOrderingEClass, InstanceRolesOrdering.class, "InstanceRolesOrdering", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getInstanceRolesOrdering_SemanticInstanceRoles(), theEcorePackage.getEObject(), null, "semanticInstanceRoles", null, 0, -1, InstanceRolesOrdering.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    }

} // OrderingPackageImpl
