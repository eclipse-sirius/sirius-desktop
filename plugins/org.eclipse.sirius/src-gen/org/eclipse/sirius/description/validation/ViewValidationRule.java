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
package org.eclipse.sirius.description.validation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.sirius.description.DiagramElementMapping;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>View Validation Rule</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> A validation rule that is applied on a view element.
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.description.validation.ViewValidationRule#getTargets
 * <em>Targets</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.description.validation.ValidationPackage#getViewValidationRule()
 * @model
 * @generated
 */
public interface ViewValidationRule extends ValidationRule {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Targets</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.description.DiagramElementMapping}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Targets</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Targets</em>' reference list.
     * @see org.eclipse.sirius.description.validation.ValidationPackage#getViewValidationRule_Targets()
     * @model type="viewpoint.description.DiagramElementMapping"
     * @generated
     */
    EList<DiagramElementMapping> getTargets();

} // ViewValidationRule
