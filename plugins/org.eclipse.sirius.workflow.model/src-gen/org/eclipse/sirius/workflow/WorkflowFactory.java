/**
 *  Copyright (c) 2018 Obeo.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  https://www.eclipse.org/legal/epl-2.0/
 *
 *  Contributors:
 *     Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.workflow;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.workflow.WorkflowPackage
 * @generated
 */
public interface WorkflowFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    WorkflowFactory eINSTANCE = org.eclipse.sirius.workflow.impl.WorkflowFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Description</em>'.
     * @generated
     */
    WorkflowDescription createWorkflowDescription();

    /**
     * Returns a new object of class '<em>Page Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Page Description</em>'.
     * @generated
     */
    PageDescription createPageDescription();

    /**
     * Returns a new object of class '<em>Section Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Section Description</em>'.
     * @generated
     */
    SectionDescription createSectionDescription();

    /**
     * Returns a new object of class '<em>Activity Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Activity Description</em>'.
     * @generated
     */
    ActivityDescription createActivityDescription();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    WorkflowPackage getWorkflowPackage();

} // WorkflowFactory
