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

import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Rounded Corner Style Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription#getArcWidth
 * <em>Arc Width</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription#getArcHeight
 * <em>Arc Height</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getRoundedCornerStyleDescription()
 * @model abstract="true"
 * @generated
 */
public interface RoundedCornerStyleDescription extends StyleDescription {
    /**
     * Returns the value of the '<em><b>Arc Width</b></em>' attribute. The
     * default value is <code>"1"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The width of the ellipse used
     * to draw the corners. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Arc Width</em>' attribute.
     * @see #setArcWidth(Integer)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getRoundedCornerStyleDescription_ArcWidth()
     * @model default="1"
     * @generated
     */
    Integer getArcWidth();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription#getArcWidth
     * <em>Arc Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Arc Width</em>' attribute.
     * @see #getArcWidth()
     * @generated
     */
    void setArcWidth(Integer value);

    /**
     * Returns the value of the '<em><b>Arc Height</b></em>' attribute. The
     * default value is <code>"1"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The height of the ellipse used
     * to draw the corners. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Arc Height</em>' attribute.
     * @see #setArcHeight(Integer)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getRoundedCornerStyleDescription_ArcHeight()
     * @model default="1"
     * @generated
     */
    Integer getArcHeight();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription#getArcHeight
     * <em>Arc Height</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Arc Height</em>' attribute.
     * @see #getArcHeight()
     * @generated
     */
    void setArcHeight(Integer value);

} // RoundedCornerStyleDescription
