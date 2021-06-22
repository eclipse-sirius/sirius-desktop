/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.metamodel.table.description;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage
 * @generated
 */
public interface DescriptionFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DescriptionFactory eINSTANCE = org.eclipse.sirius.table.metamodel.table.description.impl.DescriptionFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Edition Table Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edition Table Description</em>'.
     * @generated
     */
    EditionTableDescription createEditionTableDescription();

    /**
     * Returns a new object of class '<em>Cross Table Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Cross Table Description</em>'.
     * @generated
     */
    CrossTableDescription createCrossTableDescription();

    /**
     * Returns a new object of class '<em>Table Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Table Mapping</em>'.
     * @generated
     */
    TableMapping createTableMapping();

    /**
     * Returns a new object of class '<em>Line Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Line Mapping</em>'.
     * @generated
     */
    LineMapping createLineMapping();

    /**
     * Returns a new object of class '<em>Column Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Column Mapping</em>'.
     * @generated
     */
    ColumnMapping createColumnMapping();

    /**
     * Returns a new object of class '<em>Element Column Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Element Column Mapping</em>'.
     * @generated
     */
    ElementColumnMapping createElementColumnMapping();

    /**
     * Returns a new object of class '<em>Feature Column Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Feature Column Mapping</em>'.
     * @generated
     */
    FeatureColumnMapping createFeatureColumnMapping();

    /**
     * Returns a new object of class '<em>Cell Updater</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Cell Updater</em>'.
     * @generated
     */
    CellUpdater createCellUpdater();

    /**
     * Returns a new object of class '<em>Intersection Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Intersection Mapping</em>'.
     * @generated
     */
    IntersectionMapping createIntersectionMapping();

    /**
     * Returns a new object of class '<em>Table Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Table Tool</em>'.
     * @generated
     */
    TableTool createTableTool();

    /**
     * Returns a new object of class '<em>Label Edit Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Label Edit Tool</em>'.
     * @generated
     */
    LabelEditTool createLabelEditTool();

    /**
     * Returns a new object of class '<em>Create Column Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Column Tool</em>'.
     * @generated
     */
    CreateColumnTool createCreateColumnTool();

    /**
     * Returns a new object of class '<em>Create Cross Column Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Cross Column Tool</em>'.
     * @generated
     */
    CreateCrossColumnTool createCreateCrossColumnTool();

    /**
     * Returns a new object of class '<em>Create Line Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Line Tool</em>'.
     * @generated
     */
    CreateLineTool createCreateLineTool();

    /**
     * Returns a new object of class '<em>Create Cell Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Create Cell Tool</em>'.
     * @generated
     */
    CreateCellTool createCreateCellTool();

    /**
     * Returns a new object of class '<em>Delete Column Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Delete Column Tool</em>'.
     * @generated
     */
    DeleteColumnTool createDeleteColumnTool();

    /**
     * Returns a new object of class '<em>Delete Line Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Delete Line Tool</em>'.
     * @generated
     */
    DeleteLineTool createDeleteLineTool();

    /**
     * Returns a new object of class '<em>Foreground Style Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Foreground Style Description</em>'.
     * @generated
     */
    ForegroundStyleDescription createForegroundStyleDescription();

    /**
     * Returns a new object of class '<em>Background Style Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Background Style Description</em>'.
     * @generated
     */
    BackgroundStyleDescription createBackgroundStyleDescription();

    /**
     * Returns a new object of class '<em>Foreground Conditional Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Foreground Conditional Style</em>'.
     * @generated
     */
    ForegroundConditionalStyle createForegroundConditionalStyle();

    /**
     * Returns a new object of class '<em>Background Conditional Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Background Conditional Style</em>'.
     * @generated
     */
    BackgroundConditionalStyle createBackgroundConditionalStyle();

    /**
     * Returns a new object of class '<em>Table Variable</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Table Variable</em>'.
     * @generated
     */
    TableVariable createTableVariable();

    /**
     * Returns a new object of class '<em>Table Creation Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Table Creation Description</em>'.
     * @generated
     */
    TableCreationDescription createTableCreationDescription();

    /**
     * Returns a new object of class '<em>Table Navigation Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Table Navigation Description</em>'.
     * @generated
     */
    TableNavigationDescription createTableNavigationDescription();

    /**
     * Returns a new object of class '<em>Cell Editor Tool</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Cell Editor Tool</em>'.
     * @generated
     */
    CellEditorTool createCellEditorTool();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    DescriptionPackage getDescriptionPackage();

} // DescriptionFactory
