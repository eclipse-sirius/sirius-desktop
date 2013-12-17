/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.action;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.tools.internal.graphical.edit.part.DDiagramRootEditPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A ControlContribution that uses a {@link org.eclipse.swt.widgets.Button} as
 * its control.
 * 
 * @author cnotot
 */
public class DeleteFromDiagramContributionItem extends ActionContributionItem {

    private final IPartService service;

    private IPartListener partListener;

    private IWorkbenchPart representationPart;

    private final ISelectionListener editPartSelectionListener = new ISelectionListener() {
        public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
            // if selection is not in this item parent part => do nothing
            if (representationPart != null && !representationPart.equals(part)) {
                return;
            }
            getAction().setEnabled(shouldBeEnabled(selection));
            update();
        }
    };

    /**
     * Default Constructor for ComboToolItem.
     * 
     * @param action
     *            contributed action
     */
    public DeleteFromDiagramContributionItem(final IAction action) {
        super(action);
        service = null;
    }

    /**
     * Constructor for ComboToolItem.
     * 
     * @param action
     *            contributed action
     * @param partService
     *            used to add a PartListener and a SelectionListener
     */
    public DeleteFromDiagramContributionItem(final IAction action, final IPartService partService) {
        super(action);
        service = partService;
        partListener = new IPartListener() {
            public void partActivated(final IWorkbenchPart part) {
                final EditPart editPArt = (EditPart) part.getAdapter(EditPart.class);
                if (editPArt instanceof DDiagramRootEditPart) {
                    part.getSite().getPage().addSelectionListener(editPartSelectionListener);
                }

            }

            public void partBroughtToTop(final IWorkbenchPart p) {
            }

            public void partClosed(final IWorkbenchPart p) {
            }

            public void partDeactivated(final IWorkbenchPart p) {
                p.getSite().getPage().removeSelectionListener(editPartSelectionListener);
            }

            public void partOpened(final IWorkbenchPart p) {
            }
        };
        partService.addPartListener(partListener);
        IWorkbenchPart part = partService.getActivePart();
        if (part != null) {
            ISelectionProvider selectionProvider = part.getSite().getSelectionProvider();
            if (selectionProvider != null) {
                getAction().setEnabled(shouldBeEnabled(selectionProvider.getSelection()));
                update();
            }
        }
    }

    public void setItemPart(IWorkbenchPart itemPart) {
        this.representationPart = itemPart;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.ContributionItem#dispose()
     */
    @Override
    public void dispose() {
        if (partListener != null && service != null) {
            service.removePartListener(partListener);
        }

        if (representationPart != null && representationPart.getSite() != null && representationPart.getSite().getPage() != null) {
            representationPart.getSite().getPage().removeSelectionListener(editPartSelectionListener);
        }

        partListener = null;
        representationPart = null;
        super.dispose();
    }

    private boolean shouldBeEnabled(final ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            final IAction action = getAction();
            if (action instanceof DeleteFromDiagramAction) {
                final DeleteFromDiagramAction deleteAction = (DeleteFromDiagramAction) action;
                return deleteAction.shouldBeEnabled(selection);
            }
        }
        return false;
    }
}
