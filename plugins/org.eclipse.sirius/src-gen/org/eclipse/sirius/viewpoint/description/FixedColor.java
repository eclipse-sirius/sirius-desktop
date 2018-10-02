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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Fixed Color</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A fixed color description defined by a specific RGB triplet. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.FixedColor#getRed <em>Red</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.FixedColor#getGreen <em>Green</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.FixedColor#getBlue <em>Blue</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getFixedColor()
 * @model
 * @generated
 */
public interface FixedColor extends ColorDescription {
    /**
     * Returns the value of the '<em><b>Red</b></em>' attribute. The default value is <code>"125"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The red value of the RGB color. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Red</em>' attribute.
     * @see #setRed(int)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getFixedColor_Red()
     * @model default="125" required="true"
     * @generated
     */
    int getRed();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.FixedColor#getRed <em>Red</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Red</em>' attribute.
     * @see #getRed()
     * @generated
     */
    void setRed(int value);

    /**
     * Returns the value of the '<em><b>Green</b></em>' attribute. The default value is <code>"125"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The green value of the RGB color. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Green</em>' attribute.
     * @see #setGreen(int)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getFixedColor_Green()
     * @model default="125" required="true"
     * @generated
     */
    int getGreen();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.FixedColor#getGreen <em>Green</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Green</em>' attribute.
     * @see #getGreen()
     * @generated
     */
    void setGreen(int value);

    /**
     * Returns the value of the '<em><b>Blue</b></em>' attribute. The default value is <code>"125"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The blue value of the RGB color. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Blue</em>' attribute.
     * @see #setBlue(int)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getFixedColor_Blue()
     * @model default="125" required="true"
     * @generated
     */
    int getBlue();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.FixedColor#getBlue <em>Blue</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Blue</em>' attribute.
     * @see #getBlue()
     * @generated
     */
    void setBlue(int value);

} // FixedColor
