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
package org.eclipse.sirius.table.ui.tools.internal.editor.listeners;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeColumn;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.metamodel.table.provider.TableUIPlugin;
import org.eclipse.sirius.table.ui.tools.internal.command.ChangeColumnWidthCommand;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.table.ui.tools.internal.editor.provider.DTableEditorUtil;
import org.eclipse.sirius.table.ui.tools.internal.editor.utils.TreeColumnWidthQuery;
import org.eclipse.sirius.tools.api.interpreter.IInterpreterMessages;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.FeatureNotFoundException;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;

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

    private Session session;

    private TransactionalEditingDomain domain;

    /**
     * Default constructor.
     * 
     * @param dTableViewerManager
     *            the table viewer manager.
     * @param session
     *            the session.
     */
    public DTableViewerListener(DTableViewerManager dTableViewerManager, Session session) {
        this.dTableViewerManager = dTableViewerManager;
        this.dTable = (DTable) this.dTableViewerManager.getEditor().getRepresentation();
        this.session = session;
        this.domain = session.getTransactionalEditingDomain();
    }

    /**
     * {@inheritDoc}
     */
    public void treeExpanded(final TreeExpansionEvent event) {
        if (event.getElement() instanceof DLine) {
            DLine dLine = (DLine) event.getElement();
            if (dLine.isCollapsed()) {
                CommandStack commandStack = domain.getCommandStack();
                CompoundCommand expandDLineCmd = new CompoundCommand("Expand " + dLine.getName() + " line");
                expandDLineCmd.append(new SetAccessorRecordingCommand(session, event, false));
                commandStack.execute(expandDLineCmd);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void treeCollapsed(final TreeExpansionEvent event) {
        if (event.getElement() instanceof DLine) {
            DLine dLine = (DLine) event.getElement();
            if (!dLine.isCollapsed()) {
                CommandStack commandStack = domain.getCommandStack();
                CompoundCommand expandDTreeItemCmd = new CompoundCommand("Collapse " + dLine.getName() + " line");
                expandDTreeItemCmd.append(new SetAccessorRecordingCommand(session, event, true));
                commandStack.execute(expandDTreeItemCmd);
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.ControlListener#controlMoved(org.eclipse.swt.events.ControlEvent)
     */
    public void controlMoved(final ControlEvent event) {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.ControlListener#controlResized(org.eclipse.swt.events.ControlEvent)
     */
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
            ModelAccessor modelAccessor = session.getModelAccessor();
            TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
            if (mustUpdateModel && modelAccessor.getPermissionAuthority().canEditFeature(objectToModify, featureName) && ((TransactionalEditingDomainImpl) ted).getActiveTransaction() == null) {
                // If the user has resized the column with a null size
                // We set it to 1 to avoid that the DColumn width is considered
                // as default
                if (widgetWidth == 0) {
                    widgetWidth = 1;
                }
                if (objectToModify instanceof DColumn && widgetWidth != dColumn.getWidth()) {
                    ted.getCommandStack().execute(new ChangeColumnWidthCommand(session, widgetWidth, dColumn));
                } else if (dTable.getHeaderColumnWidth() != widgetWidth) {
                    ted.getCommandStack().execute(new ChangeColumnWidthCommand(session, widgetWidth, dTable));
                }

            }
        }
    }

    /**
     * EMF Command to synchronize the DTable model according to a
     * {@link TreeExpansionEvent}.
     * 
     * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban
     *         Dugueperoux</a>
     */
    private static class SetAccessorRecordingCommand extends RecordingCommand {

        private Session session;

        private TreeExpansionEvent event;

        private Object value;

        public SetAccessorRecordingCommand(Session session, TreeExpansionEvent event, Object value) {
            super(session.getTransactionalEditingDomain());
            this.session = session;
            this.event = event;
            this.value = value;
        }

        @Override
        protected void doExecute() {
            try {
                session.getModelAccessor().eSet((DLine) event.getElement(), TablePackage.eINSTANCE.getDLine_Collapsed().getName(), value);
            } catch (final LockedInstanceException e) {
                TableUIPlugin.getPlugin().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
            } catch (final FeatureNotFoundException e) {
                TableUIPlugin.getPlugin().error(IInterpreterMessages.EVALUATION_ERROR_ON_MODEL_MODIFICATION, e);
            }
        }

    }
}
