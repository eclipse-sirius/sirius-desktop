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
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ContextMenuFinder;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.ListResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * A specific version for popup menu in tab bar. use with care.
 * 
 * @author mchauvin
 */
public class SWTBotShellForTabbar extends SWTBotShell {

    /**
     * Construct a new instance of this object.
     * 
     * @param shell
     *            the widget
     * @throws WidgetNotFoundException
     *             if the widget is <code>null</code> or widget has been
     *             disposed.
     */
    public SWTBotShellForTabbar(Shell shell) throws WidgetNotFoundException {
        super(shell);
    }

    /**
     * Gets the context menu on the given control, matching the text.
     * 
     * @param control
     *            the control
     * @param text
     *            the text on the context menu.
     * @return the menu that has the given text.
     * @throws WidgetNotFoundException
     *             if the widget is not found.
     * @since 2.0
     */
    @Override
    // varargs and generics doesn't mix well!
    protected SWTBotMenu contextMenu(final Control control, final String text) throws WidgetNotFoundException {
        Matcher<MenuItem> withMnemonic = WidgetMatcherFactory.withMnemonic(text);
        final Matcher<MenuItem> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(MenuItem.class), withMnemonic);
        final ContextMenuFinder menuFinder = new ContextMenuFinder(control) {
            @Override
            public List<MenuItem> findMenus(final Matcher<MenuItem> matcher) {
                return UIThreadRunnable.syncExec(display, new ListResult<MenuItem>() {
                    @Override
                    public List<MenuItem> run() {
                        Menu[] menus = getMenus(widget);
                        for (int i = menus.length - 1; i >= 0; i--) {
                            if (menus[i] != null) {
                                for (MenuItem item : menus[i].getItems()) {
                                    if (matcher.matches(item)) {
                                        List<MenuItem> menuItems = new ArrayList<MenuItem>();
                                        menuItems.add(item);
                                        return menuItems;
                                    }
                                }
                            }
                        }
                        return Collections.emptyList();
                    }
                });
            }
        };

        new SWTBot().waitUntil(new DefaultCondition() {
            @Override
            public String getFailureMessage() {
                return "Could not find context menu with text: " + text; //$NON-NLS-1$
            }

            @Override
            public boolean test() throws Exception {
                return !menuFinder.findMenus(matcher).isEmpty();
            }
        });
        return new SWTBotMenu(menuFinder.findMenus(matcher).get(0), matcher);
    }

    private Menu[] getMenus(final Shell shell) {
        try {
            Field field = Decorations.class.getDeclaredField("menus"); //$NON-NLS-1$
            field.setAccessible(true);
            return (Menu[]) field.get(shell);
        } catch (final SecurityException e) {
            // Cannot happen here
        } catch (final IllegalArgumentException e) {
            // // Cannot happen here
        } catch (final IllegalAccessException e) {
            // // Cannot happen here
        } catch (final NoSuchFieldException e) {
            // Cannot happen here
        }
        return null;
    }
}
