/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.Disposable;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
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
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.CopyLayoutAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.PasteLayoutAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh.RefreshDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.RevealAllElementsAction;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.ui.internal.WorkbenchWindow;
import org.osgi.framework.Bundle;
import org.osgi.framework.Version;

/**
 * @was-generated
 */
public class SiriusDiagramActionBarContributor extends DiagramActionBarContributor {

    /**
     * @was-generated
     */
    protected Class<?> getEditorClass() {
        return SiriusDiagramEditor.class;
    }

    /**
     * Action id for the "Launch behavior"
     */
    public static final String LAUNCH_BEHAVIOR = "Launch behavior";

    /**
     * Action id for the "Refresh DDiagram"
     */
    public static final String REFRESH_DIAGRAM = "Refresh diagram";

    /**
     * Action id for the "Hide element"
     */
    public static final String HIDE_ELEMENT = "Hide element";

    /**
     * Action id for the "Hide label"
     */
    public static final String HIDE_LABEL = "Hide label";

    /**
     * Action id for the "Reveal elements"
     */
    public static final String REVEAL_ELEMENTS = "Reveal elements";

    /**
     * Action tooltip text for the "Delete from Diagram"
     */
    public static final String DELETE_FROM_DIAGRAM = "Delete from Diagram";

    /**
     * Action tooltip text for the "Delete from Diagram"
     */
    public static final String DELETE_FROM_MODEL = "Delete from Model";

    /**
     * @was-generated
     */
    protected String getEditorId() {
        return DDiagramEditor.EDITOR_ID;
    }

    /**
     * {@inheritDoc}
     * 
     * @was-generated NOT
     * @see org.eclipse.gmf.runtime.diagram.ui.parts.DiagramActionBarContributor#buildActions()
     */
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

        final IAction copyLayoutAction = new CopyLayoutAction(getPage());
        addAction(copyLayoutAction);

        final IAction routerAction = TabbarRouterAction.createTreeRouterAction(getPage());
        addAction(routerAction);

        final IAction pasteLayoutAction = new PasteLayoutAction(getPage());
        addAction(pasteLayoutAction);

        final IAction selectHiddenElementsAction = new SelectHiddenElementsAction(getPage());
        addAction(selectHiddenElementsAction);
    }

    /**
     * We add a special tool to handle concern selection
     * 
     * @was-generated NOT
     */
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
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.COPY_LAYOUT));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.PASTE_LAYOUT));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.SELECT_HIDDEN_ELEMENTS));
            toolBarManager.add(getActionRegistry().getAction(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ROUTING_STYLE));
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
                // calculations
                // on these actions.
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FONT_NAME);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FONT_COLOR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FONT_SIZE);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_FONT_ITALIC);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_FONT_BOLD);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_FILL_COLOR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.CUSTOM_LINE_COLOR);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_MAKE_SAME_SIZE_BOTH);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_AUTOSIZE);
                cleanOldToolBarGMFAction(toolBarManager, ActionIds.ACTION_COPY_APPEARANCE_PROPERTIES);
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
     * Remove the contribution with the given id and dispose the corresponding
     * action if this contribution is an {@link ActionContributionItem}.
     * 
     * @param toolBarManager
     *            The {@link IToolBarManager} to clean.
     * @param actionId
     *            The id of the action to clean.
     */
    private void cleanOldToolBarGMFAction(IToolBarManager toolBarManager, String actionId) {
        IContributionItem contribution = toolBarManager.find(actionId);
        if (contribution instanceof ActionContributionItem) {
            if (((ActionContributionItem) contribution).getAction() instanceof Disposable) {
                ((Disposable) ((ActionContributionItem) contribution).getAction()).dispose();
            }
        }
        toolBarManager.remove(contribution);
    }

    private boolean isOldUIEnabled() {
        return Platform.getPreferencesService().getBoolean(DiagramUIPlugin.ID, SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false, null);
    }
}
