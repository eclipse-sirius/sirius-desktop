/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.gmf.runtime.common.ui.action.IDisposableAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.LayoutingModeSwitchingAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.VisibilityModeSwitchingAction;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Menu manager used to display a drop down menu allowing to choose the edit mode for a diagram editor. The available
 * modes are the default one, the layouting and show/hide mode.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ModesMenuManager extends ActionMenuManager {

    /**
     * The default mode menu action containing the UI for the straighten menu manager
     */
    private static class StandardModeMenuAction extends Action {
        StandardModeMenuAction() {
            setText(Messages.EditModeAction_Label);
            ImageDescriptor imageDesc = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DEFAULT_MODE);
            setImageDescriptor(imageDesc);
            setHoverImageDescriptor(imageDesc);
        }
    }

    /**
     * Action to activate standard edit mode.
     */
    private StandardModeAction standardModeAction;

    /**
     * Action to activate layouting edit mode.
     */
    private LayoutingModeSwitchingAction layoutingModeSwitchingAction;

    /**
     * Action to activate visibility edit mode.
     */
    private VisibilityModeSwitchingAction visibilityModeSwitchingAction;

    /**
     * Creates a new instance of the menu manager.
     * 
     * @param page
     *            the current {@link IWorkbenchPage}.
     * @param editorDiagram
     *            the current {@link DDiagram}.
     */
    public ModesMenuManager(IWorkbenchPage page, DDiagram editorDiagram) {
        super(ActionIds.DEFAULT_MODE, new StandardModeMenuAction(), true);
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
        standardModeAction = null;
        layoutingModeSwitchingAction = null;
        visibilityModeSwitchingAction = null;
        removeAll();
        super.dispose();
    }

    /**
     * Refresh icon of all actions.
     */
    public void refresh() {
        standardModeAction.refresh();
        if (visibilityModeSwitchingAction != null) {
            visibilityModeSwitchingAction.refresh();
        }
        if (layoutingModeSwitchingAction != null) {
            layoutingModeSwitchingAction.refresh();
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (isEmpty() && visible) {
            IWorkbenchPage page = EclipseUIUtil.getActivePage();

            if (page != null && page.getActivePart() instanceof DDiagramEditor) {
                final DDiagramEditor editor = (DDiagramEditor) page.getActivePart();
                DDiagram editorDiagram = (DDiagram) editor.getRepresentation();

                boolean visibilityModeActive = VisibilityModeSwitchingAction.diagramAllowsVisibilityMode(editorDiagram);
                boolean layoutingModeActive = LayoutingModeSwitchingAction.diagramAllowsLayoutingMode(editorDiagram);
                if (!visibilityModeActive && !layoutingModeActive) {
                    super.setVisible(false);
                } else {

                    standardModeAction = new StandardModeAction(page, editor, editorDiagram, this);

                    add(standardModeAction);
                    if (visibilityModeActive) {
                        visibilityModeSwitchingAction = new VisibilityModeSwitchingAction(page, editor, editorDiagram, this);
                        add(visibilityModeSwitchingAction);
                    }
                    if (layoutingModeActive) {
                        layoutingModeSwitchingAction = new LayoutingModeSwitchingAction(page, editor, editorDiagram, this);
                        add(layoutingModeSwitchingAction);
                    }
                    if (editorDiagram != null && editorDiagram.isIsInLayoutingMode() && layoutingModeSwitchingAction != null) {
                        setDefaultAction(ActionIds.SWITCH_LAYOUTING_MODE);
                        layoutingModeSwitchingAction.setChecked(true);
                    } else if (editorDiagram != null && editorDiagram.isIsInShowingMode()) {
                        setDefaultAction(ActionIds.SWITCH_SHOWING_MODE);
                        visibilityModeSwitchingAction.setChecked(true);
                    } else {
                        setDefaultAction(ActionIds.DEFAULT_MODE);
                        standardModeAction.setChecked(true);
                    }
                }
            }
        } else if (!isEmpty() && !visible) {
            remove(ActionIds.SWITCH_LAYOUTING_MODE);
            remove(ActionIds.SWITCH_SHOWING_MODE);
            remove(ActionIds.DEFAULT_MODE);
            standardModeAction = null;
            layoutingModeSwitchingAction = null;
            visibilityModeSwitchingAction = null;
        }
    }
}
