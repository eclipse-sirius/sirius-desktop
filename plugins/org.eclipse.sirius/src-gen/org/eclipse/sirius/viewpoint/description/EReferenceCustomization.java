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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>EReference Customization</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getReferenceName <em>Reference
 * Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEReferenceCustomization()
 * @model
 * @generated
 */
public interface EReferenceCustomization extends EStructuralFeatureCustomization {
    /**
     * Returns the value of the '<em><b>Reference Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference Name</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reference Name</em>' attribute.
     * @see #setReferenceName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEReferenceCustomization_ReferenceName()
     * @model required="true"
     * @generated
     */
    String getReferenceName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getReferenceName
     * <em>Reference Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Reference Name</em>' attribute.
     * @see #getReferenceName()
     * @generated
     */
    void setReferenceName(String value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value</em>' reference.
     * @see #setValue(EObject)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEReferenceCustomization_Value()
     * @model
     * @generated
     */
    EObject getValue();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.EReferenceCustomization#getValue
     * <em>Value</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' reference.
     * @see #getValue()
     * @generated
     */
    void setValue(EObject value);

} // EReferenceCustomization
