/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.api.command;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.tree.DTreeElement;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.description.TreeItemContainerDropTool;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemEditionTool;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;

/**
 * Describes the contract of the table command factory.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public interface ITreeCommandFactory extends ICommandFactory {

    /**
     * Create a command that creates a line.
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
    Command buildCreateLineCommandFromTool(final DTreeItemContainer lineContainer, final EObject semanticCurrentElement, final TreeItemCreationTool tool);

    /**
     * Create a command that creates a column.
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
    // Command buildCreateColumnCommandFromTool(final DTable containerView,
    // final EObject semanticCurrentElement, final CreateTool tool);

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
    Command buildDeleteTreeElement(final DTreeElement element);

    /**
     * Create a command that set the content of a crossTable cell.
     * 
     * @param editedCell
     *            The cell to set
     * @param newValue
     *            the new value for this cell
     * @return a command able to set the content of a cell, corresponding to the
     *         {@link org.eclipse.sirius.table.metamodel.table.description.LabelEditTool
     *         LabelEditTool} or
     *         {@link org.eclipse.sirius.table.metamodel.table.description.CreateCellTool
     *         CreateCellTool}.
     */
    // Command buildSetCellValueFromTool(final DCell editedCell, final Object
    // newValue);

    /**
     * Set the autoRefresh.
     * 
     * @param autoRefreshDTree
     *            true to activate
     */
    void setAutoRefreshDTree(final boolean autoRefreshDTree);

    /**
     * Set the model accessor.
     * 
     * @param modelAccessor
     *            the modelAccessor to set
     */
    void setModelAccessor(final ModelAccessor modelAccessor);

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
    // Command buildSetValue(final EObject instance, final String name, final
    // Object value);

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
    // Command buildAddValue(final EObject instance, final String name, final
    // Object value);

    /**
     * Create a command that clear ths values of an instance feature.
     * 
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to clear.
     * @return a command able to clear the values of an instance feature
     */
    // Command buildClearValue(final EObject instance, final String name);

    /**
     * Build a direct edit label command using the corresponding tool
     * description.
     * 
     * @param labelled
     *            : the element on which the label should be changed.
     * @param directEditTool
     *            : the tool description.
     * @param newValue
     *            : the new label value
     * @return : a command which prepare the model request interpreter and set
     *         the new label.
     */
    Command buildDirectEditLabelFromTool(DTreeItem labelled, TreeItemEditionTool directEditTool, String newValue);

    /**
     * Build a command that will execute all semantic operations related to the
     * drop of the given element into the given container, using the given tool.
     * 
     * @param droppedElement
     *            the semantic element that has been dropped in the given
     *            container
     * @param dropTarget
     *            the new container of the dropped element (can whether be a
     *            TreeItem or a DTree)
     * @param precedingSiblings
     *            a collection containing all preceding siblings of the dragged
     *            element
     * @param dropTool
     *            the dropTool to use
     * @return a command that will execute all semantic operations related to
     *         the drop of the given element into the given container, using the
     *         given tool.
     */
    Command buildDropItemFromTool(EObject droppedElement, DTreeItemContainer dropTarget, Collection<DTreeItem> precedingSiblings, TreeItemContainerDropTool dropTool);

    /**
     * Build a command that will execute all the operations contained in the
     * given operation Action, with the given selectedItem as target.
     * 
     * @param operationAction
     *            the operation Action to apply
     * @param selectedItem
     *            the selected item on which apply the given operation Action
     * @return a command that will execute all the operations contained in the
     *         given operation Action, with the given selectedItem as target
     */
    Command buildOperationActionFromTool(OperationAction operationAction, DTreeItem selectedItem);

    /**
     * Build a command that is able to execute the operations of a
     * {@link org.eclipse.sirius.description.tool.JavaActionMenuItem}.
     * 
     * @param javaActionItem
     *            the
     *            {@link org.eclipse.sirius.description.tool.JavaActionMenuItem}
     *            referencing the action to launch
     * @param selectedItem
     *            the selected {@link DTreeItem}
     * @param javaAction
     *            the java action to execute.
     * @return a command that is able to execute the operations of
     *         {@link org.eclipse.sirius.description.tool.JavaActionMenuItem} .
     */
    Command buildJavaActionFromTool(ExternalJavaAction javaActionItem, DTreeItem selectedItem, IExternalJavaAction javaAction);
}
