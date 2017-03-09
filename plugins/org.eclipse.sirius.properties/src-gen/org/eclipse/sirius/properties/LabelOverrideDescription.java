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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Label Override Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.LabelOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.LabelOverrideDescription#getFilterConditionalStylesFromOverriddenLabelExpression
 * <em>Filter Conditional Styles From Overridden Label Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.LabelOverrideDescription#getFilterActionsFromOverriddenLabelExpression
 * <em>Filter Actions From Overridden Label Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getLabelOverrideDescription()
 * @model
 * @generated
 */
public interface LabelOverrideDescription extends AbstractLabelDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(LabelDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getLabelOverrideDescription_Overrides()
     * @model
     * @generated
     */
    LabelDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.LabelOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(LabelDescription value);

    /**
     * Returns the value of the '<em><b>Filter Conditional Styles From Overridden Label Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Conditional Styles From Overridden Label Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Conditional Styles From Overridden Label Expression</em>' attribute.
     * @see #setFilterConditionalStylesFromOverriddenLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getLabelOverrideDescription_FilterConditionalStylesFromOverriddenLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterConditionalStylesFromOverriddenLabelExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.LabelOverrideDescription#getFilterConditionalStylesFromOverriddenLabelExpression
     * <em>Filter Conditional Styles From Overridden Label Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Conditional Styles From Overridden Label Expression</em>' attribute.
     * @see #getFilterConditionalStylesFromOverriddenLabelExpression()
     * @generated
     */
    void setFilterConditionalStylesFromOverriddenLabelExpression(String value);

    /**
     * Returns the value of the '<em><b>Filter Actions From Overridden Label Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Actions From Overridden Label Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Actions From Overridden Label Expression</em>' attribute.
     * @see #setFilterActionsFromOverriddenLabelExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getLabelOverrideDescription_FilterActionsFromOverriddenLabelExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterActionsFromOverriddenLabelExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.LabelOverrideDescription#getFilterActionsFromOverriddenLabelExpression
     * <em>Filter Actions From Overridden Label Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Filter Actions From Overridden Label Expression</em>' attribute.
     * @see #getFilterActionsFromOverriddenLabelExpression()
     * @generated
     */
    void setFilterActionsFromOverriddenLabelExpression(String value);

} // LabelOverrideDescription
