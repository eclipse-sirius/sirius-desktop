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
 * <em><b>Bordered Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderSize
 * <em>Border Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderColor
 * <em>Border Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBorderedStyle()
 * @model
 * @generated
 */
public interface BorderedStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Border Size</b></em>' attribute. The
     * default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Size</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Size</em>' attribute.
     * @see #setBorderSize(int)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBorderedStyle_BorderSize()
     * @model default="0" required="true"
     * @generated
     */
    int getBorderSize();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderSize
     * <em>Border Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Size</em>' attribute.
     * @see #getBorderSize()
     * @generated
     */
    void setBorderSize(int value);

    /**
     * Returns the value of the '<em><b>Border Color</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Color</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Color</em>' containment reference.
     * @see #setBorderColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBorderedStyle_BorderColor()
     * @model containment="true"
     * @generated
     */
    Color getBorderColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedStyle#getBorderColor
     * <em>Border Color</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Color</em>' containment
     *            reference.
     * @see #getBorderColor()
     * @generated
     */
    void setBorderColor(Color value);

} // BorderedStyle
