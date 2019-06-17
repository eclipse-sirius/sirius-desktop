/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.sirius.viewpoint.description.SubVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Acceleo Variable</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable#getComputationExpression <em>Computation
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAcceleoVariable()
 * @model
 * @generated
 */
public interface AcceleoVariable extends VariableContainer, SubVariable {
    /**
     * Returns the value of the '<em><b>Computation Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Computation Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Computation Expression</em>' attribute.
     * @see #setComputationExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getAcceleoVariable_ComputationExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a
     *        Collection&lt;Object&gt; or an Object.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables"
     * @generated
     */
    String getComputationExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.tool.AcceleoVariable#getComputationExpression <em>Computation
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Computation Expression</em>' attribute.
     * @see #getComputationExpression()
     * @generated
     */
    void setComputationExpression(String value);

} // AcceleoVariable
