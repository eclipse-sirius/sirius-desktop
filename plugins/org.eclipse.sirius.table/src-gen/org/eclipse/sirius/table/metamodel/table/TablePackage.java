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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

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
 * @see org.eclipse.sirius.table.metamodel.table.TableFactory
 * @model kind="package"
 * @generated
 */
public interface TablePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "table"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/table/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "table"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    TablePackage eINSTANCE = org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl
     * <em>DTable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTableImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTable()
     * @generated
     */
    int DTABLE = 0;

    /**
     * The feature id for the '<em><b>Documentation</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE__DOCUMENTATION = ViewpointPackage.DREPRESENTATION__DOCUMENTATION;

    /**
     * The feature id for the '<em><b>EAnnotations</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__EANNOTATIONS = ViewpointPackage.DREPRESENTATION__EANNOTATIONS;

    /**
     * The feature id for the '<em><b>Owned Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Representation Elements</b></em>'
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE__NAME = ViewpointPackage.DREPRESENTATION__NAME;

    /**
     * The feature id for the '<em><b>Owned Annotation Entries</b></em>'
     * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES;

    /**
     * The feature id for the '<em><b>Ui State</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__UI_STATE = ViewpointPackage.DREPRESENTATION__UI_STATE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE__TARGET = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Lines</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__LINES = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Columns</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__COLUMNS = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Header Column Width</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE__HEADER_COLUMN_WIDTH = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Description</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE__DESCRIPTION = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>DTable</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementImpl
     * <em>DTable Element</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTableElement()
     * @generated
     */
    int DTABLE_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT__TARGET = ViewpointPackage.DREPRESENTATION_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT__NAME = ViewpointPackage.DREPRESENTATION_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT__SEMANTIC_ELEMENTS = ViewpointPackage.DREPRESENTATION_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Table Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DTable Element</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.LineContainerImpl
     * <em>Line Container</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.LineContainerImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getLineContainer()
     * @generated
     */
    int LINE_CONTAINER = 2;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LINE_CONTAINER__TARGET = ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;

    /**
     * The feature id for the '<em><b>Lines</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_CONTAINER__LINES = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Line Container</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINE_CONTAINER_FEATURE_COUNT = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl
     * <em>DLine</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DLineImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDLine()
     * @generated
     */
    int DLINE = 3;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE__TARGET = TablePackage.LINE_CONTAINER__TARGET;

    /**
     * The feature id for the '<em><b>Lines</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__LINES = TablePackage.LINE_CONTAINER__LINES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE__NAME = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__SEMANTIC_ELEMENTS = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Table Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__TABLE_ELEMENT_MAPPING = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE__LABEL = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Origin Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE__ORIGIN_MAPPING = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE__VISIBLE = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Collapsed</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE__COLLAPSED = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Cells</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__CELLS = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Container</b></em>' container reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__CONTAINER = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Ordered Cells</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__ORDERED_CELLS = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Current Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DLINE__CURRENT_STYLE = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>DLine</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DLINE_FEATURE_COUNT = TablePackage.LINE_CONTAINER_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl
     * <em>DCell</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DCellImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDCell()
     * @generated
     */
    int DCELL = 4;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL__TARGET = ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL__NAME = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL__SEMANTIC_ELEMENTS = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Table Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL__TABLE_ELEMENT_MAPPING = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL__LABEL = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Line</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL__LINE = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Column</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL__COLUMN = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Current Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL__CURRENT_STYLE = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Updater</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL__UPDATER = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Intersection Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL__INTERSECTION_MAPPING = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>DCell</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL_FEATURE_COUNT = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 9;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl
     * <em>DTable Element Style</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTableElementStyle()
     * @generated
     */
    int DTABLE_ELEMENT_STYLE = 10;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE__LABEL_SIZE = 0;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE__LABEL_FORMAT = 1;

    /**
     * The feature id for the '<em><b>Default Foreground Style</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE = 2;

    /**
     * The feature id for the '<em><b>Default Background Style</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE = 3;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR = 4;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR = 5;

    /**
     * The number of structural features of the '<em>DTable Element Style</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_STYLE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellStyleImpl
     * <em>DCell Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.eclipse.sirius.table.metamodel.table.impl.DCellStyleImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDCellStyle()
     * @generated
     */
    int DCELL_STYLE = 5;

    /**
     * The feature id for the '<em><b>Label Size</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL_STYLE__LABEL_SIZE = TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE;

    /**
     * The feature id for the '<em><b>Label Format</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL_STYLE__LABEL_FORMAT = TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT;

    /**
     * The feature id for the '<em><b>Default Foreground Style</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL_STYLE__DEFAULT_FOREGROUND_STYLE = TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE;

    /**
     * The feature id for the '<em><b>Default Background Style</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL_STYLE__DEFAULT_BACKGROUND_STYLE = TablePackage.DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE;

    /**
     * The feature id for the '<em><b>Foreground Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL_STYLE__FOREGROUND_COLOR = TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Background Color</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCELL_STYLE__BACKGROUND_COLOR = TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR;

    /**
     * The feature id for the '<em><b>Foreground Style Origin</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL_STYLE__FOREGROUND_STYLE_ORIGIN = TablePackage.DTABLE_ELEMENT_STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Background Style Origin</b></em>'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL_STYLE__BACKGROUND_STYLE_ORIGIN = TablePackage.DTABLE_ELEMENT_STYLE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>DCell Style</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCELL_STYLE_FEATURE_COUNT = TablePackage.DTABLE_ELEMENT_STYLE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl
     * <em>DColumn</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDColumn()
     * @generated
     */
    int DCOLUMN = 6;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__TARGET = TablePackage.DTABLE_ELEMENT__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__NAME = TablePackage.DTABLE_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCOLUMN__SEMANTIC_ELEMENTS = TablePackage.DTABLE_ELEMENT__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Table Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCOLUMN__TABLE_ELEMENT_MAPPING = TablePackage.DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__LABEL = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Cells</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__CELLS = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Origin Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__ORIGIN_MAPPING = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Table</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__TABLE = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Ordered Cells</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCOLUMN__ORDERED_CELLS = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__VISIBLE = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN__WIDTH = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Current Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DCOLUMN__CURRENT_STYLE = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>DColumn</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DCOLUMN_FEATURE_COUNT = TablePackage.DTABLE_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl
     * <em>DTarget Column</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTargetColumn()
     * @generated
     */
    int DTARGET_COLUMN = 7;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__TARGET = ViewpointPackage.DSEMANTIC_DECORATOR__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__NAME = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__SEMANTIC_ELEMENTS = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Table Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__TABLE_ELEMENT_MAPPING = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__LABEL = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Cells</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__CELLS = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Origin Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__ORIGIN_MAPPING = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Table</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__TABLE = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Ordered Cells</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__ORDERED_CELLS = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__VISIBLE = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__WIDTH = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Current Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN__CURRENT_STYLE = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 10;

    /**
     * The number of structural features of the '<em>DTarget Column</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTARGET_COLUMN_FEATURE_COUNT = ViewpointPackage.DSEMANTIC_DECORATOR_FEATURE_COUNT + 11;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DFeatureColumnImpl
     * <em>DFeature Column</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DFeatureColumnImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDFeatureColumn()
     * @generated
     */
    int DFEATURE_COLUMN = 8;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__TARGET = TablePackage.DCOLUMN__TARGET;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__NAME = TablePackage.DCOLUMN__NAME;

    /**
     * The feature id for the '<em><b>Semantic Elements</b></em>' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__SEMANTIC_ELEMENTS = TablePackage.DCOLUMN__SEMANTIC_ELEMENTS;

    /**
     * The feature id for the '<em><b>Table Element Mapping</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__TABLE_ELEMENT_MAPPING = TablePackage.DCOLUMN__TABLE_ELEMENT_MAPPING;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__LABEL = TablePackage.DCOLUMN__LABEL;

    /**
     * The feature id for the '<em><b>Cells</b></em>' reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__CELLS = TablePackage.DCOLUMN__CELLS;

    /**
     * The feature id for the '<em><b>Origin Mapping</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__ORIGIN_MAPPING = TablePackage.DCOLUMN__ORIGIN_MAPPING;

    /**
     * The feature id for the '<em><b>Table</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__TABLE = TablePackage.DCOLUMN__TABLE;

    /**
     * The feature id for the '<em><b>Ordered Cells</b></em>' reference list.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__ORDERED_CELLS = TablePackage.DCOLUMN__ORDERED_CELLS;

    /**
     * The feature id for the '<em><b>Visible</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__VISIBLE = TablePackage.DCOLUMN__VISIBLE;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__WIDTH = TablePackage.DCOLUMN__WIDTH;

    /**
     * The feature id for the '<em><b>Current Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__CURRENT_STYLE = TablePackage.DCOLUMN__CURRENT_STYLE;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN__FEATURE_NAME = TablePackage.DCOLUMN_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>DFeature Column</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DFEATURE_COLUMN_FEATURE_COUNT = TablePackage.DCOLUMN_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementSynchronizerImpl
     * <em>DTable Element Synchronizer</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementSynchronizerImpl
     * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTableElementSynchronizer()
     * @generated
     */
    int DTABLE_ELEMENT_SYNCHRONIZER = 9;

    /**
     * The number of structural features of the '
     * <em>DTable Element Synchronizer</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DTABLE_ELEMENT_SYNCHRONIZER_FEATURE_COUNT = 0;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DTable <em>DTable</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DTable</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTable
     * @generated
     */
    EClass getDTable();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.DTable#getColumns
     * <em>Columns</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Columns</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTable#getColumns()
     * @see #getDTable()
     * @generated
     */
    EReference getDTable_Columns();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DTable#getDescription
     * <em>Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Description</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTable#getDescription()
     * @see #getDTable()
     * @generated
     */
    EReference getDTable_Description();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DTable#getHeaderColumnWidth
     * <em>Header Column Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Header Column Width</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTable#getHeaderColumnWidth()
     * @see #getDTable()
     * @generated
     */
    EAttribute getDTable_HeaderColumnWidth();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElement
     * <em>DTable Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DTable Element</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElement
     * @generated
     */
    EClass getDTableElement();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElement#getTableElementMapping
     * <em>Table Element Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Table Element Mapping</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElement#getTableElementMapping()
     * @see #getDTableElement()
     * @generated
     */
    EReference getDTableElement_TableElementMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.LineContainer
     * <em>Line Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Line Container</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.LineContainer
     * @generated
     */
    EClass getLineContainer();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.LineContainer#getLines
     * <em>Lines</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Lines</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.LineContainer#getLines()
     * @see #getLineContainer()
     * @generated
     */
    EReference getLineContainer_Lines();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine <em>DLine</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DLine</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine
     * @generated
     */
    EClass getDLine();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getLabel()
     * @see #getDLine()
     * @generated
     */
    EAttribute getDLine_Label();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#getOriginMapping
     * <em>Origin Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Origin Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getOriginMapping()
     * @see #getDLine()
     * @generated
     */
    EReference getDLine_OriginMapping();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#isVisible
     * <em>Visible</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#isVisible()
     * @see #getDLine()
     * @generated
     */
    EAttribute getDLine_Visible();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#isCollapsed
     * <em>Collapsed</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Collapsed</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#isCollapsed()
     * @see #getDLine()
     * @generated
     */
    EAttribute getDLine_Collapsed();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#getCells
     * <em>Cells</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Cells</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getCells()
     * @see #getDLine()
     * @generated
     */
    EReference getDLine_Cells();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#getContainer
     * <em>Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Container</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getContainer()
     * @see #getDLine()
     * @generated
     */
    EReference getDLine_Container();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#getOrderedCells
     * <em>Ordered Cells</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Ordered Cells</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getOrderedCells()
     * @see #getDLine()
     * @generated
     */
    EReference getDLine_OrderedCells();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DLine#getCurrentStyle
     * <em>Current Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Current Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DLine#getCurrentStyle()
     * @see #getDLine()
     * @generated
     */
    EReference getDLine_CurrentStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell <em>DCell</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DCell</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCell
     * @generated
     */
    EClass getDCell();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getLabel()
     * @see #getDCell()
     * @generated
     */
    EAttribute getDCell_Label();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getLine
     * <em>Line</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Line</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getLine()
     * @see #getDCell()
     * @generated
     */
    EReference getDCell_Line();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getColumn
     * <em>Column</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Column</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getColumn()
     * @see #getDCell()
     * @generated
     */
    EReference getDCell_Column();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getCurrentStyle
     * <em>Current Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Current Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getCurrentStyle()
     * @see #getDCell()
     * @generated
     */
    EReference getDCell_CurrentStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getUpdater
     * <em>Updater</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Updater</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getUpdater()
     * @see #getDCell()
     * @generated
     */
    EReference getDCell_Updater();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCell#getIntersectionMapping
     * <em>Intersection Mapping</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Intersection Mapping</em>
     *         '.
     * @see org.eclipse.sirius.table.metamodel.table.DCell#getIntersectionMapping()
     * @see #getDCell()
     * @generated
     */
    EReference getDCell_IntersectionMapping();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DCellStyle
     * <em>DCell Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DCell Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCellStyle
     * @generated
     */
    EClass getDCellStyle();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCellStyle#getForegroundStyleOrigin
     * <em>Foreground Style Origin</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Foreground Style Origin</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCellStyle#getForegroundStyleOrigin()
     * @see #getDCellStyle()
     * @generated
     */
    EReference getDCellStyle_ForegroundStyleOrigin();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DCellStyle#getBackgroundStyleOrigin
     * <em>Background Style Origin</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the reference '
     *         <em>Background Style Origin</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DCellStyle#getBackgroundStyleOrigin()
     * @see #getDCellStyle()
     * @generated
     */
    EReference getDCellStyle_BackgroundStyleOrigin();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn <em>DColumn</em>}
     * '. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DColumn</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn
     * @generated
     */
    EClass getDColumn();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getLabel()
     * @see #getDColumn()
     * @generated
     */
    EAttribute getDColumn_Label();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getCells
     * <em>Cells</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Cells</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getCells()
     * @see #getDColumn()
     * @generated
     */
    EReference getDColumn_Cells();

    /**
     * Returns the meta object for the reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getOriginMapping
     * <em>Origin Mapping</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Origin Mapping</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getOriginMapping()
     * @see #getDColumn()
     * @generated
     */
    EReference getDColumn_OriginMapping();

    /**
     * Returns the meta object for the container reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getTable
     * <em>Table</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Table</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getTable()
     * @see #getDColumn()
     * @generated
     */
    EReference getDColumn_Table();

    /**
     * Returns the meta object for the reference list '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getOrderedCells
     * <em>Ordered Cells</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference list '<em>Ordered Cells</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getOrderedCells()
     * @see #getDColumn()
     * @generated
     */
    EReference getDColumn_OrderedCells();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#isVisible
     * <em>Visible</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Visible</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#isVisible()
     * @see #getDColumn()
     * @generated
     */
    EAttribute getDColumn_Visible();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getWidth()
     * @see #getDColumn()
     * @generated
     */
    EAttribute getDColumn_Width();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DColumn#getCurrentStyle
     * <em>Current Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '
     *         <em>Current Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DColumn#getCurrentStyle()
     * @see #getDColumn()
     * @generated
     */
    EReference getDColumn_CurrentStyle();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DTargetColumn
     * <em>DTarget Column</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DTarget Column</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTargetColumn
     * @generated
     */
    EClass getDTargetColumn();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DFeatureColumn
     * <em>DFeature Column</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>DFeature Column</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DFeatureColumn
     * @generated
     */
    EClass getDFeatureColumn();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DFeatureColumn#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DFeatureColumn#getFeatureName()
     * @see #getDFeatureColumn()
     * @generated
     */
    EAttribute getDFeatureColumn_FeatureName();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer
     * <em>DTable Element Synchronizer</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DTable Element Synchronizer</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementSynchronizer
     * @generated
     */
    EClass getDTableElementSynchronizer();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle
     * <em>DTable Element Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>DTable Element Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle
     * @generated
     */
    EClass getDTableElementStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelSize
     * <em>Label Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Label Size</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelSize()
     * @see #getDTableElementStyle()
     * @generated
     */
    EAttribute getDTableElementStyle_LabelSize();

    /**
     * Returns the meta object for the attribute list '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelFormat
     * <em>Label Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Label Format</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelFormat()
     * @see #getDTableElementStyle()
     * @generated
     */
    EAttribute getDTableElementStyle_LabelFormat();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getForegroundColor
     * <em>Foreground Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Foreground Color</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getForegroundColor()
     * @see #getDTableElementStyle()
     * @generated
     */
    EAttribute getDTableElementStyle_ForegroundColor();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getBackgroundColor
     * <em>Background Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Background Color</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getBackgroundColor()
     * @see #getDTableElementStyle()
     * @generated
     */
    EAttribute getDTableElementStyle_BackgroundColor();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultForegroundStyle
     * <em>Default Foreground Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Default Foreground Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultForegroundStyle()
     * @see #getDTableElementStyle()
     * @generated
     */
    EAttribute getDTableElementStyle_DefaultForegroundStyle();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultBackgroundStyle
     * <em>Default Background Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for the attribute '
     *         <em>Default Background Style</em>'.
     * @see org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultBackgroundStyle()
     * @see #getDTableElementStyle()
     * @generated
     */
    EAttribute getDTableElementStyle_DefaultBackgroundStyle();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TableFactory getTableFactory();

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
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableImpl
         * <em>DTable</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.table.metamodel.table.impl.DTableImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTable()
         * @generated
         */
        EClass DTABLE = TablePackage.eINSTANCE.getDTable();

        /**
         * The meta object literal for the '<em><b>Columns</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTABLE__COLUMNS = TablePackage.eINSTANCE.getDTable_Columns();

        /**
         * The meta object literal for the '<em><b>Description</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DTABLE__DESCRIPTION = TablePackage.eINSTANCE.getDTable_Description();

        /**
         * The meta object literal for the '<em><b>Header Column Width</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DTABLE__HEADER_COLUMN_WIDTH = TablePackage.eINSTANCE.getDTable_HeaderColumnWidth();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementImpl
         * <em>DTable Element</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTableElement()
         * @generated
         */
        EClass DTABLE_ELEMENT = TablePackage.eINSTANCE.getDTableElement();

        /**
         * The meta object literal for the '
         * <em><b>Table Element Mapping</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DTABLE_ELEMENT__TABLE_ELEMENT_MAPPING = TablePackage.eINSTANCE.getDTableElement_TableElementMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.LineContainerImpl
         * <em>Line Container</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.LineContainerImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getLineContainer()
         * @generated
         */
        EClass LINE_CONTAINER = TablePackage.eINSTANCE.getLineContainer();

        /**
         * The meta object literal for the '<em><b>Lines</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LINE_CONTAINER__LINES = TablePackage.eINSTANCE.getLineContainer_Lines();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DLineImpl
         * <em>DLine</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DLineImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDLine()
         * @generated
         */
        EClass DLINE = TablePackage.eINSTANCE.getDLine();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DLINE__LABEL = TablePackage.eINSTANCE.getDLine_Label();

        /**
         * The meta object literal for the '<em><b>Origin Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DLINE__ORIGIN_MAPPING = TablePackage.eINSTANCE.getDLine_OriginMapping();

        /**
         * The meta object literal for the '<em><b>Visible</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DLINE__VISIBLE = TablePackage.eINSTANCE.getDLine_Visible();

        /**
         * The meta object literal for the '<em><b>Collapsed</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DLINE__COLLAPSED = TablePackage.eINSTANCE.getDLine_Collapsed();

        /**
         * The meta object literal for the '<em><b>Cells</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DLINE__CELLS = TablePackage.eINSTANCE.getDLine_Cells();

        /**
         * The meta object literal for the '<em><b>Container</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DLINE__CONTAINER = TablePackage.eINSTANCE.getDLine_Container();

        /**
         * The meta object literal for the '<em><b>Ordered Cells</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DLINE__ORDERED_CELLS = TablePackage.eINSTANCE.getDLine_OrderedCells();

        /**
         * The meta object literal for the '<em><b>Current Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DLINE__CURRENT_STYLE = TablePackage.eINSTANCE.getDLine_CurrentStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellImpl
         * <em>DCell</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DCellImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDCell()
         * @generated
         */
        EClass DCELL = TablePackage.eINSTANCE.getDCell();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DCELL__LABEL = TablePackage.eINSTANCE.getDCell_Label();

        /**
         * The meta object literal for the '<em><b>Line</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCELL__LINE = TablePackage.eINSTANCE.getDCell_Line();

        /**
         * The meta object literal for the '<em><b>Column</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCELL__COLUMN = TablePackage.eINSTANCE.getDCell_Column();

        /**
         * The meta object literal for the '<em><b>Current Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DCELL__CURRENT_STYLE = TablePackage.eINSTANCE.getDCell_CurrentStyle();

        /**
         * The meta object literal for the '<em><b>Updater</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCELL__UPDATER = TablePackage.eINSTANCE.getDCell_Updater();

        /**
         * The meta object literal for the '<em><b>Intersection Mapping</b></em>
         * ' reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCELL__INTERSECTION_MAPPING = TablePackage.eINSTANCE.getDCell_IntersectionMapping();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DCellStyleImpl
         * <em>DCell Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DCellStyleImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDCellStyle()
         * @generated
         */
        EClass DCELL_STYLE = TablePackage.eINSTANCE.getDCellStyle();

        /**
         * The meta object literal for the '
         * <em><b>Foreground Style Origin</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DCELL_STYLE__FOREGROUND_STYLE_ORIGIN = TablePackage.eINSTANCE.getDCellStyle_ForegroundStyleOrigin();

        /**
         * The meta object literal for the '
         * <em><b>Background Style Origin</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DCELL_STYLE__BACKGROUND_STYLE_ORIGIN = TablePackage.eINSTANCE.getDCellStyle_BackgroundStyleOrigin();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl
         * <em>DColumn</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.eclipse.sirius.table.metamodel.table.impl.DColumnImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDColumn()
         * @generated
         */
        EClass DCOLUMN = TablePackage.eINSTANCE.getDColumn();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DCOLUMN__LABEL = TablePackage.eINSTANCE.getDColumn_Label();

        /**
         * The meta object literal for the '<em><b>Cells</b></em>' reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCOLUMN__CELLS = TablePackage.eINSTANCE.getDColumn_Cells();

        /**
         * The meta object literal for the '<em><b>Origin Mapping</b></em>'
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCOLUMN__ORIGIN_MAPPING = TablePackage.eINSTANCE.getDColumn_OriginMapping();

        /**
         * The meta object literal for the '<em><b>Table</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCOLUMN__TABLE = TablePackage.eINSTANCE.getDColumn_Table();

        /**
         * The meta object literal for the '<em><b>Ordered Cells</b></em>'
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DCOLUMN__ORDERED_CELLS = TablePackage.eINSTANCE.getDColumn_OrderedCells();

        /**
         * The meta object literal for the '<em><b>Visible</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DCOLUMN__VISIBLE = TablePackage.eINSTANCE.getDColumn_Visible();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DCOLUMN__WIDTH = TablePackage.eINSTANCE.getDColumn_Width();

        /**
         * The meta object literal for the '<em><b>Current Style</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference DCOLUMN__CURRENT_STYLE = TablePackage.eINSTANCE.getDColumn_CurrentStyle();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl
         * <em>DTarget Column</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DTargetColumnImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTargetColumn()
         * @generated
         */
        EClass DTARGET_COLUMN = TablePackage.eINSTANCE.getDTargetColumn();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DFeatureColumnImpl
         * <em>DFeature Column</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DFeatureColumnImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDFeatureColumn()
         * @generated
         */
        EClass DFEATURE_COLUMN = TablePackage.eINSTANCE.getDFeatureColumn();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DFEATURE_COLUMN__FEATURE_NAME = TablePackage.eINSTANCE.getDFeatureColumn_FeatureName();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementSynchronizerImpl
         * <em>DTable Element Synchronizer</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementSynchronizerImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTableElementSynchronizer()
         * @generated
         */
        EClass DTABLE_ELEMENT_SYNCHRONIZER = TablePackage.eINSTANCE.getDTableElementSynchronizer();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl
         * <em>DTable Element Style</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.table.metamodel.table.impl.DTableElementStyleImpl
         * @see org.eclipse.sirius.table.metamodel.table.impl.TablePackageImpl#getDTableElementStyle()
         * @generated
         */
        EClass DTABLE_ELEMENT_STYLE = TablePackage.eINSTANCE.getDTableElementStyle();

        /**
         * The meta object literal for the '<em><b>Label Size</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DTABLE_ELEMENT_STYLE__LABEL_SIZE = TablePackage.eINSTANCE.getDTableElementStyle_LabelSize();

        /**
         * The meta object literal for the '<em><b>Label Format</b></em>'
         * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DTABLE_ELEMENT_STYLE__LABEL_FORMAT = TablePackage.eINSTANCE.getDTableElementStyle_LabelFormat();

        /**
         * The meta object literal for the '<em><b>Foreground Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR = TablePackage.eINSTANCE.getDTableElementStyle_ForegroundColor();

        /**
         * The meta object literal for the '<em><b>Background Color</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR = TablePackage.eINSTANCE.getDTableElementStyle_BackgroundColor();

        /**
         * The meta object literal for the '
         * <em><b>Default Foreground Style</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DTABLE_ELEMENT_STYLE__DEFAULT_FOREGROUND_STYLE = TablePackage.eINSTANCE.getDTableElementStyle_DefaultForegroundStyle();

        /**
         * The meta object literal for the '
         * <em><b>Default Background Style</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DTABLE_ELEMENT_STYLE__DEFAULT_BACKGROUND_STYLE = TablePackage.eINSTANCE.getDTableElementStyle_DefaultBackgroundStyle();

    }

} // TablePackage
