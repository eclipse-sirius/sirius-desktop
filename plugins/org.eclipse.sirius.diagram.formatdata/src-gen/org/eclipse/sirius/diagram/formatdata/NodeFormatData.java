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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Node Format Data</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> The format data for a node. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getWidth <em>Width</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getHeight <em>Height</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getChildren <em>Children</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getOutgoingEdges <em>Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#getNodeFormatData()
 * @model
 * @generated
 */
public interface NodeFormatData extends AbstractFormatData {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The width of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#getNodeFormatData_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getWidth <em>Width</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The height of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(int)
     * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#getNodeFormatData_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the ' {@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getHeight <em>Height</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.formatdata.NodeFormatData}. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Represents the format of the children of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#getNodeFormatData_Children()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<NodeFormatData> getChildren();

    /**
     * Returns the value of the '<em><b>Outgoing Edges</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.diagram.formatdata.EdgeFormatData}. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> Represents the format of the edges that go out from this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Outgoing Edges</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#getNodeFormatData_OutgoingEdges()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<EdgeFormatData> getOutgoingEdges();

    /**
     * Returns the value of the '<em><b>Location</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Represents the relative location of this node (relative to the parent
     * formatData). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Location</em>' containment reference.
     * @see #setLocation(Point)
     * @see org.eclipse.sirius.diagram.formatdata.FormatdataPackage#getNodeFormatData_Location()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Point getLocation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.formatdata.NodeFormatData#getLocation
     * <em>Location</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Location</em>' containment reference.
     * @see #getLocation()
     * @generated
     */
    void setLocation(Point value);

} // NodeFormatData
