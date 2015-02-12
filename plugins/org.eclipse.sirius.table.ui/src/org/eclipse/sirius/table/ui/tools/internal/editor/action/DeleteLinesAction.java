/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.util.Collection;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.table.business.api.query.DLineQuery;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

import com.google.common.collect.Lists;

/**
 * This action delete the lines (the corresponding semantic element).
 * 
 * @author mporhel
 */
public class DeleteLinesAction extends Action {

    private static final String DELETE_LINE = "Delete line";

    private static final String DELETE_LINES = DELETE_LINE + "s";

    private static final String TOOLTIP_LINE = "Delete the target semantic element";

    private static final String TOOLTIP_LINES = TOOLTIP_LINE + "s";

    private final TransactionalEditingDomain editingDomain;

    private final ITableCommandFactory tableCommandFactory;

    private final Collection<DLine> lines = Lists.newArrayList();

    /**
     * Constructor.
     * 
     * @see DeleteLineAction
     * 
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     * 
     */
    public DeleteLinesAction(final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(DELETE_LINE, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.DELETE_IMG));
        this.editingDomain = editingDomain;
        this.tableCommandFactory = tableCommandFactory;
    }

    @Override
    public void run() {
        super.run();
        CompoundCommand cc = new CompoundCommand(getText());
        for (DLine line : lines) {
            cc.append(tableCommandFactory.buildDeleteTableElement(line));
        }
        getEditingDomain().getCommandStack().execute(cc);
        lines.clear();
    }

    private TransactionalEditingDomain getEditingDomain() {
        return editingDomain;
    }

    /**
     * Set the lines to delete.
     * 
     * @param linesToDelete
     *            the lines to delete.
     */
    public void setLines(Collection<DLine> linesToDelete) {
        lines.clear();
        if (linesToDelete != null) {
            lines.addAll(linesToDelete);

            if (linesToDelete.size() == 1) {
                setText(DELETE_LINE);
                setToolTipText(TOOLTIP_LINE);

                DeleteTool deleteTool = getDeleteTool(linesToDelete.iterator().next());
                if (deleteTool != null) {
                    setText(new IdentifiedElementQuery(deleteTool).getLabel());
                    setToolTipText(deleteTool.getDocumentation());
                }
            } else if (linesToDelete.size() > 1) {
                setText(DELETE_LINES);
                setToolTipText(TOOLTIP_LINES);
            }
        }
    }

    /**
     * Tell if the action can do something.
     * 
     * @return true if the action can do something
     */
    public boolean canExecute() {
        boolean canExecute = !lines.isEmpty();

        for (DLine lineToDelete : lines) {
            canExecute = canExecute && new DLineQuery(lineToDelete).canBeDeleted();

            if (!canExecute) {
                break;
            }
        }

        return canExecute;
    }

    private DeleteTool getDeleteTool(DLine line) {
        DeleteTool tool = null;
        if (line != null && line.getOriginMapping() != null) {
            tool = line.getOriginMapping().getDelete();
        }
        return tool;
    }
}
