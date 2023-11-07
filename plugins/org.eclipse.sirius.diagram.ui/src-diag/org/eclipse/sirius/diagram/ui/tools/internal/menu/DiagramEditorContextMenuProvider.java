/*******************************************************************************
 * Copyright (c) 2009, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.ColorPropertyContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.ArrangeAllOnlyLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;

/**
 * A Custom menu provider to contribute our custom delete action.
 * 
 * @author mchauvin
 */
public class DiagramEditorContextMenuProvider extends DiagramContextMenuProvider {

    private static final String DELETE_FROM_GROUP = "deleteFromGroup"; //$NON-NLS-1$

    private static final String FILTER_FORMAT_GROUP = "filterFormatGroup"; //$NON-NLS-1$

    private static final String PLUGIN_MENU_MANAGER_CLASS_NAME = "PluginMenuManager"; //$NON-NLS-1$

    private static final String PIN_GROUP = "pinGroup"; //$NON-NLS-1$

    /** the workbench part */
    private IWorkbenchPart part;

    /** our custom delete action */
    private DeleteFromModelWithHookAction deleteAction;

    /**
     * Construct a new instance.
     * 
     * @param part
     *            The workbench part
     * @param viewer
     *            The edit part viewer
     */
    public DiagramEditorContextMenuProvider(final IWorkbenchPart part, final EditPartViewer viewer) {
        super(part, viewer);
        this.part = part;
        deleteAction = new DeleteFromModelWithHookAction(part);
        deleteAction.init();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.MenuManager#dispose()
     */
    @Override
    public void dispose() {
        if (deleteAction != null) {
            deleteAction.dispose();
            deleteAction = null;
        }
        super.dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
     */
    @Override
    public void buildContextMenu(final IMenuManager menu) {
        getViewer().flush();
        try {
            TransactionUtil.getEditingDomain((EObject) getViewer().getContents().getModel()).runExclusive(() -> {
                ContributionItemService.getInstance().contributeToPopupMenu(this, part);
                menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);
                updateSelectMenu(menu);
                updateFormatMenu(menu);
                final IMenuManager editMenu = menu.findMenuUsingPath(ActionIds.MENU_EDIT);
                IContributionItem find = editMenu.find(DELETE_FROM_GROUP);
                if (find != null) {
                    IContributionItem deleteFromDiagram = menu.find(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
                    if (deleteFromDiagram != null) {
                        menu.remove(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
                        editMenu.appendToGroup(DELETE_FROM_GROUP, deleteFromDiagram);
                    }
                    editMenu.appendToGroup(DELETE_FROM_GROUP, deleteAction);
                } else {
                    editMenu.add(deleteAction);
                }

                if (clipboardSupportOnlyOnNote() && hasViewOfDDiagramElementSelected()) {
                    editMenu.remove(GlobalActionId.CUT);
                    editMenu.remove(GlobalActionId.COPY);
                    editMenu.remove(GlobalActionId.PASTE);
                }

                // Add the arrangeBorderNodesAction just after the
                // arrangeAllAction (Arrange All action of GMF)
                // This is needed just in case of diagram selection.
                final IContributionItem arrangeBorderNodesItem = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ARRANGE_BORDER_NODES);
                if (arrangeBorderNodesItem != null) {
                    menu.remove(arrangeBorderNodesItem);
                    final IMenuManager arrangeMenu = menu.findMenuUsingPath(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.MENU_ARRANGE);
                    if (PLUGIN_MENU_MANAGER_CLASS_NAME.equals(arrangeMenu.getClass().getSimpleName())) { // $NON-NLS-1$
                        // We move the arrangeMenu only if it is contributed through plugin contribution. In case of
                        // VSM contribution, we ignore it.
                        arrangeMenu.insertAfter(ActionIds.ACTION_ARRANGE_ALL, arrangeBorderNodesItem);
                    }
                }

                final IContributionItem movePinnedElementsItem = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.MOVE_PINNED_ELEMENTS);
                if (movePinnedElementsItem != null) {
                    menu.remove(movePinnedElementsItem);
                    final IMenuManager arrangeMenu = menu.findMenuUsingPath(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.MENU_ARRANGE);
                    arrangeMenu.add(new Separator(PIN_GROUP));
                    arrangeMenu.appendToGroup(PIN_GROUP, movePinnedElementsItem);
                }

                // Move arrange menu for diagram element
                moveArrangeMenuForDiagramElements(menu);

                // move Show/Hide and Export diagram as image after refresh
                final IContributionItem selectHiddenElementsItem = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.SELECT_HIDDEN_ELEMENTS);
                if (selectHiddenElementsItem != null) {
                    menu.remove(selectHiddenElementsItem);
                    menu.insertAfter(FILTER_FORMAT_GROUP, selectHiddenElementsItem);
                }
                final IContributionItem copyToImageItem = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.COPY_TO_IMAGE);
                if (copyToImageItem != null) {
                    menu.remove(copyToImageItem);
                    menu.insertAfter(FILTER_FORMAT_GROUP, copyToImageItem);
                }
            });
        } catch (final InterruptedException e) {
            // do nothing
        }
    }

    /**
     * Get the value of the preference SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.
     * 
     * @return the value of the preference SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE
     */
    private boolean clipboardSupportOnlyOnNote() {
        return Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.name(), false, null);
    }

    /**
     * Check if there is views of {@link DDiagramElement} selected.
     * 
     * @return true if there is views of {@link DDiagramElement} selected, false else
     */
    private boolean hasViewOfDDiagramElementSelected() {
        boolean hasViewOfDDiagramElementSelected = false;
        List<?> selectedEditParts = getViewer().getSelectedEditParts();
        for (Object object : selectedEditParts) {
            if (object instanceof IGraphicalEditPart) {
                IGraphicalEditPart graphicalEditPart = (IGraphicalEditPart) object;
                EObject semanticElement = graphicalEditPart.resolveSemanticElement();
                if (semanticElement instanceof DDiagramElement) {
                    hasViewOfDDiagramElementSelected = true;
                    break;
                }
            }
        }
        return hasViewOfDDiagramElementSelected;
    }

    private void updateFormatMenu(IMenuManager menu) {
        final IMenuManager manager2 = menu.findMenuUsingPath(ActionIds.MENU_FORMAT);
        if (manager2 != null) {
            IContributionItem item = manager2.findUsingPath(ActionIds.CUSTOM_FILL_COLOR);
            if (item != null) {
                manager2.remove(ActionIds.CUSTOM_FILL_COLOR);
                manager2.remove(ActionIds.CUSTOM_FONT_COLOR);
                manager2.remove(ActionIds.CUSTOM_LINE_COLOR);

                manager2.insertAfter(ActionIds.ACTION_FONT_DIALOG, ColorPropertyContributionItem.createFillColorContributionItem(part.getSite().getPage()));
                ColorPropertyContributionItem fontColorContributionItem = ColorPropertyContributionItem.createFontColorContributionItem(part.getSite().getPage());
                manager2.insertAfter(ActionIds.CUSTOM_FILL_COLOR, fontColorContributionItem);
                manager2.insertAfter(ActionIds.CUSTOM_FONT_COLOR, ColorPropertyContributionItem.createLineColorContributionItem(part.getSite().getPage()));
            } else {
                item = manager2.findUsingPath(ActionIds.ACTION_FONT_DIALOG);
                if (item != null) {
                    manager2.insertAfter(ActionIds.ACTION_FONT_DIALOG, ColorPropertyContributionItem.createFontColorContributionItem(part.getSite().getPage()));
                }
            }
        }
    }

    /**
     * Remove default select GMF actions from contextual menu.
     * 
     * @param menu
     *            The root menu
     */
    private void updateSelectMenu(IMenuManager menu) {
        final IMenuManager manager2 = menu.findMenuUsingPath(ActionIds.MENU_SELECT);
        if (manager2 != null) {
            manager2.remove(ActionFactory.SELECT_ALL.getId());
            manager2.remove(ActionIds.ACTION_SELECT_ALL_SHAPES);
            manager2.remove(ActionIds.ACTION_SELECT_ALL_CONNECTIONS);
        }
    }

    /**
     * Rename the Arrange menu (new name=Layout menu), move it and reorganize it.
     * 
     * @param menu
     *            The parent menu containing "Format" menu
     */
    private void moveArrangeMenuForDiagramElements(IMenuManager menu) {
        final IMenuManager formatMenu = menu.findMenuUsingPath(ActionIds.MENU_FORMAT);
        if (formatMenu != null) {
            final IMenuManager arrangeMenu = formatMenu.findMenuUsingPath(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.MENU_ARRANGE);
            if (arrangeMenu != null) {
                // We check the enablement of the arrange actions according to
                // the current selected elements.
                updateArrangeMenuEnableActions(arrangeMenu, this.getViewer().getSelection());
                if (PLUGIN_MENU_MANAGER_CLASS_NAME.equals(arrangeMenu.getClass().getSimpleName())) {
                    // We move the arrangeMenu only if it is contributed through plugin contribution. In case of VSM
                    // contribution, we ignore it.
                    // Remove Layout menu from format menu
                    formatMenu.remove(arrangeMenu);
                    // and add it just after FILTER_FORMAT_GROUP
                    menu.insertAfter(FILTER_FORMAT_GROUP, arrangeMenu);
                }
            }
        }
    }

    /**
     * Disables the "Arrange Selection" Action (if contained) of the given arrangeMenu, if the given selection contains
     * element that forbid this action.
     * 
     * @param arrangeMenu
     *            the menu containing all the Arrange Action ("ArrangeAll", "Arrange Selection"...).
     * @param selection
     *            the current selected graphical elements
     */
    // Partial Layout on Diagrams with OrderedTreeLayout
    private void updateArrangeMenuEnableActions(IMenuManager arrangeMenu, ISelection selection) {

        // Step 1 : We get the diagram's edit part associated to the selected
        // elements
        IGraphicalEditPart graphicalElement = null;
        if (selection instanceof IStructuredSelection) {
            Iterator<Object> iterator = ((IStructuredSelection) selection).iterator();
            while (iterator.hasNext() && (graphicalElement == null)) {
                Object next = iterator.next();
                if (next instanceof IGraphicalEditPart) {
                    graphicalElement = (IGraphicalEditPart) next;
                }
            }
            if (graphicalElement != null) {
                RootEditPart root = graphicalElement.getRoot();
                if ((IGraphicalEditPart) root.getChildren().iterator().next() != null) {
                    IGraphicalEditPart diagramEditPart = (IGraphicalEditPart) root.getChildren().iterator().next();

                    // Step 2 : we get the LayoutNodeProvider associated to this
                    // diagramEditPart
                    AbstractLayoutEditPartProvider layoutNodeProvider = LayoutService.getProvider(diagramEditPart).getLayoutNodeProvider(diagramEditPart);
                    // Step 3 : we disable the action if the LayoutNodeProvider
                    // associated to this editPart implements
                    // ArrangeAllOnlyLayoutProvider
                    if (layoutNodeProvider instanceof ArrangeAllOnlyLayoutProvider) {
                        disableArrangeSelectionActionFromArrangeMenu(arrangeMenu);
                    }
                }
            }
        }

    }

    /**
     * Disables the "Arrange Selection" action from (if contained in) the given menu.
     * 
     * @param arrangeMenu
     *            the menu to remove the "Arrange Selection" action from
     */
    // Partial Layout on Diagrams with OrderedTreeLayout
    private void disableArrangeSelectionActionFromArrangeMenu(IMenuManager arrangeMenu) {
        for (int i = 0; i < arrangeMenu.getItems().length; i++) {
            if (arrangeMenu.getItems()[i] instanceof ActionContributionItem) {
                ActionContributionItem iContributionItem = (ActionContributionItem) arrangeMenu.getItems()[i];
                // FIXME find where this constant is defined
                if (ActionIds.ACTION_ARRANGE_SELECTION.equals(iContributionItem.getId())) {
                    iContributionItem.getAction().setEnabled(false);
                }
            }
        }
    }

}
