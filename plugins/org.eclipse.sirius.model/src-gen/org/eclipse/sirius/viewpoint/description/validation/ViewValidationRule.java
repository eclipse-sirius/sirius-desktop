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
import org.eclipse.sirius.viewpoint.description.RepresentationElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>View Validation Rule</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> A validation rule that is applied on a view element. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getViewValidationRule()
 * @model
 * @generated
 */
public interface ViewValidationRule extends ValidationRule {
    /**
     * Returns the value of the '<em><b>Targets</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.RepresentationElementMapping} . <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The mapping to validate. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Targets</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getViewValidationRule_Targets()
     * @model
     * @generated
     */
    EList<RepresentationElementMapping> getTargets();

} // ViewValidationRule
