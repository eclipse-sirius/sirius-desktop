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
package org.eclipse.sirius.diagram.sequence.description;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.description.DescriptionPackage
 * @generated
 */
public interface DescriptionFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DescriptionFactory eINSTANCE = org.eclipse.sirius.diagram.sequence.description.impl.DescriptionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Sequence Diagram Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Sequence Diagram Description</em>'.
     * @generated
     */
    SequenceDiagramDescription createSequenceDiagramDescription();

    /**
     * Returns a new object of class '<em>Instance Role Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Instance Role Mapping</em>'.
     * @generated
     */
    InstanceRoleMapping createInstanceRoleMapping();

    /**
     * Returns a new object of class '<em>Execution Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Execution Mapping</em>'.
     * @generated
     */
    ExecutionMapping createExecutionMapping();

    /**
     * Returns a new object of class '<em>State Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>State Mapping</em>'.
     * @generated
     */
    StateMapping createStateMapping();

    /**
     * Returns a new object of class '<em>End Of Life Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>End Of Life Mapping</em>'.
     * @generated
     */
    EndOfLifeMapping createEndOfLifeMapping();

    /**
     * Returns a new object of class '<em>Basic Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Basic Message Mapping</em>'.
     * @generated
     */
    BasicMessageMapping createBasicMessageMapping();

    /**
     * Returns a new object of class '<em>Return Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Return Message Mapping</em>'.
     * @generated
     */
    ReturnMessageMapping createReturnMessageMapping();

    /**
     * Returns a new object of class '<em>Creation Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Creation Message Mapping</em>'.
     * @generated
     */
    CreationMessageMapping createCreationMessageMapping();

    /**
     * Returns a new object of class '<em>Destruction Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Destruction Message Mapping</em>'.
     * @generated
     */
    DestructionMessageMapping createDestructionMessageMapping();

    /**
     * Returns a new object of class '<em>Message End Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Message End Variable</em>'.
     * @generated
     */
    MessageEndVariable createMessageEndVariable();

    /**
     * Returns a new object of class '<em>Covered Lifelines Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Covered Lifelines Variable</em>'.
     * @generated
     */
    CoveredLifelinesVariable createCoveredLifelinesVariable();

    /**
     * Returns a new object of class '<em>Interaction Use Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Interaction Use Mapping</em>'.
     * @generated
     */
    InteractionUseMapping createInteractionUseMapping();

    /**
     * Returns a new object of class '<em>Combined Fragment Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Combined Fragment Mapping</em>'.
     * @generated
     */
    CombinedFragmentMapping createCombinedFragmentMapping();

    /**
     * Returns a new object of class '<em>Operand Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Operand Mapping</em>'.
     * @generated
     */
    OperandMapping createOperandMapping();

    /**
     * Returns a new object of class '<em>Observation Point Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Observation Point Mapping</em>'.
     * @generated
     */
    ObservationPointMapping createObservationPointMapping();

    /**
     * Returns a new object of class '<em>Interaction Container Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Interaction Container Mapping</em>'.
     * @generated
     */
    InteractionContainerMapping createInteractionContainerMapping();

    /**
     * Returns a new object of class '<em>Gate Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Gate Mapping</em>'.
     * @generated
     */
    GateMapping createGateMapping();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    DescriptionPackage getDescriptionPackage();

} // DescriptionFactory
