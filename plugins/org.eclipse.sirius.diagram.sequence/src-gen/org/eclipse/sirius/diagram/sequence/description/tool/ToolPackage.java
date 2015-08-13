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
package org.eclipse.sirius.diagram.sequence.description.tool;

import org.eclipse.emf.ecore.EClass;
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
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolFactory
 * @model kind="package"
 * @generated
 */
public interface ToolPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "tool"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/sequence/description/tool/2.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "tool"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ToolPackage eINSTANCE = org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.SequenceDiagramToolDescriptionImpl
     * <em>Sequence Diagram Tool Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.SequenceDiagramToolDescriptionImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getSequenceDiagramToolDescription()
     * @generated
     */
    int SEQUENCE_DIAGRAM_TOOL_DESCRIPTION = 0;

    /**
     * The number of structural features of the '
     * <em>Sequence Diagram Tool Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.OrderedElementCreationToolImpl
     * <em>Ordered Element Creation Tool</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.OrderedElementCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getOrderedElementCreationTool()
     * @generated
     */
    int ORDERED_ELEMENT_CREATION_TOOL = 1;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR = 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR = 1;

    /**
     * The number of structural features of the '
     * <em>Ordered Element Creation Tool</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ORDERED_ELEMENT_CREATION_TOOL_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CoveringElementCreationToolImpl
     * <em>Covering Element Creation Tool</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.CoveringElementCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getCoveringElementCreationTool()
     * @generated
     */
    int COVERING_ELEMENT_CREATION_TOOL = 2;

    /**
     * The feature id for the '<em><b>Covered Lifelines</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES = 0;

    /**
     * The number of structural features of the '
     * <em>Covering Element Creation Tool</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COVERING_ELEMENT_CREATION_TOOL_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleCreationToolImpl
     * <em>Instance Role Creation Tool</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getInstanceRoleCreationTool()
     * @generated
     */
    int INSTANCE_ROLE_CREATION_TOOL = 3;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__NODE_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Predecessor</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Instance Role Creation Tool</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.LifelineCreationToolImpl
     * <em>Lifeline Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.LifelineCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getLifelineCreationTool()
     * @generated
     */
    int LIFELINE_CREATION_TOOL = 4;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The number of structural features of the '<em>Lifeline Creation Tool</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LIFELINE_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl
     * <em>Message Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getMessageCreationTool()
     * @generated
     */
    int MESSAGE_CREATION_TOOL = 5;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__DOCUMENTATION = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__NAME = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__LABEL = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__PRECONDITION = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__FORCE_REFRESH = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__FILTERS = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__ELEMENTS_TO_SELECT = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__INVERSE_SELECTION_ORDER = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__EDGE_MAPPINGS = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Source Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__SOURCE_VARIABLE = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Target Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__TARGET_VARIABLE = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Source View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__SOURCE_VIEW_VARIABLE = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Target View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__TARGET_VIEW_VARIABLE = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__INITIAL_OPERATION = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__ICON_PATH = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Extra Source Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__EXTRA_SOURCE_MAPPINGS = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Extra Target Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__EXTRA_TARGET_MAPPINGS = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Connection Start Precondition</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__CONNECTION_START_PRECONDITION = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__STARTING_END_PREDECESSOR = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL__FINISHING_END_PREDECESSOR = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 19;

    /**
     * The number of structural features of the '<em>Message Creation Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MESSAGE_CREATION_TOOL_FEATURE_COUNT = ToolPackage.SEQUENCE_DIAGRAM_TOOL_DESCRIPTION_FEATURE_COUNT + 20;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ExecutionCreationToolImpl
     * <em>Execution Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ExecutionCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getExecutionCreationTool()
     * @generated
     */
    int EXECUTION_CREATION_TOOL = 6;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__NODE_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__STARTING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL__FINISHING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Execution Creation Tool</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXECUTION_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.StateCreationToolImpl
     * <em>State Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.StateCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getStateCreationTool()
     * @generated
     */
    int STATE_CREATION_TOOL = 7;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__NODE_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__STARTING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL__FINISHING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>State Creation Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl
     * <em>Reorder Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getReorderTool()
     * @generated
     */
    int REORDER_TOOL = 12;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InteractionUseCreationToolImpl
     * <em>Interaction Use Creation Tool</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.InteractionUseCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getInteractionUseCreationTool()
     * @generated
     */
    int INTERACTION_USE_CREATION_TOOL = 8;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__STARTING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__FINISHING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Covered Lifelines</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL__COVERED_LIFELINES = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Interaction Use Creation Tool</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERACTION_USE_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl
     * <em>Combined Fragment Creation Tool</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getCombinedFragmentCreationTool()
     * @generated
     */
    int COMBINED_FRAGMENT_CREATION_TOOL = 9;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__STARTING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Covered Lifelines</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL__COVERED_LIFELINES = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Combined Fragment Creation Tool</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMBINED_FRAGMENT_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.OperandCreationToolImpl
     * <em>Operand Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.OperandCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getOperandCreationTool()
     * @generated
     */
    int OPERAND_CREATION_TOOL = 10;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__CONTAINER_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__STARTING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL__FINISHING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Operand Creation Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERAND_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ObservationPointCreationToolImpl
     * <em>Observation Point Creation Tool</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ObservationPointCreationToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getObservationPointCreationTool()
     * @generated
     */
    int OBSERVATION_POINT_CREATION_TOOL = 11;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__DOCUMENTATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__NAME = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__LABEL = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__PRECONDITION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__FORCE_REFRESH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__FILTERS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__NODE_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VARIABLE;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__VIEW_VARIABLE = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__INITIAL_OPERATION = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__ICON_PATH = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__EXTRA_MAPPINGS = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS;

    /**
     * The feature id for the '<em><b>Starting End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__STARTING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL__FINISHING_END_PREDECESSOR = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Observation Point Creation Tool</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OBSERVATION_POINT_CREATION_TOOL_FEATURE_COUNT = org.eclipse.sirius.diagram.description.tool.ToolPackage.NODE_CREATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REORDER_TOOL__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REORDER_TOOL__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REORDER_TOOL__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REORDER_TOOL__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REORDER_TOOL__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REORDER_TOOL__MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Starting End Predecessor Before</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Starting End Predecessor After</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor Before</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Finishing End Predecessor After</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>On Event Moved Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL__ON_EVENT_MOVED_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Reorder Tool</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REORDER_TOOL_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl
     * <em>Instance Role Reorder Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl
     * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getInstanceRoleReorderTool()
     * @generated
     */
    int INSTANCE_ROLE_REORDER_TOOL = 13;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Predecessor Before</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Predecessor After</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Instance Role Moved</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>Instance Role Reorder Tool</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INSTANCE_ROLE_REORDER_TOOL_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.SequenceDiagramToolDescription
     * <em>Sequence Diagram Tool Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Sequence Diagram Tool Description</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.SequenceDiagramToolDescription
     * @generated
     */
    EClass getSequenceDiagramToolDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool
     * <em>Ordered Element Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Ordered Element Creation Tool</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool
     * @generated
     */
    EClass getOrderedElementCreationTool();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getStartingEndPredecessor
     * <em>Starting End Predecessor</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Starting End Predecessor</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getStartingEndPredecessor()
     * @see #getOrderedElementCreationTool()
     * @generated
     */
    EReference getOrderedElementCreationTool_StartingEndPredecessor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getFinishingEndPredecessor
     * <em>Finishing End Predecessor</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Finishing End Predecessor</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getFinishingEndPredecessor()
     * @see #getOrderedElementCreationTool()
     * @generated
     */
    EReference getOrderedElementCreationTool_FinishingEndPredecessor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool
     * <em>Covering Element Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Covering Element Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool
     * @generated
     */
    EClass getCoveringElementCreationTool();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool#getCoveredLifelines
     * <em>Covered Lifelines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Covered Lifelines</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.CoveringElementCreationTool#getCoveredLifelines()
     * @see #getCoveringElementCreationTool()
     * @generated
     */
    EReference getCoveringElementCreationTool_CoveredLifelines();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool
     * <em>Instance Role Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Instance Role Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool
     * @generated
     */
    EClass getInstanceRoleCreationTool();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool#getPredecessor
     * <em>Predecessor</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Predecessor</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool#getPredecessor()
     * @see #getInstanceRoleCreationTool()
     * @generated
     */
    EReference getInstanceRoleCreationTool_Predecessor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.LifelineCreationTool
     * <em>Lifeline Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Lifeline Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.LifelineCreationTool
     * @generated
     */
    EClass getLifelineCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool
     * <em>Message Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Message Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.MessageCreationTool
     * @generated
     */
    EClass getMessageCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool
     * <em>Execution Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Execution Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ExecutionCreationTool
     * @generated
     */
    EClass getExecutionCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool
     * <em>State Creation Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>State Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.StateCreationTool
     * @generated
     */
    EClass getStateCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool
     * <em>Reorder Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Reorder Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool
     * @generated
     */
    EClass getReorderTool();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getMappings()
     * @see #getReorderTool()
     * @generated
     */
    EReference getReorderTool_Mappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorBefore
     * <em>Starting End Predecessor Before</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Starting End Predecessor Before</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorBefore()
     * @see #getReorderTool()
     * @generated
     */
    EReference getReorderTool_StartingEndPredecessorBefore();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorAfter
     * <em>Starting End Predecessor After</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Starting End Predecessor After</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorAfter()
     * @see #getReorderTool()
     * @generated
     */
    EReference getReorderTool_StartingEndPredecessorAfter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorBefore
     * <em>Finishing End Predecessor Before</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Finishing End Predecessor Before</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorBefore()
     * @see #getReorderTool()
     * @generated
     */
    EReference getReorderTool_FinishingEndPredecessorBefore();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorAfter
     * <em>Finishing End Predecessor After</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Finishing End Predecessor After</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorAfter()
     * @see #getReorderTool()
     * @generated
     */
    EReference getReorderTool_FinishingEndPredecessorAfter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getOnEventMovedOperation
     * <em>On Event Moved Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>On Event Moved Operation</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getOnEventMovedOperation()
     * @see #getReorderTool()
     * @generated
     */
    EReference getReorderTool_OnEventMovedOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool
     * <em>Instance Role Reorder Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Instance Role Reorder Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool
     * @generated
     */
    EClass getInstanceRoleReorderTool();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getMappings()
     * @see #getInstanceRoleReorderTool()
     * @generated
     */
    EReference getInstanceRoleReorderTool_Mappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorBefore
     * <em>Predecessor Before</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Predecessor Before</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorBefore()
     * @see #getInstanceRoleReorderTool()
     * @generated
     */
    EReference getInstanceRoleReorderTool_PredecessorBefore();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorAfter
     * <em>Predecessor After</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Predecessor After</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorAfter()
     * @see #getInstanceRoleReorderTool()
     * @generated
     */
    EReference getInstanceRoleReorderTool_PredecessorAfter();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getInstanceRoleMoved
     * <em>Instance Role Moved</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Instance Role Moved</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getInstanceRoleMoved()
     * @see #getInstanceRoleReorderTool()
     * @generated
     */
    EReference getInstanceRoleReorderTool_InstanceRoleMoved();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool
     * <em>Observation Point Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Observation Point Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ObservationPointCreationTool
     * @generated
     */
    EClass getObservationPointCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool
     * <em>Interaction Use Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Interaction Use Creation Tool</em>
     *         '.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.InteractionUseCreationTool
     * @generated
     */
    EClass getInteractionUseCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool
     * <em>Combined Fragment Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Combined Fragment Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.CombinedFragmentCreationTool
     * @generated
     */
    EClass getCombinedFragmentCreationTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool
     * <em>Operand Creation Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Operand Creation Tool</em>'.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.OperandCreationTool
     * @generated
     */
    EClass getOperandCreationTool();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ToolFactory getToolFactory();

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
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.SequenceDiagramToolDescriptionImpl
         * <em>Sequence Diagram Tool Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.SequenceDiagramToolDescriptionImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getSequenceDiagramToolDescription()
         * @generated
         */
        EClass SEQUENCE_DIAGRAM_TOOL_DESCRIPTION = ToolPackage.eINSTANCE.getSequenceDiagramToolDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.OrderedElementCreationToolImpl
         * <em>Ordered Element Creation Tool</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.OrderedElementCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getOrderedElementCreationTool()
         * @generated
         */
        EClass ORDERED_ELEMENT_CREATION_TOOL = ToolPackage.eINSTANCE.getOrderedElementCreationTool();

        /**
         * The meta object literal for the '
         * <em><b>Starting End Predecessor</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ORDERED_ELEMENT_CREATION_TOOL__STARTING_END_PREDECESSOR = ToolPackage.eINSTANCE.getOrderedElementCreationTool_StartingEndPredecessor();

        /**
         * The meta object literal for the '
         * <em><b>Finishing End Predecessor</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ORDERED_ELEMENT_CREATION_TOOL__FINISHING_END_PREDECESSOR = ToolPackage.eINSTANCE.getOrderedElementCreationTool_FinishingEndPredecessor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CoveringElementCreationToolImpl
         * <em>Covering Element Creation Tool</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.CoveringElementCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getCoveringElementCreationTool()
         * @generated
         */
        EClass COVERING_ELEMENT_CREATION_TOOL = ToolPackage.eINSTANCE.getCoveringElementCreationTool();

        /**
         * The meta object literal for the '<em><b>Covered Lifelines</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference COVERING_ELEMENT_CREATION_TOOL__COVERED_LIFELINES = ToolPackage.eINSTANCE.getCoveringElementCreationTool_CoveredLifelines();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleCreationToolImpl
         * <em>Instance Role Creation Tool</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getInstanceRoleCreationTool()
         * @generated
         */
        EClass INSTANCE_ROLE_CREATION_TOOL = ToolPackage.eINSTANCE.getInstanceRoleCreationTool();

        /**
         * The meta object literal for the '<em><b>Predecessor</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference INSTANCE_ROLE_CREATION_TOOL__PREDECESSOR = ToolPackage.eINSTANCE.getInstanceRoleCreationTool_Predecessor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.LifelineCreationToolImpl
         * <em>Lifeline Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.LifelineCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getLifelineCreationTool()
         * @generated
         */
        EClass LIFELINE_CREATION_TOOL = ToolPackage.eINSTANCE.getLifelineCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl
         * <em>Message Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.MessageCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getMessageCreationTool()
         * @generated
         */
        EClass MESSAGE_CREATION_TOOL = ToolPackage.eINSTANCE.getMessageCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ExecutionCreationToolImpl
         * <em>Execution Creation Tool</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ExecutionCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getExecutionCreationTool()
         * @generated
         */
        EClass EXECUTION_CREATION_TOOL = ToolPackage.eINSTANCE.getExecutionCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.StateCreationToolImpl
         * <em>State Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.StateCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getStateCreationTool()
         * @generated
         */
        EClass STATE_CREATION_TOOL = ToolPackage.eINSTANCE.getStateCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl
         * <em>Reorder Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ReorderToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getReorderTool()
         * @generated
         */
        EClass REORDER_TOOL = ToolPackage.eINSTANCE.getReorderTool();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REORDER_TOOL__MAPPINGS = ToolPackage.eINSTANCE.getReorderTool_Mappings();

        /**
         * The meta object literal for the '
         * <em><b>Starting End Predecessor Before</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REORDER_TOOL__STARTING_END_PREDECESSOR_BEFORE = ToolPackage.eINSTANCE.getReorderTool_StartingEndPredecessorBefore();

        /**
         * The meta object literal for the '
         * <em><b>Starting End Predecessor After</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REORDER_TOOL__STARTING_END_PREDECESSOR_AFTER = ToolPackage.eINSTANCE.getReorderTool_StartingEndPredecessorAfter();

        /**
         * The meta object literal for the '
         * <em><b>Finishing End Predecessor Before</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REORDER_TOOL__FINISHING_END_PREDECESSOR_BEFORE = ToolPackage.eINSTANCE.getReorderTool_FinishingEndPredecessorBefore();

        /**
         * The meta object literal for the '
         * <em><b>Finishing End Predecessor After</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REORDER_TOOL__FINISHING_END_PREDECESSOR_AFTER = ToolPackage.eINSTANCE.getReorderTool_FinishingEndPredecessorAfter();

        /**
         * The meta object literal for the '
         * <em><b>On Event Moved Operation</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REORDER_TOOL__ON_EVENT_MOVED_OPERATION = ToolPackage.eINSTANCE.getReorderTool_OnEventMovedOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl
         * <em>Instance Role Reorder Tool</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.InstanceRoleReorderToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getInstanceRoleReorderTool()
         * @generated
         */
        EClass INSTANCE_ROLE_REORDER_TOOL = ToolPackage.eINSTANCE.getInstanceRoleReorderTool();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INSTANCE_ROLE_REORDER_TOOL__MAPPINGS = ToolPackage.eINSTANCE.getInstanceRoleReorderTool_Mappings();

        /**
         * The meta object literal for the '<em><b>Predecessor Before</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_BEFORE = ToolPackage.eINSTANCE.getInstanceRoleReorderTool_PredecessorBefore();

        /**
         * The meta object literal for the '<em><b>Predecessor After</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference INSTANCE_ROLE_REORDER_TOOL__PREDECESSOR_AFTER = ToolPackage.eINSTANCE.getInstanceRoleReorderTool_PredecessorAfter();

        /**
         * The meta object literal for the '<em><b>Instance Role Moved</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference INSTANCE_ROLE_REORDER_TOOL__INSTANCE_ROLE_MOVED = ToolPackage.eINSTANCE.getInstanceRoleReorderTool_InstanceRoleMoved();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.ObservationPointCreationToolImpl
         * <em>Observation Point Creation Tool</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ObservationPointCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getObservationPointCreationTool()
         * @generated
         */
        EClass OBSERVATION_POINT_CREATION_TOOL = ToolPackage.eINSTANCE.getObservationPointCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.InteractionUseCreationToolImpl
         * <em>Interaction Use Creation Tool</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.InteractionUseCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getInteractionUseCreationTool()
         * @generated
         */
        EClass INTERACTION_USE_CREATION_TOOL = ToolPackage.eINSTANCE.getInteractionUseCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl
         * <em>Combined Fragment Creation Tool</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.CombinedFragmentCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getCombinedFragmentCreationTool()
         * @generated
         */
        EClass COMBINED_FRAGMENT_CREATION_TOOL = ToolPackage.eINSTANCE.getCombinedFragmentCreationTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.sequence.description.tool.impl.OperandCreationToolImpl
         * <em>Operand Creation Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.OperandCreationToolImpl
         * @see org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolPackageImpl#getOperandCreationTool()
         * @generated
         */
        EClass OPERAND_CREATION_TOOL = ToolPackage.eINSTANCE.getOperandCreationTool();

    }

} // ToolPackage
