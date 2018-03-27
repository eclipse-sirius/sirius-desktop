/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.formatdata.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.sirius.diagram.formatdata.AbstractFormatData;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.FormatdataFactory;
import org.eclipse.sirius.diagram.formatdata.FormatdataPackage;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.Point;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class FormatdataPackageImpl extends EPackageImpl implements FormatdataPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractFormatDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeFormatDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeFormatDataEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass pointEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private FormatdataPackageImpl() {
        super(FormatdataPackage.eNS_URI, FormatdataFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link FormatdataPackage#eINSTANCE} when that field is accessed. Clients should
     * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static FormatdataPackage init() {
        if (FormatdataPackageImpl.isInited) {
            return (FormatdataPackage) EPackage.Registry.INSTANCE.getEPackage(FormatdataPackage.eNS_URI);
        }

        // Obtain or create and register package
        FormatdataPackageImpl theFormatdataPackage = (FormatdataPackageImpl) (EPackage.Registry.INSTANCE.get(FormatdataPackage.eNS_URI) instanceof FormatdataPackageImpl
                ? EPackage.Registry.INSTANCE.get(FormatdataPackage.eNS_URI)
                : new FormatdataPackageImpl());

        FormatdataPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        NotationPackage.eINSTANCE.eClass();
        ViewpointPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theFormatdataPackage.createPackageContents();

        // Initialize created meta-data
        theFormatdataPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theFormatdataPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(FormatdataPackage.eNS_URI, theFormatdataPackage);
        return theFormatdataPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractFormatData() {
        return abstractFormatDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractFormatData_Id() {
        return (EAttribute) abstractFormatDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractFormatData_Label() {
        return (EReference) abstractFormatDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractFormatData_SiriusStyle() {
        return (EReference) abstractFormatDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractFormatData_GmfView() {
        return (EReference) abstractFormatDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeFormatData() {
        return nodeFormatDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeFormatData_Width() {
        return (EAttribute) nodeFormatDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeFormatData_Height() {
        return (EAttribute) nodeFormatDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeFormatData_Children() {
        return (EReference) nodeFormatDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeFormatData_OutgoingEdges() {
        return (EReference) nodeFormatDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeFormatData_Location() {
        return (EReference) nodeFormatDataEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeFormatData() {
        return edgeFormatDataEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_SourceTerminal() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_TargetTerminal() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_Routing() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeFormatData_PointList() {
        return (EReference) edgeFormatDataEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeFormatData_SourceRefPoint() {
        return (EReference) edgeFormatDataEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeFormatData_TargetRefPoint() {
        return (EReference) edgeFormatDataEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_JumpLinkStatus() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_JumpLinkType() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_ReverseJumpLink() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeFormatData_Smoothness() {
        return (EAttribute) edgeFormatDataEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPoint() {
        return pointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPoint_X() {
        return (EAttribute) pointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPoint_Y() {
        return (EAttribute) pointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FormatdataFactory getFormatdataFactory() {
        return (FormatdataFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        abstractFormatDataEClass = createEClass(FormatdataPackage.ABSTRACT_FORMAT_DATA);
        createEAttribute(abstractFormatDataEClass, FormatdataPackage.ABSTRACT_FORMAT_DATA__ID);
        createEReference(abstractFormatDataEClass, FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL);
        createEReference(abstractFormatDataEClass, FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE);
        createEReference(abstractFormatDataEClass, FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW);

        nodeFormatDataEClass = createEClass(FormatdataPackage.NODE_FORMAT_DATA);
        createEAttribute(nodeFormatDataEClass, FormatdataPackage.NODE_FORMAT_DATA__WIDTH);
        createEAttribute(nodeFormatDataEClass, FormatdataPackage.NODE_FORMAT_DATA__HEIGHT);
        createEReference(nodeFormatDataEClass, FormatdataPackage.NODE_FORMAT_DATA__CHILDREN);
        createEReference(nodeFormatDataEClass, FormatdataPackage.NODE_FORMAT_DATA__OUTGOING_EDGES);
        createEReference(nodeFormatDataEClass, FormatdataPackage.NODE_FORMAT_DATA__LOCATION);

        edgeFormatDataEClass = createEClass(FormatdataPackage.EDGE_FORMAT_DATA);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__SOURCE_TERMINAL);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__TARGET_TERMINAL);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__ROUTING);
        createEReference(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__POINT_LIST);
        createEReference(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__SOURCE_REF_POINT);
        createEReference(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__TARGET_REF_POINT);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__JUMP_LINK_STATUS);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__JUMP_LINK_TYPE);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__REVERSE_JUMP_LINK);
        createEAttribute(edgeFormatDataEClass, FormatdataPackage.EDGE_FORMAT_DATA__SMOOTHNESS);

        pointEClass = createEClass(FormatdataPackage.POINT);
        createEAttribute(pointEClass, FormatdataPackage.POINT__X);
        createEAttribute(pointEClass, FormatdataPackage.POINT__Y);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(FormatdataPackage.eNAME);
        setNsPrefix(FormatdataPackage.eNS_PREFIX);
        setNsURI(FormatdataPackage.eNS_URI);

        // Obtain other dependent packages
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);
        NotationPackage theNotationPackage = (NotationPackage) EPackage.Registry.INSTANCE.getEPackage(NotationPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        nodeFormatDataEClass.getESuperTypes().add(this.getAbstractFormatData());
        edgeFormatDataEClass.getESuperTypes().add(this.getAbstractFormatData());

        // Initialize classes and features; add operations and parameters
        initEClass(abstractFormatDataEClass, AbstractFormatData.class, "AbstractFormatData", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbstractFormatData_Id(), ecorePackage.getEString(), "id", null, 1, 1, AbstractFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractFormatData_Label(), this.getNodeFormatData(), null, "label", null, 0, 1, AbstractFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getAbstractFormatData_SiriusStyle(), theViewpointPackage.getStyle(), null, "siriusStyle", null, 1, 1, AbstractFormatData.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractFormatData_GmfView(), theNotationPackage.getView(), null, "gmfView", null, 1, 1, AbstractFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(nodeFormatDataEClass, NodeFormatData.class, "NodeFormatData", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNodeFormatData_Width(), ecorePackage.getEInt(), "width", "-2", 0, 1, NodeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getNodeFormatData_Height(), ecorePackage.getEInt(), "height", "-2", 0, 1, NodeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$ //$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getNodeFormatData_Children(), this.getNodeFormatData(), null, "children", null, 0, -1, NodeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getNodeFormatData_OutgoingEdges(), this.getEdgeFormatData(), null, "outgoingEdges", null, 0, -1, NodeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getNodeFormatData_Location(), this.getPoint(), null, "location", null, 0, 1, NodeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(edgeFormatDataEClass, EdgeFormatData.class, "EdgeFormatData", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getEdgeFormatData_SourceTerminal(), ecorePackage.getEString(), "sourceTerminal", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeFormatData_TargetTerminal(), ecorePackage.getEString(), "targetTerminal", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeFormatData_Routing(), ecorePackage.getEInt(), "routing", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeFormatData_PointList(), this.getPoint(), null, "pointList", null, 0, -1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEdgeFormatData_SourceRefPoint(), this.getPoint(), null, "sourceRefPoint", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEdgeFormatData_TargetRefPoint(), this.getPoint(), null, "targetRefPoint", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeFormatData_JumpLinkStatus(), ecorePackage.getEInt(), "jumpLinkStatus", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeFormatData_JumpLinkType(), ecorePackage.getEInt(), "jumpLinkType", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeFormatData_ReverseJumpLink(), ecorePackage.getEBoolean(), "reverseJumpLink", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeFormatData_Smoothness(), ecorePackage.getEInt(), "smoothness", null, 0, 1, EdgeFormatData.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(pointEClass, Point.class, "Point", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getPoint_X(), ecorePackage.getEInt(), "x", null, 0, 1, Point.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getPoint_Y(), ecorePackage.getEInt(), "y", null, 0, 1, Point.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        // Create resource
        createResource(FormatdataPackage.eNS_URI);
    }

} // FormatdataPackageImpl
