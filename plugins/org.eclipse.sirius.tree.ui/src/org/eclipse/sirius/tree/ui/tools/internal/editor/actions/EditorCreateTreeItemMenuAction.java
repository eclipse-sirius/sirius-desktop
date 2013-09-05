/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;

/**
 * This implementation is used to create the structure viewer's
 * "Create Tree Item" action.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class EditorCreateTreeItemMenuAction extends Action implements IMenuCreator {
    /** The Id of this action. */
    public static final String ID = "CreateTreeItemMenu";

    /** Menu manager for this action. */
    private final MenuManager menuManager = new MenuManager();

    /** The last action launch from this menu. */
    private Action lastCreateTreeItemAction;

    /** List of the current available actions for this menu. */
    private final List<CreateToolItemAction> createTreeItemActionsForTree = new ArrayList<CreateToolItemAction>();

    // menu item selection listener: listens to selection events
    private final Listener menuItemListener = new Listener() {
        public void handleEvent(final Event event) {
            if (SWT.Selection == event.type && !event.widget.isDisposed()) {
                final ActionContributionItem item = (ActionContributionItem) event.widget.getData();
                setLastAction((CreateToolItemAction) item.getAction());
            }
        }
    };

    /**
     * This default constructor will instantiate an action given the
     * {@link #BUNDLE bundle} resources prefixed by &quot;action.save&quot;.
     */
    public EditorCreateTreeItemMenuAction() {
        super("Create root tree items", DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.CREATE_TREE_ITEM_IMG));
        setId(ID);
        setMenuCreator(this);
        setEnabled(false);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        if (lastCreateTreeItemAction != null && lastCreateTreeItemAction.isEnabled()) {
            lastCreateTreeItemAction.run();
        }
    }

    /**
     * This will add the given action to this action's menu.
     * 
     * @param action
     *            {@link Action} to add to this action's menu.
     */
    public void addActionToMenu(final Action action) {
        final ActionContributionItem contribution = new ActionContributionItem(action);
        menuManager.add(contribution);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuCreator#dispose()
     */
    public void dispose() {
        if (menuManager.getMenu() != null) {
            final MenuItem[] menuItems = menuManager.getMenu().getItems();
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getStyle() == SWT.SEPARATOR) {
                    continue;
                }
                menuItem.removeListener(SWT.Selection, menuItemListener);
            }
            menuManager.getMenu().dispose();
        }
        menuManager.dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
     */
    public Menu getMenu(final Control parent) {
        // Creates the menu if needed, or removes all elements except for the
        // save action
        if (menuManager.getMenu() == null) {
            menuManager.createContextMenu(parent);
            update();
        }

        final MenuItem[] menuItems = menuManager.getMenu().getItems();
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getStyle() == SWT.SEPARATOR) {
                continue;
            }
            menuItem.addListener(SWT.Selection, menuItemListener);
        }

        return menuManager.getMenu();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
     */
    public Menu getMenu(final Menu parent) {
        return null;
    }

    /**
     * The change is applied on the next getMenu.
     * 
     * @param createActionsForTable
     *            List of <{@link AbstractToolAction}
     */
    public void update(final List<AbstractToolAction> createActionsForTable) {
        getCreateTreeItemActionsForTree().clear();
        menuManager.removeAll();
        // Add all create line tool
        for (final AbstractToolAction toolAction : createActionsForTable) {
            if (toolAction instanceof CreateToolItemAction) {
                getCreateTreeItemActionsForTree().add((CreateToolItemAction) toolAction);
            }
        }
        update();
    }

    /**
     * The change is applied on the next getMenu.
     */
    protected void update() {
        setEnabled(!getCreateTreeItemActionsForTree().isEmpty());

        menuManager.removeAll();
        // Add all create line tool on the table
        for (final CreateToolItemAction createLineAction : getCreateTreeItemActionsForTree()) {
            if (createLineAction.canExecute()) {
                menuManager.add(createLineAction);
            }
        }
    }

    public void setLastAction(final CreateToolItemAction createLineAction) {
        lastCreateTreeItemAction = createLineAction;
    }

    /**
     * Return the available createTreeItem actions.
     * 
     * @return the createTreeItemActionsForTable
     */
    protected List<CreateToolItemAction> getCreateTreeItemActionsForTree() {
        return createTreeItemActionsForTree;
    }
}
