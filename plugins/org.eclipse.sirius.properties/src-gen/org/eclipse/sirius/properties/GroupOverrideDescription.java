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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Group Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.GroupOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterControlsFromOverriddenGroupExpression
 * <em>Filter Controls From Overridden Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterValidationRulesFromOverriddenGroupExpression
 * <em>Filter Validation Rules From Overridden Group Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterConditionalStylesFromOverriddenGroupExpression
 * <em>Filter Conditional Styles From Overridden Group Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupOverrideDescription()
 * @model
 * @generated
 */
public interface GroupOverrideDescription extends AbstractGroupDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(GroupDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupOverrideDescription_Overrides()
     * @model
     * @generated
     */
    GroupDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.GroupOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(GroupDescription value);

    /**
     * Returns the value of the '<em><b>Filter Controls From Overridden Group Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Controls From Overridden Group Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Controls From Overridden Group Expression</em>' attribute.
     * @see #setFilterControlsFromOverriddenGroupExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupOverrideDescription_FilterControlsFromOverriddenGroupExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterControlsFromOverriddenGroupExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterControlsFromOverriddenGroupExpression
     * <em>Filter Controls From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Filter Controls From Overridden Group Expression</em>' attribute.
     * @see #getFilterControlsFromOverriddenGroupExpression()
     * @generated
     */
    void setFilterControlsFromOverriddenGroupExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Validation Rules From Overridden Group Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Validation Rules From Overridden Group Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Validation Rules From Overridden Group Expression</em>' attribute.
     * @see #setFilterValidationRulesFromOverriddenGroupExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupOverrideDescription_FilterValidationRulesFromOverriddenGroupExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterValidationRulesFromOverriddenGroupExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterValidationRulesFromOverriddenGroupExpression
     * <em>Filter Validation Rules From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Validation Rules From Overridden Group Expression</em>' attribute.
     * @see #getFilterValidationRulesFromOverriddenGroupExpression()
     * @generated
     */
    void setFilterValidationRulesFromOverriddenGroupExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Overridden Group Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Overridden Group Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Overridden Group Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromOverriddenGroupExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupOverrideDescription_FilterConditionalStylesFromOverriddenGroupExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromOverriddenGroupExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.GroupOverrideDescription#getFilterConditionalStylesFromOverriddenGroupExpression
     * <em>Filter Conditional Styles From Overridden Group Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Overridden Group Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromOverriddenGroupExpression()
     * @generated
     */
    void setFilterConditionalStylesFromOverriddenGroupExpression(String value);

} // GroupOverrideDescription
