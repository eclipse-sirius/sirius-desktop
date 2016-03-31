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
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Group</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.tool.ToolGroup#getTools
 * <em>Tools</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolGroup()
 * @model
 * @generated
 */
public interface ToolGroup extends ToolEntry {
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
     * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getToolGroup_Tools()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    EList<AbstractToolDescription> getTools();

} // ToolGroup
