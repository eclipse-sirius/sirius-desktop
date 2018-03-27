/*******************************************************************************
 * Copyright (c) 2007, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.formatdata;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.eclipse.sirius.diagram.formatdata.FormatdataFactory
 * @model kind="package"
 * @generated
 */
public interface FormatdataPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "formatdata"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/dsl/formatdata/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "formatdata"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    FormatdataPackage eINSTANCE = org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl <em>Abstract
     * Format Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl
     * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getAbstractFormatData()
     * @generated
     */
    int ABSTRACT_FORMAT_DATA = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_FORMAT_DATA__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_FORMAT_DATA__LABEL = 1;

    /**
     * The feature id for the '<em><b>Sirius Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_FORMAT_DATA__SIRIUS_STYLE = 2;

    /**
     * The feature id for the '<em><b>Gmf View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_FORMAT_DATA__GMF_VIEW = 3;

    /**
     * The number of structural features of the '<em>Abstract Format Data</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_FORMAT_DATA_FEATURE_COUNT = 4;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl <em>Node Format
     * Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl
     * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getNodeFormatData()
     * @generated
     */
    int NODE_FORMAT_DATA = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__ID = FormatdataPackage.ABSTRACT_FORMAT_DATA__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__LABEL = FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL;

    /**
     * The feature id for the '<em><b>Sirius Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__SIRIUS_STYLE = FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE;

    /**
     * The feature id for the '<em><b>Gmf View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__GMF_VIEW = FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__WIDTH = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__HEIGHT = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__CHILDREN = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__OUTGOING_EDGES = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Location</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA__LOCATION = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Node Format Data</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_FORMAT_DATA_FEATURE_COUNT = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.formatdata.impl.EdgeFormatDataImpl <em>Edge Format
     * Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.formatdata.impl.EdgeFormatDataImpl
     * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getEdgeFormatData()
     * @generated
     */
    int EDGE_FORMAT_DATA = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__ID = FormatdataPackage.ABSTRACT_FORMAT_DATA__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__LABEL = FormatdataPackage.ABSTRACT_FORMAT_DATA__LABEL;

    /**
     * The feature id for the '<em><b>Sirius Style</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__SIRIUS_STYLE = FormatdataPackage.ABSTRACT_FORMAT_DATA__SIRIUS_STYLE;

    /**
     * The feature id for the '<em><b>Gmf View</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__GMF_VIEW = FormatdataPackage.ABSTRACT_FORMAT_DATA__GMF_VIEW;

    /**
     * The feature id for the '<em><b>Source Terminal</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__SOURCE_TERMINAL = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target Terminal</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__TARGET_TERMINAL = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Routing</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__ROUTING = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Point List</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__POINT_LIST = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Ref Point</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__SOURCE_REF_POINT = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Ref Point</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__TARGET_REF_POINT = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Jump Link Status</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__JUMP_LINK_STATUS = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Jump Link Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__JUMP_LINK_TYPE = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Reverse Jump Link</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__REVERSE_JUMP_LINK = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Smoothness</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA__SMOOTHNESS = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Edge Format Data</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_FORMAT_DATA_FEATURE_COUNT = FormatdataPackage.ABSTRACT_FORMAT_DATA_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.diagram.formatdata.impl.PointImpl <em>Point</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.diagram.formatdata.impl.PointImpl
     * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getPoint()
     * @generated
     */
    int POINT = 3;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT__Y = 1;

    /**
     * The number of structural features of the '<em>Point</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.diagram.formatdata.AbstractFormatData <em>Abstract
     * Format Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Abstract Format Data</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.AbstractFormatData
     * @generated
     */
    EClass getAbstractFormatData();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getId()
     * @see #getAbstractFormatData()
     * @generated
     */
    EAttribute getAbstractFormatData_Id();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getLabel <em>Label</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getLabel()
     * @see #getAbstractFormatData()
     * @generated
     */
    EReference getAbstractFormatData_Label();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getSiriusStyle <em>Sirius Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Sirius Style</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getSiriusStyle()
     * @see #getAbstractFormatData()
     * @generated
     */
    EReference getAbstractFormatData_SiriusStyle();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getGmfView <em>Gmf View</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Gmf View</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.AbstractFormatData#getGmfView()
     * @see #getAbstractFormatData()
     * @generated
     */
    EReference getAbstractFormatData_GmfView();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.formatdata.NodeFormatData <em>Node Format
     * Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Format Data</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.NodeFormatData
     * @generated
     */
    EClass getNodeFormatData();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.NodeFormatData#getWidth()
     * @see #getNodeFormatData()
     * @generated
     */
    EAttribute getNodeFormatData_Width();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.NodeFormatData#getHeight()
     * @see #getNodeFormatData()
     * @generated
     */
    EAttribute getNodeFormatData_Height();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getChildren <em>Children</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.NodeFormatData#getChildren()
     * @see #getNodeFormatData()
     * @generated
     */
    EReference getNodeFormatData_Children();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getOutgoingEdges <em>Outgoing Edges</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Outgoing Edges</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.NodeFormatData#getOutgoingEdges()
     * @see #getNodeFormatData()
     * @generated
     */
    EReference getNodeFormatData_OutgoingEdges();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getLocation <em>Location</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Location</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.NodeFormatData#getLocation()
     * @see #getNodeFormatData()
     * @generated
     */
    EReference getNodeFormatData_Location();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData <em>Edge Format
     * Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Format Data</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData
     * @generated
     */
    EClass getEdgeFormatData();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getSourceTerminal <em>Source Terminal</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Source Terminal</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getSourceTerminal()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_SourceTerminal();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getTargetTerminal <em>Target Terminal</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Terminal</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getTargetTerminal()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_TargetTerminal();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getRouting
     * <em>Routing</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Routing</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getRouting()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_Routing();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getPointList <em>Point List</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Point List</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getPointList()
     * @see #getEdgeFormatData()
     * @generated
     */
    EReference getEdgeFormatData_PointList();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getSourceRefPoint <em>Source Ref Point</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Source Ref Point</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getSourceRefPoint()
     * @see #getEdgeFormatData()
     * @generated
     */
    EReference getEdgeFormatData_SourceRefPoint();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getTargetRefPoint <em>Target Ref Point</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference ' <em>Target Ref Point</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getTargetRefPoint()
     * @see #getEdgeFormatData()
     * @generated
     */
    EReference getEdgeFormatData_TargetRefPoint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getJumpLinkStatus <em>Jump Link Status</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Jump Link Status</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getJumpLinkStatus()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_JumpLinkStatus();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getJumpLinkType <em>Jump Link Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Jump Link Type</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getJumpLinkType()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_JumpLinkType();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#isReverseJumpLink <em>Reverse Jump Link</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Reverse Jump Link</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#isReverseJumpLink()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_ReverseJumpLink();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getSmoothness <em>Smoothness</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Smoothness</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.EdgeFormatData#getSmoothness()
     * @see #getEdgeFormatData()
     * @generated
     */
    EAttribute getEdgeFormatData_Smoothness();

    /**
     * Returns the meta object for class ' {@link org.eclipse.sirius.diagram.formatdata.Point <em>Point</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Point</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.Point
     * @generated
     */
    EClass getPoint();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.formatdata.Point#getX <em>X</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.Point#getX()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_X();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.diagram.formatdata.Point#getY <em>Y</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.diagram.formatdata.Point#getY()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_Y();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    FormatdataFactory getFormatdataFactory();

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
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl
         * <em>Abstract Format Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.formatdata.impl.AbstractFormatDataImpl
         * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getAbstractFormatData()
         * @generated
         */
        EClass ABSTRACT_FORMAT_DATA = FormatdataPackage.eINSTANCE.getAbstractFormatData();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ABSTRACT_FORMAT_DATA__ID = FormatdataPackage.eINSTANCE.getAbstractFormatData_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_FORMAT_DATA__LABEL = FormatdataPackage.eINSTANCE.getAbstractFormatData_Label();

        /**
         * The meta object literal for the '<em><b>Sirius Style</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_FORMAT_DATA__SIRIUS_STYLE = FormatdataPackage.eINSTANCE.getAbstractFormatData_SiriusStyle();

        /**
         * The meta object literal for the '<em><b>Gmf View</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ABSTRACT_FORMAT_DATA__GMF_VIEW = FormatdataPackage.eINSTANCE.getAbstractFormatData_GmfView();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl
         * <em>Node Format Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.formatdata.impl.NodeFormatDataImpl
         * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getNodeFormatData()
         * @generated
         */
        EClass NODE_FORMAT_DATA = FormatdataPackage.eINSTANCE.getNodeFormatData();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_FORMAT_DATA__WIDTH = FormatdataPackage.eINSTANCE.getNodeFormatData_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_FORMAT_DATA__HEIGHT = FormatdataPackage.eINSTANCE.getNodeFormatData_Height();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_FORMAT_DATA__CHILDREN = FormatdataPackage.eINSTANCE.getNodeFormatData_Children();

        /**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_FORMAT_DATA__OUTGOING_EDGES = FormatdataPackage.eINSTANCE.getNodeFormatData_OutgoingEdges();

        /**
         * The meta object literal for the '<em><b>Location</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_FORMAT_DATA__LOCATION = FormatdataPackage.eINSTANCE.getNodeFormatData_Location();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.formatdata.impl.EdgeFormatDataImpl
         * <em>Edge Format Data</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.formatdata.impl.EdgeFormatDataImpl
         * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getEdgeFormatData()
         * @generated
         */
        EClass EDGE_FORMAT_DATA = FormatdataPackage.eINSTANCE.getEdgeFormatData();

        /**
         * The meta object literal for the '<em><b>Source Terminal</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__SOURCE_TERMINAL = FormatdataPackage.eINSTANCE.getEdgeFormatData_SourceTerminal();

        /**
         * The meta object literal for the '<em><b>Target Terminal</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__TARGET_TERMINAL = FormatdataPackage.eINSTANCE.getEdgeFormatData_TargetTerminal();

        /**
         * The meta object literal for the '<em><b>Routing</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__ROUTING = FormatdataPackage.eINSTANCE.getEdgeFormatData_Routing();

        /**
         * The meta object literal for the '<em><b>Point List</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_FORMAT_DATA__POINT_LIST = FormatdataPackage.eINSTANCE.getEdgeFormatData_PointList();

        /**
         * The meta object literal for the '<em><b>Source Ref Point</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_FORMAT_DATA__SOURCE_REF_POINT = FormatdataPackage.eINSTANCE.getEdgeFormatData_SourceRefPoint();

        /**
         * The meta object literal for the '<em><b>Target Ref Point</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_FORMAT_DATA__TARGET_REF_POINT = FormatdataPackage.eINSTANCE.getEdgeFormatData_TargetRefPoint();

        /**
         * The meta object literal for the '<em><b>Jump Link Status</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__JUMP_LINK_STATUS = FormatdataPackage.eINSTANCE.getEdgeFormatData_JumpLinkStatus();

        /**
         * The meta object literal for the '<em><b>Jump Link Type</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__JUMP_LINK_TYPE = FormatdataPackage.eINSTANCE.getEdgeFormatData_JumpLinkType();

        /**
         * The meta object literal for the '<em><b>Reverse Jump Link</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__REVERSE_JUMP_LINK = FormatdataPackage.eINSTANCE.getEdgeFormatData_ReverseJumpLink();

        /**
         * The meta object literal for the '<em><b>Smoothness</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_FORMAT_DATA__SMOOTHNESS = FormatdataPackage.eINSTANCE.getEdgeFormatData_Smoothness();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.diagram.formatdata.impl.PointImpl <em>Point</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.diagram.formatdata.impl.PointImpl
         * @see org.eclipse.sirius.diagram.formatdata.impl.FormatdataPackageImpl#getPoint()
         * @generated
         */
        EClass POINT = FormatdataPackage.eINSTANCE.getPoint();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute POINT__X = FormatdataPackage.eINSTANCE.getPoint_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute POINT__Y = FormatdataPackage.eINSTANCE.getPoint_Y();

    }

} // FormatdataPackage
