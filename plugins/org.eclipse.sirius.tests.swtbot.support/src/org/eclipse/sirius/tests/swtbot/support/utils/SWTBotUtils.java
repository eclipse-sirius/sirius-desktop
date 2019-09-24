/**
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES and others
 * This program and the accompanying materials
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
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.sirius.business.api.metamodel.helper.FontFormatHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.menu.SWTBotContextMenu;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.ControlFinder;
import org.eclipse.swtbot.swt.finder.finders.MenuFinder;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.results.WidgetResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.TimeoutException;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;

/**
 * Some utilities.
 * 
 * @author smonnier
 */
public final class SWTBotUtils {

    private static class LabelChecker implements BoolResult {

        String labelToLookFor;

        Composite compositeToInvestigate;

        LabelChecker(String label, Composite composite) {
            super();
            this.labelToLookFor = label;
            this.compositeToInvestigate = composite;
        }

        @Override
        public Boolean run() {
            return findLabelAmongChildren(this.compositeToInvestigate);
        }

        /**
         * Explore the children element of the {@link Composite} looking for the expected label.
         * 
         * @param composite
         *            parent {@link Composite}
         * @return true if the label is found among children, false otherwise
         */
        private boolean findLabelAmongChildren(Composite composite) {
            boolean result = false;
            for (Control child : composite.getChildren()) {
                if (child instanceof Composite) {
                    result = result || findLabelAmongChildren((Composite) child);
                } else if (child instanceof Label) {
                    if (labelToLookFor.equals(((Label) child).getText())) {
                        return true;
                    }
                }
            }
            return result;
        }

    }

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
     * Returns a {@link SWTBotToolbarButton} with the specified <code>tooltip</code>.
     * 
     * @param tooltip
     *            the tooltip on the widget.
     * @return a {@link SWTBotToolbarButton} with the specified <code>tooltip</code>.
     * @throws WidgetNotFoundException
     *             if the widget is not found or is disposed.
     */
    public static SWTBotToolbarButton toolbarButtonWithTooltip(String tooltip) {
        ControlFinder controlFinder = new ControlFinderWithDefaultShell();
        SWTBot swtBot = new SWTBot(controlFinder, new MenuFinder());
        return swtBot.toolbarButtonWithTooltip(tooltip);
    }

    /**
     * Press a combination of keys.
     * 
     * @param widget
     *            the current widget
     * @param key
     *            the key to press
     * @param c
     *            the key character to press
     */
    public static void pressKeyboardKey(final Widget widget, final int key, final char c) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                Event event = SWTBotUtils.keyEvent(key, c, SWT.Selection, widget);
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
     * WARNING this class should move in SWTBot one day. WARNING if the text is not found it will not failed this method
     * to get disposed elements with the current click on context menu SWTBot method
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
     * WARNING this class should move in SWTBot one day. WARNING if the text is not found it will not failed this method
     * to get disposed elements with the current click on context menu SWTBot method
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
     * WARNING this class should move in SWTBot one day. WARNING if the text is not found it will not failed this method
     * to get disposed elements with the current click on context menu SWTBot method
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
     * WARNING this class should move in SWTBot one day. WARNING if the text is not found it will not failed this method
     * to get disposed elements with the current click on context menu SWTBot method
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
     * This method is designed to wait close of progress monitor dialog if it opens. The dialog may not be opening if
     * underlying process is shorter than 800ms. This method is able to manage this case.
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
     * This method is designed to wait close of progress monitor dialog if it opens. The dialog may not be opening if
     * underlying process is shorter than 800ms. This method is able to mange this case.
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
     * This method is designed to wait close of progress monitor dialog if it opens. The dialog may not be opening if
     * underlying process is shorter than 800ms. This method is able to mange this case.
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
     *            false if the <code>dialogTitle</code> corresponds only to a part of the title, true otherwise
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
    public static List<FontFormat> getWidgetFormat(final TreeItem widget) {
        return UIThreadRunnable.syncExec(new Result<List<FontFormat>>() {
            @Override
            public List<FontFormat> run() {
                List<FontFormat> result = new ArrayList<FontFormat>();
                Font font = widget.getFont(0);
                if (font.getFontData().length > 0) {
                    switch (font.getFontData()[0].getStyle()) {
                    case SWT.BOLD:
                        FontFormatHelper.setFontFormat(result, FontFormat.BOLD_LITERAL);
                        break;
                    case SWT.ITALIC:
                        FontFormatHelper.setFontFormat(result, FontFormat.ITALIC_LITERAL);
                        break;
                    case SWT.BOLD | SWT.ITALIC:
                        FontFormatHelper.setFontFormat(result, FontFormat.BOLD_LITERAL);
                        FontFormatHelper.setFontFormat(result, FontFormat.ITALIC_LITERAL);
                        break;
                    default:
                        break;
                    }
                }
                return result;
            }
        });
    }

    /**
     * Clicks on the column at the given index inside the given {@link SWTBotTree}.
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
         *             if the widget is <code>null</code> or widget has been disposed.
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

    /**
     * Find a {@link SWTBotTreeItem} with its name in a {@link SWTBotTreeItem} list and its children recursively.It will
     * return the first found matching the given label.
     * 
     * This method checks enabled and disabled items and does consider only visible items, it will expand the tree to
     * find a matching item in the non displayed elements. If those features are required, see
     * {@link SWTBotUtils#getTreeItem(SWTBotTreeItem[], String, boolean, boolean)}.
     * 
     * @param treeElements
     *            the {@link SWTBotTreeItem} list to search in
     * @param name
     *            the name of the searched {@link SWTBotTreeItem}
     * @return the tree item
     */
    public static SWTBotTreeItem getTreeItem(final SWTBotTreeItem[] treeElements, final String name) {
        return getTreeItem(treeElements, name, false, false);
    }

    /**
     * Find a {@link SWTBotTreeItem} with its name in a {@link SWTBotTreeItem} list and its children recursively. It
     * will return the first found matching the given label.
     * 
     * If checkEnabled=true, it will only consider enabled items. If espance
     * 
     * @param treeElements
     *            the {@link SWTBotTreeItem} list to search in
     * @param searchedLabel
     *            the searched label
     * @param enabledItemsOnly
     *            if true, consider only enabled items
     * @param expandItems
     *            if true, expand tree to check non displayed tree items (and re-collapse expanded items if no matching
     *            element has been found).
     * @return the first {@link SWTBotTreeItem} matching the given label (or null if none found)
     */
    public static SWTBotTreeItem getTreeItem(SWTBotTreeItem[] treeElements, String searchedLabel, boolean enabledItemsOnly, boolean expandItems) {
        SWTBotTreeItem treeItem = null;
        if (treeElements != null) {
            for (SWTBotTreeItem item : treeElements) {
                String text = item.getText();
                // Check isEnabled if required before checking the label.
                if ((!enabledItemsOnly || item.isEnabled()) && Objects.equals(searchedLabel, text)) {
                    treeItem = item;
                } else {

                    // Expand tree if required
                    boolean oldExpanded = item.isExpanded();
                    if (expandItems) {
                        item.expand();
                    }

                    treeItem = getTreeItem(item.getItems(), searchedLabel, enabledItemsOnly, expandItems);

                    // Collapse tree if it has been expanded.
                    if (treeItem == null && expandItems && !oldExpanded) {
                        item.collapse();
                    }
                }
                if (treeItem != null) {
                    break;
                }
            }
        }
        return treeItem;
    }

    /**
     * Validate that there is a Label having a specific text in a {@link SWTBotShell}.
     * 
     * @param labelToLookFor
     *            text to look for
     * @param shell
     *            {@link SWTBotShell} to investigate
     */
    public static void checkLabelInShell(String labelToLookFor, SWTBotShell shell) {
        Assert.assertTrue("No label '" + labelToLookFor + "' has been found in the given Composite", UIThreadRunnable.syncExec(bot.getDisplay(), new LabelChecker(labelToLookFor, shell.widget)));
    }

    /**
     * Validate that there is a {@link SWTBotShell} having a specific title.
     * 
     * @param title
     *            the expected title
     * @return the {@link SWTBotShell} having the specific title
     */
    public static SWTBotShell checkForShellWithTitle(String title) {
        // Validate that TOOL_TITLE_LABEL is properly displayed
        SWTBotShell[] shells = bot.shells();
        SWTBotShell shellWithTitle = null;
        for (SWTBotShell swtBotShell : shells) {
            if (title != null && title.equals(swtBotShell.getText())) {
                shellWithTitle = swtBotShell;
            }
        }
        Assert.assertNotNull("No shell was found with label: " + title, shellWithTitle);
        return shellWithTitle;
    }

    /**
     * Checks that the tools section label and tools label are displayed as expected.
     * 
     * @param editor
     *            the current {@link SWTBotSiriusDiagramEditor}
     * @param labelsToCheck
     *            the label to check in the palette
     */
    public static void checkLabelsInPalette(SWTBotSiriusDiagramEditor editor, List<String> labelsToCheck) {
        List<SWTBotGefEditPart> children = editor.getPaletteRootEditPartBot().children();
        for (SWTBotGefEditPart child : children) {
            child.part();
            SWTBotUtils.checkLabelsInPalette(child, labelsToCheck);
        }
        Assert.assertTrue("The following tools or sections have not been found in the palette: " + labelsToCheck, labelsToCheck.isEmpty());
    }

    private static void checkLabelsInPalette(SWTBotGefEditPart swtBotGefEditPart, List<String> labelsToCheck) {
        if (labelsToCheck.isEmpty()) {
            return;
        }
        List<SWTBotGefEditPart> children = swtBotGefEditPart.children();
        for (SWTBotGefEditPart child : children) {
            if (child.part() instanceof GraphicalEditPart && ((GraphicalEditPart) child.part()).getModel() instanceof PaletteEntry) {
                PaletteEntry paletteEntry = (PaletteEntry) ((GraphicalEditPart) child.part()).getModel();
                if (labelsToCheck.contains(paletteEntry.getLabel())) {
                    labelsToCheck.remove(paletteEntry.getLabel());
                }
            }
            child.part();
            checkLabelsInPalette(child, labelsToCheck);
        }
    }

    /**
     * Checks that the tools bar contribution items label are displayed as expected. The toolBar contribution is
     * identified by its toolTip given in parameter.
     * 
     * @param editor
     *            the current {@link SWTBotSiriusDiagramEditor}
     * @param toolTip
     *            toolTip of the toolBar contribution to check
     * @param labelsToCheck
     *            the label to check in the toolBar
     */
    public static void checkLabelsInDiagramToolBar(SWTBotSiriusDiagramEditor editor, String toolTip, List<String> labelsToCheck) {
        if (labelsToCheck.isEmpty()) {
            return;
        }
        final SWTBotToolbarDropDownButton toolMenuButton = editor.bot().toolbarDropDownButtonWithTooltip(toolTip);
        List<? extends SWTBotMenu> menuItems = toolMenuButton.menuItems(WidgetMatcherFactory.widgetOfType(MenuItem.class));
        for (SWTBotMenu menuItem : menuItems) {
            if (labelsToCheck.contains(menuItem.getText())) {
                labelsToCheck.remove(menuItem.getText());
            }
        }
        Assert.assertTrue("The following menu items have not been found in the " + toolTip + " toolBars contribution : " + labelsToCheck, labelsToCheck.isEmpty());
        try {
            toolMenuButton.pressShortcut(KeyStroke.getInstance("ESC"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validate that each of the given label are available among contextual menus.
     * 
     * @param display
     *            current {@link Display}
     * @param control
     *            the {@link Control} on which the contextual menus are checked
     * @param labelsToCheck
     *            the labels that should be available among contextual menus
     * @return the parent menu containing the contextual menus
     */
    public static Menu checkContextualMenus(final Display display, final Control control, List<String> labelsToCheck) {
        final List<String> labelToFind = new ArrayList<>(labelsToCheck);
        return UIThreadRunnable.syncExec(display, new WidgetResult<Menu>() {
            @Override
            public Menu run() {
                checkMenu(control.getMenu());
                Assert.assertTrue("Some label have not been found :" + labelToFind, labelToFind.isEmpty());
                return control.getMenu();
            }

            private void checkMenu(Menu menu) {
                try {
                    menu.notifyListeners(SWT.Show, new Event());
                    for (MenuItem menuItem : menu.getItems()) {
                        if (labelToFind.contains(menuItem.getText())) {
                            labelToFind.remove(menuItem.getText());
                        }
                        if (menuItem.getMenu() != null) {
                            checkMenu(menuItem.getMenu());
                        }
                    }
                } finally {
                    menu.notifyListeners(SWT.Hide, new Event());
                }
            }
        });
    }

    /**
     * Validate that the given label is enabled among contextual menus.
     * 
     * @param display
     *            current {@link Display}
     * @param control
     *            the {@link Control} on which the contextual menus are checked
     * @param label
     *            the label that should be enabled among contextual menus
     * @return the parent menu containing the contextual menus
     * @throws WidgetNotFoundException
     *             This exception is raised if the label has not been found.
     */
    public static boolean isMenuEnabled(final Display display, final Control control, final String label) throws WidgetNotFoundException {
        // status[0]: if the menu item has been found
        // status[1]: if the menu item is enabled
        final boolean[] status = { false, false };
        UIThreadRunnable.syncExec(display, new WidgetResult<Menu>() {
            @Override
            public Menu run() {
                checkMenu(control.getMenu());
                return control.getMenu();
            }

            private void checkMenu(Menu menu) {
                try {
                    menu.notifyListeners(SWT.Show, new Event());
                    for (MenuItem menuItem : menu.getItems()) {
                        if (label.equals(menuItem.getText())) {
                            status[0] = true;
                            status[1] = menuItem.isEnabled();
                            break;
                        } else if (menuItem.getMenu() != null) {
                            checkMenu(menuItem.getMenu());
                        }
                    }
                } finally {
                    menu.notifyListeners(SWT.Hide, new Event());
                }
            }
        });
        // if the menu item has been found we return the enable status.
        if (status[0]) {
            return status[1];
        }

        // else we throw the exception.
        throw new WidgetNotFoundException("The menu item \"" + label + "\" has not been found.");
    }

    /**
     * Drag and drop a local file to the session editor.
     * 
     * @param currentBot
     *            current {@link SWTGefBot}
     * @param semanticAirdTree
     *            {@link SWTBotTree} in the aird editor.
     * @param sampleFile
     *            {@link SWTBotTreeItem} to drag and drop in the aird editor
     */
    public static void dragAndDropFileToAirdEditor(SWTGefBot currentBot, SWTBotTree semanticAirdTree, SWTBotTreeItem sampleFile) {
        int modelNumber = semanticAirdTree.getAllItems().length;

        currentBot.getDisplay().asyncExec(() -> {
            sampleFile.select();
            sampleFile.dragAndDrop(semanticAirdTree);
        });
        currentBot.waitUntil(new DefaultCondition() {

            @Override
            public boolean test() throws Exception {
                return semanticAirdTree.getAllItems().length > modelNumber;
            }

            @Override
            public String getFailureMessage() {
                return "No model was added after the drag and drop.";
            }
        });
    }

}
