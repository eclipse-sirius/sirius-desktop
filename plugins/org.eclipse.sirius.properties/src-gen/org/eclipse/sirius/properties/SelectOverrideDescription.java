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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Select Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.SelectOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.SelectOverrideDescription#getFilterConditionalStylesFromOverriddenSelectExpression
 * <em>Filter Conditional Styles From Overridden Select Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectOverrideDescription()
 * @model
 * @generated
 */
public interface SelectOverrideDescription extends AbstractSelectDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(SelectDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectOverrideDescription_Overrides()
     * @model keys="name"
     * @generated
     */
    SelectDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.SelectOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(SelectDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Overridden Select Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Overridden Select Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Overridden Select Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromOverriddenSelectExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getSelectOverrideDescription_FilterConditionalStylesFromOverriddenSelectExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromOverriddenSelectExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.SelectOverrideDescription#getFilterConditionalStylesFromOverriddenSelectExpression
     * <em>Filter Conditional Styles From Overridden Select Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Overridden Select Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromOverriddenSelectExpression()
     * @generated
     */
    void setFilterConditionalStylesFromOverriddenSelectExpression(String value);

} // SelectOverrideDescription
