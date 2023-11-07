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
package org.eclipse.sirius.tests.swtbot.tabbar;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;

/**
 * Test that check that tools does not become invisible in toolbar after the
 * first use.
 * 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=442847
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=439338
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public class NotInvisibleTabBarTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String VSM_FILE = "tc2216.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String SELECTED_REFERENCE = "[0..1] ref0";

    private static final String SELECTED_PACKAGE = "0";

    private static final String LINE_STYLE = "Line Style";

    private static final String RECTILINEAR_STYLE_ROUTING = "Rectilinear Style Routing";

    private static final String CHANGE_TO_RECTILINEAR_STYLE_ROUTING = "Change routing to rectilinear style for connectors";

    private static final String ARRANGE_ALL = "Arrange All";

    private static final String ARRANGE_SELECTION = "Arrange Selection";

    private static final String SELECT_ALL = "Select &All";

    private static final String ALIGN_LEFT = "Align Left";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Open the entity diagram
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "aaaa package entities", DDiagram.class);
    }

    /**
     * Select the edge element named "ref0"
     */
    private void selectEdgeElement() {
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, SELECTED_REFERENCE, DEdgeEditPart.class);
        editor.reveal(SELECTED_REFERENCE);

        // Select all Connectors ; as there is only one edge, it works.
        editor.clickContextMenu("All Connectors");
        bot.waitUntil(cs);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Select the package element named "0"
     */
    private void selectPackageElement() {
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, SELECTED_PACKAGE, AbstractDiagramContainerEditPart.class);
        editor.reveal(SELECTED_PACKAGE);
        editor.click(SELECTED_PACKAGE);
        bot.waitUntil(cs);
    }

    /**
     * Select the diagram
     */
    private void selectDiagram() {
        SWTBotGefEditPart diagPart = editor.rootEditPart().children().iterator().next();
        IDDiagramEditPart part = (IDDiagramEditPart) diagPart.part();
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, part);
        editor.select(diagPart);
        bot.waitUntil(cs);

        // Wait for tabbar refresh
        // Should be removed when tabbar will be rewritten.
        bot.sleep(1000);
    }

    /**
     * Loose and retrieve the focus
     */
    private void looseAndRetrieveTheFocus() {
        // loose the focus (set focus to the session brower)
        localSession.getLocalSessionBrowser().getTreeItem().setFocus();
        SWTBotUtils.waitAllUiEvents();

        // retrieve the focus
        editor.setFocus();
        editor.click(300, 300);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Check that the button and one menu item are enabled. Click on the menu
     * item
     * 
     * @param dropDownButton
     *            drop down button
     * @param menuItem
     *            menu item
     */
    private void checkEnabledDropDownButton(String dropDownButton, String menuItem) {
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarDropDownButton button = bot.toolbarDropDownButtonWithTooltip(dropDownButton);
        assertEnabled(button);
        SWTBotMenu menu = button.menuItem(menuItem);
        assertEnabled(menu);
        menu.click();
        SWTBotUtils.waitAllUiEvents();
        menu.pressShortcut(Keystrokes.ESC);
    }

    /**
     * Check that the button is enabled and click on it.
     * 
     * @param dropDownButton
     *            drop down button
     */
    private void checkEnabledDropDownButton(String dropDownButton) {
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarDropDownButton button = bot.toolbarDropDownButtonWithTooltip(dropDownButton);
        assertEnabled(button);
        button.click();
    }

    /**
     * Test that check that tools does not become invisible in toolbar after the
     * first use.
     * 
     * Checks "Line Style", "Arrange All", "Arrange Selection", "Select All" and
     * "Align Left"
     * 
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=442847
     * https://bugs.eclipse.org/bugs/show_bug.cgi?id=439338
     */
    public void testTabbarButtonsNotInvisible() {

        // line style
        selectEdgeElement();
        checkEnabledDropDownButton(LINE_STYLE, RECTILINEAR_STYLE_ROUTING);
        looseAndRetrieveTheFocus();
        selectEdgeElement();
        // the tooltip has changed
        checkEnabledDropDownButton(CHANGE_TO_RECTILINEAR_STYLE_ROUTING, RECTILINEAR_STYLE_ROUTING);

        // arrange all
        selectDiagram();
        checkEnabledDropDownButton(ARRANGE_ALL);
        looseAndRetrieveTheFocus();
        selectDiagram();
        checkEnabledDropDownButton(ARRANGE_ALL);

        // arrange selection
        selectPackageElement();
        checkEnabledDropDownButton(ARRANGE_SELECTION, ARRANGE_SELECTION);
        looseAndRetrieveTheFocus();
        selectPackageElement();
        checkEnabledDropDownButton(ARRANGE_SELECTION, ARRANGE_SELECTION);

        // select all
        selectDiagram();
        checkEnabledDropDownButton(SELECT_ALL);
        looseAndRetrieveTheFocus();
        selectDiagram();
        checkEnabledDropDownButton(SELECT_ALL);

        // align left
        selectDiagram();
        checkEnabledDropDownButton(SELECT_ALL);
        checkEnabledDropDownButton(ALIGN_LEFT);
        looseAndRetrieveTheFocus();
        selectDiagram();
        checkEnabledDropDownButton(SELECT_ALL);
        checkEnabledDropDownButton(ALIGN_LEFT);
    }
}
