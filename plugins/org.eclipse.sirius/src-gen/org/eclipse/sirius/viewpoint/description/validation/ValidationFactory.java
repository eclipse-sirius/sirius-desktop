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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage
 * @generated
 */
public interface ValidationFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ValidationFactory eINSTANCE = org.eclipse.sirius.viewpoint.description.validation.impl.ValidationFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Set</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Set</em>'.
     * @generated
     */
    ValidationSet createValidationSet();

    /**
     * Returns a new object of class '<em>Semantic Validation Rule</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Semantic Validation Rule</em>'.
     * @generated
     */
    SemanticValidationRule createSemanticValidationRule();

    /**
     * Returns a new object of class '<em>View Validation Rule</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>View Validation Rule</em>'.
     * @generated
     */
    ViewValidationRule createViewValidationRule();

    /**
     * Returns a new object of class '<em>Rule Audit</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Rule Audit</em>'.
     * @generated
     */
    RuleAudit createRuleAudit();

    /**
     * Returns a new object of class '<em>Fix</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Fix</em>'.
     * @generated
     */
    ValidationFix createValidationFix();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ValidationPackage getValidationPackage();

} // ValidationFactory
