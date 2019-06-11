/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;

/**
 * 
 * @author smonnier
 */
public class DiagramDocumentationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new Entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "2100.ecore";

    private static final String SESSION_FILE = "2100.aird";

    private static final String DATA_UNIT_DIR = "data/unit/documentation/2100/";

    private static final String FILE_DIR = "/";

    private static final String EXPECTED_DOCUMENTATION = "This is the comments of my Class diagram.\n\nIt is displayed on many lines.";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testDiagramDocumentation() throws Exception {
        // Eclipse 4.x setFocus (an element is on (0,0))
        editor.click(100, 100);
        SWTBotUtils.waitAllUiEvents();
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBotSiriusHelper.selectPropertyTabItem("Documentation", propertiesView.bot());

        SWTBotUtils.waitAllUiEvents();
        propertiesView.setFocus();
        SWTBot propertiesBot = propertiesView.bot();

        assertEquals("The content of the documentation is not as expected", EXPECTED_DOCUMENTATION, propertiesBot.styledText().getText());
    }

}
