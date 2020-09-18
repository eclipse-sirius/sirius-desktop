/*******************************************************************************
 * Copyright (c) 2010, 2020 THALES GLOBAL SERVICES and others.
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
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.SelectMenuManager;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SiriusSelectAllAction;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.PopupMenuContribution;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;

/**
 * A select menu manager which handle cleanly {@link IDisposableAction} and set correctly handler for tabbar. <BR>
 * This menu contains GMF global SelectAll actions plus SelectAll actions defined in the VSM.
 * 
 * @author mchauvin
 */
public class TabbarSelectMenuManager extends SelectMenuManager {

    private boolean contributionsAdded;

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
        if (visible) {
            if (isEmpty()) {
                IWorkbenchPage page = EclipseUIUtil.getActivePage();
                if (page != null) {
                    add(SiriusSelectAllAction.createToolbarSelectAllAction(page));
                    add(SiriusSelectAllAction.createToolbarSelectAllConnectionsAction(page));
                    add(SiriusSelectAllAction.createToolbarSelectAllShapesAction(page));
                    setDefaultAction(ActionIds.ACTION_SIRIUS_TOOLBAR_SELECT_ALL);

                }
            }
            if (!contributionsAdded) {
                IEditorPart editorPart = EclipseUIUtil.getActiveEditor();
                if (editorPart instanceof IDiagramWorkbenchPart && ((IDiagramWorkbenchPart) editorPart).getDiagramGraphicalViewer() != null) {
                    // Add potential select all actions contributed in VSM
                    PopupMenuContribution.contributeToPopupMenuProgrammatically(this, editorPart, true);
                    contributionsAdded = true;
                }
            }
        } else {
            // Remove actions contributed from VSM. They will be added on the next call to setVisible(true).
            for (int i = getItems().length - 1; i > 2; i--) {
                remove(getItems()[i]);
            }
            contributionsAdded = false;
        }
    }
}
