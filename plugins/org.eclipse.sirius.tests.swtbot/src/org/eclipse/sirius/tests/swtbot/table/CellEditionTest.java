/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.table;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.CellUpdater;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.table.ui.tools.api.editor.ITableCellEditorFactory;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.table.celleditorfactory.AbstractClassCellEditorFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.swtbot.swt.finder.widgets.SiriusSWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SiriusSWTBotTreeItem;

/**
 * Tests for table cell edition.
 * 
 * @author pcdavid
 */
public class CellEditionTest extends AbstractSiriusSwtBotGefTestCase {

    /** Odesign. */
    private static final String MODEL = "different_parent_expression.odesign";

    private static final String VSM_MODEL_LOADED_DYNAMICALLY_PATH = "/data/unit/table/vsmWithCellEditorTools/vsmWithCellEditorTools.odesign";

    /** Test repository. */
    private static final String DATA_UNIT_DIR = "data/unit/table/cellEdition/";

    /** Session file. */
    private static final String SESSION_FILE = "My.aird";

    /** UML File. */
    private static final String ECORE_FILE = "My.ecore";

    /** File directory. */
    private static final String FILE_DIR = "/";

    /** Local Session. */
    private UILocalSession localSession;

    /** Session. */
    private UIResource sessionAirdResource;

    /** List of viewpoints loaded for this test. */
    private final Set<Viewpoint> viewpoints = new HashSet<>();

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        viewpoints.addAll(ViewpointRegistry.getInstance().registerFromPlugin(Activator.PLUGIN_ID + VSM_MODEL_LOADED_DYNAMICALLY_PATH));

        String[] fileNames = { MODEL, SESSION_FILE, ECORE_FILE };
        for (final String fileName : fileNames) {
            EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, DATA_UNIT_DIR + fileName, getProjectName() + "/" + fileName);
        }
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Tests that editing a cell on a column which uses the same featureName as another column edits the right cell.
     * This is a regression test for VP-2683.
     * 
     * @throws Exception
     *             if an error occurs.
     */
    public void testEditBooleanCellWithOtherColumnOnSameFeatureName() throws Exception {
        final UITableRepresentation table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("different_parent_expression").selectRepresentation("different_parent_expression")
                .selectRepresentationInstance("new different_parent_expression", UITableRepresentation.class).open();
        SWTBotTreeItem[] items = table.getTable().getAllItems();

        // Check the values before.
        assertEquals("A0", items[0].cell(0));
        assertEquals("true", items[0].cell(1));
        assertEquals("", items[0].cell(2));

        assertEquals("B0", items[1].cell(0));
        assertEquals("false", items[1].cell(1));
        assertEquals("true", items[1].cell(2));

        // Toggle the cell on line "B0" and column "IsAbstract".
        items[1].select();
        items[1].click(1);
        SWTBotUtils.pressKeyboardKey(table.getTable().widget, SWT.SPACE, SWT.SPACE);

        bot.toolbarButtonWithTooltip("Force a refresh of the table").click();
        SWTBotUtils.waitAllUiEvents();
        table.getTable().display.syncExec(new Runnable() {
            @Override
            public void run() {
                table.getTable().widget.update();
            }
        });
        SWTBotUtils.waitAllUiEvents();

        // Check that the edited cell has changed, and only that cell.
        assertEquals("A0", items[0].cell(0));
        assertEquals("true", items[0].cell(1));
        assertEquals("", items[0].cell(2));

        assertEquals("B0", items[1].cell(0));
        assertEquals("true", items[1].cell(1));
        assertEquals("true", items[1].cell(2));
    }

    /**
     * Check that when a wrong qualified name is used, the default CellEditor is used and a message is displayed in
     * Error Log view.
     */
    public void testCellEditorWithWrongQualifiedName() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 3;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTableWithInvalidCellEditor", "new ClassTableWithInvalidCellEditor", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            SWTBotUtils.waitAllUiEvents();

            // Check that the expected warning is displayed in the Error Log view
            assertEquals("One warning must be displayed in Error Log view when using double click on a column using a CellEditor with a wrong qualified name.", 1,
                    platformProblemsListener.warningsCount());
            String expectedMessage = MessageFormat.format(Messages.DFeatureColumnEditingSupport_notJavaQualifiedName,
                    "org.eclipse.sirius.tests.swtbot.table.celleditorfactory.WrongQualifiedName/CellEditorFactory");
            String message = platformProblemsListener.getWarnings().values().stream().filter(status -> !status.isEmpty()).findFirst().get().iterator().next().getMessage();
            assertEquals("The displayed message in the Error Log view is not the expected one.", expectedMessage, message);
            // Check that the value has not been changed (in this case the Cell is not editable).
            assertEquals("The value must not be changed in case of a CellEditor with a wrong qualified name.", "false", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check that when the CellEditor tool returns null, the default editor is used to edit the value and a
     * corresponding message is displayed in Error Log view.
     */
    public void testCellEditorWithNullCellEditor() {
        int lineIndexToTest = 7;
        int columnIndexToTest = 3;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTable", "new ClassTable", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("NullMyClass4", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            SWTBotUtils.waitAllUiEvents();

            // Check that there is no warning displayed in the Error Log view.
            assertEquals("0 warning must be displayed in Error Log view when using double click on a column using a CellEditorFactory returning null as CellEditor.", 0,
                    platformProblemsListener.warningsCount());
            // Check that the value has not been changed (in this case the Cell is not editable).
            assertEquals("The value must not be changed in case of a null CellEditor returned by the CellEditorFactory.", "false", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }

    }

    /**
     * Check a LabelEdit tool.
     */
    public void testLabelEdit() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 2;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTable", "new ClassTable", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            // Get the text field
            SWTBotText textArea = editor.bot().text();
            assertEquals("The current value of the combo box is not the expected one", "false", textArea.getText());
            // Write "true" in the text area
            textArea.setText("true");
            // Select the line to validate the new value
            items[lineIndexToTest].select();
            SWTBotUtils.waitAllUiEvents();

            // Check that the value has been changed (with the LabelEdit tool).
            assertEquals("true", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check a CellEditor tool returning a combo box with boolean values (corresponding to the value of edited field).
     */
    public void testCellEditorReturningAComboBox() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 3;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTable", "new ClassTable", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            // Get the combo box
            SWTBotCCombo combo = editor.bot().ccomboBox();
            assertEquals("The current value of the combo box is not the expected one", "false", combo.getText());
            // Select the second value ("true")
            combo.setSelection(1);
            // Select the line to validate the selection
            items[lineIndexToTest].select();
            SWTBotUtils.waitAllUiEvents();

            // Check that the value has been changed (with the combo box provided by the CellEditor tool).
            assertEquals("true", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check a CellEditor tool returning a specific selection dialog. The result is not tested, only the dialog opening
     * is tested.
     */
    public void testCellEditorReturningASelectionDialog() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 6;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTable", "new ClassTable", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("[]", items[lineIndexToTest].cell(columnIndexToTest));

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            // Get the button to display the selection dialog
            editor.bot().button().click();
            String title = AbstractClassCellEditorFactory.DIALOG_TITLE + " -- " + AbstractClassCellEditorFactory.getDisplayedName("MyClass1");
            editor.bot().waitUntilWidgetAppears(Conditions.shellIsActive(title));
            final SWTBotShell selectionShell = editor.bot().shell(title);
            selectionShell.close();
            SWTBotUtils.waitAllUiEvents();
        } finally {
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check that CellEditor tool takes priority over LabelEdit tool.
     */
    public void testCellEditorPriority() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 1;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTableWithInvalidCellEditor", "new ClassTableWithInvalidCellEditor", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Ensure the column contains both a CellEditor tool and an EditLabel tool.
            DTableEditor dTableEditor = (DTableEditor) editor.getReference().getEditor(false);
            DTable dTable = (DTable) dTableEditor.getRepresentation();
            CellUpdater cellUpdater = dTable.getLines().get(lineIndexToTest).getCells().get(columnIndexToTest - 1).getUpdater();
            if (!(cellUpdater != null && cellUpdater.getDirectEdit() != null && cellUpdater.getCellEditor() != null)) {
                fail("The cell to edit must contain both a CellEditor tool and an EditLabel tool.");
            }

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            // Get the combo box
            try {
                SWTBotText textArea = editor.bot().text();
                fail("The CellEditor should takes the priority. So a text field with value \"" + textArea.getText() + "\" should not be displayed.");
            } catch (WidgetNotFoundException e) {
                // Expected as the CellEditor provides a combo box
            }
            SWTBotCCombo combo = editor.bot().ccomboBox();
            assertEquals("The current value of the combo box is not the expected one", "false", combo.getText());
            // Select the second value ("true")
            combo.setSelection(1);
            // Select the line to validate the selection
            items[lineIndexToTest].select();
            SWTBotUtils.waitAllUiEvents();

            // Check that the value has been changed (with the combo box provided by the CellEditor tool).
            assertEquals("true", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check a CellEditor tool returning a combo box with specific values (not corresponding to the value of edited
     * field).
     */
    public void testCellEditorReturningAComboBox2() {
        int lineIndexToTest = 2;
        int columnIndexToTest = 3;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTable", "new ClassTable", DTable.class);
            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("SubstituteMyClass3", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            // Get the combo box
            SWTBotCCombo combo = editor.bot().ccomboBox();
            assertEquals("The current value of the combo box is not the expected one", "My false value", combo.getText());
            // Select the second value ("My true value")
            combo.setSelection(1);
            // Select the line to validate the selection
            items[lineIndexToTest].select();
            SWTBotUtils.waitAllUiEvents();

            // Check that the value has been changed (with the combo box provided by the CellEditor tool).
            assertEquals("true", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check that when the qualified name of the CellEditor tool correspond to an unexisting class or inaccessible
     * class, the default CellEditor is used and a message is displayed in Error Log view.
     */
    public void testCellEditorWithUnexistingOrNotAccessibleClass() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 2;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTableWithInvalidCellEditor", "new ClassTableWithInvalidCellEditor", DTable.class);

            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);

            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            SWTBotUtils.waitAllUiEvents();

            // Check that the expected warning is displayed in the Error Log view
            assertEquals("One warning must be displayed in Error Log view when using double click on a column using a CellEditor with an unexisting CellEditorFactory.", 1,
                    platformProblemsListener.warningsCount());
            String qualifiedName = "org.eclipse.sirius.tests.swtbot.table.celleditorfactory.UnexistingCellEditorFactory";
            String expectedMessage = MessageFormat.format(Messages.DFeatureColumnEditingSupport_unusableCellEditor, qualifiedName,
                    MessageFormat.format(Messages.CelEditorFactoryManager_notFound, qualifiedName));
            String message = platformProblemsListener.getWarnings().values().stream().filter(status -> !status.isEmpty()).findFirst().get().iterator().next().getMessage();
            assertEquals("The displayed message in the Error Log view is not the expected one.", expectedMessage, message);
            // Check that the value has not been changed (in this case the Cell is not editable).
            assertEquals("The value must not be changed in case of a CellEditor with an unexisting CellEditorFactory.", "false", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Check that when the qualified name of the CellEditor tool correspond to a class that does not implement
     * {@link ITableCellEditorFactory}, the default CellEditor is used and a message is displayed in Error Log view.
     */
    public void testCellEditorWithClassNotImplementingITableCellEditorFactory() {
        int lineIndexToTest = 0;
        int columnIndexToTest = 4;
        SWTBotEditor editor = null;
        try {
            editor = openRepresentation(localSession.getOpenedSession(), "ClassTableWithInvalidCellEditor", "new ClassTableWithInvalidCellEditor", DTable.class);

            // TODO: To replace by SWTBotTreeItem[] items = editor.bot().tree().getAllItems(); as soon as bugzilla
            // 571838 is resolved and corresponding SWTBot version is used by Sirius.
            SiriusSWTBotTreeItem[] items = SiriusSWTBotTree.tree(editor.bot()).getAllItems();

            // Check the values before.
            assertEquals("MyClass1", items[lineIndexToTest].cell(0));
            assertEquals("false", items[lineIndexToTest].cell(columnIndexToTest));

            platformProblemsListener.setWarningCatchActive(true);
            items[lineIndexToTest].select();
            items[lineIndexToTest].doubleClick(columnIndexToTest);
            SWTBotUtils.waitAllUiEvents();

            // Check that the expected warning is displayed in the Error Log view
            assertEquals("One warning must be displayed in Error Log view when using double click on a column using a CellEditor that does not implement ITableCellEditorFactory.", 1,
                    platformProblemsListener.warningsCount());
            String qualifiedName = "org.eclipse.sirius.tests.swtbot.table.celleditorfactory.NotATableCellEditorFactory";
            String expectedMessage = MessageFormat.format(Messages.DFeatureColumnEditingSupport_unusableCellEditor, qualifiedName,
                    MessageFormat.format(Messages.CelEditorFactoryManager_wrongImplementation, qualifiedName));
            String message = platformProblemsListener.getWarnings().values().stream().filter(status -> !status.isEmpty()).findFirst().get().iterator().next().getMessage();
            assertEquals("The displayed message in the Error Log view is not the expected one.", expectedMessage, message);
            // Check that the value has not been changed (in this case the Cell is not editable).
            assertEquals("The value must not be changed in case of a CellEditor that does not implement ITableCellEditorFactory.", "false", items[lineIndexToTest].cell(columnIndexToTest));
        } finally {
            platformProblemsListener.setWarningCatchActive(false);
            platformProblemsListener.clearWarnings();
            if (editor != null) {
                editor.close();
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        for (Viewpoint vp : viewpoints) {
            ViewpointRegistry.getInstance().disposeFromPlugin(vp);
        }
        viewpoints.clear();

        super.tearDown();
    }
}
