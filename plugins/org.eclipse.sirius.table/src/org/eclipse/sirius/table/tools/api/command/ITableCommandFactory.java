/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.tools.api.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.LineContainer;
import org.eclipse.sirius.table.metamodel.table.description.CreateTool;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Describes the contract of the table command factory.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public interface ITableCommandFactory extends ICommandFactory {

    /**
     * Create a command that creates a line.
     * <p>
     * Legacy implementation:
     * Deprecated to {@link ITableCommandFactory#buildCreateLineCommandFromTool(LineContainer, CreateTool)}.
     * </p>
     * 
     * @param lineContainer
     *            container element in which the command should put the created
     *            line.
     * @param semanticCurrentElement
     *            the semantic current element
     * @param tool
     *            {@link CreateTool} used to build the command.
     * @return a command able to create the line and putting it in the
     *         container, corresponding to the {@link CreateTool}.
     */
    @Deprecated
    Command buildCreateLineCommandFromTool(LineContainer lineContainer, EObject semanticCurrentElement, CreateTool tool);

    /**
     * Create a command that creates a column.
     * <p>
     * Legacy implementation:
     * Deprecated to {@link ITableCommandFactory#buildCreateColumnCommandFromTool(DSemanticDecorator, CreateTool)}.
     * </p>
     * 
     * @param containerView
     *            container element in which the command should put the created
     *            line.
     * @param semanticCurrentElement
     *            the semantic current element
     * @param tool
     *            {@link CreateTool} used to build the command.
     * @return a command able to create the line and putting it in the
     *         container, corresponding to the {@link CreateTool}.
     */
    @Deprecated
    Command buildCreateColumnCommandFromTool(DTable containerView, EObject semanticCurrentElement, CreateTool tool);

    /**
     * Create a command that creates a line.
     * 
     * @param lineContainer
     *            container element in which the command should put the created
     *            line.
     * @param tool
     *            {@link CreateTool} used to build the command.
     * @return a command able to create the line and putting it in the
     *         container, corresponding to the {@link CreateTool}.
     */

    Command buildCreateLineCommandFromTool(LineContainer lineContainer, CreateTool tool);

    /**
     * Create a command that creates a column.
     * 
     * @param containerView
     *            container element in which the command should put the created
     *            line.
     * @param tool
     *            {@link CreateTool} used to build the command.
     * @return a command able to create the line and putting it in the
     *         container, corresponding to the {@link CreateTool}.
     */
    Command buildCreateColumnCommandFromTool(DSemanticDecorator containerView, CreateTool tool);

    /**
     * Returns a command that can delete the specified element.
     * 
     * @param element
     *            the element to delete (a
     *            {@link org.eclipse.sirius.table.metamodel.table.DLine} or a
     *            {@link org.eclipse.sirius.table.metamodel.table.DTargetColumn}
     *            ).
     * @return a command that can delete the specified element.
     */
    Command buildDeleteTableElement(DTableElement element);

    /**
     * Create a command that creates a cell using the
     * {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     * CreateCellTool} associated with this line and column.
     * 
     * @param line
     *            the line in which the command add the created cell
     * @param column
     *            the line in which the command add a reference to the created
     *            cell
     * @param value
     *            the new value for this cell
     * @return a command able to create a new cell according to
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     *         CreateCellTool}.
     */
    Command buildCreateCellFromTool(DLine line, DTargetColumn column, Object value);

    /**
     * Create a command that set the content of a crossTable cell.
     * 
     * @param editedCell
     *            The cell to set
     * @param newValue
     *            the new value for this cell
     * @return a command able to set the content of a cell, corresponding to the
     *         {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool LabelEditTool},
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CellEditorTool CellEditorTool}, or
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool CreateCellTool}.
     */
    Command buildSetCellValueFromTool(DCell editedCell, Object newValue);

    /**
     * Set the model accessor.
     * 
     * @param modelAccessor
     *            the modelAccessor to set
     */
    void setModelAccessor(ModelAccessor modelAccessor);

    /**
     * Create a command that set a value on an instance feature.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to set.
     * @param value
     *            value to set
     * @return a command able to set the value of an instance feature
     */
    Command buildSetValue(EObject instance, String name, Object value);

    /**
     * Create a command that add a value on an instance feature.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to add.
     * @param value
     *            value to add
     * @return a command able to add the value of an instance feature
     */
    Command buildAddValue(EObject instance, String name, Object value);

    /**
     * Create a command that clear ths values of an instance feature.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to clear.
     * @return a command able to clear the values of an instance feature
     */
    Command buildClearValue(EObject instance, String name);
}
