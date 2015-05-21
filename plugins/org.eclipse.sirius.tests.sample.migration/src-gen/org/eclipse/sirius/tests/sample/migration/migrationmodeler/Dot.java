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
 * <em><b>Dot</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDot()
 * @model
 * @generated
 */
public interface Dot extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The background color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Color</em>' containment
     *         reference.
     * @see #setBackgroundColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDot_BackgroundColor()
     * @model containment="true"
     * @generated
     */
    Color getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Dot#getBackgroundColor
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

} // Dot
