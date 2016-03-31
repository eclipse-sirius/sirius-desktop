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
 * <em><b>Lozenge Node Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The diamond style to display a node as a diamond.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription#getWidthComputationExpression
 * <em>Width Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription#getHeightComputationExpression
 * <em>Height Computation Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription#getColor
 * <em>Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getLozengeNodeDescription()
 * @model
 * @generated
 */
public interface LozengeNodeDescription extends NodeStyleDescription {
    /**
     * Returns the value of the '<em><b>Width Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> An expression to compute the width of the lozenge.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Width Computation Expression</em>'
     *         attribute.
     * @see #setWidthComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getLozengeNodeDescription_WidthComputationExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getWidthComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription#getWidthComputationExpression
     * <em>Width Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Width Computation Expression</em>'
     *            attribute.
     * @see #getWidthComputationExpression()
     * @generated
     */
    void setWidthComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Height Computation Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> An expression to computes the height of the lozenge.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Height Computation Expression</em>'
     *         attribute.
     * @see #setHeightComputationExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getLozengeNodeDescription_HeightComputationExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getHeightComputationExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription#getHeightComputationExpression
     * <em>Height Computation Expression</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Height Computation Expression</em>'
     *            attribute.
     * @see #getHeightComputationExpression()
     * @generated
     */
    void setHeightComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * color of the lozenge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Color</em>' reference.
     * @see #setColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getLozengeNodeDescription_Color()
     * @model required="true"
     * @generated
     */
    ColorDescription getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.LozengeNodeDescription#getColor
     * <em>Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' reference.
     * @see #getColor()
     * @generated
     */
    void setColor(ColorDescription value);

} // LozengeNodeDescription
