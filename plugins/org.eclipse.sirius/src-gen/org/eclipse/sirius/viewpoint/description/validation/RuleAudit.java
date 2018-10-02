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
package org.eclipse.sirius.viewpoint.description.validation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Rule Audit</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The validation expression. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.RuleAudit#getAuditExpression <em>Audit
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getRuleAudit()
 * @model
 * @generated
 */
public interface RuleAudit extends EObject {
    /**
     * Returns the value of the '<em><b>Audit Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * --> <!-- begin-model-doc --> An expression checked on the model, if the audit fails (return false) then the rule
     * fail. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Audit Expression</em>' attribute.
     * @see #setAuditExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getRuleAudit_AuditExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'"
     * @generated
     */
    String getAuditExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.validation.RuleAudit#getAuditExpression
     * <em>Audit Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Audit Expression</em>' attribute.
     * @see #getAuditExpression()
     * @generated
     */
    void setAuditExpression(String value);

} // RuleAudit
