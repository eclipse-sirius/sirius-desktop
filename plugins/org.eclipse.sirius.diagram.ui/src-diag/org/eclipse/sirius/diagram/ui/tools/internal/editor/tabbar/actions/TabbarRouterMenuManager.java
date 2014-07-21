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

import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.RouterMenuManager;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.TabbarRouterAction;
import org.eclipse.ui.IWorkbenchPage;

/**
 * The tabbar router menu manager.
 * 
 * @author fbarbin
 */
@SuppressWarnings("restriction")
public class TabbarRouterMenuManager extends RouterMenuManager {

    @Override
    public void add(IAction action) {
        super.add(action);
        if (action instanceof IDisposableAction) {
            ((IDisposableAction) action).init();
        }
    }

    @Override
    protected void itemRemoved(IContributionItem item) {
        if (item instanceof ActionContributionItem) {
            IAction action = ((ActionContributionItem) item).getAction();
            if (action instanceof IDisposableAction) {
                ((IDisposableAction) action).dispose();
            }
        }
        super.itemRemoved(item);
    }

    @Override
    public void dispose() {
        removeAll();
        super.dispose();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isEmpty() && visible) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                add(TabbarRouterAction.createRectilinearRouterAction(page));
                add(TabbarRouterAction.createObliqueRouterAction(page));
                add(TabbarRouterAction.createTreeRouterAction(page));
            }
        }
    }
}
