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
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

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
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory
 * @model kind="package"
 * @generated
 */
public interface DescriptionPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "description"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/table/description/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "description"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    DescriptionPackage eINSTANCE = org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl
     * <em>Table Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableDescription()
     * @generated
     */
    int TABLE_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__END_USER_DOCUMENTATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__LABEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__TITLE_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__INITIALISATION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__METAMODEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__SHOW_ON_STARTUP = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__PRECONDITION_EXPRESSION = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__DOMAIN_CLASS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '
     * <em><b>Owned Representation Creation Descriptions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '
     * <em><b>Reused Representation Creation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '
     * <em><b>All Representation Creation Descriptions</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '
     * <em><b>Owned Representation Navigation Descriptions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '
     * <em><b>Reused Representation Navigation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '
     * <em><b>All Representation Navigation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Owned Line Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Reused Line Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>All Line Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__ALL_LINE_MAPPINGS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Owned Create Line</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__OWNED_CREATE_LINE = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Reused Create Line</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__REUSED_CREATE_LINE = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>All Create Line</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__ALL_CREATE_LINE = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Initial Header Column Width</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Imported Elements</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION__IMPORTED_ELEMENTS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 15;

    /**
     * The number of structural features of the '<em>Table Description</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_DESCRIPTION_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 16;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl
     * <em>Edition Table Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getEditionTableDescription()
     * @generated
     */
    int EDITION_TABLE_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__DOCUMENTATION = DescriptionPackage.TABLE_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__END_USER_DOCUMENTATION = DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__NAME = DescriptionPackage.TABLE_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__LABEL = DescriptionPackage.TABLE_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__INITIALISATION = DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__METAMODEL = DescriptionPackage.TABLE_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '
     * <em><b>Owned Representation Creation Descriptions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>Reused Representation Creation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>All Representation Creation Descriptions</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>Owned Representation Navigation Descriptions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>Reused Representation Navigation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>All Representation Navigation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Owned Line Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Line Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Line Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__ALL_LINE_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION__ALL_LINE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Owned Create Line</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__OWNED_CREATE_LINE = DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE;

    /**
     * The feature id for the '<em><b>Reused Create Line</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__REUSED_CREATE_LINE = DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE;

    /**
     * The feature id for the '<em><b>All Create Line</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__ALL_CREATE_LINE = DescriptionPackage.TABLE_DESCRIPTION__ALL_CREATE_LINE;

    /**
     * The feature id for the '<em><b>Initial Header Column Width</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH = DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH;

    /**
     * The feature id for the '<em><b>Imported Elements</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__IMPORTED_ELEMENTS = DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Column Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reused Column Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>All Column Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION__ALL_COLUMN_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Edition Table Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDITION_TABLE_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl
     * <em>Cross Table Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCrossTableDescription()
     * @generated
     */
    int CROSS_TABLE_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__DOCUMENTATION = DescriptionPackage.TABLE_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>End User Documentation</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__END_USER_DOCUMENTATION = DescriptionPackage.TABLE_DESCRIPTION__END_USER_DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__NAME = DescriptionPackage.TABLE_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__LABEL = DescriptionPackage.TABLE_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.TABLE_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initialisation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__INITIALISATION = DescriptionPackage.TABLE_DESCRIPTION__INITIALISATION;

    /**
     * The feature id for the '<em><b>Metamodel</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__METAMODEL = DescriptionPackage.TABLE_DESCRIPTION__METAMODEL;

    /**
     * The feature id for the '<em><b>Show On Startup</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.TABLE_DESCRIPTION__SHOW_ON_STARTUP;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.TABLE_DESCRIPTION__PRECONDITION_EXPRESSION;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.TABLE_DESCRIPTION__DOMAIN_CLASS;

    /**
     * The feature id for the '
     * <em><b>Owned Representation Creation Descriptions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>Reused Representation Creation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>All Representation Creation Descriptions</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>Owned Representation Navigation Descriptions</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>Reused Representation Navigation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '
     * <em><b>All Representation Navigation Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Owned Line Mappings</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Reused Line Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS;

    /**
     * The feature id for the '<em><b>All Line Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__ALL_LINE_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION__ALL_LINE_MAPPINGS;

    /**
     * The feature id for the '<em><b>Owned Create Line</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__OWNED_CREATE_LINE = DescriptionPackage.TABLE_DESCRIPTION__OWNED_CREATE_LINE;

    /**
     * The feature id for the '<em><b>Reused Create Line</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__REUSED_CREATE_LINE = DescriptionPackage.TABLE_DESCRIPTION__REUSED_CREATE_LINE;

    /**
     * The feature id for the '<em><b>All Create Line</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__ALL_CREATE_LINE = DescriptionPackage.TABLE_DESCRIPTION__ALL_CREATE_LINE;

    /**
     * The feature id for the '<em><b>Initial Header Column Width</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH = DescriptionPackage.TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH;

    /**
     * The feature id for the '<em><b>Imported Elements</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__IMPORTED_ELEMENTS = DescriptionPackage.TABLE_DESCRIPTION__IMPORTED_ELEMENTS;

    /**
     * The feature id for the '<em><b>Owned Column Mappings</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Intersection</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__INTERSECTION = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Create Column</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION__CREATE_COLUMN = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '
     * <em>Cross Table Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CROSS_TABLE_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.TABLE_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableMappingImpl
     * <em>Table Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableMappingImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableMapping()
     * @generated
     */
    int TABLE_MAPPING = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_MAPPING__NAME = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_MAPPING__LABEL = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_MAPPING__DETAIL_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_MAPPING__NAVIGATION_DESCRIPTIONS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_MAPPING__SEMANTIC_ELEMENTS = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Table Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_MAPPING_FEATURE_COUNT = org.eclipse.sirius.viewpoint.description.DescriptionPackage.REPRESENTATION_ELEMENT_MAPPING_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl
     * <em>Line Mapping</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getLineMapping()
     * @generated
     */
    int LINE_MAPPING = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LINE_MAPPING__NAME = DescriptionPackage.TABLE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LINE_MAPPING__LABEL = DescriptionPackage.TABLE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.TABLE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.TABLE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Default Foreground</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__DEFAULT_FOREGROUND = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Foreground Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__FOREGROUND_CONDITIONAL_STYLE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Default Background</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__DEFAULT_BACKGROUND = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Background Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__BACKGROUND_CONDITIONAL_STYLE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Owned Sub Lines</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__OWNED_SUB_LINES = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Reused Sub Lines</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__REUSED_SUB_LINES = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>All Sub Lines</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__ALL_SUB_LINES = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Reused In Mappings</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__REUSED_IN_MAPPINGS = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LINE_MAPPING__DOMAIN_CLASS = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Create</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__CREATE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Delete</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__DELETE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Header Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING__HEADER_LABEL_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the '<em>Line Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_MAPPING_FEATURE_COUNT = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 13;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ColumnMappingImpl
     * <em>Column Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.ColumnMappingImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getColumnMapping()
     * @generated
     */
    int COLUMN_MAPPING = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__NAME = DescriptionPackage.TABLE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__LABEL = DescriptionPackage.TABLE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.TABLE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.TABLE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Header Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__HEADER_LABEL_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Initial Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING__INITIAL_WIDTH = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Column Mapping</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COLUMN_MAPPING_FEATURE_COUNT = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl
     * <em>Element Column Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getElementColumnMapping()
     * @generated
     */
    int ELEMENT_COLUMN_MAPPING = 6;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__NAME = DescriptionPackage.COLUMN_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__LABEL = DescriptionPackage.COLUMN_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.COLUMN_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.COLUMN_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.COLUMN_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Header Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__HEADER_LABEL_EXPRESSION = DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__INITIAL_WIDTH = DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH;

    /**
     * The feature id for the '<em><b>Default Foreground</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__DEFAULT_FOREGROUND = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Foreground Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Default Background</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__DEFAULT_BACKGROUND = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Background Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Create</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__CREATE = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Delete</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING__DELETE = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Element Column Mapping</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ELEMENT_COLUMN_MAPPING_FEATURE_COUNT = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl
     * <em>Feature Column Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getFeatureColumnMapping()
     * @generated
     */
    int FEATURE_COLUMN_MAPPING = 7;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__NAME = DescriptionPackage.COLUMN_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__LABEL = DescriptionPackage.COLUMN_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.COLUMN_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.COLUMN_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.COLUMN_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Header Label Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__HEADER_LABEL_EXPRESSION = DescriptionPackage.COLUMN_MAPPING__HEADER_LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Initial Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__INITIAL_WIDTH = DescriptionPackage.COLUMN_MAPPING__INITIAL_WIDTH;

    /**
     * The feature id for the '<em><b>Direct Edit</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__DIRECT_EDIT = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Can Edit</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__CAN_EDIT = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Default Foreground</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__DEFAULT_FOREGROUND = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Foreground Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__FOREGROUND_CONDITIONAL_STYLE = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Default Background</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__DEFAULT_BACKGROUND = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Background Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__BACKGROUND_CONDITIONAL_STYLE = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__FEATURE_NAME = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Feature Parent Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Feature Column Mapping</em>
     * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FEATURE_COLUMN_MAPPING_FEATURE_COUNT = DescriptionPackage.COLUMN_MAPPING_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl
     * <em>Cell Updater</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCellUpdater()
     * @generated
     */
    int CELL_UPDATER = 8;

    /**
     * The feature id for the '<em><b>Direct Edit</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CELL_UPDATER__DIRECT_EDIT = 0;

    /**
     * The feature id for the '<em><b>Can Edit</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CELL_UPDATER__CAN_EDIT = 1;

    /**
     * The number of structural features of the '<em>Cell Updater</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CELL_UPDATER_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl
     * <em>Style Updater</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getStyleUpdater()
     * @generated
     */
    int STYLE_UPDATER = 9;

    /**
     * The feature id for the '<em><b>Default Foreground</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_UPDATER__DEFAULT_FOREGROUND = 0;

    /**
     * The feature id for the '<em><b>Foreground Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE = 1;

    /**
     * The feature id for the '<em><b>Default Background</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_UPDATER__DEFAULT_BACKGROUND = 2;

    /**
     * The feature id for the '<em><b>Background Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE = 3;

    /**
     * The number of structural features of the '<em>Style Updater</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STYLE_UPDATER_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl
     * <em>Intersection Mapping</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getIntersectionMapping()
     * @generated
     */
    int INTERSECTION_MAPPING = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__NAME = DescriptionPackage.TABLE_MAPPING__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__LABEL = DescriptionPackage.TABLE_MAPPING__LABEL;

    /**
     * The feature id for the '<em><b>Detail Descriptions</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__DETAIL_DESCRIPTIONS = DescriptionPackage.TABLE_MAPPING__DETAIL_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Navigation Descriptions</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__NAVIGATION_DESCRIPTIONS = DescriptionPackage.TABLE_MAPPING__NAVIGATION_DESCRIPTIONS;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.TABLE_MAPPING__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Direct Edit</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__DIRECT_EDIT = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Can Edit</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__CAN_EDIT = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Default Foreground</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__DEFAULT_FOREGROUND = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Foreground Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__FOREGROUND_CONDITIONAL_STYLE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Default Background</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__DEFAULT_BACKGROUND = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Background Conditional Style</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__BACKGROUND_CONDITIONAL_STYLE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Line Mapping</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__LINE_MAPPING = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Column Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__COLUMN_MAPPING = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__LABEL_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Use Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__USE_DOMAIN_CLASS = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Column Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Line Finder Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Domain Class</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__DOMAIN_CLASS = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 13;

    /**
     * The feature id for the '<em><b>Precondition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 14;

    /**
     * The feature id for the '<em><b>Create</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING__CREATE = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 15;

    /**
     * The number of structural features of the '<em>Intersection Mapping</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int INTERSECTION_MAPPING_FEATURE_COUNT = DescriptionPackage.TABLE_MAPPING_FEATURE_COUNT + 16;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableToolImpl
     * <em>Table Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableTool()
     * @generated
     */
    int TABLE_TOOL = 11;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_TOOL__VARIABLES = 0;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_TOOL__FIRST_MODEL_OPERATION = 1;

    /**
     * The number of structural features of the '<em>Table Tool</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_TOOL_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LabelEditToolImpl
     * <em>Label Edit Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.LabelEditToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getLabelEditTool()
     * @generated
     */
    int LABEL_EDIT_TOOL = 12;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL__VARIABLES = DescriptionPackage.TABLE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.TABLE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Mask</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL__MASK = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Label Edit Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL_FEATURE_COUNT = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateToolImpl
     * <em>Create Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateTool()
     * @generated
     */
    int CREATE_TOOL = 13;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_TOOL__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_TOOL__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_TOOL__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_TOOL__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_TOOL__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_TOOL__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_TOOL__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_TOOL__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_TOOL__VARIABLES = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_TOOL__FIRST_MODEL_OPERATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Create Tool</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_TOOL_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateColumnToolImpl
     * <em>Create Column Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateColumnToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateColumnTool()
     * @generated
     */
    int CREATE_COLUMN_TOOL = 14;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__DOCUMENTATION = DescriptionPackage.CREATE_TOOL__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__NAME = DescriptionPackage.CREATE_TOOL__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__LABEL = DescriptionPackage.CREATE_TOOL__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__PRECONDITION = DescriptionPackage.CREATE_TOOL__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__FORCE_REFRESH = DescriptionPackage.CREATE_TOOL__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__FILTERS = DescriptionPackage.CREATE_TOOL__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__ELEMENTS_TO_SELECT = DescriptionPackage.CREATE_TOOL__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__INVERSE_SELECTION_ORDER = DescriptionPackage.CREATE_TOOL__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__VARIABLES = DescriptionPackage.CREATE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.CREATE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL__MAPPING = DescriptionPackage.CREATE_TOOL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Create Column Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_COLUMN_TOOL_FEATURE_COUNT = DescriptionPackage.CREATE_TOOL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCrossColumnToolImpl
     * <em>Create Cross Column Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateCrossColumnToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateCrossColumnTool()
     * @generated
     */
    int CREATE_CROSS_COLUMN_TOOL = 15;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__DOCUMENTATION = DescriptionPackage.CREATE_TOOL__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__NAME = DescriptionPackage.CREATE_TOOL__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__LABEL = DescriptionPackage.CREATE_TOOL__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__PRECONDITION = DescriptionPackage.CREATE_TOOL__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__FORCE_REFRESH = DescriptionPackage.CREATE_TOOL__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__FILTERS = DescriptionPackage.CREATE_TOOL__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__ELEMENTS_TO_SELECT = DescriptionPackage.CREATE_TOOL__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__INVERSE_SELECTION_ORDER = DescriptionPackage.CREATE_TOOL__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__VARIABLES = DescriptionPackage.CREATE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.CREATE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL__MAPPING = DescriptionPackage.CREATE_TOOL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Create Cross Column Tool</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CROSS_COLUMN_TOOL_FEATURE_COUNT = DescriptionPackage.CREATE_TOOL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateLineToolImpl
     * <em>Create Line Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateLineToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateLineTool()
     * @generated
     */
    int CREATE_LINE_TOOL = 16;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__DOCUMENTATION = DescriptionPackage.CREATE_TOOL__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__NAME = DescriptionPackage.CREATE_TOOL__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__LABEL = DescriptionPackage.CREATE_TOOL__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__PRECONDITION = DescriptionPackage.CREATE_TOOL__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__FORCE_REFRESH = DescriptionPackage.CREATE_TOOL__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__FILTERS = DescriptionPackage.CREATE_TOOL__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__ELEMENTS_TO_SELECT = DescriptionPackage.CREATE_TOOL__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__INVERSE_SELECTION_ORDER = DescriptionPackage.CREATE_TOOL__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__VARIABLES = DescriptionPackage.CREATE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.CREATE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL__MAPPING = DescriptionPackage.CREATE_TOOL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Create Line Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_LINE_TOOL_FEATURE_COUNT = DescriptionPackage.CREATE_TOOL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl
     * <em>Create Cell Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateCellTool()
     * @generated
     */
    int CREATE_CELL_TOOL = 17;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__VARIABLES = DescriptionPackage.TABLE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.TABLE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__DOCUMENTATION = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__NAME = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__LABEL = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__PRECONDITION = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__FORCE_REFRESH = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__FILTERS = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__ELEMENTS_TO_SELECT = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__INVERSE_SELECTION_ORDER = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Mask</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__MASK = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL__MAPPING = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Create Cell Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CREATE_CELL_TOOL_FEATURE_COUNT = DescriptionPackage.TABLE_TOOL_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.DeleteToolImpl
     * <em>Delete Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DeleteToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getDeleteTool()
     * @generated
     */
    int DELETE_TOOL = 18;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__DOCUMENTATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__NAME = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__LABEL = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__PRECONDITION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__FORCE_REFRESH = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_TOOL__FILTERS = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_TOOL__ELEMENTS_TO_SELECT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_TOOL__INVERSE_SELECTION_ORDER = ToolPackage.ABSTRACT_TOOL_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_TOOL__VARIABLES = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_TOOL__FIRST_MODEL_OPERATION = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Delete Tool</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_TOOL_FEATURE_COUNT = ToolPackage.ABSTRACT_TOOL_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.DeleteColumnToolImpl
     * <em>Delete Column Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DeleteColumnToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getDeleteColumnTool()
     * @generated
     */
    int DELETE_COLUMN_TOOL = 19;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__DOCUMENTATION = DescriptionPackage.DELETE_TOOL__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__NAME = DescriptionPackage.DELETE_TOOL__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__LABEL = DescriptionPackage.DELETE_TOOL__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__PRECONDITION = DescriptionPackage.DELETE_TOOL__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__FORCE_REFRESH = DescriptionPackage.DELETE_TOOL__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__FILTERS = DescriptionPackage.DELETE_TOOL__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__ELEMENTS_TO_SELECT = DescriptionPackage.DELETE_TOOL__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__INVERSE_SELECTION_ORDER = DescriptionPackage.DELETE_TOOL__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__VARIABLES = DescriptionPackage.DELETE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.DELETE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL__MAPPING = DescriptionPackage.DELETE_TOOL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Delete Column Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_COLUMN_TOOL_FEATURE_COUNT = DescriptionPackage.DELETE_TOOL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.DeleteLineToolImpl
     * <em>Delete Line Tool</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DeleteLineToolImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getDeleteLineTool()
     * @generated
     */
    int DELETE_LINE_TOOL = 20;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__DOCUMENTATION = DescriptionPackage.DELETE_TOOL__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__NAME = DescriptionPackage.DELETE_TOOL__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__LABEL = DescriptionPackage.DELETE_TOOL__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__PRECONDITION = DescriptionPackage.DELETE_TOOL__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__FORCE_REFRESH = DescriptionPackage.DELETE_TOOL__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__FILTERS = DescriptionPackage.DELETE_TOOL__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__ELEMENTS_TO_SELECT = DescriptionPackage.DELETE_TOOL__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__INVERSE_SELECTION_ORDER = DescriptionPackage.DELETE_TOOL__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__VARIABLES = DescriptionPackage.DELETE_TOOL__VARIABLES;

    /**
     * The feature id for the '<em><b>First Model Operation</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.DELETE_TOOL__FIRST_MODEL_OPERATION;

    /**
     * The feature id for the '<em><b>Mapping</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL__MAPPING = DescriptionPackage.DELETE_TOOL_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Delete Line Tool</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DELETE_LINE_TOOL_FEATURE_COUNT = DescriptionPackage.DELETE_TOOL_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl
     * <em>Foreground Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getForegroundStyleDescription()
     * @generated
     */
    int FOREGROUND_STYLE_DESCRIPTION = 21;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE = 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT = 1;

    /**
     * The feature id for the '<em><b>Fore Ground Color</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR = 2;

    /**
     * The number of structural features of the '
     * <em>Foreground Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOREGROUND_STYLE_DESCRIPTION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundStyleDescriptionImpl
     * <em>Background Style Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundStyleDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getBackgroundStyleDescription()
     * @generated
     */
    int BACKGROUND_STYLE_DESCRIPTION = 22;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int BACKGROUND_STYLE_DESCRIPTION__BACKGROUND_COLOR = 0;

    /**
     * The number of structural features of the '
     * <em>Background Style Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BACKGROUND_STYLE_DESCRIPTION_FEATURE_COUNT = 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundConditionalStyleImpl
     * <em>Foreground Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundConditionalStyleImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getForegroundConditionalStyle()
     * @generated
     */
    int FOREGROUND_CONDITIONAL_STYLE = 23;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOREGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOREGROUND_CONDITIONAL_STYLE__STYLE = 1;

    /**
     * The number of structural features of the '
     * <em>Foreground Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int FOREGROUND_CONDITIONAL_STYLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundConditionalStyleImpl
     * <em>Background Conditional Style</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundConditionalStyleImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getBackgroundConditionalStyle()
     * @generated
     */
    int BACKGROUND_CONDITIONAL_STYLE = 24;

    /**
     * The feature id for the '<em><b>Predicate Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BACKGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION = 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BACKGROUND_CONDITIONAL_STYLE__STYLE = 1;

    /**
     * The number of structural features of the '
     * <em>Background Conditional Style</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BACKGROUND_CONDITIONAL_STYLE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableVariableImpl
     * <em>Table Variable</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableVariableImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableVariable()
     * @generated
     */
    int TABLE_VARIABLE = 25;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_VARIABLE__NAME = ToolPackage.ABSTRACT_VARIABLE__NAME;

    /**
     * The feature id for the '<em><b>Sub Variables</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_VARIABLE__SUB_VARIABLES = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_VARIABLE__DOCUMENTATION = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Table Variable</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_VARIABLE_FEATURE_COUNT = ToolPackage.ABSTRACT_VARIABLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableCreationDescriptionImpl
     * <em>Table Creation Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableCreationDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableCreationDescription()
     * @generated
     */
    int TABLE_CREATION_DESCRIPTION = 26;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__DOCUMENTATION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__NAME = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__LABEL = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__PRECONDITION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__FORCE_REFRESH = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__FILTERS = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__TITLE_EXPRESSION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__BROWSE_EXPRESSION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__BROWSE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Initial Operation</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__INITIAL_OPERATION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__INITIAL_OPERATION;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE;

    /**
     * The feature id for the '<em><b>Table Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION__TABLE_DESCRIPTION = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Table Creation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_CREATION_DESCRIPTION_FEATURE_COUNT = ToolPackage.REPRESENTATION_CREATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableNavigationDescriptionImpl
     * <em>Table Navigation Description</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableNavigationDescriptionImpl
     * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableNavigationDescription()
     * @generated
     */
    int TABLE_NAVIGATION_DESCRIPTION = 27;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__DOCUMENTATION = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__NAME = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__LABEL = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__LABEL;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__PRECONDITION = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__PRECONDITION;

    /**
     * The feature id for the '<em><b>Force Refresh</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__FORCE_REFRESH = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__FORCE_REFRESH;

    /**
     * The feature id for the '<em><b>Filters</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__FILTERS = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__FILTERS;

    /**
     * The feature id for the '<em><b>Elements To Select</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__ELEMENTS_TO_SELECT;

    /**
     * The feature id for the '<em><b>Inverse Selection Order</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__INVERSE_SELECTION_ORDER = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__INVERSE_SELECTION_ORDER;

    /**
     * The feature id for the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__BROWSE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Navigation Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__NAVIGATION_NAME_EXPRESSION;

    /**
     * The feature id for the '<em><b>Representation Description</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_DESCRIPTION;

    /**
     * The feature id for the '<em><b>Container View Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VIEW_VARIABLE;

    /**
     * The feature id for the '<em><b>Container Variable</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__CONTAINER_VARIABLE;

    /**
     * The feature id for the '<em><b>Representation Name Variable</b></em>'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION__REPRESENTATION_NAME_VARIABLE;

    /**
     * The feature id for the '<em><b>Table Description</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION__TABLE_DESCRIPTION = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '
     * <em>Table Navigation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_NAVIGATION_DESCRIPTION_FEATURE_COUNT = ToolPackage.REPRESENTATION_NAVIGATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription
     * <em>Table Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Table Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription
     * @generated
     */
    EClass getTableDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getPreconditionExpression()
     * @see #getTableDescription()
     * @generated
     */
    EAttribute getTableDescription_PreconditionExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getDomainClass()
     * @see #getTableDescription()
     * @generated
     */
    EAttribute getTableDescription_DomainClass();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedRepresentationCreationDescriptions
     * <em>Owned Representation Creation Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Representation Creation Descriptions</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedRepresentationCreationDescriptions()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_OwnedRepresentationCreationDescriptions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedRepresentationCreationDescriptions
     * <em>Reused Representation Creation Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '
     *         <em>Reused Representation Creation Descriptions</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedRepresentationCreationDescriptions()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_ReusedRepresentationCreationDescriptions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllRepresentationCreationDescriptions
     * <em>All Representation Creation Descriptions</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>All Representation Creation Descriptions</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllRepresentationCreationDescriptions()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_AllRepresentationCreationDescriptions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedRepresentationNavigationDescriptions
     * <em>Owned Representation Navigation Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Representation Navigation Descriptions</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedRepresentationNavigationDescriptions()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_OwnedRepresentationNavigationDescriptions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedRepresentationNavigationDescriptions
     * <em>Reused Representation Navigation Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '
     *         <em>Reused Representation Navigation Descriptions</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedRepresentationNavigationDescriptions()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_ReusedRepresentationNavigationDescriptions();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllRepresentationNavigationDescriptions
     * <em>All Representation Navigation Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '
     *         <em>All Representation Navigation Descriptions</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllRepresentationNavigationDescriptions()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_AllRepresentationNavigationDescriptions();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedLineMappings
     * <em>Owned Line Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Line Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedLineMappings()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_OwnedLineMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedLineMappings
     * <em>Reused Line Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Reused Line Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedLineMappings()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_ReusedLineMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllLineMappings
     * <em>All Line Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>All Line Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllLineMappings()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_AllLineMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedCreateLine
     * <em>Owned Create Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Owned Create Line</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getOwnedCreateLine()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_OwnedCreateLine();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedCreateLine
     * <em>Reused Create Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>Reused Create Line</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getReusedCreateLine()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_ReusedCreateLine();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllCreateLine
     * <em>All Create Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Create Line</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getAllCreateLine()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_AllCreateLine();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getInitialHeaderColumnWidth
     * <em>Initial Header Column Width</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Initial Header Column Width</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getInitialHeaderColumnWidth()
     * @see #getTableDescription()
     * @generated
     */
    EAttribute getTableDescription_InitialHeaderColumnWidth();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableDescription#getImportedElements
     * <em>Imported Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '
     *         <em>Imported Elements</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableDescription#getImportedElements()
     * @see #getTableDescription()
     * @generated
     */
    EReference getTableDescription_ImportedElements();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription
     * <em>Edition Table Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Edition Table Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription
     * @generated
     */
    EClass getEditionTableDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getOwnedColumnMappings
     * <em>Owned Column Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Column Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getOwnedColumnMappings()
     * @see #getEditionTableDescription()
     * @generated
     */
    EReference getEditionTableDescription_OwnedColumnMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getReusedColumnMappings
     * <em>Reused Column Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference list '
     *         <em>Reused Column Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getReusedColumnMappings()
     * @see #getEditionTableDescription()
     * @generated
     */
    EReference getEditionTableDescription_ReusedColumnMappings();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getAllColumnMappings
     * <em>All Column Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>All Column Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription#getAllColumnMappings()
     * @see #getEditionTableDescription()
     * @generated
     */
    EReference getEditionTableDescription_AllColumnMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription
     * <em>Cross Table Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Cross Table Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription
     * @generated
     */
    EClass getCrossTableDescription();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getOwnedColumnMappings
     * <em>Owned Column Mappings</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Column Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getOwnedColumnMappings()
     * @see #getCrossTableDescription()
     * @generated
     */
    EReference getCrossTableDescription_OwnedColumnMappings();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getIntersection
     * <em>Intersection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Intersection</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getIntersection()
     * @see #getCrossTableDescription()
     * @generated
     */
    EReference getCrossTableDescription_Intersection();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getCreateColumn
     * <em>Create Column</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Create Column</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription#getCreateColumn()
     * @see #getCrossTableDescription()
     * @generated
     */
    EReference getCrossTableDescription_CreateColumn();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableMapping
     * <em>Table Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Table Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableMapping
     * @generated
     */
    EClass getTableMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableMapping#getSemanticElements
     * <em>Semantic Elements</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Semantic Elements</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableMapping#getSemanticElements()
     * @see #getTableMapping()
     * @generated
     */
    EAttribute getTableMapping_SemanticElements();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping
     * <em>Line Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Line Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping
     * @generated
     */
    EClass getLineMapping();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getOwnedSubLines
     * <em>Owned Sub Lines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Owned Sub Lines</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getOwnedSubLines()
     * @see #getLineMapping()
     * @generated
     */
    EReference getLineMapping_OwnedSubLines();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedSubLines
     * <em>Reused Sub Lines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '<em>Reused Sub Lines</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedSubLines()
     * @see #getLineMapping()
     * @generated
     */
    EReference getLineMapping_ReusedSubLines();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDomainClass()
     * @see #getLineMapping()
     * @generated
     */
    EAttribute getLineMapping_DomainClass();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getCreate
     * <em>Create</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Create</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getCreate()
     * @see #getLineMapping()
     * @generated
     */
    EReference getLineMapping_Create();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDelete
     * <em>Delete</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Delete</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getDelete()
     * @see #getLineMapping()
     * @generated
     */
    EReference getLineMapping_Delete();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getSemanticCandidatesExpression()
     * @see #getLineMapping()
     * @generated
     */
    EAttribute getLineMapping_SemanticCandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getHeaderLabelExpression
     * <em>Header Label Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Header Label Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getHeaderLabelExpression()
     * @see #getLineMapping()
     * @generated
     */
    EAttribute getLineMapping_HeaderLabelExpression();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getAllSubLines
     * <em>All Sub Lines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>All Sub Lines</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getAllSubLines()
     * @see #getLineMapping()
     * @generated
     */
    EReference getLineMapping_AllSubLines();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedInMappings
     * <em>Reused In Mappings</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference list '
     *         <em>Reused In Mappings</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LineMapping#getReusedInMappings()
     * @see #getLineMapping()
     * @generated
     */
    EReference getLineMapping_ReusedInMappings();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping
     * <em>Column Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Column Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ColumnMapping
     * @generated
     */
    EClass getColumnMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getHeaderLabelExpression
     * <em>Header Label Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Header Label Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getHeaderLabelExpression()
     * @see #getColumnMapping()
     * @generated
     */
    EAttribute getColumnMapping_HeaderLabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getInitialWidth
     * <em>Initial Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Initial Width</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ColumnMapping#getInitialWidth()
     * @see #getColumnMapping()
     * @generated
     */
    EAttribute getColumnMapping_InitialWidth();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping
     * <em>Element Column Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Element Column Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping
     * @generated
     */
    EClass getElementColumnMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDomainClass()
     * @see #getElementColumnMapping()
     * @generated
     */
    EAttribute getElementColumnMapping_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getSemanticCandidatesExpression()
     * @see #getElementColumnMapping()
     * @generated
     */
    EAttribute getElementColumnMapping_SemanticCandidatesExpression();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getCreate
     * <em>Create</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Create</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getCreate()
     * @see #getElementColumnMapping()
     * @generated
     */
    EReference getElementColumnMapping_Create();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDelete
     * <em>Delete</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Delete</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ElementColumnMapping#getDelete()
     * @see #getElementColumnMapping()
     * @generated
     */
    EReference getElementColumnMapping_Delete();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping
     * <em>Feature Column Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Feature Column Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping
     * @generated
     */
    EClass getFeatureColumnMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureName()
     * @see #getFeatureColumnMapping()
     * @generated
     */
    EAttribute getFeatureColumnMapping_FeatureName();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getLabelExpression()
     * @see #getFeatureColumnMapping()
     * @generated
     */
    EAttribute getFeatureColumnMapping_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureParentExpression
     * <em>Feature Parent Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Feature Parent Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.FeatureColumnMapping#getFeatureParentExpression()
     * @see #getFeatureColumnMapping()
     * @generated
     */
    EAttribute getFeatureColumnMapping_FeatureParentExpression();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater
     * <em>Cell Updater</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Cell Updater</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CellUpdater
     * @generated
     */
    EClass getCellUpdater();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getDirectEdit
     * <em>Direct Edit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Direct Edit</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getDirectEdit()
     * @see #getCellUpdater()
     * @generated
     */
    EReference getCellUpdater_DirectEdit();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getCanEdit
     * <em>Can Edit</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Can Edit</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CellUpdater#getCanEdit()
     * @see #getCellUpdater()
     * @generated
     */
    EAttribute getCellUpdater_CanEdit();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater
     * <em>Style Updater</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Style Updater</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.StyleUpdater
     * @generated
     */
    EClass getStyleUpdater();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultForeground
     * <em>Default Foreground</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Default Foreground</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultForeground()
     * @see #getStyleUpdater()
     * @generated
     */
    EReference getStyleUpdater_DefaultForeground();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getForegroundConditionalStyle
     * <em>Foreground Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Foreground Conditional Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getForegroundConditionalStyle()
     * @see #getStyleUpdater()
     * @generated
     */
    EReference getStyleUpdater_ForegroundConditionalStyle();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultBackground
     * <em>Default Background</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Default Background</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getDefaultBackground()
     * @see #getStyleUpdater()
     * @generated
     */
    EReference getStyleUpdater_DefaultBackground();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getBackgroundConditionalStyle
     * <em>Background Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Background Conditional Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.StyleUpdater#getBackgroundConditionalStyle()
     * @see #getStyleUpdater()
     * @generated
     */
    EReference getStyleUpdater_BackgroundConditionalStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping
     * <em>Intersection Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Intersection Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping
     * @generated
     */
    EClass getIntersectionMapping();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineMapping
     * <em>Line Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Line Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineMapping()
     * @see #getIntersectionMapping()
     * @generated
     */
    EReference getIntersectionMapping_LineMapping();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnMapping
     * <em>Column Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Column Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnMapping()
     * @see #getIntersectionMapping()
     * @generated
     */
    EReference getIntersectionMapping_ColumnMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLabelExpression
     * <em>Label Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLabelExpression()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_LabelExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#isUseDomainClass
     * <em>Use Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Use Domain Class</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#isUseDomainClass()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_UseDomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnFinderExpression
     * <em>Column Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Column Finder Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getColumnFinderExpression()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_ColumnFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineFinderExpression
     * <em>Line Finder Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Line Finder Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getLineFinderExpression()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_LineFinderExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getSemanticCandidatesExpression
     * <em>Semantic Candidates Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getSemanticCandidatesExpression()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_SemanticCandidatesExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getDomainClass
     * <em>Domain Class</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Domain Class</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getDomainClass()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_DomainClass();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getPreconditionExpression
     * <em>Precondition Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Precondition Expression</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getPreconditionExpression()
     * @see #getIntersectionMapping()
     * @generated
     */
    EAttribute getIntersectionMapping_PreconditionExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getCreate
     * <em>Create</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Create</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.IntersectionMapping#getCreate()
     * @see #getIntersectionMapping()
     * @generated
     */
    EReference getIntersectionMapping_Create();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableTool
     * <em>Table Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Table Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableTool
     * @generated
     */
    EClass getTableTool();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableTool#getVariables
     * <em>Variables</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Variables</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableTool#getVariables()
     * @see #getTableTool()
     * @generated
     */
    EReference getTableTool_Variables();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableTool#getFirstModelOperation
     * <em>First Model Operation</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>First Model Operation</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableTool#getFirstModelOperation()
     * @see #getTableTool()
     * @generated
     */
    EReference getTableTool_FirstModelOperation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool
     * <em>Label Edit Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Label Edit Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LabelEditTool
     * @generated
     */
    EClass getLabelEditTool();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool#getMask
     * <em>Mask</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Mask</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.LabelEditTool#getMask()
     * @see #getLabelEditTool()
     * @generated
     */
    EReference getLabelEditTool_Mask();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateTool
     * <em>Create Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Create Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateTool
     * @generated
     */
    EClass getCreateTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool
     * <em>Create Column Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Create Column Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool
     * @generated
     */
    EClass getCreateColumnTool();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateColumnTool#getMapping()
     * @see #getCreateColumnTool()
     * @generated
     */
    EReference getCreateColumnTool_Mapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool
     * <em>Create Cross Column Tool</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Create Cross Column Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool
     * @generated
     */
    EClass getCreateCrossColumnTool();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCrossColumnTool#getMapping()
     * @see #getCreateCrossColumnTool()
     * @generated
     */
    EReference getCreateCrossColumnTool_Mapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool
     * <em>Create Line Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Create Line Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateLineTool
     * @generated
     */
    EClass getCreateLineTool();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateLineTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateLineTool#getMapping()
     * @see #getCreateLineTool()
     * @generated
     */
    EReference getCreateLineTool_Mapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     * <em>Create Cell Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Create Cell Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     * @generated
     */
    EClass getCreateCellTool();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMask
     * <em>Mask</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Mask</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMask()
     * @see #getCreateCellTool()
     * @generated
     */
    EReference getCreateCellTool_Mask();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.CreateCellTool#getMapping()
     * @see #getCreateCellTool()
     * @generated
     */
    EReference getCreateCellTool_Mapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteTool
     * <em>Delete Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Delete Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteTool
     * @generated
     */
    EClass getDeleteTool();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool
     * <em>Delete Column Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Delete Column Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool
     * @generated
     */
    EClass getDeleteColumnTool();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteColumnTool#getMapping()
     * @see #getDeleteColumnTool()
     * @generated
     */
    EReference getDeleteColumnTool_Mapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool
     * <em>Delete Line Tool</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Delete Line Tool</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool
     * @generated
     */
    EClass getDeleteLineTool();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool#getMapping
     * <em>Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.DeleteLineTool#getMapping()
     * @see #getDeleteLineTool()
     * @generated
     */
    EReference getDeleteLineTool_Mapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription
     * <em>Foreground Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Foreground Style Description</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription
     * @generated
     */
    EClass getForegroundStyleDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelSize()
     * @see #getForegroundStyleDescription()
     * @generated
     */
    EAttribute getForegroundStyleDescription_LabelSize();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Label Format</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getLabelFormat()
     * @see #getForegroundStyleDescription()
     * @generated
     */
    EAttribute getForegroundStyleDescription_LabelFormat();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getForeGroundColor
     * <em>Fore Ground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Fore Ground Color</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundStyleDescription#getForeGroundColor()
     * @see #getForegroundStyleDescription()
     * @generated
     */
    EReference getForegroundStyleDescription_ForeGroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription
     * <em>Background Style Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Background Style Description</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription
     * @generated
     */
    EClass getBackgroundStyleDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Background Color</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundStyleDescription#getBackgroundColor()
     * @see #getBackgroundStyleDescription()
     * @generated
     */
    EReference getBackgroundStyleDescription_BackgroundColor();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle
     * <em>Foreground Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Foreground Conditional Style</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle
     * @generated
     */
    EClass getForegroundConditionalStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle#getPredicateExpression()
     * @see #getForegroundConditionalStyle()
     * @generated
     */
    EAttribute getForegroundConditionalStyle_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.ForegroundConditionalStyle#getStyle()
     * @see #getForegroundConditionalStyle()
     * @generated
     */
    EReference getForegroundConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle
     * <em>Background Conditional Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Background Conditional Style</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle
     * @generated
     */
    EClass getBackgroundConditionalStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle#getPredicateExpression
     * <em>Predicate Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Predicate Expression</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle#getPredicateExpression()
     * @see #getBackgroundConditionalStyle()
     * @generated
     */
    EAttribute getBackgroundConditionalStyle_PredicateExpression();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle#getStyle
     * <em>Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.BackgroundConditionalStyle#getStyle()
     * @see #getBackgroundConditionalStyle()
     * @generated
     */
    EReference getBackgroundConditionalStyle_Style();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableVariable
     * <em>Table Variable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Table Variable</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableVariable
     * @generated
     */
    EClass getTableVariable();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableVariable#getDocumentation
     * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Documentation</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableVariable#getDocumentation()
     * @see #getTableVariable()
     * @generated
     */
    EAttribute getTableVariable_Documentation();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription
     * <em>Table Creation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Table Creation Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription
     * @generated
     */
    EClass getTableCreationDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription#getTableDescription
     * <em>Table Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Table Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableCreationDescription#getTableDescription()
     * @see #getTableCreationDescription()
     * @generated
     */
    EReference getTableCreationDescription_TableDescription();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription
     * <em>Table Navigation Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Table Navigation Description</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription
     * @generated
     */
    EClass getTableNavigationDescription();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription#getTableDescription
     * <em>Table Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the reference '<em>Table Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.description.TableNavigationDescription#getTableDescription()
     * @see #getTableNavigationDescription()
     * @generated
     */
    EReference getTableNavigationDescription_TableDescription();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DescriptionFactory getDescriptionFactory();

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
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl
         * <em>Table Description</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableDescription()
         * @generated
         */
        EClass TABLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getTableDescription();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TABLE_DESCRIPTION__PRECONDITION_EXPRESSION = DescriptionPackage.eINSTANCE.getTableDescription_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABLE_DESCRIPTION__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getTableDescription_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representation Creation Descriptions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__OWNED_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getTableDescription_OwnedRepresentationCreationDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>Reused Representation Creation Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__REUSED_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getTableDescription_ReusedRepresentationCreationDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>All Representation Creation Descriptions</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__ALL_REPRESENTATION_CREATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getTableDescription_AllRepresentationCreationDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>Owned Representation Navigation Descriptions</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__OWNED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getTableDescription_OwnedRepresentationNavigationDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>Reused Representation Navigation Descriptions</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__REUSED_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getTableDescription_ReusedRepresentationNavigationDescriptions();

        /**
         * The meta object literal for the '
         * <em><b>All Representation Navigation Descriptions</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__ALL_REPRESENTATION_NAVIGATION_DESCRIPTIONS = DescriptionPackage.eINSTANCE.getTableDescription_AllRepresentationNavigationDescriptions();

        /**
         * The meta object literal for the '<em><b>Owned Line Mappings</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__OWNED_LINE_MAPPINGS = DescriptionPackage.eINSTANCE.getTableDescription_OwnedLineMappings();

        /**
         * The meta object literal for the '<em><b>Reused Line Mappings</b></em>
         * ' reference list feature. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @generated
         */
        EReference TABLE_DESCRIPTION__REUSED_LINE_MAPPINGS = DescriptionPackage.eINSTANCE.getTableDescription_ReusedLineMappings();

        /**
         * The meta object literal for the '<em><b>All Line Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__ALL_LINE_MAPPINGS = DescriptionPackage.eINSTANCE.getTableDescription_AllLineMappings();

        /**
         * The meta object literal for the '<em><b>Owned Create Line</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__OWNED_CREATE_LINE = DescriptionPackage.eINSTANCE.getTableDescription_OwnedCreateLine();

        /**
         * The meta object literal for the '<em><b>Reused Create Line</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__REUSED_CREATE_LINE = DescriptionPackage.eINSTANCE.getTableDescription_ReusedCreateLine();

        /**
         * The meta object literal for the '<em><b>All Create Line</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__ALL_CREATE_LINE = DescriptionPackage.eINSTANCE.getTableDescription_AllCreateLine();

        /**
         * The meta object literal for the '
         * <em><b>Initial Header Column Width</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute TABLE_DESCRIPTION__INITIAL_HEADER_COLUMN_WIDTH = DescriptionPackage.eINSTANCE.getTableDescription_InitialHeaderColumnWidth();

        /**
         * The meta object literal for the '<em><b>Imported Elements</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_DESCRIPTION__IMPORTED_ELEMENTS = DescriptionPackage.eINSTANCE.getTableDescription_ImportedElements();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl
         * <em>Edition Table Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.EditionTableDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getEditionTableDescription()
         * @generated
         */
        EClass EDITION_TABLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getEditionTableDescription();

        /**
         * The meta object literal for the '
         * <em><b>Owned Column Mappings</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDITION_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS = DescriptionPackage.eINSTANCE.getEditionTableDescription_OwnedColumnMappings();

        /**
         * The meta object literal for the '
         * <em><b>Reused Column Mappings</b></em>' reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDITION_TABLE_DESCRIPTION__REUSED_COLUMN_MAPPINGS = DescriptionPackage.eINSTANCE.getEditionTableDescription_ReusedColumnMappings();

        /**
         * The meta object literal for the '<em><b>All Column Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference EDITION_TABLE_DESCRIPTION__ALL_COLUMN_MAPPINGS = DescriptionPackage.eINSTANCE.getEditionTableDescription_AllColumnMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl
         * <em>Cross Table Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CrossTableDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCrossTableDescription()
         * @generated
         */
        EClass CROSS_TABLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getCrossTableDescription();

        /**
         * The meta object literal for the '
         * <em><b>Owned Column Mappings</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CROSS_TABLE_DESCRIPTION__OWNED_COLUMN_MAPPINGS = DescriptionPackage.eINSTANCE.getCrossTableDescription_OwnedColumnMappings();

        /**
         * The meta object literal for the '<em><b>Intersection</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CROSS_TABLE_DESCRIPTION__INTERSECTION = DescriptionPackage.eINSTANCE.getCrossTableDescription_Intersection();

        /**
         * The meta object literal for the '<em><b>Create Column</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CROSS_TABLE_DESCRIPTION__CREATE_COLUMN = DescriptionPackage.eINSTANCE.getCrossTableDescription_CreateColumn();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableMappingImpl
         * <em>Table Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableMappingImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableMapping()
         * @generated
         */
        EClass TABLE_MAPPING = DescriptionPackage.eINSTANCE.getTableMapping();

        /**
         * The meta object literal for the '<em><b>Semantic Elements</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABLE_MAPPING__SEMANTIC_ELEMENTS = DescriptionPackage.eINSTANCE.getTableMapping_SemanticElements();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl
         * <em>Line Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.LineMappingImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getLineMapping()
         * @generated
         */
        EClass LINE_MAPPING = DescriptionPackage.eINSTANCE.getLineMapping();

        /**
         * The meta object literal for the '<em><b>Owned Sub Lines</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_MAPPING__OWNED_SUB_LINES = DescriptionPackage.eINSTANCE.getLineMapping_OwnedSubLines();

        /**
         * The meta object literal for the '<em><b>Reused Sub Lines</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_MAPPING__REUSED_SUB_LINES = DescriptionPackage.eINSTANCE.getLineMapping_ReusedSubLines();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LINE_MAPPING__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getLineMapping_DomainClass();

        /**
         * The meta object literal for the '<em><b>Create</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_MAPPING__CREATE = DescriptionPackage.eINSTANCE.getLineMapping_Create();

        /**
         * The meta object literal for the '<em><b>Delete</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_MAPPING__DELETE = DescriptionPackage.eINSTANCE.getLineMapping_Delete();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidates Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LINE_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.eINSTANCE.getLineMapping_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '
         * <em><b>Header Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute LINE_MAPPING__HEADER_LABEL_EXPRESSION = DescriptionPackage.eINSTANCE.getLineMapping_HeaderLabelExpression();

        /**
         * The meta object literal for the '<em><b>All Sub Lines</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_MAPPING__ALL_SUB_LINES = DescriptionPackage.eINSTANCE.getLineMapping_AllSubLines();

        /**
         * The meta object literal for the '<em><b>Reused In Mappings</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_MAPPING__REUSED_IN_MAPPINGS = DescriptionPackage.eINSTANCE.getLineMapping_ReusedInMappings();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ColumnMappingImpl
         * <em>Column Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.ColumnMappingImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getColumnMapping()
         * @generated
         */
        EClass COLUMN_MAPPING = DescriptionPackage.eINSTANCE.getColumnMapping();

        /**
         * The meta object literal for the '
         * <em><b>Header Label Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute COLUMN_MAPPING__HEADER_LABEL_EXPRESSION = DescriptionPackage.eINSTANCE.getColumnMapping_HeaderLabelExpression();

        /**
         * The meta object literal for the '<em><b>Initial Width</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COLUMN_MAPPING__INITIAL_WIDTH = DescriptionPackage.eINSTANCE.getColumnMapping_InitialWidth();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl
         * <em>Element Column Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.ElementColumnMappingImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getElementColumnMapping()
         * @generated
         */
        EClass ELEMENT_COLUMN_MAPPING = DescriptionPackage.eINSTANCE.getElementColumnMapping();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELEMENT_COLUMN_MAPPING__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getElementColumnMapping_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidates Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ELEMENT_COLUMN_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.eINSTANCE.getElementColumnMapping_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Create</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ELEMENT_COLUMN_MAPPING__CREATE = DescriptionPackage.eINSTANCE.getElementColumnMapping_Create();

        /**
         * The meta object literal for the '<em><b>Delete</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ELEMENT_COLUMN_MAPPING__DELETE = DescriptionPackage.eINSTANCE.getElementColumnMapping_Delete();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl
         * <em>Feature Column Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.FeatureColumnMappingImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getFeatureColumnMapping()
         * @generated
         */
        EClass FEATURE_COLUMN_MAPPING = DescriptionPackage.eINSTANCE.getFeatureColumnMapping();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FEATURE_COLUMN_MAPPING__FEATURE_NAME = DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureName();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FEATURE_COLUMN_MAPPING__LABEL_EXPRESSION = DescriptionPackage.eINSTANCE.getFeatureColumnMapping_LabelExpression();

        /**
         * The meta object literal for the '
         * <em><b>Feature Parent Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute FEATURE_COLUMN_MAPPING__FEATURE_PARENT_EXPRESSION = DescriptionPackage.eINSTANCE.getFeatureColumnMapping_FeatureParentExpression();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl
         * <em>Cell Updater</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CellUpdaterImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCellUpdater()
         * @generated
         */
        EClass CELL_UPDATER = DescriptionPackage.eINSTANCE.getCellUpdater();

        /**
         * The meta object literal for the '<em><b>Direct Edit</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference CELL_UPDATER__DIRECT_EDIT = DescriptionPackage.eINSTANCE.getCellUpdater_DirectEdit();

        /**
         * The meta object literal for the '<em><b>Can Edit</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CELL_UPDATER__CAN_EDIT = DescriptionPackage.eINSTANCE.getCellUpdater_CanEdit();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl
         * <em>Style Updater</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.StyleUpdaterImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getStyleUpdater()
         * @generated
         */
        EClass STYLE_UPDATER = DescriptionPackage.eINSTANCE.getStyleUpdater();

        /**
         * The meta object literal for the '<em><b>Default Foreground</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference STYLE_UPDATER__DEFAULT_FOREGROUND = DescriptionPackage.eINSTANCE.getStyleUpdater_DefaultForeground();

        /**
         * The meta object literal for the '
         * <em><b>Foreground Conditional Style</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference STYLE_UPDATER__FOREGROUND_CONDITIONAL_STYLE = DescriptionPackage.eINSTANCE.getStyleUpdater_ForegroundConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Default Background</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference STYLE_UPDATER__DEFAULT_BACKGROUND = DescriptionPackage.eINSTANCE.getStyleUpdater_DefaultBackground();

        /**
         * The meta object literal for the '
         * <em><b>Background Conditional Style</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference STYLE_UPDATER__BACKGROUND_CONDITIONAL_STYLE = DescriptionPackage.eINSTANCE.getStyleUpdater_BackgroundConditionalStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl
         * <em>Intersection Mapping</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.IntersectionMappingImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getIntersectionMapping()
         * @generated
         */
        EClass INTERSECTION_MAPPING = DescriptionPackage.eINSTANCE.getIntersectionMapping();

        /**
         * The meta object literal for the '<em><b>Line Mapping</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INTERSECTION_MAPPING__LINE_MAPPING = DescriptionPackage.eINSTANCE.getIntersectionMapping_LineMapping();

        /**
         * The meta object literal for the '<em><b>Column Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INTERSECTION_MAPPING__COLUMN_MAPPING = DescriptionPackage.eINSTANCE.getIntersectionMapping_ColumnMapping();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__LABEL_EXPRESSION = DescriptionPackage.eINSTANCE.getIntersectionMapping_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Use Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__USE_DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getIntersectionMapping_UseDomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Column Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__COLUMN_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getIntersectionMapping_ColumnFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Line Finder Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__LINE_FINDER_EXPRESSION = DescriptionPackage.eINSTANCE.getIntersectionMapping_LineFinderExpression();

        /**
         * The meta object literal for the '
         * <em><b>Semantic Candidates Expression</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__SEMANTIC_CANDIDATES_EXPRESSION = DescriptionPackage.eINSTANCE.getIntersectionMapping_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Domain Class</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__DOMAIN_CLASS = DescriptionPackage.eINSTANCE.getIntersectionMapping_DomainClass();

        /**
         * The meta object literal for the '
         * <em><b>Precondition Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute INTERSECTION_MAPPING__PRECONDITION_EXPRESSION = DescriptionPackage.eINSTANCE.getIntersectionMapping_PreconditionExpression();

        /**
         * The meta object literal for the '<em><b>Create</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference INTERSECTION_MAPPING__CREATE = DescriptionPackage.eINSTANCE.getIntersectionMapping_Create();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableToolImpl
         * <em>Table Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableTool()
         * @generated
         */
        EClass TABLE_TOOL = DescriptionPackage.eINSTANCE.getTableTool();

        /**
         * The meta object literal for the '<em><b>Variables</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_TOOL__VARIABLES = DescriptionPackage.eINSTANCE.getTableTool_Variables();

        /**
         * The meta object literal for the '
         * <em><b>First Model Operation</b></em>' containment reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_TOOL__FIRST_MODEL_OPERATION = DescriptionPackage.eINSTANCE.getTableTool_FirstModelOperation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.LabelEditToolImpl
         * <em>Label Edit Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.LabelEditToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getLabelEditTool()
         * @generated
         */
        EClass LABEL_EDIT_TOOL = DescriptionPackage.eINSTANCE.getLabelEditTool();

        /**
         * The meta object literal for the '<em><b>Mask</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LABEL_EDIT_TOOL__MASK = DescriptionPackage.eINSTANCE.getLabelEditTool_Mask();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateToolImpl
         * <em>Create Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateTool()
         * @generated
         */
        EClass CREATE_TOOL = DescriptionPackage.eINSTANCE.getCreateTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateColumnToolImpl
         * <em>Create Column Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateColumnToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateColumnTool()
         * @generated
         */
        EClass CREATE_COLUMN_TOOL = DescriptionPackage.eINSTANCE.getCreateColumnTool();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CREATE_COLUMN_TOOL__MAPPING = DescriptionPackage.eINSTANCE.getCreateColumnTool_Mapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCrossColumnToolImpl
         * <em>Create Cross Column Tool</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateCrossColumnToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateCrossColumnTool()
         * @generated
         */
        EClass CREATE_CROSS_COLUMN_TOOL = DescriptionPackage.eINSTANCE.getCreateCrossColumnTool();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CREATE_CROSS_COLUMN_TOOL__MAPPING = DescriptionPackage.eINSTANCE.getCreateCrossColumnTool_Mapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateLineToolImpl
         * <em>Create Line Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateLineToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateLineTool()
         * @generated
         */
        EClass CREATE_LINE_TOOL = DescriptionPackage.eINSTANCE.getCreateLineTool();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CREATE_LINE_TOOL__MAPPING = DescriptionPackage.eINSTANCE.getCreateLineTool_Mapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl
         * <em>Create Cell Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.CreateCellToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getCreateCellTool()
         * @generated
         */
        EClass CREATE_CELL_TOOL = DescriptionPackage.eINSTANCE.getCreateCellTool();

        /**
         * The meta object literal for the '<em><b>Mask</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CREATE_CELL_TOOL__MASK = DescriptionPackage.eINSTANCE.getCreateCellTool_Mask();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CREATE_CELL_TOOL__MAPPING = DescriptionPackage.eINSTANCE.getCreateCellTool_Mapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.DeleteToolImpl
         * <em>Delete Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DeleteToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getDeleteTool()
         * @generated
         */
        EClass DELETE_TOOL = DescriptionPackage.eINSTANCE.getDeleteTool();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.DeleteColumnToolImpl
         * <em>Delete Column Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DeleteColumnToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getDeleteColumnTool()
         * @generated
         */
        EClass DELETE_COLUMN_TOOL = DescriptionPackage.eINSTANCE.getDeleteColumnTool();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_COLUMN_TOOL__MAPPING = DescriptionPackage.eINSTANCE.getDeleteColumnTool_Mapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.DeleteLineToolImpl
         * <em>Delete Line Tool</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DeleteLineToolImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getDeleteLineTool()
         * @generated
         */
        EClass DELETE_LINE_TOOL = DescriptionPackage.eINSTANCE.getDeleteLineTool();

        /**
         * The meta object literal for the '<em><b>Mapping</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DELETE_LINE_TOOL__MAPPING = DescriptionPackage.eINSTANCE.getDeleteLineTool_Mapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl
         * <em>Foreground Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundStyleDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getForegroundStyleDescription()
         * @generated
         */
        EClass FOREGROUND_STYLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getForegroundStyleDescription();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOREGROUND_STYLE_DESCRIPTION__LABEL_SIZE = DescriptionPackage.eINSTANCE.getForegroundStyleDescription_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOREGROUND_STYLE_DESCRIPTION__LABEL_FORMAT = DescriptionPackage.eINSTANCE.getForegroundStyleDescription_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Fore Ground Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FOREGROUND_STYLE_DESCRIPTION__FORE_GROUND_COLOR = DescriptionPackage.eINSTANCE.getForegroundStyleDescription_ForeGroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundStyleDescriptionImpl
         * <em>Background Style Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundStyleDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getBackgroundStyleDescription()
         * @generated
         */
        EClass BACKGROUND_STYLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getBackgroundStyleDescription();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BACKGROUND_STYLE_DESCRIPTION__BACKGROUND_COLOR = DescriptionPackage.eINSTANCE.getBackgroundStyleDescription_BackgroundColor();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundConditionalStyleImpl
         * <em>Foreground Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.ForegroundConditionalStyleImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getForegroundConditionalStyle()
         * @generated
         */
        EClass FOREGROUND_CONDITIONAL_STYLE = DescriptionPackage.eINSTANCE.getForegroundConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute FOREGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION = DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference FOREGROUND_CONDITIONAL_STYLE__STYLE = DescriptionPackage.eINSTANCE.getForegroundConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundConditionalStyleImpl
         * <em>Background Conditional Style</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.BackgroundConditionalStyleImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getBackgroundConditionalStyle()
         * @generated
         */
        EClass BACKGROUND_CONDITIONAL_STYLE = DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle();

        /**
         * The meta object literal for the '<em><b>Predicate Expression</b></em>
         * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BACKGROUND_CONDITIONAL_STYLE__PREDICATE_EXPRESSION = DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_PredicateExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BACKGROUND_CONDITIONAL_STYLE__STYLE = DescriptionPackage.eINSTANCE.getBackgroundConditionalStyle_Style();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableVariableImpl
         * <em>Table Variable</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableVariableImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableVariable()
         * @generated
         */
        EClass TABLE_VARIABLE = DescriptionPackage.eINSTANCE.getTableVariable();

        /**
         * The meta object literal for the '<em><b>Documentation</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABLE_VARIABLE__DOCUMENTATION = DescriptionPackage.eINSTANCE.getTableVariable_Documentation();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableCreationDescriptionImpl
         * <em>Table Creation Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableCreationDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableCreationDescription()
         * @generated
         */
        EClass TABLE_CREATION_DESCRIPTION = DescriptionPackage.eINSTANCE.getTableCreationDescription();

        /**
         * The meta object literal for the '<em><b>Table Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_CREATION_DESCRIPTION__TABLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getTableCreationDescription_TableDescription();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.description.impl.TableNavigationDescriptionImpl
         * <em>Table Navigation Description</em>}' class. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.TableNavigationDescriptionImpl
         * @see org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionPackageImpl#getTableNavigationDescription()
         * @generated
         */
        EClass TABLE_NAVIGATION_DESCRIPTION = DescriptionPackage.eINSTANCE.getTableNavigationDescription();

        /**
         * The meta object literal for the '<em><b>Table Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE_NAVIGATION_DESCRIPTION__TABLE_DESCRIPTION = DescriptionPackage.eINSTANCE.getTableNavigationDescription_TableDescription();

    }

} // DescriptionPackage
