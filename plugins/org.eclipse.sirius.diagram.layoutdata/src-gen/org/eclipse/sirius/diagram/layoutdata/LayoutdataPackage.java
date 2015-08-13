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
package org.eclipse.sirius.diagram.layoutdata;

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
 * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataFactory
 * @model kind="package"
 * @generated
 */
public interface LayoutdataPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "layoutdata"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/dsl/layoutdata/1.1.0"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "layoutdata"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    LayoutdataPackage eINSTANCE = org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.layoutdata.impl.AbstractLayoutDataImpl
     * <em>Abstract Layout Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.layoutdata.impl.AbstractLayoutDataImpl
     * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getAbstractLayoutData()
     * @generated
     */
    int ABSTRACT_LAYOUT_DATA = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_LAYOUT_DATA__ID = 0;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_LAYOUT_DATA__LABEL = 1;

    /**
     * The number of structural features of the '<em>Abstract Layout Data</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_LAYOUT_DATA_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl
     * <em>Node Layout Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl
     * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getNodeLayoutData()
     * @generated
     */
    int NODE_LAYOUT_DATA = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__ID = ABSTRACT_LAYOUT_DATA__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__LABEL = ABSTRACT_LAYOUT_DATA__LABEL;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__WIDTH = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__HEIGHT = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__CHILDREN = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Outgoing Edges</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__OUTGOING_EDGES = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Location</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA__LOCATION = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Node Layout Data</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NODE_LAYOUT_DATA_FEATURE_COUNT = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl
     * <em>Edge Layout Data</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl
     * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getEdgeLayoutData()
     * @generated
     */
    int EDGE_LAYOUT_DATA = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__ID = ABSTRACT_LAYOUT_DATA__ID;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__LABEL = ABSTRACT_LAYOUT_DATA__LABEL;

    /**
     * The feature id for the '<em><b>Source Terminal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__SOURCE_TERMINAL = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target Terminal</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__TARGET_TERMINAL = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Routing</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__ROUTING = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Point List</b></em>' containment reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__POINT_LIST = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Ref Point</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__SOURCE_REF_POINT = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Ref Point</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__TARGET_REF_POINT = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Jump Link Status</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__JUMP_LINK_STATUS = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Jump Link Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__JUMP_LINK_TYPE = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Reverse Jump Link</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Smoothness</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA__SMOOTHNESS = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the '<em>Edge Layout Data</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int EDGE_LAYOUT_DATA_FEATURE_COUNT = ABSTRACT_LAYOUT_DATA_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.diagram.layoutdata.impl.PointImpl
     * <em>Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.sirius.diagram.layoutdata.impl.PointImpl
     * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getPoint()
     * @generated
     */
    int POINT = 3;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINT__X = 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int POINT__Y = 1;

    /**
     * The number of structural features of the '<em>Point</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int POINT_FEATURE_COUNT = 2;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData
     * <em>Abstract Layout Data</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Layout Data</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData
     * @generated
     */
    EClass getAbstractLayoutData();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData#getId
     * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData#getId()
     * @see #getAbstractLayoutData()
     * @generated
     */
    EAttribute getAbstractLayoutData_Id();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData#getLabel
     * <em>Label</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.AbstractLayoutData#getLabel()
     * @see #getAbstractLayoutData()
     * @generated
     */
    EReference getAbstractLayoutData_Label();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData
     * <em>Node Layout Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Node Layout Data</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData
     * @generated
     */
    EClass getNodeLayoutData();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getWidth
     * <em>Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getWidth()
     * @see #getNodeLayoutData()
     * @generated
     */
    EAttribute getNodeLayoutData_Width();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getHeight
     * <em>Height</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getHeight()
     * @see #getNodeLayoutData()
     * @generated
     */
    EAttribute getNodeLayoutData_Height();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getChildren
     * <em>Children</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Children</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getChildren()
     * @see #getNodeLayoutData()
     * @generated
     */
    EReference getNodeLayoutData_Children();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getOutgoingEdges
     * <em>Outgoing Edges</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Outgoing Edges</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getOutgoingEdges()
     * @see #getNodeLayoutData()
     * @generated
     */
    EReference getNodeLayoutData_OutgoingEdges();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getLocation
     * <em>Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Location</em>
     *         '.
     * @see org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getLocation()
     * @see #getNodeLayoutData()
     * @generated
     */
    EReference getNodeLayoutData_Location();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData
     * <em>Edge Layout Data</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Edge Layout Data</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData
     * @generated
     */
    EClass getEdgeLayoutData();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceTerminal
     * <em>Source Terminal</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Source Terminal</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceTerminal()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_SourceTerminal();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetTerminal
     * <em>Target Terminal</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Target Terminal</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetTerminal()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_TargetTerminal();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getRouting
     * <em>Routing</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Routing</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getRouting()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_Routing();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getPointList
     * <em>Point List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '
     *         <em>Point List</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getPointList()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EReference getEdgeLayoutData_PointList();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceRefPoint
     * <em>Source Ref Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Source Ref Point</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceRefPoint()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EReference getEdgeLayoutData_SourceRefPoint();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetRefPoint
     * <em>Target Ref Point</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference '
     *         <em>Target Ref Point</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetRefPoint()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EReference getEdgeLayoutData_TargetRefPoint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkStatus
     * <em>Jump Link Status</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Jump Link Status</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkStatus()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_JumpLinkStatus();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkType
     * <em>Jump Link Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Jump Link Type</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkType()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_JumpLinkType();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#isReverseJumpLink
     * <em>Reverse Jump Link</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the attribute '<em>Reverse Jump Link</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#isReverseJumpLink()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_ReverseJumpLink();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSmoothness
     * <em>Smoothness</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Smoothness</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSmoothness()
     * @see #getEdgeLayoutData()
     * @generated
     */
    EAttribute getEdgeLayoutData_Smoothness();

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.diagram.layoutdata.Point <em>Point</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Point</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.Point
     * @generated
     */
    EClass getPoint();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.Point#getX <em>X</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>X</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.Point#getX()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_X();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.diagram.layoutdata.Point#getY <em>Y</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see org.eclipse.sirius.diagram.layoutdata.Point#getY()
     * @see #getPoint()
     * @generated
     */
    EAttribute getPoint_Y();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    LayoutdataFactory getLayoutdataFactory();

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
         * {@link org.eclipse.sirius.diagram.layoutdata.impl.AbstractLayoutDataImpl
         * <em>Abstract Layout Data</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.layoutdata.impl.AbstractLayoutDataImpl
         * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getAbstractLayoutData()
         * @generated
         */
        EClass ABSTRACT_LAYOUT_DATA = eINSTANCE.getAbstractLayoutData();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ABSTRACT_LAYOUT_DATA__ID = eINSTANCE.getAbstractLayoutData_Id();

        /**
         * The meta object literal for the '<em><b>Label</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_LAYOUT_DATA__LABEL = eINSTANCE.getAbstractLayoutData_Label();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl
         * <em>Node Layout Data</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.layoutdata.impl.NodeLayoutDataImpl
         * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getNodeLayoutData()
         * @generated
         */
        EClass NODE_LAYOUT_DATA = eINSTANCE.getNodeLayoutData();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_LAYOUT_DATA__WIDTH = eINSTANCE.getNodeLayoutData_Width();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute NODE_LAYOUT_DATA__HEIGHT = eINSTANCE.getNodeLayoutData_Height();

        /**
         * The meta object literal for the '<em><b>Children</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_LAYOUT_DATA__CHILDREN = eINSTANCE.getNodeLayoutData_Children();

        /**
         * The meta object literal for the '<em><b>Outgoing Edges</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_LAYOUT_DATA__OUTGOING_EDGES = eINSTANCE.getNodeLayoutData_OutgoingEdges();

        /**
         * The meta object literal for the '<em><b>Location</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference NODE_LAYOUT_DATA__LOCATION = eINSTANCE.getNodeLayoutData_Location();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl
         * <em>Edge Layout Data</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.layoutdata.impl.EdgeLayoutDataImpl
         * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getEdgeLayoutData()
         * @generated
         */
        EClass EDGE_LAYOUT_DATA = eINSTANCE.getEdgeLayoutData();

        /**
         * The meta object literal for the '<em><b>Source Terminal</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__SOURCE_TERMINAL = eINSTANCE.getEdgeLayoutData_SourceTerminal();

        /**
         * The meta object literal for the '<em><b>Target Terminal</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__TARGET_TERMINAL = eINSTANCE.getEdgeLayoutData_TargetTerminal();

        /**
         * The meta object literal for the '<em><b>Routing</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__ROUTING = eINSTANCE.getEdgeLayoutData_Routing();

        /**
         * The meta object literal for the '<em><b>Point List</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_LAYOUT_DATA__POINT_LIST = eINSTANCE.getEdgeLayoutData_PointList();

        /**
         * The meta object literal for the '<em><b>Source Ref Point</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_LAYOUT_DATA__SOURCE_REF_POINT = eINSTANCE.getEdgeLayoutData_SourceRefPoint();

        /**
         * The meta object literal for the '<em><b>Target Ref Point</b></em>'
         * containment reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @generated
         */
        EReference EDGE_LAYOUT_DATA__TARGET_REF_POINT = eINSTANCE.getEdgeLayoutData_TargetRefPoint();

        /**
         * The meta object literal for the '<em><b>Jump Link Status</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__JUMP_LINK_STATUS = eINSTANCE.getEdgeLayoutData_JumpLinkStatus();

        /**
         * The meta object literal for the '<em><b>Jump Link Type</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__JUMP_LINK_TYPE = eINSTANCE.getEdgeLayoutData_JumpLinkType();

        /**
         * The meta object literal for the '<em><b>Reverse Jump Link</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__REVERSE_JUMP_LINK = eINSTANCE.getEdgeLayoutData_ReverseJumpLink();

        /**
         * The meta object literal for the '<em><b>Smoothness</b></em>'
         * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute EDGE_LAYOUT_DATA__SMOOTHNESS = eINSTANCE.getEdgeLayoutData_Smoothness();

        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.diagram.layoutdata.impl.PointImpl
         * <em>Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.sirius.diagram.layoutdata.impl.PointImpl
         * @see org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataPackageImpl#getPoint()
         * @generated
         */
        EClass POINT = eINSTANCE.getPoint();

        /**
         * The meta object literal for the '<em><b>X</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute POINT__X = eINSTANCE.getPoint_X();

        /**
         * The meta object literal for the '<em><b>Y</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute POINT__Y = eINSTANCE.getPoint_Y();

    }

} // LayoutdataPackage
