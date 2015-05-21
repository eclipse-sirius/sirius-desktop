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
 * <em><b>Diagram</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getContainers
 * <em>Containers</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getNodes
 * <em>Nodes</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getEdges
 * <em>Edges</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getFilters
 * <em>Filters</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Diagram#getLayers
 * <em>Layers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDiagram()
 * @model
 * @generated
 */
public interface Diagram extends Representation {
    /**
     * Returns the value of the '<em><b>Containers</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Container}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Containers</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Containers</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDiagram_Containers()
     * @model containment="true"
     * @generated
     */
    EList<Container> getContainers();

    /**
     * Returns the value of the '<em><b>Nodes</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Node}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Nodes</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDiagram_Nodes()
     * @model containment="true"
     * @generated
     */
    EList<Node> getNodes();

    /**
     * Returns the value of the '<em><b>Edges</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Edge}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Edges</em>' reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Edges</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDiagram_Edges()
     * @model containment="true"
     * @generated
     */
    EList<Edge> getEdges();

    /**
     * Returns the value of the '<em><b>Filters</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Filter}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filters</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filters</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDiagram_Filters()
     * @model containment="true"
     * @generated
     */
    EList<Filter> getFilters();

    /**
     * Returns the value of the '<em><b>Layers</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.Layer}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layers</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layers</em>' containment reference list.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getDiagram_Layers()
     * @model containment="true"
     * @generated
     */
    EList<Layer> getLayers();

} // Diagram
