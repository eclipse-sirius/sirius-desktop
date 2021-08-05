/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

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
 * @see org.eclipse.sirius.diagram.description.tool.ToolFactory
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
    String eNS_URI = "http://www.eclipse.org/sirius/diagram/description/tool/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "tool"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ToolPackage eINSTANCE = org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl
     * <em>Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getToolSection()
     * @generated
     */
    int TOOL_SECTION = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__ICON = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Owned Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__OWNED_TOOLS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Sub Sections</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__SUB_SECTIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Popup Menus</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__POPUP_MENUS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__REUSED_TOOLS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Group Extensions</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__GROUP_EXTENSIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION__GROUPS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Section</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_SECTION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ToolGroupImpl
     * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolGroupImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getToolGroup()
     * @generated
     */
    int TOOL_GROUP = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.TOOL_ENTRY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.TOOL_ENTRY__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.TOOL_ENTRY__LABEL;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP__TOOLS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Group</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ToolGroupExtensionImpl
     * <em>Group Extension</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolGroupExtensionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getToolGroupExtension()
     * @generated
     */
    int TOOL_GROUP_EXTENSION = 2;

    /**
     * The feature id for the '<em><b>Group</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_EXTENSION__GROUP = 0;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_EXTENSION__TOOLS = 1;

    /**
     * The number of structural features of the '<em>Group Extension</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_GROUP_EXTENSION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl
     * <em>Node Creation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getNodeCreationDescription()
     * @generated
     */
    int NODE_CREATION_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__NODE_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__VIEW_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__ICON_PATH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Node Creation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl
     * <em>Edge Creation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getEdgeCreationDescription()
     * @generated
     */
    int EDGE_CREATION_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source View Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Target View Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__ICON_PATH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Extra Source Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Extra Target Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Connection Start Precondition</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Edge Creation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.ContainerCreationDescriptionImpl <em>Container Creation
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ContainerCreationDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getContainerCreationDescription()
     * @generated
     */
    int CONTAINER_CREATION_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__ICON_PATH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Container Creation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl
     * <em>Delete Element Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDeleteElementDescription()
     * @generated
     */
    int DELETE_ELEMENT_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__ELEMENT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Hook</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__HOOK = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Delete Element Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl
     * <em>Double Click Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDoubleClickDescription()
     * @generated
     */
    int DOUBLE_CLICK_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__ELEMENT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Element View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Double Click Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DeleteHookImpl <em>Delete
     * Hook</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DeleteHookImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDeleteHook()
     * @generated
     */
    int DELETE_HOOK = 8;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_HOOK__ID = 0;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_HOOK__PARAMETERS = 1;

    /**
     * The number of structural features of the '<em>Delete Hook</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DELETE_HOOK_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DeleteHookParameterImpl
     * <em>Delete Hook Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DeleteHookParameterImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDeleteHookParameter()
     * @generated
     */
    int DELETE_HOOK_PARAMETER = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_HOOK_PARAMETER__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_HOOK_PARAMETER__VALUE = 1;

    /**
     * The number of structural features of the '<em>Delete Hook Parameter</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_HOOK_PARAMETER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl
     * <em>Reconnect Edge Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getReconnectEdgeDescription()
     * @generated
     */
    int RECONNECT_EDGE_DESCRIPTION = 10;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Reconnection Kind</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__SOURCE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__TARGET = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Target View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__ELEMENT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Edge View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Reconnect Edge Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.RequestDescriptionImpl
     * <em>Request Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.RequestDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getRequestDescription()
     * @generated
     */
    int REQUEST_DESCRIPTION = 11;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__TYPE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Request Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl
     * <em>Direct Edit Label</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDirectEditLabel()
     * @generated
     */
    int DIRECT_EDIT_LABEL = 12;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Mask</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__MASK = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Input Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Direct Edit Label</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.BehaviorToolImpl <em>Behavior
     * Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.BehaviorToolImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getBehaviorTool()
     * @generated
     */
    int BEHAVIOR_TOOL = 13;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__DOMAIN_CLASS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Behavior Tool</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeCreationVariableImpl <em>Source Edge Creation
     * Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeCreationVariableImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getSourceEdgeCreationVariable()
     * @generated
     */
    int SOURCE_EDGE_CREATION_VARIABLE = 14;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_CREATION_VARIABLE__NAME = DescriptionPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_CREATION_VARIABLE__SUB_VARIABLES = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Source Edge Creation Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_CREATION_VARIABLE_FEATURE_COUNT = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeViewCreationVariableImpl <em>Source Edge View
     * Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeViewCreationVariableImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getSourceEdgeViewCreationVariable()
     * @generated
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE__NAME = DescriptionPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE__SUB_VARIABLES = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Source Edge View Creation Variable</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE_FEATURE_COUNT = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeCreationVariableImpl <em>Target Edge Creation
     * Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeCreationVariableImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getTargetEdgeCreationVariable()
     * @generated
     */
    int TARGET_EDGE_CREATION_VARIABLE = 16;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TARGET_EDGE_CREATION_VARIABLE__NAME = DescriptionPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TARGET_EDGE_CREATION_VARIABLE__SUB_VARIABLES = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Target Edge Creation Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TARGET_EDGE_CREATION_VARIABLE_FEATURE_COUNT = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeViewCreationVariableImpl <em>Target Edge View
     * Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeViewCreationVariableImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getTargetEdgeViewCreationVariable()
     * @generated
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE = 17;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE__NAME = DescriptionPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE__SUB_VARIABLES = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Target Edge View Creation Variable</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE_FEATURE_COUNT = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.ElementDoubleClickVariableImpl <em>Element Double Click
     * Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ElementDoubleClickVariableImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getElementDoubleClickVariable()
     * @generated
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE = 18;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE__NAME = DescriptionPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element Double Click Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE_FEATURE_COUNT = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationVariableImpl
     * <em>Node Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.NodeCreationVariableImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getNodeCreationVariable()
     * @generated
     */
    int NODE_CREATION_VARIABLE = 19;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_VARIABLE__NAME = DescriptionPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_VARIABLE__SUB_VARIABLES = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Node Creation Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_CREATION_VARIABLE_FEATURE_COUNT = DescriptionPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl <em>Create
     * View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getCreateView()
     * @generated
     */
    int CREATE_VIEW = 20;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_VIEW__SUB_MODEL_OPERATIONS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_VIEW__MAPPING = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Container View Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_VIEW__CONTAINER_VIEW_EXPRESSION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_VIEW__VARIABLE_NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Create View</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CREATE_VIEW_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.CreateEdgeViewImpl <em>Create
     * Edge View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.CreateEdgeViewImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getCreateEdgeView()
     * @generated
     */
    int CREATE_EDGE_VIEW = 21;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__SUB_MODEL_OPERATIONS = ToolPackage.CREATE_VIEW__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__MAPPING = ToolPackage.CREATE_VIEW__MAPPING;

    /**
     * The feature id for the '<em><b>Container View Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__CONTAINER_VIEW_EXPRESSION = ToolPackage.CREATE_VIEW__CONTAINER_VIEW_EXPRESSION;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__VARIABLE_NAME = ToolPackage.CREATE_VIEW__VARIABLE_NAME;

    /**
     * The feature id for the '<em><b>Source Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__SOURCE_EXPRESSION = ToolPackage.CREATE_VIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__TARGET_EXPRESSION = ToolPackage.CREATE_VIEW_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Create Edge View</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW_FEATURE_COUNT = ToolPackage.CREATE_VIEW_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.NavigationImpl
     * <em>Navigation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.NavigationImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getNavigation()
     * @generated
     */
    int NAVIGATION = 22;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NAVIGATION__SUB_MODEL_OPERATIONS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Create If Not Existent</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NAVIGATION__CREATE_IF_NOT_EXISTENT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Diagram Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NAVIGATION__DIAGRAM_DESCRIPTION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Navigation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NAVIGATION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.DiagramCreationDescriptionImpl <em>Diagram Creation
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DiagramCreationDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDiagramCreationDescription()
     * @generated
     */
    int DIAGRAM_CREATION_DESCRIPTION = 23;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__TITLE_EXPRESSION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__BROWSE_EXPRESSION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE;

    /**
     * The feature id for the '<em><b>Diagram Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__DIAGRAM_DESCRIPTION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Diagram Creation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.diagram.description.tool.impl.DiagramNavigationDescriptionImpl <em>Diagram Navigation
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.DiagramNavigationDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDiagramNavigationDescription()
     * @generated
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION = 24;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Navigation Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Container Variable</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE;

    /**
     * The feature id for the '<em><b>Diagram Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Diagram Navigation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl
     * <em>Container Drop Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getContainerDropDescription()
     * @generated
     */
    int CONTAINER_DROP_DESCRIPTION = 25;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__PRECONDITION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__FORCE_REFRESH = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__FILTERS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__ELEMENTS_TO_SELECT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__INVERSE_SELECTION_ORDER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__MAPPINGS = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Old Container</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>New Container</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__ELEMENT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>New View Container</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Drag Source</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Move Edges</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__MOVE_EDGES = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Container Drop Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.tool.ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.description.tool.ReconnectionKind <em>Reconnection
     * Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectionKind
     * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getReconnectionKind()
     * @generated
     */
    int RECONNECTION_KIND = 26;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.ToolSection
     * <em>Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Section</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection
     * @generated
     */
    EClass getToolSection();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.description.tool.ToolSection#getIcon
     * <em>Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getIcon()
     * @see #getToolSection()
     * @generated
     */
    EAttribute getToolSection_Icon();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getOwnedTools <em>Owned Tools</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Owned Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getOwnedTools()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_OwnedTools();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getSubSections <em>Sub Sections</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Sub Sections</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getSubSections()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_SubSections();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getPopupMenus <em>Popup Menus</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Popup Menus</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getPopupMenus()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_PopupMenus();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolSection#getReusedTools <em>Reused Tools</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Reused Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getReusedTools()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_ReusedTools();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.ToolSection#getGroupExtensions <em>Group Extensions</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Group Extensions</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getGroupExtensions()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_GroupExtensions();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.ToolSection#getGroups <em>Groups</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolSection#getGroups()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_Groups();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.ToolGroup <em>Group</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroup
     * @generated
     */
    EClass getToolGroup();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.ToolGroup#getTools <em>Tools</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroup#getTools()
     * @see #getToolGroup()
     * @generated
     */
    EReference getToolGroup_Tools();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension
     * <em>Group Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Extension</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroupExtension
     * @generated
     */
    EClass getToolGroupExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getGroup <em>Group</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Group</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getGroup()
     * @see #getToolGroupExtension()
     * @generated
     */
    EReference getToolGroupExtension_Group();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getTools <em>Tools</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Tools</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getTools()
     * @see #getToolGroupExtension()
     * @generated
     */
    EReference getToolGroupExtension_Tools();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription
     * <em>Node Creation Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Creation Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription
     * @generated
     */
    EClass getNodeCreationDescription();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getNodeMappings <em>Node
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Node Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getNodeMappings()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_NodeMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getVariable <em>Variable</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getVariable()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_Variable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getViewVariable <em>View
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>View Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getViewVariable()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_ViewVariable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getInitialOperation()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getIconPath <em>Icon Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getIconPath()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EAttribute getNodeCreationDescription_IconPath();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getExtraMappings <em>Extra
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Extra Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationDescription#getExtraMappings()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_ExtraMappings();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription
     * <em>Edge Creation Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Creation Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription
     * @generated
     */
    EClass getEdgeCreationDescription();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getEdgeMappings <em>Edge
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Edge Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getEdgeMappings()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_EdgeMappings();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceVariable <em>Source
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Source Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_SourceVariable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetVariable <em>Target
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Target Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_TargetVariable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceViewVariable <em>Source View
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Source View Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getSourceViewVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_SourceViewVariable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetViewVariable <em>Target View
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Target View Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getTargetViewVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_TargetViewVariable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getInitialOperation()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getIconPath <em>Icon Path</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getIconPath()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EAttribute getEdgeCreationDescription_IconPath();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getExtraSourceMappings <em>Extra
     * Source Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Extra Source Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getExtraSourceMappings()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_ExtraSourceMappings();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getExtraTargetMappings <em>Extra
     * Target Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Extra Target Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getExtraTargetMappings()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_ExtraTargetMappings();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getConnectionStartPrecondition
     * <em>Connection Start Precondition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Connection Start Precondition</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription#getConnectionStartPrecondition()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EAttribute getEdgeCreationDescription_ConnectionStartPrecondition();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription <em>Container Creation
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Creation Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription
     * @generated
     */
    EClass getContainerCreationDescription();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getContainerMappings
     * <em>Container Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Container Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getContainerMappings()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_ContainerMappings();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getVariable <em>Variable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getVariable()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_Variable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getViewVariable <em>View
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>View Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getViewVariable()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_ViewVariable();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getInitialOperation()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getIconPath <em>Icon
     * Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getIconPath()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EAttribute getContainerCreationDescription_IconPath();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getExtraMappings <em>Extra
     * Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Extra Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerCreationDescription#getExtraMappings()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_ExtraMappings();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
     * <em>Delete Element Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delete Element Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription
     * @generated
     */
    EClass getDeleteElementDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getElement <em>Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getElement()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_Element();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getElementView <em>Element
     * View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Element View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getElementView()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_ElementView();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getContainerView <em>Container
     * View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Container View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getContainerView()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getInitialOperation()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getHook <em>Hook</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Hook</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteElementDescription#getHook()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_Hook();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
     * <em>Double Click Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Double Click Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription
     * @generated
     */
    EClass getDoubleClickDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getMappings <em>Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getMappings()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_Mappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElement <em>Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElement()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_Element();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElementView <em>Element
     * View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Element View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getElementView()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_ElementView();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DoubleClickDescription#getInitialOperation()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.DeleteHook <em>Delete
     * Hook</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delete Hook</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHook
     * @generated
     */
    EClass getDeleteHook();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.description.tool.DeleteHook#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHook#getId()
     * @see #getDeleteHook()
     * @generated
     */
    EAttribute getDeleteHook_Id();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHook#getParameters <em>Parameters</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHook#getParameters()
     * @see #getDeleteHook()
     * @generated
     */
    EReference getDeleteHook_Parameters();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter
     * <em>Delete Hook Parameter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delete Hook Parameter</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHookParameter
     * @generated
     */
    EClass getDeleteHookParameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getName()
     * @see #getDeleteHookParameter()
     * @generated
     */
    EAttribute getDeleteHookParameter_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getValue <em>Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DeleteHookParameter#getValue()
     * @see #getDeleteHookParameter()
     * @generated
     */
    EAttribute getDeleteHookParameter_Value();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription
     * <em>Reconnect Edge Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Reconnect Edge Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription
     * @generated
     */
    EClass getReconnectEdgeDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getReconnectionKind <em>Reconnection
     * Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Reconnection Kind</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getReconnectionKind()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EAttribute getReconnectEdgeDescription_ReconnectionKind();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSource <em>Source</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Source</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSource()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTarget <em>Target</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTarget()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_Target();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSourceView <em>Source
     * View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Source View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getSourceView()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_SourceView();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTargetView <em>Target
     * View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Target View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getTargetView()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_TargetView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getElement <em>Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getElement()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_Element();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getInitialOperation()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getEdgeView <em>Edge View</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Edge View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectEdgeDescription#getEdgeView()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_EdgeView();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.RequestDescription
     * <em>Request Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Request Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.RequestDescription
     * @generated
     */
    EClass getRequestDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.RequestDescription#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.RequestDescription#getType()
     * @see #getRequestDescription()
     * @generated
     */
    EAttribute getRequestDescription_Type();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel <em>Direct
     * Edit Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Direct Edit Label</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DirectEditLabel
     * @generated
     */
    EClass getDirectEditLabel();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getMask <em>Mask</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Mask</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getMask()
     * @see #getDirectEditLabel()
     * @generated
     */
    EReference getDirectEditLabel_Mask();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInitialOperation()
     * @see #getDirectEditLabel()
     * @generated
     */
    EReference getDirectEditLabel_InitialOperation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInputLabelExpression <em>Input Label
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Input Label Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DirectEditLabel#getInputLabelExpression()
     * @see #getDirectEditLabel()
     * @generated
     */
    EAttribute getDirectEditLabel_InputLabelExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.BehaviorTool <em>Behavior
     * Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Behavior Tool</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.BehaviorTool
     * @generated
     */
    EClass getBehaviorTool();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.BehaviorTool#getDomainClass <em>Domain Class</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.BehaviorTool#getDomainClass()
     * @see #getBehaviorTool()
     * @generated
     */
    EAttribute getBehaviorTool_DomainClass();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.BehaviorTool#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.BehaviorTool#getInitialOperation()
     * @see #getBehaviorTool()
     * @generated
     */
    EReference getBehaviorTool_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable
     * <em>Source Edge Creation Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Source Edge Creation Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.SourceEdgeCreationVariable
     * @generated
     */
    EClass getSourceEdgeCreationVariable();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable <em>Source Edge View Creation
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Source Edge View Creation Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.SourceEdgeViewCreationVariable
     * @generated
     */
    EClass getSourceEdgeViewCreationVariable();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable
     * <em>Target Edge Creation Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Target Edge Creation Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.TargetEdgeCreationVariable
     * @generated
     */
    EClass getTargetEdgeCreationVariable();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable <em>Target Edge View Creation
     * Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Target Edge View Creation Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.TargetEdgeViewCreationVariable
     * @generated
     */
    EClass getTargetEdgeViewCreationVariable();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable
     * <em>Element Double Click Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Element Double Click Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ElementDoubleClickVariable
     * @generated
     */
    EClass getElementDoubleClickVariable();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.NodeCreationVariable
     * <em>Node Creation Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Creation Variable</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.NodeCreationVariable
     * @generated
     */
    EClass getNodeCreationVariable();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.CreateView <em>Create
     * View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Create View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateView
     * @generated
     */
    EClass getCreateView();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.tool.CreateView#getMapping <em>Mapping</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateView#getMapping()
     * @see #getCreateView()
     * @generated
     */
    EReference getCreateView_Mapping();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.CreateView#getContainerViewExpression <em>Container View
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Container View Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateView#getContainerViewExpression()
     * @see #getCreateView()
     * @generated
     */
    EAttribute getCreateView_ContainerViewExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.description.tool.CreateView#getVariableName <em>Variable Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateView#getVariableName()
     * @see #getCreateView()
     * @generated
     */
    EAttribute getCreateView_VariableName();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView <em>Create
     * Edge View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Create Edge View</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateEdgeView
     * @generated
     */
    EClass getCreateEdgeView();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getSourceExpression <em>Source
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Source Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getSourceExpression()
     * @see #getCreateEdgeView()
     * @generated
     */
    EAttribute getCreateEdgeView_SourceExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getTargetExpression <em>Target
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Expression</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.CreateEdgeView#getTargetExpression()
     * @see #getCreateEdgeView()
     * @generated
     */
    EAttribute getCreateEdgeView_TargetExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.Navigation
     * <em>Navigation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Navigation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.Navigation
     * @generated
     */
    EClass getNavigation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.Navigation#isCreateIfNotExistent <em>Create If Not
     * Existent</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Create If Not Existent</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.Navigation#isCreateIfNotExistent()
     * @see #getNavigation()
     * @generated
     */
    EAttribute getNavigation_CreateIfNotExistent();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.tool.Navigation#getDiagramDescription <em>Diagram
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.Navigation#getDiagramDescription()
     * @see #getNavigation()
     * @generated
     */
    EReference getNavigation_DiagramDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription
     * <em>Diagram Creation Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Creation Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription
     * @generated
     */
    EClass getDiagramCreationDescription();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription#getDiagramDescription <em>Diagram
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription#getDiagramDescription()
     * @see #getDiagramCreationDescription()
     * @generated
     */
    EReference getDiagramCreationDescription_DiagramDescription();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription <em>Diagram Navigation
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Navigation Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription
     * @generated
     */
    EClass getDiagramNavigationDescription();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription#getDiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.DiagramNavigationDescription#getDiagramDescription()
     * @see #getDiagramNavigationDescription()
     * @generated
     */
    EReference getDiagramNavigationDescription_DiagramDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription
     * <em>Container Drop Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Drop Description</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription
     * @generated
     */
    EClass getContainerDropDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getMappings <em>Mappings</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getMappings()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_Mappings();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getOldContainer <em>Old
     * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Old Container</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getOldContainer()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_OldContainer();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewContainer <em>New
     * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>New Container</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewContainer()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_NewContainer();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getElement <em>Element</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getElement()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_Element();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewViewContainer <em>New View
     * Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>New View Container</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getNewViewContainer()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_NewViewContainer();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getInitialOperation()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getDragSource <em>Drag
     * Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Drag Source</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#getDragSource()
     * @see #getContainerDropDescription()
     * @generated
     */
    EAttribute getContainerDropDescription_DragSource();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#isMoveEdges <em>Move Edges</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Move Edges</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ContainerDropDescription#isMoveEdges()
     * @see #getContainerDropDescription()
     * @generated
     */
    EAttribute getContainerDropDescription_MoveEdges();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.diagram.description.tool.ReconnectionKind
     * <em>Reconnection Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Reconnection Kind</em>'.
     * @see org.eclipse.sirius.diagram.description.tool.ReconnectionKind
     * @generated
     */
    EEnum getReconnectionKind();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ToolFactory getToolFactory();

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
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl
         * <em>Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolSectionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getToolSection()
         * @generated
         */
        EClass TOOL_SECTION = ToolPackage.eINSTANCE.getToolSection();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOL_SECTION__ICON = ToolPackage.eINSTANCE.getToolSection_Icon();

        /**
         * The meta object literal for the '<em><b>Owned Tools</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION__OWNED_TOOLS = ToolPackage.eINSTANCE.getToolSection_OwnedTools();

        /**
         * The meta object literal for the '<em><b>Sub Sections</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION__SUB_SECTIONS = ToolPackage.eINSTANCE.getToolSection_SubSections();

        /**
         * The meta object literal for the '<em><b>Popup Menus</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION__POPUP_MENUS = ToolPackage.eINSTANCE.getToolSection_PopupMenus();

        /**
         * The meta object literal for the '<em><b>Reused Tools</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION__REUSED_TOOLS = ToolPackage.eINSTANCE.getToolSection_ReusedTools();

        /**
         * The meta object literal for the '<em><b>Group Extensions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION__GROUP_EXTENSIONS = ToolPackage.eINSTANCE.getToolSection_GroupExtensions();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_SECTION__GROUPS = ToolPackage.eINSTANCE.getToolSection_Groups();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.ToolGroupImpl
         * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolGroupImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getToolGroup()
         * @generated
         */
        EClass TOOL_GROUP = ToolPackage.eINSTANCE.getToolGroup();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_GROUP__TOOLS = ToolPackage.eINSTANCE.getToolGroup_Tools();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.ToolGroupExtensionImpl <em>Group Extension</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolGroupExtensionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getToolGroupExtension()
         * @generated
         */
        EClass TOOL_GROUP_EXTENSION = ToolPackage.eINSTANCE.getToolGroupExtension();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_GROUP_EXTENSION__GROUP = ToolPackage.eINSTANCE.getToolGroupExtension_Group();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL_GROUP_EXTENSION__TOOLS = ToolPackage.eINSTANCE.getToolGroupExtension_Tools();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl <em>Node Creation
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.NodeCreationDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getNodeCreationDescription()
         * @generated
         */
        EClass NODE_CREATION_DESCRIPTION = ToolPackage.eINSTANCE.getNodeCreationDescription();

        /**
         * The meta object literal for the '<em><b>Node Mappings</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__NODE_MAPPINGS = ToolPackage.eINSTANCE.getNodeCreationDescription_NodeMappings();

        /**
         * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__VARIABLE = ToolPackage.eINSTANCE.getNodeCreationDescription_Variable();

        /**
         * The meta object literal for the '<em><b>View Variable</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__VIEW_VARIABLE = ToolPackage.eINSTANCE.getNodeCreationDescription_ViewVariable();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getNodeCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_CREATION_DESCRIPTION__ICON_PATH = ToolPackage.eINSTANCE.getNodeCreationDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Extra Mappings</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS = ToolPackage.eINSTANCE.getNodeCreationDescription_ExtraMappings();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl <em>Edge Creation
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.EdgeCreationDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getEdgeCreationDescription()
         * @generated
         */
        EClass EDGE_CREATION_DESCRIPTION = ToolPackage.eINSTANCE.getEdgeCreationDescription();

        /**
         * The meta object literal for the '<em><b>Edge Mappings</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS = ToolPackage.eINSTANCE.getEdgeCreationDescription_EdgeMappings();

        /**
         * The meta object literal for the '<em><b>Source Variable</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE = ToolPackage.eINSTANCE.getEdgeCreationDescription_SourceVariable();

        /**
         * The meta object literal for the '<em><b>Target Variable</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE = ToolPackage.eINSTANCE.getEdgeCreationDescription_TargetVariable();

        /**
         * The meta object literal for the '<em><b>Source View Variable</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE = ToolPackage.eINSTANCE.getEdgeCreationDescription_SourceViewVariable();

        /**
         * The meta object literal for the '<em><b>Target View Variable</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE = ToolPackage.eINSTANCE.getEdgeCreationDescription_TargetViewVariable();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getEdgeCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_CREATION_DESCRIPTION__ICON_PATH = ToolPackage.eINSTANCE.getEdgeCreationDescription_IconPath();

        /**
         * The meta object literal for the ' <em><b>Extra Source Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS = ToolPackage.eINSTANCE.getEdgeCreationDescription_ExtraSourceMappings();

        /**
         * The meta object literal for the ' <em><b>Extra Target Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS = ToolPackage.eINSTANCE.getEdgeCreationDescription_ExtraTargetMappings();

        /**
         * The meta object literal for the '<em><b>Connection Start Precondition</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION = ToolPackage.eINSTANCE.getEdgeCreationDescription_ConnectionStartPrecondition();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.ContainerCreationDescriptionImpl <em>Container
         * Creation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ContainerCreationDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getContainerCreationDescription()
         * @generated
         */
        EClass CONTAINER_CREATION_DESCRIPTION = ToolPackage.eINSTANCE.getContainerCreationDescription();

        /**
         * The meta object literal for the '<em><b>Container Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS = ToolPackage.eINSTANCE.getContainerCreationDescription_ContainerMappings();

        /**
         * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__VARIABLE = ToolPackage.eINSTANCE.getContainerCreationDescription_Variable();

        /**
         * The meta object literal for the '<em><b>View Variable</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE = ToolPackage.eINSTANCE.getContainerCreationDescription_ViewVariable();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getContainerCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTAINER_CREATION_DESCRIPTION__ICON_PATH = ToolPackage.eINSTANCE.getContainerCreationDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Extra Mappings</b></em>' reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS = ToolPackage.eINSTANCE.getContainerCreationDescription_ExtraMappings();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl <em>Delete Element
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DeleteElementDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDeleteElementDescription()
         * @generated
         */
        EClass DELETE_ELEMENT_DESCRIPTION = ToolPackage.eINSTANCE.getDeleteElementDescription();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getDeleteElementDescription_Element();

        /**
         * The meta object literal for the '<em><b>Element View</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW = ToolPackage.eINSTANCE.getDeleteElementDescription_ElementView();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW = ToolPackage.eINSTANCE.getDeleteElementDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getDeleteElementDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Hook</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__HOOK = ToolPackage.eINSTANCE.getDeleteElementDescription_Hook();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl <em>Double Click
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DoubleClickDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDoubleClickDescription()
         * @generated
         */
        EClass DOUBLE_CLICK_DESCRIPTION = ToolPackage.eINSTANCE.getDoubleClickDescription();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__MAPPINGS = ToolPackage.eINSTANCE.getDoubleClickDescription_Mappings();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getDoubleClickDescription_Element();

        /**
         * The meta object literal for the '<em><b>Element View</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW = ToolPackage.eINSTANCE.getDoubleClickDescription_ElementView();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getDoubleClickDescription_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DeleteHookImpl
         * <em>Delete Hook</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DeleteHookImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDeleteHook()
         * @generated
         */
        EClass DELETE_HOOK = ToolPackage.eINSTANCE.getDeleteHook();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DELETE_HOOK__ID = ToolPackage.eINSTANCE.getDeleteHook_Id();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DELETE_HOOK__PARAMETERS = ToolPackage.eINSTANCE.getDeleteHook_Parameters();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.DeleteHookParameterImpl <em>Delete Hook
         * Parameter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DeleteHookParameterImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDeleteHookParameter()
         * @generated
         */
        EClass DELETE_HOOK_PARAMETER = ToolPackage.eINSTANCE.getDeleteHookParameter();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DELETE_HOOK_PARAMETER__NAME = ToolPackage.eINSTANCE.getDeleteHookParameter_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DELETE_HOOK_PARAMETER__VALUE = ToolPackage.eINSTANCE.getDeleteHookParameter_Value();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl <em>Reconnect Edge
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ReconnectEdgeDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getReconnectEdgeDescription()
         * @generated
         */
        EClass RECONNECT_EDGE_DESCRIPTION = ToolPackage.eINSTANCE.getReconnectEdgeDescription();

        /**
         * The meta object literal for the '<em><b>Reconnection Kind</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND = ToolPackage.eINSTANCE.getReconnectEdgeDescription_ReconnectionKind();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__SOURCE = ToolPackage.eINSTANCE.getReconnectEdgeDescription_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__TARGET = ToolPackage.eINSTANCE.getReconnectEdgeDescription_Target();

        /**
         * The meta object literal for the '<em><b>Source View</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW = ToolPackage.eINSTANCE.getReconnectEdgeDescription_SourceView();

        /**
         * The meta object literal for the '<em><b>Target View</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW = ToolPackage.eINSTANCE.getReconnectEdgeDescription_TargetView();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getReconnectEdgeDescription_Element();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getReconnectEdgeDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Edge View</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW = ToolPackage.eINSTANCE.getReconnectEdgeDescription_EdgeView();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.RequestDescriptionImpl <em>Request
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.RequestDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getRequestDescription()
         * @generated
         */
        EClass REQUEST_DESCRIPTION = ToolPackage.eINSTANCE.getRequestDescription();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute REQUEST_DESCRIPTION__TYPE = ToolPackage.eINSTANCE.getRequestDescription_Type();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl
         * <em>Direct Edit Label</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DirectEditLabelImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDirectEditLabel()
         * @generated
         */
        EClass DIRECT_EDIT_LABEL = ToolPackage.eINSTANCE.getDirectEditLabel();

        /**
         * The meta object literal for the '<em><b>Mask</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIRECT_EDIT_LABEL__MASK = ToolPackage.eINSTANCE.getDirectEditLabel_Mask();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIRECT_EDIT_LABEL__INITIAL_OPERATION = ToolPackage.eINSTANCE.getDirectEditLabel_InitialOperation();

        /**
         * The meta object literal for the ' <em><b>Input Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION = ToolPackage.eINSTANCE.getDirectEditLabel_InputLabelExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.BehaviorToolImpl
         * <em>Behavior Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.BehaviorToolImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getBehaviorTool()
         * @generated
         */
        EClass BEHAVIOR_TOOL = ToolPackage.eINSTANCE.getBehaviorTool();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BEHAVIOR_TOOL__DOMAIN_CLASS = ToolPackage.eINSTANCE.getBehaviorTool_DomainClass();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BEHAVIOR_TOOL__INITIAL_OPERATION = ToolPackage.eINSTANCE.getBehaviorTool_InitialOperation();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeCreationVariableImpl <em>Source Edge
         * Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeCreationVariableImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getSourceEdgeCreationVariable()
         * @generated
         */
        EClass SOURCE_EDGE_CREATION_VARIABLE = ToolPackage.eINSTANCE.getSourceEdgeCreationVariable();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeViewCreationVariableImpl <em>Source Edge
         * View Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.SourceEdgeViewCreationVariableImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getSourceEdgeViewCreationVariable()
         * @generated
         */
        EClass SOURCE_EDGE_VIEW_CREATION_VARIABLE = ToolPackage.eINSTANCE.getSourceEdgeViewCreationVariable();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeCreationVariableImpl <em>Target Edge
         * Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeCreationVariableImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getTargetEdgeCreationVariable()
         * @generated
         */
        EClass TARGET_EDGE_CREATION_VARIABLE = ToolPackage.eINSTANCE.getTargetEdgeCreationVariable();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeViewCreationVariableImpl <em>Target Edge
         * View Creation Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.TargetEdgeViewCreationVariableImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getTargetEdgeViewCreationVariable()
         * @generated
         */
        EClass TARGET_EDGE_VIEW_CREATION_VARIABLE = ToolPackage.eINSTANCE.getTargetEdgeViewCreationVariable();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.ElementDoubleClickVariableImpl <em>Element Double
         * Click Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ElementDoubleClickVariableImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getElementDoubleClickVariable()
         * @generated
         */
        EClass ELEMENT_DOUBLE_CLICK_VARIABLE = ToolPackage.eINSTANCE.getElementDoubleClickVariable();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.NodeCreationVariableImpl <em>Node Creation
         * Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.NodeCreationVariableImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getNodeCreationVariable()
         * @generated
         */
        EClass NODE_CREATION_VARIABLE = ToolPackage.eINSTANCE.getNodeCreationVariable();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl
         * <em>Create View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.CreateViewImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getCreateView()
         * @generated
         */
        EClass CREATE_VIEW = ToolPackage.eINSTANCE.getCreateView();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CREATE_VIEW__MAPPING = ToolPackage.eINSTANCE.getCreateView_Mapping();

        /**
         * The meta object literal for the ' <em><b>Container View Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_VIEW__CONTAINER_VIEW_EXPRESSION = ToolPackage.eINSTANCE.getCreateView_ContainerViewExpression();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_VIEW__VARIABLE_NAME = ToolPackage.eINSTANCE.getCreateView_VariableName();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.CreateEdgeViewImpl
         * <em>Create Edge View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.CreateEdgeViewImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getCreateEdgeView()
         * @generated
         */
        EClass CREATE_EDGE_VIEW = ToolPackage.eINSTANCE.getCreateEdgeView();

        /**
         * The meta object literal for the '<em><b>Source Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_EDGE_VIEW__SOURCE_EXPRESSION = ToolPackage.eINSTANCE.getCreateEdgeView_SourceExpression();

        /**
         * The meta object literal for the '<em><b>Target Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_EDGE_VIEW__TARGET_EXPRESSION = ToolPackage.eINSTANCE.getCreateEdgeView_TargetExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.impl.NavigationImpl
         * <em>Navigation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.NavigationImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getNavigation()
         * @generated
         */
        EClass NAVIGATION = ToolPackage.eINSTANCE.getNavigation();

        /**
         * The meta object literal for the ' <em><b>Create If Not Existent</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute NAVIGATION__CREATE_IF_NOT_EXISTENT = ToolPackage.eINSTANCE.getNavigation_CreateIfNotExistent();

        /**
         * The meta object literal for the '<em><b>Diagram Description</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NAVIGATION__DIAGRAM_DESCRIPTION = ToolPackage.eINSTANCE.getNavigation_DiagramDescription();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.DiagramCreationDescriptionImpl <em>Diagram Creation
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DiagramCreationDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDiagramCreationDescription()
         * @generated
         */
        EClass DIAGRAM_CREATION_DESCRIPTION = ToolPackage.eINSTANCE.getDiagramCreationDescription();

        /**
         * The meta object literal for the '<em><b>Diagram Description</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_CREATION_DESCRIPTION__DIAGRAM_DESCRIPTION = ToolPackage.eINSTANCE.getDiagramCreationDescription_DiagramDescription();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.DiagramNavigationDescriptionImpl <em>Diagram
         * Navigation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.DiagramNavigationDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getDiagramNavigationDescription()
         * @generated
         */
        EClass DIAGRAM_NAVIGATION_DESCRIPTION = ToolPackage.eINSTANCE.getDiagramNavigationDescription();

        /**
         * The meta object literal for the '<em><b>Diagram Description</b></em>' reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION = ToolPackage.eINSTANCE.getDiagramNavigationDescription_DiagramDescription();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl <em>Container Drop
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.impl.ContainerDropDescriptionImpl
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getContainerDropDescription()
         * @generated
         */
        EClass CONTAINER_DROP_DESCRIPTION = ToolPackage.eINSTANCE.getContainerDropDescription();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__MAPPINGS = ToolPackage.eINSTANCE.getContainerDropDescription_Mappings();

        /**
         * The meta object literal for the '<em><b>Old Container</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER = ToolPackage.eINSTANCE.getContainerDropDescription_OldContainer();

        /**
         * The meta object literal for the '<em><b>New Container</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER = ToolPackage.eINSTANCE.getContainerDropDescription_NewContainer();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getContainerDropDescription_Element();

        /**
         * The meta object literal for the '<em><b>New View Container</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER = ToolPackage.eINSTANCE.getContainerDropDescription_NewViewContainer();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getContainerDropDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Drag Source</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE = ToolPackage.eINSTANCE.getContainerDropDescription_DragSource();

        /**
         * The meta object literal for the '<em><b>Move Edges</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTAINER_DROP_DESCRIPTION__MOVE_EDGES = ToolPackage.eINSTANCE.getContainerDropDescription_MoveEdges();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.description.tool.ReconnectionKind
         * <em>Reconnection Kind</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.description.tool.ReconnectionKind
         * @see org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl#getReconnectionKind()
         * @generated
         */
        EEnum RECONNECTION_KIND = ToolPackage.eINSTANCE.getReconnectionKind();

    }

} // ToolPackage
