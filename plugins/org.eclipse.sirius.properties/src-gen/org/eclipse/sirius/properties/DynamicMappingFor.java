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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Dynamic Mapping For</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.DynamicMappingFor#getIterator
 * <em>Iterator</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.DynamicMappingFor#getIterableExpression
 * <em>Iterable Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.DynamicMappingFor#getIfs
 * <em>Ifs</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor()
 * @model
 * @generated
 */
public interface DynamicMappingFor extends ControlDescription {
    /**
     * Returns the value of the '<em><b>Iterator</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Iterator</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Iterator</em>' attribute.
     * @see #setIterator(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor_Iterator()
     * @model required="true"
     * @generated
     */
    String getIterator();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.DynamicMappingFor#getIterator
     * <em>Iterator</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Iterator</em>' attribute.
     * @see #getIterator()
     * @generated
     */
    void setIterator(String value);

    /**
     * Returns the value of the '<em><b>Iterable Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Iterable Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Iterable Expression</em>' attribute.
     * @see #setIterableExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor_IterableExpression()
     * @model dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        required="true"
     * @generated
     */
    String getIterableExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.DynamicMappingFor#getIterableExpression
     * <em>Iterable Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Iterable Expression</em>' attribute.
     * @see #getIterableExpression()
     * @generated
     */
    void setIterableExpression(String value);

    /**
     * Returns the value of the '<em><b>Ifs</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.properties.DynamicMappingIf}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ifs</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Ifs</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getDynamicMappingFor_Ifs()
     * @model containment="true" required="true"
     * @generated
     */
    EList<DynamicMappingIf> getIfs();

} // DynamicMappingFor
