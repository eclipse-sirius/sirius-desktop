/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES
 * All rights reserved.
 *
 * Contributors:
 *      Obeo - Initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.diagram.tools.internal.actions.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IEditableEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.diagram.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DDiagram;

/**
 * In case the diagram is frozen we allow the refresh (enable editMode and
 * launch refresh). For diagramEditPart, it stay in enable editMode. For other
 * editPart, we restore the old editMode value if the refreshRequest returns a
 * null or unexecutable command.
 * 
 * @author lredor
 * 
 */
public class RefreshRunnableWithProgress implements IRunnableWithProgress {
    Collection<EditPart> editPartsToRefresh;

    /**
     * Default constructor.
     * 
     * @param editPartsToRefresh
     *            The edit parts to refresh
     */
    public RefreshRunnableWithProgress(Collection<EditPart> editPartsToRefresh) {
        this.editPartsToRefresh = editPartsToRefresh;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        try {
            monitor.beginTask("Refresh", editPartsToRefresh.size());
            for (final EditPart editPartToRefresh : editPartsToRefresh) {
                if (editPartToRefresh instanceof IDDiagramEditPart) {
                    refreshFromDiagramEditPart((IDDiagramEditPart) editPartToRefresh, monitor);
                } else {
                    refreshFromEditPart(editPartToRefresh, monitor);
                }
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Refresh the diagram (enable editMode and launch refresh).
     * 
     * @param dDiagramEditPart
     *            The diagram edit part to refresh.
     * @param progressMonitor
     *            Progress monitor to use.
     */
    private void refreshFromDiagramEditPart(IDDiagramEditPart dDiagramEditPart, IProgressMonitor progressMonitor) {
        // Enable edit mode for the diagram.
        dDiagramEditPart.enableEditMode();
        Option<DDiagram> dDiagramOption = dDiagramEditPart.resolveDDiagram();
        if (dDiagramOption.some()) {
            DDiagram dDiagram = dDiagramOption.get();
            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
            domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, new SubProgressMonitor(progressMonitor, 1), dDiagram));
        }
    }

    /**
     * Refresh this editPart: enable editMode, get refresh command. If the
     * refresh command is executable, launch it. Otherwise, restore the old
     * editMode state if it is disabled before.
     * 
     * @param editPart
     *            The edit part to refresh.
     * @param progressMonitor
     *            Progress monitor to use.
     */
    private void refreshFromEditPart(EditPart editPart, IProgressMonitor progressMonitor) {
        Option<Boolean> oldEditModeState = Options.newNone();
        if (editPart instanceof IEditableEditPart) {
            // Keep the old editMode state for editPart and activate editMode.
            IEditableEditPart editableEditPart = (IEditableEditPart) editPart;
            oldEditModeState = Options.newSome(editableEditPart.isEditModeEnabled());
            editableEditPart.enableEditMode();
        }
        // Get the command from refresh request on the "enabledMode" editPart.
        final Request refreshRequest = new GroupRequest(RequestConstants.REQ_REFRESH_VIEWPOINT);
        Command refreshCmd = editPart.getCommand(refreshRequest);
        if (refreshCmd != null && refreshCmd.canExecute() && editPart instanceof IGraphicalEditPart) {
            // Launch the refresh and stay it enableMode.
            IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) editPart;
            graphicalEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(refreshCmd);
        } else if (editPart instanceof IEditableEditPart && oldEditModeState.get() && !oldEditModeState.some()) {
            // Restore the old editMode state (if disable)
            ((IEditableEditPart) editPart).disableEditMode();
        }
    }
}