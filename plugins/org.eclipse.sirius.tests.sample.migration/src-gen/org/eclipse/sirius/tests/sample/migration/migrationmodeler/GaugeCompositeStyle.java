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
 * <em><b>Gauge Composite Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getAlignment
 * <em>Alignment</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getSections
 * <em>Sections</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeCompositeStyle()
 * @model
 * @generated
 */
public interface GaugeCompositeStyle extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Alignment</b></em>' attribute. The
     * default value is <code>"SQUARE"</code>. The literals are from the
     * enumeration
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The alignment of the gauges <!-- end-model-doc -->
     *
     * @return the value of the '<em>Alignment</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
     * @see #setAlignment(AlignmentKind)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeCompositeStyle_Alignment()
     * @model default="SQUARE"
     * @generated
     */
    AlignmentKind getAlignment();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeCompositeStyle#getAlignment
     * <em>Alignment</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Alignment</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.AlignmentKind
     * @see #getAlignment()
     * @generated
     */
    void setAlignment(AlignmentKind value);

    /**
     * Returns the value of the '<em><b>Sections</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sections</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sections</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeCompositeStyle_Sections()
     * @model containment="true"
     * @generated
     */
    EList<GaugeSection> getSections();

} // GaugeCompositeStyle
