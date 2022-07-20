/*******************************************************************************
 * Copyright (c) 2017, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.Status;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.straighten.StraightenToAction;
import org.eclipse.ui.IWorkbenchPage;

/**
 * A distribute menu manager which handle cleanly {@link IDisposableAction} and set correctly handler for tabbar.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class TabbarStraightenToMenuManager extends StraightenToMenuManager {

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
    @Override
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
        } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            DiagramUIPlugin.getPlugin().getLog().log(new Status(Status.ERROR, DiagramUIPlugin.ID, e.getMessage(), e));
        }
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (isEmpty() && visible) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                add(StraightenToAction.createStraightenToTopAction(page));
                add(StraightenToAction.createStraightenToBottomAction(page));
                add(StraightenToAction.createStraightenLeftSidePinnedAction(page));
                add(StraightenToAction.createStraightenRightSidePinnedAction(page));
                add(new Separator());
                add(StraightenToAction.createStraightenToLeftAction(page));
                add(StraightenToAction.createStraightenToRightAction(page));
                add(StraightenToAction.createStraightenTopSidePinnedAction(page));
                add(StraightenToAction.createStraightenBottomSidePinnedAction(page));
                setDefaultAction(ActionIds.STRAIGHTEN_TO_TOP);
            }
        }
    }

}
