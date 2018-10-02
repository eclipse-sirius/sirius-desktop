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

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Semantic Validation Rule</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> A validation rule that is applied on a semantic element. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule#getTargetClass <em>Target
 * Class</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getSemanticValidationRule()
 * @model
 * @generated
 */
public interface SemanticValidationRule extends ValidationRule {
    /**
     * Returns the value of the '<em><b>Target Class</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * <!-- begin-model-doc --> The name of the domain class of the element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Target Class</em>' attribute.
     * @see #setTargetClass(String)
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getSemanticValidationRule_TargetClass()
     * @model dataType="org.eclipse.sirius.viewpoint.description.TypeName" required="true"
     * @generated
     */
    String getTargetClass();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule#getTargetClass <em>Target
     * Class</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Class</em>' attribute.
     * @see #getTargetClass()
     * @generated
     */
    void setTargetClass(String value);

} // SemanticValidationRule
