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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Ordered Element Creation Tool</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getStartingEndPredecessor
 * <em>Starting End Predecessor</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getFinishingEndPredecessor
 * <em>Finishing End Predecessor</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getOrderedElementCreationTool()
 * @model abstract="true"
 * @generated
 */
public interface OrderedElementCreationTool extends EObject {
    /**
     * Returns the value of the '<em><b>Starting End Predecessor</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Starting End Predecessor</em>' containment reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Starting End Predecessor</em>' containment reference.
     * @see #setStartingEndPredecessor(MessageEndVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getOrderedElementCreationTool_StartingEndPredecessor()
     * @model containment="true" annotation="toolVariable name='startingEndPredecessor'"
     * @generated
     */
    MessageEndVariable getStartingEndPredecessor();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getStartingEndPredecessor
     * <em>Starting End Predecessor</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Starting End Predecessor</em>' containment reference.
     * @see #getStartingEndPredecessor()
     * @generated
     */
    void setStartingEndPredecessor(MessageEndVariable value);

    /**
     * Returns the value of the '<em><b>Finishing End Predecessor</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Finishing End Predecessor</em>' containment reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finishing End Predecessor</em>' containment reference.
     * @see #setFinishingEndPredecessor(MessageEndVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getOrderedElementCreationTool_FinishingEndPredecessor()
     * @model containment="true" annotation="toolVariable name='finishingEndPredecessor'"
     * @generated
     */
    MessageEndVariable getFinishingEndPredecessor();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.OrderedElementCreationTool#getFinishingEndPredecessor
     * <em>Finishing End Predecessor</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Finishing End Predecessor</em>' containment reference.
     * @see #getFinishingEndPredecessor()
     * @generated
     */
    void setFinishingEndPredecessor(MessageEndVariable value);

} // OrderedElementCreationTool
