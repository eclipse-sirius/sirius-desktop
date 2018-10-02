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
import org.eclipse.sirius.viewpoint.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Annotation Entry</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.AnnotationEntry#getSource <em>Source</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.AnnotationEntry#getData <em>Data</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAnnotationEntry()
 * @model
 * @generated
 */
public interface AnnotationEntry extends IdentifiedElement {
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
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAnnotationEntry_Source()
     * @model
     * @generated
     */
    String getSource();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.AnnotationEntry#getSource
     * <em>Source</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source</em>' attribute.
     * @see #getSource()
     * @generated
     */
    void setSource(String value);

    /**
     * Returns the value of the '<em><b>Data</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Data</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Data</em>' containment reference.
     * @see #setData(EObject)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getAnnotationEntry_Data()
     * @model containment="true"
     * @generated
     */
    EObject getData();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.AnnotationEntry#getData <em>Data</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Data</em>' containment reference.
     * @see #getData()
     * @generated
     */
    void setData(EObject value);

} // AnnotationEntry
