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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;

/**
 * Test the tree routing style UI
 * 
 * @author nlepine
 */
public class RoutingStyleTest extends AbstractSiriusSwtBotGefTestCase {

    public static final String STYLES = "Styles:";

    public static final String LINE_STYLE = "Line Style";

    public static final String DIAGRAM2 = "Diagram";

    public static final String APPEARANCE = "Appearance";

    public static final String PROPERTIES = "Properties";

    public static final String OBLIQUE_STYLE_ROUTING = "Oblique Style Routing";

    public static final String TREE_STYLE_ROUTING = "Tree Style Routing";

    public static final String REF = "[0..1] ref";

    // We don't need a specific diagram so we reuse an existing one.
    private static final String REPRESENTATION_INSTANCE_NAME = "p1 package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "2304.ecore";

    private static final String SESSION_FILE = "2304.aird";

    private static final String DATA_UNIT_DIR = "data/unit/routing/2304/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test the routing style UI.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRoutingStyle() throws Exception {

        // Select a diagram element named "ref"
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, REF, DEdgeEditPart.class);
        editor.reveal(REF);
        // Select all Connectors ; as there is only one edge, it works.
        editor.clickContextMenu("All Connectors");
        bot.waitUntil(cS);

        // select the routing style with the contextmenu
        editor.clickContextMenu(TREE_STYLE_ROUTING);
        editor.clickContextMenu(OBLIQUE_STYLE_ROUTING);

        // select the routing style with properties view
        bot.viewByTitle(PROPERTIES).setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE);
        new WrappedSWTBotRadio(bot.viewByTitle(PROPERTIES).bot().radioInGroup("Tree", STYLES)).click();
        new WrappedSWTBotRadio(bot.viewByTitle(PROPERTIES).bot().radioInGroup("Oblique", STYLES)).click();

        // select the routing style with the diagram menu
        bot.menu(DIAGRAM2).menu(LINE_STYLE).menu(TREE_STYLE_ROUTING);
        bot.menu(DIAGRAM2).menu(LINE_STYLE).menu(OBLIQUE_STYLE_ROUTING);

    }

    /**
     * test the tabbar routing style UI.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testTabBarRoutingStyle() throws Exception {
        // Select a diagram element named "ref"
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, REF, DEdgeEditPart.class);
        editor.reveal(REF);
        // Select all Connectors ; as there is only one edge, it works.
        editor.clickContextMenu("All Connectors");
        bot.waitUntil(cS);
        editor.bot().toolbarDropDownButtonWithTooltip(LINE_STYLE).click().menuItem(TREE_STYLE_ROUTING).click();
    }
}
