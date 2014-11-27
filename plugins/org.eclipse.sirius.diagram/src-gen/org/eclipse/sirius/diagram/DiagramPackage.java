/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
    String eNAME = "diagram";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "diagram";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    DiagramPackage eINSTANCE = org.eclipse.sirius.diagram.impl.DiagramPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DDiagramImpl <em>DDiagram</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DDiagramImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagram()
     * @generated
     */
    int DDIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__DOCUMENTATION = ViewpointPackage.DREPRESENTATION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__EANNOTATIONS = ViewpointPackage.DREPRESENTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__NAME = ViewpointPackage.DREPRESENTATION__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__DIAGRAM_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__DESCRIPTION = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__EDGES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__NODES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__NODE_LIST_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__CONTAINERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__CURRENT_CONCERN = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_FILTERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ALL_FILTERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_RULES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATE_BEHAVIORS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__FILTER_VARIABLE_HISTORY = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__ACTIVATED_LAYERS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__SYNCHRONIZED = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__HIDDEN_ELEMENTS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__IS_IN_LAYOUTING_MODE = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM__HEADER_HEIGHT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 17;

    /**
     * The number of structural features of the '<em>DDiagram</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 18;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl
     * <em>DSemantic Diagram</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDSemanticDiagram()
     * @generated
     */
    int DSEMANTIC_DIAGRAM = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DOCUMENTATION = DDIAGRAM__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__EANNOTATIONS = DDIAGRAM__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_REPRESENTATION_ELEMENTS = DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__REPRESENTATION_ELEMENTS = DDIAGRAM__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NAME = DDIAGRAM__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_ANNOTATION_ENTRIES = DDIAGRAM__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__OWNED_DIAGRAM_ELEMENTS = DDIAGRAM__OWNED_DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DIAGRAM_ELEMENTS = DDIAGRAM__DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__DESCRIPTION = DDIAGRAM__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__EDGES = DDIAGRAM__EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NODES = DDIAGRAM__NODES;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__NODE_LIST_ELEMENTS = DDIAGRAM__NODE_LIST_ELEMENTS;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__CONTAINERS = DDIAGRAM__CONTAINERS;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__CURRENT_CONCERN = DDIAGRAM__CURRENT_CONCERN;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_FILTERS = DDIAGRAM__ACTIVATED_FILTERS;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ALL_FILTERS = DDIAGRAM__ALL_FILTERS;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_RULES = DDIAGRAM__ACTIVATED_RULES;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATE_BEHAVIORS = DDIAGRAM__ACTIVATE_BEHAVIORS;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__FILTER_VARIABLE_HISTORY = DDIAGRAM__FILTER_VARIABLE_HISTORY;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__ACTIVATED_LAYERS = DDIAGRAM__ACTIVATED_LAYERS;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__SYNCHRONIZED = DDIAGRAM__SYNCHRONIZED;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__HIDDEN_ELEMENTS = DDIAGRAM__HIDDEN_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__IS_IN_LAYOUTING_MODE = DDIAGRAM__IS_IN_LAYOUTING_MODE;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__HEADER_HEIGHT = DDIAGRAM__HEADER_HEIGHT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM__TARGET = DDIAGRAM_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DSemantic Diagram</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DSEMANTIC_DIAGRAM_FEATURE_COUNT = DDIAGRAM_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DDiagramElementImpl
     * <em>DDiagram Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DDiagramElementImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElement()
     * @generated
     */
    int DDIAGRAM_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TARGET = ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__NAME = ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS = ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__VISIBLE = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__TOOLTIP_TEXT = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__PARENT_LAYERS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__DECORATIONS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>DDiagram Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.GraphicalFilter
     * <em>Graphical Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.GraphicalFilter
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGraphicalFilter()
     * @generated
     */
    int GRAPHICAL_FILTER = 3;

    /**
     * The number of structural features of the '<em>Graphical Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GRAPHICAL_FILTER_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.HideFilterImpl
     * <em>Hide Filter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.impl.HideFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideFilter()
     * @generated
     */
    int HIDE_FILTER = 4;

    /**
     * The number of structural features of the '<em>Hide Filter</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HIDE_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.HideLabelFilterImpl
     * <em>Hide Label Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.HideLabelFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideLabelFilter()
     * @generated
     */
    int HIDE_LABEL_FILTER = 5;

    /**
     * The number of structural features of the '<em>Hide Label Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int HIDE_LABEL_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl
     * <em>Folding Point Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingPointFilter()
     * @generated
     */
    int FOLDING_POINT_FILTER = 6;

    /**
     * The number of structural features of the '<em>Folding Point Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOLDING_POINT_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.FoldingFilterImpl
     * <em>Folding Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.FoldingFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingFilter()
     * @generated
     */
    int FOLDING_FILTER = 7;

    /**
     * The number of structural features of the '<em>Folding Filter</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOLDING_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
     * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAppliedCompositeFilters()
     * @generated
     */
    int APPLIED_COMPOSITE_FILTERS = 8;

    /**
     * The feature id for the '<em><b>Composite Filter Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Applied Composite Filters</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int APPLIED_COMPOSITE_FILTERS_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl
     * <em>Absolute Bounds Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbsoluteBoundsFilter()
     * @generated
     */
    int ABSOLUTE_BOUNDS_FILTER = 9;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__X = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__Y = GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__HEIGHT = GRAPHICAL_FILTER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER__WIDTH = GRAPHICAL_FILTER_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Absolute Bounds Filter</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSOLUTE_BOUNDS_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.AbstractDNode <em>Abstract DNode</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.AbstractDNode
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbstractDNode()
     * @generated
     */
    int ABSTRACT_DNODE = 10;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TARGET = DDIAGRAM_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__NAME = DDIAGRAM_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__VISIBLE = DDIAGRAM_ELEMENT__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__TOOLTIP_TEXT = DDIAGRAM_ELEMENT__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__PARENT_LAYERS = DDIAGRAM_ELEMENT__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__DECORATIONS = DDIAGRAM_ELEMENT__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__OWNED_BORDERED_NODES = DDIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Abstract DNode</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_DNODE_FEATURE_COUNT = DDIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DNodeImpl <em>DNode</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DNodeImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNode()
     * @generated
     */
    int DNODE = 11;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__TARGET = ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__NAME = ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__SEMANTIC_ELEMENTS = ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_NAVIGATION_LINKS = ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__VISIBLE = ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__TOOLTIP_TEXT = ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__PARENT_LAYERS = ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__DECORATIONS = ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__DIAGRAM_ELEMENT_MAPPING = ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__GRAPHICAL_FILTERS = ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_BORDERED_NODES = ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__ARRANGE_CONSTRAINTS = ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OUTGOING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__INCOMING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__WIDTH = ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__HEIGHT = ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__OWNED_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__LABEL_POSITION = ABSTRACT_DNODE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__RESIZE_KIND = ABSTRACT_DNODE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__ORIGINAL_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__ACTUAL_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE__CANDIDATES_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>DNode</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
     * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElementContainer()
     * @generated
     */
    int DDIAGRAM_ELEMENT_CONTAINER = 12;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TARGET = ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__NAME = ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS = ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_NAVIGATION_LINKS = ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__VISIBLE = ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT = ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS = ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS = ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING = ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS = ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES = ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS = ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES = ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__NODES = ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS = ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS = ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__WIDTH = ABSTRACT_DNODE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER__HEIGHT = ABSTRACT_DNODE_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '
     * <em>DDiagram Element Container</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DNodeContainerImpl
     * <em>DNode Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DNodeContainerImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeContainer()
     * @generated
     */
    int DNODE_CONTAINER = 13;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TARGET = DDIAGRAM_ELEMENT_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__NAME = DDIAGRAM_ELEMENT_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT_CONTAINER__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__VISIBLE = DDIAGRAM_ELEMENT_CONTAINER__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__TOOLTIP_TEXT = DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__PARENT_LAYERS = DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__DECORATIONS = DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_BORDERED_NODES = DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OUTGOING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__INCOMING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__NODES = DDIAGRAM_ELEMENT_CONTAINER__NODES;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CONTAINERS = DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_STYLE = DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ORIGINAL_STYLE = DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__ACTUAL_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CANDIDATES_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__WIDTH = DDIAGRAM_ELEMENT_CONTAINER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__HEIGHT = DDIAGRAM_ELEMENT_CONTAINER__HEIGHT;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER__CHILDREN_PRESENTATION = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DNode Container</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_CONTAINER_FEATURE_COUNT = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DNodeListImpl <em>DNode List</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DNodeListImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeList()
     * @generated
     */
    int DNODE_LIST = 14;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__TARGET = DDIAGRAM_ELEMENT_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__NAME = DDIAGRAM_ELEMENT_CONTAINER__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT_CONTAINER__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__VISIBLE = DDIAGRAM_ELEMENT_CONTAINER__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__TOOLTIP_TEXT = DDIAGRAM_ELEMENT_CONTAINER__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__PARENT_LAYERS = DDIAGRAM_ELEMENT_CONTAINER__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__DECORATIONS = DDIAGRAM_ELEMENT_CONTAINER__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT_CONTAINER__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_BORDERED_NODES = DDIAGRAM_ELEMENT_CONTAINER__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_CONTAINER__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OUTGOING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__OUTGOING_EDGES;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__INCOMING_EDGES = DDIAGRAM_ELEMENT_CONTAINER__INCOMING_EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__NODES = DDIAGRAM_ELEMENT_CONTAINER__NODES;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__CONTAINERS = DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS;

    /**
     * The feature id for the '<em><b>Elements</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_STYLE = DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ORIGINAL_STYLE = DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__ACTUAL_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__CANDIDATES_MAPPING = DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__WIDTH = DDIAGRAM_ELEMENT_CONTAINER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__HEIGHT = DDIAGRAM_ELEMENT_CONTAINER__HEIGHT;

    /**
     * The feature id for the '<em><b>Owned Elements</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST__OWNED_ELEMENTS = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DNode List</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_FEATURE_COUNT = DDIAGRAM_ELEMENT_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl
     * <em>DNode List Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DNodeListElementImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeListElement()
     * @generated
     */
    int DNODE_LIST_ELEMENT = 15;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TARGET = ABSTRACT_DNODE__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__NAME = ABSTRACT_DNODE__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__SEMANTIC_ELEMENTS = ABSTRACT_DNODE__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_NAVIGATION_LINKS = ABSTRACT_DNODE__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__VISIBLE = ABSTRACT_DNODE__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__TOOLTIP_TEXT = ABSTRACT_DNODE__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__PARENT_LAYERS = ABSTRACT_DNODE__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__DECORATIONS = ABSTRACT_DNODE__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__DIAGRAM_ELEMENT_MAPPING = ABSTRACT_DNODE__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__GRAPHICAL_FILTERS = ABSTRACT_DNODE__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Owned Bordered Nodes</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_BORDERED_NODES = ABSTRACT_DNODE__OWNED_BORDERED_NODES;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ARRANGE_CONSTRAINTS = ABSTRACT_DNODE__ARRANGE_CONSTRAINTS;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__OWNED_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ORIGINAL_STYLE = ABSTRACT_DNODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__ACTUAL_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidates Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT__CANDIDATES_MAPPING = ABSTRACT_DNODE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>DNode List Element</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DNODE_LIST_ELEMENT_FEATURE_COUNT = ABSTRACT_DNODE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DEdgeImpl <em>DEdge</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DEdgeImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDEdge()
     * @generated
     */
    int DEDGE = 16;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__TARGET = DDIAGRAM_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__NAME = DDIAGRAM_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__SEMANTIC_ELEMENTS = DDIAGRAM_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Navigation Links</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__OWNED_NAVIGATION_LINKS = DDIAGRAM_ELEMENT__OWNED_NAVIGATION_LINKS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__VISIBLE = DDIAGRAM_ELEMENT__VISIBLE;

    /**
     * The feature id for the '<em><b>Tooltip Text</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__TOOLTIP_TEXT = DDIAGRAM_ELEMENT__TOOLTIP_TEXT;

    /**
     * The feature id for the '<em><b>Parent Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__PARENT_LAYERS = DDIAGRAM_ELEMENT__PARENT_LAYERS;

    /**
     * The feature id for the '<em><b>Decorations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__DECORATIONS = DDIAGRAM_ELEMENT__DECORATIONS;

    /**
     * The feature id for the '<em><b>Diagram Element Mapping</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__DIAGRAM_ELEMENT_MAPPING = DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Graphical Filters</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__GRAPHICAL_FILTERS = DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__OUTGOING_EDGES = DDIAGRAM_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__INCOMING_EDGES = DDIAGRAM_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__OWNED_STYLE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__SIZE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Node</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__SOURCE_NODE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Node</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__TARGET_NODE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Actual Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ACTUAL_MAPPING = DDIAGRAM_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ROUTING_STYLE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Is Fold</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__IS_FOLD = DDIAGRAM_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Is Mock Edge</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__IS_MOCK_EDGE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Original Style</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ORIGINAL_STYLE = DDIAGRAM_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Path</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__PATH = DDIAGRAM_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Arrange Constraints</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__ARRANGE_CONSTRAINTS = DDIAGRAM_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Begin Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__BEGIN_LABEL = DDIAGRAM_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>End Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE__END_LABEL = DDIAGRAM_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>DEdge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEDGE_FEATURE_COUNT = DDIAGRAM_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.NodeStyleImpl <em>Node Style</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.NodeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNodeStyle()
     * @generated
     */
    int NODE_STYLE = 17;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__CUSTOM_FEATURES = ViewpointPackage.LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_SIZE = ViewpointPackage.LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_FORMAT = ViewpointPackage.LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__SHOW_ICON = ViewpointPackage.LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__ICON_PATH = ViewpointPackage.LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_COLOR = ViewpointPackage.LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_ALIGNMENT = ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__DESCRIPTION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_COLOR = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_POSITION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE__HIDE_LABEL_BY_DEFAULT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Node Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_FEATURE_COUNT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DotImpl <em>Dot</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DotImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDot()
     * @generated
     */
    int DOT = 18;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '
     * <em><b>Stroke Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__STROKE_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT__BACKGROUND_COLOR = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Dot</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl
     * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.impl.GaugeSectionImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeSection()
     * @generated
     */
    int GAUGE_SECTION = 19;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__CUSTOM_FEATURES = ViewpointPackage.CUSTOMIZABLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Min</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MIN = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Max</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__MAX = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__VALUE = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__LABEL = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__BACKGROUND_COLOR = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION__FOREGROUND_COLOR = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Gauge Section</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_FEATURE_COUNT = ViewpointPackage.CUSTOMIZABLE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.ContainerStyleImpl
     * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.ContainerStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerStyle()
     * @generated
     */
    int CONTAINER_STYLE = 20;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__CUSTOM_FEATURES = ViewpointPackage.LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_SIZE = ViewpointPackage.LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_FORMAT = ViewpointPackage.LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__SHOW_ICON = ViewpointPackage.LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__ICON_PATH = ViewpointPackage.LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_COLOR = ViewpointPackage.LABEL_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__LABEL_ALIGNMENT = ViewpointPackage.LABEL_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__DESCRIPTION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE__BORDER_COLOR = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_FEATURE_COUNT = ViewpointPackage.LABEL_STYLE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl
     * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFlatContainerStyle()
     * @generated
     */
    int FLAT_CONTAINER_STYLE = 21;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__CUSTOM_FEATURES = CONTAINER_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_SIZE = CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_FORMAT = CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__SHOW_ICON = CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__ICON_PATH = CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_COLOR = CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__LABEL_ALIGNMENT = CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__DESCRIPTION = CONTAINER_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE = CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BORDER_COLOR = CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Background Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Flat Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_FEATURE_COUNT = CONTAINER_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl
     * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getShapeContainerStyle()
     * @generated
     */
    int SHAPE_CONTAINER_STYLE = 22;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__CUSTOM_FEATURES = CONTAINER_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_SIZE = CONTAINER_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_FORMAT = CONTAINER_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHOW_ICON = CONTAINER_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__ICON_PATH = CONTAINER_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_COLOR = CONTAINER_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__LABEL_ALIGNMENT = CONTAINER_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__DESCRIPTION = CONTAINER_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE = CONTAINER_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = CONTAINER_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BORDER_COLOR = CONTAINER_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__SHAPE = CONTAINER_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = CONTAINER_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Shape Container Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_FEATURE_COUNT = CONTAINER_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.SquareImpl <em>Square</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.SquareImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getSquare()
     * @generated
     */
    int SQUARE = 23;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__WIDTH = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__HEIGHT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE__COLOR = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Square</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.EllipseImpl <em>Ellipse</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.EllipseImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEllipse()
     * @generated
     */
    int ELLIPSE = 24;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Horizontal Diameter</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__HORIZONTAL_DIAMETER = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Vertical Diameter</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__VERTICAL_DIAMETER = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE__COLOR = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Ellipse</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.LozengeImpl <em>Lozenge</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.LozengeImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLozenge()
     * @generated
     */
    int LOZENGE = 25;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__WIDTH = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__HEIGHT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE__COLOR = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Lozenge</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.BundledImageImpl
     * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.impl.BundledImageImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImage()
     * @generated
     */
    int BUNDLED_IMAGE = 26;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__SHAPE = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE__COLOR = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Bundled Image</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.WorkspaceImageImpl
     * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.WorkspaceImageImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getWorkspaceImage()
     * @generated
     */
    int WORKSPACE_IMAGE = 27;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Workspace Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE__WORKSPACE_PATH = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Workspace Image</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.CustomStyleImpl
     * <em>Custom Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.impl.CustomStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCustomStyle()
     * @generated
     */
    int CUSTOM_STYLE = 28;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE__ID = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Custom Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.EdgeTargetImpl
     * <em>Edge Target</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.impl.EdgeTargetImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeTarget()
     * @generated
     */
    int EDGE_TARGET = 29;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_TARGET__OUTGOING_EDGES = 0;

    /**
     * The feature id for the '<em><b>Incoming Edges</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_TARGET__INCOMING_EDGES = 1;

    /**
     * The number of structural features of the '<em>Edge Target</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_TARGET_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl <em>Edge Style</em>}
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.EdgeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeStyle()
     * @generated
     */
    int EDGE_STYLE = 30;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CUSTOM_FEATURES = ViewpointPackage.STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__DESCRIPTION = ViewpointPackage.STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__LINE_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SOURCE_ARROW = ViewpointPackage.STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__TARGET_ARROW = ViewpointPackage.STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__FOLDING_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SIZE = ViewpointPackage.STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__ROUTING_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__BEGIN_LABEL_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CENTER_LABEL_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__END_LABEL_STYLE = ViewpointPackage.STYLE_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Centered</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__CENTERED = ViewpointPackage.STYLE_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE__STROKE_COLOR = ViewpointPackage.STYLE_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Edge Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_FEATURE_COUNT = ViewpointPackage.STYLE_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl
     * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeCompositeStyle()
     * @generated
     */
    int GAUGE_COMPOSITE_STYLE = 31;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__ALIGNMENT = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sections</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE__SECTIONS = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Gauge Composite Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl
     * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.BorderedStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBorderedStyle()
     * @generated
     */
    int BORDERED_STYLE = 32;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__CUSTOM_FEATURES = ViewpointPackage.STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__DESCRIPTION = ViewpointPackage.STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE = ViewpointPackage.STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = ViewpointPackage.STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE__BORDER_COLOR = ViewpointPackage.STYLE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Bordered Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_FEATURE_COUNT = ViewpointPackage.STYLE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.NoteImpl <em>Note</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.NoteImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNote()
     * @generated
     */
    int NOTE = 33;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__CUSTOM_FEATURES = NODE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_SIZE = NODE_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_FORMAT = NODE_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__SHOW_ICON = NODE_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__ICON_PATH = NODE_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_COLOR = NODE_STYLE__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_ALIGNMENT = NODE_STYLE__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__DESCRIPTION = NODE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE = NODE_STYLE__BORDER_SIZE;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__BORDER_COLOR = NODE_STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__LABEL_POSITION = NODE_STYLE__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__HIDE_LABEL_BY_DEFAULT = NODE_STYLE__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE__COLOR = NODE_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Note</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_FEATURE_COUNT = NODE_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl
     * <em>Filter Variable History</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFilterVariableHistory()
     * @generated
     */
    int FILTER_VARIABLE_HISTORY = 34;

    /**
     * The feature id for the '<em><b>Owned Values</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY__OWNED_VALUES = 0;

    /**
     * The number of structural features of the '
     * <em>Filter Variable History</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_HISTORY_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.FilterVariableValueImpl
     * <em>Filter Variable Value</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.FilterVariableValueImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFilterVariableValue()
     * @generated
     */
    int FILTER_VARIABLE_VALUE = 35;

    /**
     * The feature id for the '<em><b>Variable Definition</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION = 0;

    /**
     * The feature id for the '<em><b>Model Element</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_VALUE__MODEL_ELEMENT = 1;

    /**
     * The number of structural features of the '<em>Filter Variable Value</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FILTER_VARIABLE_VALUE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.CollapseFilterImpl
     * <em>Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.CollapseFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCollapseFilter()
     * @generated
     */
    int COLLAPSE_FILTER = 36;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__WIDTH = GRAPHICAL_FILTER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER__HEIGHT = GRAPHICAL_FILTER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Collapse Filter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLLAPSE_FILTER_FEATURE_COUNT = GRAPHICAL_FILTER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
     * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getIndirectlyCollapseFilter()
     * @generated
     */
    int INDIRECTLY_COLLAPSE_FILTER = 37;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__WIDTH = COLLAPSE_FILTER__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER__HEIGHT = COLLAPSE_FILTER__HEIGHT;

    /**
     * The number of structural features of the '
     * <em>Indirectly Collapse Filter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INDIRECTLY_COLLAPSE_FILTER_FEATURE_COUNT = COLLAPSE_FILTER_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl
     * <em>Begin Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBeginLabelStyle()
     * @generated
     */
    int BEGIN_LABEL_STYLE = 38;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The number of structural features of the '<em>Begin Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl
     * <em>Center Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCenterLabelStyle()
     * @generated
     */
    int CENTER_LABEL_STYLE = 39;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The number of structural features of the '<em>Center Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.EndLabelStyleImpl
     * <em>End Label Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.EndLabelStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEndLabelStyle()
     * @generated
     */
    int END_LABEL_STYLE = 40;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__CUSTOM_FEATURES = ViewpointPackage.BASIC_LABEL_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_SIZE = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_FORMAT = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__SHOW_ICON = ViewpointPackage.BASIC_LABEL_STYLE__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__ICON_PATH = ViewpointPackage.BASIC_LABEL_STYLE__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE__LABEL_COLOR = ViewpointPackage.BASIC_LABEL_STYLE__LABEL_COLOR;

    /**
     * The number of structural features of the '<em>End Label Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_FEATURE_COUNT = ViewpointPackage.BASIC_LABEL_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl
     * <em>Bracket Edge Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBracketEdgeStyle()
     * @generated
     */
    int BRACKET_EDGE_STYLE = 41;

    /**
     * The feature id for the '<em><b>Custom Features</b></em>' attribute list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CUSTOM_FEATURES = EDGE_STYLE__CUSTOM_FEATURES;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__DESCRIPTION = EDGE_STYLE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__LINE_STYLE = EDGE_STYLE__LINE_STYLE;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__SOURCE_ARROW = EDGE_STYLE__SOURCE_ARROW;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__TARGET_ARROW = EDGE_STYLE__TARGET_ARROW;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__FOLDING_STYLE = EDGE_STYLE__FOLDING_STYLE;

    /**
     * The feature id for the '<em><b>Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__SIZE = EDGE_STYLE__SIZE;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__ROUTING_STYLE = EDGE_STYLE__ROUTING_STYLE;

    /**
     * The feature id for the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__BEGIN_LABEL_STYLE = EDGE_STYLE__BEGIN_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CENTER_LABEL_STYLE = EDGE_STYLE__CENTER_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__END_LABEL_STYLE = EDGE_STYLE__END_LABEL_STYLE;

    /**
     * The feature id for the '<em><b>Centered</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__CENTERED = EDGE_STYLE__CENTERED;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE__STROKE_COLOR = EDGE_STYLE__STROKE_COLOR;

    /**
     * The number of structural features of the '<em>Bracket Edge Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_FEATURE_COUNT = EDGE_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
     * <em>Computed Style Description Registry</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getComputedStyleDescriptionRegistry()
     * @generated
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY = 42;

    /**
     * The feature id for the '<em><b>Computed Style Descriptions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS = 0;

    /**
     * The feature id for the '<em><b>Cache</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE = 1;

    /**
     * The number of structural features of the '
     * <em>Computed Style Description Registry</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMPUTED_STYLE_DESCRIPTION_REGISTRY_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DiagramElementMapping2ModelElementImpl
     * <em>Element Mapping2 Model Element</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DiagramElementMapping2ModelElementImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDiagramElementMapping2ModelElement()
     * @generated
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT = 43;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>Element Mapping2 Model Element</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.ModelElement2ViewVariableImpl
     * <em>Model Element2 View Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.ModelElement2ViewVariableImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getModelElement2ViewVariable()
     * @generated
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE = 44;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>Model Element2 View Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_ELEMENT2_VIEW_VARIABLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.ViewVariable2ContainerVariableImpl
     * <em>View Variable2 Container Variable</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.ViewVariable2ContainerVariableImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getViewVariable2ContainerVariable()
     * @generated
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE = 45;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' map. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>View Variable2 Container Variable</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_VARIABLE2_CONTAINER_VARIABLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.ContainerVariable2StyleDescriptionImpl
     * <em>Container Variable2 Style Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.ContainerVariable2StyleDescriptionImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerVariable2StyleDescription()
     * @generated
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION = 46;

    /**
     * The feature id for the '<em><b>Key</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>Container Variable2 Style Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VARIABLE2_STYLE_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl
     * <em>Drag And Drop Target</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDragAndDropTarget()
     * @generated
     */
    int DRAG_AND_DROP_TARGET = 47;

    /**
     * The number of structural features of the '<em>Drag And Drop Target</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.ContainerLayout
     * <em>Container Layout</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerLayout()
     * @generated
     */
    int CONTAINER_LAYOUT = 48;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.LabelPosition <em>Label Position</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLabelPosition()
     * @generated
     */
    int LABEL_POSITION = 49;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.ContainerShape
     * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.ContainerShape
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerShape()
     * @generated
     */
    int CONTAINER_SHAPE = 50;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.BackgroundStyle
     * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.BackgroundStyle
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBackgroundStyle()
     * @generated
     */
    int BACKGROUND_STYLE = 51;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.BundledImageShape
     * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.BundledImageShape
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImageShape()
     * @generated
     */
    int BUNDLED_IMAGE_SHAPE = 52;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.LineStyle
     * <em>Line Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 53;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.EdgeArrows
     * <em>Edge Arrows</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeArrows()
     * @generated
     */
    int EDGE_ARROWS = 54;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.EdgeRouting <em>Edge Routing</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeRouting()
     * @generated
     */
    int EDGE_ROUTING = 55;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.AlignmentKind <em>Alignment Kind</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.AlignmentKind
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAlignmentKind()
     * @generated
     */
    int ALIGNMENT_KIND = 56;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.ResizeKind
     * <em>Resize Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getResizeKind()
     * @generated
     */
    int RESIZE_KIND = 57;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.ArrangeConstraint
     * <em>Arrange Constraint</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.ArrangeConstraint
     * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getArrangeConstraint()
     * @generated
     */
    int ARRANGE_CONSTRAINT = 58;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DDiagram <em>DDiagram</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram
     * @generated
     */
    EClass getDDiagram();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getOwnedDiagramElements
     * <em>Owned Diagram Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getOwnedDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_OwnedDiagramElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getDiagramElements
     * <em>Diagram Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Diagram Elements</em>
     *         '.
     * @see org.eclipse.sirius.diagram.DDiagram#getDiagramElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_DiagramElements();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagram#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getDescription()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Description();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getEdges <em>Edges</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Edges</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getEdges()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Edges();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getNodes <em>Nodes</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getNodes()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getNodeListElements
     * <em>Node List Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Node List Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getNodeListElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_NodeListElements();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getContainers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_Containers();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagram#getCurrentConcern
     * <em>Current Concern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Current Concern</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getCurrentConcern()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_CurrentConcern();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getActivatedFilters
     * <em>Activated Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activated Filters</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getAllFilters
     * <em>All Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Filters</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getAllFilters()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_AllFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getActivatedRules
     * <em>Activated Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Activated Rules</em>
     *         '.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedRules()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedRules();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getActivateBehaviors
     * <em>Activate Behaviors</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Activate Behaviors</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivateBehaviors()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivateBehaviors();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DDiagram#getFilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getFilterVariableHistory()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_FilterVariableHistory();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getActivatedLayers
     * <em>Activated Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '<em>Activated Layers</em>
     *         '.
     * @see org.eclipse.sirius.diagram.DDiagram#getActivatedLayers()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_ActivatedLayers();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagram#isSynchronized
     * <em>Synchronized</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Synchronized</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#isSynchronized()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_Synchronized();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagram#getHiddenElements
     * <em>Hidden Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Hidden Elements</em>
     *         '.
     * @see org.eclipse.sirius.diagram.DDiagram#getHiddenElements()
     * @see #getDDiagram()
     * @generated
     */
    EReference getDDiagram_HiddenElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagram#isIsInLayoutingMode
     * <em>Is In Layouting Mode</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is In Layouting Mode</em>
     *         '.
     * @see org.eclipse.sirius.diagram.DDiagram#isIsInLayoutingMode()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_IsInLayoutingMode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagram#getHeaderHeight
     * <em>Header Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Header Height</em>'.
     * @see org.eclipse.sirius.diagram.DDiagram#getHeaderHeight()
     * @see #getDDiagram()
     * @generated
     */
    EAttribute getDDiagram_HeaderHeight();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DSemanticDiagram
     * <em>DSemantic Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DSemantic Diagram</em>'.
     * @see org.eclipse.sirius.diagram.DSemanticDiagram
     * @generated
     */
    EClass getDSemanticDiagram();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DDiagramElement
     * <em>DDiagram Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DDiagram Element</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement
     * @generated
     */
    EClass getDDiagramElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#isVisible
     * <em>Visible</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#isVisible()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_Visible();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getTooltipText
     * <em>Tooltip Text</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tooltip Text</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getTooltipText()
     * @see #getDDiagramElement()
     * @generated
     */
    EAttribute getDDiagramElement_TooltipText();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getParentLayers
     * <em>Parent Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Parent Layers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getParentLayers()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_ParentLayers();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getDecorations
     * <em>Decorations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Decorations</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getDecorations()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_Decorations();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getDiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Diagram Element Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getDiagramElementMapping()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_DiagramElementMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElement#getGraphicalFilters
     * <em>Graphical Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Graphical Filters</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElement#getGraphicalFilters()
     * @see #getDDiagramElement()
     * @generated
     */
    EReference getDDiagramElement_GraphicalFilters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.GraphicalFilter
     * <em>Graphical Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Graphical Filter</em>'.
     * @see org.eclipse.sirius.diagram.GraphicalFilter
     * @generated
     */
    EClass getGraphicalFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.HideFilter <em>Hide Filter</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Hide Filter</em>'.
     * @see org.eclipse.sirius.diagram.HideFilter
     * @generated
     */
    EClass getHideFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.HideLabelFilter
     * <em>Hide Label Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Hide Label Filter</em>'.
     * @see org.eclipse.sirius.diagram.HideLabelFilter
     * @generated
     */
    EClass getHideLabelFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.FoldingPointFilter
     * <em>Folding Point Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Folding Point Filter</em>'.
     * @see org.eclipse.sirius.diagram.FoldingPointFilter
     * @generated
     */
    EClass getFoldingPointFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.FoldingFilter <em>Folding Filter</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Folding Filter</em>'.
     * @see org.eclipse.sirius.diagram.FoldingFilter
     * @generated
     */
    EClass getFoldingFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.AppliedCompositeFilters
     * <em>Applied Composite Filters</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Applied Composite Filters</em>'.
     * @see org.eclipse.sirius.diagram.AppliedCompositeFilters
     * @generated
     */
    EClass getAppliedCompositeFilters();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.AppliedCompositeFilters#getCompositeFilterDescriptions
     * <em>Composite Filter Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Composite Filter Descriptions</em>'.
     * @see org.eclipse.sirius.diagram.AppliedCompositeFilters#getCompositeFilterDescriptions()
     * @see #getAppliedCompositeFilters()
     * @generated
     */
    EReference getAppliedCompositeFilters_CompositeFilterDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter
     * <em>Absolute Bounds Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Absolute Bounds Filter</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter
     * @generated
     */
    EClass getAbsoluteBoundsFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getX <em>X</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getX()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getY <em>Y</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getY()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Y();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getHeight()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Height();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter#getWidth()
     * @see #getAbsoluteBoundsFilter()
     * @generated
     */
    EAttribute getAbsoluteBoundsFilter_Width();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.AbstractDNode <em>Abstract DNode</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract DNode</em>'.
     * @see org.eclipse.sirius.diagram.AbstractDNode
     * @generated
     */
    EClass getAbstractDNode();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.AbstractDNode#getOwnedBorderedNodes
     * <em>Owned Bordered Nodes</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Bordered Nodes</em>'.
     * @see org.eclipse.sirius.diagram.AbstractDNode#getOwnedBorderedNodes()
     * @see #getAbstractDNode()
     * @generated
     */
    EReference getAbstractDNode_OwnedBorderedNodes();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.diagram.AbstractDNode#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.diagram.AbstractDNode#getArrangeConstraints()
     * @see #getAbstractDNode()
     * @generated
     */
    EAttribute getAbstractDNode_ArrangeConstraints();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DNode <em>DNode</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode</em>'.
     * @see org.eclipse.sirius.diagram.DNode
     * @generated
     */
    EClass getDNode();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DNode#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getWidth()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DNode#getHeight <em>Height</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getHeight()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DNode#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getOwnedStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DNode#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getLabelPosition()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_LabelPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DNode#getResizeKind
     * <em>Resize Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getResizeKind()
     * @see #getDNode()
     * @generated
     */
    EAttribute getDNode_ResizeKind();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DNode#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getOriginalStyle()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DNode#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getActualMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DNode#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNode#getCandidatesMapping()
     * @see #getDNode()
     * @generated
     */
    EReference getDNode_CandidatesMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer
     * <em>DDiagram Element Container</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram Element Container</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer
     * @generated
     */
    EClass getDDiagramElementContainer();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getNodes
     * <em>Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Nodes</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getNodes()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Nodes();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getContainers
     * <em>Containers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Containers</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getContainers()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Containers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getElements
     * <em>Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Elements</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getElements()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_Elements();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getOwnedStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OwnedStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getOriginalStyle()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getActualMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_ActualMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getCandidatesMapping()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EReference getDDiagramElementContainer_CandidatesMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getWidth()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EAttribute getDDiagramElementContainer_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DDiagramElementContainer#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer#getHeight()
     * @see #getDDiagramElementContainer()
     * @generated
     */
    EAttribute getDDiagramElementContainer_Height();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DNodeContainer
     * <em>DNode Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode Container</em>'.
     * @see org.eclipse.sirius.diagram.DNodeContainer
     * @generated
     */
    EClass getDNodeContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DNodeContainer#getOwnedDiagramElements
     * <em>Owned Diagram Elements</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Diagram Elements</em>'.
     * @see org.eclipse.sirius.diagram.DNodeContainer#getOwnedDiagramElements()
     * @see #getDNodeContainer()
     * @generated
     */
    EReference getDNodeContainer_OwnedDiagramElements();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DNodeContainer#getChildrenPresentation
     * <em>Children Presentation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Children Presentation</em>
     *         '.
     * @see org.eclipse.sirius.diagram.DNodeContainer#getChildrenPresentation()
     * @see #getDNodeContainer()
     * @generated
     */
    EAttribute getDNodeContainer_ChildrenPresentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DNodeList <em>DNode List</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DNode List</em>'.
     * @see org.eclipse.sirius.diagram.DNodeList
     * @generated
     */
    EClass getDNodeList();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.DNodeList#getOwnedElements
     * <em>Owned Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Elements</em>'.
     * @see org.eclipse.sirius.diagram.DNodeList#getOwnedElements()
     * @see #getDNodeList()
     * @generated
     */
    EReference getDNodeList_OwnedElements();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DNodeListElement
     * <em>DNode List Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>DNode List Element</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement
     * @generated
     */
    EClass getDNodeListElement();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DNodeListElement#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getOwnedStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OwnedStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DNodeListElement#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getOriginalStyle()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_OriginalStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DNodeListElement#getActualMapping
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
     * {@link org.eclipse.sirius.diagram.DNodeListElement#getCandidatesMapping
     * <em>Candidates Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Candidates Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DNodeListElement#getCandidatesMapping()
     * @see #getDNodeListElement()
     * @generated
     */
    EReference getDNodeListElement_CandidatesMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DEdge <em>DEdge</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DEdge</em>'.
     * @see org.eclipse.sirius.diagram.DEdge
     * @generated
     */
    EClass getDEdge();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.DEdge#getOwnedStyle
     * <em>Owned Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Owned Style</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getOwnedStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OwnedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DEdge#getSize <em>Size</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getSize()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_Size();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DEdge#getSourceNode
     * <em>Source Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Source Node</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getSourceNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_SourceNode();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DEdge#getTargetNode
     * <em>Target Node</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Target Node</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getTargetNode()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_TargetNode();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DEdge#getActualMapping
     * <em>Actual Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Actual Mapping</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getActualMapping()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_ActualMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DEdge#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getRoutingStyle()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_RoutingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DEdge#isIsFold <em>Is Fold</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is Fold</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#isIsFold()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsFold();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DEdge#isIsMockEdge
     * <em>Is Mock Edge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Is Mock Edge</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#isIsMockEdge()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_IsMockEdge();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.DEdge#getOriginalStyle
     * <em>Original Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Original Style</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getOriginalStyle()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_OriginalStyle();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.DEdge#getPath <em>Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Path</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getPath()
     * @see #getDEdge()
     * @generated
     */
    EReference getDEdge_Path();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.diagram.DEdge#getArrangeConstraints
     * <em>Arrange Constraints</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute list '
     *         <em>Arrange Constraints</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getArrangeConstraints()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_ArrangeConstraints();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DEdge#getBeginLabel
     * <em>Begin Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Begin Label</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getBeginLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_BeginLabel();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.DEdge#getEndLabel <em>End Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>End Label</em>'.
     * @see org.eclipse.sirius.diagram.DEdge#getEndLabel()
     * @see #getDEdge()
     * @generated
     */
    EAttribute getDEdge_EndLabel();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Style</em>'.
     * @see org.eclipse.sirius.diagram.NodeStyle
     * @generated
     */
    EClass getNodeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.NodeStyle#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.diagram.NodeStyle#getLabelPosition()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_LabelPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.NodeStyle#isHideLabelByDefault
     * <em>Hide Label By Default</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Hide Label By Default</em>
     *         '.
     * @see org.eclipse.sirius.diagram.NodeStyle#isHideLabelByDefault()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_HideLabelByDefault();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.Dot
     * <em>Dot</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dot</em>'.
     * @see org.eclipse.sirius.diagram.Dot
     * @generated
     */
    EClass getDot();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.Dot#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.Dot#getBackgroundColor()
     * @see #getDot()
     * @generated
     */
    EAttribute getDot_BackgroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Dot#getStrokeSizeComputationExpression
     * <em>Stroke Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Stroke Size Computation Expression</em>'.
     * @see org.eclipse.sirius.diagram.Dot#getStrokeSizeComputationExpression()
     * @see #getDot()
     * @generated
     */
    EAttribute getDot_StrokeSizeComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.GaugeSection <em>Gauge Section</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Section</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection
     * @generated
     */
    EClass getGaugeSection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getMin <em>Min</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Min</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getMin()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Min();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getMax <em>Max</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Max</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getMax()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Max();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getValue <em>Value</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getValue()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Value();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getLabel()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_Label();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getBackgroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.diagram.GaugeSection#getForegroundColor()
     * @see #getGaugeSection()
     * @generated
     */
    EAttribute getGaugeSection_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.ContainerStyle
     * <em>Container Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Style</em>'.
     * @see org.eclipse.sirius.diagram.ContainerStyle
     * @generated
     */
    EClass getContainerStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Flat Container Style</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle
     * @generated
     */
    EClass getFlatContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Background Style</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundStyle()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle#getForegroundColor()
     * @see #getFlatContainerStyle()
     * @generated
     */
    EAttribute getFlatContainerStyle_ForegroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Shape Container Style</em>'.
     * @see org.eclipse.sirius.diagram.ShapeContainerStyle
     * @generated
     */
    EClass getShapeContainerStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.ShapeContainerStyle#getShape
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
     * {@link org.eclipse.sirius.diagram.ShapeContainerStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.ShapeContainerStyle#getBackgroundColor()
     * @see #getShapeContainerStyle()
     * @generated
     */
    EAttribute getShapeContainerStyle_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.Square <em>Square</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Square</em>'.
     * @see org.eclipse.sirius.diagram.Square
     * @generated
     */
    EClass getSquare();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Square#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.Square#getWidth()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Square#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.Square#getHeight()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.Square#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Square#getColor()
     * @see #getSquare()
     * @generated
     */
    EAttribute getSquare_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.Ellipse <em>Ellipse</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Ellipse</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse
     * @generated
     */
    EClass getEllipse();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Ellipse#getHorizontalDiameter
     * <em>Horizontal Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Horizontal Diameter</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse#getHorizontalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_HorizontalDiameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Ellipse#getVerticalDiameter
     * <em>Vertical Diameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Vertical Diameter</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse#getVerticalDiameter()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_VerticalDiameter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.Ellipse#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Ellipse#getColor()
     * @see #getEllipse()
     * @generated
     */
    EAttribute getEllipse_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.Lozenge <em>Lozenge</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Lozenge</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge
     * @generated
     */
    EClass getLozenge();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Lozenge#getWidth <em>Width</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge#getWidth()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.Lozenge#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge#getHeight()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Height();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.Lozenge#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Lozenge#getColor()
     * @see #getLozenge()
     * @generated
     */
    EAttribute getLozenge_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.BundledImage <em>Bundled Image</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bundled Image</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage
     * @generated
     */
    EClass getBundledImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.BundledImage#getShape <em>Shape</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage#getShape()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Shape();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.BundledImage#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.BundledImage#getColor()
     * @see #getBundledImage()
     * @generated
     */
    EAttribute getBundledImage_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.WorkspaceImage
     * <em>Workspace Image</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Workspace Image</em>'.
     * @see org.eclipse.sirius.diagram.WorkspaceImage
     * @generated
     */
    EClass getWorkspaceImage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath
     * <em>Workspace Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Workspace Path</em>'.
     * @see org.eclipse.sirius.diagram.WorkspaceImage#getWorkspacePath()
     * @see #getWorkspaceImage()
     * @generated
     */
    EAttribute getWorkspaceImage_WorkspacePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.CustomStyle <em>Custom Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Custom Style</em>'.
     * @see org.eclipse.sirius.diagram.CustomStyle
     * @generated
     */
    EClass getCustomStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.CustomStyle#getId <em>Id</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.CustomStyle#getId()
     * @see #getCustomStyle()
     * @generated
     */
    EAttribute getCustomStyle_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.EdgeTarget <em>Edge Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Target</em>'.
     * @see org.eclipse.sirius.diagram.EdgeTarget
     * @generated
     */
    EClass getEdgeTarget();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.EdgeTarget#getOutgoingEdges
     * <em>Outgoing Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Outgoing Edges</em>'.
     * @see org.eclipse.sirius.diagram.EdgeTarget#getOutgoingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_OutgoingEdges();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.EdgeTarget#getIncomingEdges
     * <em>Incoming Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Incoming Edges</em>'.
     * @see org.eclipse.sirius.diagram.EdgeTarget#getIncomingEdges()
     * @see #getEdgeTarget()
     * @generated
     */
    EReference getEdgeTarget_IncomingEdges();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle
     * @generated
     */
    EClass getEdgeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getStrokeColor
     * <em>Stroke Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Stroke Color</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getStrokeColor()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_StrokeColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getLineStyle
     * <em>Line Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getLineStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_LineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getSourceArrow
     * <em>Source Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Arrow</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getSourceArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_SourceArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getTargetArrow
     * <em>Target Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Arrow</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getTargetArrow()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_TargetArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getFoldingStyle
     * <em>Folding Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getFoldingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_FoldingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getSize <em>Size</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Size</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getSize()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_Size();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getRoutingStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_RoutingStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getBeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getBeginLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_BeginLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getCenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Center Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getCenterLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_CenterLabelStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getEndLabelStyle
     * <em>End Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>End Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getEndLabelStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EReference getEdgeStyle_EndLabelStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.EdgeStyle#getCentered
     * <em>Centered</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Centered</em>'.
     * @see org.eclipse.sirius.diagram.EdgeStyle#getCentered()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_Centered();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Composite Style</em>'.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle
     * @generated
     */
    EClass getGaugeCompositeStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle#getAlignment()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EAttribute getGaugeCompositeStyle_Alignment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.GaugeCompositeStyle#getSections
     * <em>Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sections</em>'.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle#getSections()
     * @see #getGaugeCompositeStyle()
     * @generated
     */
    EReference getGaugeCompositeStyle_Sections();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.BorderedStyle <em>Bordered Style</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bordered Style</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle
     * @generated
     */
    EClass getBorderedStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSize
     * <em>Border Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Border Size</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderSize()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSize();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Border Size Computation Expression</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderSizeComputationExpression()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderSizeComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Border Color</em>'.
     * @see org.eclipse.sirius.diagram.BorderedStyle#getBorderColor()
     * @see #getBorderedStyle()
     * @generated
     */
    EAttribute getBorderedStyle_BorderColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.Note <em>Note</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Note</em>'.
     * @see org.eclipse.sirius.diagram.Note
     * @generated
     */
    EClass getNote();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.Note#getColor <em>Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Color</em>'.
     * @see org.eclipse.sirius.diagram.Note#getColor()
     * @see #getNote()
     * @generated
     */
    EAttribute getNote_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.FilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter Variable History</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableHistory
     * @generated
     */
    EClass getFilterVariableHistory();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.FilterVariableHistory#getOwnedValues
     * <em>Owned Values</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Values</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableHistory#getOwnedValues()
     * @see #getFilterVariableHistory()
     * @generated
     */
    EReference getFilterVariableHistory_OwnedValues();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.FilterVariableValue
     * <em>Filter Variable Value</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Filter Variable Value</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableValue
     * @generated
     */
    EClass getFilterVariableValue();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.FilterVariableValue#getVariableDefinition
     * <em>Variable Definition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Variable Definition</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableValue#getVariableDefinition()
     * @see #getFilterVariableValue()
     * @generated
     */
    EReference getFilterVariableValue_VariableDefinition();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.FilterVariableValue#getModelElement
     * <em>Model Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Model Element</em>'.
     * @see org.eclipse.sirius.diagram.FilterVariableValue#getModelElement()
     * @see #getFilterVariableValue()
     * @generated
     */
    EReference getFilterVariableValue_ModelElement();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.CollapseFilter
     * <em>Collapse Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Collapse Filter</em>'.
     * @see org.eclipse.sirius.diagram.CollapseFilter
     * @generated
     */
    EClass getCollapseFilter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.CollapseFilter#getWidth <em>Width</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.CollapseFilter#getWidth()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.CollapseFilter#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.CollapseFilter#getHeight()
     * @see #getCollapseFilter()
     * @generated
     */
    EAttribute getCollapseFilter_Height();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.IndirectlyCollapseFilter
     * <em>Indirectly Collapse Filter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Indirectly Collapse Filter</em>'.
     * @see org.eclipse.sirius.diagram.IndirectlyCollapseFilter
     * @generated
     */
    EClass getIndirectlyCollapseFilter();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.BeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Begin Label Style</em>'.
     * @see org.eclipse.sirius.diagram.BeginLabelStyle
     * @generated
     */
    EClass getBeginLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.CenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Center Label Style</em>'.
     * @see org.eclipse.sirius.diagram.CenterLabelStyle
     * @generated
     */
    EClass getCenterLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.EndLabelStyle <em>End Label Style</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>End Label Style</em>'.
     * @see org.eclipse.sirius.diagram.EndLabelStyle
     * @generated
     */
    EClass getEndLabelStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.BracketEdgeStyle
     * <em>Bracket Edge Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Bracket Edge Style</em>'.
     * @see org.eclipse.sirius.diagram.BracketEdgeStyle
     * @generated
     */
    EClass getBracketEdgeStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Computed Style Description Registry</em>'.
     * @see org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry
     * @generated
     */
    EClass getComputedStyleDescriptionRegistry();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions
     * <em>Computed Style Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Computed Style Descriptions</em>'.
     * @see org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getComputedStyleDescriptions()
     * @see #getComputedStyleDescriptionRegistry()
     * @generated
     */
    EReference getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

    /**
     * Returns the meta object for the map '
     * {@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getCache
     * <em>Cache</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Cache</em>'.
     * @see org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry#getCache()
     * @see #getComputedStyleDescriptionRegistry()
     * @generated
     */
    EReference getComputedStyleDescriptionRegistry_Cache();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>Element Mapping2 Model Element</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Element Mapping2 Model Element</em>'.
     * @see java.util.Map.Entry
     * @model 
     *        keyType="org.eclipse.sirius.diagram.description.DiagramElementMapping"
     *        keyRequired="true" valueMapType=
     *        "org.eclipse.sirius.diagram.ModelElement2ViewVariable<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.diagram.ViewVariable2ContainerVariable>"
     * @generated
     */
    EClass getDiagramElementMapping2ModelElement();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getDiagramElementMapping2ModelElement()
     * @generated
     */
    EReference getDiagramElementMapping2ModelElement_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getDiagramElementMapping2ModelElement()
     * @generated
     */
    EReference getDiagramElementMapping2ModelElement_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>Model Element2 View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Element2 View Variable</em>
     *         '.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueMapType=
     *        "org.eclipse.sirius.diagram.ViewVariable2ContainerVariable<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.diagram.ContainerVariable2StyleDescription>"
     * @generated
     */
    EClass getModelElement2ViewVariable();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getModelElement2ViewVariable()
     * @generated
     */
    EReference getModelElement2ViewVariable_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getModelElement2ViewVariable()
     * @generated
     */
    EReference getModelElement2ViewVariable_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>View Variable2 Container Variable</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>View Variable2 Container Variable</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueMapType=
     *        "org.eclipse.sirius.diagram.ContainerVariable2StyleDescription<org.eclipse.emf.ecore.EObject, org.eclipse.sirius.viewpoint.description.style.StyleDescription>"
     * @generated
     */
    EClass getViewVariable2ContainerVariable();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getViewVariable2ContainerVariable()
     * @generated
     */
    EReference getViewVariable2ContainerVariable_Key();

    /**
     * Returns the meta object for the map '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the map '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getViewVariable2ContainerVariable()
     * @generated
     */
    EReference getViewVariable2ContainerVariable_Value();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry
     * <em>Container Variable2 Style Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Container Variable2 Style Description</em>'.
     * @see java.util.Map.Entry
     * @model keyType="org.eclipse.emf.ecore.EObject" keyRequired="true"
     *        valueType=
     *        "org.eclipse.sirius.viewpoint.description.style.StyleDescription"
     * @generated
     */
    EClass getContainerVariable2StyleDescription();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Key</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getContainerVariable2StyleDescription()
     * @generated
     */
    EReference getContainerVariable2StyleDescription_Key();

    /**
     * Returns the meta object for the reference '{@link java.util.Map.Entry
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getContainerVariable2StyleDescription()
     * @generated
     */
    EReference getContainerVariable2StyleDescription_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.DragAndDropTarget
     * <em>Drag And Drop Target</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Drag And Drop Target</em>'.
     * @see org.eclipse.sirius.diagram.DragAndDropTarget
     * @generated
     */
    EClass getDragAndDropTarget();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.ContainerLayout
     * <em>Container Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Container Layout</em>'.
     * @see org.eclipse.sirius.diagram.ContainerLayout
     * @generated
     */
    EEnum getContainerLayout();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.LabelPosition <em>Label Position</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Label Position</em>'.
     * @see org.eclipse.sirius.diagram.LabelPosition
     * @generated
     */
    EEnum getLabelPosition();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.ContainerShape
     * <em>Container Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Container Shape</em>'.
     * @see org.eclipse.sirius.diagram.ContainerShape
     * @generated
     */
    EEnum getContainerShape();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.BackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Background Style</em>'.
     * @see org.eclipse.sirius.diagram.BackgroundStyle
     * @generated
     */
    EEnum getBackgroundStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.BundledImageShape
     * <em>Bundled Image Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Bundled Image Shape</em>'.
     * @see org.eclipse.sirius.diagram.BundledImageShape
     * @generated
     */
    EEnum getBundledImageShape();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.LineStyle <em>Line Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.EdgeArrows <em>Edge Arrows</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Edge Arrows</em>'.
     * @see org.eclipse.sirius.diagram.EdgeArrows
     * @generated
     */
    EEnum getEdgeArrows();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.EdgeRouting <em>Edge Routing</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Edge Routing</em>'.
     * @see org.eclipse.sirius.diagram.EdgeRouting
     * @generated
     */
    EEnum getEdgeRouting();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.AlignmentKind <em>Alignment Kind</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Alignment Kind</em>'.
     * @see org.eclipse.sirius.diagram.AlignmentKind
     * @generated
     */
    EEnum getAlignmentKind();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.ResizeKind <em>Resize Kind</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.diagram.ResizeKind
     * @generated
     */
    EEnum getResizeKind();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.diagram.ArrangeConstraint
     * <em>Arrange Constraint</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Arrange Constraint</em>'.
     * @see org.eclipse.sirius.diagram.ArrangeConstraint
     * @generated
     */
    EEnum getArrangeConstraint();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DiagramFactory getDiagramFactory();

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
         * {@link org.eclipse.sirius.diagram.impl.DDiagramImpl
         * <em>DDiagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DDiagramImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagram()
         * @generated
         */
        EClass DDIAGRAM = eINSTANCE.getDDiagram();

        /**
         * The meta object literal for the '
         * <em><b>Owned Diagram Elements</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = eINSTANCE.getDDiagram_OwnedDiagramElements();

        /**
         * The meta object literal for the '<em><b>Diagram Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__DIAGRAM_ELEMENTS = eINSTANCE.getDDiagram_DiagramElements();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__DESCRIPTION = eINSTANCE.getDDiagram_Description();

        /**
         * The meta object literal for the '<em><b>Edges</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__EDGES = eINSTANCE.getDDiagram_Edges();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__NODES = eINSTANCE.getDDiagram_Nodes();

        /**
         * The meta object literal for the '<em><b>Node List Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__NODE_LIST_ELEMENTS = eINSTANCE.getDDiagram_NodeListElements();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__CONTAINERS = eINSTANCE.getDDiagram_Containers();

        /**
         * The meta object literal for the '<em><b>Current Concern</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__CURRENT_CONCERN = eINSTANCE.getDDiagram_CurrentConcern();

        /**
         * The meta object literal for the '<em><b>Activated Filters</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_FILTERS = eINSTANCE.getDDiagram_ActivatedFilters();

        /**
         * The meta object literal for the '<em><b>All Filters</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ALL_FILTERS = eINSTANCE.getDDiagram_AllFilters();

        /**
         * The meta object literal for the '<em><b>Activated Rules</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_RULES = eINSTANCE.getDDiagram_ActivatedRules();

        /**
         * The meta object literal for the '<em><b>Activate Behaviors</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATE_BEHAVIORS = eINSTANCE.getDDiagram_ActivateBehaviors();

        /**
         * The meta object literal for the '
         * <em><b>Filter Variable History</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__FILTER_VARIABLE_HISTORY = eINSTANCE.getDDiagram_FilterVariableHistory();

        /**
         * The meta object literal for the '<em><b>Activated Layers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__ACTIVATED_LAYERS = eINSTANCE.getDDiagram_ActivatedLayers();

        /**
         * The meta object literal for the '<em><b>Synchronized</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__SYNCHRONIZED = eINSTANCE.getDDiagram_Synchronized();

        /**
         * The meta object literal for the '<em><b>Hidden Elements</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM__HIDDEN_ELEMENTS = eINSTANCE.getDDiagram_HiddenElements();

        /**
         * The meta object literal for the '<em><b>Is In Layouting Mode</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__IS_IN_LAYOUTING_MODE = eINSTANCE.getDDiagram_IsInLayoutingMode();

        /**
         * The meta object literal for the '<em><b>Header Height</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM__HEADER_HEIGHT = eINSTANCE.getDDiagram_HeaderHeight();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl
         * <em>DSemantic Diagram</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DSemanticDiagramImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDSemanticDiagram()
         * @generated
         */
        EClass DSEMANTIC_DIAGRAM = eINSTANCE.getDSemanticDiagram();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DDiagramElementImpl
         * <em>DDiagram Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DDiagramElementImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElement()
         * @generated
         */
        EClass DDIAGRAM_ELEMENT = eINSTANCE.getDDiagramElement();

        /**
         * The meta object literal for the '<em><b>Visible</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT__VISIBLE = eINSTANCE.getDDiagramElement_Visible();

        /**
         * The meta object literal for the '<em><b>Tooltip Text</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT__TOOLTIP_TEXT = eINSTANCE.getDDiagramElement_TooltipText();

        /**
         * The meta object literal for the '<em><b>Parent Layers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__PARENT_LAYERS = eINSTANCE.getDDiagramElement_ParentLayers();

        /**
         * The meta object literal for the '<em><b>Decorations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__DECORATIONS = eINSTANCE.getDDiagramElement_Decorations();

        /**
         * The meta object literal for the '
         * <em><b>Diagram Element Mapping</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING = eINSTANCE.getDDiagramElement_DiagramElementMapping();

        /**
         * The meta object literal for the '<em><b>Graphical Filters</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS = eINSTANCE.getDDiagramElement_GraphicalFilters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.GraphicalFilter
         * <em>Graphical Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.GraphicalFilter
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGraphicalFilter()
         * @generated
         */
        EClass GRAPHICAL_FILTER = eINSTANCE.getGraphicalFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.HideFilterImpl
         * <em>Hide Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.HideFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideFilter()
         * @generated
         */
        EClass HIDE_FILTER = eINSTANCE.getHideFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.HideLabelFilterImpl
         * <em>Hide Label Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.HideLabelFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getHideLabelFilter()
         * @generated
         */
        EClass HIDE_LABEL_FILTER = eINSTANCE.getHideLabelFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl
         * <em>Folding Point Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.FoldingPointFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingPointFilter()
         * @generated
         */
        EClass FOLDING_POINT_FILTER = eINSTANCE.getFoldingPointFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.FoldingFilterImpl
         * <em>Folding Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.FoldingFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFoldingFilter()
         * @generated
         */
        EClass FOLDING_FILTER = eINSTANCE.getFoldingFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
         * <em>Applied Composite Filters</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.AppliedCompositeFiltersImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAppliedCompositeFilters()
         * @generated
         */
        EClass APPLIED_COMPOSITE_FILTERS = eINSTANCE.getAppliedCompositeFilters();

        /**
         * The meta object literal for the '
         * <em><b>Composite Filter Descriptions</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS = eINSTANCE.getAppliedCompositeFilters_CompositeFilterDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl
         * <em>Absolute Bounds Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.AbsoluteBoundsFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbsoluteBoundsFilter()
         * @generated
         */
        EClass ABSOLUTE_BOUNDS_FILTER = eINSTANCE.getAbsoluteBoundsFilter();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__X = eINSTANCE.getAbsoluteBoundsFilter_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__Y = eINSTANCE.getAbsoluteBoundsFilter_Y();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__HEIGHT = eINSTANCE.getAbsoluteBoundsFilter_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSOLUTE_BOUNDS_FILTER__WIDTH = eINSTANCE.getAbsoluteBoundsFilter_Width();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.AbstractDNode
         * <em>Abstract DNode</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.AbstractDNode
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAbstractDNode()
         * @generated
         */
        EClass ABSTRACT_DNODE = eINSTANCE.getAbstractDNode();

        /**
         * The meta object literal for the '<em><b>Owned Bordered Nodes</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_DNODE__OWNED_BORDERED_NODES = eINSTANCE.getAbstractDNode_OwnedBorderedNodes();

        /**
         * The meta object literal for the '<em><b>Arrange Constraints</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_DNODE__ARRANGE_CONSTRAINTS = eINSTANCE.getAbstractDNode_ArrangeConstraints();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DNodeImpl <em>DNode</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DNodeImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNode()
         * @generated
         */
        EClass DNODE = eINSTANCE.getDNode();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__WIDTH = eINSTANCE.getDNode_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__HEIGHT = eINSTANCE.getDNode_Height();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__OWNED_STYLE = eINSTANCE.getDNode_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__LABEL_POSITION = eINSTANCE.getDNode_LabelPosition();

        /**
         * The meta object literal for the '<em><b>Resize Kind</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE__RESIZE_KIND = eINSTANCE.getDNode_ResizeKind();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__ORIGINAL_STYLE = eINSTANCE.getDNode_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__ACTUAL_MAPPING = eINSTANCE.getDNode_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE__CANDIDATES_MAPPING = eINSTANCE.getDNode_CandidatesMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
         * <em>DDiagram Element Container</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DDiagramElementContainerImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDDiagramElementContainer()
         * @generated
         */
        EClass DDIAGRAM_ELEMENT_CONTAINER = eINSTANCE.getDDiagramElementContainer();

        /**
         * The meta object literal for the '<em><b>Nodes</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__NODES = eINSTANCE.getDDiagramElementContainer_Nodes();

        /**
         * The meta object literal for the '<em><b>Containers</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS = eINSTANCE.getDDiagramElementContainer_Containers();

        /**
         * The meta object literal for the '<em><b>Elements</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS = eINSTANCE.getDDiagramElementContainer_Elements();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE = eINSTANCE.getDDiagramElementContainer_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE = eINSTANCE.getDDiagramElementContainer_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING = eINSTANCE.getDDiagramElementContainer_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING = eINSTANCE.getDDiagramElementContainer_CandidatesMapping();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT_CONTAINER__WIDTH = eINSTANCE.getDDiagramElementContainer_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DDIAGRAM_ELEMENT_CONTAINER__HEIGHT = eINSTANCE.getDDiagramElementContainer_Height();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DNodeContainerImpl
         * <em>DNode Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DNodeContainerImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeContainer()
         * @generated
         */
        EClass DNODE_CONTAINER = eINSTANCE.getDNodeContainer();

        /**
         * The meta object literal for the '
         * <em><b>Owned Diagram Elements</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS = eINSTANCE.getDNodeContainer_OwnedDiagramElements();

        /**
         * The meta object literal for the '
         * <em><b>Children Presentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DNODE_CONTAINER__CHILDREN_PRESENTATION = eINSTANCE.getDNodeContainer_ChildrenPresentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DNodeListImpl
         * <em>DNode List</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DNodeListImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeList()
         * @generated
         */
        EClass DNODE_LIST = eINSTANCE.getDNodeList();

        /**
         * The meta object literal for the '<em><b>Owned Elements</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST__OWNED_ELEMENTS = eINSTANCE.getDNodeList_OwnedElements();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DNodeListElementImpl
         * <em>DNode List Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DNodeListElementImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDNodeListElement()
         * @generated
         */
        EClass DNODE_LIST_ELEMENT = eINSTANCE.getDNodeListElement();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__OWNED_STYLE = eINSTANCE.getDNodeListElement_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__ORIGINAL_STYLE = eINSTANCE.getDNodeListElement_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__ACTUAL_MAPPING = eINSTANCE.getDNodeListElement_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Candidates Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DNODE_LIST_ELEMENT__CANDIDATES_MAPPING = eINSTANCE.getDNodeListElement_CandidatesMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DEdgeImpl <em>DEdge</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DEdgeImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDEdge()
         * @generated
         */
        EClass DEDGE = eINSTANCE.getDEdge();

        /**
         * The meta object literal for the '<em><b>Owned Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__OWNED_STYLE = eINSTANCE.getDEdge_OwnedStyle();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__SIZE = eINSTANCE.getDEdge_Size();

        /**
         * The meta object literal for the '<em><b>Source Node</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__SOURCE_NODE = eINSTANCE.getDEdge_SourceNode();

        /**
         * The meta object literal for the '<em><b>Target Node</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__TARGET_NODE = eINSTANCE.getDEdge_TargetNode();

        /**
         * The meta object literal for the '<em><b>Actual Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__ACTUAL_MAPPING = eINSTANCE.getDEdge_ActualMapping();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__ROUTING_STYLE = eINSTANCE.getDEdge_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Is Fold</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__IS_FOLD = eINSTANCE.getDEdge_IsFold();

        /**
         * The meta object literal for the '<em><b>Is Mock Edge</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__IS_MOCK_EDGE = eINSTANCE.getDEdge_IsMockEdge();

        /**
         * The meta object literal for the '<em><b>Original Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__ORIGINAL_STYLE = eINSTANCE.getDEdge_OriginalStyle();

        /**
         * The meta object literal for the '<em><b>Path</b></em>' reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DEDGE__PATH = eINSTANCE.getDEdge_Path();

        /**
         * The meta object literal for the '<em><b>Arrange Constraints</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__ARRANGE_CONSTRAINTS = eINSTANCE.getDEdge_ArrangeConstraints();

        /**
         * The meta object literal for the '<em><b>Begin Label</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__BEGIN_LABEL = eINSTANCE.getDEdge_BeginLabel();

        /**
         * The meta object literal for the '<em><b>End Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DEDGE__END_LABEL = eINSTANCE.getDEdge_EndLabel();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.NodeStyleImpl
         * <em>Node Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.NodeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNodeStyle()
         * @generated
         */
        EClass NODE_STYLE = eINSTANCE.getNodeStyle();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE__LABEL_POSITION = eINSTANCE.getNodeStyle_LabelPosition();

        /**
         * The meta object literal for the '
         * <em><b>Hide Label By Default</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE__HIDE_LABEL_BY_DEFAULT = eINSTANCE.getNodeStyle_HideLabelByDefault();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DotImpl <em>Dot</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DotImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDot()
         * @generated
         */
        EClass DOT = eINSTANCE.getDot();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOT__BACKGROUND_COLOR = eINSTANCE.getDot_BackgroundColor();

        /**
         * The meta object literal for the '
         * <em><b>Stroke Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOT__STROKE_SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getDot_StrokeSizeComputationExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.GaugeSectionImpl
         * <em>Gauge Section</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.GaugeSectionImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeSection()
         * @generated
         */
        EClass GAUGE_SECTION = eINSTANCE.getGaugeSection();

        /**
         * The meta object literal for the '<em><b>Min</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__MIN = eINSTANCE.getGaugeSection_Min();

        /**
         * The meta object literal for the '<em><b>Max</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__MAX = eINSTANCE.getGaugeSection_Max();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__VALUE = eINSTANCE.getGaugeSection_Value();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__LABEL = eINSTANCE.getGaugeSection_Label();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__BACKGROUND_COLOR = eINSTANCE.getGaugeSection_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION__FOREGROUND_COLOR = eINSTANCE.getGaugeSection_ForegroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.ContainerStyleImpl
         * <em>Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.ContainerStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerStyle()
         * @generated
         */
        EClass CONTAINER_STYLE = eINSTANCE.getContainerStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl
         * <em>Flat Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.FlatContainerStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFlatContainerStyle()
         * @generated
         */
        EClass FLAT_CONTAINER_STYLE = eINSTANCE.getFlatContainerStyle();

        /**
         * The meta object literal for the '<em><b>Background Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_STYLE = eINSTANCE.getFlatContainerStyle_BackgroundStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__BACKGROUND_COLOR = eINSTANCE.getFlatContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE__FOREGROUND_COLOR = eINSTANCE.getFlatContainerStyle_ForegroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl
         * <em>Shape Container Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.ShapeContainerStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getShapeContainerStyle()
         * @generated
         */
        EClass SHAPE_CONTAINER_STYLE = eINSTANCE.getShapeContainerStyle();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE__SHAPE = eINSTANCE.getShapeContainerStyle_Shape();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR = eINSTANCE.getShapeContainerStyle_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.SquareImpl <em>Square</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.SquareImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getSquare()
         * @generated
         */
        EClass SQUARE = eINSTANCE.getSquare();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE__WIDTH = eINSTANCE.getSquare_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE__HEIGHT = eINSTANCE.getSquare_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE__COLOR = eINSTANCE.getSquare_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.EllipseImpl <em>Ellipse</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.EllipseImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEllipse()
         * @generated
         */
        EClass ELLIPSE = eINSTANCE.getEllipse();

        /**
         * The meta object literal for the '<em><b>Horizontal Diameter</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE__HORIZONTAL_DIAMETER = eINSTANCE.getEllipse_HorizontalDiameter();

        /**
         * The meta object literal for the '<em><b>Vertical Diameter</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE__VERTICAL_DIAMETER = eINSTANCE.getEllipse_VerticalDiameter();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE__COLOR = eINSTANCE.getEllipse_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.LozengeImpl <em>Lozenge</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.LozengeImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLozenge()
         * @generated
         */
        EClass LOZENGE = eINSTANCE.getLozenge();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE__WIDTH = eINSTANCE.getLozenge_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE__HEIGHT = eINSTANCE.getLozenge_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE__COLOR = eINSTANCE.getLozenge_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.BundledImageImpl
         * <em>Bundled Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.BundledImageImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImage()
         * @generated
         */
        EClass BUNDLED_IMAGE = eINSTANCE.getBundledImage();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BUNDLED_IMAGE__SHAPE = eINSTANCE.getBundledImage_Shape();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BUNDLED_IMAGE__COLOR = eINSTANCE.getBundledImage_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.WorkspaceImageImpl
         * <em>Workspace Image</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.WorkspaceImageImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getWorkspaceImage()
         * @generated
         */
        EClass WORKSPACE_IMAGE = eINSTANCE.getWorkspaceImage();

        /**
         * The meta object literal for the '<em><b>Workspace Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORKSPACE_IMAGE__WORKSPACE_PATH = eINSTANCE.getWorkspaceImage_WorkspacePath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.CustomStyleImpl
         * <em>Custom Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.CustomStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCustomStyle()
         * @generated
         */
        EClass CUSTOM_STYLE = eINSTANCE.getCustomStyle();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CUSTOM_STYLE__ID = eINSTANCE.getCustomStyle_Id();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.EdgeTargetImpl
         * <em>Edge Target</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.EdgeTargetImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeTarget()
         * @generated
         */
        EClass EDGE_TARGET = eINSTANCE.getEdgeTarget();

        /**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_TARGET__OUTGOING_EDGES = eINSTANCE.getEdgeTarget_OutgoingEdges();

        /**
         * The meta object literal for the '<em><b>Incoming Edges</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_TARGET__INCOMING_EDGES = eINSTANCE.getEdgeTarget_IncomingEdges();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.EdgeStyleImpl
         * <em>Edge Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.EdgeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeStyle()
         * @generated
         */
        EClass EDGE_STYLE = eINSTANCE.getEdgeStyle();

        /**
         * The meta object literal for the '<em><b>Stroke Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__STROKE_COLOR = eINSTANCE.getEdgeStyle_StrokeColor();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__LINE_STYLE = eINSTANCE.getEdgeStyle_LineStyle();

        /**
         * The meta object literal for the '<em><b>Source Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__SOURCE_ARROW = eINSTANCE.getEdgeStyle_SourceArrow();

        /**
         * The meta object literal for the '<em><b>Target Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__TARGET_ARROW = eINSTANCE.getEdgeStyle_TargetArrow();

        /**
         * The meta object literal for the '<em><b>Folding Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__FOLDING_STYLE = eINSTANCE.getEdgeStyle_FoldingStyle();

        /**
         * The meta object literal for the '<em><b>Size</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__SIZE = eINSTANCE.getEdgeStyle_Size();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__ROUTING_STYLE = eINSTANCE.getEdgeStyle_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Begin Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__BEGIN_LABEL_STYLE = eINSTANCE.getEdgeStyle_BeginLabelStyle();

        /**
         * The meta object literal for the '<em><b>Center Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__CENTER_LABEL_STYLE = eINSTANCE.getEdgeStyle_CenterLabelStyle();

        /**
         * The meta object literal for the '<em><b>End Label Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE__END_LABEL_STYLE = eINSTANCE.getEdgeStyle_EndLabelStyle();

        /**
         * The meta object literal for the '<em><b>Centered</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE__CENTERED = eINSTANCE.getEdgeStyle_Centered();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl
         * <em>Gauge Composite Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.GaugeCompositeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getGaugeCompositeStyle()
         * @generated
         */
        EClass GAUGE_COMPOSITE_STYLE = eINSTANCE.getGaugeCompositeStyle();

        /**
         * The meta object literal for the '<em><b>Alignment</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_COMPOSITE_STYLE__ALIGNMENT = eINSTANCE.getGaugeCompositeStyle_Alignment();

        /**
         * The meta object literal for the '<em><b>Sections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_COMPOSITE_STYLE__SECTIONS = eINSTANCE.getGaugeCompositeStyle_Sections();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.BorderedStyleImpl
         * <em>Bordered Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.BorderedStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBorderedStyle()
         * @generated
         */
        EClass BORDERED_STYLE = eINSTANCE.getBorderedStyle();

        /**
         * The meta object literal for the '<em><b>Border Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE = eINSTANCE.getBorderedStyle_BorderSize();

        /**
         * The meta object literal for the '
         * <em><b>Border Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getBorderedStyle_BorderSizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BORDERED_STYLE__BORDER_COLOR = eINSTANCE.getBorderedStyle_BorderColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.NoteImpl <em>Note</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.NoteImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getNote()
         * @generated
         */
        EClass NOTE = eINSTANCE.getNote();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NOTE__COLOR = eINSTANCE.getNote_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl
         * <em>Filter Variable History</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.FilterVariableHistoryImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFilterVariableHistory()
         * @generated
         */
        EClass FILTER_VARIABLE_HISTORY = eINSTANCE.getFilterVariableHistory();

        /**
         * The meta object literal for the '<em><b>Owned Values</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference FILTER_VARIABLE_HISTORY__OWNED_VALUES = eINSTANCE.getFilterVariableHistory_OwnedValues();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.FilterVariableValueImpl
         * <em>Filter Variable Value</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.FilterVariableValueImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getFilterVariableValue()
         * @generated
         */
        EClass FILTER_VARIABLE_VALUE = eINSTANCE.getFilterVariableValue();

        /**
         * The meta object literal for the '<em><b>Variable Definition</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FILTER_VARIABLE_VALUE__VARIABLE_DEFINITION = eINSTANCE.getFilterVariableValue_VariableDefinition();

        /**
         * The meta object literal for the '<em><b>Model Element</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FILTER_VARIABLE_VALUE__MODEL_ELEMENT = eINSTANCE.getFilterVariableValue_ModelElement();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.CollapseFilterImpl
         * <em>Collapse Filter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.CollapseFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCollapseFilter()
         * @generated
         */
        EClass COLLAPSE_FILTER = eINSTANCE.getCollapseFilter();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLLAPSE_FILTER__WIDTH = eINSTANCE.getCollapseFilter_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLLAPSE_FILTER__HEIGHT = eINSTANCE.getCollapseFilter_Height();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
         * <em>Indirectly Collapse Filter</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.IndirectlyCollapseFilterImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getIndirectlyCollapseFilter()
         * @generated
         */
        EClass INDIRECTLY_COLLAPSE_FILTER = eINSTANCE.getIndirectlyCollapseFilter();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl
         * <em>Begin Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.BeginLabelStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBeginLabelStyle()
         * @generated
         */
        EClass BEGIN_LABEL_STYLE = eINSTANCE.getBeginLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl
         * <em>Center Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.CenterLabelStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getCenterLabelStyle()
         * @generated
         */
        EClass CENTER_LABEL_STYLE = eINSTANCE.getCenterLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.EndLabelStyleImpl
         * <em>End Label Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.EndLabelStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEndLabelStyle()
         * @generated
         */
        EClass END_LABEL_STYLE = eINSTANCE.getEndLabelStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl
         * <em>Bracket Edge Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.BracketEdgeStyleImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBracketEdgeStyle()
         * @generated
         */
        EClass BRACKET_EDGE_STYLE = eINSTANCE.getBracketEdgeStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
         * <em>Computed Style Description Registry</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.ComputedStyleDescriptionRegistryImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getComputedStyleDescriptionRegistry()
         * @generated
         */
        EClass COMPUTED_STYLE_DESCRIPTION_REGISTRY = eINSTANCE.getComputedStyleDescriptionRegistry();

        /**
         * The meta object literal for the '
         * <em><b>Computed Style Descriptions</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS = eINSTANCE.getComputedStyleDescriptionRegistry_ComputedStyleDescriptions();

        /**
         * The meta object literal for the '<em><b>Cache</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference COMPUTED_STYLE_DESCRIPTION_REGISTRY__CACHE = eINSTANCE.getComputedStyleDescriptionRegistry_Cache();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DiagramElementMapping2ModelElementImpl
         * <em>Element Mapping2 Model Element</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DiagramElementMapping2ModelElementImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDiagramElementMapping2ModelElement()
         * @generated
         */
        EClass DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT = eINSTANCE.getDiagramElementMapping2ModelElement();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__KEY = eINSTANCE.getDiagramElementMapping2ModelElement_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT__VALUE = eINSTANCE.getDiagramElementMapping2ModelElement_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.ModelElement2ViewVariableImpl
         * <em>Model Element2 View Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.ModelElement2ViewVariableImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getModelElement2ViewVariable()
         * @generated
         */
        EClass MODEL_ELEMENT2_VIEW_VARIABLE = eINSTANCE.getModelElement2ViewVariable();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_ELEMENT2_VIEW_VARIABLE__KEY = eINSTANCE.getModelElement2ViewVariable_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MODEL_ELEMENT2_VIEW_VARIABLE__VALUE = eINSTANCE.getModelElement2ViewVariable_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.ViewVariable2ContainerVariableImpl
         * <em>View Variable2 Container Variable</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.ViewVariable2ContainerVariableImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getViewVariable2ContainerVariable()
         * @generated
         */
        EClass VIEW_VARIABLE2_CONTAINER_VARIABLE = eINSTANCE.getViewVariable2ContainerVariable();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_VARIABLE2_CONTAINER_VARIABLE__KEY = eINSTANCE.getViewVariable2ContainerVariable_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' map feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_VARIABLE2_CONTAINER_VARIABLE__VALUE = eINSTANCE.getViewVariable2ContainerVariable_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.ContainerVariable2StyleDescriptionImpl
         * <em>Container Variable2 Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.ContainerVariable2StyleDescriptionImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerVariable2StyleDescription()
         * @generated
         */
        EClass CONTAINER_VARIABLE2_STYLE_DESCRIPTION = eINSTANCE.getContainerVariable2StyleDescription();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_VARIABLE2_STYLE_DESCRIPTION__KEY = eINSTANCE.getContainerVariable2StyleDescription_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_VARIABLE2_STYLE_DESCRIPTION__VALUE = eINSTANCE.getContainerVariable2StyleDescription_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl
         * <em>Drag And Drop Target</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.impl.DragAndDropTargetImpl
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getDragAndDropTarget()
         * @generated
         */
        EClass DRAG_AND_DROP_TARGET = eINSTANCE.getDragAndDropTarget();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.ContainerLayout
         * <em>Container Layout</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.ContainerLayout
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerLayout()
         * @generated
         */
        EEnum CONTAINER_LAYOUT = eINSTANCE.getContainerLayout();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.LabelPosition
         * <em>Label Position</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.LabelPosition
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLabelPosition()
         * @generated
         */
        EEnum LABEL_POSITION = eINSTANCE.getLabelPosition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.ContainerShape
         * <em>Container Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.ContainerShape
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getContainerShape()
         * @generated
         */
        EEnum CONTAINER_SHAPE = eINSTANCE.getContainerShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.BackgroundStyle
         * <em>Background Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.BackgroundStyle
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBackgroundStyle()
         * @generated
         */
        EEnum BACKGROUND_STYLE = eINSTANCE.getBackgroundStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.BundledImageShape
         * <em>Bundled Image Shape</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.BundledImageShape
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getBundledImageShape()
         * @generated
         */
        EEnum BUNDLED_IMAGE_SHAPE = eINSTANCE.getBundledImageShape();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.LineStyle <em>Line Style</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.LineStyle
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = eINSTANCE.getLineStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.EdgeArrows <em>Edge Arrows</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.EdgeArrows
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeArrows()
         * @generated
         */
        EEnum EDGE_ARROWS = eINSTANCE.getEdgeArrows();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.EdgeRouting <em>Edge Routing</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.EdgeRouting
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getEdgeRouting()
         * @generated
         */
        EEnum EDGE_ROUTING = eINSTANCE.getEdgeRouting();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.AlignmentKind
         * <em>Alignment Kind</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.AlignmentKind
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getAlignmentKind()
         * @generated
         */
        EEnum ALIGNMENT_KIND = eINSTANCE.getAlignmentKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.ResizeKind <em>Resize Kind</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.ResizeKind
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getResizeKind()
         * @generated
         */
        EEnum RESIZE_KIND = eINSTANCE.getResizeKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.ArrangeConstraint
         * <em>Arrange Constraint</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.ArrangeConstraint
         * @see org.eclipse.sirius.diagram.impl.DiagramPackageImpl#getArrangeConstraint()
         * @generated
         */
        EEnum ARRANGE_CONSTRAINT = eINSTANCE.getArrangeConstraint();

    }

} // DiagramPackage
