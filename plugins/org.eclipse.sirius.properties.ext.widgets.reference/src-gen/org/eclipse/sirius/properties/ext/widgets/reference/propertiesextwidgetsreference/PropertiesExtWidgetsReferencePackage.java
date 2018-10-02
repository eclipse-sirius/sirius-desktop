/**
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.properties.PropertiesPackage;

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
 * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferenceFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesExtWidgetsReferencePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "propertiesextwidgetsreference"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/properties/1.0.0/ext/widgets/reference"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "properties-ext-widgets-reference"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PropertiesExtWidgetsReferencePackage eINSTANCE = org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl.init();

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl
     * <em>Abstract Ext Reference Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getAbstractExtReferenceDescription()
     * @generated
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__NAME = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__LABEL = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__DOCUMENTATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reference Owner Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '<em>Abstract Ext Reference Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.ABSTRACT_WIDGET_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
     * <em>Ext Reference Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceDescription()
     * @generated
     */
    int EXT_REFERENCE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__NAME = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__LABEL = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__DOCUMENTATION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__LABEL_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__HELP_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Owner Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__STYLE = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__EXTENDS = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS;

    /**
     * The number of structural features of the '<em>Ext Reference Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
     * <em>Ext Reference Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetStyle()
     * @generated
     */
    int EXT_REFERENCE_WIDGET_STYLE = 2;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '<em>Ext Reference Widget Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
     * <em>Ext Reference Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetConditionalStyle()
     * @generated
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE = 3;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Ext Reference Widget Conditional Style</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceOverrideDescriptionImpl
     * <em>Ext Reference Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceOverrideDescriptionImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceOverrideDescription()
     * @generated
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__NAME = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__LABEL = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__DOCUMENTATION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__LABEL_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__HELP_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Name Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Owner Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION;

    /**
     * The feature id for the '<em><b>On Click Operation</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__STYLE = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES;

    /**
     * The feature id for the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__EXTENDS = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS;

    /**
     * The feature id for the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Filter Conditional Styles From Overridden Ext Reference Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT
            + 1;

    /**
     * The number of structural features of the '<em>Ext Reference Override Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_OVERRIDE_DESCRIPTION_FEATURE_COUNT = PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription
     * <em>Abstract Ext Reference Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Ext Reference Description</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription
     * @generated
     */
    EClass getAbstractExtReferenceDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getReferenceNameExpression
     * <em>Reference Name Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Reference Name Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getReferenceNameExpression()
     * @see #getAbstractExtReferenceDescription()
     * @generated
     */
    EAttribute getAbstractExtReferenceDescription_ReferenceNameExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getReferenceOwnerExpression
     * <em>Reference Owner Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Reference Owner Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getReferenceOwnerExpression()
     * @see #getAbstractExtReferenceDescription()
     * @generated
     */
    EAttribute getAbstractExtReferenceDescription_ReferenceOwnerExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getOnClickOperation
     * <em>On Click Operation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>On Click Operation</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getOnClickOperation()
     * @see #getAbstractExtReferenceDescription()
     * @generated
     */
    EReference getAbstractExtReferenceDescription_OnClickOperation();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getStyle()
     * @see #getAbstractExtReferenceDescription()
     * @generated
     */
    EReference getAbstractExtReferenceDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getConditionalStyles()
     * @see #getAbstractExtReferenceDescription()
     * @generated
     */
    EReference getAbstractExtReferenceDescription_ConditionalStyles();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getExtends
     * <em>Extends</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Extends</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription#getExtends()
     * @see #getAbstractExtReferenceDescription()
     * @generated
     */
    EReference getAbstractExtReferenceDescription_Extends();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription
     * <em>Ext Reference Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Description</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription
     * @generated
     */
    EClass getExtReferenceDescription();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle
     * <em>Ext Reference Widget Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Widget Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle
     * @generated
     */
    EClass getExtReferenceWidgetStyle();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle
     * <em>Ext Reference Widget Conditional Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle
     * @generated
     */
    EClass getExtReferenceWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle#getStyle()
     * @see #getExtReferenceWidgetConditionalStyle()
     * @generated
     */
    EReference getExtReferenceWidgetConditionalStyle_Style();

    /**
     * Returns the meta object for class
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription
     * <em>Ext Reference Override Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Override Description</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription
     * @generated
     */
    EClass getExtReferenceOverrideDescription();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription#getOverrides
     * <em>Overrides</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Overrides</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription#getOverrides()
     * @see #getExtReferenceOverrideDescription()
     * @generated
     */
    EReference getExtReferenceOverrideDescription_Overrides();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription#getFilterConditionalStylesFromOverriddenExtReferenceExpression
     * <em>Filter Conditional Styles From Overridden Ext Reference Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Filter Conditional Styles From Overridden Ext Reference
     *         Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription#getFilterConditionalStylesFromOverriddenExtReferenceExpression()
     * @see #getExtReferenceOverrideDescription()
     * @generated
     */
    EAttribute getExtReferenceOverrideDescription_FilterConditionalStylesFromOverriddenExtReferenceExpression();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesExtWidgetsReferenceFactory getPropertiesExtWidgetsReferenceFactory();

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
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl
         * <em>Abstract Ext Reference Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.AbstractExtReferenceDescriptionImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getAbstractExtReferenceDescription()
         * @generated
         */
        EClass ABSTRACT_EXT_REFERENCE_DESCRIPTION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription();

        /**
         * The meta object literal for the '<em><b>Reference Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_ReferenceNameExpression();

        /**
         * The meta object literal for the '<em><b>Reference Owner Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_ReferenceOwnerExpression();

        /**
         * The meta object literal for the '<em><b>On Click Operation</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_EXT_REFERENCE_DESCRIPTION__ON_CLICK_OPERATION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_OnClickOperation();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_ConditionalStyles();

        /**
         * The meta object literal for the '<em><b>Extends</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS = PropertiesExtWidgetsReferencePackage.eINSTANCE.getAbstractExtReferenceDescription_Extends();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
         * <em>Ext Reference Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceDescription()
         * @generated
         */
        EClass EXT_REFERENCE_DESCRIPTION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
         * <em>Ext Reference Widget Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetStyle()
         * @generated
         */
        EClass EXT_REFERENCE_WIDGET_STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceWidgetStyle();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
         * <em>Ext Reference Widget Conditional Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetConditionalStyle()
         * @generated
         */
        EClass EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceWidgetConditionalStyle_Style();

        /**
         * The meta object literal for the
         * '{@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceOverrideDescriptionImpl
         * <em>Ext Reference Override Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceOverrideDescriptionImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceOverrideDescription()
         * @generated
         */
        EClass EXT_REFERENCE_OVERRIDE_DESCRIPTION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceOverrideDescription();

        /**
         * The meta object literal for the '<em><b>Overrides</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceOverrideDescription_Overrides();

        /**
         * The meta object literal for the '<em><b>Filter Conditional Styles From Overridden Ext Reference
         * Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE
                .getExtReferenceOverrideDescription_FilterConditionalStylesFromOverriddenExtReferenceExpression();

    }

} // PropertiesExtWidgetsReferencePackage
