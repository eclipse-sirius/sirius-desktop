/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.utils;

import java.util.concurrent.TimeUnit;

import org.eclipse.sirius.tests.swtbot.support.utils.menu.SWTBotContextMenu;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * Some utilities.
 * 
 * @author smonnier
 */
public final class SWTBotUtils {

    /**
     * 60 seconds of timeout (long timeout but big session can be long to open).
     */
    public static final long CLOSE_PROGRESS_MONITOR_TIMEOUT = TimeUnit.SECONDS.toSeconds(60);

    /**
     * SWTWorkbenchBot
     */
    private static SWTWorkbenchBot bot = new SWTWorkbenchBot();

    /**
     * Avoid instantiation.
     */
    private SWTBotUtils() {

    }

    /**
     * Type text as direct edition.
     * 
     * @param widget
     *            the current widget
     * @param text
     *            the text to type
     */
    public static void directEditWithKeyboard(final Widget widget, final String text) {
        final char[] charArray = text.toCharArray();
        if (charArray.length > 0) {
            for (char element : charArray) {
                SWTBotUtils.pressKeyboardKey(widget, element);
            }
        }
    }

    /**
     * Press a key.
     * 
     * @param widget
     *            the current widget
     * @param key
     *            the key to press
     */
    public static void pressKeyboardKey(final Widget widget, final int key) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                Event event = SWTBotUtils.keyEvent(SWT.NONE, (char) key, key, widget);
                widget.notifyListeners(SWT.KeyDown, event);
                widget.notifyListeners(SWT.KeyUp, event);
            }
        });
    }

    /**
     * Press the enter key.
     * 
     * @param widget
     *            the current widget
     */
    public static void pressEnterKey(final Widget widget) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                widget.notifyListeners(SWT.Traverse, SWTBotUtils.keyEvent(SWT.NONE, SWT.CR, SWT.Selection, widget));
            }
        });
    }

    /**
     * @param c
     *            the character.
     * @param modificationKey
     *            the modification key.
     * @param keyCode
     *            the keycode.
     * @return a key event with the specified keys.
     * @see Event#keyCode
     * @see Event#character
     * @see Event#stateMask
     * @since 1.2
     */
    private static Event keyEvent(final int modificationKey, final char c, final int keyCode, final Widget widget) {
        final Event keyEvent = SWTBotUtils.createEvent(widget);
        keyEvent.stateMask = modificationKey;
        keyEvent.character = c;
        keyEvent.keyCode = keyCode;

        return keyEvent;
    }

    private static Event createEvent(final Widget widget) {
        final Event event = new Event();
        event.time = (int) System.currentTimeMillis();
        event.widget = widget;
        event.display = SWTBotUtils.bot.getDisplay();
        return event;
    }

    /**
     * WARNING this class should move in SWTBot one day. WARNING if the text is
     * not found it will not failed this method to get disposed elements with
     * the current click on context menu SWTBot method
     * 
     * @param treeItem
     *            the current item
     * @param text
     *            the menu text
     */
    public static void clickContextMenu(final SWTBotTreeItem treeItem, final String text) {
        UIThreadRunnable.asyncExec(new VoidResult() {

            @Override
            public void run() {
                final SWTBotContextMenu menu = new SWTBotContextMenu(treeItem);
                menu.click(text);
            }
        });
    }

    /**
     * WARNING this class should move in SWTBot one day. WARNING if the text is
     * not found it will not failed this method to get disposed elements with
     * the current click on context menu SWTBot method
     * 
     * @param treeItem
     *            the current item
     * @param text
     *            the menu text
     * @return if treeItem has a context menu action named as the text parameter
     */
    public static boolean hasContextMenu(final SWTBotTreeItem treeItem, final String text) {

        return UIThreadRunnable.syncExec(treeItem.display, new BoolResult() {

            @Override
            public Boolean run() {
                SWTBotContextMenu swtBotContextMenu = new SWTBotContextMenu(treeItem);
                return swtBotContextMenu.exist(text);
            }
        });
    }

    /**
     * WARNING this class should move in SWTBot one day. WARNING if the text is
     * not found it will not failed this method to get disposed elements with
     * the current click on context menu SWTBot method
     * 
     * @param treeItem
     *            the current item
     * @param text
     *            the menu text
     * @return if the context menu named as text is enabled
     */
    public static boolean isContextMenuEnabled(final SWTBotTreeItem treeItem, final String text) {
        Matcher<MenuItem> withMnemonic = WidgetMatcherFactory.withMnemonic(text);
        final Matcher<MenuItem> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(MenuItem.class), withMnemonic);

        return UIThreadRunnable.syncExec(new BoolResult() {

            @Override
            public Boolean run() {
                return new SiriusContextMenuFinder(treeItem.widget.getParent()).findMenuEnablement(matcher);
            }
        });
    }

    /**
     * WARNING this class should move in SWTBot one day. WARNING if the text is
     * not found it will not failed this method to get disposed elements with
     * the current click on context menu SWTBot method
     * 
     * @param tree
     *            the tree
     * @param text
     *            the menu text
     */
    public static void clickContextMenu(final SWTBotTree tree, final String text) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                final SWTBotContextMenu menu = new SWTBotContextMenu(tree);
                menu.click(text);
            }
        });
    }

    /**
     * Workaround method to ensure that previous call was processed fully.
     */
    public static void waitAllUiEvents() {
        // use another sync exec to ensure that previous call was processed
        // fully
        UIThreadRunnable.syncExec(new VoidResult() {
            @Override
            public void run() {
                // do nothing, just wait for all events to be processed
            }
        });
    }

    /**
     * This method is designed to wait close of progress monitor dialog if it
     * opens. The dialog may not be opening if underlying process is shorter
     * than 800ms. This method is able to manage this case.
     * 
     * @param dialogTaskLabel
     *            A convenient name for the dialog to display errors
     * @throws TimeoutException
     *             If dialog didn't close before timeout
     * @see #waitProgressMonitorClose(String, String, long, TimeUnit)
     */
    public static void waitProgressMonitorClose(final String dialogTaskLabel) throws TimeoutException {
        SWTBotUtils.waitProgressMonitorClose("Progress Information", dialogTaskLabel, SWTBotUtils.CLOSE_PROGRESS_MONITOR_TIMEOUT, TimeUnit.SECONDS);
    }

    /**
     * This method is designed to wait close of progress monitor dialog if it
     * opens. The dialog may not be opening if underlying process is shorter
     * than 800ms. This method is able to mange this case.
     * 
     * @param dialogTitle
     *            The real name of progress monitor dialog to look for
     * @param dialogTaskLabel
     *            A convenient name for the dialog to display errors
     * @param timeout
     *            Time to wait for dialog close
     * @param unit
     *            Unit of timeout.
     * @throws TimeoutException
     *             If dialog didn't close before timeout
     */
    public static void waitProgressMonitorClose(final String dialogTitle, final String dialogTaskLabel, final long timeout, final TimeUnit unit) throws TimeoutException {
        SWTBotUtils.waitProgressMonitorClose(dialogTitle, dialogTaskLabel, timeout, unit, true);
    }

    /**
     * This method is designed to wait close of progress monitor dialog if it
     * opens. The dialog may not be opening if underlying process is shorter
     * than 800ms. This method is able to mange this case.
     * 
     * @param dialogTitle
     *            The real name of progress monitor dialog to look for
     * @param dialogTaskLabel
     *            A convenient name for the dialog to display errors
     * @param timeout
     *            Time to wait for dialog close
     * @param unit
     *            Unit of timeout.
     * @param wholeTitle
     *            false if the <code>dialogTitle</code> corresponds only to a
     *            part of the title, true otherwise
     * @throws TimeoutException
     *             If dialog didn't close before timeout
     */
    public static void waitProgressMonitorClose(final String dialogTitle, final String dialogTaskLabel, final long timeout, final TimeUnit unit, final boolean wholeTitle) throws TimeoutException {
        // Need to wait progress monitor opening, that is to say at least
        // ProgressManager#getLongOperationTime()
        // As of 2010-03-31, it's 800ms
        SWTBotUtils.bot.sleep(800);

        // Try to find progress dialog monitor. It may be already closed
        // SWTBot API can't be used because shell can be closed during
        // SWTBotShell object initialization, and so it fails because shell
        // might be disposed.
        final Shell[] shells = SWTBotUtils.bot.getFinder().getShells();

        final Shell progressShell = UIThreadRunnable.syncExec(SWTBotUtils.bot.getDisplay(), new WidgetResult<Shell>() {
            @Override
            public Shell run() {
                for (Shell shell : shells) {
                    if (!shell.isDisposed()) {
                        if ((wholeTitle && shell.getText().equalsIgnoreCase(dialogTitle)) || (!wholeTitle && shell.getText().contains(dialogTitle))) {
                            // Shell has been found
                            return shell;
                        }
                    }
                }
                return null;
            }
        });

        if (progressShell != null) {
            // Wait for close operation
            long begin = System.currentTimeMillis();

            boolean noTimeOut = true;

            while (noTimeOut && SWTBotUtils.isShellNotClosed(progressShell)) {

                long now = System.currentTimeMillis();

                if (now - begin > unit.toMillis(timeout)) {
                    noTimeOut = false;

                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            if (!noTimeOut) {
                // Check again, sometimes it is necessary to wait a little bit
                // more
                SWTBotUtils.waitAllUiEvents();

                // Still not closed
                if (SWTBotUtils.isShellNotClosed(progressShell)) {
                    throw new TimeoutException("'" + dialogTaskLabel + "' progress monitor dialog did not close in " + unit.toSeconds(SWTBotUtils.CLOSE_PROGRESS_MONITOR_TIMEOUT) + "s");
                }
            }
        }
    }

    private static boolean isShellNotClosed(final Shell progressShell) {
        return UIThreadRunnable.syncExec(SWTBotUtils.bot.getDisplay(), new BoolResult() {
            @Override
            public Boolean run() {
                return !progressShell.isDisposed();
            }
        });
    }

    /**
     * Finds the {@link FontFormat} of a {@link TreeItem}.
     * 
     * @param widget
     *            the {@link TreeItem} under investigation
     * @return the {@link FontFormat} of the {@link TreeItem}
     */
    public static FontFormat getWidgetFormat(final TreeItem widget) {
        return UIThreadRunnable.syncExec(new Result<FontFormat>() {
            @Override
            public FontFormat run() {
                FontFormat result = FontFormat.NORMAL_LITERAL;
                Font font = widget.getFont(0);
                if (font.getFontData().length > 0) {
                    switch (font.getFontData()[0].getStyle()) {
                    case SWT.BOLD:
                        result = FontFormat.BOLD_LITERAL;
                        break;
                    case SWT.ITALIC:
                        result = FontFormat.ITALIC_LITERAL;
                        break;
                    case SWT.BOLD | SWT.ITALIC:
                        result = FontFormat.BOLD_ITALIC_LITERAL;
                        break;
                    default:
                        result = FontFormat.NORMAL_LITERAL;
                        break;
                    }
                }
                return result;
            }
        });
    }

    /**
     * Clicks on the column at the given index inside the given
     * {@link SWTBotTree}.
     * 
     * @param tree
     *            the {@link SWTBotTree} to click on
     * @param columnIndex
     *            the column index
     */
    public static void clickOnTreeColumn(final SWTBotTree tree, final int columnIndex) {
        Display.getDefault().asyncExec(new Runnable() {

            @Override
            public void run() {
                TreeColumn column = tree.widget.getColumn(columnIndex);
                new SWTBotTreeColumn(column).clickOnColumn();
            }
        });
    }

    /**
     * A Mock-up allowing to click on TreeColumns.
     * 
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     * 
     */
    public static final class SWTBotTreeColumn extends AbstractSWTBot<TreeColumn> {
        /**
         * Constructs a new instance with the given widget.
         * 
         * @param w
         *            the widget.
         * @throws WidgetNotFoundException
         *             if the widget is <code>null</code> or widget has been
         *             disposed.
         */
        public SWTBotTreeColumn(TreeColumn w) throws WidgetNotFoundException {
            super(w);
        }

        /**
         * Simulate a click on the column.
         */
        public void clickOnColumn() {
            notify(SWT.Selection);
            notify(SWT.MouseUp, createMouseEvent(0, 0, 1, SWT.BUTTON1, 1), this.widget.getParent());
        }

    }

}
