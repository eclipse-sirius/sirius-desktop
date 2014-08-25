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

import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * 
 * @author amartin
 */
public class STD018 extends AbstractSTDTestCase {

    private static final String FILE_DIR = "/";

    private static final String SESSION_FILE_018 = "STD-TEST-018.aird";

    private static final String MODEL_018 = "STD-TEST-018.ecore";

    private static final String VIEWPOINT_NAME_018 = "Design";

    private static final String REPRESENTATION_NAME_DIAGRAM_018 = "Entities";

    private static final String REPRESENTATION_INSTANCE_NAME_DIAGRAM_018 = "STD-TEST-018-DIAGRAMME";

    private static final String PACKAGE_LAYER = "Package";

    private static final String DYNAMIC_LAYER = "Dynamic";

    /**
     * {@inheritDoc}
     */
    @Override
    String[] getFilesUsedForTest() {
        return new String[] { SESSION_FILE_018, MODEL_018 };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    String getSTDDiretory() {
        return "018/";
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
    }

    /**
     * @throws Exception
     *             if the test fails
     */
    public void testSTD018() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_018);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final UIDiagramRepresentation diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME_018).selectRepresentation(REPRESENTATION_NAME_DIAGRAM_018)
                .selectRepresentationInstance(REPRESENTATION_INSTANCE_NAME_DIAGRAM_018, UIDiagramRepresentation.class).open();

        assertNotNull("Test-018]:Error the diagram couldn't be opened!", diagram);
        SWTBotSiriusDiagramEditor editordiagram = diagram.getEditor();

        // We are expecting that when a layer is activate new form appears/ or
        // form disappear according to the layer parameter.

        List<SWTBotGefEditPart> allEditParts = editordiagram.mainEditPart().children();
        allEditParts.addAll(editordiagram.mainEditPart().sourceConnections());

        assertEquals("There should be only 2 nodes!", 2, allEditParts.size());

        diagram.changeLayerActivation(PACKAGE_LAYER);

        bot.sleep(5000);
        editordiagram.refresh();
        allEditParts = editordiagram.mainEditPart().children();
        allEditParts.addAll(editordiagram.mainEditPart().sourceConnections());

        assertEquals("There should be now 5 nodes!", 5, allEditParts.size());

        // Many layers could be activated at the same time.
        diagram.changeLayerActivation(DYNAMIC_LAYER);
        localSession.close(false);

    }

}
