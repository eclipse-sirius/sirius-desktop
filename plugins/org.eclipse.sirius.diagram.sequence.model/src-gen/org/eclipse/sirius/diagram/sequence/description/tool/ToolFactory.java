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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage
 * @generated
 */
public interface ToolFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ToolFactory eINSTANCE = org.eclipse.sirius.diagram.sequence.description.tool.impl.ToolFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Sequence Diagram Tool Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Sequence Diagram Tool Description</em>'.
     * @generated
     */
    SequenceDiagramToolDescription createSequenceDiagramToolDescription();

    /**
     * Returns a new object of class '<em>Instance Role Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Instance Role Creation Tool</em>'.
     * @generated
     */
    InstanceRoleCreationTool createInstanceRoleCreationTool();

    /**
     * Returns a new object of class '<em>Lifeline Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Lifeline Creation Tool</em>'.
     * @generated
     */
    LifelineCreationTool createLifelineCreationTool();

    /**
     * Returns a new object of class '<em>Message Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Message Creation Tool</em>'.
     * @generated
     */
    MessageCreationTool createMessageCreationTool();

    /**
     * Returns a new object of class '<em>Execution Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Execution Creation Tool</em>'.
     * @generated
     */
    ExecutionCreationTool createExecutionCreationTool();

    /**
     * Returns a new object of class '<em>State Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>State Creation Tool</em>'.
     * @generated
     */
    StateCreationTool createStateCreationTool();

    /**
     * Returns a new object of class '<em>Reorder Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Reorder Tool</em>'.
     * @generated
     */
    ReorderTool createReorderTool();

    /**
     * Returns a new object of class '<em>Instance Role Reorder Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Instance Role Reorder Tool</em>'.
     * @generated
     */
    InstanceRoleReorderTool createInstanceRoleReorderTool();

    /**
     * Returns a new object of class '<em>Gate Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Gate Creation Tool</em>'.
     * @generated
     */
    GateCreationTool createGateCreationTool();

    /**
     * Returns a new object of class '<em>Observation Point Creation Tool</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Observation Point Creation Tool</em>'.
     * @generated
     */
    ObservationPointCreationTool createObservationPointCreationTool();

    /**
     * Returns a new object of class '<em>Interaction Use Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Interaction Use Creation Tool</em>'.
     * @generated
     */
    InteractionUseCreationTool createInteractionUseCreationTool();

    /**
     * Returns a new object of class '<em>Combined Fragment Creation Tool</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Combined Fragment Creation Tool</em>'.
     * @generated
     */
    CombinedFragmentCreationTool createCombinedFragmentCreationTool();

    /**
     * Returns a new object of class '<em>Operand Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Operand Creation Tool</em>'.
     * @generated
     */
    OperandCreationTool createOperandCreationTool();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ToolPackage getToolPackage();

} // ToolFactory
