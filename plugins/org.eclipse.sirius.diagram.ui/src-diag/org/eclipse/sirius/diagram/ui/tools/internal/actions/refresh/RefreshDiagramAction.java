/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectUIServices;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramHelper;
import org.eclipse.sirius.ui.business.api.action.RefreshActionListenerRegistry;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

import com.google.common.collect.Iterables;

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

            final Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            final ProgressMonitorDialog monitorDialog = new ProgressMonitorDialog(activeShell);
            try {
                monitorDialog.run(true, false, new RefreshRunnableWithProgress(minimizedSelection));
            } catch (final InvocationTargetException e) {
                MessageDialog.openError(activeShell, Messages.RefreshDiagramAction_error, e.getTargetException().getMessage());
                SiriusPlugin.getDefault().error(Messages.RefreshDiagramAction_refreshDiagramError, e);
            } catch (final InterruptedException e) {
                MessageDialog.openInformation(activeShell, Messages.RefreshDiagramAction_cancelled, e.getMessage());
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
