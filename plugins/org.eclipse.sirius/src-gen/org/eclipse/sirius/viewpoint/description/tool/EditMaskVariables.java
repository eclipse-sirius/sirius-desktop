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
package org.eclipse.sirius.viewpoint.description.tool;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Edit Mask Variables</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables#getMask <em>Mask</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getEditMaskVariables()
 * @model
 * @generated
 */
public interface EditMaskVariables extends EObject {
    /**
     * Returns the value of the '<em><b>Mask</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> Editing mask, in the form of {0} : {1} for instance. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Mask</em>' attribute.
     * @see #setMask(String)
     * @see org.eclipse.sirius.viewpoint.description.tool.ToolPackage#getEditMaskVariables_Mask()
     * @model
     * @generated
     */
    String getMask();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.tool.EditMaskVariables#getMask
     * <em>Mask</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mask</em>' attribute.
     * @see #getMask()
     * @generated
     */
    void setMask(String value);

} // EditMaskVariables
