/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.results.Result;

/**
 * {@link Result} to check if a MenuItem is available or not.
 * 
 * @author lredor
 */
public class ContextualMenuItemAvailable implements Result<Boolean> {

    private final Control control;

    private final String[] path;

    /**
     * Default constructor.
     * 
     * @param control
     *            the {@link Control} of which to get the contextual
     *            {@link Menu}.
     * 
     * @param path
     *            the path of string corresponding to {@link MenuItem} from the
     *            {@link Menu} of the contextual menu to the searched
     *            {@link MenuItem}
     */
    public ContextualMenuItemAvailable(Control control, String[] path) {
        this.control = control;
        this.path = path;
    }

    /**
     * Overridden to get a {@link MenuItem}.
     * 
     * {@inheritDoc}
     */
    @Override
    public Boolean run() {
        MenuItem result = null;

        Menu menu = control.getMenu();
        int i;
        ArrayList<Menu> menusToHide = new ArrayList<Menu>();
        for (i = 0; i < path.length; i++) {
            String menuItemText = path[i];
            result = getMenuItem(menu, menuItemText);
            menusToHide.add(menu);
            if (result == null) {
                break;
            }
            menu = result.getMenu();
        }

        if (result != null && !result.getText().equals(path[path.length - 1])) {
            result = null;
        }
        // Hide all menu to avoid to have side effects with "visible" menu
        for (Menu menuToHide : menusToHide) {
            menuToHide.notifyListeners(SWT.Hide, new Event());
        }

        return result != null;
    }

    private MenuItem getMenuItem(Menu menu, String menuItemText) {
        MenuItem result = null;
        menu.notifyListeners(SWT.Show, new Event());
        MenuItem[] items = menu.getItems();
        for (MenuItem menuItem : items) {
            if (menuItem.getText().equals(menuItemText)) {
                result = menuItem;
                break;
            }
        }
        return result;
    }

}
