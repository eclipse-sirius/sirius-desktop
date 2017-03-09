/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Property Validation Rule</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.PropertyValidationRule#getTargets <em>Targets</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getPropertyValidationRule()
 * @model
 * @generated
 */
public interface PropertyValidationRule extends ValidationRule {
    /**
     * Returns the value of the '<em><b>Targets</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.WidgetDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Targets</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Targets</em>' reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getPropertyValidationRule_Targets()
     * @model keys="name"
     * @generated
     */
    EList<WidgetDescription> getTargets();

} // PropertyValidationRule
