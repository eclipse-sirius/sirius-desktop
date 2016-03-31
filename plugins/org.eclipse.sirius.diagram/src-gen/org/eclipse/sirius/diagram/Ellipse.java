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
 * <em><b>Ellipse</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The ellipse style to display a node as an ellipse.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.Ellipse#getHorizontalDiameter
 * <em>Horizontal Diameter</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.Ellipse#getVerticalDiameter
 * <em>Vertical Diameter</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.Ellipse#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getEllipse()
 * @model
 * @generated
 */
public interface Ellipse extends NodeStyle {
    /**
     * Returns the value of the '<em><b>Horizontal Diameter</b></em>' attribute.
     * The default value is <code>"0"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The horizontal diameter size of
     * the ellipse. (Semimajor axis) <!-- end-model-doc -->
     *
     * @return the value of the '<em>Horizontal Diameter</em>' attribute.
     * @see #setHorizontalDiameter(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEllipse_HorizontalDiameter()
     * @model default="0"
     * @generated
     */
    Integer getHorizontalDiameter();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.Ellipse#getHorizontalDiameter
     * <em>Horizontal Diameter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Horizontal Diameter</em>' attribute.
     * @see #getHorizontalDiameter()
     * @generated
     */
    void setHorizontalDiameter(Integer value);

    /**
     * Returns the value of the '<em><b>Vertical Diameter</b></em>' attribute.
     * The default value is <code>"0"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The vertical diameter of the
     * ellipse. (Semiminor axis) <!-- end-model-doc -->
     *
     * @return the value of the '<em>Vertical Diameter</em>' attribute.
     * @see #setVerticalDiameter(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEllipse_VerticalDiameter()
     * @model default="0"
     * @generated
     */
    Integer getVerticalDiameter();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.Ellipse#getVerticalDiameter
     * <em>Vertical Diameter</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Vertical Diameter</em>' attribute.
     * @see #getVerticalDiameter()
     * @generated
     */
    void setVerticalDiameter(Integer value);

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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEllipse_Color()
     * @model dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.Ellipse#getColor
     * <em>Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' attribute.
     * @see #getColor()
     * @generated
     */
    void setColor(RGBValues value);

} // Ellipse
