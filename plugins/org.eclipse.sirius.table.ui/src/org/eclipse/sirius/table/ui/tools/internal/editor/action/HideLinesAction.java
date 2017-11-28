/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;

/**
 * Hide the line.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class HideLinesAction extends AbstractTransactionalTableAction {

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
        super(dTable, Messages.HideLinesAction_label, DTableViewerManager.getImageRegistry().getDescriptor(DTableViewerManager.HIDE_IMG), editingDomain, tableCommandFactory);
        this.lines = new ArrayList<>();
    }

    /**
     * Set the line on which the tool of this action applied.
     *
     * @param linesToHide
     *            the line to set.
     */
    public void setLines(final Collection<DLine> linesToHide) {
        this.lines.clear();
        if (linesToHide != null && !linesToHide.isEmpty()) {
            this.lines.addAll(linesToHide);
        }

        setEnabled(!this.lines.isEmpty());
        setText(this.lines.size() <= 1 ? Messages.HideLinesAction_label : Messages.HideLinesAction_labelMany);
    }

    @Override
    public void run() {
        super.run();
        String commandLabel = MessageFormat.format(Messages.Action_setValue, TablePackage.eINSTANCE.getDLine_Visible().getName());
        String name = TablePackage.eINSTANCE.getDLine_Visible().getName();
        CompoundCommand compoundCommand = new CompoundCommand(commandLabel);
        for (EObject instance : lines) {
            compoundCommand.append(getTableCommandFactory().buildSetValue(instance, name, false));
        }
        getEditingDomain().getCommandStack().execute(compoundCommand);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled() && !this.lines.isEmpty();
    }
}
