/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableEditorUtil;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthQuery;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * IPartListener to update the {@code TablePackage#DTABLE__HEADER_COLUMN_WIDTH}
 * feature of the {@link DTable} and the {@code TablePackage#DCOLUMN__WIDTH}
 * feature of the {@link DColumn}s at opening.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RefreshAtOpeningActivator implements IPartListener {

    private AbstractDTableEditor abstractDTableEditor;

    private boolean enablePropertiesRefreshOnNextActivate;

    private Session session;

    private DTable dTable;

    private Tree tree;

    /**
     * Default constructor.
     * 
     * @param session
     *            the current {@link Session}
     * @param abstractDTableEditor
     *            the {@link AbstractDTableEditor}
     */
    public RefreshAtOpeningActivator(Session session, AbstractDTableEditor abstractDTableEditor) {
        this.session = session;
        this.abstractDTableEditor = abstractDTableEditor;
        this.dTable = abstractDTableEditor.getTableModel();
        this.tree = (Tree) abstractDTableEditor.getControl();
    }

    /**
     * {@inheritDoc}
     */
    public void partActivated(IWorkbenchPart part) {
        if (part == abstractDTableEditor && enablePropertiesRefreshOnNextActivate) {
            refreshDTableModelFromTreeColumnWidth();

            // Activation of the refresh of the DTable property page
            abstractDTableEditor.enablePropertiesUpdate(true);
            abstractDTableEditor.forceRefreshProperties();

            enablePropertiesRefreshOnNextActivate = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void partBroughtToTop(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     */
    public void partClosed(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     */
    public void partDeactivated(IWorkbenchPart part) {

    }

    /**
     * {@inheritDoc}
     */
    public void partOpened(IWorkbenchPart part) {
        if (part == abstractDTableEditor) {
            enablePropertiesRefreshOnNextActivate = true;
        }
    }

    private void refreshDTableModelFromTreeColumnWidth() {
        if (DialectUIManager.INSTANCE.isRefreshActivatedOnRepresentationOpening()) {
            CompoundCommand refreshDTableAtOpeningCmd = new CompoundCommand(Messages.RefreshAtOpeningActivator_refreshTableCmdName);

            TreeColumn[] treeColumns = tree.getColumns();

            TreeColumn treeColumn = treeColumns[0];
            TreeColumnWidthQuery treeColumnWidthQuery = new TreeColumnWidthQuery(treeColumn);
            Display.getDefault().syncExec(treeColumnWidthQuery);
            int widgetWidth = treeColumnWidthQuery.getResult();
            if (dTable.getHeaderColumnWidth() != widgetWidth && session.getModelAccessor().getPermissionAuthority().canEditInstance(dTable)) {
                Command changeColumnWidthCommand = SetCommand.create(session.getTransactionalEditingDomain(), dTable, TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH, widgetWidth);
                refreshDTableAtOpeningCmd.append(changeColumnWidthCommand);
            }

            for (int i = 1; i < treeColumns.length; i++) {
                treeColumn = treeColumns[i];
                DColumn dColumn = (DColumn) treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA);
                treeColumnWidthQuery = new TreeColumnWidthQuery(treeColumn);
                Display.getDefault().syncExec(treeColumnWidthQuery);
                widgetWidth = treeColumnWidthQuery.getResult();

                // If opening the editor causes a resize of the DTable
                if (dColumn.isVisible() && dColumn.getWidth() != widgetWidth && session.getModelAccessor().getPermissionAuthority().canEditInstance(dColumn)) {
                    // If we are on an UNIX-based environment
                    // we do not update the last dColumn as its width is always
                    // the remaining width of the editor
                    if (!(DTableViewerManager.IS_GTK_OS && DTableEditorUtil.isLastColumn(treeColumn))) {
                        Command changeColumnWidthCommand = SetCommand.create(session.getTransactionalEditingDomain(), dColumn, TablePackage.Literals.DCOLUMN__WIDTH, widgetWidth);
                        refreshDTableAtOpeningCmd.append(changeColumnWidthCommand);
                    }

                }
            }
            if (refreshDTableAtOpeningCmd.canExecute()) {
                session.getTransactionalEditingDomain().getCommandStack().execute(refreshDTableAtOpeningCmd);
            }
        }
    }

}
