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
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DAnnotation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DAnnotation#getSource <em>Source</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DAnnotation#getDetails <em>Details</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DAnnotation#getReferences <em>References</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDAnnotation()
 * @model
 * @generated
 */
public interface DAnnotation extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source</em>' attribute.
     * @see #setSource(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDAnnotation_Source()
     * @model
     * @generated
     */
    String getSource();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.DAnnotation#getSource <em>Source</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source</em>' attribute.
     * @see #getSource()
     * @generated
     */
    void setSource(String value);

    /**
     * Returns the value of the '<em><b>Details</b></em>' map. The key is of type {@link java.lang.String}, and the
     * value is of type {@link java.lang.String}, <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Details</em>' map isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Details</em>' map.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDAnnotation_Details()
     * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry&lt;org.eclipse.emf.ecore.EString,
     *        org.eclipse.emf.ecore.EString&gt;"
     * @generated
     */
    EMap<String, String> getDetails();

    /**
     * Returns the value of the '<em><b>References</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.emf.ecore.EObject}. <!-- begin-user-doc -->
     * <p>
     * This feature allows to have some information without having to search them into the entire model of the Sirius
     * session.</br>
     * Warning : Ideally only elements from semantic resources of the session should be referenced or else it may broke
     * the session behavior either because resolving too soon EObject in the session or because adding a resource that
     * is not known by Sirius session.
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>References</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDAnnotation_References()
     * @model
     * @generated
     */
    EList<EObject> getReferences();

} // DAnnotation
