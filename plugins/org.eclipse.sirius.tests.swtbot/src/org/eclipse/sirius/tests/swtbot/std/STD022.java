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
package org.eclipse.sirius.tests.swtbot.std;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.Messages;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * 
 * @author amartin
 */
public class STD022 extends AbstractSTDTestCase {

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_022 = "STD-TEST-022.aird";

    private static final String MODEL_022 = "STD-TEST-022.ecore";

    private static final String VIEWPOINT_NAME_022 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_022 = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_022 = "STD-TEST-022-DIAGRAMME";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE_022 = "new Tags";

    private static final String VIEWPOINT_NAME_022_BIS = "Review";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_022, MODEL_022 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "022/";
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD022_1() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_022);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_022).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_022)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_022, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-022]:Error the diagram couldn't be opened!", diagram);
        diagram.close();

        Set<String> vpToSelect = new HashSet<String>();
        vpToSelect.add(VIEWPOINT_NAME_022_BIS);
        localSession.changeViewpointSelection(vpToSelect, null);

        // We are expecting that when a viewpoint is activated new
        // representation are possible
        SWTBotTreeItem root = localSession.getSemanticResourceNode(sessionAirdResource);
        root.contextMenu("Siriuss Selection");

        // create diagram

        SWTBotTreeItem rootSemantic = localSession.getLocalSessionBrowser().perSemantic();
        SWTBotUtils.clickContextMenu(rootSemantic.expandNode("Root").select(), REPRESENTATION_INSTANCE_NAME_TABLE_022); // Category().selectSirius(VIEWPOINT_NAME_043)
        SWTBotShell confirmBoxDiagram = bot.shell(MessageFormat.format(Messages.createRepresentationInputDialog_Title, REPRESENTATION_NAME_DIAGRAM_022));
        SWTBotButton ok = bot.button("OK");
        bot.waitUntil(new ItemEnabledCondition(ok));
        ok.click();

        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_022_BIS).selectRepresentation("new Tags")
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_TABLE_022, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-022]:Error the diagram couldn't be opened!", diagram);
        diagram.close();

        localSession.close(false);

    }

}
