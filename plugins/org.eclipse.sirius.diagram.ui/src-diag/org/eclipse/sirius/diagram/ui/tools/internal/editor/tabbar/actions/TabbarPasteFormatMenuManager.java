/*******************************************************************************
 * Copyright (c) 2016, 2023 THALES GLOBAL SERVICES.
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

import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteLayoutAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteStyleAction;
import org.eclipse.ui.IWorkbenchPage;

/**
 * A paste format (layout and style) menu manager which handle cleanly
 * {@link IDisposableAction} and set correctly handler for tabbar.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class TabbarPasteFormatMenuManager extends PasteFormatMenuManager {

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
                add(new PasteFormatAction(page));
                add(new PasteLayoutAction(page));
                add(new PasteStyleAction(page));
                setDefaultAction(ActionIds.PASTE_FORMAT);
            }
        }
    }

    /**
     * Refresh the enablement of all the encapsulated actions and then update this contribution.
     *
     * When the diagram part is selected, the update of the tabbar is done before the enablement refresh of the action
     * for selection listener.
     *
     * {@inheritDoc}
     */
    @Override
    public void update() {
        for (IContributionItem contributionItem : getItems()) {
            if (contributionItem instanceof ActionContributionItem) {
                // There's no reason it shouldn't because the add of the actions creates an ActionContributionItem.
                IAction aContributedAction = ((ActionContributionItem) contributionItem).getAction();
                if (aContributedAction instanceof DiagramAction) {
                    // There's no reason it shouldn't because the 3 added actions are PasteFormatAction,
                    // PasteLayoutAction and PasteStyleAction.
                    ((DiagramAction) aContributedAction).refresh();
                }
            }
        }
        DiagramAction diagramAction = (DiagramAction) getDefaultAction();
        if (diagramAction != null) {
            // This action has already been refreshed because it is one of the "contribution items actions".
            action.setEnabled(diagramAction.isEnabled());
        }

        super.update();
    }
}
