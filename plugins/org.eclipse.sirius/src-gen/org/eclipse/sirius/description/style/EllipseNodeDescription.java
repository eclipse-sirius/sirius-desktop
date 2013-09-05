/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.style;

import org.eclipse.sirius.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Ellipse Node Description</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> The ellipse style to display a node as an ellipse.
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.style.EllipseNodeDescription#getColor
 * <em>Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.style.EllipseNodeDescription#getHorizontalDiameterComputationExpression
 * <em>Horizontal Diameter Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.description.style.EllipseNodeDescription#getVerticalDiameterComputationExpression
 * <em>Vertical Diameter Computation Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.style.StylePackage#getEllipseNodeDescription()
 * @model
 * @generated
 */
public interface EllipseNodeDescription extends NodeStyleDescription {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Color</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The color of the ellipse. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Color</em>' containment reference.
     * @see #setColor(ColorMapping)
     * @see org.eclipse.sirius.description.style.StylePackage#getEllipseNodeDescription_Color()
     * @model required="true"
     * @generated
     */
    ColorDescription getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.style.EllipseNodeDescription#getColor
     * <em>Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Color</em>' reference.
     * @see #getColor()
     * @generated
     */
    void setColor(ColorDescription value);

    /**
     * Returns the value of the '
     * <em><b>Horizontal Diameter Computation Expression</b></em>' attribute.
     * The default value is <code>"0"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The acceleo expression that
     * computes the horizontal diameter of the ellipse. (Semimajor axis) <!--
     * end-model-doc -->
     * 
     * @return the value of the '
     *         <em>Horizontal Diameter Computation Expression</em>' attribute.
     * @see #setHorizontalDiameterComputationExpression(String)
     * @see org.eclipse.sirius.description.style.StylePackage#getEllipseNodeDescription_HorizontalDiameterComputationExpression()
     * @model default="0"
     *        dataType="org.eclipse.sirius.description.AcceleoExpression"
     * @generated
     */
    String getHorizontalDiameterComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.style.EllipseNodeDescription#getHorizontalDiameterComputationExpression
     * <em>Horizontal Diameter Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '
     *            <em>Horizontal Diameter Computation Expression</em>'
     *            attribute.
     * @see #getHorizontalDiameterComputationExpression()
     * @generated
     */
    void setHorizontalDiameterComputationExpression(String value);

    /**
     * Returns the value of the '
     * <em><b>Vertical Diameter Computation Expression</b></em>' attribute. The
     * default value is <code>"0"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The acceleo expression that
     * computes the vertical diameter of the ellipse. (Semiminor axis) <!--
     * end-model-doc -->
     * 
     * @return the value of the '
     *         <em>Vertical Diameter Computation Expression</em>' attribute.
     * @see #setVerticalDiameterComputationExpression(String)
     * @see org.eclipse.sirius.description.style.StylePackage#getEllipseNodeDescription_VerticalDiameterComputationExpression()
     * @model default="0"
     *        dataType="org.eclipse.sirius.description.AcceleoExpression"
     * @generated
     */
    String getVerticalDiameterComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.style.EllipseNodeDescription#getVerticalDiameterComputationExpression
     * <em>Vertical Diameter Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '
     *            <em>Vertical Diameter Computation Expression</em>' attribute.
     * @see #getVerticalDiameterComputationExpression()
     * @generated
     */
    void setVerticalDiameterComputationExpression(String value);

} // EllipseNodeDescription
