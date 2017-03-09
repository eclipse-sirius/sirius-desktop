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

import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Custom Expression</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.CustomExpression#getCustomExpression <em>Custom Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getCustomExpression()
 * @model
 * @generated
 */
public interface CustomExpression extends IdentifiedElement, DocumentedElement {
    /**
     * Returns the value of the '<em><b>Custom Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Custom Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Custom Expression</em>' attribute.
     * @see #setCustomExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getCustomExpression_CustomExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getCustomExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.CustomExpression#getCustomExpression <em>Custom
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Custom Expression</em>' attribute.
     * @see #getCustomExpression()
     * @generated
     */
    void setCustomExpression(String value);

} // CustomExpression
