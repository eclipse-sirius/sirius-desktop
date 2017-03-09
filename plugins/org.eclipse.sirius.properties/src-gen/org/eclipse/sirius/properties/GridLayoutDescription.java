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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Grid Layout Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.GridLayoutDescription#getNumberOfColumns <em>Number Of Columns</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.GridLayoutDescription#isMakeColumnsWithEqualWidth <em>Make Columns With
 * Equal Width</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getGridLayoutDescription()
 * @model
 * @generated
 */
public interface GridLayoutDescription extends LayoutDescription {
    /**
     * Returns the value of the '<em><b>Number Of Columns</b></em>' attribute. The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Number Of Columns</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Number Of Columns</em>' attribute.
     * @see #setNumberOfColumns(int)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGridLayoutDescription_NumberOfColumns()
     * @model default="1"
     * @generated
     */
    int getNumberOfColumns();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.GridLayoutDescription#getNumberOfColumns <em>Number
     * Of Columns</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Number Of Columns</em>' attribute.
     * @see #getNumberOfColumns()
     * @generated
     */
    void setNumberOfColumns(int value);

    /**
     * Returns the value of the '<em><b>Make Columns With Equal Width</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Make Columns With Equal Width</em>' attribute isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Make Columns With Equal Width</em>' attribute.
     * @see #setMakeColumnsWithEqualWidth(boolean)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGridLayoutDescription_MakeColumnsWithEqualWidth()
     * @model
     * @generated
     */
    boolean isMakeColumnsWithEqualWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.GridLayoutDescription#isMakeColumnsWithEqualWidth
     * <em>Make Columns With Equal Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Make Columns With Equal Width</em>' attribute.
     * @see #isMakeColumnsWithEqualWidth()
     * @generated
     */
    void setMakeColumnsWithEqualWidth(boolean value);

} // GridLayoutDescription
