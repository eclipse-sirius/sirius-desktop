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

import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Style</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The super class of all styles. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.Style#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getStyle()
 * @model abstract="true"
 * @generated
 */
public interface Style extends IdentifiedElement, DRefreshable, Customizable {
    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(StyleDescription)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getStyle_Description()
     * @model
     * @generated
     */
    StyleDescription getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.Style#getDescription <em>Description</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(StyleDescription value);

} // Style
