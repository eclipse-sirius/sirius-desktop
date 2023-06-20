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

import java.util.List;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.impl.EdgeStyleImpl;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.widget.WrappedSWTBotRadio;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarToggleButton;

import junit.framework.TestCase;

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

    private static final String BEGIN_LABEL_NAME = "BEGIN";

    private static final String ODESIGN_FILE = "My.odesign";

    private String oldDefaultFontName;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // Set the default fontName to have tests on
        // "Reset style properties to default values" button works.
        oldDefaultFontName = changeDefaultFontName("Times New Roman");

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
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
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Fill Color in tabbar for a list
     * container.
     */
    public void testRefreshActionCancelCustomStyleTabbarForListContainer() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
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
     * Test refresh "Reset style properties to default values" button after click on Fill Color in tabbar for a Container.
     */
    public void testRefreshActionCancelCustomStyleTabbarForContainer() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
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
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
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
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
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
     * Test refresh "Reset style properties to default values" button after click on Style in properties view Appearance for
     * an edge.
     */
    public void testRefreshActionCancelCustomStylePropertiesViewForEdge() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
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
     * Test refresh "Reset style properties to default values" button after click changing colors on edge
     */
    public void testRefreshActionCancelCustomStylePropertiesViewForFunctionnalChain() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "testLabelEdgeDiagram", "functionnalChain", DDiagram.class);
        SWTBotGefEditPart editPartBot = selectAndCheckEditPart(BEGIN_LABEL_NAME, AbstractDEdgeNameEditPart.class);
        DEdgeBeginNameEditPart editPart = (DEdgeBeginNameEditPart) editPartBot.part();
        EdgeStyleImpl edgeStyleImpl = (EdgeStyleImpl) ((DRepresentationElement) ((Node) (editPart.getModel())).getElement()).getStyle();

        // Check that the "Reset style properties to default values" button is
        // disabled
        getResetStylePropertiesToDefaultValuesButton(true, false);
        // Check the equality of colors between model from editPart and figures
        TestCase.assertTrue(areSameFiguresAndModelsColorsFromEditPart(editPart));
        List<Integer> colors = extractLabelColors(edgeStyleImpl);
        editor.bot().toolbarDropDownButtonWithTooltip("Font Color").menuItem("Cyan").click();
        // Check the equality of colors between model from editPart and figures
        TestCase.assertTrue(areSameFiguresAndModelsColorsFromEditPart(editPart));
        // Verify that colors have changed
        TestCase.assertFalse(colors.equals(extractLabelColors(edgeStyleImpl)));
        // Check that the "Reset style properties to default values" button is
        // enabled and use it
        click(getResetStylePropertiesToDefaultValuesButton(true, true));
        // Check the equality of colors between model from editPart and figures
        TestCase.assertTrue(areSameFiguresAndModelsColorsFromEditPart(editPart));
        edgeStyleImpl = (EdgeStyleImpl) ((DRepresentationElement) ((Node) (editPart.getModel())).getElement()).getStyle();
        // Verify that colors are back to the inital ones
        TestCase.assertTrue(colors.equals(extractLabelColors(edgeStyleImpl)));
    }

    private List<Integer> extractLabelColors(EdgeStyleImpl edgeImpl) {
        return List.of(edgeImpl.getBeginLabelStyle().getLabelColor().toInteger(), edgeImpl.getCenterLabelStyle().getLabelColor().toInteger(), edgeImpl.getEndLabelStyle().getLabelColor().toInteger());
    }

    /**
     * Compare colors of the three labels (1 by 1) between model and figures gmf : BEGIN-CENTER-END
     * @param editPart editPart from which we get model and figures
     * @return true if colors are the same
     */
    private boolean areSameFiguresAndModelsColorsFromEditPart(DEdgeBeginNameEditPart editPart) {
        DRepresentationElement labelRepresentation = (DRepresentationElement) ((Node) (editPart.getModel())).getElement();
        EdgeStyleImpl edgeImpl = (EdgeStyleImpl) labelRepresentation.getStyle();
        RGBValues c1, c2, c3;
        c1 = edgeImpl.getBeginLabelStyle().getLabelColor();
        c2 = edgeImpl.getCenterLabelStyle().getLabelColor();
        c3 = edgeImpl.getEndLabelStyle().getLabelColor();
        boolean e1 = false, e2 = false, e3 = false;
        for (AbstractDEdgeNameEditPart edgeEditPart : (List<AbstractDEdgeNameEditPart>) editPart.getParent().getChildren()) {
            switch (edgeEditPart.getEditText()) {
            case "BEGIN":
                e1 = isSameColor(edgeEditPart.getFigure().getForegroundColor().getRGB(), c1);
                break;
            case "CENTER":
                e2 = isSameColor(edgeEditPart.getFigure().getForegroundColor().getRGB(), c2);
                break;
            case "END":
                e3 = isSameColor(edgeEditPart.getFigure().getForegroundColor().getRGB(), c3);
                break;
            }
        }
        return e1 && e2 && e3;
    }

    private boolean isSameColor(RGB rgb, RGBValues rgbValues) {
        return rgb.green == rgbValues.getGreen() && rgb.red == rgbValues.getRed() && rgb.blue == rgbValues.getBlue();
    }

    /**
     * Test refresh "Reset style properties to default values" button after click on Fill Color on contextual menu for a
     * list Container.
     */
    public void testRefreshActionCancelCustomStyleContextualMenuForListContainer() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
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
