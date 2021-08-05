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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.IdentifiedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Edge Target</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The target of a ViewEdge. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.EdgeTarget#getOutgoingEdges <em>Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.EdgeTarget#getIncomingEdges <em>Incoming Edges</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeTarget()
 * @model abstract="true"
 * @generated
 */
public interface EdgeTarget extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Outgoing Edges</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.DEdge}. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.diagram.DEdge#getSourceNode <em>Source Node</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The outgoing view edges. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Outgoing Edges</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeTarget_OutgoingEdges()
     * @see org.eclipse.sirius.diagram.DEdge#getSourceNode
     * @model opposite="sourceNode"
     * @generated
     */
    EList<DEdge> getOutgoingEdges();

    /**
     * Returns the value of the '<em><b>Incoming Edges</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.DEdge}. It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.diagram.DEdge#getTargetNode <em>Target Node</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The incoming view edges. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Incoming Edges</em>' reference list.
     * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeTarget_IncomingEdges()
     * @see org.eclipse.sirius.diagram.DEdge#getTargetNode
     * @model opposite="targetNode"
     * @generated
     */
    EList<DEdge> getIncomingEdges();

} // EdgeTarget
