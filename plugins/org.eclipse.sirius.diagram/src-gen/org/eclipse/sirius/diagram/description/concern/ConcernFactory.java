/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.concern;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.concern.ConcernPackage
 * @generated
 */
public interface ConcernFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    ConcernFactory eINSTANCE = org.eclipse.sirius.diagram.description.concern.impl.ConcernFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Set</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Set</em>'.
     * @generated
     */
    ConcernSet createConcernSet();

    /**
     * Returns a new object of class '<em>Description</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Description</em>'.
     * @generated
     */
    ConcernDescription createConcernDescription();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    ConcernPackage getConcernPackage();

} // ConcernFactory
