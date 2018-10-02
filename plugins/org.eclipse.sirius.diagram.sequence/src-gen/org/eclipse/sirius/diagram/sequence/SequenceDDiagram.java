/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.sequence;

import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering;
import org.eclipse.sirius.diagram.sequence.ordering.InstanceRolesOrdering;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DDiagram</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getSemanticOrdering <em>Semantic Ordering</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getGraphicalOrdering <em>Graphical
 * Ordering</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getInstanceRoleSemanticOrdering <em>Instance Role
 * Semantic Ordering</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.SequencePackage#getSequenceDDiagram()
 * @model
 * @generated
 */
public interface SequenceDDiagram extends DSemanticDiagram {
    /**
     * Returns the value of the '<em><b>Semantic Ordering</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Ordering</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Ordering</em>' containment reference.
     * @see #setSemanticOrdering(EventEndsOrdering)
     * @see org.eclipse.sirius.diagram.sequence.SequencePackage#getSequenceDDiagram_SemanticOrdering()
     * @model containment="true" transient="true"
     * @generated
     */
    EventEndsOrdering getSemanticOrdering();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getSemanticOrdering
     * <em>Semantic Ordering</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Ordering</em>' containment reference.
     * @see #getSemanticOrdering()
     * @generated
     */
    void setSemanticOrdering(EventEndsOrdering value);

    /**
     * Returns the value of the '<em><b>Graphical Ordering</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Graphical Ordering</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Graphical Ordering</em>' containment reference.
     * @see #setGraphicalOrdering(EventEndsOrdering)
     * @see org.eclipse.sirius.diagram.sequence.SequencePackage#getSequenceDDiagram_GraphicalOrdering()
     * @model containment="true" transient="true"
     * @generated
     */
    EventEndsOrdering getGraphicalOrdering();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getGraphicalOrdering
     * <em>Graphical Ordering</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Graphical Ordering</em>' containment reference.
     * @see #getGraphicalOrdering()
     * @generated
     */
    void setGraphicalOrdering(EventEndsOrdering value);

    /**
     * Returns the value of the '<em><b>Instance Role Semantic Ordering</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance Role Semantic Ordering</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Instance Role Semantic Ordering</em>' containment reference.
     * @see #setInstanceRoleSemanticOrdering(InstanceRolesOrdering)
     * @see org.eclipse.sirius.diagram.sequence.SequencePackage#getSequenceDDiagram_InstanceRoleSemanticOrdering()
     * @model containment="true" transient="true"
     * @generated
     */
    InstanceRolesOrdering getInstanceRoleSemanticOrdering();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.SequenceDDiagram#getInstanceRoleSemanticOrdering <em>Instance Role
     * Semantic Ordering</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Instance Role Semantic Ordering</em>' containment reference.
     * @see #getInstanceRoleSemanticOrdering()
     * @generated
     */
    void setInstanceRoleSemanticOrdering(InstanceRolesOrdering value);

} // SequenceDDiagram
