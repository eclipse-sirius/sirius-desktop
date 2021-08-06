/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Tool Group Instance</b></em>'. <!-- end-user-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolGroupInstance#getTools <em>Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolGroupInstance#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolGroupInstance()
 * @model
 * @generated
 */
public interface ToolGroupInstance extends ToolInstance {
    /**
     * Returns the value of the '<em><b>Tools</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.ToolInstance}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tools</em>' containment reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tools</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolGroupInstance_Tools()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolInstance> getTools();

    /**
     * Returns the value of the '<em><b>Group</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Group</em>' containment reference.
     * @see #setGroup(EObject)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolGroupInstance_Group()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EObject getGroup();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolGroupInstance#getGroup <em>Group</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Group</em>' containment reference.
     * @see #getGroup()
     * @generated
     */
    void setGroup(EObject value);

} // ToolGroupInstance
