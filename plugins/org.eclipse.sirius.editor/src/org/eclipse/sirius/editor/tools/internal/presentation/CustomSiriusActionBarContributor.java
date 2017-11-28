/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.presentation;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.tools.api.menu.AbstractMenuBuilder;
import org.eclipse.sirius.editor.tools.api.menu.CompositeMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.MenuBuildersManager;
import org.eclipse.sirius.editor.tools.internal.menu.OthersMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.ConditionalStyleMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.CustomizationMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.EditToolsMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.ExtensionsMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.MenuToolsMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.NavigationToolsMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.OperationsMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.RepresentationCreationToolsMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.RepresentationMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.RepresentationTemplateMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.StyleMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.ValidationMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.VariablesMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.child.ViewpointAndPaletteMenuBuilder;
import org.eclipse.sirius.editor.tools.internal.menu.refactoring.RefactoringMenu;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

/**
 * Specific class to provide actions.
 * 
 * @author cbrun.
 * 
 */
public class CustomSiriusActionBarContributor extends EditingDomainActionBarContributor implements ISelectionChangedListener {
    private final class ShowTypeAction extends Action {
        private final String showText = SiriusEditorPlugin.INSTANCE.getString("_UI_ShowType_menu_item");

        private final String hideText = SiriusEditorPlugin.INSTANCE.getString("_UI_HideType_menu_item");

        @Override
        public void run() {
            CustomSiriusAdapterFactoryLabelProvider labelProvider = getCustomLabelProvider();
            if (labelProvider != null) {
                labelProvider.setShowTypes(!labelProvider.isShowTypes());
                setText(getText());
                refreshViewerAction.run();
            }
        }

        private CustomSiriusAdapterFactoryLabelProvider getCustomLabelProvider() {
            if (activeEditor instanceof IViewerProvider) {
                Viewer viewer = ((IViewerProvider) activeEditor).getViewer();
                if (viewer instanceof ContentViewer) {
                    IBaseLabelProvider labelProvider = ((ContentViewer) viewer).getLabelProvider();
                    if (labelProvider instanceof DecoratingStyledCellLabelProvider) {
                        labelProvider = ((DecoratingStyledCellLabelProvider) labelProvider).getStyledStringProvider();
                    }
                    if (labelProvider instanceof CustomSiriusAdapterFactoryLabelProvider) {
                        return (CustomSiriusAdapterFactoryLabelProvider) labelProvider;
                    }
                }
            }
            return null;
        }

        public String getText() {
            CustomSiriusAdapterFactoryLabelProvider labelProvider = getCustomLabelProvider();
            if (labelProvider != null) {
                return labelProvider.isShowTypes() ? hideText : showText;
            }
            return super.getText();
        }

        public boolean isEnabled() {
            return getCustomLabelProvider() != null;
        }
    }

    private static final String UI_ACTIONS = "ui-actions";

    /**
     * This keeps track of the active editor.
     */
    protected IEditorPart activeEditorPart;

    /**
     * This keeps track of the current selection provider.
     */
    protected ISelectionProvider selectionProvider;

    /**
     * This action opens the Properties view.
     */
    protected IAction showPropertiesViewAction = new Action(SiriusEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")) {
        @Override
        public void run() {
            try {
                getPage().showView("org.eclipse.ui.views.PropertySheet");
            } catch (final PartInitException exception) {
                SiriusEditorPlugin.INSTANCE.log(exception);
            }
        }
    };

    /**
     * This action refreshes the viewer of the current editor if the editor
     * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
     */
    protected IAction refreshViewerAction = new Action(SiriusEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")) {
        @Override
        public boolean isEnabled() {
            return activeEditorPart instanceof IViewerProvider;
        }

        @Override
        public void run() {
            if (activeEditorPart instanceof IViewerProvider) {
                final Viewer viewer = ((IViewerProvider) activeEditorPart).getViewer();
                if (viewer != null) {
                    viewer.refresh();
                }
            }
        }
    };

    /**
     * This action allow to display type on VSM or hide if types are displayed.
     */
    protected IAction showType = new ShowTypeAction();

    private final Collection<AbstractMenuBuilder> builders;

    private final OthersMenuBuilder other;

    private final RefactoringMenu refactoring;

    /**
     * This creates an instance of the contributor.
     */
    public CustomSiriusActionBarContributor() {
        super(ADDITIONS_LAST_STYLE);
        if (!Movida.isEnabled()) {
            loadResourceAction = new LoadResourceAction();
        }
        validateAction = new ValidateAction();
        controlAction = new ControlAction();

        // Init menu builders
        builders = new ArrayList<AbstractMenuBuilder>();
        initMenusBuilders();
        other = new OthersMenuBuilder(builders);
        refactoring = new RefactoringMenu();
    }

    private void initMenusBuilders() {
        Collection<AbstractMenuBuilder> allMenusBuilders = new ArrayList<>();
        allMenusBuilders.add(new EditToolsMenuBuilder());
        allMenusBuilders.add(new RepresentationCreationToolsMenuBuilder());
        allMenusBuilders.add(new MenuToolsMenuBuilder());
        allMenusBuilders.add(new RepresentationMenuBuilder());
        allMenusBuilders.add(new RepresentationTemplateMenuBuilder());
        allMenusBuilders.add(new NavigationToolsMenuBuilder());
        allMenusBuilders.add(new VariablesMenuBuilder());
        allMenusBuilders.add(new StyleMenuBuilder());
        allMenusBuilders.add(new CustomizationMenuBuilder());
        allMenusBuilders.add(new ConditionalStyleMenuBuilder());
        allMenusBuilders.add(new OperationsMenuBuilder());
        allMenusBuilders.add(new ValidationMenuBuilder());
        allMenusBuilders.add(new ExtensionsMenuBuilder());
        allMenusBuilders.add(new ViewpointAndPaletteMenuBuilder());

        // Add contributions
        allMenusBuilders.addAll(MenuBuildersManager.getInstance().getContributedMenuBuilders());

        // Avoid menu duplication
        Multimap<String, AbstractMenuBuilder> c = LinkedHashMultimap.create();
        for (AbstractMenuBuilder builder : allMenusBuilders) {
            c.put(builder.getLabel(), builder);
        }
        List<AbstractMenuBuilder> computedBuilders = new ArrayList<>();
        for (String label : c.keySet()) {
            if (c.get(label).size() > 1) {
                computedBuilders.add(new CompositeMenuBuilder(label, c.get(label)));
            } else {
                computedBuilders.add(c.get(label).iterator().next());
            }
        }

        Comparator<AbstractMenuBuilder> comparator = new Comparator<AbstractMenuBuilder>() {
            @Override
            public int compare(AbstractMenuBuilder builder1, AbstractMenuBuilder builder2) {
                int diff = builder1.getPriority() - builder2.getPriority();
                if (diff == 0) {
                    return Collator.getInstance().compare(builder1.getLabel(), builder2.getLabel());
                }
                return diff;
            }
        };
        Collections.sort(computedBuilders, comparator);
        builders.addAll(computedBuilders);
    }

    /**
     * This adds Separators for editor additions to the tool bar.
     * 
     * @param toolBarManager
     *            the toolbarmanager.
     */
    @Override
    public void contributeToToolBar(final IToolBarManager toolBarManager) {
        toolBarManager.add(new Separator("viewpoint-settings"));
        toolBarManager.add(new Separator("viewpoint-additions"));
    }

    /**
     * This adds to the menu bar a menu and some separators for editor
     * additions, as well as the sub-menus for object creation items.
     * 
     * @param menuManager
     *            the menu manager.
     */
    @Override
    public void contributeToMenu(final IMenuManager menuManager) {
        super.contributeToMenu(menuManager);

        final IMenuManager submenuManager = new MenuManager(SiriusEditorPlugin.INSTANCE.getString("_UI_SiriusEditor_menu"), "designerMenuID");
        menuManager.insertAfter("additions", submenuManager);
        submenuManager.add(new Separator("settings"));
        submenuManager.add(new Separator("actions"));
        submenuManager.add(new Separator("additions"));
        submenuManager.add(new Separator("additions-end"));

        // for (final AbstractMenuBuilder builder : builders) {
        // builder.attach(submenuManager);
        // }
        // other.attach(submenuManager);
        // refactoring.attach(submenuManager);

        // Force an update because Eclipse hides empty menus now.
        //
        submenuManager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(final IMenuManager menuManager) {
                menuManager.updateAll(true);
            }
        });

        addGlobalActions(submenuManager);
    }

    /**
     * When the active editor changes, this remembers the change and registers
     * with it as a selection provider.
     * 
     * @param part
     *            the current editor.
     */
    @Override
    public void setActiveEditor(final IEditorPart part) {
        super.setActiveEditor(part);
        activeEditorPart = part;

        // Switch to the new selection provider.
        //
        if (selectionProvider != null) {
            selectionProvider.removeSelectionChangedListener(this);
        }
        if (part == null) {
            selectionProvider = null;
        } else {
            selectionProvider = part.getSite().getSelectionProvider();
            selectionProvider.addSelectionChangedListener(this);

            // Fake a selection changed event to update the menus.
            //
            if (selectionProvider.getSelection() != null) {
                selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
            }
        }
    }

    /**
     * This implements
     * {@link org.eclipse.jface.viewers.ISelectionChangedListener}, handling
     * {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for
     * the children and siblings that can be added to the selected object and
     * updating the menus accordingly.
     * 
     * @param event
     *            event to consider.
     */
    public void selectionChanged(final SelectionChangedEvent event) {

        // Query the new selection for appropriate new child/sibling descriptors
        //

        final ISelection selection = event.getSelection();
        depopulateMenus();
        Collection<?> newChildDescriptors = null;

        if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
            final Object object = ((IStructuredSelection) selection).getFirstElement();

            final EditingDomain domain = ((IEditingDomainProvider) activeEditorPart).getEditingDomain();

            newChildDescriptors = domain.getNewChildDescriptors(object, null);
        }

        // Generate actions for selection; populate and redraw the menus.
        if (newChildDescriptors != null) {
            updateChildDescriptorMenus(selection, newChildDescriptors);
        }

        populateMenus();
    }

    private void depopulateMenus() {
        for (final AbstractMenuBuilder builder : builders) {
            builder.depopulateMenu();
        }
        other.depopulateMenu();
        refactoring.depopulateMenu();
    }

    private void updateChildDescriptorMenus(final ISelection selection, Collection<?> newChildDescriptors) {
        for (final AbstractMenuBuilder builder : builders) {
            builder.update(newChildDescriptors, selection, activeEditorPart);
        }
        other.update(newChildDescriptors, selection, activeEditorPart);
        refactoring.update(newChildDescriptors, selection, activeEditorPart);
    }

    /**
     * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction}
     * for each object in <code>descriptors</code>, and returns the collection
     * of these actions.
     * 
     * @param descriptors
     *            descriptors to generate actions from.
     * @param selection
     *            current selection.
     * @return the list of generated actions.
     */
    protected Collection<IAction> generateCreateChildActions(final Collection<?> descriptors, final ISelection selection) {
        final Collection<IAction> actions = new ArrayList<IAction>();
        if (descriptors != null) {
            for (final Iterator<?> i = descriptors.iterator(); i.hasNext(); /* */) {
                actions.add(new CreateChildAction(activeEditorPart, selection, i.next()));
            }
        }
        return actions;
    }

    /**
     * This generates a
     * {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each
     * object in <code>descriptors</code>, and returns the collection of these
     * actions.
     * 
     * @param descriptors
     *            descriptors to generate actions from.
     * @param selection
     *            current selection.
     * @return the list of generated actions.
     */
    protected Collection<IAction> generateCreateSiblingActions(final Collection<?> descriptors, final ISelection selection) {
        final Collection<IAction> actions = new ArrayList<IAction>();
        if (descriptors != null) {
            for (final Iterator<?> i = descriptors.iterator(); i.hasNext(); /* */) {
                actions.add(new CreateSiblingAction(activeEditorPart, selection, i.next()));
            }
        }
        return actions;
    }

    /**
     * This populates the pop-up menu before it appears.
     * 
     * @param menuManager
     *            the menu manager.
     */
    @Override
    public void menuAboutToShow(final IMenuManager menuManager) {
        super.menuAboutToShow(menuManager);

        insertInParentMenu(menuManager);

        menuManager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(final IMenuManager menuManager) {
                menuManager.updateAll(true);
            }
        });

    }

    private void insertInParentMenu(final IMenuManager menuManager) {
        for (final AbstractMenuBuilder builder : builders) {
            if (RefactoringMenu.REFACTORING_MENU_LABEL.equals(builder.getLabel())) {
                // Refactoring menu should be added after the edit group
                builder.insertAfterInContainer(menuManager);
            } else {
                builder.insertBeforeInContainer(menuManager);
            }

        }
        other.insertBeforeInContainer(menuManager);
        refactoring.insertAfterInContainer(menuManager);
    }

    private void populateMenus() {
        for (final AbstractMenuBuilder builder : builders) {
            builder.populateMenu();
        }
        other.populateMenu();
        refactoring.populateMenu();
    }

    /**
     * This inserts global actions before the "additions-end" separator.
     * 
     * @param menuManager
     *            the menu manager.
     */
    @Override
    protected void addGlobalActions(final IMenuManager menuManager) {
        menuManager.insertAfter("additions-end", new Separator(UI_ACTIONS));

        showType.setText(showType.getText());
        if (showType.isEnabled()) {
            showType.setEnabled(true);
            menuManager.insertAfter(UI_ACTIONS, showType);
        }
        menuManager.insertAfter(UI_ACTIONS, showPropertiesViewAction);

        refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
        menuManager.insertAfter(UI_ACTIONS, refreshViewerAction);

        super.addGlobalActions(menuManager);
    }

    /**
     * This ensures that a delete action will clean up all references to deleted
     * objects.
     * 
     * @return true if everything goes well.
     */
    @Override
    protected boolean removeAllReferencesOnDelete() {
        return true;
    }
}
