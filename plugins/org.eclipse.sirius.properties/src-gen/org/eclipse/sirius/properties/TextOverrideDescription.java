/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Text Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.TextOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.TextOverrideDescription#getFilterConditionalStylesFromOverriddenTextExpression
 * <em>Filter Conditional Styles From Overridden Text Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getTextOverrideDescription()
 * @model
 * @generated
 */
public interface TextOverrideDescription extends AbstractTextDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(TextDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getTextOverrideDescription_Overrides()
     * @model
     * @generated
     */
    TextDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.TextOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(TextDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Overridden Text Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Overridden Text Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Overridden Text Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromOverriddenTextExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getTextOverrideDescription_FilterConditionalStylesFromOverriddenTextExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromOverriddenTextExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.TextOverrideDescription#getFilterConditionalStylesFromOverriddenTextExpression
     * <em>Filter Conditional Styles From Overridden Text Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Overridden Text Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromOverriddenTextExpression()
     * @generated
     */
    void setFilterConditionalStylesFromOverriddenTextExpression(String value);

} // TextOverrideDescription
