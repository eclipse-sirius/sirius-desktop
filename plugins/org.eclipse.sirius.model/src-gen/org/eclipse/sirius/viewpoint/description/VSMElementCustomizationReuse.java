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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>VSM Element Customization Reuse</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getReuse <em>Reuse</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getAppliedOn <em>Applied
 * On</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomizationReuse()
 * @model
 * @generated
 */
public interface VSMElementCustomizationReuse extends IVSMElementCustomization {
    /**
     * Returns the value of the '<em><b>Reuse</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reuse</em>' reference list isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Reuse</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomizationReuse_Reuse()
     * @model required="true"
     * @generated
     */
    EList<EStructuralFeatureCustomization> getReuse();

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
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomizationReuse_AppliedOn()
     * @model required="true"
     * @generated
     */
    EList<EObject> getAppliedOn();

} // VSMElementCustomizationReuse
