/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.utils.menu;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.finders.ContextMenuFinder;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.hamcrest.Matcher;

/**
 * A context menu finder, which stops to search when he found one result.
 * 
 * @author mchauvin
 */
public class FirstContextMenuFinder extends ContextMenuFinder {

    /**
     * Constructs the context menu finder for the given control to be searched.
     * 
     * @param control
     *            the control that has a context menu.
     */
    public FirstContextMenuFinder(Control control) {
        super(control);
    }

    /**
     * Finds all the menus using the given matcher in the set of shells
     * provided. If recursive is set, it will attempt to find the controls
     * recursively in each of the menus it that is found.
     * 
     * @param shells
     *            the shells to probe for menus.
     * @param matcher
     *            the matcher that can match menus and menu items.
     * @param recursive
     *            if set to true, will find sub-menus as well.
     * @return all menus in the specified shells that match the matcher.
     */
    @Override
    public List<MenuItem> findMenus(Shell[] shells, Matcher<MenuItem> matcher, boolean recursive) {
        LinkedHashSet<MenuItem> result = new LinkedHashSet<MenuItem>();
        for (Shell shell : shells) {
            result.addAll(findMenus(shell, matcher, recursive));
            if (!result.isEmpty()) {
                break;
            }
        }
        return new ArrayList<MenuItem>(result);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swtbot.swt.finder.finders.MenuFinder#findMenus(org.eclipse.swt.widgets.Menu,
     *      org.hamcrest.Matcher, boolean)
     */
    @Override
    public List<MenuItem> findMenus(final Menu bar, final Matcher<MenuItem> matcher, final boolean recursive) {
        return UIThreadRunnable.syncExec(display, new ListResult<MenuItem>() {
            @Override
            public List<MenuItem> run() {
                return findMenuInternal(bar, matcher, recursive);
            }
        });
    }

    private List<MenuItem> findMenuInternal(final Menu bar, final Matcher<MenuItem> matcher, final boolean recursive) {
        LinkedHashSet<MenuItem> result = new LinkedHashSet<MenuItem>();
        if (bar != null) {
            bar.notifyListeners(SWT.Show, new Event());
            MenuItem[] items = bar.getItems();
            for (MenuItem menuItem : items) {
                if (isSeparator(menuItem)) {
                    continue;
                }
                if (matcher.matches(menuItem)) {
                    result.add(menuItem);
                    /* we found one, do not continue */
                    break;
                }
                if (recursive) {
                    result.addAll(findMenuInternal(menuItem.getMenu(), matcher, recursive));
                    if (!result.isEmpty()) {
                        break;
                    }
                }

            }
            bar.notifyListeners(SWT.Hide, new Event());
        }
        return new ArrayList<MenuItem>(result);
    }

    private boolean isSeparator(MenuItem menuItem) {
        return (menuItem.getStyle() & SWT.SEPARATOR) != 0;
    }

}
