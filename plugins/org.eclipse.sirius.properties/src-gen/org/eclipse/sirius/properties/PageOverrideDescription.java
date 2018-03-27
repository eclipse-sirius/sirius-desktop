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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Page Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.PageOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.PageOverrideDescription#getFilterGroupsFromOverriddenPageExpression
 * <em>Filter Groups From Overridden Page Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.PageOverrideDescription#getFilterValidationRulesFromOverriddenPageExpression
 * <em>Filter Validation Rules From Overridden Page Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getPageOverrideDescription()
 * @model
 * @generated
 */
public interface PageOverrideDescription extends AbstractPageDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(PageDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getPageOverrideDescription_Overrides()
     * @model
     * @generated
     */
    PageDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.PageOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(PageDescription value);

    /**
     * Returns the value of the '<em><b>Filter Groups From Overridden Page Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Groups From Overridden Page Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Groups From Overridden Page Expression</em>' attribute.
     * @see #setFilterGroupsFromOverriddenPageExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getPageOverrideDescription_FilterGroupsFromOverriddenPageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterGroupsFromOverriddenPageExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.PageOverrideDescription#getFilterGroupsFromOverriddenPageExpression
     * <em>Filter Groups From Overridden Page Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Groups From Overridden Page Expression</em>' attribute.
     * @see #getFilterGroupsFromOverriddenPageExpression()
     * @generated
     */
    void setFilterGroupsFromOverriddenPageExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Validation Rules From Overridden Page Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Validation Rules From Overridden Page Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Validation Rules From Overridden Page Expression</em>' attribute.
     * @see #setFilterValidationRulesFromOverriddenPageExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getPageOverrideDescription_FilterValidationRulesFromOverriddenPageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterValidationRulesFromOverriddenPageExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.PageOverrideDescription#getFilterValidationRulesFromOverriddenPageExpression
     * <em>Filter Validation Rules From Overridden Page Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Validation Rules From Overridden Page Expression</em>' attribute.
     * @see #getFilterValidationRulesFromOverriddenPageExpression()
     * @generated
     */
    void setFilterValidationRulesFromOverriddenPageExpression(String value);

} // PageOverrideDescription
