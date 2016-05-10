/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.table.unit.tools;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.description.CrossTableDescription;
import org.eclipse.sirius.table.metamodel.table.description.EditionTableDescription;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.IEditorPart;

/**
 * Test the good opening of table editor.
 * 
 * @author cnotot
 */
public class CreateTableWithToolWithPrecondtionTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/table/unit/tools/";

    private static final String SEMANTIC_MODEL_FILENAME = "tests.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/tools/tests.odesign";

    String VIEWPOINT_NAME = "TestTableTools";

    String EDITION_TABLE_DESC__NAME = "TestTableTools_Classes";

    String CROSS_TABLE_DESC__NAME = "TestTableTools_Columns";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH);

        initViewpoint(VIEWPOINT_NAME);
    }

    public void testOpenTableWithPreconditionOnCreateLineTool() throws Exception {
        RepresentationDescription repDescription = getRepresentationDescription(EDITION_TABLE_DESC__NAME, session.getSelectedViewpoints(false).iterator().next());
        assertTrue("No edition table description found for name " + EDITION_TABLE_DESC__NAME, repDescription instanceof EditionTableDescription);
        assertFalse("The precondition of the line creation tool should be valuated.", ((EditionTableDescription) repDescription).getAllCreateLine().get(0).getPrecondition().isEmpty());
        final DTable dTable = (DTable) createRepresentation(EDITION_TABLE_DESC__NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull("The editor has not been correctly opened", editor);

        if (editor != null) {
            // Close of the editor
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }

    public void testOpenTableWithPreconditionOnCreatColumnTool() throws Exception {
        RepresentationDescription repDescription = getRepresentationDescription(CROSS_TABLE_DESC__NAME, session.getSelectedViewpoints(false).iterator().next());
        assertTrue("No cross table description found for name " + CROSS_TABLE_DESC__NAME, repDescription instanceof CrossTableDescription);
        assertFalse("The precondition of the column creation tool should be valuated.", ((CrossTableDescription) repDescription).getCreateColumn().get(0).getPrecondition().isEmpty());

        final DTable dTable = (DTable) createRepresentation(CROSS_TABLE_DESC__NAME, semanticModel);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, dTable, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull("The editor has not been correctly opened", editor);

        if (editor != null) {
            // Close of the editor
            DialectUIManager.INSTANCE.closeEditor(editor, false);
            TestsUtil.synchronizationWithUIThread();
        }
    }
}
