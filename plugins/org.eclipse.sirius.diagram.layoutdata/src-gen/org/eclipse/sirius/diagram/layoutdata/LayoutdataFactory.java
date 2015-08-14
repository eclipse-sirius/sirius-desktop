/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.layoutdata;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage
 * @generated
 */
public interface LayoutdataFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    LayoutdataFactory eINSTANCE = org.eclipse.sirius.diagram.layoutdata.impl.LayoutdataFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Node Layout Data</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Node Layout Data</em>'.
     * @generated
     */
    NodeLayoutData createNodeLayoutData();

    /**
     * Returns a new object of class '<em>Edge Layout Data</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Layout Data</em>'.
     * @generated
     */
    EdgeLayoutData createEdgeLayoutData();

    /**
     * Returns a new object of class '<em>Point</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Point</em>'.
     * @generated
     */
    Point createPoint();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    LayoutdataPackage getLayoutdataPackage();

} // LayoutdataFactory
