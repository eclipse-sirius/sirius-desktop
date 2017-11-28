/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * Transactional table action.
 * 
 * @author dlecan
 */
public class AbstractTransactionalTableAction extends Action {
    /**
     * Editing domain.
     */
    private final TransactionalEditingDomain editingDomain;

    /**
     * Table command factory.
     */
    private final ITableCommandFactory tableCommandFactory;

    private final DTable dTable;

    /**
     * Creates a new action.
     * 
     * @param dTable
     *            {@link DTable} to use
     * @param text
     *            the string used as the text for the action, or
     *            <code>null</code> if there is no text
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public AbstractTransactionalTableAction(final DTable dTable, final String text, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        this(dTable, text, null, editingDomain, tableCommandFactory);
    }

    /**
     * Creates a new action with the given image.
     * 
     * @param dTable
     *            {@link DTable} to use
     * @param text
     *            the string used as the text for the action, or
     *            <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public AbstractTransactionalTableAction(final DTable dTable, final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain,
            final ITableCommandFactory tableCommandFactory) {
        super(text, image);
        this.dTable = dTable;
        this.editingDomain = editingDomain;
        this.tableCommandFactory = tableCommandFactory;
    }

    /**
     * Returns the editingDomain.
     * 
     * @return The editingDomain.
     */
    protected TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Returns the tableCommandFactory.
     * 
     * @return The tableCommandFactory.
     */
    protected ITableCommandFactory getTableCommandFactory() {
        return tableCommandFactory;
    }

    /**
     * Returns the dTable.
     * 
     * @return The dTable.
     */
    protected DTable getTable() {
        return dTable;
    }
}
