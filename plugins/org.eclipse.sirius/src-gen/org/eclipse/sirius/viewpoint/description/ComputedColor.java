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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Computed Color</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Describes a color which each value red, blue and green are computed expressions. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.ComputedColor#getRed <em>Red</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.ComputedColor#getGreen <em>Green</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.ComputedColor#getBlue <em>Blue</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getComputedColor()
 * @model
 * @generated
 */
public interface ComputedColor extends UserColor, ColorDescription {
    /**
     * Returns the value of the '<em><b>Red</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> An expression computing the value of the color.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Red</em>' attribute.
     * @see #setRed(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getComputedColor_Red()
     * @model default="" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getRed();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.ComputedColor#getRed <em>Red</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Red</em>' attribute.
     * @see #getRed()
     * @generated
     */
    void setRed(String value);

    /**
     * Returns the value of the '<em><b>Green</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Green</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Green</em>' attribute.
     * @see #setGreen(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getComputedColor_Green()
     * @model default="" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getGreen();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.ComputedColor#getGreen <em>Green</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Green</em>' attribute.
     * @see #getGreen()
     * @generated
     */
    void setGreen(String value);

    /**
     * Returns the value of the '<em><b>Blue</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blue</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Blue</em>' attribute.
     * @see #setBlue(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getComputedColor_Blue()
     * @model default="" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getBlue();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.ComputedColor#getBlue <em>Blue</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Blue</em>' attribute.
     * @see #getBlue()
     * @generated
     */
    void setBlue(String value);

} // ComputedColor
