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

import org.eclipse.sirius.viewpoint.description.FeatureExtensionDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DFeature Extension</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.DFeatureExtension#getDescription <em>Description</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDFeatureExtension()
 * @model abstract="true"
 * @generated
 */
public interface DFeatureExtension extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Description</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Description</em>' reference.
     * @see #setDescription(FeatureExtensionDescription)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getDFeatureExtension_Description()
     * @model required="true"
     * @generated
     */
    FeatureExtensionDescription getDescription();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.DFeatureExtension#getDescription
     * <em>Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Description</em>' reference.
     * @see #getDescription()
     * @generated
     */
    void setDescription(FeatureExtensionDescription value);

} // DFeatureExtension
