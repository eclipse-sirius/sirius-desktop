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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Layout Data</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The layout data for an edge. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceTerminal
 * <em>Source Terminal</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetTerminal
 * <em>Target Terminal</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getRouting
 * <em>Routing</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getPointList
 * <em>Point List</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceRefPoint
 * <em>Source Ref Point</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetRefPoint
 * <em>Target Ref Point</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkStatus
 * <em>Jump Link Status</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkType
 * <em>Jump Link Type</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#isReverseJumpLink
 * <em>Reverse Jump Link</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSmoothness
 * <em>Smoothness</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData()
 * @model
 * @generated
 */
public interface EdgeLayoutData extends AbstractLayoutData {
    /**
     * Returns the value of the '<em><b>Source Terminal</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Identifier associated with the source anchor.
     *
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IAnchorableFigure}
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor.parseTerminalString}
     *      <!-- end-model-doc -->
     * @return the value of the '<em>Source Terminal</em>' attribute.
     * @see #setSourceTerminal(String)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_SourceTerminal()
     * @model
     * @generated
     */
    String getSourceTerminal();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceTerminal
     * <em>Source Terminal</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Terminal</em>' attribute.
     * @see #getSourceTerminal()
     * @generated
     */
    void setSourceTerminal(String value);

    /**
     * Returns the value of the '<em><b>Target Terminal</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Identifier associated with the target anchor.
     *
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.IAnchorableFigure}
     * @see {@link org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor.parseTerminalString}
     *      <!-- end-model-doc -->
     * @return the value of the '<em>Target Terminal</em>' attribute.
     * @see #setTargetTerminal(String)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_TargetTerminal()
     * @model
     * @generated
     */
    String getTargetTerminal();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetTerminal
     * <em>Target Terminal</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Terminal</em>' attribute.
     * @see #getTargetTerminal()
     * @generated
     */
    void setTargetTerminal(String value);

    /**
     * Returns the value of the '<em><b>Routing</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * literal value of the routing.
     *
     * @see {@link org.eclipse.gmf.runtime.notation.Routing} <!-- end-model-doc
     *      -->
     * @return the value of the '<em>Routing</em>' attribute.
     * @see #setRouting(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_Routing()
     * @model
     * @generated
     */
    int getRouting();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getRouting
     * <em>Routing</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Routing</em>' attribute.
     * @see #getRouting()
     * @generated
     */
    void setRouting(int value);

    /**
     * Returns the value of the '<em><b>Point List</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.layoutdata.Point}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> List of points by
     * which the edge passes. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Point List</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_PointList()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<Point> getPointList();

    /**
     * Returns the value of the '<em><b>Source Ref Point</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Ref Point</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Source Ref Point</em>' containment
     *         reference.
     * @see #setSourceRefPoint(Point)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_SourceRefPoint()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Point getSourceRefPoint();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSourceRefPoint
     * <em>Source Ref Point</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Source Ref Point</em>' containment
     *            reference.
     * @see #getSourceRefPoint()
     * @generated
     */
    void setSourceRefPoint(Point value);

    /**
     * Returns the value of the '<em><b>Target Ref Point</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Ref Point</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Target Ref Point</em>' containment
     *         reference.
     * @see #setTargetRefPoint(Point)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_TargetRefPoint()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Point getTargetRefPoint();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getTargetRefPoint
     * <em>Target Ref Point</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Target Ref Point</em>' containment
     *            reference.
     * @see #getTargetRefPoint()
     * @generated
     */
    void setTargetRefPoint(Point value);

    /**
     * Returns the value of the '<em><b>Jump Link Status</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The literal value of the Jump Links Status
     *
     * @see {@link org.eclipse.gmf.runtime.notation.JumpLinkStatus} <!--
     *      end-model-doc -->
     * @return the value of the '<em>Jump Link Status</em>' attribute.
     * @see #setJumpLinkStatus(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_JumpLinkStatus()
     * @model
     * @generated
     */
    int getJumpLinkStatus();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkStatus
     * <em>Jump Link Status</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Jump Link Status</em>' attribute.
     * @see #getJumpLinkStatus()
     * @generated
     */
    void setJumpLinkStatus(int value);

    /**
     * Returns the value of the '<em><b>Jump Link Type</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * literal value of the Jump Links Type.
     *
     * @see {@link org.eclipse.gmf.runtime.notation.JumpLinkType} <!--
     *      end-model-doc -->
     * @return the value of the '<em>Jump Link Type</em>' attribute.
     * @see #setJumpLinkType(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_JumpLinkType()
     * @model
     * @generated
     */
    int getJumpLinkType();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getJumpLinkType
     * <em>Jump Link Type</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Jump Link Type</em>' attribute.
     * @see #getJumpLinkType()
     * @generated
     */
    void setJumpLinkType(int value);

    /**
     * Returns the value of the '<em><b>Reverse Jump Link</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The literal value of the Reverse Jump Links. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Reverse Jump Link</em>' attribute.
     * @see #setReverseJumpLink(boolean)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_ReverseJumpLink()
     * @model
     * @generated
     */
    boolean isReverseJumpLink();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#isReverseJumpLink
     * <em>Reverse Jump Link</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Reverse Jump Link</em>' attribute.
     * @see #isReverseJumpLink()
     * @generated
     */
    void setReverseJumpLink(boolean value);

    /**
     * Returns the value of the '<em><b>Smoothness</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * literal value of the smotohness.
     *
     * @see {@link org.eclipse.gmf.runtime.notation.Smoothness} <!--
     *      end-model-doc -->
     * @return the value of the '<em>Smoothness</em>' attribute.
     * @see #setSmoothness(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getEdgeLayoutData_Smoothness()
     * @model
     * @generated
     */
    int getSmoothness();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData#getSmoothness
     * <em>Smoothness</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Smoothness</em>' attribute.
     * @see #getSmoothness()
     * @generated
     */
    void setSmoothness(int value);

} // EdgeLayoutData
