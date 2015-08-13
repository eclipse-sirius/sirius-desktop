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
package org.eclipse.sirius.diagram.sequence;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.diagram.DiagramPackage;

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
 * @see org.eclipse.sirius.diagram.sequence.SequenceFactory
 * @model kind="package"
 * @generated
 */
public interface SequencePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "sequence"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/sequence/2.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "sequence"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    SequencePackage eINSTANCE = org.eclipse.sirius.diagram.sequence.impl.SequencePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl
     * <em>DDiagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl
     * @see org.eclipse.sirius.diagram.sequence.impl.SequencePackageImpl#getSequenceDDiagram()
     * @generated
     */
    int SEQUENCE_DDIAGRAM = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__DOCUMENTATION = DiagramPackage.DSEMANTIC_DIAGRAM__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__EANNOTATIONS = DiagramPackage.DSEMANTIC_DIAGRAM__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__OWNED_REPRESENTATION_ELEMENTS = DiagramPackage.DSEMANTIC_DIAGRAM__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__REPRESENTATION_ELEMENTS = DiagramPackage.DSEMANTIC_DIAGRAM__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__NAME = DiagramPackage.DSEMANTIC_DIAGRAM__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__OWNED_ANNOTATION_ENTRIES = DiagramPackage.DSEMANTIC_DIAGRAM__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Ui State</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__UI_STATE = DiagramPackage.DSEMANTIC_DIAGRAM__UI_STATE;

    /**
     * The feature id for the '<em><b>Owned Diagram Elements</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__OWNED_DIAGRAM_ELEMENTS = DiagramPackage.DSEMANTIC_DIAGRAM__OWNED_DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Diagram Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__DIAGRAM_ELEMENTS = DiagramPackage.DSEMANTIC_DIAGRAM__DIAGRAM_ELEMENTS;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__DESCRIPTION = DiagramPackage.DSEMANTIC_DIAGRAM__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Edges</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__EDGES = DiagramPackage.DSEMANTIC_DIAGRAM__EDGES;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__NODES = DiagramPackage.DSEMANTIC_DIAGRAM__NODES;

    /**
     * The feature id for the '<em><b>Node List Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__NODE_LIST_ELEMENTS = DiagramPackage.DSEMANTIC_DIAGRAM__NODE_LIST_ELEMENTS;

    /**
     * The feature id for the '<em><b>Containers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__CONTAINERS = DiagramPackage.DSEMANTIC_DIAGRAM__CONTAINERS;

    /**
     * The feature id for the '<em><b>Current Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__CURRENT_CONCERN = DiagramPackage.DSEMANTIC_DIAGRAM__CURRENT_CONCERN;

    /**
     * The feature id for the '<em><b>Activated Filters</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__ACTIVATED_FILTERS = DiagramPackage.DSEMANTIC_DIAGRAM__ACTIVATED_FILTERS;

    /**
     * The feature id for the '<em><b>All Filters</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__ALL_FILTERS = DiagramPackage.DSEMANTIC_DIAGRAM__ALL_FILTERS;

    /**
     * The feature id for the '<em><b>Activated Rules</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__ACTIVATED_RULES = DiagramPackage.DSEMANTIC_DIAGRAM__ACTIVATED_RULES;

    /**
     * The feature id for the '<em><b>Activate Behaviors</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__ACTIVATE_BEHAVIORS = DiagramPackage.DSEMANTIC_DIAGRAM__ACTIVATE_BEHAVIORS;

    /**
     * The feature id for the '<em><b>Filter Variable History</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__FILTER_VARIABLE_HISTORY = DiagramPackage.DSEMANTIC_DIAGRAM__FILTER_VARIABLE_HISTORY;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__ACTIVATED_LAYERS = DiagramPackage.DSEMANTIC_DIAGRAM__ACTIVATED_LAYERS;

    /**
     * The feature id for the '<em><b>Synchronized</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__SYNCHRONIZED = DiagramPackage.DSEMANTIC_DIAGRAM__SYNCHRONIZED;

    /**
     * The feature id for the '<em><b>Hidden Elements</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__HIDDEN_ELEMENTS = DiagramPackage.DSEMANTIC_DIAGRAM__HIDDEN_ELEMENTS;

    /**
     * The feature id for the '<em><b>Is In Layouting Mode</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__IS_IN_LAYOUTING_MODE = DiagramPackage.DSEMANTIC_DIAGRAM__IS_IN_LAYOUTING_MODE;

    /**
     * The feature id for the '<em><b>Header Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__HEADER_HEIGHT = DiagramPackage.DSEMANTIC_DIAGRAM__HEADER_HEIGHT;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__TARGET = DiagramPackage.DSEMANTIC_DIAGRAM__TARGET;

    /**
     * The feature id for the '<em><b>Semantic Ordering</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING = DiagramPackage.DSEMANTIC_DIAGRAM_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Graphical Ordering</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING = DiagramPackage.DSEMANTIC_DIAGRAM_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Instance Role Semantic Ordering</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING = DiagramPackage.DSEMANTIC_DIAGRAM_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>DDiagram</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DDIAGRAM_FEATURE_COUNT = DiagramPackage.DSEMANTIC_DIAGRAM_FEATURE_COUNT + 3;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram
     * <em>DDiagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DDiagram</em>'.
     * @see org.eclipse.sirius.diagram.sequence.SequenceDDiagram
     * @generated
     */
    EClass getSequenceDDiagram();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getSemanticOrdering
     * <em>Semantic Ordering</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Semantic Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getSemanticOrdering()
     * @see #getSequenceDDiagram()
     * @generated
     */
    EReference getSequenceDDiagram_SemanticOrdering();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getGraphicalOrdering
     * <em>Graphical Ordering</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Graphical Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getGraphicalOrdering()
     * @see #getSequenceDDiagram()
     * @generated
     */
    EReference getSequenceDDiagram_GraphicalOrdering();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getInstanceRoleSemanticOrdering
     * <em>Instance Role Semantic Ordering</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Instance Role Semantic Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getInstanceRoleSemanticOrdering()
     * @see #getSequenceDDiagram()
     * @generated
     */
    EReference getSequenceDDiagram_InstanceRoleSemanticOrdering();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    SequenceFactory getSequenceFactory();

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
         * {@link org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl
         * <em>DDiagram</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.diagram.sequence.impl.SequenceDDiagramImpl
         * @see org.eclipse.sirius.diagram.sequence.impl.SequencePackageImpl#getSequenceDDiagram()
         * @generated
         */
        EClass SEQUENCE_DDIAGRAM = SequencePackage.eINSTANCE.getSequenceDDiagram();

        /**
         * The meta object literal for the '<em><b>Semantic Ordering</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SEQUENCE_DDIAGRAM__SEMANTIC_ORDERING = SequencePackage.eINSTANCE.getSequenceDDiagram_SemanticOrdering();

        /**
         * The meta object literal for the '<em><b>Graphical Ordering</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SEQUENCE_DDIAGRAM__GRAPHICAL_ORDERING = SequencePackage.eINSTANCE.getSequenceDDiagram_GraphicalOrdering();

        /**
         * The meta object literal for the '
         * <em><b>Instance Role Semantic Ordering</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SEQUENCE_DDIAGRAM__INSTANCE_ROLE_SEMANTIC_ORDERING = SequencePackage.eINSTANCE.getSequenceDDiagram_InstanceRoleSemanticOrdering();

    }

} // SequencePackage
