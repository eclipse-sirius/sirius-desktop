/**
 * Copyright (c) 2014 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.basicfamily;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.sample.basicfamily.BasicfamilyPackage
 * @generated
 */
public interface BasicfamilyFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    BasicfamilyFactory eINSTANCE = org.eclipse.sirius.sample.basicfamily.impl.BasicfamilyFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Family</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Family</em>'.
     * @generated
     */
    Family createFamily();

    /**
     * Returns a new object of class '<em>Man</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Man</em>'.
     * @generated
     */
    Man createMan();

    /**
     * Returns a new object of class '<em>Woman</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Woman</em>'.
     * @generated
     */
    Woman createWoman();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    BasicfamilyPackage getBasicfamilyPackage();

} // BasicfamilyFactory
