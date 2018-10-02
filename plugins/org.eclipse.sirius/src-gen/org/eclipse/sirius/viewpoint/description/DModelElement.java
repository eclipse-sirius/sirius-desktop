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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DModel Element</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DModelElement#getEAnnotations <em>EAnnotations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDModelElement()
 * @model abstract="true"
 * @generated
 */
public interface DModelElement extends EObject {
    /**
     * Returns the value of the '<em><b>EAnnotations</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotation}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>EAnnotations</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>EAnnotations</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDModelElement_EAnnotations()
     * @model containment="true"
     * @generated
     */
    EList<DAnnotation> getEAnnotations();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    DAnnotation getDAnnotation(String source);

} // DModelElement
