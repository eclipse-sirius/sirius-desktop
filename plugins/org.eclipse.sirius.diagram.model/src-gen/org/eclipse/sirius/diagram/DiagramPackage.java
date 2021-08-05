/**
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramFactory
 * @model kind="package"
 * @generated
 */
public interface DiagramPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "diagram"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "diagram"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DiagramPackage eINSTANCE = org.eclipse.sirius.diagram.impl.DiagramPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DDiagramImpl <em>DDiagram</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DDiagramImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagram()
     * @generated
     */
    int DDIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__UID = ViewpointPackage.DREPRESENTATION__UID;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__EANNOTATIONS = ViewpointPackage.DREPRESENTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Ui State</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__UI_STATE = ViewpointPackage.DREPRESENTATION__UI_STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__NAME = ViewpointPackage.DREPRESENTATION__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__DOCUMENTATION = ViewpointPackage.DREPRESENTATION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__DIAGRAM_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__DESCRIPTION = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__EDGES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__NODES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__NODE_LIST_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__CONTAINERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__CURRENT_CONCERN = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_FILTERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Activated Transient Layers</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_TRANSIENT_LAYERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__ALL_FILTERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_RULES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATE_BEHAVIORS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__FILTER_VARIABLE_HISTORY = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_LAYERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__SYNCHRONIZED = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__HIDDEN_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__IS_IN_LAYOUTING_MODE = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Is In Showing Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__IS_IN_SHOWING_MODE = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM__HEADER_HEIGHT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 19;

    /**
     * The number of structural features of the '<em>DDiagram</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 20;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl <em>DSemantic
     * Diagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDSemanticDiagram()
     * @generated
     */
    int DSEMANTIC_DIAGRAM = 1;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__UID = DiagramPackage.DDIAGRAM__UID;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__EANNOTATIONS = DiagramPackage.DDIAGRAM__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_REPRESENTATION_ELEMENTS = DiagramPackage.DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__REPRESENTATION_ELEMENTS = DiagramPackage.DDIAGRAM__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_ANNOTATION_ENTRIES = DiagramPackage.DDIAGRAM__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Ui State</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__UI_STATE = DiagramPackage.DDIAGRAM__UI_STATE;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NAME = DiagramPackage.DDIAGRAM__NAME;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DOCUMENTATION = DiagramPackage.DDIAGRAM__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_DIAGRAM_ELEMENTS = DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DIAGRAM_ELEMENTS = DiagramPackage.DDIAGRAM__DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DESCRIPTION = DiagramPackage.DDIAGRAM__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__EDGES = DiagramPackage.DDIAGRAM__EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NODES = DiagramPackage.DDIAGRAM__NODES;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NODE_LIST_ELEMENTS = DiagramPackage.DDIAGRAM__NODE_LIST_ELEMENTS;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__CONTAINERS = DiagramPackage.DDIAGRAM__CONTAINERS;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__CURRENT_CONCERN = DiagramPackage.DDIAGRAM__CURRENT_CONCERN;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_FILTERS = DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS;

    /**
     * The feature id for the '<em><b>Activated Transient Layers</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_TRANSIENT_LAYERS = DiagramPackage.DDIAGRAM__ACTIVATED_TRANSIENT_LAYERS;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ALL_FILTERS = DiagramPackage.DDIAGRAM__ALL_FILTERS;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_RULES = DiagramPackage.DDIAGRAM__ACTIVATED_RULES;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATE_BEHAVIORS = DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__FILTER_VARIABLE_HISTORY = DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_LAYERS = DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__SYNCHRONIZED = DiagramPackage.DDIAGRAM__SYNCHRONIZED;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__HIDDEN_ELEMENTS = DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__IS_IN_LAYOUTING_MODE = DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE;

    /**
     * The feature id for the '<em><b>Is In Showing Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__IS_IN_SHOWING_MODE = DiagramPackage.DDIAGRAM__IS_IN_SHOWING_MODE;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__HEADER_HEIGHT = DiagramPackage.DDIAGRAM__HEADER_HEIGHT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__TARGET = DiagramPackage.DDIAGRAM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DSemantic Diagram</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM_FEATURE_COUNT = DiagramPackage.DDIAGRAM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DDiagramElementImpl <em>DDiagram
     * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DDiagramElementImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElement()
     * @generated
     */
    int DDIAGRAM_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__UID = ViewpointPackage.DREPRESENTATION_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TARGET = ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__NAME = ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS = ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__VISIBLE = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TOOLTIP_TEXT = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__PARENT_LAYERS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__DECORATIONS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>DDiagram Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.GraphicalFilterImpl <em>Graphical
     * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.GraphicalFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGraphicalFilter()
     * @generated
     */
    int GRAPHICAL_FILTER = 3;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRAPHICAL_FILTER__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The number of structural features of the '<em>Graphical Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRAPHICAL_FILTER_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.HideFilterImpl <em>Hide Filter</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.HideFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideFilter()
     * @generated
     */
    int HIDE_FILTER = 4;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HIDE_FILTER__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The number of structural features of the '<em>Hide Filter</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HIDE_FILTER_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.HideLabelFilterImpl <em>Hide Label
     * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.HideLabelFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideLabelFilter()
     * @generated
     */
    int HIDE_LABEL_FILTER = 5;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HIDE_LABEL_FILTER__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The number of structural features of the '<em>Hide Label Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HIDE_LABEL_FILTER_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl <em>Folding Point
     * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingPointFilter()
     * @generated
     */
    int FOLDING_POINT_FILTER = 6;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOLDING_POINT_FILTER__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The number of structural features of the '<em>Folding Point Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOLDING_POINT_FILTER_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.FoldingFilterImpl <em>Folding Filter</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.FoldingFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingFilter()
     * @generated
     */
    int FOLDING_FILTER = 7;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOLDING_FILTER__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The number of structural features of the '<em>Folding Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOLDING_FILTER_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl <em>Applied
     * Composite Filters</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAppliedCompositeFilters()
     * @generated
     */
    int APPLIED_COMPOSITE_FILTERS = 8;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The feature id for the '<em><b>Composite Filter Descriptions</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Applied Composite Filters</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl <em>Absolute Bounds
     * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbsoluteBoundsFilter()
     * @generated
     */
    int ABSOLUTE_BOUNDS_FILTER = 9;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__X = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__Y = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__HEIGHT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__WIDTH = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Absolute Bounds Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.AbstractDNode <em>Abstract DNode</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.AbstractDNode
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbstractDNode()
     * @generated
     */
    int ABSTRACT_DNODE = 10;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__UID = DiagramPackage.DDIAGRAM_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TARGET = DiagramPackage.DDIAGRAM_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__NAME = DiagramPackage.DDIAGRAM_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__SEMANTIC_ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__VISIBLE = DiagramPackage.DDIAGRAM_ELEMENT__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TOOLTIP_TEXT = DiagramPackage.DDIAGRAM_ELEMENT__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__PARENT_LAYERS = DiagramPackage.DDIAGRAM_ELEMENT__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TRANSIENT_DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__GRAPHICAL_FILTERS = DiagramPackage.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__OWNED_BORDERED_NODES = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__ARRANGE_CONSTRAINTS = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Abstract DNode</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE_FEATURE_COUNT = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DNodeImpl <em>DNode</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DNodeImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNode()
     * @generated
     */
    int DNODE = 11;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__UID = DiagramPackage.ABSTRACT_DNODE__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__TARGET = DiagramPackage.ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__NAME = DiagramPackage.ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__SEMANTIC_ELEMENTS = DiagramPackage.ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__VISIBLE = DiagramPackage.ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__TOOLTIP_TEXT = DiagramPackage.ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE__PARENT_LAYERS = DiagramPackage.ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__DECORATIONS = DiagramPackage.ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__TRANSIENT_DECORATIONS = DiagramPackage.ABSTRACT_DNODE__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__GRAPHICAL_FILTERS = DiagramPackage.ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__OWNED_BORDERED_NODES = DiagramPackage.ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__ARRANGE_CONSTRAINTS = DiagramPackage.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE__OUTGOING_EDGES = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE__INCOMING_EDGES = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__WIDTH = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__HEIGHT = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__OWNED_STYLE = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__LABEL_POSITION = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__RESIZE_KIND = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__ORIGINAL_STYLE = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__ACTUAL_MAPPING = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE__CANDIDATES_MAPPING = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>DNode</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_FEATURE_COUNT = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl <em>DDiagram
     * Element Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElementContainer()
     * @generated
     */
    int DDIAGRAM_ELEMENT_CONTAINER = 12;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__UID = DiagramPackage.ABSTRACT_DNODE__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TARGET = DiagramPackage.ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__NAME = DiagramPackage.ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS = DiagramPackage.ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__VISIBLE = DiagramPackage.ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT = DiagramPackage.ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS = DiagramPackage.ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS = DiagramPackage.ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TRANSIENT_DECORATIONS = DiagramPackage.ABSTRACT_DNODE__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS = DiagramPackage.ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES = DiagramPackage.ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS = DiagramPackage.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__NODES = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__WIDTH = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__HEIGHT = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>DDiagram Element Container</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DNodeContainerImpl <em>DNode Container</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DNodeContainerImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeContainer()
     * @generated
     */
    int DNODE_CONTAINER = 13;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__UID = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TARGET = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__NAME = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__SEMANTIC_ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__VISIBLE = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TOOLTIP_TEXT = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__PARENT_LAYERS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TRANSIENT_DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__GRAPHICAL_FILTERS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_BORDERED_NODES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ARRANGE_CONSTRAINTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OUTGOING_EDGES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__INCOMING_EDGES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__NODES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NODES;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CONTAINERS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ORIGINAL_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ACTUAL_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CANDIDATES_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__WIDTH = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__HEIGHT = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CHILDREN_PRESENTATION = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DNode Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER_FEATURE_COUNT = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DNodeListImpl <em>DNode List</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DNodeListImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeList()
     * @generated
     */
    int DNODE_LIST = 14;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__UID = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__TARGET = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__NAME = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__SEMANTIC_ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__VISIBLE = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__TOOLTIP_TEXT = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__PARENT_LAYERS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__TRANSIENT_DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__GRAPHICAL_FILTERS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_BORDERED_NODES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__ARRANGE_CONSTRAINTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__OUTGOING_EDGES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__INCOMING_EDGES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__NODES = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NODES;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__CONTAINERS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__ORIGINAL_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__ACTUAL_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__CANDIDATES_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__WIDTH = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__HEIGHT = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT;

    /**
     * The feature id for the '<em><b>Owned Elements</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DNode List</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_FEATURE_COUNT = DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl <em>DNode List
     * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DNodeListElementImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeListElement()
     * @generated
     */
    int DNODE_LIST_ELEMENT = 15;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__UID = DiagramPackage.ABSTRACT_DNODE__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TARGET = DiagramPackage.ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__NAME = DiagramPackage.ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__SEMANTIC_ELEMENTS = DiagramPackage.ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__VISIBLE = DiagramPackage.ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TOOLTIP_TEXT = DiagramPackage.ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__PARENT_LAYERS = DiagramPackage.ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__DECORATIONS = DiagramPackage.ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TRANSIENT_DECORATIONS = DiagramPackage.ABSTRACT_DNODE__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__GRAPHICAL_FILTERS = DiagramPackage.ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES = DiagramPackage.ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS = DiagramPackage.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_STYLE = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ORIGINAL_STYLE = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ACTUAL_MAPPING = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__CANDIDATES_MAPPING = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>DNode List Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT_FEATURE_COUNT = DiagramPackage.ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DEdgeImpl <em>DEdge</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DEdgeImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDEdge()
     * @generated
     */
    int DEDGE = 16;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__UID = DiagramPackage.DDIAGRAM_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__TARGET = DiagramPackage.DDIAGRAM_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__NAME = DiagramPackage.DDIAGRAM_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__SEMANTIC_ELEMENTS = DiagramPackage.DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__VISIBLE = DiagramPackage.DDIAGRAM_ELEMENT__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__TOOLTIP_TEXT = DiagramPackage.DDIAGRAM_ELEMENT__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__PARENT_LAYERS = DiagramPackage.DDIAGRAM_ELEMENT__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT__DECORATIONS;

    /**
     * The feature id for the '<em><b>Transient Decorations</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__TRANSIENT_DECORATIONS = DiagramPackage.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__GRAPHICAL_FILTERS = DiagramPackage.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__OUTGOING_EDGES = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__INCOMING_EDGES = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__OWNED_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__SIZE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Node</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__SOURCE_NODE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Node</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__TARGET_NODE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__ACTUAL_MAPPING = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__ROUTING_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Is Fold</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__IS_FOLD = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Is Mock Edge</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__IS_MOCK_EDGE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__ORIGINAL_STYLE = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Path</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__PATH = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__ARRANGE_CONSTRAINTS = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Begin Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__BEGIN_LABEL = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>End Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE__END_LABEL = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>DEdge</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEDGE_FEATURE_COUNT = DiagramPackage.DDIAGRAM_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.NodeStyleImpl <em>Node Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.NodeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNodeStyle()
     * @generated
     */
    int NODE_STYLE = 17;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__UID = ViewpointPackage.LABEL_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__CUSTOM_FEATURES = ViewpointPackage.LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_SIZE = ViewpointPackage.LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_FORMAT = ViewpointPackage.LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__SHOW_ICON = ViewpointPackage.LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__ICON_PATH = ViewpointPackage.LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_COLOR = ViewpointPackage.LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_ALIGNMENT = ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__DESCRIPTION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_COLOR = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_LINE_STYLE = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__HIDE_LABEL_BY_DEFAULT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_POSITION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Node Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE_FEATURE_COUNT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 7;

    /**
     * The meta object id for the ' {@link org.eclipse.sirius.diagram.impl.DotImpl <em>Dot</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DotImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDot()
     * @generated
     */
    int DOT = 18;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DOT__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Stroke Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT__STROKE_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DOT__BACKGROUND_COLOR = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Dot</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOT_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl <em>Gauge Section</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.GaugeSectionImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeSection()
     * @generated
     */
    int GAUGE_SECTION = 19;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__UID = ViewpointPackage.CUSTOMIZABLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__CUSTOM_FEATURES = ViewpointPackage.CUSTOMIZABLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Min</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MIN = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MAX = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__VALUE = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__LABEL = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__BACKGROUND_COLOR = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__FOREGROUND_COLOR = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Gauge Section</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_FEATURE_COUNT = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.ContainerStyleImpl <em>Container Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.ContainerStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerStyle()
     * @generated
     */
    int CONTAINER_STYLE = 20;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__UID = ViewpointPackage.LABEL_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__CUSTOM_FEATURES = ViewpointPackage.LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_SIZE = ViewpointPackage.LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_FORMAT = ViewpointPackage.LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__SHOW_ICON = ViewpointPackage.LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__ICON_PATH = ViewpointPackage.LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_COLOR = ViewpointPackage.LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_ALIGNMENT = ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__DESCRIPTION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_COLOR = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_LINE_STYLE = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__HIDE_LABEL_BY_DEFAULT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Container Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_FEATURE_COUNT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl <em>Flat Container
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFlatContainerStyle()
     * @generated
     */
    int FLAT_CONTAINER_STYLE = 21;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__UID = DiagramPackage.CONTAINER_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__CUSTOM_FEATURES = DiagramPackage.CONTAINER_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_SIZE = DiagramPackage.CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_FORMAT = DiagramPackage.CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__SHOW_ICON = DiagramPackage.CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__ICON_PATH = DiagramPackage.CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_COLOR = DiagramPackage.CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_ALIGNMENT = DiagramPackage.CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__DESCRIPTION = DiagramPackage.CONTAINER_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE = DiagramPackage.CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_COLOR = DiagramPackage.CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_LINE_STYLE = DiagramPackage.CONTAINER_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.CONTAINER_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Background Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Flat Container Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_FEATURE_COUNT = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl <em>Shape Container
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getShapeContainerStyle()
     * @generated
     */
    int SHAPE_CONTAINER_STYLE = 22;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__UID = DiagramPackage.CONTAINER_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__CUSTOM_FEATURES = DiagramPackage.CONTAINER_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_SIZE = DiagramPackage.CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_FORMAT = DiagramPackage.CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHOW_ICON = DiagramPackage.CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__ICON_PATH = DiagramPackage.CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_COLOR = DiagramPackage.CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_ALIGNMENT = DiagramPackage.CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__DESCRIPTION = DiagramPackage.CONTAINER_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE = DiagramPackage.CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_COLOR = DiagramPackage.CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_LINE_STYLE = DiagramPackage.CONTAINER_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.CONTAINER_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHAPE = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Shape Container Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_FEATURE_COUNT = DiagramPackage.CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.SquareImpl <em>Square</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.SquareImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getSquare()
     * @generated
     */
    int SQUARE = 23;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__WIDTH = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__HEIGHT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE__COLOR = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Square</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SQUARE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.EllipseImpl <em>Ellipse</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.EllipseImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEllipse()
     * @generated
     */
    int ELLIPSE = 24;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Horizontal Diameter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__HORIZONTAL_DIAMETER = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Vertical Diameter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__VERTICAL_DIAMETER = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE__COLOR = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Ellipse</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELLIPSE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.LozengeImpl <em>Lozenge</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.LozengeImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLozenge()
     * @generated
     */
    int LOZENGE = 25;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__WIDTH = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__HEIGHT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE__COLOR = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Lozenge</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LOZENGE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.BundledImageImpl <em>Bundled Image</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.BundledImageImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImage()
     * @generated
     */
    int BUNDLED_IMAGE = 26;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHAPE = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__COLOR = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Provided Shape ID</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__PROVIDED_SHAPE_ID = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Bundled Image</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.WorkspaceImageImpl <em>Workspace Image</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.WorkspaceImageImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getWorkspaceImage()
     * @generated
     */
    int WORKSPACE_IMAGE = 27;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Workspace Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__WORKSPACE_PATH = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Workspace Image</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.CustomStyleImpl <em>Custom Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.CustomStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCustomStyle()
     * @generated
     */
    int CUSTOM_STYLE = 28;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__ID = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Custom Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.EdgeTargetImpl <em>Edge Target</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.EdgeTargetImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeTarget()
     * @generated
     */
    int EDGE_TARGET = 29;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TARGET__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TARGET__OUTGOING_EDGES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TARGET__INCOMING_EDGES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Edge Target</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TARGET_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl <em>Edge Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.EdgeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeStyle()
     * @generated
     */
    int EDGE_STYLE = 30;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__UID = ViewpointPackage.STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CUSTOM_FEATURES = ViewpointPackage.STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__DESCRIPTION = ViewpointPackage.STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__LINE_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SOURCE_ARROW = ViewpointPackage.STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__TARGET_ARROW = ViewpointPackage.STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__FOLDING_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SIZE = ViewpointPackage.STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__ROUTING_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__BEGIN_LABEL_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CENTER_LABEL_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__END_LABEL_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Centered</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CENTERED = ViewpointPackage.STYLE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__STROKE_COLOR = ViewpointPackage.STYLE_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Edge Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE_FEATURE_COUNT = ViewpointPackage.STYLE_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl <em>Gauge Composite
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeCompositeStyle()
     * @generated
     */
    int GAUGE_COMPOSITE_STYLE = 31;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ALIGNMENT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sections</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SECTIONS = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Gauge Composite Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl <em>Bordered Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.BorderedStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBorderedStyle()
     * @generated
     */
    int BORDERED_STYLE = 32;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__UID = ViewpointPackage.STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__CUSTOM_FEATURES = ViewpointPackage.STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__DESCRIPTION = ViewpointPackage.STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE = ViewpointPackage.STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = ViewpointPackage.STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_COLOR = ViewpointPackage.STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_LINE_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Bordered Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_FEATURE_COUNT = ViewpointPackage.STYLE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.NoteImpl <em>Note</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.NoteImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNote()
     * @generated
     */
    int NOTE = 33;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__UID = DiagramPackage.NODE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__CUSTOM_FEATURES = DiagramPackage.NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_SIZE = DiagramPackage.NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_FORMAT = DiagramPackage.NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__SHOW_ICON = DiagramPackage.NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__ICON_PATH = DiagramPackage.NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_COLOR = DiagramPackage.NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_ALIGNMENT = DiagramPackage.NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__DESCRIPTION = DiagramPackage.NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE = DiagramPackage.NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '<em><b>Border Size Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__BORDER_COLOR = DiagramPackage.NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Border Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NOTE__BORDER_LINE_STYLE = DiagramPackage.NODE_STYLE__BORDER_LINE_STYLE;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__LABEL_POSITION = DiagramPackage.NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE__COLOR = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Note</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NOTE_FEATURE_COUNT = DiagramPackage.NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl <em>Filter Variable
     * History</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFilterVariableHistory()
     * @generated
     */
    int FILTER_VARIABLE_HISTORY = 34;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Owned Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY__OWNED_VALUES = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Filter Variable History</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.CollapseFilterImpl <em>Collapse Filter</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.CollapseFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCollapseFilter()
     * @generated
     */
    int COLLAPSE_FILTER = 35;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__UID = DiagramPackage.GRAPHICAL_FILTER__UID;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__WIDTH = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__HEIGHT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Collapse Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER_FEATURE_COUNT = DiagramPackage.GRAPHICAL_FILTER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl <em>Indirectly
     * Collapse Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getIndirectlyCollapseFilter()
     * @generated
     */
    int INDIRECTLY_COLLAPSE_FILTER = 36;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__UID = DiagramPackage.COLLAPSE_FILTER__UID;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__WIDTH = DiagramPackage.COLLAPSE_FILTER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__HEIGHT = DiagramPackage.COLLAPSE_FILTER__HEIGHT;

    /**
     * The number of structural features of the '<em>Indirectly Collapse Filter</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER_FEATURE_COUNT = DiagramPackage.COLLAPSE_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl <em>Begin Label
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBeginLabelStyle()
     * @generated
     */
    int BEGIN_LABEL_STYLE = 37;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__UID = ViewpointPackage.BASIC_LABEL_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The number of structural features of the '<em>Begin Label Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl <em>Center Label
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCenterLabelStyle()
     * @generated
     */
    int CENTER_LABEL_STYLE = 38;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__UID = ViewpointPackage.BASIC_LABEL_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The number of structural features of the '<em>Center Label Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.EndLabelStyleImpl <em>End Label Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.EndLabelStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEndLabelStyle()
     * @generated
     */
    int END_LABEL_STYLE = 39;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__UID = ViewpointPackage.BASIC_LABEL_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The number of structural features of the '<em>End Label Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl <em>Bracket Edge
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBracketEdgeStyle()
     * @generated
     */
    int BRACKET_EDGE_STYLE = 40;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__UID = DiagramPackage.EDGE_STYLE__UID;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CUSTOM_FEATURES = DiagramPackage.EDGE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__DESCRIPTION = DiagramPackage.EDGE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__LINE_STYLE = DiagramPackage.EDGE_STYLE__LINE_STYLE;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__SOURCE_ARROW = DiagramPackage.EDGE_STYLE__SOURCE_ARROW;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__TARGET_ARROW = DiagramPackage.EDGE_STYLE__TARGET_ARROW;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__FOLDING_STYLE = DiagramPackage.EDGE_STYLE__FOLDING_STYLE;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__SIZE = DiagramPackage.EDGE_STYLE__SIZE;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__ROUTING_STYLE = DiagramPackage.EDGE_STYLE__ROUTING_STYLE;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__BEGIN_LABEL_STYLE = DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CENTER_LABEL_STYLE = DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__END_LABEL_STYLE = DiagramPackage.EDGE_STYLE__END_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>Centered</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CENTERED = DiagramPackage.EDGE_STYLE__CENTERED;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__STROKE_COLOR = DiagramPackage.EDGE_STYLE__STROKE_COLOR;

    /**
     * The number of structural features of the '<em>Bracket Edge Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_FEATURE_COUNT = DiagramPackage.EDGE_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
     * <em>Computed Style Description Registry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getComputedStyleDescriptionRegistry()
     * @generated
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY = 41;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The feature id for the '<em><b>Computed Style Descriptions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Computed Style Description Registry</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl <em>Drag And Drop
     * Target</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDragAndDropTarget()
     * @generated
     */
    int DRAG_AND_DROP_TARGET = 42;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The number of structural features of the '<em>Drag And Drop Target</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.HideLabelCapabilityStyle <em>Hide Label Capability
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.HideLabelCapabilityStyle
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideLabelCapabilityStyle()
     * @generated
     */
    int HIDE_LABEL_CAPABILITY_STYLE = 43;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT = 0;

    /**
     * The number of structural features of the '<em>Hide Label Capability Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HIDE_LABEL_CAPABILITY_STYLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.VariableValueImpl <em>Variable Value</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.VariableValueImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getVariableValue()
     * @generated
     */
    int VARIABLE_VALUE = 44;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VARIABLE_VALUE__UID = ViewpointPackage.IDENTIFIED_ELEMENT__UID;

    /**
     * The number of structural features of the '<em>Variable Value</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VARIABLE_VALUE_FEATURE_COUNT = ViewpointPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.TypedVariableValueImpl <em>Typed Variable
     * Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.TypedVariableValueImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getTypedVariableValue()
     * @generated
     */
    int TYPED_VARIABLE_VALUE = 45;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TYPED_VARIABLE_VALUE__UID = DiagramPackage.VARIABLE_VALUE__UID;

    /**
     * The feature id for the '<em><b>Variable Definition</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION = DiagramPackage.VARIABLE_VALUE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TYPED_VARIABLE_VALUE__VALUE = DiagramPackage.VARIABLE_VALUE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Typed Variable Value</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TYPED_VARIABLE_VALUE_FEATURE_COUNT = DiagramPackage.VARIABLE_VALUE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.impl.EObjectVariableValueImpl <em>EObject Variable
     * Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.impl.EObjectVariableValueImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEObjectVariableValue()
     * @generated
     */
    int EOBJECT_VARIABLE_VALUE = 46;

    /**
     * The feature id for the '<em><b>Uid</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EOBJECT_VARIABLE_VALUE__UID = DiagramPackage.VARIABLE_VALUE__UID;

    /**
     * The feature id for the '<em><b>Variable Definition</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EOBJECT_VARIABLE_VALUE__VARIABLE_DEFINITION = DiagramPackage.VARIABLE_VALUE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EOBJECT_VARIABLE_VALUE__MODEL_ELEMENT = DiagramPackage.VARIABLE_VALUE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>EObject Variable Value</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EOBJECT_VARIABLE_VALUE_FEATURE_COUNT = DiagramPackage.VARIABLE_VALUE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.ContainerLayout <em>Container Layout</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerLayout()
     * @generated
     */
    int CONTAINER_LAYOUT = 47;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.LabelPosition <em>Label Position</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLabelPosition()
     * @generated
     */
    int LABEL_POSITION = 48;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.ContainerShape <em>Container Shape</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.ContainerShape
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerShape()
     * @generated
     */
    int CONTAINER_SHAPE = 49;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.BackgroundStyle <em>Background Style</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.BackgroundStyle
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBackgroundStyle()
     * @generated
     */
    int BACKGROUND_STYLE = 50;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.BundledImageShape <em>Bundled Image Shape</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.BundledImageShape
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImageShape()
     * @generated
     */
    int BUNDLED_IMAGE_SHAPE = 51;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.LineStyle <em>Line Style</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 52;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.EdgeArrows <em>Edge Arrows</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeArrows()
     * @generated
     */
    int EDGE_ARROWS = 53;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.EdgeRouting <em>Edge Routing</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeRouting()
     * @generated
     */
    int EDGE_ROUTING = 54;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.AlignmentKind <em>Alignment Kind</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.AlignmentKind
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAlignmentKind()
     * @generated
     */
    int ALIGNMENT_KIND = 55;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.ResizeKind <em>Resize Kind</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getResizeKind()
     * @generated
     */
    int RESIZE_KIND = 56;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.ArrangeConstraint <em>Arrange Constraint</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.ArrangeConstraint
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getArrangeConstraint()
     * @generated
     */
    int ARRANGE_CONSTRAINT = 57;

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.DDiagram <em>DDiagram</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DDiagram</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram
     * @generated
     */
    EClass getDDiagram();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getOwnedDiagramElements <em>Owned Diagram Elements</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getOwnedDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_OwnedDiagramElements();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getDiagramElements
     * <em>Diagram Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Diagram Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_DiagramElements();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DDiagram#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getDescription()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Description();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getEdges
     * <em>Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getEdges()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Edges();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getNodes
     * <em>Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getNodes()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Nodes();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getNodeListElements
     * <em>Node List Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Node List Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getNodeListElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_NodeListElements();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getContainers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Containers();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DDiagram#getCurrentConcern
     * <em>Current Concern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Current Concern</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getCurrentConcern()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_CurrentConcern();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getActivatedFilters
     * <em>Activated Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Activated Filters</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedFilters();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.DDiagram#getActivatedTransientLayers <em>Activated Transient Layers</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Activated Transient Layers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedTransientLayers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedTransientLayers();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getAllFilters <em>All
     * Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Filters</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getAllFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_AllFilters();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getActivatedRules
     * <em>Activated Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Activated Rules</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedRules()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedRules();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getActivateBehaviors
     * <em>Activate Behaviors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Activate Behaviors</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivateBehaviors()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivateBehaviors();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DDiagram#getFilterVariableHistory <em>Filter Variable History</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getFilterVariableHistory()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_FilterVariableHistory();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getActivatedLayers
     * <em>Activated Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Activated Layers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedLayers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedLayers();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagram#isSynchronized
     * <em>Synchronized</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Synchronized</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#isSynchronized()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_Synchronized();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagram#getHiddenElements
     * <em>Hidden Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Hidden Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getHiddenElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_HiddenElements();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagram#isIsInLayoutingMode <em>Is
     * In Layouting Mode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is In Layouting Mode</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#isIsInLayoutingMode()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_IsInLayoutingMode();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagram#isIsInShowingMode <em>Is In
     * Showing Mode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is In Showing Mode</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#isIsInShowingMode()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_IsInShowingMode();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagram#getHeaderHeight <em>Header
     * Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Header Height</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getHeaderHeight()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_HeaderHeight();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DSemanticDiagram <em>DSemantic
     * Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DSemantic Diagram</em>'.
     * @see org.eclipse.sirius.diagram.DSemanticDiagram
     * @generated
     */
    EClass getDSemanticDiagram();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DDiagramElement <em>DDiagram Element</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DDiagram Element</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement
     * @generated
     */
    EClass getDDiagramElement();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagramElement#isVisible
     * <em>Visible</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#isVisible()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_Visible();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagramElement#getTooltipText
     * <em>Tooltip Text</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Tooltip Text</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getTooltipText()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_TooltipText();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DDiagramElement#getParentLayers
     * <em>Parent Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Parent Layers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getParentLayers()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_ParentLayers();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.DDiagramElement#getDecorations <em>Decorations</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Decorations</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getDecorations()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_Decorations();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getTransientDecorations <em>Transient Decorations</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Transient Decorations</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getTransientDecorations()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_TransientDecorations();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.DDiagramElement#getDiagramElementMapping <em>Diagram Element Mapping</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Diagram Element Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getDiagramElementMapping()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_DiagramElementMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getGraphicalFilters <em>Graphical Filters</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list ' <em>Graphical Filters</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getGraphicalFilters()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_GraphicalFilters();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.GraphicalFilter <em>Graphical Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Graphical Filter</em>'.
     * @see org.eclipse.sirius.diagram.GraphicalFilter
     * @generated
     */
    EClass getGraphicalFilter();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.HideFilter <em>Hide Filter</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hide Filter</em>'.
     * @see org.eclipse.sirius.diagram.HideFilter
     * @generated
     */
    EClass getHideFilter();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.HideLabelFilter <em>Hide Label
     * Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hide Label Filter</em>'.
     * @see org.eclipse.sirius.diagram.HideLabelFilter
     * @generated
     */
    EClass getHideLabelFilter();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.FoldingPointFilter <em>Folding Point
     * Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Folding Point Filter</em>'.
     * @see org.eclipse.sirius.diagram.FoldingPointFilter
     * @generated
     */
    EClass getFoldingPointFilter();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.FoldingFilter <em>Folding Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Folding Filter</em>'.
     * @see org.eclipse.sirius.diagram.FoldingFilter
     * @generated
     */
    EClass getFoldingFilter();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.AppliedCompositeFilters <em>Applied
     * Composite Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Applied Composite Filters</em>'.
     * @see org.eclipse.sirius.diagram.AppliedCompositeFilters
     * @generated
     */
    EClass getAppliedCompositeFilters();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.AppliedCompositeFilters#getCompositeFilterDescriptions <em>Composite Filter
     * Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Composite Filter Descriptions</em>'.
     * @see org.eclipse.sirius.diagram.AppliedCompositeFilters#getCompositeFilterDescriptions()
     * @see #getAppliedCompositeFilters()
     * @generated
     */
    EReference getAppliedCompositeFilters_CompositeFilterDescriptions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter <em>Absolute Bounds
     * Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Absolute Bounds Filter</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter
     * @generated
     */
    EClass getAbsoluteBoundsFilter();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getX
     * <em>X</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getX()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_X();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getY
     * <em>Y</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getY()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Y();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getHeight()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Height();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getWidth()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Width();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.AbstractDNode <em>Abstract DNode</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract DNode</em>'.
     * @see org.eclipse.sirius.diagram.AbstractDNode
     * @generated
     */
    EClass getAbstractDNode();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.AbstractDNode#getOwnedBorderedNodes <em>Owned Bordered Nodes</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Bordered Nodes</em>'.
     * @see org.eclipse.sirius.diagram.AbstractDNode#getOwnedBorderedNodes()
     * @see #getAbstractDNode()
     * @generated
     */
    EReference getAbstractDNode_OwnedBorderedNodes();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.diagram.AbstractDNode#getArrangeConstraints <em>Arrange Constraints</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list ' <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.diagram.AbstractDNode#getArrangeConstraints()
     * @see #getAbstractDNode()
     * @generated
     */
    EAttribute getAbstractDNode_ArrangeConstraints();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DNode <em>DNode</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DNode</em>'.
     * @see org.eclipse.sirius.diagram.DNode
     * @generated
     */
    EClass getDNode();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DNode#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getWidth()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Width();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DNode#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getHeight()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Height();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.diagram.DNode#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getOwnedStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OwnedStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DNode#getLabelPosition <em>Label
     * Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getLabelPosition()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_LabelPosition();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DNode#getResizeKind <em>Resize
     * Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getResizeKind()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_ResizeKind();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DNode#getOriginalStyle <em>Original
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getOriginalStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OriginalStyle();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DNode#getActualMapping <em>Actual
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getActualMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_ActualMapping();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DNode#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getCandidatesMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_CandidatesMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DDiagramElementContainer <em>DDiagram
     * Element Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DDiagram Element Container</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer
     * @generated
     */
    EClass getDDiagramElementContainer();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getNodes <em>Nodes</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getNodes()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getContainers <em>Containers</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getContainers()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Containers();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getElements <em>Elements</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getElements()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Elements();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOwnedStyle <em>Owned Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getOwnedStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OwnedStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOriginalStyle <em>Original Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getOriginalStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getActualMapping <em>Actual Mapping</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getActualMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_ActualMapping();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getCandidatesMapping <em>Candidates Mapping</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getCandidatesMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_CandidatesMapping();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getWidth()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EAttribute getDDiagramElementContainer_Width();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DDiagramElementContainer#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getHeight()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EAttribute getDDiagramElementContainer_Height();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DNodeContainer <em>DNode Container</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DNode Container</em>'.
     * @see org.eclipse.sirius.diagram.DNodeContainer
     * @generated
     */
    EClass getDNodeContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DNodeContainer#getOwnedDiagramElements <em>Owned Diagram Elements</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.diagram.DNodeContainer#getOwnedDiagramElements()
     * @see #getDNodeContainer()
     * @generated
     */
    EReference getDNodeContainer_OwnedDiagramElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DNodeContainer#getChildrenPresentation <em>Children Presentation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Children Presentation</em>'.
     * @see org.eclipse.sirius.diagram.DNodeContainer#getChildrenPresentation()
     * @see #getDNodeContainer()
     * @generated
     */
    EAttribute getDNodeContainer_ChildrenPresentation();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.DNodeList <em>DNode List</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DNode List</em>'.
     * @see org.eclipse.sirius.diagram.DNodeList
     * @generated
     */
    EClass getDNodeList();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.DNodeList#getOwnedElements <em>Owned Elements</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Elements</em>'.
     * @see org.eclipse.sirius.diagram.DNodeList#getOwnedElements()
     * @see #getDNodeList()
     * @generated
     */
    EReference getDNodeList_OwnedElements();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DNodeListElement <em>DNode List
     * Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DNode List Element</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement
     * @generated
     */
    EClass getDNodeListElement();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.DNodeListElement#getOwnedStyle <em>Owned Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getOwnedStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OwnedStyle();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DNodeListElement#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getOriginalStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OriginalStyle();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DNodeListElement#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getActualMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DNodeListElement#getCandidatesMapping <em>Candidates Mapping</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list ' <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getCandidatesMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_CandidatesMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DEdge <em>DEdge</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>DEdge</em>'.
     * @see org.eclipse.sirius.diagram.DEdge
     * @generated
     */
    EClass getDEdge();

    /**
     * Returns the meta object for the containment reference '{@link org.eclipse.sirius.diagram.DEdge#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getOwnedStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OwnedStyle();

    /**
     * Returns the meta object for the attribute ' {@link org.eclipse.sirius.diagram.DEdge#getSize <em>Size</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getSize()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_Size();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DEdge#getSourceNode <em>Source
     * Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Source Node</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getSourceNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_SourceNode();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DEdge#getTargetNode <em>Target
     * Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target Node</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getTargetNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_TargetNode();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DEdge#getActualMapping <em>Actual
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getActualMapping()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_ActualMapping();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DEdge#getRoutingStyle <em>Routing
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getRoutingStyle()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_RoutingStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DEdge#isIsFold <em>Is Fold</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Fold</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#isIsFold()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsFold();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DEdge#isIsMockEdge <em>Is Mock
     * Edge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Mock Edge</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#isIsMockEdge()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsMockEdge();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.DEdge#getOriginalStyle <em>Original
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getOriginalStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OriginalStyle();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.DEdge#getPath <em>Path</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Path</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getPath()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_Path();

    /**
     * Returns the meta object for the attribute list '{@link org.eclipse.sirius.diagram.DEdge#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getArrangeConstraints()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_ArrangeConstraints();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DEdge#getBeginLabel <em>Begin
     * Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Begin Label</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getBeginLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_BeginLabel();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.DEdge#getEndLabel <em>End
     * Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>End Label</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getEndLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_EndLabel();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Style</em>'.
     * @see org.eclipse.sirius.diagram.NodeStyle
     * @generated
     */
    EClass getNodeStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.NodeStyle#getLabelPosition <em>Label
     * Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.diagram.NodeStyle#getLabelPosition()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_LabelPosition();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.Dot <em>Dot</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dot</em>'.
     * @see org.eclipse.sirius.diagram.Dot
     * @generated
     */
    EClass getDot();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Dot#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.Dot#getBackgroundColor()
     * @see #getDot()
     * @generated
     */
    EAttribute getDot_BackgroundColor();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.Dot#getStrokeSizeComputationExpression <em>Stroke Size Computation
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Stroke Size Computation Expression</em>'.
     * @see org.eclipse.sirius.diagram.Dot#getStrokeSizeComputationExpression()
     * @see #getDot()
     * @generated
     */
    EAttribute getDot_StrokeSizeComputationExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.GaugeSection <em>Gauge Section</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Gauge Section</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection
     * @generated
     */
    EClass getGaugeSection();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.GaugeSection#getMin <em>Min</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getMin()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Min();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.GaugeSection#getMax <em>Max</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getMax()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Max();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.GaugeSection#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getValue()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Value();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.GaugeSection#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getLabel()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Label();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getBackgroundColor <em>Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getBackgroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getForegroundColor <em>Foreground Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getForegroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_ForegroundColor();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.ContainerStyle <em>Container Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Style</em>'.
     * @see org.eclipse.sirius.diagram.ContainerStyle
     * @generated
     */
    EClass getContainerStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.FlatContainerStyle <em>Flat Container
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Flat Container Style</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle
     * @generated
     */
    EClass getFlatContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundStyle <em>Background Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Background Style</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundStyle()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundColor <em>Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getForegroundColor <em>Foreground Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle#getForegroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_ForegroundColor();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.ShapeContainerStyle <em>Shape Container
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Shape Container Style</em>'.
     * @see org.eclipse.sirius.diagram.ShapeContainerStyle
     * @generated
     */
    EClass getShapeContainerStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.ShapeContainerStyle#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.diagram.ShapeContainerStyle#getShape()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EAttribute getShapeContainerStyle_Shape();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.ShapeContainerStyle#getBackgroundColor <em>Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.ShapeContainerStyle#getBackgroundColor()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EAttribute getShapeContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.Square <em>Square</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Square</em>'.
     * @see org.eclipse.sirius.diagram.Square
     * @generated
     */
    EClass getSquare();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Square#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.Square#getWidth()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Width();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Square#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.Square#getHeight()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Height();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Square#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Square#getColor()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Color();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.Ellipse <em>Ellipse</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ellipse</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse
     * @generated
     */
    EClass getEllipse();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Ellipse#getHorizontalDiameter
     * <em>Horizontal Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Horizontal Diameter</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse#getHorizontalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_HorizontalDiameter();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Ellipse#getVerticalDiameter
     * <em>Vertical Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Vertical Diameter</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse#getVerticalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_VerticalDiameter();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Ellipse#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse#getColor()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_Color();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.Lozenge <em>Lozenge</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Lozenge</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge
     * @generated
     */
    EClass getLozenge();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Lozenge#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge#getWidth()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Width();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Lozenge#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge#getHeight()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Height();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Lozenge#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge#getColor()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Color();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.BundledImage <em>Bundled Image</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Bundled Image</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage
     * @generated
     */
    EClass getBundledImage();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.BundledImage#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage#getShape()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Shape();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.BundledImage#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage#getColor()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Color();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.BundledImage#getProvidedShapeID
     * <em>Provided Shape ID</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Provided Shape ID</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage#getProvidedShapeID()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_ProvidedShapeID();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.WorkspaceImage <em>Workspace Image</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Workspace Image</em>'.
     * @see org.eclipse.sirius.diagram.WorkspaceImage
     * @generated
     */
    EClass getWorkspaceImage();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath
     * <em>Workspace Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Workspace Path</em>'.
     * @see org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath()
     * @see #getWorkspaceImage()
     * @generated
     */
    EAttribute getWorkspaceImage_WorkspacePath();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.CustomStyle <em>Custom Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Style</em>'.
     * @see org.eclipse.sirius.diagram.CustomStyle
     * @generated
     */
    EClass getCustomStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.CustomStyle#getId <em>Id</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.CustomStyle#getId()
     * @see #getCustomStyle()
     * @generated
     */
    EAttribute getCustomStyle_Id();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.EdgeTarget <em>Edge Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Target</em>'.
     * @see org.eclipse.sirius.diagram.EdgeTarget
     * @generated
     */
    EClass getEdgeTarget();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.EdgeTarget#getOutgoingEdges
     * <em>Outgoing Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Outgoing Edges</em>'.
     * @see org.eclipse.sirius.diagram.EdgeTarget#getOutgoingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_OutgoingEdges();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.EdgeTarget#getIncomingEdges
     * <em>Incoming Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Incoming Edges</em>'.
     * @see org.eclipse.sirius.diagram.EdgeTarget#getIncomingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_IncomingEdges();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle
     * @generated
     */
    EClass getEdgeStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getStrokeColor <em>Stroke
     * Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Stroke Color</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getStrokeColor()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_StrokeColor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getLineStyle <em>Line
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getLineStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_LineStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getSourceArrow <em>Source
     * Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Source Arrow</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getSourceArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_SourceArrow();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getTargetArrow <em>Target
     * Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Arrow</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getTargetArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_TargetArrow();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getFoldingStyle
     * <em>Folding Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getFoldingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_FoldingStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getSize <em>Size</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getSize()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_Size();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getRoutingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_RoutingStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.EdgeStyle#getBeginLabelStyle <em>Begin Label Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getBeginLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_BeginLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getCenterLabelStyle <em>Center Label Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Center Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getCenterLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_CenterLabelStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.EdgeStyle#getEndLabelStyle <em>End Label Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>End Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getEndLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_EndLabelStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.EdgeStyle#getCentered
     * <em>Centered</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Centered</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getCentered()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_Centered();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.GaugeCompositeStyle <em>Gauge Composite
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Gauge Composite Style</em>'.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle
     * @generated
     */
    EClass getGaugeCompositeStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle#getAlignment()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EAttribute getGaugeCompositeStyle_Alignment();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getSections <em>Sections</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sections</em>'.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle#getSections()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EReference getGaugeCompositeStyle_Sections();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.BorderedStyle <em>Bordered Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Bordered Style</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle
     * @generated
     */
    EClass getBorderedStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSize
     * <em>Border Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Size</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderSize()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSize();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSizeComputationExpression <em>Border Size Computation
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Size Computation Expression</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderSizeComputationExpression()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSizeComputationExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.BorderedStyle#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Color</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderColor()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderColor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.BorderedStyle#getBorderLineStyle
     * <em>Border Line Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Line Style</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderLineStyle()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderLineStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.Note <em>Note</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Note</em>'.
     * @see org.eclipse.sirius.diagram.Note
     * @generated
     */
    EClass getNote();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.Note#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Note#getColor()
     * @see #getNote()
     * @generated
     */
    EAttribute getNote_Color();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.FilterVariableHistory <em>Filter Variable
     * History</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableHistory
     * @generated
     */
    EClass getFilterVariableHistory();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.FilterVariableHistory#getOwnedValues <em>Owned Values</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Values</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableHistory#getOwnedValues()
     * @see #getFilterVariableHistory()
     * @generated
     */
    EReference getFilterVariableHistory_OwnedValues();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.CollapseFilter <em>Collapse Filter</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Collapse Filter</em>'.
     * @see org.eclipse.sirius.diagram.CollapseFilter
     * @generated
     */
    EClass getCollapseFilter();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.CollapseFilter#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.CollapseFilter#getWidth()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Width();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.CollapseFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.CollapseFilter#getHeight()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Height();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.IndirectlyCollapseFilter <em>Indirectly
     * Collapse Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Indirectly Collapse Filter</em>'.
     * @see org.eclipse.sirius.diagram.IndirectlyCollapseFilter
     * @generated
     */
    EClass getIndirectlyCollapseFilter();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.BeginLabelStyle <em>Begin Label
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.diagram.BeginLabelStyle
     * @generated
     */
    EClass getBeginLabelStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.CenterLabelStyle <em>Center Label
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Center Label Style</em>'.
     * @see org.eclipse.sirius.diagram.CenterLabelStyle
     * @generated
     */
    EClass getCenterLabelStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.EndLabelStyle <em>End Label Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>End Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EndLabelStyle
     * @generated
     */
    EClass getEndLabelStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.BracketEdgeStyle <em>Bracket Edge
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Bracket Edge Style</em>'.
     * @see org.eclipse.sirius.diagram.BracketEdgeStyle
     * @generated
     */
    EClass getBracketEdgeStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Computed Style Description Registry</em>'.
     * @see org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry
     * @generated
     */
    EClass getComputedStyleDescriptionRegistry();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions <em>Computed
     * Style Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Computed Style Descriptions</em>'.
     * @see org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions()
     * @see #getComputedStyleDescriptionRegistry()
     * @generated
     */
    EReference getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.DragAndDropTarget <em>Drag And Drop
     * Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Drag And Drop Target</em>'.
     * @see org.eclipse.sirius.diagram.DragAndDropTarget
     * @generated
     */
    EClass getDragAndDropTarget();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.HideLabelCapabilityStyle <em>Hide Label
     * Capability Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hide Label Capability Style</em>'.
     * @see org.eclipse.sirius.diagram.HideLabelCapabilityStyle
     * @generated
     */
    EClass getHideLabelCapabilityStyle();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.HideLabelCapabilityStyle#isHideLabelByDefault <em>Hide Label By
     * Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Hide Label By Default</em>'.
     * @see org.eclipse.sirius.diagram.HideLabelCapabilityStyle#isHideLabelByDefault()
     * @see #getHideLabelCapabilityStyle()
     * @generated
     */
    EAttribute getHideLabelCapabilityStyle_HideLabelByDefault();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.VariableValue <em>Variable Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Variable Value</em>'.
     * @see org.eclipse.sirius.diagram.VariableValue
     * @generated
     */
    EClass getVariableValue();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.TypedVariableValue <em>Typed Variable
     * Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Typed Variable Value</em>'.
     * @see org.eclipse.sirius.diagram.TypedVariableValue
     * @generated
     */
    EClass getTypedVariableValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.TypedVariableValue#getVariableDefinition <em>Variable Definition</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Variable Definition</em>'.
     * @see org.eclipse.sirius.diagram.TypedVariableValue#getVariableDefinition()
     * @see #getTypedVariableValue()
     * @generated
     */
    EReference getTypedVariableValue_VariableDefinition();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.TypedVariableValue#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.TypedVariableValue#getValue()
     * @see #getTypedVariableValue()
     * @generated
     */
    EAttribute getTypedVariableValue_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.EObjectVariableValue <em>EObject Variable
     * Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>EObject Variable Value</em>'.
     * @see org.eclipse.sirius.diagram.EObjectVariableValue
     * @generated
     */
    EClass getEObjectVariableValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.EObjectVariableValue#getVariableDefinition <em>Variable Definition</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Variable Definition</em>'.
     * @see org.eclipse.sirius.diagram.EObjectVariableValue#getVariableDefinition()
     * @see #getEObjectVariableValue()
     * @generated
     */
    EReference getEObjectVariableValue_VariableDefinition();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.diagram.EObjectVariableValue#getModelElement
     * <em>Model Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Model Element</em>'.
     * @see org.eclipse.sirius.diagram.EObjectVariableValue#getModelElement()
     * @see #getEObjectVariableValue()
     * @generated
     */
    EReference getEObjectVariableValue_ModelElement();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.ContainerLayout <em>Container Layout</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Container Layout</em>'.
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @generated
     */
    EEnum getContainerLayout();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.LabelPosition <em>Label Position</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Label Position</em>'.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @generated
     */
    EEnum getLabelPosition();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.ContainerShape <em>Container Shape</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Container Shape</em>'.
     * @see org.eclipse.sirius.diagram.ContainerShape
     * @generated
     */
    EEnum getContainerShape();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.BackgroundStyle <em>Background Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Background Style</em>'.
     * @see org.eclipse.sirius.diagram.BackgroundStyle
     * @generated
     */
    EEnum getBackgroundStyle();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.BundledImageShape <em>Bundled Image
     * Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Bundled Image Shape</em>'.
     * @see org.eclipse.sirius.diagram.BundledImageShape
     * @generated
     */
    EEnum getBundledImageShape();

    /**
     * Returns the meta object for enum ' {@link org.eclipse.sirius.diagram.LineStyle <em>Line Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the meta object for enum ' {@link org.eclipse.sirius.diagram.EdgeArrows <em>Edge Arrows</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Edge Arrows</em>'.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @generated
     */
    EEnum getEdgeArrows();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.EdgeRouting <em>Edge Routing</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Edge Routing</em>'.
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @generated
     */
    EEnum getEdgeRouting();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.AlignmentKind <em>Alignment Kind</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Alignment Kind</em>'.
     * @see org.eclipse.sirius.diagram.AlignmentKind
     * @generated
     */
    EEnum getAlignmentKind();

    /**
     * Returns the meta object for enum ' {@link org.eclipse.sirius.diagram.ResizeKind <em>Resize Kind</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @generated
     */
    EEnum getResizeKind();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.ArrangeConstraint <em>Arrange
     * Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Arrange Constraint</em>'.
     * @see org.eclipse.sirius.diagram.ArrangeConstraint
     * @generated
     */
    EEnum getArrangeConstraint();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DiagramFactory getDiagramFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DDiagramImpl <em>DDiagram</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DDiagramImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagram()
         * @generated
         */
        EClass DDIAGRAM = DiagramPackage.eINSTANCE.getDDiagram();

        /**
         * The meta object literal for the '<em><b>Owned Diagram Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = DiagramPackage.eINSTANCE.getDDiagram_OwnedDiagramElements();

        /**
         * The meta object literal for the '<em><b>Diagram Elements</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__DIAGRAM_ELEMENTS = DiagramPackage.eINSTANCE.getDDiagram_DiagramElements();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__DESCRIPTION = DiagramPackage.eINSTANCE.getDDiagram_Description();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__EDGES = DiagramPackage.eINSTANCE.getDDiagram_Edges();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__NODES = DiagramPackage.eINSTANCE.getDDiagram_Nodes();

        /**
         * The meta object literal for the '<em><b>Node List Elements</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__NODE_LIST_ELEMENTS = DiagramPackage.eINSTANCE.getDDiagram_NodeListElements();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__CONTAINERS = DiagramPackage.eINSTANCE.getDDiagram_Containers();

        /**
         * The meta object literal for the '<em><b>Current Concern</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__CURRENT_CONCERN = DiagramPackage.eINSTANCE.getDDiagram_CurrentConcern();

        /**
         * The meta object literal for the '<em><b>Activated Filters</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_FILTERS = DiagramPackage.eINSTANCE.getDDiagram_ActivatedFilters();

        /**
         * The meta object literal for the '<em><b>Activated Transient Layers</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_TRANSIENT_LAYERS = DiagramPackage.eINSTANCE.getDDiagram_ActivatedTransientLayers();

        /**
         * The meta object literal for the '<em><b>All Filters</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__ALL_FILTERS = DiagramPackage.eINSTANCE.getDDiagram_AllFilters();

        /**
         * The meta object literal for the '<em><b>Activated Rules</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_RULES = DiagramPackage.eINSTANCE.getDDiagram_ActivatedRules();

        /**
         * The meta object literal for the '<em><b>Activate Behaviors</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__ACTIVATE_BEHAVIORS = DiagramPackage.eINSTANCE.getDDiagram_ActivateBehaviors();

        /**
         * The meta object literal for the '<em><b>Filter Variable History</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__FILTER_VARIABLE_HISTORY = DiagramPackage.eINSTANCE.getDDiagram_FilterVariableHistory();

        /**
         * The meta object literal for the '<em><b>Activated Layers</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_LAYERS = DiagramPackage.eINSTANCE.getDDiagram_ActivatedLayers();

        /**
         * The meta object literal for the '<em><b>Synchronized</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM__SYNCHRONIZED = DiagramPackage.eINSTANCE.getDDiagram_Synchronized();

        /**
         * The meta object literal for the '<em><b>Hidden Elements</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM__HIDDEN_ELEMENTS = DiagramPackage.eINSTANCE.getDDiagram_HiddenElements();

        /**
         * The meta object literal for the '<em><b>Is In Layouting Mode</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM__IS_IN_LAYOUTING_MODE = DiagramPackage.eINSTANCE.getDDiagram_IsInLayoutingMode();

        /**
         * The meta object literal for the '<em><b>Is In Showing Mode</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM__IS_IN_SHOWING_MODE = DiagramPackage.eINSTANCE.getDDiagram_IsInShowingMode();

        /**
         * The meta object literal for the '<em><b>Header Height</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM__HEADER_HEIGHT = DiagramPackage.eINSTANCE.getDDiagram_HeaderHeight();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl <em>DSemantic
         * Diagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDSemanticDiagram()
         * @generated
         */
        EClass DSEMANTIC_DIAGRAM = DiagramPackage.eINSTANCE.getDSemanticDiagram();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DDiagramElementImpl <em>DDiagram
         * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DDiagramElementImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElement()
         * @generated
         */
        EClass DDIAGRAM_ELEMENT = DiagramPackage.eINSTANCE.getDDiagramElement();

        /**
         * The meta object literal for the '<em><b>Visible</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT__VISIBLE = DiagramPackage.eINSTANCE.getDDiagramElement_Visible();

        /**
         * The meta object literal for the '<em><b>Tooltip Text</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT__TOOLTIP_TEXT = DiagramPackage.eINSTANCE.getDDiagramElement_TooltipText();

        /**
         * The meta object literal for the '<em><b>Parent Layers</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__PARENT_LAYERS = DiagramPackage.eINSTANCE.getDDiagramElement_ParentLayers();

        /**
         * The meta object literal for the '<em><b>Decorations</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__DECORATIONS = DiagramPackage.eINSTANCE.getDDiagramElement_Decorations();

        /**
         * The meta object literal for the '<em><b>Transient Decorations</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS = DiagramPackage.eINSTANCE.getDDiagramElement_TransientDecorations();

        /**
         * The meta object literal for the ' <em><b>Diagram Element Mapping</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING = DiagramPackage.eINSTANCE.getDDiagramElement_DiagramElementMapping();

        /**
         * The meta object literal for the '<em><b>Graphical Filters</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS = DiagramPackage.eINSTANCE.getDDiagramElement_GraphicalFilters();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.GraphicalFilterImpl <em>Graphical
         * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.GraphicalFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGraphicalFilter()
         * @generated
         */
        EClass GRAPHICAL_FILTER = DiagramPackage.eINSTANCE.getGraphicalFilter();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.HideFilterImpl <em>Hide Filter</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.HideFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideFilter()
         * @generated
         */
        EClass HIDE_FILTER = DiagramPackage.eINSTANCE.getHideFilter();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.HideLabelFilterImpl <em>Hide Label
         * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.HideLabelFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideLabelFilter()
         * @generated
         */
        EClass HIDE_LABEL_FILTER = DiagramPackage.eINSTANCE.getHideLabelFilter();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl <em>Folding
         * Point Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingPointFilter()
         * @generated
         */
        EClass FOLDING_POINT_FILTER = DiagramPackage.eINSTANCE.getFoldingPointFilter();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.FoldingFilterImpl <em>Folding
         * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.FoldingFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingFilter()
         * @generated
         */
        EClass FOLDING_FILTER = DiagramPackage.eINSTANCE.getFoldingFilter();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
         * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAppliedCompositeFilters()
         * @generated
         */
        EClass APPLIED_COMPOSITE_FILTERS = DiagramPackage.eINSTANCE.getAppliedCompositeFilters();

        /**
         * The meta object literal for the '<em><b>Composite Filter Descriptions</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS = DiagramPackage.eINSTANCE.getAppliedCompositeFilters_CompositeFilterDescriptions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl <em>Absolute
         * Bounds Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbsoluteBoundsFilter()
         * @generated
         */
        EClass ABSOLUTE_BOUNDS_FILTER = DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__X = DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__Y = DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Y();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__HEIGHT = DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__WIDTH = DiagramPackage.eINSTANCE.getAbsoluteBoundsFilter_Width();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.AbstractDNode <em>Abstract DNode</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.AbstractDNode
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbstractDNode()
         * @generated
         */
        EClass ABSTRACT_DNODE = DiagramPackage.eINSTANCE.getAbstractDNode();

        /**
         * The meta object literal for the '<em><b>Owned Bordered Nodes</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_DNODE__OWNED_BORDERED_NODES = DiagramPackage.eINSTANCE.getAbstractDNode_OwnedBorderedNodes();

        /**
         * The meta object literal for the '<em><b>Arrange Constraints</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_DNODE__ARRANGE_CONSTRAINTS = DiagramPackage.eINSTANCE.getAbstractDNode_ArrangeConstraints();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DNodeImpl <em>DNode</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DNodeImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNode()
         * @generated
         */
        EClass DNODE = DiagramPackage.eINSTANCE.getDNode();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DNODE__WIDTH = DiagramPackage.eINSTANCE.getDNode_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DNODE__HEIGHT = DiagramPackage.eINSTANCE.getDNode_Height();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE__OWNED_STYLE = DiagramPackage.eINSTANCE.getDNode_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DNODE__LABEL_POSITION = DiagramPackage.eINSTANCE.getDNode_LabelPosition();

        /**
         * The meta object literal for the '<em><b>Resize Kind</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DNODE__RESIZE_KIND = DiagramPackage.eINSTANCE.getDNode_ResizeKind();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE__ORIGINAL_STYLE = DiagramPackage.eINSTANCE.getDNode_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE__ACTUAL_MAPPING = DiagramPackage.eINSTANCE.getDNode_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE__CANDIDATES_MAPPING = DiagramPackage.eINSTANCE.getDNode_CandidatesMapping();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
         * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElementContainer()
         * @generated
         */
        EClass DDIAGRAM_ELEMENT_CONTAINER = DiagramPackage.eINSTANCE.getDDiagramElementContainer();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__NODES = DiagramPackage.eINSTANCE.getDDiagramElementContainer_Nodes();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS = DiagramPackage.eINSTANCE.getDDiagramElementContainer_Containers();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS = DiagramPackage.eINSTANCE.getDDiagramElementContainer_Elements();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE = DiagramPackage.eINSTANCE.getDDiagramElementContainer_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE = DiagramPackage.eINSTANCE.getDDiagramElementContainer_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING = DiagramPackage.eINSTANCE.getDDiagramElementContainer_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING = DiagramPackage.eINSTANCE.getDDiagramElementContainer_CandidatesMapping();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT_CONTAINER__WIDTH = DiagramPackage.eINSTANCE.getDDiagramElementContainer_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT_CONTAINER__HEIGHT = DiagramPackage.eINSTANCE.getDDiagramElementContainer_Height();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DNodeContainerImpl <em>DNode
         * Container</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DNodeContainerImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeContainer()
         * @generated
         */
        EClass DNODE_CONTAINER = DiagramPackage.eINSTANCE.getDNodeContainer();

        /**
         * The meta object literal for the '<em><b>Owned Diagram Elements</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS = DiagramPackage.eINSTANCE.getDNodeContainer_OwnedDiagramElements();

        /**
         * The meta object literal for the ' <em><b>Children Presentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DNODE_CONTAINER__CHILDREN_PRESENTATION = DiagramPackage.eINSTANCE.getDNodeContainer_ChildrenPresentation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DNodeListImpl <em>DNode List</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DNodeListImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeList()
         * @generated
         */
        EClass DNODE_LIST = DiagramPackage.eINSTANCE.getDNodeList();

        /**
         * The meta object literal for the '<em><b>Owned Elements</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE_LIST__OWNED_ELEMENTS = DiagramPackage.eINSTANCE.getDNodeList_OwnedElements();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl <em>DNode List
         * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DNodeListElementImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeListElement()
         * @generated
         */
        EClass DNODE_LIST_ELEMENT = DiagramPackage.eINSTANCE.getDNodeListElement();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__OWNED_STYLE = DiagramPackage.eINSTANCE.getDNodeListElement_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__ORIGINAL_STYLE = DiagramPackage.eINSTANCE.getDNodeListElement_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__ACTUAL_MAPPING = DiagramPackage.eINSTANCE.getDNodeListElement_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__CANDIDATES_MAPPING = DiagramPackage.eINSTANCE.getDNodeListElement_CandidatesMapping();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DEdgeImpl <em>DEdge</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DEdgeImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDEdge()
         * @generated
         */
        EClass DEDGE = DiagramPackage.eINSTANCE.getDEdge();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DEDGE__OWNED_STYLE = DiagramPackage.eINSTANCE.getDEdge_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__SIZE = DiagramPackage.eINSTANCE.getDEdge_Size();

        /**
         * The meta object literal for the '<em><b>Source Node</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DEDGE__SOURCE_NODE = DiagramPackage.eINSTANCE.getDEdge_SourceNode();

        /**
         * The meta object literal for the '<em><b>Target Node</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DEDGE__TARGET_NODE = DiagramPackage.eINSTANCE.getDEdge_TargetNode();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DEDGE__ACTUAL_MAPPING = DiagramPackage.eINSTANCE.getDEdge_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__ROUTING_STYLE = DiagramPackage.eINSTANCE.getDEdge_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Is Fold</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__IS_FOLD = DiagramPackage.eINSTANCE.getDEdge_IsFold();

        /**
         * The meta object literal for the '<em><b>Is Mock Edge</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__IS_MOCK_EDGE = DiagramPackage.eINSTANCE.getDEdge_IsMockEdge();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DEDGE__ORIGINAL_STYLE = DiagramPackage.eINSTANCE.getDEdge_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DEDGE__PATH = DiagramPackage.eINSTANCE.getDEdge_Path();

        /**
         * The meta object literal for the '<em><b>Arrange Constraints</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__ARRANGE_CONSTRAINTS = DiagramPackage.eINSTANCE.getDEdge_ArrangeConstraints();

        /**
         * The meta object literal for the '<em><b>Begin Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__BEGIN_LABEL = DiagramPackage.eINSTANCE.getDEdge_BeginLabel();

        /**
         * The meta object literal for the '<em><b>End Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DEDGE__END_LABEL = DiagramPackage.eINSTANCE.getDEdge_EndLabel();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.NodeStyleImpl <em>Node Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.NodeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNodeStyle()
         * @generated
         */
        EClass NODE_STYLE = DiagramPackage.eINSTANCE.getNodeStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__LABEL_POSITION = DiagramPackage.eINSTANCE.getNodeStyle_LabelPosition();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DotImpl <em>Dot</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DotImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDot()
         * @generated
         */
        EClass DOT = DiagramPackage.eINSTANCE.getDot();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DOT__BACKGROUND_COLOR = DiagramPackage.eINSTANCE.getDot_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Stroke Size Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DOT__STROKE_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.eINSTANCE.getDot_StrokeSizeComputationExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl <em>Gauge
         * Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.GaugeSectionImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeSection()
         * @generated
         */
        EClass GAUGE_SECTION = DiagramPackage.eINSTANCE.getGaugeSection();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__MIN = DiagramPackage.eINSTANCE.getGaugeSection_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__MAX = DiagramPackage.eINSTANCE.getGaugeSection_Max();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__VALUE = DiagramPackage.eINSTANCE.getGaugeSection_Value();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__LABEL = DiagramPackage.eINSTANCE.getGaugeSection_Label();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__BACKGROUND_COLOR = DiagramPackage.eINSTANCE.getGaugeSection_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_SECTION__FOREGROUND_COLOR = DiagramPackage.eINSTANCE.getGaugeSection_ForegroundColor();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.ContainerStyleImpl <em>Container
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.ContainerStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerStyle()
         * @generated
         */
        EClass CONTAINER_STYLE = DiagramPackage.eINSTANCE.getContainerStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl <em>Flat
         * Container Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFlatContainerStyle()
         * @generated
         */
        EClass FLAT_CONTAINER_STYLE = DiagramPackage.eINSTANCE.getFlatContainerStyle();

        /**
         * The meta object literal for the '<em><b>Background Style</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = DiagramPackage.eINSTANCE.getFlatContainerStyle_BackgroundStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = DiagramPackage.eINSTANCE.getFlatContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = DiagramPackage.eINSTANCE.getFlatContainerStyle_ForegroundColor();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl <em>Shape
         * Container Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getShapeContainerStyle()
         * @generated
         */
        EClass SHAPE_CONTAINER_STYLE = DiagramPackage.eINSTANCE.getShapeContainerStyle();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE__SHAPE = DiagramPackage.eINSTANCE.getShapeContainerStyle_Shape();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = DiagramPackage.eINSTANCE.getShapeContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.SquareImpl <em>Square</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.SquareImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getSquare()
         * @generated
         */
        EClass SQUARE = DiagramPackage.eINSTANCE.getSquare();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute SQUARE__WIDTH = DiagramPackage.eINSTANCE.getSquare_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute SQUARE__HEIGHT = DiagramPackage.eINSTANCE.getSquare_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute SQUARE__COLOR = DiagramPackage.eINSTANCE.getSquare_Color();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.EllipseImpl <em>Ellipse</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.EllipseImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEllipse()
         * @generated
         */
        EClass ELLIPSE = DiagramPackage.eINSTANCE.getEllipse();

        /**
         * The meta object literal for the '<em><b>Horizontal Diameter</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ELLIPSE__HORIZONTAL_DIAMETER = DiagramPackage.eINSTANCE.getEllipse_HorizontalDiameter();

        /**
         * The meta object literal for the '<em><b>Vertical Diameter</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ELLIPSE__VERTICAL_DIAMETER = DiagramPackage.eINSTANCE.getEllipse_VerticalDiameter();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ELLIPSE__COLOR = DiagramPackage.eINSTANCE.getEllipse_Color();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.LozengeImpl <em>Lozenge</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.LozengeImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLozenge()
         * @generated
         */
        EClass LOZENGE = DiagramPackage.eINSTANCE.getLozenge();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LOZENGE__WIDTH = DiagramPackage.eINSTANCE.getLozenge_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LOZENGE__HEIGHT = DiagramPackage.eINSTANCE.getLozenge_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LOZENGE__COLOR = DiagramPackage.eINSTANCE.getLozenge_Color();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.BundledImageImpl <em>Bundled
         * Image</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.BundledImageImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImage()
         * @generated
         */
        EClass BUNDLED_IMAGE = DiagramPackage.eINSTANCE.getBundledImage();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BUNDLED_IMAGE__SHAPE = DiagramPackage.eINSTANCE.getBundledImage_Shape();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BUNDLED_IMAGE__COLOR = DiagramPackage.eINSTANCE.getBundledImage_Color();

        /**
         * The meta object literal for the '<em><b>Provided Shape ID</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BUNDLED_IMAGE__PROVIDED_SHAPE_ID = DiagramPackage.eINSTANCE.getBundledImage_ProvidedShapeID();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.WorkspaceImageImpl <em>Workspace
         * Image</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.WorkspaceImageImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getWorkspaceImage()
         * @generated
         */
        EClass WORKSPACE_IMAGE = DiagramPackage.eINSTANCE.getWorkspaceImage();

        /**
         * The meta object literal for the '<em><b>Workspace Path</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WORKSPACE_IMAGE__WORKSPACE_PATH = DiagramPackage.eINSTANCE.getWorkspaceImage_WorkspacePath();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.CustomStyleImpl <em>Custom
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.CustomStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCustomStyle()
         * @generated
         */
        EClass CUSTOM_STYLE = DiagramPackage.eINSTANCE.getCustomStyle();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_STYLE__ID = DiagramPackage.eINSTANCE.getCustomStyle_Id();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.EdgeTargetImpl <em>Edge Target</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.EdgeTargetImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeTarget()
         * @generated
         */
        EClass EDGE_TARGET = DiagramPackage.eINSTANCE.getEdgeTarget();

        /**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_TARGET__OUTGOING_EDGES = DiagramPackage.eINSTANCE.getEdgeTarget_OutgoingEdges();

        /**
         * The meta object literal for the '<em><b>Incoming Edges</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_TARGET__INCOMING_EDGES = DiagramPackage.eINSTANCE.getEdgeTarget_IncomingEdges();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl <em>Edge Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.EdgeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeStyle()
         * @generated
         */
        EClass EDGE_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle();

        /**
         * The meta object literal for the '<em><b>Stroke Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__STROKE_COLOR = DiagramPackage.eINSTANCE.getEdgeStyle_StrokeColor();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__LINE_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle_LineStyle();

        /**
         * The meta object literal for the '<em><b>Source Arrow</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__SOURCE_ARROW = DiagramPackage.eINSTANCE.getEdgeStyle_SourceArrow();

        /**
         * The meta object literal for the '<em><b>Target Arrow</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__TARGET_ARROW = DiagramPackage.eINSTANCE.getEdgeStyle_TargetArrow();

        /**
         * The meta object literal for the '<em><b>Folding Style</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__FOLDING_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle_FoldingStyle();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__SIZE = DiagramPackage.eINSTANCE.getEdgeStyle_Size();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__ROUTING_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Begin Label Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__BEGIN_LABEL_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle_BeginLabelStyle();

        /**
         * The meta object literal for the '<em><b>Center Label Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__CENTER_LABEL_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle_CenterLabelStyle();

        /**
         * The meta object literal for the '<em><b>End Label Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_STYLE__END_LABEL_STYLE = DiagramPackage.eINSTANCE.getEdgeStyle_EndLabelStyle();

        /**
         * The meta object literal for the '<em><b>Centered</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__CENTERED = DiagramPackage.eINSTANCE.getEdgeStyle_Centered();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl <em>Gauge
         * Composite Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeCompositeStyle()
         * @generated
         */
        EClass GAUGE_COMPOSITE_STYLE = DiagramPackage.eINSTANCE.getGaugeCompositeStyle();

        /**
         * The meta object literal for the '<em><b>Alignment</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute GAUGE_COMPOSITE_STYLE__ALIGNMENT = DiagramPackage.eINSTANCE.getGaugeCompositeStyle_Alignment();

        /**
         * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GAUGE_COMPOSITE_STYLE__SECTIONS = DiagramPackage.eINSTANCE.getGaugeCompositeStyle_Sections();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl <em>Bordered
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.BorderedStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBorderedStyle()
         * @generated
         */
        EClass BORDERED_STYLE = DiagramPackage.eINSTANCE.getBorderedStyle();

        /**
         * The meta object literal for the '<em><b>Border Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE = DiagramPackage.eINSTANCE.getBorderedStyle_BorderSize();

        /**
         * The meta object literal for the '<em><b>Border Size Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = DiagramPackage.eINSTANCE.getBorderedStyle_BorderSizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_COLOR = DiagramPackage.eINSTANCE.getBorderedStyle_BorderColor();

        /**
         * The meta object literal for the '<em><b>Border Line Style</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_LINE_STYLE = DiagramPackage.eINSTANCE.getBorderedStyle_BorderLineStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.NoteImpl <em>Note</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.NoteImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNote()
         * @generated
         */
        EClass NOTE = DiagramPackage.eINSTANCE.getNote();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NOTE__COLOR = DiagramPackage.eINSTANCE.getNote_Color();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl <em>Filter
         * Variable History</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFilterVariableHistory()
         * @generated
         */
        EClass FILTER_VARIABLE_HISTORY = DiagramPackage.eINSTANCE.getFilterVariableHistory();

        /**
         * The meta object literal for the '<em><b>Owned Values</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference FILTER_VARIABLE_HISTORY__OWNED_VALUES = DiagramPackage.eINSTANCE.getFilterVariableHistory_OwnedValues();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.CollapseFilterImpl <em>Collapse
         * Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.CollapseFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCollapseFilter()
         * @generated
         */
        EClass COLLAPSE_FILTER = DiagramPackage.eINSTANCE.getCollapseFilter();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute COLLAPSE_FILTER__WIDTH = DiagramPackage.eINSTANCE.getCollapseFilter_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute COLLAPSE_FILTER__HEIGHT = DiagramPackage.eINSTANCE.getCollapseFilter_Height();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
         * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getIndirectlyCollapseFilter()
         * @generated
         */
        EClass INDIRECTLY_COLLAPSE_FILTER = DiagramPackage.eINSTANCE.getIndirectlyCollapseFilter();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl <em>Begin Label
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBeginLabelStyle()
         * @generated
         */
        EClass BEGIN_LABEL_STYLE = DiagramPackage.eINSTANCE.getBeginLabelStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl <em>Center Label
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCenterLabelStyle()
         * @generated
         */
        EClass CENTER_LABEL_STYLE = DiagramPackage.eINSTANCE.getCenterLabelStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.EndLabelStyleImpl <em>End Label
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.EndLabelStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEndLabelStyle()
         * @generated
         */
        EClass END_LABEL_STYLE = DiagramPackage.eINSTANCE.getEndLabelStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl <em>Bracket Edge
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBracketEdgeStyle()
         * @generated
         */
        EClass BRACKET_EDGE_STYLE = DiagramPackage.eINSTANCE.getBracketEdgeStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
         * <em>Computed Style Description Registry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getComputedStyleDescriptionRegistry()
         * @generated
         */
        EClass COMPUTED_STYLE_DESCRIPTION_REGISTRY = DiagramPackage.eINSTANCE.getComputedStyleDescriptionRegistry();

        /**
         * The meta object literal for the '<em><b>Computed Style Descriptions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS = DiagramPackage.eINSTANCE.getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl <em>Drag And
         * Drop Target</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDragAndDropTarget()
         * @generated
         */
        EClass DRAG_AND_DROP_TARGET = DiagramPackage.eINSTANCE.getDragAndDropTarget();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.HideLabelCapabilityStyle <em>Hide Label
         * Capability Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.HideLabelCapabilityStyle
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideLabelCapabilityStyle()
         * @generated
         */
        EClass HIDE_LABEL_CAPABILITY_STYLE = DiagramPackage.eINSTANCE.getHideLabelCapabilityStyle();

        /**
         * The meta object literal for the ' <em><b>Hide Label By Default</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT = DiagramPackage.eINSTANCE.getHideLabelCapabilityStyle_HideLabelByDefault();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.VariableValueImpl <em>Variable
         * Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.VariableValueImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getVariableValue()
         * @generated
         */
        EClass VARIABLE_VALUE = DiagramPackage.eINSTANCE.getVariableValue();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.TypedVariableValueImpl <em>Typed
         * Variable Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.TypedVariableValueImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getTypedVariableValue()
         * @generated
         */
        EClass TYPED_VARIABLE_VALUE = DiagramPackage.eINSTANCE.getTypedVariableValue();

        /**
         * The meta object literal for the '<em><b>Variable Definition</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION = DiagramPackage.eINSTANCE.getTypedVariableValue_VariableDefinition();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TYPED_VARIABLE_VALUE__VALUE = DiagramPackage.eINSTANCE.getTypedVariableValue_Value();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.impl.EObjectVariableValueImpl <em>EObject
         * Variable Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.impl.EObjectVariableValueImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEObjectVariableValue()
         * @generated
         */
        EClass EOBJECT_VARIABLE_VALUE = DiagramPackage.eINSTANCE.getEObjectVariableValue();

        /**
         * The meta object literal for the '<em><b>Variable Definition</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EOBJECT_VARIABLE_VALUE__VARIABLE_DEFINITION = DiagramPackage.eINSTANCE.getEObjectVariableValue_VariableDefinition();

        /**
         * The meta object literal for the '<em><b>Model Element</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EOBJECT_VARIABLE_VALUE__MODEL_ELEMENT = DiagramPackage.eINSTANCE.getEObjectVariableValue_ModelElement();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.ContainerLayout <em>Container
         * Layout</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.ContainerLayout
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerLayout()
         * @generated
         */
        EEnum CONTAINER_LAYOUT = DiagramPackage.eINSTANCE.getContainerLayout();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.LabelPosition <em>Label Position</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.LabelPosition
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLabelPosition()
         * @generated
         */
        EEnum LABEL_POSITION = DiagramPackage.eINSTANCE.getLabelPosition();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.ContainerShape <em>Container Shape</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.ContainerShape
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerShape()
         * @generated
         */
        EEnum CONTAINER_SHAPE = DiagramPackage.eINSTANCE.getContainerShape();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.BackgroundStyle <em>Background
         * Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.BackgroundStyle
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBackgroundStyle()
         * @generated
         */
        EEnum BACKGROUND_STYLE = DiagramPackage.eINSTANCE.getBackgroundStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.BundledImageShape <em>Bundled Image
         * Shape</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.BundledImageShape
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImageShape()
         * @generated
         */
        EEnum BUNDLED_IMAGE_SHAPE = DiagramPackage.eINSTANCE.getBundledImageShape();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.LineStyle <em>Line Style</em>}' enum. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.LineStyle
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = DiagramPackage.eINSTANCE.getLineStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.EdgeArrows <em>Edge Arrows</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.EdgeArrows
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeArrows()
         * @generated
         */
        EEnum EDGE_ARROWS = DiagramPackage.eINSTANCE.getEdgeArrows();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.EdgeRouting <em>Edge Routing</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.EdgeRouting
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeRouting()
         * @generated
         */
        EEnum EDGE_ROUTING = DiagramPackage.eINSTANCE.getEdgeRouting();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.AlignmentKind <em>Alignment Kind</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.AlignmentKind
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAlignmentKind()
         * @generated
         */
        EEnum ALIGNMENT_KIND = DiagramPackage.eINSTANCE.getAlignmentKind();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.ResizeKind <em>Resize Kind</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.ResizeKind
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getResizeKind()
         * @generated
         */
        EEnum RESIZE_KIND = DiagramPackage.eINSTANCE.getResizeKind();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.ArrangeConstraint <em>Arrange
         * Constraint</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.ArrangeConstraint
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getArrangeConstraint()
         * @generated
         */
        EEnum ARRANGE_CONSTRAINT = DiagramPackage.eINSTANCE.getArrangeConstraint();

    }

} // DiagramPackage
