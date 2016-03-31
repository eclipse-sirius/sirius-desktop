/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>DAnnotation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DAnnotation#getSource
 * <em>Source</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.DAnnotation#getDetails
 * <em>Details</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDAnnotation()
 * @model
 * @generated
 */
public interface DAnnotation extends EObject {
    /**
     * Returns the value of the '<em><b>Source</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source</em>' attribute isn't clear, there
     * really should be more of a description here...
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
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.DAnnotation#getSource
     * <em>Source</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Source</em>' attribute.
     * @see #getSource()
     * @generated
     */
    void setSource(String value);

    /**
     * Returns the value of the '<em><b>Details</b></em>' map. The key is of
     * type {@link java.lang.String}, and the value is of type
     * {@link java.lang.String}, <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Details</em>' map isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Details</em>' map.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDAnnotation_Details()
     * @model mapType=
     *        "org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
     * @generated
     */
    EMap<String, String> getDetails();

} // DAnnotation
