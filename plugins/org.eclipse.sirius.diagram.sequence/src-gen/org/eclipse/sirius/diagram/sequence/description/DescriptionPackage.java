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
package org.eclipse.sirius.diagram.sequence.description;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

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
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionFactory
 * @model kind="package"
 * @generated
 */
public interface DescriptionPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "description"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/sequence/description/2.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "description"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    DescriptionPackage eINSTANCE = org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl
     * <em>Sequence Diagram Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getSequenceDiagramDescription()
     * @generated
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__TITLE_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__INITIALISATION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__METAMODEL = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__FILTERS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__VALIDATION_SET = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__VALIDATION_SET;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__CONCERNS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__CONCERNS;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ALL_TOOLS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_TOOLS;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Default Concern</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__DEFAULT_CONCERN = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_CONCERN;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ROOT_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ROOT_EXPRESSION;

    /**
     * The feature id for the '<em><b>Init</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__INIT = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__INIT;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__LAYOUT = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__LAYOUT;

    /**
     * The feature id for the '<em><b>Diagram Initialisation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION;

    /**
     * The feature id for the '<em><b>Default Layer</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__DEFAULT_LAYER = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__DEFAULT_LAYER;

    /**
     * The feature id for the '<em><b>Additional Layers</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS;

    /**
     * The feature id for the '<em><b>All Layers</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ALL_LAYERS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_LAYERS;

    /**
     * The feature id for the '<em><b>All Activated Tools</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__EDGE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__REUSED_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_MAPPINGS;

    /**
     * The feature id for the '<em><b>Tool Section</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__TOOL_SECTION = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__TOOL_SECTION;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__REUSED_TOOLS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__REUSED_TOOLS;

    /**
     * The feature id for the '<em><b>Enable Popup Bars</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS;

    /**
     * The feature id for the '<em><b>Ends Ordering</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Instance Roles Ordering</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Sequence Diagram Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.DIAGRAM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.InstanceRoleMappingImpl
     * <em>Instance Role Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.InstanceRoleMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getInstanceRoleMapping()
     * @generated
     */
    int INSTANCE_ROLE_MAPPING = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The number of structural features of the '<em>Instance Role Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.EventMappingImpl
     * <em>Event Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.diagram.sequence.description.impl.EventMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getEventMapping()
     * @generated
     */
    int EVENT_MAPPING = 2;

    /**
     * The number of structural features of the '<em>Event Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EVENT_MAPPING_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.DelimitedEventMappingImpl
     * <em>Delimited Event Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DelimitedEventMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getDelimitedEventMapping()
     * @generated
     */
    int DELIMITED_EVENT_MAPPING = 3;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION = DescriptionPackage.EVENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION = DescriptionPackage.EVENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Delimited Event Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELIMITED_EVENT_MAPPING_FEATURE_COUNT = DescriptionPackage.EVENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.ExecutionMappingImpl
     * <em>Execution Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.ExecutionMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getExecutionMapping()
     * @generated
     */
    int EXECUTION_MAPPING = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__STARTING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING__FINISHING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Execution Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.StateMappingImpl
     * <em>State Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.diagram.sequence.description.impl.StateMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getStateMapping()
     * @generated
     */
    int STATE_MAPPING = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__STARTING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING__FINISHING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>State Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.EndOfLifeMappingImpl
     * <em>End Of Life Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.EndOfLifeMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getEndOfLifeMapping()
     * @generated
     */
    int END_OF_LIFE_MAPPING = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The number of structural features of the '<em>End Of Life Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_OF_LIFE_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.MessageMappingImpl
     * <em>Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.MessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getMessageMapping()
     * @generated
     */
    int MESSAGE_MAPPING = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__SOURCE_MAPPING = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__SOURCE_MAPPING;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__TARGET_MAPPING = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__TARGET_MAPPING;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__TARGET_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__TARGET_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__RECONNECTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__RECONNECTIONS;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__PATH_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__PATH_EXPRESSION;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__PATH_NODE_MAPPING = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING__PATH_NODE_MAPPING;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Message Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.EDGE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.BasicMessageMappingImpl
     * <em>Basic Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.BasicMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getBasicMessageMapping()
     * @generated
     */
    int BASIC_MESSAGE_MAPPING = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__NAME = DescriptionPackage.MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__LABEL = DescriptionPackage.MESSAGE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.MESSAGE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.MESSAGE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__DOCUMENTATION = DescriptionPackage.MESSAGE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__SOURCE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__SOURCE_MAPPING;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__TARGET_MAPPING = DescriptionPackage.MESSAGE_MAPPING__TARGET_MAPPING;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__STYLE = DescriptionPackage.MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.MESSAGE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__TARGET_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__DOMAIN_CLASS = DescriptionPackage.MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = DescriptionPackage.MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__RECONNECTIONS = DescriptionPackage.MESSAGE_MAPPING__RECONNECTIONS;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__PATH_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PATH_EXPRESSION;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__PATH_NODE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__PATH_NODE_MAPPING;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The number of structural features of the '<em>Basic Message Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_MESSAGE_MAPPING_FEATURE_COUNT = DescriptionPackage.MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.ReturnMessageMappingImpl
     * <em>Return Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.ReturnMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getReturnMessageMapping()
     * @generated
     */
    int RETURN_MESSAGE_MAPPING = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__NAME = DescriptionPackage.MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__LABEL = DescriptionPackage.MESSAGE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.MESSAGE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.MESSAGE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__DOCUMENTATION = DescriptionPackage.MESSAGE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__SOURCE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__SOURCE_MAPPING;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__TARGET_MAPPING = DescriptionPackage.MESSAGE_MAPPING__TARGET_MAPPING;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__STYLE = DescriptionPackage.MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.MESSAGE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__TARGET_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__DOMAIN_CLASS = DescriptionPackage.MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = DescriptionPackage.MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__RECONNECTIONS = DescriptionPackage.MESSAGE_MAPPING__RECONNECTIONS;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__PATH_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PATH_EXPRESSION;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__PATH_NODE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__PATH_NODE_MAPPING;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '
     * <em><b>Invocation Message Finder Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Return Message Mapping</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RETURN_MESSAGE_MAPPING_FEATURE_COUNT = DescriptionPackage.MESSAGE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.CreationMessageMappingImpl
     * <em>Creation Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.CreationMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getCreationMessageMapping()
     * @generated
     */
    int CREATION_MESSAGE_MAPPING = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__NAME = DescriptionPackage.MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__LABEL = DescriptionPackage.MESSAGE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.MESSAGE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.MESSAGE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__DOCUMENTATION = DescriptionPackage.MESSAGE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__SOURCE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__SOURCE_MAPPING;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__TARGET_MAPPING = DescriptionPackage.MESSAGE_MAPPING__TARGET_MAPPING;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__STYLE = DescriptionPackage.MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.MESSAGE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__TARGET_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__DOMAIN_CLASS = DescriptionPackage.MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = DescriptionPackage.MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__RECONNECTIONS = DescriptionPackage.MESSAGE_MAPPING__RECONNECTIONS;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__PATH_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PATH_EXPRESSION;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__PATH_NODE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__PATH_NODE_MAPPING;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The number of structural features of the '
     * <em>Creation Message Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATION_MESSAGE_MAPPING_FEATURE_COUNT = DescriptionPackage.MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.DestructionMessageMappingImpl
     * <em>Destruction Message Mapping</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DestructionMessageMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getDestructionMessageMapping()
     * @generated
     */
    int DESTRUCTION_MESSAGE_MAPPING = 11;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__NAME = DescriptionPackage.MESSAGE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__LABEL = DescriptionPackage.MESSAGE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.MESSAGE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.MESSAGE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.MESSAGE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.MESSAGE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.MESSAGE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__DOCUMENTATION = DescriptionPackage.MESSAGE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__SOURCE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__SOURCE_MAPPING;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__TARGET_MAPPING = DescriptionPackage.MESSAGE_MAPPING__TARGET_MAPPING;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SOURCE_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__STYLE = DescriptionPackage.MESSAGE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.MESSAGE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__TARGET_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__TARGET_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__DOMAIN_CLASS = DescriptionPackage.MESSAGE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__USE_DOMAIN_ELEMENT = DescriptionPackage.MESSAGE_MAPPING__USE_DOMAIN_ELEMENT;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__RECONNECTIONS = DescriptionPackage.MESSAGE_MAPPING__RECONNECTIONS;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__PATH_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__PATH_EXPRESSION;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__PATH_NODE_MAPPING = DescriptionPackage.MESSAGE_MAPPING__PATH_NODE_MAPPING;

    /**
     * The feature id for the '<em><b>Sending End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Receiving End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = DescriptionPackage.MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION;

    /**
     * The number of structural features of the '
     * <em>Destruction Message Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DESTRUCTION_MESSAGE_MAPPING_FEATURE_COUNT = DescriptionPackage.MESSAGE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.MessageEndVariableImpl
     * <em>Message End Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.MessageEndVariableImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getMessageEndVariable()
     * @generated
     */
    int MESSAGE_END_VARIABLE = 12;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_END_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '<em>Message End Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_END_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.CoveredLifelinesVariableImpl
     * <em>Covered Lifelines Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.CoveredLifelinesVariableImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getCoveredLifelinesVariable()
     * @generated
     */
    int COVERED_LIFELINES_VARIABLE = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COVERED_LIFELINES_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '
     * <em>Covered Lifelines Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COVERED_LIFELINES_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl
     * <em>Frame Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getFrameMapping()
     * @generated
     */
    int FRAME_MAPPING = 14;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__SUB_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__ALL_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__REUSED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__SUB_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__REUSED_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__ALL_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__CHILDREN_PRESENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Covered Lifelines Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Center Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING__CENTER_LABEL_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Frame Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FRAME_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.InteractionUseMappingImpl
     * <em>Interaction Use Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.InteractionUseMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getInteractionUseMapping()
     * @generated
     */
    int INTERACTION_USE_MAPPING = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__NAME = DescriptionPackage.FRAME_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__LABEL = DescriptionPackage.FRAME_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.FRAME_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.FRAME_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.FRAME_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.FRAME_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.FRAME_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.FRAME_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.FRAME_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.FRAME_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__DOCUMENTATION = DescriptionPackage.FRAME_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__DOMAIN_CLASS = DescriptionPackage.FRAME_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__BORDERED_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__DROP_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__SUB_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__SUB_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__ALL_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__REUSED_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__REUSED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__SUB_CONTAINER_MAPPINGS = DescriptionPackage.FRAME_MAPPING__SUB_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__REUSED_CONTAINER_MAPPINGS = DescriptionPackage.FRAME_MAPPING__REUSED_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__ALL_CONTAINER_MAPPINGS = DescriptionPackage.FRAME_MAPPING__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__STYLE = DescriptionPackage.FRAME_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.FRAME_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__CHILDREN_PRESENTATION = DescriptionPackage.FRAME_MAPPING__CHILDREN_PRESENTATION;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__STARTING_END_FINDER_EXPRESSION = DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__FINISHING_END_FINDER_EXPRESSION = DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Covered Lifelines Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__COVERED_LIFELINES_EXPRESSION = DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Center Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING__CENTER_LABEL_EXPRESSION = DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION;

    /**
     * The number of structural features of the '
     * <em>Interaction Use Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_MAPPING_FEATURE_COUNT = DescriptionPackage.FRAME_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.CombinedFragmentMappingImpl
     * <em>Combined Fragment Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.CombinedFragmentMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getCombinedFragmentMapping()
     * @generated
     */
    int COMBINED_FRAGMENT_MAPPING = 16;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__NAME = DescriptionPackage.FRAME_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__LABEL = DescriptionPackage.FRAME_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.FRAME_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.FRAME_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.FRAME_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.FRAME_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__CREATE_ELEMENTS = DescriptionPackage.FRAME_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.FRAME_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.FRAME_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.FRAME_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__DOCUMENTATION = DescriptionPackage.FRAME_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__DOMAIN_CLASS = DescriptionPackage.FRAME_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__BORDERED_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__DROP_DESCRIPTIONS = DescriptionPackage.FRAME_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__SUB_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__SUB_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__ALL_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__REUSED_NODE_MAPPINGS = DescriptionPackage.FRAME_MAPPING__REUSED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__SUB_CONTAINER_MAPPINGS = DescriptionPackage.FRAME_MAPPING__SUB_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__REUSED_CONTAINER_MAPPINGS = DescriptionPackage.FRAME_MAPPING__REUSED_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__ALL_CONTAINER_MAPPINGS = DescriptionPackage.FRAME_MAPPING__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__STYLE = DescriptionPackage.FRAME_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.FRAME_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__CHILDREN_PRESENTATION = DescriptionPackage.FRAME_MAPPING__CHILDREN_PRESENTATION;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__STARTING_END_FINDER_EXPRESSION = DescriptionPackage.FRAME_MAPPING__STARTING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__FINISHING_END_FINDER_EXPRESSION = DescriptionPackage.FRAME_MAPPING__FINISHING_END_FINDER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Covered Lifelines Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__COVERED_LIFELINES_EXPRESSION = DescriptionPackage.FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Center Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING__CENTER_LABEL_EXPRESSION = DescriptionPackage.FRAME_MAPPING__CENTER_LABEL_EXPRESSION;

    /**
     * The number of structural features of the '
     * <em>Combined Fragment Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_MAPPING_FEATURE_COUNT = DescriptionPackage.FRAME_MAPPING_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.OperandMappingImpl
     * <em>Operand Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.OperandMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getOperandMapping()
     * @generated
     */
    int OPERAND_MAPPING = 17;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__SUB_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__ALL_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__REUSED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__SUB_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__REUSED_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__ALL_CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__CHILDREN_PRESENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION;

    /**
     * The feature id for the '<em><b>Starting End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__STARTING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING__FINISHING_END_FINDER_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Operand Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.impl.ObservationPointMappingImpl
     * <em>Observation Point Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.impl.ObservationPointMappingImpl
     * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getObservationPointMapping()
     * @generated
     */
    int OBSERVATION_POINT_MAPPING = 18;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__NAME = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__LABEL = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__DOCUMENTATION = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__DOMAIN_CLASS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__DROP_DESCRIPTIONS = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__STYLE = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING__CONDITIONNAL_STYLES = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The number of structural features of the '
     * <em>Observation Point Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_MAPPING_FEATURE_COUNT = org.eclipse.sirius.diagram.description.DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription
     * <em>Sequence Diagram Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Sequence Diagram Description</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription
     * @generated
     */
    EClass getSequenceDiagramDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getEndsOrdering
     * <em>Ends Ordering</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Ends Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getEndsOrdering()
     * @see #getSequenceDiagramDescription()
     * @generated
     */
    EAttribute getSequenceDiagramDescription_EndsOrdering();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getInstanceRolesOrdering
     * <em>Instance Roles Ordering</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Instance Roles Ordering</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription#getInstanceRolesOrdering()
     * @see #getSequenceDiagramDescription()
     * @generated
     */
    EAttribute getSequenceDiagramDescription_InstanceRolesOrdering();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping
     * <em>Instance Role Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Instance Role Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping
     * @generated
     */
    EClass getInstanceRoleMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.EventMapping
     * <em>Event Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Event Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.EventMapping
     * @generated
     */
    EClass getEventMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping
     * <em>Delimited Event Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Delimited Event Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping
     * @generated
     */
    EClass getDelimitedEventMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getStartingEndFinderExpression
     * <em>Starting End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Starting End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getStartingEndFinderExpression()
     * @see #getDelimitedEventMapping()
     * @generated
     */
    EAttribute getDelimitedEventMapping_StartingEndFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getFinishingEndFinderExpression
     * <em>Finishing End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Finishing End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.DelimitedEventMapping#getFinishingEndFinderExpression()
     * @see #getDelimitedEventMapping()
     * @generated
     */
    EAttribute getDelimitedEventMapping_FinishingEndFinderExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.ExecutionMapping
     * <em>Execution Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Execution Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.ExecutionMapping
     * @generated
     */
    EClass getExecutionMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.StateMapping
     * <em>State Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>State Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.StateMapping
     * @generated
     */
    EClass getStateMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping
     * <em>End Of Life Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>End Of Life Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.EndOfLifeMapping
     * @generated
     */
    EClass getEndOfLifeMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.MessageMapping
     * <em>Message Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.MessageMapping
     * @generated
     */
    EClass getMessageMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.MessageMapping#getSendingEndFinderExpression
     * <em>Sending End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Sending End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.MessageMapping#getSendingEndFinderExpression()
     * @see #getMessageMapping()
     * @generated
     */
    EAttribute getMessageMapping_SendingEndFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.MessageMapping#getReceivingEndFinderExpression
     * <em>Receiving End Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Receiving End Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.MessageMapping#getReceivingEndFinderExpression()
     * @see #getMessageMapping()
     * @generated
     */
    EAttribute getMessageMapping_ReceivingEndFinderExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping
     * <em>Basic Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Basic Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping
     * @generated
     */
    EClass getBasicMessageMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping
     * <em>Return Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Return Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping
     * @generated
     */
    EClass getReturnMessageMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping#getInvocationMessageFinderExpression
     * <em>Invocation Message Finder Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Invocation Message Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.ReturnMessageMapping#getInvocationMessageFinderExpression()
     * @see #getReturnMessageMapping()
     * @generated
     */
    EAttribute getReturnMessageMapping_InvocationMessageFinderExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping
     * <em>Creation Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Creation Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.CreationMessageMapping
     * @generated
     */
    EClass getCreationMessageMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping
     * <em>Destruction Message Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Destruction Message Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.DestructionMessageMapping
     * @generated
     */
    EClass getDestructionMessageMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.MessageEndVariable
     * <em>Message End Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Message End Variable</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.MessageEndVariable
     * @generated
     */
    EClass getMessageEndVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable
     * <em>Covered Lifelines Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Covered Lifelines Variable</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.CoveredLifelinesVariable
     * @generated
     */
    EClass getCoveredLifelinesVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.FrameMapping
     * <em>Frame Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Frame Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.FrameMapping
     * @generated
     */
    EClass getFrameMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCoveredLifelinesExpression
     * <em>Covered Lifelines Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Covered Lifelines Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCoveredLifelinesExpression()
     * @see #getFrameMapping()
     * @generated
     */
    EAttribute getFrameMapping_CoveredLifelinesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCenterLabelExpression
     * <em>Center Label Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Center Label Expression</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.FrameMapping#getCenterLabelExpression()
     * @see #getFrameMapping()
     * @generated
     */
    EAttribute getFrameMapping_CenterLabelExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping
     * <em>Interaction Use Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Interaction Use Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.InteractionUseMapping
     * @generated
     */
    EClass getInteractionUseMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping
     * <em>Combined Fragment Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Combined Fragment Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.CombinedFragmentMapping
     * @generated
     */
    EClass getCombinedFragmentMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.OperandMapping
     * <em>Operand Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Operand Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.OperandMapping
     * @generated
     */
    EClass getOperandMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping
     * <em>Observation Point Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Observation Point Mapping</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.ObservationPointMapping
     * @generated
     */
    EClass getObservationPointMapping();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DescriptionFactory getDescriptionFactory();

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
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl
         * <em>Sequence Diagram Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.SequenceDiagramDescriptionImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getSequenceDiagramDescription()
         * @generated
         */
        EClass SEQUENCE_DIAGRAM_DESCRIPTION = DescriptionPackage.eINSTANCE.getSequenceDiagramDescription();

        /**
         * The meta object literal for the '<em><b>Ends Ordering</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SEQUENCE_DIAGRAM_DESCRIPTION__ENDS_ORDERING = DescriptionPackage.eINSTANCE.getSequenceDiagramDescription_EndsOrdering();

        /**
         * The meta object literal for the '
         * <em><b>Instance Roles Ordering</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SEQUENCE_DIAGRAM_DESCRIPTION__INSTANCE_ROLES_ORDERING = DescriptionPackage.eINSTANCE.getSequenceDiagramDescription_InstanceRolesOrdering();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.InstanceRoleMappingImpl
         * <em>Instance Role Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.InstanceRoleMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getInstanceRoleMapping()
         * @generated
         */
        EClass INSTANCE_ROLE_MAPPING = DescriptionPackage.eINSTANCE.getInstanceRoleMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.EventMappingImpl
         * <em>Event Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.EventMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getEventMapping()
         * @generated
         */
        EClass EVENT_MAPPING = DescriptionPackage.eINSTANCE.getEventMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.DelimitedEventMappingImpl
         * <em>Delimited Event Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DelimitedEventMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getDelimitedEventMapping()
         * @generated
         */
        EClass DELIMITED_EVENT_MAPPING = DescriptionPackage.eINSTANCE.getDelimitedEventMapping();

        /**
         * The meta object literal for the '
         * <em><b>Starting End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DELIMITED_EVENT_MAPPING__STARTING_END_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getDelimitedEventMapping_StartingEndFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Finishing End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DELIMITED_EVENT_MAPPING__FINISHING_END_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getDelimitedEventMapping_FinishingEndFinderExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.ExecutionMappingImpl
         * <em>Execution Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.ExecutionMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getExecutionMapping()
         * @generated
         */
        EClass EXECUTION_MAPPING = DescriptionPackage.eINSTANCE.getExecutionMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.StateMappingImpl
         * <em>State Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.StateMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getStateMapping()
         * @generated
         */
        EClass STATE_MAPPING = DescriptionPackage.eINSTANCE.getStateMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.EndOfLifeMappingImpl
         * <em>End Of Life Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.EndOfLifeMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getEndOfLifeMapping()
         * @generated
         */
        EClass END_OF_LIFE_MAPPING = DescriptionPackage.eINSTANCE.getEndOfLifeMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.MessageMappingImpl
         * <em>Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.MessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getMessageMapping()
         * @generated
         */
        EClass MESSAGE_MAPPING = DescriptionPackage.eINSTANCE.getMessageMapping();

        /**
         * The meta object literal for the '
         * <em><b>Sending End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MESSAGE_MAPPING__SENDING_END_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getMessageMapping_SendingEndFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Receiving End Finder Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MESSAGE_MAPPING__RECEIVING_END_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getMessageMapping_ReceivingEndFinderExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.BasicMessageMappingImpl
         * <em>Basic Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.BasicMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getBasicMessageMapping()
         * @generated
         */
        EClass BASIC_MESSAGE_MAPPING = DescriptionPackage.eINSTANCE.getBasicMessageMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.ReturnMessageMappingImpl
         * <em>Return Message Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.ReturnMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getReturnMessageMapping()
         * @generated
         */
        EClass RETURN_MESSAGE_MAPPING = DescriptionPackage.eINSTANCE.getReturnMessageMapping();

        /**
         * The meta object literal for the '
         * <em><b>Invocation Message Finder Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RETURN_MESSAGE_MAPPING__INVOCATION_MESSAGE_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getReturnMessageMapping_InvocationMessageFinderExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.CreationMessageMappingImpl
         * <em>Creation Message Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.CreationMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getCreationMessageMapping()
         * @generated
         */
        EClass CREATION_MESSAGE_MAPPING = DescriptionPackage.eINSTANCE.getCreationMessageMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.DestructionMessageMappingImpl
         * <em>Destruction Message Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DestructionMessageMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getDestructionMessageMapping()
         * @generated
         */
        EClass DESTRUCTION_MESSAGE_MAPPING = DescriptionPackage.eINSTANCE.getDestructionMessageMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.MessageEndVariableImpl
         * <em>Message End Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.MessageEndVariableImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getMessageEndVariable()
         * @generated
         */
        EClass MESSAGE_END_VARIABLE = DescriptionPackage.eINSTANCE.getMessageEndVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.CoveredLifelinesVariableImpl
         * <em>Covered Lifelines Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.CoveredLifelinesVariableImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getCoveredLifelinesVariable()
         * @generated
         */
        EClass COVERED_LIFELINES_VARIABLE = DescriptionPackage.eINSTANCE.getCoveredLifelinesVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl
         * <em>Frame Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.FrameMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getFrameMapping()
         * @generated
         */
        EClass FRAME_MAPPING = DescriptionPackage.eINSTANCE.getFrameMapping();

        /**
         * The meta object literal for the '
         * <em><b>Covered Lifelines Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FRAME_MAPPING__COVERED_LIFELINES_EXPRESSION = DescriptionPackage.eINSTANCE.getFrameMapping_CoveredLifelinesExpression();

        /**
         * The meta object literal for the '
         * <em><b>Center Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FRAME_MAPPING__CENTER_LABEL_EXPRESSION = DescriptionPackage.eINSTANCE.getFrameMapping_CenterLabelExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.InteractionUseMappingImpl
         * <em>Interaction Use Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.InteractionUseMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getInteractionUseMapping()
         * @generated
         */
        EClass INTERACTION_USE_MAPPING = DescriptionPackage.eINSTANCE.getInteractionUseMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.CombinedFragmentMappingImpl
         * <em>Combined Fragment Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.CombinedFragmentMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getCombinedFragmentMapping()
         * @generated
         */
        EClass COMBINED_FRAGMENT_MAPPING = DescriptionPackage.eINSTANCE.getCombinedFragmentMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.OperandMappingImpl
         * <em>Operand Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.OperandMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getOperandMapping()
         * @generated
         */
        EClass OPERAND_MAPPING = DescriptionPackage.eINSTANCE.getOperandMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.impl.ObservationPointMappingImpl
         * <em>Observation Point Mapping</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.impl.ObservationPointMappingImpl
         * @see org.eclipse.sirius.diagram.sequence.description.impl.DescriptionPackageImpl#getObservationPointMapping()
         * @generated
         */
        EClass OBSERVATION_POINT_MAPPING = DescriptionPackage.eINSTANCE.getObservationPointMapping();

    }

} // DescriptionPackage
