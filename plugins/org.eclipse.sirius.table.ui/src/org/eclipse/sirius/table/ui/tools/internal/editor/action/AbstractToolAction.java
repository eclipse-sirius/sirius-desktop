/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * An abstract class for all actions which launch tool (DeleteTool, CreateTool).
 * 
 * @author lredor
 * @param <T> type of the tool
 */
public class AbstractToolAction<T extends TableTool> extends Action {
    TransactionalEditingDomain editingDomain;

    ITableCommandFactory tableCommandFactory;

    /**
     * The tool corresponding to this action.
     */
    T tool;

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
     * 
     */
    public AbstractToolAction(final String text, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory, final T tableTool) {
        super(text);
        this.editingDomain = editingDomain;
        this.tableCommandFactory = tableCommandFactory;
        this.tool = tableTool;
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
    public AbstractToolAction(final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory, final T tableTool) {
        super(text, image);
        this.editingDomain = editingDomain;
        this.tableCommandFactory = tableCommandFactory;
        this.tool = tableTool;
    }

    /**
     * return The transactional editing domain.
     * 
     * @return the editingDomain
     */
    protected TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Return the EMF command factory.
     * 
     * @return the tableCommandFactory
     */
    protected ITableCommandFactory getITableCommandFactory() {
        return tableCommandFactory;
    }

    /**
     * The default implementation of this <code>AbstractToolAction</code> method
     * return false. Subclasses should override this method.
     * 
     * @return true if this action can be execute, false otherwise
     */
    public boolean canExecute() {
        return false;
    }

    protected T getTool() {
        return tool;
    }

    
}
