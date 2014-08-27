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

import java.util.List;

import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;


public class STD013 extends AbstractSTDTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/std/";

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_013 = "STD-TEST-013.aird";

    private static final String MODEL_013 = "STD-TEST-013.ecore";

    private static final String VIEWPOINT_NAME_013 = "Design";

    private static final String REPRESENTATION_NAME_TABLE_048 = "Classes";

    private static final String REPRESENTATION_INSTANCE_NAME_TABLE_013 = "new Classes";

    private static final String REPRESENTATION_NAME_DIAGRAM_013 = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_013 = "STD-TEST-013-DIAGRAMME";

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "013/";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_013, MODEL_013 };
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD013() throws Exception {

        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_013);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_013).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_013)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_013, UIDiagramRepresentation.class).open();

        assertNotNull("[Test-013]:Error the diagram couldn't be opened!", diagram);

        SWTBotSiriusDiagramEditor editordiagram = diagram.getEditor();
        editordiagram.activateTool("Class");
        editordiagram.click(100, 100);

        editordiagram.activateTool("Class");
        editordiagram.click(300, 100);

        editordiagram.activateTool("SuperType");
        // editordiagram.drag(210, 110, 60, 110);
        // sw
        editordiagram.click("new EClass 1");
        editordiagram.click("new EClass 2");
        // editordiagram.click(101, 101);
        // editordiagram.click(301, 101);

        assertNotNull("2error", editordiagram.getEditPart("new EClass 2"));
        assertNotNull("1error", editordiagram.getEditPart("new EClass 1"));

        List<SWTBotGefConnectionEditPart> listResult = editordiagram.getConnectionEditPart(editordiagram.getEditPart("new EClass 2"), editordiagram.getEditPart("new EClass 1"));

        assertEquals("There is no edge between the two class!", 1, editordiagram.getEditPart("new EClass 1").targetConnections().size());

        assertNotNull(listResult);
        assertEquals("There is no edge between the two class!", 1, listResult.size());

        editordiagram.activateTool("Class");
        editordiagram.click(300, 200);

        editordiagram.activateTool("Reference");
        editordiagram.click(60, 110);
        editordiagram.click(310, 210);

        listResult = editordiagram.getConnectionEditPart(editordiagram.getEditPart("new EClass 2"), editordiagram.getEditPart("new EClass 3"));
        assertNotNull(listResult);
        assertEquals("There is no edge between the two class!", listResult.size(), 1);

    }

}
