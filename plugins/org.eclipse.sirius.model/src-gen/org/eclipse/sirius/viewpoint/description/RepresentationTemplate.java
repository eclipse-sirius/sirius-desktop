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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Representation Template</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getOwnedRepresentations <em>Owned
 * Representations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationTemplate()
 * @model abstract="true"
 * @generated
 */
public interface RepresentationTemplate extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationTemplate_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.RepresentationTemplate#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Owned Representations</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.RepresentationDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Owned Representations</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Owned Representations</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getRepresentationTemplate_OwnedRepresentations()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<RepresentationDescription> getOwnedRepresentations();

} // RepresentationTemplate
