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
package org.eclipse.sirius.diagram.sequence.description;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Basic Message Mapping</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping#isOblique <em>Oblique</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getBasicMessageMapping()
 * @model
 * @generated
 */
public interface BasicMessageMapping extends MessageMapping {

    /**
     * Returns the value of the '<em><b>Oblique</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Oblique</em>' attribute.
     * @see #setOblique(boolean)
     * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage#getBasicMessageMapping_Oblique()
     * @model required="true"
     * @generated
     */
    boolean isOblique();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.sequence.description.BasicMessageMapping#isOblique
     * <em>Oblique</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Oblique</em>' attribute.
     * @see #isOblique()
     * @generated
     */
    void setOblique(boolean value);
} // BasicMessageMapping
