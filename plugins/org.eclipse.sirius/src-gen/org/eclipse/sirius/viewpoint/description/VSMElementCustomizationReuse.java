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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>VSM Element Customization Reuse</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getReuse
 * <em>Reuse</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.VSMElementCustomizationReuse#getAppliedOn
 * <em>Applied On</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomizationReuse()
 * @model
 * @generated
 */
public interface VSMElementCustomizationReuse extends IVSMElementCustomization {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Reuse</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reuse</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Reuse</em>' reference.
     * @see #setReuse(EStructuralFeatureCustomization)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomizationReuse_Reuse()
     * @model
     * @generated
     */
    EList<EStructuralFeatureCustomization> getReuse();

    /**
     * Returns the value of the '<em><b>Applied On</b></em>' reference list. The
     * list contents are of type {@link org.eclipse.emf.ecore.EObject}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Applied On</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Applied On</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomizationReuse_AppliedOn()
     * @model
     * @generated
     */
    EList<EObject> getAppliedOn();

} // VSMElementCustomizationReuse
