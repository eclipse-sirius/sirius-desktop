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

import static org.eclipse.sirius.tests.swtbot.support.api.matcher.geometry.PointAround.around;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.IBorderItemLocator;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Tests for global refresh review and viewpoint ticket #1283.
 * 
 * @author smonnier
 */
public class PortsOnNodePositionStabilityTest extends AbstractSiriusSwtBotGefTestCase {

    private static final int ACCEPTABLE_DISTANCE = 3;

    private static final Point TRANSLATION_PLUS_10X = new Point(10, 0);

    private static final Point TRANSLATION_MINUS_10X = new Point(-10, 0);

    private static final Point TRANSLATION_MINUS_10Y = new Point(0, -10);

    private static final Point TRANSLATION_PLUS_10Y = new Point(0, 10);

    private static final String REPRESENTATION_INSTANCE_NAME = "new tc_viewpoint_1283";

    private static final String REPRESENTATION_NAME = "tc_viewpoint_1283";

    private static final String MODEL = "tc_viewpoint_1283.ecore";

    private static final String SESSION_FILE = "tc_viewpoint_1283.aird";

    private static final String VSM_FILE = "tc_viewpoint_1283.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc_viewpoint_1283/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        editor.setSnapToGrid(false);
        editor.maximize();
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNodeEditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNodeEditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNodeEditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
        bot.sleep(500);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNodeEditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
        bot.sleep(500);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNodeEditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
        bot.sleep(500);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNodeEditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
        bot.sleep(500);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNodeEditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
        bot.sleep(500);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNodeEditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DNEP_0");
        editor.reveal("DNEP_0");
        Rectangle bounds = editor.clickCentered("DNEP_0");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DNEP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_c", DNode2EditPart.class), around(locationC.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DNEP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DNEP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DNEP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DNEP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DNEP_d", DNode2EditPart.class), equalTo(dimensionD));
        bot.sleep(500);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode2EditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode2EditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode2EditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode2EditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode2EditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode2EditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode2EditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode2EditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN2EP_on_DNEP_a");
        editor.reveal("DN2EP_on_DNEP_a");
        Rectangle bounds = editor.clickCentered("DN2EP_on_DNEP_a");
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN2EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN2EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN2EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode3EditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode3EditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode3EditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode3EditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode3EditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode3EditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode3EditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode3EditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN3EP_a");
        editor.reveal("DN3EP_a");
        Point locationDN3EP_a = editor.getLocation("DN3EP_a", DNode3EditPart.class);
        Dimension dimensionDN3EP_a = editor.getDimension("DN3EP_a", DNode3EditPart.class);
        editor.click(locationDN3EP_a);
        Rectangle bounds = new Rectangle(locationDN3EP_a, dimensionDN3EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Point locationB = editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Point locationC = editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Point locationD = editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class);
        Dimension dimensionA = editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class);
        Dimension dimensionB = editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class);
        Dimension dimensionC = editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class);
        Dimension dimensionD = editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN2EP_on_DN3EP_a", DNode2EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_b", DNode2EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_c", DNode2EditPart.class), around(locationC.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN2EP_on_DN3EP_d", DNode2EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_a", DNode2EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_b", DNode2EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_c", DNode2EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN2EP_on_DN3EP_d", DNode2EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode4EditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode4EditPart_North() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode4EditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode4EditPart_East() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode4EditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode4EditPart_South() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_MINUS_10Y));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB.getTranslated(TRANSLATION_MINUS_10Y), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD, ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_expand_DNode4EditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD.getTranslated(TRANSLATION_MINUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_shrink_DNode4EditPart_West() throws Exception {
        // Select the node
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "DN4EP_a");
        editor.reveal("DN4EP_a");
        Point locationDN4EP_a = editor.getLocation("DN4EP_a", DNode4EditPart.class);
        Dimension dimensionDN4EP_a = editor.getDimension("DN4EP_a", DNode4EditPart.class);
        editor.click(locationDN4EP_a);
        Rectangle bounds = new Rectangle(locationDN4EP_a, dimensionDN4EP_a);
        bot.waitUntil(cS);

        // Save position of border nodes
        Point locationA = editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Point locationB = editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Point locationC = editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Point locationD = editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class);
        Dimension dimensionA = editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class);
        Dimension dimensionB = editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class);
        Dimension dimensionC = editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class);
        Dimension dimensionD = editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class);

        // Resize the node
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_PLUS_10X));

        // Validate bordered node position and dimension
        assertThat(editor.getLocation("DN4EP_on_DN4EP_a", DNode4EditPart.class), around(locationA, ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_b", DNode4EditPart.class), around(locationB.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_c", DNode4EditPart.class), around(locationC.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getLocation("DN4EP_on_DN4EP_d", DNode4EditPart.class), around(locationD.getTranslated(TRANSLATION_PLUS_10X), ACCEPTABLE_DISTANCE));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_a", DNode4EditPart.class), equalTo(dimensionA));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_b", DNode4EditPart.class), equalTo(dimensionB));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_c", DNode4EditPart.class), equalTo(dimensionC));
        assertThat(editor.getDimension("DN4EP_on_DN4EP_d", DNode4EditPart.class), equalTo(dimensionD));
    }

    /**
     * Test that when a label size become high the label is relocated in another side.
     */
    public void test_DNode4EditPart_LabelSizeIncrease() {
        SWTBotGefEditPart borderEditPart = editor.getEditPart("DN2EP_on_DN2EP_a", AbstractDiagramBorderNodeEditPart.class);
        borderEditPart.select();
        SWTBotGefEditPart labelBorderEditPart = editor.getEditPart("DN2EP_on_DN2EP_a", AbstractDiagramNameEditPart.class);
        IBorderItemLocator borderItemLocator = ((IBorderItemEditPart) labelBorderEditPart.part()).getBorderItemLocator();
        int currentSideOfParent = borderItemLocator.getCurrentSideOfParent();
        assertEquals("At the beginning the label as being small should be east positionned", PositionConstants.EAST, currentSideOfParent);

        editor.restore();
        SWTBotView propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesBot.bot());
        propertiesBot.bot().ccomboBox(1).setSelection("72");
        SWTBotUtils.waitAllUiEvents();

        // Checks that the label is on the south side now
        int newSideOfParent = borderItemLocator.getCurrentSideOfParent();
        assertEquals("Now with a big label size the label should be south positionned", PositionConstants.SOUTH, newSideOfParent);

        localSession.save();
        editor.close();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        // Checks that after reopening the label is on the south side now
        labelBorderEditPart = editor.getEditPart("DN2EP_on_DN2EP_a", AbstractDiagramNameEditPart.class);
        borderItemLocator = ((IBorderItemEditPart) labelBorderEditPart.part()).getBorderItemLocator();
        newSideOfParent = borderItemLocator.getCurrentSideOfParent();
        assertEquals("Even after diagram reopening the label should be south positionned", PositionConstants.SOUTH, newSideOfParent);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.sirius.tests.swtbot.support.api. AbstractSiriusSwtBotGefTestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        editor.restore();
        super.tearDown();
    }

}
