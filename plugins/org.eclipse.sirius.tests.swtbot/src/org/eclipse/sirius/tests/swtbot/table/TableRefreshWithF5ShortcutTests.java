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

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.metamodel.table.DLine;
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
 * Test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=458977 .
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class TableRefreshWithF5ShortcutTests extends AbstractTreeSiriusSWTBotGefTestCase {

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

    private DLine firstLine;

    private DLine secondLine;

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
        firstLine = dTable.getLines().get(0);
        secondLine = dTable.getLines().get(1);
    }

    /**
     * Test table global refresh with F5 shortcut.
     */
    public void testTableGlobalRefreshWithF5Shortcut() {
        int nbSubLinesOfFirstLine = firstLine.getLines().size();
        int nbSubLinesOfSecondLine = secondLine.getLines().size();
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        Command cmd = AddCommand.create(domain, firstLine.getTarget(), EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, EcoreFactory.eINSTANCE.createEAttribute());
        domain.getCommandStack().execute(cmd);
        cmd = AddCommand.create(domain, secondLine.getTarget(), EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES, EcoreFactory.eINSTANCE.createEAttribute());
        domain.getCommandStack().execute(cmd);

        assertEquals(nbSubLinesOfFirstLine, firstLine.getLines().size());
        assertEquals(nbSubLinesOfSecondLine, secondLine.getLines().size());

        tableEditorBot.setFocus();
        launchRefreshWithF5Shortcut(tableRepresentation.getTree());

        assertEquals(nbSubLinesOfFirstLine + 1, firstLine.getLines().size());
        assertEquals(nbSubLinesOfSecondLine + 1, secondLine.getLines().size());
    }

    private void launchRefreshWithF5Shortcut(SWTBotTree swtBotTree) {
        ICondition condition = new OperationDoneCondition();
        SWTBotUtils.pressKeyboardKey(swtBotTree.widget, SWT.F5);
        bot.waitUntil(condition);
    }

    @Override
    protected void tearDown() throws Exception {
        tableEditorBot.close();
        tableEditorBot = null;
        tableRepresentation = null;
        dTable = null;
        firstLine = null;
        secondLine = null;

        super.tearDown();
    }

}
