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
import org.eclipse.sirius.viewpoint.Style;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Bordered Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSize <em>Border
 * Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.BorderedStyle#getBorderColor <em>Border
 * Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getBorderedStyle()
 * @model
 * @generated
 */
public interface BorderedStyle extends Style {
    /**
     * Returns the value of the '<em><b>Border Size</b></em>' attribute. The
     * default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Size</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Border Size</em>' attribute.
     * @see #setBorderSize(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getBorderedStyle_BorderSize()
     * @model default="0" required="true"
     * @generated
     */
    Integer getBorderSize();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSize
     * <em>Border Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Border Size</em>' attribute.
     * @see #getBorderSize()
     * @generated
     */
    void setBorderSize(Integer value);

    /**
     * Returns the value of the '
     * <em><b>Border Size Computation Expression</b></em>' attribute. The
     * default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Size Computation Expression</em>'
     * attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Border Size Computation Expression</em>'
     *         attribute.
     * @see #setBorderSizeComputationExpression(String)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getBorderedStyle_BorderSizeComputationExpression()
     * @model default="0" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getBorderSizeComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '
     *            <em>Border Size Computation Expression</em>' attribute.
     * @see #getBorderSizeComputationExpression()
     * @generated
     */
    void setBorderSizeComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Border Color</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Color</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Color</em>' attribute.
     * @see #setBorderColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getBorderedStyle_BorderColor()
     * @model dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getBorderColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.BorderedStyle#getBorderColor
     * <em>Border Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Border Color</em>' attribute.
     * @see #getBorderColor()
     * @generated
     */
    void setBorderColor(RGBValues value);

} // BorderedStyle
