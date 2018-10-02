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
package org.eclipse.sirius.diagram.description.filter;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage
 * @generated
 */
public interface FilterFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    FilterFactory eINSTANCE = org.eclipse.sirius.diagram.description.filter.impl.FilterFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Mapping Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Mapping Filter</em>'.
     * @generated
     */
    MappingFilter createMappingFilter();

    /**
     * Returns a new object of class '<em>Composite Filter Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Composite Filter Description</em>'.
     * @generated
     */
    CompositeFilterDescription createCompositeFilterDescription();

    /**
     * Returns a new object of class '<em>Variable Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Variable Filter</em>'.
     * @generated
     */
    VariableFilter createVariableFilter();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    FilterPackage getFilterPackage();

} // FilterFactory
