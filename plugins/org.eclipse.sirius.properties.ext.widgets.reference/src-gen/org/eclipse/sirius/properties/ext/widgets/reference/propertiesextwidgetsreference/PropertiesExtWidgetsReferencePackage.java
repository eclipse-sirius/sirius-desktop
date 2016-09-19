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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
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
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    PropertiesExtWidgetsReferencePackage eINSTANCE = org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
     * <em>Ext Reference Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceDescription()
     * @generated
     */
    int EXT_REFERENCE_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reference Owner Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__STYLE = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>Ext Reference Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
     * <em>Ext Reference Widget Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetStyle()
     * @generated
     */
    int EXT_REFERENCE_WIDGET_STYLE = 1;

    /**
     * The feature id for the '<em><b>Label Font Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Font Size Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_SIZE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Background Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_BACKGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Foreground Color</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FOREGROUND_COLOR = PropertiesPackage.WIDGET_STYLE__LABEL_FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Label Font Format</b></em>' attribute
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE__LABEL_FONT_FORMAT = PropertiesPackage.WIDGET_STYLE__LABEL_FONT_FORMAT;

    /**
     * The number of structural features of the '
     * <em>Ext Reference Widget Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_STYLE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
     * <em>Ext Reference Widget Conditional Style</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetConditionalStyle()
     * @generated
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE = 2;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION = PropertiesPackage.WIDGET_CONDITIONAL_STYLE__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Ext Reference Widget Conditional Style</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT = PropertiesPackage.WIDGET_CONDITIONAL_STYLE_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription
     * <em>Ext Reference Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Description</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription
     * @generated
     */
    EClass getExtReferenceDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceNameExpression
     * <em>Reference Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Reference Name Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceNameExpression()
     * @see #getExtReferenceDescription()
     * @generated
     */
    EAttribute getExtReferenceDescription_ReferenceNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceOwnerExpression
     * <em>Reference Owner Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Reference Owner Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceOwnerExpression()
     * @see #getExtReferenceDescription()
     * @generated
     */
    EAttribute getExtReferenceDescription_ReferenceOwnerExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getStyle()
     * @see #getExtReferenceDescription()
     * @generated
     */
    EReference getExtReferenceDescription_Style();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getConditionalStyles
     * <em>Conditional Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getConditionalStyles()
     * @see #getExtReferenceDescription()
     * @generated
     */
    EReference getExtReferenceDescription_ConditionalStyles();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle
     * <em>Ext Reference Widget Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Widget Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle
     * @generated
     */
    EClass getExtReferenceWidgetStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle
     * <em>Ext Reference Widget Conditional Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '
     *         <em>Ext Reference Widget Conditional Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle
     * @generated
     */
    EClass getExtReferenceWidgetConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle#getStyle()
     * @see #getExtReferenceWidgetConditionalStyle()
     * @generated
     */
    EReference getExtReferenceWidgetConditionalStyle_Style();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesExtWidgetsReferenceFactory getPropertiesExtWidgetsReferenceFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
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
         * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
         * <em>Ext Reference Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceDescription()
         * @generated
         */
        EClass EXT_REFERENCE_DESCRIPTION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription();

        /**
         * The meta object literal for the '
         * <em><b>Reference Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription_ReferenceNameExpression();

        /**
         * The meta object literal for the '
         * <em><b>Reference Owner Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription_ReferenceOwnerExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXT_REFERENCE_DESCRIPTION__STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription_Style();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription_ConditionalStyles();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
         * <em>Ext Reference Widget Style</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetStyleImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetStyle()
         * @generated
         */
        EClass EXT_REFERENCE_WIDGET_STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceWidgetStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
         * <em>Ext Reference Widget Conditional Style</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceWidgetConditionalStyleImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceWidgetConditionalStyle()
         * @generated
         */
        EClass EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceWidgetConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__STYLE = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceWidgetConditionalStyle_Style();

    }

} // PropertiesExtWidgetsReferencePackage
