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
package org.eclipse.sirius.tests.swtbot.layout;

import java.util.List;

import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Routing;
import org.eclipse.gmf.runtime.notation.RoutingStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;

/**
 * Test class to check that edge edit part is still editable after having
 * performed a refresh.
 * 
 * @author fbarbin
 * 
 */
public class ModifyEdgeLayoutAfterRefreshTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "VP-4432.ecore";

    private static final String SESSION_FILE = "VP-4432.aird";

    private static final String DATA_UNIT_DIR = "data/unit/layout/VP-4432/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = " package entities";

    private String REF = "[0..1] ref1";

    private String REF2 = "[0..1] ref2";

    private String REF3 = "[0..1] ref3";

    private static final String TREE_STYLE_ROUTING = "Tree Style Routing";

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
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * This test checks that after having refresh all edges, we still able to
     * change their routing style. see VP-4432 for more details.
     */
    public void testChangeRoutingStyleAfterRefresh() {

        // Select a diagram element named "ref" "ref2" "ref3"
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, REF, DEdgeEditPart.class);
        CheckSelectedCondition cS2 = new CheckSelectedCondition(editor, REF2, DEdgeEditPart.class);
        CheckSelectedCondition cS3 = new CheckSelectedCondition(editor, REF3, DEdgeEditPart.class);

        // Select all Connectors
        editor.clickContextMenu("All Connectors");
        bot.waitUntil(cS);
        bot.waitUntil(cS2);
        bot.waitUntil(cS3);
        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        editor.getCanvas().pressShortcut(Keystrokes.F5);
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
        SWTBotUtils.waitAllUiEvents();

        // select the routing style with the contextmenu

        editor.clickContextMenu(TREE_STYLE_ROUTING);

        List<SWTBotGefConnectionEditPart> connections = editor.getConnectionsEditPart();

        for (SWTBotGefConnectionEditPart connection : connections) {
            Edge edge = (Edge) connection.part().getModel();
            final RoutingStyle rstyle = (RoutingStyle) edge.getStyle(NotationPackage.eINSTANCE.getRoutingStyle());
            assertEquals("The edge routing style should be a Tree", Routing.TREE_LITERAL, rstyle.getRouting());
        }

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
