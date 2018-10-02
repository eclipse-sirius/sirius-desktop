/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Interactive Variable Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> This interface represents a variable which value is given by the user. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription#getUserDocumentation <em>User
 * Documentation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInteractiveVariableDescription()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface InteractiveVariableDescription extends EObject {
    /**
     * Returns the value of the '<em><b>User Documentation</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> A documentation that is displayed to the user. <!-- end-model-doc -->
     *
     * @return the value of the '<em>User Documentation</em>' attribute.
     * @see #setUserDocumentation(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getInteractiveVariableDescription_UserDocumentation()
     * @model
     * @generated
     */
    String getUserDocumentation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.InteractiveVariableDescription#getUserDocumentation <em>User
     * Documentation</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>User Documentation</em>' attribute.
     * @see #getUserDocumentation()
     * @generated
     */
    void setUserDocumentation(String value);

} // InteractiveVariableDescription
