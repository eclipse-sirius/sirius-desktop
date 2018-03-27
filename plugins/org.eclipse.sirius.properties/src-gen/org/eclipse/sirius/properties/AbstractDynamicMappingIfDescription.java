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

import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Dynamic Mapping If Description</b></em>
 * '. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getPredicateExpression <em>Predicate
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getWidget <em>Widget</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getExtends <em>Extends</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingIfDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractDynamicMappingIfDescription extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicate Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Predicate Expression</em>' attribute.
     * @see #setPredicateExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingIfDescription_PredicateExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true"
     * @generated
     */
    String getPredicateExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getPredicateExpression <em>Predicate
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Predicate Expression</em>' attribute.
     * @see #getPredicateExpression()
     * @generated
     */
    void setPredicateExpression(String value);

    /**
     * Returns the value of the '<em><b>Widget</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Widget</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Widget</em>' containment reference.
     * @see #setWidget(WidgetDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingIfDescription_Widget()
     * @model containment="true" required="true"
     * @generated
     */
    WidgetDescription getWidget();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getWidget
     * <em>Widget</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Widget</em>' containment reference.
     * @see #getWidget()
     * @generated
     */
    void setWidget(WidgetDescription value);

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(DynamicMappingIfDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractDynamicMappingIfDescription_Extends()
     * @model keys="name"
     * @generated
     */
    DynamicMappingIfDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractDynamicMappingIfDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(DynamicMappingIfDescription value);

} // AbstractDynamicMappingIfDescription
