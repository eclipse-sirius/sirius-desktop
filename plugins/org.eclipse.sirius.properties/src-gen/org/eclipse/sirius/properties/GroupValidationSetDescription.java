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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Group Validation Set Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.GroupValidationSetDescription#getSemanticValidationRules <em>Semantic
 * Validation Rules</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.GroupValidationSetDescription#getPropertyValidationRules <em>Property
 * Validation Rules</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupValidationSetDescription()
 * @model
 * @generated
 */
public interface GroupValidationSetDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Semantic Validation Rules</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.viewpoint.description.validation.SemanticValidationRule}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Semantic Validation Rules</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Semantic Validation Rules</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupValidationSetDescription_SemanticValidationRules()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<SemanticValidationRule> getSemanticValidationRules();

    /**
     * Returns the value of the '<em><b>Property Validation Rules</b></em>' containment reference list. The list
     * contents are of type {@link org.eclipse.sirius.properties.PropertyValidationRule}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Property Validation Rules</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Property Validation Rules</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getGroupValidationSetDescription_PropertyValidationRules()
     * @model containment="true" keys="name"
     * @generated
     */
    EList<PropertyValidationRule> getPropertyValidationRules();

} // GroupValidationSetDescription
