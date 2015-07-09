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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class MigrationmodelerFactoryImpl extends EFactoryImpl implements MigrationmodelerFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static MigrationmodelerFactory init() {
        try {
            MigrationmodelerFactory theMigrationmodelerFactory = (MigrationmodelerFactory) EPackage.Registry.INSTANCE.getEFactory(MigrationmodelerPackage.eNS_URI);
            if (theMigrationmodelerFactory != null) {
                return theMigrationmodelerFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new MigrationmodelerFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public MigrationmodelerFactoryImpl() {
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
        case MigrationmodelerPackage.DIAGRAM:
            return createDiagram();
        case MigrationmodelerPackage.NODE:
            return createNode();
        case MigrationmodelerPackage.BORDERED:
            return createBordered();
        case MigrationmodelerPackage.CONTAINER:
            return createContainer();
        case MigrationmodelerPackage.EDGE:
            return createEdge();
        case MigrationmodelerPackage.EDGE_REPRESENTATION:
            return createEdgeRepresentation();
        case MigrationmodelerPackage.NODE_REPRESENTATION:
            return createNodeRepresentation();
        case MigrationmodelerPackage.BORDERED_REPRESENTATION:
            return createBorderedRepresentation();
        case MigrationmodelerPackage.CONTAINER_REPRESENTATION:
            return createContainerRepresentation();
        case MigrationmodelerPackage.EDGE_STYLE:
            return createEdgeStyle();
        case MigrationmodelerPackage.LAYOUT:
            return createLayout();
        case MigrationmodelerPackage.COLOR:
            return createColor();
        case MigrationmodelerPackage.POINT:
            return createPoint();
        case MigrationmodelerPackage.TEST_CASE:
            return createTestCase();
        case MigrationmodelerPackage.BORDERED_STYLE:
            return createBorderedStyle();
        case MigrationmodelerPackage.FILTER:
            return createFilter();
        case MigrationmodelerPackage.LAYER:
            return createLayer();
        case MigrationmodelerPackage.NODE_STYLE:
            return createNodeStyle();
        case MigrationmodelerPackage.BASIC_LABEL_STYLE:
            return createBasicLabelStyle();
        case MigrationmodelerPackage.CONTAINER_STYLE:
            return createContainerStyle();
        case MigrationmodelerPackage.LABEL_STYLE:
            return createLabelStyle();
        case MigrationmodelerPackage.DOT:
            return createDot();
        case MigrationmodelerPackage.GAUGE_SECTION:
            return createGaugeSection();
        case MigrationmodelerPackage.FLAT_CONTAINER_STYLE:
            return createFlatContainerStyle();
        case MigrationmodelerPackage.SHAPE_CONTAINER_STYLE:
            return createShapeContainerStyle();
        case MigrationmodelerPackage.SQUARE:
            return createSquare();
        case MigrationmodelerPackage.ELLIPSE:
            return createEllipse();
        case MigrationmodelerPackage.LOZENGE:
            return createLozenge();
        case MigrationmodelerPackage.BUNDLED_IMAGE:
            return createBundledImage();
        case MigrationmodelerPackage.WORKSPACE_IMAGE:
            return createWorkspaceImage();
        case MigrationmodelerPackage.GAUGE_COMPOSITE_STYLE:
            return createGaugeCompositeStyle();
        case MigrationmodelerPackage.NOTE:
            return createNote();
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
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case MigrationmodelerPackage.ROUTING_STYLE:
            return createRoutingStyleFromString(eDataType, initialValue);
        case MigrationmodelerPackage.LABEL_POSITION:
            return createLabelPositionFromString(eDataType, initialValue);
        case MigrationmodelerPackage.FONT_FORMAT:
            return createFontFormatFromString(eDataType, initialValue);
        case MigrationmodelerPackage.LABEL_ALIGNMENT:
            return createLabelAlignmentFromString(eDataType, initialValue);
        case MigrationmodelerPackage.CONTAINER_SHAPE:
            return createContainerShapeFromString(eDataType, initialValue);
        case MigrationmodelerPackage.BACKGROUND_STYLE:
            return createBackgroundStyleFromString(eDataType, initialValue);
        case MigrationmodelerPackage.ALIGNMENT_KIND:
            return createAlignmentKindFromString(eDataType, initialValue);
        case MigrationmodelerPackage.BUNDLED_IMAGE_SHAPE:
            return createBundledImageShapeFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case MigrationmodelerPackage.ROUTING_STYLE:
            return convertRoutingStyleToString(eDataType, instanceValue);
        case MigrationmodelerPackage.LABEL_POSITION:
            return convertLabelPositionToString(eDataType, instanceValue);
        case MigrationmodelerPackage.FONT_FORMAT:
            return convertFontFormatToString(eDataType, instanceValue);
        case MigrationmodelerPackage.LABEL_ALIGNMENT:
            return convertLabelAlignmentToString(eDataType, instanceValue);
        case MigrationmodelerPackage.CONTAINER_SHAPE:
            return convertContainerShapeToString(eDataType, instanceValue);
        case MigrationmodelerPackage.BACKGROUND_STYLE:
            return convertBackgroundStyleToString(eDataType, instanceValue);
        case MigrationmodelerPackage.ALIGNMENT_KIND:
            return convertAlignmentKindToString(eDataType, instanceValue);
        case MigrationmodelerPackage.BUNDLED_IMAGE_SHAPE:
            return convertBundledImageShapeToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Node createNode() {
        NodeImpl node = new NodeImpl();
        return node;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Bordered createBordered() {
        BorderedImpl bordered = new BorderedImpl();
        return bordered;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container createContainer() {
        ContainerImpl container = new ContainerImpl();
        return container;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Edge createEdge() {
        EdgeImpl edge = new EdgeImpl();
        return edge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeRepresentation createEdgeRepresentation() {
        EdgeRepresentationImpl edgeRepresentation = new EdgeRepresentationImpl();
        return edgeRepresentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeRepresentation createNodeRepresentation() {
        NodeRepresentationImpl nodeRepresentation = new NodeRepresentationImpl();
        return nodeRepresentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BorderedRepresentation createBorderedRepresentation() {
        BorderedRepresentationImpl borderedRepresentation = new BorderedRepresentationImpl();
        return borderedRepresentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerRepresentation createContainerRepresentation() {
        ContainerRepresentationImpl containerRepresentation = new ContainerRepresentationImpl();
        return containerRepresentation;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Diagram createDiagram() {
        DiagramImpl diagram = new DiagramImpl();
        return diagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeStyle createEdgeStyle() {
        EdgeStyleImpl edgeStyle = new EdgeStyleImpl();
        return edgeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Layout createLayout() {
        LayoutImpl layout = new LayoutImpl();
        return layout;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Color createColor() {
        ColorImpl color = new ColorImpl();
        return color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Point createPoint() {
        PointImpl point = new PointImpl();
        return point;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NodeStyle createNodeStyle() {
        NodeStyleImpl nodeStyle = new NodeStyleImpl();
        return nodeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BasicLabelStyle createBasicLabelStyle() {
        BasicLabelStyleImpl basicLabelStyle = new BasicLabelStyleImpl();
        return basicLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ContainerStyle createContainerStyle() {
        ContainerStyleImpl containerStyle = new ContainerStyleImpl();
        return containerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LabelStyle createLabelStyle() {
        LabelStyleImpl labelStyle = new LabelStyleImpl();
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Dot createDot() {
        DotImpl dot = new DotImpl();
        return dot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GaugeSection createGaugeSection() {
        GaugeSectionImpl gaugeSection = new GaugeSectionImpl();
        return gaugeSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FlatContainerStyle createFlatContainerStyle() {
        FlatContainerStyleImpl flatContainerStyle = new FlatContainerStyleImpl();
        return flatContainerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ShapeContainerStyle createShapeContainerStyle() {
        ShapeContainerStyleImpl shapeContainerStyle = new ShapeContainerStyleImpl();
        return shapeContainerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Square createSquare() {
        SquareImpl square = new SquareImpl();
        return square;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Ellipse createEllipse() {
        EllipseImpl ellipse = new EllipseImpl();
        return ellipse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Lozenge createLozenge() {
        LozengeImpl lozenge = new LozengeImpl();
        return lozenge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BundledImage createBundledImage() {
        BundledImageImpl bundledImage = new BundledImageImpl();
        return bundledImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WorkspaceImage createWorkspaceImage() {
        WorkspaceImageImpl workspaceImage = new WorkspaceImageImpl();
        return workspaceImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GaugeCompositeStyle createGaugeCompositeStyle() {
        GaugeCompositeStyleImpl gaugeCompositeStyle = new GaugeCompositeStyleImpl();
        return gaugeCompositeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Note createNote() {
        NoteImpl note = new NoteImpl();
        return note;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TestCase createTestCase() {
        TestCaseImpl testCase = new TestCaseImpl();
        return testCase;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BorderedStyle createBorderedStyle() {
        BorderedStyleImpl borderedStyle = new BorderedStyleImpl();
        return borderedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Filter createFilter() {
        FilterImpl filter = new FilterImpl();
        return filter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Layer createLayer() {
        LayerImpl layer = new LayerImpl();
        return layer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RoutingStyle createRoutingStyleFromString(EDataType eDataType, String initialValue) {
        RoutingStyle result = RoutingStyle.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertRoutingStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LabelPosition createLabelPositionFromString(EDataType eDataType, String initialValue) {
        LabelPosition result = LabelPosition.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertLabelPositionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public FontFormat createFontFormatFromString(EDataType eDataType, String initialValue) {
        FontFormat result = FontFormat.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertFontFormatToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public LabelAlignment createLabelAlignmentFromString(EDataType eDataType, String initialValue) {
        LabelAlignment result = LabelAlignment.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertLabelAlignmentToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerShape createContainerShapeFromString(EDataType eDataType, String initialValue) {
        ContainerShape result = ContainerShape.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertContainerShapeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BackgroundStyle createBackgroundStyleFromString(EDataType eDataType, String initialValue) {
        BackgroundStyle result = BackgroundStyle.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertBackgroundStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public AlignmentKind createAlignmentKindFromString(EDataType eDataType, String initialValue) {
        AlignmentKind result = AlignmentKind.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertAlignmentKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BundledImageShape createBundledImageShapeFromString(EDataType eDataType, String initialValue) {
        BundledImageShape result = BundledImageShape.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public String convertBundledImageShapeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MigrationmodelerPackage getMigrationmodelerPackage() {
        return (MigrationmodelerPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static MigrationmodelerPackage getPackage() {
        return MigrationmodelerPackage.eINSTANCE;
    }

} // MigrationmodelerFactoryImpl
