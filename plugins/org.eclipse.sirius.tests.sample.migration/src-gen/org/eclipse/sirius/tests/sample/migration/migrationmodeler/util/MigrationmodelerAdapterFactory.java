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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase;
import org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage
 * @generated
 */
public class MigrationmodelerAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MigrationmodelerPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public MigrationmodelerAdapterFactory() {
        if (MigrationmodelerAdapterFactory.modelPackage == null) {
            MigrationmodelerAdapterFactory.modelPackage = MigrationmodelerPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == MigrationmodelerAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == MigrationmodelerAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected MigrationmodelerSwitch<Adapter> modelSwitch = new MigrationmodelerSwitch<Adapter>() {
        @Override
        public Adapter caseDiagram(Diagram object) {
            return createDiagramAdapter();
        }

        @Override
        public Adapter caseGraphicalElement(GraphicalElement object) {
            return createGraphicalElementAdapter();
        }

        @Override
        public Adapter caseAbstractNode(AbstractNode object) {
            return createAbstractNodeAdapter();
        }

        @Override
        public Adapter caseNode(Node object) {
            return createNodeAdapter();
        }

        @Override
        public Adapter caseBordered(Bordered object) {
            return createBorderedAdapter();
        }

        @Override
        public Adapter caseContainer(Container object) {
            return createContainerAdapter();
        }

        @Override
        public Adapter caseEdge(Edge object) {
            return createEdgeAdapter();
        }

        @Override
        public Adapter caseAbstractRepresentation(AbstractRepresentation object) {
            return createAbstractRepresentationAdapter();
        }

        @Override
        public Adapter caseEdgeRepresentation(EdgeRepresentation object) {
            return createEdgeRepresentationAdapter();
        }

        @Override
        public Adapter caseAbstractNodeRepresentation(AbstractNodeRepresentation object) {
            return createAbstractNodeRepresentationAdapter();
        }

        @Override
        public Adapter caseNodeRepresentation(NodeRepresentation object) {
            return createNodeRepresentationAdapter();
        }

        @Override
        public Adapter caseBorderedRepresentation(BorderedRepresentation object) {
            return createBorderedRepresentationAdapter();
        }

        @Override
        public Adapter caseContainerRepresentation(ContainerRepresentation object) {
            return createContainerRepresentationAdapter();
        }

        @Override
        public Adapter caseEdgeStyle(EdgeStyle object) {
            return createEdgeStyleAdapter();
        }

        @Override
        public Adapter caseLayout(Layout object) {
            return createLayoutAdapter();
        }

        @Override
        public Adapter caseColor(Color object) {
            return createColorAdapter();
        }

        @Override
        public Adapter casePoint(Point object) {
            return createPointAdapter();
        }

        @Override
        public Adapter caseTestCase(TestCase object) {
            return createTestCaseAdapter();
        }

        @Override
        public Adapter caseRepresentation(Representation object) {
            return createRepresentationAdapter();
        }

        @Override
        public Adapter caseBorderedStyle(BorderedStyle object) {
            return createBorderedStyleAdapter();
        }

        @Override
        public Adapter caseFilter(Filter object) {
            return createFilterAdapter();
        }

        @Override
        public Adapter caseLayer(Layer object) {
            return createLayerAdapter();
        }

        @Override
        public Adapter caseNodeStyle(NodeStyle object) {
            return createNodeStyleAdapter();
        }

        @Override
        public Adapter caseBasicLabelStyle(BasicLabelStyle object) {
            return createBasicLabelStyleAdapter();
        }

        @Override
        public Adapter caseContainerStyle(ContainerStyle object) {
            return createContainerStyleAdapter();
        }

        @Override
        public Adapter caseLabelStyle(LabelStyle object) {
            return createLabelStyleAdapter();
        }

        @Override
        public Adapter caseDot(Dot object) {
            return createDotAdapter();
        }

        @Override
        public Adapter caseGaugeSection(GaugeSection object) {
            return createGaugeSectionAdapter();
        }

        @Override
        public Adapter caseFlatContainerStyle(FlatContainerStyle object) {
            return createFlatContainerStyleAdapter();
        }

        @Override
        public Adapter caseShapeContainerStyle(ShapeContainerStyle object) {
            return createShapeContainerStyleAdapter();
        }

        @Override
        public Adapter caseSquare(Square object) {
            return createSquareAdapter();
        }

        @Override
        public Adapter caseEllipse(Ellipse object) {
            return createEllipseAdapter();
        }

        @Override
        public Adapter caseLozenge(Lozenge object) {
            return createLozengeAdapter();
        }

        @Override
        public Adapter caseBundledImage(BundledImage object) {
            return createBundledImageAdapter();
        }

        @Override
        public Adapter caseWorkspaceImage(WorkspaceImage object) {
            return createWorkspaceImageAdapter();
        }

        @Override
        public Adapter caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            return createGaugeCompositeStyleAdapter();
        }

        @Override
        public Adapter caseNote(Note object) {
            return createNoteAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node
     * <em>Node</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node
     * @generated
     */
    public Adapter createNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered
     * <em>Bordered</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered
     * @generated
     */
    public Adapter createBorderedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container
     * <em>Container</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container
     * @generated
     */
    public Adapter createContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement
     * <em>Graphical Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement
     * @generated
     */
    public Adapter createGraphicalElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge
     * <em>Edge</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge
     * @generated
     */
    public Adapter createEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation
     * <em>Abstract Representation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation
     * @generated
     */
    public Adapter createAbstractRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation
     * <em>Edge Representation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation
     * @generated
     */
    public Adapter createEdgeRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation
     * <em>Abstract Node Representation</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation
     * @generated
     */
    public Adapter createAbstractNodeRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation
     * <em>Node Representation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation
     * @generated
     */
    public Adapter createNodeRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation
     * <em>Bordered Representation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation
     * @generated
     */
    public Adapter createBorderedRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation
     * <em>Container Representation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation
     * @generated
     */
    public Adapter createContainerRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram
     * <em>Diagram</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram
     * @generated
     */
    public Adapter createDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle
     * <em>Edge Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle
     * @generated
     */
    public Adapter createEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout
     * <em>Layout</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout
     * @generated
     */
    public Adapter createLayoutAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color
     * <em>Color</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color
     * @generated
     */
    public Adapter createColorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point
     * <em>Point</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point
     * @generated
     */
    public Adapter createPointAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle
     * <em>Node Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle
     * @generated
     */
    public Adapter createNodeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle
     * <em>Basic Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle
     * @generated
     */
    public Adapter createBasicLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle
     * <em>Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle
     * @generated
     */
    public Adapter createContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle
     * <em>Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle
     * @generated
     */
    public Adapter createLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot
     * <em>Dot</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot
     * @generated
     */
    public Adapter createDotAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection
     * <em>Gauge Section</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection
     * @generated
     */
    public Adapter createGaugeSectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle
     * @generated
     */
    public Adapter createFlatContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle
     * @generated
     */
    public Adapter createShapeContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square
     * <em>Square</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square
     * @generated
     */
    public Adapter createSquareAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse
     * <em>Ellipse</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse
     * @generated
     */
    public Adapter createEllipseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge
     * <em>Lozenge</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge
     * @generated
     */
    public Adapter createLozengeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage
     * <em>Bundled Image</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage
     * @generated
     */
    public Adapter createBundledImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage
     * <em>Workspace Image</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage
     * @generated
     */
    public Adapter createWorkspaceImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle
     * @generated
     */
    public Adapter createGaugeCompositeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note
     * <em>Note</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note
     * @generated
     */
    public Adapter createNoteAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode
     * <em>Abstract Node</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode
     * @generated
     */
    public Adapter createAbstractNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase
     * <em>Test Case</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase
     * @generated
     */
    public Adapter createTestCaseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation
     * <em>Representation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation
     * @generated
     */
    public Adapter createRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle
     * <em>Bordered Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle
     * @generated
     */
    public Adapter createBorderedStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter
     * <em>Filter</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter
     * @generated
     */
    public Adapter createFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer
     * <em>Layer</em>}'. <!-- begin-user-doc --> This default implementation
     * returns null so that we can easily ignore cases; it's useful to ignore a
     * case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer
     * @generated
     */
    public Adapter createLayerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // MigrationmodelerAdapterFactory
