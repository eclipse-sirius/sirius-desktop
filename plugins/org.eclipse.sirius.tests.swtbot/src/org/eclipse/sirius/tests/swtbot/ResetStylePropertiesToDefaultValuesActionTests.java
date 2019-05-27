/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

/**
 * Test that the action "Reset style properties to default values" is refreshed in tabbar when style is changed. Test
 * VP-3449.
 * 
 * @author jdupont
 */
public class ResetStylePropertiesToDefaultValuesActionTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String DATA_UNIT_DIR = "data/unit/style/customizations/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "root package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String C1 = "C1";

    private static final String P1 = "newPackage1";

    private static final String REF1 = "[0..1] newEReference1";

    private String oldDefaultFontName;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // Set the default fontName to have tests on
        // "Reset style properties to default values" button works.
        oldDefaultFontName = changeDefaultFontName("Times New Roman");

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE);
    }

    @Override
    protected void tearDown() throws Exception {
        // Reset the default fontName
        changeDefaultFontName(oldDefaultFontName);
        super.tearDown();
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Fill Color in tabbar for a list
     * container.
     */
    public void testRefreshActionCancelCustomStyleTabbarForListContainer() {
        selectAndCheckEditPart(C1, AbstractDiagramListEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Change the background color with the tabbar
        editor.bot().toolbarDropDownButtonWithTooltip("Fill &Color").menuItem("Yellow").click();
        SWTBotUtils.waitAllUiEvents();
        // Check that the "Reset style properties to default values" button is
        // enabled and click on
        // it.
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Fill Color in tabbar for a
     * Container.
     */
    public void testRefreshActionCancelCustomStyleTabbarForContainer() {
        selectAndCheckEditPart(P1, AbstractDiagramContainerEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Change the background color with the tabbar
        editor.bot().toolbarDropDownButtonWithTooltip("Fill &Color").menuItem("Yellow").click();
        SWTBotUtils.waitAllUiEvents();
        // Check that the "Reset style properties to default values" button is
        // enabled and click on
        // it.
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on bold button in tabbar for an edge.
     */
    public void _testRefreshActionCancelCustomStyleTabbarForEdgeWithBoldFontStyle() {
        selectAndCheckEditPart(REF1, AbstractDiagramEdgeEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Change the line color with the tabbar
        SWTBotToolbarToggleButton boldButtonBot = editor.bot().toolbarToggleButtonWithTooltip("Bold Font Style");
        boldButtonBot.click();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("The bold button should be toggled", boldButtonBot.isChecked());
        // Check that the "Reset style properties to default values" button is
        // enabled and click on
        // it.
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        assertFalse("The bold button should not be toggled", boldButtonBot.isChecked());
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Line Color in tabbar for an edge.
     */
    public void testRefreshActionCancelCustomStyleTabbarForEdgeWithLineColor() {
        selectAndCheckEditPart(REF1, AbstractDiagramEdgeEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Change the line color with the tabbar
        editor.bot().toolbarDropDownButtonWithTooltip("Li&ne Color").menuItem("Yellow").click();
        SWTBotUtils.waitAllUiEvents();
        // Check that the "Reset style properties to default values" button is
        // enabled and click on
        // it.
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Style in properties view Appearance
     * for an edge.
     */
    public void testRefreshActionCancelCustomStylePropertiesViewForEdge() {
        selectAndCheckEditPart(REF1, AbstractDiagramEdgeEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Change the routing style in the Appearance tab of the properties
        // view.
        SWTBot propertiesBot = selectAppearanceTab();
        new WrappedSWTBotRadio(propertiesBot.radioInGroup("Rectilinear", "Styles:")).click();
        editor.setFocus();
        selectAndCheckEditPart(REF1, AbstractDiagramEdgeEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // enabled and click on
        // it.
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Fill Color on contextual menu for a
     * list Container.
     */
    public void testRefreshActionCancelCustomStyleContextualMenuForListContainer() {
        selectAndCheckEditPart(C1, AbstractDiagramListEditPart.class);
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Change the line color with the contextual menu
        editor.clickContextMenu("Yellow");
        // Check that the "Reset style properties to default values" button is
        // enabled and click on
        // it.
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends IGraphicalEditPart> type) {
        editor.reveal(name);

        SWTBotGefEditPart botPart = editor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);

        CheckSelectedCondition cs = new CheckSelectedCondition(editor, name, type);
        editor.click(botPart);
        botPart.select();
        bot.waitUntil(cs);

        return botPart;
    }

    /**
     * Select the Appearance tab of the Properties view.
     * 
     * @return The SWTBot corresponding to the Properties view
     */
    private SWTBot selectAppearanceTab() {
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        propertiesView.setFocus();
        SWTBot propertiesBot = propertiesView.bot();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesBot);
        return propertiesBot;
    }
}
