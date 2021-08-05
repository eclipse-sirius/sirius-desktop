/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram;

import org.eclipse.sirius.viewpoint.description.TypedVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Typed Variable Value</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.TypedVariableValue#getVariableDefinition <em>Variable Definition</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.TypedVariableValue#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getTypedVariableValue()
 * @model
 * @generated
 */
public interface TypedVariableValue extends VariableValue {
    /**
     * Returns the value of the '<em><b>Variable Definition</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variable Definition</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Variable Definition</em>' reference.
     * @see #setVariableDefinition(TypedVariable)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getTypedVariableValue_VariableDefinition()
     * @model required="true"
     * @generated
     */
    TypedVariable getVariableDefinition();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.TypedVariableValue#getVariableDefinition <em>Variable
     * Definition</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Variable Definition</em>' reference.
     * @see #getVariableDefinition()
     * @generated
     */
    void setVariableDefinition(TypedVariable value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getTypedVariableValue_Value()
     * @model
     * @generated
     */
    String getValue();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.TypedVariableValue#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(String value);

} // TypedVariableValue
