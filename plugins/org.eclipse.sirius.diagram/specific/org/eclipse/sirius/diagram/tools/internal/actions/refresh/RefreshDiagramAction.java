/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

import com.google.common.collect.Iterables;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.diagram.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.part.DDiagramHelper;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;

/**
 * This action refresh the diagram.
 * 
 * @author cbrun
 */
public class RefreshDiagramAction extends RetargetAction {

    /** The selection. */
    private ISelection selection;

    /**
     * Create an ew {@link RefreshDiagramAction}.
     * 
     * @param actionID
     *            the action id
     * @param imageDescriptor
     *            an image descriptor for the UI
     */
    public RefreshDiagramAction(final String actionID, final ImageDescriptor imageDescriptor) {
        super(actionID, DiagramDialectUIServices.REFRESH_DIAGRAM, IAction.AS_PUSH_BUTTON);
        this.setImageDescriptor(imageDescriptor);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.RetargetAction#run()
     */
    @Override
    public void run() {
        this.selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        RefreshDiagramAction.refresh(selection);
    }

    /**
     * Refresh from the selection.
     * 
     * @param selection
     *            ISelection
     */
    public static void refresh(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            final Collection<EditPart> minimizedSelection = RefreshDiagramAction.minimizeSelection(Arrays.asList(structuredSelection.toArray()));

            if (!minimizedSelection.isEmpty()) {
                DDiagram diagram = DDiagramHelper.findParentDDiagram(Iterables.filter(minimizedSelection, IGraphicalEditPart.class).iterator().next());
                if (diagram != null) {
                    RefreshActionListenerRegistry.INSTANCE.notifyRepresentationIsAboutToBeRefreshed(diagram);
                }
            }
            // In case the diagram is frozen we allows the refresh, then we
            // enable the editMode, else the refreshRequest returns a null or
            // unexecutable command and we disable the editMode
            IRunnableWithProgress runnable = new IRunnableWithProgress() {

                public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                    try {
                        monitor.beginTask("Refresh diagram", minimizedSelection.size());
                        for (final EditPart selectedPart : minimizedSelection) {
                            if (selectedPart instanceof IEditableEditPart) {
                                IEditableEditPart editableEditPart = (IEditableEditPart) selectedPart;
                                editableEditPart.enableEditMode();
                            }
                            if (selectedPart instanceof IDDiagramEditPart) {
                                IDDiagramEditPart dDiagramEditPart = (IDDiagramEditPart) selectedPart;
                                Option<DDiagram> dDiagramOption = dDiagramEditPart.resolveDDiagram();
                                if (dDiagramOption.some()) {
                                    DDiagram dDiagram = dDiagramOption.get();
                                    TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(dDiagram);
                                    domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, new SubProgressMonitor(monitor, 1), dDiagram));
                                }
                            } else {
                                final Request refreshRequest = new GroupRequest(RequestConstants.REQ_REFRESH_VIEWPOINT);
                                Command refreshCmd = selectedPart.getCommand(refreshRequest);
                                if (refreshCmd != null && refreshCmd.canExecute() && selectedPart instanceof IGraphicalEditPart) {
                                    IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) selectedPart;
                                    graphicalEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(refreshCmd);
                                } else if (selectedPart instanceof IEditableEditPart) {
                                    IEditableEditPart editableEditPart = (IEditableEditPart) selectedPart;
                                    editableEditPart.disableEditMode();
                                }
                            }
                        }
                    } finally {
                        monitor.done();
                    }
                }
            };
            final Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
            try {
                monitorDialog.run(true, false, runnable);
            } catch (final InvocationTargetException e) {
                MessageDialog.openError(activeShell, "Error", e.getTargetException().getMessage());
                SiriusPlugin.getDefault().error("Error while refreshing diagram", e);
            } catch (final InterruptedException e) {
                MessageDialog.openInformation(activeShell, "Cancelled", e.getMessage());
            }

        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.RetargetAction#runWithEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void runWithEvent(final Event event) {
        super.runWithEvent(event);
        run();
    }

    private static Collection<EditPart> minimizeSelection(final List<?> selection) {
        final Collection<EditPart> result = new ArrayList<EditPart>(selection.size());
        final Iterator<?> iterSelection = new ArrayList<Object>(selection).iterator();
        while (iterSelection.hasNext()) {
            final Object next = iterSelection.next();
            if (next instanceof EditPart) {
                final EditPart editPart = (EditPart) next;
                if (RefreshDiagramAction.isNotAChild(editPart, selection)) {
                    result.add(editPart);
                } else {
                    iterSelection.remove();
                }
            } else {
                iterSelection.remove();
            }
        }
        return result;
    }

    private static boolean isNotAChild(final EditPart editPart, final Collection<?> selection) {
        boolean result = true;
        final Iterator<?> iterEditParts = selection.iterator();
        while (iterEditParts.hasNext() && result) {
            final EditPart currentEditPart = (EditPart) iterEditParts.next();
            if (currentEditPart != editPart) {
                if (iterEditParts.hasNext()) {
                    result = !RefreshDiagramAction.isAChild(editPart, (EditPart) iterEditParts.next());
                }
            }
        }
        return result;
    }

    private static boolean isAChild(final EditPart mayBeChild, final EditPart editPart) {
        boolean res = false;
        if (editPart.getChildren().contains(mayBeChild)) {
            res = true;
        }
        @SuppressWarnings("unchecked")
        final Iterator<EditPart> iterChildren = editPart.getChildren().iterator();
        while (iterChildren.hasNext() && !res) {
            final EditPart currentEditPart = iterChildren.next();
            res = RefreshDiagramAction.isAChild(mayBeChild, currentEditPart);
        }
        return res;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.RetargetAction#dispose()
     */
    @Override
    public void dispose() {
        super.dispose();
        selection = null;
    }
}
