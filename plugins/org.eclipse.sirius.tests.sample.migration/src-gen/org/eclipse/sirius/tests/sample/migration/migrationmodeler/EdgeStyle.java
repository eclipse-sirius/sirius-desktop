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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getRoutingStyle
 * <em>Routing Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getColor
 * <em>Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getBeginLabelStyle
 * <em>Begin Label Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getCenterLabelStyle
 * <em>Center Label Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getEndLabelStyle
 * <em>End Label Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeStyle()
 * @model
 * @generated
 */
public interface EdgeStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Routing Style</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Routing Style</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
     * @see #setRoutingStyle(RoutingStyle)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeStyle_RoutingStyle()
     * @model
     * @generated
     */
    RoutingStyle getRoutingStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getRoutingStyle
     * <em>Routing Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Routing Style</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.RoutingStyle
     * @see #getRoutingStyle()
     * @generated
     */
    void setRoutingStyle(RoutingStyle value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeStyle_Color()
     * @model containment="true"
     * @generated
     */
    Color getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getColor
     * <em>Color</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(Color value);

    /**
     * Returns the value of the '<em><b>Begin Label Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Begin Label Style</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Begin Label Style</em>' containment
     *         reference.
     * @see #setBeginLabelStyle(BasicLabelStyle)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeStyle_BeginLabelStyle()
     * @model containment="true"
     * @generated
     */
    BasicLabelStyle getBeginLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getBeginLabelStyle
     * <em>Begin Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Begin Label Style</em>' containment
     *            reference.
     * @see #getBeginLabelStyle()
     * @generated
     */
    void setBeginLabelStyle(BasicLabelStyle value);

    /**
     * Returns the value of the '<em><b>Center Label Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Center Label Style</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Center Label Style</em>' containment
     *         reference.
     * @see #setCenterLabelStyle(BasicLabelStyle)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeStyle_CenterLabelStyle()
     * @model containment="true"
     * @generated
     */
    BasicLabelStyle getCenterLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getCenterLabelStyle
     * <em>Center Label Style</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Center Label Style</em>' containment
     *            reference.
     * @see #getCenterLabelStyle()
     * @generated
     */
    void setCenterLabelStyle(BasicLabelStyle value);

    /**
     * Returns the value of the '<em><b>End Label Style</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Label Style</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>End Label Style</em>' containment
     *         reference.
     * @see #setEndLabelStyle(BasicLabelStyle)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getEdgeStyle_EndLabelStyle()
     * @model containment="true"
     * @generated
     */
    BasicLabelStyle getEndLabelStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.EdgeStyle#getEndLabelStyle
     * <em>End Label Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>End Label Style</em>' containment
     *            reference.
     * @see #getEndLabelStyle()
     * @generated
     */
    void setEndLabelStyle(BasicLabelStyle value);

} // EdgeStyle
