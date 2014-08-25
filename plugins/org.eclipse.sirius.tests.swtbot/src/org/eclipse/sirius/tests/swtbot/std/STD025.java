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
package org.eclipse.sirius.tests.swtbot.std;

import org.eclipse.sirius.tests.swtbot.support.api.bot.description.GroupBot;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * 
 * @author amartin
 */
public class STD025 extends AbstractSTDTestCase {

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_025 = "STD-TEST-025.aird";

    private static final String MODEL_025 = "STD-TEST-025.ecore";

    private static final String ODESIGN_025 = "STD-TEST-025.odesign";

    private static final String VIEWPOINT_NAME_025 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_025 = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_025 = "STD-TEST-025-DIAGRAMME";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE_025 = "new Tags";

    private static final String VIEWPOINT_NAME_025_BIS = "Review";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { MODEL_025 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "025/";
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD025_1() throws Exception {

        bot.sleep(500);
        bot.menu("File").menu("Other...").click();
        bot.waitUntil(Conditions.shellIsActive("New"));
        SWTBotShell shell = bot.shell("New");
        shell.activate();
        SWTBotTree tree = bot.tree();
        tree.expandNode("Sirius").select("Viewpoint Specification Model").click();
        // bot.tree().getTreeItem(
        // shell.setFocus();
        bot.sleep(500);
        bot.button("Next >").click();

        tree = bot.tree();
        tree.expandNode(getProjectName()).select();// getNode("025");
        bot.sleep(5000);
        bot.textWithLabel("File na&me:").setText(ODESIGN_025);
        bot.button("Finish").click();
        bot.sleep(5000);

        // SiriusPropertiesView viewproperties =
        // designerViews.openPropertiesView();
        // GroupBot group
        SWTBotVSMEditor odesignEditor = SWTBotVSMHelper.getVSMEditorContainingName(ODESIGN_025);
        odesignEditor.setFocus();
        GroupBot group = odesignEditor.getGroup();
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void WAITINGtestSTD025_2() throws Exception {
        bot.menu("File").menu("Other...").click();
        bot.shell("New").activate();
        bot.table().select("Ecore Model");
        bot.button("Next").click();


        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_025);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_025).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_025)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_025, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-025]:Error the diagram couldn't be opened!", diagram);
        diagram.close();
        localSession.close(false);

    }

}
