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
 * <em><b>Node Layout Data</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The layout data for a node. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getWidth <em>
 * Width</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getHeight
 * <em>Height</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getChildren
 * <em>Children</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getOutgoingEdges
 * <em>Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getLocation
 * <em>Location</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getNodeLayoutData()
 * @model
 * @generated
 */
public interface NodeLayoutData extends AbstractLayoutData {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * width of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getNodeLayoutData_Width()
     * @model
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getWidth
     * <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(int value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * height of this node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getNodeLayoutData_Height()
     * @model
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(int value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Represents the layout of the children of this node. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getNodeLayoutData_Children()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<NodeLayoutData> getChildren();

    /**
     * Returns the value of the '<em><b>Outgoing Edges</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData}. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Represents the layout of the edges that go out from this node. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Outgoing Edges</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getNodeLayoutData_OutgoingEdges()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<EdgeLayoutData> getOutgoingEdges();

    /**
     * Returns the value of the '<em><b>Location</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Represents the relative location of this node
     * (relative to the parent layoutData). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Location</em>' containment reference.
     * @see #setLocation(Point)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getNodeLayoutData_Location()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    Point getLocation();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.NodeLayoutData#getLocation
     * <em>Location</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Location</em>' containment
     *            reference.
     * @see #getLocation()
     * @generated
     */
    void setLocation(Point value);

} // NodeLayoutData
