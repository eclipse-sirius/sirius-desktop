/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Variable Filter</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> A filter that filters viewpoint elements considering an expression and some variables
 * defined by the user.
 *
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.filter.VariableFilter#getOwnedVariables <em>Owned Variables</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.description.filter.VariableFilter#getSemanticConditionExpression <em>Semantic
 * Condition Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getVariableFilter()
 * @model
 * @generated
 */
public interface VariableFilter extends Filter {
    /**
     * Returns the value of the '<em><b>Owned Variables</b></em>' containment reference list. The list contents are of
     * type {@link org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Variables</em>' containment reference list isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Variables</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getVariableFilter_OwnedVariables()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<InteractiveVariableDescription> getOwnedVariables();

    /**
     * Returns the value of the '<em><b>Semantic Condition Expression</b></em>' attribute. The default value is
     * <code>""</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The condition to apply on
     * the semantic element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Semantic Condition Expression</em>' attribute.
     * @see #setSemanticConditionExpression(String)
     * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getVariableFilter_SemanticConditionExpression()
     * @model default="" dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getSemanticConditionExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.description.filter.VariableFilter#getSemanticConditionExpression <em>Semantic
     * Condition Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Semantic Condition Expression</em>' attribute.
     * @see #getSemanticConditionExpression()
     * @generated
     */
    void setSemanticConditionExpression(String value);

} // VariableFilter
