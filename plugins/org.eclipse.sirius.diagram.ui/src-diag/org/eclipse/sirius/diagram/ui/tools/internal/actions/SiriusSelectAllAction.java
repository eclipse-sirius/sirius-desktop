/******************************************************************************
 * Copyright (c) 2002, 2020 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GroupEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IPrimaryEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.ISurfaceEditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.actions.ActionFactory;

/**
 * Sirius specific select all action: Only to always launch refresh in UI thread. This new class has been created
 * waiting the resolution in GMF of bugzilla 567128.<BR>
 * Only the method refresh is not from the original class
 * {@link org.eclipse.gmf.runtime.diagram.ui.actions.internal.SelectAllAction}. The field siriusOperationSet has been
 * also added instead of private _operationSet. So all methods using this field have also been duplicated (dispose,
 * getSiriusOperationSet, and internalRefresh).
 * 
 * @author lredor
 * 
 */
// CHECKSTYLE:OFF
public class SiriusSelectAllAction extends DiagramAction {

    /**
     * the cached operation set. This field replaces {@link DiagramAction#_operationSet} as it is private in super class
     * (used in dispose(), getOperationSet() and refresh());.
     */
    private List siriusOperationSet = Collections.EMPTY_LIST;

    /** whether to select shapes */
    private boolean selectShapes;

    /** whether to select connections */
    private boolean selectConnections;

    /**
     * @param partService
     * @param selectShapes
     * @param selectConnections
     */
    private SiriusSelectAllAction(IWorkbenchPage partService, boolean selectShapes, boolean selectConnections) {
        super(partService);
        this.selectShapes = selectShapes;
        this.selectConnections = selectConnections;
    }

    protected Request createTargetRequest() {
        return null;
    }

    /**
     * The operation set is the shapes, connections or both on the diagram edit part
     */
    protected List createOperationSet() {
        List selection = getSelectedObjects();
        if (selection.isEmpty() || !(selection.get(0) instanceof IGraphicalEditPart))
            return Collections.EMPTY_LIST;

        List selectables = new ArrayList();

        EditPart primaryEP = (EditPart) selection.get(selection.size() - 1);
        List nodeEditParts = new ArrayList();
        nodeEditParts.addAll(getSelectableNodes(primaryEP));

        if (selectShapes)
            selectables.addAll(nodeEditParts);
        if (selectConnections)
            selectables.addAll(addSelectableConnections(nodeEditParts));
        return filterEditPartsMatching(selectables, getSelectionConditional());
    }

    /**
     * Determines the candidate list of node editparts for selection
     * 
     * @param editpart
     * @return
     */
    protected List getSelectableNodes(EditPart editpart) {
        if (editpart == null) {
            return Collections.EMPTY_LIST;
        }

        List retval = new ArrayList();
        getSelectableNodesInside(editpart, true, retval);
        return retval;
    }

    /**
     * Determines the candidate list of node editparts for selection
     * 
     * @param editpart
     * @param topLevel
     *            <code>boolean</code> is this the initial entry point into the recursive method.
     * @param retval
     *            <code>List</code> to modify
     */
    private void getSelectableNodesInside(EditPart editpart, boolean topLevel, List retval) {

        if (editpart instanceof ISurfaceEditPart) {
            getSelectableChildrenNodes(editpart, retval);
        } else if (editpart instanceof IPrimaryEditPart) {
            if (topLevel) {
                if (editpart instanceof ConnectionEditPart) {
                    ConnectionEditPart connection = (ConnectionEditPart) editpart;
                    EditPart source = connection.getSource();
                    EditPart target = connection.getTarget();
                    if (source != null && target != null) {
                        getSelectableNodesInside(source, true, retval);
                        if (target.getParent() != source.getParent())
                            getSelectableNodesInside(target, true, retval);
                    }
                } else
                    getSelectableNodesInside(editpart.getParent(), true, retval);
            } else {
                if (editpart.isSelectable())
                    retval.add(editpart);

                // Do not dig into groups -- just select the group, but not the
                // shapes inside.
                if (!(editpart instanceof GroupEditPart)) {
                    getSelectableChildrenNodes(editpart, retval);
                }
            }
        }
    }

    private void getSelectableChildrenNodes(EditPart editpart, List retval) {
        Iterator iter = editpart.getChildren().iterator();
        while (iter.hasNext()) {
            EditPart child = (EditPart) iter.next();
            getSelectableNodesInside(child, false, retval);
        }
    }

    /**
     * This method searches an edit part for a child that is a border item edit part
     * 
     * @param parent
     *            part needed to search
     * @param set
     *            to be modified of border item edit parts that are direct children of the parent
     */
    private void getBorderItemEditParts(EditPart parent, Set retval) {

        Iterator iter = parent.getChildren().iterator();
        while (iter.hasNext()) {
            EditPart child = (EditPart) iter.next();
            if (child instanceof IBorderItemEditPart) {
                retval.add(child);
                retval.addAll(child.getChildren());
            }
            getBorderItemEditParts(child, retval);
        }
    }

    /**
     * Determines the candidate list of connection edit for selection A connection is included if atleast the source or
     * the target is included in the given list
     * 
     * @param editparts
     */
    protected List addSelectableConnections(List editparts) {
        List selectableConnections = new ArrayList();

        DiagramEditPart diagramEditPart = getDiagramEditPart();
        Set connnectableEditParts = new HashSet(editparts);
        ListIterator li = editparts.listIterator();
        while (li.hasNext()) {
            EditPart ep = (EditPart) li.next();
            getBorderItemEditParts(ep, connnectableEditParts);
            if (ep instanceof GroupEditPart) {
                connnectableEditParts.addAll(((GroupEditPart) ep).getShapeChildren());
            }
        }

        if (diagramEditPart != null) {
            Iterator connections = diagramEditPart.getConnections().iterator();
            while (connections.hasNext()) {
                ConnectionEditPart connection = (ConnectionEditPart) connections.next();
                if (canSelectConnection(connection, connnectableEditParts))
                    selectableConnections.add(connection);
            }
        }
        return selectableConnections;
    }

    /**
     * Determines whether the given connection can be selected. First checks whether the source or target of the
     * connection is in the given connetableEditPart list. If it isn't it checks recursively whether the source or
     * target of the connection is another connection and if that connection's source or target is in the given
     * connectableEditPart list. This is in response to Bugzilla #162083.
     * 
     * @param connection
     *            connection to check
     * @param connectableEditParts
     */
    private boolean canSelectConnection(ConnectionEditPart connection, Set connectableEditParts) {
        EditPart connectionSource = connection.getSource();
        EditPart connectionTarget = connection.getTarget();
        boolean sourceHasSelectable = false;
        boolean targetHasSelectable = false;

        if (connectableEditParts.contains(connectionSource) || connectableEditParts.contains(connectionTarget))
            return true;

        if (connectionSource instanceof ConnectionEditPart)
            sourceHasSelectable = canSelectConnection((ConnectionEditPart) connectionSource, connectableEditParts);

        if (!sourceHasSelectable && connectionTarget instanceof ConnectionEditPart)
            targetHasSelectable = canSelectConnection((ConnectionEditPart) connectionTarget, connectableEditParts);

        return sourceHasSelectable || targetHasSelectable;
    }

    /**
     * @return The Selection Conditional which tests if the editpart is selectable
     */
    protected EditPartViewer.Conditional getSelectionConditional() {
        return new EditPartViewer.Conditional() {
            public boolean evaluate(EditPart editpart) {
                return editpart.isSelectable();
            }
        };
    }

    /**
     * Returns true if the operation set is not empty and only if the diagram is selected.
     */
    protected boolean calculateEnabled() {
        return !getSiriusOperationSet().isEmpty();
    }

    protected void doRun(IProgressMonitor progressMonitor) {
        getDiagramGraphicalViewer().setSelection(new StructuredSelection(getSiriusOperationSet()));
    }

    protected boolean isSelectionListener() {
        return true;
    }

    protected boolean isOperationHistoryListener() {
        // certain undo/redo actions can cause selection to change. see bugzilla#156261
        return true;
    }

    /**
     * Create the SelectAll action
     * 
     * @return The SelectAll action
     */
    public static SiriusSelectAllAction createSelectAllAction(IWorkbenchPage partService) {
        SiriusSelectAllAction action = new SiriusSelectAllAction(partService, true, true);
        action.setId(ActionFactory.SELECT_ALL.getId());
        action.setText(DiagramUIActionsMessages.SelectAllAction_SelectAll);
        action.setToolTipText(DiagramUIActionsMessages.SelectAllAction_SelectAll);
        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTALL);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTALL_DISABLED);
        return action;
    }

    /**
     * Create the SelectAll toolbar action
     * 
     * @return The SelectAll toobar action
     */
    public static SiriusSelectAllAction createToolbarSelectAllAction(IWorkbenchPage partService) {
        SiriusSelectAllAction action = new SiriusSelectAllAction(partService, true, true);
        action.setId(ActionIds.ACTION_SIRIUS_TOOLBAR_SELECT_ALL);
        action.setText(DiagramUIActionsMessages.SelectAllAction_toolbar_SelectAll);
        action.setToolTipText(DiagramUIActionsMessages.SelectAllAction_toolbar_SelectAll);
        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTALL);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTALL_DISABLED);
        return action;
    }

    /**
     * Create the SelectAllShapes action
     * 
     * @return The SelectAllShapes action
     */
    public static SiriusSelectAllAction createSelectAllShapesAction(IWorkbenchPage partService) {
        SiriusSelectAllAction action = new SiriusSelectAllAction(partService, true, false);
        action.setId(ActionIds.ACTION_SIRIUS_SELECT_ALL_SHAPES);
        action.setText(DiagramUIActionsMessages.SelectAllAction_SelectShapes);
        action.setToolTipText(DiagramUIActionsMessages.SelectAllAction_SelectShapes);
        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTSHAPES);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTSHAPES_DISABLED);
        return action;
    }

    /**
     * Create the SelectAllShapes toolbar action
     * 
     * @return The SelectAllShapes toolbar action
     */
    public static SiriusSelectAllAction createToolbarSelectAllShapesAction(IWorkbenchPage partService) {
        SiriusSelectAllAction action = new SiriusSelectAllAction(partService, true, false);
        action.setId(ActionIds.ACTION_SIRIUS_TOOLBAR_SELECT_ALL_SHAPES);
        action.setText(DiagramUIActionsMessages.SelectAllAction_toolbar_SelectShapes);
        action.setToolTipText(DiagramUIActionsMessages.SelectAllAction_toolbar_SelectShapes);
        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTSHAPES);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTSHAPES_DISABLED);
        return action;
    }

    /**
     * Create the SelectAllConnections action
     * 
     * @return The SelectAllConnections action
     */
    public static SiriusSelectAllAction createSelectAllConnectionsAction(IWorkbenchPage partService) {
        SiriusSelectAllAction action = new SiriusSelectAllAction(partService, false, true);
        action.setId(ActionIds.ACTION_SIRIUS_SELECT_ALL_CONNECTIONS);
        action.setText(DiagramUIActionsMessages.SelectAllAction_SelectConnections);
        action.setToolTipText(DiagramUIActionsMessages.SelectAllAction_SelectConnections);
        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTCONNECTIONS);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTCONNECTIONS_DISABLED);
        return action;
    }

    /**
     * Create the SelectAllConnections toolbar action
     * 
     * @return The SelectAllConnections toolbar action
     */
    public static SiriusSelectAllAction createToolbarSelectAllConnectionsAction(IWorkbenchPage partService) {
        SiriusSelectAllAction action = new SiriusSelectAllAction(partService, false, true);
        action.setId(ActionIds.ACTION_SIRIUS_TOOLBAR_SELECT_ALL_CONNECTIONS);
        action.setText(DiagramUIActionsMessages.SelectAllAction_toolbar_SelectConnections);
        action.setToolTipText(DiagramUIActionsMessages.SelectAllAction_toolbar_SelectConnections);
        action.setImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTCONNECTIONS);
        action.setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_SELECTCONNECTIONS_DISABLED);
        return action;
    }

    @Override
    public void dispose() {
        super.dispose();
        siriusOperationSet = null;
    }

    /**
     * A copy of {@link DiagramAction#getOperationSet()} to manipulate siriusOperationSet instead of _operationSet.
     * 
     * Return the list of editparts considered the operation set after caching them
     * 
     * @return A list of editparts conidered the operation set
     */
    protected final List getSiriusOperationSet() {
        if (siriusOperationSet == null) {
            siriusOperationSet = createOperationSet();
            if (siriusOperationSet == null)
                siriusOperationSet = Collections.EMPTY_LIST;
        }
        return siriusOperationSet;
    }

    @Override
    public void refresh() {
        if (Display.getCurrent() == null) {
            /*
             * We are not in a UI thread, so we call the refresh later to avoid potential
             * ConcurrentModificationException or worse.
             */
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    internalRefresh();
                }
            });
        } else {
            /* Here we are in UI Thread */
            internalRefresh();
        }
    }

    /**
     * A copy of original {@link DiagramAction#refresh()} but using siriusOperationSet instead of _operationSet.
     */
    public void internalRefresh() {
        siriusOperationSet = null; // invalidate the cached set
        updateTargetRequest();
        setEnabled(calculateEnabled());
    }
}
// CHECKSTYLE:ON
