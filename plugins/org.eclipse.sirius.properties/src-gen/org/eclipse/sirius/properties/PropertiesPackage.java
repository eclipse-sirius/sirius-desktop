/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

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
 * @see org.eclipse.sirius.properties.PropertiesFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "properties"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/properties/1.0.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "properties"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PropertiesPackage eINSTANCE = org.eclipse.sirius.properties.impl.PropertiesPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl <em>View
     * Extension Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
     * @generated
     */
    int VIEW_EXTENSION_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__NAME = DescriptionPackage.EXTENSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__LABEL = DescriptionPackage.EXTENSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__DOCUMENTATION = DescriptionPackage.EXTENSION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Metamodels</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__METAMODELS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Categories</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__CATEGORIES = DescriptionPackage.EXTENSION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>View Extension Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.EXTENSION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CategoryImpl <em>Category</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CategoryImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCategory()
     * @generated
     */
    int CATEGORY = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Pages</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY__PAGES = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Groups</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY__GROUPS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY__OVERRIDES = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Category</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CATEGORY_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractOverrideDescriptionImpl
     * <em>Abstract Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractOverrideDescription()
     * @generated
     */
    int ABSTRACT_OVERRIDE_DESCRIPTION = 2;

    /**
     * The number of structural features of the '<em>Abstract Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_OVERRIDE_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl <em>Abstract
     * Page Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractPageDescription()
     * @generated
     */
    int ABSTRACT_PAGE_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__GROUPS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__ACTIONS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__EXTENDS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Filter Groups From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Indented</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION__INDENTED = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>Abstract Page Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_PAGE_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.PageDescriptionImpl <em>Page
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
     * @generated
     */
    int PAGE_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__GROUPS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__VALIDATION_SET = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Groups From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Indented</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__INDENTED = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED;

    /**
     * The number of structural features of the '<em>Page Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl <em>Page
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageOverrideDescription()
     * @generated
     */
    int PAGE_OVERRIDE_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__GROUPS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__GROUPS;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__VALIDATION_SET = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__ACTIONS;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Groups From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Extended Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Indented</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__INDENTED = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION__INDENTED;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Groups From Overridden Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Overridden Page Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Page Override Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_PAGE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl <em>Page
     * Validation Set Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageValidationSetDescription()
     * @generated
     */
    int PAGE_VALIDATION_SET_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Semantic Validation Rules</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = 0;

    /**
     * The number of structural features of the '<em>Page Validation Set Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_VALIDATION_SET_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl <em>Property
     * Validation Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPropertyValidationRule()
     * @generated
     */
    int PROPERTY_VALIDATION_RULE = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__NAME = ValidationPackage.VALIDATION_RULE__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__LABEL = ValidationPackage.VALIDATION_RULE__LABEL;

    /**
     * The feature id for the '<em><b>Level</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__LEVEL = ValidationPackage.VALIDATION_RULE__LEVEL;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__MESSAGE = ValidationPackage.VALIDATION_RULE__MESSAGE;

    /**
     * The feature id for the '<em><b>Audits</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__AUDITS = ValidationPackage.VALIDATION_RULE__AUDITS;

    /**
     * The feature id for the '<em><b>Fixes</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__FIXES = ValidationPackage.VALIDATION_RULE__FIXES;

    /**
     * The feature id for the '<em><b>Targets</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__TARGETS = ValidationPackage.VALIDATION_RULE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Property Validation Rule</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE_FEATURE_COUNT = ValidationPackage.VALIDATION_RULE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl <em>Abstract
     * Group Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractGroupDescription()
     * @generated
     */
    int ABSTRACT_GROUP_DESCRIPTION = 8;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__CONTROLS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__STYLE = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__EXTENDS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Filter Controls From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION__ACTIONS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The number of structural features of the '<em>Abstract Group Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 14;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ToolbarActionImpl <em>Toolbar Action</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ToolbarActionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getToolbarAction()
     * @generated
     */
    int TOOLBAR_ACTION = 9;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TOOLBAR_ACTION__TOOLTIP_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TOOLBAR_ACTION__IMAGE_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOLBAR_ACTION__INITIAL_OPERATION = 2;

    /**
     * The number of structural features of the '<em>Toolbar Action</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOLBAR_ACTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl <em>Group
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
     * @generated
     */
    int GROUP_DESCRIPTION = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONTROLS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__VALIDATION_SET = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Controls From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS;

    /**
     * The number of structural features of the '<em>Group Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl <em>Group
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupOverrideDescription()
     * @generated
     */
    int GROUP_OVERRIDE_DESCRIPTION = 11;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__CONTROLS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONTROLS;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__VALIDATION_SET = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Controls From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION__ACTIONS;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Controls From Overridden Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filter Validation Rules From Overridden Group Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Group Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Group Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_GROUP_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl <em>Group
     * Validation Set Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupValidationSetDescription()
     * @generated
     */
    int GROUP_VALIDATION_SET_DESCRIPTION = 12;

    /**
     * The feature id for the '<em><b>Semantic Validation Rules</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = 0;

    /**
     * The feature id for the '<em><b>Property Validation Rules</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES = 1;

    /**
     * The number of structural features of the '<em>Group Validation Set Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_VALIDATION_SET_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractControlDescriptionImpl <em>Abstract
     * Control Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractControlDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractControlDescription()
     * @generated
     */
    int ABSTRACT_CONTROL_DESCRIPTION = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTROL_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTROL_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTROL_DESCRIPTION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Abstract Control Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ControlDescriptionImpl <em>Control
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ControlDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getControlDescription()
     * @generated
     */
    int CONTROL_DESCRIPTION = 14;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROL_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROL_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROL_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The number of structural features of the '<em>Control Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROL_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractContainerDescriptionImpl
     * <em>Abstract Container Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractContainerDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractContainerDescription()
     * @generated
     */
    int ABSTRACT_CONTAINER_DESCRIPTION = 15;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__CONTROLS = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__LAYOUT = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Filter Controls From Extended Container Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Abstract Container Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CONTAINER_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl <em>Container
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
     * @generated
     */
    int CONTAINER_DESCRIPTION = 16;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__NAME = PropertiesPackage.CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__LABEL = PropertiesPackage.CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__DOCUMENTATION = PropertiesPackage.CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__CONTROLS = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__LAYOUT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__EXTENDS = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Filter Controls From Extended Container Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Container Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ContainerOverrideDescriptionImpl
     * <em>Container Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ContainerOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerOverrideDescription()
     * @generated
     */
    int CONTAINER_OVERRIDE_DESCRIPTION = 17;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__CONTROLS = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__CONTROLS;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__LAYOUT = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__LAYOUT;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Controls From Extended Container Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Controls From Overridden Container Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Container Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CONTAINER_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.LayoutDescriptionImpl <em>Layout
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LayoutDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLayoutDescription()
     * @generated
     */
    int LAYOUT_DESCRIPTION = 18;

    /**
     * The number of structural features of the '<em>Layout Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl <em>Fill Layout
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFillLayoutDescription()
     * @generated
     */
    int FILL_LAYOUT_DESCRIPTION = 19;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILL_LAYOUT_DESCRIPTION__ORIENTATION = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Fill Layout Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILL_LAYOUT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl <em>Grid Layout
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGridLayoutDescription()
     * @generated
     */
    int GRID_LAYOUT_DESCRIPTION = 20;

    /**
     * The feature id for the '<em><b>Number Of Columns</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Make Columns With Equal Width</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Grid Layout Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRID_LAYOUT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractWidgetDescriptionImpl <em>Abstract
     * Widget Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractWidgetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractWidgetDescription()
     * @generated
     */
    int ABSTRACT_WIDGET_DESCRIPTION = 21;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Abstract Widget Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl <em>Widget
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
     * @generated
     */
    int WIDGET_DESCRIPTION = 22;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__NAME = PropertiesPackage.CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__LABEL = PropertiesPackage.CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__DOCUMENTATION = PropertiesPackage.CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The number of structural features of the '<em>Widget Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractTextDescriptionImpl <em>Abstract
     * Text Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractTextDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractTextDescription()
     * @generated
     */
    int ABSTRACT_TEXT_DESCRIPTION = 23;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Text Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Abstract Text Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextDescriptionImpl <em>Text
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
     * @generated
     */
    int TEXT_DESCRIPTION = 24;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Text Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION;

    /**
     * The number of structural features of the '<em>Text Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextOverrideDescriptionImpl <em>Text
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextOverrideDescription()
     * @generated
     */
    int TEXT_OVERRIDE_DESCRIPTION = 25;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Text Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Text Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Text Override Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_TEXT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractButtonDescriptionImpl <em>Abstract
     * Button Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractButtonDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractButtonDescription()
     * @generated
     */
    int ABSTRACT_BUTTON_DESCRIPTION = 26;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Button Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__IMAGE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Button Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Abstract Button Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_BUTTON_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl <em>Button
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ButtonDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonDescription()
     * @generated
     */
    int BUTTON_DESCRIPTION = 27;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Button Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__IMAGE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Button Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Button Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ButtonOverrideDescriptionImpl <em>Button
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ButtonOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonOverrideDescription()
     * @generated
     */
    int BUTTON_OVERRIDE_DESCRIPTION = 28;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Button Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__BUTTON_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__IMAGE_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__IMAGE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Button Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Button Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Button Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_BUTTON_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl <em>Abstract
     * Label Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractLabelDescription()
     * @generated
     */
    int ABSTRACT_LABEL_DESCRIPTION = 29;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Abstract Label Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LABEL_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl <em>Label
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LabelDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelDescription()
     * @generated
     */
    int LABEL_DESCRIPTION = 30;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__ACTIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Label Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl <em>Label
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelOverrideDescription()
     * @generated
     */
    int LABEL_OVERRIDE_DESCRIPTION = 31;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__ACTIONS;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Label Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filter Actions From Overridden Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Label Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_LABEL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl
     * <em>Abstract Checkbox Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractCheckboxDescription()
     * @generated
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION = 32;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Checkbox Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Abstract Checkbox Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CHECKBOX_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl <em>Checkbox
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxDescription()
     * @generated
     */
    int CHECKBOX_DESCRIPTION = 33;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Checkbox Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Checkbox Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CheckboxOverrideDescriptionImpl
     * <em>Checkbox Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CheckboxOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxOverrideDescription()
     * @generated
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION = 34;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Checkbox Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Checkbox Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_CHECKBOX_EXPRESSION = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Checkbox Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CHECKBOX_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl <em>Abstract
     * Select Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractSelectDescription()
     * @generated
     */
    int ABSTRACT_SELECT_DESCRIPTION = 35;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Select Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Abstract Select Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_SELECT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl <em>Select
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.SelectDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectDescription()
     * @generated
     */
    int SELECT_DESCRIPTION = 36;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Select Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>Select Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.SelectOverrideDescriptionImpl <em>Select
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.SelectOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectOverrideDescription()
     * @generated
     */
    int SELECT_OVERRIDE_DESCRIPTION = 37;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Select Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Select Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_SELECT_EXPRESSION = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Select Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_SELECT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl
     * <em>Abstract Dynamic Mapping For Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractDynamicMappingForDescription()
     * @generated
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION = 38;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Iterator</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Iterable Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Ifs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filter Ifs From Extended Dynamic Mapping For Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Abstract Dynamic Mapping For Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CONTROL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.DynamicMappingForDescriptionImpl
     * <em>Dynamic Mapping For Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.DynamicMappingForDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingForDescription()
     * @generated
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION = 39;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__NAME = PropertiesPackage.CONTROL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__LABEL = PropertiesPackage.CONTROL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__DOCUMENTATION = PropertiesPackage.CONTROL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Iterator</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Iterable Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Ifs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filter Ifs From Extended Dynamic Mapping For Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Dynamic Mapping For Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.DynamicMappingForOverrideDescriptionImpl
     * <em>Dynamic Mapping For Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.DynamicMappingForOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingForOverrideDescription()
     * @generated
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION = 40;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Iterator</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__ITERATOR = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR;

    /**
     * The feature id for the '<em><b>Iterable Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__ITERABLE_EXPRESSION = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FORCE_REFRESH = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Ifs</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__IFS = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Ifs From Extended Dynamic Mapping For Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Ifs From Overridden Dynamic Mapping For Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Dynamic Mapping For Override Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl
     * <em>Abstract Dynamic Mapping If Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractDynamicMappingIfDescription()
     * @generated
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION = 41;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Widget</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Abstract Dynamic Mapping If Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.DynamicMappingIfDescriptionImpl <em>Dynamic
     * Mapping If Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.DynamicMappingIfDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingIfDescription()
     * @generated
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION = 42;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Widget</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS;

    /**
     * The number of structural features of the '<em>Dynamic Mapping If Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.DynamicMappingIfOverrideDescriptionImpl
     * <em>Dynamic Mapping If Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.DynamicMappingIfOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingIfOverrideDescription()
     * @generated
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION = 43;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__PREDICATE_EXPRESSION = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Widget</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__WIDGET = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Dynamic Mapping If Override Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl
     * <em>Abstract Text Area Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractTextAreaDescription()
     * @generated
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION = 44;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Line Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Text Area Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Abstract Text Area Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_TEXT_AREA_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl <em>Text Area
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextAreaDescription()
     * @generated
     */
    int TEXT_AREA_DESCRIPTION = 45;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Line Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__LINE_COUNT = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Text Area Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION;

    /**
     * The number of structural features of the '<em>Text Area Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextAreaOverrideDescriptionImpl <em>Text
     * Area Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextAreaOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextAreaOverrideDescription()
     * @generated
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION = 46;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Line Count</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__LINE_COUNT = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Text Area Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Text Area Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Text Area Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_TEXT_AREA_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl <em>Abstract
     * Radio Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractRadioDescription()
     * @generated
     */
    int ABSTRACT_RADIO_DESCRIPTION = 47;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Number Of Columns</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Radio Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Abstract Radio Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_RADIO_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.RadioDescriptionImpl <em>Radio
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.RadioDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioDescription()
     * @generated
     */
    int RADIO_DESCRIPTION = 48;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Number Of Columns</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Radio Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>Radio Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.RadioOverrideDescriptionImpl <em>Radio
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.RadioOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioOverrideDescription()
     * @generated
     */
    int RADIO_OVERRIDE_DESCRIPTION = 49;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Number Of Columns</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Radio Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Radio Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Radio Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_RADIO_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl <em>Abstract
     * List Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractListDescription()
     * @generated
     */
    int ABSTRACT_LIST_DESCRIPTION = 50;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Abstract List Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LIST_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl <em>List
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListDescription()
     * @generated
     */
    int LIST_DESCRIPTION = 51;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__ACTIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>List Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl <em>List
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListOverrideDescription()
     * @generated
     */
    int LIST_OVERRIDE_DESCRIPTION = 52;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__ACTIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden List Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filter Actions From Overridden List Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>List Override Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_LIST_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.OperationDescriptionImpl <em>Operation
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.OperationDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getOperationDescription()
     * @generated
     */
    int OPERATION_DESCRIPTION = 53;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_DESCRIPTION__INITIAL_OPERATION = 0;

    /**
     * The number of structural features of the '<em>Operation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl <em>Abstract
     * Custom Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractCustomDescription()
     * @generated
     */
    int ABSTRACT_CUSTOM_DESCRIPTION = 54;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Custom Expressions</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Custom Operations</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Custom Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Abstract Custom Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_CUSTOM_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CustomDescriptionImpl <em>Custom
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomDescription()
     * @generated
     */
    int CUSTOM_DESCRIPTION = 55;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Custom Expressions</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Custom Operations</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Custom Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Custom Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CustomOverrideDescriptionImpl <em>Custom
     * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomOverrideDescription()
     * @generated
     */
    int CUSTOM_OVERRIDE_DESCRIPTION = 56;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Custom Expressions</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__CUSTOM_EXPRESSIONS = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS;

    /**
     * The feature id for the '<em><b>Custom Operations</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__CUSTOM_OPERATIONS = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Custom Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Custom Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_CUSTOM_EXPRESSION = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Custom Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_CUSTOM_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CustomExpressionImpl <em>Custom
     * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomExpressionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomExpression()
     * @generated
     */
    int CUSTOM_EXPRESSION = 57;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Custom Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION__CUSTOM_EXPRESSION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Custom Expression</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CustomOperationImpl <em>Custom
     * Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomOperationImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomOperation()
     * @generated
     */
    int CUSTOM_OPERATION = 58;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION__NAME = DescriptionPackage.IDENTIFIED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION__LABEL = DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION__DOCUMENTATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION__INITIAL_OPERATION = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Custom Operation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION_FEATURE_COUNT = DescriptionPackage.IDENTIFIED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl
     * <em>Abstract Hyperlink Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractHyperlinkDescription()
     * @generated
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION = 59;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Hyperlink Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended Hyperlink Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Abstract Hyperlink Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_HYPERLINK_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl <em>Hyperlink
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkDescription()
     * @generated
     */
    int HYPERLINK_DESCRIPTION = 60;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__NAME = PropertiesPackage.WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__LABEL = PropertiesPackage.WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__DOCUMENTATION = PropertiesPackage.WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__ACTIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__EXTENDS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Hyperlink Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended Hyperlink Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>Hyperlink Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl
     * <em>Hyperlink Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkOverrideDescription()
     * @generated
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION = 61;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__ACTIONS = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Extended Hyperlink Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION;

    /**
     * The feature id for the '<em><b>Filter Actions From Extended Hyperlink Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Hyperlink Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filter Actions From Overridden Hyperlink Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Hyperlink Override Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_HYPERLINK_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WidgetStyleImpl <em>Widget Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetStyle()
     * @generated
     */
    int WIDGET_STYLE = 62;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_BACKGROUND_COLOR = 2;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FOREGROUND_COLOR = 3;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FONT_FORMAT = 4;

    /**
     * The number of structural features of the '<em>Widget Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextWidgetStyleImpl <em>Text Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetStyle()
     * @generated
     */
    int TEXT_WIDGET_STYLE = 63;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Text Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl <em>Label Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetStyle()
     * @generated
     */
    int LABEL_WIDGET_STYLE = 64;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Label Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl <em>Checkbox Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetStyle()
     * @generated
     */
    int CHECKBOX_WIDGET_STYLE = 65;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Checkbox Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl <em>Radio Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetStyle()
     * @generated
     */
    int RADIO_WIDGET_STYLE = 66;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Radio Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl <em>Button Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetStyle()
     * @generated
     */
    int BUTTON_WIDGET_STYLE = 67;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Button Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl <em>Select Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetStyle()
     * @generated
     */
    int SELECT_WIDGET_STYLE = 68;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Select Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl <em>Custom Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetStyle()
     * @generated
     */
    int CUSTOM_WIDGET_STYLE = 69;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Custom Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ListWidgetStyleImpl <em>List Widget
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetStyle()
     * @generated
     */
    int LIST_WIDGET_STYLE = 70;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>List Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl <em>Hyperlink
     * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetStyle()
     * @generated
     */
    int HYPERLINK_WIDGET_STYLE = 71;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Hyperlink Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupStyleImpl <em>Group Style</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupStyle()
     * @generated
     */
    int GROUP_STYLE = 72;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__BACKGROUND_COLOR = 0;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__FOREGROUND_COLOR = 1;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__FONT_NAME_EXPRESSION = 2;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__FONT_SIZE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Bar Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__BAR_STYLE = 4;

    /**
     * The feature id for the '<em><b>Toggle Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__TOGGLE_STYLE = 5;

    /**
     * The feature id for the '<em><b>Expanded By Default</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__EXPANDED_BY_DEFAULT = 6;

    /**
     * The number of structural features of the '<em>Group Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl <em>Widget
     * Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetConditionalStyle()
     * @generated
     */
    int WIDGET_CONDITIONAL_STYLE = 73;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = 0;

    /**
     * The number of structural features of the '<em>Widget Conditional Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl <em>Text
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetConditionalStyle()
     * @generated
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE = 74;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Text Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl <em>Label
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetConditionalStyle()
     * @generated
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE = 75;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
     * <em>Checkbox Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetConditionalStyle()
     * @generated
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE = 76;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Checkbox Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl <em>Radio
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetConditionalStyle()
     * @generated
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE = 77;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Radio Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl <em>Button
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetConditionalStyle()
     * @generated
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE = 78;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Button Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl <em>Select
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetConditionalStyle()
     * @generated
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE = 79;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Select Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl <em>Custom
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetConditionalStyle()
     * @generated
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE = 80;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Custom Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl <em>List
     * Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetConditionalStyle()
     * @generated
     */
    int LIST_WIDGET_CONDITIONAL_STYLE = 81;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>List Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WidgetActionImpl <em>Widget Action</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetActionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetAction()
     * @generated
     */
    int WIDGET_ACTION = 82;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION__LABEL_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION__IMAGE_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION__INITIAL_OPERATION = 2;

    /**
     * The number of structural features of the '<em>Widget Action</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
     * <em>Hyperlink Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetConditionalStyle()
     * @generated
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE = 83;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Hyperlink Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl <em>Group
     * Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupConditionalStyle()
     * @generated
     */
    int GROUP_CONDITIONAL_STYLE = 84;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int GROUP_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Group Conditional Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.DialogModelOperationImpl <em>Dialog Model
     * Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.DialogModelOperationImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDialogModelOperation()
     * @generated
     */
    int DIALOG_MODEL_OPERATION = 85;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_MODEL_OPERATION__TITLE_EXPRESSION = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Buttons</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_MODEL_OPERATION__BUTTONS = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Page</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_MODEL_OPERATION__PAGE = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Groups</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_MODEL_OPERATION__GROUPS = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Dialog Model Operation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_MODEL_OPERATION_FEATURE_COUNT = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.DialogButtonImpl <em>Dialog Button</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.DialogButtonImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDialogButton()
     * @generated
     */
    int DIALOG_BUTTON = 86;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON__LABEL_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON__IS_ENABLED_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON__INITIAL_OPERATION = 2;

    /**
     * The feature id for the '<em><b>Default</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON__DEFAULT = 3;

    /**
     * The feature id for the '<em><b>Close Dialog On Click</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK = 4;

    /**
     * The feature id for the '<em><b>Rollback Changes On Close</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE = 5;

    /**
     * The number of structural features of the '<em>Dialog Button</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIALOG_BUTTON_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl <em>Wizard Model
     * Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.WizardModelOperationImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWizardModelOperation()
     * @generated
     */
    int WIZARD_MODEL_OPERATION = 87;

    /**
     * The feature id for the '<em><b>Window Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__TITLE_EXPRESSION = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Description Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Is Page Complete Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Pages</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__PAGES = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Groups</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__GROUPS = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION__INITIAL_OPERATION = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Wizard Model Operation</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIZARD_MODEL_OPERATION_FEATURE_COUNT = ToolPackage.MODEL_OPERATION_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.impl.EditSupportImpl <em>Edit Support</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.EditSupportImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getEditSupport()
     * @generated
     */
    int EDIT_SUPPORT = 88;

    /**
     * The number of structural features of the '<em>Edit Support</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDIT_SUPPORT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION <em>FILL LAYOUT
     * ORIENTATION</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFILL_LAYOUT_ORIENTATION()
     * @generated
     */
    int FILL_LAYOUT_ORIENTATION = 89;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.ToggleStyle <em>Toggle Style</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ToggleStyle
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getToggleStyle()
     * @generated
     */
    int TOGGLE_STYLE = 90;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.properties.TitleBarStyle <em>Title Bar Style</em>}' enum.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.TitleBarStyle
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTitleBarStyle()
     * @generated
     */
    int TITLE_BAR_STYLE = 91;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ViewExtensionDescription <em>View
     * Extension Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>View Extension Description</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription
     * @generated
     */
    EClass getViewExtensionDescription();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels <em>Metamodels</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Metamodels</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Metamodels();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getCategories <em>Categories</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Categories</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getCategories()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Categories();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.Category <em>Category</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Category</em>'.
     * @see org.eclipse.sirius.properties.Category
     * @generated
     */
    EClass getCategory();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.Category#getPages <em>Pages</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Pages</em>'.
     * @see org.eclipse.sirius.properties.Category#getPages()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Pages();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.Category#getGroups <em>Groups</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.Category#getGroups()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Groups();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.Category#getOverrides <em>Overrides</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.Category#getOverrides()
     * @see #getCategory()
     * @generated
     */
    EReference getCategory_Overrides();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractOverrideDescription <em>Abstract
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Override Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractOverrideDescription
     * @generated
     */
    EClass getAbstractOverrideDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractPageDescription <em>Abstract Page
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Page Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription
     * @generated
     */
    EClass getAbstractPageDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractPageDescription#getLabelExpression <em>Label Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getLabelExpression()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractPageDescription#getDomainClass <em>Domain Class</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getDomainClass()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_DomainClass();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getSemanticCandidateExpression <em>Semantic
     * Candidate Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getSemanticCandidateExpression()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getPreconditionExpression <em>Precondition
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getPreconditionExpression()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_PreconditionExpression();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getGroups <em>Groups</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getGroups()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EReference getAbstractPageDescription_Groups();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.AbstractPageDescription#getValidationSet <em>Validation Set</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Validation Set</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getValidationSet()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EReference getAbstractPageDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getActions <em>Actions</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getActions()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EReference getAbstractPageDescription_Actions();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getExtends <em>Extends</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getExtends()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EReference getAbstractPageDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getFilterGroupsFromExtendedPageExpression
     * <em>Filter Groups From Extended Page Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Groups From Extended Page Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getFilterGroupsFromExtendedPageExpression()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_FilterGroupsFromExtendedPageExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#getFilterValidationRulesFromExtendedPageExpression
     * <em>Filter Validation Rules From Extended Page Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Validation Rules From Extended Page Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#getFilterValidationRulesFromExtendedPageExpression()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_FilterValidationRulesFromExtendedPageExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractPageDescription#isIndented <em>Indented</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Indented</em>'.
     * @see org.eclipse.sirius.properties.AbstractPageDescription#isIndented()
     * @see #getAbstractPageDescription()
     * @generated
     */
    EAttribute getAbstractPageDescription_Indented();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.PageDescription <em>Page
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Page Description</em>'.
     * @see org.eclipse.sirius.properties.PageDescription
     * @generated
     */
    EClass getPageDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.PageOverrideDescription <em>Page Override
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Page Override Description</em>'.
     * @see org.eclipse.sirius.properties.PageOverrideDescription
     * @generated
     */
    EClass getPageOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.PageOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.PageOverrideDescription#getOverrides()
     * @see #getPageOverrideDescription()
     * @generated
     */
    EReference getPageOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.PageOverrideDescription#getFilterGroupsFromOverriddenPageExpression
     * <em>Filter Groups From Overridden Page Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Groups From Overridden Page Expression</em>'.
     * @see org.eclipse.sirius.properties.PageOverrideDescription#getFilterGroupsFromOverriddenPageExpression()
     * @see #getPageOverrideDescription()
     * @generated
     */
    EAttribute getPageOverrideDescription_FilterGroupsFromOverriddenPageExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.PageOverrideDescription#getFilterValidationRulesFromOverriddenPageExpression
     * <em>Filter Validation Rules From Overridden Page Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Validation Rules From Overridden Page Expression</em>'.
     * @see org.eclipse.sirius.properties.PageOverrideDescription#getFilterValidationRulesFromOverriddenPageExpression()
     * @see #getPageOverrideDescription()
     * @generated
     */
    EAttribute getPageOverrideDescription_FilterValidationRulesFromOverriddenPageExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.PageValidationSetDescription <em>Page
     * Validation Set Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Page Validation Set Description</em>'.
     * @see org.eclipse.sirius.properties.PageValidationSetDescription
     * @generated
     */
    EClass getPageValidationSetDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.PageValidationSetDescription#getSemanticValidationRules <em>Semantic
     * Validation Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Semantic Validation Rules</em>'.
     * @see org.eclipse.sirius.properties.PageValidationSetDescription#getSemanticValidationRules()
     * @see #getPageValidationSetDescription()
     * @generated
     */
    EReference getPageValidationSetDescription_SemanticValidationRules();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.PropertyValidationRule <em>Property
     * Validation Rule</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Property Validation Rule</em>'.
     * @see org.eclipse.sirius.properties.PropertyValidationRule
     * @generated
     */
    EClass getPropertyValidationRule();

    /**
     * Returns the meta object for the reference list
     * '{@link org.eclipse.sirius.properties.PropertyValidationRule#getTargets <em>Targets</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Targets</em>'.
     * @see org.eclipse.sirius.properties.PropertyValidationRule#getTargets()
     * @see #getPropertyValidationRule()
     * @generated
     */
    EReference getPropertyValidationRule_Targets();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractGroupDescription <em>Abstract
     * Group Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Group Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription
     * @generated
     */
    EClass getAbstractGroupDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getLabelExpression()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractGroupDescription#getDomainClass <em>Domain Class</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getDomainClass()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_DomainClass();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getSemanticCandidateExpression <em>Semantic
     * Candidate Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getSemanticCandidateExpression()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getPreconditionExpression <em>Precondition
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getPreconditionExpression()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_PreconditionExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.AbstractGroupDescription#getControls <em>Controls</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Controls</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getControls()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EReference getAbstractGroupDescription_Controls();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.AbstractGroupDescription#getValidationSet <em>Validation Set</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Validation Set</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getValidationSet()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EReference getAbstractGroupDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getStyle()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EReference getAbstractGroupDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getConditionalStyles()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EReference getAbstractGroupDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getExtends <em>Extends</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getExtends()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EReference getAbstractGroupDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getFilterControlsFromExtendedGroupExpression
     * <em>Filter Controls From Extended Group Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Controls From Extended Group Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getFilterControlsFromExtendedGroupExpression()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_FilterControlsFromExtendedGroupExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getFilterValidationRulesFromExtendedGroupExpression
     * <em>Filter Validation Rules From Extended Group Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Validation Rules From Extended Group Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getFilterValidationRulesFromExtendedGroupExpression()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_FilterValidationRulesFromExtendedGroupExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractGroupDescription#getFilterConditionalStylesFromExtendedGroupExpression
     * <em>Filter Conditional Styles From Extended Group Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Group Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getFilterConditionalStylesFromExtendedGroupExpression()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EAttribute getAbstractGroupDescription_FilterConditionalStylesFromExtendedGroupExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractGroupDescription#getActions <em>Actions</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.eclipse.sirius.properties.AbstractGroupDescription#getActions()
     * @see #getAbstractGroupDescription()
     * @generated
     */
    EReference getAbstractGroupDescription_Actions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ToolbarAction <em>Toolbar Action</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Toolbar Action</em>'.
     * @see org.eclipse.sirius.properties.ToolbarAction
     * @generated
     */
    EClass getToolbarAction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ToolbarAction#getTooltipExpression <em>Tooltip Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Tooltip Expression</em>'.
     * @see org.eclipse.sirius.properties.ToolbarAction#getTooltipExpression()
     * @see #getToolbarAction()
     * @generated
     */
    EAttribute getToolbarAction_TooltipExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.ToolbarAction#getImageExpression
     * <em>Image Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Image Expression</em>'.
     * @see org.eclipse.sirius.properties.ToolbarAction#getImageExpression()
     * @see #getToolbarAction()
     * @generated
     */
    EAttribute getToolbarAction_ImageExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ToolbarAction#getInitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.ToolbarAction#getInitialOperation()
     * @see #getToolbarAction()
     * @generated
     */
    EReference getToolbarAction_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GroupDescription <em>Group
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Description</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription
     * @generated
     */
    EClass getGroupDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GroupOverrideDescription <em>Group
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Override Description</em>'.
     * @see org.eclipse.sirius.properties.GroupOverrideDescription
     * @generated
     */
    EClass getGroupOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.GroupOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.GroupOverrideDescription#getOverrides()
     * @see #getGroupOverrideDescription()
     * @generated
     */
    EReference getGroupOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterControlsFromOverriddenGroupExpression
     * <em>Filter Controls From Overridden Group Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Controls From Overridden Group Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupOverrideDescription#getFilterControlsFromOverriddenGroupExpression()
     * @see #getGroupOverrideDescription()
     * @generated
     */
    EAttribute getGroupOverrideDescription_FilterControlsFromOverriddenGroupExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterValidationRulesFromOverriddenGroupExpression
     * <em>Filter Validation Rules From Overridden Group Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Validation Rules From Overridden Group Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupOverrideDescription#getFilterValidationRulesFromOverriddenGroupExpression()
     * @see #getGroupOverrideDescription()
     * @generated
     */
    EAttribute getGroupOverrideDescription_FilterValidationRulesFromOverriddenGroupExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterConditionalStylesFromOverriddenGroupExpression
     * <em>Filter Conditional Styles From Overridden Group Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Group Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupOverrideDescription#getFilterConditionalStylesFromOverriddenGroupExpression()
     * @see #getGroupOverrideDescription()
     * @generated
     */
    EAttribute getGroupOverrideDescription_FilterConditionalStylesFromOverriddenGroupExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GroupValidationSetDescription <em>Group
     * Validation Set Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Validation Set Description</em>'.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription
     * @generated
     */
    EClass getGroupValidationSetDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.GroupValidationSetDescription#getSemanticValidationRules <em>Semantic
     * Validation Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Semantic Validation Rules</em>'.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription#getSemanticValidationRules()
     * @see #getGroupValidationSetDescription()
     * @generated
     */
    EReference getGroupValidationSetDescription_SemanticValidationRules();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.GroupValidationSetDescription#getPropertyValidationRules <em>Property
     * Validation Rules</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Property Validation Rules</em>'.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription#getPropertyValidationRules()
     * @see #getGroupValidationSetDescription()
     * @generated
     */
    EReference getGroupValidationSetDescription_PropertyValidationRules();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractControlDescription <em>Abstract
     * Control Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Control Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractControlDescription
     * @generated
     */
    EClass getAbstractControlDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ControlDescription <em>Control
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Control Description</em>'.
     * @see org.eclipse.sirius.properties.ControlDescription
     * @generated
     */
    EClass getControlDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractContainerDescription <em>Abstract
     * Container Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Container Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractContainerDescription
     * @generated
     */
    EClass getAbstractContainerDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.AbstractContainerDescription#getControls <em>Controls</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Controls</em>'.
     * @see org.eclipse.sirius.properties.AbstractContainerDescription#getControls()
     * @see #getAbstractContainerDescription()
     * @generated
     */
    EReference getAbstractContainerDescription_Controls();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.AbstractContainerDescription#getLayout <em>Layout</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see org.eclipse.sirius.properties.AbstractContainerDescription#getLayout()
     * @see #getAbstractContainerDescription()
     * @generated
     */
    EReference getAbstractContainerDescription_Layout();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.AbstractContainerDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractContainerDescription#getExtends()
     * @see #getAbstractContainerDescription()
     * @generated
     */
    EReference getAbstractContainerDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractContainerDescription#getFilterControlsFromExtendedContainerExpression
     * <em>Filter Controls From Extended Container Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Controls From Extended Container Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractContainerDescription#getFilterControlsFromExtendedContainerExpression()
     * @see #getAbstractContainerDescription()
     * @generated
     */
    EAttribute getAbstractContainerDescription_FilterControlsFromExtendedContainerExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ContainerDescription <em>Container
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Description</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription
     * @generated
     */
    EClass getContainerDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ContainerOverrideDescription
     * <em>Container Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Container Override Description</em>'.
     * @see org.eclipse.sirius.properties.ContainerOverrideDescription
     * @generated
     */
    EClass getContainerOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.ContainerOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.ContainerOverrideDescription#getOverrides()
     * @see #getContainerOverrideDescription()
     * @generated
     */
    EReference getContainerOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.ContainerOverrideDescription#getFilterControlsFromOverriddenContainerExpression
     * <em>Filter Controls From Overridden Container Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Controls From Overridden Container Expression</em>'.
     * @see org.eclipse.sirius.properties.ContainerOverrideDescription#getFilterControlsFromOverriddenContainerExpression()
     * @see #getContainerOverrideDescription()
     * @generated
     */
    EAttribute getContainerOverrideDescription_FilterControlsFromOverriddenContainerExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.LayoutDescription <em>Layout
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Layout Description</em>'.
     * @see org.eclipse.sirius.properties.LayoutDescription
     * @generated
     */
    EClass getLayoutDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.FillLayoutDescription <em>Fill Layout
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Fill Layout Description</em>'.
     * @see org.eclipse.sirius.properties.FillLayoutDescription
     * @generated
     */
    EClass getFillLayoutDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.FillLayoutDescription#getOrientation <em>Orientation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see org.eclipse.sirius.properties.FillLayoutDescription#getOrientation()
     * @see #getFillLayoutDescription()
     * @generated
     */
    EAttribute getFillLayoutDescription_Orientation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GridLayoutDescription <em>Grid Layout
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Grid Layout Description</em>'.
     * @see org.eclipse.sirius.properties.GridLayoutDescription
     * @generated
     */
    EClass getGridLayoutDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GridLayoutDescription#getNumberOfColumns <em>Number Of Columns</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Number Of Columns</em>'.
     * @see org.eclipse.sirius.properties.GridLayoutDescription#getNumberOfColumns()
     * @see #getGridLayoutDescription()
     * @generated
     */
    EAttribute getGridLayoutDescription_NumberOfColumns();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.GridLayoutDescription#isMakeColumnsWithEqualWidth <em>Make Columns With
     * Equal Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Make Columns With Equal Width</em>'.
     * @see org.eclipse.sirius.properties.GridLayoutDescription#isMakeColumnsWithEqualWidth()
     * @see #getGridLayoutDescription()
     * @generated
     */
    EAttribute getGridLayoutDescription_MakeColumnsWithEqualWidth();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractWidgetDescription <em>Abstract
     * Widget Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Widget Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractWidgetDescription
     * @generated
     */
    EClass getAbstractWidgetDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractWidgetDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractWidgetDescription#getLabelExpression()
     * @see #getAbstractWidgetDescription()
     * @generated
     */
    EAttribute getAbstractWidgetDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractWidgetDescription#getHelpExpression <em>Help Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Help Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractWidgetDescription#getHelpExpression()
     * @see #getAbstractWidgetDescription()
     * @generated
     */
    EAttribute getAbstractWidgetDescription_HelpExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractWidgetDescription#getIsEnabledExpression <em>Is Enabled
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Enabled Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractWidgetDescription#getIsEnabledExpression()
     * @see #getAbstractWidgetDescription()
     * @generated
     */
    EAttribute getAbstractWidgetDescription_IsEnabledExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.WidgetDescription <em>Widget
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Description</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription
     * @generated
     */
    EClass getWidgetDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractTextDescription <em>Abstract Text
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Text Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription
     * @generated
     */
    EClass getAbstractTextDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractTextDescription#getValueExpression <em>Value Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription#getValueExpression()
     * @see #getAbstractTextDescription()
     * @generated
     */
    EAttribute getAbstractTextDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractTextDescription#getInitialOperation <em>Initial Operation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription#getInitialOperation()
     * @see #getAbstractTextDescription()
     * @generated
     */
    EReference getAbstractTextDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractTextDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription#getStyle()
     * @see #getAbstractTextDescription()
     * @generated
     */
    EReference getAbstractTextDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractTextDescription#getConditionalStyles <em>Conditional Styles</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription#getConditionalStyles()
     * @see #getAbstractTextDescription()
     * @generated
     */
    EReference getAbstractTextDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractTextDescription#getExtends <em>Extends</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription#getExtends()
     * @see #getAbstractTextDescription()
     * @generated
     */
    EReference getAbstractTextDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractTextDescription#getFilterConditionalStylesFromExtendedTextExpression
     * <em>Filter Conditional Styles From Extended Text Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Text Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextDescription#getFilterConditionalStylesFromExtendedTextExpression()
     * @see #getAbstractTextDescription()
     * @generated
     */
    EAttribute getAbstractTextDescription_FilterConditionalStylesFromExtendedTextExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextDescription <em>Text
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Text Description</em>'.
     * @see org.eclipse.sirius.properties.TextDescription
     * @generated
     */
    EClass getTextDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextOverrideDescription <em>Text Override
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Text Override Description</em>'.
     * @see org.eclipse.sirius.properties.TextOverrideDescription
     * @generated
     */
    EClass getTextOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.TextOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.TextOverrideDescription#getOverrides()
     * @see #getTextOverrideDescription()
     * @generated
     */
    EReference getTextOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextOverrideDescription#getFilterConditionalStylesFromOverriddenTextExpression
     * <em>Filter Conditional Styles From Overridden Text Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Text Expression</em>'.
     * @see org.eclipse.sirius.properties.TextOverrideDescription#getFilterConditionalStylesFromOverriddenTextExpression()
     * @see #getTextOverrideDescription()
     * @generated
     */
    EAttribute getTextOverrideDescription_FilterConditionalStylesFromOverriddenTextExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractButtonDescription <em>Abstract
     * Button Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Button Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription
     * @generated
     */
    EClass getAbstractButtonDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getButtonLabelExpression <em>Button Label
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Button Label Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getButtonLabelExpression()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EAttribute getAbstractButtonDescription_ButtonLabelExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getImageExpression <em>Image Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Image Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getImageExpression()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EAttribute getAbstractButtonDescription_ImageExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getInitialOperation <em>Initial Operation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getInitialOperation()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EReference getAbstractButtonDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getStyle()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EReference getAbstractButtonDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getConditionalStyles()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EReference getAbstractButtonDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractButtonDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getExtends()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EReference getAbstractButtonDescription_Extends();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractButtonDescription#getFilterConditionalStylesFromExtendedButtonExpression
     * <em>Filter Conditional Styles From Extended Button Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Button Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractButtonDescription#getFilterConditionalStylesFromExtendedButtonExpression()
     * @see #getAbstractButtonDescription()
     * @generated
     */
    EAttribute getAbstractButtonDescription_FilterConditionalStylesFromExtendedButtonExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ButtonDescription <em>Button
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Button Description</em>'.
     * @see org.eclipse.sirius.properties.ButtonDescription
     * @generated
     */
    EClass getButtonDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ButtonOverrideDescription <em>Button
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Button Override Description</em>'.
     * @see org.eclipse.sirius.properties.ButtonOverrideDescription
     * @generated
     */
    EClass getButtonOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.ButtonOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.ButtonOverrideDescription#getOverrides()
     * @see #getButtonOverrideDescription()
     * @generated
     */
    EReference getButtonOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ButtonOverrideDescription#getFilterConditionalStylesFromOverriddenButtonExpression
     * <em>Filter Conditional Styles From Overridden Button Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Button Expression</em>'.
     * @see org.eclipse.sirius.properties.ButtonOverrideDescription#getFilterConditionalStylesFromOverriddenButtonExpression()
     * @see #getButtonOverrideDescription()
     * @generated
     */
    EAttribute getButtonOverrideDescription_FilterConditionalStylesFromOverriddenButtonExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractLabelDescription <em>Abstract
     * Label Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Label Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription
     * @generated
     */
    EClass getAbstractLabelDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getValueExpression()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EAttribute getAbstractLabelDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getDisplayExpression <em>Display
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getDisplayExpression()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EAttribute getAbstractLabelDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getStyle()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EReference getAbstractLabelDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getConditionalStyles()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EReference getAbstractLabelDescription_ConditionalStyles();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getActions <em>Actions</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getActions()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EReference getAbstractLabelDescription_Actions();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getExtends <em>Extends</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getExtends()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EReference getAbstractLabelDescription_Extends();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractLabelDescription#getFilterConditionalStylesFromExtendedLabelExpression
     * <em>Filter Conditional Styles From Extended Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Label Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getFilterConditionalStylesFromExtendedLabelExpression()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EAttribute getAbstractLabelDescription_FilterConditionalStylesFromExtendedLabelExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractLabelDescription#getFilterActionsFromExtendedLabelExpression
     * <em>Filter Actions From Extended Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Actions From Extended Label Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractLabelDescription#getFilterActionsFromExtendedLabelExpression()
     * @see #getAbstractLabelDescription()
     * @generated
     */
    EAttribute getAbstractLabelDescription_FilterActionsFromExtendedLabelExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.LabelDescription <em>Label
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Description</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription
     * @generated
     */
    EClass getLabelDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.LabelOverrideDescription <em>Label
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Override Description</em>'.
     * @see org.eclipse.sirius.properties.LabelOverrideDescription
     * @generated
     */
    EClass getLabelOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.LabelOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.LabelOverrideDescription#getOverrides()
     * @see #getLabelOverrideDescription()
     * @generated
     */
    EReference getLabelOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelOverrideDescription#getFilterConditionalStylesFromOverriddenLabelExpression
     * <em>Filter Conditional Styles From Overridden Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Label Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelOverrideDescription#getFilterConditionalStylesFromOverriddenLabelExpression()
     * @see #getLabelOverrideDescription()
     * @generated
     */
    EAttribute getLabelOverrideDescription_FilterConditionalStylesFromOverriddenLabelExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.LabelOverrideDescription#getFilterActionsFromOverriddenLabelExpression
     * <em>Filter Actions From Overridden Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Actions From Overridden Label Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelOverrideDescription#getFilterActionsFromOverriddenLabelExpression()
     * @see #getLabelOverrideDescription()
     * @generated
     */
    EAttribute getLabelOverrideDescription_FilterActionsFromOverriddenLabelExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractCheckboxDescription <em>Abstract
     * Checkbox Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Checkbox Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription
     * @generated
     */
    EClass getAbstractCheckboxDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractCheckboxDescription#getValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription#getValueExpression()
     * @see #getAbstractCheckboxDescription()
     * @generated
     */
    EAttribute getAbstractCheckboxDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractCheckboxDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription#getInitialOperation()
     * @see #getAbstractCheckboxDescription()
     * @generated
     */
    EReference getAbstractCheckboxDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractCheckboxDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription#getStyle()
     * @see #getAbstractCheckboxDescription()
     * @generated
     */
    EReference getAbstractCheckboxDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractCheckboxDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription#getConditionalStyles()
     * @see #getAbstractCheckboxDescription()
     * @generated
     */
    EReference getAbstractCheckboxDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.AbstractCheckboxDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription#getExtends()
     * @see #getAbstractCheckboxDescription()
     * @generated
     */
    EReference getAbstractCheckboxDescription_Extends();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractCheckboxDescription#getFilterConditionalStylesFromExtendedCheckboxExpression
     * <em>Filter Conditional Styles From Extended Checkbox Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Checkbox Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractCheckboxDescription#getFilterConditionalStylesFromExtendedCheckboxExpression()
     * @see #getAbstractCheckboxDescription()
     * @generated
     */
    EAttribute getAbstractCheckboxDescription_FilterConditionalStylesFromExtendedCheckboxExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CheckboxDescription <em>Checkbox
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Checkbox Description</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription
     * @generated
     */
    EClass getCheckboxDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CheckboxOverrideDescription <em>Checkbox
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Checkbox Override Description</em>'.
     * @see org.eclipse.sirius.properties.CheckboxOverrideDescription
     * @generated
     */
    EClass getCheckboxOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.CheckboxOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.CheckboxOverrideDescription#getOverrides()
     * @see #getCheckboxOverrideDescription()
     * @generated
     */
    EReference getCheckboxOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.CheckboxOverrideDescription#getFilterConditionalStylesFromOverriddenCheckboxExpression
     * <em>Filter Conditional Styles From Overridden Checkbox Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Checkbox
     *         Expression</em>'.
     * @see org.eclipse.sirius.properties.CheckboxOverrideDescription#getFilterConditionalStylesFromOverriddenCheckboxExpression()
     * @see #getCheckboxOverrideDescription()
     * @generated
     */
    EAttribute getCheckboxOverrideDescription_FilterConditionalStylesFromOverriddenCheckboxExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractSelectDescription <em>Abstract
     * Select Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Select Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription
     * @generated
     */
    EClass getAbstractSelectDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getValueExpression()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EAttribute getAbstractSelectDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getInitialOperation <em>Initial Operation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getInitialOperation()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EReference getAbstractSelectDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getCandidatesExpression <em>Candidates
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Candidates Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getCandidatesExpression()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EAttribute getAbstractSelectDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getCandidateDisplayExpression <em>Candidate
     * Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Candidate Display Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getCandidateDisplayExpression()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EAttribute getAbstractSelectDescription_CandidateDisplayExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getStyle()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EReference getAbstractSelectDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getConditionalStyles()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EReference getAbstractSelectDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractSelectDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getExtends()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EReference getAbstractSelectDescription_Extends();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractSelectDescription#getFilterConditionalStylesFromExtendedSelectExpression
     * <em>Filter Conditional Styles From Extended Select Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Select Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractSelectDescription#getFilterConditionalStylesFromExtendedSelectExpression()
     * @see #getAbstractSelectDescription()
     * @generated
     */
    EAttribute getAbstractSelectDescription_FilterConditionalStylesFromExtendedSelectExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.SelectDescription <em>Select
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Select Description</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription
     * @generated
     */
    EClass getSelectDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.SelectOverrideDescription <em>Select
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Select Override Description</em>'.
     * @see org.eclipse.sirius.properties.SelectOverrideDescription
     * @generated
     */
    EClass getSelectOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.SelectOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.SelectOverrideDescription#getOverrides()
     * @see #getSelectOverrideDescription()
     * @generated
     */
    EReference getSelectOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SelectOverrideDescription#getFilterConditionalStylesFromOverriddenSelectExpression
     * <em>Filter Conditional Styles From Overridden Select Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Select Expression</em>'.
     * @see org.eclipse.sirius.properties.SelectOverrideDescription#getFilterConditionalStylesFromOverriddenSelectExpression()
     * @see #getSelectOverrideDescription()
     * @generated
     */
    EAttribute getSelectOverrideDescription_FilterConditionalStylesFromOverriddenSelectExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription
     * <em>Abstract Dynamic Mapping For Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Dynamic Mapping For Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription
     * @generated
     */
    EClass getAbstractDynamicMappingForDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterator <em>Iterator</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Iterator</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterator()
     * @see #getAbstractDynamicMappingForDescription()
     * @generated
     */
    EAttribute getAbstractDynamicMappingForDescription_Iterator();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterableExpression <em>Iterable
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Iterable Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterableExpression()
     * @see #getAbstractDynamicMappingForDescription()
     * @generated
     */
    EAttribute getAbstractDynamicMappingForDescription_IterableExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#isForceRefresh <em>Force
     * Refresh</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Force Refresh</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#isForceRefresh()
     * @see #getAbstractDynamicMappingForDescription()
     * @generated
     */
    EAttribute getAbstractDynamicMappingForDescription_ForceRefresh();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIfs <em>Ifs</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Ifs</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIfs()
     * @see #getAbstractDynamicMappingForDescription()
     * @generated
     */
    EReference getAbstractDynamicMappingForDescription_Ifs();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getExtends()
     * @see #getAbstractDynamicMappingForDescription()
     * @generated
     */
    EReference getAbstractDynamicMappingForDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getFilterIfsFromExtendedDynamicMappingForExpression
     * <em>Filter Ifs From Extended Dynamic Mapping For Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Ifs From Extended Dynamic Mapping For Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getFilterIfsFromExtendedDynamicMappingForExpression()
     * @see #getAbstractDynamicMappingForDescription()
     * @generated
     */
    EAttribute getAbstractDynamicMappingForDescription_FilterIfsFromExtendedDynamicMappingForExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.DynamicMappingForDescription <em>Dynamic
     * Mapping For Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dynamic Mapping For Description</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingForDescription
     * @generated
     */
    EClass getDynamicMappingForDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription
     * <em>Dynamic Mapping For Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dynamic Mapping For Override Description</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingForOverrideDescription
     * @generated
     */
    EClass getDynamicMappingForOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getOverrides()
     * @see #getDynamicMappingForOverrideDescription()
     * @generated
     */
    EReference getDynamicMappingForOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getFilterIfsFromOverriddenDynamicMappingForExpression
     * <em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getFilterIfsFromOverriddenDynamicMappingForExpression()
     * @see #getDynamicMappingForOverrideDescription()
     * @generated
     */
    EAttribute getDynamicMappingForOverrideDescription_FilterIfsFromOverriddenDynamicMappingForExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription
     * <em>Abstract Dynamic Mapping If Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Dynamic Mapping If Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription
     * @generated
     */
    EClass getAbstractDynamicMappingIfDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getPredicateExpression <em>Predicate
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Predicate Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getPredicateExpression()
     * @see #getAbstractDynamicMappingIfDescription()
     * @generated
     */
    EAttribute getAbstractDynamicMappingIfDescription_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getWidget <em>Widget</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Widget</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getWidget()
     * @see #getAbstractDynamicMappingIfDescription()
     * @generated
     */
    EReference getAbstractDynamicMappingIfDescription_Widget();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getExtends()
     * @see #getAbstractDynamicMappingIfDescription()
     * @generated
     */
    EReference getAbstractDynamicMappingIfDescription_Extends();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.DynamicMappingIfDescription <em>Dynamic
     * Mapping If Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dynamic Mapping If Description</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingIfDescription
     * @generated
     */
    EClass getDynamicMappingIfDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription
     * <em>Dynamic Mapping If Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dynamic Mapping If Override Description</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription
     * @generated
     */
    EClass getDynamicMappingIfOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingIfOverrideDescription#getOverrides()
     * @see #getDynamicMappingIfOverrideDescription()
     * @generated
     */
    EReference getDynamicMappingIfOverrideDescription_Overrides();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription <em>Abstract
     * Text Area Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Text Area Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription
     * @generated
     */
    EClass getAbstractTextAreaDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getLineCount <em>Line Count</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Line Count</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getLineCount()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EAttribute getAbstractTextAreaDescription_LineCount();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getValueExpression()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EAttribute getAbstractTextAreaDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getInitialOperation()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EReference getAbstractTextAreaDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getStyle()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EReference getAbstractTextAreaDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getConditionalStyles()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EReference getAbstractTextAreaDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getExtends()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EReference getAbstractTextAreaDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractTextAreaDescription#getFilterConditionalStylesFromExtendedTextAreaExpression
     * <em>Filter Conditional Styles From Extended Text Area Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Text Area
     *         Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractTextAreaDescription#getFilterConditionalStylesFromExtendedTextAreaExpression()
     * @see #getAbstractTextAreaDescription()
     * @generated
     */
    EAttribute getAbstractTextAreaDescription_FilterConditionalStylesFromExtendedTextAreaExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextAreaDescription <em>Text Area
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Text Area Description</em>'.
     * @see org.eclipse.sirius.properties.TextAreaDescription
     * @generated
     */
    EClass getTextAreaDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextAreaOverrideDescription <em>Text Area
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Text Area Override Description</em>'.
     * @see org.eclipse.sirius.properties.TextAreaOverrideDescription
     * @generated
     */
    EClass getTextAreaOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.TextAreaOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.TextAreaOverrideDescription#getOverrides()
     * @see #getTextAreaOverrideDescription()
     * @generated
     */
    EReference getTextAreaOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.TextAreaOverrideDescription#getFilterConditionalStylesFromOverriddenTextAreaExpression
     * <em>Filter Conditional Styles From Overridden Text Area Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Text Area
     *         Expression</em>'.
     * @see org.eclipse.sirius.properties.TextAreaOverrideDescription#getFilterConditionalStylesFromOverriddenTextAreaExpression()
     * @see #getTextAreaOverrideDescription()
     * @generated
     */
    EAttribute getTextAreaOverrideDescription_FilterConditionalStylesFromOverriddenTextAreaExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractRadioDescription <em>Abstract
     * Radio Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Radio Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription
     * @generated
     */
    EClass getAbstractRadioDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getValueExpression <em>Value Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getValueExpression()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EAttribute getAbstractRadioDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getInitialOperation <em>Initial Operation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getInitialOperation()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EReference getAbstractRadioDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getCandidatesExpression <em>Candidates
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Candidates Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getCandidatesExpression()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EAttribute getAbstractRadioDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getCandidateDisplayExpression <em>Candidate
     * Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Candidate Display Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getCandidateDisplayExpression()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EAttribute getAbstractRadioDescription_CandidateDisplayExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getStyle()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EReference getAbstractRadioDescription_Style();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getNumberOfColumns <em>Number Of Columns</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Number Of Columns</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getNumberOfColumns()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EAttribute getAbstractRadioDescription_NumberOfColumns();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getConditionalStyles()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EReference getAbstractRadioDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractRadioDescription#getExtends <em>Extends</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getExtends()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EReference getAbstractRadioDescription_Extends();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractRadioDescription#getFilterConditionalStylesFromExtendedRadioExpression
     * <em>Filter Conditional Styles From Extended Radio Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Radio Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractRadioDescription#getFilterConditionalStylesFromExtendedRadioExpression()
     * @see #getAbstractRadioDescription()
     * @generated
     */
    EAttribute getAbstractRadioDescription_FilterConditionalStylesFromExtendedRadioExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.RadioDescription <em>Radio
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Radio Description</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription
     * @generated
     */
    EClass getRadioDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.RadioOverrideDescription <em>Radio
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Radio Override Description</em>'.
     * @see org.eclipse.sirius.properties.RadioOverrideDescription
     * @generated
     */
    EClass getRadioOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.RadioOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.RadioOverrideDescription#getOverrides()
     * @see #getRadioOverrideDescription()
     * @generated
     */
    EReference getRadioOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.RadioOverrideDescription#getFilterConditionalStylesFromOverriddenRadioExpression
     * <em>Filter Conditional Styles From Overridden Radio Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Radio Expression</em>'.
     * @see org.eclipse.sirius.properties.RadioOverrideDescription#getFilterConditionalStylesFromOverriddenRadioExpression()
     * @see #getRadioOverrideDescription()
     * @generated
     */
    EAttribute getRadioOverrideDescription_FilterConditionalStylesFromOverriddenRadioExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractListDescription <em>Abstract List
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract List Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription
     * @generated
     */
    EClass getAbstractListDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractListDescription#getValueExpression <em>Value Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getValueExpression()
     * @see #getAbstractListDescription()
     * @generated
     */
    EAttribute getAbstractListDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getDisplayExpression <em>Display Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getDisplayExpression()
     * @see #getAbstractListDescription()
     * @generated
     */
    EAttribute getAbstractListDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getOnClickOperation <em>On Click Operation</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>On Click Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getOnClickOperation()
     * @see #getAbstractListDescription()
     * @generated
     */
    EReference getAbstractListDescription_OnClickOperation();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getActions <em>Actions</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getActions()
     * @see #getAbstractListDescription()
     * @generated
     */
    EReference getAbstractListDescription_Actions();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getStyle()
     * @see #getAbstractListDescription()
     * @generated
     */
    EReference getAbstractListDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getConditionalStyles <em>Conditional Styles</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getConditionalStyles()
     * @see #getAbstractListDescription()
     * @generated
     */
    EReference getAbstractListDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getExtends <em>Extends</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getExtends()
     * @see #getAbstractListDescription()
     * @generated
     */
    EReference getAbstractListDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getFilterConditionalStylesFromExtendedListExpression
     * <em>Filter Conditional Styles From Extended List Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended List Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getFilterConditionalStylesFromExtendedListExpression()
     * @see #getAbstractListDescription()
     * @generated
     */
    EAttribute getAbstractListDescription_FilterConditionalStylesFromExtendedListExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractListDescription#getFilterActionsFromExtendedListExpression
     * <em>Filter Actions From Extended List Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Actions From Extended List Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractListDescription#getFilterActionsFromExtendedListExpression()
     * @see #getAbstractListDescription()
     * @generated
     */
    EAttribute getAbstractListDescription_FilterActionsFromExtendedListExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ListDescription <em>List
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>List Description</em>'.
     * @see org.eclipse.sirius.properties.ListDescription
     * @generated
     */
    EClass getListDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ListOverrideDescription <em>List Override
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>List Override Description</em>'.
     * @see org.eclipse.sirius.properties.ListOverrideDescription
     * @generated
     */
    EClass getListOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.ListOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.ListOverrideDescription#getOverrides()
     * @see #getListOverrideDescription()
     * @generated
     */
    EReference getListOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ListOverrideDescription#getFilterConditionalStylesFromOverriddenListExpression
     * <em>Filter Conditional Styles From Overridden List Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden List Expression</em>'.
     * @see org.eclipse.sirius.properties.ListOverrideDescription#getFilterConditionalStylesFromOverriddenListExpression()
     * @see #getListOverrideDescription()
     * @generated
     */
    EAttribute getListOverrideDescription_FilterConditionalStylesFromOverriddenListExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.ListOverrideDescription#getFilterActionsFromOverriddenListExpression
     * <em>Filter Actions From Overridden List Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Actions From Overridden List Expression</em>'.
     * @see org.eclipse.sirius.properties.ListOverrideDescription#getFilterActionsFromOverriddenListExpression()
     * @see #getListOverrideDescription()
     * @generated
     */
    EAttribute getListOverrideDescription_FilterActionsFromOverriddenListExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.OperationDescription <em>Operation
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Operation Description</em>'.
     * @see org.eclipse.sirius.properties.OperationDescription
     * @generated
     */
    EClass getOperationDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.OperationDescription#getInitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.OperationDescription#getInitialOperation()
     * @see #getOperationDescription()
     * @generated
     */
    EReference getOperationDescription_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractCustomDescription <em>Abstract
     * Custom Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Custom Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription
     * @generated
     */
    EClass getAbstractCustomDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getCustomExpressions <em>Custom
     * Expressions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Custom Expressions</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription#getCustomExpressions()
     * @see #getAbstractCustomDescription()
     * @generated
     */
    EReference getAbstractCustomDescription_CustomExpressions();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getCustomOperations <em>Custom Operations</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Custom Operations</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription#getCustomOperations()
     * @see #getAbstractCustomDescription()
     * @generated
     */
    EReference getAbstractCustomDescription_CustomOperations();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription#getStyle()
     * @see #getAbstractCustomDescription()
     * @generated
     */
    EReference getAbstractCustomDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription#getConditionalStyles()
     * @see #getAbstractCustomDescription()
     * @generated
     */
    EReference getAbstractCustomDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.AbstractCustomDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription#getExtends()
     * @see #getAbstractCustomDescription()
     * @generated
     */
    EReference getAbstractCustomDescription_Extends();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.AbstractCustomDescription#getFilterConditionalStylesFromExtendedCustomExpression
     * <em>Filter Conditional Styles From Extended Custom Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Custom Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractCustomDescription#getFilterConditionalStylesFromExtendedCustomExpression()
     * @see #getAbstractCustomDescription()
     * @generated
     */
    EAttribute getAbstractCustomDescription_FilterConditionalStylesFromExtendedCustomExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CustomDescription <em>Custom
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Description</em>'.
     * @see org.eclipse.sirius.properties.CustomDescription
     * @generated
     */
    EClass getCustomDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CustomOverrideDescription <em>Custom
     * Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Override Description</em>'.
     * @see org.eclipse.sirius.properties.CustomOverrideDescription
     * @generated
     */
    EClass getCustomOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.CustomOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.CustomOverrideDescription#getOverrides()
     * @see #getCustomOverrideDescription()
     * @generated
     */
    EReference getCustomOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CustomOverrideDescription#getFilterConditionalStylesFromOverriddenCustomExpression
     * <em>Filter Conditional Styles From Overridden Custom Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Custom Expression</em>'.
     * @see org.eclipse.sirius.properties.CustomOverrideDescription#getFilterConditionalStylesFromOverriddenCustomExpression()
     * @see #getCustomOverrideDescription()
     * @generated
     */
    EAttribute getCustomOverrideDescription_FilterConditionalStylesFromOverriddenCustomExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CustomExpression <em>Custom
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Expression</em>'.
     * @see org.eclipse.sirius.properties.CustomExpression
     * @generated
     */
    EClass getCustomExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CustomExpression#getCustomExpression <em>Custom Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Custom Expression</em>'.
     * @see org.eclipse.sirius.properties.CustomExpression#getCustomExpression()
     * @see #getCustomExpression()
     * @generated
     */
    EAttribute getCustomExpression_CustomExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CustomOperation <em>Custom
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Operation</em>'.
     * @see org.eclipse.sirius.properties.CustomOperation
     * @generated
     */
    EClass getCustomOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CustomOperation#getInitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.CustomOperation#getInitialOperation()
     * @see #getCustomOperation()
     * @generated
     */
    EReference getCustomOperation_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription <em>Abstract
     * Hyperlink Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Hyperlink Description</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription
     * @generated
     */
    EClass getAbstractHyperlinkDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getValueExpression <em>Value
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getValueExpression()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EAttribute getAbstractHyperlinkDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getDisplayExpression <em>Display
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getDisplayExpression()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EAttribute getAbstractHyperlinkDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getInitialOperation <em>Initial
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getInitialOperation()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EReference getAbstractHyperlinkDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getStyle()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EReference getAbstractHyperlinkDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getConditionalStyles <em>Conditional
     * Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getConditionalStyles()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EReference getAbstractHyperlinkDescription_ConditionalStyles();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getActions <em>Actions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getActions()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EReference getAbstractHyperlinkDescription_Actions();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getExtends <em>Extends</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getExtends()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EReference getAbstractHyperlinkDescription_Extends();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterConditionalStylesFromExtendedHyperlinkExpression
     * <em>Filter Conditional Styles From Extended Hyperlink Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Extended Hyperlink
     *         Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterConditionalStylesFromExtendedHyperlinkExpression()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EAttribute getAbstractHyperlinkDescription_FilterConditionalStylesFromExtendedHyperlinkExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterActionsFromExtendedHyperlinkExpression
     * <em>Filter Actions From Extended Hyperlink Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Actions From Extended Hyperlink Expression</em>'.
     * @see org.eclipse.sirius.properties.AbstractHyperlinkDescription#getFilterActionsFromExtendedHyperlinkExpression()
     * @see #getAbstractHyperlinkDescription()
     * @generated
     */
    EAttribute getAbstractHyperlinkDescription_FilterActionsFromExtendedHyperlinkExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.HyperlinkDescription <em>Hyperlink
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hyperlink Description</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription
     * @generated
     */
    EClass getHyperlinkDescription();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription
     * <em>Hyperlink Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hyperlink Override Description</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkOverrideDescription
     * @generated
     */
    EClass getHyperlinkOverrideDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getOverrides <em>Overrides</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkOverrideDescription#getOverrides()
     * @see #getHyperlinkOverrideDescription()
     * @generated
     */
    EReference getHyperlinkOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterConditionalStylesFromOverriddenHyperlinkExpression
     * <em>Filter Conditional Styles From Overridden Hyperlink Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Hyperlink
     *         Expression</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterConditionalStylesFromOverriddenHyperlinkExpression()
     * @see #getHyperlinkOverrideDescription()
     * @generated
     */
    EAttribute getHyperlinkOverrideDescription_FilterConditionalStylesFromOverriddenHyperlinkExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterActionsFromOverriddenHyperlinkExpression
     * <em>Filter Actions From Overridden Hyperlink Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Actions From Overridden Hyperlink Expression</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterActionsFromOverriddenHyperlinkExpression()
     * @see #getHyperlinkOverrideDescription()
     * @generated
     */
    EAttribute getHyperlinkOverrideDescription_FilterActionsFromOverriddenHyperlinkExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.WidgetStyle <em>Widget Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Style</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle
     * @generated
     */
    EClass getWidgetStyle();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontNameExpression <em>Label Font Name
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Font Name Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelFontNameExpression()
     * @see #getWidgetStyle()
     * @generated
     */
    EAttribute getWidgetStyle_LabelFontNameExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontSizeExpression <em>Label Font Size
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Font Size Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelFontSizeExpression()
     * @see #getWidgetStyle()
     * @generated
     */
    EAttribute getWidgetStyle_LabelFontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelBackgroundColor <em>Label Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Label Background Color</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelBackgroundColor()
     * @see #getWidgetStyle()
     * @generated
     */
    EReference getWidgetStyle_LabelBackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelForegroundColor <em>Label Foreground Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Label Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelForegroundColor()
     * @see #getWidgetStyle()
     * @generated
     */
    EReference getWidgetStyle_LabelForegroundColor();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontFormat <em>Label Font Format</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Label Font Format</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelFontFormat()
     * @see #getWidgetStyle()
     * @generated
     */
    EAttribute getWidgetStyle_LabelFontFormat();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextWidgetStyle <em>Text Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Text Widget Style</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle
     * @generated
     */
    EClass getTextWidgetStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getFontNameExpression <em>Font Name Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getFontNameExpression()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EAttribute getTextWidgetStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getFontSizeExpression <em>Font Size Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getFontSizeExpression()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EAttribute getTextWidgetStyle_FontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getBackgroundColor <em>Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getBackgroundColor()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EReference getTextWidgetStyle_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getForegroundColor <em>Foreground Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getForegroundColor()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EReference getTextWidgetStyle_ForegroundColor();

    /**
     * Returns the meta object for the attribute list
     * '{@link org.eclipse.sirius.properties.TextWidgetStyle#getFontFormat <em>Font Format</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Font Format</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getFontFormat()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EAttribute getTextWidgetStyle_FontFormat();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.LabelWidgetStyle <em>Label Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Widget Style</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle
     * @generated
     */
    EClass getLabelWidgetStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getFontNameExpression <em>Font Name Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getFontNameExpression()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EAttribute getLabelWidgetStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getFontSizeExpression <em>Font Size Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getFontSizeExpression()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EAttribute getLabelWidgetStyle_FontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getBackgroundColor <em>Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getBackgroundColor()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EReference getLabelWidgetStyle_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getForegroundColor <em>Foreground Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getForegroundColor()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EReference getLabelWidgetStyle_ForegroundColor();

    /**
     * Returns the meta object for the attribute list
     * '{@link org.eclipse.sirius.properties.LabelWidgetStyle#getFontFormat <em>Font Format</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Font Format</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getFontFormat()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EAttribute getLabelWidgetStyle_FontFormat();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CheckboxWidgetStyle <em>Checkbox Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Checkbox Widget Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxWidgetStyle
     * @generated
     */
    EClass getCheckboxWidgetStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.RadioWidgetStyle <em>Radio Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Radio Widget Style</em>'.
     * @see org.eclipse.sirius.properties.RadioWidgetStyle
     * @generated
     */
    EClass getRadioWidgetStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ButtonWidgetStyle <em>Button Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Button Widget Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonWidgetStyle
     * @generated
     */
    EClass getButtonWidgetStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.SelectWidgetStyle <em>Select Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Select Widget Style</em>'.
     * @see org.eclipse.sirius.properties.SelectWidgetStyle
     * @generated
     */
    EClass getSelectWidgetStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CustomWidgetStyle <em>Custom Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Widget Style</em>'.
     * @see org.eclipse.sirius.properties.CustomWidgetStyle
     * @generated
     */
    EClass getCustomWidgetStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ListWidgetStyle <em>List Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>List Widget Style</em>'.
     * @see org.eclipse.sirius.properties.ListWidgetStyle
     * @generated
     */
    EClass getListWidgetStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.HyperlinkWidgetStyle <em>Hyperlink Widget
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hyperlink Widget Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle
     * @generated
     */
    EClass getHyperlinkWidgetStyle();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontNameExpression <em>Font Name Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontNameExpression()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EAttribute getHyperlinkWidgetStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontSizeExpression <em>Font Size Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontSizeExpression()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EAttribute getHyperlinkWidgetStyle_FontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getBackgroundColor <em>Background Color</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getBackgroundColor()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EReference getHyperlinkWidgetStyle_BackgroundColor();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontFormat <em>Font Format</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Font Format</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontFormat()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EAttribute getHyperlinkWidgetStyle_FontFormat();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GroupStyle <em>Group Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Style</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle
     * @generated
     */
    EClass getGroupStyle();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.properties.GroupStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getBackgroundColor()
     * @see #getGroupStyle()
     * @generated
     */
    EReference getGroupStyle_BackgroundColor();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.properties.GroupStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getForegroundColor()
     * @see #getGroupStyle()
     * @generated
     */
    EReference getGroupStyle_ForegroundColor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupStyle#getFontNameExpression
     * <em>Font Name Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getFontNameExpression()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupStyle#getFontSizeExpression
     * <em>Font Size Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getFontSizeExpression()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_FontSizeExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupStyle#getBarStyle <em>Bar
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Bar Style</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getBarStyle()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_BarStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupStyle#getToggleStyle
     * <em>Toggle Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Toggle Style</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getToggleStyle()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_ToggleStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.GroupStyle#isExpandedByDefault
     * <em>Expanded By Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expanded By Default</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#isExpandedByDefault()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_ExpandedByDefault();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.WidgetConditionalStyle <em>Widget
     * Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.WidgetConditionalStyle
     * @generated
     */
    EClass getWidgetConditionalStyle();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.WidgetConditionalStyle#getPreconditionExpression <em>Precondition
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetConditionalStyle#getPreconditionExpression()
     * @see #getWidgetConditionalStyle()
     * @generated
     */
    EAttribute getWidgetConditionalStyle_PreconditionExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.TextWidgetConditionalStyle <em>Text
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Text Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetConditionalStyle
     * @generated
     */
    EClass getTextWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.TextWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetConditionalStyle#getStyle()
     * @see #getTextWidgetConditionalStyle()
     * @generated
     */
    EReference getTextWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.LabelWidgetConditionalStyle <em>Label
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetConditionalStyle
     * @generated
     */
    EClass getLabelWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.LabelWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetConditionalStyle#getStyle()
     * @see #getLabelWidgetConditionalStyle()
     * @generated
     */
    EReference getLabelWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle
     * <em>Checkbox Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Checkbox Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle
     * @generated
     */
    EClass getCheckboxWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle#getStyle <em>Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle#getStyle()
     * @see #getCheckboxWidgetConditionalStyle()
     * @generated
     */
    EReference getCheckboxWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.RadioWidgetConditionalStyle <em>Radio
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Radio Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.RadioWidgetConditionalStyle
     * @generated
     */
    EClass getRadioWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.RadioWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.RadioWidgetConditionalStyle#getStyle()
     * @see #getRadioWidgetConditionalStyle()
     * @generated
     */
    EReference getRadioWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ButtonWidgetConditionalStyle <em>Button
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Button Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonWidgetConditionalStyle
     * @generated
     */
    EClass getButtonWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.ButtonWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonWidgetConditionalStyle#getStyle()
     * @see #getButtonWidgetConditionalStyle()
     * @generated
     */
    EReference getButtonWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.SelectWidgetConditionalStyle <em>Select
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Select Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.SelectWidgetConditionalStyle
     * @generated
     */
    EClass getSelectWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.SelectWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.SelectWidgetConditionalStyle#getStyle()
     * @see #getSelectWidgetConditionalStyle()
     * @generated
     */
    EReference getSelectWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.CustomWidgetConditionalStyle <em>Custom
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Custom Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.CustomWidgetConditionalStyle
     * @generated
     */
    EClass getCustomWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.CustomWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.CustomWidgetConditionalStyle#getStyle()
     * @see #getCustomWidgetConditionalStyle()
     * @generated
     */
    EReference getCustomWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.ListWidgetConditionalStyle <em>List
     * Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>List Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.ListWidgetConditionalStyle
     * @generated
     */
    EClass getListWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.ListWidgetConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ListWidgetConditionalStyle#getStyle()
     * @see #getListWidgetConditionalStyle()
     * @generated
     */
    EReference getListWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.WidgetAction <em>Widget Action</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Action</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction
     * @generated
     */
    EClass getWidgetAction();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.WidgetAction#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction#getLabelExpression()
     * @see #getWidgetAction()
     * @generated
     */
    EAttribute getWidgetAction_LabelExpression();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.WidgetAction#getImageExpression
     * <em>Image Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Image Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction#getImageExpression()
     * @see #getWidgetAction()
     * @generated
     */
    EAttribute getWidgetAction_ImageExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.WidgetAction#getInitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction#getInitialOperation()
     * @see #getWidgetAction()
     * @generated
     */
    EReference getWidgetAction_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle
     * <em>Hyperlink Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Hyperlink Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle
     * @generated
     */
    EClass getHyperlinkWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle#getStyle <em>Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle#getStyle()
     * @see #getHyperlinkWidgetConditionalStyle()
     * @generated
     */
    EReference getHyperlinkWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.GroupConditionalStyle <em>Group
     * Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.GroupConditionalStyle
     * @generated
     */
    EClass getGroupConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.GroupConditionalStyle#getStyle <em>Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.GroupConditionalStyle#getStyle()
     * @see #getGroupConditionalStyle()
     * @generated
     */
    EReference getGroupConditionalStyle_Style();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.DialogModelOperation <em>Dialog Model
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dialog Model Operation</em>'.
     * @see org.eclipse.sirius.properties.DialogModelOperation
     * @generated
     */
    EClass getDialogModelOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.DialogModelOperation#getTitleExpression <em>Title Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.properties.DialogModelOperation#getTitleExpression()
     * @see #getDialogModelOperation()
     * @generated
     */
    EAttribute getDialogModelOperation_TitleExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.DialogModelOperation#getButtons <em>Buttons</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Buttons</em>'.
     * @see org.eclipse.sirius.properties.DialogModelOperation#getButtons()
     * @see #getDialogModelOperation()
     * @generated
     */
    EReference getDialogModelOperation_Buttons();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.DialogModelOperation#getPage <em>Page</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Page</em>'.
     * @see org.eclipse.sirius.properties.DialogModelOperation#getPage()
     * @see #getDialogModelOperation()
     * @generated
     */
    EReference getDialogModelOperation_Page();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.DialogModelOperation#getGroups <em>Groups</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.DialogModelOperation#getGroups()
     * @see #getDialogModelOperation()
     * @generated
     */
    EReference getDialogModelOperation_Groups();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.DialogButton <em>Dialog Button</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Dialog Button</em>'.
     * @see org.eclipse.sirius.properties.DialogButton
     * @generated
     */
    EClass getDialogButton();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.DialogButton#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.DialogButton#getLabelExpression()
     * @see #getDialogButton()
     * @generated
     */
    EAttribute getDialogButton_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.DialogButton#getIsEnabledExpression <em>Is Enabled Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Enabled Expression</em>'.
     * @see org.eclipse.sirius.properties.DialogButton#getIsEnabledExpression()
     * @see #getDialogButton()
     * @generated
     */
    EAttribute getDialogButton_IsEnabledExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.DialogButton#getInitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.DialogButton#getInitialOperation()
     * @see #getDialogButton()
     * @generated
     */
    EReference getDialogButton_InitialOperation();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.DialogButton#isDefault
     * <em>Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Default</em>'.
     * @see org.eclipse.sirius.properties.DialogButton#isDefault()
     * @see #getDialogButton()
     * @generated
     */
    EAttribute getDialogButton_Default();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.properties.DialogButton#isCloseDialogOnClick
     * <em>Close Dialog On Click</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Close Dialog On Click</em>'.
     * @see org.eclipse.sirius.properties.DialogButton#isCloseDialogOnClick()
     * @see #getDialogButton()
     * @generated
     */
    EAttribute getDialogButton_CloseDialogOnClick();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.DialogButton#isRollbackChangesOnClose <em>Rollback Changes On Close</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Rollback Changes On Close</em>'.
     * @see org.eclipse.sirius.properties.DialogButton#isRollbackChangesOnClose()
     * @see #getDialogButton()
     * @generated
     */
    EAttribute getDialogButton_RollbackChangesOnClose();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.WizardModelOperation <em>Wizard Model
     * Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Wizard Model Operation</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation
     * @generated
     */
    EClass getWizardModelOperation();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.WizardModelOperation#getWindowTitleExpression <em>Window Title
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Window Title Expression</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getWindowTitleExpression()
     * @see #getWizardModelOperation()
     * @generated
     */
    EAttribute getWizardModelOperation_WindowTitleExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WizardModelOperation#getTitleExpression <em>Title Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getTitleExpression()
     * @see #getWizardModelOperation()
     * @generated
     */
    EAttribute getWizardModelOperation_TitleExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.WizardModelOperation#getDescriptionExpression <em>Description
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Description Expression</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getDescriptionExpression()
     * @see #getWizardModelOperation()
     * @generated
     */
    EAttribute getWizardModelOperation_DescriptionExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.WizardModelOperation#getIsPageCompleteExpression <em>Is Page Complete
     * Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Page Complete Expression</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getIsPageCompleteExpression()
     * @see #getWizardModelOperation()
     * @generated
     */
    EAttribute getWizardModelOperation_IsPageCompleteExpression();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.WizardModelOperation#getPages <em>Pages</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Pages</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getPages()
     * @see #getWizardModelOperation()
     * @generated
     */
    EReference getWizardModelOperation_Pages();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.WizardModelOperation#getGroups <em>Groups</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getGroups()
     * @see #getWizardModelOperation()
     * @generated
     */
    EReference getWizardModelOperation_Groups();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.WizardModelOperation#getInitialOperation <em>Initial Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.WizardModelOperation#getInitialOperation()
     * @see #getWizardModelOperation()
     * @generated
     */
    EReference getWizardModelOperation_InitialOperation();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.properties.EditSupport <em>Edit Support</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edit Support</em>'.
     * @see org.eclipse.sirius.properties.EditSupport
     * @generated
     */
    EClass getEditSupport();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION <em>FILL LAYOUT
     * ORIENTATION</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>FILL LAYOUT ORIENTATION</em>'.
     * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * @generated
     */
    EEnum getFILL_LAYOUT_ORIENTATION();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.properties.ToggleStyle <em>Toggle Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Toggle Style</em>'.
     * @see org.eclipse.sirius.properties.ToggleStyle
     * @generated
     */
    EEnum getToggleStyle();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.properties.TitleBarStyle <em>Title Bar Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Title Bar Style</em>'.
     * @see org.eclipse.sirius.properties.TitleBarStyle
     * @generated
     */
    EEnum getTitleBarStyle();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesFactory getPropertiesFactory();

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
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
         * <em>View Extension Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
         * @generated
         */
        EClass VIEW_EXTENSION_DESCRIPTION = PropertiesPackage.eINSTANCE.getViewExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Metamodels</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__METAMODELS = PropertiesPackage.eINSTANCE.getViewExtensionDescription_Metamodels();

        /**
         * The meta object literal for the '<em><b>Categories</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__CATEGORIES = PropertiesPackage.eINSTANCE.getViewExtensionDescription_Categories();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CategoryImpl <em>Category</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CategoryImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCategory()
         * @generated
         */
        EClass CATEGORY = PropertiesPackage.eINSTANCE.getCategory();

        /**
         * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CATEGORY__PAGES = PropertiesPackage.eINSTANCE.getCategory_Pages();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CATEGORY__GROUPS = PropertiesPackage.eINSTANCE.getCategory_Groups();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CATEGORY__OVERRIDES = PropertiesPackage.eINSTANCE.getCategory_Overrides();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractOverrideDescriptionImpl
         * <em>Abstract Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractOverrideDescription()
         * @generated
         */
        EClass ABSTRACT_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractOverrideDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl
         * <em>Abstract Page Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractPageDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractPageDescription()
         * @generated
         */
        EClass ABSTRACT_PAGE_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractPageDescription();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractPageDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.eINSTANCE.getAbstractPageDescription_DomainClass();

        /**
         * The meta object literal for the '<em><b>Semantic Candidate Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractPageDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractPageDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_PAGE_DESCRIPTION__GROUPS = PropertiesPackage.eINSTANCE.getAbstractPageDescription_Groups();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_PAGE_DESCRIPTION__VALIDATION_SET = PropertiesPackage.eINSTANCE.getAbstractPageDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_PAGE_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getAbstractPageDescription_Actions();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_PAGE_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractPageDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Groups From Extended Page Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__FILTER_GROUPS_FROM_EXTENDED_PAGE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractPageDescription_FilterGroupsFromExtendedPageExpression();

        /**
         * The meta object literal for the '<em><b>Filter Validation Rules From Extended Page Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_PAGE_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractPageDescription_FilterValidationRulesFromExtendedPageExpression();

        /**
         * The meta object literal for the '<em><b>Indented</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_PAGE_DESCRIPTION__INDENTED = PropertiesPackage.eINSTANCE.getAbstractPageDescription_Indented();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.PageDescriptionImpl <em>Page
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
         * @generated
         */
        EClass PAGE_DESCRIPTION = PropertiesPackage.eINSTANCE.getPageDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl
         * <em>Page Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.PageOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageOverrideDescription()
         * @generated
         */
        EClass PAGE_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getPageOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference PAGE_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getPageOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Groups From Overridden Page Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PAGE_OVERRIDE_DESCRIPTION__FILTER_GROUPS_FROM_OVERRIDDEN_PAGE_EXPRESSION = PropertiesPackage.eINSTANCE.getPageOverrideDescription_FilterGroupsFromOverriddenPageExpression();

        /**
         * The meta object literal for the '<em><b>Filter Validation Rules From Overridden Page Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PAGE_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_PAGE_EXPRESSION = PropertiesPackage.eINSTANCE
                .getPageOverrideDescription_FilterValidationRulesFromOverriddenPageExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
         * <em>Page Validation Set Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageValidationSetDescription()
         * @generated
         */
        EClass PAGE_VALIDATION_SET_DESCRIPTION = PropertiesPackage.eINSTANCE.getPageValidationSetDescription();

        /**
         * The meta object literal for the '<em><b>Semantic Validation Rules</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = PropertiesPackage.eINSTANCE.getPageValidationSetDescription_SemanticValidationRules();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
         * <em>Property Validation Rule</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPropertyValidationRule()
         * @generated
         */
        EClass PROPERTY_VALIDATION_RULE = PropertiesPackage.eINSTANCE.getPropertyValidationRule();

        /**
         * The meta object literal for the '<em><b>Targets</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PROPERTY_VALIDATION_RULE__TARGETS = PropertiesPackage.eINSTANCE.getPropertyValidationRule_Targets();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl
         * <em>Abstract Group Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractGroupDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractGroupDescription()
         * @generated
         */
        EClass ABSTRACT_GROUP_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractGroupDescription();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_DomainClass();

        /**
         * The meta object literal for the '<em><b>Semantic Candidate Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Controls</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_GROUP_DESCRIPTION__CONTROLS = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_Controls();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_GROUP_DESCRIPTION__VALIDATION_SET = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_GROUP_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_GROUP_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_GROUP_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Controls From Extended Group Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_FilterControlsFromExtendedGroupExpression();

        /**
         * The meta object literal for the '<em><b>Filter Validation Rules From Extended Group Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractGroupDescription_FilterValidationRulesFromExtendedGroupExpression();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Group Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_GROUP_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_GROUP_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractGroupDescription_FilterConditionalStylesFromExtendedGroupExpression();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_GROUP_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getAbstractGroupDescription_Actions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ToolbarActionImpl <em>Toolbar
         * Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ToolbarActionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getToolbarAction()
         * @generated
         */
        EClass TOOLBAR_ACTION = PropertiesPackage.eINSTANCE.getToolbarAction();

        /**
         * The meta object literal for the '<em><b>Tooltip Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOLBAR_ACTION__TOOLTIP_EXPRESSION = PropertiesPackage.eINSTANCE.getToolbarAction_TooltipExpression();

        /**
         * The meta object literal for the '<em><b>Image Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOLBAR_ACTION__IMAGE_EXPRESSION = PropertiesPackage.eINSTANCE.getToolbarAction_ImageExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOLBAR_ACTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getToolbarAction_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl <em>Group
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
         * @generated
         */
        EClass GROUP_DESCRIPTION = PropertiesPackage.eINSTANCE.getGroupDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl
         * <em>Group Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupOverrideDescription()
         * @generated
         */
        EClass GROUP_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getGroupOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getGroupOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Controls From Overridden Group Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_GROUP_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupOverrideDescription_FilterControlsFromOverriddenGroupExpression();

        /**
         * The meta object literal for the '<em><b>Filter Validation Rules From Overridden Group Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_OVERRIDE_DESCRIPTION__FILTER_VALIDATION_RULES_FROM_OVERRIDDEN_GROUP_EXPRESSION = PropertiesPackage.eINSTANCE
                .getGroupOverrideDescription_FilterValidationRulesFromOverriddenGroupExpression();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Group Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_GROUP_EXPRESSION = PropertiesPackage.eINSTANCE
                .getGroupOverrideDescription_FilterConditionalStylesFromOverriddenGroupExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
         * <em>Group Validation Set Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupValidationSetDescription()
         * @generated
         */
        EClass GROUP_VALIDATION_SET_DESCRIPTION = PropertiesPackage.eINSTANCE.getGroupValidationSetDescription();

        /**
         * The meta object literal for the '<em><b>Semantic Validation Rules</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = PropertiesPackage.eINSTANCE.getGroupValidationSetDescription_SemanticValidationRules();

        /**
         * The meta object literal for the '<em><b>Property Validation Rules</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES = PropertiesPackage.eINSTANCE.getGroupValidationSetDescription_PropertyValidationRules();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractControlDescriptionImpl
         * <em>Abstract Control Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractControlDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractControlDescription()
         * @generated
         */
        EClass ABSTRACT_CONTROL_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractControlDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ControlDescriptionImpl <em>Control
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ControlDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getControlDescription()
         * @generated
         */
        EClass CONTROL_DESCRIPTION = PropertiesPackage.eINSTANCE.getControlDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractContainerDescriptionImpl
         * <em>Abstract Container Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractContainerDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractContainerDescription()
         * @generated
         */
        EClass ABSTRACT_CONTAINER_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractContainerDescription();

        /**
         * The meta object literal for the '<em><b>Controls</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CONTAINER_DESCRIPTION__CONTROLS = PropertiesPackage.eINSTANCE.getAbstractContainerDescription_Controls();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CONTAINER_DESCRIPTION__LAYOUT = PropertiesPackage.eINSTANCE.getAbstractContainerDescription_Layout();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CONTAINER_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractContainerDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Controls From Extended Container Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_CONTAINER_DESCRIPTION__FILTER_CONTROLS_FROM_EXTENDED_CONTAINER_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractContainerDescription_FilterControlsFromExtendedContainerExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
         * <em>Container Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
         * @generated
         */
        EClass CONTAINER_DESCRIPTION = PropertiesPackage.eINSTANCE.getContainerDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ContainerOverrideDescriptionImpl
         * <em>Container Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ContainerOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerOverrideDescription()
         * @generated
         */
        EClass CONTAINER_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getContainerOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getContainerOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Controls From Overridden Container Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTAINER_OVERRIDE_DESCRIPTION__FILTER_CONTROLS_FROM_OVERRIDDEN_CONTAINER_EXPRESSION = PropertiesPackage.eINSTANCE
                .getContainerOverrideDescription_FilterControlsFromOverriddenContainerExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.LayoutDescriptionImpl <em>Layout
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LayoutDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLayoutDescription()
         * @generated
         */
        EClass LAYOUT_DESCRIPTION = PropertiesPackage.eINSTANCE.getLayoutDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl <em>Fill
         * Layout Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFillLayoutDescription()
         * @generated
         */
        EClass FILL_LAYOUT_DESCRIPTION = PropertiesPackage.eINSTANCE.getFillLayoutDescription();

        /**
         * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute FILL_LAYOUT_DESCRIPTION__ORIENTATION = PropertiesPackage.eINSTANCE.getFillLayoutDescription_Orientation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl <em>Grid
         * Layout Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGridLayoutDescription()
         * @generated
         */
        EClass GRID_LAYOUT_DESCRIPTION = PropertiesPackage.eINSTANCE.getGridLayoutDescription();

        /**
         * The meta object literal for the '<em><b>Number Of Columns</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.eINSTANCE.getGridLayoutDescription_NumberOfColumns();

        /**
         * The meta object literal for the '<em><b>Make Columns With Equal Width</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH = PropertiesPackage.eINSTANCE.getGridLayoutDescription_MakeColumnsWithEqualWidth();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractWidgetDescriptionImpl
         * <em>Abstract Widget Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractWidgetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractWidgetDescription()
         * @generated
         */
        EClass ABSTRACT_WIDGET_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractWidgetDescription();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractWidgetDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Help Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractWidgetDescription_HelpExpression();

        /**
         * The meta object literal for the '<em><b>Is Enabled Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractWidgetDescription_IsEnabledExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl <em>Widget
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
         * @generated
         */
        EClass WIDGET_DESCRIPTION = PropertiesPackage.eINSTANCE.getWidgetDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractTextDescriptionImpl
         * <em>Abstract Text Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractTextDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractTextDescription()
         * @generated
         */
        EClass ABSTRACT_TEXT_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractTextDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_TEXT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractTextDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractTextDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractTextDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractTextDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractTextDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Text Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_TEXT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractTextDescription_FilterConditionalStylesFromExtendedTextExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextDescriptionImpl <em>Text
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
         * @generated
         */
        EClass TEXT_DESCRIPTION = PropertiesPackage.eINSTANCE.getTextDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextOverrideDescriptionImpl
         * <em>Text Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextOverrideDescription()
         * @generated
         */
        EClass TEXT_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getTextOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getTextOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Text Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_EXPRESSION = PropertiesPackage.eINSTANCE
                .getTextOverrideDescription_FilterConditionalStylesFromOverriddenTextExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractButtonDescriptionImpl
         * <em>Abstract Button Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractButtonDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractButtonDescription()
         * @generated
         */
        EClass ABSTRACT_BUTTON_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractButtonDescription();

        /**
         * The meta object literal for the '<em><b>Button Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractButtonDescription_ButtonLabelExpression();

        /**
         * The meta object literal for the '<em><b>Image Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_BUTTON_DESCRIPTION__IMAGE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractButtonDescription_ImageExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_BUTTON_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractButtonDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_BUTTON_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractButtonDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_BUTTON_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractButtonDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_BUTTON_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractButtonDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Button Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_BUTTON_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_BUTTON_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractButtonDescription_FilterConditionalStylesFromExtendedButtonExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl <em>Button
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ButtonDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonDescription()
         * @generated
         */
        EClass BUTTON_DESCRIPTION = PropertiesPackage.eINSTANCE.getButtonDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ButtonOverrideDescriptionImpl
         * <em>Button Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ButtonOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonOverrideDescription()
         * @generated
         */
        EClass BUTTON_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getButtonOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference BUTTON_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getButtonOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Button Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BUTTON_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_BUTTON_EXPRESSION = PropertiesPackage.eINSTANCE
                .getButtonOverrideDescription_FilterConditionalStylesFromOverriddenButtonExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl
         * <em>Abstract Label Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractLabelDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractLabelDescription()
         * @generated
         */
        EClass ABSTRACT_LABEL_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractLabelDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LABEL_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LABEL_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LABEL_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LABEL_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LABEL_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_Actions();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LABEL_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LABEL_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractLabelDescription_FilterConditionalStylesFromExtendedLabelExpression();

        /**
         * The meta object literal for the '<em><b>Filter Actions From Extended Label Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LABEL_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractLabelDescription_FilterActionsFromExtendedLabelExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl <em>Label
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LabelDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelDescription()
         * @generated
         */
        EClass LABEL_DESCRIPTION = PropertiesPackage.eINSTANCE.getLabelDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl
         * <em>Label Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LabelOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelOverrideDescription()
         * @generated
         */
        EClass LABEL_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getLabelOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getLabelOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LABEL_EXPRESSION = PropertiesPackage.eINSTANCE
                .getLabelOverrideDescription_FilterConditionalStylesFromOverriddenLabelExpression();

        /**
         * The meta object literal for the '<em><b>Filter Actions From Overridden Label Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelOverrideDescription_FilterActionsFromOverriddenLabelExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl
         * <em>Abstract Checkbox Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractCheckboxDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractCheckboxDescription()
         * @generated
         */
        EClass ABSTRACT_CHECKBOX_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractCheckboxDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractCheckboxDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CHECKBOX_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractCheckboxDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CHECKBOX_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractCheckboxDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractCheckboxDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CHECKBOX_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractCheckboxDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Checkbox Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_CHECKBOX_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CHECKBOX_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractCheckboxDescription_FilterConditionalStylesFromExtendedCheckboxExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
         * <em>Checkbox Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxDescription()
         * @generated
         */
        EClass CHECKBOX_DESCRIPTION = PropertiesPackage.eINSTANCE.getCheckboxDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CheckboxOverrideDescriptionImpl
         * <em>Checkbox Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CheckboxOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxOverrideDescription()
         * @generated
         */
        EClass CHECKBOX_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getCheckboxOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CHECKBOX_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getCheckboxOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Checkbox
         * Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CHECKBOX_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_CHECKBOX_EXPRESSION = PropertiesPackage.eINSTANCE
                .getCheckboxOverrideDescription_FilterConditionalStylesFromOverriddenCheckboxExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl
         * <em>Abstract Select Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractSelectDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractSelectDescription()
         * @generated
         */
        EClass ABSTRACT_SELECT_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractSelectDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_SELECT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_SELECT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_CandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Candidate Display Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_CandidateDisplayExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_SELECT_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_SELECT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_SELECT_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractSelectDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Select Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_SELECT_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_SELECT_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractSelectDescription_FilterConditionalStylesFromExtendedSelectExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl <em>Select
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.SelectDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectDescription()
         * @generated
         */
        EClass SELECT_DESCRIPTION = PropertiesPackage.eINSTANCE.getSelectDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.SelectOverrideDescriptionImpl
         * <em>Select Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.SelectOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectOverrideDescription()
         * @generated
         */
        EClass SELECT_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getSelectOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SELECT_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getSelectOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Select Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SELECT_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_SELECT_EXPRESSION = PropertiesPackage.eINSTANCE
                .getSelectOverrideDescription_FilterConditionalStylesFromOverriddenSelectExpression();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl <em>Abstract Dynamic
         * Mapping For Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractDynamicMappingForDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractDynamicMappingForDescription()
         * @generated
         */
        EClass ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingForDescription();

        /**
         * The meta object literal for the '<em><b>Iterator</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERATOR = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingForDescription_Iterator();

        /**
         * The meta object literal for the '<em><b>Iterable Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__ITERABLE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingForDescription_IterableExpression();

        /**
         * The meta object literal for the '<em><b>Force Refresh</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FORCE_REFRESH = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingForDescription_ForceRefresh();

        /**
         * The meta object literal for the '<em><b>Ifs</b></em>' containment reference list feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__IFS = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingForDescription_Ifs();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingForDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Ifs From Extended Dynamic Mapping For Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_DYNAMIC_MAPPING_FOR_DESCRIPTION__FILTER_IFS_FROM_EXTENDED_DYNAMIC_MAPPING_FOR_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractDynamicMappingForDescription_FilterIfsFromExtendedDynamicMappingForExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.DynamicMappingForDescriptionImpl
         * <em>Dynamic Mapping For Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.DynamicMappingForDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingForDescription()
         * @generated
         */
        EClass DYNAMIC_MAPPING_FOR_DESCRIPTION = PropertiesPackage.eINSTANCE.getDynamicMappingForDescription();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.impl.DynamicMappingForOverrideDescriptionImpl <em>Dynamic Mapping For
         * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.DynamicMappingForOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingForOverrideDescription()
         * @generated
         */
        EClass DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getDynamicMappingForOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getDynamicMappingForOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Ifs From Overridden Dynamic Mapping For Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DYNAMIC_MAPPING_FOR_OVERRIDE_DESCRIPTION__FILTER_IFS_FROM_OVERRIDDEN_DYNAMIC_MAPPING_FOR_EXPRESSION = PropertiesPackage.eINSTANCE
                .getDynamicMappingForOverrideDescription_FilterIfsFromOverriddenDynamicMappingForExpression();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl <em>Abstract Dynamic
         * Mapping If Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractDynamicMappingIfDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractDynamicMappingIfDescription()
         * @generated
         */
        EClass ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingIfDescription();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__PREDICATE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingIfDescription_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Widget</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__WIDGET = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingIfDescription_Widget();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_DYNAMIC_MAPPING_IF_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractDynamicMappingIfDescription_Extends();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.DynamicMappingIfDescriptionImpl
         * <em>Dynamic Mapping If Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.DynamicMappingIfDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingIfDescription()
         * @generated
         */
        EClass DYNAMIC_MAPPING_IF_DESCRIPTION = PropertiesPackage.eINSTANCE.getDynamicMappingIfDescription();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.impl.DynamicMappingIfOverrideDescriptionImpl <em>Dynamic Mapping If
         * Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.DynamicMappingIfOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingIfOverrideDescription()
         * @generated
         */
        EClass DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getDynamicMappingIfOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference DYNAMIC_MAPPING_IF_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getDynamicMappingIfOverrideDescription_Overrides();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl
         * <em>Abstract Text Area Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractTextAreaDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractTextAreaDescription()
         * @generated
         */
        EClass ABSTRACT_TEXT_AREA_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription();

        /**
         * The meta object literal for the '<em><b>Line Count</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_TEXT_AREA_DESCRIPTION__LINE_COUNT = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription_LineCount();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_AREA_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_AREA_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_TEXT_AREA_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractTextAreaDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Text Area
         * Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_TEXT_AREA_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_TEXT_AREA_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractTextAreaDescription_FilterConditionalStylesFromExtendedTextAreaExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl <em>Text
         * Area Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextAreaDescription()
         * @generated
         */
        EClass TEXT_AREA_DESCRIPTION = PropertiesPackage.eINSTANCE.getTextAreaDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextAreaOverrideDescriptionImpl
         * <em>Text Area Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextAreaOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextAreaOverrideDescription()
         * @generated
         */
        EClass TEXT_AREA_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getTextAreaOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_AREA_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getTextAreaOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Text Area
         * Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_AREA_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_TEXT_AREA_EXPRESSION = PropertiesPackage.eINSTANCE
                .getTextAreaOverrideDescription_FilterConditionalStylesFromOverriddenTextAreaExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl
         * <em>Abstract Radio Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractRadioDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractRadioDescription()
         * @generated
         */
        EClass ABSTRACT_RADIO_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractRadioDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_RADIO_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_RADIO_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_RADIO_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_CandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Candidate Display Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_CandidateDisplayExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_RADIO_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_Style();

        /**
         * The meta object literal for the '<em><b>Number Of Columns</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_RADIO_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_NumberOfColumns();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_RADIO_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_RADIO_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractRadioDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Radio Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_RADIO_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_RADIO_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractRadioDescription_FilterConditionalStylesFromExtendedRadioExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.RadioDescriptionImpl <em>Radio
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.RadioDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioDescription()
         * @generated
         */
        EClass RADIO_DESCRIPTION = PropertiesPackage.eINSTANCE.getRadioDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.RadioOverrideDescriptionImpl
         * <em>Radio Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.RadioOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioOverrideDescription()
         * @generated
         */
        EClass RADIO_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getRadioOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference RADIO_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getRadioOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Radio Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RADIO_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_RADIO_EXPRESSION = PropertiesPackage.eINSTANCE
                .getRadioOverrideDescription_FilterConditionalStylesFromOverriddenRadioExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl
         * <em>Abstract List Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractListDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractListDescription()
         * @generated
         */
        EClass ABSTRACT_LIST_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractListDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LIST_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractListDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LIST_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractListDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>On Click Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LIST_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.eINSTANCE.getAbstractListDescription_OnClickOperation();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LIST_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getAbstractListDescription_Actions();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LIST_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractListDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LIST_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractListDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_LIST_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractListDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended List Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LIST_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractListDescription_FilterConditionalStylesFromExtendedListExpression();

        /**
         * The meta object literal for the '<em><b>Filter Actions From Extended List Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_LIST_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_LIST_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractListDescription_FilterActionsFromExtendedListExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ListDescriptionImpl <em>List
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListDescription()
         * @generated
         */
        EClass LIST_DESCRIPTION = PropertiesPackage.eINSTANCE.getListDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl
         * <em>List Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListOverrideDescription()
         * @generated
         */
        EClass LIST_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getListOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference LIST_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getListOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden List Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LIST_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_LIST_EXPRESSION = PropertiesPackage.eINSTANCE
                .getListOverrideDescription_FilterConditionalStylesFromOverriddenListExpression();

        /**
         * The meta object literal for the '<em><b>Filter Actions From Overridden List Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LIST_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_LIST_EXPRESSION = PropertiesPackage.eINSTANCE.getListOverrideDescription_FilterActionsFromOverriddenListExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.OperationDescriptionImpl
         * <em>Operation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.OperationDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getOperationDescription()
         * @generated
         */
        EClass OPERATION_DESCRIPTION = PropertiesPackage.eINSTANCE.getOperationDescription();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference OPERATION_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getOperationDescription_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl
         * <em>Abstract Custom Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractCustomDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractCustomDescription()
         * @generated
         */
        EClass ABSTRACT_CUSTOM_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractCustomDescription();

        /**
         * The meta object literal for the '<em><b>Custom Expressions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS = PropertiesPackage.eINSTANCE.getAbstractCustomDescription_CustomExpressions();

        /**
         * The meta object literal for the '<em><b>Custom Operations</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS = PropertiesPackage.eINSTANCE.getAbstractCustomDescription_CustomOperations();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CUSTOM_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractCustomDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CUSTOM_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractCustomDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_CUSTOM_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractCustomDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Custom Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_CUSTOM_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_CUSTOM_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractCustomDescription_FilterConditionalStylesFromExtendedCustomExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CustomDescriptionImpl <em>Custom
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomDescription()
         * @generated
         */
        EClass CUSTOM_DESCRIPTION = PropertiesPackage.eINSTANCE.getCustomDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CustomOverrideDescriptionImpl
         * <em>Custom Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomOverrideDescription()
         * @generated
         */
        EClass CUSTOM_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getCustomOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getCustomOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Custom Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_CUSTOM_EXPRESSION = PropertiesPackage.eINSTANCE
                .getCustomOverrideDescription_FilterConditionalStylesFromOverriddenCustomExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CustomExpressionImpl <em>Custom
         * Expression</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomExpressionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomExpression()
         * @generated
         */
        EClass CUSTOM_EXPRESSION = PropertiesPackage.eINSTANCE.getCustomExpression();

        /**
         * The meta object literal for the '<em><b>Custom Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_EXPRESSION__CUSTOM_EXPRESSION = PropertiesPackage.eINSTANCE.getCustomExpression_CustomExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CustomOperationImpl <em>Custom
         * Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomOperationImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomOperation()
         * @generated
         */
        EClass CUSTOM_OPERATION = PropertiesPackage.eINSTANCE.getCustomOperation();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_OPERATION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getCustomOperation_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl
         * <em>Abstract Hyperlink Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.AbstractHyperlinkDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getAbstractHyperlinkDescription()
         * @generated
         */
        EClass ABSTRACT_HYPERLINK_DESCRIPTION = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_HYPERLINK_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_HYPERLINK_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_HYPERLINK_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_HYPERLINK_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_Actions();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_HYPERLINK_DESCRIPTION__EXTENDS = PropertiesPackage.eINSTANCE.getAbstractHyperlinkDescription_Extends();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Extended Hyperlink
         * Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractHyperlinkDescription_FilterConditionalStylesFromExtendedHyperlinkExpression();

        /**
         * The meta object literal for the '<em><b>Filter Actions From Extended Hyperlink Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_HYPERLINK_DESCRIPTION__FILTER_ACTIONS_FROM_EXTENDED_HYPERLINK_EXPRESSION = PropertiesPackage.eINSTANCE
                .getAbstractHyperlinkDescription_FilterActionsFromExtendedHyperlinkExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
         * <em>Hyperlink Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkDescription()
         * @generated
         */
        EClass HYPERLINK_DESCRIPTION = PropertiesPackage.eINSTANCE.getHyperlinkDescription();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl
         * <em>Hyperlink Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkOverrideDescription()
         * @generated
         */
        EClass HYPERLINK_OVERRIDE_DESCRIPTION = PropertiesPackage.eINSTANCE.getHyperlinkOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesPackage.eINSTANCE.getHyperlinkOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Hyperlink
         * Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION = PropertiesPackage.eINSTANCE
                .getHyperlinkOverrideDescription_FilterConditionalStylesFromOverriddenHyperlinkExpression();

        /**
         * The meta object literal for the '<em><b>Filter Actions From Overridden Hyperlink Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_OVERRIDE_DESCRIPTION__FILTER_ACTIONS_FROM_OVERRIDDEN_HYPERLINK_EXPRESSION = PropertiesPackage.eINSTANCE
                .getHyperlinkOverrideDescription_FilterActionsFromOverriddenHyperlinkExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WidgetStyleImpl <em>Widget
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetStyle()
         * @generated
         */
        EClass WIDGET_STYLE = PropertiesPackage.eINSTANCE.getWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Label Font Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelFontNameExpression();

        /**
         * The meta object literal for the '<em><b>Label Font Size Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelFontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Label Background Color</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelBackgroundColor();

        /**
         * The meta object literal for the '<em><b>Label Foreground Color</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelForegroundColor();

        /**
         * The meta object literal for the '<em><b>Label Font Format</b></em>' attribute list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelFontFormat();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextWidgetStyleImpl <em>Text
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetStyle()
         * @generated
         */
        EClass TEXT_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getTextWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getTextWidgetStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getTextWidgetStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Font Format</b></em>' attribute list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontFormat();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl <em>Label
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetStyle()
         * @generated
         */
        EClass LABEL_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getLabelWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Font Format</b></em>' attribute list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_FontFormat();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
         * <em>Checkbox Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetStyle()
         * @generated
         */
        EClass CHECKBOX_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getCheckboxWidgetStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl <em>Radio
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetStyle()
         * @generated
         */
        EClass RADIO_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getRadioWidgetStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl <em>Button
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetStyle()
         * @generated
         */
        EClass BUTTON_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getButtonWidgetStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl <em>Select
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetStyle()
         * @generated
         */
        EClass SELECT_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getSelectWidgetStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl <em>Custom
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetStyle()
         * @generated
         */
        EClass CUSTOM_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getCustomWidgetStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ListWidgetStyleImpl <em>List
         * Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetStyle()
         * @generated
         */
        EClass LIST_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getListWidgetStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
         * <em>Hyperlink Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetStyle()
         * @generated
         */
        EClass HYPERLINK_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Font Format</b></em>' attribute list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_FontFormat();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupStyleImpl <em>Group
         * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupStyle()
         * @generated
         */
        EClass GROUP_STYLE = PropertiesPackage.eINSTANCE.getGroupStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getGroupStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>' reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_STYLE__FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getGroupStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Bar Style</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__BAR_STYLE = PropertiesPackage.eINSTANCE.getGroupStyle_BarStyle();

        /**
         * The meta object literal for the '<em><b>Toggle Style</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__TOGGLE_STYLE = PropertiesPackage.eINSTANCE.getGroupStyle_ToggleStyle();

        /**
         * The meta object literal for the '<em><b>Expanded By Default</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__EXPANDED_BY_DEFAULT = PropertiesPackage.eINSTANCE.getGroupStyle_ExpandedByDefault();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
         * <em>Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetConditionalStyle()
         * @generated
         */
        EClass WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetConditionalStyle_PreconditionExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
         * <em>Text Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetConditionalStyle()
         * @generated
         */
        EClass TEXT_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getTextWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getTextWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
         * <em>Label Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetConditionalStyle()
         * @generated
         */
        EClass LABEL_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getLabelWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getLabelWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
         * <em>Checkbox Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetConditionalStyle()
         * @generated
         */
        EClass CHECKBOX_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getCheckboxWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CHECKBOX_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getCheckboxWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
         * <em>Radio Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetConditionalStyle()
         * @generated
         */
        EClass RADIO_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getRadioWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RADIO_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getRadioWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
         * <em>Button Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetConditionalStyle()
         * @generated
         */
        EClass BUTTON_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getButtonWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BUTTON_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getButtonWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
         * <em>Select Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetConditionalStyle()
         * @generated
         */
        EClass SELECT_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getSelectWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SELECT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getSelectWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
         * <em>Custom Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetConditionalStyle()
         * @generated
         */
        EClass CUSTOM_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getCustomWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getCustomWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
         * <em>List Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetConditionalStyle()
         * @generated
         */
        EClass LIST_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getListWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LIST_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getListWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WidgetActionImpl <em>Widget
         * Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetActionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetAction()
         * @generated
         */
        EClass WIDGET_ACTION = PropertiesPackage.eINSTANCE.getWidgetAction();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_ACTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetAction_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Image Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_ACTION__IMAGE_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetAction_ImageExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIDGET_ACTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getWidgetAction_InitialOperation();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl <em>Hyperlink Widget
         * Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetConditionalStyle()
         * @generated
         */
        EClass HYPERLINK_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getHyperlinkWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getHyperlinkWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
         * <em>Group Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupConditionalStyle()
         * @generated
         */
        EClass GROUP_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getGroupConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getGroupConditionalStyle_Style();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.DialogModelOperationImpl
         * <em>Dialog Model Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.DialogModelOperationImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDialogModelOperation()
         * @generated
         */
        EClass DIALOG_MODEL_OPERATION = PropertiesPackage.eINSTANCE.getDialogModelOperation();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIALOG_MODEL_OPERATION__TITLE_EXPRESSION = PropertiesPackage.eINSTANCE.getDialogModelOperation_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Buttons</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIALOG_MODEL_OPERATION__BUTTONS = PropertiesPackage.eINSTANCE.getDialogModelOperation_Buttons();

        /**
         * The meta object literal for the '<em><b>Page</b></em>' containment reference feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIALOG_MODEL_OPERATION__PAGE = PropertiesPackage.eINSTANCE.getDialogModelOperation_Page();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIALOG_MODEL_OPERATION__GROUPS = PropertiesPackage.eINSTANCE.getDialogModelOperation_Groups();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.DialogButtonImpl <em>Dialog
         * Button</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.DialogButtonImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDialogButton()
         * @generated
         */
        EClass DIALOG_BUTTON = PropertiesPackage.eINSTANCE.getDialogButton();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIALOG_BUTTON__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getDialogButton_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Is Enabled Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIALOG_BUTTON__IS_ENABLED_EXPRESSION = PropertiesPackage.eINSTANCE.getDialogButton_IsEnabledExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIALOG_BUTTON__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getDialogButton_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Default</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DIALOG_BUTTON__DEFAULT = PropertiesPackage.eINSTANCE.getDialogButton_Default();

        /**
         * The meta object literal for the '<em><b>Close Dialog On Click</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIALOG_BUTTON__CLOSE_DIALOG_ON_CLICK = PropertiesPackage.eINSTANCE.getDialogButton_CloseDialogOnClick();

        /**
         * The meta object literal for the '<em><b>Rollback Changes On Close</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIALOG_BUTTON__ROLLBACK_CHANGES_ON_CLOSE = PropertiesPackage.eINSTANCE.getDialogButton_RollbackChangesOnClose();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.WizardModelOperationImpl
         * <em>Wizard Model Operation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WizardModelOperationImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWizardModelOperation()
         * @generated
         */
        EClass WIZARD_MODEL_OPERATION = PropertiesPackage.eINSTANCE.getWizardModelOperation();

        /**
         * The meta object literal for the '<em><b>Window Title Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIZARD_MODEL_OPERATION__WINDOW_TITLE_EXPRESSION = PropertiesPackage.eINSTANCE.getWizardModelOperation_WindowTitleExpression();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIZARD_MODEL_OPERATION__TITLE_EXPRESSION = PropertiesPackage.eINSTANCE.getWizardModelOperation_TitleExpression();

        /**
         * The meta object literal for the '<em><b>Description Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIZARD_MODEL_OPERATION__DESCRIPTION_EXPRESSION = PropertiesPackage.eINSTANCE.getWizardModelOperation_DescriptionExpression();

        /**
         * The meta object literal for the '<em><b>Is Page Complete Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIZARD_MODEL_OPERATION__IS_PAGE_COMPLETE_EXPRESSION = PropertiesPackage.eINSTANCE.getWizardModelOperation_IsPageCompleteExpression();

        /**
         * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIZARD_MODEL_OPERATION__PAGES = PropertiesPackage.eINSTANCE.getWizardModelOperation_Pages();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIZARD_MODEL_OPERATION__GROUPS = PropertiesPackage.eINSTANCE.getWizardModelOperation_Groups();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIZARD_MODEL_OPERATION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getWizardModelOperation_InitialOperation();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.impl.EditSupportImpl <em>Edit
         * Support</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.EditSupportImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getEditSupport()
         * @generated
         */
        EClass EDIT_SUPPORT = PropertiesPackage.eINSTANCE.getEditSupport();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION <em>FILL LAYOUT
         * ORIENTATION</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFILL_LAYOUT_ORIENTATION()
         * @generated
         */
        EEnum FILL_LAYOUT_ORIENTATION = PropertiesPackage.eINSTANCE.getFILL_LAYOUT_ORIENTATION();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.ToggleStyle <em>Toggle Style</em>}'
         * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ToggleStyle
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getToggleStyle()
         * @generated
         */
        EEnum TOGGLE_STYLE = PropertiesPackage.eINSTANCE.getToggleStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.properties.TitleBarStyle <em>Title Bar
         * Style</em>}' enum. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.TitleBarStyle
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTitleBarStyle()
         * @generated
         */
        EEnum TITLE_BAR_STYLE = PropertiesPackage.eINSTANCE.getTitleBarStyle();

    }

} // PropertiesPackage
