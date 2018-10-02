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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Event End</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.ordering.EventEnd#getSemanticEnd <em>Semantic End</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getEventEnd()
 * @model abstract="true"
 * @generated
 */
public interface EventEnd extends EObject {
    /**
     * Returns the value of the '<em><b>Semantic End</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic End</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic End</em>' reference.
     * @see #setSemanticEnd(EObject)
     * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage#getEventEnd_SemanticEnd()
     * @model required="true"
     * @generated
     */
    EObject getSemanticEnd();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.ordering.EventEnd#getSemanticEnd <em>Semantic
     * End</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic End</em>' reference.
     * @see #getSemanticEnd()
     * @generated
     */
    void setSemanticEnd(EObject value);

} // EventEnd
