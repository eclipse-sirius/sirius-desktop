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
package org.eclipse.sirius.tests.unit.table.unit.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.table.business.api.helper.TableHelper;
import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.table.ui.tools.internal.editor.DTableEditionEditor;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorPart;

/**
 * Tests for the <code>featureParentExpression</code> support on feature column
 * mappings.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class InvalidParentExpressionTest extends SiriusTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/vp-2673/fixture.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/vp-2673/invalid_parent_expression.odesign";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/table/unit/vp-2673/fixture.aird";

    private ITableCommandFactory tableCommandFactory;

    /**
     * @return the tableCommandFactory
     */
    @Override
    protected ITableCommandFactory getCommandFactory() {
        if (tableCommandFactory == null) {
            tableCommandFactory = TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(session.getTransactionalEditingDomain());
        }
        return tableCommandFactory;
    }

    /**
     * Tests that for cells for which the column's featureParentExpression is
     * null or false, we do not show anything but a blank cell. See VP-2673.
     * 
     * @throws Exception
     *             if an error occurs.
     */
    public void testCellWithInvalidFeatureParentExpressionIsBlank() throws Exception {
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        // The test model contains a single view with a single representation.
        DTable table = (DTable) new DViewQuery(session.getOwnedViews().iterator().next()).getLoadedRepresentations().get(0);
        refresh(table);
        IEditorPart editor = DialectUIManager.INSTANCE.openEditor(session, table, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // Get the cell for A0 / InheritsFromAbstract: is should be completely
        // blank.
        // First, check the model part
        DCell cell1 = TableHelper.getCell(table, 0, 0);
        assertNull("The cell (0,0) should not exist.", cell1);
        // Next, also check the pure-UI level: no text, no icon.
        String uiText1 = ((DTableEditionEditor) editor).getTableViewer().getTreeViewer().getTree().getItems()[0].getText(1);
        assertEquals("", uiText1);
        Image icon1 = ((DTableEditionEditor) editor).getTableViewer().getTreeViewer().getTree().getItems()[0].getImage(1);
        assertNull(icon1);

        // Get the cell for B0 / InheritsFromAbstract: is should have a checked
        // box and "true" as label
        // First, check the model part
        DCell cell2 = TableHelper.getCell(table, 1, 0);
        assertNotNull(cell2);
        assertEquals("true", cell2.getLabel());
        // Next, also check the pure-UI level: no text, no icon.
        String uiText2 = ((DTableEditionEditor) editor).getTableViewer().getTreeViewer().getTree().getItems()[1].getText(1);
        assertEquals("true", uiText2);
        Image icon2 = ((DTableEditionEditor) editor).getTableViewer().getTreeViewer().getTree().getItems()[1].getImage(1);
        assertNotNull(icon2);
    }
}
