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
 * <em><b>Node</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node#getNodeRepresentations
 * <em>Node Representations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getNode()
 * @model
 * @generated
 */
public interface Node extends AbstractNode {

    /**
     * Returns the value of the '<em><b>Node Representations</b></em>'
     * containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.NodeRepresentation}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Node Representations</em>' containment
     * reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Node Representations</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getNode_NodeRepresentations()
     * @model containment="true" required="true"
     * @generated
     */
    EList<NodeRepresentation> getNodeRepresentations();
} // Node
