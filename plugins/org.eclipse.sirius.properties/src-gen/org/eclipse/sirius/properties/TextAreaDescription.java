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
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Text Area Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents a text area in the user interface. <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.TextAreaDescription#getLineCount
 * <em>Line Count</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getTextAreaDescription()
 * @model
 * @generated
 */
public interface TextAreaDescription extends TextDescription {
    /**
     * Returns the value of the '<em><b>Line Count</b></em>' attribute. The
     * default value is <code>"5"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Count</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Line Count</em>' attribute.
     * @see #setLineCount(int)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getTextAreaDescription_LineCount()
     * @model default="5"
     * @generated
     */
    int getLineCount();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.TextAreaDescription#getLineCount
     * <em>Line Count</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Line Count</em>' attribute.
     * @see #getLineCount()
     * @generated
     */
    void setLineCount(int value);

} // TextAreaDescription
