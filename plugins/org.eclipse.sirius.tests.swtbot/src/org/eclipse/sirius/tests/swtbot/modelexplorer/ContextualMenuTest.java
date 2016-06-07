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
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test class for context menu computation in Model Explorer (See VP-3485).
 * 
 * @author mporhel
 */
public class ContextualMenuTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DELETE_MENU_LABEL = "Delete";

    private static final String MODEL_FILE = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "ticket2298.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeCreation/";

    private static final String REPRESENTATION_DESC_2298_NAME = "Diag2298";

    private static final String TEST_PACKAGE = "test";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Test that there is no "Delete" menu on semantic elements in Model
     * Explorer.
     */
    public void test_No_Delete_Menu_On_Semantic() {
        // Get a semantic element.
        SWTBotTreeItem testPackageNode = checkAndSelect(localSession.getLocalSessionBrowser().perSemantic().expandNode(TEST_PACKAGE), EPackage.class);
        assertFalse("Delete menu should not be present on semantic element.", SWTBotUtils.hasContextMenu(testPackageNode, DELETE_MENU_LABEL));
    }

    /**
     * Test that there is a "Delete" menu on representation in Model Explorer.
     */
    public void test_Delete_Menu_On_DRepresentation() {
        // Get a representation.
        SWTBotTreeItem diagram = checkAndSelect(localSession.getLocalSessionBrowser().perSemantic().expandNode(TEST_PACKAGE).expandNode(REPRESENTATION_DESC_2298_NAME),
                DRepresentationDescriptor.class);

        assertTrue("Delete menu should be present on the DRepresentationDescriptor corresponding to the DDiagram.", SWTBotUtils.hasContextMenu(diagram, DELETE_MENU_LABEL));
        assertTrue("Delete menu should be present on the DRepresentationDescriptor corresponding to the DDiagram.", SWTBotUtils.isContextMenuEnabled(diagram, DELETE_MENU_LABEL));
    }

    private SWTBotTreeItem checkAndSelect(final SWTBotTreeItem item, Class<?> dataType) {
        final Object data = UIThreadRunnable.syncExec(new Result<Object>() {
            /** {@inheritDoc} */
            @Override
            public Object run() {
                return item.widget.getData();
            }
        });
        assertTrue(dataType.isInstance(data));

        return item.select();
    }
}
