/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;

/**
 * Command to update the {@link TablePackage#DTABLE__HEADER_COLUMN_WIDTH}
 * feature of a {@link DTable} or the {@link TablePackage#DCOLUMN__WIDTH} of a
 * {@link DColumn}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ChangeColumnWidthCommand extends RecordingCommand {

    private Session session;

    private int widgetWidth;

    private DTable dTable;

    private DColumn dColumn;

    /**
     * Create a new command.
     * 
     * @param session
     *            the session in which to operate.
     * @param widgetWidth
     *            the new width to set for the column.
     * @param dColumn
     *            the column to resize.
     */
    public ChangeColumnWidthCommand(Session session, int widgetWidth, DColumn dColumn) {
        super(session.getTransactionalEditingDomain(), "Change Column Width");
        this.session = session;
        this.widgetWidth = widgetWidth;
        this.dColumn = dColumn;
    }

    /**
     * Create a new command.
     * 
     * @param session
     *            the session in which to operate.
     * @param widgetWidth
     *            the new width to set for the table.
     * @param dTable
     *            the table to resize.
     */
    public ChangeColumnWidthCommand(Session session, int widgetWidth, DTable dTable) {
        this(session, widgetWidth, (DColumn) null);
        this.dTable = dTable;
    }

    @Override
    protected void doExecute() {
        try {
            ModelAccessor modelAccessor = session.getModelAccessor();
            if (dColumn != null) {
                modelAccessor.eSet(dColumn, TablePackage.eINSTANCE.getDColumn_Width().getName(), widgetWidth);
            } else {
                // It's about the DTable.headerColumnWidth
                modelAccessor.eSet(dTable, TablePackage.eINSTANCE.getDTable_HeaderColumnWidth().getName(), widgetWidth);
            }
        } catch (final LockedInstanceException e) {
            TableUIPlugin.getPlugin().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
        } catch (final FeatureNotFoundException e) {
            TableUIPlugin.getPlugin().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
        }
    }
}
