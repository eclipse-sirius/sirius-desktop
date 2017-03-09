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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>List Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ListOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ListOverrideDescription#getFilterConditionalStylesFromOverriddenListExpression
 * <em>Filter Conditional Styles From Overridden List Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ListOverrideDescription#getFilterActionsFromOverriddenListExpression
 * <em>Filter Actions From Overridden List Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getListOverrideDescription()
 * @model
 * @generated
 */
public interface ListOverrideDescription extends AbstractListDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(ListDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getListOverrideDescription_Overrides()
     * @model keys="name"
     * @generated
     */
    ListDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.ListOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(ListDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Overridden List Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Overridden List Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Overridden List Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromOverriddenListExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getListOverrideDescription_FilterConditionalStylesFromOverriddenListExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromOverriddenListExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.ListOverrideDescription#getFilterConditionalStylesFromOverriddenListExpression
     * <em>Filter Conditional Styles From Overridden List Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Overridden List Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromOverriddenListExpression()
     * @generated
     */
    void setFilterConditionalStylesFromOverriddenListExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Actions From Overridden List Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Actions From Overridden List Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Actions From Overridden List Expression</em>' attribute.
     * @see #setFilterActionsFromOverriddenListExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getListOverrideDescription_FilterActionsFromOverriddenListExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterActionsFromOverriddenListExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ListOverrideDescription#getFilterActionsFromOverriddenListExpression
     * <em>Filter Actions From Overridden List Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Filter Actions From Overridden List Expression</em>' attribute.
     * @see #getFilterActionsFromOverriddenListExpression()
     * @generated
     */
    void setFilterActionsFromOverriddenListExpression(String value);

} // ListOverrideDescription
