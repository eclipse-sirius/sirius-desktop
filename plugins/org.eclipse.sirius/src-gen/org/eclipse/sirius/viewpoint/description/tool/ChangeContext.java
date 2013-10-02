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
package org.eclipse.sirius.viewpoint.description.tool;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Change Context</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> This operation allows to change the execution
 * context. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext#getBrowseExpression
 * <em>Browse Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getChangeContext()
 * @model
 * @generated
 */
public interface ChangeContext extends ContainerModelOperation {
    /**
     * Returns the value of the '<em><b>Browse Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Browse Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> Expression Acceleo that
     * browse to a new context. <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Browse Expression</em>' attribute.
     * @see #setBrowseExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getChangeContext_BrowseExpression()
     * @model dataType="viewpoint.description.AcceleoExpression"
     * @generated
     */
    String getBrowseExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.tool.ChangeContext#getBrowseExpression
     * <em>Browse Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Browse Expression</em>' attribute.
     * @see #getBrowseExpression()
     * @generated
     */
    void setBrowseExpression(String value);

} // ChangeContext
