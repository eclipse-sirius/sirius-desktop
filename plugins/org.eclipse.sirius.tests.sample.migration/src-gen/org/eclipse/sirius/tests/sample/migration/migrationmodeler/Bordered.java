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
 * <em><b>Bordered</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Bordered#getBorderedRepresentations
 * <em>Bordered Representations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBordered()
 * @model
 * @generated
 */
public interface Bordered extends AbstractNode {

    /**
     * Returns the value of the '<em><b>Bordered Representations</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BorderedRepresentation}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bordered Representations</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Bordered Representations</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBordered_BorderedRepresentations()
     * @model containment="true" required="true"
     * @generated
     */
    EList<BorderedRepresentation> getBorderedRepresentations();
} // Bordered
