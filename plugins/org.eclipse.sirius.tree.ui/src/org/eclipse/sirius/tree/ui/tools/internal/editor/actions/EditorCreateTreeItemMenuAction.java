/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tree.ui.provider.Messages;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

/**
 * This implementation is used to create the structure viewer's
 * "Create Tree Item" action.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class EditorCreateTreeItemMenuAction extends Action implements IMenuCreator {
    /** The Id of this action. */
    public static final String ID = "CreateTreeItemMenu"; //$NON-NLS-1$

    /** Menu manager for this action. */
    private final MenuManager menuManager = new MenuManager();

    /** The last action launch from this menu. */
    private Action lastCreateTreeItemAction;

    /** List of the current available actions for this menu. */
    private final List<CreateToolItemAction> createTreeItemActionsForTree = new ArrayList<CreateToolItemAction>();

    // menu item selection listener: listens to selection events
    private final Listener menuItemListener = new Listener() {
        @Override
        public void handleEvent(final Event event) {
            if (SWT.Selection == event.type && !event.widget.isDisposed()) {
                final ActionContributionItem item = (ActionContributionItem) event.widget.getData();
                setLastAction((CreateToolItemAction) item.getAction());
            }
        }
    };

    private final MenuListener menuListener = new MenuAdapter() {
        @Override
        public void menuShown(MenuEvent e) {
            final MenuItem[] menuItems = menuManager.getMenu().getItems();
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getStyle() == SWT.SEPARATOR) {
                    continue;
                }
                menuItem.removeListener(SWT.Selection, menuItemListener);
                menuItem.addListener(SWT.Selection, menuItemListener);
            }
        }
    };

    /**
     * This default constructor will instantiate an action given the
     * {@link #BUNDLE bundle} resources prefixed by &quot;action.save&quot;.
     */
    public EditorCreateTreeItemMenuAction() {
        super(Messages.EditorCreateTreeItemMenuAction_name, DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.CREATE_TREE_ITEM_IMG));
        setId(ID);
        setMenuCreator(this);
        setEnabled(false);
    }

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

    @Override
    public void dispose() {
        if (menuManager.getMenu() != null) {
            final MenuItem[] menuItems = menuManager.getMenu().getItems();
            for (MenuItem menuItem : menuItems) {
                if (menuItem.getStyle() == SWT.SEPARATOR) {
                    continue;
                }
                menuItem.removeListener(SWT.Selection, menuItemListener);
            }
            menuManager.getMenu().removeMenuListener(menuListener);
            menuManager.getMenu().dispose();
        }
        menuManager.dispose();
    }

    @Override
    public Menu getMenu(final Control parent) {
        // Creates the menu if needed, or removes all elements except for the
        // save action
        if (menuManager.getMenu() == null) {
            menuManager.createContextMenu(parent);

            // at that time menuManager.getMenu().getItems() is empty. So we
            // have to wait when the menu is about to show to set the listener
            // on the menu item
            menuManager.getMenu().addMenuListener(menuListener);

            update();
        }

        return menuManager.getMenu();
    }

    @Override
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
        // Add all create line tool
        for (final AbstractToolAction toolAction : createActionsForTable) {
            if (toolAction instanceof CreateToolItemAction) {
                getCreateTreeItemActionsForTree().add((CreateToolItemAction) toolAction);
            }
        }

        // init last action
        if (!createActionsForTable.isEmpty()) {
            setLastAction((CreateToolItemAction) createActionsForTable.get(0));
        }

        update();
    }

    /**
     * The change is applied on the next getMenu.
     */
    protected void update() {
        menuManager.removeAll();
        setEnabled(!createTreeItemActionsForTree.isEmpty());

        if (createTreeItemActionsForTree.isEmpty()) {
            this.setText(Messages.EditorCreateTreeItemMenuAction_name);
        } else {
            // Add all create line tool on the table
            for (final CreateToolItemAction createAction : createTreeItemActionsForTree) {
                if (createAction.canExecute()) {
                    menuManager.add(createAction);
                }
            }
        }
    }

    /**
     * Set the last action and update the menu text.
     * 
     * @param createLineAction
     *            the action
     */
    public void setLastAction(final CreateToolItemAction createLineAction) {
        lastCreateTreeItemAction = createLineAction;
        this.setText(lastCreateTreeItemAction.getText());
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
