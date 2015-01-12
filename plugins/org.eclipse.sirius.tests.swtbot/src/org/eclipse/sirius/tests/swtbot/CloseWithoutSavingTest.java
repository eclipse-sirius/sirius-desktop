/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Tests that the session modifications are properly canceled when the user
 * chooses to don't save the changes.
 * 
 * @author Florian Barbin
 */
public class CloseWithoutSavingTest extends AbstractSiriusSwtBotGefTestCase implements EcoreModeler {

    private static final String MODEL = "blank.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/blankEcore/";

    private UILocalSession localSession;

    private DDiagram diagram;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        UIResource semanticModel = new UIResource(designerProject, "/", MODEL);
        localSession = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticModel).fromAlreadySelectedSemanticResource().withDefaultSessionName().finish()
                .selectViewpoints(DESIGN_VIEWPOINT_NAME);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), ENTITIES_DESC_NAME, "root" + " package entities", DDiagram.class);
        editor.save();
        diagram = (DDiagram) getRepresentationWithName(localSession.getOpenedSession(), ENTITIES_DESC_NAME, "root" + " package entities");
    }

    /**
     * This test creates multiple EPackages and make sure the restore to the
     * last save point works properly.
     */
    public void testCreateMultipleElementsAndCloseWithoutSaving() {
        assertEquals("The diagram should be empty", 0, diagram.getOwnedDiagramElements().size());
        for (int i = 0; i < 35; i++) {
            editor.activateTool("Package");
            editor.click(new Point(2, 2));
            SWTBotUtils.waitAllUiEvents();
        }

        // Closes the editor without saving.
        SWTBotSiriusHelper.close(editor, true);
        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Save"));
        final SWTBotShell uncontrolShell = bot.shell("Save");
        uncontrolShell.activate();
        SWTBotButton no = bot.button("No");
        bot.waitUntilWidgetAppears(new ItemEnabledCondition(no));
        no.click();

        SWTBotUtils.waitAllUiEvents();

        // Retrieves the diagram and makes sure it's empty.
        diagram = (DDiagram) getRepresentationWithName(localSession.getOpenedSession(), ENTITIES_DESC_NAME, "root" + " package entities");
        assertEquals("The diagram should be empty after having closed without saving.", 0, diagram.getDiagramElements().size());
    }

    @Override
    protected void tearDown() throws Exception {
        localSession = null;
        diagram = null;
        editor = null;
        super.tearDown();
    }

}
