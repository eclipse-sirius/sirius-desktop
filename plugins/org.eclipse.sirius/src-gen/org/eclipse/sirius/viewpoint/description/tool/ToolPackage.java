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
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

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
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolFactory
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
    String eNS_URI = "http://www.eclipse.org/sirius/description/tool/1.1.0"; //$NON-NLS-1$

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
    ToolPackage eINSTANCE = org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * <em>Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getToolEntry()
     * @generated
     */
    int TOOL_ENTRY = 0;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl
     * <em>Abstract Tool Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getAbstractToolDescription()
     * @generated
     */
    int ABSTRACT_TOOL_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION = ToolPackage.TOOL_ENTRY__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__NAME = ToolPackage.TOOL_ENTRY__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__LABEL = ToolPackage.TOOL_ENTRY__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__PRECONDITION = ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH = ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__FILTERS = ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '
     * <em>Abstract Tool Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT = ToolPackage.TOOL_ENTRY_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl
     * <em>Mapping Based Tool Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMappingBasedToolDescription()
     * @generated
     */
    int MAPPING_BASED_TOOL_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The number of structural features of the '
     * <em>Mapping Based Tool Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolDescriptionImpl
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getToolDescription()
     * @generated
     */
    int TOOL_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__DOCUMENTATION = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__NAME = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__LABEL = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__PRECONDITION = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__FORCE_REFRESH = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__FILTERS = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ICON_PATH = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ELEMENT = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Element View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__ELEMENT_VIEW = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION__INITIAL_OPERATION = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TOOL_DESCRIPTION_FEATURE_COUNT = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl
     * <em>Paste Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getPasteDescription()
     * @generated
     */
    int PASTE_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__DOCUMENTATION = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__NAME = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__LABEL = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__PRECONDITION = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__FORCE_REFRESH = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__FILTERS = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__CONTAINER = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__CONTAINER_VIEW = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Copied View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__COPIED_VIEW = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Copied Element</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__COPIED_ELEMENT = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION__INITIAL_OPERATION = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Paste Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PASTE_DESCRIPTION_FEATURE_COUNT = ToolPackage.MAPPING_BASED_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectionWizardDescriptionImpl
     * <em>Selection Wizard Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SelectionWizardDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSelectionWizardDescription()
     * @generated
     */
    int SELECTION_WIZARD_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__MULTIPLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__TREE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__MESSAGE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ELEMENT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__CONTAINER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__ICON_PATH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Window Title</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Window Image Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the '
     * <em>Selection Wizard Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECTION_WIZARD_DESCRIPTION_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
     * <em>Pane Based Selection Wizard Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getPaneBasedSelectionWizardDescription()
     * @generated
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Element</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Container View</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Window Title</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Window Image Path</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Choice Of Values Message</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Selected Values Message</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '
     * <em><b>Pre Selected Candidates Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The number of structural features of the '
     * <em>Pane Based Selection Wizard Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PANE_BASED_SELECTION_WIZARD_DESCRIPTION_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl
     * <em>Representation Creation Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getRepresentationCreationDescription()
     * @generated
     */
    int REPRESENTATION_CREATION_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Representation Creation Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl
     * <em>Representation Navigation Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getRepresentationNavigationDescription()
     * @generated
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION = 8;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Navigation Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Container Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Representation Navigation Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
     * <em>Menu Item Or Ref</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMenuItemOrRef()
     * @generated
     */
    int MENU_ITEM_OR_REF = 9;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionImpl
     * <em>Menu Item Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMenuItemDescription()
     * @generated
     */
    int MENU_ITEM_DESCRIPTION = 10;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION__ICON = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Menu Item Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionReferenceImpl
     * <em>Menu Item Description Reference</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionReferenceImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMenuItemDescriptionReference()
     * @generated
     */
    int MENU_ITEM_DESCRIPTION_REFERENCE = 11;

    /**
     * The feature id for the '<em><b>Item</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION_REFERENCE__ITEM = ToolPackage.MENU_ITEM_OR_REF_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Menu Item Description Reference</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MENU_ITEM_DESCRIPTION_REFERENCE_FEATURE_COUNT = ToolPackage.MENU_ITEM_OR_REF_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.OperationActionImpl
     * <em>Operation Action</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.OperationActionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getOperationAction()
     * @generated
     */
    int OPERATION_ACTION = 12;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__DOCUMENTATION = ToolPackage.MENU_ITEM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__NAME = ToolPackage.MENU_ITEM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__LABEL = ToolPackage.MENU_ITEM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__PRECONDITION = ToolPackage.MENU_ITEM_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__FORCE_REFRESH = ToolPackage.MENU_ITEM_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__FILTERS = ToolPackage.MENU_ITEM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__ELEMENTS_TO_SELECT = ToolPackage.MENU_ITEM_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__INVERSE_SELECTION_ORDER = ToolPackage.MENU_ITEM_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__ICON = ToolPackage.MENU_ITEM_DESCRIPTION__ICON;

    /**
     * The feature id for the '<em><b>View</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__VIEW = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION__INITIAL_OPERATION = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Operation Action</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_ACTION_FEATURE_COUNT = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl
     * <em>External Java Action</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getExternalJavaAction()
     * @generated
     */
    int EXTERNAL_JAVA_ACTION = 13;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__DOCUMENTATION = ToolPackage.MENU_ITEM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__NAME = ToolPackage.MENU_ITEM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__LABEL = ToolPackage.MENU_ITEM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__PRECONDITION = ToolPackage.MENU_ITEM_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__FORCE_REFRESH = ToolPackage.MENU_ITEM_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__FILTERS = ToolPackage.MENU_ITEM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__ELEMENTS_TO_SELECT = ToolPackage.MENU_ITEM_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__INVERSE_SELECTION_ORDER = ToolPackage.MENU_ITEM_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__ICON = ToolPackage.MENU_ITEM_DESCRIPTION__ICON;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__SUB_MODEL_OPERATIONS = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__ID = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION__PARAMETERS = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>External Java Action</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_FEATURE_COUNT = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionCallImpl
     * <em>External Java Action Call</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionCallImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getExternalJavaActionCall()
     * @generated
     */
    int EXTERNAL_JAVA_ACTION_CALL = 14;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__DOCUMENTATION = ToolPackage.MENU_ITEM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__NAME = ToolPackage.MENU_ITEM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__LABEL = ToolPackage.MENU_ITEM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__PRECONDITION = ToolPackage.MENU_ITEM_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__FORCE_REFRESH = ToolPackage.MENU_ITEM_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__FILTERS = ToolPackage.MENU_ITEM_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__ELEMENTS_TO_SELECT = ToolPackage.MENU_ITEM_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__INVERSE_SELECTION_ORDER = ToolPackage.MENU_ITEM_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__ICON = ToolPackage.MENU_ITEM_DESCRIPTION__ICON;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__SUB_MODEL_OPERATIONS = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Action</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL__ACTION = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>External Java Action Call</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EXTERNAL_JAVA_ACTION_CALL_FEATURE_COUNT = ToolPackage.MENU_ITEM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PopupMenuImpl
     * <em>Popup Menu</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.PopupMenuImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getPopupMenu()
     * @generated
     */
    int POPUP_MENU = 15;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POPUP_MENU__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POPUP_MENU__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POPUP_MENU__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POPUP_MENU__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POPUP_MENU__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Menu Item Description</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU__MENU_ITEM_DESCRIPTION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Popup Menu</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POPUP_MENU_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl
     * <em>Abstract Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getAbstractVariable()
     * @generated
     */
    int ABSTRACT_VARIABLE = 16;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.VariableContainerImpl
     * <em>Variable Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.VariableContainerImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getVariableContainer()
     * @generated
     */
    int VARIABLE_CONTAINER = 17;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AcceleoVariableImpl
     * <em>Acceleo Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.AcceleoVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getAcceleoVariable()
     * @generated
     */
    int ACCELEO_VARIABLE = 18;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE__SUB_VARIABLES = ToolPackage.VARIABLE_CONTAINER__SUB_VARIABLES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE__NAME = ToolPackage.VARIABLE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE__COMPUTATION_EXPRESSION = ToolPackage.VARIABLE_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Acceleo Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ACCELEO_VARIABLE_FEATURE_COUNT = ToolPackage.VARIABLE_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SubVariableImpl
     * <em>Sub Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SubVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSubVariable()
     * @generated
     */
    int SUB_VARIABLE = 19;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SUB_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '<em>Sub Variable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SUB_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DialogVariableImpl
     * <em>Dialog Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.DialogVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDialogVariable()
     * @generated
     */
    int DIALOG_VARIABLE = 20;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Dialog Prompt</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_VARIABLE__DIALOG_PROMPT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Dialog Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DIALOG_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementDropVariableImpl
     * <em>Element Drop Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementDropVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementDropVariable()
     * @generated
     */
    int ELEMENT_DROP_VARIABLE = 21;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_DROP_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DROP_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element Drop Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DROP_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementSelectVariableImpl
     * <em>Element Select Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementSelectVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementSelectVariable()
     * @generated
     */
    int ELEMENT_SELECT_VARIABLE = 22;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_SELECT_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '
     * <em>Element Select Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_SELECT_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementVariableImpl
     * <em>Element Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementVariable()
     * @generated
     */
    int ELEMENT_VARIABLE = 23;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementViewVariableImpl
     * <em>Element View Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementViewVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementViewVariable()
     * @generated
     */
    int ELEMENT_VIEW_VARIABLE = 24;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_VIEW_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VIEW_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Element View Variable</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_VIEW_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementDeleteVariableImpl
     * <em>Element Delete Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementDeleteVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementDeleteVariable()
     * @generated
     */
    int ELEMENT_DELETE_VARIABLE = 25;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_DELETE_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DELETE_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Element Delete Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_DELETE_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DropContainerVariableImpl
     * <em>Drop Container Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.DropContainerVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDropContainerVariable()
     * @generated
     */
    int DROP_CONTAINER_VARIABLE = 26;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DROP_CONTAINER_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DROP_CONTAINER_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Drop Container Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DROP_CONTAINER_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectContainerVariableImpl
     * <em>Select Container Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SelectContainerVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSelectContainerVariable()
     * @generated
     */
    int SELECT_CONTAINER_VARIABLE = 27;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_CONTAINER_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_CONTAINER_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Select Container Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_CONTAINER_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ContainerViewVariableImpl
     * <em>Container View Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerViewVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getContainerViewVariable()
     * @generated
     */
    int CONTAINER_VIEW_VARIABLE = 28;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_VIEW_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VIEW_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Container View Variable</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_VIEW_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl
     * <em>Select Model Element Variable</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSelectModelElementVariable()
     * @generated
     */
    int SELECT_MODEL_ELEMENT_VARIABLE = 29;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__NAME = ToolPackage.SUB_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__CANDIDATES_EXPRESSION = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Multiple</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__MULTIPLE = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Tree</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__TREE = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Root Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__ROOT_EXPRESSION = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Children Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__CHILDREN_EXPRESSION = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE__MESSAGE = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Select Model Element Variable</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_MODEL_ELEMENT_VARIABLE_FEATURE_COUNT = ToolPackage.SUB_VARIABLE_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.EditMaskVariablesImpl
     * <em>Edit Mask Variables</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.EditMaskVariablesImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getEditMaskVariables()
     * @generated
     */
    int EDIT_MASK_VARIABLES = 30;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ModelOperationImpl
     * <em>Model Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ModelOperationImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getModelOperation()
     * @generated
     */
    int MODEL_OPERATION = 32;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ContainerModelOperationImpl
     * <em>Container Model Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerModelOperationImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getContainerModelOperation()
     * @generated
     */
    int CONTAINER_MODEL_OPERATION = 31;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Container Model Operation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_MODEL_OPERATION_FEATURE_COUNT = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialNodeCreationOperationImpl
     * <em>Initial Node Creation Operation</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitialNodeCreationOperationImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitialNodeCreationOperation()
     * @generated
     */
    int INITIAL_NODE_CREATION_OPERATION = 33;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialOperationImpl
     * <em>Initial Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitialOperationImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitialOperation()
     * @generated
     */
    int INITIAL_OPERATION = 34;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitEdgeCreationOperationImpl
     * <em>Init Edge Creation Operation</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitEdgeCreationOperationImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitEdgeCreationOperation()
     * @generated
     */
    int INIT_EDGE_CREATION_OPERATION = 35;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialContainerDropOperationImpl
     * <em>Initial Container Drop Operation</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitialContainerDropOperationImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitialContainerDropOperation()
     * @generated
     */
    int INITIAL_CONTAINER_DROP_OPERATION = 36;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl
     * <em>Create Instance</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getCreateInstance()
     * @generated
     */
    int CREATE_INSTANCE = 37;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Type Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__TYPE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reference Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__REFERENCE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__VARIABLE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Create Instance</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ChangeContextImpl
     * <em>Change Context</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ChangeContextImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getChangeContext()
     * @generated
     */
    int CHANGE_CONTEXT = 38;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT__BROWSE_EXPRESSION = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Change Context</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SetValueImpl
     * <em>Set Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SetValueImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSetValue()
     * @generated
     */
    int SET_VALUE = 39;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_VALUE__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE__FEATURE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE__VALUE_EXPRESSION = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Set Value</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SetObjectImpl
     * <em>Set Object</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SetObjectImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSetObject()
     * @generated
     */
    int SET_OBJECT = 40;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_OBJECT__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_OBJECT__FEATURE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Object</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_OBJECT__OBJECT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Set Object</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SET_OBJECT_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.UnsetImpl
     * <em>Unset</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.UnsetImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getUnset()
     * @generated
     */
    int UNSET = 41;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int UNSET__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UNSET__FEATURE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int UNSET__ELEMENT_EXPRESSION = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Unset</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UNSET_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MoveElementImpl
     * <em>Move Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.MoveElementImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMoveElement()
     * @generated
     */
    int MOVE_ELEMENT = 42;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>New Container Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT__FEATURE_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Move Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MOVE_ELEMENT_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RemoveElementImpl
     * <em>Remove Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.RemoveElementImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getRemoveElement()
     * @generated
     */
    int REMOVE_ELEMENT = 43;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOVE_ELEMENT__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The number of structural features of the '<em>Remove Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REMOVE_ELEMENT_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ForImpl
     * <em>For</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ForImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getFor()
     * @generated
     */
    int FOR = 44;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOR__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOR__EXPRESSION = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Iterator Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOR__ITERATOR_NAME = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>For</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOR_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.IfImpl
     * <em>If</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.IfImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getIf()
     * @generated
     */
    int IF = 45;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IF__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IF__CONDITION_EXPRESSION = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>If</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int IF_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DeleteViewImpl
     * <em>Delete View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.DeleteViewImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDeleteView()
     * @generated
     */
    int DELETE_VIEW = 46;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_VIEW__SUB_MODEL_OPERATIONS = ToolPackage.CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS;

    /**
     * The number of structural features of the '<em>Delete View</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_VIEW_FEATURE_COUNT = ToolPackage.CONTAINER_MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.NameVariableImpl
     * <em>Name Variable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.NameVariableImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getNameVariable()
     * @generated
     */
    int NAME_VARIABLE = 47;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NAME_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The number of structural features of the '<em>Name Variable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NAME_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionParameterImpl
     * <em>External Java Action Parameter</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionParameterImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getExternalJavaActionParameter()
     * @generated
     */
    int EXTERNAL_JAVA_ACTION_PARAMETER = 48;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl
     * <em>Filter Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getToolFilterDescription()
     * @generated
     */
    int TOOL_FILTER_DESCRIPTION = 49;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.FeatureChangeListenerImpl
     * <em>Feature Change Listener</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.FeatureChangeListenerImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getFeatureChangeListener()
     * @generated
     */
    int FEATURE_CHANGE_LISTENER = 50;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SwitchChildImpl
     * <em>Switch Child</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SwitchChildImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSwitchChild()
     * @generated
     */
    int SWITCH_CHILD = 52;

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
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CaseImpl
     * <em>Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.CaseImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getCase()
     * @generated
     */
    int CASE = 51;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CASE__SUB_MODEL_OPERATIONS = ToolPackage.SWITCH_CHILD__SUB_MODEL_OPERATIONS;

    /**
     * The feature id for the '<em><b>Condition Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CASE__CONDITION_EXPRESSION = ToolPackage.SWITCH_CHILD_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Case</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CASE_FEATURE_COUNT = ToolPackage.SWITCH_CHILD_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DefaultImpl
     * <em>Default</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.DefaultImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDefault()
     * @generated
     */
    int DEFAULT = 53;

    /**
     * The feature id for the '<em><b>Sub Model Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DEFAULT__SUB_MODEL_OPERATIONS = ToolPackage.SWITCH_CHILD__SUB_MODEL_OPERATIONS;

    /**
     * The number of structural features of the '<em>Default</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DEFAULT_FEATURE_COUNT = ToolPackage.SWITCH_CHILD_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SwitchImpl
     * <em>Switch</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.SwitchImpl
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSwitch()
     * @generated
     */
    int SWITCH = 54;

    /**
     * The feature id for the '<em><b>Cases</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH__CASES = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Default</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SWITCH__DEFAULT = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Switch</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SWITCH_FEATURE_COUNT = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DragSource
     * <em>Drag Source</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.tool.DragSource
     * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDragSource()
     * @generated
     */
    int DRAG_SOURCE = 55;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * <em>Entry</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Entry</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolEntry
     * @generated
     */
    EClass getToolEntry();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription
     * <em>Abstract Tool Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Tool Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription
     * @generated
     */
    EClass getAbstractToolDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getPrecondition
     * <em>Precondition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Precondition</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getPrecondition()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EAttribute getAbstractToolDescription_Precondition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isForceRefresh
     * <em>Force Refresh</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Force Refresh</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isForceRefresh()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EAttribute getAbstractToolDescription_ForceRefresh();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getFilters
     * <em>Filters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Filters</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getFilters()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EReference getAbstractToolDescription_Filters();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getElementsToSelect
     * <em>Elements To Select</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Elements To Select</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#getElementsToSelect()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EAttribute getAbstractToolDescription_ElementsToSelect();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isInverseSelectionOrder
     * <em>Inverse Selection Order</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Inverse Selection Order</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription#isInverseSelectionOrder()
     * @see #getAbstractToolDescription()
     * @generated
     */
    EAttribute getAbstractToolDescription_InverseSelectionOrder();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription
     * <em>Mapping Based Tool Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Mapping Based Tool Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MappingBasedToolDescription
     * @generated
     */
    EClass getMappingBasedToolDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription
     * @generated
     */
    EClass getToolDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getIconPath()
     * @see #getToolDescription()
     * @generated
     */
    EAttribute getToolDescription_IconPath();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElement()
     * @see #getToolDescription()
     * @generated
     */
    EReference getToolDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElementView
     * <em>Element View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Element View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getElementView()
     * @see #getToolDescription()
     * @generated
     */
    EReference getToolDescription_ElementView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolDescription#getInitialOperation()
     * @see #getToolDescription()
     * @generated
     */
    EReference getToolDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription
     * <em>Paste Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Paste Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription
     * @generated
     */
    EClass getPasteDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainer()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_Container();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getContainerView()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedView
     * <em>Copied View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Copied View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedView()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_CopiedView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedElement
     * <em>Copied Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Copied Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getCopiedElement()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_CopiedElement();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PasteDescription#getInitialOperation()
     * @see #getPasteDescription()
     * @generated
     */
    EReference getPasteDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription
     * <em>Selection Wizard Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Selection Wizard Description</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription
     * @generated
     */
    EClass getSelectionWizardDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getElement()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainerView()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getContainer()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_Container();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getInitialOperation()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EReference getSelectionWizardDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getIconPath()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EAttribute getSelectionWizardDescription_IconPath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowTitle
     * <em>Window Title</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Window Title</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowTitle()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EAttribute getSelectionWizardDescription_WindowTitle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowImagePath
     * <em>Window Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Window Image Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectionWizardDescription#getWindowImagePath()
     * @see #getSelectionWizardDescription()
     * @generated
     */
    EAttribute getSelectionWizardDescription_WindowImagePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription
     * <em>Pane Based Selection Wizard Description</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Pane Based Selection Wizard Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription
     * @generated
     */
    EClass getPaneBasedSelectionWizardDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getElement
     * <em>Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getElement()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_Element();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainerView
     * <em>Container View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainerView()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_ContainerView();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getContainer()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_Container();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getInitialOperation()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EReference getPaneBasedSelectionWizardDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getIconPath()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_IconPath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowTitle
     * <em>Window Title</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Window Title</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowTitle()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_WindowTitle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowImagePath
     * <em>Window Image Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Window Image Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getWindowImagePath()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_WindowImagePath();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getMessage
     * <em>Message</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Message</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getMessage()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_Message();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChoiceOfValuesMessage
     * <em>Choice Of Values Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Choice Of Values Message</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChoiceOfValuesMessage()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Candidates Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getCandidatesExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#isTree
     * <em>Tree</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Tree</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#isTree()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_Tree();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getRootExpression
     * <em>Root Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Root Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getRootExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_RootExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChildrenExpression
     * <em>Children Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Children Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getChildrenExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_ChildrenExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getSelectedValuesMessage
     * <em>Selected Values Message</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Selected Values Message</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getSelectedValuesMessage()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_SelectedValuesMessage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getPreSelectedCandidatesExpression
     * <em>Pre Selected Candidates Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Pre Selected Candidates Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PaneBasedSelectionWizardDescription#getPreSelectedCandidatesExpression()
     * @see #getPaneBasedSelectionWizardDescription()
     * @generated
     */
    EAttribute getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription
     * <em>Representation Creation Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Creation Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription
     * @generated
     */
    EClass getRepresentationCreationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getTitleExpression
     * <em>Title Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getTitleExpression()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EAttribute getRepresentationCreationDescription_TitleExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getBrowseExpression
     * <em>Browse Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Browse Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getBrowseExpression()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EAttribute getRepresentationCreationDescription_BrowseExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Representation Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationDescription()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_RepresentationDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getInitialOperation()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getContainerViewVariable
     * <em>Container View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getContainerViewVariable()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_ContainerViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationNameVariable
     * <em>Representation Name Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Representation Name Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription#getRepresentationNameVariable()
     * @see #getRepresentationCreationDescription()
     * @generated
     */
    EReference getRepresentationCreationDescription_RepresentationNameVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription
     * <em>Representation Navigation Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Representation Navigation Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription
     * @generated
     */
    EClass getRepresentationNavigationDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getBrowseExpression
     * <em>Browse Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Browse Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getBrowseExpression()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EAttribute getRepresentationNavigationDescription_BrowseExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getNavigationNameExpression
     * <em>Navigation Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Navigation Name Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getNavigationNameExpression()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EAttribute getRepresentationNavigationDescription_NavigationNameExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Representation Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationDescription()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_RepresentationDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerViewVariable
     * <em>Container View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Container View Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerViewVariable()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_ContainerViewVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerVariable
     * <em>Container Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Container Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getContainerVariable()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_ContainerVariable();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationNameVariable
     * <em>Representation Name Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Representation Name Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription#getRepresentationNameVariable()
     * @see #getRepresentationNavigationDescription()
     * @generated
     */
    EReference getRepresentationNavigationDescription_RepresentationNameVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
     * <em>Menu Item Or Ref</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Menu Item Or Ref</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
     * @generated
     */
    EClass getMenuItemOrRef();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription
     * <em>Menu Item Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Menu Item Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription
     * @generated
     */
    EClass getMenuItemDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription#getIcon
     * <em>Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription#getIcon()
     * @see #getMenuItemDescription()
     * @generated
     */
    EAttribute getMenuItemDescription_Icon();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference
     * <em>Menu Item Description Reference</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Menu Item Description Reference</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference
     * @generated
     */
    EClass getMenuItemDescriptionReference();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference#getItem
     * <em>Item</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Item</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemDescriptionReference#getItem()
     * @see #getMenuItemDescriptionReference()
     * @generated
     */
    EReference getMenuItemDescriptionReference_Item();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.OperationAction
     * <em>Operation Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Operation Action</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.OperationAction
     * @generated
     */
    EClass getOperationAction();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.OperationAction#getView
     * <em>View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.OperationAction#getView()
     * @see #getOperationAction()
     * @generated
     */
    EReference getOperationAction_View();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.OperationAction#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.OperationAction#getInitialOperation()
     * @see #getOperationAction()
     * @generated
     */
    EReference getOperationAction_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
     * <em>External Java Action</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>External Java Action</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction
     * @generated
     */
    EClass getExternalJavaAction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getId()
     * @see #getExternalJavaAction()
     * @generated
     */
    EAttribute getExternalJavaAction_Id();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getParameters
     * <em>Parameters</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Parameters</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction#getParameters()
     * @see #getExternalJavaAction()
     * @generated
     */
    EReference getExternalJavaAction_Parameters();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall
     * <em>External Java Action Call</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>External Java Action Call</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall
     * @generated
     */
    EClass getExternalJavaActionCall();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall#getAction
     * <em>Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Action</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall#getAction()
     * @see #getExternalJavaActionCall()
     * @generated
     */
    EReference getExternalJavaActionCall_Action();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu
     * <em>Popup Menu</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Popup Menu</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PopupMenu
     * @generated
     */
    EClass getPopupMenu();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu#getMenuItemDescription
     * <em>Menu Item Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Menu Item Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.PopupMenu#getMenuItemDescription()
     * @see #getPopupMenu()
     * @generated
     */
    EReference getPopupMenu_MenuItemDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractVariable
     * <em>Abstract Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Abstract Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractVariable
     * @generated
     */
    EClass getAbstractVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractVariable#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AbstractVariable#getName()
     * @see #getAbstractVariable()
     * @generated
     */
    EAttribute getAbstractVariable_Name();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.VariableContainer
     * <em>Variable Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Variable Container</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.VariableContainer
     * @generated
     */
    EClass getVariableContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.VariableContainer#getSubVariables
     * <em>Sub Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Variables</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.VariableContainer#getSubVariables()
     * @see #getVariableContainer()
     * @generated
     */
    EReference getVariableContainer_SubVariables();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable
     * <em>Acceleo Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Acceleo Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable
     * @generated
     */
    EClass getAcceleoVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable#getComputationExpression
     * <em>Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable#getComputationExpression()
     * @see #getAcceleoVariable()
     * @generated
     */
    EAttribute getAcceleoVariable_ComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SubVariable
     * <em>Sub Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Sub Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SubVariable
     * @generated
     */
    EClass getSubVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DialogVariable
     * <em>Dialog Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dialog Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.DialogVariable
     * @generated
     */
    EClass getDialogVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DialogVariable#getDialogPrompt
     * <em>Dialog Prompt</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Dialog Prompt</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.DialogVariable#getDialogPrompt()
     * @see #getDialogVariable()
     * @generated
     */
    EAttribute getDialogVariable_DialogPrompt();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable
     * <em>Element Drop Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Drop Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementDropVariable
     * @generated
     */
    EClass getElementDropVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable
     * <em>Element Select Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Select Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementSelectVariable
     * @generated
     */
    EClass getElementSelectVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementVariable
     * <em>Element Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Element Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementVariable
     * @generated
     */
    EClass getElementVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
     * <em>Element View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element View Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementViewVariable
     * @generated
     */
    EClass getElementViewVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
     * <em>Element Delete Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Delete Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ElementDeleteVariable
     * @generated
     */
    EClass getElementDeleteVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
     * <em>Drop Container Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Drop Container Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.DropContainerVariable
     * @generated
     */
    EClass getDropContainerVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable
     * <em>Select Container Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Select Container Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectContainerVariable
     * @generated
     */
    EClass getSelectContainerVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
     * <em>Container View Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container View Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ContainerViewVariable
     * @generated
     */
    EClass getContainerViewVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable
     * <em>Select Model Element Variable</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Select Model Element Variable</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.SelectModelElementVariable
     * @generated
     */
    EClass getSelectModelElementVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables
     * <em>Edit Mask Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Edit Mask Variables</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables
     * @generated
     */
    EClass getEditMaskVariables();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables#getMask
     * <em>Mask</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Mask</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables#getMask()
     * @see #getEditMaskVariables()
     * @generated
     */
    EAttribute getEditMaskVariables_Mask();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation
     * <em>Container Model Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Model Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation
     * @generated
     */
    EClass getContainerModelOperation();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation#getSubModelOperations
     * <em>Sub Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Model Operations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ContainerModelOperation#getSubModelOperations()
     * @see #getContainerModelOperation()
     * @generated
     */
    EReference getContainerModelOperation_SubModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * <em>Model Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Model Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ModelOperation
     * @generated
     */
    EClass getModelOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation
     * <em>Initial Node Creation Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Initial Node Creation Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation
     * @generated
     */
    EClass getInitialNodeCreationOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialNodeCreationOperation#getFirstModelOperations()
     * @see #getInitialNodeCreationOperation()
     * @generated
     */
    EReference getInitialNodeCreationOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialOperation
     * @generated
     */
    EClass getInitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialOperation#getFirstModelOperations()
     * @see #getInitialOperation()
     * @generated
     */
    EReference getInitialOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation
     * <em>Init Edge Creation Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Init Edge Creation Operation</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation
     * @generated
     */
    EClass getInitEdgeCreationOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitEdgeCreationOperation#getFirstModelOperations()
     * @see #getInitEdgeCreationOperation()
     * @generated
     */
    EReference getInitEdgeCreationOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation
     * <em>Initial Container Drop Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Initial Container Drop Operation</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation
     * @generated
     */
    EClass getInitialContainerDropOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation#getFirstModelOperations
     * <em>First Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.InitialContainerDropOperation#getFirstModelOperations()
     * @see #getInitialContainerDropOperation()
     * @generated
     */
    EReference getInitialContainerDropOperation_FirstModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance
     * <em>Create Instance</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Create Instance</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.CreateInstance
     * @generated
     */
    EClass getCreateInstance();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getTypeName
     * <em>Type Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Type Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getTypeName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_TypeName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getReferenceName
     * <em>Reference Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Reference Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getReferenceName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_ReferenceName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getVariableName
     * <em>Variable Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.CreateInstance#getVariableName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_VariableName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext
     * <em>Change Context</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Change Context</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ChangeContext
     * @generated
     */
    EClass getChangeContext();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext#getBrowseExpression
     * <em>Browse Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Browse Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ChangeContext#getBrowseExpression()
     * @see #getChangeContext()
     * @generated
     */
    EAttribute getChangeContext_BrowseExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SetValue
     * <em>Set Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Set Value</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetValue
     * @generated
     */
    EClass getSetValue();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SetValue#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetValue#getFeatureName()
     * @see #getSetValue()
     * @generated
     */
    EAttribute getSetValue_FeatureName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SetValue#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetValue#getValueExpression()
     * @see #getSetValue()
     * @generated
     */
    EAttribute getSetValue_ValueExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SetObject
     * <em>Set Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Set Object</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetObject
     * @generated
     */
    EClass getSetObject();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SetObject#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetObject#getFeatureName()
     * @see #getSetObject()
     * @generated
     */
    EAttribute getSetObject_FeatureName();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SetObject#getObject
     * <em>Object</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Object</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SetObject#getObject()
     * @see #getSetObject()
     * @generated
     */
    EReference getSetObject_Object();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Unset
     * <em>Unset</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Unset</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Unset
     * @generated
     */
    EClass getUnset();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Unset#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Unset#getFeatureName()
     * @see #getUnset()
     * @generated
     */
    EAttribute getUnset_FeatureName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Unset#getElementExpression
     * <em>Element Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Element Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Unset#getElementExpression()
     * @see #getUnset()
     * @generated
     */
    EAttribute getUnset_ElementExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MoveElement
     * <em>Move Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Move Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MoveElement
     * @generated
     */
    EClass getMoveElement();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MoveElement#getNewContainerExpression
     * <em>New Container Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>New Container Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MoveElement#getNewContainerExpression()
     * @see #getMoveElement()
     * @generated
     */
    EAttribute getMoveElement_NewContainerExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.MoveElement#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.MoveElement#getFeatureName()
     * @see #getMoveElement()
     * @generated
     */
    EAttribute getMoveElement_FeatureName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.RemoveElement
     * <em>Remove Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Remove Element</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.RemoveElement
     * @generated
     */
    EClass getRemoveElement();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.For <em>For</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>For</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.For
     * @generated
     */
    EClass getFor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.For#getExpression
     * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.For#getExpression()
     * @see #getFor()
     * @generated
     */
    EAttribute getFor_Expression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.For#getIteratorName
     * <em>Iterator Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Iterator Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.For#getIteratorName()
     * @see #getFor()
     * @generated
     */
    EAttribute getFor_IteratorName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.If <em>If</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>If</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.If
     * @generated
     */
    EClass getIf();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.If#getConditionExpression
     * <em>Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Condition Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.If#getConditionExpression()
     * @see #getIf()
     * @generated
     */
    EAttribute getIf_ConditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DeleteView
     * <em>Delete View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Delete View</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.DeleteView
     * @generated
     */
    EClass getDeleteView();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.NameVariable
     * <em>Name Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Name Variable</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.NameVariable
     * @generated
     */
    EClass getNameVariable();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
     * <em>External Java Action Parameter</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>External Java Action Parameter</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter
     * @generated
     */
    EClass getExternalJavaActionParameter();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter#getName()
     * @see #getExternalJavaActionParameter()
     * @generated
     */
    EAttribute getExternalJavaActionParameter_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter#getValue
     * <em>Value</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionParameter#getValue()
     * @see #getExternalJavaActionParameter()
     * @generated
     */
    EAttribute getExternalJavaActionParameter_Value();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription
     * <em>Filter Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Filter Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription
     * @generated
     */
    EClass getToolFilterDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getPrecondition
     * <em>Precondition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Precondition</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getPrecondition()
     * @see #getToolFilterDescription()
     * @generated
     */
    EAttribute getToolFilterDescription_Precondition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getElementsToListen
     * <em>Elements To Listen</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Elements To Listen</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getElementsToListen()
     * @see #getToolFilterDescription()
     * @generated
     */
    EAttribute getToolFilterDescription_ElementsToListen();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getListeners
     * <em>Listeners</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Listeners</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolFilterDescription#getListeners()
     * @see #getToolFilterDescription()
     * @generated
     */
    EReference getToolFilterDescription_Listeners();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener
     * <em>Feature Change Listener</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Feature Change Listener</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener
     * @generated
     */
    EClass getFeatureChangeListener();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener#getDomainClass()
     * @see #getFeatureChangeListener()
     * @generated
     */
    EAttribute getFeatureChangeListener_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.FeatureChangeListener#getFeatureName()
     * @see #getFeatureChangeListener()
     * @generated
     */
    EAttribute getFeatureChangeListener_FeatureName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Case <em>Case</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Case</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Case
     * @generated
     */
    EClass getCase();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Case#getConditionExpression
     * <em>Condition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Condition Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.tool.Case#getConditionExpression()
     * @see #getCase()
     * @generated
     */
    EAttribute getCase_ConditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SwitchChild
     * <em>Switch Child</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Switch Child</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SwitchChild
     * @generated
     */
    EClass getSwitchChild();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.SwitchChild#getSubModelOperations
     * <em>Sub Model Operations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sub Model Operations</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.SwitchChild#getSubModelOperations()
     * @see #getSwitchChild()
     * @generated
     */
    EReference getSwitchChild_SubModelOperations();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Default
     * <em>Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Default</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Default
     * @generated
     */
    EClass getDefault();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Switch
     * <em>Switch</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Switch</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Switch
     * @generated
     */
    EClass getSwitch();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Switch#getCases
     * <em>Cases</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Cases</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Switch#getCases()
     * @see #getSwitch()
     * @generated
     */
    EReference getSwitch_Cases();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.tool.Switch#getDefault
     * <em>Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Default</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.Switch#getDefault()
     * @see #getSwitch()
     * @generated
     */
    EReference getSwitch_Default();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.viewpoint.description.tool.DragSource
     * <em>Drag Source</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Drag Source</em>'.
     * @see org.eclipse.sirius.viewpoint.description.tool.DragSource
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
         * {@link org.eclipse.sirius.viewpoint.description.tool.ToolEntry
         * <em>Entry</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.ToolEntry
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getToolEntry()
         * @generated
         */
        EClass TOOL_ENTRY = ToolPackage.eINSTANCE.getToolEntry();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl
         * <em>Abstract Tool Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.AbstractToolDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getAbstractToolDescription()
         * @generated
         */
        EClass ABSTRACT_TOOL_DESCRIPTION = ToolPackage.eINSTANCE.getAbstractToolDescription();

        /**
         * The meta object literal for the '<em><b>Precondition</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_TOOL_DESCRIPTION__PRECONDITION = ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition();

        /**
         * The meta object literal for the '<em><b>Force Refresh</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH = ToolPackage.eINSTANCE.getAbstractToolDescription_ForceRefresh();

        /**
         * The meta object literal for the '<em><b>Filters</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_TOOL_DESCRIPTION__FILTERS = ToolPackage.eINSTANCE.getAbstractToolDescription_Filters();

        /**
         * The meta object literal for the '<em><b>Elements To Select</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.eINSTANCE.getAbstractToolDescription_ElementsToSelect();

        /**
         * The meta object literal for the '
         * <em><b>Inverse Selection Order</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.eINSTANCE.getAbstractToolDescription_InverseSelectionOrder();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl
         * <em>Mapping Based Tool Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.MappingBasedToolDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMappingBasedToolDescription()
         * @generated
         */
        EClass MAPPING_BASED_TOOL_DESCRIPTION = ToolPackage.eINSTANCE.getMappingBasedToolDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolDescriptionImpl
         * <em>Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getToolDescription()
         * @generated
         */
        EClass TOOL_DESCRIPTION = ToolPackage.eINSTANCE.getToolDescription();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_DESCRIPTION__ICON_PATH = ToolPackage.eINSTANCE.getToolDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getToolDescription_Element();

        /**
         * The meta object literal for the '<em><b>Element View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_DESCRIPTION__ELEMENT_VIEW = ToolPackage.eINSTANCE.getToolDescription_ElementView();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getToolDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl
         * <em>Paste Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.PasteDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getPasteDescription()
         * @generated
         */
        EClass PASTE_DESCRIPTION = ToolPackage.eINSTANCE.getPasteDescription();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__CONTAINER = ToolPackage.eINSTANCE.getPasteDescription_Container();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__CONTAINER_VIEW = ToolPackage.eINSTANCE.getPasteDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Copied View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__COPIED_VIEW = ToolPackage.eINSTANCE.getPasteDescription_CopiedView();

        /**
         * The meta object literal for the '<em><b>Copied Element</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__COPIED_ELEMENT = ToolPackage.eINSTANCE.getPasteDescription_CopiedElement();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PASTE_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getPasteDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectionWizardDescriptionImpl
         * <em>Selection Wizard Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SelectionWizardDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSelectionWizardDescription()
         * @generated
         */
        EClass SELECTION_WIZARD_DESCRIPTION = ToolPackage.eINSTANCE.getSelectionWizardDescription();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getSelectionWizardDescription_Element();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = ToolPackage.eINSTANCE.getSelectionWizardDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__CONTAINER = ToolPackage.eINSTANCE.getSelectionWizardDescription_Container();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getSelectionWizardDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_WIZARD_DESCRIPTION__ICON_PATH = ToolPackage.eINSTANCE.getSelectionWizardDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Window Title</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = ToolPackage.eINSTANCE.getSelectionWizardDescription_WindowTitle();

        /**
         * The meta object literal for the '<em><b>Window Image Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = ToolPackage.eINSTANCE.getSelectionWizardDescription_WindowImagePath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
         * <em>Pane Based Selection Wizard Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.PaneBasedSelectionWizardDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getPaneBasedSelectionWizardDescription()
         * @generated
         */
        EClass PANE_BASED_SELECTION_WIZARD_DESCRIPTION = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription();

        /**
         * The meta object literal for the '<em><b>Element</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ELEMENT = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_Element();

        /**
         * The meta object literal for the '<em><b>Container View</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER_VIEW = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_ContainerView();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CONTAINER = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_Container();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference PANE_BASED_SELECTION_WIZARD_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ICON_PATH = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_IconPath();

        /**
         * The meta object literal for the '<em><b>Window Title</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_TITLE = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_WindowTitle();

        /**
         * The meta object literal for the '<em><b>Window Image Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__WINDOW_IMAGE_PATH = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_WindowImagePath();

        /**
         * The meta object literal for the '<em><b>Message</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__MESSAGE = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_Message();

        /**
         * The meta object literal for the '
         * <em><b>Choice Of Values Message</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHOICE_OF_VALUES_MESSAGE = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_ChoiceOfValuesMessage();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CANDIDATES_EXPRESSION = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_CandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Tree</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__TREE = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_Tree();

        /**
         * The meta object literal for the '<em><b>Root Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__ROOT_EXPRESSION = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_RootExpression();

        /**
         * The meta object literal for the '<em><b>Children Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__CHILDREN_EXPRESSION = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_ChildrenExpression();

        /**
         * The meta object literal for the '
         * <em><b>Selected Values Message</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__SELECTED_VALUES_MESSAGE = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_SelectedValuesMessage();

        /**
         * The meta object literal for the '
         * <em><b>Pre Selected Candidates Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PANE_BASED_SELECTION_WIZARD_DESCRIPTION__PRE_SELECTED_CANDIDATES_EXPRESSION = ToolPackage.eINSTANCE.getPaneBasedSelectionWizardDescription_PreSelectedCandidatesExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl
         * <em>Representation Creation Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationCreationDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getRepresentationCreationDescription()
         * @generated
         */
        EClass REPRESENTATION_CREATION_DESCRIPTION = ToolPackage.eINSTANCE.getRepresentationCreationDescription();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION = ToolPackage.eINSTANCE.getRepresentationCreationDescription_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Browse Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION = ToolPackage.eINSTANCE.getRepresentationCreationDescription_BrowseExpression();

        /**
         * The meta object literal for the '
         * <em><b>Representation Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ToolPackage.eINSTANCE.getRepresentationCreationDescription_RepresentationDescription();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getRepresentationCreationDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>Container View Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ToolPackage.eINSTANCE.getRepresentationCreationDescription_ContainerViewVariable();

        /**
         * The meta object literal for the '
         * <em><b>Representation Name Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ToolPackage.eINSTANCE.getRepresentationCreationDescription_RepresentationNameVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl
         * <em>Representation Navigation Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.RepresentationNavigationDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getRepresentationNavigationDescription()
         * @generated
         */
        EClass REPRESENTATION_NAVIGATION_DESCRIPTION = ToolPackage.eINSTANCE.getRepresentationNavigationDescription();

        /**
         * The meta object literal for the '<em><b>Browse Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_BrowseExpression();

        /**
         * The meta object literal for the '
         * <em><b>Navigation Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_NavigationNameExpression();

        /**
         * The meta object literal for the '
         * <em><b>Representation Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_RepresentationDescription();

        /**
         * The meta object literal for the '
         * <em><b>Container View Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_ContainerViewVariable();

        /**
         * The meta object literal for the '<em><b>Container Variable</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_ContainerVariable();

        /**
         * The meta object literal for the '
         * <em><b>Representation Name Variable</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_RepresentationNameVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
         * <em>Menu Item Or Ref</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.MenuItemOrRef
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMenuItemOrRef()
         * @generated
         */
        EClass MENU_ITEM_OR_REF = ToolPackage.eINSTANCE.getMenuItemOrRef();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionImpl
         * <em>Menu Item Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMenuItemDescription()
         * @generated
         */
        EClass MENU_ITEM_DESCRIPTION = ToolPackage.eINSTANCE.getMenuItemDescription();

        /**
         * The meta object literal for the '<em><b>Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MENU_ITEM_DESCRIPTION__ICON = ToolPackage.eINSTANCE.getMenuItemDescription_Icon();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionReferenceImpl
         * <em>Menu Item Description Reference</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.MenuItemDescriptionReferenceImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMenuItemDescriptionReference()
         * @generated
         */
        EClass MENU_ITEM_DESCRIPTION_REFERENCE = ToolPackage.eINSTANCE.getMenuItemDescriptionReference();

        /**
         * The meta object literal for the '<em><b>Item</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference MENU_ITEM_DESCRIPTION_REFERENCE__ITEM = ToolPackage.eINSTANCE.getMenuItemDescriptionReference_Item();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.OperationActionImpl
         * <em>Operation Action</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.OperationActionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getOperationAction()
         * @generated
         */
        EClass OPERATION_ACTION = ToolPackage.eINSTANCE.getOperationAction();

        /**
         * The meta object literal for the '<em><b>View</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference OPERATION_ACTION__VIEW = ToolPackage.eINSTANCE.getOperationAction_View();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference OPERATION_ACTION__INITIAL_OPERATION = ToolPackage.eINSTANCE.getOperationAction_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl
         * <em>External Java Action</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getExternalJavaAction()
         * @generated
         */
        EClass EXTERNAL_JAVA_ACTION = ToolPackage.eINSTANCE.getExternalJavaAction();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EXTERNAL_JAVA_ACTION__ID = ToolPackage.eINSTANCE.getExternalJavaAction_Id();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EXTERNAL_JAVA_ACTION__PARAMETERS = ToolPackage.eINSTANCE.getExternalJavaAction_Parameters();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionCallImpl
         * <em>External Java Action Call</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionCallImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getExternalJavaActionCall()
         * @generated
         */
        EClass EXTERNAL_JAVA_ACTION_CALL = ToolPackage.eINSTANCE.getExternalJavaActionCall();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EXTERNAL_JAVA_ACTION_CALL__ACTION = ToolPackage.eINSTANCE.getExternalJavaActionCall_Action();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.PopupMenuImpl
         * <em>Popup Menu</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.PopupMenuImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getPopupMenu()
         * @generated
         */
        EClass POPUP_MENU = ToolPackage.eINSTANCE.getPopupMenu();

        /**
         * The meta object literal for the '
         * <em><b>Menu Item Description</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference POPUP_MENU__MENU_ITEM_DESCRIPTION = ToolPackage.eINSTANCE.getPopupMenu_MenuItemDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl
         * <em>Abstract Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.AbstractVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getAbstractVariable()
         * @generated
         */
        EClass ABSTRACT_VARIABLE = ToolPackage.eINSTANCE.getAbstractVariable();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_VARIABLE__NAME = ToolPackage.eINSTANCE.getAbstractVariable_Name();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.VariableContainerImpl
         * <em>Variable Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.VariableContainerImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getVariableContainer()
         * @generated
         */
        EClass VARIABLE_CONTAINER = ToolPackage.eINSTANCE.getVariableContainer();

        /**
         * The meta object literal for the '<em><b>Sub Variables</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference VARIABLE_CONTAINER__SUB_VARIABLES = ToolPackage.eINSTANCE.getVariableContainer_SubVariables();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.AcceleoVariableImpl
         * <em>Acceleo Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.AcceleoVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getAcceleoVariable()
         * @generated
         */
        EClass ACCELEO_VARIABLE = ToolPackage.eINSTANCE.getAcceleoVariable();

        /**
         * The meta object literal for the '
         * <em><b>Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ACCELEO_VARIABLE__COMPUTATION_EXPRESSION = ToolPackage.eINSTANCE.getAcceleoVariable_ComputationExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SubVariableImpl
         * <em>Sub Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SubVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSubVariable()
         * @generated
         */
        EClass SUB_VARIABLE = ToolPackage.eINSTANCE.getSubVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DialogVariableImpl
         * <em>Dialog Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.DialogVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDialogVariable()
         * @generated
         */
        EClass DIALOG_VARIABLE = ToolPackage.eINSTANCE.getDialogVariable();

        /**
         * The meta object literal for the '<em><b>Dialog Prompt</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DIALOG_VARIABLE__DIALOG_PROMPT = ToolPackage.eINSTANCE.getDialogVariable_DialogPrompt();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementDropVariableImpl
         * <em>Element Drop Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementDropVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementDropVariable()
         * @generated
         */
        EClass ELEMENT_DROP_VARIABLE = ToolPackage.eINSTANCE.getElementDropVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementSelectVariableImpl
         * <em>Element Select Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementSelectVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementSelectVariable()
         * @generated
         */
        EClass ELEMENT_SELECT_VARIABLE = ToolPackage.eINSTANCE.getElementSelectVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementVariableImpl
         * <em>Element Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementVariable()
         * @generated
         */
        EClass ELEMENT_VARIABLE = ToolPackage.eINSTANCE.getElementVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementViewVariableImpl
         * <em>Element View Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementViewVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementViewVariable()
         * @generated
         */
        EClass ELEMENT_VIEW_VARIABLE = ToolPackage.eINSTANCE.getElementViewVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ElementDeleteVariableImpl
         * <em>Element Delete Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ElementDeleteVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getElementDeleteVariable()
         * @generated
         */
        EClass ELEMENT_DELETE_VARIABLE = ToolPackage.eINSTANCE.getElementDeleteVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DropContainerVariableImpl
         * <em>Drop Container Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.DropContainerVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDropContainerVariable()
         * @generated
         */
        EClass DROP_CONTAINER_VARIABLE = ToolPackage.eINSTANCE.getDropContainerVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectContainerVariableImpl
         * <em>Select Container Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SelectContainerVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSelectContainerVariable()
         * @generated
         */
        EClass SELECT_CONTAINER_VARIABLE = ToolPackage.eINSTANCE.getSelectContainerVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ContainerViewVariableImpl
         * <em>Container View Variable</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerViewVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getContainerViewVariable()
         * @generated
         */
        EClass CONTAINER_VIEW_VARIABLE = ToolPackage.eINSTANCE.getContainerViewVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl
         * <em>Select Model Element Variable</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SelectModelElementVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSelectModelElementVariable()
         * @generated
         */
        EClass SELECT_MODEL_ELEMENT_VARIABLE = ToolPackage.eINSTANCE.getSelectModelElementVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.EditMaskVariablesImpl
         * <em>Edit Mask Variables</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.EditMaskVariablesImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getEditMaskVariables()
         * @generated
         */
        EClass EDIT_MASK_VARIABLES = ToolPackage.eINSTANCE.getEditMaskVariables();

        /**
         * The meta object literal for the '<em><b>Mask</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDIT_MASK_VARIABLES__MASK = ToolPackage.eINSTANCE.getEditMaskVariables_Mask();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ContainerModelOperationImpl
         * <em>Container Model Operation</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ContainerModelOperationImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getContainerModelOperation()
         * @generated
         */
        EClass CONTAINER_MODEL_OPERATION = ToolPackage.eINSTANCE.getContainerModelOperation();

        /**
         * The meta object literal for the '<em><b>Sub Model Operations</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_MODEL_OPERATION__SUB_MODEL_OPERATIONS = ToolPackage.eINSTANCE.getContainerModelOperation_SubModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ModelOperationImpl
         * <em>Model Operation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ModelOperationImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getModelOperation()
         * @generated
         */
        EClass MODEL_OPERATION = ToolPackage.eINSTANCE.getModelOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialNodeCreationOperationImpl
         * <em>Initial Node Creation Operation</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitialNodeCreationOperationImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitialNodeCreationOperation()
         * @generated
         */
        EClass INITIAL_NODE_CREATION_OPERATION = ToolPackage.eINSTANCE.getInitialNodeCreationOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INITIAL_NODE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS = ToolPackage.eINSTANCE.getInitialNodeCreationOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialOperationImpl
         * <em>Initial Operation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitialOperationImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitialOperation()
         * @generated
         */
        EClass INITIAL_OPERATION = ToolPackage.eINSTANCE.getInitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INITIAL_OPERATION__FIRST_MODEL_OPERATIONS = ToolPackage.eINSTANCE.getInitialOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitEdgeCreationOperationImpl
         * <em>Init Edge Creation Operation</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitEdgeCreationOperationImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitEdgeCreationOperation()
         * @generated
         */
        EClass INIT_EDGE_CREATION_OPERATION = ToolPackage.eINSTANCE.getInitEdgeCreationOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INIT_EDGE_CREATION_OPERATION__FIRST_MODEL_OPERATIONS = ToolPackage.eINSTANCE.getInitEdgeCreationOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.InitialContainerDropOperationImpl
         * <em>Initial Container Drop Operation</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.InitialContainerDropOperationImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getInitialContainerDropOperation()
         * @generated
         */
        EClass INITIAL_CONTAINER_DROP_OPERATION = ToolPackage.eINSTANCE.getInitialContainerDropOperation();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operations</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INITIAL_CONTAINER_DROP_OPERATION__FIRST_MODEL_OPERATIONS = ToolPackage.eINSTANCE.getInitialContainerDropOperation_FirstModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl
         * <em>Create Instance</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.CreateInstanceImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getCreateInstance()
         * @generated
         */
        EClass CREATE_INSTANCE = ToolPackage.eINSTANCE.getCreateInstance();

        /**
         * The meta object literal for the '<em><b>Type Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_INSTANCE__TYPE_NAME = ToolPackage.eINSTANCE.getCreateInstance_TypeName();

        /**
         * The meta object literal for the '<em><b>Reference Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_INSTANCE__REFERENCE_NAME = ToolPackage.eINSTANCE.getCreateInstance_ReferenceName();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CREATE_INSTANCE__VARIABLE_NAME = ToolPackage.eINSTANCE.getCreateInstance_VariableName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ChangeContextImpl
         * <em>Change Context</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ChangeContextImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getChangeContext()
         * @generated
         */
        EClass CHANGE_CONTEXT = ToolPackage.eINSTANCE.getChangeContext();

        /**
         * The meta object literal for the '<em><b>Browse Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHANGE_CONTEXT__BROWSE_EXPRESSION = ToolPackage.eINSTANCE.getChangeContext_BrowseExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SetValueImpl
         * <em>Set Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SetValueImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSetValue()
         * @generated
         */
        EClass SET_VALUE = ToolPackage.eINSTANCE.getSetValue();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SET_VALUE__FEATURE_NAME = ToolPackage.eINSTANCE.getSetValue_FeatureName();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SET_VALUE__VALUE_EXPRESSION = ToolPackage.eINSTANCE.getSetValue_ValueExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SetObjectImpl
         * <em>Set Object</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SetObjectImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSetObject()
         * @generated
         */
        EClass SET_OBJECT = ToolPackage.eINSTANCE.getSetObject();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SET_OBJECT__FEATURE_NAME = ToolPackage.eINSTANCE.getSetObject_FeatureName();

        /**
         * The meta object literal for the '<em><b>Object</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SET_OBJECT__OBJECT = ToolPackage.eINSTANCE.getSetObject_Object();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.UnsetImpl
         * <em>Unset</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.UnsetImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getUnset()
         * @generated
         */
        EClass UNSET = ToolPackage.eINSTANCE.getUnset();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute UNSET__FEATURE_NAME = ToolPackage.eINSTANCE.getUnset_FeatureName();

        /**
         * The meta object literal for the '<em><b>Element Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute UNSET__ELEMENT_EXPRESSION = ToolPackage.eINSTANCE.getUnset_ElementExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.MoveElementImpl
         * <em>Move Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.MoveElementImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getMoveElement()
         * @generated
         */
        EClass MOVE_ELEMENT = ToolPackage.eINSTANCE.getMoveElement();

        /**
         * The meta object literal for the '
         * <em><b>New Container Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute MOVE_ELEMENT__NEW_CONTAINER_EXPRESSION = ToolPackage.eINSTANCE.getMoveElement_NewContainerExpression();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MOVE_ELEMENT__FEATURE_NAME = ToolPackage.eINSTANCE.getMoveElement_FeatureName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.RemoveElementImpl
         * <em>Remove Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.RemoveElementImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getRemoveElement()
         * @generated
         */
        EClass REMOVE_ELEMENT = ToolPackage.eINSTANCE.getRemoveElement();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ForImpl
         * <em>For</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ForImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getFor()
         * @generated
         */
        EClass FOR = ToolPackage.eINSTANCE.getFor();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOR__EXPRESSION = ToolPackage.eINSTANCE.getFor_Expression();

        /**
         * The meta object literal for the '<em><b>Iterator Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOR__ITERATOR_NAME = ToolPackage.eINSTANCE.getFor_IteratorName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.IfImpl
         * <em>If</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.IfImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getIf()
         * @generated
         */
        EClass IF = ToolPackage.eINSTANCE.getIf();

        /**
         * The meta object literal for the '<em><b>Condition Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute IF__CONDITION_EXPRESSION = ToolPackage.eINSTANCE.getIf_ConditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DeleteViewImpl
         * <em>Delete View</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.DeleteViewImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDeleteView()
         * @generated
         */
        EClass DELETE_VIEW = ToolPackage.eINSTANCE.getDeleteView();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.NameVariableImpl
         * <em>Name Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.NameVariableImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getNameVariable()
         * @generated
         */
        EClass NAME_VARIABLE = ToolPackage.eINSTANCE.getNameVariable();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionParameterImpl
         * <em>External Java Action Parameter</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ExternalJavaActionParameterImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getExternalJavaActionParameter()
         * @generated
         */
        EClass EXTERNAL_JAVA_ACTION_PARAMETER = ToolPackage.eINSTANCE.getExternalJavaActionParameter();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EXTERNAL_JAVA_ACTION_PARAMETER__NAME = ToolPackage.eINSTANCE.getExternalJavaActionParameter_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EXTERNAL_JAVA_ACTION_PARAMETER__VALUE = ToolPackage.eINSTANCE.getExternalJavaActionParameter_Value();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl
         * <em>Filter Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolFilterDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getToolFilterDescription()
         * @generated
         */
        EClass TOOL_FILTER_DESCRIPTION = ToolPackage.eINSTANCE.getToolFilterDescription();

        /**
         * The meta object literal for the '<em><b>Precondition</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_FILTER_DESCRIPTION__PRECONDITION = ToolPackage.eINSTANCE.getToolFilterDescription_Precondition();

        /**
         * The meta object literal for the '<em><b>Elements To Listen</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TOOL_FILTER_DESCRIPTION__ELEMENTS_TO_LISTEN = ToolPackage.eINSTANCE.getToolFilterDescription_ElementsToListen();

        /**
         * The meta object literal for the '<em><b>Listeners</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TOOL_FILTER_DESCRIPTION__LISTENERS = ToolPackage.eINSTANCE.getToolFilterDescription_Listeners();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.FeatureChangeListenerImpl
         * <em>Feature Change Listener</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.FeatureChangeListenerImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getFeatureChangeListener()
         * @generated
         */
        EClass FEATURE_CHANGE_LISTENER = ToolPackage.eINSTANCE.getFeatureChangeListener();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FEATURE_CHANGE_LISTENER__DOMAIN_CLASS = ToolPackage.eINSTANCE.getFeatureChangeListener_DomainClass();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FEATURE_CHANGE_LISTENER__FEATURE_NAME = ToolPackage.eINSTANCE.getFeatureChangeListener_FeatureName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.CaseImpl
         * <em>Case</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.CaseImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getCase()
         * @generated
         */
        EClass CASE = ToolPackage.eINSTANCE.getCase();

        /**
         * The meta object literal for the '<em><b>Condition Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CASE__CONDITION_EXPRESSION = ToolPackage.eINSTANCE.getCase_ConditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SwitchChildImpl
         * <em>Switch Child</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SwitchChildImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSwitchChild()
         * @generated
         */
        EClass SWITCH_CHILD = ToolPackage.eINSTANCE.getSwitchChild();

        /**
         * The meta object literal for the '<em><b>Sub Model Operations</b></em>
         * ' containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SWITCH_CHILD__SUB_MODEL_OPERATIONS = ToolPackage.eINSTANCE.getSwitchChild_SubModelOperations();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.DefaultImpl
         * <em>Default</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.DefaultImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDefault()
         * @generated
         */
        EClass DEFAULT = ToolPackage.eINSTANCE.getDefault();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.impl.SwitchImpl
         * <em>Switch</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.SwitchImpl
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getSwitch()
         * @generated
         */
        EClass SWITCH = ToolPackage.eINSTANCE.getSwitch();

        /**
         * The meta object literal for the '<em><b>Cases</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SWITCH__CASES = ToolPackage.eINSTANCE.getSwitch_Cases();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SWITCH__DEFAULT = ToolPackage.eINSTANCE.getSwitch_Default();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.tool.DragSource
         * <em>Drag Source</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.tool.DragSource
         * @see org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl#getDragSource()
         * @generated
         */
        EEnum DRAG_SOURCE = ToolPackage.eINSTANCE.getDragSource();

    }

} // ToolPackage
