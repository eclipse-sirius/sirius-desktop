/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * An abstract class for all actions on {@link DTargetColumn}.
 * 
 * @author lredor
 */
public abstract class AbstractTargetColumnAction extends AbstractToolAction {
    /**
     * The column concerned with this action
     */
    private DTargetColumn column;

    /**
     * Creates a new action with the given text.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableTool
     *            The tool corresponding to this action
     */
    public AbstractTargetColumnAction(final String text, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory, final TableTool tableTool) {
        super(text, editingDomain, tableCommandFactory, tableTool);
    }

    /**
     * Creates a new action with the given text and style.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableTool
     *            The tool corresponding to this action
     */
    public AbstractTargetColumnAction(final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory,
            final TableTool tableTool) {
        super(text, image, editingDomain, tableCommandFactory, tableTool);
    }

    /**
     * Creates a new action with the given column.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param column
     *            the column on which the tool of this action applied
     * @param editingDomain
     *            The transactional editing domain *
     * @param tableCommandFactory
     *            The EMF command factory
     * @param tableTool
     *            The tool corresponding to this action
     */
    public AbstractTargetColumnAction(final String text, final DTargetColumn column, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory,
            final TableTool tableTool) {
        super(text, editingDomain, tableCommandFactory, tableTool);
        this.column = column;
    }

    /**
     * Return the column.
     * 
     * @return the column
     */
    public DTargetColumn getColumn() {
        return column;
    }

    /**
     * Set the column.
     * 
     * @param column
     *            the column to set
     */
    public void setColumn(final DTargetColumn column) {
        this.column = column;
    }
}
