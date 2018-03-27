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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Toolbar Action</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ToolbarAction#getTooltipExpression <em>Tooltip Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ToolbarAction#getImageExpression <em>Image Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ToolbarAction#getInitialOperation <em>Initial Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getToolbarAction()
 * @model
 * @generated
 */
public interface ToolbarAction extends EObject {
    /**
     * Returns the value of the '<em><b>Tooltip Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tooltip Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tooltip Expression</em>' attribute.
     * @see #setTooltipExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getToolbarAction_TooltipExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getTooltipExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.ToolbarAction#getTooltipExpression <em>Tooltip
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tooltip Expression</em>' attribute.
     * @see #getTooltipExpression()
     * @generated
     */
    void setTooltipExpression(String value);

    /**
     * Returns the value of the '<em><b>Image Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Image Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Image Expression</em>' attribute.
     * @see #setImageExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getToolbarAction_ImageExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getImageExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.ToolbarAction#getImageExpression <em>Image
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Image Expression</em>' attribute.
     * @see #getImageExpression()
     * @generated
     */
    void setImageExpression(String value);

    /**
     * Returns the value of the '<em><b>Initial Operation</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Initial Operation</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Initial Operation</em>' containment reference.
     * @see #setInitialOperation(InitialOperation)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getToolbarAction_InitialOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInitialOperation();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.ToolbarAction#getInitialOperation <em>Initial
     * Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Initial Operation</em>' containment reference.
     * @see #getInitialOperation()
     * @generated
     */
    void setInitialOperation(InitialOperation value);

} // ToolbarAction
