/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramRootEditPart;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPartService;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A ControlContribution that uses a {@link org.eclipse.swt.widgets.Button} as
 * its control.
 * 
 * @author Maxime Porhel (mporhel)
 */
public class SiriusActionBarActionContributionItem extends ActionContributionItem {

    private IPartService service;

    private IPartListener partListener;

    private IWorkbenchPart representationPart;

    private ISelectionListener editPartSelectionListener = new ISelectionListener() {
        public void selectionChanged(final IWorkbenchPart part, final ISelection selection) {
            // if selection is not in this item parent part => do nothing
            if (representationPart != null && !representationPart.equals(part)) {
                return;
            }
            IAction action = getAction();
            if (action instanceof IActionDelegate) {
                ((IObjectActionDelegate) action).selectionChanged(action, selection);
            } else if (action instanceof ISelectionListener) {
                ((ISelectionListener) action).selectionChanged(part, selection);
            }
            update();
        }
    };

    /**
     * Default Constructor for an action.
     * 
     * @param action
     *            contributed action
     */
    public SiriusActionBarActionContributionItem(final IAction action) {
        super(action);
        service = null;
    }

    /**
     * Constructor for an action.
     * 
     * @param action
     *            contributed action
     * @param partService
     *            used to add a PartListener and a SelectionListener
     * @param part
     *            this contribution item workbench part.
     */
    public SiriusActionBarActionContributionItem(final IAction action, final IPartService partService, IWorkbenchPart part) {
        super(action);
        representationPart = part;
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
        IWorkbenchPart activePart = partService.getActivePart();
        if (activePart != null) {
            ISelectionProvider selectionProvider = activePart.getSite().getSelectionProvider();
            if (selectionProvider != null) {
                IAction action2 = getAction();
                if (action instanceof IActionDelegate) {
                    ((IObjectActionDelegate) action2).selectionChanged(action, selectionProvider.getSelection());
                } else if (action instanceof ISelectionListener) {
                    ((ISelectionListener) action2).selectionChanged(part, selectionProvider.getSelection());
                }
                update();
            }
        }
    }

    /**
     * Constructor for an action.
     * 
     * @param action
     *            contributed action
     * @param partService
     *            used to add a PartListener and a SelectionListener
     */
    public SiriusActionBarActionContributionItem(final IAction action, final IPartService partService) {
        this(action, partService, null);
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
        service = null;
        editPartSelectionListener = null;
        partListener = null;
        representationPart = null;
        super.dispose();
    }
}
