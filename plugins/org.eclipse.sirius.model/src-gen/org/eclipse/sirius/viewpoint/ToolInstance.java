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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Tool Instance</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolInstance#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolInstance#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolInstance#isVisible <em>Visible</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolInstance#getToolEntry <em>Tool Entry</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.ToolInstance#isFiltered <em>Filtered</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolInstance()
 * @model
 * @generated
 */
public interface ToolInstance extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolInstance_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolInstance#getId <em>Id</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Enabled</b></em>' attribute. The default value is <code>"true"</code>. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Enabled</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Enabled</em>' attribute.
     * @see #setEnabled(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolInstance_Enabled()
     * @model default="true"
     * @generated
     */
    boolean isEnabled();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolInstance#isEnabled <em>Enabled</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Enabled</em>' attribute.
     * @see #isEnabled()
     * @generated
     */
    void setEnabled(boolean value);

    /**
     * Returns the value of the '<em><b>Visible</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Visible</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Visible</em>' attribute.
     * @see #setVisible(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolInstance_Visible()
     * @model
     * @generated
     */
    boolean isVisible();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolInstance#isVisible <em>Visible</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Visible</em>' attribute.
     * @see #isVisible()
     * @generated
     */
    void setVisible(boolean value);

    /**
     * Returns the value of the '<em><b>Tool Entry</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tool Entry</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tool Entry</em>' reference.
     * @see #setToolEntry(ToolEntry)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolInstance_ToolEntry()
     * @model
     * @generated
     */
    ToolEntry getToolEntry();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolInstance#getToolEntry <em>Tool Entry</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Tool Entry</em>' reference.
     * @see #getToolEntry()
     * @generated
     */
    void setToolEntry(ToolEntry value);

    /**
     * Returns the value of the '<em><b>Filtered</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filtered</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filtered</em>' attribute.
     * @see #setFiltered(boolean)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getToolInstance_Filtered()
     * @model
     * @generated
     */
    boolean isFiltered();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.ToolInstance#isFiltered <em>Filtered</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filtered</em>' attribute.
     * @see #isFiltered()
     * @generated
     */
    void setFiltered(boolean value);

} // ToolInstance
