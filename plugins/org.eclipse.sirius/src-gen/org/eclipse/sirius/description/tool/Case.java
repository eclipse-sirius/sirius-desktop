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
 * <em><b>Case</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> If the evaluation of the condition returns true then
 * all operations contains by this case statement will be executed, otherwise
 * all operations will be ignored. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.tool.Case#getConditionExpression
 * <em>Condition Expression</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.tool.ToolPackage#getCase()
 * @model
 * @generated
 */
public interface Case extends SwitchChild {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Condition Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Expression representing the condition, if it returns
     * true, every operation contained by this statement will be executed. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Condition Expression</em>' attribute.
     * @see #setConditionExpression(String)
     * @see org.eclipse.sirius.description.tool.ToolPackage#getCase_ConditionExpression()
     * @model dataType="org.eclipse.sirius.description.InterpretedExpression"
     *        annotation
     *        ="http://www.eclipse.org/emf/2002/GenModel contentassist=''"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getConditionExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.description.tool.Case#getConditionExpression
     * <em>Condition Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Condition Expression</em>'
     *            attribute.
     * @see #getConditionExpression()
     * @generated
     */
    void setConditionExpression(String value);

} // Case
