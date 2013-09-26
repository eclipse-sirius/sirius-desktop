/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Target</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> The target of a ViewEdge. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeTarget#getOutgoingEdges <em>
 * Outgoing Edges</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.EdgeTarget#getIncomingEdges <em>
 * Incoming Edges</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getEdgeTarget()
 * @model abstract="true"
 * @generated
 */
public interface EdgeTarget extends EObject {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Outgoing Edges</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.sirius.viewpoint.DEdge}.
     * It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getSourceNode
     * <em>Source Node</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Outgoing Edges</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The outgoing view edges.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Outgoing Edges</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeTarget_OutgoingEdges()
     * @see org.eclipse.sirius.viewpoint.DEdge#getSourceNode
     * @model type="viewpoint.DEdge" opposite="sourceNode" changeable="false"
     * @generated
     */
    EList<DEdge> getOutgoingEdges();

    /**
     * Returns the value of the '<em><b>Incoming Edges</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.sirius.viewpoint.DEdge}.
     * It is bidirectional and its opposite is '
     * {@link org.eclipse.sirius.viewpoint.DEdge#getTargetNode
     * <em>Target Node</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Incoming Edges</em>' reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The incoming view edges.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Incoming Edges</em>' reference list.
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getEdgeTarget_IncomingEdges()
     * @see org.eclipse.sirius.viewpoint.DEdge#getTargetNode
     * @model type="viewpoint.DEdge" opposite="targetNode" changeable="false"
     * @generated
     */
    EList<DEdge> getIncomingEdges();
} // EdgeTarget
