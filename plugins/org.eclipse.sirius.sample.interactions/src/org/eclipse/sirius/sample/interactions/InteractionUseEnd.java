/**
 * Copyright (c) 2007, 2025 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.interactions;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Interaction Use End</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.sample.interactions.InteractionUseEnd#getOwner <em>Owner</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUseEnd()
 * @model
 * @generated
 */
public interface InteractionUseEnd extends AbstractEnd {
    /**
     * Returns the value of the '<em><b>Owner</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owner</em>' reference.
     * @see #setOwner(InteractionUse)
     * @see org.eclipse.sirius.sample.interactions.InteractionsPackage#getInteractionUseEnd_Owner()
     * @model required="true"
     * @generated
     */
    InteractionUse getOwner();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.sample.interactions.InteractionUseEnd#getOwner <em>Owner</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Owner</em>' reference.
     * @see #getOwner()
     * @generated
     */
    void setOwner(InteractionUse value);

} // InteractionUseEnd
