/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.common.ui.action.ActionMenuManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.ColorPropertyContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.ArrangeAllOnlyLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.provider.LayoutService;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A Custom menu provider to contribute our custom delete action.
 * 
 * @author mchauvin
 */
public class DiagramEditorContextMenuProvider extends DiagramContextMenuProvider {

    private static final String DELETE_FROM_GROUP = "deleteFromGroup"; //$NON-NLS-1$

    private static final String PIN_GROUP = "pinGroup"; //$NON-NLS-1$

    private static final String FILTER_FORMAT_GROUP = "filterFormatGroup"; //$NON-NLS-1$

    private static final String ARRANGE_MENU_ERROR = "Arrange menu is not renamed in Layout";

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
        // CHECKSTYLE:OFF
        try {
            TransactionUtil.getEditingDomain((EObject) getViewer().getContents().getModel()).runExclusive(new Runnable() {

                public void run() {
                    ContributionItemService.getInstance().contributeToPopupMenu(DiagramEditorContextMenuProvider.this, part);
                    menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);
                    updateFormatMenu(menu);
                    final IMenuManager manager = menu.findMenuUsingPath(ActionIds.MENU_EDIT);
                    IContributionItem find = manager.find(DELETE_FROM_GROUP);
                    if (find != null) {
                        IContributionItem deleteFromDiagram = menu.find(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
                        if (deleteFromDiagram != null) {
                            menu.remove(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
                            manager.appendToGroup(DELETE_FROM_GROUP, deleteFromDiagram);
                        }
                        manager.appendToGroup(DELETE_FROM_GROUP, deleteAction);
                    } else {
                        manager.add(deleteAction);
                    }

                    if (clipboardSupportOnlyOnNote() && hasViewOfDDiagramElementSelected()) {
                        manager.remove(GlobalActionId.CUT);
                        manager.remove(GlobalActionId.COPY);
                        manager.remove(GlobalActionId.PASTE);
                    }

                    // Add the arrangeBorderedNodesActionToolBar just after the
                    // toolbarArrangeAllAction (Arrange All action of GMF)
                    // This is needed just in case of diagram selection.
                    final IContributionItem item1 = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.ARRANGE_BORDERED_NODES);
                    if (item1 != null) {
                        menu.remove(item1);
                        final IMenuManager arrangeMenu = menu.findMenuUsingPath(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.MENU_ARRANGE);
                        updateArrangeMenuName(arrangeMenu);
                        arrangeMenu.insertAfter(ActionIds.ACTION_TOOLBAR_ARRANGE_ALL, item1);
                    }

                    // Move arrange menu for diagram element
                    moveArrangeMenuForDiagramElements(menu);

                    // move Show/Hide and Export digram as image after refresh
                    final IContributionItem item3 = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.SELECT_HIDDEN_ELEMENTS);
                    if (item3 != null) {
                        menu.remove(item3);
                        menu.insertAfter(FILTER_FORMAT_GROUP, item3);
                    }
                    final IContributionItem item2 = menu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.COPY_TO_IMAGE);
                    if (item2 != null) {
                        menu.remove(item2);
                        menu.insertAfter(FILTER_FORMAT_GROUP, item2);
                    }
                }

            });
        } catch (final InterruptedException e) {
            // do nothing
        }
    }

    /**
     * Get the value of the preference
     * SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.
     * 
     * @return the value of the preference
     *         SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE
     */
    private boolean clipboardSupportOnlyOnNote() {
        return Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_CLIPBOOARD_SUPPORT_ONLY_ON_NOTE.name(), false, null);
    }

    /**
     * Check if there is views of {@link DDiagramElement} selected.
     * 
     * @return true if there is views of {@link DDiagramElement} selected, false
     *         else
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
            }
        }
    }

    // CHECKSTYLE:ON
    private void moveArrangeMenuForDiagramElements(IMenuManager menu) {
        final IMenuManager formatMenu = menu.findMenuUsingPath(ActionIds.MENU_FORMAT);
        if (formatMenu != null) {
            final IMenuManager arrangeMenu = formatMenu.findMenuUsingPath(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.MENU_ARRANGE);
            if (arrangeMenu != null) {
                // We check the enablement of the arrange actions according to
                // the current selected elements.
                updateArrangeMenuEnableActions(arrangeMenu, this.getViewer().getSelection());
                updateArrangeMenuName(arrangeMenu);
                IContributionItem pinSeparator = formatMenu.find(PIN_GROUP);
                if (pinSeparator != null) {
                    formatMenu.remove(pinSeparator);
                    arrangeMenu.add(pinSeparator);
                }
                final IContributionItem pinItem = formatMenu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.PIN_ELEMENTS);
                if (pinItem != null) {
                    formatMenu.remove(pinItem);
                    arrangeMenu.add(pinItem);
                }
                final IContributionItem unpinItem = formatMenu.find(org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds.UNPIN_ELEMENTS);
                if (unpinItem != null) {
                    formatMenu.remove(unpinItem);
                    arrangeMenu.add(unpinItem);
                }
                formatMenu.remove(arrangeMenu);
                menu.insertAfter(FILTER_FORMAT_GROUP, arrangeMenu);
            }
        }
    }

    /**
     * Disables the "Arrange Selection" Action (if contained) of the given
     * arrangeMenu, if the given selection contains element that forbid this
     * action.
     * 
     * @param arrangeMenu
     *            the menu containing all the Arrange Action ("ArrangeAll",
     *            "Arrange Selection"...).
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
     * Disables the "Arrange Selection" action from (if contained in) the given
     * menu.
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

    /**
     * Change the name of the menu Arrange -> Layout
     * 
     * @param arrangeMenu
     *            arrange all menu
     */
    private void updateArrangeMenuName(final IMenuManager arrangeMenu) {
        try {
            Class<? extends IMenuManager> arrangeMenuClass = arrangeMenu.getClass();
            Field field = arrangeMenuClass.getDeclaredField("realMenuManager"); //$NON-NLS-1$
            field.setAccessible(true);
            Object realMenuManager = field.get(arrangeMenu);
            if (realMenuManager instanceof ActionMenuManager) {
                ((ActionMenuManager) realMenuManager).getDefaultAction().setText("Layout");
            }

        } catch (SecurityException e) {
            SiriusPlugin.getDefault().error(ARRANGE_MENU_ERROR, e);
        } catch (NoSuchFieldException e) {
            SiriusPlugin.getDefault().error(ARRANGE_MENU_ERROR, e);
        } catch (IllegalArgumentException e) {
            SiriusPlugin.getDefault().error(ARRANGE_MENU_ERROR, e);
        } catch (IllegalAccessException e) {
            SiriusPlugin.getDefault().error(ARRANGE_MENU_ERROR, e);
        }
    }

}
