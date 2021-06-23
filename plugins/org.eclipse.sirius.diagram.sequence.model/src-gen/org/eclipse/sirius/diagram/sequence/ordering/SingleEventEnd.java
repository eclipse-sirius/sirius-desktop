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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Single Event End</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#isStart <em>Start</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#getSemanticEvent <em>Semantic Event</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getSingleEventEnd()
 * @model
 * @generated
 */
public interface SingleEventEnd extends EventEnd {
    /**
     * Returns the value of the '<em><b>Start</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Start</em>' attribute.
     * @see #setStart(boolean)
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getSingleEventEnd_Start()
     * @model required="true"
     * @generated
     */
    boolean isStart();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#isStart
     * <em>Start</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Start</em>' attribute.
     * @see #isStart()
     * @generated
     */
    void setStart(boolean value);

    /**
     * Returns the value of the '<em><b>Semantic Event</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Event</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Event</em>' reference.
     * @see #setSemanticEvent(EObject)
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getSingleEventEnd_SemanticEvent()
     * @model required="true"
     * @generated
     */
    EObject getSemanticEvent();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.ordering.SingleEventEnd#getSemanticEvent
     * <em>Semantic Event</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Event</em>' reference.
     * @see #getSemanticEvent()
     * @generated
     */
    void setSemanticEvent(EObject value);

} // SingleEventEnd
