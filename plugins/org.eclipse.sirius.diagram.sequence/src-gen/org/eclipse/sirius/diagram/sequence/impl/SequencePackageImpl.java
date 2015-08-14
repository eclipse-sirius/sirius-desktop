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
package org.eclipse.sirius.diagram.sequence.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceFactory;
import org.eclipse.sirius.diagram.sequence.SequencePackage;
import org.eclipse.sirius.diagram.sequence.description.DescriptionPackage;
import org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl;
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
public class SequencePackageImpl extends EPackageImpl implements SequencePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass sequenceDDiagramEClass = null;

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
     * @see org.eclipse.sirius.diagram.sequence.SequencePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private SequencePackageImpl() {
        super(SequencePackage.eNS_URI, SequenceFactory.eINSTANCE);
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
     * This method is used to initialize {@link SequencePackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static SequencePackage init() {
        if (SequencePackageImpl.isInited) {
            return (SequencePackage) EPackage.Registry.INSTANCE.getEPackage(SequencePackage.eNS_URI);
        }

        // Obtain or create and register package
        SequencePackageImpl theSequencePackage = (SequencePackageImpl) (EPackage.Registry.INSTANCE.get(SequencePackage.eNS_URI) instanceof SequencePackageImpl ? EPackage.Registry.INSTANCE
                .get(SequencePackage.eNS_URI) : new SequencePackageImpl());

        SequencePackageImpl.isInited = true;

        // Initialize simple dependencies
        DiagramPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        OrderingPackageImpl theOrderingPackage = (OrderingPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI) instanceof OrderingPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(OrderingPackage.eNS_URI) : OrderingPackage.eINSTANCE);
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(TemplatePackage.eNS_URI) : TemplatePackage.eINSTANCE);

        // Create package meta-data objects
        theSequencePackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theToolPackage.createPackageContents();
        theOrderingPackage.createPackageContents();
        theTemplatePackage.createPackageContents();

        // Initialize created meta-data
        theSequencePackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theOrderingPackage.initializePackageContents();
        theTemplatePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theSequencePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(SequencePackage.eNS_URI, theSequencePackage);
        return theSequencePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getSequenceDDiagram() {
        return sequenceDDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSequenceDDiagram_SemanticOrdering() {
        return (EReference) sequenceDDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSequenceDDiagram_GraphicalOrdering() {
        return (EReference) sequenceDDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getSequenceDDiagram_InstanceRoleSemanticOrdering() {
        return (EReference) sequenceDDiagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public SequenceFactory getSequenceFactory() {
        return (SequenceFactory) getEFactoryInstance();
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
        sequenceDDiagramEClass = createEClass(SequencePackage.SEQUENCE_DDIAGRAM);
        createEReference(sequenceDDiagramEClass, SequencePackage.SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING);
        createEReference(sequenceDDiagramEClass, SequencePackage.SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING);
        createEReference(sequenceDDiagramEClass, SequencePackage.SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING);
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
        setName(SequencePackage.eNAME);
        setNsPrefix(SequencePackage.eNS_PREFIX);
        setNsURI(SequencePackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        OrderingPackage theOrderingPackage = (OrderingPackage) EPackage.Registry.INSTANCE.getEPackage(OrderingPackage.eNS_URI);
        TemplatePackage theTemplatePackage = (TemplatePackage) EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theDescriptionPackage);
        getESubpackages().add(theOrderingPackage);
        getESubpackages().add(theTemplatePackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        sequenceDDiagramEClass.getESuperTypes().add(theDiagramPackage.getDSemanticDiagram());

        // Initialize classes and features; add operations and parameters
        initEClass(sequenceDDiagramEClass, SequenceDDiagram.class, "SequenceDDiagram", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(
                getSequenceDDiagram_SemanticOrdering(),
                theOrderingPackage.getEventEndsOrdering(),
                null,
                "semanticOrdering", null, 0, 1, SequenceDDiagram.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSequenceDDiagram_GraphicalOrdering(),
                theOrderingPackage.getEventEndsOrdering(),
                null,
                "graphicalOrdering", null, 0, 1, SequenceDDiagram.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        initEReference(
                getSequenceDDiagram_InstanceRoleSemanticOrdering(),
                theOrderingPackage.getInstanceRolesOrdering(),
                null,
                "instanceRoleSemanticOrdering", null, 0, 1, SequenceDDiagram.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        // Create resource
        createResource(SequencePackage.eNS_URI);
    }

} // SequencePackageImpl
