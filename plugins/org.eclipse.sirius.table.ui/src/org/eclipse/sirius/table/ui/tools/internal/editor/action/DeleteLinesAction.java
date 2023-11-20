/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.table.business.api.helper.TableToolHelper;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.description.DeleteTool;
import org.eclipse.sirius.table.metamodel.table.description.LineMapping;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * This action delete the lines (the corresponding semantic element).
 * 
 * @author mporhel
 */
public class DeleteLinesAction extends Action {

    private final TransactionalEditingDomain editingDomain;

    private final ITableCommandFactory tableCommandFactory;

    private final Collection<DLine> lines = new ArrayList<>();

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
        super(Messages.DeleteLinesAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.DELETE_IMG));
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
                setText(Messages.DeleteLinesAction_label);
                setToolTipText(Messages.DeleteLinesAction_tooltip);

                DeleteTool deleteTool = getDeleteTool(linesToDelete.iterator().next());
                if (deleteTool != null) {
                    setText(new IdentifiedElementQuery(deleteTool).getLabel());
                    setToolTipText(MessageTranslator.INSTANCE.getMessage(deleteTool, deleteTool.getDocumentation()));
                }
            } else if (linesToDelete.size() > 1) {
                setText(Messages.DeleteLinesAction_labelMany);
                setToolTipText(Messages.DeleteLinesAction_tooltipMany);
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
            canExecute = canExecute && canExecute(lineToDelete);
            if (!canExecute) {
                break;
            }
        }

        return canExecute;
    }
    
    private boolean canExecute(DLine line) {
        DeleteTool tool = getDeleteTool(line);
        return tool == null // By default (no tool), element can be deleted
                || TableToolHelper.isAxisToolEnable(tool, line);
    }

    private DeleteTool getDeleteTool(DLine line) {
        DeleteTool tool = null;
        if (line != null) {
            LineMapping originMapping = line.getOriginMapping();
            if (originMapping != null) {
                tool = originMapping.getDelete();
            }
        }
        return tool;
    }
}
