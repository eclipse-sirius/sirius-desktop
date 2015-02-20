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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test the table synchronizer.
 * 
 * @author mporhel
 */
public class DCellDeleteTest extends TableTestCase {

    private static final String INCORRECT_DATA = "The unit test data seems incorrect";

    private static final String VP_2734 = "vp-2734";

    private static final String COMMON_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/" + VP_2734 + "/" + VP_2734;

    private static final String SEMANTIC_MODEL_PATH = COMMON_PATH + ".ecore";

    private static final String MODELER_PATH = COMMON_PATH + ".odesign";

    private static final String REPRESENTATION_PATH = COMMON_PATH + ".aird";

    private IEditorPart tableEditor;

    private static DTable table;

    private static EPackage testPackage;

    @Override
    protected void init() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_PATH);
        table = (DTable) getRepresentations(VP_2734).toArray()[0];
        testPackage = (EPackage) table.getTarget();

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        tableEditor = DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * The test is here to check that table reacts to semantic changes and
     * update the corresponding cells.
     * 
     * @throws Exception
     */
    public void testDeleteLastCellInLine() throws Exception {
        final EClass c2 = (EClass) testPackage.getEClassifiers().get(2);
        DCell superTypeCell = TableHelper.getCell(table, 2, 1);

        // Check initial data
        assertEquals(INCORRECT_DATA, 1, c2.getESuperTypes().size());
        assertEquals(INCORRECT_DATA, null, c2.getEReferences().get(0).getEType());
        assertEquals(INCORRECT_DATA, c2, table.getLines().get(2).getTarget());
        assertEquals(INCORRECT_DATA, "X", superTypeCell.getLabel());

        // Modify c2 to delete the cross
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) session.getTransactionalEditingDomain()) {
            public void doExecute() {
                c2.getESuperTypes().clear();
            }
        });

        // Check modified data
        assertEquals(INCORRECT_DATA, 0, c2.getESuperTypes().size());
        assertEquals("DCell should be deleted", null, superTypeCell.getLine());
        assertEquals("DCell should be deleted", null, superTypeCell.getColumn());
        assertEquals("DCell should be the same", null, TableHelper.getCell(table, 2, 1));

        undo();

        // Check initial data
        assertEquals(INCORRECT_DATA, 1, c2.getESuperTypes().size());
        assertEquals(INCORRECT_DATA, c2, table.getLines().get(2).getTarget());
        assertEquals(INCORRECT_DATA, "X", superTypeCell.getLabel());
        assertEquals("DCell should be the same", superTypeCell, TableHelper.getCell(table, 2, 1));

        redo();

        // Check modified data
        assertEquals(INCORRECT_DATA, 0, c2.getESuperTypes().size());
        assertEquals("DCell should be deleted", null, superTypeCell.getLine());
        assertEquals("DCell should be deleted", null, superTypeCell.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 2, 1));
    }

    public void testDeleteLastDomainBasedCellInLine() throws Exception {
        final EClass c0 = (EClass) testPackage.getEClassifiers().get(0);
        final EClass c1 = (EClass) testPackage.getEClassifiers().get(1);
        DCell refType = TableHelper.getCell(table, 0, 4);

        // Check initial data
        assertEquals(INCORRECT_DATA, c1, c0.getEReferences().get(0).getEType());
        assertEquals(INCORRECT_DATA, c0, table.getLines().get(0).getTarget());
        assertEquals(INCORRECT_DATA, "X", refType.getLabel());

        // Modify c2 to delete the cross
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) session.getTransactionalEditingDomain()) {
            public void doExecute() {
                c0.getEReferences().get(0).setEType(null);
            }
        });

        // Check modified data
        assertEquals(INCORRECT_DATA, null, c0.getEReferences().get(0).getEType());
        assertEquals("DCell should be deleted", null, refType.getLine());
        assertEquals("DCell should be deleted", null, refType.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 0, 4));

        undo();

        // Check initial data
        assertEquals(INCORRECT_DATA, c1, c0.getEReferences().get(0).getEType());
        assertEquals(INCORRECT_DATA, c0, table.getLines().get(0).getTarget());
        assertEquals(INCORRECT_DATA, "X", refType.getLabel());
        assertTrue("DCell should be the same", refType.getLine() != null);
        assertTrue("DCell should be the same", refType.getColumn() != null);
        assertEquals("DCell should be the same", refType, TableHelper.getCell(table, 0, 4));

        redo();

        // Check modified data
        assertEquals(INCORRECT_DATA, null, c0.getEReferences().get(0).getEType());
        assertEquals("DCell should be deleted", null, refType.getLine());
        assertEquals("DCell should be deleted", null, refType.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 0, 4));
    }

    public void testDeleteManyCells() throws Exception {
        final EClass c1 = (EClass) testPackage.getEClassifiers().get(1);
        DCell superTypeCell = TableHelper.getCell(table, 1, 0);
        DCell superTypeCell2 = TableHelper.getCell(table, 1, 2);

        // Check initial data
        assertEquals(INCORRECT_DATA, 2, c1.getESuperTypes().size());
        assertEquals(INCORRECT_DATA, c1, table.getLines().get(1).getTarget());
        assertEquals(INCORRECT_DATA, "X", superTypeCell.getLabel());
        assertEquals(INCORRECT_DATA, "X", superTypeCell2.getLabel());

        // Modify c2 to delete the cross
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand((TransactionalEditingDomain) session.getTransactionalEditingDomain()) {
            public void doExecute() {
                c1.getESuperTypes().clear();
            }
        });

        // Check modified data
        assertEquals(INCORRECT_DATA, 0, c1.getESuperTypes().size());
        assertEquals("DCell should be deleted", null, superTypeCell.getLine());
        assertEquals("DCell should be deleted", null, superTypeCell.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 1, 0));
        assertEquals("DCell should be deleted", null, superTypeCell2.getLine());
        assertEquals("DCell should be deleted", null, superTypeCell2.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 1, 2));

        undo();

        // Check initial data
        assertEquals(INCORRECT_DATA, 2, c1.getESuperTypes().size());
        assertEquals(INCORRECT_DATA, c1, table.getLines().get(1).getTarget());
        assertEquals(INCORRECT_DATA, "X", superTypeCell.getLabel());
        assertEquals(INCORRECT_DATA, "X", superTypeCell2.getLabel());

        redo();

        // Check modified data
        assertEquals(INCORRECT_DATA, 0, c1.getESuperTypes().size());
        assertEquals("DCell should be deleted", null, superTypeCell.getLine());
        assertEquals("DCell should be deleted", null, superTypeCell.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 1, 0));
        assertEquals("DCell should be deleted", null, superTypeCell2.getLine());
        assertEquals("DCell should be deleted", null, superTypeCell2.getColumn());
        assertEquals("DCell should be deleted", null, TableHelper.getCell(table, 1, 2));
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(tableEditor, false);
        TestsUtil.synchronizationWithUIThread();
        tableEditor = null;
        table = null;
        testPackage = null;

        super.tearDown();
    }

}
