/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.utils;

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
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.hamcrest.Matcher;
import org.junit.Assert;

/**
 * This Class overrides ContextMenuFinder to be able to check a context menu
 * enablement as soon as it is found in order to avoid a "Widget is disposed"
 * exception.
 * 
 * @author <a href="mailto:steve.monnier@obeo.fr">Steve Monnier</a>
 */
public class SiriusContextMenuFinder extends ContextMenuFinder {

    /**
     * Constructs the context menu finder for the given control to be searched.
     * 
     * @param control
     *            the control that has a context menu.
     */
    public SiriusContextMenuFinder(Control control) {
        super(control);
    }

    /**
     * Finds a menu matching the given item in all available shells and return
     * its enablement status. If recursive is set, it will attempt to find the
     * controls recursively in each of the menus it that is found.
     * 
     * @param matcher
     *            the matcher that can match menus and menu items.
     * @return the enablement status if one menu in all shells matches the
     *         matcher.
     */
    public boolean findMenuEnablement(Matcher<MenuItem> matcher) {
        return findMenuEnablement(getShells(), matcher, true);
    }

    /**
     * Finds all the menus using the given matcher in the set of shells provided
     * and return its enablement status. If recursive is set, it will attempt to
     * find the controls recursively in each of the menus it that is found.
     * 
     * @param shells
     *            the shells to probe for menus.
     * @param matcher
     *            the matcher that can match menus and menu items.
     * @param recursive
     *            if set to true, will find sub-menus as well.
     * @return the enablement status if one menu in the specified shells matches
     *         the matcher.
     */
    public boolean findMenuEnablement(Shell[] shells, Matcher<MenuItem> matcher, boolean recursive) {
        boolean result = false;
        for (Shell shell : shells) {
            result = result || findMenuEnablement(menuBar(shell), matcher, recursive);
        }
        return result;
    }

    /**
     * Finds all the menus in the given menu bar matching the given matcher. If
     * recursive is set, it will attempt to find the controls recursively in
     * each of the menus it that is found.
     * 
     * @param bar
     *            the menu bar
     * @param matcher
     *            the matcher that can match menus and menu items.
     * @param recursive
     *            if set to true, will find sub-menus as well.
     * @return all menus in the specified menubar that match the matcher.
     */
    public boolean findMenuEnablement(final Menu bar, final Matcher<MenuItem> matcher, final boolean recursive) {
        return UIThreadRunnable.syncExec(display, new BoolResult() {

            @Override
            public Boolean run() {
                List<MenuItem> menus = findMenusInternal(bar, matcher, recursive);
                Assert.assertEquals("Only one widget was expected to be found", 1, menus.size());
                return menus.get(0).getEnabled();
            }
        });
    }

    /**
     * This method if just a copy from org.eclipse.swtbot.swt.finder.finders.MenuFinder.
     */
    private List<MenuItem> findMenusInternal(final Menu bar, final Matcher<MenuItem> matcher, final boolean recursive) {
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
                }
                if (recursive) {
                    result.addAll(findMenusInternal(menuItem.getMenu(), matcher, recursive));
                }
            }
            bar.notifyListeners(SWT.Hide, new Event());
        }
        return new ArrayList<MenuItem>(result);
    }

    /**
     * This method if just a copy from org.eclipse.swtbot.swt.finder.finders.MenuFinder
     */
    private boolean isSeparator(MenuItem menuItem) {
        // FIXME see https://bugs.eclipse.org/bugs/show_bug.cgi?id=208188
        // FIXED > 20071101
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=208188#c2
        return (menuItem.getStyle() & SWT.SEPARATOR) != 0;
    }
}
