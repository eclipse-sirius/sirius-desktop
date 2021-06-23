/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.description.tool;

import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Instance Role Creation Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool#getPredecessor
 * <em>Predecessor</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleCreationTool()
 * @model
 * @generated
 */
public interface InstanceRoleCreationTool extends NodeCreationDescription, SequenceDiagramToolDescription {
    /**
     * Returns the value of the '<em><b>Predecessor</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predecessor</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Predecessor</em>' containment reference.
     * @see #setPredecessor(ElementVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleCreationTool_Predecessor()
     * @model containment="true" annotation="toolVariable name='predecessor'"
     * @generated
     */
    ElementVariable getPredecessor();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleCreationTool#getPredecessor
     * <em>Predecessor</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Predecessor</em>' containment reference.
     * @see #getPredecessor()
     * @generated
     */
    void setPredecessor(ElementVariable value);

} // InstanceRoleCreationTool
