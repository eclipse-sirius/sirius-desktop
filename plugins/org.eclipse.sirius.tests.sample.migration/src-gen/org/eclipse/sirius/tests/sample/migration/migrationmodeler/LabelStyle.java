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
 * <em><b>Label Style</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The style of a label. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle#getLabelAlignment
 * <em>Label Alignment</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getLabelStyle()
 * @model
 * @generated
 */
public interface LabelStyle extends BasicLabelStyle {
    /**
     * Returns the value of the '<em><b>Label Alignment</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Alignment</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Alignment</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
     * @see #setLabelAlignment(LabelAlignment)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getLabelStyle_LabelAlignment()
     * @model
     * @generated
     */
    LabelAlignment getLabelAlignment();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelStyle#getLabelAlignment
     * <em>Label Alignment</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Alignment</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.LabelAlignment
     * @see #getLabelAlignment()
     * @generated
     */
    void setLabelAlignment(LabelAlignment value);

} // LabelStyle
