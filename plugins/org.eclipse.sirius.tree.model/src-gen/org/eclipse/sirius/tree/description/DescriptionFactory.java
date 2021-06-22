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
package org.eclipse.sirius.tree.description;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage
 * @generated
 */
public interface DescriptionFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DescriptionFactory eINSTANCE = org.eclipse.sirius.tree.description.impl.DescriptionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Tree Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Description</em>'.
     * @generated
     */
    TreeDescription createTreeDescription();

    /**
     * Returns a new object of class '<em>Tree Item Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Item Mapping</em>'.
     * @generated
     */
    TreeItemMapping createTreeItemMapping();

    /**
     * Returns a new object of class '<em>Tree Item Style Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Tree Item Style Description</em>'.
     * @generated
     */
    TreeItemStyleDescription createTreeItemStyleDescription();

    /**
     * Returns a new object of class '<em>Conditional Tree Item Style Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Conditional Tree Item Style Description</em>'.
     * @generated
     */
    ConditionalTreeItemStyleDescription createConditionalTreeItemStyleDescription();

    /**
     * Returns a new object of class '<em>Tree Item Drag Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Item Drag Tool</em>'.
     * @generated
     */
    @Deprecated
    TreeItemDragTool createTreeItemDragTool();

    /**
     * Returns a new object of class '<em>Tree Item Container Drop Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Tree Item Container Drop Tool</em>'.
     * @generated
     */
    TreeItemContainerDropTool createTreeItemContainerDropTool();

    /**
     * Returns a new object of class '<em>Tree Item Creation Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Item Creation Tool</em>'.
     * @generated
     */
    TreeItemCreationTool createTreeItemCreationTool();

    /**
     * Returns a new object of class '<em>Tree Item Edition Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Item Edition Tool</em>'.
     * @generated
     */
    TreeItemEditionTool createTreeItemEditionTool();

    /**
     * Returns a new object of class '<em>Tree Item Deletion Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Item Deletion Tool</em>'.
     * @generated
     */
    TreeItemDeletionTool createTreeItemDeletionTool();

    /**
     * Returns a new object of class '<em>Tree Creation Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Creation Description</em>'.
     * @generated
     */
    TreeCreationDescription createTreeCreationDescription();

    /**
     * Returns a new object of class '<em>Tree Navigation Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Tree Navigation Description</em>'.
     * @generated
     */
    TreeNavigationDescription createTreeNavigationDescription();

    /**
     * Returns a new object of class '<em>Tree Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Mapping</em>'.
     * @generated
     */
    TreeMapping createTreeMapping();

    /**
     * Returns a new object of class '<em>Style Updater</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Style Updater</em>'.
     * @generated
     */
    StyleUpdater createStyleUpdater();

    /**
     * Returns a new object of class '<em>Tree Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Variable</em>'.
     * @generated
     */
    TreeVariable createTreeVariable();

    /**
     * Returns a new object of class '<em>Tree Item Updater</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Item Updater</em>'.
     * @generated
     */
    TreeItemUpdater createTreeItemUpdater();

    /**
     * Returns a new object of class '<em>Preceding Siblings Variables</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Preceding Siblings Variables</em>'.
     * @generated
     */
    PrecedingSiblingsVariables createPrecedingSiblingsVariables();

    /**
     * Returns a new object of class '<em>Tree Popup Menu</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Tree Popup Menu</em>'.
     * @generated
     */
    TreePopupMenu createTreePopupMenu();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    DescriptionPackage getDescriptionPackage();

} // DescriptionFactory
