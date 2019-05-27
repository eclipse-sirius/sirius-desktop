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
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.sirius.diagram.description.DescriptionFactory
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
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/description/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "description"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DescriptionPackage eINSTANCE = org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.DragAndDropTargetDescriptionImpl
     * <em>Drag And Drop Target Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.DragAndDropTargetDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDragAndDropTargetDescription()
     * @generated
     */
    int DRAG_AND_DROP_TARGET_DESCRIPTION = 31;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS = 0;

    /**
     * The number of structural features of the '<em>Drag And Drop Target Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl <em>Diagram
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramDescription()
     * @generated
     */
    int DIAGRAM_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DROP_DESCRIPTIONS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DOCUMENTATION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__END_USER_DOCUMENTATION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__NAME = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__LABEL = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__INITIALISATION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__METAMODEL = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__PASTE_DESCRIPTIONS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__FILTERS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__VALIDATION_SET = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__CONCERNS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_TOOLS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>Default Concern</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DEFAULT_CONCERN = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ROOT_EXPRESSION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 19;

    /**
     * The feature id for the '<em><b>Init</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__INIT = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 20;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__LAYOUT = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 21;

    /**
     * The feature id for the '<em><b>Diagram Initialisation</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 22;

    /**
     * The feature id for the '<em><b>Default Layer</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DEFAULT_LAYER = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 23;

    /**
     * The feature id for the '<em><b>Additional Layers</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 24;

    /**
     * The feature id for the '<em><b>All Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_LAYERS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 25;

    /**
     * The feature id for the '<em><b>All Activated Tools</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 26;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__NODE_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 27;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__EDGE_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 28;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 29;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 30;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__REUSED_MAPPINGS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 31;

    /**
     * The feature id for the '<em><b>Tool Section</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__TOOL_SECTION = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 32;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__REUSED_TOOLS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 33;

    /**
     * The feature id for the '<em><b>Enable Popup Bars</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 34;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__BACKGROUND_COLOR = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 35;

    /**
     * The number of structural features of the '<em>Diagram Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.DRAG_AND_DROP_TARGET_DESCRIPTION_FEATURE_COUNT + 36;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.DiagramImportDescriptionImpl
     * <em>Diagram Import Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.DiagramImportDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramImportDescription()
     * @generated
     */
    int DIAGRAM_IMPORT_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__TITLE_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__INITIALISATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__METAMODEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__SHOW_ON_STARTUP = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DROP_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__PASTE_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_EDGE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_NODE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_CONTAINER_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__VALIDATION_SET = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__CONCERNS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_TOOLS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DOMAIN_CLASS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__PRECONDITION_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Default Concern</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DEFAULT_CONCERN = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ROOT_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Init</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__INIT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__LAYOUT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Diagram Initialisation</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DIAGRAM_INITIALISATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The feature id for the '<em><b>Default Layer</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__DEFAULT_LAYER = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 16;

    /**
     * The feature id for the '<em><b>Additional Layers</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ADDITIONAL_LAYERS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 17;

    /**
     * The feature id for the '<em><b>All Layers</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_LAYERS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 18;

    /**
     * The feature id for the '<em><b>All Activated Tools</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ALL_ACTIVATED_TOOLS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 19;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__NODE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 20;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__EDGE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 21;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__EDGE_MAPPING_IMPORTS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 22;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__CONTAINER_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 23;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__REUSED_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 24;

    /**
     * The feature id for the '<em><b>Tool Section</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__TOOL_SECTION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 25;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__REUSED_TOOLS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 26;

    /**
     * The feature id for the '<em><b>Enable Popup Bars</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__ENABLE_POPUP_BARS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 27;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__BACKGROUND_COLOR = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 28;

    /**
     * The feature id for the '<em><b>Imported Diagram</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 29;

    /**
     * The number of structural features of the '<em>Diagram Import Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_IMPORT_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_IMPORT_DESCRIPTION_FEATURE_COUNT + 30;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl
     * <em>Diagram Extension Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramExtensionDescription()
     * @generated
     */
    int DIAGRAM_EXTENSION_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Viewpoint URI</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__VIEWPOINT_URI = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__VIEWPOINT_URI;

    /**
     * The feature id for the '<em><b>Representation Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__REPRESENTATION_NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__REPRESENTATION_NAME;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__METAMODEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Layers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__LAYERS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Concerns</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Diagram Extension Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_EXTENSION_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_EXTENSION_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl
     * <em>Diagram Element Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramElementMapping()
     * @generated
     */
    int DIAGRAM_ELEMENT_MAPPING = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__LABEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Diagram Element Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl
     * <em>Abstract Node Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getAbstractNodeMapping()
     * @generated
     */
    int ABSTRACT_NODE_MAPPING = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__NAME = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__LABEL = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DOCUMENTATION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__DOMAIN_CLASS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Abstract Node Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_NODE_MAPPING_FEATURE_COUNT = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.NodeMappingImpl <em>Node
     * Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.NodeMappingImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getNodeMapping()
     * @generated
     */
    int NODE_MAPPING = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__NAME = DescriptionPackage.ABSTRACT_NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__LABEL = DescriptionPackage.ABSTRACT_NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.ABSTRACT_NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.ABSTRACT_NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.ABSTRACT_NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.ABSTRACT_NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.ABSTRACT_NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DOCUMENTATION = DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DOMAIN_CLASS = DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__BORDERED_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__DROP_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__STYLE = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Node Mapping</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_FEATURE_COUNT = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl <em>Container
     * Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getContainerMapping()
     * @generated
     */
    int CONTAINER_MAPPING = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__NAME = DescriptionPackage.ABSTRACT_NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__LABEL = DescriptionPackage.ABSTRACT_NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.ABSTRACT_NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.ABSTRACT_NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.ABSTRACT_NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.ABSTRACT_NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__CREATE_ELEMENTS = DescriptionPackage.ABSTRACT_NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.ABSTRACT_NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.ABSTRACT_NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.ABSTRACT_NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DOCUMENTATION = DescriptionPackage.ABSTRACT_NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DOMAIN_CLASS = DescriptionPackage.ABSTRACT_NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__BORDERED_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__DROP_DESCRIPTIONS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SUB_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__ALL_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__REUSED_NODE_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__STYLE = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING__CHILDREN_PRESENTATION = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Container Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_FEATURE_COUNT = DescriptionPackage.ABSTRACT_NODE_MAPPING_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl <em>Node
     * Mapping Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getNodeMappingImport()
     * @generated
     */
    int NODE_MAPPING_IMPORT = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__NAME = DescriptionPackage.NODE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__LABEL = DescriptionPackage.NODE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DETAIL_DESCRIPTIONS = DescriptionPackage.NODE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__NAVIGATION_DESCRIPTIONS = DescriptionPackage.NODE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__PASTE_DESCRIPTIONS = DescriptionPackage.NODE_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__PRECONDITION_EXPRESSION = DescriptionPackage.NODE_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DELETION_DESCRIPTION = DescriptionPackage.NODE_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__LABEL_DIRECT_EDIT = DescriptionPackage.NODE_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.NODE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__CREATE_ELEMENTS = DescriptionPackage.NODE_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__SEMANTIC_ELEMENTS = DescriptionPackage.NODE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.NODE_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__SYNCHRONIZATION_LOCK = DescriptionPackage.NODE_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DOCUMENTATION = DescriptionPackage.NODE_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DOMAIN_CLASS = DescriptionPackage.NODE_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__BORDERED_NODE_MAPPINGS = DescriptionPackage.NODE_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__DROP_DESCRIPTIONS = DescriptionPackage.NODE_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__STYLE = DescriptionPackage.NODE_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__CONDITIONNAL_STYLES = DescriptionPackage.NODE_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Hide Sub Mappings</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT__IMPORTED_MAPPING = DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Node Mapping Import</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_MAPPING_IMPORT_FEATURE_COUNT = DescriptionPackage.NODE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImportImpl
     * <em>Container Mapping Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.ContainerMappingImportImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getContainerMappingImport()
     * @generated
     */
    int CONTAINER_MAPPING_IMPORT = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__NAME = DescriptionPackage.CONTAINER_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__LABEL = DescriptionPackage.CONTAINER_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DETAIL_DESCRIPTIONS = DescriptionPackage.CONTAINER_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__NAVIGATION_DESCRIPTIONS = DescriptionPackage.CONTAINER_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__PASTE_DESCRIPTIONS = DescriptionPackage.CONTAINER_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__PRECONDITION_EXPRESSION = DescriptionPackage.CONTAINER_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DELETION_DESCRIPTION = DescriptionPackage.CONTAINER_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__LABEL_DIRECT_EDIT = DescriptionPackage.CONTAINER_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.CONTAINER_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__CREATE_ELEMENTS = DescriptionPackage.CONTAINER_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SEMANTIC_ELEMENTS = DescriptionPackage.CONTAINER_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.CONTAINER_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SYNCHRONIZATION_LOCK = DescriptionPackage.CONTAINER_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DOCUMENTATION = DescriptionPackage.CONTAINER_MAPPING__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DOMAIN_CLASS = DescriptionPackage.CONTAINER_MAPPING__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Bordered Node Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__BORDERED_NODE_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Bordered Node Mappings</b></em>' reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__REUSED_BORDERED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Drop Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__DROP_DESCRIPTIONS = DescriptionPackage.CONTAINER_MAPPING__DROP_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Sub Node Mappings</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SUB_NODE_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__SUB_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__ALL_NODE_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__ALL_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__REUSED_NODE_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__REUSED_NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Sub Container Mappings</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__SUB_CONTAINER_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__REUSED_CONTAINER_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__ALL_CONTAINER_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__STYLE = DescriptionPackage.CONTAINER_MAPPING__STYLE;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__CONDITIONNAL_STYLES = DescriptionPackage.CONTAINER_MAPPING__CONDITIONNAL_STYLES;

    /**
     * The feature id for the '<em><b>Children Presentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__CHILDREN_PRESENTATION = DescriptionPackage.CONTAINER_MAPPING__CHILDREN_PRESENTATION;

    /**
     * The feature id for the '<em><b>Hide Sub Mappings</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__HIDE_SUB_MAPPINGS = DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING = DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Container Mapping Import</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_MAPPING_IMPORT_FEATURE_COUNT = DescriptionPackage.CONTAINER_MAPPING_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl <em>Edge
     * Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEdgeMapping()
     * @generated
     */
    int EDGE_MAPPING = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__NAME = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__LABEL = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Paste Descriptions</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PASTE_DESCRIPTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PASTE_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Deletion Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Label Direct Edit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Create Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__CREATE_ELEMENTS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Double Click Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Synchronization Lock</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DOCUMENTATION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Mapping</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SOURCE_MAPPING = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Mapping</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__TARGET_MAPPING = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Finder Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__TARGET_FINDER_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Finder Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__SOURCE_FINDER_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__STYLE = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__TARGET_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__DOMAIN_CLASS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Use Domain Element</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__USE_DOMAIN_ELEMENT = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Reconnections</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__RECONNECTIONS = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Path Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PATH_EXPRESSION = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Path Node Mapping</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING__PATH_NODE_MAPPING = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the '<em>Edge Mapping</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_FEATURE_COUNT = DescriptionPackage.DIAGRAM_ELEMENT_MAPPING_FEATURE_COUNT + 13;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.IEdgeMapping <em>IEdge Mapping</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.IEdgeMapping
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getIEdgeMapping()
     * @generated
     */
    int IEDGE_MAPPING = 10;

    /**
     * The number of structural features of the '<em>IEdge Mapping</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IEDGE_MAPPING_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl <em>Edge
     * Mapping Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEdgeMappingImport()
     * @generated
     */
    int EDGE_MAPPING_IMPORT = 11;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__LABEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Imported Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__IMPORTED_MAPPING = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditionnal Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Inherits Ancestor Filters</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Edge Mapping Import</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_MAPPING_IMPORT_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.impl.ConditionalNodeStyleDescriptionImpl <em>Conditional Node
     * Style Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.ConditionalNodeStyleDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getConditionalNodeStyleDescription()
     * @generated
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION = 12;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION__STYLE = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Conditional Node Style Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.impl.ConditionalEdgeStyleDescriptionImpl <em>Conditional Edge
     * Style Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.ConditionalEdgeStyleDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getConditionalEdgeStyleDescription()
     * @generated
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION = 13;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION__STYLE = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Conditional Edge Style Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.impl.ConditionalContainerStyleDescriptionImpl <em>Conditional
     * Container Style Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.ConditionalContainerStyleDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getConditionalContainerStyleDescription()
     * @generated
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION = 14;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__PREDICATE_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the ' <em>Conditional Container Style Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.CONDITIONAL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.Layout <em>Layout</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.Layout
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayout()
     * @generated
     */
    int LAYOUT = 15;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The number of structural features of the '<em>Layout</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.OrderedTreeLayoutImpl <em>Ordered
     * Tree Layout</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.OrderedTreeLayoutImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getOrderedTreeLayout()
     * @generated
     */
    int ORDERED_TREE_LAYOUT = 16;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT__DOCUMENTATION = DescriptionPackage.LAYOUT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION = DescriptionPackage.LAYOUT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Node Mapping</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT__NODE_MAPPING = DescriptionPackage.LAYOUT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Ordered Tree Layout</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ORDERED_TREE_LAYOUT_FEATURE_COUNT = DescriptionPackage.LAYOUT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.CompositeLayoutImpl <em>Composite
     * Layout</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.CompositeLayoutImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getCompositeLayout()
     * @generated
     */
    int COMPOSITE_LAYOUT = 17;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT__DOCUMENTATION = DescriptionPackage.LAYOUT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Padding</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT__PADDING = DescriptionPackage.LAYOUT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Direction</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT__DIRECTION = DescriptionPackage.LAYOUT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Composite Layout</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COMPOSITE_LAYOUT_FEATURE_COUNT = DescriptionPackage.LAYOUT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl
     * <em>Custom Layout Configuration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getCustomLayoutConfiguration()
     * @generated
     */
    int CUSTOM_LAYOUT_CONFIGURATION = 18;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_LAYOUT_CONFIGURATION__DOCUMENTATION = DescriptionPackage.LAYOUT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_LAYOUT_CONFIGURATION__ID = DescriptionPackage.LAYOUT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_LAYOUT_CONFIGURATION__LABEL = DescriptionPackage.LAYOUT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION = DescriptionPackage.LAYOUT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Layout Options</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS = DescriptionPackage.LAYOUT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Custom Layout Configuration</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_LAYOUT_CONFIGURATION_FEATURE_COUNT = DescriptionPackage.LAYOUT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl <em>Layout
     * Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayoutOption()
     * @generated
     */
    int LAYOUT_OPTION = 19;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__LABEL = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION__TARGETS = 3;

    /**
     * The number of structural features of the '<em>Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_OPTION_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.BooleanLayoutOptionImpl
     * <em>Boolean Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.BooleanLayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getBooleanLayoutOption()
     * @generated
     */
    int BOOLEAN_LAYOUT_OPTION = 20;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOLEAN_LAYOUT_OPTION__ID = DescriptionPackage.LAYOUT_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOLEAN_LAYOUT_OPTION__LABEL = DescriptionPackage.LAYOUT_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOLEAN_LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.LAYOUT_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOLEAN_LAYOUT_OPTION__TARGETS = DescriptionPackage.LAYOUT_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOLEAN_LAYOUT_OPTION__VALUE = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Boolean Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BOOLEAN_LAYOUT_OPTION_FEATURE_COUNT = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.StringLayoutOptionImpl <em>String
     * Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.StringLayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getStringLayoutOption()
     * @generated
     */
    int STRING_LAYOUT_OPTION = 21;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_LAYOUT_OPTION__ID = DescriptionPackage.LAYOUT_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_LAYOUT_OPTION__LABEL = DescriptionPackage.LAYOUT_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.LAYOUT_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_LAYOUT_OPTION__TARGETS = DescriptionPackage.LAYOUT_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_LAYOUT_OPTION__VALUE = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>String Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STRING_LAYOUT_OPTION_FEATURE_COUNT = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.IntegerLayoutOptionImpl
     * <em>Integer Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.IntegerLayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getIntegerLayoutOption()
     * @generated
     */
    int INTEGER_LAYOUT_OPTION = 22;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_LAYOUT_OPTION__ID = DescriptionPackage.LAYOUT_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_LAYOUT_OPTION__LABEL = DescriptionPackage.LAYOUT_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.LAYOUT_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_LAYOUT_OPTION__TARGETS = DescriptionPackage.LAYOUT_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_LAYOUT_OPTION__VALUE = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Integer Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTEGER_LAYOUT_OPTION_FEATURE_COUNT = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.DoubleLayoutOptionImpl <em>Double
     * Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.DoubleLayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDoubleLayoutOption()
     * @generated
     */
    int DOUBLE_LAYOUT_OPTION = 23;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_LAYOUT_OPTION__ID = DescriptionPackage.LAYOUT_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_LAYOUT_OPTION__LABEL = DescriptionPackage.LAYOUT_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.LAYOUT_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_LAYOUT_OPTION__TARGETS = DescriptionPackage.LAYOUT_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_LAYOUT_OPTION__VALUE = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Double Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_LAYOUT_OPTION_FEATURE_COUNT = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.EnumOptionImpl <em>Enum
     * Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.EnumOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumOption()
     * @generated
     */
    int ENUM_OPTION = 26;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_OPTION__ID = DescriptionPackage.LAYOUT_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_OPTION__LABEL = DescriptionPackage.LAYOUT_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_OPTION__DESCRIPTION = DescriptionPackage.LAYOUT_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_OPTION__TARGETS = DescriptionPackage.LAYOUT_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Choices</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_OPTION__CHOICES = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enum Option</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ENUM_OPTION_FEATURE_COUNT = DescriptionPackage.LAYOUT_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.EnumLayoutOptionImpl <em>Enum
     * Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.EnumLayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumLayoutOption()
     * @generated
     */
    int ENUM_LAYOUT_OPTION = 24;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION__ID = DescriptionPackage.ENUM_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION__LABEL = DescriptionPackage.ENUM_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.ENUM_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION__TARGETS = DescriptionPackage.ENUM_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Choices</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION__CHOICES = DescriptionPackage.ENUM_OPTION__CHOICES;

    /**
     * The feature id for the '<em><b>Value</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION__VALUE = DescriptionPackage.ENUM_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enum Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_OPTION_FEATURE_COUNT = DescriptionPackage.ENUM_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.EnumSetLayoutOptionImpl <em>Enum
     * Set Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.EnumSetLayoutOptionImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumSetLayoutOption()
     * @generated
     */
    int ENUM_SET_LAYOUT_OPTION = 25;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION__ID = DescriptionPackage.ENUM_OPTION__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION__LABEL = DescriptionPackage.ENUM_OPTION__LABEL;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.ENUM_OPTION__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Targets</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION__TARGETS = DescriptionPackage.ENUM_OPTION__TARGETS;

    /**
     * The feature id for the '<em><b>Choices</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION__CHOICES = DescriptionPackage.ENUM_OPTION__CHOICES;

    /**
     * The feature id for the '<em><b>Values</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION__VALUES = DescriptionPackage.ENUM_OPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enum Set Layout Option</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_SET_LAYOUT_OPTION_FEATURE_COUNT = DescriptionPackage.ENUM_OPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.EnumLayoutValueImpl <em>Enum
     * Layout Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.EnumLayoutValueImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumLayoutValue()
     * @generated
     */
    int ENUM_LAYOUT_VALUE = 27;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_VALUE__NAME = 0;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_VALUE__DESCRIPTION = 1;

    /**
     * The number of structural features of the '<em>Enum Layout Value</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENUM_LAYOUT_VALUE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.MappingBasedDecorationImpl
     * <em>Mapping Based Decoration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.MappingBasedDecorationImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getMappingBasedDecoration()
     * @generated
     */
    int MAPPING_BASED_DECORATION = 28;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Position</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__POSITION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__POSITION;

    /**
     * The feature id for the '<em><b>Distribution Direction</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__DISTRIBUTION_DIRECTION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__DISTRIBUTION_DIRECTION;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__PRECONDITION_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__IMAGE_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__IMAGE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__TOOLTIP_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION__MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Mapping Based Decoration</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_DECORATION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DECORATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.LayerImpl <em>Layer</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.LayerImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayer()
     * @generated
     */
    int LAYER = 29;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__END_USER_DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__LABEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__NODE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__EDGE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__EDGE_MAPPING_IMPORTS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__CONTAINER_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__REUSED_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__ALL_TOOLS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Tool Sections</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__TOOL_SECTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LAYER__REUSED_TOOLS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Decoration Descriptions Set</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__DECORATION_DESCRIPTIONS_SET = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__ICON = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__ALL_EDGE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Customization</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER__CUSTOMIZATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '<em>Layer</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYER_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.impl.AdditionalLayerImpl <em>Additional
     * Layer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.impl.AdditionalLayerImpl
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getAdditionalLayer()
     * @generated
     */
    int ADDITIONAL_LAYER = 30;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__DOCUMENTATION = DescriptionPackage.LAYER__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__END_USER_DOCUMENTATION = DescriptionPackage.LAYER__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__NAME = DescriptionPackage.LAYER__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__LABEL = DescriptionPackage.LAYER__LABEL;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__NODE_MAPPINGS = DescriptionPackage.LAYER__NODE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__EDGE_MAPPINGS = DescriptionPackage.LAYER__EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Edge Mapping Imports</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__EDGE_MAPPING_IMPORTS = DescriptionPackage.LAYER__EDGE_MAPPING_IMPORTS;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__CONTAINER_MAPPINGS = DescriptionPackage.LAYER__CONTAINER_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__REUSED_MAPPINGS = DescriptionPackage.LAYER__REUSED_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ALL_TOOLS = DescriptionPackage.LAYER__ALL_TOOLS;

    /**
     * The feature id for the '<em><b>Tool Sections</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__TOOL_SECTIONS = DescriptionPackage.LAYER__TOOL_SECTIONS;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__REUSED_TOOLS = DescriptionPackage.LAYER__REUSED_TOOLS;

    /**
     * The feature id for the '<em><b>Decoration Descriptions Set</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__DECORATION_DESCRIPTIONS_SET = DescriptionPackage.LAYER__DECORATION_DESCRIPTIONS_SET;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ICON = DescriptionPackage.LAYER__ICON;

    /**
     * The feature id for the '<em><b>All Edge Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ALL_EDGE_MAPPINGS = DescriptionPackage.LAYER__ALL_EDGE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Customization</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__CUSTOMIZATION = DescriptionPackage.LAYER__CUSTOMIZATION;

    /**
     * The feature id for the '<em><b>Active By Default</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT = DescriptionPackage.LAYER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER__OPTIONAL = DescriptionPackage.LAYER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Additional Layer</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADDITIONAL_LAYER_FEATURE_COUNT = DescriptionPackage.LAYER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.FoldingStyle <em>Folding Style</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.FoldingStyle
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getFoldingStyle()
     * @generated
     */
    int FOLDING_STYLE = 32;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.LayoutDirection <em>Layout
     * Direction</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.LayoutDirection
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayoutDirection()
     * @generated
     */
    int LAYOUT_DIRECTION = 33;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.CenteringStyle <em>Centering
     * Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.CenteringStyle
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getCenteringStyle()
     * @generated
     */
    int CENTERING_STYLE = 34;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.LayoutOptionTarget <em>Layout Option
     * Target</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.LayoutOptionTarget
     * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayoutOptionTarget()
     * @generated
     */
    int LAYOUT_OPTION_TARGET = 35;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.DiagramDescription <em>Diagram
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription
     * @generated
     */
    EClass getDiagramDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getFilters <em>Filters</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Filters</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getFilters()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Filters();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllEdgeMappings <em>All Edge
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Edge Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAllEdgeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllEdgeMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllNodeMappings <em>All Node
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAllNodeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllNodeMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllContainerMappings <em>All Container
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAllContainerMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllContainerMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getValidationSet <em>Validation Set</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Validation Set</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getValidationSet()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getConcerns <em>Concerns</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Concerns</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getConcerns()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Concerns();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllTools <em>All Tools</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAllTools()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllTools();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDomainClass <em>Domain Class</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getDomainClass()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_DomainClass();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getPreconditionExpression <em>Precondition
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getPreconditionExpression()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_PreconditionExpression();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultConcern <em>Default Concern</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Default Concern</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultConcern()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_DefaultConcern();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getRootExpression <em>Root Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Root Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getRootExpression()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_RootExpression();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getInit <em>Init</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Init</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getInit()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Init();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getLayout <em>Layout</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getLayout()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_Layout();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getDiagramInitialisation <em>Diagram
     * Initialisation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Diagram Initialisation</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getDiagramInitialisation()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_DiagramInitialisation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultLayer <em>Default Layer</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Default Layer</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getDefaultLayer()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_DefaultLayer();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getAdditionalLayers <em>Additional
     * Layers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Additional Layers</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAdditionalLayers()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AdditionalLayers();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllLayers <em>All Layers</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Layers</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAllLayers()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllLayers();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getAllActivatedTools <em>All Activated
     * Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Activated Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getAllActivatedTools()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_AllActivatedTools();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getNodeMappings <em>Node Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getNodeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_NodeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getEdgeMappings <em>Edge Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Edge Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getEdgeMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_EdgeMappings();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getEdgeMappingImports <em>Edge Mapping
     * Imports</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Edge Mapping Imports</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getEdgeMappingImports()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_EdgeMappingImports();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getContainerMappings <em>Container
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getContainerMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ContainerMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getReusedMappings <em>Reused Mappings</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getReusedMappings()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ReusedMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getToolSection <em>Tool Section</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Tool Section</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getToolSection()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ToolSection();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramDescription#getReusedTools <em>Reused Tools</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getReusedTools()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_ReusedTools();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#isEnablePopupBars <em>Enable Popup Bars</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Enable Popup Bars</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#isEnablePopupBars()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_EnablePopupBars();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramDescription#getBackgroundColor <em>Background Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramDescription#getBackgroundColor()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_BackgroundColor();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.DiagramImportDescription
     * <em>Diagram Import Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Import Description</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramImportDescription
     * @generated
     */
    EClass getDiagramImportDescription();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramImportDescription#getImportedDiagram <em>Imported
     * Diagram</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Imported Diagram</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramImportDescription#getImportedDiagram()
     * @see #getDiagramImportDescription()
     * @generated
     */
    EReference getDiagramImportDescription_ImportedDiagram();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription
     * <em>Diagram Extension Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Extension Description</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramExtensionDescription
     * @generated
     */
    EClass getDiagramExtensionDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getLayers <em>Layers</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Layers</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getLayers()
     * @see #getDiagramExtensionDescription()
     * @generated
     */
    EReference getDiagramExtensionDescription_Layers();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getValidationSet <em>Validation
     * Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Validation Set</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getValidationSet()
     * @see #getDiagramExtensionDescription()
     * @generated
     */
    EReference getDiagramExtensionDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getConcerns <em>Concerns</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Concerns</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramExtensionDescription#getConcerns()
     * @see #getDiagramExtensionDescription()
     * @generated
     */
    EReference getDiagramExtensionDescription_Concerns();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping
     * <em>Diagram Element Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Element Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping
     * @generated
     */
    EClass getDiagramElementMapping();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getPreconditionExpression <em>Precondition
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getPreconditionExpression()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_PreconditionExpression();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDeletionDescription <em>Deletion
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Deletion Description</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getDeletionDescription()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EReference getDiagramElementMapping_DeletionDescription();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getLabelDirectEdit <em>Label Direct
     * Edit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Label Direct Edit</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getLabelDirectEdit()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EReference getDiagramElementMapping_LabelDirectEdit();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticCandidatesExpression <em>Semantic
     * Candidates Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticCandidatesExpression()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_SemanticCandidatesExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#isCreateElements <em>Create Elements</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Create Elements</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#isCreateElements()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_CreateElements();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticElements <em>Semantic
     * Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getSemanticElements()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_SemanticElements();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#getDoubleClickDescription <em>Double Click
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Double Click Description</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#getDoubleClickDescription()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EReference getDiagramElementMapping_DoubleClickDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DiagramElementMapping#isSynchronizationLock <em>Synchronization
     * Lock</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Synchronization Lock</em>'.
     * @see org.eclipse.sirius.diagram.description.DiagramElementMapping#isSynchronizationLock()
     * @see #getDiagramElementMapping()
     * @generated
     */
    EAttribute getDiagramElementMapping_SynchronizationLock();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.AbstractNodeMapping <em>Abstract
     * Node Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Node Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.AbstractNodeMapping
     * @generated
     */
    EClass getAbstractNodeMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getDomainClass <em>Domain Class</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.diagram.description.AbstractNodeMapping#getDomainClass()
     * @see #getAbstractNodeMapping()
     * @generated
     */
    EAttribute getAbstractNodeMapping_DomainClass();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getBorderedNodeMappings <em>Bordered Node
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Bordered Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.AbstractNodeMapping#getBorderedNodeMappings()
     * @see #getAbstractNodeMapping()
     * @generated
     */
    EReference getAbstractNodeMapping_BorderedNodeMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.AbstractNodeMapping#getReusedBorderedNodeMappings <em>Reused
     * Bordered Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Bordered Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.AbstractNodeMapping#getReusedBorderedNodeMappings()
     * @see #getAbstractNodeMapping()
     * @generated
     */
    EReference getAbstractNodeMapping_ReusedBorderedNodeMappings();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.NodeMapping <em>Node
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.NodeMapping
     * @generated
     */
    EClass getNodeMapping();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.NodeMapping#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.description.NodeMapping#getStyle()
     * @see #getNodeMapping()
     * @generated
     */
    EReference getNodeMapping_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.NodeMapping#getConditionnalStyles <em>Conditionnal Styles</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.diagram.description.NodeMapping#getConditionnalStyles()
     * @see #getNodeMapping()
     * @generated
     */
    EReference getNodeMapping_ConditionnalStyles();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.ContainerMapping <em>Container
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping
     * @generated
     */
    EClass getContainerMapping();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getSubNodeMappings <em>Sub Node Mappings</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sub Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getSubNodeMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_SubNodeMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getAllNodeMappings <em>All Node Mappings</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getAllNodeMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_AllNodeMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getReusedNodeMappings <em>Reused Node
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getReusedNodeMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_ReusedNodeMappings();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getSubContainerMappings <em>Sub Container
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sub Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getSubContainerMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_SubContainerMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getReusedContainerMappings <em>Reused Container
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getReusedContainerMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_ReusedContainerMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getAllContainerMappings <em>All Container
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getAllContainerMappings()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_AllContainerMappings();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getStyle()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getConditionnalStyles <em>Conditionnal
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getConditionnalStyles()
     * @see #getContainerMapping()
     * @generated
     */
    EReference getContainerMapping_ConditionnalStyles();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.ContainerMapping#getChildrenPresentation <em>Children
     * Presentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Children Presentation</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMapping#getChildrenPresentation()
     * @see #getContainerMapping()
     * @generated
     */
    EAttribute getContainerMapping_ChildrenPresentation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.NodeMappingImport <em>Node
     * Mapping Import</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Mapping Import</em>'.
     * @see org.eclipse.sirius.diagram.description.NodeMappingImport
     * @generated
     */
    EClass getNodeMappingImport();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.NodeMappingImport#getImportedMapping <em>Imported Mapping</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Imported Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.NodeMappingImport#getImportedMapping()
     * @see #getNodeMappingImport()
     * @generated
     */
    EReference getNodeMappingImport_ImportedMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.ContainerMappingImport
     * <em>Container Mapping Import</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Mapping Import</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMappingImport
     * @generated
     */
    EClass getContainerMappingImport();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.ContainerMappingImport#getImportedMapping <em>Imported
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Imported Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.ContainerMappingImport#getImportedMapping()
     * @see #getContainerMappingImport()
     * @generated
     */
    EReference getContainerMappingImport_ImportedMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.EdgeMapping <em>Edge
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping
     * @generated
     */
    EClass getEdgeMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getSourceMapping <em>Source Mapping</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Source Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getSourceMapping()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_SourceMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetMapping <em>Target Mapping</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Target Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getTargetMapping()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_TargetMapping();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetFinderExpression <em>Target Finder
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getTargetFinderExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_TargetFinderExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.EdgeMapping#getSourceFinderExpression <em>Source Finder
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Source Finder Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getSourceFinderExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_SourceFinderExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.EdgeMapping#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getStyle()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.EdgeMapping#getConditionnalStyles <em>Conditionnal Styles</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getConditionnalStyles()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_ConditionnalStyles();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getTargetExpression <em>Target Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getTargetExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_TargetExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getDomainClass <em>Domain Class</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getDomainClass()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#isUseDomainElement <em>Use Domain Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Use Domain Element</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#isUseDomainElement()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_UseDomainElement();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getReconnections <em>Reconnections</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reconnections</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getReconnections()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_Reconnections();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getPathExpression <em>Path Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Path Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getPathExpression()
     * @see #getEdgeMapping()
     * @generated
     */
    EAttribute getEdgeMapping_PathExpression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.EdgeMapping#getPathNodeMapping <em>Path Node Mapping</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list ' <em>Path Node Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMapping#getPathNodeMapping()
     * @see #getEdgeMapping()
     * @generated
     */
    EReference getEdgeMapping_PathNodeMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.IEdgeMapping <em>IEdge
     * Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>IEdge Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.IEdgeMapping
     * @generated
     */
    EClass getIEdgeMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.EdgeMappingImport <em>Edge
     * Mapping Import</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Mapping Import</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMappingImport
     * @generated
     */
    EClass getEdgeMappingImport();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.EdgeMappingImport#getImportedMapping <em>Imported Mapping</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Imported Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMappingImport#getImportedMapping()
     * @see #getEdgeMappingImport()
     * @generated
     */
    EReference getEdgeMappingImport_ImportedMapping();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.EdgeMappingImport#getConditionnalStyles <em>Conditionnal
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditionnal Styles</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMappingImport#getConditionnalStyles()
     * @see #getEdgeMappingImport()
     * @generated
     */
    EReference getEdgeMappingImport_ConditionnalStyles();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.EdgeMappingImport#isInheritsAncestorFilters <em>Inherits Ancestor
     * Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Inherits Ancestor Filters</em>'.
     * @see org.eclipse.sirius.diagram.description.EdgeMappingImport#isInheritsAncestorFilters()
     * @see #getEdgeMappingImport()
     * @generated
     */
    EAttribute getEdgeMappingImport_InheritsAncestorFilters();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription
     * <em>Conditional Node Style Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Conditional Node Style Description</em>'.
     * @see org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription
     * @generated
     */
    EClass getConditionalNodeStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription#getStyle <em>Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription#getStyle()
     * @see #getConditionalNodeStyleDescription()
     * @generated
     */
    EReference getConditionalNodeStyleDescription_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription
     * <em>Conditional Edge Style Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Conditional Edge Style Description</em>'.
     * @see org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription
     * @generated
     */
    EClass getConditionalEdgeStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription#getStyle <em>Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.description.ConditionalEdgeStyleDescription#getStyle()
     * @see #getConditionalEdgeStyleDescription()
     * @generated
     */
    EReference getConditionalEdgeStyleDescription_Style();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription <em>Conditional Container
     * Style Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Conditional Container Style Description</em>'.
     * @see org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription
     * @generated
     */
    EClass getConditionalContainerStyleDescription();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription#getStyle <em>Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription#getStyle()
     * @see #getConditionalContainerStyleDescription()
     * @generated
     */
    EReference getConditionalContainerStyleDescription_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.Layout <em>Layout</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Layout</em>'.
     * @see org.eclipse.sirius.diagram.description.Layout
     * @generated
     */
    EClass getLayout();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.OrderedTreeLayout <em>Ordered
     * Tree Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ordered Tree Layout</em>'.
     * @see org.eclipse.sirius.diagram.description.OrderedTreeLayout
     * @generated
     */
    EClass getOrderedTreeLayout();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.OrderedTreeLayout#getChildrenExpression <em>Children
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Children Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.OrderedTreeLayout#getChildrenExpression()
     * @see #getOrderedTreeLayout()
     * @generated
     */
    EAttribute getOrderedTreeLayout_ChildrenExpression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.OrderedTreeLayout#getNodeMapping <em>Node Mapping</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Node Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.OrderedTreeLayout#getNodeMapping()
     * @see #getOrderedTreeLayout()
     * @generated
     */
    EReference getOrderedTreeLayout_NodeMapping();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.CompositeLayout <em>Composite
     * Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Composite Layout</em>'.
     * @see org.eclipse.sirius.diagram.description.CompositeLayout
     * @generated
     */
    EClass getCompositeLayout();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.CompositeLayout#getPadding <em>Padding</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Padding</em>'.
     * @see org.eclipse.sirius.diagram.description.CompositeLayout#getPadding()
     * @see #getCompositeLayout()
     * @generated
     */
    EAttribute getCompositeLayout_Padding();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.CompositeLayout#getDirection <em>Direction</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Direction</em>'.
     * @see org.eclipse.sirius.diagram.description.CompositeLayout#getDirection()
     * @see #getCompositeLayout()
     * @generated
     */
    EAttribute getCompositeLayout_Direction();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration
     * <em>Custom Layout Configuration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Layout Configuration</em>'.
     * @see org.eclipse.sirius.diagram.description.CustomLayoutConfiguration
     * @generated
     */
    EClass getCustomLayoutConfiguration();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getId <em>Id</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getId()
     * @see #getCustomLayoutConfiguration()
     * @generated
     */
    EAttribute getCustomLayoutConfiguration_Id();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLabel <em>Label</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLabel()
     * @see #getCustomLayoutConfiguration()
     * @generated
     */
    EAttribute getCustomLayoutConfiguration_Label();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getDescription()
     * @see #getCustomLayoutConfiguration()
     * @generated
     */
    EAttribute getCustomLayoutConfiguration_Description();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLayoutOptions <em>Layout
     * Options</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Layout Options</em>'.
     * @see org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLayoutOptions()
     * @see #getCustomLayoutConfiguration()
     * @generated
     */
    EReference getCustomLayoutConfiguration_LayoutOptions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.LayoutOption <em>Layout
     * Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutOption
     * @generated
     */
    EClass getLayoutOption();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.description.LayoutOption#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutOption#getId()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Id();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.description.LayoutOption#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutOption#getLabel()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Label();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.LayoutOption#getDescription <em>Description</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutOption#getDescription()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Description();

    /**
     * Returns the meta object for the attribute list
     * '{@link org.eclipse.sirius.diagram.description.LayoutOption#getTargets <em>Targets</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Targets</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutOption#getTargets()
     * @see #getLayoutOption()
     * @generated
     */
    EAttribute getLayoutOption_Targets();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.BooleanLayoutOption <em>Boolean
     * Layout Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Boolean Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.BooleanLayoutOption
     * @generated
     */
    EClass getBooleanLayoutOption();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.BooleanLayoutOption#isValue <em>Value</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.description.BooleanLayoutOption#isValue()
     * @see #getBooleanLayoutOption()
     * @generated
     */
    EAttribute getBooleanLayoutOption_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.StringLayoutOption <em>String
     * Layout Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>String Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.StringLayoutOption
     * @generated
     */
    EClass getStringLayoutOption();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.StringLayoutOption#getValue <em>Value</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.description.StringLayoutOption#getValue()
     * @see #getStringLayoutOption()
     * @generated
     */
    EAttribute getStringLayoutOption_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.IntegerLayoutOption <em>Integer
     * Layout Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Integer Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.IntegerLayoutOption
     * @generated
     */
    EClass getIntegerLayoutOption();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.IntegerLayoutOption#getValue <em>Value</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.description.IntegerLayoutOption#getValue()
     * @see #getIntegerLayoutOption()
     * @generated
     */
    EAttribute getIntegerLayoutOption_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.DoubleLayoutOption <em>Double
     * Layout Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Double Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.DoubleLayoutOption
     * @generated
     */
    EClass getDoubleLayoutOption();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.DoubleLayoutOption#getValue <em>Value</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.description.DoubleLayoutOption#getValue()
     * @see #getDoubleLayoutOption()
     * @generated
     */
    EAttribute getDoubleLayoutOption_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.EnumLayoutOption <em>Enum Layout
     * Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Enum Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutOption
     * @generated
     */
    EClass getEnumLayoutOption();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.EnumLayoutOption#getValue <em>Value</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutOption#getValue()
     * @see #getEnumLayoutOption()
     * @generated
     */
    EReference getEnumLayoutOption_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.EnumSetLayoutOption <em>Enum Set
     * Layout Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Enum Set Layout Option</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumSetLayoutOption
     * @generated
     */
    EClass getEnumSetLayoutOption();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.EnumSetLayoutOption#getValues <em>Values</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Values</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumSetLayoutOption#getValues()
     * @see #getEnumSetLayoutOption()
     * @generated
     */
    EReference getEnumSetLayoutOption_Values();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.EnumOption <em>Enum
     * Option</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Enum Option</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumOption
     * @generated
     */
    EClass getEnumOption();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.EnumOption#getChoices <em>Choices</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Choices</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumOption#getChoices()
     * @see #getEnumOption()
     * @generated
     */
    EReference getEnumOption_Choices();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.EnumLayoutValue <em>Enum Layout
     * Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Enum Layout Value</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutValue
     * @generated
     */
    EClass getEnumLayoutValue();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.description.EnumLayoutValue#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutValue#getName()
     * @see #getEnumLayoutValue()
     * @generated
     */
    EAttribute getEnumLayoutValue_Name();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.EnumLayoutValue#getDescription <em>Description</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.eclipse.sirius.diagram.description.EnumLayoutValue#getDescription()
     * @see #getEnumLayoutValue()
     * @generated
     */
    EAttribute getEnumLayoutValue_Description();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.MappingBasedDecoration
     * <em>Mapping Based Decoration</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Mapping Based Decoration</em>'.
     * @see org.eclipse.sirius.diagram.description.MappingBasedDecoration
     * @generated
     */
    EClass getMappingBasedDecoration();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.MappingBasedDecoration#getMappings <em>Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.MappingBasedDecoration#getMappings()
     * @see #getMappingBasedDecoration()
     * @generated
     */
    EReference getMappingBasedDecoration_Mappings();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.Layer <em>Layer</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Layer</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer
     * @generated
     */
    EClass getLayer();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.Layer#getNodeMappings <em>Node Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getNodeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_NodeMappings();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.Layer#getEdgeMappings <em>Edge Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Edge Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getEdgeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_EdgeMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.Layer#getEdgeMappingImports <em>Edge Mapping Imports</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Edge Mapping Imports</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getEdgeMappingImports()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_EdgeMappingImports();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.Layer#getContainerMappings <em>Container Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list ' <em>Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getContainerMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ContainerMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.Layer#getReusedMappings <em>Reused Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getReusedMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ReusedMappings();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.diagram.description.Layer#getAllTools
     * <em>All Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>All Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getAllTools()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_AllTools();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.Layer#getToolSections <em>Tool Sections</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Tool Sections</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getToolSections()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ToolSections();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.Layer#getReusedTools <em>Reused Tools</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getReusedTools()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_ReusedTools();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.Layer#getDecorationDescriptionsSet <em>Decoration Descriptions
     * Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Decoration Descriptions Set</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getDecorationDescriptionsSet()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_DecorationDescriptionsSet();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.description.Layer#getIcon
     * <em>Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getIcon()
     * @see #getLayer()
     * @generated
     */
    EAttribute getLayer_Icon();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.Layer#getAllEdgeMappings <em>All Edge Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list ' <em>All Edge Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getAllEdgeMappings()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_AllEdgeMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.Layer#getCustomization <em>Customization</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Customization</em>'.
     * @see org.eclipse.sirius.diagram.description.Layer#getCustomization()
     * @see #getLayer()
     * @generated
     */
    EReference getLayer_Customization();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.AdditionalLayer <em>Additional
     * Layer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Additional Layer</em>'.
     * @see org.eclipse.sirius.diagram.description.AdditionalLayer
     * @generated
     */
    EClass getAdditionalLayer();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.AdditionalLayer#isActiveByDefault <em>Active By Default</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Active By Default</em>'.
     * @see org.eclipse.sirius.diagram.description.AdditionalLayer#isActiveByDefault()
     * @see #getAdditionalLayer()
     * @generated
     */
    EAttribute getAdditionalLayer_ActiveByDefault();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.AdditionalLayer#isOptional <em>Optional</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Optional</em>'.
     * @see org.eclipse.sirius.diagram.description.AdditionalLayer#isOptional()
     * @see #getAdditionalLayer()
     * @generated
     */
    EAttribute getAdditionalLayer_Optional();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.DragAndDropTargetDescription
     * <em>Drag And Drop Target Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Drag And Drop Target Description</em>'.
     * @see org.eclipse.sirius.diagram.description.DragAndDropTargetDescription
     * @generated
     */
    EClass getDragAndDropTargetDescription();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.DragAndDropTargetDescription#getDropDescriptions <em>Drop
     * Descriptions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Drop Descriptions</em>'.
     * @see org.eclipse.sirius.diagram.description.DragAndDropTargetDescription#getDropDescriptions()
     * @see #getDragAndDropTargetDescription()
     * @generated
     */
    EReference getDragAndDropTargetDescription_DropDescriptions();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.description.FoldingStyle <em>Folding
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.diagram.description.FoldingStyle
     * @generated
     */
    EEnum getFoldingStyle();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.description.LayoutDirection <em>Layout
     * Direction</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Layout Direction</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutDirection
     * @generated
     */
    EEnum getLayoutDirection();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.description.CenteringStyle <em>Centering
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Centering Style</em>'.
     * @see org.eclipse.sirius.diagram.description.CenteringStyle
     * @generated
     */
    EEnum getCenteringStyle();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.description.LayoutOptionTarget <em>Layout
     * Option Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Layout Option Target</em>'.
     * @see org.eclipse.sirius.diagram.description.LayoutOptionTarget
     * @generated
     */
    EEnum getLayoutOptionTarget();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DescriptionFactory getDescriptionFactory();

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
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl
         * <em>Diagram Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.DiagramDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramDescription()
         * @generated
         */
        EClass DIAGRAM_DESCRIPTION = DescriptionPackage.eINSTANCE.getDiagramDescription();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__FILTERS = DescriptionPackage.eINSTANCE.getDiagramDescription_Filters();

        /**
         * The meta object literal for the '<em><b>All Edge Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_EDGE_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_AllEdgeMappings();

        /**
         * The meta object literal for the '<em><b>All Node Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_AllNodeMappings();

        /**
         * The meta object literal for the ' <em><b>All Container Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_CONTAINER_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_AllContainerMappings();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__VALIDATION_SET = DescriptionPackage.eINSTANCE.getDiagramDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Concerns</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__CONCERNS = DescriptionPackage.eINSTANCE.getDiagramDescription_Concerns();

        /**
         * The meta object literal for the '<em><b>All Tools</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_TOOLS = DescriptionPackage.eINSTANCE.getDiagramDescription_AllTools();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getDiagramDescription_DomainClass();

        /**
         * The meta object literal for the ' <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.eINSTANCE.getDiagramDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Default Concern</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__DEFAULT_CONCERN = DescriptionPackage.eINSTANCE.getDiagramDescription_DefaultConcern();

        /**
         * The meta object literal for the '<em><b>Root Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__ROOT_EXPRESSION = DescriptionPackage.eINSTANCE.getDiagramDescription_RootExpression();

        /**
         * The meta object literal for the '<em><b>Init</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__INIT = DescriptionPackage.eINSTANCE.getDiagramDescription_Init();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__LAYOUT = DescriptionPackage.eINSTANCE.getDiagramDescription_Layout();

        /**
         * The meta object literal for the '<em><b>Diagram Initialisation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__DIAGRAM_INITIALISATION = DescriptionPackage.eINSTANCE.getDiagramDescription_DiagramInitialisation();

        /**
         * The meta object literal for the '<em><b>Default Layer</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__DEFAULT_LAYER = DescriptionPackage.eINSTANCE.getDiagramDescription_DefaultLayer();

        /**
         * The meta object literal for the '<em><b>Additional Layers</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ADDITIONAL_LAYERS = DescriptionPackage.eINSTANCE.getDiagramDescription_AdditionalLayers();

        /**
         * The meta object literal for the '<em><b>All Layers</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_LAYERS = DescriptionPackage.eINSTANCE.getDiagramDescription_AllLayers();

        /**
         * The meta object literal for the '<em><b>All Activated Tools</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__ALL_ACTIVATED_TOOLS = DescriptionPackage.eINSTANCE.getDiagramDescription_AllActivatedTools();

        /**
         * The meta object literal for the '<em><b>Node Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_NodeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__EDGE_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_EdgeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mapping Imports</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__EDGE_MAPPING_IMPORTS = DescriptionPackage.eINSTANCE.getDiagramDescription_EdgeMappingImports();

        /**
         * The meta object literal for the '<em><b>Container Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__CONTAINER_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_ContainerMappings();

        /**
         * The meta object literal for the '<em><b>Reused Mappings</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__REUSED_MAPPINGS = DescriptionPackage.eINSTANCE.getDiagramDescription_ReusedMappings();

        /**
         * The meta object literal for the '<em><b>Tool Section</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__TOOL_SECTION = DescriptionPackage.eINSTANCE.getDiagramDescription_ToolSection();

        /**
         * The meta object literal for the '<em><b>Reused Tools</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__REUSED_TOOLS = DescriptionPackage.eINSTANCE.getDiagramDescription_ReusedTools();

        /**
         * The meta object literal for the '<em><b>Enable Popup Bars</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__ENABLE_POPUP_BARS = DescriptionPackage.eINSTANCE.getDiagramDescription_EnablePopupBars();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__BACKGROUND_COLOR = DescriptionPackage.eINSTANCE.getDiagramDescription_BackgroundColor();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.DiagramImportDescriptionImpl <em>Diagram Import
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.DiagramImportDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramImportDescription()
         * @generated
         */
        EClass DIAGRAM_IMPORT_DESCRIPTION = DescriptionPackage.eINSTANCE.getDiagramImportDescription();

        /**
         * The meta object literal for the '<em><b>Imported Diagram</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_IMPORT_DESCRIPTION__IMPORTED_DIAGRAM = DescriptionPackage.eINSTANCE.getDiagramImportDescription_ImportedDiagram();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl <em>Diagram Extension
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.DiagramExtensionDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramExtensionDescription()
         * @generated
         */
        EClass DIAGRAM_EXTENSION_DESCRIPTION = DescriptionPackage.eINSTANCE.getDiagramExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Layers</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_EXTENSION_DESCRIPTION__LAYERS = DescriptionPackage.eINSTANCE.getDiagramExtensionDescription_Layers();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_EXTENSION_DESCRIPTION__VALIDATION_SET = DescriptionPackage.eINSTANCE.getDiagramExtensionDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Concerns</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_EXTENSION_DESCRIPTION__CONCERNS = DescriptionPackage.eINSTANCE.getDiagramExtensionDescription_Concerns();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl
         * <em>Diagram Element Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.DiagramElementMappingImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDiagramElementMapping()
         * @generated
         */
        EClass DIAGRAM_ELEMENT_MAPPING = DescriptionPackage.eINSTANCE.getDiagramElementMapping();

        /**
         * The meta object literal for the ' <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.eINSTANCE.getDiagramElementMapping_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Deletion Description</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING__DELETION_DESCRIPTION = DescriptionPackage.eINSTANCE.getDiagramElementMapping_DeletionDescription();

        /**
         * The meta object literal for the '<em><b>Label Direct Edit</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING__LABEL_DIRECT_EDIT = DescriptionPackage.eINSTANCE.getDiagramElementMapping_LabelDirectEdit();

        /**
         * The meta object literal for the '<em><b>Semantic Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Create Elements</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__CREATE_ELEMENTS = DescriptionPackage.eINSTANCE.getDiagramElementMapping_CreateElements();

        /**
         * The meta object literal for the '<em><b>Semantic Elements</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.eINSTANCE.getDiagramElementMapping_SemanticElements();

        /**
         * The meta object literal for the ' <em><b>Double Click Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_ELEMENT_MAPPING__DOUBLE_CLICK_DESCRIPTION = DescriptionPackage.eINSTANCE.getDiagramElementMapping_DoubleClickDescription();

        /**
         * The meta object literal for the '<em><b>Synchronization Lock</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_MAPPING__SYNCHRONIZATION_LOCK = DescriptionPackage.eINSTANCE.getDiagramElementMapping_SynchronizationLock();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl
         * <em>Abstract Node Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.AbstractNodeMappingImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getAbstractNodeMapping()
         * @generated
         */
        EClass ABSTRACT_NODE_MAPPING = DescriptionPackage.eINSTANCE.getAbstractNodeMapping();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_NODE_MAPPING__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getAbstractNodeMapping_DomainClass();

        /**
         * The meta object literal for the '<em><b>Bordered Node Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_NODE_MAPPING__BORDERED_NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings();

        /**
         * The meta object literal for the '<em><b>Reused Bordered Node Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_NODE_MAPPING__REUSED_BORDERED_NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getAbstractNodeMapping_ReusedBorderedNodeMappings();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.NodeMappingImpl <em>Node
         * Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.NodeMappingImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getNodeMapping()
         * @generated
         */
        EClass NODE_MAPPING = DescriptionPackage.eINSTANCE.getNodeMapping();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_MAPPING__STYLE = DescriptionPackage.eINSTANCE.getNodeMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.eINSTANCE.getNodeMapping_ConditionnalStyles();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl
         * <em>Container Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.ContainerMappingImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getContainerMapping()
         * @generated
         */
        EClass CONTAINER_MAPPING = DescriptionPackage.eINSTANCE.getContainerMapping();

        /**
         * The meta object literal for the '<em><b>Sub Node Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__SUB_NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getContainerMapping_SubNodeMappings();

        /**
         * The meta object literal for the '<em><b>All Node Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__ALL_NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getContainerMapping_AllNodeMappings();

        /**
         * The meta object literal for the '<em><b>Reused Node Mappings</b></em> ' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__REUSED_NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getContainerMapping_ReusedNodeMappings();

        /**
         * The meta object literal for the '<em><b>Sub Container Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__SUB_CONTAINER_MAPPINGS = DescriptionPackage.eINSTANCE.getContainerMapping_SubContainerMappings();

        /**
         * The meta object literal for the '<em><b>Reused Container Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__REUSED_CONTAINER_MAPPINGS = DescriptionPackage.eINSTANCE.getContainerMapping_ReusedContainerMappings();

        /**
         * The meta object literal for the ' <em><b>All Container Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__ALL_CONTAINER_MAPPINGS = DescriptionPackage.eINSTANCE.getContainerMapping_AllContainerMappings();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__STYLE = DescriptionPackage.eINSTANCE.getContainerMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.eINSTANCE.getContainerMapping_ConditionnalStyles();

        /**
         * The meta object literal for the ' <em><b>Children Presentation</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTAINER_MAPPING__CHILDREN_PRESENTATION = DescriptionPackage.eINSTANCE.getContainerMapping_ChildrenPresentation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl
         * <em>Node Mapping Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.NodeMappingImportImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getNodeMappingImport()
         * @generated
         */
        EClass NODE_MAPPING_IMPORT = DescriptionPackage.eINSTANCE.getNodeMappingImport();

        /**
         * The meta object literal for the '<em><b>Imported Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_MAPPING_IMPORT__IMPORTED_MAPPING = DescriptionPackage.eINSTANCE.getNodeMappingImport_ImportedMapping();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.ContainerMappingImportImpl <em>Container Mapping
         * Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.ContainerMappingImportImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getContainerMappingImport()
         * @generated
         */
        EClass CONTAINER_MAPPING_IMPORT = DescriptionPackage.eINSTANCE.getContainerMappingImport();

        /**
         * The meta object literal for the '<em><b>Imported Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_MAPPING_IMPORT__IMPORTED_MAPPING = DescriptionPackage.eINSTANCE.getContainerMappingImport_ImportedMapping();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl <em>Edge
         * Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.EdgeMappingImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEdgeMapping()
         * @generated
         */
        EClass EDGE_MAPPING = DescriptionPackage.eINSTANCE.getEdgeMapping();

        /**
         * The meta object literal for the '<em><b>Source Mapping</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING__SOURCE_MAPPING = DescriptionPackage.eINSTANCE.getEdgeMapping_SourceMapping();

        /**
         * The meta object literal for the '<em><b>Target Mapping</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING__TARGET_MAPPING = DescriptionPackage.eINSTANCE.getEdgeMapping_TargetMapping();

        /**
         * The meta object literal for the ' <em><b>Target Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING__TARGET_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getEdgeMapping_TargetFinderExpression();

        /**
         * The meta object literal for the ' <em><b>Source Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING__SOURCE_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getEdgeMapping_SourceFinderExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING__STYLE = DescriptionPackage.eINSTANCE.getEdgeMapping_Style();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING__CONDITIONNAL_STYLES = DescriptionPackage.eINSTANCE.getEdgeMapping_ConditionnalStyles();

        /**
         * The meta object literal for the '<em><b>Target Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING__TARGET_EXPRESSION = DescriptionPackage.eINSTANCE.getEdgeMapping_TargetExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getEdgeMapping_DomainClass();

        /**
         * The meta object literal for the '<em><b>Use Domain Element</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING__USE_DOMAIN_ELEMENT = DescriptionPackage.eINSTANCE.getEdgeMapping_UseDomainElement();

        /**
         * The meta object literal for the '<em><b>Reconnections</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING__RECONNECTIONS = DescriptionPackage.eINSTANCE.getEdgeMapping_Reconnections();

        /**
         * The meta object literal for the '<em><b>Path Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING__PATH_EXPRESSION = DescriptionPackage.eINSTANCE.getEdgeMapping_PathExpression();

        /**
         * The meta object literal for the '<em><b>Path Node Mapping</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING__PATH_NODE_MAPPING = DescriptionPackage.eINSTANCE.getEdgeMapping_PathNodeMapping();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.IEdgeMapping <em>IEdge
         * Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.IEdgeMapping
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getIEdgeMapping()
         * @generated
         */
        EClass IEDGE_MAPPING = DescriptionPackage.eINSTANCE.getIEdgeMapping();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl
         * <em>Edge Mapping Import</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.EdgeMappingImportImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEdgeMappingImport()
         * @generated
         */
        EClass EDGE_MAPPING_IMPORT = DescriptionPackage.eINSTANCE.getEdgeMappingImport();

        /**
         * The meta object literal for the '<em><b>Imported Mapping</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING_IMPORT__IMPORTED_MAPPING = DescriptionPackage.eINSTANCE.getEdgeMappingImport_ImportedMapping();

        /**
         * The meta object literal for the '<em><b>Conditionnal Styles</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_MAPPING_IMPORT__CONDITIONNAL_STYLES = DescriptionPackage.eINSTANCE.getEdgeMappingImport_ConditionnalStyles();

        /**
         * The meta object literal for the ' <em><b>Inherits Ancestor Filters</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_MAPPING_IMPORT__INHERITS_ANCESTOR_FILTERS = DescriptionPackage.eINSTANCE.getEdgeMappingImport_InheritsAncestorFilters();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.ConditionalNodeStyleDescriptionImpl <em>Conditional Node
         * Style Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.ConditionalNodeStyleDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getConditionalNodeStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_NODE_STYLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getConditionalNodeStyleDescription();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONDITIONAL_NODE_STYLE_DESCRIPTION__STYLE = DescriptionPackage.eINSTANCE.getConditionalNodeStyleDescription_Style();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.ConditionalEdgeStyleDescriptionImpl <em>Conditional Edge
         * Style Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.ConditionalEdgeStyleDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getConditionalEdgeStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_EDGE_STYLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getConditionalEdgeStyleDescription();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONDITIONAL_EDGE_STYLE_DESCRIPTION__STYLE = DescriptionPackage.eINSTANCE.getConditionalEdgeStyleDescription_Style();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.ConditionalContainerStyleDescriptionImpl <em>Conditional
         * Container Style Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.ConditionalContainerStyleDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getConditionalContainerStyleDescription()
         * @generated
         */
        EClass CONDITIONAL_CONTAINER_STYLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getConditionalContainerStyleDescription();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONDITIONAL_CONTAINER_STYLE_DESCRIPTION__STYLE = DescriptionPackage.eINSTANCE.getConditionalContainerStyleDescription_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.Layout <em>Layout</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.Layout
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayout()
         * @generated
         */
        EClass LAYOUT = DescriptionPackage.eINSTANCE.getLayout();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.OrderedTreeLayoutImpl
         * <em>Ordered Tree Layout</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.OrderedTreeLayoutImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getOrderedTreeLayout()
         * @generated
         */
        EClass ORDERED_TREE_LAYOUT = DescriptionPackage.eINSTANCE.getOrderedTreeLayout();

        /**
         * The meta object literal for the '<em><b>Children Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ORDERED_TREE_LAYOUT__CHILDREN_EXPRESSION = DescriptionPackage.eINSTANCE.getOrderedTreeLayout_ChildrenExpression();

        /**
         * The meta object literal for the '<em><b>Node Mapping</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ORDERED_TREE_LAYOUT__NODE_MAPPING = DescriptionPackage.eINSTANCE.getOrderedTreeLayout_NodeMapping();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.CompositeLayoutImpl
         * <em>Composite Layout</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.CompositeLayoutImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getCompositeLayout()
         * @generated
         */
        EClass COMPOSITE_LAYOUT = DescriptionPackage.eINSTANCE.getCompositeLayout();

        /**
         * The meta object literal for the '<em><b>Padding</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute COMPOSITE_LAYOUT__PADDING = DescriptionPackage.eINSTANCE.getCompositeLayout_Padding();

        /**
         * The meta object literal for the '<em><b>Direction</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute COMPOSITE_LAYOUT__DIRECTION = DescriptionPackage.eINSTANCE.getCompositeLayout_Direction();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl <em>Custom Layout
         * Configuration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.CustomLayoutConfigurationImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getCustomLayoutConfiguration()
         * @generated
         */
        EClass CUSTOM_LAYOUT_CONFIGURATION = DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_LAYOUT_CONFIGURATION__ID = DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_LAYOUT_CONFIGURATION__LABEL = DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_LAYOUT_CONFIGURATION__DESCRIPTION = DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_Description();

        /**
         * The meta object literal for the '<em><b>Layout Options</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_LAYOUT_CONFIGURATION__LAYOUT_OPTIONS = DescriptionPackage.eINSTANCE.getCustomLayoutConfiguration_LayoutOptions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl
         * <em>Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.LayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayoutOption()
         * @generated
         */
        EClass LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getLayoutOption();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT_OPTION__ID = DescriptionPackage.eINSTANCE.getLayoutOption_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT_OPTION__LABEL = DescriptionPackage.eINSTANCE.getLayoutOption_Label();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT_OPTION__DESCRIPTION = DescriptionPackage.eINSTANCE.getLayoutOption_Description();

        /**
         * The meta object literal for the '<em><b>Targets</b></em>' attribute list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYOUT_OPTION__TARGETS = DescriptionPackage.eINSTANCE.getLayoutOption_Targets();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.BooleanLayoutOptionImpl
         * <em>Boolean Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.BooleanLayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getBooleanLayoutOption()
         * @generated
         */
        EClass BOOLEAN_LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getBooleanLayoutOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute BOOLEAN_LAYOUT_OPTION__VALUE = DescriptionPackage.eINSTANCE.getBooleanLayoutOption_Value();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.StringLayoutOptionImpl
         * <em>String Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.StringLayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getStringLayoutOption()
         * @generated
         */
        EClass STRING_LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getStringLayoutOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute STRING_LAYOUT_OPTION__VALUE = DescriptionPackage.eINSTANCE.getStringLayoutOption_Value();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.IntegerLayoutOptionImpl
         * <em>Integer Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.IntegerLayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getIntegerLayoutOption()
         * @generated
         */
        EClass INTEGER_LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getIntegerLayoutOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute INTEGER_LAYOUT_OPTION__VALUE = DescriptionPackage.eINSTANCE.getIntegerLayoutOption_Value();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.DoubleLayoutOptionImpl
         * <em>Double Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.DoubleLayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDoubleLayoutOption()
         * @generated
         */
        EClass DOUBLE_LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getDoubleLayoutOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DOUBLE_LAYOUT_OPTION__VALUE = DescriptionPackage.eINSTANCE.getDoubleLayoutOption_Value();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.EnumLayoutOptionImpl
         * <em>Enum Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.EnumLayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumLayoutOption()
         * @generated
         */
        EClass ENUM_LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getEnumLayoutOption();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ENUM_LAYOUT_OPTION__VALUE = DescriptionPackage.eINSTANCE.getEnumLayoutOption_Value();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.EnumSetLayoutOptionImpl
         * <em>Enum Set Layout Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.EnumSetLayoutOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumSetLayoutOption()
         * @generated
         */
        EClass ENUM_SET_LAYOUT_OPTION = DescriptionPackage.eINSTANCE.getEnumSetLayoutOption();

        /**
         * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ENUM_SET_LAYOUT_OPTION__VALUES = DescriptionPackage.eINSTANCE.getEnumSetLayoutOption_Values();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.EnumOptionImpl <em>Enum
         * Option</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.EnumOptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumOption()
         * @generated
         */
        EClass ENUM_OPTION = DescriptionPackage.eINSTANCE.getEnumOption();

        /**
         * The meta object literal for the '<em><b>Choices</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ENUM_OPTION__CHOICES = DescriptionPackage.eINSTANCE.getEnumOption_Choices();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.EnumLayoutValueImpl
         * <em>Enum Layout Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.EnumLayoutValueImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getEnumLayoutValue()
         * @generated
         */
        EClass ENUM_LAYOUT_VALUE = DescriptionPackage.eINSTANCE.getEnumLayoutValue();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ENUM_LAYOUT_VALUE__NAME = DescriptionPackage.eINSTANCE.getEnumLayoutValue_Name();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ENUM_LAYOUT_VALUE__DESCRIPTION = DescriptionPackage.eINSTANCE.getEnumLayoutValue_Description();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.MappingBasedDecorationImpl <em>Mapping Based
         * Decoration</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.MappingBasedDecorationImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getMappingBasedDecoration()
         * @generated
         */
        EClass MAPPING_BASED_DECORATION = DescriptionPackage.eINSTANCE.getMappingBasedDecoration();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference MAPPING_BASED_DECORATION__MAPPINGS = DescriptionPackage.eINSTANCE.getMappingBasedDecoration_Mappings();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.LayerImpl
         * <em>Layer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.LayerImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayer()
         * @generated
         */
        EClass LAYER = DescriptionPackage.eINSTANCE.getLayer();

        /**
         * The meta object literal for the '<em><b>Node Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__NODE_MAPPINGS = DescriptionPackage.eINSTANCE.getLayer_NodeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__EDGE_MAPPINGS = DescriptionPackage.eINSTANCE.getLayer_EdgeMappings();

        /**
         * The meta object literal for the '<em><b>Edge Mapping Imports</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__EDGE_MAPPING_IMPORTS = DescriptionPackage.eINSTANCE.getLayer_EdgeMappingImports();

        /**
         * The meta object literal for the '<em><b>Container Mappings</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__CONTAINER_MAPPINGS = DescriptionPackage.eINSTANCE.getLayer_ContainerMappings();

        /**
         * The meta object literal for the '<em><b>Reused Mappings</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__REUSED_MAPPINGS = DescriptionPackage.eINSTANCE.getLayer_ReusedMappings();

        /**
         * The meta object literal for the '<em><b>All Tools</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__ALL_TOOLS = DescriptionPackage.eINSTANCE.getLayer_AllTools();

        /**
         * The meta object literal for the '<em><b>Tool Sections</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__TOOL_SECTIONS = DescriptionPackage.eINSTANCE.getLayer_ToolSections();

        /**
         * The meta object literal for the '<em><b>Reused Tools</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__REUSED_TOOLS = DescriptionPackage.eINSTANCE.getLayer_ReusedTools();

        /**
         * The meta object literal for the '<em><b>Decoration Descriptions Set</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__DECORATION_DESCRIPTIONS_SET = DescriptionPackage.eINSTANCE.getLayer_DecorationDescriptionsSet();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute LAYER__ICON = DescriptionPackage.eINSTANCE.getLayer_Icon();

        /**
         * The meta object literal for the '<em><b>All Edge Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__ALL_EDGE_MAPPINGS = DescriptionPackage.eINSTANCE.getLayer_AllEdgeMappings();

        /**
         * The meta object literal for the '<em><b>Customization</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LAYER__CUSTOMIZATION = DescriptionPackage.eINSTANCE.getLayer_Customization();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.impl.AdditionalLayerImpl
         * <em>Additional Layer</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.AdditionalLayerImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getAdditionalLayer()
         * @generated
         */
        EClass ADDITIONAL_LAYER = DescriptionPackage.eINSTANCE.getAdditionalLayer();

        /**
         * The meta object literal for the '<em><b>Active By Default</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ADDITIONAL_LAYER__ACTIVE_BY_DEFAULT = DescriptionPackage.eINSTANCE.getAdditionalLayer_ActiveByDefault();

        /**
         * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ADDITIONAL_LAYER__OPTIONAL = DescriptionPackage.eINSTANCE.getAdditionalLayer_Optional();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.impl.DragAndDropTargetDescriptionImpl <em>Drag And Drop Target
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.impl.DragAndDropTargetDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getDragAndDropTargetDescription()
         * @generated
         */
        EClass DRAG_AND_DROP_TARGET_DESCRIPTION = DescriptionPackage.eINSTANCE.getDragAndDropTargetDescription();

        /**
         * The meta object literal for the '<em><b>Drop Descriptions</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DRAG_AND_DROP_TARGET_DESCRIPTION__DROP_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getDragAndDropTargetDescription_DropDescriptions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.FoldingStyle <em>Folding
         * Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.FoldingStyle
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getFoldingStyle()
         * @generated
         */
        EEnum FOLDING_STYLE = DescriptionPackage.eINSTANCE.getFoldingStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.LayoutDirection <em>Layout
         * Direction</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.LayoutDirection
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayoutDirection()
         * @generated
         */
        EEnum LAYOUT_DIRECTION = DescriptionPackage.eINSTANCE.getLayoutDirection();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.CenteringStyle <em>Centering
         * Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.CenteringStyle
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getCenteringStyle()
         * @generated
         */
        EEnum CENTERING_STYLE = DescriptionPackage.eINSTANCE.getCenteringStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.LayoutOptionTarget <em>Layout
         * Option Target</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.LayoutOptionTarget
         * @see org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl#getLayoutOptionTarget()
         * @generated
         */
        EEnum LAYOUT_OPTION_TARGET = DescriptionPackage.eINSTANCE.getLayoutOptionTarget();

    }

} // DescriptionPackage
