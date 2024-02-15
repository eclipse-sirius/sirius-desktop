/**
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.editor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.SWTGefBot;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.StringResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyList.ListElement;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyTitle;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * 
 * SWTBot designer helper.
 * 
 * @author nlepine
 * 
 */
@SuppressWarnings("restriction")
public final class SWTBotSiriusHelper {
    /**
     * SWTWorkbenchBot
     */
    private static SWTWorkbenchBot workbenchBot = new SWTWorkbenchBot();

    private SWTBotSiriusHelper() {
        // Nothing
    }

    /**
     * Get the tabbed property sheet title in the properties view.
     * 
     * @return the tabbed property sheet title. Label to find.
     */
    public static Option<String> getPropertyItemTitle() {
        @SuppressWarnings("unchecked")
        final Matcher<TabbedPropertyTitle> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(TabbedPropertyTitle.class));
        final List<TabbedPropertyTitle> widgets = SWTBotSiriusHelper.widget(matcher);

        String result = UIThreadRunnable.syncExec(SWTUtils.display(), (StringResult) () -> {
            for (final TabbedPropertyTitle tabbedProperty : widgets) {
                Optional<Object> title = ReflectionHelper.getFieldValueWithoutException(tabbedProperty, "text");
                if (title.isPresent()) {
                    return (String) title.get();
                }
            }
            return null;
        });

        return Options.newSome(result);
    }

    /**
     * Select the tab with the name label in the property views.
     * 
     * @param label
     *            Label to find.
     * @param propertiesBot
     *            the bot corresponding to the property view.
     * @return true if the property tab is found, false otherwise
     */
    public static boolean selectPropertyTabItem(final String label, SWTBot propertiesBot) {
        @SuppressWarnings({ "unchecked" })
        final Matcher<TabbedPropertyList> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
        final TabbedPropertyList widgets = propertiesBot.widget(matcher);

        Boolean result = UIThreadRunnable.syncExec(SWTUtils.display(), (BoolResult) () -> {
            boolean result1 = false;

            Control[] children = widgets.getTabList();
            for (Control control : children) {
                if (control.toString().equals(label)) {
                    final Event mouseEvent = SWTBotSiriusHelper.createEvent(control, control.getBounds().x, control.getBounds().y, 1, SWT.BUTTON1, 1);
                    control.notifyListeners(SWT.MouseUp, mouseEvent);

                    result1 = true;
                    break; // quit the for
                }
            }
            return result1;
        });

        return result != null && result.booleanValue();
    }

    /**
     * Returns the menu with the given label from the given bot.
     * 
     * @param bot
     *            the swtbot to use for the search
     * @param menuLabel
     *            the label of the menu to search.
     * @return the menu with the given label from the given bot.
     * @throws WidgetNotFoundException
     *             if the menu could not be found.
     */
    public static SWTBotMenu menu(SWTBot bot, String menuLabel) {
        SWTBotShell[] shells = bot.shells();
        SWTBotMenu menu = null;
        for (SWTBotShell swtBotShell : shells) {
            if (!swtBotShell.isOpen()) {
                // Avoid looking for the menu on inactive shells, which can cause long delays
                continue;
            }
            try {
                menu = bot.menu(swtBotShell).menu(menuLabel);
            } catch (WidgetNotFoundException e) {
                // we ignore.
            }
        }
        if (menu == null) {
            String shellNames = Arrays.stream(shells).map(SWTBotShell::getText).collect(Collectors.joining("\n"));
            throw new WidgetNotFoundException("No widget with label '" + menuLabel + "' could be found from the given bot in the associated shells: \n" + shellNames);
        }
        return menu;
    }

    /**
     * Return the swtbot corresponding to the shell with the given label.
     * 
     * @param label
     *            the label of the shell from which we want the related swtbot.
     * @return the swtbot corresponding to the shell with the given label.
     */
    public static SWTBot getShellBot(String label) {
        workbenchBot.waitUntil(Conditions.shellIsActive(label));
        return workbenchBot.shell(label).bot();
    }

    /**
     * Select the tab with the name label in the property views.
     * 
     * @param label
     *            Label to find.
     * @return true if the property tab is found, false otherwise
     * @deprecated you should use {@link SWTBotSiriusHelper#selectPropertyTabItem(String, SWTBot)} instead.
     */
    @Deprecated
    public static boolean selectPropertyTabItem(final String label) {
        @SuppressWarnings({ "unchecked" })
        final Matcher<TabbedPropertyList> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
        final List<TabbedPropertyList> widgets = SWTBotSiriusHelper.widget(matcher);

        Boolean result = UIThreadRunnable.syncExec(SWTUtils.display(), (BoolResult) () -> {
            boolean result1 = false;

            for (final TabbedPropertyList tabbedProperty : widgets) {
                final ListElement tabItem = SWTBotSiriusHelper.getTabItem(label, tabbedProperty);
                if (tabItem != null) {
                    final Event mouseEvent = SWTBotSiriusHelper.createEvent(tabItem, tabItem.getBounds().x, tabItem.getBounds().y, 1, SWT.BUTTON1, 1);
                    tabItem.notifyListeners(SWT.MouseUp, mouseEvent);

                    result1 = true;
                    break; // quit the for
                }
            } // for

            return result1;
        });

        return result != null && result.booleanValue();
    }

    /**
     * Find widget.
     * 
     * @param matcher
     *            the matcher used to match widgets.
     * @param <T>
     *            the type of the {@link Widget} to match
     * @return all the widgets matching the matcher.
     */
    public static <T extends Widget> List<T> widget(final Matcher<T> matcher) {
        final WaitForObjectCondition<T> waitForWidget = org.eclipse.swtbot.swt.finder.waits.Conditions.waitForWidget(matcher);
        SWTBotSiriusHelper.workbenchBot.waitUntilWidgetAppears(waitForWidget);
        return waitForWidget.getAllMatches();
    }

    /**
     * Create a event <br>
     * 
     * @param x
     *            the x coordinate of the mouse event.
     * @param y
     *            the y coordinate of the mouse event.
     * @param button
     *            the mouse button that was clicked.
     * @param stateMask
     *            the state of the keyboard modifier keys.
     * @param count
     *            the number of times the mouse was clicked.
     * @return an event that encapsulates {@link #widget} and {@link #display}
     */
    private static Event createEvent(final Widget widget, final int x, final int y, final int button, final int stateMask, final int count) {
        final Event event = new Event();
        event.time = (int) System.currentTimeMillis();
        event.widget = widget;
        event.display = SWTBotSiriusHelper.workbenchBot.getDisplay();
        event.x = x;
        event.y = y;
        event.button = button;
        event.stateMask = stateMask;
        event.count = count;
        return event;
    }

    /**
     * Select the tab with the name label in the property views
     * 
     * @param label
     */
    private static ListElement getTabItem(final String label, final TabbedPropertyList tabbedProperty) {
        for (final Object listElement : tabbedProperty.getTabList()) {
            if (listElement instanceof ListElement && ((ListElement) listElement).getTabItem().getText().equals(label)) {
                return (ListElement) listElement;
            }
        }
        return null;
    }

    /**
     * Attempts to locate the editor matching the given name. If no match is found an exception will be thrown. The name
     * is the name as displayed on the editor's tab in eclipse.
     * 
     * @param fileName
     *            the name of the file.
     * @return an editor for the specified fileName.
     * @throws WidgetNotFoundException
     *             if the editor is not found.
     */
    public static SWTBotEditor getSiriusEditor(final String fileName) throws WidgetNotFoundException {
        SWTBotSiriusHelper.workbenchBot.editorByTitle(fileName).show();
        return SWTBotSiriusHelper.getSiriusEditor(fileName, 0);
    }

    /**
     * Attempts to locate the Gef editor matching the given name. If no match is found an exception will be thrown. The
     * name is the name as displayed on the editor's tab in eclipse.
     * 
     * @param fileName
     *            the name of the file.
     * @return an editor for the specified fileName.
     * @throws WidgetNotFoundException
     *             if the editor is not found.
     */
    public static SWTBotSiriusDiagramEditor getSiriusDiagramEditor(final String fileName) throws WidgetNotFoundException {
        SWTBotSiriusHelper.workbenchBot.editorByTitle(fileName).show();
        return SWTBotSiriusHelper.getSiriusDiagramEditor(fileName, 0);
    }

    /**
     * Attempts to locate the Gef editor matching the partial given name. If no match is found an exception will be
     * thrown. The name is the name as displayed on the editor's tab in eclipse.
     * 
     * @param partialFileName
     *            the partial name of the file.
     * @return an editor for the specified fileName.
     * @throws WidgetNotFoundException
     *             if the editor is not found.
     */
    public static SWTBotSiriusDiagramEditor getDesignerEditorContainingName(final String partialFileName) throws WidgetNotFoundException {
        final Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(Matchers.containsString(partialFileName));
        return SWTBotSiriusHelper.getSiriusDiagramEditor(withPartName, 0);
    }

    /**
     * Get the first {@link SWTBotEditor} corresponding to a Diagram DialectEditor with <code>title</code> has title.
     * 
     * @param title
     *            the title of the searched editor
     * 
     * @return the first {@link SWTBotEditor} corresponding to a Diagram DialectEditor with <code>title</code> has title
     */
    public static SWTBotEditor getDiagramDialectEditorBot(String title) {
        return SWTBotSiriusHelper.getDiagramDialectEditorBots(title).get(0);
    }

    /**
     * Get all {@link SWTBotEditor} corresponding to a Diagram DialectEditor with <code>title</code> has title.
     * 
     * @param title
     *            the title of the searched editor
     * 
     * @return all {@link SWTBotEditor} corresponding to a Diagram DialectEditor with <code>title</code> has title
     */
    public static List<SWTBotEditor> getDiagramDialectEditorBots(String title) {
        List<SWTBotEditor> diagramDialectEditorBots = new ArrayList<>();
        Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(title);
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.workbenchBot.waitUntilWidgetAppears(waitForEditor);
        List<IEditorReference> editorReferences = waitForEditor.getAllMatches();
        for (IEditorReference editorReference : editorReferences) {
            IEditorPart editorPart = editorReference.getEditor(false);
            if (editorPart instanceof DDiagramEditor) {
                SWTBotEditor swtBotEditor = new SWTBotEditor(editorReference, SWTBotSiriusHelper.workbenchBot);
                diagramDialectEditorBots.add(swtBotEditor);
            }
        }
        return diagramDialectEditorBots;
    }

    /**
     * Get the first {@link SWTBotEditor} corresponding to a Tree DialectEditor with <code>title</code> has title.
     * 
     * @param title
     *            the title of the searched editor
     * 
     * @return the first {@link SWTBotEditor} corresponding to a Tree DialectEditor with <code>title</code> has title
     */
    public static SWTBotEditor getTreeDialectEditorBot(String title) {
        SWTBotSiriusHelper.workbenchBot.editorByTitle(title).show();
        return SWTBotSiriusHelper.getTreeDialectEditorBots(title).get(0);
    }

    /**
     * Get all {@link SWTBotEditor} corresponding to a Tree DialectEditor with <code>title</code> has title.
     * 
     * @param title
     *            the title of the searched editor
     * 
     * @return all {@link SWTBotEditor} corresponding to a Tree DialectEditor with <code>title</code> has title
     */
    public static List<SWTBotEditor> getTreeDialectEditorBots(String title) {
        List<SWTBotEditor> treeDialectEditorBots = new ArrayList<>();
        Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(title);
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.workbenchBot.waitUntilWidgetAppears(waitForEditor);
        List<IEditorReference> editorReferences = waitForEditor.getAllMatches();
        for (IEditorReference editorReference : editorReferences) {
            IEditorPart editorPart = editorReference.getEditor(false);
            if (editorPart instanceof DTreeEditor) {
                SWTBotEditor swtBotEditor = new SWTBotEditor(editorReference, SWTBotSiriusHelper.workbenchBot);
                treeDialectEditorBots.add(swtBotEditor);
            }
        }
        return treeDialectEditorBots;
    }

    /**
     * Get the first {@link SWTBotEditor} corresponding to a Table DialectEditor with <code>title</code> has title.
     * 
     * @param title
     *            the title of the searched editor
     * 
     * @return the first {@link SWTBotEditor} corresponding to a Table DialectEditor with <code>title</code> has title
     */
    public static SWTBotEditor getTableDialectEditorBot(String title) {
        SWTBotSiriusHelper.workbenchBot.editorByTitle(title).show();
        return SWTBotSiriusHelper.getTableDialectEditorBots(title).get(0);
    }

    /**
     * Get all {@link SWTBotEditor} corresponding to a Table DialectEditor with <code>title</code> has title.
     * 
     * @param title
     *            the title of the searched editor
     * 
     * @return all {@link SWTBotEditor} corresponding to a Table DialectEditor with <code>title</code> has title
     */
    public static List<SWTBotEditor> getTableDialectEditorBots(String title) {
        List<SWTBotEditor> tableDialectEditorBots = new ArrayList<>();
        Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(title);
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.workbenchBot.waitUntilWidgetAppears(waitForEditor);
        List<IEditorReference> editorReferences = waitForEditor.getAllMatches();
        for (IEditorReference editorReference : editorReferences) {
            IEditorPart editorPart = editorReference.getEditor(false);
            if (editorPart instanceof DTableEditor) {
                SWTBotEditor swtBotEditor = new SWTBotEditor(editorReference, SWTBotSiriusHelper.workbenchBot);
                tableDialectEditorBots.add(swtBotEditor);
            }
        }
        return tableDialectEditorBots;
    }

    /**
     * Attempts to locate the editor matching the given name. If no match is found an exception will be thrown. The name
     * is the name as displayed on the editor's tab in eclipse.
     * 
     * @param fileName
     *            the name of the file.
     * @param index
     *            in case of multiple views with the same fileName.
     * @return an editor for the specified fileName.
     * @throws WidgetNotFoundException
     *             if the editor is not found.
     */
    public static SWTBotSiriusDiagramEditor getSiriusDiagramEditor(final String fileName, final int index) throws WidgetNotFoundException {
        final Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(fileName);
        return SWTBotSiriusHelper.getSiriusDiagramEditor(withPartName, index);
    }

    private static SWTBotSiriusDiagramEditor getSiriusDiagramEditor(final Matcher<IEditorReference> withPartName, final int index) throws WidgetNotFoundException {
        SWTBotSiriusDiagramEditor swtBotDesignerEditor = null;
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.workbenchBot.waitUntilWidgetAppears(waitForEditor);
        IEditorReference editorReference = waitForEditor.get(index);
        if (editorReference.getEditor(false) instanceof DialectEditor) {
            swtBotDesignerEditor = new SWTBotSiriusDiagramEditor(waitForEditor.get(index), SWTBotSiriusHelper.workbenchBot);
        }
        return swtBotDesignerEditor;
    }

    /**
     * Attempts to locate the editor matching the given name. If no match is found an exception will be thrown. The name
     * is the name as displayed on the editor's tab in eclipse.
     * 
     * @param fileName
     *            the name of the file.
     * @param index
     *            in case of multiple views with the same fileName.
     * @return an editor for the specified fileName.
     * @throws WidgetNotFoundException
     *             if the editor is not found.
     */
    public static SWTBotEditor getSiriusEditor(final String fileName, final int index) throws WidgetNotFoundException {
        final Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(fileName);
        return SWTBotSiriusHelper.getSiriusEditor(withPartName, index);
    }

    private static SWTBotEditor getSiriusEditor(final Matcher<IEditorReference> withPartName, final int index) throws WidgetNotFoundException {
        SWTBotEditor swtBotSiriusEditor = null;
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.workbenchBot.waitUntilWidgetAppears(waitForEditor);
        IEditorReference editorReference = waitForEditor.get(index);
        if (editorReference.getEditor(false) instanceof DialectEditor) {
            swtBotSiriusEditor = new SWTBotEditor(waitForEditor.get(index), SWTBotSiriusHelper.workbenchBot);
        }
        return swtBotSiriusEditor;
    }

    /**
     * Close the specified {@link SWTBotEditor}.
     * 
     * @param swtBotEditor
     *            the specified {@link SWTBotEditor}
     * @param save
     *            tells if the editor to close must be saved or not
     */
    public static void close(final SWTBotEditor swtBotEditor, final boolean save) {
        UIThreadRunnable.asyncExec(() -> swtBotEditor.getReference().getPage().closeEditor(swtBotEditor.getReference().getEditor(false), save));
    }

    /**
     * Close all opened {@link SWTBotEditor}s.
     * 
     * @param save
     *            tells if the editor to close must be saved or not
     */
    public static void closeAllEditors(final boolean save) {
        UIThreadRunnable.asyncExec(() -> {
            List<? extends SWTBotEditor> editors = SWTBotSiriusHelper.workbenchBot.editors();
            for (SWTBotEditor swtBotEditor : editors) {
                swtBotEditor.getReference().getPage().closeEditor(swtBotEditor.getReference().getEditor(false), save);
            }
        });
    }

    /**
     * Returns the ColorPalettePopup shell.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell getColorPalettePopupShell(SWTGefBot bot) {
        SWTBotUtils.waitAllUiEvents();
        return bot.shell(Messages.ColorPalettePopup_title);
    }

    /**
     * Click on "Fill Color" menu in navigation bar "Diagram".
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeFillColorNavigationBar(SWTGefBot bot) {
        return changeColorNavigationBar(bot, DiagramUIMessages.ColorChangeActionMenu_fillColor);
    }

    /**
     * Click on "Font Color" menu in navigation bar "Diagram".
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeFontColorNavigationBar(SWTGefBot bot) {
        return changeColorNavigationBar(bot, DiagramUIMessages.ColorChangeActionMenu_fontColor);
    }

    /**
     * Click on "Line Color" menu in navigation bar "Diagram".
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeLineColorNavigationBar(SWTGefBot bot) {
        return changeColorNavigationBar(bot, DiagramUIMessages.ColorChangeActionMenu_lineColor);
    }

    /**
     * Click on a change color menu in navigation bar "Diagram".
     * 
     * @param menuText
     *            text of the color menu to click.
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeColorNavigationBar(SWTGefBot bot, String menuText) {
        SWTBotSiriusHelper.menu(bot, DiagramUIMessages.DiagramMainMenu_DiagramMainMenuText) //
                .menu(menuText) //
                .click();
        return getColorPalettePopupShell(bot);
    }

    /**
     * Click on "Fill Color" menu in context menu.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeFillColorContextMenu(SWTBotSiriusDiagramEditor editor, SWTGefBot bot) {
        return changeColorContextMenu(editor, bot, DiagramUIMessages.ColorChangeActionMenu_fillColor);
    }

    /**
     * Click on "Font Color" menu in context menu.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeFontColorContextMenu(SWTBotSiriusDiagramEditor editor, SWTGefBot bot) {
        return changeColorContextMenu(editor, bot, DiagramUIMessages.ColorChangeActionMenu_fontColor);
    }

    /**
     * Click on "Line Color" menu in context menu.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeLineColorContextMenu(SWTBotSiriusDiagramEditor editor, SWTGefBot bot) {
        return changeColorContextMenu(editor, bot, DiagramUIMessages.ColorChangeActionMenu_lineColor);
    }

    /**
     * Click on a change color menu in context menu.
     * 
     * @param menuText
     *            text of the color menu to click.
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeColorContextMenu(SWTBotSiriusDiagramEditor editor, SWTGefBot bot, String menuText) {
        editor.clickContextMenu(menuText);
        return getColorPalettePopupShell(bot);
    }

    /**
     * Click on "Fill Color" menu in toolbar.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeFillColorToolbarMenu(SWTGefBot bot) {
        return changeColorToolbarMenu(bot, DiagramUIMessages.ColorChangeActionMenu_fillColor);
    }

    /**
     * Click on "Font Color" menu in toolbar.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeFontColorToolbarMenu(SWTGefBot bot) {
        return changeColorToolbarMenu(bot, DiagramUIMessages.ColorChangeActionMenu_fontColor);
    }

    /**
     * Click on "Line Color" menu in toolbar.
     * 
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeLineColorToolbarMenu(SWTGefBot bot) {
        return changeColorToolbarMenu(bot, DiagramUIMessages.ColorChangeActionMenu_lineColor);
    }

    /**
     * Click on a change color menu in toolbar.
     * 
     * @param menuToolTip
     *            tooltip of the color menu to click.
     * @return the ColorPalettePopup shell.
     */
    public static SWTBotShell changeColorToolbarMenu(SWTGefBot bot, String menuToolTip) {
        SWTBotToolbarDropDownButton toolbarButton = bot.toolbarDropDownButtonWithTooltip(menuToolTip);
        Display.getDefault().asyncExec(() -> {
            int[] eventTypes = { SWT.MouseEnter, SWT.MouseMove, SWT.Activate, SWT.FocusIn, SWT.MouseDown, SWT.MouseUp, SWT.Selection, SWT.MouseHover, SWT.MouseMove, SWT.MouseExit, SWT.Deactivate,
                    SWT.FocusOut };
            for (int eventType : eventTypes) {
                Event event = new Event();
                event.time = (int) System.currentTimeMillis();
                event.widget = toolbarButton.widget;
                event.display = toolbarButton.display;
                if (eventType == SWT.Selection) {
                    event.detail = SWT.ARROW;
                }
                toolbarButton.widget.notifyListeners(eventType, event);
            }
        });
        return getColorPalettePopupShell(bot);
    }

}
