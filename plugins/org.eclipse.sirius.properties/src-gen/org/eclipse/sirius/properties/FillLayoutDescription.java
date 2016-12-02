/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Fill Layout Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.FillLayoutDescription#getOrientation <em>Orientation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getFillLayoutDescription()
 * @model
 * @generated
 */
public interface FillLayoutDescription extends LayoutDescription {
    /**
     * Returns the value of the '<em><b>Orientation</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Orientation</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Orientation</em>' attribute.
     * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * @see #setOrientation(FILL_LAYOUT_ORIENTATION)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getFillLayoutDescription_Orientation()
     * @model
     * @generated
     */
    FILL_LAYOUT_ORIENTATION getOrientation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.FillLayoutDescription#getOrientation
     * <em>Orientation</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Orientation</em>' attribute.
     * @see org.eclipse.sirius.properties.FILL_LAYOUT_ORIENTATION
     * @see #getOrientation()
     * @generated
     */
    void setOrientation(FILL_LAYOUT_ORIENTATION value);

} // FillLayoutDescription
