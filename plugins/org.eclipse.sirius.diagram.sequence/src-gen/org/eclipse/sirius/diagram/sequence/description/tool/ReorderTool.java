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
import org.eclipse.sirius.diagram.sequence.description.EventMapping;
import org.eclipse.sirius.diagram.sequence.description.MessageEndVariable;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.InitialOperation;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Reorder Tool</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getMappings <em>Mappings</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorBefore
 * <em>Starting End Predecessor Before</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorAfter
 * <em>Starting End Predecessor After</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorBefore
 * <em>Finishing End Predecessor Before</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorAfter
 * <em>Finishing End Predecessor After</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getOnEventMovedOperation <em>On Event
 * Moved Operation</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool()
 * @model
 * @generated
 */
public interface ReorderTool extends AbstractToolDescription, SequenceDiagramToolDescription {
    /**
     * Returns the value of the '<em><b>Mappings</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.sequence.description.EventMapping}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mappings</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mappings</em>' reference list.
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool_Mappings()
     * @model
     * @generated
     */
    EList<EventMapping> getMappings();

    /**
     * Returns the value of the '<em><b>Starting End Predecessor Before</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Starting End Predecessor Before</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Starting End Predecessor Before</em>' containment reference.
     * @see #setStartingEndPredecessorBefore(MessageEndVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool_StartingEndPredecessorBefore()
     * @model containment="true" annotation="toolVariable name='startingEndPredecessorBefore'"
     * @generated
     */
    MessageEndVariable getStartingEndPredecessorBefore();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorBefore
     * <em>Starting End Predecessor Before</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Starting End Predecessor Before</em>' containment reference.
     * @see #getStartingEndPredecessorBefore()
     * @generated
     */
    void setStartingEndPredecessorBefore(MessageEndVariable value);

    /**
     * Returns the value of the '<em><b>Starting End Predecessor After</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Starting End Predecessor After</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Starting End Predecessor After</em>' containment reference.
     * @see #setStartingEndPredecessorAfter(MessageEndVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool_StartingEndPredecessorAfter()
     * @model containment="true" annotation="toolVariable name='startingEndPredecessorAfter'"
     * @generated
     */
    MessageEndVariable getStartingEndPredecessorAfter();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getStartingEndPredecessorAfter
     * <em>Starting End Predecessor After</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Starting End Predecessor After</em>' containment reference.
     * @see #getStartingEndPredecessorAfter()
     * @generated
     */
    void setStartingEndPredecessorAfter(MessageEndVariable value);

    /**
     * Returns the value of the '<em><b>Finishing End Predecessor Before</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finishing End Predecessor Before</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finishing End Predecessor Before</em>' containment reference.
     * @see #setFinishingEndPredecessorBefore(MessageEndVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool_FinishingEndPredecessorBefore()
     * @model containment="true" annotation="toolVariable name='finishingEndPredecessorBefore'"
     * @generated
     */
    MessageEndVariable getFinishingEndPredecessorBefore();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorBefore
     * <em>Finishing End Predecessor Before</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Finishing End Predecessor Before</em>' containment reference.
     * @see #getFinishingEndPredecessorBefore()
     * @generated
     */
    void setFinishingEndPredecessorBefore(MessageEndVariable value);

    /**
     * Returns the value of the '<em><b>Finishing End Predecessor After</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Finishing End Predecessor After</em>' containment reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Finishing End Predecessor After</em>' containment reference.
     * @see #setFinishingEndPredecessorAfter(MessageEndVariable)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool_FinishingEndPredecessorAfter()
     * @model containment="true" annotation="toolVariable name='finishingEndPredecessorAfter'"
     * @generated
     */
    MessageEndVariable getFinishingEndPredecessorAfter();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getFinishingEndPredecessorAfter
     * <em>Finishing End Predecessor After</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Finishing End Predecessor After</em>' containment reference.
     * @see #getFinishingEndPredecessorAfter()
     * @generated
     */
    void setFinishingEndPredecessorAfter(MessageEndVariable value);

    /**
     * Returns the value of the '<em><b>On Event Moved Operation</b></em>' containment reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>On Event Moved Operation</em>' containment reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>On Event Moved Operation</em>' containment reference.
     * @see #setOnEventMovedOperation(InitialOperation)
     * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage#getReorderTool_OnEventMovedOperation()
     * @model containment="true" required="true"
     * @generated
     */
    InitialOperation getOnEventMovedOperation();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.sequence.description.tool.ReorderTool#getOnEventMovedOperation <em>On Event
     * Moved Operation</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>On Event Moved Operation</em>' containment reference.
     * @see #getOnEventMovedOperation()
     * @generated
     */
    void setOnEventMovedOperation(InitialOperation value);

} // ReorderTool
