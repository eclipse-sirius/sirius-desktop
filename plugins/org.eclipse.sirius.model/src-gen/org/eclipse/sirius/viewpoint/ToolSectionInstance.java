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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Tool Section Instance</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getTools <em>Tools</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getSection <em>Section</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getSubSections <em>Sub Sections</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolSectionInstance()
 * @model
 * @generated
 */
public interface ToolSectionInstance extends ToolInstance {
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
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolSectionInstance_Tools()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolInstance> getTools();

    /**
     * Returns the value of the '<em><b>Section</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Section</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Section</em>' reference.
     * @see #setSection(EObject)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolSectionInstance_Section()
     * @model
     * @generated
     */
    EObject getSection();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolSectionInstance#getSection <em>Section</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Section</em>' reference.
     * @see #getSection()
     * @generated
     */
    void setSection(EObject value);

    /**
     * Returns the value of the '<em><b>Sub Sections</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.ToolSectionInstance}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sub Sections</em>' containment reference list isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Sub Sections</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolSectionInstance_SubSections()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<ToolSectionInstance> getSubSections();

} // ToolSectionInstance
