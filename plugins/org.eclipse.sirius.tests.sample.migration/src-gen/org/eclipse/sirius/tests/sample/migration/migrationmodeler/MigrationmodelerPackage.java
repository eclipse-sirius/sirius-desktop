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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerFactory
 * @model kind="package"
 * @generated
 */
public interface MigrationmodelerPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "migrationmodeler";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/tests/sample/migrationmodeler";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "migrationmodeler";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    MigrationmodelerPackage eINSTANCE = org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GraphicalElementImpl
     * <em>Graphical Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GraphicalElementImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getGraphicalElement()
     * @generated
     */
    int GRAPHICAL_ELEMENT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeImpl
     * <em>Abstract Node</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAbstractNode()
     * @generated
     */
    int ABSTRACT_NODE = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeImpl
     * <em>Node</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNode()
     * @generated
     */
    int NODE = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedImpl
     * <em>Bordered</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBordered()
     * @generated
     */
    int BORDERED = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerImpl
     * <em>Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainer()
     * @generated
     */
    int CONTAINER = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl
     * <em>Edge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEdge()
     * @generated
     */
    int EDGE = 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl
     * <em>Abstract Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAbstractRepresentation()
     * @generated
     */
    int ABSTRACT_REPRESENTATION = 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl
     * <em>Edge Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEdgeRepresentation()
     * @generated
     */
    int EDGE_REPRESENTATION = 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeRepresentationImpl
     * <em>Abstract Node Representation</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeRepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAbstractNodeRepresentation()
     * @generated
     */
    int ABSTRACT_NODE_REPRESENTATION = 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeRepresentationImpl
     * <em>Node Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeRepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNodeRepresentation()
     * @generated
     */
    int NODE_REPRESENTATION = 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedRepresentationImpl
     * <em>Bordered Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedRepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBorderedRepresentation()
     * @generated
     */
    int BORDERED_REPRESENTATION = 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerRepresentationImpl
     * <em>Container Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerRepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainerRepresentation()
     * @generated
     */
    int CONTAINER_REPRESENTATION = 12;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.RepresentationImpl
     * <em>Representation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.RepresentationImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getRepresentation()
     * @generated
     */
    int REPRESENTATION = 18;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION__NAME = 0;

    /**
     * The number of structural features of the '<em>Representation</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl
     * <em>Diagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getDiagram()
     * @generated
     */
    int DIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM__NAME = MigrationmodelerPackage.REPRESENTATION__NAME;

    /**
     * The feature id for the '<em><b>Containers</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM__CONTAINERS = MigrationmodelerPackage.REPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM__NODES = MigrationmodelerPackage.REPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Edges</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM__EDGES = MigrationmodelerPackage.REPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM__FILTERS = MigrationmodelerPackage.REPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Layers</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM__LAYERS = MigrationmodelerPackage.REPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Diagram</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_FEATURE_COUNT = MigrationmodelerPackage.REPRESENTATION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRAPHICAL_ELEMENT__ID = 0;

    /**
     * The number of structural features of the '<em>Graphical Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRAPHICAL_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE__ID = MigrationmodelerPackage.GRAPHICAL_ELEMENT__ID;

    /**
     * The number of structural features of the '<em>Abstract Node</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_FEATURE_COUNT = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE__ID = MigrationmodelerPackage.ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Node Representations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE__NODE_REPRESENTATIONS = MigrationmodelerPackage.ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Node</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED__ID = MigrationmodelerPackage.ABSTRACT_NODE__ID;

    /**
     * The feature id for the '<em><b>Bordered Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED__BORDERED_REPRESENTATIONS = MigrationmodelerPackage.ABSTRACT_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Bordered</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER__ID = MigrationmodelerPackage.GRAPHICAL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Container Representations</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER__CONTAINER_REPRESENTATIONS = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Elements</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER__ELEMENTS = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Container</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_FEATURE_COUNT = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE__ID = MigrationmodelerPackage.GRAPHICAL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Edge Representations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE__EDGE_REPRESENTATIONS = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE__SOURCE = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE__TARGET = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Edge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FEATURE_COUNT = MigrationmodelerPackage.GRAPHICAL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_REPRESENTATION__MAPPING_ID = 0;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_REPRESENTATION__LAYOUT = 1;

    /**
     * The feature id for the '<em><b>Displayed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_REPRESENTATION__DISPLAYED = 2;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_REPRESENTATION__HIDDEN = 3;

    /**
     * The feature id for the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_REPRESENTATION__PINNED = 4;

    /**
     * The number of structural features of the '
     * <em>Abstract Representation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_REPRESENTATION_FEATURE_COUNT = 5;

    /**
     * The feature id for the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__MAPPING_ID = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__LAYOUT = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT;

    /**
     * The feature id for the '<em><b>Displayed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__DISPLAYED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__HIDDEN = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN;

    /**
     * The feature id for the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__PINNED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__SOURCE = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__TARGET = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Bendpoints</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__BENDPOINTS = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Edge Representation</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_REPRESENTATION_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__MAPPING_ID = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__LAYOUT = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT;

    /**
     * The feature id for the '<em><b>Displayed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__DISPLAYED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__HIDDEN = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN;

    /**
     * The feature id for the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__PINNED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED;

    /**
     * The feature id for the '<em><b>Bordereds</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__BORDEREDS = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Abstract Node Representation</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_REPRESENTATION_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__MAPPING_ID = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__MAPPING_ID;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__LAYOUT = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__LAYOUT;

    /**
     * The feature id for the '<em><b>Displayed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__DISPLAYED = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__DISPLAYED;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__HIDDEN = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__HIDDEN;

    /**
     * The feature id for the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__PINNED = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__PINNED;

    /**
     * The feature id for the '<em><b>Bordereds</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__BORDEREDS = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE;

    /**
     * The number of structural features of the '<em>Node Representation</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_REPRESENTATION_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__MAPPING_ID = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__MAPPING_ID;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__LAYOUT = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__LAYOUT;

    /**
     * The feature id for the '<em><b>Displayed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__DISPLAYED = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__DISPLAYED;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__HIDDEN = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__HIDDEN;

    /**
     * The feature id for the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__PINNED = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__PINNED;

    /**
     * The feature id for the '<em><b>Bordereds</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__BORDEREDS = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__BORDEREDS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE;

    /**
     * The number of structural features of the '
     * <em>Bordered Representation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_REPRESENTATION_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_NODE_REPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__MAPPING_ID = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__MAPPING_ID;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__LAYOUT = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__LAYOUT;

    /**
     * The feature id for the '<em><b>Displayed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__DISPLAYED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__DISPLAYED;

    /**
     * The feature id for the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__HIDDEN = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__HIDDEN;

    /**
     * The feature id for the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__PINNED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION__PINNED;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Auto Sized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION__AUTO_SIZED = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Container Representation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_REPRESENTATION_FEATURE_COUNT = MigrationmodelerPackage.ABSTRACT_REPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl
     * <em>Edge Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEdgeStyle()
     * @generated
     */
    int EDGE_STYLE = 13;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__ROUTING_STYLE = 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__COLOR = 1;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__BEGIN_LABEL_STYLE = 2;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CENTER_LABEL_STYLE = 3;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__END_LABEL_STYLE = 4;

    /**
     * The number of structural features of the '<em>Edge Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayoutImpl
     * <em>Layout</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayoutImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLayout()
     * @generated
     */
    int LAYOUT = 14;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT__Y = 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT__WIDTH = 2;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT__HEIGHT = 3;

    /**
     * The number of structural features of the '<em>Layout</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
     * <em>Routing Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getRoutingStyle()
     * @generated
     */
    int ROUTING_STYLE = 37;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition
     * <em>Label Position</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLabelPosition()
     * @generated
     */
    int LABEL_POSITION = 38;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
     * <em>Font Format</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getFontFormat()
     * @generated
     */
    int FONT_FORMAT = 39;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
     * <em>Label Alignment</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLabelAlignment()
     * @generated
     */
    int LABEL_ALIGNMENT = 40;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
     * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainerShape()
     * @generated
     */
    int CONTAINER_SHAPE = 41;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle
     * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBackgroundStyle()
     * @generated
     */
    int BACKGROUND_STYLE = 42;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
     * <em>Alignment Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAlignmentKind()
     * @generated
     */
    int ALIGNMENT_KIND = 43;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
     * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBundledImageShape()
     * @generated
     */
    int BUNDLED_IMAGE_SHAPE = 44;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl
     * <em>Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getColor()
     * @generated
     */
    int COLOR = 15;

    /**
     * The feature id for the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLOR__RED = 0;

    /**
     * The feature id for the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLOR__GREEN = 1;

    /**
     * The feature id for the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLOR__BLUE = 2;

    /**
     * The number of structural features of the '<em>Color</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLOR_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.PointImpl
     * <em>Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.PointImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getPoint()
     * @generated
     */
    int POINT = 16;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT__Y = 1;

    /**
     * The number of structural features of the '<em>Point</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl
     * <em>Node Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNodeStyle()
     * @generated
     */
    int NODE_STYLE = 22;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerStyleImpl
     * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainerStyle()
     * @generated
     */
    int CONTAINER_STYLE = 24;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DotImpl
     * <em>Dot</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DotImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getDot()
     * @generated
     */
    int DOT = 26;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl
     * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getGaugeSection()
     * @generated
     */
    int GAUGE_SECTION = 27;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl
     * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getFlatContainerStyle()
     * @generated
     */
    int FLAT_CONTAINER_STYLE = 28;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ShapeContainerStyleImpl
     * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ShapeContainerStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getShapeContainerStyle()
     * @generated
     */
    int SHAPE_CONTAINER_STYLE = 29;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.SquareImpl
     * <em>Square</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.SquareImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getSquare()
     * @generated
     */
    int SQUARE = 30;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl
     * <em>Ellipse</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEllipse()
     * @generated
     */
    int ELLIPSE = 31;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LozengeImpl
     * <em>Lozenge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LozengeImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLozenge()
     * @generated
     */
    int LOZENGE = 32;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BundledImageImpl
     * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BundledImageImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBundledImage()
     * @generated
     */
    int BUNDLED_IMAGE = 33;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.WorkspaceImageImpl
     * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.WorkspaceImageImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getWorkspaceImage()
     * @generated
     */
    int WORKSPACE_IMAGE = 34;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeCompositeStyleImpl
     * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeCompositeStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getGaugeCompositeStyle()
     * @generated
     */
    int GAUGE_COMPOSITE_STYLE = 35;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NoteImpl
     * <em>Note</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NoteImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNote()
     * @generated
     */
    int NOTE = 36;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.TestCaseImpl
     * <em>Test Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.TestCaseImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getTestCase()
     * @generated
     */
    int TEST_CASE = 17;

    /**
     * The feature id for the '<em><b>Representations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_CASE__REPRESENTATIONS = 0;

    /**
     * The number of structural features of the '<em>Test Case</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEST_CASE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedStyleImpl
     * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBorderedStyle()
     * @generated
     */
    int BORDERED_STYLE = 19;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE = 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_COLOR = 1;

    /**
     * The number of structural features of the '<em>Bordered Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FilterImpl
     * <em>Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FilterImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getFilter()
     * @generated
     */
    int FILTER = 20;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER__ID = 0;

    /**
     * The feature id for the '<em><b>Activated</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER__ACTIVATED = 1;

    /**
     * The number of structural features of the '<em>Filter</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayerImpl
     * <em>Layer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayerImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLayer()
     * @generated
     */
    int LAYER = 21;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__ID = 0;

    /**
     * The feature id for the '<em><b>Activated</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__ACTIVATED = 1;

    /**
     * The number of structural features of the '<em>Layer</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl
     * <em>Basic Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBasicLabelStyle()
     * @generated
     */
    int BASIC_LABEL_STYLE = 23;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_SIZE = 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_FORMAT = 1;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__SHOW_ICON = 2;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__LABEL_COLOR = 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE__ICON_PATH = 4;

    /**
     * The number of structural features of the '<em>Basic Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LabelStyleImpl
     * <em>Label Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LabelStyleImpl
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLabelStyle()
     * @generated
     */
    int LABEL_STYLE = 25;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_SIZE = MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_FORMAT = MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__SHOW_ICON = MigrationmodelerPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_COLOR = MigrationmodelerPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__ICON_PATH = MigrationmodelerPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_FEATURE_COUNT = MigrationmodelerPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_SIZE = MigrationmodelerPackage.LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_FORMAT = MigrationmodelerPackage.LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__SHOW_ICON = MigrationmodelerPackage.LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_COLOR = MigrationmodelerPackage.LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__ICON_PATH = MigrationmodelerPackage.LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_COLOR = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_POSITION = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Node Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE_FEATURE_COUNT = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_SIZE = MigrationmodelerPackage.LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_FORMAT = MigrationmodelerPackage.LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__SHOW_ICON = MigrationmodelerPackage.LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_COLOR = MigrationmodelerPackage.LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__ICON_PATH = MigrationmodelerPackage.LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_COLOR = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_FEATURE_COUNT = MigrationmodelerPackage.LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__BACKGROUND_COLOR = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Dot</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Min</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MIN = 0;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MAX = 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__VALUE = 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__LABEL = 3;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__BACKGROUND_COLOR = 4;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__FOREGROUND_COLOR = 5;

    /**
     * The number of structural features of the '<em>Gauge Section</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_FEATURE_COUNT = 6;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_SIZE = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_FORMAT = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__SHOW_ICON = MigrationmodelerPackage.CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_COLOR = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__ICON_PATH = MigrationmodelerPackage.CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE = MigrationmodelerPackage.CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_COLOR = MigrationmodelerPackage.CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Background Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Flat Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_FEATURE_COUNT = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_SIZE = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_FORMAT = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHOW_ICON = MigrationmodelerPackage.CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_COLOR = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__ICON_PATH = MigrationmodelerPackage.CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE = MigrationmodelerPackage.CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_COLOR = MigrationmodelerPackage.CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHAPE = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Shape Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_FEATURE_COUNT = MigrationmodelerPackage.CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__WIDTH = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__HEIGHT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__COLOR = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Square</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Horizontal Diameter</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__HORIZONTAL_DIAMETER = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Vertical Diameter</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__VERTICAL_DIAMETER = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__COLOR = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Ellipse</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__WIDTH = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__HEIGHT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__COLOR = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Lozenge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHAPE = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__COLOR = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Bundled Image</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Workspace Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__WORKSPACE_PATH = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Workspace Image</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ALIGNMENT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sections</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SECTIONS = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Gauge Composite Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_SIZE = MigrationmodelerPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_FORMAT = MigrationmodelerPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__SHOW_ICON = MigrationmodelerPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_COLOR = MigrationmodelerPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__ICON_PATH = MigrationmodelerPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_ALIGNMENT = MigrationmodelerPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE = MigrationmodelerPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__BORDER_COLOR = MigrationmodelerPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_POSITION = MigrationmodelerPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__COLOR = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Note</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE_FEATURE_COUNT = MigrationmodelerPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node
     * <em>Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node
     * @generated
     */
    EClass getNode();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node#getNodeRepresentations
     * <em>Node Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Node Representations</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node#getNodeRepresentations()
     * @see #getNode()
     * @generated
     */
    EReference getNode_NodeRepresentations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered
     * <em>Bordered</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Bordered</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered
     * @generated
     */
    EClass getBordered();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered#getBorderedRepresentations
     * <em>Bordered Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Bordered Representations</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered#getBorderedRepresentations()
     * @see #getBordered()
     * @generated
     */
    EReference getBordered_BorderedRepresentations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container
     * @generated
     */
    EClass getContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container#getContainerRepresentations
     * <em>Container Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Container Representations</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container#getContainerRepresentations()
     * @see #getContainer()
     * @generated
     */
    EReference getContainer_ContainerRepresentations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Elements</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container#getElements()
     * @see #getContainer()
     * @generated
     */
    EReference getContainer_Elements();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement
     * <em>Graphical Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Graphical Element</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement
     * @generated
     */
    EClass getGraphicalElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement#getId()
     * @see #getGraphicalElement()
     * @generated
     */
    EAttribute getGraphicalElement_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge
     * <em>Edge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge
     * @generated
     */
    EClass getEdge();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge#getEdgeRepresentations
     * <em>Edge Representations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Edge Representations</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge#getEdgeRepresentations()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_EdgeRepresentations();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge#getSource()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Source();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge#getTarget()
     * @see #getEdge()
     * @generated
     */
    EReference getEdge_Target();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation
     * <em>Abstract Representation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Representation</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation
     * @generated
     */
    EClass getAbstractRepresentation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getMappingId
     * <em>Mapping Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Mapping Id</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getMappingId()
     * @see #getAbstractRepresentation()
     * @generated
     */
    EAttribute getAbstractRepresentation_MappingId();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getLayout
     * <em>Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getLayout()
     * @see #getAbstractRepresentation()
     * @generated
     */
    EReference getAbstractRepresentation_Layout();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isDisplayed
     * <em>Displayed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Displayed</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isDisplayed()
     * @see #getAbstractRepresentation()
     * @generated
     */
    EAttribute getAbstractRepresentation_Displayed();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isHidden
     * <em>Hidden</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Hidden</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isHidden()
     * @see #getAbstractRepresentation()
     * @generated
     */
    EAttribute getAbstractRepresentation_Hidden();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isPinned
     * <em>Pinned</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Pinned</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isPinned()
     * @see #getAbstractRepresentation()
     * @generated
     */
    EAttribute getAbstractRepresentation_Pinned();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation
     * <em>Edge Representation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Edge Representation</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation
     * @generated
     */
    EClass getEdgeRepresentation();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Source</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getSource()
     * @see #getEdgeRepresentation()
     * @generated
     */
    EReference getEdgeRepresentation_Source();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getTarget()
     * @see #getEdgeRepresentation()
     * @generated
     */
    EReference getEdgeRepresentation_Target();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getBendpoints
     * <em>Bendpoints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Bendpoints</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getBendpoints()
     * @see #getEdgeRepresentation()
     * @generated
     */
    EReference getEdgeRepresentation_Bendpoints();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getOwnedStyle()
     * @see #getEdgeRepresentation()
     * @generated
     */
    EReference getEdgeRepresentation_OwnedStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation
     * <em>Abstract Node Representation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Node Representation</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation
     * @generated
     */
    EClass getAbstractNodeRepresentation();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getBordereds
     * <em>Bordereds</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Bordereds</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getBordereds()
     * @see #getAbstractNodeRepresentation()
     * @generated
     */
    EReference getAbstractNodeRepresentation_Bordereds();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getOwnedStyle()
     * @see #getAbstractNodeRepresentation()
     * @generated
     */
    EReference getAbstractNodeRepresentation_OwnedStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation
     * <em>Node Representation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Node Representation</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation
     * @generated
     */
    EClass getNodeRepresentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation
     * <em>Bordered Representation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Bordered Representation</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation
     * @generated
     */
    EClass getBorderedRepresentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation
     * <em>Container Representation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Container Representation</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation
     * @generated
     */
    EClass getContainerRepresentation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation#getOwnedStyle()
     * @see #getContainerRepresentation()
     * @generated
     */
    EReference getContainerRepresentation_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation#isAutoSized
     * <em>Auto Sized</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Auto Sized</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation#isAutoSized()
     * @see #getContainerRepresentation()
     * @generated
     */
    EAttribute getContainerRepresentation_AutoSized();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram
     * <em>Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram
     * @generated
     */
    EClass getDiagram();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Containers</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getContainers()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_Containers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getNodes
     * <em>Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Nodes</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getNodes()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_Nodes();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getEdges
     * <em>Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Edges</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getEdges()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_Edges();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getFilters
     * <em>Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Filters</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getFilters()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_Filters();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getLayers
     * <em>Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Layers</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getLayers()
     * @see #getDiagram()
     * @generated
     */
    EReference getDiagram_Layers();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle
     * <em>Edge Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle
     * @generated
     */
    EClass getEdgeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getRoutingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_RoutingStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getColor()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_Color();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getBeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getBeginLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_BeginLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getCenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Center Label Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getCenterLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_CenterLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getEndLabelStyle
     * <em>End Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>End Label Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getEndLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_EndLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout
     * <em>Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Layout</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout
     * @generated
     */
    EClass getLayout();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getX
     * <em>X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getX()
     * @see #getLayout()
     * @generated
     */
    EAttribute getLayout_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getY
     * <em>Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getY()
     * @see #getLayout()
     * @generated
     */
    EAttribute getLayout_Y();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getWidth()
     * @see #getLayout()
     * @generated
     */
    EAttribute getLayout_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layout#getHeight()
     * @see #getLayout()
     * @generated
     */
    EAttribute getLayout_Height();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
     * @generated
     */
    EEnum getRoutingStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Label Position</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition
     * @generated
     */
    EEnum getLabelPosition();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
     * <em>Font Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Font Format</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
     * @generated
     */
    EEnum getFontFormat();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
     * @generated
     */
    EEnum getLabelAlignment();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
     * <em>Container Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Container Shape</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
     * @generated
     */
    EEnum getContainerShape();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for enum '<em>Background Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle
     * @generated
     */
    EEnum getBackgroundStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
     * <em>Alignment Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Alignment Kind</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
     * @generated
     */
    EEnum getAlignmentKind();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
     * <em>Bundled Image Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for enum '<em>Bundled Image Shape</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
     * @generated
     */
    EEnum getBundledImageShape();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color
     * @generated
     */
    EClass getColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color#getRed
     * <em>Red</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Red</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color#getRed()
     * @see #getColor()
     * @generated
     */
    EAttribute getColor_Red();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color#getGreen
     * <em>Green</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Green</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color#getGreen()
     * @see #getColor()
     * @generated
     */
    EAttribute getColor_Green();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color#getBlue
     * <em>Blue</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Blue</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Color#getBlue()
     * @see #getColor()
     * @generated
     */
    EAttribute getColor_Blue();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point
     * <em>Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Point</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point
     * @generated
     */
    EClass getPoint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point#getX
     * <em>X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point#getX()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point#getY
     * <em>Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point#getY()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_Y();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle
     * <em>Node Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle
     * @generated
     */
    EClass getNodeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle#getLabelPosition()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_LabelPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle#isHideLabelByDefault
     * <em>Hide Label By Default</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Hide Label By Default</em>
     *         '.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeStyle#isHideLabelByDefault()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_HideLabelByDefault();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle
     * <em>Basic Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Basic Label Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle
     * @generated
     */
    EClass getBasicLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelSize()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelSize();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Format</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelFormat()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_LabelFormat();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#isShowIcon
     * <em>Show Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Show Icon</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#isShowIcon()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_ShowIcon();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelColor
     * <em>Label Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Label Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelColor()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EReference getBasicLabelStyle_LabelColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getIconPath()
     * @see #getBasicLabelStyle()
     * @generated
     */
    EAttribute getBasicLabelStyle_IconPath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle
     * <em>Container Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerStyle
     * @generated
     */
    EClass getContainerStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle
     * <em>Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle
     * @generated
     */
    EClass getLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle#getLabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle#getLabelAlignment()
     * @see #getLabelStyle()
     * @generated
     */
    EAttribute getLabelStyle_LabelAlignment();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot
     * <em>Dot</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dot</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot
     * @generated
     */
    EClass getDot();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot#getBackgroundColor()
     * @see #getDot()
     * @generated
     */
    EReference getDot_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection
     * <em>Gauge Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Gauge Section</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection
     * @generated
     */
    EClass getGaugeSection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMin
     * <em>Min</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMin()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Min();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMax
     * <em>Max</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMax()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Max();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getValue()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Value();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getLabel()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Label();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getBackgroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EReference getGaugeSection_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getForegroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EReference getGaugeSection_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Flat Container Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle
     * @generated
     */
    EClass getFlatContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle#getBackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Background Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle#getBackgroundStyle()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle#getBackgroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EReference getFlatContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FlatContainerStyle#getForegroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EReference getFlatContainerStyle_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Shape Container Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle
     * @generated
     */
    EClass getShapeContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getShape()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EAttribute getShapeContainerStyle_Shape();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getBackgroundColor()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EReference getShapeContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square
     * <em>Square</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Square</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square
     * @generated
     */
    EClass getSquare();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square#getWidth()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square#getHeight()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Square#getColor()
     * @see #getSquare()
     * @generated
     */
    EReference getSquare_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse
     * <em>Ellipse</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ellipse</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse
     * @generated
     */
    EClass getEllipse();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse#getHorizontalDiameter
     * <em>Horizontal Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Horizontal Diameter</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse#getHorizontalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_HorizontalDiameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse#getVerticalDiameter
     * <em>Vertical Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Vertical Diameter</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse#getVerticalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_VerticalDiameter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Ellipse#getColor()
     * @see #getEllipse()
     * @generated
     */
    EReference getEllipse_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge
     * <em>Lozenge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Lozenge</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge
     * @generated
     */
    EClass getLozenge();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getWidth()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getHeight()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getColor()
     * @see #getLozenge()
     * @generated
     */
    EReference getLozenge_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage
     * <em>Bundled Image</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Bundled Image</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage
     * @generated
     */
    EClass getBundledImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getShape()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Shape();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getColor()
     * @see #getBundledImage()
     * @generated
     */
    EReference getBundledImage_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage
     * <em>Workspace Image</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Workspace Image</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage
     * @generated
     */
    EClass getWorkspaceImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage#getWorkspacePath
     * <em>Workspace Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Workspace Path</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.WorkspaceImage#getWorkspacePath()
     * @see #getWorkspaceImage()
     * @generated
     */
    EAttribute getWorkspaceImage_WorkspacePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Gauge Composite Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle
     * @generated
     */
    EClass getGaugeCompositeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getAlignment()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EAttribute getGaugeCompositeStyle_Alignment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getSections
     * <em>Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Sections</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getSections()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EReference getGaugeCompositeStyle_Sections();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note
     * <em>Note</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Note</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note
     * @generated
     */
    EClass getNote();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Note#getColor()
     * @see #getNote()
     * @generated
     */
    EReference getNote_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode
     * <em>Abstract Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Node</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNode
     * @generated
     */
    EClass getAbstractNode();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase
     * <em>Test Case</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Test Case</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase
     * @generated
     */
    EClass getTestCase();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase#getRepresentations
     * <em>Representations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Representations</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.TestCase#getRepresentations()
     * @see #getTestCase()
     * @generated
     */
    EReference getTestCase_Representations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation
     * <em>Representation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Representation</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation
     * @generated
     */
    EClass getRepresentation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Representation#getName()
     * @see #getRepresentation()
     * @generated
     */
    EAttribute getRepresentation_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle
     * <em>Bordered Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Bordered Style</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle
     * @generated
     */
    EClass getBorderedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderSize
     * <em>Border Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Size</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderSize()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSize();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Border Color</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderColor()
     * @see #getBorderedStyle()
     * @generated
     */
    EReference getBorderedStyle_BorderColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter
     * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Filter</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter
     * @generated
     */
    EClass getFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter#getId()
     * @see #getFilter()
     * @generated
     */
    EAttribute getFilter_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter#isActivated
     * <em>Activated</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Activated</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter#isActivated()
     * @see #getFilter()
     * @generated
     */
    EAttribute getFilter_Activated();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer
     * <em>Layer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Layer</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer
     * @generated
     */
    EClass getLayer();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer#getId()
     * @see #getLayer()
     * @generated
     */
    EAttribute getLayer_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer#isActivated
     * <em>Activated</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Activated</em>'.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer#isActivated()
     * @see #getLayer()
     * @generated
     */
    EAttribute getLayer_Activated();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    MigrationmodelerFactory getMigrationmodelerFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeImpl
         * <em>Node</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNode()
         * @generated
         */
        EClass NODE = MigrationmodelerPackage.eINSTANCE.getNode();

        /**
         * The meta object literal for the '<em><b>Node Representations</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference NODE__NODE_REPRESENTATIONS = MigrationmodelerPackage.eINSTANCE.getNode_NodeRepresentations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedImpl
         * <em>Bordered</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBordered()
         * @generated
         */
        EClass BORDERED = MigrationmodelerPackage.eINSTANCE.getBordered();

        /**
         * The meta object literal for the '
         * <em><b>Bordered Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BORDERED__BORDERED_REPRESENTATIONS = MigrationmodelerPackage.eINSTANCE.getBordered_BorderedRepresentations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerImpl
         * <em>Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainer()
         * @generated
         */
        EClass CONTAINER = MigrationmodelerPackage.eINSTANCE.getContainer();

        /**
         * The meta object literal for the '
         * <em><b>Container Representations</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER__CONTAINER_REPRESENTATIONS = MigrationmodelerPackage.eINSTANCE.getContainer_ContainerRepresentations();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER__ELEMENTS = MigrationmodelerPackage.eINSTANCE.getContainer_Elements();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GraphicalElementImpl
         * <em>Graphical Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GraphicalElementImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getGraphicalElement()
         * @generated
         */
        EClass GRAPHICAL_ELEMENT = MigrationmodelerPackage.eINSTANCE.getGraphicalElement();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GRAPHICAL_ELEMENT__ID = MigrationmodelerPackage.eINSTANCE.getGraphicalElement_Id();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl
         * <em>Edge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEdge()
         * @generated
         */
        EClass EDGE = MigrationmodelerPackage.eINSTANCE.getEdge();

        /**
         * The meta object literal for the '<em><b>Edge Representations</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EDGE__EDGE_REPRESENTATIONS = MigrationmodelerPackage.eINSTANCE.getEdge_EdgeRepresentations();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE__SOURCE = MigrationmodelerPackage.eINSTANCE.getEdge_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE__TARGET = MigrationmodelerPackage.eINSTANCE.getEdge_Target();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl
         * <em>Abstract Representation</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractRepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAbstractRepresentation()
         * @generated
         */
        EClass ABSTRACT_REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getAbstractRepresentation();

        /**
         * The meta object literal for the '<em><b>Mapping Id</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_REPRESENTATION__MAPPING_ID = MigrationmodelerPackage.eINSTANCE.getAbstractRepresentation_MappingId();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_REPRESENTATION__LAYOUT = MigrationmodelerPackage.eINSTANCE.getAbstractRepresentation_Layout();

        /**
         * The meta object literal for the '<em><b>Displayed</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_REPRESENTATION__DISPLAYED = MigrationmodelerPackage.eINSTANCE.getAbstractRepresentation_Displayed();

        /**
         * The meta object literal for the '<em><b>Hidden</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_REPRESENTATION__HIDDEN = MigrationmodelerPackage.eINSTANCE.getAbstractRepresentation_Hidden();

        /**
         * The meta object literal for the '<em><b>Pinned</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_REPRESENTATION__PINNED = MigrationmodelerPackage.eINSTANCE.getAbstractRepresentation_Pinned();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl
         * <em>Edge Representation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeRepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEdgeRepresentation()
         * @generated
         */
        EClass EDGE_REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getEdgeRepresentation();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_REPRESENTATION__SOURCE = MigrationmodelerPackage.eINSTANCE.getEdgeRepresentation_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_REPRESENTATION__TARGET = MigrationmodelerPackage.eINSTANCE.getEdgeRepresentation_Target();

        /**
         * The meta object literal for the '<em><b>Bendpoints</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_REPRESENTATION__BENDPOINTS = MigrationmodelerPackage.eINSTANCE.getEdgeRepresentation_Bendpoints();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.eINSTANCE.getEdgeRepresentation_OwnedStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeRepresentationImpl
         * <em>Abstract Node Representation</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeRepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAbstractNodeRepresentation()
         * @generated
         */
        EClass ABSTRACT_NODE_REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getAbstractNodeRepresentation();

        /**
         * The meta object literal for the '<em><b>Bordereds</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_NODE_REPRESENTATION__BORDEREDS = MigrationmodelerPackage.eINSTANCE.getAbstractNodeRepresentation_Bordereds();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_NODE_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.eINSTANCE.getAbstractNodeRepresentation_OwnedStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeRepresentationImpl
         * <em>Node Representation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeRepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNodeRepresentation()
         * @generated
         */
        EClass NODE_REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getNodeRepresentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedRepresentationImpl
         * <em>Bordered Representation</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedRepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBorderedRepresentation()
         * @generated
         */
        EClass BORDERED_REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getBorderedRepresentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerRepresentationImpl
         * <em>Container Representation</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerRepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainerRepresentation()
         * @generated
         */
        EClass CONTAINER_REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getContainerRepresentation();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_REPRESENTATION__OWNED_STYLE = MigrationmodelerPackage.eINSTANCE.getContainerRepresentation_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Auto Sized</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTAINER_REPRESENTATION__AUTO_SIZED = MigrationmodelerPackage.eINSTANCE.getContainerRepresentation_AutoSized();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl
         * <em>Diagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DiagramImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getDiagram()
         * @generated
         */
        EClass DIAGRAM = MigrationmodelerPackage.eINSTANCE.getDiagram();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM__CONTAINERS = MigrationmodelerPackage.eINSTANCE.getDiagram_Containers();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM__NODES = MigrationmodelerPackage.eINSTANCE.getDiagram_Nodes();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM__EDGES = MigrationmodelerPackage.eINSTANCE.getDiagram_Edges();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM__FILTERS = MigrationmodelerPackage.eINSTANCE.getDiagram_Filters();

        /**
         * The meta object literal for the '<em><b>Layers</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM__LAYERS = MigrationmodelerPackage.eINSTANCE.getDiagram_Layers();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl
         * <em>Edge Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EdgeStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEdgeStyle()
         * @generated
         */
        EClass EDGE_STYLE = MigrationmodelerPackage.eINSTANCE.getEdgeStyle();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__ROUTING_STYLE = MigrationmodelerPackage.eINSTANCE.getEdgeStyle_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__COLOR = MigrationmodelerPackage.eINSTANCE.getEdgeStyle_Color();

        /**
         * The meta object literal for the '<em><b>Begin Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__BEGIN_LABEL_STYLE = MigrationmodelerPackage.eINSTANCE.getEdgeStyle_BeginLabelStyle();

        /**
         * The meta object literal for the '<em><b>Center Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__CENTER_LABEL_STYLE = MigrationmodelerPackage.eINSTANCE.getEdgeStyle_CenterLabelStyle();

        /**
         * The meta object literal for the '<em><b>End Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__END_LABEL_STYLE = MigrationmodelerPackage.eINSTANCE.getEdgeStyle_EndLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayoutImpl
         * <em>Layout</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayoutImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLayout()
         * @generated
         */
        EClass LAYOUT = MigrationmodelerPackage.eINSTANCE.getLayout();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT__X = MigrationmodelerPackage.eINSTANCE.getLayout_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT__Y = MigrationmodelerPackage.eINSTANCE.getLayout_Y();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT__WIDTH = MigrationmodelerPackage.eINSTANCE.getLayout_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT__HEIGHT = MigrationmodelerPackage.eINSTANCE.getLayout_Height();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
         * <em>Routing Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getRoutingStyle()
         * @generated
         */
        EEnum ROUTING_STYLE = MigrationmodelerPackage.eINSTANCE.getRoutingStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition
         * <em>Label Position</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelPosition
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLabelPosition()
         * @generated
         */
        EEnum LABEL_POSITION = MigrationmodelerPackage.eINSTANCE.getLabelPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
         * <em>Font Format</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getFontFormat()
         * @generated
         */
        EEnum FONT_FORMAT = MigrationmodelerPackage.eINSTANCE.getFontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
         * <em>Label Alignment</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLabelAlignment()
         * @generated
         */
        EEnum LABEL_ALIGNMENT = MigrationmodelerPackage.eINSTANCE.getLabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
         * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainerShape()
         * @generated
         */
        EEnum CONTAINER_SHAPE = MigrationmodelerPackage.eINSTANCE.getContainerShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle
         * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BackgroundStyle
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBackgroundStyle()
         * @generated
         */
        EEnum BACKGROUND_STYLE = MigrationmodelerPackage.eINSTANCE.getBackgroundStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
         * <em>Alignment Kind</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAlignmentKind()
         * @generated
         */
        EEnum ALIGNMENT_KIND = MigrationmodelerPackage.eINSTANCE.getAlignmentKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
         * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBundledImageShape()
         * @generated
         */
        EEnum BUNDLED_IMAGE_SHAPE = MigrationmodelerPackage.eINSTANCE.getBundledImageShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl
         * <em>Color</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ColorImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getColor()
         * @generated
         */
        EClass COLOR = MigrationmodelerPackage.eINSTANCE.getColor();

        /**
         * The meta object literal for the '<em><b>Red</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COLOR__RED = MigrationmodelerPackage.eINSTANCE.getColor_Red();

        /**
         * The meta object literal for the '<em><b>Green</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COLOR__GREEN = MigrationmodelerPackage.eINSTANCE.getColor_Green();

        /**
         * The meta object literal for the '<em><b>Blue</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COLOR__BLUE = MigrationmodelerPackage.eINSTANCE.getColor_Blue();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.PointImpl
         * <em>Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.PointImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getPoint()
         * @generated
         */
        EClass POINT = MigrationmodelerPackage.eINSTANCE.getPoint();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute POINT__X = MigrationmodelerPackage.eINSTANCE.getPoint_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute POINT__Y = MigrationmodelerPackage.eINSTANCE.getPoint_Y();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl
         * <em>Node Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NodeStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNodeStyle()
         * @generated
         */
        EClass NODE_STYLE = MigrationmodelerPackage.eINSTANCE.getNodeStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__LABEL_POSITION = MigrationmodelerPackage.eINSTANCE.getNodeStyle_LabelPosition();

        /**
         * The meta object literal for the '
         * <em><b>Hide Label By Default</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__HIDE_LABEL_BY_DEFAULT = MigrationmodelerPackage.eINSTANCE.getNodeStyle_HideLabelByDefault();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl
         * <em>Basic Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BasicLabelStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBasicLabelStyle()
         * @generated
         */
        EClass BASIC_LABEL_STYLE = MigrationmodelerPackage.eINSTANCE.getBasicLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_SIZE = MigrationmodelerPackage.eINSTANCE.getBasicLabelStyle_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__LABEL_FORMAT = MigrationmodelerPackage.eINSTANCE.getBasicLabelStyle_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Show Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__SHOW_ICON = MigrationmodelerPackage.eINSTANCE.getBasicLabelStyle_ShowIcon();

        /**
         * The meta object literal for the '<em><b>Label Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference BASIC_LABEL_STYLE__LABEL_COLOR = MigrationmodelerPackage.eINSTANCE.getBasicLabelStyle_LabelColor();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE__ICON_PATH = MigrationmodelerPackage.eINSTANCE.getBasicLabelStyle_IconPath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerStyleImpl
         * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ContainerStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getContainerStyle()
         * @generated
         */
        EClass CONTAINER_STYLE = MigrationmodelerPackage.eINSTANCE.getContainerStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LabelStyleImpl
         * <em>Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LabelStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLabelStyle()
         * @generated
         */
        EClass LABEL_STYLE = MigrationmodelerPackage.eINSTANCE.getLabelStyle();

        /**
         * The meta object literal for the '<em><b>Label Alignment</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_STYLE__LABEL_ALIGNMENT = MigrationmodelerPackage.eINSTANCE.getLabelStyle_LabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DotImpl
         * <em>Dot</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.DotImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getDot()
         * @generated
         */
        EClass DOT = MigrationmodelerPackage.eINSTANCE.getDot();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DOT__BACKGROUND_COLOR = MigrationmodelerPackage.eINSTANCE.getDot_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl
         * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeSectionImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getGaugeSection()
         * @generated
         */
        EClass GAUGE_SECTION = MigrationmodelerPackage.eINSTANCE.getGaugeSection();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__MIN = MigrationmodelerPackage.eINSTANCE.getGaugeSection_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__MAX = MigrationmodelerPackage.eINSTANCE.getGaugeSection_Max();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__VALUE = MigrationmodelerPackage.eINSTANCE.getGaugeSection_Value();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__LABEL = MigrationmodelerPackage.eINSTANCE.getGaugeSection_Label();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GAUGE_SECTION__BACKGROUND_COLOR = MigrationmodelerPackage.eINSTANCE.getGaugeSection_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GAUGE_SECTION__FOREGROUND_COLOR = MigrationmodelerPackage.eINSTANCE.getGaugeSection_ForegroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl
         * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FlatContainerStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getFlatContainerStyle()
         * @generated
         */
        EClass FLAT_CONTAINER_STYLE = MigrationmodelerPackage.eINSTANCE.getFlatContainerStyle();

        /**
         * The meta object literal for the '<em><b>Background Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = MigrationmodelerPackage.eINSTANCE.getFlatContainerStyle_BackgroundStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = MigrationmodelerPackage.eINSTANCE.getFlatContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = MigrationmodelerPackage.eINSTANCE.getFlatContainerStyle_ForegroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ShapeContainerStyleImpl
         * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.ShapeContainerStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getShapeContainerStyle()
         * @generated
         */
        EClass SHAPE_CONTAINER_STYLE = MigrationmodelerPackage.eINSTANCE.getShapeContainerStyle();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE__SHAPE = MigrationmodelerPackage.eINSTANCE.getShapeContainerStyle_Shape();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = MigrationmodelerPackage.eINSTANCE.getShapeContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.SquareImpl
         * <em>Square</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.SquareImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getSquare()
         * @generated
         */
        EClass SQUARE = MigrationmodelerPackage.eINSTANCE.getSquare();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SQUARE__WIDTH = MigrationmodelerPackage.eINSTANCE.getSquare_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SQUARE__HEIGHT = MigrationmodelerPackage.eINSTANCE.getSquare_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SQUARE__COLOR = MigrationmodelerPackage.eINSTANCE.getSquare_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl
         * <em>Ellipse</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.EllipseImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getEllipse()
         * @generated
         */
        EClass ELLIPSE = MigrationmodelerPackage.eINSTANCE.getEllipse();

        /**
         * The meta object literal for the '<em><b>Horizontal Diameter</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ELLIPSE__HORIZONTAL_DIAMETER = MigrationmodelerPackage.eINSTANCE.getEllipse_HorizontalDiameter();

        /**
         * The meta object literal for the '<em><b>Vertical Diameter</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ELLIPSE__VERTICAL_DIAMETER = MigrationmodelerPackage.eINSTANCE.getEllipse_VerticalDiameter();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ELLIPSE__COLOR = MigrationmodelerPackage.eINSTANCE.getEllipse_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LozengeImpl
         * <em>Lozenge</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LozengeImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLozenge()
         * @generated
         */
        EClass LOZENGE = MigrationmodelerPackage.eINSTANCE.getLozenge();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LOZENGE__WIDTH = MigrationmodelerPackage.eINSTANCE.getLozenge_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LOZENGE__HEIGHT = MigrationmodelerPackage.eINSTANCE.getLozenge_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LOZENGE__COLOR = MigrationmodelerPackage.eINSTANCE.getLozenge_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BundledImageImpl
         * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BundledImageImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBundledImage()
         * @generated
         */
        EClass BUNDLED_IMAGE = MigrationmodelerPackage.eINSTANCE.getBundledImage();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BUNDLED_IMAGE__SHAPE = MigrationmodelerPackage.eINSTANCE.getBundledImage_Shape();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BUNDLED_IMAGE__COLOR = MigrationmodelerPackage.eINSTANCE.getBundledImage_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.WorkspaceImageImpl
         * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.WorkspaceImageImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getWorkspaceImage()
         * @generated
         */
        EClass WORKSPACE_IMAGE = MigrationmodelerPackage.eINSTANCE.getWorkspaceImage();

        /**
         * The meta object literal for the '<em><b>Workspace Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WORKSPACE_IMAGE__WORKSPACE_PATH = MigrationmodelerPackage.eINSTANCE.getWorkspaceImage_WorkspacePath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeCompositeStyleImpl
         * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.GaugeCompositeStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getGaugeCompositeStyle()
         * @generated
         */
        EClass GAUGE_COMPOSITE_STYLE = MigrationmodelerPackage.eINSTANCE.getGaugeCompositeStyle();

        /**
         * The meta object literal for the '<em><b>Alignment</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_COMPOSITE_STYLE__ALIGNMENT = MigrationmodelerPackage.eINSTANCE.getGaugeCompositeStyle_Alignment();

        /**
         * The meta object literal for the '<em><b>Sections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GAUGE_COMPOSITE_STYLE__SECTIONS = MigrationmodelerPackage.eINSTANCE.getGaugeCompositeStyle_Sections();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NoteImpl
         * <em>Note</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.NoteImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getNote()
         * @generated
         */
        EClass NOTE = MigrationmodelerPackage.eINSTANCE.getNote();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NOTE__COLOR = MigrationmodelerPackage.eINSTANCE.getNote_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeImpl
         * <em>Abstract Node</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.AbstractNodeImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getAbstractNode()
         * @generated
         */
        EClass ABSTRACT_NODE = MigrationmodelerPackage.eINSTANCE.getAbstractNode();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.TestCaseImpl
         * <em>Test Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.TestCaseImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getTestCase()
         * @generated
         */
        EClass TEST_CASE = MigrationmodelerPackage.eINSTANCE.getTestCase();

        /**
         * The meta object literal for the '<em><b>Representations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TEST_CASE__REPRESENTATIONS = MigrationmodelerPackage.eINSTANCE.getTestCase_Representations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.RepresentationImpl
         * <em>Representation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.RepresentationImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getRepresentation()
         * @generated
         */
        EClass REPRESENTATION = MigrationmodelerPackage.eINSTANCE.getRepresentation();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute REPRESENTATION__NAME = MigrationmodelerPackage.eINSTANCE.getRepresentation_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedStyleImpl
         * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.BorderedStyleImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getBorderedStyle()
         * @generated
         */
        EClass BORDERED_STYLE = MigrationmodelerPackage.eINSTANCE.getBorderedStyle();

        /**
         * The meta object literal for the '<em><b>Border Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE = MigrationmodelerPackage.eINSTANCE.getBorderedStyle_BorderSize();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference BORDERED_STYLE__BORDER_COLOR = MigrationmodelerPackage.eINSTANCE.getBorderedStyle_BorderColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FilterImpl
         * <em>Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.FilterImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getFilter()
         * @generated
         */
        EClass FILTER = MigrationmodelerPackage.eINSTANCE.getFilter();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FILTER__ID = MigrationmodelerPackage.eINSTANCE.getFilter_Id();

        /**
         * The meta object literal for the '<em><b>Activated</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FILTER__ACTIVATED = MigrationmodelerPackage.eINSTANCE.getFilter_Activated();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayerImpl
         * <em>Layer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.LayerImpl
         * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.impl.MigrationmodelerPackageImpl#getLayer()
         * @generated
         */
        EClass LAYER = MigrationmodelerPackage.eINSTANCE.getLayer();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYER__ID = MigrationmodelerPackage.eINSTANCE.getLayer_Id();

        /**
         * The meta object literal for the '<em><b>Activated</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYER__ACTIVATED = MigrationmodelerPackage.eINSTANCE.getLayer_Activated();

    }

} // MigrationmodelerPackage
