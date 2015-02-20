/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test the table synchronizer.
 * 
 * @author mporhel
 */
public class CrossReferencedDLineDeleteTest extends SiriusTestCase {

    private static final String INCORRECT_DATA = "The unit test data seems incorrect";

    private static final String VP_2759 = "vp-2759";

    private static final String COMMON_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/" + VP_2759 + "/" + VP_2759;

    private static final String SEMANTIC_MODEL_PATH = COMMON_PATH + ".ecore";

    private static final String MODELER_PATH = COMMON_PATH + ".odesign";

    private static final String REPRESENTATION_PATH = COMMON_PATH + ".aird";

    private ITableCommandFactory tableCommandFactory;

    private IEditorPart tableEditor;

    private static DTable table;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_PATH);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        table = (DTable) getRepresentations(VP_2759).toArray()[0];
        tableEditor = DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * The test is here to check that table reacts to semantic changes and
     * update the corresponding cells.
     * 
     * The issue was that elements with semantic candidates expression
     * containing .~, were still found and so not updated.
     * 
     * @throws Exception
     */
    public void testDeleteSemanticDeleteCrossReferencedLine() throws Exception {
        EClass c = (EClass) table.getTarget();
        final EReference ref1 = c.getEReferences().get(0);
        DLine mainLine = table.getLines().get(0);

        // Check initial data
        assertEquals(INCORRECT_DATA, 2, c.getEReferences().size());
        assertEquals(INCORRECT_DATA, 1, c.getEAttributes().size());
        assertEquals(INCORRECT_DATA, 1, table.getLines().size());
        assertEquals(INCORRECT_DATA, 2, mainLine.getLines().size());

        // Modify modify the model to change .~eKeys result
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) session.getTransactionalEditingDomain()) {
            public void doExecute() {
                ref1.getEKeys().clear();
            }
        });
        // Refresh the table : with clic remove line, the refresh will not be
        // needed.

        // Check modified data
        assertEquals(INCORRECT_DATA, 2, c.getEReferences().size());
        assertEquals(INCORRECT_DATA, 1, c.getEAttributes().size());
        assertEquals(INCORRECT_DATA, 1, table.getLines().size());
        assertEquals("A cross referenced sub line should have been deleted", 1, mainLine.getLines().size());
        assertEquals("The wrong line was deleted", c.getEReferences().get(1).getName(), TableHelper.getCell(mainLine.getLines().get(0), table.getColumns().get(0)).get().getLabel());

        // Undo/redo to be sure that the cross referencer adapter is able to
        // retrieved undeleted elements.

        undo();

        // Check initial data
        assertEquals(INCORRECT_DATA, 2, c.getEReferences().size());
        assertEquals(INCORRECT_DATA, 1, c.getEAttributes().size());
        assertEquals(INCORRECT_DATA, 1, table.getLines().size());
        assertEquals(INCORRECT_DATA, 2, mainLine.getLines().size());

        redo();

        // Check modified data
        assertEquals(INCORRECT_DATA, 2, c.getEReferences().size());
        assertEquals(INCORRECT_DATA, 1, c.getEAttributes().size());
        assertEquals(INCORRECT_DATA, 1, table.getLines().size());
        assertEquals("A cross referenced sub line should have been deleted", 1, mainLine.getLines().size());
        assertEquals("The wrong line was deleted", c.getEReferences().get(1).getName(), TableHelper.getCell(mainLine.getLines().get(0), table.getColumns().get(0)).get().getLabel());
    }

    /**
     * @return the tableCommandFactory
     */
    protected ITableCommandFactory getCommandFactory() {
        if (tableCommandFactory == null) {
            tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        }
        return tableCommandFactory;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
        tableCommandFactory = null;
        tableEditor = null;
        table = null;
        super.tearDown();
    }

}
