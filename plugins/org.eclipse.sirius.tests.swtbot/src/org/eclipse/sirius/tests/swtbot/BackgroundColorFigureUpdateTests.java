/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.color.ColorManager;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Test change background color for Container and node and verify that the color correspond to GMF Color.
 * 
 * See VP-3427.
 * 
 * @author jdupont
 */
public class BackgroundColorFigureUpdateTests extends AbstractSiriusSwtBotGefTestCase {

    private static final RGB[] BASIC_COLORS = { new RGB(0, 0, 0), new RGB(69, 69, 69), new RGB(209, 209, 209), new RGB(255, 255, 255), new RGB(239, 41, 41), new RGB(252, 175, 62),
        new RGB(252, 233, 79), new RGB(138, 226, 52), new RGB(114, 159, 207), new RGB(173, 127, 168) };

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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
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
        for (RGB basicColor : BASIC_COLORS) {
            String colorString = ColorManager.getDefault().rgbToString(basicColor);
            SWTBotGefEditPart botEditPart = selectAndCheckEditPart(figureName, type);
            SWTBotShell colorPaletteShell = SWTBotSiriusHelper.changeFillColorContextMenu(editor, bot);
            colorPaletteShell.bot().buttonWithTooltip(colorString).click();
            Color figureColor = ((GraphicalEditPart) botEditPart.part()).getFigure().getBackgroundColor();
            assertEquals("The color of figure should be same that color selected", basicColor, figureColor.getRGB());
        }
    }

    private SWTBotGefEditPart selectAndCheckEditPart(String name, Class<? extends EditPart> type) {
        SWTBotGefEditPart botPart = editor.getEditPart(name, type);
        assertNotNull("The requested edit part should not be null", botPart);
        botPart.select();
        bot.waitUntil(new CheckSelectedCondition(editor, name));
        return botPart;
    }
}
