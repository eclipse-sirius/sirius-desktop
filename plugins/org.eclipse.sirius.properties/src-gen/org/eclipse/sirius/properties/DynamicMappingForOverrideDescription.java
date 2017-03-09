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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Dynamic Mapping For Override
 * Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getOverrides <em>Overrides</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getFilterIfsFromOverriddenDynamicMappingForExpression
 * <em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingForOverrideDescription()
 * @model
 * @generated
 */
public interface DynamicMappingForOverrideDescription extends AbstractDynamicMappingForDescription, AbstractOverrideDescription {
    /**
     * Returns the value of the '<em><b>Overrides</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Overrides</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Overrides</em>' reference.
     * @see #setOverrides(DynamicMappingForDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingForOverrideDescription_Overrides()
     * @model keys="name"
     * @generated
     */
    DynamicMappingForDescription getOverrides();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getOverrides
     * <em>Overrides</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Overrides</em>' reference.
     * @see #getOverrides()
     * @generated
     */
    void setOverrides(DynamicMappingForDescription value);

    /**
     * Returns the value of the '<em><b>Filter Ifs From Overridden Dynamic Mapping For Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>' attribute.
     * @see #setFilterIfsFromOverriddenDynamicMappingForExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingForOverrideDescription_FilterIfsFromOverriddenDynamicMappingForExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterIfsFromOverriddenDynamicMappingForExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.DynamicMappingForOverrideDescription#getFilterIfsFromOverriddenDynamicMappingForExpression
     * <em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Ifs From Overridden Dynamic Mapping For Expression</em>' attribute.
     * @see #getFilterIfsFromOverriddenDynamicMappingForExpression()
     * @generated
     */
    void setFilterIfsFromOverriddenDynamicMappingForExpression(String value);

} // DynamicMappingForOverrideDescription
