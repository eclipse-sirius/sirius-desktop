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
package org.eclipse.sirius.diagram.sequence.ordering;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.sequence.ordering.OrderingPackage
 * @generated
 */
public interface OrderingFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    OrderingFactory eINSTANCE = org.eclipse.sirius.diagram.sequence.ordering.impl.OrderingFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Event Ends Ordering</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Event Ends Ordering</em>'.
     * @generated
     */
    EventEndsOrdering createEventEndsOrdering();

    /**
     * Returns a new object of class '<em>Single Event End</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Single Event End</em>'.
     * @generated
     */
    SingleEventEnd createSingleEventEnd();

    /**
     * Returns a new object of class '<em>Compound Event End</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Compound Event End</em>'.
     * @generated
     */
    CompoundEventEnd createCompoundEventEnd();

    /**
     * Returns a new object of class '<em>Instance Roles Ordering</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Instance Roles Ordering</em>'.
     * @generated
     */
    InstanceRolesOrdering createInstanceRolesOrdering();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    OrderingPackage getOrderingPackage();

} // OrderingFactory
