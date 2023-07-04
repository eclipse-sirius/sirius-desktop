/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;

/**
 * This action launch all {@link RuleTool} to update the model.
 *
 * @author ymortier
 */
public class LaunchBehaviorToolAction extends RetargetAction {

    /** The selection. */
    private ISelection selection;

    /**
     * Create a new {@link LaunchBehaviorToolAction}.
     *
     * @param actionID
     *            the action id
     * @param imageDescriptor
     *            an image descriptor for the UI
     */
    public LaunchBehaviorToolAction(final String actionID, final ImageDescriptor imageDescriptor) {
        super(actionID, Messages.LaunchBehaviorToolAction_label, IAction.AS_PUSH_BUTTON);
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
        if (this.selection instanceof IStructuredSelection) {
            final IStructuredSelection structuredSelection = (IStructuredSelection) this.selection;
            final Collection minimizedSelection = LaunchBehaviorToolAction.minimizeSelection(new LinkedList(Arrays.asList(structuredSelection.toArray())));
            final Iterator<?> iterSelection = minimizedSelection.iterator();
            while (iterSelection.hasNext()) {
                final Object nextSelected = iterSelection.next();
                if (nextSelected instanceof EditPart) {
                    final Request refreshRequest = new GroupRequest(RequestConstants.REQ_LAUNCH_RULE_TOOL);
                    ((EditPart) nextSelected).performRequest(refreshRequest);
                }
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

    private static Collection<EditPart> minimizeSelection(final List<EditPart> selection) {
        final Collection<EditPart> result = new ArrayList<EditPart>(selection.size());
        final Iterator<EditPart> iterSelection = selection.iterator();
        while (iterSelection.hasNext()) {
            final Object next = iterSelection.next();
            if (next instanceof EditPart) {
                final EditPart editPart = (EditPart) next;
                if (LaunchBehaviorToolAction.isNotAChild(editPart, selection)) {
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

    private static boolean isNotAChild(final EditPart editPart, final Collection<EditPart> selection) {
        boolean result = true;
        final Iterator<EditPart> iterEditParts = selection.iterator();
        while (iterEditParts.hasNext() && result) {
            final EditPart currentEditPart = iterEditParts.next();
            if (currentEditPart != editPart) {
                if (iterEditParts.hasNext()) {
                    result = !LaunchBehaviorToolAction.isAChild(editPart, iterEditParts.next());
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
        var iterChildren = editPart.getChildren().iterator();
        while (iterChildren.hasNext() && !res) {
            final EditPart currentEditPart = (EditPart) iterChildren.next();
            res = LaunchBehaviorToolAction.isAChild(mayBeChild, currentEditPart);
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

}
