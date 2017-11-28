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
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * An abstract class for all actions on {@link DLine}.
 * 
 * @author lredor
 */
public abstract class AbstractLineAction extends AbstractToolAction {

    /**
     * The line concerned with this action
     */
    private DLine line;

    /**
     * Creates a new action with the given text and the given line.
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
    public AbstractLineAction(final String text, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory, final TableTool tableTool) {
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
    public AbstractLineAction(final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory, final TableTool tableTool) {
        super(text, image, editingDomain, tableCommandFactory, tableTool);
    }

    /**
     * Return the line.
     * 
     * @return the line
     */
    public DLine getLine() {
        return line;
    }

    /**
     * Set the line on which the tool of this action applied.
     * 
     * @param line
     *            the line to set
     */
    public void setLine(final DLine line) {
        this.line = line;
    }
}
