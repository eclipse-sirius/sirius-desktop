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

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * STD-044 : Siriusprovides export diagram as image
 * 
 * @author amartin
 */
public class STD044 extends AbstractSTDTestCase {

    // AENRICHIR
    private static final String SESSION_FILE_044 = "STD-TEST-044.aird";

    private static final String MODEL_044 = "STD-TEST-044.ecore";

    private static final String VIEWPOINT_NAME_044 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_044 = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_044 = "RootPackage package entities";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        // TODO Auto-generated method stub
        return new String[] { SESSION_FILE_044, MODEL_044 };
    }

    /**
     * @throws Exception
     *             Test error.
     */
    public void testSTD044() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_044);

        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        SWTBotUtils.clickContextMenu(localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_044).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_044)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_044, UIDiagramRepresentation.class).getTreeItem(), "Export diagram as images");
        bot.sleep(2000);
        SWTBotShell imageShell = bot.shell("Export representations as image files");

        imageShell.activate();
        bot.button("OK").click();
        bot.waitUntil(Conditions.shellCloses(imageShell));

        localSession.closeNoDirty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "044/";
    }

}
