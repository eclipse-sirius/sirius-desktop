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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Compound Event End</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.CompoundEventEnd#getEventEnds <em>Event Ends</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getCompoundEventEnd()
 * @model
 * @generated
 */
public interface CompoundEventEnd extends EventEnd {
    /**
     * Returns the value of the '<em><b>Event Ends</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Event Ends</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Event Ends</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getCompoundEventEnd_EventEnds()
     * @model containment="true" required="true"
     * @generated
     */
    EList<SingleEventEnd> getEventEnds();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    EList<EObject> getSemanticEvents();

} // CompoundEventEnd
