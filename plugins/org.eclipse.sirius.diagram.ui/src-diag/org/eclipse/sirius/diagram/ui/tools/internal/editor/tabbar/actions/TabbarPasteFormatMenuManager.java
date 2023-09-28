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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteImageAction;
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

    ArrayList<IAction> actionHistory = new ArrayList<IAction>();

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
        actionHistory.clear();
        super.dispose();
    }

    @Override
    protected void setDefaultAction(IAction defaultAction) {
        actionHistory.removeIf(candidate -> candidate.getId().equals(defaultAction.getId())); // remove by action id
        actionHistory.add(0, defaultAction);
        setHandler(defaultAction);
        super.setDefaultAction(defaultAction);
    }

    private void setDefaultActionWithoutRegister(IAction defaultAction) {
        setHandler(defaultAction);
        super.setDefaultAction(defaultAction);
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

    private Stream<IAction> getActions() {
        return Arrays.stream(getItems()) //
                .filter(ActionContributionItem.class::isInstance) //
                .map(ActionContributionItem.class::cast) //
                .map(ActionContributionItem::getAction);
    }

    private Optional<IAction> getAction(String actionId) {
        return getActions() //
                .filter(candidateAction -> actionId.equals(candidateAction.getId())) //
                .findAny();
    }

    private void safeAdd(String actionId, IWorkbenchPage page) {
        if (getAction(actionId).isEmpty()) { // add action only if it not already present
            if (ActionIds.PASTE_FORMAT.equals(actionId)) {
                add(new PasteFormatAction(page));
            } else if (ActionIds.PASTE_STYLE.equals(actionId)) {
                add(new PasteStyleAction(page));
            } else if (ActionIds.PASTE_LAYOUT.equals(actionId)) {
                add(new PasteLayoutAction(page));
            } else if (ActionIds.PASTE_IMAGE.equals(actionId)) {
                var action = new PasteImageAction();
                action.onChangeState(Optional.of(e -> update()));
                add(action);
            } else {
                throw new IllegalArgumentException(String.format("Unexpected action id '%s'", actionId)); //$NON-NLS-1$
            }
        }
    }

    private void updateActions(IWorkbenchPage page) {
        safeAdd(ActionIds.PASTE_FORMAT, page);
        safeAdd(ActionIds.PASTE_LAYOUT, page);
        safeAdd(ActionIds.PASTE_STYLE, page);
        if (elementIsSelected(page)) {
            safeAdd(ActionIds.PASTE_IMAGE, page);
        }
    }

    private boolean elementIsSelected(IWorkbenchPage page) {
        ISelection basicSelection = page.getSelection();
        if (basicSelection instanceof IStructuredSelection selection) {
            Object firstElement = selection.getFirstElement();
            return !(firstElement instanceof AbstractDDiagramEditPart);
        } else {
            return false;
        }
    }

    private Stream<IAction> getMenuActionCandidate() {
        // Menu default action, order of candidates:
        // - enabled in history
        // - enabled in all
        // - disabled in history
        // - disabled in all
        // - nothing/null

        // enabled in history
        Stream<IAction> actionHistoryEnabledStream = actionHistory.stream() //
                .filter(action -> action.isEnabled());
        // enabled in all action
        Stream<IAction> allActionEnabledStream = getActions() //
                .filter(action -> action.isEnabled());

        Stream<IAction> enabledActions = Stream.concat(actionHistoryEnabledStream, allActionEnabledStream);
        Stream<IAction> allActions = Stream.concat(actionHistory.stream(), getActions());

        return Stream.concat(enabledActions, allActions);
    }

    private void updateDefaultAction() {
        Optional<IAction> firstEnabledAction = getMenuActionCandidate().findFirst();

        firstEnabledAction.ifPresent(newDefaultAction -> {
            setDefaultActionWithoutRegister(newDefaultAction);
        });
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();
            if (page != null) {
                updateActions(page);
                updateDefaultAction();
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

        updateDefaultAction();
        IAction diagramAction = getDefaultAction();
        if (diagramAction != null) {
            // This action has already been refreshed because it is one of the "contribution items actions".
            action.setEnabled(diagramAction.isEnabled());
        }

        super.update();
    }
}
