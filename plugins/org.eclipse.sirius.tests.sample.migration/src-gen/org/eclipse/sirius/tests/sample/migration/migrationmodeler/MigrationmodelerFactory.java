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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage
 * @generated
 */
public interface MigrationmodelerFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    MigrationmodelerFactory eINSTANCE = org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Node</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node</em>'.
     * @generated
     */
    Node createNode();

    /**
     * Returns a new object of class '<em>Bordered</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bordered</em>'.
     * @generated
     */
    Bordered createBordered();

    /**
     * Returns a new object of class '<em>Container</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container</em>'.
     * @generated
     */
    Container createContainer();

    /**
     * Returns a new object of class '<em>Edge</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge</em>'.
     * @generated
     */
    Edge createEdge();

    /**
     * Returns a new object of class '<em>Edge Representation</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Representation</em>'.
     * @generated
     */
    EdgeRepresentation createEdgeRepresentation();

    /**
     * Returns a new object of class '<em>Node Representation</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Representation</em>'.
     * @generated
     */
    NodeRepresentation createNodeRepresentation();

    /**
     * Returns a new object of class '<em>Bordered Representation</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bordered Representation</em>'.
     * @generated
     */
    BorderedRepresentation createBorderedRepresentation();

    /**
     * Returns a new object of class '<em>Container Representation</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Representation</em>'.
     * @generated
     */
    ContainerRepresentation createContainerRepresentation();

    /**
     * Returns a new object of class '<em>Diagram</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Diagram</em>'.
     * @generated
     */
    Diagram createDiagram();

    /**
     * Returns a new object of class '<em>Edge Style</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Style</em>'.
     * @generated
     */
    EdgeStyle createEdgeStyle();

    /**
     * Returns a new object of class '<em>Layout</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Layout</em>'.
     * @generated
     */
    Layout createLayout();

    /**
     * Returns a new object of class '<em>Color</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Color</em>'.
     * @generated
     */
    Color createColor();

    /**
     * Returns a new object of class '<em>Point</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Point</em>'.
     * @generated
     */
    Point createPoint();

    /**
     * Returns a new object of class '<em>Node Style</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Style</em>'.
     * @generated
     */
    NodeStyle createNodeStyle();

    /**
     * Returns a new object of class '<em>Basic Label Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Basic Label Style</em>'.
     * @generated
     */
    BasicLabelStyle createBasicLabelStyle();

    /**
     * Returns a new object of class '<em>Container Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Container Style</em>'.
     * @generated
     */
    ContainerStyle createContainerStyle();

    /**
     * Returns a new object of class '<em>Label Style</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Style</em>'.
     * @generated
     */
    LabelStyle createLabelStyle();

    /**
     * Returns a new object of class '<em>Dot</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Dot</em>'.
     * @generated
     */
    Dot createDot();

    /**
     * Returns a new object of class '<em>Gauge Section</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Gauge Section</em>'.
     * @generated
     */
    GaugeSection createGaugeSection();

    /**
     * Returns a new object of class '<em>Flat Container Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Flat Container Style</em>'.
     * @generated
     */
    FlatContainerStyle createFlatContainerStyle();

    /**
     * Returns a new object of class '<em>Shape Container Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Shape Container Style</em>'.
     * @generated
     */
    ShapeContainerStyle createShapeContainerStyle();

    /**
     * Returns a new object of class '<em>Square</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Square</em>'.
     * @generated
     */
    Square createSquare();

    /**
     * Returns a new object of class '<em>Ellipse</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Ellipse</em>'.
     * @generated
     */
    Ellipse createEllipse();

    /**
     * Returns a new object of class '<em>Lozenge</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Lozenge</em>'.
     * @generated
     */
    Lozenge createLozenge();

    /**
     * Returns a new object of class '<em>Bundled Image</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bundled Image</em>'.
     * @generated
     */
    BundledImage createBundledImage();

    /**
     * Returns a new object of class '<em>Workspace Image</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Workspace Image</em>'.
     * @generated
     */
    WorkspaceImage createWorkspaceImage();

    /**
     * Returns a new object of class '<em>Gauge Composite Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Gauge Composite Style</em>'.
     * @generated
     */
    GaugeCompositeStyle createGaugeCompositeStyle();

    /**
     * Returns a new object of class '<em>Note</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Note</em>'.
     * @generated
     */
    Note createNote();

    /**
     * Returns a new object of class '<em>Test Case</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Test Case</em>'.
     * @generated
     */
    TestCase createTestCase();

    /**
     * Returns a new object of class '<em>Bordered Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bordered Style</em>'.
     * @generated
     */
    BorderedStyle createBorderedStyle();

    /**
     * Returns a new object of class '<em>Filter</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Filter</em>'.
     * @generated
     */
    Filter createFilter();

    /**
     * Returns a new object of class '<em>Layer</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Layer</em>'.
     * @generated
     */
    Layer createLayer();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    MigrationmodelerPackage getMigrationmodelerPackage();

} // MigrationmodelerFactory
