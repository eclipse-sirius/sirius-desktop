/*******************************************************************************
 * Copyright (c) 2018, 2024  THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.ui.tools.api.editor.DTableEditor;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TableHasColumnCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.tree.AbstractTreeSiriusSWTBotGefTestCase;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=459003 .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DeleteTableColumnTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/table/deleteColumn/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "table.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "representations.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "test.ecore";

    private SWTBotEditor tableEditorBot;

    private UITreeRepresentation tableRepresentation;

    private DTable dTable;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_FILE, SESSION_RESOURCE_FILE, SEMANTIC_RESOURCE_FILE);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();

        tableRepresentation = openEditor(localSession, "table", "table", "new table");
        tableEditorBot = tableRepresentation.getEditor();
        DTableEditor dTableEditor = (DTableEditor) tableEditorBot.getReference().getEditor(false);
        dTable = (DTable) dTableEditor.getRepresentation();
    }

    /**
     * Test table column deletion.
     */
    public void testTableLineDeletionWithDELShortcut() {
        assertEquals("The sample is expected to have 3 column", 3, dTable.getColumns().size());

        // Select a cell in the last column
        tableEditorBot.setFocus();
        SWTBotTree tree = tableEditorBot.bot().tree();
        SWTBotTreeItem treeItem = tree.getAllItems()[0].click(3);

        // Delete the column
        SWTBotUtils.clickContextMenu(treeItem, "Delete column");
        bot.waitUntil(new TableHasColumnCondition(dTable, 2));

        // Press a key and check that it does not raise an exception
        SWTBotUtils.pressKeyboardKey(tree.widget, SWT.CTRL);
        Assert.assertFalse("Pressing a key after a column deletion should not raise exceptions", platformProblemsListener.doesAnErrorOccurs());
    }

    @Override
    protected void tearDown() throws Exception {
        tableEditorBot.close();
        tableEditorBot = null;
        tableRepresentation = null;
        dTable = null;
        super.tearDown();
    }

}
