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
 * <em><b>Abstract Node Representation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getBordereds
 * <em>Bordereds</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getOwnedStyle
 * <em>Owned Style</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractNodeRepresentation()
 * @model abstract="true"
 * @generated
 */
public interface AbstractNodeRepresentation extends AbstractRepresentation {
    /**
     * Returns the value of the '<em><b>Bordereds</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bordereds</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Bordereds</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractNodeRepresentation_Bordereds()
     * @model containment="true"
     * @generated
     */
    EList<Bordered> getBordereds();

    /**
     * Returns the value of the '<em><b>Owned Style</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The style of the node. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Style</em>' containment reference.
     * @see #setOwnedStyle(NodeStyle)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractNodeRepresentation_OwnedStyle()
     * @model containment="true"
     * @generated
     */
    NodeStyle getOwnedStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractNodeRepresentation#getOwnedStyle
     * <em>Owned Style</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owned Style</em>' containment
     *            reference.
     * @see #getOwnedStyle()
     * @generated
     */
    void setOwnedStyle(NodeStyle value);

} // AbstractNodeRepresentation
