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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Interpolated Color</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> Describes a color which varies between two level color depending on the values of
 * dynamically computed expressions.
 *
 * @Deprecated : Describes a color which varies between two extremes (red and green) depending on the values of
 *             dynamically computed expressions. <!-- end-model-doc -->
 *
 *             <p>
 *             The following features are supported:
 *             </p>
 *             <ul>
 *             <li>{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorValueComputationExpression
 *             <em>Color Value Computation Expression</em>}</li>
 *             <li>{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMinValueComputationExpression
 *             <em>Min Value Computation Expression</em>}</li>
 *             <li>{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMaxValueComputationExpression
 *             <em>Max Value Computation Expression</em>}</li>
 *             <li>{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorSteps <em>Color Steps</em>}
 *             </li>
 *             </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInterpolatedColor()
 * @model
 * @generated
 */
public interface InterpolatedColor extends ColorDescription, UserColor {
    /**
     * Returns the value of the ' <em><b>Color Value Computation Expression</b></em>' attribute. The default value is
     * <code>"1"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An expression computing
     * the value of the color. The value of the color must be include in the scale bounds <!-- end-model-doc -->
     *
     * @return the value of the '<em>Color Value Computation Expression</em>' attribute.
     * @see #setColorValueComputationExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInterpolatedColor_ColorValueComputationExpression()
     * @model default="1" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation= "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getColorValueComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getColorValueComputationExpression <em>Color
     * Value Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color Value Computation Expression</em>' attribute.
     * @see #getColorValueComputationExpression()
     * @generated
     */
    void setColorValueComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Min Value Computation Expression</b></em>' attribute. The default value is
     * <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Value Computation Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Min Value Computation Expression</em>' attribute.
     * @see #setMinValueComputationExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInterpolatedColor_MinValueComputationExpression()
     * @model default="0" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getMinValueComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMinValueComputationExpression <em>Min Value
     * Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Min Value Computation Expression</em>' attribute.
     * @see #getMinValueComputationExpression()
     * @generated
     */
    void setMinValueComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Max Value Computation Expression</b></em>' attribute. The default value is
     * <code>"10"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Value Computation Expression</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Max Value Computation Expression</em>' attribute.
     * @see #setMaxValueComputationExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInterpolatedColor_MaxValueComputationExpression()
     * @model default="10" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getMaxValueComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.InterpolatedColor#getMaxValueComputationExpression <em>Max Value
     * Computation Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Max Value Computation Expression</em>' attribute.
     * @see #getMaxValueComputationExpression()
     * @generated
     */
    void setMaxValueComputationExpression(String value);

    /**
     * Returns the value of the '<em><b>Color Steps</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.ColorStep}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color Steps</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Color Steps</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInterpolatedColor_ColorSteps()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ColorStep> getColorSteps();

} // InterpolatedColor
