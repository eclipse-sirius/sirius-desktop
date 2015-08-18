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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.RouterAction;
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

    /**
     * Set the default action id for this menu manager.
     * 
     * @param actionId
     *            the action id to set
     */
    public void setDefaultAction(String actionId) {
        for (final IContributionItem item : getItems()) {
            if (item instanceof ActionContributionItem) {
                if (actionId.equals(((ActionContributionItem) item).getAction().getId())) {
                    final IAction defaultAction = ((ActionContributionItem) item).getAction();
                    setHandler(defaultAction);
                    super.setDefaultAction(defaultAction);
                    return;
                }
            }
        }
    }

    /**
     * We should use reflection to access the default handler method
     * 
     * @param defaultAction
     *            the default action to set
     */
    private void setHandler(final IAction defaultAction) {
        Method method;
        try {
            method = MenuCreatorAction.class.getDeclaredMethod("setActionHandler", IAction.class); //$NON-NLS-1$
            method.setAccessible(true);
            method.invoke(super.action, defaultAction);
        } catch (SecurityException e) {
            /* do nothing should not happen */
        } catch (NoSuchMethodException e) {
            /* do nothing should not happen */
        } catch (IllegalArgumentException e) {
            /* do nothing should not happen */
        } catch (IllegalAccessException e) {
            /* do nothing should not happen */
        } catch (InvocationTargetException e) {
            /* do nothing should not happen */
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isEmpty() && visible) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                RouterAction rectilinearAction = TabbarRouterAction.createRectilinearRouterAction(page);
                add(rectilinearAction);
                RouterAction obliqueAction = TabbarRouterAction.createObliqueRouterAction(page);
                add(obliqueAction);
                RouterAction treeAction = TabbarRouterAction.createTreeRouterAction(page);
                add(treeAction);
                // Use the last used action as default action (until no action
                // has been explicitly launched by expanding the combo, the
                // button has no effect).
                if (rectilinearAction.getText().equals(super.action.getText())) {
                    setDefaultAction(ActionIds.ACTION_ROUTER_RECTILINEAR);
                } else if (obliqueAction.getText().equals(super.action.getText())) {
                    setDefaultAction(ActionIds.ACTION_ROUTER_OBLIQUE);
                } else if (treeAction.getText().equals(super.action.getText())) {
                    setDefaultAction(ActionIds.ACTION_ROUTER_TREE);
                }
            }
        }
    }
}
