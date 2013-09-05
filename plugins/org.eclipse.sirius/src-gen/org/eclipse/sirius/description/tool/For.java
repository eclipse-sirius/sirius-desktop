/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>For</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> This operation allows to iterate a list of elements.
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.description.tool.For#getExpression <em>
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.description.tool.For#getIteratorName <em>
 * Iterator Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.tool.ToolPackage#getFor()
 * @model
 * @generated
 */
public interface For extends ContainerModelOperation {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Acceleo expression that
     * returns all elements to iterate. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Expression</em>' attribute.
     * @see #setExpression(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getFor_Expression()
     * @model required="true"
     * @generated
     */
    String getExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.For#getExpression
     * <em>Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Expression</em>' attribute.
     * @see #getExpression()
     * @generated
     */
    void setExpression(String value);

    /**
     * Returns the value of the '<em><b>Iterator Name</b></em>' attribute. The
     * default value is <code>"i"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Acceleo expression that returns
     * all elements to iterate. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Iterator Name</em>' attribute.
     * @see #setIteratorName(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getFor_IteratorName()
     * @model default="i" required="true"
     * @generated
     */
    String getIteratorName();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.For#getIteratorName
     * <em>Iterator Name</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Iterator Name</em>' attribute.
     * @see #getIteratorName()
     * @generated
     */
    void setIteratorName(String value);

} // For
