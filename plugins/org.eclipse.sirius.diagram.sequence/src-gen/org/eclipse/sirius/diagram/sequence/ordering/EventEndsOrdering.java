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
package org.eclipse.sirius.diagram.sequence.ordering;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Event Ends Ordering</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getSequenceDiagram <em>Sequence
 * Diagram</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getEventEnds <em>Event Ends</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getEventEndsOrdering()
 * @model
 * @generated
 */
public interface EventEndsOrdering extends EObject {
    /**
     * Returns the value of the '<em><b>Sequence Diagram</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sequence Diagram</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sequence Diagram</em>' reference.
     * @see #setSequenceDiagram(SequenceDDiagram)
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getEventEndsOrdering_SequenceDiagram()
     * @model transient="true"
     * @generated
     */
    SequenceDDiagram getSequenceDiagram();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.ordering.EventEndsOrdering#getSequenceDiagram
     * <em>Sequence Diagram</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Sequence Diagram</em>' reference.
     * @see #getSequenceDiagram()
     * @generated
     */
    void setSequenceDiagram(SequenceDDiagram value);

    /**
     * Returns the value of the '<em><b>Event Ends</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.sequence.ordering.EventEnd}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Ends</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Event Ends</em>' reference list.
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getEventEndsOrdering_EventEnds()
     * @model
     * @generated
     */
    EList<EventEnd> getEventEnds();

} // EventEndsOrdering
