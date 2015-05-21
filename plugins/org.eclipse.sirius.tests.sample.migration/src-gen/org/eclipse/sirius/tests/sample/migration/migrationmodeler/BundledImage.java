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
 * <em><b>Bundled Image</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getColor
 * <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBundledImage()
 * @model
 * @generated
 */
public interface BundledImage extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The shape to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
     * @see #setShape(BundledImageShape)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBundledImage_Shape()
     * @model required="true"
     * @generated
     */
    BundledImageShape getShape();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getShape
     * <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImageShape
     * @see #getShape()
     * @generated
     */
    void setShape(BundledImageShape value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBundledImage_Color()
     * @model containment="true" required="true"
     * @generated
     */
    Color getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BundledImage#getColor
     * <em>Color</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(Color value);

} // BundledImage
