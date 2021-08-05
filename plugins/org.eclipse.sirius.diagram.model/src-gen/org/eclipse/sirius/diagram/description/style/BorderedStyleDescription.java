/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.viewpoint.description.ColorDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Bordered Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription#getBorderSizeComputationExpression
 * <em>Border Size Computation Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription#getBorderColor <em>Border
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription#getBorderLineStyle <em>Border Line
 * Style</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBorderedStyleDescription()
 * @model
 * @generated
 */
public interface BorderedStyleDescription extends StyleDescription {
    /**
     * Returns the value of the ' <em><b>Border Size Computation Expression</b></em>' attribute. The default value is
     * <code>"0"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An expression computing
     * the size of the border. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Border Size Computation Expression</em>' attribute.
     * @see #setBorderSizeComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBorderedStyleDescription_BorderSizeComputationExpression()
     * @model default="0" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getBorderSizeComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription#getBorderSizeComputationExpression
     * <em>Border Size Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Size Computation Expression</em>' attribute.
     * @see #getBorderSizeComputationExpression()
     * @generated
     */
    void setBorderSizeComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Border Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Border Color</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Color</em>' reference.
     * @see #setBorderColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBorderedStyleDescription_BorderColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getBorderColor();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription#getBorderColor <em>Border
     * Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Color</em>' reference.
     * @see #getBorderColor()
     * @generated
     */
    void setBorderColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Border Line Style</b></em>' attribute. The default value is <code>"solid"</code>
     * . The literals are from the enumeration {@link org.eclipse.sirius.diagram.LineStyle}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The style of the border line. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Border Line Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see #setBorderLineStyle(LineStyle)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBorderedStyleDescription_BorderLineStyle()
     * @model default="solid"
     * @generated
     */
    LineStyle getBorderLineStyle();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.description.style.BorderedStyleDescription#getBorderLineStyle <em>Border Line
     * Style</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Line Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.LineStyle
     * @see #getBorderLineStyle()
     * @generated
     */
    void setBorderLineStyle(LineStyle value);

} // BorderedStyleDescription
