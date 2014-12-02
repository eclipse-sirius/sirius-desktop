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
package org.eclipse.sirius.tests.unit.table.unit.tools;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test the good opening of table editor
 * 
 * @author cnotot
 */
public class CreateLineToolTest extends SiriusDiagramTestCase {

    private static final String PATH = "/data/table/unit/tools/";

    private static final String SEMANTIC_MODEL_FILENAME = "tests.ecore";

    String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/tools/tests.odesign";

    String VIEWPOINT_NAME = "TestTableTools";

    String REPRESENTATION_DESC__NAME = "TestTableTools_Classes";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_MODEL_FILENAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME);

        genericSetUp(TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_MODEL_FILENAME, MODELER_PATH);

        initViewpoint(VIEWPOINT_NAME);
    }

    public void testOpenTableWithPreconditionOnCreateLineTool() throws Exception {
        final DTable dTable = (DTable) createRepresentation(REPRESENTATION_DESC__NAME, semanticModel);
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
