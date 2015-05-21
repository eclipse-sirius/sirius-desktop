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
 * <em><b>Lozenge</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getHeight
 * <em>Height</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getColor
 * <em>Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getLozenge()
 * @model
 * @generated
 */
public interface Lozenge extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. The default
     * value is <code>"0"</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The width of the lozenge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(Integer)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getLozenge_Width()
     * @model default="0"
     * @generated
     */
    Integer getWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getWidth
     * <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(Integer value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. The default
     * value is <code>"0"</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The height of the lozenge. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(Integer)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getLozenge_Height()
     * @model default="0"
     * @generated
     */
    Integer getHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(Integer value);

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
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getLozenge_Color()
     * @model containment="true"
     * @generated
     */
    Color getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Lozenge#getColor
     * <em>Color</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(Color value);

} // Lozenge
