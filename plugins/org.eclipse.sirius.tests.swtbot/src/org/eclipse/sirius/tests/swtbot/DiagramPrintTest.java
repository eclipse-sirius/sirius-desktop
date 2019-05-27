/*******************************************************************************
 * Copyright (c) 2014, 2019 THALES GLOBAL SERVICES and others.
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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * A test for https://bugs.eclipse.org/bugs/show_bug.cgi?id=447242, i.e. test that the GMF diagram print dialog is
 * displayed.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DiagramPrintTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String AIRD = "My.aird";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "desc";

    private static final String REPRESENTATION_NAME = "new desc";

    private static final String PATH = "/data/unit/directEdit/VP-3507/";

    private static final String ODESIGN = "My.odesign";

    private static final String SEMANTIC = "My.ecore";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC, AIRD, ODESIGN);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, AIRD);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        SWTBotUtils.waitAllUiEvents();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class, false);
    }

    /**
     * Test that GMF diagram print dialog is displayed and not the system one.
     */
    public void testGMFPrintDiagramDialogDisplay() {
        String expectedPrintDialogTitle = "Print Diagram";
        SWTBotUtils.waitAllUiEvents();
        editor.setFocus();
        bot.menu("File").menu("Print...").click();
        bot.waitUntil(Conditions.shellIsActive(expectedPrintDialogTitle));
        SWTBotShell printShell = bot.shell(expectedPrintDialogTitle);
        assertEquals(expectedPrintDialogTitle, printShell.getText());
    }

    @Override
    protected void tearDown() throws Exception {
        editor.close();
        editor = null;
        super.tearDown();
    }
}
