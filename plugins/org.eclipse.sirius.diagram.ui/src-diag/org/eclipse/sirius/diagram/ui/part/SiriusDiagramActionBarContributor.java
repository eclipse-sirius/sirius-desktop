/*******************************************************************************
 * Copyright (c) 2007, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.part;

import java.util.Optional;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.Disposable;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.CommonUIServicesActionPlugin;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.CommonUIServicesActionStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.jface.action.AbstractGroupMarker;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.action.ConcernComboContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.action.SiriusActionBarActionContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.LaunchBehaviorToolAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SelectHiddenElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.TabbarRouterAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.CopyFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteImageAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteLayoutAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteStyleAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh.RefreshDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealAllElementsAction;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * @was-generated
 */
public class SiriusDiagramActionBarContributor extends DiagramActionBarContributor {

    boolean disposed;

    /**
     * @was-generated
     */
    @Override
    protected Class<?> getEditorClass() {
        return SiriusDiagramEditor.class;
    }

    /**
     * Action id for the "Launch behavior"
     */
    public static final String LAUNCH_BEHAVIOR = Messages.SiriusDiagramActionBarContributor_launchBehavior;

    /**
     * Action id for the "Refresh DDiagram"
     */
    public static final String REFRESH_DIAGRAM = Messages.SiriusDiagramActionBarContributor_refreshDiagram;

    /**
     * Action id for the "Hide element"
     */
    public static final String HIDE_ELEMENT = Messages.SiriusDiagramActionBarContributor_hideElement;

    /**
     * Action id for the "Show element"
     */
    public static final String SHOW_ELEMENT = Messages.SiriusDiagramActionBarContributor_showElement;

    /**
     * Action id for the "Hide label"
     */
    public static final String HIDE_LABEL = Messages.SiriusDiagramActionBarContributor_hideLabel;

    /**
     * Action id for the "Show label"
     */
    public static final String SHOW_LABEL = Messages.RevealOutlineLabelsAction_label;

    /**
     * Action id for the "Reveal elements"
     */
    public static final String REVEAL_ELEMENTS = Messages.SiriusDiagramActionBarContributor_revealElement;

    /**
     * Action tooltip text for the "Delete from Diagram"
     */
    public static final String DELETE_FROM_DIAGRAM = Messages.SiriusDiagramActionBarContributor_deleteFromDiagram;

    /**
     * Action tooltip text for the "Delete from Diagram"
     */
    public static final String DELETE_FROM_MODEL = Messages.SiriusDiagramActionBarContributor_deleteFromModel;

    /**
     * @was-generated
     */
    @Override
    protected String getEditorId() {
        return DDiagramEditor.EDITOR_ID;
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated NOT
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor#buildActions()
     */
    @Override
    protected void buildActions() {
        super.buildActions();
        final RetargetAction action = new LaunchBehaviorToolAction(LAUNCH_BEHAVIOR, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.GO_IMG));
        addAction(action);

        final RetargetAction refresh = new RefreshDiagramAction(REFRESH_DIAGRAM, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.REFRESH_IMG));
        addAction(refresh);

        final Action setStyleToWorkspaceImageAction = new SetStyleToWorkspaceImageAction();
        addAction(setStyleToWorkspaceImageAction);

        final HideDDiagramElementAction hideElementAction = new HideDDiagramElementAction(HIDE_ELEMENT);
        addAction(hideElementAction);

        final HideDDiagramElementLabelAction hideElementLabelAction = new HideDDiagramElementLabelAction(HIDE_LABEL);
        addAction(hideElementLabelAction);

        final RevealAllElementsAction revealElementsAction = new RevealAllElementsAction(REVEAL_ELEMENTS);
        addAction(revealElementsAction);

        final RetargetAction deleteFromDiagramAction = new DeleteFromDiagramAction(DiagramUIMessages.DiagramEditor_Delete_from_Diagram, DELETE_FROM_DIAGRAM, ActionIds.ACTION_DELETE_FROM_DIAGRAM,
                DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_DIAGRAM_ICON));
        addAction(deleteFromDiagramAction);

        final IAction deleteFromModelAction = new DeleteFromModelAction(DiagramUIMessages.DiagramEditor_Delete_from_Model, DELETE_FROM_MODEL, ActionIds.ACTION_DELETE_FROM_MODEL,
                DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_MODEL_ICON));
        addAction(deleteFromModelAction);

        final IAction copyLayoutAction = new CopyFormatAction(getPage());
        addAction(copyLayoutAction);

        addAction(TabbarRouterAction.createTreeRouterAction(getPage()));

        addAction(TabbarRouterAction.createObliqueRouterAction(getPage()));

        addAction(TabbarRouterAction.createRectilinearRouterAction(getPage()));

        final IAction pasteFormatAction = new PasteFormatAction(getPage());
        addAction(pasteFormatAction);

        final IAction pasteLayoutAction = new PasteLayoutAction(getPage());
        addAction(pasteLayoutAction);

        final IAction pasteStyleAction = new PasteStyleAction(getPage());
        addAction(pasteStyleAction);

        final IAction pasteImageAction = new PasteImageAction();
        addAction(pasteImageAction);

        final IAction selectHiddenElementsAction = new SelectHiddenElementsAction(getPage());
        addAction(selectHiddenElementsAction);
    }

    /**
     * We add a special tool to handle concern selection
     * 
     * @was-generated NOT
     */
    @Override
    public void contributeToToolBar(final IToolBarManager toolBarManager) {
        super.contributeToToolBar(toolBarManager);
        if (isOldUIEnabled()) {
            toolBarManager.add(getActionRegistry().getAction(REFRESH_DIAGRAM));
            toolBarManager.add(new Separator());
            toolBarManager.add(new ConcernComboContributionItem(getPage(), "")); //$NON-NLS-1$
            toolBarManager.add(getActionRegistry().getAction(LAUNCH_BEHAVIOR));
            toolBarManager.add(new Separator());
            toolBarManager.add(new SiriusActionBarActionContributionItem(getActionRegistry().getAction(SetStyleToWorkspaceImageAction.SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_ID), getPage()));
            toolBarManager.add(getActionRegistry().getAction(HIDE_ELEMENT));
            toolBarManager.add(getActionRegistry().getAction(HIDE_LABEL));
            toolBarManager.add(getActionRegistry().getAction(REVEAL_ELEMENTS));
            toolBarManager.add(getActionRegistry().getAction(ActionIds.ACTION_DELETE_FROM_MODEL));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.COPY_FORMAT));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.PASTE_FORMAT));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.SELECT_HIDDEN_ELEMENTS));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.TREE_ROUTING_STYLE));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.OBLIQUE_ROUTING_STYLE));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.RECTILINEAR_ROUTING_STYLE));
        }
    }

    @Override
    public void init(IActionBars bars) {
        disableActionBarUIUpdates();

        try {
            super.init(bars);

            IToolBarManager toolBarManager = bars.getToolBarManager();
            toolBarManager.remove(ActionIds.MENU_COMPARTMENT);
            toolBarManager.remove(ActionIds.ACTION_COMPARTMENT_ALL);
            toolBarManager.remove(ActionIds.ACTION_COMPARTMENT_NONE);
            toolBarManager.remove(ActionIds.ACTION_HIDE_CONNECTION_LABELS);
            toolBarManager.remove(ActionIds.ACTION_SHOW_CONNECTION_LABELS);
            toolBarManager.remove(ActionIds.ACTION_SHOW_COMPARTMENT_TITLE);

            if (!isOldUIEnabled()) {
                // The actions create for the default GMF toolbar are no longer
                // useful. They must be removed from the toolbarManager and
                // disposed. This avoids unnecessary notifications and
                // calculations on these actions.
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FONT_NAME);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FONT_COLOR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FONT_SIZE);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_FONT_ITALIC);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_FONT_BOLD);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FILL_COLOR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_LINE_COLOR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_MAKE_SAME_SIZE_BOTH);
                removeExistingItem(ActionIds.ACTION_AUTOSIZE, "/", bars.getToolBarManager(), false).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_AUTOSIZE, "/diagramMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_COPY_APPEARANCE_PROPERTIES, "/", bars.getToolBarManager(), false).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_COPY_APPEARANCE_PROPERTIES, "/diagramMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ACTION_SIRIUS_AUTOSIZE, "/", bars.getToolBarManager(), false) //$NON-NLS-1$
                        .ifPresent(contributionItem -> disposeIfPossible(contributionItem));
                removeExistingItem(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ACTION_SIRIUS_AUTOSIZE, "/diagramMenu", bars.getMenuManager(), true) //$NON-NLS-1$
                        .ifPresent(contributionItem -> disposeIfPossible(contributionItem));
                removeExistingItem(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ACTION_SIRIUS_COPY_APPEARANCE_PROPERTIES, "/", bars.getToolBarManager(), false) //$NON-NLS-1$
                        .ifPresent(contributionItem -> disposeIfPossible(contributionItem));
                removeExistingItem(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ACTION_SIRIUS_COPY_APPEARANCE_PROPERTIES, "/diagramMenu", bars.getMenuManager(), true) //$NON-NLS-1$
                        .ifPresent(contributionItem -> disposeIfPossible(contributionItem));
                removeExistingItem(ActionIds.ACTION_TOOLBAR_SELECT_ALL_SHAPES, "/selectMenu", bars.getToolBarManager(), false).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionFactory.SELECT_ALL.getId(), "/diagramMenu/selectMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_SELECT_ALL_SHAPES, "/diagramMenu/selectMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_TOOLBAR_SELECT_ALL_CONNECTIONS, "/selectMenu", bars.getToolBarManager(), false).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_SELECT_ALL_CONNECTIONS, "/diagramMenu/selectMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(ActionIds.ACTION_TOOLBAR_SELECT_ALL, "/selectMenu", bars.getToolBarManager(), false).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(GEFActionConstants.ALIGN_LEFT, "/diagramMenu/alignMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(GEFActionConstants.ALIGN_CENTER, "/diagramMenu/alignMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(GEFActionConstants.ALIGN_RIGHT, "/diagramMenu/alignMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(GEFActionConstants.ALIGN_TOP, "/diagramMenu/alignMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(GEFActionConstants.ALIGN_MIDDLE, "/diagramMenu/alignMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                removeExistingItem(GEFActionConstants.ALIGN_BOTTOM, "/diagramMenu/alignMenu", bars.getMenuManager(), true).ifPresent(contributionItem -> disposeIfPossible(contributionItem)); //$NON-NLS-1$
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_ZOOM);

                cleanOldToolBarGMFAction(toolBarManager, ActionIds.MENU_ARRANGE);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ARRANGE_ALL);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ARRANGE_SELECTION);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.MENU_ARRANGE_TOOLBAR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_TOOLBAR_ARRANGE_ALL);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_TOOLBAR_ARRANGE_SELECTION);

                cleanOldToolBarGMFAction(toolBarManager, ActionIds.MENU_SELECT);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_SELECT_ALL_CONNECTIONS);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_SELECT_ALL_SHAPES);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.MENU_SELECT_TOOLBAR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_TOOLBAR_SELECT_ALL_SHAPES);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_TOOLBAR_SELECT_ALL_CONNECTIONS);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_TOOLBAR_SELECT_ALL);

                cleanOldToolBarGMFAction(toolBarManager, ActionIds.MENU_ALIGN);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ALIGN_BOTTOM);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ALIGN_CENTER);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ALIGN_LEFT);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ALIGN_MIDDLE);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ALIGN_RIGHT);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ALIGN_TOP);

                cleanOldToolBarGMFAction(toolBarManager, ActionIds.MENU_ROUTER);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ROUTER_OBLIQUE);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ROUTER_RECTILINEAR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_ROUTER_TREE);
            } else {
                Bundle uiWorkbenchBundle = Platform.getBundle("org.eclipse.ui.workbench"); //$NON-NLS-1$
                Version junoStart = Version.parseVersion("3.103"); //$NON-NLS-1$
                // Version keplerStart = Version.parseVersion("3.105");

                if (uiWorkbenchBundle != null && uiWorkbenchBundle.getVersion().compareTo(junoStart) < 0) {
                    // Do not reorder old ui toolbar for 4.x.
                    IContributionItem arrange = toolBarManager.find(ActionIds.MENU_ARRANGE);
                    IContributionItem diagram = toolBarManager.find(REFRESH_DIAGRAM);
                    if (arrange != null && diagram != null) {
                        toolBarManager.remove(arrange);
                        toolBarManager.insertBefore(REFRESH_DIAGRAM, arrange);

                        IContributionItem select = toolBarManager.find(ActionIds.MENU_SELECT);
                        if (select != null) {
                            toolBarManager.remove(select);
                            toolBarManager.insertAfter(ActionIds.MENU_ARRANGE, select);

                            IContributionItem align = toolBarManager.find(ActionIds.MENU_ALIGN);
                            if (align != null) {
                                toolBarManager.remove(align);
                                toolBarManager.insertAfter(ActionIds.MENU_SELECT, align);
                                toolBarManager.insertAfter(ActionIds.MENU_ALIGN, new Separator());
                            }
                        }
                    }

                    IContributionItem setStyleItem = toolBarManager.find(SetStyleToWorkspaceImageAction.SET_STYLE_TO_WORKSPACE_IMAGE_ACTION_ID);
                    IContributionItem copyApparenceItem = toolBarManager.find(ActionIds.ACTION_COPY_APPEARANCE_PROPERTIES);
                    if (setStyleItem != null && copyApparenceItem != null) {
                        toolBarManager.remove(setStyleItem);
                        toolBarManager.insertBefore(ActionIds.ACTION_COPY_APPEARANCE_PROPERTIES, setStyleItem);
                    }

                    IContributionItem zoom = toolBarManager.find(ActionIds.CUSTOM_ZOOM);
                    IContributionItem launchBehavior = toolBarManager.find(LAUNCH_BEHAVIOR);
                    if (zoom != null && launchBehavior != null) {
                        toolBarManager.remove(zoom);
                        toolBarManager.insertAfter(LAUNCH_BEHAVIOR, zoom);
                    }
                }
            }
        } finally {
            reenableActionBarUIUpdates();
        }
    }

    /**
     * See {@link WorkbenchWindow#largeUpdateStart()}
     */
    private void disableActionBarUIUpdates() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window instanceof WorkbenchWindow) {
            ((WorkbenchWindow) window).largeUpdateStart();
        }
    }

    /**
     * See {@link WorkbenchWindow#largeUpdateEnd()}
     */
    private void reenableActionBarUIUpdates() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window instanceof WorkbenchWindow) {
            ((WorkbenchWindow) window).largeUpdateEnd();
        }

    }

    /**
     * Remove the contribution with the given id and dispose the corresponding action if this contribution is an
     * {@link ActionContributionItem}.
     * 
     * @param toolBarManager
     *            The {@link IToolBarManager} to clean.
     * @param actionId
     *            The id of the action to clean.
     */
    private void cleanOldToolBarGMFAction(IToolBarManager toolBarManager, String actionId) {
        IContributionItem contribution = toolBarManager.find(actionId);
        disposeIfPossible(contribution);
        toolBarManager.remove(contribution);
    }

    /**
     * Dispose the corresponding action if this contribution is an {@link ActionContributionItem}.
     * 
     * @param contribution
     *            The {@link IContributionItem} to clean.
     */
    private void disposeIfPossible(IContributionItem contribution) {
        if (contribution instanceof ActionContributionItem) {
            if (((ActionContributionItem) contribution).getAction() instanceof Disposable) {
                ((Disposable) ((ActionContributionItem) contribution).getAction()).dispose();
            }
        } else if (contribution instanceof SubContributionItem) {
            disposeIfPossible(((SubContributionItem) contribution).getInnerItem());
        }
    }

    /**
     * Copied from
     * {@link org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider#removeExistingItem(String, String, IContributionManager, boolean)}.
     * This copy returned the removed item (if removed). It was not the case in original method.
     * 
     * @param id
     * @param path
     * @param contributionManager
     */
    private Optional<IContributionItem> removeExistingItem(String id, String path, IContributionManager contributionManager, boolean useIdForRemoval) {

        // Find the menu or action or group.
        if (id == null)
            return Optional.empty();

        IContributionManager parent = contributionManager;
        if (path.length() > 1) { // if path is more than '/'
            parent = findMenuUsingPath(contributionManager, path.substring(1));
            if (parent == null) {
                Log.info(CommonUIServicesActionPlugin.getDefault(), CommonUIServicesActionStatusCodes.SERVICE_FAILURE, "The contribution item path is invalid"); //$NON-NLS-1$
                return Optional.empty();
            }
        }

        IContributionItem predefinedItem = parent.find(id);
        if (predefinedItem == null) {
            Log.info(CommonUIServicesActionPlugin.getDefault(), CommonUIServicesActionStatusCodes.SERVICE_FAILURE, "The contribution item path is invalid"); //$NON-NLS-1$
            return Optional.empty();
        }

        if (predefinedItem instanceof AbstractGroupMarker) {
            IContributionItem allItems[] = parent.getItems();
            int groupIndex;
            for (groupIndex = 0; groupIndex < allItems.length; groupIndex++) {
                IContributionItem item = allItems[groupIndex];
                if (item.equals(predefinedItem)) {
                    break;
                }
            }
            for (int j = groupIndex + 1; j < allItems.length; j++) {
                IContributionItem item = allItems[j];
                if (item instanceof AbstractGroupMarker) {
                    break;
                }
                parent.remove(item);
            }

        }
        // parent.remove(item) and parent.remove(item.getId()) yield different results in some cases
        // parent.remove(item) seems to be working for all cases except for removing a menu from menu bar (item defined
        // as partMenu)
        if (useIdForRemoval) {
            return Optional.of(parent.remove(predefinedItem.getId()));
        } else {
            return Optional.of(parent.remove(predefinedItem));
        }
    }

    /**
     * Finds a menu manager using a '/' separated path. Copied from
     * {@link org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.AbstractContributionItemProvider#findMenuUsingPath(IContributionManager, String)}
     * 
     * @param parent
     *            The starting contribution manager
     * @param path
     *            The '/' separated path
     * @return A menu manager described by the given path
     */
    private IMenuManager findMenuUsingPath(IContributionManager parent, String path) {

        IContributionItem item = null;
        String id = path;
        String rest = null;
        int separator = path.indexOf('/');
        if (separator != -1) {
            id = path.substring(0, separator);
            rest = path.substring(separator + 1);
        } else {
            item = parent.find(path);
            if (item instanceof IMenuManager)
                return (IMenuManager) item;
        }

        item = parent.find(id);
        if (item instanceof IMenuManager) {
            IMenuManager manager = (IMenuManager) item;
            return manager.findMenuUsingPath(rest);
        }
        return null;
    }

    private boolean isOldUIEnabled() {
        return Platform.getPreferencesService().getBoolean(DiagramUIPlugin.ID, SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false, null);
    }

    @Override
    public void dispose() {
        // In Sirius, we called the dispose in method
        // org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl.preClose() to be sure that the
        // actions are not "refreshed" after the async close of an editor. But when the editor is really closed, the
        // dispose would be called a second time (not necessary).
        if (!disposed) {
            disposed = true;
            super.dispose();
        }
    }
}
