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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Abstract Dynamic Mapping For
 * Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterator <em>Iterator</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterableExpression <em>Iterable
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#isForceRefresh <em>Force
 * Refresh</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIfs <em>Ifs</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getFilterIfsFromExtendedDynamicMappingForExpression
 * <em>Filter Ifs From Extended Dynamic Mapping For Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractDynamicMappingForDescription extends AbstractControlDescription {
    /**
     * Returns the value of the '<em><b>Iterator</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Iterator</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Iterator</em>' attribute.
     * @see #setIterator(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription_Iterator()
     * @model required="true"
     * @generated
     */
    String getIterator();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterator
     * <em>Iterator</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Iterator</em>' attribute.
     * @see #getIterator()
     * @generated
     */
    void setIterator(String value);

    /**
     * Returns the value of the '<em><b>Iterable Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Iterable Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Iterable Expression</em>' attribute.
     * @see #setIterableExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription_IterableExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     * @generated
     */
    String getIterableExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getIterableExpression <em>Iterable
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Iterable Expression</em>' attribute.
     * @see #getIterableExpression()
     * @generated
     */
    void setIterableExpression(String value);

    /**
     * Returns the value of the '<em><b>Force Refresh</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Force Refresh</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Force Refresh</em>' attribute.
     * @see #setForceRefresh(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription_ForceRefresh()
     * @model required="true"
     * @generated
     */
    boolean isForceRefresh();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#isForceRefresh
     * <em>Force Refresh</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Force Refresh</em>' attribute.
     * @see #isForceRefresh()
     * @generated
     */
    void setForceRefresh(boolean value);

    /**
     * Returns the value of the '<em><b>Ifs</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.DynamicMappingIfDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ifs</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Ifs</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription_Ifs()
     * @model containment="true" required="true"
     * @generated
     */
    EList<DynamicMappingIfDescription> getIfs();

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(DynamicMappingForDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription_Extends()
     * @model keys="name"
     * @generated
     */
    DynamicMappingForDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(DynamicMappingForDescription value);

    /**
     * Returns the value of the '<em><b>Filter Ifs From Extended Dynamic Mapping For Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Ifs From Extended Dynamic Mapping For Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Ifs From Extended Dynamic Mapping For Expression</em>' attribute.
     * @see #setFilterIfsFromExtendedDynamicMappingForExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingForDescription_FilterIfsFromExtendedDynamicMappingForExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterIfsFromExtendedDynamicMappingForExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingForDescription#getFilterIfsFromExtendedDynamicMappingForExpression
     * <em>Filter Ifs From Extended Dynamic Mapping For Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Ifs From Extended Dynamic Mapping For Expression</em>' attribute.
     * @see #getFilterIfsFromExtendedDynamicMappingForExpression()
     * @generated
     */
    void setFilterIfsFromExtendedDynamicMappingForExpression(String value);

} // AbstractDynamicMappingForDescription
