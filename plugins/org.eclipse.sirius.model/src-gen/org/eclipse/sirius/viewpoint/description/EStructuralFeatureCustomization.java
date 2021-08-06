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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>EStructural Feature Customization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#getAppliedOn <em>Applied
 * On</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#isApplyOnAll <em>Apply On
 * All</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEStructuralFeatureCustomization()
 * @model abstract="true"
 * @generated
 */
public interface EStructuralFeatureCustomization extends EObject {
    /**
     * Returns the value of the '<em><b>Applied On</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Applied On</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Applied On</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEStructuralFeatureCustomization_AppliedOn()
     * @model
     * @generated
     */
    EList<EObject> getAppliedOn();

    /**
     * Returns the value of the '<em><b>Apply On All</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Apply On All</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Apply On All</em>' attribute.
     * @see #setApplyOnAll(boolean)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEStructuralFeatureCustomization_ApplyOnAll()
     * @model
     * @generated
     */
    boolean isApplyOnAll();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization#isApplyOnAll <em>Apply On
     * All</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Apply On All</em>' attribute.
     * @see #isApplyOnAll()
     * @generated
     */
    void setApplyOnAll(boolean value);

} // EStructuralFeatureCustomization
