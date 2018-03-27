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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Hyperlink Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterConditionalStylesFromOverriddenHyperlinkExpression
 * <em>Filter Conditional Styles From Overridden Hyperlink Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterActionsFromOverriddenHyperlinkExpression
 * <em>Filter Actions From Overridden Hyperlink Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getHyperlinkOverrideDescription()
 * @model
 * @generated
 */
public interface HyperlinkOverrideDescription extends AbstractHyperlinkDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(HyperlinkDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getHyperlinkOverrideDescription_Overrides()
     * @model keys="name"
     * @generated
     */
    HyperlinkDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(HyperlinkDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Overridden Hyperlink Expression</b></em>'
     * attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Overridden Hyperlink Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Overridden Hyperlink Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromOverriddenHyperlinkExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getHyperlinkOverrideDescription_FilterConditionalStylesFromOverriddenHyperlinkExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromOverriddenHyperlinkExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterConditionalStylesFromOverriddenHyperlinkExpression
     * <em>Filter Conditional Styles From Overridden Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Overridden Hyperlink Expression</em>'
     *            attribute.
     * @see #getFilterConditionalStylesFromOverriddenHyperlinkExpression()
     * @generated
     */
    void setFilterConditionalStylesFromOverriddenHyperlinkExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Actions From Overridden Hyperlink Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Actions From Overridden Hyperlink Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Actions From Overridden Hyperlink Expression</em>' attribute.
     * @see #setFilterActionsFromOverriddenHyperlinkExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getHyperlinkOverrideDescription_FilterActionsFromOverriddenHyperlinkExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterActionsFromOverriddenHyperlinkExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.HyperlinkOverrideDescription#getFilterActionsFromOverriddenHyperlinkExpression
     * <em>Filter Actions From Overridden Hyperlink Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Actions From Overridden Hyperlink Expression</em>' attribute.
     * @see #getFilterActionsFromOverriddenHyperlinkExpression()
     * @generated
     */
    void setFilterActionsFromOverriddenHyperlinkExpression(String value);

} // HyperlinkOverrideDescription
