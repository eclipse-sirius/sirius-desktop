/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.listeners;

import java.text.MessageFormat;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableEditorUtil;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthQuery;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * A {@link ITreeViewerListener} and {@link ControlListener} to update the
 * DTable model when a SWT TreeItem is collapsed/expanded and
 * {@link TreeColumn#getWidth()} change.
 *
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DTableViewerListener implements ITreeViewerListener, ControlListener {

    private DTableViewerManager dTableViewerManager;

    private DTable dTable;

    private ModelAccessor modelAccessor;

    private TransactionalEditingDomain domain;

    /**
     * Default constructor.
     *
     * @param dTableViewerManager
     *            the table viewer manager.
     * @param modelAccessor
     *            the {@link ModelAccessor} to query model
     * @param domain
     *            the {@link TransactionalEditingDomain} to update the model
     */
    public DTableViewerListener(DTableViewerManager dTableViewerManager, ModelAccessor modelAccessor, TransactionalEditingDomain domain) {
        this.dTableViewerManager = dTableViewerManager;
        this.dTable = (DTable) this.dTableViewerManager.getEditor().getRepresentation();
        this.modelAccessor = modelAccessor;
        this.domain = domain;
    }

    @Override
    public void treeExpanded(final TreeExpansionEvent event) {
        if (event.getElement() instanceof DLine) {
            DLine dLine = (DLine) event.getElement();
            if (dLine.isCollapsed()) {
                CommandStack commandStack = domain.getCommandStack();
                CompoundCommand cmd = new CompoundCommand(MessageFormat.format(Messages.DTableViewerListener_expandLine, dLine.getName()));
                Command expandDLineCmd = SetCommand.create(domain, dLine, TablePackage.Literals.DLINE__COLLAPSED, false);
                cmd.append(expandDLineCmd);
                commandStack.execute(cmd);
            }
        }
    }

    @Override
    public void treeCollapsed(final TreeExpansionEvent event) {
        if (event.getElement() instanceof DLine) {
            DLine dLine = (DLine) event.getElement();
            if (!dLine.isCollapsed()) {
                CommandStack commandStack = domain.getCommandStack();
                CompoundCommand cmd = new CompoundCommand(MessageFormat.format(Messages.DTableViewerListener_collapseLine, dLine.getName()));
                Command collapseDLineCmd = SetCommand.create(domain, dLine, TablePackage.Literals.DLINE__COLLAPSED, true);
                cmd.append(collapseDLineCmd);
                commandStack.execute(cmd);
            }
        }
    }

    @Override
    public void controlMoved(final ControlEvent event) {
    }

    @Override
    public void controlResized(final ControlEvent event) {
        // Keep call to isPropertiesUpdateEnabled() to avoid a dead-lock at
        // opening
        if (event.widget instanceof TreeColumn && dTableViewerManager.getEditor().isPropertiesUpdateEnabled()) {
            final TreeColumn treeColumn = (TreeColumn) event.widget;
            // Store the value if we can modify the column
            EObject objectToModify;
            String featureName;
            boolean mustUpdateModel = false;

            TreeColumnWidthQuery treeColumnWidthQuery = new TreeColumnWidthQuery(treeColumn);
            Display.getDefault().syncExec(treeColumnWidthQuery);
            int widgetWidth = treeColumnWidthQuery.getResult();

            final DColumn dColumn = (DColumn) treeColumn.getData(DTableViewerManager.TABLE_COLUMN_DATA);
            if (dColumn != null) {
                mustUpdateModel = dColumn.isVisible() && dColumn.getWidth() != widgetWidth;
                // If we are on an UNIX-based environment
                // we do not update the last dColumn as its width is always the
                // remaining width of the editor
                if (DTableViewerManager.IS_GTK_OS && DTableEditorUtil.isLastColumn(treeColumn)) {
                    mustUpdateModel = false;
                }
                objectToModify = dColumn;
                featureName = TablePackage.eINSTANCE.getDColumn_Width().getName();
            } else {
                mustUpdateModel = dTable.getHeaderColumnWidth() != widgetWidth;
                objectToModify = dTable;
                featureName = TablePackage.eINSTANCE.getDTable_HeaderColumnWidth().getName();
            }
            if (mustUpdateModel && modelAccessor.getPermissionAuthority().canEditFeature(objectToModify, featureName) && ((TransactionalEditingDomainImpl) domain).getActiveTransaction() == null) {
                // If the user has resized the column with a null size
                // We set it to 1 to avoid that the DColumn width is considered
                // as default
                if (widgetWidth == 0) {
                    widgetWidth = 1;
                }
                Command updateWidthCmd = null;
                if (objectToModify instanceof DColumn && widgetWidth != dColumn.getWidth()) {
                    updateWidthCmd = SetCommand.create(domain, dColumn, TablePackage.Literals.DCOLUMN__WIDTH, widgetWidth);
                } else if (dTable.getHeaderColumnWidth() != widgetWidth) {
                    updateWidthCmd = SetCommand.create(domain, dTable, TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH, widgetWidth);
                }
                domain.getCommandStack().execute(updateWidthCmd);
            }
        }
    }

}
