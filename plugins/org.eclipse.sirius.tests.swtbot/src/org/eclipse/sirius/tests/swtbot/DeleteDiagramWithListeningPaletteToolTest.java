/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that there is no NPE on diagram deletion when one of the palette tools
 * listens on it.
 *
 * See bugzilla 444728 for more details.
 *
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
public class DeleteDiagramWithListeningPaletteToolTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/delete/bugzilla-444728/";

    private static final String SESSION_FILE = "toolFilterNPE.aird";

    private static final String MODEL_FILE = "toolFilterNPE.ecore";

    private static final String VSM_FILE = "toolFilterNPE.odesign";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM = "new toolFilterNPE_Diagram";

    private static final String TEST_PACKAGE = "p0";

    private static final String DELETE_MENU_LABEL = "Delete";

    private static final String OPEN_MENU_LABEL = "Open";

    private static final String OK = "OK";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SESSION_FILE, MODEL_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Delete an opened diagram from the model explorer.
     */
    public void testDeleteDiagramFromModelExplorer() {
        // Step 1: get a representation.
        SWTBotTreeItem diagram = localSession.getLocalSessionBrowser().perSemantic().expandNode(TEST_PACKAGE).expandNode(REPRESENTATION_INSTANCE_NAME_DIAGRAM).select();

        // Step 2: open the representation
        SWTBotUtils.clickContextMenu(diagram, OPEN_MENU_LABEL);
        SWTBotUtils.waitAllUiEvents();

        // Step 3: delete the representation
        SWTBotUtils.clickContextMenu(diagram, DELETE_MENU_LABEL);
        bot.waitUntil(Conditions.shellIsActive("Delete representation"));
        bot.button(OK).click();
        SWTBotUtils.waitAllUiEvents();

        // Result: no error occurs (no NPE)
        assertFalse(platformProblemsListener.getErrorLoggersMessage(),platformProblemsListener.doesAnErrorOccurs());
    }
}
