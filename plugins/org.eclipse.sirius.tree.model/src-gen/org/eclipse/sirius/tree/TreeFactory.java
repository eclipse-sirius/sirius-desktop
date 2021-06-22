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
package org.eclipse.sirius.tree;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tree.TreePackage
 * @generated
 */
public interface TreeFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    TreeFactory eINSTANCE = org.eclipse.sirius.tree.impl.TreeFactoryImpl.init();

    /**
     * Returns a new object of class '<em>DTree</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DTree</em>'.
     * @generated
     */
    DTree createDTree();

    /**
     * Returns a new object of class '<em>DTree Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DTree Element</em>'.
     * @generated
     */
    DTreeElement createDTreeElement();

    /**
     * Returns a new object of class '<em>DTree Item</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DTree Item</em>'.
     * @generated
     */
    DTreeItem createDTreeItem();

    /**
     * Returns a new object of class '<em>Item Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Item Style</em>'.
     * @generated
     */
    TreeItemStyle createTreeItemStyle();

    /**
     * Returns a new object of class '<em>DTree Element Synchronizer</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>DTree Element Synchronizer</em>'.
     * @generated
     */
    DTreeElementSynchronizer createDTreeElementSynchronizer();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    TreePackage getTreePackage();

} // TreeFactory
