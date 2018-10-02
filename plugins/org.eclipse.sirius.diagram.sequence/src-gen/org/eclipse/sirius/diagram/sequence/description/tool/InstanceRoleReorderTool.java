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

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ElementVariable;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Instance Role Reorder Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getMappings
 * <em>Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorBefore
 * <em>Predecessor Before</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorAfter
 * <em>Predecessor After</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getInstanceRoleMoved
 * <em>Instance Role Moved</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleReorderTool()
 * @model
 * @generated
 */
public interface InstanceRoleReorderTool extends AbstractToolDescription, SequenceDiagramToolDescription {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.sequence.description.InstanceRoleMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleReorderTool_Mappings()
     * @model
     * @generated
     */
    EList<InstanceRoleMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Predecessor Before</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predecessor Before</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Predecessor Before</em>' containment reference.
     * @see #setPredecessorBefore(ElementVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleReorderTool_PredecessorBefore()
     * @model containment="true" annotation="toolVariable name='predecessorBefore'"
     * @generated
     */
    ElementVariable getPredecessorBefore();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorBefore
     * <em>Predecessor Before</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Predecessor Before</em>' containment reference.
     * @see #getPredecessorBefore()
     * @generated
     */
    void setPredecessorBefore(ElementVariable value);

    /**
     * Returns the value of the '<em><b>Predecessor After</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predecessor After</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Predecessor After</em>' containment reference.
     * @see #setPredecessorAfter(ElementVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleReorderTool_PredecessorAfter()
     * @model containment="true" annotation="toolVariable name='predecessorAfter'"
     * @generated
     */
    ElementVariable getPredecessorAfter();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getPredecessorAfter
     * <em>Predecessor After</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Predecessor After</em>' containment reference.
     * @see #getPredecessorAfter()
     * @generated
     */
    void setPredecessorAfter(ElementVariable value);

    /**
     * Returns the value of the '<em><b>Instance Role Moved</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Instance Role Moved</em>' containment reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Instance Role Moved</em>' containment reference.
     * @see #setInstanceRoleMoved(InitialOperation)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getInstanceRoleReorderTool_InstanceRoleMoved()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getInstanceRoleMoved();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.InstanceRoleReorderTool#getInstanceRoleMoved
     * <em>Instance Role Moved</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Instance Role Moved</em>' containment reference.
     * @see #getInstanceRoleMoved()
     * @generated
     */
    void setInstanceRoleMoved(InitialOperation value);

} // InstanceRoleReorderTool
