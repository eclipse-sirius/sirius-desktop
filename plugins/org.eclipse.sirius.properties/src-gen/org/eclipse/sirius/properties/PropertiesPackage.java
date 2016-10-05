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
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

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
     * The feature id for the '<em><b>Groups</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION__GROUPS = DescriptionPackage.EXTENSION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>View Extension Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_EXTENSION_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.EXTENSION_FEATURE_COUNT + 4;

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
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__PRECONDITION_EXPRESSION = 4;

    /**
     * The feature id for the '<em><b>Groups</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__GROUPS = 5;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION__VALIDATION_SET = 6;

    /**
     * The number of structural features of the '<em>Page Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_DESCRIPTION_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
     * <em>Page Validation Set Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageValidationSetDescription()
     * @generated
     */
    int PAGE_VALIDATION_SET_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Semantic Validation Rules</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = 0;

    /**
     * The number of structural features of the '
     * <em>Page Validation Set Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PAGE_VALIDATION_SET_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
     * <em>Property Validation Rule</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPropertyValidationRule()
     * @generated
     */
    int PROPERTY_VALIDATION_RULE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__NAME = ValidationPackage.VALIDATION_RULE__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__LABEL = ValidationPackage.VALIDATION_RULE__LABEL;

    /**
     * The feature id for the '<em><b>Level</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__LEVEL = ValidationPackage.VALIDATION_RULE__LEVEL;

    /**
     * The feature id for the '<em><b>Message</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__MESSAGE = ValidationPackage.VALIDATION_RULE__MESSAGE;

    /**
     * The feature id for the '<em><b>Audits</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__AUDITS = ValidationPackage.VALIDATION_RULE__AUDITS;

    /**
     * The feature id for the '<em><b>Fixes</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__FIXES = ValidationPackage.VALIDATION_RULE__FIXES;

    /**
     * The feature id for the '<em><b>Targets</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE__TARGETS = ValidationPackage.VALIDATION_RULE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Property Validation Rule</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int PROPERTY_VALIDATION_RULE_FEATURE_COUNT = ValidationPackage.VALIDATION_RULE_FEATURE_COUNT + 1;

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
    int GROUP_DESCRIPTION = 4;

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
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__PRECONDITION_EXPRESSION = 4;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONTROLS = 5;

    /**
     * The feature id for the '<em><b>Validation Set</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__VALIDATION_SET = 6;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__STYLE = 7;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION__CONDITIONAL_STYLES = 8;

    /**
     * The number of structural features of the '<em>Group Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_DESCRIPTION_FEATURE_COUNT = 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
     * <em>Group Validation Set Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupValidationSetDescription()
     * @generated
     */
    int GROUP_VALIDATION_SET_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Semantic Validation Rules</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = 0;

    /**
     * The feature id for the '<em><b>Property Validation Rules</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES = 1;

    /**
     * The number of structural features of the '
     * <em>Group Validation Set Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_VALIDATION_SET_DESCRIPTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ControlDescriptionImpl
     * <em>Control Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ControlDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getControlDescription()
     * @generated
     */
    int CONTROL_DESCRIPTION = 6;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROL_DESCRIPTION__IDENTIFIER = 0;

    /**
     * The number of structural features of the '<em>Control Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROL_DESCRIPTION_FEATURE_COUNT = 1;

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
    int CONTAINER_DESCRIPTION = 7;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__IDENTIFIER = PropertiesPackage.CONTROL_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Controls</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__CONTROLS = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION__LAYOUT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Container Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTAINER_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.LayoutDescriptionImpl
     * <em>Layout Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LayoutDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLayoutDescription()
     * @generated
     */
    int LAYOUT_DESCRIPTION = 8;

    /**
     * The number of structural features of the '<em>Layout Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LAYOUT_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl
     * <em>Fill Layout Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFillLayoutDescription()
     * @generated
     */
    int FILL_LAYOUT_DESCRIPTION = 9;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILL_LAYOUT_DESCRIPTION__ORIENTATION = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Fill Layout Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FILL_LAYOUT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl
     * <em>Grid Layout Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGridLayoutDescription()
     * @generated
     */
    int GRID_LAYOUT_DESCRIPTION = 10;

    /**
     * The feature id for the '<em><b>Number Of Columns</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Make Columns With Equal Width</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Grid Layout Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GRID_LAYOUT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.LAYOUT_DESCRIPTION_FEATURE_COUNT + 2;

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
    int WIDGET_DESCRIPTION = 11;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__IDENTIFIER = PropertiesPackage.CONTROL_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Widget Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

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
    int TEXT_DESCRIPTION = 12;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

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
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Text Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

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
    int BUTTON_DESCRIPTION = 13;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

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
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Button Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

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
    int LABEL_DESCRIPTION = 14;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION__ACTIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Label Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

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
    int CHECKBOX_DESCRIPTION = 15;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

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
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Checkbox Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

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
    int SELECT_DESCRIPTION = 16;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

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
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Select Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

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
    int DYNAMIC_MAPPING_FOR = 17;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__IDENTIFIER = PropertiesPackage.CONTROL_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Iterator</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__ITERATOR = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Iterable Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__ITERABLE_EXPRESSION = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Ifs</b></em>' containment reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR__IFS = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Dynamic Mapping For</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DYNAMIC_MAPPING_FOR_FEATURE_COUNT = PropertiesPackage.CONTROL_DESCRIPTION_FEATURE_COUNT + 3;

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
    int DYNAMIC_MAPPING_IF = 18;

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
    int TEXT_AREA_DESCRIPTION = 19;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.TEXT_DESCRIPTION__IS_ENABLED_EXPRESSION;

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
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__STYLE = PropertiesPackage.TEXT_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_AREA_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.TEXT_DESCRIPTION__CONDITIONAL_STYLES;

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
    int RADIO_DESCRIPTION = 20;

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
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

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
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Number Of Columns</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Radio Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ListDescriptionImpl
     * <em>List Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListDescription()
     * @generated
     */
    int LIST_DESCRIPTION = 21;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__ACTIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>List Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

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
    int OPERATION_DESCRIPTION = 22;

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
     * {@link org.eclipse.sirius.properties.impl.CustomDescriptionImpl
     * <em>Custom Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomDescription()
     * @generated
     */
    int CUSTOM_DESCRIPTION = 23;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Custom Expressions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Custom Operations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Custom Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CustomExpressionImpl
     * <em>Custom Expression</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomExpressionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomExpression()
     * @generated
     */
    int CUSTOM_EXPRESSION = 24;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Custom Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION__CUSTOM_EXPRESSION = 1;

    /**
     * The number of structural features of the '<em>Custom Expression</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_EXPRESSION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CustomOperationImpl
     * <em>Custom Operation</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomOperationImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomOperation()
     * @generated
     */
    int CUSTOM_OPERATION = 25;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION__IDENTIFIER = 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION__INITIAL_OPERATION = 1;

    /**
     * The number of structural features of the '<em>Custom Operation</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_OPERATION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
     * <em>Hyperlink Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkDescription()
     * @generated
     */
    int HYPERLINK_DESCRIPTION = 26;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Display Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION__ACTIONS = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Hyperlink Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.WidgetStyleImpl
     * <em>Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetStyle()
     * @generated
     */
    int WIDGET_STYLE = 27;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_BACKGROUND_COLOR = 2;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FOREGROUND_COLOR = 3;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE__LABEL_FONT_FORMAT = 4;

    /**
     * The number of structural features of the '<em>Widget Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_STYLE_FEATURE_COUNT = 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.TextWidgetStyleImpl
     * <em>Text Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetStyle()
     * @generated
     */
    int TEXT_WIDGET_STYLE = 28;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Font Format</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Text Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl
     * <em>Label Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetStyle()
     * @generated
     */
    int LABEL_WIDGET_STYLE = 29;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Font Format</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Label Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
     * <em>Checkbox Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetStyle()
     * @generated
     */
    int CHECKBOX_WIDGET_STYLE = 30;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Checkbox Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl
     * <em>Radio Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetStyle()
     * @generated
     */
    int RADIO_WIDGET_STYLE = 31;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Radio Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl
     * <em>Button Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetStyle()
     * @generated
     */
    int BUTTON_WIDGET_STYLE = 32;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Button Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl
     * <em>Select Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetStyle()
     * @generated
     */
    int SELECT_WIDGET_STYLE = 33;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Select Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl
     * <em>Custom Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetStyle()
     * @generated
     */
    int CUSTOM_WIDGET_STYLE = 34;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Custom Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
     * <em>Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetConditionalStyle()
     * @generated
     */
    int WIDGET_CONDITIONAL_STYLE = 38;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
     * <em>Text Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetConditionalStyle()
     * @generated
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE = 39;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
     * <em>Label Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetConditionalStyle()
     * @generated
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE = 40;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
     * <em>Checkbox Widget Conditional Style</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetConditionalStyle()
     * @generated
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE = 41;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
     * <em>Radio Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetConditionalStyle()
     * @generated
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE = 42;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
     * <em>Button Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetConditionalStyle()
     * @generated
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE = 43;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
     * <em>Select Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetConditionalStyle()
     * @generated
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE = 44;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
     * <em>Custom Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetConditionalStyle()
     * @generated
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE = 45;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.WidgetActionImpl
     * <em>Widget Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.properties.impl.WidgetActionImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetAction()
     * @generated
     */
    int WIDGET_ACTION = 47;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ListWidgetStyleImpl
     * <em>List Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetStyle()
     * @generated
     */
    int LIST_WIDGET_STYLE = 35;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>List Widget Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
     * <em>Hyperlink Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetStyle()
     * @generated
     */
    int HYPERLINK_WIDGET_STYLE = 36;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Font Format</b></em>' attribute list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Hyperlink Widget Style</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.GroupStyleImpl
     * <em>Group Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupStyle()
     * @generated
     */
    int GROUP_STYLE = 37;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__BACKGROUND_COLOR = 0;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__FOREGROUND_COLOR = 1;

    /**
     * The feature id for the '<em><b>Font Name Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__FONT_NAME_EXPRESSION = 2;

    /**
     * The feature id for the '<em><b>Font Size Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__FONT_SIZE_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Bar Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__BAR_STYLE = 4;

    /**
     * The feature id for the '<em><b>Toggle Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__TOGGLE_STYLE = 5;

    /**
     * The feature id for the '<em><b>Expanded By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE__EXPANDED_BY_DEFAULT = 6;

    /**
     * The number of structural features of the '<em>Group Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_STYLE_FEATURE_COUNT = 7;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = 0;

    /**
     * The number of structural features of the '
     * <em>Widget Conditional Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Text Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TEXT_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Label Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Checkbox Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHECKBOX_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Radio Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RADIO_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Button Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BUTTON_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Select Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SELECT_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Custom Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CUSTOM_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
     * <em>List Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetConditionalStyle()
     * @generated
     */
    int LIST_WIDGET_CONDITIONAL_STYLE = 46;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>List Widget Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LIST_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION__LABEL_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION__INITIAL_OPERATION = 1;

    /**
     * The number of structural features of the '<em>Widget Action</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int WIDGET_ACTION_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
     * <em>Hyperlink Widget Conditional Style</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetConditionalStyle()
     * @generated
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE = 48;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Hyperlink Widget Conditional Style</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int HYPERLINK_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
     * <em>Group Conditional Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupConditionalStyle()
     * @generated
     */
    int GROUP_CONDITIONAL_STYLE = 49;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Group Conditional Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GROUP_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.impl.EditSupportImpl
     * <em>Edit Support</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.properties.impl.EditSupportImpl
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getEditSupport()
     * @generated
     */
    int EDIT_SUPPORT = 50;

    /**
     * The number of structural features of the '<em>Edit Support</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDIT_SUPPORT_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * <em>FILL LAYOUT ORIENTATION</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFILL_LAYOUT_ORIENTATION()
     * @generated
     */
    int FILL_LAYOUT_ORIENTATION = 51;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.ToggleStyle <em>Toggle Style</em>}'
     * enum. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ToggleStyle
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getToggleStyle()
     * @generated
     */
    int TOGGLE_STYLE = 52;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.TitleBarStyle
     * <em>Title Bar Style</em>}' enum. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.TitleBarStyle
     * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTitleBarStyle()
     * @generated
     */
    int TITLE_BAR_STYLE = 53;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ViewExtensionDescription
     * <em>View Extension Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
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
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.PageDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getPreconditionExpression()
     * @see #getPageDescription()
     * @generated
     */
    EAttribute getPageDescription_PreconditionExpression();

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
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.PageDescription#getValidationSet
     * <em>Validation Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Validation Set</em>'.
     * @see org.eclipse.sirius.properties.PageDescription#getValidationSet()
     * @see #getPageDescription()
     * @generated
     */
    EReference getPageDescription_ValidationSet();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.PageValidationSetDescription
     * <em>Page Validation Set Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Page Validation Set Description</em>'.
     * @see org.eclipse.sirius.properties.PageValidationSetDescription
     * @generated
     */
    EClass getPageValidationSetDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.PageValidationSetDescription#getSemanticValidationRules
     * <em>Semantic Validation Rules</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Semantic Validation Rules</em>'.
     * @see org.eclipse.sirius.properties.PageValidationSetDescription#getSemanticValidationRules()
     * @see #getPageValidationSetDescription()
     * @generated
     */
    EReference getPageValidationSetDescription_SemanticValidationRules();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.PropertyValidationRule
     * <em>Property Validation Rule</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Property Validation Rule</em>'.
     * @see org.eclipse.sirius.properties.PropertyValidationRule
     * @generated
     */
    EClass getPropertyValidationRule();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.properties.PropertyValidationRule#getTargets
     * <em>Targets</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Targets</em>'.
     * @see org.eclipse.sirius.properties.PropertyValidationRule#getTargets()
     * @see #getPropertyValidationRule()
     * @generated
     */
    EReference getPropertyValidationRule_Targets();

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
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getPreconditionExpression()
     * @see #getGroupDescription()
     * @generated
     */
    EAttribute getGroupDescription_PreconditionExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.GroupDescription#getControls
     * <em>Controls</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Controls</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getControls()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_Controls();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.GroupDescription#getValidationSet
     * <em>Validation Set</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '
     *         <em>Validation Set</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getValidationSet()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_ValidationSet();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.GroupDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getStyle()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.GroupDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.GroupDescription#getConditionalStyles()
     * @see #getGroupDescription()
     * @generated
     */
    EReference getGroupDescription_ConditionalStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.GroupValidationSetDescription
     * <em>Group Validation Set Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Group Validation Set Description</em>'.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription
     * @generated
     */
    EClass getGroupValidationSetDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.GroupValidationSetDescription#getSemanticValidationRules
     * <em>Semantic Validation Rules</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Semantic Validation Rules</em>'.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription#getSemanticValidationRules()
     * @see #getGroupValidationSetDescription()
     * @generated
     */
    EReference getGroupValidationSetDescription_SemanticValidationRules();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.GroupValidationSetDescription#getPropertyValidationRules
     * <em>Property Validation Rules</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Property Validation Rules</em>'.
     * @see org.eclipse.sirius.properties.GroupValidationSetDescription#getPropertyValidationRules()
     * @see #getGroupValidationSetDescription()
     * @generated
     */
    EReference getGroupValidationSetDescription_PropertyValidationRules();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ControlDescription
     * <em>Control Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Control Description</em>'.
     * @see org.eclipse.sirius.properties.ControlDescription
     * @generated
     */
    EClass getControlDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ControlDescription#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.ControlDescription#getIdentifier()
     * @see #getControlDescription()
     * @generated
     */
    EAttribute getControlDescription_Identifier();

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
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ContainerDescription#getControls
     * <em>Controls</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Controls</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getControls()
     * @see #getContainerDescription()
     * @generated
     */
    EReference getContainerDescription_Controls();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ContainerDescription#getLayout
     * <em>Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Layout</em>'.
     * @see org.eclipse.sirius.properties.ContainerDescription#getLayout()
     * @see #getContainerDescription()
     * @generated
     */
    EReference getContainerDescription_Layout();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.LayoutDescription
     * <em>Layout Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Layout Description</em>'.
     * @see org.eclipse.sirius.properties.LayoutDescription
     * @generated
     */
    EClass getLayoutDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.FillLayoutDescription
     * <em>Fill Layout Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Fill Layout Description</em>'.
     * @see org.eclipse.sirius.properties.FillLayoutDescription
     * @generated
     */
    EClass getFillLayoutDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.FillLayoutDescription#getOrientation
     * <em>Orientation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see org.eclipse.sirius.properties.FillLayoutDescription#getOrientation()
     * @see #getFillLayoutDescription()
     * @generated
     */
    EAttribute getFillLayoutDescription_Orientation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.GridLayoutDescription
     * <em>Grid Layout Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Grid Layout Description</em>'.
     * @see org.eclipse.sirius.properties.GridLayoutDescription
     * @generated
     */
    EClass getGridLayoutDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GridLayoutDescription#getNumberOfColumns
     * <em>Number Of Columns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Number Of Columns</em>'.
     * @see org.eclipse.sirius.properties.GridLayoutDescription#getNumberOfColumns()
     * @see #getGridLayoutDescription()
     * @generated
     */
    EAttribute getGridLayoutDescription_NumberOfColumns();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GridLayoutDescription#isMakeColumnsWithEqualWidth
     * <em>Make Columns With Equal Width</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Make Columns With Equal Width</em>'.
     * @see org.eclipse.sirius.properties.GridLayoutDescription#isMakeColumnsWithEqualWidth()
     * @see #getGridLayoutDescription()
     * @generated
     */
    EAttribute getGridLayoutDescription_MakeColumnsWithEqualWidth();

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
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetDescription#getIsEnabledExpression
     * <em>Is Enabled Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Enabled Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.WidgetDescription#getIsEnabledExpression()
     * @see #getWidgetDescription()
     * @generated
     */
    EAttribute getWidgetDescription_IsEnabledExpression();

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
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.TextDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getStyle()
     * @see #getTextDescription()
     * @generated
     */
    EReference getTextDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.TextDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.TextDescription#getConditionalStyles()
     * @see #getTextDescription()
     * @generated
     */
    EReference getTextDescription_ConditionalStyles();

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
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ButtonDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonDescription#getStyle()
     * @see #getButtonDescription()
     * @generated
     */
    EReference getButtonDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ButtonDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.ButtonDescription#getConditionalStyles()
     * @see #getButtonDescription()
     * @generated
     */
    EReference getButtonDescription_ConditionalStyles();

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
     * {@link org.eclipse.sirius.properties.LabelDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription#getValueExpression()
     * @see #getLabelDescription()
     * @generated
     */
    EAttribute getLabelDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelDescription#getDisplayExpression
     * <em>Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription#getDisplayExpression()
     * @see #getLabelDescription()
     * @generated
     */
    EAttribute getLabelDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.LabelDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription#getStyle()
     * @see #getLabelDescription()
     * @generated
     */
    EReference getLabelDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.LabelDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription#getConditionalStyles()
     * @see #getLabelDescription()
     * @generated
     */
    EReference getLabelDescription_ConditionalStyles();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.LabelDescription#getActions
     * <em>Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Actions</em>'.
     * @see org.eclipse.sirius.properties.LabelDescription#getActions()
     * @see #getLabelDescription()
     * @generated
     */
    EReference getLabelDescription_Actions();

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
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CheckboxDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription#getStyle()
     * @see #getCheckboxDescription()
     * @generated
     */
    EReference getCheckboxDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.CheckboxDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.CheckboxDescription#getConditionalStyles()
     * @see #getCheckboxDescription()
     * @generated
     */
    EReference getCheckboxDescription_ConditionalStyles();

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
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SelectDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getStyle()
     * @see #getSelectDescription()
     * @generated
     */
    EReference getSelectDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.SelectDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.SelectDescription#getConditionalStyles()
     * @see #getSelectDescription()
     * @generated
     */
    EReference getSelectDescription_ConditionalStyles();

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
     * {@link org.eclipse.sirius.properties.DynamicMappingFor#getIterableExpression
     * <em>Iterable Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Iterable Expression</em>'.
     * @see org.eclipse.sirius.properties.DynamicMappingFor#getIterableExpression()
     * @see #getDynamicMappingFor()
     * @generated
     */
    EAttribute getDynamicMappingFor_IterableExpression();

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
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.RadioDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription#getStyle()
     * @see #getRadioDescription()
     * @generated
     */
    EReference getRadioDescription_Style();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.RadioDescription#getNumberOfColumns
     * <em>Number Of Columns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Number Of Columns</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription#getNumberOfColumns()
     * @see #getRadioDescription()
     * @generated
     */
    EAttribute getRadioDescription_NumberOfColumns();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.RadioDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.RadioDescription#getConditionalStyles()
     * @see #getRadioDescription()
     * @generated
     */
    EReference getRadioDescription_ConditionalStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ListDescription
     * <em>List Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>List Description</em>'.
     * @see org.eclipse.sirius.properties.ListDescription
     * @generated
     */
    EClass getListDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ListDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.ListDescription#getValueExpression()
     * @see #getListDescription()
     * @generated
     */
    EAttribute getListDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ListDescription#getDisplayExpression
     * <em>Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.ListDescription#getDisplayExpression()
     * @see #getListDescription()
     * @generated
     */
    EAttribute getListDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ListDescription#getOnClickOperation
     * <em>On Click Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>On Click Operation</em>'.
     * @see org.eclipse.sirius.properties.ListDescription#getOnClickOperation()
     * @see #getListDescription()
     * @generated
     */
    EReference getListDescription_OnClickOperation();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ListDescription#getActions
     * <em>Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Actions</em>'.
     * @see org.eclipse.sirius.properties.ListDescription#getActions()
     * @see #getListDescription()
     * @generated
     */
    EReference getListDescription_Actions();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ListDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ListDescription#getStyle()
     * @see #getListDescription()
     * @generated
     */
    EReference getListDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ListDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.ListDescription#getConditionalStyles()
     * @see #getListDescription()
     * @generated
     */
    EReference getListDescription_ConditionalStyles();

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
     * {@link org.eclipse.sirius.properties.CustomDescription
     * <em>Custom Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Custom Description</em>'.
     * @see org.eclipse.sirius.properties.CustomDescription
     * @generated
     */
    EClass getCustomDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.CustomDescription#getCustomExpressions
     * <em>Custom Expressions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Custom Expressions</em>'.
     * @see org.eclipse.sirius.properties.CustomDescription#getCustomExpressions()
     * @see #getCustomDescription()
     * @generated
     */
    EReference getCustomDescription_CustomExpressions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.CustomDescription#getCustomOperations
     * <em>Custom Operations</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Custom Operations</em>'.
     * @see org.eclipse.sirius.properties.CustomDescription#getCustomOperations()
     * @see #getCustomDescription()
     * @generated
     */
    EReference getCustomDescription_CustomOperations();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CustomDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.CustomDescription#getStyle()
     * @see #getCustomDescription()
     * @generated
     */
    EReference getCustomDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.CustomDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.CustomDescription#getConditionalStyles()
     * @see #getCustomDescription()
     * @generated
     */
    EReference getCustomDescription_ConditionalStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CustomExpression
     * <em>Custom Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Custom Expression</em>'.
     * @see org.eclipse.sirius.properties.CustomExpression
     * @generated
     */
    EClass getCustomExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CustomExpression#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.CustomExpression#getIdentifier()
     * @see #getCustomExpression()
     * @generated
     */
    EAttribute getCustomExpression_Identifier();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CustomExpression#getCustomExpression
     * <em>Custom Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Custom Expression</em>'.
     * @see org.eclipse.sirius.properties.CustomExpression#getCustomExpression()
     * @see #getCustomExpression()
     * @generated
     */
    EAttribute getCustomExpression_CustomExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CustomOperation
     * <em>Custom Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Custom Operation</em>'.
     * @see org.eclipse.sirius.properties.CustomOperation
     * @generated
     */
    EClass getCustomOperation();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.CustomOperation#getIdentifier
     * <em>Identifier</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Identifier</em>'.
     * @see org.eclipse.sirius.properties.CustomOperation#getIdentifier()
     * @see #getCustomOperation()
     * @generated
     */
    EAttribute getCustomOperation_Identifier();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CustomOperation#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.CustomOperation#getInitialOperation()
     * @see #getCustomOperation()
     * @generated
     */
    EReference getCustomOperation_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription
     * <em>Hyperlink Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Hyperlink Description</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription
     * @generated
     */
    EClass getHyperlinkDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription#getValueExpression()
     * @see #getHyperlinkDescription()
     * @generated
     */
    EAttribute getHyperlinkDescription_ValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription#getDisplayExpression
     * <em>Display Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Display Expression</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription#getDisplayExpression()
     * @see #getHyperlinkDescription()
     * @generated
     */
    EAttribute getHyperlinkDescription_DisplayExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription#getInitialOperation()
     * @see #getHyperlinkDescription()
     * @generated
     */
    EReference getHyperlinkDescription_InitialOperation();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription#getStyle()
     * @see #getHyperlinkDescription()
     * @generated
     */
    EReference getHyperlinkDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription#getConditionalStyles()
     * @see #getHyperlinkDescription()
     * @generated
     */
    EReference getHyperlinkDescription_ConditionalStyles();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.HyperlinkDescription#getActions
     * <em>Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Actions</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkDescription#getActions()
     * @see #getHyperlinkDescription()
     * @generated
     */
    EReference getHyperlinkDescription_Actions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.WidgetStyle <em>Widget Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Style</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle
     * @generated
     */
    EClass getWidgetStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontNameExpression
     * <em>Label Font Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Label Font Name Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelFontNameExpression()
     * @see #getWidgetStyle()
     * @generated
     */
    EAttribute getWidgetStyle_LabelFontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontSizeExpression
     * <em>Label Font Size Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Label Font Size Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelFontSizeExpression()
     * @see #getWidgetStyle()
     * @generated
     */
    EAttribute getWidgetStyle_LabelFontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelBackgroundColor
     * <em>Label Background Color</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the reference '
     *         <em>Label Background Color</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelBackgroundColor()
     * @see #getWidgetStyle()
     * @generated
     */
    EReference getWidgetStyle_LabelBackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelForegroundColor
     * <em>Label Foreground Color</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the reference '
     *         <em>Label Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelForegroundColor()
     * @see #getWidgetStyle()
     * @generated
     */
    EReference getWidgetStyle_LabelForegroundColor();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontFormat
     * <em>Label Font Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute list '
     *         <em>Label Font Format</em>'.
     * @see org.eclipse.sirius.properties.WidgetStyle#getLabelFontFormat()
     * @see #getWidgetStyle()
     * @generated
     */
    EAttribute getWidgetStyle_LabelFontFormat();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle
     * <em>Text Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Text Widget Style</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle
     * @generated
     */
    EClass getTextWidgetStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getFontNameExpression
     * <em>Font Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getFontNameExpression()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EAttribute getTextWidgetStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getFontSizeExpression
     * <em>Font Size Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getFontSizeExpression()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EAttribute getTextWidgetStyle_FontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getBackgroundColor()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EReference getTextWidgetStyle_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getForegroundColor()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EReference getTextWidgetStyle_ForegroundColor();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.properties.TextWidgetStyle#getFontFormat
     * <em>Font Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Font Format</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetStyle#getFontFormat()
     * @see #getTextWidgetStyle()
     * @generated
     */
    EAttribute getTextWidgetStyle_FontFormat();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle
     * <em>Label Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Label Widget Style</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle
     * @generated
     */
    EClass getLabelWidgetStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getFontNameExpression
     * <em>Font Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getFontNameExpression()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EAttribute getLabelWidgetStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getFontSizeExpression
     * <em>Font Size Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getFontSizeExpression()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EAttribute getLabelWidgetStyle_FontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getBackgroundColor()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EReference getLabelWidgetStyle_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getForegroundColor()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EReference getLabelWidgetStyle_ForegroundColor();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.properties.LabelWidgetStyle#getFontFormat
     * <em>Font Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Font Format</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetStyle#getFontFormat()
     * @see #getLabelWidgetStyle()
     * @generated
     */
    EAttribute getLabelWidgetStyle_FontFormat();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CheckboxWidgetStyle
     * <em>Checkbox Widget Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Checkbox Widget Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxWidgetStyle
     * @generated
     */
    EClass getCheckboxWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.RadioWidgetStyle
     * <em>Radio Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Radio Widget Style</em>'.
     * @see org.eclipse.sirius.properties.RadioWidgetStyle
     * @generated
     */
    EClass getRadioWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ButtonWidgetStyle
     * <em>Button Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Button Widget Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonWidgetStyle
     * @generated
     */
    EClass getButtonWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.SelectWidgetStyle
     * <em>Select Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Select Widget Style</em>'.
     * @see org.eclipse.sirius.properties.SelectWidgetStyle
     * @generated
     */
    EClass getSelectWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CustomWidgetStyle
     * <em>Custom Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Custom Widget Style</em>'.
     * @see org.eclipse.sirius.properties.CustomWidgetStyle
     * @generated
     */
    EClass getCustomWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.WidgetConditionalStyle
     * <em>Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.WidgetConditionalStyle
     * @generated
     */
    EClass getWidgetConditionalStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetConditionalStyle#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetConditionalStyle#getPreconditionExpression()
     * @see #getWidgetConditionalStyle()
     * @generated
     */
    EAttribute getWidgetConditionalStyle_PreconditionExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.TextWidgetConditionalStyle
     * <em>Text Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Text Widget Conditional Style</em>
     *         '.
     * @see org.eclipse.sirius.properties.TextWidgetConditionalStyle
     * @generated
     */
    EClass getTextWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.TextWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.TextWidgetConditionalStyle#getStyle()
     * @see #getTextWidgetConditionalStyle()
     * @generated
     */
    EReference getTextWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.LabelWidgetConditionalStyle
     * <em>Label Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Label Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetConditionalStyle
     * @generated
     */
    EClass getLabelWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.LabelWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.LabelWidgetConditionalStyle#getStyle()
     * @see #getLabelWidgetConditionalStyle()
     * @generated
     */
    EReference getLabelWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle
     * <em>Checkbox Widget Conditional Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Checkbox Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle
     * @generated
     */
    EClass getCheckboxWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.CheckboxWidgetConditionalStyle#getStyle()
     * @see #getCheckboxWidgetConditionalStyle()
     * @generated
     */
    EReference getCheckboxWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.RadioWidgetConditionalStyle
     * <em>Radio Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Radio Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.RadioWidgetConditionalStyle
     * @generated
     */
    EClass getRadioWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.RadioWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.RadioWidgetConditionalStyle#getStyle()
     * @see #getRadioWidgetConditionalStyle()
     * @generated
     */
    EReference getRadioWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ButtonWidgetConditionalStyle
     * <em>Button Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Button Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonWidgetConditionalStyle
     * @generated
     */
    EClass getButtonWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ButtonWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ButtonWidgetConditionalStyle#getStyle()
     * @see #getButtonWidgetConditionalStyle()
     * @generated
     */
    EReference getButtonWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.SelectWidgetConditionalStyle
     * <em>Select Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Select Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.SelectWidgetConditionalStyle
     * @generated
     */
    EClass getSelectWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.SelectWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.SelectWidgetConditionalStyle#getStyle()
     * @see #getSelectWidgetConditionalStyle()
     * @generated
     */
    EReference getSelectWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.CustomWidgetConditionalStyle
     * <em>Custom Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Custom Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.CustomWidgetConditionalStyle
     * @generated
     */
    EClass getCustomWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.CustomWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.CustomWidgetConditionalStyle#getStyle()
     * @see #getCustomWidgetConditionalStyle()
     * @generated
     */
    EReference getCustomWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ListWidgetConditionalStyle
     * <em>List Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>List Widget Conditional Style</em>
     *         '.
     * @see org.eclipse.sirius.properties.ListWidgetConditionalStyle
     * @generated
     */
    EClass getListWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ListWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ListWidgetConditionalStyle#getStyle()
     * @see #getListWidgetConditionalStyle()
     * @generated
     */
    EReference getListWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.WidgetAction <em>Widget Action</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Widget Action</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction
     * @generated
     */
    EClass getWidgetAction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.WidgetAction#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction#getLabelExpression()
     * @see #getWidgetAction()
     * @generated
     */
    EAttribute getWidgetAction_LabelExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.WidgetAction#getInitialOperation
     * <em>Initial Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Initial Operation</em>'.
     * @see org.eclipse.sirius.properties.WidgetAction#getInitialOperation()
     * @see #getWidgetAction()
     * @generated
     */
    EReference getWidgetAction_InitialOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle
     * <em>Hyperlink Widget Conditional Style</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Hyperlink Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle
     * @generated
     */
    EClass getHyperlinkWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetConditionalStyle#getStyle()
     * @see #getHyperlinkWidgetConditionalStyle()
     * @generated
     */
    EReference getHyperlinkWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.GroupConditionalStyle
     * <em>Group Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Group Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.GroupConditionalStyle
     * @generated
     */
    EClass getGroupConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.GroupConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.GroupConditionalStyle#getStyle()
     * @see #getGroupConditionalStyle()
     * @generated
     */
    EReference getGroupConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.EditSupport <em>Edit Support</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edit Support</em>'.
     * @see org.eclipse.sirius.properties.EditSupport
     * @generated
     */
    EClass getEditSupport();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ListWidgetStyle
     * <em>List Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>List Widget Style</em>'.
     * @see org.eclipse.sirius.properties.ListWidgetStyle
     * @generated
     */
    EClass getListWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle
     * <em>Hyperlink Widget Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Hyperlink Widget Style</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle
     * @generated
     */
    EClass getHyperlinkWidgetStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontNameExpression
     * <em>Font Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontNameExpression()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EAttribute getHyperlinkWidgetStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontSizeExpression
     * <em>Font Size Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontSizeExpression()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EAttribute getHyperlinkWidgetStyle_FontSizeExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getBackgroundColor()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EReference getHyperlinkWidgetStyle_BackgroundColor();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontFormat
     * <em>Font Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute list '<em>Font Format</em>'.
     * @see org.eclipse.sirius.properties.HyperlinkWidgetStyle#getFontFormat()
     * @see #getHyperlinkWidgetStyle()
     * @generated
     */
    EAttribute getHyperlinkWidgetStyle_FontFormat();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.GroupStyle <em>Group Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Group Style</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle
     * @generated
     */
    EClass getGroupStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.GroupStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getBackgroundColor()
     * @see #getGroupStyle()
     * @generated
     */
    EReference getGroupStyle_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.properties.GroupStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getForegroundColor()
     * @see #getGroupStyle()
     * @generated
     */
    EReference getGroupStyle_ForegroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupStyle#getFontNameExpression
     * <em>Font Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Name Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.GroupStyle#getFontNameExpression()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_FontNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupStyle#getFontSizeExpression
     * <em>Font Size Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size Expression</em>
     *         '.
     * @see org.eclipse.sirius.properties.GroupStyle#getFontSizeExpression()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_FontSizeExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupStyle#getBarStyle
     * <em>Bar Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Bar Style</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getBarStyle()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_BarStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupStyle#getToggleStyle
     * <em>Toggle Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Toggle Style</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#getToggleStyle()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_ToggleStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.GroupStyle#isExpandedByDefault
     * <em>Expanded By Default</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Expanded By Default</em>'.
     * @see org.eclipse.sirius.properties.GroupStyle#isExpandedByDefault()
     * @see #getGroupStyle()
     * @generated
     */
    EAttribute getGroupStyle_ExpandedByDefault();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * <em>FILL LAYOUT ORIENTATION</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for enum '<em>FILL LAYOUT ORIENTATION</em>'.
     * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * @generated
     */
    EEnum getFILL_LAYOUT_ORIENTATION();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.properties.ToggleStyle <em>Toggle Style</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Toggle Style</em>'.
     * @see org.eclipse.sirius.properties.ToggleStyle
     * @generated
     */
    EEnum getToggleStyle();

    /**
     * Returns the meta object for enum '
     * {@link org.eclipse.sirius.properties.TitleBarStyle
     * <em>Title Bar Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Title Bar Style</em>'.
     * @see org.eclipse.sirius.properties.TitleBarStyle
     * @generated
     */
    EEnum getTitleBarStyle();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
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
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute PAGE_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.eINSTANCE.getPageDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Groups</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PAGE_DESCRIPTION__GROUPS = PropertiesPackage.eINSTANCE.getPageDescription_Groups();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference PAGE_DESCRIPTION__VALIDATION_SET = PropertiesPackage.eINSTANCE.getPageDescription_ValidationSet();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
         * <em>Page Validation Set Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.PageValidationSetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPageValidationSetDescription()
         * @generated
         */
        EClass PAGE_VALIDATION_SET_DESCRIPTION = PropertiesPackage.eINSTANCE.getPageValidationSetDescription();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Validation Rules</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PAGE_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = PropertiesPackage.eINSTANCE.getPageValidationSetDescription_SemanticValidationRules();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
         * <em>Property Validation Rule</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.PropertyValidationRuleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getPropertyValidationRule()
         * @generated
         */
        EClass PROPERTY_VALIDATION_RULE = PropertiesPackage.eINSTANCE.getPropertyValidationRule();

        /**
         * The meta object literal for the '<em><b>Targets</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference PROPERTY_VALIDATION_RULE__TARGETS = PropertiesPackage.eINSTANCE.getPropertyValidationRule_Targets();

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
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_DESCRIPTION__PRECONDITION_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Controls</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_DESCRIPTION__CONTROLS = PropertiesPackage.eINSTANCE.getGroupDescription_Controls();

        /**
         * The meta object literal for the '<em><b>Validation Set</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_DESCRIPTION__VALIDATION_SET = PropertiesPackage.eINSTANCE.getGroupDescription_ValidationSet();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getGroupDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getGroupDescription_ConditionalStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
         * <em>Group Validation Set Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupValidationSetDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupValidationSetDescription()
         * @generated
         */
        EClass GROUP_VALIDATION_SET_DESCRIPTION = PropertiesPackage.eINSTANCE.getGroupValidationSetDescription();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Validation Rules</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_VALIDATION_SET_DESCRIPTION__SEMANTIC_VALIDATION_RULES = PropertiesPackage.eINSTANCE.getGroupValidationSetDescription_SemanticValidationRules();

        /**
         * The meta object literal for the '
         * <em><b>Property Validation Rules</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_VALIDATION_SET_DESCRIPTION__PROPERTY_VALIDATION_RULES = PropertiesPackage.eINSTANCE.getGroupValidationSetDescription_PropertyValidationRules();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ControlDescriptionImpl
         * <em>Control Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ControlDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getControlDescription()
         * @generated
         */
        EClass CONTROL_DESCRIPTION = PropertiesPackage.eINSTANCE.getControlDescription();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CONTROL_DESCRIPTION__IDENTIFIER = PropertiesPackage.eINSTANCE.getControlDescription_Identifier();

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
         * The meta object literal for the '<em><b>Controls</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DESCRIPTION__CONTROLS = PropertiesPackage.eINSTANCE.getContainerDescription_Controls();

        /**
         * The meta object literal for the '<em><b>Layout</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTAINER_DESCRIPTION__LAYOUT = PropertiesPackage.eINSTANCE.getContainerDescription_Layout();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.LayoutDescriptionImpl
         * <em>Layout Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LayoutDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLayoutDescription()
         * @generated
         */
        EClass LAYOUT_DESCRIPTION = PropertiesPackage.eINSTANCE.getLayoutDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl
         * <em>Fill Layout Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.FillLayoutDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFillLayoutDescription()
         * @generated
         */
        EClass FILL_LAYOUT_DESCRIPTION = PropertiesPackage.eINSTANCE.getFillLayoutDescription();

        /**
         * The meta object literal for the '<em><b>Orientation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FILL_LAYOUT_DESCRIPTION__ORIENTATION = PropertiesPackage.eINSTANCE.getFillLayoutDescription_Orientation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl
         * <em>Grid Layout Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GridLayoutDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGridLayoutDescription()
         * @generated
         */
        EClass GRID_LAYOUT_DESCRIPTION = PropertiesPackage.eINSTANCE.getGridLayoutDescription();

        /**
         * The meta object literal for the '<em><b>Number Of Columns</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GRID_LAYOUT_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.eINSTANCE.getGridLayoutDescription_NumberOfColumns();

        /**
         * The meta object literal for the '
         * <em><b>Make Columns With Equal Width</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GRID_LAYOUT_DESCRIPTION__MAKE_COLUMNS_WITH_EQUAL_WIDTH = PropertiesPackage.eINSTANCE.getGridLayoutDescription_MakeColumnsWithEqualWidth();

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
         * <em><b>Is Enabled Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetDescription_IsEnabledExpression();

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
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getTextDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getTextDescription_ConditionalStyles();

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
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BUTTON_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getButtonDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference BUTTON_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getButtonDescription_ConditionalStyles();

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
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getLabelDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getLabelDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getLabelDescription_Actions();

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
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CHECKBOX_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getCheckboxDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CHECKBOX_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getCheckboxDescription_ConditionalStyles();

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
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SELECT_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getSelectDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference SELECT_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getSelectDescription_ConditionalStyles();

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
         * The meta object literal for the '<em><b>Iterable Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DYNAMIC_MAPPING_FOR__ITERABLE_EXPRESSION = PropertiesPackage.eINSTANCE.getDynamicMappingFor_IterableExpression();

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
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RADIO_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getRadioDescription_Style();

        /**
         * The meta object literal for the '<em><b>Number Of Columns</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute RADIO_DESCRIPTION__NUMBER_OF_COLUMNS = PropertiesPackage.eINSTANCE.getRadioDescription_NumberOfColumns();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference RADIO_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getRadioDescription_ConditionalStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ListDescriptionImpl
         * <em>List Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListDescription()
         * @generated
         */
        EClass LIST_DESCRIPTION = PropertiesPackage.eINSTANCE.getListDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LIST_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getListDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LIST_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getListDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>On Click Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference LIST_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.eINSTANCE.getListDescription_OnClickOperation();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LIST_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getListDescription_Actions();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LIST_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getListDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference LIST_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getListDescription_ConditionalStyles();

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
         * {@link org.eclipse.sirius.properties.impl.CustomDescriptionImpl
         * <em>Custom Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomDescription()
         * @generated
         */
        EClass CUSTOM_DESCRIPTION = PropertiesPackage.eINSTANCE.getCustomDescription();

        /**
         * The meta object literal for the '<em><b>Custom Expressions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_DESCRIPTION__CUSTOM_EXPRESSIONS = PropertiesPackage.eINSTANCE.getCustomDescription_CustomExpressions();

        /**
         * The meta object literal for the '<em><b>Custom Operations</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_DESCRIPTION__CUSTOM_OPERATIONS = PropertiesPackage.eINSTANCE.getCustomDescription_CustomOperations();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getCustomDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getCustomDescription_ConditionalStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CustomExpressionImpl
         * <em>Custom Expression</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomExpressionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomExpression()
         * @generated
         */
        EClass CUSTOM_EXPRESSION = PropertiesPackage.eINSTANCE.getCustomExpression();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_EXPRESSION__IDENTIFIER = PropertiesPackage.eINSTANCE.getCustomExpression_Identifier();

        /**
         * The meta object literal for the '<em><b>Custom Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_EXPRESSION__CUSTOM_EXPRESSION = PropertiesPackage.eINSTANCE.getCustomExpression_CustomExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CustomOperationImpl
         * <em>Custom Operation</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomOperationImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomOperation()
         * @generated
         */
        EClass CUSTOM_OPERATION = PropertiesPackage.eINSTANCE.getCustomOperation();

        /**
         * The meta object literal for the '<em><b>Identifier</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CUSTOM_OPERATION__IDENTIFIER = PropertiesPackage.eINSTANCE.getCustomOperation_Identifier();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_OPERATION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getCustomOperation_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
         * <em>Hyperlink Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkDescriptionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkDescription()
         * @generated
         */
        EClass HYPERLINK_DESCRIPTION = PropertiesPackage.eINSTANCE.getHyperlinkDescription();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_DESCRIPTION__VALUE_EXPRESSION = PropertiesPackage.eINSTANCE.getHyperlinkDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Display Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_DESCRIPTION__DISPLAY_EXPRESSION = PropertiesPackage.eINSTANCE.getHyperlinkDescription_DisplayExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_DESCRIPTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getHyperlinkDescription_InitialOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_DESCRIPTION__STYLE = PropertiesPackage.eINSTANCE.getHyperlinkDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.eINSTANCE.getHyperlinkDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_DESCRIPTION__ACTIONS = PropertiesPackage.eINSTANCE.getHyperlinkDescription_Actions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.WidgetStyleImpl
         * <em>Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetStyle()
         * @generated
         */
        EClass WIDGET_STYLE = PropertiesPackage.eINSTANCE.getWidgetStyle();

        /**
         * The meta object literal for the '
         * <em><b>Label Font Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelFontNameExpression();

        /**
         * The meta object literal for the '
         * <em><b>Label Font Size Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelFontSizeExpression();

        /**
         * The meta object literal for the '
         * <em><b>Label Background Color</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelBackgroundColor();

        /**
         * The meta object literal for the '
         * <em><b>Label Foreground Color</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelForegroundColor();

        /**
         * The meta object literal for the '<em><b>Label Font Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.eINSTANCE.getWidgetStyle_LabelFontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.TextWidgetStyleImpl
         * <em>Text Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetStyle()
         * @generated
         */
        EClass TEXT_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getTextWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getTextWidgetStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getTextWidgetStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Font Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TEXT_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.eINSTANCE.getTextWidgetStyle_FontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl
         * <em>Label Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LabelWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetStyle()
         * @generated
         */
        EClass LABEL_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getLabelWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_WIDGET_STYLE__FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Font Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LABEL_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.eINSTANCE.getLabelWidgetStyle_FontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
         * <em>Checkbox Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CheckboxWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetStyle()
         * @generated
         */
        EClass CHECKBOX_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getCheckboxWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl
         * <em>Radio Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.RadioWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetStyle()
         * @generated
         */
        EClass RADIO_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getRadioWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl
         * <em>Button Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ButtonWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetStyle()
         * @generated
         */
        EClass BUTTON_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getButtonWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl
         * <em>Select Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.SelectWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetStyle()
         * @generated
         */
        EClass SELECT_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getSelectWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl
         * <em>Custom Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetStyle()
         * @generated
         */
        EClass CUSTOM_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getCustomWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
         * <em>Widget Conditional Style</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetConditionalStyle()
         * @generated
         */
        EClass WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getWidgetConditionalStyle();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetConditionalStyle_PreconditionExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
         * <em>Text Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.TextWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTextWidgetConditionalStyle()
         * @generated
         */
        EClass TEXT_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getTextWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TEXT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getTextWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
         * <em>Label Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.LabelWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getLabelWidgetConditionalStyle()
         * @generated
         */
        EClass LABEL_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getLabelWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LABEL_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getLabelWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
         * <em>Checkbox Widget Conditional Style</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CheckboxWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCheckboxWidgetConditionalStyle()
         * @generated
         */
        EClass CHECKBOX_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getCheckboxWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CHECKBOX_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getCheckboxWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
         * <em>Radio Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.RadioWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getRadioWidgetConditionalStyle()
         * @generated
         */
        EClass RADIO_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getRadioWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference RADIO_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getRadioWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
         * <em>Button Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ButtonWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getButtonWidgetConditionalStyle()
         * @generated
         */
        EClass BUTTON_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getButtonWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference BUTTON_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getButtonWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
         * <em>Select Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.SelectWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getSelectWidgetConditionalStyle()
         * @generated
         */
        EClass SELECT_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getSelectWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference SELECT_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getSelectWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
         * <em>Custom Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.CustomWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getCustomWidgetConditionalStyle()
         * @generated
         */
        EClass CUSTOM_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getCustomWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CUSTOM_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getCustomWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
         * <em>List Widget Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetConditionalStyle()
         * @generated
         */
        EClass LIST_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getListWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference LIST_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getListWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.WidgetActionImpl
         * <em>Widget Action</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.WidgetActionImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getWidgetAction()
         * @generated
         */
        EClass WIDGET_ACTION = PropertiesPackage.eINSTANCE.getWidgetAction();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute WIDGET_ACTION__LABEL_EXPRESSION = PropertiesPackage.eINSTANCE.getWidgetAction_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Initial Operation</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference WIDGET_ACTION__INITIAL_OPERATION = PropertiesPackage.eINSTANCE.getWidgetAction_InitialOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
         * <em>Hyperlink Widget Conditional Style</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetConditionalStyle()
         * @generated
         */
        EClass HYPERLINK_WIDGET_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getHyperlinkWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getHyperlinkWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
         * <em>Group Conditional Style</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupConditionalStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupConditionalStyle()
         * @generated
         */
        EClass GROUP_CONDITIONAL_STYLE = PropertiesPackage.eINSTANCE.getGroupConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_CONDITIONAL_STYLE__STYLE = PropertiesPackage.eINSTANCE.getGroupConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.EditSupportImpl
         * <em>Edit Support</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.EditSupportImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getEditSupport()
         * @generated
         */
        EClass EDIT_SUPPORT = PropertiesPackage.eINSTANCE.getEditSupport();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.ListWidgetStyleImpl
         * <em>List Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.ListWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getListWidgetStyle()
         * @generated
         */
        EClass LIST_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getListWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
         * <em>Hyperlink Widget Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.HyperlinkWidgetStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getHyperlinkWidgetStyle()
         * @generated
         */
        EClass HYPERLINK_WIDGET_STYLE = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_WIDGET_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_WIDGET_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference HYPERLINK_WIDGET_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Font Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute HYPERLINK_WIDGET_STYLE__FONT_FORMAT = PropertiesPackage.eINSTANCE.getHyperlinkWidgetStyle_FontFormat();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.impl.GroupStyleImpl
         * <em>Group Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.impl.GroupStyleImpl
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getGroupStyle()
         * @generated
         */
        EClass GROUP_STYLE = PropertiesPackage.eINSTANCE.getGroupStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_STYLE__BACKGROUND_COLOR = PropertiesPackage.eINSTANCE.getGroupStyle_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference GROUP_STYLE__FOREGROUND_COLOR = PropertiesPackage.eINSTANCE.getGroupStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Font Name Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__FONT_NAME_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupStyle_FontNameExpression();

        /**
         * The meta object literal for the '<em><b>Font Size Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__FONT_SIZE_EXPRESSION = PropertiesPackage.eINSTANCE.getGroupStyle_FontSizeExpression();

        /**
         * The meta object literal for the '<em><b>Bar Style</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__BAR_STYLE = PropertiesPackage.eINSTANCE.getGroupStyle_BarStyle();

        /**
         * The meta object literal for the '<em><b>Toggle Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__TOGGLE_STYLE = PropertiesPackage.eINSTANCE.getGroupStyle_ToggleStyle();

        /**
         * The meta object literal for the '<em><b>Expanded By Default</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GROUP_STYLE__EXPANDED_BY_DEFAULT = PropertiesPackage.eINSTANCE.getGroupStyle_ExpandedByDefault();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
         * <em>FILL LAYOUT ORIENTATION</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getFILL_LAYOUT_ORIENTATION()
         * @generated
         */
        EEnum FILL_LAYOUT_ORIENTATION = PropertiesPackage.eINSTANCE.getFILL_LAYOUT_ORIENTATION();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.ToggleStyle
         * <em>Toggle Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ToggleStyle
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getToggleStyle()
         * @generated
         */
        EEnum TOGGLE_STYLE = PropertiesPackage.eINSTANCE.getToggleStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.TitleBarStyle
         * <em>Title Bar Style</em>}' enum. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.TitleBarStyle
         * @see org.eclipse.sirius.properties.impl.PropertiesPackageImpl#getTitleBarStyle()
         * @generated
         */
        EEnum TITLE_BAR_STYLE = PropertiesPackage.eINSTANCE.getTitleBarStyle();

    }

} // PropertiesPackage
