/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.hamcrest.Matcher;

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
     * Test the routing style UI with a selection containing only one edge.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRoutingStyleWithOneEdgeSelected() throws Exception {
        // Use "All Connectors" contextual menu; as there is only one edge, it works.
        changeRoutingStyle("All Connectors", true);
    }

    /**
     * Test the routing style UI with a selection that does not contain only edges.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRoutingStyleWithMultiSelection() throws Exception {
        // Use "All" contextual menu to select all elements of the diagram.
        changeRoutingStyle("All", false);
    }

    private void changeRoutingStyle(String contextualMenuName, boolean checkPropertiesView) {
        // Reveal the edge named "ref"
        editor.reveal(REF);
        // Select elements and ensure that at least the edge named "ref" is
        // selected
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, REF, DEdgeEditPart.class);
        editor.clickContextMenu(contextualMenuName);
        bot.waitUntil(cS);

        // select the routing style with the contextual menu
        editor.clickContextMenu(TREE_STYLE_ROUTING);
        editor.clickContextMenu(OBLIQUE_STYLE_ROUTING);

        // select the routing style from tabbar
        SWTBotToolbarDropDownButton button = editor.bot().toolbarDropDownButtonWithTooltip(LINE_STYLE);
        Matcher<MenuItem> withName = WidgetMatcherFactory.withText("&" + TREE_STYLE_ROUTING);
        SWTBotMenu treeRoutingStyleButton = button.menuItem(withName);
        treeRoutingStyleButton.click();

        if (checkPropertiesView) {
            // select the routing style with properties view
            SWTBotView propertiesView = bot.viewByTitle(PROPERTIES);
            propertiesView.setFocus();
            SWTBotSiriusHelper.selectPropertyTabItem(APPEARANCE, propertiesView.bot());
            new WrappedSWTBotRadio(propertiesView.bot().radioInGroup("Tree", STYLES)).click();
            new WrappedSWTBotRadio(propertiesView.bot().radioInGroup("Oblique", STYLES)).click();
        }

        // select the routing style with the diagram menu
        bot.menu(DIAGRAM2).menu(LINE_STYLE).menu(TREE_STYLE_ROUTING);
        bot.menu(DIAGRAM2).menu(LINE_STYLE).menu(OBLIQUE_STYLE_ROUTING);
    }
}
