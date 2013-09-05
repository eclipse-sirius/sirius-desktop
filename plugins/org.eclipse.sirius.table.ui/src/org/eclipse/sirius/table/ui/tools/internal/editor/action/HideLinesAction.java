/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.collect.Lists;

import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.command.TableCommandFactorySetValueRecordingCommand;

/**
 * Hide the line.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class HideLinesAction extends AbstractTransactionalTableAction {
    private static final String HIDE_LINE = "Hide line";

    private static final String HIDE_LINES = "Hide lines";

    /**
     * The line concerned with this action
     */
    private Collection<DLine> lines;

    /**
     * Creates a new action.
     * 
     * @param dTable
     *            {@link DTable} to use
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public HideLinesAction(final DTable dTable, final TransactionalEditingDomain editingDomain, final ITableCommandFactory tableCommandFactory) {
        super(dTable, HIDE_LINE, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.HIDE_IMG), editingDomain, tableCommandFactory);
        this.lines = Lists.newArrayList();
    }

    /**
     * Set the line on which the tool of this action applied.
     * 
     * @param line
     *            the line to set
     */
    public void setLines(final Collection<DLine> linesToHide) {
        this.lines.clear();
        if (linesToHide != null && !linesToHide.isEmpty()) {
            this.lines.addAll(linesToHide);
        }

        setEnabled(!this.lines.isEmpty());
        setText(this.lines.size() <= 1 ? HIDE_LINE : HIDE_LINES);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        super.run();
        getEditingDomain().getCommandStack().execute(
                new TableCommandFactorySetValueRecordingCommand(getEditingDomain(), "Set " + TablePackage.eINSTANCE.getDLine_Visible().getName() + " value", getTableCommandFactory(), lines,
                        TablePackage.eINSTANCE.getDLine_Visible().getName(), false));
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && !this.lines.isEmpty();
    }
}
