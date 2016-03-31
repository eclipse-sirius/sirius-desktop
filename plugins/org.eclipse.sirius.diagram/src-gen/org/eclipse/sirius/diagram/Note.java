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
package org.eclipse.sirius.diagram;

import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Note</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.Note#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getNote()
 * @model
 * @generated
 */
public interface Note extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Color</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Color</em>' attribute.
     * @see #setColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getNote_Color()
     * @model dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.Note#getColor
     * <em>Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(RGBValues value);

} // Note
