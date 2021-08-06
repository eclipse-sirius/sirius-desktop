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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Color Step</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A ColorStep is identified by its associatedValue and references an associatedColor
 * (FixedColor).
 *
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedValue <em>Associated Value</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedColor <em>Associated Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getColorStep()
 * @model
 * @generated
 */
public interface ColorStep extends EObject {
    /**
     * Returns the value of the '<em><b>Associated Value</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Associated Value</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Associated Value</em>' attribute.
     * @see #setAssociatedValue(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getColorStep_AssociatedValue()
     * @model default="" dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getAssociatedValue();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedValue
     * <em>Associated Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Associated Value</em>' attribute.
     * @see #getAssociatedValue()
     * @generated
     */
    void setAssociatedValue(String value);

    /**
     * Returns the value of the '<em><b>Associated Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Associated Color</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Associated Color</em>' reference.
     * @see #setAssociatedColor(FixedColor)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getColorStep_AssociatedColor()
     * @model required="true"
     * @generated
     */
    FixedColor getAssociatedColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.ColorStep#getAssociatedColor
     * <em>Associated Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Associated Color</em>' reference.
     * @see #getAssociatedColor()
     * @generated
     */
    void setAssociatedColor(FixedColor value);

} // ColorStep
