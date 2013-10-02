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
package org.eclipse.sirius.viewpoint.description.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;

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
 * {@link org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule#getTargets
 * <em>Targets</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getViewValidationRule()
 * @model
 * @generated
 */
public interface ViewValidationRule extends ValidationRule {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * Returns the value of the '<em><b>Targets</b></em>' reference list. The
     * list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.DiagramElementMapping}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Targets</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The mapping to validate.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Targets</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getViewValidationRule_Targets()
     * @model
     * @generated
     */
    EList<DiagramElementMapping> getTargets();

} // ViewValidationRule
