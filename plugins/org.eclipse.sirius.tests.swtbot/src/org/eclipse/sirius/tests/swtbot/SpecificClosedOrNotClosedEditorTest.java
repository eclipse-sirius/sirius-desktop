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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.gef.EditPart;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test that if deleted target element present in other representation opened,
 * the other representation is closed. Testing VP-1853 and VP-1854.
 * 
 * @author jdupont
 */
public class SpecificClosedOrNotClosedEditorTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "testclosingotherdiagram";

    private static final String VIEWPOINT_NAME_1854 = "VP-1854";

    private static final String REPRESENTATION_INSTANCE_NAME_1854_1 = "p1 package entities";

    private static final String REPRESENTATION_INSTANCE_NAME_1854_2 = "new VP-1854";

    private static final String REPRESENTATION_INSTANCE_NAME1 = "new diag1";

    private static final String REPRESENTATION_INSTANCE_NAME2 = "new diag2";

    private static final String EDITOR_NAME_2 = REPRESENTATION_INSTANCE_NAME2 + "(EClass AAA)";

    private static final String REPRESENTATION_NAME_1854_1 = "Entities";

    private static final String REPRESENTATION_NAME_1854_2 = "VP-1854";

    private static final String REPRESENTATION_NAME1 = "diag1";

    private static final String REPRESENTATION_NAME2 = "diag2";

    private static final String MODEL = "My.ecore";

    private static final String VSM = "My.odesign";

    private static final String SESSION_FILE = "My.aird";

    private static final String MODEL_1854 = "1854.ecore";

    private static final String VSM_1854 = "vp-1854.odesign";

    private static final String SESSION_FILE_1854 = "1854.aird";

    private static final String DATA_UNIT_DIR = "data/unit/closeEditorSpecificTest/";

    private static final String FILE_DIR = "/";

    private SWTBotSiriusDiagramEditor editor;

    private UIResource sessionAirdResource;

    private UIResource sessionAirdResource1854;

    private UILocalSession localSession;

    private UILocalSession localSession1854;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL_1854, SESSION_FILE, SESSION_FILE_1854, VSM, VSM_1854);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        sessionAirdResource1854 = new UIResource(designerProject, FILE_DIR, SESSION_FILE_1854);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        localSession1854 = designerPerspective.openSessionFromFile(sessionAirdResource1854, true);
    }

    /**
     * Test the deleted no-target element on representation, not closed other
     * editor.
     */
    public void testSpecificNotCloseEditor() {
        // Open the 2 representations
        openDiagram(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME2, REPRESENTATION_INSTANCE_NAME2);
        openDiagram(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME1, REPRESENTATION_INSTANCE_NAME1);

        // Selected the edge to remove
        SWTBotGefEditPart selectedElement = selectAndCheckEditPart("", AbstractDiagramEdgeEditPart.class);
        DEdgeEditPart edgeSelected = (DEdgeEditPart) selectedElement.part();
        assertEquals("The selected edge is not correct", (((DNodeEditPart) ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)).getSourceConnections().get(0)),
                edgeSelected);
        editor.select(selectedElement);
        // Remove the edge
        editor.clickContextMenu("Delete from Model");
        // Check that edge was removed.
        assertTrue("The edge is not deleted", ((DNodeEditPart) ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)).getSourceConnections().size() == 0);
        // Check that the second editor is always open.
        assertEquals("The second editor was closed", EDITOR_NAME_2, ((SWTBotEditor) bot.editors().get(0)).getReference().getPartName());
    }

    /**
     * Test the deleted target element on representation, not closed other
     * editor.
     */
    public void testSpecificCloseEditor() {
        // Open the 2 representations
        openDiagram(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME2, REPRESENTATION_INSTANCE_NAME2);
        openDiagram(localSession, VIEWPOINT_NAME, REPRESENTATION_NAME1, REPRESENTATION_INSTANCE_NAME1);
        // Selected the node to remove
        SWTBotGefEditPart selectedElement = selectAndCheckEditPart("AAA", DNodeEditPart.class);
        DNodeEditPart nodeSelected = (DNodeEditPart) selectedElement.part();
        assertEquals("The selected edge is not correct", (((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(0)), nodeSelected);
        editor.select(selectedElement);
        // Remove the node
        editor.clickContextMenu("Delete from Model");
        // Check that node was removed.
        assertTrue("The node is not deleted", ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().size() == 1);
        // Check that the second editor is closed.
        assertTrue("The second editor was not closed", bot.editors().size() == 1);
    }

    /**
     * Test that UnGroup packages working properly and that the second editor is
     * closed because the package p1 was deleted.
     */
    public void testUnGroupPackages() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        // Open the 2 representations
        openDiagram(localSession1854, "Design", REPRESENTATION_NAME_1854_1, REPRESENTATION_INSTANCE_NAME_1854_1);
        openDiagram(localSession1854, VIEWPOINT_NAME_1854, REPRESENTATION_NAME_1854_2, REPRESENTATION_INSTANCE_NAME_1854_2);
        // Selected the package
        SWTBotGefEditPart selectedElement = selectAndCheckEditPart("p1", DNodeContainerEditPart.class);
        DNodeContainerEditPart containerSelected = (DNodeContainerEditPart) selectedElement.part();
        assertEquals("The selected container is not correct", (((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().get(2)), containerSelected);
        editor.select(selectedElement);
        // UnGroup the package (move class in p1 to root and remove p1)
        editor.clickContextMenu("Ungroup classes");
        // The refresh is not effective, that why we select the diagram for
        // refresh manually
        editor.click(200, 200);
        editor.refresh();
        // Check that package was removed.
        assertTrue("The package is not deleted", ((DDiagramEditPart) editor.rootEditPart().part().getChildren().get(0)).getChildren().size() == 4);
        // Check that the second editor is closed.
        assertEquals("The second editor is always opened", 1, bot.editors().size());
    }

    /**
     * Return the selected edit part.
     * 
     * @param name
     * @param type
     * @return the selected edit part
     */
    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);

        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();

        return botPart;
    }

    private void openDiagram(UILocalSession localSession, String viewpointName, String representationName, String representationInstanceName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, representationInstanceName, DDiagram.class);
    }
}
