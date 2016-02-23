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
    String eNAME = "properties";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/properties/1.0.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "properties";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    PropertiesPackage eINSTANCE = org.eclipse.sirius.properties.impl.PropertiesPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
     * <em>View Extension Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
     * @generated
     */
    int VIEW_EXTENSION_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__IDENTIFIER = DescriptionPackage.EXTENSION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Metamodels</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__METAMODELS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Pages</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__PAGES = DescriptionPackage.EXTENSION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Default Page</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE = DescriptionPackage.EXTENSION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION = DescriptionPackage.EXTENSION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Groups</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__GROUPS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>View Extension Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.EXTENSION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.PageDescriptionImpl
     * <em>Page Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
     * @generated
     */
    int PAGE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__DOMAIN_CLASS = 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__GROUPS = 4;

    /**
     * The number of structural features of the '<em>Page Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl
     * <em>Group Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
     * @generated
     */
    int GROUP_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__DOMAIN_CLASS = 2;

    /**
     * The feature id for the '<em><b>Semantic Candidate Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Container</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONTAINER = 4;

    /**
     * The number of structural features of the '<em>Group Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
     * <em>Container Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
     * @generated
     */
    int CONTAINER_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Widgets</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__WIDGETS = 1;

    /**
     * The feature id for the '<em><b>Dynamic Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS = 2;

    /**
     * The number of structural features of the '<em>Container Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
     * <em>Widget Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
     * @generated
     */
    int WIDGET_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__LABEL_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__HELP_EXPRESSION = 2;

    /**
     * The number of structural features of the '<em>Widget Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.TextDescriptionImpl
     * <em>Text Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
     * @generated
     */
    int TEXT_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Text Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl
     * <em>Button Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.ButtonDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonDescription()
     * @generated
     */
    int BUTTON_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Button Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Button Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl
     * <em>Label Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.LabelDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelDescription()
     * @generated
     */
    int LABEL_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Body Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__BODY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
     * <em>Checkbox Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxDescription()
     * @generated
     */
    int CHECKBOX_DESCRIPTION = 8;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Checkbox Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl
     * <em>Select Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.SelectDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectDescription()
     * @generated
     */
    int SELECT_DESCRIPTION = 9;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Select Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.DynamicMappingForImpl
     * <em>Dynamic Mapping For</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.DynamicMappingForImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingFor()
     * @generated
     */
    int DYNAMIC_MAPPING_FOR = 10;

    /**
     * The feature id for the '<em><b>Iterator</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__ITERATOR = 0;

    /**
     * The feature id for the '<em><b>Domain Class Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Ifs</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__IFS = 2;

    /**
     * The number of structural features of the '<em>Dynamic Mapping For</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.DynamicMappingIfImpl
     * <em>Dynamic Mapping If</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.DynamicMappingIfImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingIf()
     * @generated
     */
    int DYNAMIC_MAPPING_IF = 11;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Widget</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF__WIDGET = 1;

    /**
     * The number of structural features of the '<em>Dynamic Mapping If</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_IF_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl
     * <em>Text Area Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextAreaDescription()
     * @generated
     */
    int TEXT_AREA_DESCRIPTION = 12;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__IDENTIFIER = PropertiesPackage.TEXT_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.TEXT_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.TEXT_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.TEXT_DESCRIPTION__VALUE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.TEXT_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Line Count</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__LINE_COUNT = PropertiesPackage.TEXT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Text Area Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.TEXT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.RadioDescriptionImpl
     * <em>Radio Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.RadioDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioDescription()
     * @generated
     */
    int RADIO_DESCRIPTION = 13;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Candidates Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Candidate Display Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Radio Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl
     * <em>Single Reference Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSingleReferenceDescription()
     * @generated
     */
    int SINGLE_REFERENCE_DESCRIPTION = 14;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Create Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Search Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Unset Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Single Reference Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SINGLE_REFERENCE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.OperationDescriptionImpl
     * <em>Operation Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.OperationDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getOperationDescription()
     * @generated
     */
    int OPERATION_DESCRIPTION = 15;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_DESCRIPTION__INITIAL_OPERATION = 0;

    /**
     * The number of structural features of the '<em>Operation Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int OPERATION_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl
     * <em>Multiple References Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getMultipleReferencesDescription()
     * @generated
     */
    int MULTIPLE_REFERENCES_DESCRIPTION = 16;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Create Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Search Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Unset Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Up Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Down Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '
     * <em>Multiple References Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int MULTIPLE_REFERENCES_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * @return the meta object for class '<em>View Extension Description</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription
     * @generated
     */
    EClass getViewExtensionDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getIdentifier()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_Identifier();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels
     * <em>Metamodels</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Metamodels</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getMetamodels()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Metamodels();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getPages
     * <em>Pages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Pages</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getPages()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Pages();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getDefaultPage
     * <em>Default Page</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Default Page</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getDefaultPage()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_DefaultPage();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getLabelExpression()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EAttribute getViewExtensionDescription_LabelExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription#getGroups
     * <em>Groups</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Groups</em>'.
     * @see org.eclipse.sirius.properties.ViewExtensionDescription#getGroups()
     * @see #getViewExtensionDescription()
     * @generated
     */
    EReference getViewExtensionDescription_Groups();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.PageDescription
     * <em>Page Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Page Description</em>'.
     * @see org.eclipse.sirius.properties.PageDescription
     * @generated
     */
    EClass getPageDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.PageDescription#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getIdentifier()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.PageDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getLabelExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.PageDescription#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getDomainClass()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.PageDescription#getSemanticCandidateExpression
     * <em>Semantic Candidate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getSemanticCandidateExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.properties.PageDescription#getGroups
     * <em>Groups</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Groups</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getGroups()
     * @see #getPageDescription()
     * @generated
     */
    EReference getPageDescription_Groups();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.GroupDescription
     * <em>Group Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Group Description</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription
     * @generated
     */
    EClass getGroupDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupDescription#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getIdentifier()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getLabelExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupDescription#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getDomainClass()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupDescription#getSemanticCandidateExpression
     * <em>Semantic Candidate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidate Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getSemanticCandidateExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_SemanticCandidateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.GroupDescription#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Container</em>
     *         '.
     * @see org.eclipse.sirius.properties.GroupDescription#getContainer()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_Container();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ContainerDescription
     * <em>Container Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Description</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription
     * @generated
     */
    EClass getContainerDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ContainerDescription#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getIdentifier()
     * @see #getContainerDescription()
     * @generated
     */
    EAttribute getContainerDescription_Identifier();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ContainerDescription#getWidgets
     * <em>Widgets</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Widgets</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getWidgets()
     * @see #getContainerDescription()
     * @generated
     */
    EReference getContainerDescription_Widgets();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ContainerDescription#getDynamicMappings
     * <em>Dynamic Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Dynamic Mappings</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getDynamicMappings()
     * @see #getContainerDescription()
     * @generated
     */
    EReference getContainerDescription_DynamicMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.WidgetDescription
     * <em>Widget Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Widget Description</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription
     * @generated
     */
    EClass getWidgetDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetDescription#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getIdentifier()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getLabelExpression()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetDescription#getHelpExpression
     * <em>Help Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Help Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetDescription#getHelpExpression()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_HelpExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.TextDescription
     * <em>Text Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Text Description</em>'.
     * @see org.eclipse.sirius.properties.TextDescription
     * @generated
     */
    EClass getTextDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getValueExpression()
     * @see #getTextDescription()
     * @generated
     */
    EAttribute getTextDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.TextDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getInitialOperation()
     * @see #getTextDescription()
     * @generated
     */
    EReference getTextDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ButtonDescription
     * <em>Button Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Button Description</em>'.
     * @see org.eclipse.sirius.properties.ButtonDescription
     * @generated
     */
    EClass getButtonDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ButtonDescription#getButtonLabelExpression
     * <em>Button Label Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Button Label Expression</em>'.
     * @see org.eclipse.sirius.properties.ButtonDescription#getButtonLabelExpression()
     * @see #getButtonDescription()
     * @generated
     */
    EAttribute getButtonDescription_ButtonLabelExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ButtonDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.ButtonDescription#getInitialOperation()
     * @see #getButtonDescription()
     * @generated
     */
    EReference getButtonDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.LabelDescription
     * <em>Label Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Label Description</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription
     * @generated
     */
    EClass getLabelDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelDescription#getBodyExpression
     * <em>Body Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Body Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription#getBodyExpression()
     * @see #getLabelDescription()
     * @generated
     */
    EAttribute getLabelDescription_BodyExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CheckboxDescription
     * <em>Checkbox Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Checkbox Description</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription
     * @generated
     */
    EClass getCheckboxDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CheckboxDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription#getValueExpression()
     * @see #getCheckboxDescription()
     * @generated
     */
    EAttribute getCheckboxDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CheckboxDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription#getInitialOperation()
     * @see #getCheckboxDescription()
     * @generated
     */
    EReference getCheckboxDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.SelectDescription
     * <em>Select Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Select Description</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription
     * @generated
     */
    EClass getSelectDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SelectDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getValueExpression()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SelectDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getInitialOperation()
     * @see #getSelectDescription()
     * @generated
     */
    EReference getSelectDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SelectDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Candidates Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.SelectDescription#getCandidatesExpression()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SelectDescription#getCandidateDisplayExpression
     * <em>Candidate Display Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Candidate Display Expression</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getCandidateDisplayExpression()
     * @see #getSelectDescription()
     * @generated
     */
    EAttribute getSelectDescription_CandidateDisplayExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.DynamicMappingFor
     * <em>Dynamic Mapping For</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Dynamic Mapping For</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingFor
     * @generated
     */
    EClass getDynamicMappingFor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.DynamicMappingFor#getIterator
     * <em>Iterator</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Iterator</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingFor#getIterator()
     * @see #getDynamicMappingFor()
     * @generated
     */
    EAttribute getDynamicMappingFor_Iterator();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.DynamicMappingFor#getDomainClassExpression
     * <em>Domain Class Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Domain Class Expression</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingFor#getDomainClassExpression()
     * @see #getDynamicMappingFor()
     * @generated
     */
    EAttribute getDynamicMappingFor_DomainClassExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.DynamicMappingFor#getIfs
     * <em>Ifs</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Ifs</em>
     *         '.
     * @see org.eclipse.sirius.properties.DynamicMappingFor#getIfs()
     * @see #getDynamicMappingFor()
     * @generated
     */
    EReference getDynamicMappingFor_Ifs();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.DynamicMappingIf
     * <em>Dynamic Mapping If</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Dynamic Mapping If</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingIf
     * @generated
     */
    EClass getDynamicMappingIf();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.DynamicMappingIf#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.DynamicMappingIf#getPredicateExpression()
     * @see #getDynamicMappingIf()
     * @generated
     */
    EAttribute getDynamicMappingIf_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.DynamicMappingIf#getWidget
     * <em>Widget</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Widget</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingIf#getWidget()
     * @see #getDynamicMappingIf()
     * @generated
     */
    EReference getDynamicMappingIf_Widget();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.TextAreaDescription
     * <em>Text Area Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Text Area Description</em>'.
     * @see org.eclipse.sirius.properties.TextAreaDescription
     * @generated
     */
    EClass getTextAreaDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextAreaDescription#getLineCount
     * <em>Line Count</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Count</em>'.
     * @see org.eclipse.sirius.properties.TextAreaDescription#getLineCount()
     * @see #getTextAreaDescription()
     * @generated
     */
    EAttribute getTextAreaDescription_LineCount();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.RadioDescription
     * <em>Radio Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Radio Description</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription
     * @generated
     */
    EClass getRadioDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.RadioDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription#getValueExpression()
     * @see #getRadioDescription()
     * @generated
     */
    EAttribute getRadioDescription_ValueExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.RadioDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription#getInitialOperation()
     * @see #getRadioDescription()
     * @generated
     */
    EReference getRadioDescription_InitialOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.RadioDescription#getCandidatesExpression
     * <em>Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Candidates Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.RadioDescription#getCandidatesExpression()
     * @see #getRadioDescription()
     * @generated
     */
    EAttribute getRadioDescription_CandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.RadioDescription#getCandidateDisplayExpression
     * <em>Candidate Display Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Candidate Display Expression</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription#getCandidateDisplayExpression()
     * @see #getRadioDescription()
     * @generated
     */
    EAttribute getRadioDescription_CandidateDisplayExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription
     * <em>Single Reference Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Single Reference Description</em>
     *         '.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription
     * @generated
     */
    EClass getSingleReferenceDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription#getValueExpression()
     * @see #getSingleReferenceDescription()
     * @generated
     */
    EAttribute getSingleReferenceDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getDisplayExpression
     * <em>Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription#getDisplayExpression()
     * @see #getSingleReferenceDescription()
     * @generated
     */
    EAttribute getSingleReferenceDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getCreateOperation
     * <em>Create Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Create Operation</em>'.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription#getCreateOperation()
     * @see #getSingleReferenceDescription()
     * @generated
     */
    EReference getSingleReferenceDescription_CreateOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getSearchOperation
     * <em>Search Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Search Operation</em>'.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription#getSearchOperation()
     * @see #getSingleReferenceDescription()
     * @generated
     */
    EReference getSingleReferenceDescription_SearchOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getUnsetOperation
     * <em>Unset Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Unset Operation</em>'.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription#getUnsetOperation()
     * @see #getSingleReferenceDescription()
     * @generated
     */
    EReference getSingleReferenceDescription_UnsetOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SingleReferenceDescription#getOnClickOperation
     * <em>On Click Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>On Click Operation</em>'.
     * @see org.eclipse.sirius.properties.SingleReferenceDescription#getOnClickOperation()
     * @see #getSingleReferenceDescription()
     * @generated
     */
    EReference getSingleReferenceDescription_OnClickOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.OperationDescription
     * <em>Operation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Operation Description</em>'.
     * @see org.eclipse.sirius.properties.OperationDescription
     * @generated
     */
    EClass getOperationDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.OperationDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.OperationDescription#getInitialOperation()
     * @see #getOperationDescription()
     * @generated
     */
    EReference getOperationDescription_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription
     * <em>Multiple References Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Multiple References Description</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription
     * @generated
     */
    EClass getMultipleReferencesDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getValueExpression()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EAttribute getMultipleReferencesDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getDisplayExpression
     * <em>Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getDisplayExpression()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EAttribute getMultipleReferencesDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getCreateOperation
     * <em>Create Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Create Operation</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getCreateOperation()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EReference getMultipleReferencesDescription_CreateOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getSearchOperation
     * <em>Search Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Search Operation</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getSearchOperation()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EReference getMultipleReferencesDescription_SearchOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getUnsetOperation
     * <em>Unset Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Unset Operation</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getUnsetOperation()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EReference getMultipleReferencesDescription_UnsetOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getOnClickOperation
     * <em>On Click Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>On Click Operation</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getOnClickOperation()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EReference getMultipleReferencesDescription_OnClickOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getUpOperation
     * <em>Up Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Up Operation</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getUpOperation()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EReference getMultipleReferencesDescription_UpOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.MultipleReferencesDescription#getDownOperation
     * <em>Down Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Down Operation</em>'.
     * @see org.eclipse.sirius.properties.MultipleReferencesDescription#getDownOperation()
     * @see #getMultipleReferencesDescription()
     * @generated
     */
    EReference getMultipleReferencesDescription_DownOperation();

    /**
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesFactory getPropertiesFactory();

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
         * {@link org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
         * <em>View Extension Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.ViewExtensionDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getViewExtensionDescription()
         * @generated
         */
        EClass VIEW_EXTENSION_DESCRIPTION = PropertiesPackage.eINSTANCE.getViewExtensionDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__IDENTIFIER = PropertiesPackage.eINSTANCE.getViewExtensionDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Metamodels</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__METAMODELS = PropertiesPackage.eINSTANCE.getViewExtensionDescription_Metamodels();

        /**
         * The meta object literal for the '<em><b>Pages</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__PAGES = PropertiesPackage.eINSTANCE.getViewExtensionDescription_Pages();

        /**
         * The meta object literal for the '<em><b>Default Page</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__DEFAULT_PAGE = PropertiesPackage.eINSTANCE.getViewExtensionDescription_DefaultPage();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute VIEW_EXTENSION_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getViewExtensionDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference VIEW_EXTENSION_DESCRIPTION__GROUPS = PropertiesPackage.eINSTANCE.getViewExtensionDescription_Groups();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.PageDescriptionImpl
         * <em>Page Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.PageDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageDescription()
         * @generated
         */
        EClass PAGE_DESCRIPTION = PropertiesPackage.eINSTANCE.getPageDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__IDENTIFIER = PropertiesPackage.eINSTANCE.getPageDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getPageDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.eINSTANCE.getPageDescription_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidate Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.eINSTANCE.getPageDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference PAGE_DESCRIPTION__GROUPS = PropertiesPackage.eINSTANCE.getPageDescription_Groups();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.GroupDescriptionImpl
         * <em>Group Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.GroupDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupDescription()
         * @generated
         */
        EClass GROUP_DESCRIPTION = PropertiesPackage.eINSTANCE.getGroupDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__IDENTIFIER = PropertiesPackage.eINSTANCE.getGroupDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__DOMAIN_CLASS = PropertiesPackage.eINSTANCE.getGroupDescription_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidate Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__SEMANTIC_CANDIDATE_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupDescription_SemanticCandidateExpression();

        /**
         * The meta object literal for the '<em><b>Container</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GROUP_DESCRIPTION__CONTAINER = PropertiesPackage.eINSTANCE.getGroupDescription_Container();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
         * <em>Container Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.ContainerDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getContainerDescription()
         * @generated
         */
        EClass CONTAINER_DESCRIPTION = PropertiesPackage.eINSTANCE.getContainerDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTAINER_DESCRIPTION__IDENTIFIER = PropertiesPackage.eINSTANCE.getContainerDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Widgets</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DESCRIPTION__WIDGETS = PropertiesPackage.eINSTANCE.getContainerDescription_Widgets();

        /**
         * The meta object literal for the '<em><b>Dynamic Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CONTAINER_DESCRIPTION__DYNAMIC_MAPPINGS = PropertiesPackage.eINSTANCE.getContainerDescription_DynamicMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
         * <em>Widget Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.WidgetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetDescription()
         * @generated
         */
        EClass WIDGET_DESCRIPTION = PropertiesPackage.eINSTANCE.getWidgetDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__IDENTIFIER = PropertiesPackage.eINSTANCE.getWidgetDescription_Identifier();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Help Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetDescription_HelpExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.TextDescriptionImpl
         * <em>Text Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.TextDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextDescription()
         * @generated
         */
        EClass TEXT_DESCRIPTION = PropertiesPackage.eINSTANCE.getTextDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEXT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getTextDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TEXT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getTextDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ButtonDescriptionImpl
         * <em>Button Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.ButtonDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonDescription()
         * @generated
         */
        EClass BUTTON_DESCRIPTION = PropertiesPackage.eINSTANCE.getButtonDescription();

        /**
         * The meta object literal for the '
         * <em><b>Button Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute BUTTON_DESCRIPTION__BUTTON_LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getButtonDescription_ButtonLabelExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference BUTTON_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getButtonDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.LabelDescriptionImpl
         * <em>Label Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.LabelDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelDescription()
         * @generated
         */
        EClass LABEL_DESCRIPTION = PropertiesPackage.eINSTANCE.getLabelDescription();

        /**
         * The meta object literal for the '<em><b>Body Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_DESCRIPTION__BODY_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelDescription_BodyExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
         * <em>Checkbox Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.CheckboxDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxDescription()
         * @generated
         */
        EClass CHECKBOX_DESCRIPTION = PropertiesPackage.eINSTANCE.getCheckboxDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CHECKBOX_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getCheckboxDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CHECKBOX_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getCheckboxDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.SelectDescriptionImpl
         * <em>Select Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.SelectDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectDescription()
         * @generated
         */
        EClass SELECT_DESCRIPTION = PropertiesPackage.eINSTANCE.getSelectDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getSelectDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SELECT_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getSelectDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.eINSTANCE.getSelectDescription_CandidatesExpression();

        /**
         * The meta object literal for the '
         * <em><b>Candidate Display Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SELECT_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getSelectDescription_CandidateDisplayExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.DynamicMappingForImpl
         * <em>Dynamic Mapping For</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.DynamicMappingForImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingFor()
         * @generated
         */
        EClass DYNAMIC_MAPPING_FOR = PropertiesPackage.eINSTANCE.getDynamicMappingFor();

        /**
         * The meta object literal for the '<em><b>Iterator</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DYNAMIC_MAPPING_FOR__ITERATOR = PropertiesPackage.eINSTANCE.getDynamicMappingFor_Iterator();

        /**
         * The meta object literal for the '
         * <em><b>Domain Class Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DYNAMIC_MAPPING_FOR__DOMAIN_CLASS_EXPRESSION = PropertiesPackage.eINSTANCE.getDynamicMappingFor_DomainClassExpression();

        /**
         * The meta object literal for the '<em><b>Ifs</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DYNAMIC_MAPPING_FOR__IFS = PropertiesPackage.eINSTANCE.getDynamicMappingFor_Ifs();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.DynamicMappingIfImpl
         * <em>Dynamic Mapping If</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.DynamicMappingIfImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getDynamicMappingIf()
         * @generated
         */
        EClass DYNAMIC_MAPPING_IF = PropertiesPackage.eINSTANCE.getDynamicMappingIf();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DYNAMIC_MAPPING_IF__PREDICATE_EXPRESSION = PropertiesPackage.eINSTANCE.getDynamicMappingIf_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Widget</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DYNAMIC_MAPPING_IF__WIDGET = PropertiesPackage.eINSTANCE.getDynamicMappingIf_Widget();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.RadioDescriptionImpl
         * <em>Radio Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.RadioDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioDescription()
         * @generated
         */
        EClass RADIO_DESCRIPTION = PropertiesPackage.eINSTANCE.getRadioDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RADIO_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getRadioDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference RADIO_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getRadioDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * <em><b>Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RADIO_DESCRIPTION__CANDIDATES_EXPRESSION = PropertiesPackage.eINSTANCE.getRadioDescription_CandidatesExpression();

        /**
         * The meta object literal for the '
         * <em><b>Candidate Display Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RADIO_DESCRIPTION__CANDIDATE_DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getRadioDescription_CandidateDisplayExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl
         * <em>Single Reference Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.SingleReferenceDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSingleReferenceDescription()
         * @generated
         */
        EClass SINGLE_REFERENCE_DESCRIPTION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SINGLE_REFERENCE_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SINGLE_REFERENCE_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>Create Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SINGLE_REFERENCE_DESCRIPTION__CREATE_OPERATION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription_CreateOperation();

        /**
         * The meta object literal for the '<em><b>Search Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SINGLE_REFERENCE_DESCRIPTION__SEARCH_OPERATION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription_SearchOperation();

        /**
         * The meta object literal for the '<em><b>Unset Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SINGLE_REFERENCE_DESCRIPTION__UNSET_OPERATION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription_UnsetOperation();

        /**
         * The meta object literal for the '<em><b>On Click Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference SINGLE_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.eINSTANCE.getSingleReferenceDescription_OnClickOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.OperationDescriptionImpl
         * <em>Operation Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.OperationDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getOperationDescription()
         * @generated
         */
        EClass OPERATION_DESCRIPTION = PropertiesPackage.eINSTANCE.getOperationDescription();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference OPERATION_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getOperationDescription_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl
         * <em>Multiple References Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.MultipleReferencesDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getMultipleReferencesDescription()
         * @generated
         */
        EClass MULTIPLE_REFERENCES_DESCRIPTION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MULTIPLE_REFERENCES_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute MULTIPLE_REFERENCES_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>Create Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MULTIPLE_REFERENCES_DESCRIPTION__CREATE_OPERATION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_CreateOperation();

        /**
         * The meta object literal for the '<em><b>Search Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MULTIPLE_REFERENCES_DESCRIPTION__SEARCH_OPERATION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_SearchOperation();

        /**
         * The meta object literal for the '<em><b>Unset Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MULTIPLE_REFERENCES_DESCRIPTION__UNSET_OPERATION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_UnsetOperation();

        /**
         * The meta object literal for the '<em><b>On Click Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MULTIPLE_REFERENCES_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_OnClickOperation();

        /**
         * The meta object literal for the '<em><b>Up Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MULTIPLE_REFERENCES_DESCRIPTION__UP_OPERATION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_UpOperation();

        /**
         * The meta object literal for the '<em><b>Down Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference MULTIPLE_REFERENCES_DESCRIPTION__DOWN_OPERATION = PropertiesPackage.eINSTANCE.getMultipleReferencesDescription_DownOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl
         * <em>Text Area Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.properties.impl.TextAreaDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextAreaDescription()
         * @generated
         */
        EClass TEXT_AREA_DESCRIPTION = PropertiesPackage.eINSTANCE.getTextAreaDescription();

        /**
         * The meta object literal for the '<em><b>Line Count</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEXT_AREA_DESCRIPTION__LINE_COUNT = PropertiesPackage.eINSTANCE.getTextAreaDescription_LineCount();

    }

} // PropertiesPackage
