/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.tree.AbstractTreeSiriusSWTBotGefTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=459003 .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DeleteLineWithDELShortcutTest extends AbstractTreeSiriusSWTBotGefTestCase {

    /** Path. */
    private static final String PATH = "data/unit/tree/";

    /** Modeler resource file. */
    private static final String MODELER_RESOURCE_FILE = "ecore.odesign";

    /** Session resource file. */
    private static final String SESSION_RESOURCE_FILE = "tree.aird";

    /** Semantic resource file. */
    private static final String SEMANTIC_RESOURCE_FILE = "tree.ecore";

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

        tableRepresentation = openEditor(localSession, EcoreModeler.DESIGN_VIEWPOINT_NAME, "Classes", "new Classes");
        tableEditorBot = tableRepresentation.getEditor();
        DTableEditor dTableEditor = (DTableEditor) tableEditorBot.getReference().getEditor(false);
        dTable = (DTable) dTableEditor.getRepresentation();
    }

    /**
     * Test table line deletion with DEL shortcut.
     */
    public void testTableLineDeletionWithDELShortcut() {
        assertEquals("Wrong test data, the current test has been written to check the deletion of a line with one cell only.", 1, dTable.getColumns().size());

        int nbLines = dTable.getLines().size();
        int nbCells = dTable.getColumns().get(0).getCells().size();
        int nbSubLines = dTable.getLines().get(0).getLines().size();

        tableEditorBot.setFocus();
        tableEditorBot.bot().tree().getAllItems()[0].select();
        doLineDeletionWithDELShortcut(tableRepresentation.getTree());

        assertEquals(nbLines - 1, dTable.getLines().size());
        assertEquals("Cells of the deleted lines and sublines should be removed from cells referenced from the column.", nbCells - 1 - nbSubLines, dTable.getColumns().get(0).getCells().size());
    }

    private void doLineDeletionWithDELShortcut(SWTBotTree swtBotTree) {
        ICondition condition = new OperationDoneCondition();
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.DEL);
        bot.waitUntil(condition);
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
