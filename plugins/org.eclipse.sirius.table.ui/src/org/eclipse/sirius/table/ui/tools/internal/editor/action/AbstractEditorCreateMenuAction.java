/*******************************************************************************
 * Copyright (c) 2008, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
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
 * This implementation is used to create the structure viewer's "Create Line"
 * action.
 * 
 * @param <T>
 *            the {@link AbstractToolAction} type to create.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public abstract class AbstractEditorCreateMenuAction<T extends AbstractToolAction<?>> extends Action implements IMenuCreator {

    /** Menu manager for this action. */
    private final MenuManager menuManager = new MenuManager();

    /** The last action launch from this menu. */
    private T lastCreateAction;

    /**
     * The default name of the action menu.
     */
    private final String defaultName;

    private final List<T> createActionsForTable = new ArrayList<>();

    // menu item selection listener: listens to selection events
    private final Listener menuItemListener = new Listener() {
        @Override
        public void handleEvent(final Event event) {
            if (SWT.Selection == event.type && !event.widget.isDisposed()) {
                final ActionContributionItem item = (ActionContributionItem) event.widget.getData();
                setLastAction((T) item.getAction());
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
     * 
     * * Creates a new action with the given text and image. Calls the zero-arg
     * constructor, then <code>setText</code> and
     * <code>setImageDescriptor</code>.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @see #setText
     * @see #setImageDescriptor
     */
    protected AbstractEditorCreateMenuAction(String text, ImageDescriptor image) {
        super(text, image);
        defaultName = text;
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
        if (lastCreateAction != null && lastCreateAction.isEnabled()) {
            lastCreateAction.run();
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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
     */
    @Override
    public Menu getMenu(final Control parent) {
        // Creates the menu if needed, or removes all elements except for the
        // save action
        if (menuManager.getMenu() == null) {
            menuManager.createContextMenu(parent);

            // at that time menuManager.getMenu().getItems() is empty. So we
            // have to wait when the menu is about to show to set the listener
            // on the menu items
            menuManager.getMenu().addMenuListener(menuListener);

            update();
        }

        return menuManager.getMenu();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
     */
    @Override
    public Menu getMenu(final Menu parent) {
        return null;
    }

    /**
     * The change is applied on the next getMenu.
     * 
     * @param actions
     *            List of <{@link AbstractToolAction}
     */
    public void update(final List<AbstractToolAction<?>> actions) {
        getCreateActionsForTable().clear();
        // Add all create line tool
        for (final T toolAction : filter(actions)) {
            getCreateActionsForTable().add(toolAction);
        }

        // init last action
        if (!createActionsForTable.isEmpty()) {
            setLastAction(createActionsForTable.get(0));
        }
        update();
    }

    /**
     * Filter the actions.
     * 
     * @param createActionsForTable
     *            all the candidate actions.
     * @return the sub-set of actions to use.
     */
    protected abstract List<T> filter(List<AbstractToolAction<?>> createActionsForTable);

    /**
     * The change is applied on the next getMenu.
     */
    protected void update() {
        menuManager.removeAll();
        setEnabled(!createActionsForTable.isEmpty());

        if (createActionsForTable.isEmpty()) {
            this.setText(defaultName);
        } else {
            // Add all create line tool on the table
            for (final T createAction : createActionsForTable) {
                if (createAction.canExecute()) {
                    menuManager.add(createAction);
                }
            }
        }
    }

    /**
     * Set the last action and update the menu text.
     * 
     * @param createAction
     *            the action
     */
    public void setLastAction(final T createAction) {
        lastCreateAction = createAction;
        this.setText(lastCreateAction.getText());
    }

    /**
     * Return the available createLine actions.
     * 
     * @return the createLineActionsForTable
     */
    protected List<T> getCreateActionsForTable() {
        return createActionsForTable;
    }
}
