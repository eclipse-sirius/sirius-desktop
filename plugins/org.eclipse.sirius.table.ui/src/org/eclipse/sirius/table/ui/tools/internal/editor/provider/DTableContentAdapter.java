/*******************************************************************************
 * Copyright (c) 2008, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.PlatformUI;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElement;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.ui.tools.internal.util.ItemSearcher;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * This class is an EMF Adapter which listen change in the model to update a
 * {@link DTableTreeViewer}.
 * 
 * @author lredor
 */
@SuppressWarnings("restriction")
public class DTableContentAdapter extends ResourceSetListenerImpl {

    private DTableViewerManager dTableViewerManager;

    /** The structured viewer to update. */
    private DTableTreeViewer dTableTreeViewer;

    /**
     * Default constructor.
     * 
     * @param dTableViewerManager
     *            the {@link DTableViewerManager} managing the
     *            {@link DTableTreeViewer} to update according to {@link DTable}
     *            model changes.
     */
    public DTableContentAdapter(DTableViewerManager dTableViewerManager) {
        this.dTableViewerManager = dTableViewerManager;
        this.dTableTreeViewer = (DTableTreeViewer) dTableViewerManager.getTreeViewer();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#isPostcommitOnly()
     */
    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    private boolean isCustom(Notification notif) {
        return notif.getEventType() == -1;
    }

    private void notifyChanged(final Notification notification) {
        if (dTableTreeViewer == null || dTableTreeViewer.getControl() == null || dTableTreeViewer.getControl().isDisposed()) {
            if (notification.getNotifier() instanceof EObject) {
                final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(notification.getNotifier());
                if (domain != null) {
                    domain.removeResourceSetListener(this);
                }
            }
            return;
        }

        final Object notifier = notification.getNotifier();

        if (notifier instanceof Resource) {
            final Resource resource = (Resource) notifier;

            // Indicates that at least one description file has changed or was
            // reloaded
            if (SiriusUtil.DESCRIPTION_MODEL_EXTENSION.equals(resource.getURI().fileExtension())) {
                if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_MODIFIED || notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED) {
                    dTableTreeViewer.setDescriptionFileChanged(true);
                }
            }
        }

        if (isChangeAboutDTableModel(notification)) {
            if (notifier instanceof DTable) {
                handleDTableNotification(notification, (DTable) notifier);
            } else if (notifier instanceof DLine) {
                handleDLineNotification(notification, (DLine) notifier);
            } else if (notifier instanceof DColumn) {
                handleDColumnNotification(notification, (DColumn) notifier);
            } else if (notifier instanceof DCell) {
                handleDCellNotification(notification, (DCell) notifier);
            } else if (notifier instanceof DTableElementStyle) {
                handleDTableElementStyleNotification(notification, (DTableElementStyle) notification.getNotifier());
            } else if (notifier instanceof RGBValues) {
                handleDCellNotification(notification, (RGBValues) notifier);
            }
        }
    }

    /**
     * Checks if the specified {@link Notification} is about a change in the
     * current {@link DTable}.
     * 
     * @param notification
     *            the specified {@link Notification}
     * @return true if the specified {@link Notification} comes from the table
     *         (or one of this children) concern by this content adapter, false
     *         otherwise.
     */
    private boolean isChangeAboutDTableModel(final Notification notification) {
        boolean isImpactingNotification = false;
        Object notifier = notification.getNotifier();
        DTable dTable = null;
        if (notifier instanceof EObject) {
            EObject eObject = (EObject) notifier;
            if (eObject instanceof DTable) {
                dTable = (DTable) eObject;
            }
            while (eObject != null && dTable == null) {
                if (eObject instanceof DTable) {
                    dTable = (DTable) eObject;
                } else {
                    eObject = eObject.eContainer();
                }
            }
        }
        if (dTable != null && dTableTreeViewer.isSameTable(dTable)) {
            isImpactingNotification = true;
        } else if (dTable == null && notifier instanceof DTableElement) {
            isImpactingNotification = true;
        }
        return isImpactingNotification;
    }

    /**
     * @param n
     * @param notifier
     */
    @SuppressWarnings("unchecked")
    private void handleDTableNotification(final Notification n, final DTable dTable) {
        final int featureID = n.getFeatureID(DTable.class);

        switch (featureID) {
        case TablePackage.DTABLE__LINES:
        case TablePackage.DTABLE__TARGET:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
            refreshViewer(dTable);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
            break;
        case TablePackage.DTABLE__HEADER_COLUMN_WIDTH:
            if (dTableViewerManager.getEditor().isPropertiesUpdateEnabled()) {
                Display.getDefault().asyncExec(new Runnable() {

                    public void run() {
                        if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                            Tree tree = dTableTreeViewer.getTree();
                            TreeColumn headerTreeColumn = tree.getColumn(0);
                            if (headerTreeColumn.getWidth() != dTable.getHeaderColumnWidth()) {
                                headerTreeColumn.setWidth(dTable.getHeaderColumnWidth());
                            }
                        }
                    }
                });
            }
            break;
        case TablePackage.DTABLE__COLUMNS:
            // The position is incremented by one cause of the header column
            if (n.getEventType() == Notification.ADD) {
                if (n.getNewValue() instanceof DColumn) {
                    add(n.getPosition(), (DColumn) n.getNewValue());
                }
            } else if (n.getEventType() == Notification.MOVE) {
                if (n.getNewValue() instanceof DTargetColumn) {
                    move(n.getPosition(), (Integer) n.getOldValue(), (DTargetColumn) n.getNewValue());
                }
            } else if (n.getEventType() == Notification.REMOVE) {
                if (n.getOldValue() instanceof DColumn) {
                    remove((DColumn) n.getOldValue());
                }
            } else if (n.getEventType() == Notification.REMOVE_MANY && n.getOldValue() instanceof EList) {
                for (final EObject oldValue : (EList<EObject>) n.getOldValue()) {
                    if (oldValue instanceof DTargetColumn) {
                        remove((DTargetColumn) oldValue);
                    }
                }
            }
            break;
        default:
            break;
        }
    }

    private void add(final int position, final DColumn newValue) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                    addNewColumn(position + 1, newValue);
                    dTableTreeViewer.refresh();
                }
            }
        });
    }

    private void move(final int position, final Integer oldValue, final DTargetColumn newValue) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                addNewColumn(position + 1, (DTargetColumn) newValue);
                final int newPosition = position + 1;
                final int oldPosition = oldValue.intValue() + 1;
                if (newPosition < oldPosition) {
                    removeOldColumn(oldPosition + 1);
                } else {
                    removeOldColumn(oldPosition);
                }
            }

        });
    }

    private void remove(final DColumn column) {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
            public void run() {
                if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                    removeOldColumn(column);
                }
            }
        });
    }

    /**
     * Remove a column from the table.
     * 
     * @param oldColumn
     *            The old column to remove
     */
    private void removeOldColumn(final DColumn oldColumn) {
        dTableTreeViewer.removeOldColumn(oldColumn);
    }

    /**
     * Remove a column from the table.
     * 
     * @param position
     *            The position of the old column
     */
    private void removeOldColumn(final int position) {
        dTableTreeViewer.removeOldColumn(position);
    }

    /**
     * Add a new column in the table.
     * 
     * @param position
     *            The position of the new column
     * @param newColumn
     *            The new targetColumn to add
     */
    private void addNewColumn(final int position, final DColumn newColumn) {
        DTableEditorUtil.addNewColumn(this.dTableViewerManager, position, newColumn);
    }

    /**
     * @param n
     * @param notifier
     */
    private void handleDLineNotification(final Notification n, final DLine dLine) {
        final int featureID = n.getFeatureID(DLine.class);

        switch (featureID) {
        case TablePackage.DLINE__LABEL:
        case TablePackage.DLINE__CELLS:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            updateViewer(dLine);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            break;
        case TablePackage.DLINE__ORDERED_CELLS:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            refreshViewer(dLine);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            break;
        case TablePackage.DLINE__LINES:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            refreshViewer(dLine);
            // The refresh doesn't update swt TreeItem expansion then we must do
            // it
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                        dTableTreeViewer.setExpandedState(dLine, !dLine.isCollapsed());
                    }
                }
            });
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_LINE_KEY);
            break;
        case TablePackage.DLINE__COLLAPSED:
            if (n.getNewValue() instanceof Boolean && dTableTreeViewer instanceof DTableTreeViewer) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_LINE_COLAPSE_STATE_KEY);
                final boolean collapsed = n.getNewBooleanValue();
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                    public void run() {
                        if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                            dTableTreeViewer.setExpandedState(dLine, !collapsed);
                        }
                    }
                });
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_LINE_COLAPSE_STATE_KEY);
            }
            break;
        case TablePackage.DLINE__VISIBLE:
            if (n.getNewValue() instanceof Boolean && dTableTreeViewer instanceof DTableTreeViewer) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_LINE_VISIBLE_STATE_KEY);
                dTableTreeViewer.refresh(false);
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_LINE_VISIBLE_STATE_KEY);
            }
            break;
        default:
            break;
        }
    }

    /**
     * @param n
     * @param notifier
     */
    private void handleDColumnNotification(final Notification n, final DColumn dColumn) {
        final int featureID = n.getFeatureID(DColumn.class);

        switch (featureID) {
        case TablePackage.DCOLUMN__LABEL:
            Display.getDefault().asyncExec(new Runnable() {
                public void run() {
                    if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                        for (int i = 0; i < dTableTreeViewer.getTree().getColumns().length; i++) {
                            final CellLabelProvider labelProvider = dTableTreeViewer.getLabelProvider(i);
                            if (labelProvider instanceof DTableColumnLabelProvider && ((DTableColumnLabelProvider) labelProvider).isProvideColumn(dColumn)) {
                                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);

                                TreeColumn treeColumn = dTableTreeViewer.getTree().getColumn(i);

                                ILabelProvider dTableColumnHeaderLabelProvider = new DTableColumnHeaderLabelProvider();

                                String text = dTableColumnHeaderLabelProvider.getText(dColumn);
                                treeColumn.setText(text);

                                Image image = dTableColumnHeaderLabelProvider.getImage(dColumn);
                                treeColumn.setImage(image);

                                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.SET_COLUMN_NAME_KEY);
                            }
                        }
                    }
                }
            });
            break;
        case TablePackage.DCOLUMN__ORDERED_CELLS:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_TABLE_KEY);
            dTableTreeViewer.refresh(true);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_TABLE_KEY);
            break;
        case TablePackage.DCOLUMN__VISIBLE:
            if (n.getNewValue() instanceof Boolean && dTableTreeViewer instanceof DTableTreeViewer) {
                Display.getDefault().asyncExec(new Runnable() {

                    public void run() {
                        if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                            for (int i = 0; i < dTableTreeViewer.getTree().getColumns().length; i++) {
                                final CellLabelProvider labelProvider = dTableTreeViewer.getLabelProvider(i);
                                if (labelProvider instanceof DTableColumnLabelProvider && ((DTableColumnLabelProvider) labelProvider).isProvideColumn(dColumn)) {
                                    DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY);
                                    final TreeColumn treeColumn = dTableTreeViewer.getTree().getColumn(i);
                                    if (n.getNewBooleanValue()) {
                                        // Show column
                                        final Integer restoredWith = (Integer) treeColumn.getData("restoredWidth");
                                        if (restoredWith != null) {
                                            treeColumn.setWidth(restoredWith.intValue());
                                            ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(restoredWith.intValue()));
                                        } else {
                                            treeColumn.pack();
                                            ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(treeColumn.getWidth()));
                                        }
                                    } else {
                                        // Hide column
                                        treeColumn.setData("restoredWidth", Integer.valueOf(treeColumn.getWidth()));
                                        treeColumn.setWidth(0);
                                        ((TreeColumnLayout) treeColumn.getParent().getParent().getLayout()).setColumnData(treeColumn, new ColumnPixelData(0));
                                    }
                                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.CHANGE_SWT_COLUMN_VISIBLE_STATE_KEY);
                                }
                            }
                        }
                    }
                });
            }
            break;
        case TablePackage.DCOLUMN__WIDTH:
            if (dTableViewerManager.getEditor().isPropertiesUpdateEnabled()) {
                Display.getDefault().asyncExec(new Runnable() {

                    public void run() {
                        if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                            Tree tree = dTableTreeViewer.getTree();
                            ItemSearcher itemSearcher = new ItemSearcher(tree, dColumn);
                            itemSearcher.run();
                            Object result = itemSearcher.getResult();
                            if (result instanceof TreeColumn) {
                                TreeColumn treeColumn = (TreeColumn) result;
                                if (treeColumn.getWidth() != dColumn.getWidth()) {
                                    treeColumn.setWidth(dColumn.getWidth());
                                }
                            }
                        }
                    }

                });
            }
            break;
        default:
            break;
        }
    }

    /**
     * @param n
     * @param notifier
     */
    private void handleDCellNotification(final Notification n, final DCell cell) {
        final int featureID = n.getFeatureID(DCell.class);

        switch (featureID) {
        case TablePackage.DCELL__LABEL:
        case TablePackage.DCELL__CURRENT_STYLE:
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            updateViewer(cell.getLine());
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            break;
        default:
            break;
        }
    }

    /**
     * @param n
     * @param notifier
     */
    private void handleDCellNotification(final Notification n, final RGBValues notifier) {
        final int featureID = n.getFeatureID(RGBValues.class);

        switch (featureID) {
        case ViewpointPackage.RGB_VALUES__BLUE:
        case ViewpointPackage.RGB_VALUES__RED:
        case ViewpointPackage.RGB_VALUES__GREEN:
            final DCell containerCell = TableHelper.getCell(notifier);
            if (containerCell != null && containerCell.getLine() != null) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
                updateViewer(containerCell.getLine());
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            }
            break;
        default:
            break;
        }
    }

    /**
     * @param n
     * @param notifier
     */
    private void handleDTableElementStyleNotification(final Notification n, final DTableElementStyle notifier) {
        final int featureID = n.getFeatureID(DTableElementStyle.class);

        switch (featureID) {
        case TablePackage.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR:
        case TablePackage.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR:
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_FORMAT:
        case TablePackage.DTABLE_ELEMENT_STYLE__LABEL_SIZE:
            if (notifier.eContainer() instanceof DCell) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
                updateViewer(((DCell) notifier.eContainer()).getLine());
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            } else if (notifier.eContainer() instanceof DLine) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
                updateViewer((DLine) notifier.eContainer());
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.UPDATE_SWT_LINE_KEY);
            } else if (notifier.eContainer() instanceof DColumn) {
                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
                refreshViewer(((DColumn) notifier.eContainer()).getTable());
                DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.REFRESH_SWT_TABLE_KEY);
            }
            break;
        default:
            break;
        }
    }

    private void refreshViewer(final Object object) {
        if (object != null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                        dTableTreeViewer.refresh(object, true);
                    }
                }
            });
        }
    }

    private void updateViewer(final Object object) {
        if (object != null) {
            PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
                public void run() {
                    if (dTableTreeViewer != null && dTableTreeViewer.getControl() != null && !dTableTreeViewer.getTree().isDisposed()) {
                        dTableTreeViewer.update(object, null);
                    }
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#resourceSetChanged(org.eclipse.emf.transaction.ResourceSetChangeEvent)
     */
    @Override
    public void resourceSetChanged(final ResourceSetChangeEvent event) {
        super.resourceSetChanged(event);
        for (Notification notif : event.getNotifications()) {
            if (!isCustom(notif)) {
                notifyChanged(notif);
            }
        }
    }

}
