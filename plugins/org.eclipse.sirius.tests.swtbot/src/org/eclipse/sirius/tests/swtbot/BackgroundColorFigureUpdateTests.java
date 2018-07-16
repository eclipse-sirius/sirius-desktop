/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.FixedColor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Test change background color for Container and node and verify that the color correspond to GMF Color.
 * 
 * See VP-3427.
 * 
 * @author jdupont
 */
@SuppressWarnings("restriction")
public class BackgroundColorFigureUpdateTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/changeColorMenu/";

    private static final String FILE_DIR = "/";

    private static final String REPRESENTATION_INSTANCE_NAME = "new Diagram";

    private static final String REPRESENTATION_NAME = "Diagram";

    private static final String NODE_CONTAINER = "p1";

    private static final String NODE_CONTAINER2 = "p2";

    private static final String NODE = "E1";

    private static final String NODE2 = "E2";

    private static final String NODE3 = "EP1";

    private static final String BORDERED_NODE = "a1";

    private Map<String, FixedColor> colorNames = new LinkedHashMap<String, FixedColor>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);

        FixedColor white = DescriptionFactory.eINSTANCE.createFixedColor();
        white.setRed(255);
        white.setGreen(255);
        white.setBlue(255);
        FixedColor black = DescriptionFactory.eINSTANCE.createFixedColor();
        black.setRed(0);
        black.setGreen(0);
        black.setBlue(0);
        FixedColor lightGray = DescriptionFactory.eINSTANCE.createFixedColor();
        lightGray.setRed(192);
        lightGray.setGreen(192);
        lightGray.setBlue(192);
        FixedColor gray = DescriptionFactory.eINSTANCE.createFixedColor();
        gray.setRed(128);
        gray.setGreen(128);
        gray.setBlue(128);
        FixedColor darkGray = DescriptionFactory.eINSTANCE.createFixedColor();
        darkGray.setRed(64);
        darkGray.setGreen(64);
        darkGray.setBlue(64);
        FixedColor red = DescriptionFactory.eINSTANCE.createFixedColor();
        red.setRed(227);
        red.setGreen(164);
        red.setBlue(156);
        FixedColor green = DescriptionFactory.eINSTANCE.createFixedColor();
        green.setRed(166);
        green.setGreen(193);
        green.setBlue(152);
        FixedColor blue = DescriptionFactory.eINSTANCE.createFixedColor();
        blue.setRed(152);
        blue.setGreen(168);
        blue.setBlue(191);
        FixedColor yellow = DescriptionFactory.eINSTANCE.createFixedColor();
        yellow.setRed(225);
        yellow.setGreen(225);
        yellow.setBlue(135);
        FixedColor magenta = DescriptionFactory.eINSTANCE.createFixedColor();
        magenta.setRed(184);
        magenta.setGreen(151);
        magenta.setBlue(192);
        FixedColor cyan = DescriptionFactory.eINSTANCE.createFixedColor();
        cyan.setRed(155);
        cyan.setGreen(199);
        cyan.setBlue(204);
        FixedColor pink = DescriptionFactory.eINSTANCE.createFixedColor();
        pink.setRed(228);
        pink.setGreen(179);
        pink.setBlue(229);
        FixedColor orange = DescriptionFactory.eINSTANCE.createFixedColor();
        orange.setRed(237);
        orange.setGreen(201);
        orange.setBlue(122);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_white, white);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_black, black);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_lightGray, lightGray);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_gray, gray);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_darkGray, darkGray);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_red, red);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_green, green);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_blue, blue);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_yellow, yellow);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_magenta, magenta);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_cyan, cyan);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_pink, pink);
        colorNames.put(DiagramUIActionsMessages.ColorPropertyChangeAction_orange, orange);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test selected color in contextual Menu correspond to color applied to figure. In this case figure correspond to
     * 'Container' with 'Flat Container Style'.
     */
    public void testColorInContextualMenuFromFlatContainerStyle() {
        changeColorFigureFromContextualMenu(NODE_CONTAINER, DNodeContainerEditPart.class);
    }

    /**
     * Test selected color in contextual Menu correspond to color applied to figure. In this case figure correspond to
     * 'Container' with 'Flat Container Style'.
     */
    public void testColorInContextualMenuFromShapeContainerStyle() {
        changeColorFigureFromContextualMenu(NODE_CONTAINER2, DNodeContainerEditPart.class);
    }

    /**
     * Test selected color in contextual Menu correspond to color applied to figure. In this case figure correspond to
     * 'Node' with 'Square Style'.
     */
    public void testColorInContextualMenuFromSquareNodeStyle() {
        changeColorFigureFromContextualMenu(NODE, DNode3EditPart.class);
    }

    /**
     * Test selected color in contextual Menu correspond to color applied to figure. In this case figure correspond to
     * 'Node' with 'Custom Style'.
     */
    public void testColorInContextualMenuFromCustomNodeStyle() {
        changeColorFigureFromContextualMenu(NODE2, DNode3EditPart.class);
    }

    /**
     * Test selected color in contextual Menu correspond to color applied to figure. In this case figure correspond to
     * 'Node' with 'Dot Style'.
     */
    public void testColorInContextualMenuFromDotNodeStyle() {
        changeColorFigureFromContextualMenu(NODE3, DNode3EditPart.class);
    }

    /**
     * Test selected color in contextual Menu correspond to color applied to figure. In this case figure correspond to
     * 'BorderedNode' with 'Square Style'.
     */
    public void testColorInContextualMenuFromSquareBorderedNodeStyle() {
        changeColorFigureFromContextualMenu(BORDERED_NODE, DNode2EditPart.class);
    }

    private void changeColorFigureFromContextualMenu(String figureName, Class<? extends EditPart> type) {
        EqualityHelper equalityHelper = new EqualityHelper();
        for (String colorName : colorNames.keySet()) {
            SWTBotGefEditPart bot = selectAndCheckEditPart(figureName, type);
            editor.clickContextMenu(colorName);
            FixedColor expectedColor = colorNames.get(colorName);
            Color figureColor = ((GraphicalEditPart) bot.part()).getFigure().getBackgroundColor();
            boolean sameColor = equalityHelper.equals(expectedColor, createFixedColorFromColor(figureColor));
            assertTrue("The color of figure should be same that color selected", sameColor);
        }
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();
        bot.waitUntil(new CheckSelectedCondition(editor, name));
        return botPart;
    }

    private FixedColor createFixedColorFromColor(Color color) {
        FixedColor fixedColor = DescriptionFactory.eINSTANCE.createFixedColor();
        fixedColor.setBlue(color.getBlue());
        fixedColor.setGreen(color.getGreen());
        fixedColor.setRed(color.getRed());
        return fixedColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        colorNames = null;
        super.tearDown();
    }

}
