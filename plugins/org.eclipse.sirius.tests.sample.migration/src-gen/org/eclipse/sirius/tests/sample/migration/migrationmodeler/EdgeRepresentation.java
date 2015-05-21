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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Representation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getSource
 * <em>Source</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getTarget
 * <em>Target</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getBendpoints
 * <em>Bendpoints</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeRepresentation()
 * @model
 * @generated
 */
public interface EdgeRepresentation extends AbstractRepresentation {
    /**
     * Returns the value of the '<em><b>Source</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(GraphicalElement)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeRepresentation_Source()
     * @model
     * @generated
     */
    GraphicalElement getSource();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getSource
     * <em>Source</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
    void setSource(GraphicalElement value);

    /**
     * Returns the value of the '<em><b>Target</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(GraphicalElement)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeRepresentation_Target()
     * @model
     * @generated
     */
    GraphicalElement getTarget();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getTarget
     * <em>Target</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
    void setTarget(GraphicalElement value);

    /**
     * Returns the value of the '<em><b>Bendpoints</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Point}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bendpoints</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Bendpoints</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeRepresentation_Bendpoints()
     * @model containment="true"
     * @generated
     */
    EList<Point> getBendpoints();

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The style of the connection. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(EdgeStyle)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeRepresentation_OwnedStyle()
     * @model containment="true"
     * @generated
     */
    EdgeStyle getOwnedStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeRepresentation#getOwnedStyle
     * <em>Owned Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owned Style</em>' containment
     *            reference.
     * @see #getOwnedStyle()
     * @generated
     */
    void setOwnedStyle(EdgeStyle value);

} // EdgeRepresentation
