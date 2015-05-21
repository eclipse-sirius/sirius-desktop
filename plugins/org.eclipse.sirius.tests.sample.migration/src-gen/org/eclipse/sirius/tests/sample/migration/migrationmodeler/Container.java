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
 * <em><b>Container</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container#getContainerRepresentations
 * <em>Container Representations</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container#getElements
 * <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getContainer()
 * @model
 * @generated
 */
public interface Container extends GraphicalElement {

    /**
     * Returns the value of the '<em><b>Container Representations</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.ContainerRepresentation}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Container Representations</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Container Representations</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getContainer_ContainerRepresentations()
     * @model containment="true" required="true"
     * @generated
     */
    EList<ContainerRepresentation> getContainerRepresentations();

    /**
     * Returns the value of the '<em><b>Elements</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GraphicalElement}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Elements</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Elements</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getContainer_Elements()
     * @model containment="true"
     * @generated
     */
    EList<GraphicalElement> getElements();
} // Container
