/**
 * Copyright (c) 2011, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.swt.finder.results.Result;

/**
 * {@link Result} to get a {@link MenuItem} .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ContextualMenuItemGetter implements Result<MenuItem> {

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
    public ContextualMenuItemGetter(Control control, String[] path) {
        this.control = control;
        this.path = path;
    }

    /**
     * Overridden to get a {@link MenuItem}.
     * 
     * {@inheritDoc}
     */
    @Override
    public MenuItem run() {
        MenuItem result = null;

        Menu menu = control.getMenu();
        int i;
        for (i = 0; i < path.length; i++) {
            String menuItemText = path[i];
            result = getMenuItem(menu, menuItemText);
            if (result == null) {
                break;
            }
            menu = result.getMenu();
        }
        if (result != null && !result.getText().equals(path[path.length - 1])) {
            result = null;
        }
        return result;
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
