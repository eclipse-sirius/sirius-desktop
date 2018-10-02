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
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage
 * @generated
 */
public interface TableFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    TableFactory eINSTANCE = org.eclipse.sirius.table.metamodel.table.impl.TableFactoryImpl.init();

    /**
     * Returns a new object of class '<em>DTable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DTable</em>'.
     * @generated
     */
    DTable createDTable();

    /**
     * Returns a new object of class '<em>DLine</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DLine</em>'.
     * @generated
     */
    DLine createDLine();

    /**
     * Returns a new object of class '<em>DCell</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DCell</em>'.
     * @generated
     */
    DCell createDCell();

    /**
     * Returns a new object of class '<em>DCell Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DCell Style</em>'.
     * @generated
     */
    DCellStyle createDCellStyle();

    /**
     * Returns a new object of class '<em>DTarget Column</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DTarget Column</em>'.
     * @generated
     */
    DTargetColumn createDTargetColumn();

    /**
     * Returns a new object of class '<em>DFeature Column</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DFeature Column</em>'.
     * @generated
     */
    DFeatureColumn createDFeatureColumn();

    /**
     * Returns a new object of class '<em>DTable Element Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DTable Element Style</em>'.
     * @generated
     */
    DTableElementStyle createDTableElementStyle();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    TablePackage getTablePackage();

} // TableFactory
