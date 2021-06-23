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
package org.eclipse.sirius.diagram.sequence.template;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.template.TemplatePackage
 * @generated
 */
public interface TemplateFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    TemplateFactory eINSTANCE = org.eclipse.sirius.diagram.sequence.template.impl.TemplateFactoryImpl.init();

    /**
     * Returns a new object of class '<em>TSequence Diagram</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TSequence Diagram</em>'.
     * @generated
     */
    TSequenceDiagram createTSequenceDiagram();

    /**
     * Returns a new object of class '<em>TLifeline Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TLifeline Mapping</em>'.
     * @generated
     */
    TLifelineMapping createTLifelineMapping();

    /**
     * Returns a new object of class '<em>TLifeline Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TLifeline Style</em>'.
     * @generated
     */
    TLifelineStyle createTLifelineStyle();

    /**
     * Returns a new object of class '<em>TConditional Lifeline Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>TConditional Lifeline Style</em>'.
     * @generated
     */
    TConditionalLifelineStyle createTConditionalLifelineStyle();

    /**
     * Returns a new object of class '<em>TTransformer</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TTransformer</em>'.
     * @generated
     */
    TTransformer createTTransformer();

    /**
     * Returns a new object of class '<em>TExecution Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TExecution Mapping</em>'.
     * @generated
     */
    TExecutionMapping createTExecutionMapping();

    /**
     * Returns a new object of class '<em>TExecution Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TExecution Style</em>'.
     * @generated
     */
    TExecutionStyle createTExecutionStyle();

    /**
     * Returns a new object of class '<em>TConditional Execution Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>TConditional Execution Style</em>'.
     * @generated
     */
    TConditionalExecutionStyle createTConditionalExecutionStyle();

    /**
     * Returns a new object of class '<em>TMessage Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TMessage Style</em>'.
     * @generated
     */
    TMessageStyle createTMessageStyle();

    /**
     * Returns a new object of class '<em>TConditional Message Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>TConditional Message Style</em>'.
     * @generated
     */
    TConditionalMessageStyle createTConditionalMessageStyle();

    /**
     * Returns a new object of class '<em>TBasic Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TBasic Message Mapping</em>'.
     * @generated
     */
    TBasicMessageMapping createTBasicMessageMapping();

    /**
     * Returns a new object of class '<em>TReturn Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TReturn Message Mapping</em>'.
     * @generated
     */
    TReturnMessageMapping createTReturnMessageMapping();

    /**
     * Returns a new object of class '<em>TCreation Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TCreation Message Mapping</em>'.
     * @generated
     */
    TCreationMessageMapping createTCreationMessageMapping();

    /**
     * Returns a new object of class '<em>TDestruction Message Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>TDestruction Message Mapping</em>'.
     * @generated
     */
    TDestructionMessageMapping createTDestructionMessageMapping();

    /**
     * Returns a new object of class '<em>TAbstract Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>TAbstract Mapping</em>'.
     * @generated
     */
    TAbstractMapping createTAbstractMapping();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    TemplatePackage getTemplatePackage();

} // TemplateFactory
