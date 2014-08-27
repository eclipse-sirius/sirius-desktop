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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl;

import static org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage.CONTAINER;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerFactory;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MigrationmodelerPackageImpl extends EPackageImpl implements MigrationmodelerPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass borderedEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass graphicalElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractNodeRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nodeRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass borderedRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerRepresentationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass diagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass edgeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass layoutEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass colorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass pointEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass nodeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass basicLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass containerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass labelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass dotEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass gaugeSectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass flatContainerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass shapeContainerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass squareEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass ellipseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass lozengeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass bundledImageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass workspaceImageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass gaugeCompositeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass noteEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass testCaseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass representationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass borderedStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass filterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass layerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum routingStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum labelPositionEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum fontFormatEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum labelAlignmentEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum containerShapeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum backgroundStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum alignmentKindEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum bundledImageShapeEEnum = null;

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
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private MigrationmodelerPackageImpl() {
        super(eNS_URI, MigrationmodelerFactory.eINSTANCE);
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
     * This method is used to initialize
     * {@link MigrationmodelerPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access
     * that field to obtain the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static MigrationmodelerPackage init() {
        if (isInited)
            return (MigrationmodelerPackage) EPackage.Registry.INSTANCE.getEPackage(MigrationmodelerPackage.eNS_URI);

        // Obtain or create and register package
        MigrationmodelerPackageImpl theMigrationmodelerPackage = (MigrationmodelerPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MigrationmodelerPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new MigrationmodelerPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theMigrationmodelerPackage.createPackageContents();

        // Initialize created meta-data
        theMigrationmodelerPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theMigrationmodelerPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(MigrationmodelerPackage.eNS_URI, theMigrationmodelerPackage);
        return theMigrationmodelerPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNode() {
        return nodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNode_NodeRepresentations() {
        return (EReference) nodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBordered() {
        return borderedEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBordered_BorderedRepresentations() {
        return (EReference) borderedEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainer() {
        return containerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainer_ContainerRepresentations() {
        return (EReference) containerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainer_Elements() {
        return (EReference) containerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGraphicalElement() {
        return graphicalElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGraphicalElement_Id() {
        return (EAttribute) graphicalElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdge() {
        return edgeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdge_EdgeRepresentations() {
        return (EReference) edgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdge_Source() {
        return (EReference) edgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdge_Target() {
        return (EReference) edgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractRepresentation() {
        return abstractRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractRepresentation_MappingId() {
        return (EAttribute) abstractRepresentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractRepresentation_Layout() {
        return (EReference) abstractRepresentationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractRepresentation_Displayed() {
        return (EAttribute) abstractRepresentationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractRepresentation_Hidden() {
        return (EAttribute) abstractRepresentationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getAbstractRepresentation_Pinned() {
        return (EAttribute) abstractRepresentationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeRepresentation() {
        return edgeRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeRepresentation_Source() {
        return (EReference) edgeRepresentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeRepresentation_Target() {
        return (EReference) edgeRepresentationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeRepresentation_Bendpoints() {
        return (EReference) edgeRepresentationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeRepresentation_OwnedStyle() {
        return (EReference) edgeRepresentationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractNodeRepresentation() {
        return abstractNodeRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractNodeRepresentation_Bordereds() {
        return (EReference) abstractNodeRepresentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getAbstractNodeRepresentation_OwnedStyle() {
        return (EReference) abstractNodeRepresentationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeRepresentation() {
        return nodeRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBorderedRepresentation() {
        return borderedRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerRepresentation() {
        return containerRepresentationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getContainerRepresentation_OwnedStyle() {
        return (EReference) containerRepresentationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDiagram() {
        return diagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagram_Containers() {
        return (EReference) diagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagram_Nodes() {
        return (EReference) diagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagram_Edges() {
        return (EReference) diagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagram_Filters() {
        return (EReference) diagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDiagram_Layers() {
        return (EReference) diagramEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeStyle() {
        return edgeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyle_RoutingStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_Color() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_BeginLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_CenterLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyle_EndLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLayout() {
        return layoutEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayout_X() {
        return (EAttribute) layoutEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayout_Y() {
        return (EAttribute) layoutEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayout_Width() {
        return (EAttribute) layoutEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayout_Height() {
        return (EAttribute) layoutEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getRoutingStyle() {
        return routingStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLabelPosition() {
        return labelPositionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getFontFormat() {
        return fontFormatEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getLabelAlignment() {
        return labelAlignmentEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getContainerShape() {
        return containerShapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getBackgroundStyle() {
        return backgroundStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getAlignmentKind() {
        return alignmentKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EEnum getBundledImageShape() {
        return bundledImageShapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getColor() {
        return colorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getColor_Red() {
        return (EAttribute) colorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getColor_Green() {
        return (EAttribute) colorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getColor_Blue() {
        return (EAttribute) colorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getPoint() {
        return pointEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPoint_X() {
        return (EAttribute) pointEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getPoint_Y() {
        return (EAttribute) pointEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeStyle() {
        return nodeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyle_LabelPosition() {
        return (EAttribute) nodeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyle_HideLabelByDefault() {
        return (EAttribute) nodeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBasicLabelStyle() {
        return basicLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_LabelSize() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_LabelFormat() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_ShowIcon() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBasicLabelStyle_LabelColor() {
        return (EReference) basicLabelStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyle_IconPath() {
        return (EAttribute) basicLabelStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerStyle() {
        return containerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLabelStyle() {
        return labelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelStyle_LabelAlignment() {
        return (EAttribute) labelStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDot() {
        return dotEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDot_BackgroundColor() {
        return (EReference) dotEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGaugeSection() {
        return gaugeSectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Min() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Max() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Value() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSection_Label() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeSection_BackgroundColor() {
        return (EReference) gaugeSectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeSection_ForegroundColor() {
        return (EReference) gaugeSectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFlatContainerStyle() {
        return flatContainerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlatContainerStyle_BackgroundStyle() {
        return (EAttribute) flatContainerStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyle_BackgroundColor() {
        return (EReference) flatContainerStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyle_ForegroundColor() {
        return (EReference) flatContainerStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getShapeContainerStyle() {
        return shapeContainerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getShapeContainerStyle_Shape() {
        return (EAttribute) shapeContainerStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getShapeContainerStyle_BackgroundColor() {
        return (EReference) shapeContainerStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSquare() {
        return squareEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSquare_Width() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSquare_Height() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSquare_Color() {
        return (EReference) squareEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEllipse() {
        return ellipseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEllipse_HorizontalDiameter() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEllipse_VerticalDiameter() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEllipse_Color() {
        return (EReference) ellipseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLozenge() {
        return lozengeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLozenge_Width() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLozenge_Height() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLozenge_Color() {
        return (EReference) lozengeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBundledImage() {
        return bundledImageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBundledImage_Shape() {
        return (EAttribute) bundledImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBundledImage_Color() {
        return (EReference) bundledImageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getWorkspaceImage() {
        return workspaceImageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWorkspaceImage_WorkspacePath() {
        return (EAttribute) workspaceImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGaugeCompositeStyle() {
        return gaugeCompositeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeCompositeStyle_Alignment() {
        return (EAttribute) gaugeCompositeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeCompositeStyle_Sections() {
        return (EReference) gaugeCompositeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNote() {
        return noteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNote_Color() {
        return (EReference) noteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getAbstractNode() {
        return abstractNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTestCase() {
        return testCaseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getTestCase_Representations() {
        return (EReference) testCaseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRepresentation() {
        return representationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRepresentation_Name() {
        return (EAttribute) representationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBorderedStyle() {
        return borderedStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBorderedStyle_BorderSize() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBorderedStyle_BorderColor() {
        return (EReference) borderedStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFilter() {
        return filterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFilter_Id() {
        return (EAttribute) filterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFilter_Activated() {
        return (EAttribute) filterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLayer() {
        return layerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayer_Id() {
        return (EAttribute) layerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLayer_Activated() {
        return (EAttribute) layerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MigrationmodelerFactory getMigrationmodelerFactory() {
        return (MigrationmodelerFactory) getEFactoryInstance();
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
        diagramEClass = createEClass(DIAGRAM);
        createEReference(diagramEClass, DIAGRAM__CONTAINERS);
        createEReference(diagramEClass, DIAGRAM__NODES);
        createEReference(diagramEClass, DIAGRAM__EDGES);
        createEReference(diagramEClass, DIAGRAM__FILTERS);
        createEReference(diagramEClass, DIAGRAM__LAYERS);

        graphicalElementEClass = createEClass(GRAPHICAL_ELEMENT);
        createEAttribute(graphicalElementEClass, GRAPHICAL_ELEMENT__ID);

        abstractNodeEClass = createEClass(ABSTRACT_NODE);

        nodeEClass = createEClass(NODE);
        createEReference(nodeEClass, NODE__NODE_REPRESENTATIONS);

        borderedEClass = createEClass(BORDERED);
        createEReference(borderedEClass, BORDERED__BORDERED_REPRESENTATIONS);

        containerEClass = createEClass(CONTAINER);
        createEReference(containerEClass, CONTAINER__CONTAINER_REPRESENTATIONS);
        createEReference(containerEClass, CONTAINER__ELEMENTS);

        edgeEClass = createEClass(EDGE);
        createEReference(edgeEClass, EDGE__EDGE_REPRESENTATIONS);
        createEReference(edgeEClass, EDGE__SOURCE);
        createEReference(edgeEClass, EDGE__TARGET);

        abstractRepresentationEClass = createEClass(ABSTRACT_REPRESENTATION);
        createEAttribute(abstractRepresentationEClass, ABSTRACT_REPRESENTATION__MAPPING_ID);
        createEReference(abstractRepresentationEClass, ABSTRACT_REPRESENTATION__LAYOUT);
        createEAttribute(abstractRepresentationEClass, ABSTRACT_REPRESENTATION__DISPLAYED);
        createEAttribute(abstractRepresentationEClass, ABSTRACT_REPRESENTATION__HIDDEN);
        createEAttribute(abstractRepresentationEClass, ABSTRACT_REPRESENTATION__PINNED);

        edgeRepresentationEClass = createEClass(EDGE_REPRESENTATION);
        createEReference(edgeRepresentationEClass, EDGE_REPRESENTATION__SOURCE);
        createEReference(edgeRepresentationEClass, EDGE_REPRESENTATION__TARGET);
        createEReference(edgeRepresentationEClass, EDGE_REPRESENTATION__BENDPOINTS);
        createEReference(edgeRepresentationEClass, EDGE_REPRESENTATION__OWNED_STYLE);

        abstractNodeRepresentationEClass = createEClass(ABSTRACT_NODE_REPRESENTATION);
        createEReference(abstractNodeRepresentationEClass, ABSTRACT_NODE_REPRESENTATION__BORDEREDS);
        createEReference(abstractNodeRepresentationEClass, ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE);

        nodeRepresentationEClass = createEClass(NODE_REPRESENTATION);

        borderedRepresentationEClass = createEClass(BORDERED_REPRESENTATION);

        containerRepresentationEClass = createEClass(CONTAINER_REPRESENTATION);
        createEReference(containerRepresentationEClass, CONTAINER_REPRESENTATION__OWNED_STYLE);

        edgeStyleEClass = createEClass(EDGE_STYLE);
        createEAttribute(edgeStyleEClass, EDGE_STYLE__ROUTING_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__COLOR);
        createEReference(edgeStyleEClass, EDGE_STYLE__BEGIN_LABEL_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__CENTER_LABEL_STYLE);
        createEReference(edgeStyleEClass, EDGE_STYLE__END_LABEL_STYLE);

        layoutEClass = createEClass(LAYOUT);
        createEAttribute(layoutEClass, LAYOUT__X);
        createEAttribute(layoutEClass, LAYOUT__Y);
        createEAttribute(layoutEClass, LAYOUT__WIDTH);
        createEAttribute(layoutEClass, LAYOUT__HEIGHT);

        colorEClass = createEClass(COLOR);
        createEAttribute(colorEClass, COLOR__RED);
        createEAttribute(colorEClass, COLOR__GREEN);
        createEAttribute(colorEClass, COLOR__BLUE);

        pointEClass = createEClass(POINT);
        createEAttribute(pointEClass, POINT__X);
        createEAttribute(pointEClass, POINT__Y);

        testCaseEClass = createEClass(TEST_CASE);
        createEReference(testCaseEClass, TEST_CASE__REPRESENTATIONS);

        representationEClass = createEClass(REPRESENTATION);
        createEAttribute(representationEClass, REPRESENTATION__NAME);

        borderedStyleEClass = createEClass(BORDERED_STYLE);
        createEAttribute(borderedStyleEClass, BORDERED_STYLE__BORDER_SIZE);
        createEReference(borderedStyleEClass, BORDERED_STYLE__BORDER_COLOR);

        filterEClass = createEClass(FILTER);
        createEAttribute(filterEClass, FILTER__ID);
        createEAttribute(filterEClass, FILTER__ACTIVATED);

        layerEClass = createEClass(LAYER);
        createEAttribute(layerEClass, LAYER__ID);
        createEAttribute(layerEClass, LAYER__ACTIVATED);

        nodeStyleEClass = createEClass(NODE_STYLE);
        createEAttribute(nodeStyleEClass, NODE_STYLE__LABEL_POSITION);
        createEAttribute(nodeStyleEClass, NODE_STYLE__HIDE_LABEL_BY_DEFAULT);

        basicLabelStyleEClass = createEClass(BASIC_LABEL_STYLE);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__LABEL_SIZE);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__LABEL_FORMAT);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__SHOW_ICON);
        createEReference(basicLabelStyleEClass, BASIC_LABEL_STYLE__LABEL_COLOR);
        createEAttribute(basicLabelStyleEClass, BASIC_LABEL_STYLE__ICON_PATH);

        containerStyleEClass = createEClass(CONTAINER_STYLE);

        labelStyleEClass = createEClass(LABEL_STYLE);
        createEAttribute(labelStyleEClass, LABEL_STYLE__LABEL_ALIGNMENT);

        dotEClass = createEClass(DOT);
        createEReference(dotEClass, DOT__BACKGROUND_COLOR);

        gaugeSectionEClass = createEClass(GAUGE_SECTION);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__MIN);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__MAX);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__VALUE);
        createEAttribute(gaugeSectionEClass, GAUGE_SECTION__LABEL);
        createEReference(gaugeSectionEClass, GAUGE_SECTION__BACKGROUND_COLOR);
        createEReference(gaugeSectionEClass, GAUGE_SECTION__FOREGROUND_COLOR);

        flatContainerStyleEClass = createEClass(FLAT_CONTAINER_STYLE);
        createEAttribute(flatContainerStyleEClass, FLAT_CONTAINER_STYLE__BACKGROUND_STYLE);
        createEReference(flatContainerStyleEClass, FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        createEReference(flatContainerStyleEClass, FLAT_CONTAINER_STYLE__FOREGROUND_COLOR);

        shapeContainerStyleEClass = createEClass(SHAPE_CONTAINER_STYLE);
        createEAttribute(shapeContainerStyleEClass, SHAPE_CONTAINER_STYLE__SHAPE);
        createEReference(shapeContainerStyleEClass, SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR);

        squareEClass = createEClass(SQUARE);
        createEAttribute(squareEClass, SQUARE__WIDTH);
        createEAttribute(squareEClass, SQUARE__HEIGHT);
        createEReference(squareEClass, SQUARE__COLOR);

        ellipseEClass = createEClass(ELLIPSE);
        createEAttribute(ellipseEClass, ELLIPSE__HORIZONTAL_DIAMETER);
        createEAttribute(ellipseEClass, ELLIPSE__VERTICAL_DIAMETER);
        createEReference(ellipseEClass, ELLIPSE__COLOR);

        lozengeEClass = createEClass(LOZENGE);
        createEAttribute(lozengeEClass, LOZENGE__WIDTH);
        createEAttribute(lozengeEClass, LOZENGE__HEIGHT);
        createEReference(lozengeEClass, LOZENGE__COLOR);

        bundledImageEClass = createEClass(BUNDLED_IMAGE);
        createEAttribute(bundledImageEClass, BUNDLED_IMAGE__SHAPE);
        createEReference(bundledImageEClass, BUNDLED_IMAGE__COLOR);

        workspaceImageEClass = createEClass(WORKSPACE_IMAGE);
        createEAttribute(workspaceImageEClass, WORKSPACE_IMAGE__WORKSPACE_PATH);

        gaugeCompositeStyleEClass = createEClass(GAUGE_COMPOSITE_STYLE);
        createEAttribute(gaugeCompositeStyleEClass, GAUGE_COMPOSITE_STYLE__ALIGNMENT);
        createEReference(gaugeCompositeStyleEClass, GAUGE_COMPOSITE_STYLE__SECTIONS);

        noteEClass = createEClass(NOTE);
        createEReference(noteEClass, NOTE__COLOR);

        // Create enums
        routingStyleEEnum = createEEnum(ROUTING_STYLE);
        labelPositionEEnum = createEEnum(LABEL_POSITION);
        fontFormatEEnum = createEEnum(FONT_FORMAT);
        labelAlignmentEEnum = createEEnum(LABEL_ALIGNMENT);
        containerShapeEEnum = createEEnum(CONTAINER_SHAPE);
        backgroundStyleEEnum = createEEnum(BACKGROUND_STYLE);
        alignmentKindEEnum = createEEnum(ALIGNMENT_KIND);
        bundledImageShapeEEnum = createEEnum(BUNDLED_IMAGE_SHAPE);
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
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        diagramEClass.getESuperTypes().add(this.getRepresentation());
        abstractNodeEClass.getESuperTypes().add(this.getGraphicalElement());
        nodeEClass.getESuperTypes().add(this.getAbstractNode());
        borderedEClass.getESuperTypes().add(this.getAbstractNode());
        containerEClass.getESuperTypes().add(this.getGraphicalElement());
        edgeEClass.getESuperTypes().add(this.getGraphicalElement());
        edgeRepresentationEClass.getESuperTypes().add(this.getAbstractRepresentation());
        abstractNodeRepresentationEClass.getESuperTypes().add(this.getAbstractRepresentation());
        nodeRepresentationEClass.getESuperTypes().add(this.getAbstractNodeRepresentation());
        borderedRepresentationEClass.getESuperTypes().add(this.getAbstractNodeRepresentation());
        containerRepresentationEClass.getESuperTypes().add(this.getAbstractRepresentation());
        nodeStyleEClass.getESuperTypes().add(this.getLabelStyle());
        nodeStyleEClass.getESuperTypes().add(this.getBorderedStyle());
        containerStyleEClass.getESuperTypes().add(this.getLabelStyle());
        containerStyleEClass.getESuperTypes().add(this.getBorderedStyle());
        labelStyleEClass.getESuperTypes().add(this.getBasicLabelStyle());
        dotEClass.getESuperTypes().add(this.getNodeStyle());
        flatContainerStyleEClass.getESuperTypes().add(this.getContainerStyle());
        shapeContainerStyleEClass.getESuperTypes().add(this.getContainerStyle());
        squareEClass.getESuperTypes().add(this.getNodeStyle());
        ellipseEClass.getESuperTypes().add(this.getNodeStyle());
        lozengeEClass.getESuperTypes().add(this.getNodeStyle());
        bundledImageEClass.getESuperTypes().add(this.getNodeStyle());
        workspaceImageEClass.getESuperTypes().add(this.getNodeStyle());
        workspaceImageEClass.getESuperTypes().add(this.getContainerStyle());
        gaugeCompositeStyleEClass.getESuperTypes().add(this.getNodeStyle());
        noteEClass.getESuperTypes().add(this.getNodeStyle());

        // Initialize classes and features; add operations and parameters
        initEClass(diagramEClass, Diagram.class, "Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDiagram_Containers(), this.getContainer(), null, "containers", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagram_Nodes(), this.getNode(), null, "nodes", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagram_Edges(), this.getEdge(), null, "edges", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagram_Filters(), this.getFilter(), null, "filters", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDiagram_Layers(), this.getLayer(), null, "layers", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(graphicalElementEClass, GraphicalElement.class, "GraphicalElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGraphicalElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, GraphicalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(abstractNodeEClass, AbstractNode.class, "AbstractNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNode_NodeRepresentations(), this.getNodeRepresentation(), null, "nodeRepresentations", null, 1, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(borderedEClass, Bordered.class, "Bordered", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBordered_BorderedRepresentations(), this.getBorderedRepresentation(), null, "borderedRepresentations", null, 1, -1, Bordered.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerEClass, org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainer_ContainerRepresentations(), this.getContainerRepresentation(), null, "containerRepresentations", null, 1, -1,
                org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getContainer_Elements(), this.getGraphicalElement(), null, "elements", null, 0, -1, org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdge_EdgeRepresentations(), this.getEdgeRepresentation(), null, "edgeRepresentations", null, 1, -1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdge_Source(), this.getGraphicalElement(), null, "source", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdge_Target(), this.getGraphicalElement(), null, "target", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractRepresentationEClass, AbstractRepresentation.class, "AbstractRepresentation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractRepresentation_MappingId(), ecorePackage.getEString(), "mappingId", null, 1, 1, AbstractRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractRepresentation_Layout(), this.getLayout(), null, "layout", null, 0, 1, AbstractRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractRepresentation_Displayed(), ecorePackage.getEBoolean(), "displayed", "true", 0, 1, AbstractRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractRepresentation_Hidden(), ecorePackage.getEBoolean(), "hidden", null, 0, 1, AbstractRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractRepresentation_Pinned(), ecorePackage.getEBoolean(), "pinned", null, 0, 1, AbstractRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeRepresentationEClass, EdgeRepresentation.class, "EdgeRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeRepresentation_Source(), this.getGraphicalElement(), null, "source", null, 0, 1, EdgeRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeRepresentation_Target(), this.getGraphicalElement(), null, "target", null, 0, 1, EdgeRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeRepresentation_Bendpoints(), this.getPoint(), null, "bendpoints", null, 0, -1, EdgeRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeRepresentation_OwnedStyle(), this.getEdgeStyle(), null, "ownedStyle", null, 0, 1, EdgeRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractNodeRepresentationEClass, AbstractNodeRepresentation.class, "AbstractNodeRepresentation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractNodeRepresentation_Bordereds(), this.getBordered(), null, "bordereds", null, 0, -1, AbstractNodeRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAbstractNodeRepresentation_OwnedStyle(), this.getNodeStyle(), null, "ownedStyle", null, 0, 1, AbstractNodeRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeRepresentationEClass, NodeRepresentation.class, "NodeRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(borderedRepresentationEClass, BorderedRepresentation.class, "BorderedRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(containerRepresentationEClass, ContainerRepresentation.class, "ContainerRepresentation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getContainerRepresentation_OwnedStyle(), this.getContainerStyle(), null, "ownedStyle", null, 0, 1, ContainerRepresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeStyleEClass, EdgeStyle.class, "EdgeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEdgeStyle_RoutingStyle(), this.getRoutingStyle(), "routingStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_Color(), this.getColor(), null, "color", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_BeginLabelStyle(), this.getBasicLabelStyle(), null, "beginLabelStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_CenterLabelStyle(), this.getBasicLabelStyle(), null, "centerLabelStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyle_EndLabelStyle(), this.getBasicLabelStyle(), null, "endLabelStyle", null, 0, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(layoutEClass, Layout.class, "Layout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLayout_X(), ecorePackage.getEInt(), "x", null, 0, 1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayout_Y(), ecorePackage.getEInt(), "y", null, 0, 1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayout_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getLayout_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(colorEClass, Color.class, "Color", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getColor_Red(), ecorePackage.getEInt(), "red", null, 0, 1, Color.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getColor_Green(), ecorePackage.getEInt(), "green", null, 0, 1, Color.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getColor_Blue(), ecorePackage.getEInt(), "blue", null, 0, 1, Color.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pointEClass, Point.class, "Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getPoint_X(), ecorePackage.getEInt(), "x", null, 0, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPoint_Y(), ecorePackage.getEInt(), "y", null, 0, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(testCaseEClass, TestCase.class, "TestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTestCase_Representations(), this.getRepresentation(), null, "representations", null, 0, -1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(representationEClass, Representation.class, "Representation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentation_Name(), ecorePackage.getEString(), "name", null, 0, 1, Representation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(borderedStyleEClass, BorderedStyle.class, "BorderedStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBorderedStyle_BorderSize(), ecorePackage.getEInt(), "borderSize", "0", 1, 1, BorderedStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBorderedStyle_BorderColor(), this.getColor(), null, "borderColor", null, 0, 1, BorderedStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFilter_Id(), ecorePackage.getEString(), "id", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getFilter_Activated(), ecorePackage.getEBoolean(), "activated", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(layerEClass, Layer.class, "Layer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLayer_Id(), ecorePackage.getEString(), "id", null, 0, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLayer_Activated(), ecorePackage.getEBoolean(), "activated", null, 0, 1, Layer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(nodeStyleEClass, NodeStyle.class, "NodeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNodeStyle_LabelPosition(), this.getLabelPosition(), "labelPosition", null, 0, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyle_HideLabelByDefault(), ecorePackage.getEBoolean(), "hideLabelByDefault", "false", 0, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(basicLabelStyleEClass, BasicLabelStyle.class, "BasicLabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBasicLabelStyle_LabelSize(), theEcorePackage.getEInt(), "labelSize", "8", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyle_LabelFormat(), this.getFontFormat(), "labelFormat", "normal", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyle_ShowIcon(), ecorePackage.getEBoolean(), "showIcon", "true", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBasicLabelStyle_LabelColor(), this.getColor(), null, "labelColor", null, 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyle_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, BasicLabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerStyleEClass, ContainerStyle.class, "ContainerStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(labelStyleEClass, LabelStyle.class, "LabelStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelStyle_LabelAlignment(), this.getLabelAlignment(), "labelAlignment", null, 0, 1, LabelStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dotEClass, Dot.class, "Dot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDot_BackgroundColor(), this.getColor(), null, "backgroundColor", null, 0, 1, Dot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeSectionEClass, GaugeSection.class, "GaugeSection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeSection_Min(), theEcorePackage.getEIntegerObject(), "min", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSection_Max(), theEcorePackage.getEIntegerObject(), "max", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSection_Value(), theEcorePackage.getEIntegerObject(), "value", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSection_Label(), theEcorePackage.getEString(), "label", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSection_BackgroundColor(), this.getColor(), null, "backgroundColor", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSection_ForegroundColor(), this.getColor(), null, "foregroundColor", null, 0, 1, GaugeSection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flatContainerStyleEClass, FlatContainerStyle.class, "FlatContainerStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlatContainerStyle_BackgroundStyle(), this.getBackgroundStyle(), "backgroundStyle", null, 1, 1, FlatContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyle_BackgroundColor(), this.getColor(), null, "backgroundColor", null, 0, 1, FlatContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyle_ForegroundColor(), this.getColor(), null, "foregroundColor", null, 0, 1, FlatContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(shapeContainerStyleEClass, ShapeContainerStyle.class, "ShapeContainerStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getShapeContainerStyle_Shape(), this.getContainerShape(), "shape", null, 1, 1, ShapeContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getShapeContainerStyle_BackgroundColor(), this.getColor(), null, "backgroundColor", null, 1, 1, ShapeContainerStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(squareEClass, Square.class, "Square", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSquare_Width(), ecorePackage.getEIntegerObject(), "width", "0", 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSquare_Height(), ecorePackage.getEIntegerObject(), "height", "0", 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getSquare_Color(), this.getColor(), null, "color", null, 0, 1, Square.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ellipseEClass, Ellipse.class, "Ellipse", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEllipse_HorizontalDiameter(), ecorePackage.getEIntegerObject(), "horizontalDiameter", "0", 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEllipse_VerticalDiameter(), ecorePackage.getEIntegerObject(), "verticalDiameter", "0", 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEllipse_Color(), this.getColor(), null, "color", null, 0, 1, Ellipse.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lozengeEClass, Lozenge.class, "Lozenge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLozenge_Width(), ecorePackage.getEIntegerObject(), "width", "0", 0, 1, Lozenge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLozenge_Height(), ecorePackage.getEIntegerObject(), "height", "0", 0, 1, Lozenge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getLozenge_Color(), this.getColor(), null, "color", null, 0, 1, Lozenge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bundledImageEClass, BundledImage.class, "BundledImage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBundledImage_Shape(), this.getBundledImageShape(), "shape", null, 1, 1, BundledImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getBundledImage_Color(), this.getColor(), null, "color", null, 1, 1, BundledImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(workspaceImageEClass, WorkspaceImage.class, "WorkspaceImage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWorkspaceImage_WorkspacePath(), theEcorePackage.getEString(), "workspacePath", null, 1, 1, WorkspaceImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeCompositeStyleEClass, GaugeCompositeStyle.class, "GaugeCompositeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeCompositeStyle_Alignment(), this.getAlignmentKind(), "alignment", "SQUARE", 0, 1, GaugeCompositeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeCompositeStyle_Sections(), this.getGaugeSection(), null, "sections", null, 0, -1, GaugeCompositeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(noteEClass, Note.class, "Note", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNote_Color(), this.getColor(), null, "color", null, 0, 1, Note.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(routingStyleEEnum, RoutingStyle.class, "RoutingStyle");
        addEEnumLiteral(routingStyleEEnum, RoutingStyle.STRAIGHT);
        addEEnumLiteral(routingStyleEEnum, RoutingStyle.MANHATTAN);
        addEEnumLiteral(routingStyleEEnum, RoutingStyle.TREE);

        initEEnum(labelPositionEEnum, LabelPosition.class, "LabelPosition");
        addEEnumLiteral(labelPositionEEnum, LabelPosition.BORDER);
        addEEnumLiteral(labelPositionEEnum, LabelPosition.NODE);

        initEEnum(fontFormatEEnum, FontFormat.class, "FontFormat");
        addEEnumLiteral(fontFormatEEnum, FontFormat.NORMAL);
        addEEnumLiteral(fontFormatEEnum, FontFormat.ITALIC);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD);
        addEEnumLiteral(fontFormatEEnum, FontFormat.BOLD_ITALIC);

        initEEnum(labelAlignmentEEnum, LabelAlignment.class, "LabelAlignment");
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.CENTER);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.LEFT);
        addEEnumLiteral(labelAlignmentEEnum, LabelAlignment.RIGHT);

        initEEnum(containerShapeEEnum, ContainerShape.class, "ContainerShape");
        addEEnumLiteral(containerShapeEEnum, ContainerShape.PARALLELOGRAM);

        initEEnum(backgroundStyleEEnum, BackgroundStyle.class, "BackgroundStyle");
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.GRADIENT_LEFT_TO_RIGHT);
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.LIQUID);
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.GRADIENT_TOP_TO_BOTTOM);

        initEEnum(alignmentKindEEnum, AlignmentKind.class, "AlignmentKind");
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.VERTICAL);
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.HORIZONTAL);
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.SQUARE);

        initEEnum(bundledImageShapeEEnum, BundledImageShape.class, "BundledImageShape");
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.SQUARE);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.STROKE);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.TRIANGLE);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.DOT);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.RING);

        // Create resource
        createResource(eNS_URI);
    }

} // MigrationmodelerPackageImpl
