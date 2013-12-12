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
    String eNAME = "style";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/description/style/1.1.0";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "style";

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
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.RoundedCornerStyleDescriptionImpl
     * <em>Rounded Corner Style Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.RoundedCornerStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getRoundedCornerStyleDescription()
     * @generated
     */
    int ROUNDED_CORNER_STYLE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Arc Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH = STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arc Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT = STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Rounded Corner Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT = STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.BorderedStyleDescriptionImpl
     * <em>Bordered Style Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.BorderedStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBorderedStyleDescription()
     * @generated
     */
    int BORDERED_STYLE_DESCRIPTION = 2;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_DESCRIPTION__BORDER_COLOR = STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Bordered Style Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BORDERED_STYLE_DESCRIPTION_FEATURE_COUNT = STYLE_DESCRIPTION_FEATURE_COUNT + 2;

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
    int BASIC_LABEL_STYLE_DESCRIPTION = 3;

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
    int LABEL_STYLE_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_SIZE = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__SHOW_ICON = BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_COLOR = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__ICON_PATH = BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT = BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Label Style Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_STYLE_DESCRIPTION_FEATURE_COUNT = BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

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
    int LABEL_BORDER_STYLES = 5;

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
    int LABEL_BORDER_STYLE_DESCRIPTION = 6;

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
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.NodeStyleDescriptionImpl
     * <em>Node Style Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.NodeStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getNodeStyleDescription()
     * @generated
     */
    int NODE_STYLE_DESCRIPTION = 7;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__BORDER_COLOR = STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__LABEL_SIZE = STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__LABEL_FORMAT = STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__SHOW_ICON = STYLE_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION = STYLE_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__LABEL_COLOR = STYLE_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__ICON_PATH = STYLE_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT = STYLE_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = STYLE_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = STYLE_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__LABEL_POSITION = STYLE_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = STYLE_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION__RESIZE_KIND = STYLE_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The number of structural features of the '<em>Node Style Description</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_STYLE_DESCRIPTION_FEATURE_COUNT = STYLE_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.CustomStyleDescriptionImpl
     * <em>Custom Style Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.CustomStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getCustomStyleDescription()
     * @generated
     */
    int CUSTOM_STYLE_DESCRIPTION = 8;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION__ID = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Custom Style Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CUSTOM_STYLE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.SquareDescriptionImpl
     * <em>Square Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.SquareDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getSquareDescription()
     * @generated
     */
    int SQUARE_DESCRIPTION = 9;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__WIDTH = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__HEIGHT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION__COLOR = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Square Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SQUARE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.LozengeNodeDescriptionImpl
     * <em>Lozenge Node Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.LozengeNodeDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLozengeNodeDescription()
     * @generated
     */
    int LOZENGE_NODE_DESCRIPTION = 10;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Width Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION__COLOR = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Lozenge Node Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LOZENGE_NODE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.EllipseNodeDescriptionImpl
     * <em>Ellipse Node Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.EllipseNodeDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getEllipseNodeDescription()
     * @generated
     */
    int ELLIPSE_NODE_DESCRIPTION = 11;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__COLOR = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Horizontal Diameter Computation Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '
     * <em><b>Vertical Diameter Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Ellipse Node Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELLIPSE_NODE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.BundledImageDescriptionImpl
     * <em>Bundled Image Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.BundledImageDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBundledImageDescription()
     * @generated
     */
    int BUNDLED_IMAGE_DESCRIPTION = 12;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__SHAPE = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION__COLOR = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Bundled Image Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BUNDLED_IMAGE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.NoteDescriptionImpl
     * <em>Note Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.NoteDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getNoteDescription()
     * @generated
     */
    int NOTE_DESCRIPTION = 13;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION__COLOR = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Note Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NOTE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.DotDescriptionImpl
     * <em>Dot Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.DotDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getDotDescription()
     * @generated
     */
    int DOT_DESCRIPTION = 14;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__BACKGROUND_COLOR = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '
     * <em><b>Stroke Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Dot Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOT_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.GaugeCompositeStyleDescriptionImpl
     * <em>Gauge Composite Style Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.GaugeCompositeStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getGaugeCompositeStyleDescription()
     * @generated
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION = 15;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Alignment</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Sections</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Gauge Composite Style Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_COMPOSITE_STYLE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.ContainerStyleDescriptionImpl
     * <em>Container Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.ContainerStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getContainerStyleDescription()
     * @generated
     */
    int CONTAINER_STYLE_DESCRIPTION = 16;

    /**
     * The feature id for the '<em><b>Arc Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__ARC_WIDTH = ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH;

    /**
     * The feature id for the '<em><b>Arc Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__ARC_HEIGHT = ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__SHOW_ICON = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__ICON_PATH = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Rounded Corner</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '
     * <em>Container Style Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT = ROUNDED_CORNER_STYLE_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.FlatContainerStyleDescriptionImpl
     * <em>Flat Container Style Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.FlatContainerStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getFlatContainerStyleDescription()
     * @generated
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION = 17;

    /**
     * The feature id for the '<em><b>Arc Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__ARC_WIDTH = CONTAINER_STYLE_DESCRIPTION__ARC_WIDTH;

    /**
     * The feature id for the '<em><b>Arc Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__ARC_HEIGHT = CONTAINER_STYLE_DESCRIPTION__ARC_HEIGHT;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR = CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE = CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT = CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__SHOW_ICON = CONTAINER_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION = CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR = CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__ICON_PATH = CONTAINER_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT = CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Rounded Corner</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER = CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER;

    /**
     * The feature id for the '<em><b>Width Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Background Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label Border Style</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the '
     * <em>Flat Container Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FLAT_CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.ShapeContainerStyleDescriptionImpl
     * <em>Shape Container Style Description</em>}' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.ShapeContainerStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getShapeContainerStyleDescription()
     * @generated
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION = 18;

    /**
     * The feature id for the '<em><b>Arc Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__ARC_WIDTH = CONTAINER_STYLE_DESCRIPTION__ARC_WIDTH;

    /**
     * The feature id for the '<em><b>Arc Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__ARC_HEIGHT = CONTAINER_STYLE_DESCRIPTION__ARC_HEIGHT;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = CONTAINER_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR = CONTAINER_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE = CONTAINER_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT = CONTAINER_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__SHOW_ICON = CONTAINER_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION = CONTAINER_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR = CONTAINER_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__ICON_PATH = CONTAINER_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT = CONTAINER_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = CONTAINER_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Rounded Corner</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER = CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER;

    /**
     * The feature id for the '<em><b>Width Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>Shape Container Style Description</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SHAPE_CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT = CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.WorkspaceImageDescriptionImpl
     * <em>Workspace Image Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.WorkspaceImageDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getWorkspaceImageDescription()
     * @generated
     */
    int WORKSPACE_IMAGE_DESCRIPTION = 19;

    /**
     * The feature id for the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__BORDER_COLOR = NODE_STYLE_DESCRIPTION__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__LABEL_SIZE = NODE_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__LABEL_FORMAT = NODE_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__SHOW_ICON = NODE_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__LABEL_EXPRESSION = NODE_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__LABEL_COLOR = NODE_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__ICON_PATH = NODE_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The feature id for the '<em><b>Label Alignment</b></em>' attribute. <!--
     * begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__LABEL_ALIGNMENT = NODE_STYLE_DESCRIPTION__LABEL_ALIGNMENT;

    /**
     * The feature id for the '<em><b>Tooltip Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * 
     * @since 0.9.0<!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__TOOLTIP_EXPRESSION = NODE_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Position</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__LABEL_POSITION = NODE_STYLE_DESCRIPTION__LABEL_POSITION;

    /**
     * The feature id for the '<em><b>Hide Label By Default</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT;

    /**
     * The feature id for the '<em><b>Resize Kind</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__RESIZE_KIND = NODE_STYLE_DESCRIPTION__RESIZE_KIND;

    /**
     * The feature id for the '<em><b>Arc Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__ARC_WIDTH = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arc Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__ARC_HEIGHT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Rounded Corner</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__ROUNDED_CORNER = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Workspace Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION__WORKSPACE_PATH = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '
     * <em>Workspace Image Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int WORKSPACE_IMAGE_DESCRIPTION_FEATURE_COUNT = NODE_STYLE_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.EdgeStyleDescriptionImpl
     * <em>Edge Style Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.EdgeStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getEdgeStyleDescription()
     * @generated
     */
    int EDGE_STYLE_DESCRIPTION = 20;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__STROKE_COLOR = STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__LINE_STYLE = STYLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__SOURCE_ARROW = STYLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__TARGET_ARROW = STYLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = STYLE_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__ROUTING_STYLE = STYLE_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__FOLDING_STYLE = STYLE_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Begin Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION = STYLE_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Center Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION = STYLE_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>End Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION = STYLE_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Edge Style Description</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_STYLE_DESCRIPTION_FEATURE_COUNT = STYLE_DESCRIPTION_FEATURE_COUNT + 10;

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
    int TOOLTIP_STYLE_DESCRIPTION = 21;

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
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.GaugeSectionDescriptionImpl
     * <em>Gauge Section Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.GaugeSectionDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getGaugeSectionDescription()
     * @generated
     */
    int GAUGE_SECTION_DESCRIPTION = 22;

    /**
     * The feature id for the '<em><b>Min Value Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Max Value Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION = 2;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR = 3;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR = 4;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION__LABEL = 5;

    /**
     * The number of structural features of the '
     * <em>Gauge Section Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GAUGE_SECTION_DESCRIPTION_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.BeginLabelStyleDescriptionImpl
     * <em>Begin Label Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.BeginLabelStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBeginLabelStyleDescription()
     * @generated
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION = 23;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION__LABEL_SIZE = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION__SHOW_ICON = BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION__LABEL_COLOR = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION__ICON_PATH = BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The number of structural features of the '
     * <em>Begin Label Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BEGIN_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT = BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.CenterLabelStyleDescriptionImpl
     * <em>Center Label Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.CenterLabelStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getCenterLabelStyleDescription()
     * @generated
     */
    int CENTER_LABEL_STYLE_DESCRIPTION = 24;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION__LABEL_SIZE = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION__SHOW_ICON = BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION__LABEL_COLOR = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION__ICON_PATH = BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The number of structural features of the '
     * <em>Center Label Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CENTER_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT = BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.EndLabelStyleDescriptionImpl
     * <em>End Label Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.EndLabelStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getEndLabelStyleDescription()
     * @generated
     */
    int END_LABEL_STYLE_DESCRIPTION = 25;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION__LABEL_SIZE = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Show Icon</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION__SHOW_ICON = BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION__LABEL_COLOR = BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR;

    /**
     * The feature id for the '<em><b>Icon Path</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION__ICON_PATH = BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH;

    /**
     * The number of structural features of the '
     * <em>End Label Style Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int END_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT = BASIC_LABEL_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.BracketEdgeStyleDescriptionImpl
     * <em>Bracket Edge Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.BracketEdgeStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBracketEdgeStyleDescription()
     * @generated
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION = 26;

    /**
     * The feature id for the '<em><b>Stroke Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__STROKE_COLOR = EDGE_STYLE_DESCRIPTION__STROKE_COLOR;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__LINE_STYLE = EDGE_STYLE_DESCRIPTION__LINE_STYLE;

    /**
     * The feature id for the '<em><b>Source Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__SOURCE_ARROW = EDGE_STYLE_DESCRIPTION__SOURCE_ARROW;

    /**
     * The feature id for the '<em><b>Target Arrow</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__TARGET_ARROW = EDGE_STYLE_DESCRIPTION__TARGET_ARROW;

    /**
     * The feature id for the '<em><b>Size Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Routing Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__ROUTING_STYLE = EDGE_STYLE_DESCRIPTION__ROUTING_STYLE;

    /**
     * The feature id for the '<em><b>Folding Style</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__FOLDING_STYLE = EDGE_STYLE_DESCRIPTION__FOLDING_STYLE;

    /**
     * The feature id for the '<em><b>Begin Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION = EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Center Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION = EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION;

    /**
     * The feature id for the '<em><b>End Label Style Description</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION = EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION;

    /**
     * The number of structural features of the '
     * <em>Bracket Edge Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BRACKET_EDGE_STYLE_DESCRIPTION_FEATURE_COUNT = EDGE_STYLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.viewpoint.description.style.impl.SizeComputationContainerStyleDescriptionImpl
     * <em>Size Computation Container Style Description</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.viewpoint.description.style.impl.SizeComputationContainerStyleDescriptionImpl
     * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getSizeComputationContainerStyleDescription()
     * @generated
     */
    int SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION = 27;

    /**
     * The feature id for the '<em><b>Width Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Height Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION = 1;

    /**
     * The number of structural features of the '
     * <em>Size Computation Container Style Description</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION_FEATURE_COUNT = 2;

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
     * {@link org.eclipse.sirius.viewpoint.description.style.RoundedCornerStyleDescription
     * <em>Rounded Corner Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Rounded Corner Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.RoundedCornerStyleDescription
     * @generated
     */
    EClass getRoundedCornerStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.RoundedCornerStyleDescription#getArcWidth
     * <em>Arc Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Arc Width</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.RoundedCornerStyleDescription#getArcWidth()
     * @see #getRoundedCornerStyleDescription()
     * @generated
     */
    EAttribute getRoundedCornerStyleDescription_ArcWidth();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.RoundedCornerStyleDescription#getArcHeight
     * <em>Arc Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Arc Height</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.RoundedCornerStyleDescription#getArcHeight()
     * @see #getRoundedCornerStyleDescription()
     * @generated
     */
    EAttribute getRoundedCornerStyleDescription_ArcHeight();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription
     * <em>Bordered Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Bordered Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription
     * @generated
     */
    EClass getBorderedStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Border Size Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription#getBorderSizeComputationExpression()
     * @see #getBorderedStyleDescription()
     * @generated
     */
    EAttribute getBorderedStyleDescription_BorderSizeComputationExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription#getBorderColor
     * <em>Border Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Border Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BorderedStyleDescription#getBorderColor()
     * @see #getBorderedStyleDescription()
     * @generated
     */
    EReference getBorderedStyleDescription_BorderColor();

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
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Format</em>'.
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
     * {@link org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription
     * <em>Node Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Node Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription
     * @generated
     */
    EClass getNodeStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#getSizeComputationExpression
     * <em>Size Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Size Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#getSizeComputationExpression()
     * @see #getNodeStyleDescription()
     * @generated
     */
    EAttribute getNodeStyleDescription_SizeComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#getLabelPosition
     * <em>Label Position</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Position</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#getLabelPosition()
     * @see #getNodeStyleDescription()
     * @generated
     */
    EAttribute getNodeStyleDescription_LabelPosition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#isHideLabelByDefault
     * <em>Hide Label By Default</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Hide Label By Default</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#isHideLabelByDefault()
     * @see #getNodeStyleDescription()
     * @generated
     */
    EAttribute getNodeStyleDescription_HideLabelByDefault();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#getResizeKind
     * <em>Resize Kind</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Resize Kind</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.NodeStyleDescription#getResizeKind()
     * @see #getNodeStyleDescription()
     * @generated
     */
    EAttribute getNodeStyleDescription_ResizeKind();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.CustomStyleDescription
     * <em>Custom Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Custom Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.CustomStyleDescription
     * @generated
     */
    EClass getCustomStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.CustomStyleDescription#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.CustomStyleDescription#getId()
     * @see #getCustomStyleDescription()
     * @generated
     */
    EAttribute getCustomStyleDescription_Id();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.SquareDescription
     * <em>Square Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Square Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SquareDescription
     * @generated
     */
    EClass getSquareDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.SquareDescription#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SquareDescription#getWidth()
     * @see #getSquareDescription()
     * @generated
     */
    EAttribute getSquareDescription_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.SquareDescription#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SquareDescription#getHeight()
     * @see #getSquareDescription()
     * @generated
     */
    EAttribute getSquareDescription_Height();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.SquareDescription#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SquareDescription#getColor()
     * @see #getSquareDescription()
     * @generated
     */
    EReference getSquareDescription_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription
     * <em>Lozenge Node Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Lozenge Node Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription
     * @generated
     */
    EClass getLozengeNodeDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription#getWidthComputationExpression
     * <em>Width Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Width Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription#getWidthComputationExpression()
     * @see #getLozengeNodeDescription()
     * @generated
     */
    EAttribute getLozengeNodeDescription_WidthComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription#getHeightComputationExpression
     * <em>Height Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Height Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription#getHeightComputationExpression()
     * @see #getLozengeNodeDescription()
     * @generated
     */
    EAttribute getLozengeNodeDescription_HeightComputationExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.LozengeNodeDescription#getColor()
     * @see #getLozengeNodeDescription()
     * @generated
     */
    EReference getLozengeNodeDescription_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription
     * <em>Ellipse Node Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Ellipse Node Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription
     * @generated
     */
    EClass getEllipseNodeDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription#getColor()
     * @see #getEllipseNodeDescription()
     * @generated
     */
    EReference getEllipseNodeDescription_Color();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription#getHorizontalDiameterComputationExpression
     * <em>Horizontal Diameter Computation Expression</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Horizontal Diameter Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription#getHorizontalDiameterComputationExpression()
     * @see #getEllipseNodeDescription()
     * @generated
     */
    EAttribute getEllipseNodeDescription_HorizontalDiameterComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription#getVerticalDiameterComputationExpression
     * <em>Vertical Diameter Computation Expression</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Vertical Diameter Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EllipseNodeDescription#getVerticalDiameterComputationExpression()
     * @see #getEllipseNodeDescription()
     * @generated
     */
    EAttribute getEllipseNodeDescription_VerticalDiameterComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.BundledImageDescription
     * <em>Bundled Image Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Bundled Image Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BundledImageDescription
     * @generated
     */
    EClass getBundledImageDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.BundledImageDescription#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BundledImageDescription#getShape()
     * @see #getBundledImageDescription()
     * @generated
     */
    EAttribute getBundledImageDescription_Shape();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.BundledImageDescription#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BundledImageDescription#getColor()
     * @see #getBundledImageDescription()
     * @generated
     */
    EReference getBundledImageDescription_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.NoteDescription
     * <em>Note Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for class '<em>Note Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.NoteDescription
     * @generated
     */
    EClass getNoteDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.NoteDescription#getColor
     * <em>Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.NoteDescription#getColor()
     * @see #getNoteDescription()
     * @generated
     */
    EReference getNoteDescription_Color();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.DotDescription
     * <em>Dot Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Dot Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.DotDescription
     * @generated
     */
    EClass getDotDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.DotDescription#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.DotDescription#getBackgroundColor()
     * @see #getDotDescription()
     * @generated
     */
    EReference getDotDescription_BackgroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.DotDescription#getStrokeSizeComputationExpression
     * <em>Stroke Size Computation Expression</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Stroke Size Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.DotDescription#getStrokeSizeComputationExpression()
     * @see #getDotDescription()
     * @generated
     */
    EAttribute getDotDescription_StrokeSizeComputationExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription
     * <em>Gauge Composite Style Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Gauge Composite Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription
     * @generated
     */
    EClass getGaugeCompositeStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription#getAlignment
     * <em>Alignment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Alignment</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription#getAlignment()
     * @see #getGaugeCompositeStyleDescription()
     * @generated
     */
    EAttribute getGaugeCompositeStyleDescription_Alignment();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription#getSections
     * <em>Sections</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Sections</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeCompositeStyleDescription#getSections()
     * @see #getGaugeCompositeStyleDescription()
     * @generated
     */
    EReference getGaugeCompositeStyleDescription_Sections();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.ContainerStyleDescription
     * <em>Container Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Container Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.ContainerStyleDescription
     * @generated
     */
    EClass getContainerStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.ContainerStyleDescription#isRoundedCorner
     * <em>Rounded Corner</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Rounded Corner</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.ContainerStyleDescription#isRoundedCorner()
     * @see #getContainerStyleDescription()
     * @generated
     */
    EAttribute getContainerStyleDescription_RoundedCorner();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription
     * <em>Flat Container Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Flat Container Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription
     * @generated
     */
    EClass getFlatContainerStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getBackgroundStyle
     * <em>Background Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Background Style</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getBackgroundStyle()
     * @see #getFlatContainerStyleDescription()
     * @generated
     */
    EAttribute getFlatContainerStyleDescription_BackgroundStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getBackgroundColor()
     * @see #getFlatContainerStyleDescription()
     * @generated
     */
    EReference getFlatContainerStyleDescription_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getForegroundColor()
     * @see #getFlatContainerStyleDescription()
     * @generated
     */
    EReference getFlatContainerStyleDescription_ForegroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getLabelBorderStyle
     * <em>Label Border Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Label Border Style</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.FlatContainerStyleDescription#getLabelBorderStyle()
     * @see #getFlatContainerStyleDescription()
     * @generated
     */
    EReference getFlatContainerStyleDescription_LabelBorderStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription
     * <em>Shape Container Style Description</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Shape Container Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription
     * @generated
     */
    EClass getShapeContainerStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getShape()
     * @see #getShapeContainerStyleDescription()
     * @generated
     */
    EAttribute getShapeContainerStyleDescription_Shape();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getBackgroundColor()
     * @see #getShapeContainerStyleDescription()
     * @generated
     */
    EReference getShapeContainerStyleDescription_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.WorkspaceImageDescription
     * <em>Workspace Image Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Workspace Image Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.WorkspaceImageDescription
     * @generated
     */
    EClass getWorkspaceImageDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.WorkspaceImageDescription#getWorkspacePath
     * <em>Workspace Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Workspace Path</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.WorkspaceImageDescription#getWorkspacePath()
     * @see #getWorkspaceImageDescription()
     * @generated
     */
    EAttribute getWorkspaceImageDescription_WorkspacePath();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription
     * <em>Edge Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Edge Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription
     * @generated
     */
    EClass getEdgeStyleDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getStrokeColor
     * <em>Stroke Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Stroke Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getStrokeColor()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EReference getEdgeStyleDescription_StrokeColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getLineStyle
     * <em>Line Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getLineStyle()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EAttribute getEdgeStyleDescription_LineStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getSourceArrow
     * <em>Source Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Arrow</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getSourceArrow()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EAttribute getEdgeStyleDescription_SourceArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getTargetArrow
     * <em>Target Arrow</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Arrow</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getTargetArrow()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EAttribute getEdgeStyleDescription_TargetArrow();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getSizeComputationExpression
     * <em>Size Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Size Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getSizeComputationExpression()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EAttribute getEdgeStyleDescription_SizeComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getRoutingStyle
     * <em>Routing Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing Style</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getRoutingStyle()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EAttribute getEdgeStyleDescription_RoutingStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getFoldingStyle
     * <em>Folding Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Folding Style</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getFoldingStyle()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EAttribute getEdgeStyleDescription_FoldingStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getBeginLabelStyleDescription
     * <em>Begin Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Begin Label Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getBeginLabelStyleDescription()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EReference getEdgeStyleDescription_BeginLabelStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getCenterLabelStyleDescription
     * <em>Center Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Center Label Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getCenterLabelStyleDescription()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EReference getEdgeStyleDescription_CenterLabelStyleDescription();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getEndLabelStyleDescription
     * <em>End Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>End Label Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EdgeStyleDescription#getEndLabelStyleDescription()
     * @see #getEdgeStyleDescription()
     * @generated
     */
    EReference getEdgeStyleDescription_EndLabelStyleDescription();

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
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription
     * <em>Gauge Section Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Gauge Section Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription
     * @generated
     */
    EClass getGaugeSectionDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getMinValueExpression
     * <em>Min Value Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Min Value Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getMinValueExpression()
     * @see #getGaugeSectionDescription()
     * @generated
     */
    EAttribute getGaugeSectionDescription_MinValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getMaxValueExpression
     * <em>Max Value Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Max Value Expression</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getMaxValueExpression()
     * @see #getGaugeSectionDescription()
     * @generated
     */
    EAttribute getGaugeSectionDescription_MaxValueExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getValueExpression()
     * @see #getGaugeSectionDescription()
     * @generated
     */
    EAttribute getGaugeSectionDescription_ValueExpression();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getBackgroundColor()
     * @see #getGaugeSectionDescription()
     * @generated
     */
    EReference getGaugeSectionDescription_BackgroundColor();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @return the meta object for the reference '<em>Foreground Color</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getForegroundColor()
     * @see #getGaugeSectionDescription()
     * @generated
     */
    EReference getGaugeSectionDescription_ForegroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.GaugeSectionDescription#getLabel()
     * @see #getGaugeSectionDescription()
     * @generated
     */
    EAttribute getGaugeSectionDescription_Label();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.BeginLabelStyleDescription
     * <em>Begin Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Begin Label Style Description</em>
     *         '.
     * @see org.eclipse.sirius.viewpoint.description.style.BeginLabelStyleDescription
     * @generated
     */
    EClass getBeginLabelStyleDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.CenterLabelStyleDescription
     * <em>Center Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Center Label Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.CenterLabelStyleDescription
     * @generated
     */
    EClass getCenterLabelStyleDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.EndLabelStyleDescription
     * <em>End Label Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>End Label Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.EndLabelStyleDescription
     * @generated
     */
    EClass getEndLabelStyleDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.BracketEdgeStyleDescription
     * <em>Bracket Edge Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Bracket Edge Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.BracketEdgeStyleDescription
     * @generated
     */
    EClass getBracketEdgeStyleDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription
     * <em>Size Computation Container Style Description</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '
     *         <em>Size Computation Container Style Description</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription
     * @generated
     */
    EClass getSizeComputationContainerStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription#getWidthComputationExpression
     * <em>Width Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Width Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription#getWidthComputationExpression()
     * @see #getSizeComputationContainerStyleDescription()
     * @generated
     */
    EAttribute getSizeComputationContainerStyleDescription_WidthComputationExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription#getHeightComputationExpression
     * <em>Height Computation Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Height Computation Expression</em>'.
     * @see org.eclipse.sirius.viewpoint.description.style.SizeComputationContainerStyleDescription#getHeightComputationExpression()
     * @see #getSizeComputationContainerStyleDescription()
     * @generated
     */
    EAttribute getSizeComputationContainerStyleDescription_HeightComputationExpression();

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
        EClass STYLE_DESCRIPTION = eINSTANCE.getStyleDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.RoundedCornerStyleDescriptionImpl
         * <em>Rounded Corner Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.RoundedCornerStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getRoundedCornerStyleDescription()
         * @generated
         */
        EClass ROUNDED_CORNER_STYLE_DESCRIPTION = eINSTANCE.getRoundedCornerStyleDescription();

        /**
         * The meta object literal for the '<em><b>Arc Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH = eINSTANCE.getRoundedCornerStyleDescription_ArcWidth();

        /**
         * The meta object literal for the '<em><b>Arc Height</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT = eINSTANCE.getRoundedCornerStyleDescription_ArcHeight();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.BorderedStyleDescriptionImpl
         * <em>Bordered Style Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.BorderedStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBorderedStyleDescription()
         * @generated
         */
        EClass BORDERED_STYLE_DESCRIPTION = eINSTANCE.getBorderedStyleDescription();

        /**
         * The meta object literal for the '
         * <em><b>Border Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getBorderedStyleDescription_BorderSizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BORDERED_STYLE_DESCRIPTION__BORDER_COLOR = eINSTANCE.getBorderedStyleDescription_BorderColor();

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
        EClass BASIC_LABEL_STYLE_DESCRIPTION = eINSTANCE.getBasicLabelStyleDescription();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE = eINSTANCE.getBasicLabelStyleDescription_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT = eINSTANCE.getBasicLabelStyleDescription_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Show Icon</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON = eINSTANCE.getBasicLabelStyleDescription_ShowIcon();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getBasicLabelStyleDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Label Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR = eINSTANCE.getBasicLabelStyleDescription_LabelColor();

        /**
         * The meta object literal for the '<em><b>Icon Path</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH = eINSTANCE.getBasicLabelStyleDescription_IconPath();

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
        EClass LABEL_STYLE_DESCRIPTION = eINSTANCE.getLabelStyleDescription();

        /**
         * The meta object literal for the '<em><b>Label Alignment</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT = eINSTANCE.getLabelStyleDescription_LabelAlignment();

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
        EClass LABEL_BORDER_STYLES = eINSTANCE.getLabelBorderStyles();

        /**
         * The meta object literal for the '
         * <em><b>Label Border Style Descriptions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS = eINSTANCE.getLabelBorderStyles_LabelBorderStyleDescriptions();

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
        EClass LABEL_BORDER_STYLE_DESCRIPTION = eINSTANCE.getLabelBorderStyleDescription();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__ID = eINSTANCE.getLabelBorderStyleDescription_Id();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__NAME = eINSTANCE.getLabelBorderStyleDescription_Name();

        /**
         * The meta object literal for the '<em><b>Corner Height</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT = eINSTANCE.getLabelBorderStyleDescription_CornerHeight();

        /**
         * The meta object literal for the '<em><b>Corner Width</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH = eINSTANCE.getLabelBorderStyleDescription_CornerWidth();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.NodeStyleDescriptionImpl
         * <em>Node Style Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.NodeStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getNodeStyleDescription()
         * @generated
         */
        EClass NODE_STYLE_DESCRIPTION = eINSTANCE.getNodeStyleDescription();

        /**
         * The meta object literal for the '
         * <em><b>Size Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getNodeStyleDescription_SizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Label Position</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE_DESCRIPTION__LABEL_POSITION = eINSTANCE.getNodeStyleDescription_LabelPosition();

        /**
         * The meta object literal for the '
         * <em><b>Hide Label By Default</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT = eINSTANCE.getNodeStyleDescription_HideLabelByDefault();

        /**
         * The meta object literal for the '<em><b>Resize Kind</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_STYLE_DESCRIPTION__RESIZE_KIND = eINSTANCE.getNodeStyleDescription_ResizeKind();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.CustomStyleDescriptionImpl
         * <em>Custom Style Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.CustomStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getCustomStyleDescription()
         * @generated
         */
        EClass CUSTOM_STYLE_DESCRIPTION = eINSTANCE.getCustomStyleDescription();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CUSTOM_STYLE_DESCRIPTION__ID = eINSTANCE.getCustomStyleDescription_Id();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.SquareDescriptionImpl
         * <em>Square Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.SquareDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getSquareDescription()
         * @generated
         */
        EClass SQUARE_DESCRIPTION = eINSTANCE.getSquareDescription();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE_DESCRIPTION__WIDTH = eINSTANCE.getSquareDescription_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SQUARE_DESCRIPTION__HEIGHT = eINSTANCE.getSquareDescription_Height();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SQUARE_DESCRIPTION__COLOR = eINSTANCE.getSquareDescription_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.LozengeNodeDescriptionImpl
         * <em>Lozenge Node Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.LozengeNodeDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getLozengeNodeDescription()
         * @generated
         */
        EClass LOZENGE_NODE_DESCRIPTION = eINSTANCE.getLozengeNodeDescription();

        /**
         * The meta object literal for the '
         * <em><b>Width Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION = eINSTANCE.getLozengeNodeDescription_WidthComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Height Computation Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION = eINSTANCE.getLozengeNodeDescription_HeightComputationExpression();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LOZENGE_NODE_DESCRIPTION__COLOR = eINSTANCE.getLozengeNodeDescription_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.EllipseNodeDescriptionImpl
         * <em>Ellipse Node Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.EllipseNodeDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getEllipseNodeDescription()
         * @generated
         */
        EClass ELLIPSE_NODE_DESCRIPTION = eINSTANCE.getEllipseNodeDescription();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ELLIPSE_NODE_DESCRIPTION__COLOR = eINSTANCE.getEllipseNodeDescription_Color();

        /**
         * The meta object literal for the '
         * <em><b>Horizontal Diameter Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION = eINSTANCE.getEllipseNodeDescription_HorizontalDiameterComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Vertical Diameter Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION = eINSTANCE.getEllipseNodeDescription_VerticalDiameterComputationExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.BundledImageDescriptionImpl
         * <em>Bundled Image Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.BundledImageDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBundledImageDescription()
         * @generated
         */
        EClass BUNDLED_IMAGE_DESCRIPTION = eINSTANCE.getBundledImageDescription();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BUNDLED_IMAGE_DESCRIPTION__SHAPE = eINSTANCE.getBundledImageDescription_Shape();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BUNDLED_IMAGE_DESCRIPTION__COLOR = eINSTANCE.getBundledImageDescription_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.NoteDescriptionImpl
         * <em>Note Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.NoteDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getNoteDescription()
         * @generated
         */
        EClass NOTE_DESCRIPTION = eINSTANCE.getNoteDescription();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NOTE_DESCRIPTION__COLOR = eINSTANCE.getNoteDescription_Color();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.DotDescriptionImpl
         * <em>Dot Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.DotDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getDotDescription()
         * @generated
         */
        EClass DOT_DESCRIPTION = eINSTANCE.getDotDescription();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOT_DESCRIPTION__BACKGROUND_COLOR = eINSTANCE.getDotDescription_BackgroundColor();

        /**
         * The meta object literal for the '
         * <em><b>Stroke Size Computation Expression</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getDotDescription_StrokeSizeComputationExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.GaugeCompositeStyleDescriptionImpl
         * <em>Gauge Composite Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.GaugeCompositeStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getGaugeCompositeStyleDescription()
         * @generated
         */
        EClass GAUGE_COMPOSITE_STYLE_DESCRIPTION = eINSTANCE.getGaugeCompositeStyleDescription();

        /**
         * The meta object literal for the '<em><b>Alignment</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT = eINSTANCE.getGaugeCompositeStyleDescription_Alignment();

        /**
         * The meta object literal for the '<em><b>Sections</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS = eINSTANCE.getGaugeCompositeStyleDescription_Sections();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.ContainerStyleDescriptionImpl
         * <em>Container Style Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.ContainerStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getContainerStyleDescription()
         * @generated
         */
        EClass CONTAINER_STYLE_DESCRIPTION = eINSTANCE.getContainerStyleDescription();

        /**
         * The meta object literal for the '<em><b>Rounded Corner</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER = eINSTANCE.getContainerStyleDescription_RoundedCorner();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.FlatContainerStyleDescriptionImpl
         * <em>Flat Container Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.FlatContainerStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getFlatContainerStyleDescription()
         * @generated
         */
        EClass FLAT_CONTAINER_STYLE_DESCRIPTION = eINSTANCE.getFlatContainerStyleDescription();

        /**
         * The meta object literal for the '<em><b>Background Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE = eINSTANCE.getFlatContainerStyleDescription_BackgroundStyle();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR = eINSTANCE.getFlatContainerStyleDescription_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR = eINSTANCE.getFlatContainerStyleDescription_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Label Border Style</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE = eINSTANCE.getFlatContainerStyleDescription_LabelBorderStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.ShapeContainerStyleDescriptionImpl
         * <em>Shape Container Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.ShapeContainerStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getShapeContainerStyleDescription()
         * @generated
         */
        EClass SHAPE_CONTAINER_STYLE_DESCRIPTION = eINSTANCE.getShapeContainerStyleDescription();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE = eINSTANCE.getShapeContainerStyleDescription_Shape();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR = eINSTANCE.getShapeContainerStyleDescription_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.WorkspaceImageDescriptionImpl
         * <em>Workspace Image Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.WorkspaceImageDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getWorkspaceImageDescription()
         * @generated
         */
        EClass WORKSPACE_IMAGE_DESCRIPTION = eINSTANCE.getWorkspaceImageDescription();

        /**
         * The meta object literal for the '<em><b>Workspace Path</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute WORKSPACE_IMAGE_DESCRIPTION__WORKSPACE_PATH = eINSTANCE.getWorkspaceImageDescription_WorkspacePath();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.EdgeStyleDescriptionImpl
         * <em>Edge Style Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.EdgeStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getEdgeStyleDescription()
         * @generated
         */
        EClass EDGE_STYLE_DESCRIPTION = eINSTANCE.getEdgeStyleDescription();

        /**
         * The meta object literal for the '<em><b>Stroke Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE_DESCRIPTION__STROKE_COLOR = eINSTANCE.getEdgeStyleDescription_StrokeColor();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE_DESCRIPTION__LINE_STYLE = eINSTANCE.getEdgeStyleDescription_LineStyle();

        /**
         * The meta object literal for the '<em><b>Source Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE_DESCRIPTION__SOURCE_ARROW = eINSTANCE.getEdgeStyleDescription_SourceArrow();

        /**
         * The meta object literal for the '<em><b>Target Arrow</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE_DESCRIPTION__TARGET_ARROW = eINSTANCE.getEdgeStyleDescription_TargetArrow();

        /**
         * The meta object literal for the '
         * <em><b>Size Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION = eINSTANCE.getEdgeStyleDescription_SizeComputationExpression();

        /**
         * The meta object literal for the '<em><b>Routing Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE_DESCRIPTION__ROUTING_STYLE = eINSTANCE.getEdgeStyleDescription_RoutingStyle();

        /**
         * The meta object literal for the '<em><b>Folding Style</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_STYLE_DESCRIPTION__FOLDING_STYLE = eINSTANCE.getEdgeStyleDescription_FoldingStyle();

        /**
         * The meta object literal for the '
         * <em><b>Begin Label Style Description</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION = eINSTANCE.getEdgeStyleDescription_BeginLabelStyleDescription();

        /**
         * The meta object literal for the '
         * <em><b>Center Label Style Description</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION = eINSTANCE.getEdgeStyleDescription_CenterLabelStyleDescription();

        /**
         * The meta object literal for the '
         * <em><b>End Label Style Description</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION = eINSTANCE.getEdgeStyleDescription_EndLabelStyleDescription();

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
        EClass TOOLTIP_STYLE_DESCRIPTION = eINSTANCE.getTooltipStyleDescription();

        /**
         * The meta object literal for the '<em><b>Tooltip Expression</b></em>'
         * attribute feature. <!-- begin-user-doc -->
         * 
         * @since 0.9.0 <!-- end-user-doc -->
         * @generated
         */
        EAttribute TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION = eINSTANCE.getTooltipStyleDescription_TooltipExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.GaugeSectionDescriptionImpl
         * <em>Gauge Section Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.GaugeSectionDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getGaugeSectionDescription()
         * @generated
         */
        EClass GAUGE_SECTION_DESCRIPTION = eINSTANCE.getGaugeSectionDescription();

        /**
         * The meta object literal for the '<em><b>Min Value Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION = eINSTANCE.getGaugeSectionDescription_MinValueExpression();

        /**
         * The meta object literal for the '<em><b>Max Value Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION = eINSTANCE.getGaugeSectionDescription_MaxValueExpression();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION = eINSTANCE.getGaugeSectionDescription_ValueExpression();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR = eINSTANCE.getGaugeSectionDescription_BackgroundColor();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR = eINSTANCE.getGaugeSectionDescription_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute GAUGE_SECTION_DESCRIPTION__LABEL = eINSTANCE.getGaugeSectionDescription_Label();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.BeginLabelStyleDescriptionImpl
         * <em>Begin Label Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.BeginLabelStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBeginLabelStyleDescription()
         * @generated
         */
        EClass BEGIN_LABEL_STYLE_DESCRIPTION = eINSTANCE.getBeginLabelStyleDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.CenterLabelStyleDescriptionImpl
         * <em>Center Label Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.CenterLabelStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getCenterLabelStyleDescription()
         * @generated
         */
        EClass CENTER_LABEL_STYLE_DESCRIPTION = eINSTANCE.getCenterLabelStyleDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.EndLabelStyleDescriptionImpl
         * <em>End Label Style Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.EndLabelStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getEndLabelStyleDescription()
         * @generated
         */
        EClass END_LABEL_STYLE_DESCRIPTION = eINSTANCE.getEndLabelStyleDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.BracketEdgeStyleDescriptionImpl
         * <em>Bracket Edge Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.BracketEdgeStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getBracketEdgeStyleDescription()
         * @generated
         */
        EClass BRACKET_EDGE_STYLE_DESCRIPTION = eINSTANCE.getBracketEdgeStyleDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.viewpoint.description.style.impl.SizeComputationContainerStyleDescriptionImpl
         * <em>Size Computation Container Style Description</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.viewpoint.description.style.impl.SizeComputationContainerStyleDescriptionImpl
         * @see org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl#getSizeComputationContainerStyleDescription()
         * @generated
         */
        EClass SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION = eINSTANCE.getSizeComputationContainerStyleDescription();

        /**
         * The meta object literal for the '
         * <em><b>Width Computation Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION = eINSTANCE.getSizeComputationContainerStyleDescription_WidthComputationExpression();

        /**
         * The meta object literal for the '
         * <em><b>Height Computation Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION = eINSTANCE.getSizeComputationContainerStyleDescription_HeightComputationExpression();

    }

} // StylePackage
