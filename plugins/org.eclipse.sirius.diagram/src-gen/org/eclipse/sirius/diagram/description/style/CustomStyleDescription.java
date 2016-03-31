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

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Custom Style Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.CustomStyleDescription#getId
 * <em>Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getCustomStyleDescription()
 * @model
 * @generated
 */
public interface CustomStyleDescription extends NodeStyleDescription {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Set
     * here an ID to identify your custom style. You'll then be able to provide
     * your own figure implementation for every representation element having a
     * style with this ID. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getCustomStyleDescription_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.CustomStyleDescription#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

} // CustomStyleDescription
