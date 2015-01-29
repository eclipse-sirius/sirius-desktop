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
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.table.unit.common.TableTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test that on opening a table with feature parent expression informed is not
 * in dirty. Test VP-2726.
 * 
 * @author <a href="mailto:julien.dupont@obeo.fr">Julien DUPONT</a>
 * 
 */
public class TableNotDirtyOnOpeningTest extends TableTestCase {

    private static final String SEMANTIC_RESOURCE_NAME = "My.ecore";

    private static final String SESSION_RESOURCE_NAME = "representations.aird";

    private static final String MODELER_RESOURCE_NAME = "My.odesign";

    private static final String TABLE_DESCRIPTION_ID = "Table";

    private AbstractDTableEditor tableEditor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SESSION_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);

        String SEMANTIC_RESOURCE_PATH = TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME;
        String SESSION_RESOURCE_PATH = TEMPORARY_PROJECT_NAME + "/" + SESSION_RESOURCE_NAME;
        String MODELER_RESOURCE_PATH = TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME;

        genericSetUp(SEMANTIC_RESOURCE_PATH, MODELER_RESOURCE_PATH, SESSION_RESOURCE_PATH);

    }

    /**
     * Test editor not opening in dirty.
     */
    public void testRefreshFeatureColumn() {

        final TableDescription tableDescription = find(TABLE_DESCRIPTION_ID);
        assertNotNull(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, tableDescription);
        assertEquals(TableTestCase.THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, tableDescription.getAllLineMappings().size());

        DTable newTable = (DTable) getRepresentations(TABLE_DESCRIPTION_ID).toArray()[0];
        IEditorPart openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        // Test editor is not dirty
        assertEquals("The representation shouldn't dirty", false, tableEditor.isDirty());
        DialectUIManager.INSTANCE.closeEditor(tableEditor, true);
        openedEditor = DialectUIManager.INSTANCE.openEditor(session, newTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        tableEditor = (AbstractDTableEditor) openedEditor;
        // Test editor is not dirty
        assertEquals("The representation shouldn't dirty", false, tableEditor.isDirty());

    }

}
