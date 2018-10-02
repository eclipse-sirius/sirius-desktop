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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>End User Documented Element</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement#getEndUserDocumentation <em>End User
 * Documentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEndUserDocumentedElement()
 * @model abstract="true"
 * @generated
 */
public interface EndUserDocumentedElement extends EObject {
    /**
     * Returns the value of the '<em><b>End User Documentation</b></em>' attribute. The default value is
     * <code>""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End User Documentation</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>End User Documentation</em>' attribute.
     * @see #setEndUserDocumentation(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getEndUserDocumentedElement_EndUserDocumentation()
     * @model default=""
     * @generated
     */
    String getEndUserDocumentation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement#getEndUserDocumentation <em>End User
     * Documentation</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>End User Documentation</em>' attribute.
     * @see #getEndUserDocumentation()
     * @generated
     */
    void setEndUserDocumentation(String value);

} // EndUserDocumentedElement
