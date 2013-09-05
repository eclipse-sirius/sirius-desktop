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
package org.eclipse.sirius.description.tool;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.sirius.description.DescriptionPackage;

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
 * @see org.eclipse.sirius.description.tool.ToolFactory
 * @model kind="package"
 * @generated
 */
public interface ToolPackage extends EPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "tool";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/tool/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "tool";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ToolPackage eINSTANCE = org.eclipse.sirius.description.tool.impl.ToolPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ToolSectionImpl
     * <em>Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ToolSectionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolSection()
     * @generated
     */
    int TOOL_SECTION = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__ICON = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Owned Tools</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__OWNED_TOOLS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Sub Sections</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__SUB_SECTIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Popup Menus</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__POPUP_MENUS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Reused Tools</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__REUSED_TOOLS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Group Extensions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION__GROUP_EXTENSIONS = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Section</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_SECTION_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.ToolEntry <em>Entry</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.ToolEntry
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolEntry()
     * @generated
     */
    int TOOL_ENTRY = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_ENTRY__DOCUMENTATION = DescriptionPackage.DOCUMENTED_ELEMENT__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_ENTRY__NAME = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_ENTRY__LABEL = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Entry</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_ENTRY_FEATURE_COUNT = DescriptionPackage.DOCUMENTED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ToolGroupImpl
     * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ToolGroupImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolGroup()
     * @generated
     */
    int TOOL_GROUP = 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP__DOCUMENTATION = TOOL_ENTRY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP__NAME = TOOL_ENTRY__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP__LABEL = TOOL_ENTRY__LABEL;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP__TOOLS = TOOL_ENTRY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Group</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP_FEATURE_COUNT = TOOL_ENTRY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ToolGroupExtensionImpl
     * <em>Group Extension</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ToolGroupExtensionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolGroupExtension()
     * @generated
     */
    int TOOL_GROUP_EXTENSION = 3;

    /**
     * The feature id for the '<em><b>Group</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP_EXTENSION__GROUP = 0;

    /**
     * The feature id for the '<em><b>Tools</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP_EXTENSION__TOOLS = 1;

    /**
     * The number of structural features of the '<em>Group Extension</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_GROUP_EXTENSION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.AbstractToolDescriptionImpl
     * <em>Abstract Tool Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.AbstractToolDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getAbstractToolDescription()
     * @generated
     */
    int ABSTRACT_TOOL_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION = TOOL_ENTRY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__NAME = TOOL_ENTRY__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__LABEL = TOOL_ENTRY__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__PRECONDITION = TOOL_ENTRY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH = TOOL_ENTRY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__FILTERS = TOOL_ENTRY_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Abstract Tool Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT = TOOL_ENTRY_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.MappingBasedToolDescriptionImpl
     * <em>Mapping Based Tool Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.MappingBasedToolDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMappingBasedToolDescription()
     * @generated
     */
    int MAPPING_BASED_TOOL_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The number of structural features of the '
     * <em>Mapping Based Tool Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ToolDescriptionImpl
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ToolDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolDescription()
     * @generated
     */
    int TOOL_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ICON_PATH = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ELEMENT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Element View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ELEMENT_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.NodeCreationDescriptionImpl
     * <em>Node Creation Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.NodeCreationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNodeCreationDescription()
     * @generated
     */
    int NODE_CREATION_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Node Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__NODE_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__VIEW_VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__ICON_PATH = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Node Creation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.EdgeCreationDescriptionImpl
     * <em>Edge Creation Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.EdgeCreationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getEdgeCreationDescription()
     * @generated
     */
    int EDGE_CREATION_DESCRIPTION = 8;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Edge Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Target View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__ICON_PATH = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Extra Source Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Extra Target Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Connection Start Precondition</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '
     * <em>Edge Creation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_CREATION_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ContainerCreationDescriptionImpl
     * <em>Container Creation Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ContainerCreationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerCreationDescription()
     * @generated
     */
    int CONTAINER_CREATION_DESCRIPTION = 9;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Container Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Variable</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>View Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__ICON_PATH = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extra Mappings</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Container Creation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_CREATION_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ContainerDropDescriptionImpl
     * <em>Container Drop Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ContainerDropDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerDropDescription()
     * @generated
     */
    int CONTAINER_DROP_DESCRIPTION = 10;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Old Container</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>New Container</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__ELEMENT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>New View Container</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Drag Source</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Move Edges</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION__MOVE_EDGES = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '
     * <em>Container Drop Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DROP_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.PasteDescriptionImpl
     * <em>Paste Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.PasteDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getPasteDescription()
     * @generated
     */
    int PASTE_DESCRIPTION = 11;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__CONTAINER = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__CONTAINER_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Copied View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__COPIED_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Copied Element</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__COPIED_ELEMENT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Paste Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DeleteElementDescriptionImpl
     * <em>Delete Element Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DeleteElementDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteElementDescription()
     * @generated
     */
    int DELETE_ELEMENT_DESCRIPTION = 12;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__ELEMENT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Hook</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION__HOOK = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '
     * <em>Delete Element Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DoubleClickDescriptionImpl
     * <em>Double Click Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DoubleClickDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDoubleClickDescription()
     * @generated
     */
    int DOUBLE_CLICK_DESCRIPTION = 13;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Mappings</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__MAPPINGS = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__ELEMENT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Element View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>Double Click Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOUBLE_CLICK_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DeleteHookImpl
     * <em>Delete Hook</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DeleteHookImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteHook()
     * @generated
     */
    int DELETE_HOOK = 14;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_HOOK__ID = 0;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_HOOK__PARAMETERS = 1;

    /**
     * The number of structural features of the '<em>Delete Hook</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_HOOK_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DeleteHookParameterImpl
     * <em>Delete Hook Parameter</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DeleteHookParameterImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteHookParameter()
     * @generated
     */
    int DELETE_HOOK_PARAMETER = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_HOOK_PARAMETER__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_HOOK_PARAMETER__VALUE = 1;

    /**
     * The number of structural features of the '<em>Delete Hook Parameter</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_HOOK_PARAMETER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ReconnectEdgeDescriptionImpl
     * <em>Reconnect Edge Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ReconnectEdgeDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getReconnectEdgeDescription()
     * @generated
     */
    int RECONNECT_EDGE_DESCRIPTION = 16;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Reconnection Kind</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__SOURCE = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__TARGET = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Target View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__ELEMENT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Edge View</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '
     * <em>Reconnect Edge Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RECONNECT_EDGE_DESCRIPTION_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.RequestDescriptionImpl
     * <em>Request Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.RequestDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRequestDescription()
     * @generated
     */
    int REQUEST_DESCRIPTION = 17;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION__TYPE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Request Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REQUEST_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SelectionWizardDescriptionImpl
     * <em>Selection Wizard Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SelectionWizardDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSelectionWizardDescription()
     * @generated
     */
    int SELECTION_WIZARD_DESCRIPTION = 18;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__MULTIPLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__TREE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__MESSAGE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ELEMENT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CONTAINER = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ICON_PATH = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Window Title</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Window Image Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the '
     * <em>Selection Wizard Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
     * <em>Pane Based Selection Wizard Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getPaneBasedSelectionWizardDescription()
     * @generated
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION = 19;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Window Title</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Window Image Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Choice Of Values Message</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Selected Values Message</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '
     * <em><b>Pre Selected Candidates Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '
     * <em>Pane Based Selection Wizard Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.RepresentationCreationDescriptionImpl
     * <em>Representation Creation Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.RepresentationCreationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRepresentationCreationDescription()
     * @generated
     */
    int REPRESENTATION_CREATION_DESCRIPTION = 20;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Representation Creation Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.RepresentationNavigationDescriptionImpl
     * <em>Representation Navigation Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.RepresentationNavigationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRepresentationNavigationDescription()
     * @generated
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION = 21;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Navigation Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Container Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Representation Navigation Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.MenuItemOrRef
     * <em>Menu Item Or Ref</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.MenuItemOrRef
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMenuItemOrRef()
     * @generated
     */
    int MENU_ITEM_OR_REF = 22;

    /**
     * The number of structural features of the '<em>Menu Item Or Ref</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_OR_REF_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.MenuItemDescriptionImpl
     * <em>Menu Item Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.MenuItemDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMenuItemDescription()
     * @generated
     */
    int MENU_ITEM_DESCRIPTION = 23;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__ICON = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Menu Item Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.MenuItemDescriptionReferenceImpl
     * <em>Menu Item Description Reference</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.MenuItemDescriptionReferenceImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMenuItemDescriptionReference()
     * @generated
     */
    int MENU_ITEM_DESCRIPTION_REFERENCE = 24;

    /**
     * The feature id for the '<em><b>Item</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION_REFERENCE__ITEM = MENU_ITEM_OR_REF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Menu Item Description Reference</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION_REFERENCE_FEATURE_COUNT = MENU_ITEM_OR_REF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.OperationActionImpl
     * <em>Operation Action</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.OperationActionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getOperationAction()
     * @generated
     */
    int OPERATION_ACTION = 25;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__DOCUMENTATION = MENU_ITEM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__NAME = MENU_ITEM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__LABEL = MENU_ITEM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__PRECONDITION = MENU_ITEM_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__FORCE_REFRESH = MENU_ITEM_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__FILTERS = MENU_ITEM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__ICON = MENU_ITEM_DESCRIPTION__ICON;

    /**
     * The feature id for the '<em><b>View</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__VIEW = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__INITIAL_OPERATION = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Operation Action</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION_FEATURE_COUNT = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ExternalJavaActionImpl
     * <em>External Java Action</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ExternalJavaActionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getExternalJavaAction()
     * @generated
     */
    int EXTERNAL_JAVA_ACTION = 26;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__DOCUMENTATION = MENU_ITEM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__NAME = MENU_ITEM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__LABEL = MENU_ITEM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__PRECONDITION = MENU_ITEM_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__FORCE_REFRESH = MENU_ITEM_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__FILTERS = MENU_ITEM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__ICON = MENU_ITEM_DESCRIPTION__ICON;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__ID = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__PARAMETERS = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>External Java Action</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_FEATURE_COUNT = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ExternalJavaActionCallImpl
     * <em>External Java Action Call</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ExternalJavaActionCallImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getExternalJavaActionCall()
     * @generated
     */
    int EXTERNAL_JAVA_ACTION_CALL = 27;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__DOCUMENTATION = MENU_ITEM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__NAME = MENU_ITEM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__LABEL = MENU_ITEM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__PRECONDITION = MENU_ITEM_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__FORCE_REFRESH = MENU_ITEM_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__FILTERS = MENU_ITEM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__ICON = MENU_ITEM_DESCRIPTION__ICON;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Action</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__ACTION = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>External Java Action Call</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL_FEATURE_COUNT = MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.PopupMenuImpl
     * <em>Popup Menu</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.PopupMenuImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getPopupMenu()
     * @generated
     */
    int POPUP_MENU = 28;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Menu Item Description</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__MENU_ITEM_DESCRIPTION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Popup Menu</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DirectEditLabelImpl
     * <em>Direct Edit Label</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DirectEditLabelImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDirectEditLabel()
     * @generated
     */
    int DIRECT_EDIT_LABEL = 29;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__DOCUMENTATION = MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__NAME = MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__LABEL = MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__PRECONDITION = MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__FORCE_REFRESH = MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__FILTERS = MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Mask</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__MASK = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__INITIAL_OPERATION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Input Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Direct Edit Label</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIRECT_EDIT_LABEL_FEATURE_COUNT = MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.BehaviorToolImpl
     * <em>Behavior Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.BehaviorToolImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getBehaviorTool()
     * @generated
     */
    int BEHAVIOR_TOOL = 30;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__DOCUMENTATION = ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__NAME = ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__LABEL = ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__PRECONDITION = ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__FORCE_REFRESH = ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__FILTERS = ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__DOMAIN_CLASS = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL__INITIAL_OPERATION = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Behavior Tool</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEHAVIOR_TOOL_FEATURE_COUNT = ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.AbstractVariableImpl
     * <em>Abstract Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.AbstractVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getAbstractVariable()
     * @generated
     */
    int ABSTRACT_VARIABLE = 31;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VARIABLE__NAME = 0;

    /**
     * The number of structural features of the '<em>Abstract Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_VARIABLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.VariableContainerImpl
     * <em>Variable Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.VariableContainerImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getVariableContainer()
     * @generated
     */
    int VARIABLE_CONTAINER = 32;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABLE_CONTAINER__SUB_VARIABLES = 0;

    /**
     * The number of structural features of the '<em>Variable Container</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VARIABLE_CONTAINER_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.AcceleoVariableImpl
     * <em>Acceleo Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.AcceleoVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getAcceleoVariable()
     * @generated
     */
    int ACCELEO_VARIABLE = 33;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE__SUB_VARIABLES = VARIABLE_CONTAINER__SUB_VARIABLES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE__NAME = VARIABLE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE__COMPUTATION_EXPRESSION = VARIABLE_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Acceleo Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE_FEATURE_COUNT = VARIABLE_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SubVariableImpl
     * <em>Sub Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SubVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSubVariable()
     * @generated
     */
    int SUB_VARIABLE = 34;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SUB_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '<em>Sub Variable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SUB_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DialogVariableImpl
     * <em>Dialog Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DialogVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDialogVariable()
     * @generated
     */
    int DIALOG_VARIABLE = 35;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIALOG_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Dialog Prompt</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIALOG_VARIABLE__DIALOG_PROMPT = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Dialog Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIALOG_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SourceEdgeCreationVariableImpl
     * <em>Source Edge Creation Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SourceEdgeCreationVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSourceEdgeCreationVariable()
     * @generated
     */
    int SOURCE_EDGE_CREATION_VARIABLE = 36;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_CREATION_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_CREATION_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Source Edge Creation Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_CREATION_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SourceEdgeViewCreationVariableImpl
     * <em>Source Edge View Creation Variable</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SourceEdgeViewCreationVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSourceEdgeViewCreationVariable()
     * @generated
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE = 37;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Source Edge View Creation Variable</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SOURCE_EDGE_VIEW_CREATION_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.TargetEdgeCreationVariableImpl
     * <em>Target Edge Creation Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.TargetEdgeCreationVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getTargetEdgeCreationVariable()
     * @generated
     */
    int TARGET_EDGE_CREATION_VARIABLE = 38;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_EDGE_CREATION_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_EDGE_CREATION_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Target Edge Creation Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_EDGE_CREATION_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.TargetEdgeViewCreationVariableImpl
     * <em>Target Edge View Creation Variable</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.TargetEdgeViewCreationVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getTargetEdgeViewCreationVariable()
     * @generated
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE = 39;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Target Edge View Creation Variable</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TARGET_EDGE_VIEW_CREATION_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ElementDropVariableImpl
     * <em>Element Drop Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ElementDropVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementDropVariable()
     * @generated
     */
    int ELEMENT_DROP_VARIABLE = 40;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DROP_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DROP_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element Drop Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DROP_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ElementSelectVariableImpl
     * <em>Element Select Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ElementSelectVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementSelectVariable()
     * @generated
     */
    int ELEMENT_SELECT_VARIABLE = 41;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_SELECT_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '
     * <em>Element Select Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_SELECT_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ElementVariableImpl
     * <em>Element Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ElementVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementVariable()
     * @generated
     */
    int ELEMENT_VARIABLE = 42;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ElementViewVariableImpl
     * <em>Element View Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ElementViewVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementViewVariable()
     * @generated
     */
    int ELEMENT_VIEW_VARIABLE = 43;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VIEW_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VIEW_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element View Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VIEW_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ElementDeleteVariableImpl
     * <em>Element Delete Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ElementDeleteVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementDeleteVariable()
     * @generated
     */
    int ELEMENT_DELETE_VARIABLE = 44;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DELETE_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DELETE_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Element Delete Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DELETE_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ElementDoubleClickVariableImpl
     * <em>Element Double Click Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ElementDoubleClickVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementDoubleClickVariable()
     * @generated
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE = 45;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Element Double Click Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DOUBLE_CLICK_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.NodeCreationVariableImpl
     * <em>Node Creation Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.NodeCreationVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNodeCreationVariable()
     * @generated
     */
    int NODE_CREATION_VARIABLE = 46;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Node Creation Variable</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_CREATION_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DropContainerVariableImpl
     * <em>Drop Container Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DropContainerVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDropContainerVariable()
     * @generated
     */
    int DROP_CONTAINER_VARIABLE = 47;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DROP_CONTAINER_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DROP_CONTAINER_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Drop Container Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DROP_CONTAINER_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SelectContainerVariableImpl
     * <em>Select Container Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SelectContainerVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSelectContainerVariable()
     * @generated
     */
    int SELECT_CONTAINER_VARIABLE = 48;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_CONTAINER_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_CONTAINER_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Select Container Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_CONTAINER_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ContainerViewVariableImpl
     * <em>Container View Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ContainerViewVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerViewVariable()
     * @generated
     */
    int CONTAINER_VIEW_VARIABLE = 49;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VIEW_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VIEW_VARIABLE__SUB_VARIABLES = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Container View Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VIEW_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SelectModelElementVariableImpl
     * <em>Select Model Element Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SelectModelElementVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSelectModelElementVariable()
     * @generated
     */
    int SELECT_MODEL_ELEMENT_VARIABLE = 50;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__NAME = SUB_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION = SUB_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE = SUB_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__TREE = SUB_VARIABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION = SUB_VARIABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION = SUB_VARIABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE = SUB_VARIABLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Select Model Element Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE_FEATURE_COUNT = SUB_VARIABLE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.EditMaskVariablesImpl
     * <em>Edit Mask Variables</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.EditMaskVariablesImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getEditMaskVariables()
     * @generated
     */
    int EDIT_MASK_VARIABLES = 51;

    /**
     * The feature id for the '<em><b>Mask</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDIT_MASK_VARIABLES__MASK = 0;

    /**
     * The number of structural features of the '<em>Edit Mask Variables</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDIT_MASK_VARIABLES_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ModelOperationImpl
     * <em>Model Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ModelOperationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getModelOperation()
     * @generated
     */
    int MODEL_OPERATION = 53;

    /**
     * The number of structural features of the '<em>Model Operation</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MODEL_OPERATION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ContainerModelOperationImpl
     * <em>Container Model Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ContainerModelOperationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerModelOperation()
     * @generated
     */
    int CONTAINER_MODEL_OPERATION = 52;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS = MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Container Model Operation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MODEL_OPERATION_FEATURE_COUNT = MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.InitialNodeCreationOperationImpl
     * <em>Initial Node Creation Operation</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.InitialNodeCreationOperationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitialNodeCreationOperation()
     * @generated
     */
    int INITIAL_NODE_CREATION_OPERATION = 54;

    /**
     * The feature id for the '<em><b>First Model Operations</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INITIAL_NODE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Initial Node Creation Operation</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INITIAL_NODE_CREATION_OPERATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.InitialOperationImpl
     * <em>Initial Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.InitialOperationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitialOperation()
     * @generated
     */
    int INITIAL_OPERATION = 55;

    /**
     * The feature id for the '<em><b>First Model Operations</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INITIAL_OPERATION__FIRST_MODEL_OPERATIONS = 0;

    /**
     * The number of structural features of the '<em>Initial Operation</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INITIAL_OPERATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.InitEdgeCreationOperationImpl
     * <em>Init Edge Creation Operation</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.InitEdgeCreationOperationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitEdgeCreationOperation()
     * @generated
     */
    int INIT_EDGE_CREATION_OPERATION = 56;

    /**
     * The feature id for the '<em><b>First Model Operations</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INIT_EDGE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Init Edge Creation Operation</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INIT_EDGE_CREATION_OPERATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.InitialContainerDropOperationImpl
     * <em>Initial Container Drop Operation</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.InitialContainerDropOperationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitialContainerDropOperation()
     * @generated
     */
    int INITIAL_CONTAINER_DROP_OPERATION = 57;

    /**
     * The feature id for the '<em><b>First Model Operations</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS = 0;

    /**
     * The number of structural features of the '
     * <em>Initial Container Drop Operation</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INITIAL_CONTAINER_DROP_OPERATION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.CreateInstanceImpl
     * <em>Create Instance</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.CreateInstanceImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCreateInstance()
     * @generated
     */
    int CREATE_INSTANCE = 58;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Type Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__TYPE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reference Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__REFERENCE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__VARIABLE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Create Instance</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ChangeContextImpl
     * <em>Change Context</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ChangeContextImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getChangeContext()
     * @generated
     */
    int CHANGE_CONTEXT = 59;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT__BROWSE_EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Change Context</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SetValueImpl
     * <em>Set Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SetValueImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSetValue()
     * @generated
     */
    int SET_VALUE = 60;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_VALUE__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_VALUE__FEATURE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_VALUE__VALUE_EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Set Value</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_VALUE_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SetObjectImpl
     * <em>Set Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SetObjectImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSetObject()
     * @generated
     */
    int SET_OBJECT = 61;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_OBJECT__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_OBJECT__FEATURE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_OBJECT__OBJECT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Set Object</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_OBJECT_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.UnsetImpl
     * <em>Unset</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.UnsetImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getUnset()
     * @generated
     */
    int UNSET = 62;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int UNSET__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int UNSET__FEATURE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int UNSET__ELEMENT_EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Unset</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int UNSET_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.MoveElementImpl
     * <em>Move Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.MoveElementImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMoveElement()
     * @generated
     */
    int MOVE_ELEMENT = 63;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>New Container Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT__FEATURE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Move Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.RemoveElementImpl
     * <em>Remove Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.RemoveElementImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRemoveElement()
     * @generated
     */
    int REMOVE_ELEMENT = 64;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOVE_ELEMENT__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The number of structural features of the '<em>Remove Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOVE_ELEMENT_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ForImpl <em>For</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ForImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getFor()
     * @generated
     */
    int FOR = 65;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOR__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOR__EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Iterator Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOR__ITERATOR_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>For</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOR_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.CreateViewImpl
     * <em>Create View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.CreateViewImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCreateView()
     * @generated
     */
    int CREATE_VIEW = 66;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_VIEW__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_VIEW__MAPPING = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Container View Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_VIEW__CONTAINER_VIEW_EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_VIEW__VARIABLE_NAME = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Create View</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_VIEW_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.CreateEdgeViewImpl
     * <em>Create Edge View</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.CreateEdgeViewImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCreateEdgeView()
     * @generated
     */
    int CREATE_EDGE_VIEW = 67;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__SUB_MODEL_OPERATIONS = CREATE_VIEW__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__MAPPING = CREATE_VIEW__MAPPING;

    /**
     * The feature id for the '<em><b>Container View Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__CONTAINER_VIEW_EXPRESSION = CREATE_VIEW__CONTAINER_VIEW_EXPRESSION;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__VARIABLE_NAME = CREATE_VIEW__VARIABLE_NAME;

    /**
     * The feature id for the '<em><b>Source Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__SOURCE_EXPRESSION = CREATE_VIEW_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW__TARGET_EXPRESSION = CREATE_VIEW_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Create Edge View</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_EDGE_VIEW_FEATURE_COUNT = CREATE_VIEW_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.IfImpl <em>If</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.IfImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getIf()
     * @generated
     */
    int IF = 68;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DeleteViewImpl
     * <em>Delete View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DeleteViewImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteView()
     * @generated
     */
    int DELETE_VIEW = 69;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.NavigationImpl
     * <em>Navigation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.NavigationImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNavigation()
     * @generated
     */
    int NAVIGATION = 70;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.NameVariableImpl
     * <em>Name Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.NameVariableImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNameVariable()
     * @generated
     */
    int NAME_VARIABLE = 71;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ExternalJavaActionParameterImpl
     * <em>External Java Action Parameter</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ExternalJavaActionParameterImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getExternalJavaActionParameter()
     * @generated
     */
    int EXTERNAL_JAVA_ACTION_PARAMETER = 72;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DiagramCreationDescriptionImpl
     * <em>Diagram Creation Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DiagramCreationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDiagramCreationDescription()
     * @generated
     */
    int DIAGRAM_CREATION_DESCRIPTION = 73;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DiagramNavigationDescriptionImpl
     * <em>Diagram Navigation Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DiagramNavigationDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDiagramNavigationDescription()
     * @generated
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION = 74;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.ToolFilterDescriptionImpl
     * <em>Filter Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.ToolFilterDescriptionImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolFilterDescription()
     * @generated
     */
    int TOOL_FILTER_DESCRIPTION = 75;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.FeatureChangeListenerImpl
     * <em>Feature Change Listener</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.FeatureChangeListenerImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getFeatureChangeListener()
     * @generated
     */
    int FEATURE_CHANGE_LISTENER = 76;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IF__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IF__CONDITION_EXPRESSION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>If</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IF_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_VIEW__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The number of structural features of the '<em>Delete View</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_VIEW_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAVIGATION__SUB_MODEL_OPERATIONS = CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Create If Not Existent</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAVIGATION__CREATE_IF_NOT_EXISTENT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Diagram Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAVIGATION__DIAGRAM_DESCRIPTION = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Navigation</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAVIGATION_FEATURE_COUNT = CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_VARIABLE__NAME = ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '<em>Name Variable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_VARIABLE_FEATURE_COUNT = ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_PARAMETER__NAME = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_PARAMETER__VALUE = 1;

    /**
     * The number of structural features of the '
     * <em>External Java Action Parameter</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_PARAMETER_FEATURE_COUNT = 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__DOCUMENTATION = REPRESENTATION_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__NAME = REPRESENTATION_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__LABEL = REPRESENTATION_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__PRECONDITION = REPRESENTATION_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__FORCE_REFRESH = REPRESENTATION_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__FILTERS = REPRESENTATION_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__TITLE_EXPRESSION = REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__BROWSE_EXPRESSION = REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__INITIAL_OPERATION = REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE;

    /**
     * The feature id for the '<em><b>Diagram Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION__DIAGRAM_DESCRIPTION = REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Diagram Creation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_CREATION_DESCRIPTION_FEATURE_COUNT = REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__DOCUMENTATION = REPRESENTATION_NAVIGATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__NAME = REPRESENTATION_NAVIGATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__LABEL = REPRESENTATION_NAVIGATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__PRECONDITION = REPRESENTATION_NAVIGATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__FORCE_REFRESH = REPRESENTATION_NAVIGATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__FILTERS = REPRESENTATION_NAVIGATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Navigation Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Container Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE;

    /**
     * The feature id for the '<em><b>Diagram Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION = REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Diagram Navigation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIAGRAM_NAVIGATION_DESCRIPTION_FEATURE_COUNT = REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_FILTER_DESCRIPTION__PRECONDITION = 0;

    /**
     * The feature id for the '<em><b>Elements To Listen</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN = 1;

    /**
     * The feature id for the '<em><b>Listeners</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_FILTER_DESCRIPTION__LISTENERS = 2;

    /**
     * The number of structural features of the '<em>Filter Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_FILTER_DESCRIPTION_FEATURE_COUNT = 3;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_CHANGE_LISTENER__DOMAIN_CLASS = 0;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_CHANGE_LISTENER__FEATURE_NAME = 1;

    /**
     * The number of structural features of the '
     * <em>Feature Change Listener</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_CHANGE_LISTENER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SwitchChildImpl
     * <em>Switch Child</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SwitchChildImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSwitchChild()
     * @generated
     */
    int SWITCH_CHILD = 78;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH_CHILD__SUB_MODEL_OPERATIONS = 0;

    /**
     * The number of structural features of the '<em>Switch Child</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH_CHILD_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.CaseImpl
     * <em>Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.CaseImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCase()
     * @generated
     */
    int CASE = 77;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CASE__SUB_MODEL_OPERATIONS = SWITCH_CHILD__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CASE__CONDITION_EXPRESSION = SWITCH_CHILD_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Case</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CASE_FEATURE_COUNT = SWITCH_CHILD_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.DefaultImpl
     * <em>Default</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.DefaultImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDefault()
     * @generated
     */
    int DEFAULT = 79;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEFAULT__SUB_MODEL_OPERATIONS = SWITCH_CHILD__SUB_MODEL_OPERATIONS;

    /**
     * The number of structural features of the '<em>Default</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEFAULT_FEATURE_COUNT = SWITCH_CHILD_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.impl.SwitchImpl
     * <em>Switch</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.impl.SwitchImpl
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSwitch()
     * @generated
     */
    int SWITCH = 80;

    /**
     * The feature id for the '<em><b>Cases</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH__CASES = MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Default</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH__DEFAULT = MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Switch</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH_FEATURE_COUNT = MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.ReconnectionKind
     * <em>Reconnection Kind</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.description.tool.ReconnectionKind
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getReconnectionKind()
     * @generated
     */
    int RECONNECTION_KIND = 81;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.description.tool.DragSource
     * <em>Drag Source</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see org.eclipse.sirius.description.tool.DragSource
     * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDragSource()
     * @generated
     */
    int DRAG_SOURCE = 82;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ToolSection
     * <em>Section</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Section</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection
     * @generated
     */
    EClass getToolSection();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ToolSection#getIcon
     * <em>Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection#getIcon()
     * @see #getToolSection()
     * @generated
     */
    EAttribute getToolSection_Icon();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ToolSection#getOwnedTools
     * <em>Owned Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Tools</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection#getOwnedTools()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_OwnedTools();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ToolSection#getSubSections
     * <em>Sub Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Sections</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection#getSubSections()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_SubSections();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.ToolSection#getPopupMenus
     * <em>Popup Menus</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Popup Menus</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection#getPopupMenus()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_PopupMenus();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.ToolSection#getReusedTools
     * <em>Reused Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Reused Tools</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection#getReusedTools()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_ReusedTools();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ToolSection#getGroupExtensions
     * <em>Group Extensions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Group Extensions</em>'.
     * @see org.eclipse.sirius.description.tool.ToolSection#getGroupExtensions()
     * @see #getToolSection()
     * @generated
     */
    EReference getToolSection_GroupExtensions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ToolEntry <em>Entry</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Entry</em>'.
     * @see org.eclipse.sirius.description.tool.ToolEntry
     * @generated
     */
    EClass getToolEntry();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ToolGroup <em>Group</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Group</em>'.
     * @see org.eclipse.sirius.description.tool.ToolGroup
     * @generated
     */
    EClass getToolGroup();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ToolGroup#getTools
     * <em>Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Tools</em>'.
     * @see org.eclipse.sirius.description.tool.ToolGroup#getTools()
     * @see #getToolGroup()
     * @generated
     */
    EReference getToolGroup_Tools();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ToolGroupExtension
     * <em>Group Extension</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Group Extension</em>'.
     * @see org.eclipse.sirius.description.tool.ToolGroupExtension
     * @generated
     */
    EClass getToolGroupExtension();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.ToolGroupExtension#getGroup
     * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Group</em>'.
     * @see org.eclipse.sirius.description.tool.ToolGroupExtension#getGroup()
     * @see #getToolGroupExtension()
     * @generated
     */
    EReference getToolGroupExtension_Group();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ToolGroupExtension#getTools
     * <em>Tools</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Tools</em>'.
     * @see org.eclipse.sirius.description.tool.ToolGroupExtension#getTools()
     * @see #getToolGroupExtension()
     * @generated
     */
    EReference getToolGroupExtension_Tools();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.AbstractToolDescription
     * <em>Abstract Tool Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Tool Description</em>'.
     * @see org.eclipse.sirius.description.tool.AbstractToolDescription
     * @generated
     */
    EClass getAbstractToolDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.AbstractToolDescription#getPrecondition
     * <em>Precondition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Precondition</em>'.
     * @see org.eclipse.sirius.description.tool.AbstractToolDescription#getPrecondition()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EAttribute getAbstractToolDescription_Precondition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.AbstractToolDescription#isForceRefresh
     * <em>Force Refresh</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Force Refresh</em>'.
     * @see org.eclipse.sirius.description.tool.AbstractToolDescription#isForceRefresh()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EAttribute getAbstractToolDescription_ForceRefresh();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.AbstractToolDescription#getFilters
     * <em>Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Filters</em>'.
     * @see org.eclipse.sirius.description.tool.AbstractToolDescription#getFilters()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EReference getAbstractToolDescription_Filters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.MappingBasedToolDescription
     * <em>Mapping Based Tool Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Mapping Based Tool Description</em>'.
     * @see org.eclipse.sirius.description.tool.MappingBasedToolDescription
     * @generated
     */
    EClass getMappingBasedToolDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ToolDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.description.tool.ToolDescription
     * @generated
     */
    EClass getToolDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ToolDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.description.tool.ToolDescription#getIconPath()
     * @see #getToolDescription()
     * @generated
     */
    EAttribute getToolDescription_IconPath();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ToolDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.ToolDescription#getElement()
     * @see #getToolDescription()
     * @generated
     */
    EReference getToolDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ToolDescription#getElementView
     * <em>Element View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Element View</em>'.
     * @see org.eclipse.sirius.description.tool.ToolDescription#getElementView()
     * @see #getToolDescription()
     * @generated
     */
    EReference getToolDescription_ElementView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ToolDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.ToolDescription#getInitialOperation()
     * @see #getToolDescription()
     * @generated
     */
    EReference getToolDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription
     * <em>Node Creation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Creation Description</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription
     * @generated
     */
    EClass getNodeCreationDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getNodeMappings
     * <em>Node Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Node Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription#getNodeMappings()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_NodeMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getVariable
     * <em>Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Variable</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription#getVariable()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_Variable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getViewVariable
     * <em>View Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription#getViewVariable()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_ViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription#getInitialOperation()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription#getIconPath()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EAttribute getNodeCreationDescription_IconPath();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.NodeCreationDescription#getExtraMappings
     * <em>Extra Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Extra Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationDescription#getExtraMappings()
     * @see #getNodeCreationDescription()
     * @generated
     */
    EReference getNodeCreationDescription_ExtraMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription
     * <em>Edge Creation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Creation Description</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription
     * @generated
     */
    EClass getEdgeCreationDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getEdgeMappings
     * <em>Edge Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Edge Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getEdgeMappings()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_EdgeMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getSourceVariable
     * <em>Source Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Source Variable</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getSourceVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_SourceVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getTargetVariable
     * <em>Target Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Target Variable</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getTargetVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_TargetVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getSourceViewVariable
     * <em>Source View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Source View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getSourceViewVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_SourceViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getTargetViewVariable
     * <em>Target View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Target View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getTargetViewVariable()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_TargetViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getInitialOperation()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getIconPath()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EAttribute getEdgeCreationDescription_IconPath();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getExtraSourceMappings
     * <em>Extra Source Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Extra Source Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getExtraSourceMappings()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_ExtraSourceMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getExtraTargetMappings
     * <em>Extra Target Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Extra Target Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getExtraTargetMappings()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EReference getEdgeCreationDescription_ExtraTargetMappings();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.EdgeCreationDescription#getConnectionStartPrecondition
     * <em>Connection Start Precondition</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Connection Start Precondition</em>'.
     * @see org.eclipse.sirius.description.tool.EdgeCreationDescription#getConnectionStartPrecondition()
     * @see #getEdgeCreationDescription()
     * @generated
     */
    EAttribute getEdgeCreationDescription_ConnectionStartPrecondition();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription
     * <em>Container Creation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Container Creation Description</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription
     * @generated
     */
    EClass getContainerCreationDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription#getContainerMappings
     * <em>Container Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference list '
     *         <em>Container Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription#getContainerMappings()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_ContainerMappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription#getVariable
     * <em>Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Variable</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription#getVariable()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_Variable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription#getViewVariable
     * <em>View Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription#getViewVariable()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_ViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription#getInitialOperation()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription#getIconPath()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EAttribute getContainerCreationDescription_IconPath();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.ContainerCreationDescription#getExtraMappings
     * <em>Extra Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Extra Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerCreationDescription#getExtraMappings()
     * @see #getContainerCreationDescription()
     * @generated
     */
    EReference getContainerCreationDescription_ExtraMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription
     * <em>Container Drop Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Drop Description</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription
     * @generated
     */
    EClass getContainerDropDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getMappings()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_Mappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getOldContainer
     * <em>Old Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Old Container</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getOldContainer()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_OldContainer();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getNewContainer
     * <em>New Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>New Container</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getNewContainer()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_NewContainer();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getElement()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getNewViewContainer
     * <em>New View Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>New View Container</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getNewViewContainer()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_NewViewContainer();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getInitialOperation()
     * @see #getContainerDropDescription()
     * @generated
     */
    EReference getContainerDropDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#getDragSource
     * <em>Drag Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Drag Source</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#getDragSource()
     * @see #getContainerDropDescription()
     * @generated
     */
    EAttribute getContainerDropDescription_DragSource();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ContainerDropDescription#isMoveEdges
     * <em>Move Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Move Edges</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerDropDescription#isMoveEdges()
     * @see #getContainerDropDescription()
     * @generated
     */
    EAttribute getContainerDropDescription_MoveEdges();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.PasteDescription
     * <em>Paste Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Paste Description</em>'.
     * @see org.eclipse.sirius.description.tool.PasteDescription
     * @generated
     */
    EClass getPasteDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PasteDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.PasteDescription#getContainer()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_Container();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PasteDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.description.tool.PasteDescription#getContainerView()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PasteDescription#getCopiedView
     * <em>Copied View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Copied View</em>'.
     * @see org.eclipse.sirius.description.tool.PasteDescription#getCopiedView()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_CopiedView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PasteDescription#getCopiedElement
     * <em>Copied Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Copied Element</em>'.
     * @see org.eclipse.sirius.description.tool.PasteDescription#getCopiedElement()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_CopiedElement();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PasteDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.PasteDescription#getInitialOperation()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DeleteElementDescription
     * <em>Delete Element Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Delete Element Description</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteElementDescription
     * @generated
     */
    EClass getDeleteElementDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DeleteElementDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteElementDescription#getElement()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DeleteElementDescription#getElementView
     * <em>Element View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Element View</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteElementDescription#getElementView()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_ElementView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DeleteElementDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteElementDescription#getContainerView()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DeleteElementDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteElementDescription#getInitialOperation()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DeleteElementDescription#getHook
     * <em>Hook</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Hook</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteElementDescription#getHook()
     * @see #getDeleteElementDescription()
     * @generated
     */
    EReference getDeleteElementDescription_Hook();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DoubleClickDescription
     * <em>Double Click Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Double Click Description</em>'.
     * @see org.eclipse.sirius.description.tool.DoubleClickDescription
     * @generated
     */
    EClass getDoubleClickDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.description.tool.DoubleClickDescription#getMappings
     * <em>Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Mappings</em>'.
     * @see org.eclipse.sirius.description.tool.DoubleClickDescription#getMappings()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_Mappings();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DoubleClickDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.DoubleClickDescription#getElement()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DoubleClickDescription#getElementView
     * <em>Element View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Element View</em>'.
     * @see org.eclipse.sirius.description.tool.DoubleClickDescription#getElementView()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_ElementView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DoubleClickDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.DoubleClickDescription#getInitialOperation()
     * @see #getDoubleClickDescription()
     * @generated
     */
    EReference getDoubleClickDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DeleteHook
     * <em>Delete Hook</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Delete Hook</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteHook
     * @generated
     */
    EClass getDeleteHook();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.DeleteHook#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteHook#getId()
     * @see #getDeleteHook()
     * @generated
     */
    EAttribute getDeleteHook_Id();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.DeleteHook#getParameters
     * <em>Parameters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Parameters</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteHook#getParameters()
     * @see #getDeleteHook()
     * @generated
     */
    EReference getDeleteHook_Parameters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DeleteHookParameter
     * <em>Delete Hook Parameter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Delete Hook Parameter</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteHookParameter
     * @generated
     */
    EClass getDeleteHookParameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.DeleteHookParameter#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteHookParameter#getName()
     * @see #getDeleteHookParameter()
     * @generated
     */
    EAttribute getDeleteHookParameter_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.DeleteHookParameter#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteHookParameter#getValue()
     * @see #getDeleteHookParameter()
     * @generated
     */
    EAttribute getDeleteHookParameter_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription
     * <em>Reconnect Edge Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Reconnect Edge Description</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription
     * @generated
     */
    EClass getReconnectEdgeDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getReconnectionKind
     * <em>Reconnection Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Reconnection Kind</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getReconnectionKind()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EAttribute getReconnectEdgeDescription_ReconnectionKind();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getSource
     * <em>Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Source</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getSource()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_Source();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getTarget
     * <em>Target</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Target</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getTarget()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_Target();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getSourceView
     * <em>Source View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Source View</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getSourceView()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_SourceView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getTargetView
     * <em>Target View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Target View</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getTargetView()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_TargetView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getElement()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getInitialOperation()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getEdgeView
     * <em>Edge View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Edge View</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.ReconnectEdgeDescription#getEdgeView()
     * @see #getReconnectEdgeDescription()
     * @generated
     */
    EReference getReconnectEdgeDescription_EdgeView();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.RequestDescription
     * <em>Request Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Request Description</em>'.
     * @see org.eclipse.sirius.description.tool.RequestDescription
     * @generated
     */
    EClass getRequestDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.RequestDescription#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.description.tool.RequestDescription#getType()
     * @see #getRequestDescription()
     * @generated
     */
    EAttribute getRequestDescription_Type();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription
     * <em>Selection Wizard Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Selection Wizard Description</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription
     * @generated
     */
    EClass getSelectionWizardDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getInitialOperation()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getElement()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getContainerView()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getContainer()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_Container();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getIconPath()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EAttribute getSelectionWizardDescription_IconPath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getWindowTitle
     * <em>Window Title</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Window Title</em>'.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getWindowTitle()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EAttribute getSelectionWizardDescription_WindowTitle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.SelectionWizardDescription#getWindowImagePath
     * <em>Window Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Window Image Path</em>'.
     * @see org.eclipse.sirius.description.tool.SelectionWizardDescription#getWindowImagePath()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EAttribute getSelectionWizardDescription_WindowImagePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription
     * <em>Pane Based Selection Wizard Description</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Pane Based Selection Wizard Description</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription
     * @generated
     */
    EClass getPaneBasedSelectionWizardDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getInitialOperation()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getElement()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getContainerView()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getContainer()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_Container();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getIconPath()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_IconPath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getWindowTitle
     * <em>Window Title</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Window Title</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getWindowTitle()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_WindowTitle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getWindowImagePath
     * <em>Window Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Window Image Path</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getWindowImagePath()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_WindowImagePath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getMessage()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_Message();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getChoiceOfValuesMessage
     * <em>Choice Of Values Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Choice Of Values Message</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getChoiceOfValuesMessage()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Candidates Expression</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getCandidatesExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#isTree
     * <em>Tree</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tree</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#isTree()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_Tree();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getRootExpression
     * <em>Root Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Root Expression</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getRootExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_RootExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getChildrenExpression
     * <em>Children Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Children Expression</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getChildrenExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_ChildrenExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getSelectedValuesMessage
     * <em>Selected Values Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Selected Values Message</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getSelectedValuesMessage()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_SelectedValuesMessage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getPreSelectedCandidatesExpression
     * <em>Pre Selected Candidates Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Pre Selected Candidates Expression</em>'.
     * @see org.eclipse.sirius.description.tool.PaneBasedSelectionWizardDescription#getPreSelectedCandidatesExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription
     * <em>Representation Creation Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Creation Description</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription
     * @generated
     */
    EClass getRepresentationCreationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription#getTitleExpression
     * <em>Title Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription#getTitleExpression()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EAttribute getRepresentationCreationDescription_TitleExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription#getBrowseExpression
     * <em>Browse Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Browse Expression</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription#getBrowseExpression()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EAttribute getRepresentationCreationDescription_BrowseExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription#getRepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Representation Description</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription#getRepresentationDescription()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_RepresentationDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription#getInitialOperation()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription#getContainerViewVariable
     * <em>Container View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription#getContainerViewVariable()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_ContainerViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationCreationDescription#getRepresentationNameVariable
     * <em>Representation Name Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Representation Name Variable</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationCreationDescription#getRepresentationNameVariable()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_RepresentationNameVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription
     * <em>Representation Navigation Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Navigation Description</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription
     * @generated
     */
    EClass getRepresentationNavigationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getBrowseExpression
     * <em>Browse Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Browse Expression</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getBrowseExpression()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EAttribute getRepresentationNavigationDescription_BrowseExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getNavigationNameExpression
     * <em>Navigation Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Navigation Name Expression</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getNavigationNameExpression()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EAttribute getRepresentationNavigationDescription_NavigationNameExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getRepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Representation Description</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getRepresentationDescription()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_RepresentationDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getContainerViewVariable
     * <em>Container View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getContainerViewVariable()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_ContainerViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getContainerVariable
     * <em>Container Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container Variable</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getContainerVariable()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_ContainerVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getRepresentationNameVariable
     * <em>Representation Name Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Representation Name Variable</em>'.
     * @see org.eclipse.sirius.description.tool.RepresentationNavigationDescription#getRepresentationNameVariable()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_RepresentationNameVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.MenuItemOrRef
     * <em>Menu Item Or Ref</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Menu Item Or Ref</em>'.
     * @see org.eclipse.sirius.description.tool.MenuItemOrRef
     * @generated
     */
    EClass getMenuItemOrRef();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.MenuItemDescription
     * <em>Menu Item Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Menu Item Description</em>'.
     * @see org.eclipse.sirius.description.tool.MenuItemDescription
     * @generated
     */
    EClass getMenuItemDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.MenuItemDescription#getIcon
     * <em>Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.description.tool.MenuItemDescription#getIcon()
     * @see #getMenuItemDescription()
     * @generated
     */
    EAttribute getMenuItemDescription_Icon();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.MenuItemDescriptionReference
     * <em>Menu Item Description Reference</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Menu Item Description Reference</em>'.
     * @see org.eclipse.sirius.description.tool.MenuItemDescriptionReference
     * @generated
     */
    EClass getMenuItemDescriptionReference();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.MenuItemDescriptionReference#getItem
     * <em>Item</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Item</em>'.
     * @see org.eclipse.sirius.description.tool.MenuItemDescriptionReference#getItem()
     * @see #getMenuItemDescriptionReference()
     * @generated
     */
    EReference getMenuItemDescriptionReference_Item();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.OperationAction
     * <em>Operation Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Operation Action</em>'.
     * @see org.eclipse.sirius.description.tool.OperationAction
     * @generated
     */
    EClass getOperationAction();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.OperationAction#getView
     * <em>View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>View</em>'.
     * @see org.eclipse.sirius.description.tool.OperationAction#getView()
     * @see #getOperationAction()
     * @generated
     */
    EReference getOperationAction_View();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.OperationAction#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.OperationAction#getInitialOperation()
     * @see #getOperationAction()
     * @generated
     */
    EReference getOperationAction_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaAction
     * <em>External Java Action</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>External Java Action</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaAction
     * @generated
     */
    EClass getExternalJavaAction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaAction#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaAction#getId()
     * @see #getExternalJavaAction()
     * @generated
     */
    EAttribute getExternalJavaAction_Id();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaAction#getParameters
     * <em>Parameters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Parameters</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaAction#getParameters()
     * @see #getExternalJavaAction()
     * @generated
     */
    EReference getExternalJavaAction_Parameters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaActionCall
     * <em>External Java Action Call</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>External Java Action Call</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaActionCall
     * @generated
     */
    EClass getExternalJavaActionCall();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaActionCall#getAction
     * <em>Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Action</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaActionCall#getAction()
     * @see #getExternalJavaActionCall()
     * @generated
     */
    EReference getExternalJavaActionCall_Action();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.PopupMenu
     * <em>Popup Menu</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Popup Menu</em>'.
     * @see org.eclipse.sirius.description.tool.PopupMenu
     * @generated
     */
    EClass getPopupMenu();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.PopupMenu#getMenuItemDescription
     * <em>Menu Item Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Menu Item Description</em>'.
     * @see org.eclipse.sirius.description.tool.PopupMenu#getMenuItemDescription()
     * @see #getPopupMenu()
     * @generated
     */
    EReference getPopupMenu_MenuItemDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DirectEditLabel
     * <em>Direct Edit Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Direct Edit Label</em>'.
     * @see org.eclipse.sirius.description.tool.DirectEditLabel
     * @generated
     */
    EClass getDirectEditLabel();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DirectEditLabel#getMask
     * <em>Mask</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Mask</em>'.
     * @see org.eclipse.sirius.description.tool.DirectEditLabel#getMask()
     * @see #getDirectEditLabel()
     * @generated
     */
    EReference getDirectEditLabel_Mask();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.DirectEditLabel#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.DirectEditLabel#getInitialOperation()
     * @see #getDirectEditLabel()
     * @generated
     */
    EReference getDirectEditLabel_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.DirectEditLabel#getInputLabelExpression
     * <em>Input Label Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Input Label Expression</em>'.
     * @see org.eclipse.sirius.description.tool.DirectEditLabel#getInputLabelExpression()
     * @see #getDirectEditLabel()
     * @generated
     */
    EAttribute getDirectEditLabel_InputLabelExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.BehaviorTool
     * <em>Behavior Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Behavior Tool</em>'.
     * @see org.eclipse.sirius.description.tool.BehaviorTool
     * @generated
     */
    EClass getBehaviorTool();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.BehaviorTool#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.description.tool.BehaviorTool#getDomainClass()
     * @see #getBehaviorTool()
     * @generated
     */
    EAttribute getBehaviorTool_DomainClass();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.BehaviorTool#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.BehaviorTool#getInitialOperation()
     * @see #getBehaviorTool()
     * @generated
     */
    EReference getBehaviorTool_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.AbstractVariable
     * <em>Abstract Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Abstract Variable</em>'.
     * @see org.eclipse.sirius.description.tool.AbstractVariable
     * @generated
     */
    EClass getAbstractVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.AbstractVariable#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.tool.AbstractVariable#getName()
     * @see #getAbstractVariable()
     * @generated
     */
    EAttribute getAbstractVariable_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.VariableContainer
     * <em>Variable Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Variable Container</em>'.
     * @see org.eclipse.sirius.description.tool.VariableContainer
     * @generated
     */
    EClass getVariableContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.VariableContainer#getSubVariables
     * <em>Sub Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Variables</em>'.
     * @see org.eclipse.sirius.description.tool.VariableContainer#getSubVariables()
     * @see #getVariableContainer()
     * @generated
     */
    EReference getVariableContainer_SubVariables();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.AcceleoVariable
     * <em>Acceleo Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Acceleo Variable</em>'.
     * @see org.eclipse.sirius.description.tool.AcceleoVariable
     * @generated
     */
    EClass getAcceleoVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.AcceleoVariable#getComputationExpression
     * <em>Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Computation Expression</em>'.
     * @see org.eclipse.sirius.description.tool.AcceleoVariable#getComputationExpression()
     * @see #getAcceleoVariable()
     * @generated
     */
    EAttribute getAcceleoVariable_ComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SubVariable
     * <em>Sub Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Sub Variable</em>'.
     * @see org.eclipse.sirius.description.tool.SubVariable
     * @generated
     */
    EClass getSubVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DialogVariable
     * <em>Dialog Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dialog Variable</em>'.
     * @see org.eclipse.sirius.description.tool.DialogVariable
     * @generated
     */
    EClass getDialogVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.DialogVariable#getDialogPrompt
     * <em>Dialog Prompt</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Dialog Prompt</em>'.
     * @see org.eclipse.sirius.description.tool.DialogVariable#getDialogPrompt()
     * @see #getDialogVariable()
     * @generated
     */
    EAttribute getDialogVariable_DialogPrompt();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SourceEdgeCreationVariable
     * <em>Source Edge Creation Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Source Edge Creation Variable</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.SourceEdgeCreationVariable
     * @generated
     */
    EClass getSourceEdgeCreationVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SourceEdgeViewCreationVariable
     * <em>Source Edge View Creation Variable</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Source Edge View Creation Variable</em>'.
     * @see org.eclipse.sirius.description.tool.SourceEdgeViewCreationVariable
     * @generated
     */
    EClass getSourceEdgeViewCreationVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.TargetEdgeCreationVariable
     * <em>Target Edge Creation Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Target Edge Creation Variable</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.TargetEdgeCreationVariable
     * @generated
     */
    EClass getTargetEdgeCreationVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.TargetEdgeViewCreationVariable
     * <em>Target Edge View Creation Variable</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Target Edge View Creation Variable</em>'.
     * @see org.eclipse.sirius.description.tool.TargetEdgeViewCreationVariable
     * @generated
     */
    EClass getTargetEdgeViewCreationVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ElementDropVariable
     * <em>Element Drop Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Drop Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ElementDropVariable
     * @generated
     */
    EClass getElementDropVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ElementSelectVariable
     * <em>Element Select Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Select Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ElementSelectVariable
     * @generated
     */
    EClass getElementSelectVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ElementVariable
     * <em>Element Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Element Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ElementVariable
     * @generated
     */
    EClass getElementVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ElementViewVariable
     * <em>Element View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ElementViewVariable
     * @generated
     */
    EClass getElementViewVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ElementDeleteVariable
     * <em>Element Delete Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Delete Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ElementDeleteVariable
     * @generated
     */
    EClass getElementDeleteVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ElementDoubleClickVariable
     * <em>Element Double Click Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Double Click Variable</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.ElementDoubleClickVariable
     * @generated
     */
    EClass getElementDoubleClickVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.NodeCreationVariable
     * <em>Node Creation Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Creation Variable</em>'.
     * @see org.eclipse.sirius.description.tool.NodeCreationVariable
     * @generated
     */
    EClass getNodeCreationVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DropContainerVariable
     * <em>Drop Container Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Drop Container Variable</em>'.
     * @see org.eclipse.sirius.description.tool.DropContainerVariable
     * @generated
     */
    EClass getDropContainerVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SelectContainerVariable
     * <em>Select Container Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Select Container Variable</em>'.
     * @see org.eclipse.sirius.description.tool.SelectContainerVariable
     * @generated
     */
    EClass getSelectContainerVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ContainerViewVariable
     * <em>Container View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container View Variable</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerViewVariable
     * @generated
     */
    EClass getContainerViewVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SelectModelElementVariable
     * <em>Select Model Element Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Select Model Element Variable</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.SelectModelElementVariable
     * @generated
     */
    EClass getSelectModelElementVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.EditMaskVariables
     * <em>Edit Mask Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Edit Mask Variables</em>'.
     * @see org.eclipse.sirius.description.tool.EditMaskVariables
     * @generated
     */
    EClass getEditMaskVariables();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.EditMaskVariables#getMask
     * <em>Mask</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Mask</em>'.
     * @see org.eclipse.sirius.description.tool.EditMaskVariables#getMask()
     * @see #getEditMaskVariables()
     * @generated
     */
    EAttribute getEditMaskVariables_Mask();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ContainerModelOperation
     * <em>Container Model Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Model Operation</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerModelOperation
     * @generated
     */
    EClass getContainerModelOperation();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ContainerModelOperation#getSubModelOperations
     * <em>Sub Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Model Operations</em>'.
     * @see org.eclipse.sirius.description.tool.ContainerModelOperation#getSubModelOperations()
     * @see #getContainerModelOperation()
     * @generated
     */
    EReference getContainerModelOperation_SubModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ModelOperation
     * <em>Model Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Operation</em>'.
     * @see org.eclipse.sirius.description.tool.ModelOperation
     * @generated
     */
    EClass getModelOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.InitialNodeCreationOperation
     * <em>Initial Node Creation Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Initial Node Creation Operation</em>'.
     * @see org.eclipse.sirius.description.tool.InitialNodeCreationOperation
     * @generated
     */
    EClass getInitialNodeCreationOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.InitialNodeCreationOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.description.tool.InitialNodeCreationOperation#getFirstModelOperations()
     * @see #getInitialNodeCreationOperation()
     * @generated
     */
    EReference getInitialNodeCreationOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.InitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.description.tool.InitialOperation
     * @generated
     */
    EClass getInitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.InitialOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.description.tool.InitialOperation#getFirstModelOperations()
     * @see #getInitialOperation()
     * @generated
     */
    EReference getInitialOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.InitEdgeCreationOperation
     * <em>Init Edge Creation Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Init Edge Creation Operation</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.InitEdgeCreationOperation
     * @generated
     */
    EClass getInitEdgeCreationOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.InitEdgeCreationOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.description.tool.InitEdgeCreationOperation#getFirstModelOperations()
     * @see #getInitEdgeCreationOperation()
     * @generated
     */
    EReference getInitEdgeCreationOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.InitialContainerDropOperation
     * <em>Initial Container Drop Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Initial Container Drop Operation</em>'.
     * @see org.eclipse.sirius.description.tool.InitialContainerDropOperation
     * @generated
     */
    EClass getInitialContainerDropOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.InitialContainerDropOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.description.tool.InitialContainerDropOperation#getFirstModelOperations()
     * @see #getInitialContainerDropOperation()
     * @generated
     */
    EReference getInitialContainerDropOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.CreateInstance
     * <em>Create Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Create Instance</em>'.
     * @see org.eclipse.sirius.description.tool.CreateInstance
     * @generated
     */
    EClass getCreateInstance();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateInstance#getTypeName
     * <em>Type Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Type Name</em>'.
     * @see org.eclipse.sirius.description.tool.CreateInstance#getTypeName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_TypeName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateInstance#getReferenceName
     * <em>Reference Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Reference Name</em>'.
     * @see org.eclipse.sirius.description.tool.CreateInstance#getReferenceName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_ReferenceName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateInstance#getVariableName
     * <em>Variable Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see org.eclipse.sirius.description.tool.CreateInstance#getVariableName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_VariableName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ChangeContext
     * <em>Change Context</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Change Context</em>'.
     * @see org.eclipse.sirius.description.tool.ChangeContext
     * @generated
     */
    EClass getChangeContext();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ChangeContext#getBrowseExpression
     * <em>Browse Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Browse Expression</em>'.
     * @see org.eclipse.sirius.description.tool.ChangeContext#getBrowseExpression()
     * @see #getChangeContext()
     * @generated
     */
    EAttribute getChangeContext_BrowseExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SetValue
     * <em>Set Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Set Value</em>'.
     * @see org.eclipse.sirius.description.tool.SetValue
     * @generated
     */
    EClass getSetValue();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.SetValue#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.description.tool.SetValue#getFeatureName()
     * @see #getSetValue()
     * @generated
     */
    EAttribute getSetValue_FeatureName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.SetValue#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.description.tool.SetValue#getValueExpression()
     * @see #getSetValue()
     * @generated
     */
    EAttribute getSetValue_ValueExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SetObject
     * <em>Set Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Set Object</em>'.
     * @see org.eclipse.sirius.description.tool.SetObject
     * @generated
     */
    EClass getSetObject();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.SetObject#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.description.tool.SetObject#getFeatureName()
     * @see #getSetObject()
     * @generated
     */
    EAttribute getSetObject_FeatureName();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.SetObject#getObject
     * <em>Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Object</em>'.
     * @see org.eclipse.sirius.description.tool.SetObject#getObject()
     * @see #getSetObject()
     * @generated
     */
    EReference getSetObject_Object();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.Unset <em>Unset</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Unset</em>'.
     * @see org.eclipse.sirius.description.tool.Unset
     * @generated
     */
    EClass getUnset();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.Unset#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.description.tool.Unset#getFeatureName()
     * @see #getUnset()
     * @generated
     */
    EAttribute getUnset_FeatureName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.Unset#getElementExpression
     * <em>Element Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Element Expression</em>'.
     * @see org.eclipse.sirius.description.tool.Unset#getElementExpression()
     * @see #getUnset()
     * @generated
     */
    EAttribute getUnset_ElementExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.MoveElement
     * <em>Move Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Move Element</em>'.
     * @see org.eclipse.sirius.description.tool.MoveElement
     * @generated
     */
    EClass getMoveElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.MoveElement#getNewContainerExpression
     * <em>New Container Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>New Container Expression</em>'.
     * @see org.eclipse.sirius.description.tool.MoveElement#getNewContainerExpression()
     * @see #getMoveElement()
     * @generated
     */
    EAttribute getMoveElement_NewContainerExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.MoveElement#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.description.tool.MoveElement#getFeatureName()
     * @see #getMoveElement()
     * @generated
     */
    EAttribute getMoveElement_FeatureName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.RemoveElement
     * <em>Remove Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Remove Element</em>'.
     * @see org.eclipse.sirius.description.tool.RemoveElement
     * @generated
     */
    EClass getRemoveElement();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.For <em>For</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>For</em>'.
     * @see org.eclipse.sirius.description.tool.For
     * @generated
     */
    EClass getFor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.For#getExpression
     * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see org.eclipse.sirius.description.tool.For#getExpression()
     * @see #getFor()
     * @generated
     */
    EAttribute getFor_Expression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.For#getIteratorName
     * <em>Iterator Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Iterator Name</em>'.
     * @see org.eclipse.sirius.description.tool.For#getIteratorName()
     * @see #getFor()
     * @generated
     */
    EAttribute getFor_IteratorName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.CreateView
     * <em>Create View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Create View</em>'.
     * @see org.eclipse.sirius.description.tool.CreateView
     * @generated
     */
    EClass getCreateView();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.CreateView#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.description.tool.CreateView#getMapping()
     * @see #getCreateView()
     * @generated
     */
    EReference getCreateView_Mapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateView#getContainerViewExpression
     * <em>Container View Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Container View Expression</em>'.
     * @see org.eclipse.sirius.description.tool.CreateView#getContainerViewExpression()
     * @see #getCreateView()
     * @generated
     */
    EAttribute getCreateView_ContainerViewExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateView#getVariableName
     * <em>Variable Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see org.eclipse.sirius.description.tool.CreateView#getVariableName()
     * @see #getCreateView()
     * @generated
     */
    EAttribute getCreateView_VariableName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.CreateEdgeView
     * <em>Create Edge View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Create Edge View</em>'.
     * @see org.eclipse.sirius.description.tool.CreateEdgeView
     * @generated
     */
    EClass getCreateEdgeView();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateEdgeView#getSourceExpression
     * <em>Source Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Source Expression</em>'.
     * @see org.eclipse.sirius.description.tool.CreateEdgeView#getSourceExpression()
     * @see #getCreateEdgeView()
     * @generated
     */
    EAttribute getCreateEdgeView_SourceExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.CreateEdgeView#getTargetExpression
     * <em>Target Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Target Expression</em>'.
     * @see org.eclipse.sirius.description.tool.CreateEdgeView#getTargetExpression()
     * @see #getCreateEdgeView()
     * @generated
     */
    EAttribute getCreateEdgeView_TargetExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.If <em>If</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>If</em>'.
     * @see org.eclipse.sirius.description.tool.If
     * @generated
     */
    EClass getIf();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.If#getConditionExpression
     * <em>Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Condition Expression</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.If#getConditionExpression()
     * @see #getIf()
     * @generated
     */
    EAttribute getIf_ConditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DeleteView
     * <em>Delete View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Delete View</em>'.
     * @see org.eclipse.sirius.description.tool.DeleteView
     * @generated
     */
    EClass getDeleteView();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.Navigation
     * <em>Navigation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Navigation</em>'.
     * @see org.eclipse.sirius.description.tool.Navigation
     * @generated
     */
    EClass getNavigation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.Navigation#isCreateIfNotExistent
     * <em>Create If Not Existent</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Create If Not Existent</em>'.
     * @see org.eclipse.sirius.description.tool.Navigation#isCreateIfNotExistent()
     * @see #getNavigation()
     * @generated
     */
    EAttribute getNavigation_CreateIfNotExistent();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.Navigation#getDiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.description.tool.Navigation#getDiagramDescription()
     * @see #getNavigation()
     * @generated
     */
    EReference getNavigation_DiagramDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.NameVariable
     * <em>Name Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Name Variable</em>'.
     * @see org.eclipse.sirius.description.tool.NameVariable
     * @generated
     */
    EClass getNameVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaActionParameter
     * <em>External Java Action Parameter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>External Java Action Parameter</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaActionParameter
     * @generated
     */
    EClass getExternalJavaActionParameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaActionParameter#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaActionParameter#getName()
     * @see #getExternalJavaActionParameter()
     * @generated
     */
    EAttribute getExternalJavaActionParameter_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ExternalJavaActionParameter#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.description.tool.ExternalJavaActionParameter#getValue()
     * @see #getExternalJavaActionParameter()
     * @generated
     */
    EAttribute getExternalJavaActionParameter_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DiagramCreationDescription
     * <em>Diagram Creation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Diagram Creation Description</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.DiagramCreationDescription
     * @generated
     */
    EClass getDiagramCreationDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.DiagramCreationDescription#getDiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.description.tool.DiagramCreationDescription#getDiagramDescription()
     * @see #getDiagramCreationDescription()
     * @generated
     */
    EReference getDiagramCreationDescription_DiagramDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.DiagramNavigationDescription
     * <em>Diagram Navigation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Diagram Navigation Description</em>'.
     * @see org.eclipse.sirius.description.tool.DiagramNavigationDescription
     * @generated
     */
    EClass getDiagramNavigationDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.description.tool.DiagramNavigationDescription#getDiagramDescription
     * <em>Diagram Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.description.tool.DiagramNavigationDescription#getDiagramDescription()
     * @see #getDiagramNavigationDescription()
     * @generated
     */
    EReference getDiagramNavigationDescription_DiagramDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.ToolFilterDescription
     * <em>Filter Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Filter Description</em>'.
     * @see org.eclipse.sirius.description.tool.ToolFilterDescription
     * @generated
     */
    EClass getToolFilterDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ToolFilterDescription#getPrecondition
     * <em>Precondition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Precondition</em>'.
     * @see org.eclipse.sirius.description.tool.ToolFilterDescription#getPrecondition()
     * @see #getToolFilterDescription()
     * @generated
     */
    EAttribute getToolFilterDescription_Precondition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.ToolFilterDescription#getElementsToListen
     * <em>Elements To Listen</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Elements To Listen</em>'.
     * @see org.eclipse.sirius.description.tool.ToolFilterDescription#getElementsToListen()
     * @see #getToolFilterDescription()
     * @generated
     */
    EAttribute getToolFilterDescription_ElementsToListen();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.ToolFilterDescription#getListeners
     * <em>Listeners</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Listeners</em>'.
     * @see org.eclipse.sirius.description.tool.ToolFilterDescription#getListeners()
     * @see #getToolFilterDescription()
     * @generated
     */
    EReference getToolFilterDescription_Listeners();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.FeatureChangeListener
     * <em>Feature Change Listener</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Feature Change Listener</em>'.
     * @see org.eclipse.sirius.description.tool.FeatureChangeListener
     * @generated
     */
    EClass getFeatureChangeListener();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.FeatureChangeListener#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.description.tool.FeatureChangeListener#getDomainClass()
     * @see #getFeatureChangeListener()
     * @generated
     */
    EAttribute getFeatureChangeListener_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.FeatureChangeListener#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.description.tool.FeatureChangeListener#getFeatureName()
     * @see #getFeatureChangeListener()
     * @generated
     */
    EAttribute getFeatureChangeListener_FeatureName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.Case <em>Case</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Case</em>'.
     * @see org.eclipse.sirius.description.tool.Case
     * @generated
     */
    EClass getCase();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.description.tool.Case#getConditionExpression
     * <em>Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Condition Expression</em>
     *         '.
     * @see org.eclipse.sirius.description.tool.Case#getConditionExpression()
     * @see #getCase()
     * @generated
     */
    EAttribute getCase_ConditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.SwitchChild
     * <em>Switch Child</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Switch Child</em>'.
     * @see org.eclipse.sirius.description.tool.SwitchChild
     * @generated
     */
    EClass getSwitchChild();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.SwitchChild#getSubModelOperations
     * <em>Sub Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Model Operations</em>'.
     * @see org.eclipse.sirius.description.tool.SwitchChild#getSubModelOperations()
     * @see #getSwitchChild()
     * @generated
     */
    EReference getSwitchChild_SubModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.Default <em>Default</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Default</em>'.
     * @see org.eclipse.sirius.description.tool.Default
     * @generated
     */
    EClass getDefault();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.description.tool.Switch <em>Switch</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Switch</em>'.
     * @see org.eclipse.sirius.description.tool.Switch
     * @generated
     */
    EClass getSwitch();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.description.tool.Switch#getCases
     * <em>Cases</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Cases</em>'.
     * @see org.eclipse.sirius.description.tool.Switch#getCases()
     * @see #getSwitch()
     * @generated
     */
    EReference getSwitch_Cases();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.description.tool.Switch#getDefault
     * <em>Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Default</em>'.
     * @see org.eclipse.sirius.description.tool.Switch#getDefault()
     * @see #getSwitch()
     * @generated
     */
    EReference getSwitch_Default();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.tool.ReconnectionKind
     * <em>Reconnection Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for enum '<em>Reconnection Kind</em>'.
     * @see org.eclipse.sirius.description.tool.ReconnectionKind
     * @generated
     */
    EEnum getReconnectionKind();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.description.tool.DragSource
     * <em>Drag Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Drag Source</em>'.
     * @see org.eclipse.sirius.description.tool.DragSource
     * @generated
     */
    EEnum getDragSource();

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
         * {@link org.eclipse.sirius.description.tool.impl.ToolSectionImpl
         * <em>Section</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ToolSectionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolSection()
         * @generated
         */
        EClass TOOL_SECTION = eINSTANCE.getToolSection();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_SECTION__ICON = eINSTANCE.getToolSection_Icon();

        /**
         * The meta object literal for the '<em><b>Owned Tools</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_SECTION__OWNED_TOOLS = eINSTANCE.getToolSection_OwnedTools();

        /**
         * The meta object literal for the '<em><b>Sub Sections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_SECTION__SUB_SECTIONS = eINSTANCE.getToolSection_SubSections();

        /**
         * The meta object literal for the '<em><b>Popup Menus</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_SECTION__POPUP_MENUS = eINSTANCE.getToolSection_PopupMenus();

        /**
         * The meta object literal for the '<em><b>Reused Tools</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_SECTION__REUSED_TOOLS = eINSTANCE.getToolSection_ReusedTools();

        /**
         * The meta object literal for the '<em><b>Group Extensions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_SECTION__GROUP_EXTENSIONS = eINSTANCE.getToolSection_GroupExtensions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.ToolEntry
         * <em>Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.ToolEntry
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolEntry()
         * @generated
         */
        EClass TOOL_ENTRY = eINSTANCE.getToolEntry();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ToolGroupImpl
         * <em>Group</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ToolGroupImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolGroup()
         * @generated
         */
        EClass TOOL_GROUP = eINSTANCE.getToolGroup();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_GROUP__TOOLS = eINSTANCE.getToolGroup_Tools();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ToolGroupExtensionImpl
         * <em>Group Extension</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ToolGroupExtensionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolGroupExtension()
         * @generated
         */
        EClass TOOL_GROUP_EXTENSION = eINSTANCE.getToolGroupExtension();

        /**
         * The meta object literal for the '<em><b>Group</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_GROUP_EXTENSION__GROUP = eINSTANCE.getToolGroupExtension_Group();

        /**
         * The meta object literal for the '<em><b>Tools</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_GROUP_EXTENSION__TOOLS = eINSTANCE.getToolGroupExtension_Tools();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.AbstractToolDescriptionImpl
         * <em>Abstract Tool Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.AbstractToolDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getAbstractToolDescription()
         * @generated
         */
        EClass ABSTRACT_TOOL_DESCRIPTION = eINSTANCE.getAbstractToolDescription();

        /**
         * The meta object literal for the '<em><b>Precondition</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_TOOL_DESCRIPTION__PRECONDITION = eINSTANCE.getAbstractToolDescription_Precondition();

        /**
         * The meta object literal for the '<em><b>Force Refresh</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH = eINSTANCE.getAbstractToolDescription_ForceRefresh();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_TOOL_DESCRIPTION__FILTERS = eINSTANCE.getAbstractToolDescription_Filters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.MappingBasedToolDescriptionImpl
         * <em>Mapping Based Tool Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.MappingBasedToolDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMappingBasedToolDescription()
         * @generated
         */
        EClass MAPPING_BASED_TOOL_DESCRIPTION = eINSTANCE.getMappingBasedToolDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ToolDescriptionImpl
         * <em>Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ToolDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolDescription()
         * @generated
         */
        EClass TOOL_DESCRIPTION = eINSTANCE.getToolDescription();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_DESCRIPTION__ICON_PATH = eINSTANCE.getToolDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_DESCRIPTION__ELEMENT = eINSTANCE.getToolDescription_Element();

        /**
         * The meta object literal for the '<em><b>Element View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_DESCRIPTION__ELEMENT_VIEW = eINSTANCE.getToolDescription_ElementView();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getToolDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.NodeCreationDescriptionImpl
         * <em>Node Creation Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.NodeCreationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNodeCreationDescription()
         * @generated
         */
        EClass NODE_CREATION_DESCRIPTION = eINSTANCE.getNodeCreationDescription();

        /**
         * The meta object literal for the '<em><b>Node Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__NODE_MAPPINGS = eINSTANCE.getNodeCreationDescription_NodeMappings();

        /**
         * The meta object literal for the '<em><b>Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__VARIABLE = eINSTANCE.getNodeCreationDescription_Variable();

        /**
         * The meta object literal for the '<em><b>View Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__VIEW_VARIABLE = eINSTANCE.getNodeCreationDescription_ViewVariable();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getNodeCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_CREATION_DESCRIPTION__ICON_PATH = eINSTANCE.getNodeCreationDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Extra Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_CREATION_DESCRIPTION__EXTRA_MAPPINGS = eINSTANCE.getNodeCreationDescription_ExtraMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.EdgeCreationDescriptionImpl
         * <em>Edge Creation Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.EdgeCreationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getEdgeCreationDescription()
         * @generated
         */
        EClass EDGE_CREATION_DESCRIPTION = eINSTANCE.getEdgeCreationDescription();

        /**
         * The meta object literal for the '<em><b>Edge Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__EDGE_MAPPINGS = eINSTANCE.getEdgeCreationDescription_EdgeMappings();

        /**
         * The meta object literal for the '<em><b>Source Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__SOURCE_VARIABLE = eINSTANCE.getEdgeCreationDescription_SourceVariable();

        /**
         * The meta object literal for the '<em><b>Target Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__TARGET_VARIABLE = eINSTANCE.getEdgeCreationDescription_TargetVariable();

        /**
         * The meta object literal for the '<em><b>Source View Variable</b></em>
         * ' containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__SOURCE_VIEW_VARIABLE = eINSTANCE.getEdgeCreationDescription_SourceViewVariable();

        /**
         * The meta object literal for the '<em><b>Target View Variable</b></em>
         * ' containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__TARGET_VIEW_VARIABLE = eINSTANCE.getEdgeCreationDescription_TargetViewVariable();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getEdgeCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_CREATION_DESCRIPTION__ICON_PATH = eINSTANCE.getEdgeCreationDescription_IconPath();

        /**
         * The meta object literal for the '
         * <em><b>Extra Source Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__EXTRA_SOURCE_MAPPINGS = eINSTANCE.getEdgeCreationDescription_ExtraSourceMappings();

        /**
         * The meta object literal for the '
         * <em><b>Extra Target Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_CREATION_DESCRIPTION__EXTRA_TARGET_MAPPINGS = eINSTANCE.getEdgeCreationDescription_ExtraTargetMappings();

        /**
         * The meta object literal for the '
         * <em><b>Connection Start Precondition</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_CREATION_DESCRIPTION__CONNECTION_START_PRECONDITION = eINSTANCE.getEdgeCreationDescription_ConnectionStartPrecondition();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ContainerCreationDescriptionImpl
         * <em>Container Creation Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ContainerCreationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerCreationDescription()
         * @generated
         */
        EClass CONTAINER_CREATION_DESCRIPTION = eINSTANCE.getContainerCreationDescription();

        /**
         * The meta object literal for the '<em><b>Container Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__CONTAINER_MAPPINGS = eINSTANCE.getContainerCreationDescription_ContainerMappings();

        /**
         * The meta object literal for the '<em><b>Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__VARIABLE = eINSTANCE.getContainerCreationDescription_Variable();

        /**
         * The meta object literal for the '<em><b>View Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__VIEW_VARIABLE = eINSTANCE.getContainerCreationDescription_ViewVariable();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getContainerCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTAINER_CREATION_DESCRIPTION__ICON_PATH = eINSTANCE.getContainerCreationDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Extra Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_CREATION_DESCRIPTION__EXTRA_MAPPINGS = eINSTANCE.getContainerCreationDescription_ExtraMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ContainerDropDescriptionImpl
         * <em>Container Drop Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ContainerDropDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerDropDescription()
         * @generated
         */
        EClass CONTAINER_DROP_DESCRIPTION = eINSTANCE.getContainerDropDescription();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__MAPPINGS = eINSTANCE.getContainerDropDescription_Mappings();

        /**
         * The meta object literal for the '<em><b>Old Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__OLD_CONTAINER = eINSTANCE.getContainerDropDescription_OldContainer();

        /**
         * The meta object literal for the '<em><b>New Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__NEW_CONTAINER = eINSTANCE.getContainerDropDescription_NewContainer();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__ELEMENT = eINSTANCE.getContainerDropDescription_Element();

        /**
         * The meta object literal for the '<em><b>New View Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__NEW_VIEW_CONTAINER = eINSTANCE.getContainerDropDescription_NewViewContainer();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DROP_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getContainerDropDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Drag Source</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTAINER_DROP_DESCRIPTION__DRAG_SOURCE = eINSTANCE.getContainerDropDescription_DragSource();

        /**
         * The meta object literal for the '<em><b>Move Edges</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTAINER_DROP_DESCRIPTION__MOVE_EDGES = eINSTANCE.getContainerDropDescription_MoveEdges();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.PasteDescriptionImpl
         * <em>Paste Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.PasteDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getPasteDescription()
         * @generated
         */
        EClass PASTE_DESCRIPTION = eINSTANCE.getPasteDescription();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__CONTAINER = eINSTANCE.getPasteDescription_Container();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__CONTAINER_VIEW = eINSTANCE.getPasteDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Copied View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__COPIED_VIEW = eINSTANCE.getPasteDescription_CopiedView();

        /**
         * The meta object literal for the '<em><b>Copied Element</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__COPIED_ELEMENT = eINSTANCE.getPasteDescription_CopiedElement();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getPasteDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DeleteElementDescriptionImpl
         * <em>Delete Element Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DeleteElementDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteElementDescription()
         * @generated
         */
        EClass DELETE_ELEMENT_DESCRIPTION = eINSTANCE.getDeleteElementDescription();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__ELEMENT = eINSTANCE.getDeleteElementDescription_Element();

        /**
         * The meta object literal for the '<em><b>Element View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__ELEMENT_VIEW = eINSTANCE.getDeleteElementDescription_ElementView();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__CONTAINER_VIEW = eINSTANCE.getDeleteElementDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getDeleteElementDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Hook</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_ELEMENT_DESCRIPTION__HOOK = eINSTANCE.getDeleteElementDescription_Hook();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DoubleClickDescriptionImpl
         * <em>Double Click Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DoubleClickDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDoubleClickDescription()
         * @generated
         */
        EClass DOUBLE_CLICK_DESCRIPTION = eINSTANCE.getDoubleClickDescription();

        /**
         * The meta object literal for the '<em><b>Mappings</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__MAPPINGS = eINSTANCE.getDoubleClickDescription_Mappings();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__ELEMENT = eINSTANCE.getDoubleClickDescription_Element();

        /**
         * The meta object literal for the '<em><b>Element View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__ELEMENT_VIEW = eINSTANCE.getDoubleClickDescription_ElementView();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DOUBLE_CLICK_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getDoubleClickDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DeleteHookImpl
         * <em>Delete Hook</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DeleteHookImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteHook()
         * @generated
         */
        EClass DELETE_HOOK = eINSTANCE.getDeleteHook();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DELETE_HOOK__ID = eINSTANCE.getDeleteHook_Id();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_HOOK__PARAMETERS = eINSTANCE.getDeleteHook_Parameters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DeleteHookParameterImpl
         * <em>Delete Hook Parameter</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DeleteHookParameterImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteHookParameter()
         * @generated
         */
        EClass DELETE_HOOK_PARAMETER = eINSTANCE.getDeleteHookParameter();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DELETE_HOOK_PARAMETER__NAME = eINSTANCE.getDeleteHookParameter_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DELETE_HOOK_PARAMETER__VALUE = eINSTANCE.getDeleteHookParameter_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ReconnectEdgeDescriptionImpl
         * <em>Reconnect Edge Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ReconnectEdgeDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getReconnectEdgeDescription()
         * @generated
         */
        EClass RECONNECT_EDGE_DESCRIPTION = eINSTANCE.getReconnectEdgeDescription();

        /**
         * The meta object literal for the '<em><b>Reconnection Kind</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RECONNECT_EDGE_DESCRIPTION__RECONNECTION_KIND = eINSTANCE.getReconnectEdgeDescription_ReconnectionKind();

        /**
         * The meta object literal for the '<em><b>Source</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__SOURCE = eINSTANCE.getReconnectEdgeDescription_Source();

        /**
         * The meta object literal for the '<em><b>Target</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__TARGET = eINSTANCE.getReconnectEdgeDescription_Target();

        /**
         * The meta object literal for the '<em><b>Source View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__SOURCE_VIEW = eINSTANCE.getReconnectEdgeDescription_SourceView();

        /**
         * The meta object literal for the '<em><b>Target View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__TARGET_VIEW = eINSTANCE.getReconnectEdgeDescription_TargetView();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__ELEMENT = eINSTANCE.getReconnectEdgeDescription_Element();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getReconnectEdgeDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Edge View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RECONNECT_EDGE_DESCRIPTION__EDGE_VIEW = eINSTANCE.getReconnectEdgeDescription_EdgeView();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.RequestDescriptionImpl
         * <em>Request Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.RequestDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRequestDescription()
         * @generated
         */
        EClass REQUEST_DESCRIPTION = eINSTANCE.getRequestDescription();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REQUEST_DESCRIPTION__TYPE = eINSTANCE.getRequestDescription_Type();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SelectionWizardDescriptionImpl
         * <em>Selection Wizard Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SelectionWizardDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSelectionWizardDescription()
         * @generated
         */
        EClass SELECTION_WIZARD_DESCRIPTION = eINSTANCE.getSelectionWizardDescription();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getSelectionWizardDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__ELEMENT = eINSTANCE.getSelectionWizardDescription_Element();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = eINSTANCE.getSelectionWizardDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__CONTAINER = eINSTANCE.getSelectionWizardDescription_Container();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_WIZARD_DESCRIPTION__ICON_PATH = eINSTANCE.getSelectionWizardDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Window Title</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = eINSTANCE.getSelectionWizardDescription_WindowTitle();

        /**
         * The meta object literal for the '<em><b>Window Image Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = eINSTANCE.getSelectionWizardDescription_WindowImagePath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
         * <em>Pane Based Selection Wizard Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getPaneBasedSelectionWizardDescription()
         * @generated
         */
        EClass PANE_BASED_SELECTION_WIZARD_DESCRIPTION = eINSTANCE.getPaneBasedSelectionWizardDescription();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getPaneBasedSelectionWizardDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT = eINSTANCE.getPaneBasedSelectionWizardDescription_Element();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = eINSTANCE.getPaneBasedSelectionWizardDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER = eINSTANCE.getPaneBasedSelectionWizardDescription_Container();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH = eINSTANCE.getPaneBasedSelectionWizardDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Window Title</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = eINSTANCE.getPaneBasedSelectionWizardDescription_WindowTitle();

        /**
         * The meta object literal for the '<em><b>Window Image Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = eINSTANCE.getPaneBasedSelectionWizardDescription_WindowImagePath();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE = eINSTANCE.getPaneBasedSelectionWizardDescription_Message();

        /**
         * The meta object literal for the '
         * <em><b>Choice Of Values Message</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE = eINSTANCE.getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION = eINSTANCE.getPaneBasedSelectionWizardDescription_CandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Tree</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE = eINSTANCE.getPaneBasedSelectionWizardDescription_Tree();

        /**
         * The meta object literal for the '<em><b>Root Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION = eINSTANCE.getPaneBasedSelectionWizardDescription_RootExpression();

        /**
         * The meta object literal for the '<em><b>Children Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION = eINSTANCE.getPaneBasedSelectionWizardDescription_ChildrenExpression();

        /**
         * The meta object literal for the '
         * <em><b>Selected Values Message</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE = eINSTANCE.getPaneBasedSelectionWizardDescription_SelectedValuesMessage();

        /**
         * The meta object literal for the '
         * <em><b>Pre Selected Candidates Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION = eINSTANCE.getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.RepresentationCreationDescriptionImpl
         * <em>Representation Creation Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.RepresentationCreationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRepresentationCreationDescription()
         * @generated
         */
        EClass REPRESENTATION_CREATION_DESCRIPTION = eINSTANCE.getRepresentationCreationDescription();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION = eINSTANCE.getRepresentationCreationDescription_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Browse Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION = eINSTANCE.getRepresentationCreationDescription_BrowseExpression();

        /**
         * The meta object literal for the '
         * <em><b>Representation Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = eINSTANCE.getRepresentationCreationDescription_RepresentationDescription();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION = eINSTANCE.getRepresentationCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>Container View Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = eINSTANCE.getRepresentationCreationDescription_ContainerViewVariable();

        /**
         * The meta object literal for the '
         * <em><b>Representation Name Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = eINSTANCE.getRepresentationCreationDescription_RepresentationNameVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.RepresentationNavigationDescriptionImpl
         * <em>Representation Navigation Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.RepresentationNavigationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRepresentationNavigationDescription()
         * @generated
         */
        EClass REPRESENTATION_NAVIGATION_DESCRIPTION = eINSTANCE.getRepresentationNavigationDescription();

        /**
         * The meta object literal for the '<em><b>Browse Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = eINSTANCE.getRepresentationNavigationDescription_BrowseExpression();

        /**
         * The meta object literal for the '
         * <em><b>Navigation Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = eINSTANCE.getRepresentationNavigationDescription_NavigationNameExpression();

        /**
         * The meta object literal for the '
         * <em><b>Representation Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = eINSTANCE.getRepresentationNavigationDescription_RepresentationDescription();

        /**
         * The meta object literal for the '
         * <em><b>Container View Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = eINSTANCE.getRepresentationNavigationDescription_ContainerViewVariable();

        /**
         * The meta object literal for the '<em><b>Container Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = eINSTANCE.getRepresentationNavigationDescription_ContainerVariable();

        /**
         * The meta object literal for the '
         * <em><b>Representation Name Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = eINSTANCE.getRepresentationNavigationDescription_RepresentationNameVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.MenuItemOrRef
         * <em>Menu Item Or Ref</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.MenuItemOrRef
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMenuItemOrRef()
         * @generated
         */
        EClass MENU_ITEM_OR_REF = eINSTANCE.getMenuItemOrRef();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.MenuItemDescriptionImpl
         * <em>Menu Item Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.MenuItemDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMenuItemDescription()
         * @generated
         */
        EClass MENU_ITEM_DESCRIPTION = eINSTANCE.getMenuItemDescription();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MENU_ITEM_DESCRIPTION__ICON = eINSTANCE.getMenuItemDescription_Icon();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.MenuItemDescriptionReferenceImpl
         * <em>Menu Item Description Reference</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.MenuItemDescriptionReferenceImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMenuItemDescriptionReference()
         * @generated
         */
        EClass MENU_ITEM_DESCRIPTION_REFERENCE = eINSTANCE.getMenuItemDescriptionReference();

        /**
         * The meta object literal for the '<em><b>Item</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MENU_ITEM_DESCRIPTION_REFERENCE__ITEM = eINSTANCE.getMenuItemDescriptionReference_Item();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.OperationActionImpl
         * <em>Operation Action</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.OperationActionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getOperationAction()
         * @generated
         */
        EClass OPERATION_ACTION = eINSTANCE.getOperationAction();

        /**
         * The meta object literal for the '<em><b>View</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference OPERATION_ACTION__VIEW = eINSTANCE.getOperationAction_View();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference OPERATION_ACTION__INITIAL_OPERATION = eINSTANCE.getOperationAction_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ExternalJavaActionImpl
         * <em>External Java Action</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ExternalJavaActionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getExternalJavaAction()
         * @generated
         */
        EClass EXTERNAL_JAVA_ACTION = eINSTANCE.getExternalJavaAction();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EXTERNAL_JAVA_ACTION__ID = eINSTANCE.getExternalJavaAction_Id();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EXTERNAL_JAVA_ACTION__PARAMETERS = eINSTANCE.getExternalJavaAction_Parameters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ExternalJavaActionCallImpl
         * <em>External Java Action Call</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ExternalJavaActionCallImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getExternalJavaActionCall()
         * @generated
         */
        EClass EXTERNAL_JAVA_ACTION_CALL = eINSTANCE.getExternalJavaActionCall();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EXTERNAL_JAVA_ACTION_CALL__ACTION = eINSTANCE.getExternalJavaActionCall_Action();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.PopupMenuImpl
         * <em>Popup Menu</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.PopupMenuImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getPopupMenu()
         * @generated
         */
        EClass POPUP_MENU = eINSTANCE.getPopupMenu();

        /**
         * The meta object literal for the '
         * <em><b>Menu Item Description</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference POPUP_MENU__MENU_ITEM_DESCRIPTION = eINSTANCE.getPopupMenu_MenuItemDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DirectEditLabelImpl
         * <em>Direct Edit Label</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DirectEditLabelImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDirectEditLabel()
         * @generated
         */
        EClass DIRECT_EDIT_LABEL = eINSTANCE.getDirectEditLabel();

        /**
         * The meta object literal for the '<em><b>Mask</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIRECT_EDIT_LABEL__MASK = eINSTANCE.getDirectEditLabel_Mask();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DIRECT_EDIT_LABEL__INITIAL_OPERATION = eINSTANCE.getDirectEditLabel_InitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>Input Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIRECT_EDIT_LABEL__INPUT_LABEL_EXPRESSION = eINSTANCE.getDirectEditLabel_InputLabelExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.BehaviorToolImpl
         * <em>Behavior Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.BehaviorToolImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getBehaviorTool()
         * @generated
         */
        EClass BEHAVIOR_TOOL = eINSTANCE.getBehaviorTool();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BEHAVIOR_TOOL__DOMAIN_CLASS = eINSTANCE.getBehaviorTool_DomainClass();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BEHAVIOR_TOOL__INITIAL_OPERATION = eINSTANCE.getBehaviorTool_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.AbstractVariableImpl
         * <em>Abstract Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.AbstractVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getAbstractVariable()
         * @generated
         */
        EClass ABSTRACT_VARIABLE = eINSTANCE.getAbstractVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_VARIABLE__NAME = eINSTANCE.getAbstractVariable_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.VariableContainerImpl
         * <em>Variable Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.VariableContainerImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getVariableContainer()
         * @generated
         */
        EClass VARIABLE_CONTAINER = eINSTANCE.getVariableContainer();

        /**
         * The meta object literal for the '<em><b>Sub Variables</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABLE_CONTAINER__SUB_VARIABLES = eINSTANCE.getVariableContainer_SubVariables();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.AcceleoVariableImpl
         * <em>Acceleo Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.AcceleoVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getAcceleoVariable()
         * @generated
         */
        EClass ACCELEO_VARIABLE = eINSTANCE.getAcceleoVariable();

        /**
         * The meta object literal for the '
         * <em><b>Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ACCELEO_VARIABLE__COMPUTATION_EXPRESSION = eINSTANCE.getAcceleoVariable_ComputationExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SubVariableImpl
         * <em>Sub Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SubVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSubVariable()
         * @generated
         */
        EClass SUB_VARIABLE = eINSTANCE.getSubVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DialogVariableImpl
         * <em>Dialog Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DialogVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDialogVariable()
         * @generated
         */
        EClass DIALOG_VARIABLE = eINSTANCE.getDialogVariable();

        /**
         * The meta object literal for the '<em><b>Dialog Prompt</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIALOG_VARIABLE__DIALOG_PROMPT = eINSTANCE.getDialogVariable_DialogPrompt();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SourceEdgeCreationVariableImpl
         * <em>Source Edge Creation Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SourceEdgeCreationVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSourceEdgeCreationVariable()
         * @generated
         */
        EClass SOURCE_EDGE_CREATION_VARIABLE = eINSTANCE.getSourceEdgeCreationVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SourceEdgeViewCreationVariableImpl
         * <em>Source Edge View Creation Variable</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SourceEdgeViewCreationVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSourceEdgeViewCreationVariable()
         * @generated
         */
        EClass SOURCE_EDGE_VIEW_CREATION_VARIABLE = eINSTANCE.getSourceEdgeViewCreationVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.TargetEdgeCreationVariableImpl
         * <em>Target Edge Creation Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.TargetEdgeCreationVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getTargetEdgeCreationVariable()
         * @generated
         */
        EClass TARGET_EDGE_CREATION_VARIABLE = eINSTANCE.getTargetEdgeCreationVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.TargetEdgeViewCreationVariableImpl
         * <em>Target Edge View Creation Variable</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.TargetEdgeViewCreationVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getTargetEdgeViewCreationVariable()
         * @generated
         */
        EClass TARGET_EDGE_VIEW_CREATION_VARIABLE = eINSTANCE.getTargetEdgeViewCreationVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ElementDropVariableImpl
         * <em>Element Drop Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ElementDropVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementDropVariable()
         * @generated
         */
        EClass ELEMENT_DROP_VARIABLE = eINSTANCE.getElementDropVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ElementSelectVariableImpl
         * <em>Element Select Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ElementSelectVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementSelectVariable()
         * @generated
         */
        EClass ELEMENT_SELECT_VARIABLE = eINSTANCE.getElementSelectVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ElementVariableImpl
         * <em>Element Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ElementVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementVariable()
         * @generated
         */
        EClass ELEMENT_VARIABLE = eINSTANCE.getElementVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ElementViewVariableImpl
         * <em>Element View Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ElementViewVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementViewVariable()
         * @generated
         */
        EClass ELEMENT_VIEW_VARIABLE = eINSTANCE.getElementViewVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ElementDeleteVariableImpl
         * <em>Element Delete Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ElementDeleteVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementDeleteVariable()
         * @generated
         */
        EClass ELEMENT_DELETE_VARIABLE = eINSTANCE.getElementDeleteVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ElementDoubleClickVariableImpl
         * <em>Element Double Click Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ElementDoubleClickVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getElementDoubleClickVariable()
         * @generated
         */
        EClass ELEMENT_DOUBLE_CLICK_VARIABLE = eINSTANCE.getElementDoubleClickVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.NodeCreationVariableImpl
         * <em>Node Creation Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.NodeCreationVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNodeCreationVariable()
         * @generated
         */
        EClass NODE_CREATION_VARIABLE = eINSTANCE.getNodeCreationVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DropContainerVariableImpl
         * <em>Drop Container Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DropContainerVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDropContainerVariable()
         * @generated
         */
        EClass DROP_CONTAINER_VARIABLE = eINSTANCE.getDropContainerVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SelectContainerVariableImpl
         * <em>Select Container Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SelectContainerVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSelectContainerVariable()
         * @generated
         */
        EClass SELECT_CONTAINER_VARIABLE = eINSTANCE.getSelectContainerVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ContainerViewVariableImpl
         * <em>Container View Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ContainerViewVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerViewVariable()
         * @generated
         */
        EClass CONTAINER_VIEW_VARIABLE = eINSTANCE.getContainerViewVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SelectModelElementVariableImpl
         * <em>Select Model Element Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SelectModelElementVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSelectModelElementVariable()
         * @generated
         */
        EClass SELECT_MODEL_ELEMENT_VARIABLE = eINSTANCE.getSelectModelElementVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.EditMaskVariablesImpl
         * <em>Edit Mask Variables</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.EditMaskVariablesImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getEditMaskVariables()
         * @generated
         */
        EClass EDIT_MASK_VARIABLES = eINSTANCE.getEditMaskVariables();

        /**
         * The meta object literal for the '<em><b>Mask</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDIT_MASK_VARIABLES__MASK = eINSTANCE.getEditMaskVariables_Mask();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ContainerModelOperationImpl
         * <em>Container Model Operation</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ContainerModelOperationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getContainerModelOperation()
         * @generated
         */
        EClass CONTAINER_MODEL_OPERATION = eINSTANCE.getContainerModelOperation();

        /**
         * The meta object literal for the '<em><b>Sub Model Operations</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS = eINSTANCE.getContainerModelOperation_SubModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ModelOperationImpl
         * <em>Model Operation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ModelOperationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getModelOperation()
         * @generated
         */
        EClass MODEL_OPERATION = eINSTANCE.getModelOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.InitialNodeCreationOperationImpl
         * <em>Initial Node Creation Operation</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.InitialNodeCreationOperationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitialNodeCreationOperation()
         * @generated
         */
        EClass INITIAL_NODE_CREATION_OPERATION = eINSTANCE.getInitialNodeCreationOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INITIAL_NODE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS = eINSTANCE.getInitialNodeCreationOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.InitialOperationImpl
         * <em>Initial Operation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.InitialOperationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitialOperation()
         * @generated
         */
        EClass INITIAL_OPERATION = eINSTANCE.getInitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INITIAL_OPERATION__FIRST_MODEL_OPERATIONS = eINSTANCE.getInitialOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.InitEdgeCreationOperationImpl
         * <em>Init Edge Creation Operation</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.InitEdgeCreationOperationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitEdgeCreationOperation()
         * @generated
         */
        EClass INIT_EDGE_CREATION_OPERATION = eINSTANCE.getInitEdgeCreationOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INIT_EDGE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS = eINSTANCE.getInitEdgeCreationOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.InitialContainerDropOperationImpl
         * <em>Initial Container Drop Operation</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.InitialContainerDropOperationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getInitialContainerDropOperation()
         * @generated
         */
        EClass INITIAL_CONTAINER_DROP_OPERATION = eINSTANCE.getInitialContainerDropOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS = eINSTANCE.getInitialContainerDropOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.CreateInstanceImpl
         * <em>Create Instance</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.CreateInstanceImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCreateInstance()
         * @generated
         */
        EClass CREATE_INSTANCE = eINSTANCE.getCreateInstance();

        /**
         * The meta object literal for the '<em><b>Type Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_INSTANCE__TYPE_NAME = eINSTANCE.getCreateInstance_TypeName();

        /**
         * The meta object literal for the '<em><b>Reference Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_INSTANCE__REFERENCE_NAME = eINSTANCE.getCreateInstance_ReferenceName();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_INSTANCE__VARIABLE_NAME = eINSTANCE.getCreateInstance_VariableName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ChangeContextImpl
         * <em>Change Context</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ChangeContextImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getChangeContext()
         * @generated
         */
        EClass CHANGE_CONTEXT = eINSTANCE.getChangeContext();

        /**
         * The meta object literal for the '<em><b>Browse Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHANGE_CONTEXT__BROWSE_EXPRESSION = eINSTANCE.getChangeContext_BrowseExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SetValueImpl
         * <em>Set Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SetValueImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSetValue()
         * @generated
         */
        EClass SET_VALUE = eINSTANCE.getSetValue();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SET_VALUE__FEATURE_NAME = eINSTANCE.getSetValue_FeatureName();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SET_VALUE__VALUE_EXPRESSION = eINSTANCE.getSetValue_ValueExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SetObjectImpl
         * <em>Set Object</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SetObjectImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSetObject()
         * @generated
         */
        EClass SET_OBJECT = eINSTANCE.getSetObject();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SET_OBJECT__FEATURE_NAME = eINSTANCE.getSetObject_FeatureName();

        /**
         * The meta object literal for the '<em><b>Object</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SET_OBJECT__OBJECT = eINSTANCE.getSetObject_Object();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.UnsetImpl
         * <em>Unset</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.UnsetImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getUnset()
         * @generated
         */
        EClass UNSET = eINSTANCE.getUnset();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute UNSET__FEATURE_NAME = eINSTANCE.getUnset_FeatureName();

        /**
         * The meta object literal for the '<em><b>Element Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute UNSET__ELEMENT_EXPRESSION = eINSTANCE.getUnset_ElementExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.MoveElementImpl
         * <em>Move Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.MoveElementImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getMoveElement()
         * @generated
         */
        EClass MOVE_ELEMENT = eINSTANCE.getMoveElement();

        /**
         * The meta object literal for the '
         * <em><b>New Container Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION = eINSTANCE.getMoveElement_NewContainerExpression();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MOVE_ELEMENT__FEATURE_NAME = eINSTANCE.getMoveElement_FeatureName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.RemoveElementImpl
         * <em>Remove Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.RemoveElementImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getRemoveElement()
         * @generated
         */
        EClass REMOVE_ELEMENT = eINSTANCE.getRemoveElement();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ForImpl
         * <em>For</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ForImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getFor()
         * @generated
         */
        EClass FOR = eINSTANCE.getFor();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOR__EXPRESSION = eINSTANCE.getFor_Expression();

        /**
         * The meta object literal for the '<em><b>Iterator Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOR__ITERATOR_NAME = eINSTANCE.getFor_IteratorName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.CreateViewImpl
         * <em>Create View</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.CreateViewImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCreateView()
         * @generated
         */
        EClass CREATE_VIEW = eINSTANCE.getCreateView();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CREATE_VIEW__MAPPING = eINSTANCE.getCreateView_Mapping();

        /**
         * The meta object literal for the '
         * <em><b>Container View Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_VIEW__CONTAINER_VIEW_EXPRESSION = eINSTANCE.getCreateView_ContainerViewExpression();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_VIEW__VARIABLE_NAME = eINSTANCE.getCreateView_VariableName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.CreateEdgeViewImpl
         * <em>Create Edge View</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.CreateEdgeViewImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCreateEdgeView()
         * @generated
         */
        EClass CREATE_EDGE_VIEW = eINSTANCE.getCreateEdgeView();

        /**
         * The meta object literal for the '<em><b>Source Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_EDGE_VIEW__SOURCE_EXPRESSION = eINSTANCE.getCreateEdgeView_SourceExpression();

        /**
         * The meta object literal for the '<em><b>Target Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_EDGE_VIEW__TARGET_EXPRESSION = eINSTANCE.getCreateEdgeView_TargetExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.IfImpl
         * <em>If</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.IfImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getIf()
         * @generated
         */
        EClass IF = eINSTANCE.getIf();

        /**
         * The meta object literal for the '<em><b>Condition Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute IF__CONDITION_EXPRESSION = eINSTANCE.getIf_ConditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DeleteViewImpl
         * <em>Delete View</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DeleteViewImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDeleteView()
         * @generated
         */
        EClass DELETE_VIEW = eINSTANCE.getDeleteView();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.NavigationImpl
         * <em>Navigation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.NavigationImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNavigation()
         * @generated
         */
        EClass NAVIGATION = eINSTANCE.getNavigation();

        /**
         * The meta object literal for the '
         * <em><b>Create If Not Existent</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NAVIGATION__CREATE_IF_NOT_EXISTENT = eINSTANCE.getNavigation_CreateIfNotExistent();

        /**
         * The meta object literal for the '<em><b>Diagram Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NAVIGATION__DIAGRAM_DESCRIPTION = eINSTANCE.getNavigation_DiagramDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.NameVariableImpl
         * <em>Name Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.NameVariableImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getNameVariable()
         * @generated
         */
        EClass NAME_VARIABLE = eINSTANCE.getNameVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ExternalJavaActionParameterImpl
         * <em>External Java Action Parameter</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ExternalJavaActionParameterImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getExternalJavaActionParameter()
         * @generated
         */
        EClass EXTERNAL_JAVA_ACTION_PARAMETER = eINSTANCE.getExternalJavaActionParameter();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EXTERNAL_JAVA_ACTION_PARAMETER__NAME = eINSTANCE.getExternalJavaActionParameter_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EXTERNAL_JAVA_ACTION_PARAMETER__VALUE = eINSTANCE.getExternalJavaActionParameter_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DiagramCreationDescriptionImpl
         * <em>Diagram Creation Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DiagramCreationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDiagramCreationDescription()
         * @generated
         */
        EClass DIAGRAM_CREATION_DESCRIPTION = eINSTANCE.getDiagramCreationDescription();

        /**
         * The meta object literal for the '<em><b>Diagram Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_CREATION_DESCRIPTION__DIAGRAM_DESCRIPTION = eINSTANCE.getDiagramCreationDescription_DiagramDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DiagramNavigationDescriptionImpl
         * <em>Diagram Navigation Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DiagramNavigationDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDiagramNavigationDescription()
         * @generated
         */
        EClass DIAGRAM_NAVIGATION_DESCRIPTION = eINSTANCE.getDiagramNavigationDescription();

        /**
         * The meta object literal for the '<em><b>Diagram Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DIAGRAM_NAVIGATION_DESCRIPTION__DIAGRAM_DESCRIPTION = eINSTANCE.getDiagramNavigationDescription_DiagramDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.ToolFilterDescriptionImpl
         * <em>Filter Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.ToolFilterDescriptionImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getToolFilterDescription()
         * @generated
         */
        EClass TOOL_FILTER_DESCRIPTION = eINSTANCE.getToolFilterDescription();

        /**
         * The meta object literal for the '<em><b>Precondition</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_FILTER_DESCRIPTION__PRECONDITION = eINSTANCE.getToolFilterDescription_Precondition();

        /**
         * The meta object literal for the '<em><b>Elements To Listen</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN = eINSTANCE.getToolFilterDescription_ElementsToListen();

        /**
         * The meta object literal for the '<em><b>Listeners</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_FILTER_DESCRIPTION__LISTENERS = eINSTANCE.getToolFilterDescription_Listeners();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.FeatureChangeListenerImpl
         * <em>Feature Change Listener</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.FeatureChangeListenerImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getFeatureChangeListener()
         * @generated
         */
        EClass FEATURE_CHANGE_LISTENER = eINSTANCE.getFeatureChangeListener();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FEATURE_CHANGE_LISTENER__DOMAIN_CLASS = eINSTANCE.getFeatureChangeListener_DomainClass();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FEATURE_CHANGE_LISTENER__FEATURE_NAME = eINSTANCE.getFeatureChangeListener_FeatureName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.CaseImpl
         * <em>Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.CaseImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getCase()
         * @generated
         */
        EClass CASE = eINSTANCE.getCase();

        /**
         * The meta object literal for the '<em><b>Condition Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CASE__CONDITION_EXPRESSION = eINSTANCE.getCase_ConditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SwitchChildImpl
         * <em>Switch Child</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SwitchChildImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSwitchChild()
         * @generated
         */
        EClass SWITCH_CHILD = eINSTANCE.getSwitchChild();

        /**
         * The meta object literal for the '<em><b>Sub Model Operations</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SWITCH_CHILD__SUB_MODEL_OPERATIONS = eINSTANCE.getSwitchChild_SubModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.DefaultImpl
         * <em>Default</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.DefaultImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDefault()
         * @generated
         */
        EClass DEFAULT = eINSTANCE.getDefault();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.impl.SwitchImpl
         * <em>Switch</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         * 
         * @see org.eclipse.sirius.description.tool.impl.SwitchImpl
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getSwitch()
         * @generated
         */
        EClass SWITCH = eINSTANCE.getSwitch();

        /**
         * The meta object literal for the '<em><b>Cases</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SWITCH__CASES = eINSTANCE.getSwitch_Cases();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SWITCH__DEFAULT = eINSTANCE.getSwitch_Default();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.ReconnectionKind
         * <em>Reconnection Kind</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.ReconnectionKind
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getReconnectionKind()
         * @generated
         */
        EEnum RECONNECTION_KIND = eINSTANCE.getReconnectionKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.description.tool.DragSource
         * <em>Drag Source</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.description.tool.DragSource
         * @see org.eclipse.sirius.description.tool.impl.ToolPackageImpl#getDragSource()
         * @generated
         */
        EEnum DRAG_SOURCE = eINSTANCE.getDragSource();

    }

} // ToolPackage
