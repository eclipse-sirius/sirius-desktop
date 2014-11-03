/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.dialect.DRepresentationNotificationFilter;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DCellStyle;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.table.metamodel.table.DTargetColumn;
import org.eclipse.sirius.table.metamodel.table.TablePackage;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableTreeViewer;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableViewerManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.Sets;

/**
 * A class responsible to update the UI part of a {@link DTable}.
 * 
 * @author lredor
 */
public class TableUIUpdater extends ResourceSetListenerImpl {

    private DTableViewerManager dTableViewerManager;

    /** The structured viewer to update. */
    private DTableTreeViewer dTableTreeViewer;

    private Set<DLine> toExpands;

    private Set<DLine> toCollapses;

    private Set<Object> toRefreshInViewerWithUpdateLabels;

    private boolean launchGlobalRefreshWithoutUpdateLabels;

    private boolean launchGlobalRefreshWithUpdateLabels;

    private Set<Object> toUpdateInViewer;

    private Set<DColumn> dColumnsToUpdateDirectly;

    private Set<DColumn> dColumnsWidthToUpdate;

    private Set<DColumn> dColumnsToRemove;

    private Map<DColumn, Integer> dColumnsToAdd;

    private Map<DColumn, Boolean> dColumnsToVisibilityChanged;

    private boolean updateHeaderColumnWidth;

    /**
     * Default constructor.
     *
     * @param dTableViewerManager
     *            the {@link DTableViewerManager} to update according to
     *            {@link DTable} model changes.
     * @param dRepresentation
     *            the {@link DRepresentation} for which we update the
     *            {@link DTreeViewer}
     */
    public TableUIUpdater(DTableViewerManager dTableViewerManager, DRepresentation dRepresentation) {
        super(new DRepresentationNotificationFilter(dRepresentation));
        this.dTableTreeViewer = (DTableTreeViewer) dTableViewerManager.getTreeViewer();
        this.dTableViewerManager = dTableViewerManager;
        TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dRepresentation);
        if (domain != null) {
            domain.addResourceSetListener(this);
        }
    }

    @Override
    public boolean isPostcommitOnly() {
        return true;
    }

    @Override
    public void resourceSetChanged(final ResourceSetChangeEvent event) {
        toRefreshInViewerWithUpdateLabels = Sets.newLinkedHashSet();
        toUpdateInViewer = Sets.newLinkedHashSet();
        dColumnsToUpdateDirectly = new LinkedHashSet<DColumn>();
        dColumnsWidthToUpdate = new LinkedHashSet<DColumn>();
        toCollapses = Sets.newLinkedHashSet();
        toExpands = Sets.newLinkedHashSet();
        dColumnsToRemove = new LinkedHashSet<DColumn>();
        dColumnsToAdd = new LinkedHashMap<DColumn, Integer>();
        dColumnsToVisibilityChanged = new LinkedHashMap<DColumn, Boolean>();
        launchGlobalRefreshWithoutUpdateLabels = false;
        launchGlobalRefreshWithUpdateLabels = false;
        updateHeaderColumnWidth = false;

        analyseNotifications(event.getNotifications());
        updateDTableTreeViewer();
    }

    private void analyseNotifications(List<Notification> notifications) {
        for (Notification notification : notifications) {
            analyseNotification(notification);
        }
    }

    // CHECKSTYLE:OFF
    @SuppressWarnings("unchecked")
    private void analyseNotification(Notification notification) {
        Object notifier = notification.getNotifier();
        if (notifier instanceof DTable) {
            if (notification.getFeature() == TablePackage.Literals.DTABLE__HEADER_COLUMN_WIDTH) {
                updateHeaderColumnWidth = true;
            } else if (notification.getFeature() == TablePackage.Literals.DTABLE__COLUMNS) {
                // The position is incremented by one cause of the header column
                if (notification.getEventType() == Notification.ADD) {
                    if (notification.getNewValue() instanceof DColumn) {
                        dColumnsToAdd.put((DColumn) notification.getNewValue(), notification.getPosition() + 1);
                    }
                } else if (notification.getEventType() == Notification.MOVE) {
                    if (notification.getNewValue() instanceof DColumn) {
                        DColumn newValue = (DColumn) notification.getNewValue();
                        int newPosition = notification.getPosition() + 1;
                        dColumnsToAdd.put(newValue, newPosition);
                        dColumnsToRemove.add(newValue);
                    }
                } else if (notification.getEventType() == Notification.REMOVE) {
                    if (notification.getOldValue() instanceof DColumn) {
                        dColumnsToRemove.add((DColumn) notification.getOldValue());
                    }
                } else if (notification.getEventType() == Notification.REMOVE_MANY && notification.getOldValue() instanceof EList) {
                    for (final EObject oldValue : (EList<EObject>) notification.getOldValue()) {
                        if (oldValue instanceof DTargetColumn) {
                            dColumnsToRemove.add((DColumn) oldValue);
                        }
                    }
                }
            } else if (notification.getFeature() == ViewpointPackage.Literals.DSEMANTIC_DECORATOR__TARGET || notification.getFeature() == TablePackage.Literals.LINE_CONTAINER__LINES) {
                toRefreshInViewerWithUpdateLabels.add(notifier);
                if (notification.getFeature() == TablePackage.Literals.LINE_CONTAINER__LINES && notification.getNewValue() instanceof DLine) {
                    // By default created DLine is expand if default value of
                    // DLine.collapsed is false
                    DLine dLine = (DLine) notification.getNewValue();
                    if (!dLine.isCollapsed()) {
                        toExpands.add(dLine);
                        analyseExpansionStateOfCreatedChildren(dLine.getLines());
                    }
                }
            }
        } else if (notifier instanceof DLine) {
            DLine dLine = (DLine) notifier;
            if (notification.getFeature() == TablePackage.Literals.DLINE__LABEL || notification.getFeature() == TablePackage.Literals.DLINE__CELLS
                    || notification.getFeature() == TablePackage.Literals.DLINE__CURRENT_STYLE) {
                toUpdateInViewer.add(notifier);
            } else if (notification.getFeature() == TablePackage.Literals.DLINE__ORDERED_CELLS) {
                toRefreshInViewerWithUpdateLabels.add(notifier);
            } else if (notification.getFeature() == TablePackage.Literals.LINE_CONTAINER__LINES) {
                toRefreshInViewerWithUpdateLabels.add(notifier);
                // By default created DLine is expand of default value of
                // DLine.collapsed is false
                if (!dLine.isCollapsed()) {
                    toExpands.add(dLine);
                    analyseExpansionStateOfCreatedChildren(dLine.getLines());
                }
            } else if (notification.getFeature() == TablePackage.Literals.DLINE__VISIBLE) {
                launchGlobalRefreshWithoutUpdateLabels = true;
            } else if (notification.getFeature() == TablePackage.Literals.DLINE__COLLAPSED) {
                analyseExpansion(dLine);
            }
        } else if (notifier instanceof DColumn) {
            DColumn dColumn = (DColumn) notifier;
            if (notification.getFeature() == TablePackage.Literals.DCOLUMN__ORDERED_CELLS) {
                launchGlobalRefreshWithUpdateLabels = true;
            } else if (notification.getFeature() == TablePackage.Literals.DCOLUMN__LABEL) {
                dColumnsToUpdateDirectly.add(dColumn);
            } else if (notification.getFeature() == TablePackage.Literals.DCOLUMN__VISIBLE) {
                dColumnsToVisibilityChanged.put(dColumn, dColumn.isVisible());
            } else if (notification.getFeature() == TablePackage.Literals.DCOLUMN__WIDTH) {
                dColumnsWidthToUpdate.add(dColumn);
            }
        } else if (notification.getFeature() == TablePackage.Literals.DCELL__LABEL || notification.getFeature() == TablePackage.Literals.DCELL__CURRENT_STYLE && notifier instanceof DCell) {
            DCell dCell = (DCell) notifier;
            DLine dLine = dCell.getLine();
            if (dLine != null) {
                toUpdateInViewer.add(dLine);
            }
        } else if (isDTableElementStyleAttributeChange(notification)) {
            DTableElementStyle dTableElementStyle = (DTableElementStyle) notifier;
            EObject eContainer = dTableElementStyle.eContainer();
            if (eContainer instanceof DCell) {
                DCell dCell = (DCell) eContainer;
                DLine dLine = dCell.getLine();
                toUpdateInViewer.add(dLine);
            } else if (eContainer instanceof DLine) {
                toUpdateInViewer.add(eContainer);
            } else if (eContainer instanceof DColumn) {
                DColumn dColumn = (DColumn) eContainer;
                toRefreshInViewerWithUpdateLabels.add(dColumn.getTable());
            }
        } else if (notification.getNotifier() instanceof DCellStyle && isRGBValuesChange(notification)) {
            DCellStyle dCellStyle = (DCellStyle) notification.getNotifier();
            EObject dCellStyleContainer = dCellStyle.eContainer();
            if (dCellStyleContainer instanceof DCell) {
                DCell dCell = (DCell) dCellStyleContainer;
                DLine dLine = dCell.getLine();
                if (dLine != null) {
                    toUpdateInViewer.add(dLine);
                }
            }
        }
    }

    // CHECKSTYLE:ON

    private void analyseExpansionStateOfCreatedChildren(Collection<DLine> ownedDLines) {
        for (DLine ownedDLine : ownedDLines) {
            if (!ownedDLine.isCollapsed()) {
                toExpands.add(ownedDLine);
                analyseExpansionStateOfCreatedChildren(ownedDLine.getLines());
            }
        }
    }

    private void analyseExpansion(DLine dLine) {
        if (dLine.isCollapsed()) {
            toCollapses.add(dLine);
        } else {
            toExpands.add(dLine);
        }
    }

    private boolean isDTableElementStyleAttributeChange(Notification notification) {
        boolean isStyleFeature = notification.getFeature() == TablePackage.Literals.DTABLE_ELEMENT_STYLE__BACKGROUND_COLOR
                || notification.getFeature() == TablePackage.Literals.DTABLE_ELEMENT_STYLE__FOREGROUND_COLOR || notification.getFeature() == TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_FORMAT
                || notification.getFeature() == TablePackage.Literals.DTABLE_ELEMENT_STYLE__LABEL_SIZE;
        return isStyleFeature && notification.getNotifier() instanceof DTableElementStyle;
    }

    private boolean isRGBValuesChange(Notification notification) {
        return notification.getFeature() instanceof EAttribute && ((EAttribute) notification.getFeature()).getEType() == ViewpointPackage.Literals.RGB_VALUES;
    }

    // CHECKSTYLE:OFF
    private void updateDTableTreeViewer() {
        if (!toExpands.isEmpty() || !toCollapses.isEmpty() || !toRefreshInViewerWithUpdateLabels.isEmpty() || launchGlobalRefreshWithoutUpdateLabels || launchGlobalRefreshWithUpdateLabels
                || !toUpdateInViewer.isEmpty() || !dColumnsToUpdateDirectly.isEmpty() || !dColumnsWidthToUpdate.isEmpty() || !dColumnsToRemove.isEmpty() || !dColumnsToAdd.isEmpty()
                || !dColumnsToVisibilityChanged.isEmpty() || updateHeaderColumnWidth) {
            Object[] objectsToUpdateInViewer = Sets.difference(toUpdateInViewer, toRefreshInViewerWithUpdateLabels).toArray(new Object[0]);
            Runnable tableUIUpdaterRunnable = new TableUIUpdaterRunnable(dTableViewerManager, dTableTreeViewer, toExpands, toCollapses, toRefreshInViewerWithUpdateLabels,
                    launchGlobalRefreshWithoutUpdateLabels, launchGlobalRefreshWithUpdateLabels, objectsToUpdateInViewer, dColumnsToUpdateDirectly, dColumnsWidthToUpdate, dColumnsToRemove,
                    dColumnsToAdd, dColumnsToVisibilityChanged, updateHeaderColumnWidth);
            EclipseUIUtil.displayAsyncExec(tableUIUpdaterRunnable);
        }
    }

    // CHECKSTYLE:ON

    /**
     * Dispose this {@link TableUIUpdater}.
     */
    public void dispose() {
        if (getTarget() != null) {
            getTarget().removeResourceSetListener(this);
        }
        dTableTreeViewer = null;
        dTableViewerManager = null;
    }

}
