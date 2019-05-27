/**
 * Copyright (c) 2009-2019 THALES GLOBAL SERVICES
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
import java.util.List;
import java.util.Optional;

import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tree.ui.tools.api.editor.DTreeEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.waits.WaitForEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.results.BoolResult;
import org.eclipse.swtbot.swt.finder.results.StringResult;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.utils.SWTUtils;
import org.eclipse.swtbot.swt.finder.waits.WaitForObjectCondition;
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
    private static SWTWorkbenchBot bot = new SWTWorkbenchBot();

    private SWTBotSiriusHelper() {
        // Nothing
    }

    /**
     * Get the tabbed property sheet title in the properties view.
     * 
     * @return the tabbed property sheet title. Label to find.
     */
    @SuppressWarnings({ "unchecked" })
    public static Option<String> getPropertyItemTitle() {
        final Matcher<TabbedPropertyTitle> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(TabbedPropertyTitle.class));
        final List<TabbedPropertyTitle> widgets = SWTBotSiriusHelper.widget(matcher);

        String result = UIThreadRunnable.syncExec(SWTUtils.display(), new StringResult() {
            @Override
            public String run() {
                for (final TabbedPropertyTitle tabbedProperty : widgets) {
                    Optional<Object> title = ReflectionHelper.getFieldValueWithoutException(tabbedProperty, "text");
                    if (title.isPresent()) {
                        return (String) title.get();
                    }
                }
                return null;
            }
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
    @SuppressWarnings({ "unchecked" })
    public static boolean selectPropertyTabItem(final String label, SWTBot propertiesBot) {
        final Matcher<TabbedPropertyList> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
        final TabbedPropertyList widgets = propertiesBot.widget(matcher);

        Boolean result = UIThreadRunnable.syncExec(SWTUtils.display(), new BoolResult() {
            @Override
            public Boolean run() {
                boolean result = false;

                Control[] children = widgets.getTabList();
                for (Control control : children) {
                    if (control.toString().equals(label)) {
                        final Event mouseEvent = SWTBotSiriusHelper.createEvent(control, control.getBounds().x, control.getBounds().y, 1, SWT.BUTTON1, 1);
                        control.notifyListeners(SWT.MouseUp, mouseEvent);

                        result = true;
                        break; // quit the for
                    }
                }
                return result;
            }
        });

        return result != null ? result.booleanValue() : false;
    }

    /**
     * Return the swtbot corresponding to the shell with the given label.
     * 
     * @param label
     *            the label of the shell from which we want the related swtbot.
     * @return the swtbot corresponding to the shell with the given label.
     */
    public static SWTBot getShellBot(String label) {
        bot.waitUntil(Conditions.shellIsActive(label));
        return bot.shell(label).bot();
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
    @SuppressWarnings({ "unchecked" })
    public static boolean selectPropertyTabItem(final String label) {
        final Matcher<TabbedPropertyList> matcher = Matchers.allOf(WidgetMatcherFactory.widgetOfType(TabbedPropertyList.class));
        final List<TabbedPropertyList> widgets = SWTBotSiriusHelper.widget(matcher);

        Boolean result = UIThreadRunnable.syncExec(SWTUtils.display(), new BoolResult() {
            @Override
            public Boolean run() {
                boolean result = false;

                for (final TabbedPropertyList tabbedProperty : widgets) {
                    final ListElement tabItem = SWTBotSiriusHelper.getTabItem(label, tabbedProperty);
                    if (tabItem != null) {
                        final Event mouseEvent = SWTBotSiriusHelper.createEvent(tabItem, tabItem.getBounds().x, tabItem.getBounds().y, 1, SWT.BUTTON1, 1);
                        tabItem.notifyListeners(SWT.MouseUp, mouseEvent);

                        result = true;
                        break; // quit the for
                    }
                } // for

                return result;
            }
        });

        return result != null ? result.booleanValue() : false;
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
        SWTBotSiriusHelper.bot.waitUntilWidgetAppears(waitForWidget);
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
        event.display = SWTBotSiriusHelper.bot.getDisplay();
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
        SWTBotSiriusHelper.bot.editorByTitle(fileName).show();
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
        SWTBotSiriusHelper.bot.editorByTitle(fileName).show();
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
        SWTBotEditor swtBotEditor = SWTBotSiriusHelper.getDiagramDialectEditorBots(title).get(0);
        return swtBotEditor;
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
        List<SWTBotEditor> diagramDialectEditorBots = new ArrayList<SWTBotEditor>();
        Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(title);
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.bot.waitUntilWidgetAppears(waitForEditor);
        List<IEditorReference> editorReferences = waitForEditor.getAllMatches();
        for (IEditorReference editorReference : editorReferences) {
            IEditorPart editorPart = editorReference.getEditor(false);
            if (editorPart instanceof DDiagramEditor) {
                SWTBotEditor swtBotEditor = new SWTBotEditor(editorReference, SWTBotSiriusHelper.bot);
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
        SWTBotSiriusHelper.bot.editorByTitle(title).show();
        SWTBotEditor swtBotEditor = SWTBotSiriusHelper.getTreeDialectEditorBots(title).get(0);
        return swtBotEditor;
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
        List<SWTBotEditor> treeDialectEditorBots = new ArrayList<SWTBotEditor>();
        Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(title);
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.bot.waitUntilWidgetAppears(waitForEditor);
        List<IEditorReference> editorReferences = waitForEditor.getAllMatches();
        for (IEditorReference editorReference : editorReferences) {
            IEditorPart editorPart = editorReference.getEditor(false);
            if (editorPart instanceof DTreeEditor) {
                SWTBotEditor swtBotEditor = new SWTBotEditor(editorReference, SWTBotSiriusHelper.bot);
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
        SWTBotSiriusHelper.bot.editorByTitle(title).show();
        SWTBotEditor swtBotEditor = SWTBotSiriusHelper.getTableDialectEditorBots(title).get(0);
        return swtBotEditor;
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
        List<SWTBotEditor> tableDialectEditorBots = new ArrayList<SWTBotEditor>();
        Matcher<IEditorReference> withPartName = org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory.withPartName(title);
        final Matcher<IEditorReference> matcher = Matchers.allOf(IsInstanceOf.instanceOf(IEditorReference.class), withPartName);
        final WaitForEditor waitForEditor = Conditions.waitForEditor(matcher);
        SWTBotSiriusHelper.bot.waitUntilWidgetAppears(waitForEditor);
        List<IEditorReference> editorReferences = waitForEditor.getAllMatches();
        for (IEditorReference editorReference : editorReferences) {
            IEditorPart editorPart = editorReference.getEditor(false);
            if (editorPart instanceof DTableEditor) {
                SWTBotEditor swtBotEditor = new SWTBotEditor(editorReference, SWTBotSiriusHelper.bot);
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
        SWTBotSiriusHelper.bot.waitUntilWidgetAppears(waitForEditor);
        IEditorReference editorReference = waitForEditor.get(index);
        if (editorReference.getEditor(false) instanceof DialectEditor) {
            swtBotDesignerEditor = new SWTBotSiriusDiagramEditor(waitForEditor.get(index), SWTBotSiriusHelper.bot);
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
        SWTBotSiriusHelper.bot.waitUntilWidgetAppears(waitForEditor);
        IEditorReference editorReference = waitForEditor.get(index);
        if (editorReference.getEditor(false) instanceof DialectEditor) {
            swtBotSiriusEditor = new SWTBotEditor(waitForEditor.get(index), SWTBotSiriusHelper.bot);
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
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                swtBotEditor.getReference().getPage().closeEditor(swtBotEditor.getReference().getEditor(false), save);
            }
        });
    }

    /**
     * Close all opened {@link SWTBotEditor}s.
     * 
     * @param save
     *            tells if the editor to close must be saved or not
     */
    public static void closeAllEditors(final boolean save) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                List<? extends SWTBotEditor> editors = SWTBotSiriusHelper.bot.editors();
                for (SWTBotEditor swtBotEditor : editors) {
                    swtBotEditor.getReference().getPage().closeEditor(swtBotEditor.getReference().getEditor(false), save);
                }
            }
        });
    }

}
