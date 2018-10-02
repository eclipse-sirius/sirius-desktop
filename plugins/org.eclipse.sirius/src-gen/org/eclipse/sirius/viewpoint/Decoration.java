/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint;

import org.eclipse.sirius.viewpoint.description.DecorationDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Decoration</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represent a decoration of a diagram element with a specific icon, based on its relationships
 * with MetaElements of the MetaModel.
 *
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.Decoration#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDecoration()
 * @model
 * @generated
 */
public interface Decoration extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The referenced DecorationDescription. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(DecorationDescription)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDecoration_Description()
     * @model required="true"
     * @generated
     */
    DecorationDescription getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.Decoration#getDescription <em>Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(DecorationDescription value);

} // Decoration
