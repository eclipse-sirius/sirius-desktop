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
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Group Extension</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getGroup
 * <em>Group</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getTools
 * <em>Tools</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolGroupExtension()
 * @model
 * @generated
 */
public interface ToolGroupExtension extends EObject {
    /**
     * Returns the value of the '<em><b>Group</b></em>' reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Group</em>' reference.
     * @see #setGroup(ToolGroup)
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolGroupExtension_Group()
     * @model required="true"
     * @generated
     */
    ToolGroup getGroup();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.tool.ToolGroupExtension#getGroup
     * <em>Group</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Group</em>' reference.
     * @see #getGroup()
     * @generated
     */
    void setGroup(ToolGroup value);

    /**
     * Returns the value of the '<em><b>Tools</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription}
     * . <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Tools</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Tools</em>' containment reference list.
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolGroupExtension_Tools()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<AbstractToolDescription> getTools();

} // ToolGroupExtension
