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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Set</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A set of validation rules. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getOwnedRules <em>Owned Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getReusedRules <em>Reused
 * Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getAllRules <em>All Rules</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationSet()
 * @model
 * @generated
 */
public interface ValidationSet extends DocumentedElement {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The name of the set. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationSet_Name()
     * @model default=""
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.validation.ValidationSet#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Owned Rules</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule} . <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The validation rules owned by this set. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Owned Rules</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationSet_OwnedRules()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ValidationRule> getOwnedRules();

    /**
     * Returns the value of the '<em><b>Reused Rules</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule} . <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The validations rules that are reused by this set. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Reused Rules</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationSet_ReusedRules()
     * @model
     * @generated
     */
    EList<ValidationRule> getReusedRules();

    /**
     * Returns the value of the '<em><b>All Rules</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.validation.ValidationRule} . <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> All rules of the set. <!-- end-model-doc -->
     *
     * @return the value of the '<em>All Rules</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getValidationSet_AllRules()
     * @model transient="true" changeable="false" volatile="true" derived="true"
     * @generated
     */
    EList<ValidationRule> getAllRules();

} // ValidationSet
