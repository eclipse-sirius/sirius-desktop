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
 * <em><b>Square Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The square style to display a node as a square. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.SquareDescription#getWidth
 * <em>Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.SquareDescription#getHeight
 * <em>Height</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.SquareDescription#getColor
 * <em>Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSquareDescription()
 * @model
 * @generated
 */
public interface SquareDescription extends NodeStyleDescription {
    /**
     * Returns the value of the '<em><b>Width</b></em>' attribute. The default
     * value is <code>"0"</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> Return all nodes that have been created with the
     * specified mapping. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Width</em>' attribute.
     * @see #setWidth(Integer)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSquareDescription_Width()
     * @model default="0"
     * @generated
     */
    Integer getWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.SquareDescription#getWidth
     * <em>Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width</em>' attribute.
     * @see #getWidth()
     * @generated
     */
    void setWidth(Integer value);

    /**
     * Returns the value of the '<em><b>Height</b></em>' attribute. The default
     * value is <code>"0"</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The height of the square. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Height</em>' attribute.
     * @see #setHeight(Integer)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSquareDescription_Height()
     * @model default="0"
     * @generated
     */
    Integer getHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.SquareDescription#getHeight
     * <em>Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
    void setHeight(Integer value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Color</em>' reference.
     * @see #setColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSquareDescription_Color()
     * @model required="true"
     * @generated
     */
    ColorDescription getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.SquareDescription#getColor
     * <em>Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' reference.
     * @see #getColor()
     * @generated
     */
    void setColor(ColorDescription value);

} // SquareDescription
