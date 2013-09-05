/*******************************************************************************
 * Copyright (c) 2008, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.actions.session;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.OpenWithMenu;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * Provide the OpenCloseAnalysisAction on double click.
 * 
 * @author mporhel
 */
public class OpenCloseSessionActionProvider extends CommonActionProvider {

    private ICommonViewerWorkbenchSite viewSite;

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.navigator.CommonActionProvider#init(org.eclipse.ui.navigator.ICommonActionExtensionSite)
     */
    @Override
    public void init(final ICommonActionExtensionSite aSite) {
        super.init(aSite);
        viewSite = (ICommonViewerWorkbenchSite) aSite.getViewSite();
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillActionBars(org.eclipse.ui.IActionBars)
     */
    @Override
    public void fillActionBars(final IActionBars actionBars) {
        super.fillActionBars(actionBars);
        final IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        OpenCloseSessionAction openCloseAnalysisAction = createOpenCloseAnalysisAction(selection);
        openCloseAnalysisAction.selectionChanged(selection);
        if (openCloseAnalysisAction.isEnabled()) {
            actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openCloseAnalysisAction);
        }
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    public void fillContextMenu(final IMenuManager menu) {
        super.fillContextMenu(menu);
        if (getContext().getSelection().isEmpty()) {
            return;
        }
        final IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        OpenCloseSessionAction openCloseAnalysisAction = createOpenCloseAnalysisAction(selection);
        if (openCloseAnalysisAction.isEnabled() && openCloseAnalysisAction.isOpenMode()) {
            menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openCloseAnalysisAction);
        }
        if (selection.getFirstElement() instanceof IFile) {
            final IFile file = (IFile) selection.getFirstElement();
            final IMenuManager submenu = new MenuManager("Open With", ICommonMenuConstants.GROUP_OPEN_WITH);
            submenu.add(new OpenWithMenu(viewSite.getPage(), file));
            if (submenu.getItems().length > 0 && submenu.isEnabled()) {
                menu.appendToGroup(ICommonMenuConstants.GROUP_OPEN_WITH, submenu);
            }
        }
    }

    private OpenCloseSessionAction createOpenCloseAnalysisAction(IStructuredSelection selection) {
        OpenCloseSessionAction openCloseSessionAction = new OpenCloseSessionAction("Open", "Close");
        openCloseSessionAction.selectionChanged(selection);
        return openCloseSessionAction;

    }
}
