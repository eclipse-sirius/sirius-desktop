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

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Shape Container Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getShapeContainerStyle()
 * @model
 * @generated
 */
public interface ShapeContainerStyle extends ContainerStyle {
    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Shape</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
     * @see #setShape(ContainerShape)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getShapeContainerStyle_Shape()
     * @model required="true"
     * @generated
     */
    ContainerShape getShape();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getShape
     * <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerShape
     * @see #getShape()
     * @generated
     */
    void setShape(ContainerShape value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Background Color</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Background Color</em>' containment
     *         reference.
     * @see #setBackgroundColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getShapeContainerStyle_BackgroundColor()
     * @model containment="true" required="true"
     * @generated
     */
    Color getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ShapeContainerStyle#getBackgroundColor
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' containment
     *            reference.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(Color value);

} // ShapeContainerStyle
