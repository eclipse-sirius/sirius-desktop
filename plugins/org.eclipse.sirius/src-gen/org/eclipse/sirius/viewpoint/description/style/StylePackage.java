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
package org.eclipse.sirius.viewpoint.description.style;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see org.eclipse.sirius.viewpoint.description.style.StyleFactory
 * @model kind="package"
 * @generated
 */
public interface StylePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "style"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/style/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "style"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    StylePackage eINSTANCE = org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.StyleDescription
     * <em>Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.viewpoint.description.style.StyleDescription
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getStyleDescription()
     * @generated
     */
    int STYLE_DESCRIPTION = 0;

    /**
     * The number of structural features of the '<em>Description</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_DESCRIPTION_FEATURE_COUNT = 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl
     * <em>Basic Label Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBasicLabelStyleDescription()
     * @generated
     */
    int BASIC_LABEL_STYLE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE = 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = 1;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON = 2;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = 3;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR = 4;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH = 5;

    /**
     * The number of structural features of the '
     * <em>Basic Label Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelStyleDescriptionImpl
     * <em>Label Style Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.LabelStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLabelStyleDescription()
     * @generated
     */
    int LABEL_STYLE_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_SIZE = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__SHOW_ICON = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_COLOR = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__ICON_PATH = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     *
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Label Style Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION_FEATURE_COUNT = StylePackage.BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStylesImpl
     * <em>Label Border Styles</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStylesImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLabelBorderStyles()
     * @generated
     */
    int LABEL_BORDER_STYLES = 3;

    /**
     * The feature id for the '<em><b>Label Border Style Descriptions</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS = 0;

    /**
     * The number of structural features of the '<em>Label Border Styles</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLES_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl
     * <em>Label Border Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLabelBorderStyleDescription()
     * @generated
     */
    int LABEL_BORDER_STYLE_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLE_DESCRIPTION__ID = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLE_DESCRIPTION__NAME = 1;

    /**
     * The feature id for the '<em><b>Corner Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT = 2;

    /**
     * The feature id for the '<em><b>Corner Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH = 3;

    /**
     * The number of structural features of the '
     * <em>Label Border Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_BORDER_STYLE_DESCRIPTION_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.TooltipStyleDescriptionImpl
     * <em>Tooltip Style Description</em>}' class. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @see org.eclipse.sirius.viewpoint.description.style.impl.TooltipStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getTooltipStyleDescription()
     * @generated
     */
    int TOOLTIP_STYLE_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     *
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = 0;

    /**
     * The number of structural features of the '
     * <em>Tooltip Style Description</em>' class. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TOOLTIP_STYLE_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.StyleDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.StyleDescription
     * @generated
     */
    EClass getStyleDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription
     * <em>Basic Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Basic Label Style Description</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription
     * @generated
     */
    EClass getBasicLabelStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelSize()
     * @see #getBasicLabelStyleDescription()
     * @generated
     */
    EAttribute getBasicLabelStyleDescription_LabelSize();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Label Format</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelFormat()
     * @see #getBasicLabelStyleDescription()
     * @generated
     */
    EAttribute getBasicLabelStyleDescription_LabelFormat();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#isShowIcon
     * <em>Show Icon</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Show Icon</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#isShowIcon()
     * @see #getBasicLabelStyleDescription()
     * @generated
     */
    EAttribute getBasicLabelStyleDescription_ShowIcon();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelExpression()
     * @see #getBasicLabelStyleDescription()
     * @generated
     */
    EAttribute getBasicLabelStyleDescription_LabelExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelColor
     * <em>Label Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Label Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelColor()
     * @see #getBasicLabelStyleDescription()
     * @generated
     */
    EReference getBasicLabelStyleDescription_LabelColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getIconPath
     * <em>Icon Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Icon Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getIconPath()
     * @see #getBasicLabelStyleDescription()
     * @generated
     */
    EAttribute getBasicLabelStyleDescription_IconPath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription
     * <em>Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Label Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription
     * @generated
     */
    EClass getLabelStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription#getLabelAlignment
     * <em>Label Alignment</em>}'. <!-- begin-user-doc -->
     *
     * @since 0.9.0<!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription#getLabelAlignment()
     * @see #getLabelStyleDescription()
     * @generated
     */
    EAttribute getLabelStyleDescription_LabelAlignment();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles
     * <em>Label Border Styles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Label Border Styles</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles
     * @generated
     */
    EClass getLabelBorderStyles();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles#getLabelBorderStyleDescriptions
     * <em>Label Border Style Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Label Border Style Descriptions</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles#getLabelBorderStyleDescriptions()
     * @see #getLabelBorderStyles()
     * @generated
     */
    EReference getLabelBorderStyles_LabelBorderStyleDescriptions();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription
     * <em>Label Border Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Label Border Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription
     * @generated
     */
    EClass getLabelBorderStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getId()
     * @see #getLabelBorderStyleDescription()
     * @generated
     */
    EAttribute getLabelBorderStyleDescription_Id();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getName()
     * @see #getLabelBorderStyleDescription()
     * @generated
     */
    EAttribute getLabelBorderStyleDescription_Name();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerHeight
     * <em>Corner Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Corner Height</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerHeight()
     * @see #getLabelBorderStyleDescription()
     * @generated
     */
    EAttribute getLabelBorderStyleDescription_CornerHeight();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerWidth
     * <em>Corner Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Corner Width</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerWidth()
     * @see #getLabelBorderStyleDescription()
     * @generated
     */
    EAttribute getLabelBorderStyleDescription_CornerWidth();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription
     * <em>Tooltip Style Description</em>}'. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @return the meta object for class '<em>Tooltip Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription
     * @generated
     */
    EClass getTooltipStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription#getTooltipExpression
     * <em>Tooltip Expression</em>}'. <!-- begin-user-doc -->
     *
     * @since 0.9.0 <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Tooltip Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription#getTooltipExpression()
     * @see #getTooltipStyleDescription()
     * @generated
     */
    EAttribute getTooltipStyleDescription_TooltipExpression();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    StyleFactory getStyleFactory();

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
         * {@link org.eclipse.sirius.viewpoint.description.style.StyleDescription
         * <em>Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.StyleDescription
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getStyleDescription()
         * @generated
         */
        EClass STYLE_DESCRIPTION = StylePackage.eINSTANCE.getStyleDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl
         * <em>Basic Label Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.BasicLabelStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBasicLabelStyleDescription()
         * @generated
         */
        EClass BASIC_LABEL_STYLE_DESCRIPTION = StylePackage.eINSTANCE.getBasicLabelStyleDescription();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE = StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Show Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON = StylePackage.eINSTANCE.getBasicLabelStyleDescription_ShowIcon();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Label Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR = StylePackage.eINSTANCE.getBasicLabelStyleDescription_LabelColor();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH = StylePackage.eINSTANCE.getBasicLabelStyleDescription_IconPath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelStyleDescriptionImpl
         * <em>Label Style Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.LabelStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLabelStyleDescription()
         * @generated
         */
        EClass LABEL_STYLE_DESCRIPTION = StylePackage.eINSTANCE.getLabelStyleDescription();

        /**
         * The meta object literal for the '<em><b>Label Alignment</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT = StylePackage.eINSTANCE.getLabelStyleDescription_LabelAlignment();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStylesImpl
         * <em>Label Border Styles</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStylesImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLabelBorderStyles()
         * @generated
         */
        EClass LABEL_BORDER_STYLES = StylePackage.eINSTANCE.getLabelBorderStyles();

        /**
         * The meta object literal for the '
         * <em><b>Label Border Style Descriptions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS = StylePackage.eINSTANCE.getLabelBorderStyles_LabelBorderStyleDescriptions();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl
         * <em>Label Border Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.LabelBorderStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLabelBorderStyleDescription()
         * @generated
         */
        EClass LABEL_BORDER_STYLE_DESCRIPTION = StylePackage.eINSTANCE.getLabelBorderStyleDescription();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__ID = StylePackage.eINSTANCE.getLabelBorderStyleDescription_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__NAME = StylePackage.eINSTANCE.getLabelBorderStyleDescription_Name();

        /**
         * The meta object literal for the '<em><b>Corner Height</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT = StylePackage.eINSTANCE.getLabelBorderStyleDescription_CornerHeight();

        /**
         * The meta object literal for the '<em><b>Corner Width</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH = StylePackage.eINSTANCE.getLabelBorderStyleDescription_CornerWidth();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.TooltipStyleDescriptionImpl
         * <em>Tooltip Style Description</em>}' class. <!-- begin-user-doc -->
         *
         * @since 0.9.0 <!-- end-user-doc -->
         * @see org.eclipse.sirius.viewpoint.description.style.impl.TooltipStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getTooltipStyleDescription()
         * @generated
         */
        EClass TOOLTIP_STYLE_DESCRIPTION = StylePackage.eINSTANCE.getTooltipStyleDescription();

        /**
         * The meta object literal for the '<em><b>Tooltip Expression</b></em>'
         * attribute feature. <!-- begin-user-doc -->
         *
         * @since 0.9.0 <!-- end-user-doc -->
         * @generated
         */
        EAttribute TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = StylePackage.eINSTANCE.getTooltipStyleDescription_TooltipExpression();

    }

} // StylePackage
