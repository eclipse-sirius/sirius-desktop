/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES and others.
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

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.AlignAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AlignMenuManager;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.ui.IWorkbenchPage;

/**
 * An arrange menu manager which handle cleanly {@link IDisposableAction} and
 * set correctly handler for tabbar.
 * 
 * @author mchauvin
 */
public class TabbarAlignMenuManager extends AlignMenuManager {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.ContributionManager#add(org.eclipse.jface.action.IAction)
     */
    @Override
    public void add(IAction action) {
        super.add(action);
        if (action instanceof IDisposableAction) {
            ((IDisposableAction) action).init();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager#itemRemoved(org.eclipse.jface.action.IContributionItem)
     */
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

    /**
     * {@inheritDoc}
     */
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
                add(new AlignAction(page, GEFActionConstants.ALIGN_LEFT, PositionConstants.LEFT));
                add(new AlignAction(page, GEFActionConstants.ALIGN_CENTER, PositionConstants.CENTER));
                add(new AlignAction(page, GEFActionConstants.ALIGN_RIGHT, PositionConstants.RIGHT));
                add(new AlignAction(page, GEFActionConstants.ALIGN_TOP, PositionConstants.TOP));
                add(new AlignAction(page, GEFActionConstants.ALIGN_MIDDLE, PositionConstants.MIDDLE));
                add(new AlignAction(page, GEFActionConstants.ALIGN_BOTTOM, PositionConstants.BOTTOM));
                setDefaultAction(GEFActionConstants.ALIGN_LEFT);
            }
        }
    }

}
