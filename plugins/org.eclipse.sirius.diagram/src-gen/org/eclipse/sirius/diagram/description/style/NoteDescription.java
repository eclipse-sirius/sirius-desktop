/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Note Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.NoteDescription#getColor
 * <em>Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getNoteDescription()
 * @model
 * @generated
 */
public interface NoteDescription extends NodeStyleDescription {
    /**
     * Returns the value of the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * color to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Color</em>' reference.
     * @see #setColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getNoteDescription_Color()
     * @model required="true"
     * @generated
     */
    ColorDescription getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.NoteDescription#getColor
     * <em>Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' reference.
     * @see #getColor()
     * @generated
     */
    void setColor(ColorDescription value);

} // NoteDescription
