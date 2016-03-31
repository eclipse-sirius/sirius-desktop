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
package org.eclipse.sirius.diagram.description.tool;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Delete Hook</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.tool.DeleteHook#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.DeleteHook#getParameters
 * <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDeleteHook()
 * @model
 * @generated
 */
public interface DeleteHook extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDeleteHook_Id()
     * @model required="true"
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHook#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.tool.DeleteHookParameter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Parameters</em>' containment reference
     *         list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getDeleteHook_Parameters()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<DeleteHookParameter> getParameters();

} // DeleteHook
