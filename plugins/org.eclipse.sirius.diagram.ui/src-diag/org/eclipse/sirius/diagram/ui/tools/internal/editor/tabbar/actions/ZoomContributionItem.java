/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * ActionContributionItem implementation for zoom actions. Create a part
 * listener to set zoom manager.
 * 
 * @author fbarbin
 * 
 */
public class ZoomContributionItem extends ActionContributionItem {
    private IPartListener listener;

    private IWorkbenchPage page;

    /**
     * Constructor.
     * 
     * @param action
     *            The action to wrap
     * @param page
     *            the workbench page
     */
    public ZoomContributionItem(final IAction action, IWorkbenchPage page) {
        super(action);
        this.page = page;
        listener = new IPartListener() {
            @Override
            public void partActivated(IWorkbenchPart part) {
                if (part instanceof DDiagramEditor) {
                    final ZoomManager zoomManager = (ZoomManager) part.getAdapter(ZoomManager.class);
                    if (action instanceof TabbarZoomAction) {
                        ((TabbarZoomAction) action).setZoomManager(zoomManager);
                    }
                }
            }

            @Override
            public void partBroughtToTop(IWorkbenchPart p) {
            }

            @Override
            public void partClosed(IWorkbenchPart p) {
            }

            @Override
            public void partDeactivated(IWorkbenchPart p) {
            }

            @Override
            public void partOpened(IWorkbenchPart p) {
            }
        };
        page.addPartListener(listener);
    }

    @Override
    public void dispose() {
        super.dispose();
        if (page != null) {
            page.removePartListener(listener);
            page = null;
        }
        listener = null;
    }
}
