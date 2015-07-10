/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Rule</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A validation rule. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getLevel
 * <em>Level</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getMessage
 * <em>Message</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getAudits
 * <em>Audits</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getFixes
 * <em>Fixes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationRule()
 * @model abstract="true"
 * @generated
 */
public interface ValidationRule extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Level</b></em>' attribute. The default
     * value is <code>"INFO"</code>. The literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Level</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Level</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
     * @see #setLevel(ERROR_LEVEL)
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationRule_Level()
     * @model default="INFO" required="true"
     * @generated
     */
    ERROR_LEVEL getLevel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getLevel
     * <em>Level</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Level</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.description.validation.ERROR_LEVEL
     * @see #getLevel()
     * @generated
     */
    void setLevel(ERROR_LEVEL value);

    /**
     * Returns the value of the '<em><b>Message</b></em>' attribute. The default
     * value is <code>"The element has..."</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Message</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Message</em>' attribute.
     * @see #setMessage(String)
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationRule_Message()
     * @model default="The element has..." dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a string.'"
     * @generated
     */
    String getMessage();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule#getMessage
     * <em>Message</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Message</em>' attribute.
     * @see #getMessage()
     * @generated
     */
    void setMessage(String value);

    /**
     * Returns the value of the '<em><b>Audits</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.RuleAudit}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Audits</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Audits</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationRule_Audits()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<RuleAudit> getAudits();

    /**
     * Returns the value of the '<em><b>Fixes</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationFix}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fixes</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Fixes</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationRule_Fixes()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ValidationFix> getFixes();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Check the rule for the specified element.
     *
     * @param eObj
     *            The object to test. <!-- end-model-doc -->
     * @model
     * @generated
     */
    boolean checkRule(EObject eObj);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model
     * @generated
     */
    String getMessage(EObject eObj);

} // ValidationRule
