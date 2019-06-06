/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.std;

import java.util.List;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;

/**
 * 
 * @author amartin
 */
public class STD017 extends AbstractSTDTestCase {

    private static final String SUPER_TYPE_FILTER = "Hide generalizations";

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_017 = "STD-TEST-017.aird";

    private static final String MODEL_017 = "STD-TEST-017.ecore";

    private static final String VIEWPOINT_NAME_017 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_017 = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_017 = "STD-TEST-017-DIAGRAMME";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_017, MODEL_017 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "017/";
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD017() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_017);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_017).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_017)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_017, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-017]:Error the diagram couldn't be opened!", diagram);

        SWTBotSiriusDiagramEditor editordiagram = diagram.getEditor();

        List<SWTBotGefConnectionEditPart> listResult = editordiagram.getConnectionsEditPart();// getConnectionEditPart(editordiagram.getEditPart("new EClass 2"),
                                                                                              // editordiagram.getEditPart("new EClass 1"));

        // editordiagram.getConnectionEditPart(sourceEditPart, targetEditPart)

        // List<SWTBotGefEditPart> allEditParts =
        // editordiagram.mainEditPart().children();
        // allEditParts.addAll(editordiagram.mainEditPart().sourceConnections());

        assertEquals("There should be only 1edge between the two class!", 1, listResult.size());

        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        // outlineView.layers().activateFilter(SUPER_TYPE_FILTER);
        outlineView.layers();
        outlineView.filters().activateFilter(SUPER_TYPE_FILTER);
        bot.sleep(5000);

        listResult = editordiagram.getConnectionsEditPart();

        assertEquals("There should be no edge present between the two class!", 0, listResult.size());

    }

}
