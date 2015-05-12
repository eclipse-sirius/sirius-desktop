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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.hamcrest.Matcher;
import org.junit.Assert;

/**
 * Check the stability of children after the resize of their parent:
 * <UL>
 * <LI>Children nodes of a container should stay at the same absolute location
 * after a resize</LI>
 * <LI>Border nodes (of node or container), should stay at the same parent side
 * and at the same x (or y) coordinate according to resize direction.</LI>
 * <UL>
 * 
 * @author pcdavid
 */
public class ChildrenPositionStabilityAfterParentResizeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final Point EXPECTED_INITIAL_POSITION_A = new Point(152, 22);

    private static final Point EXPECTED_INITIAL_POSITION_NODE_A = new Point(28, 31);

    private static final Point EXPECTED_INITIAL_POSITION_NODE_DA = new Point(19, 66);

    private static final Point EXPECTED_INITIAL_POSITION_B = new Point(444, 182);

    private static final Point EXPECTED_INITIAL_POSITION_NODE_B = new Point(29, 30);

    private static final Point EXPECTED_INITIAL_POSITION_NODE_DB = new Point(103, 66);

    private static final Point EXPECTED_INITIAL_POSITION_C = new Point(96, 155);

    private static final Point EXPECTED_INITIAL_POSITION_NODE_C = new Point(28, 81);

    private static final Point EXPECTED_INITIAL_POSITION_D = new Point(394, 274);

    private static final Point EXPECTED_INITIAL_POSITION_NODE_D = new Point(29, 75);

    private static final Point TRANSLATION_PLUS_60X = new Point(60, 0);

    private static final Point TRANSLATION_PLUS_120X = new Point(120, 0);

    private static final Point TRANSLATION_PLUS_180X = new Point(180, 0);

    private static final Point TRANSLATION_PLUS_200X = new Point(200, 0);

    private static final Point TRANSLATION_MINUS_100X = new Point(-100, 0);

    private static final Point TRANSLATION_MINUS_140X = new Point(-140, 0);

    private static final Point TRANSLATION_PLUS_100X = new Point(100, 0);

    private static final Point TRANSLATION_MINUS_80X = new Point(-80, 0);

    private static final Point TRANSLATION_MINUS_60Y = new Point(0, -60);

    private static final Point TRANSLATION_MINUS_80Y = new Point(0, -80);

    private static final Point TRANSLATION_PLUS_40Y = new Point(0, 40);

    private static final Point TRANSLATION_PLUS_60Y = new Point(0, 60);

    private static final Point TRANSLATION_PLUS_80Y = new Point(0, 80);

    private static final Point TRANSLATION_PLUS_100Y = new Point(0, 100);

    private static final Point TRANSLATION_PLUS_140Y = new Point(0, 140);

    private static final String MODEL = "models/tc1479.ecore";

    private static final String SESSION_FILE = "models/tc1479.aird";

    private static final String VSM_FILE = "description/tc1479.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-1479/";

    private static final String FILE_DIR = "models/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private Rectangle aBefore;

    private Rectangle bBefore;

    private Rectangle cBefore;

    private Rectangle dBefore;

    private Rectangle nodeABefore;

    private Rectangle nodeBBefore;

    private Rectangle nodeCBefore;

    private Rectangle nodeDBefore;

    private Rectangle nodeDABefore;

    private Rectangle nodeDBBefore;

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
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, "tc1479.aird");
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Open the diagram TC1479 and initialize global variables.
     */
    protected void initializeTC1479Case() {
        openDiagram("TC1479", "TC1479");
        aBefore = getBounds("A");
        assertThat("Unexpected initial position for bordered node 'A'.", aBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_A));
        bBefore = getBounds("B");
        assertThat("Unexpected initial position for bordered node 'B'.", bBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_B));
        cBefore = getBounds("C");
        assertThat("Unexpected initial position for bordered node 'C'.", cBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_C));
        dBefore = getBounds("D");
        assertThat("Unexpected initial position for bordered node 'D'.", dBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_D));
        nodeABefore = getBounds("CA");
        assertThat("Unexpected initial position for node 'CA'.", nodeABefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_NODE_A));
        nodeBBefore = getBounds("CB");
        assertThat("Unexpected initial position for node 'CB'.", nodeBBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_NODE_B));
        nodeCBefore = getBounds("CC");
        assertThat("Unexpected initial position for node 'CC'.", nodeCBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_NODE_C));
        nodeDBefore = getBounds("CD");
        assertThat("Unexpected initial position for node 'CD'.", nodeDBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_NODE_D));
        nodeDABefore = getBounds("DA");
        assertThat("Unexpected initial position for node 'DA'.", nodeDABefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_NODE_DA));
        nodeDBBefore = getBounds("DB");
        assertThat("Unexpected initial position for node 'DB'.", nodeDBBefore.getLocation(), equalTo(EXPECTED_INITIAL_POSITION_NODE_DB));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_A_after_expand_P1_to_the_right() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_180X));
        checkBoundsAfterDrag("A", equalTo(aBefore.getTranslated(TRANSLATION_PLUS_180X)));
        checkBoundsAfterDrag("CA", equalTo(nodeABefore));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_C_after_expand_P1_to_the_right() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_180X));
        checkBoundsAfterDrag("C", equalTo(cBefore));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_A_after_expand_P1_to_the_left() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_100X));
        checkBoundsAfterDrag("A", equalTo(aBefore));
        checkBoundsAfterDrag("CA", equalTo(nodeABefore.getTranslated(TRANSLATION_MINUS_100X.getNegated())));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_C_after_expand_P1_to_the_left() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_100X));
        checkBoundsAfterDrag("C", equalTo(cBefore));
        checkBoundsAfterDrag("CC", equalTo(nodeCBefore.getTranslated(TRANSLATION_MINUS_100X.getNegated())));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_A_after_expand_P1_to_the_top() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_80Y));
        checkBoundsAfterDrag("A", equalTo(aBefore));
        checkBoundsAfterDrag("CA", equalTo(nodeABefore.getTranslated(TRANSLATION_MINUS_80Y.getNegated())));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_C_after_expand_P1_to_the_top() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_80Y));
        checkBoundsAfterDrag("C", equalTo(cBefore));
        checkBoundsAfterDrag("CC", equalTo(nodeCBefore.getTranslated(TRANSLATION_MINUS_80Y.getNegated())));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_A_after_expand_P1_to_the_bottom() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_80Y));
        checkBoundsAfterDrag("A", equalTo(aBefore));
        checkBoundsAfterDrag("CA", equalTo(nodeABefore));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_C_after_expand_P1_to_the_bottom() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p1");
        Rectangle bounds = editor.clickCentered("p1");
        bot.waitUntil(cS);
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_80Y));
        checkBoundsAfterDrag("C", equalTo(cBefore.getTranslated(TRANSLATION_PLUS_80Y)));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_expand_P2_to_the_right() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_180X));
        checkBoundsAfterDrag("B", equalTo(bBefore));
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P2_to_the_right(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
            Rectangle bounds = editor.clickCentered("p2");
            bot.waitUntil(cS);
            editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_180X));

            checkBoundsAfterDrag("D", equalTo(dBefore));
            checkBoundsAfterDrag("CD", equalTo(nodeDBefore));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_right() throws Exception {
        test_D_after_expand_P31_to_the_right(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_right_zoom125() throws Exception {
        test_D_after_expand_P31_to_the_right(ZoomLevel.ZOOM_125);
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P31_to_the_right(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.maximize();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p31");
            Rectangle bounds = editor.clickCentered("p31");
            bot.waitUntil(cS);
            editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_180X));

            checkBoundsAfterDrag("DA", equalTo(nodeDABefore));
            checkBoundsAfterDrag("DB", equalTo(nodeDBBefore));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
            editor.restore();
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_left() throws Exception {
        test_D_after_expand_P31_to_the_left(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_left_zoom125() throws Exception {
        test_D_after_expand_P31_to_the_left(ZoomLevel.ZOOM_125);
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P31_to_the_left(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.maximize();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p31");
            Rectangle bounds = editor.clickCentered("p31");
            bot.waitUntil(cS);
            editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_100X));
            checkBoundsAfterDrag("DA", equalTo(nodeDABefore.getTranslated(TRANSLATION_MINUS_100X.getScaled(1 / zoomLevel.getAmount()).getNegated())));
            checkBoundsAfterDrag("DB", equalTo(nodeDBBefore.getTranslated(TRANSLATION_MINUS_100X.getScaled(1 / zoomLevel.getAmount()).getNegated())));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
            editor.restore();
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_top() throws Exception {
        test_D_after_expand_P31_to_the_top(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_top_zoom125() throws Exception {
        test_D_after_expand_P31_to_the_top(ZoomLevel.ZOOM_125);
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P31_to_the_top(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.maximize();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p31");
            Rectangle bounds = editor.clickCentered("p31");
            bot.waitUntil(cS);
            editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_80Y));
            checkBoundsAfterDrag("DA", equalTo(nodeDABefore.getTranslated(TRANSLATION_MINUS_80Y.getScaled(1 / zoomLevel.getAmount()).getNegated())));
            checkBoundsAfterDrag("DB", equalTo(nodeDBBefore.getTranslated(TRANSLATION_MINUS_80Y.getScaled(1 / zoomLevel.getAmount()).getNegated())));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
            editor.restore();
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_bottom() throws Exception {
        test_D_after_expand_P31_to_the_bottom(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P31_to_the_bottom_zoom125() throws Exception {
        test_D_after_expand_P31_to_the_bottom(ZoomLevel.ZOOM_125);
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P31_to_the_bottom(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.maximize();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p31");
            Rectangle bounds = editor.clickCentered("p31");
            bot.waitUntil(cS);
            editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_80Y));
            checkBoundsAfterDrag("DA", equalTo(nodeDABefore));
            checkBoundsAfterDrag("DB", equalTo(nodeDBBefore));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
            editor.restore();
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_right() throws Exception {
        test_D_after_expand_P2_to_the_right(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_right_zoom125() throws Exception {
        test_D_after_expand_P2_to_the_right(ZoomLevel.ZOOM_125);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_expand_P2_to_the_left() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_100X));
        checkBoundsAfterDrag("B", equalTo(bBefore));
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P2_to_the_left(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
            Rectangle bounds = editor.clickCentered("p2");
            bot.waitUntil(cS);
            editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_100X));
            checkBoundsAfterDrag("D", equalTo((dBefore.getTranslated(TRANSLATION_MINUS_100X.getScaled(1 / zoomLevel.getAmount())))));
            checkBoundsAfterDrag("CD", equalTo(nodeDBefore.getTranslated(TRANSLATION_MINUS_100X.getScaled(1 / zoomLevel.getAmount()).getNegated())));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_left() throws Exception {
        test_D_after_expand_P2_to_the_left(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_left_125() throws Exception {
        test_D_after_expand_P2_to_the_left(ZoomLevel.ZOOM_125);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_expand_P2_to_the_top() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_80Y));
        checkBoundsAfterDrag("B", equalTo(bBefore.getTranslated(TRANSLATION_MINUS_80Y)));
        checkBoundsAfterDrag("CB", equalTo(nodeBBefore.getTranslated(TRANSLATION_MINUS_80Y.getNegated())));
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P2_to_the_top(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
            Rectangle bounds = editor.clickCentered("p2");
            bot.waitUntil(cS);
            editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_80Y));
            checkBoundsAfterDrag("D", equalTo(dBefore));
            checkBoundsAfterDrag("CD", equalTo(nodeDBefore.getTranslated(TRANSLATION_MINUS_80Y.getScaled(1 / zoomLevel.getAmount()).getNegated())));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_top() throws Exception {
        test_D_after_expand_P2_to_the_top(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_top_zoom125() throws Exception {
        test_D_after_expand_P2_to_the_top(ZoomLevel.ZOOM_125);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_expand_P2_to_the_bottom() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_80Y));
        checkBoundsAfterDrag("B", equalTo(bBefore));
        checkBoundsAfterDrag("CB", equalTo(nodeBBefore));
    }

    /**
     * @param zoomLevel
     *            The zoomLevel to apply to editor.
     * @throws Exception
     *             if an error occurs
     */
    protected void test_D_after_expand_P2_to_the_bottom(ZoomLevel zoomLevel) throws Exception {
        initializeTC1479Case();
        editor.zoom(zoomLevel);
        try {
            CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
            Rectangle bounds = editor.clickCentered("p2");
            bot.waitUntil(cS);
            editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_PLUS_80Y));
            checkBoundsAfterDrag("D", equalTo(dBefore));
            checkBoundsAfterDrag("CD", equalTo(nodeDBefore));
        } finally {
            if (!zoomLevel.equals(ZoomLevel.ZOOM_100)) {
                editor.zoom(ZoomLevel.ZOOM_100);
            }
        }
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_bottom() throws Exception {
        test_D_after_expand_P2_to_the_bottom(ZoomLevel.ZOOM_100);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_expand_P2_to_the_bottom_zoom125() throws Exception {
        test_D_after_expand_P2_to_the_bottom(ZoomLevel.ZOOM_125);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_resizing_P2_from_north_to_south_on_two_drags() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_60Y));
        final Rectangle boundsAfterFirstDrag = getBounds("D");
        assertEquals("The port's position isn't the same before and after the first drag!", dBefore.y, boundsAfterFirstDrag.y);

        bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        SWTBotUtils.waitAllUiEvents();

        // Second drag
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_60Y));
        final Rectangle boundsAfterSecondDrag = getBounds("D");

        assertEquals("The port's position isn't the same before and after the second drag!", boundsAfterFirstDrag.y, boundsAfterSecondDrag.y);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_resizing_P2_from_south_to_north_on_two_drags() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getBottom(), bounds.getBottom().getTranslated(TRANSLATION_MINUS_80Y));
        final Rectangle boundsAfterFirstDrag = getBounds("D");

        bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        SWTBotUtils.waitAllUiEvents();

        // Second drag
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_MINUS_80Y));
        final Rectangle boundsAfterSecondDrag = getBounds("D");

        assertEquals("The port's position isn't the same before and after the second drag!", boundsAfterSecondDrag.y, boundsAfterFirstDrag.y);

    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_resizing_P2_from_east_to_west_on_two_drags() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_MINUS_100X));
        final Rectangle boundsAfterFirstDrag = getBounds("B");

        bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        SWTBotUtils.waitAllUiEvents();

        // Second drag
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_100X));
        final Rectangle boundsAfterSecondDrag = getBounds("B");

        assertEquals("The port's position isn't the same before and after the second drag!", boundsAfterSecondDrag, boundsAfterFirstDrag);

    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_resizing_P2_from_west_to_east_on_two_drags() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_PLUS_100X));
        final Rectangle boundsAfterFirstDrag = getBounds("B");

        bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);
        SWTBotUtils.waitAllUiEvents();

        // Second drag
        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_PLUS_100X));
        final Rectangle boundsAfterSecondDrag = getBounds("B");

        assertEquals("The port's position isn't the same before and after the second drag!", boundsAfterSecondDrag, boundsAfterFirstDrag);

    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_resizing_P2_from_north_to_south_on_close_reopen_diag() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_60Y));
        checkBoundsAfterDrag("D", equalTo(dBefore));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_D_after_resizing_P2_from_west_to_east_on_close_reopen_diag() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_PLUS_100X));
        Rectangle boundsAfterDrag = getBounds("D");
        checkBoundsAfterDrag("D", equalTo(boundsAfterDrag));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_resizing_P2_from_east_to_west_on_close_reopen_diag() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getRight(), bounds.getRight().getTranslated(TRANSLATION_MINUS_80X));
        checkBoundsAfterDrag("B", equalTo(bBefore));
        checkBoundsAfterDrag("CB", equalTo(nodeBBefore));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_B_after_resizing_P2_from_west_to_east_on_close_reopen_diag() throws Exception {
        initializeTC1479Case();
        CheckSelectedCondition cS = new CheckSelectedCondition(editor, "p2");
        Rectangle bounds = editor.clickCentered("p2");
        bot.waitUntil(cS);

        editor.drag(bounds.getLeft(), bounds.getLeft().getTranslated(TRANSLATION_MINUS_100X));
        checkBoundsAfterDrag("B", equalTo(bBefore));
        checkBoundsAfterDrag("CB", equalTo(nodeBBefore.getTranslated(TRANSLATION_MINUS_100X.getNegated())));
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_resize_do_not_move_opposite_border_node_north() throws Exception {
        openDiagram("DiagWithBorderNodeOnNode", "caseOneNorth");
        // Check border node on west side
        CheckSelectedCondition p31SelectedCondition = new CheckSelectedCondition(editor, "p31");
        Rectangle bounds = editor.clickCentered("p31");
        bot.waitUntil(p31SelectedCondition);

        final Rectangle bBoundsBeforeDrag = getBounds("B");
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_80Y));
        checkBoundsAfterDrag("B", equalTo(bBoundsBeforeDrag), "DiagWithBorderNodeOnNode", "caseOneNorth");

        // Check border node on east side
        CheckSelectedCondition p32SelectedCondition = new CheckSelectedCondition(editor, "p32");
        bounds = editor.clickCentered("p32");
        bot.waitUntil(p32SelectedCondition);

        final Rectangle cBoundsBeforeDrag = getBounds("C");
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_80Y));
        checkBoundsAfterDrag("C", equalTo(cBoundsBeforeDrag), "DiagWithBorderNodeOnNode", "caseOneNorth");
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_resize_do_not_move_opposite_border_node_west() throws Exception {
        openDiagram("DiagWithBorderNodeOnNode", "caseOneWest");
        // Check border node on north side
        CheckSelectedCondition p31SelectedCondition = new CheckSelectedCondition(editor, "p31");
        Rectangle bounds = editor.clickCentered("p31");
        bot.waitUntil(p31SelectedCondition);

        final Rectangle aBoundsBeforeDrag = getBounds("A");
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_100X));
        checkBoundsAfterDrag("A", equalTo(aBoundsBeforeDrag), "DiagWithBorderNodeOnNode", "caseOneWest");

        // Check border node on south side
        CheckSelectedCondition p32SelectedCondition = new CheckSelectedCondition(editor, "p32");
        bounds = editor.clickCentered("p32");
        bot.waitUntil(p32SelectedCondition);

        final Rectangle dBoundsBeforeDrag = getBounds("D");
        editor.drag(bounds.getTop(), bounds.getTop().getTranslated(TRANSLATION_PLUS_100X));
        checkBoundsAfterDrag("D", equalTo(dBoundsBeforeDrag), "DiagWithBorderNodeOnNode", "caseOneWest");
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_resize_move_border_node_to_stay_in_parent_bounds_north() throws Exception {
        openDiagram("DiagWithBorderNodeOnNode", "caseTwoNorth");
        // Check border node on west side
        CheckSelectedCondition p31SelectedCondition = new CheckSelectedCondition(editor, "p31");
        Rectangle parentBounds = editor.clickCentered("p31");
        bot.waitUntil(p31SelectedCondition);

        // Moved at parent y axis
        Point newParentTop = parentBounds.getTop().getTranslated(TRANSLATION_PLUS_60Y);
        editor.drag(parentBounds.getTop(), newParentTop);
        Rectangle bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected y location", newParentTop.y, bBoundsAfterDrag.y);
        undo();

        // Moved just under its brother
        Rectangle aBoundsBeforeDrag = getBounds("A");
        editor.drag(parentBounds.getTop(), parentBounds.getTop().getTranslated(TRANSLATION_PLUS_80Y));
        bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected y location", aBoundsBeforeDrag.getBottom().y + 1, bBoundsAfterDrag.y);
        undo();

        // Moved at parent y axis and second border node changed of side
        newParentTop = parentBounds.getTop().getTranslated(TRANSLATION_PLUS_140Y);
        editor.drag(parentBounds.getTop(), newParentTop);
        bBoundsAfterDrag = getBounds("B");
        Rectangle aBoundsAfterDrag = getBounds("A");
        assertEquals("B is not at the expected y location", newParentTop.y, bBoundsAfterDrag.y);
        Assert.assertNotEquals("A is not at the expected x location", aBoundsBeforeDrag.x, aBoundsAfterDrag.x);

        // Check border node on east side
        CheckSelectedCondition p32SelectedCondition = new CheckSelectedCondition(editor, "p32");
        parentBounds = editor.clickCentered("p32");
        bot.waitUntil(p32SelectedCondition);

        // Moved at parent y axis
        newParentTop = parentBounds.getTop().getTranslated(TRANSLATION_PLUS_60Y);
        editor.drag(parentBounds.getTop(), newParentTop);
        Rectangle dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected y location", newParentTop.y, dBoundsAfterDrag.y);
        undo();

        // Moved just under its brother
        Rectangle cBoundsBeforeDrag = getBounds("C");
        editor.drag(parentBounds.getTop(), parentBounds.getTop().getTranslated(TRANSLATION_PLUS_80Y));
        dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected y location", cBoundsBeforeDrag.getBottom().y + 1, dBoundsAfterDrag.y);
        undo();

        // Moved at parent y axis and second border node changed of side
        newParentTop = parentBounds.getTop().getTranslated(TRANSLATION_PLUS_140Y);
        editor.drag(parentBounds.getTop(), newParentTop);
        dBoundsAfterDrag = getBounds("D");
        Rectangle cBoundsAfterDrag = getBounds("C");
        assertEquals("D is not at the expected y location", newParentTop.y, dBoundsAfterDrag.y);
        Assert.assertNotEquals("C is not at the expected x location", cBoundsBeforeDrag.x, cBoundsAfterDrag.x);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_resize_move_border_node_to_stay_in_parent_bounds_south() throws Exception {
        openDiagram("DiagWithBorderNodeOnNode", "caseTwoSouth");
        // Check border node on west side
        CheckSelectedCondition p31SelectedCondition = new CheckSelectedCondition(editor, "p31");
        Rectangle parentBounds = editor.clickCentered("p31");
        bot.waitUntil(p31SelectedCondition);

        // Moved at parent bottom y axis
        Point newParentBottom = parentBounds.getBottom().getTranslated(TRANSLATION_PLUS_40Y.getNegated());
        editor.drag(parentBounds.getBottom(), newParentBottom);
        Rectangle bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected y location", newParentBottom.y - bBoundsAfterDrag.height - 10, bBoundsAfterDrag.y);
        undo();

        // Moved just above its brother
        Rectangle aBoundsBeforeDrag = getBounds("A");
        editor.drag(parentBounds.getBottom(), parentBounds.getBottom().getTranslated(TRANSLATION_PLUS_60Y.getNegated()));
        bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected y location", aBoundsBeforeDrag.getTop().y - 1, bBoundsAfterDrag.getBottom().y);
        undo();

        // Moved at parent bottom y axis and second border node changed of side
        newParentBottom = parentBounds.getBottom().getTranslated(TRANSLATION_PLUS_100Y.getNegated());
        editor.drag(parentBounds.getBottom(), newParentBottom);
        bBoundsAfterDrag = getBounds("B");
        Rectangle aBoundsAfterDrag = getBounds("A");
        assertEquals("B is not at the expected y location", newParentBottom.y - bBoundsAfterDrag.height - 10, bBoundsAfterDrag.y);
        Assert.assertNotEquals("A is not at the expected x location", aBoundsBeforeDrag.x, aBoundsAfterDrag.x);

        // Check border node on east side
        CheckSelectedCondition p32SelectedCondition = new CheckSelectedCondition(editor, "p32");
        parentBounds = editor.clickCentered("p32");
        bot.waitUntil(p32SelectedCondition);

        // Moved at 0 y axis
        newParentBottom = parentBounds.getBottom().getTranslated(TRANSLATION_PLUS_40Y.getNegated());
        editor.drag(parentBounds.getBottom(), newParentBottom);
        Rectangle dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected y location", newParentBottom.y - dBoundsAfterDrag.height - 10, dBoundsAfterDrag.y);
        undo();

        // Moved just above its brother
        Rectangle cBoundsBeforeDrag = getBounds("C");
        editor.drag(parentBounds.getBottom(), parentBounds.getBottom().getTranslated(TRANSLATION_PLUS_60Y.getNegated()));
        dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected y location", cBoundsBeforeDrag.getTop().y - 1, dBoundsAfterDrag.getBottom().y);
        undo();

        // Moved at parent bottom y axis and second border node changed of side
        newParentBottom = parentBounds.getBottom().getTranslated(TRANSLATION_PLUS_100Y.getNegated());
        editor.drag(parentBounds.getBottom(), newParentBottom);
        dBoundsAfterDrag = getBounds("D");
        Rectangle cBoundsAfterDrag = getBounds("C");
        assertEquals("D is not at the expected y location", newParentBottom.y - dBoundsAfterDrag.height - 10, dBoundsAfterDrag.y);
        Assert.assertNotEquals("C is not at the expected x location", cBoundsBeforeDrag.x, cBoundsAfterDrag.x);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_resize_move_border_node_to_stay_in_parent_bounds_west() throws Exception {
        openDiagram("DiagWithBorderNodeOnNode", "caseTwoWest");
        // Check border node on north side
        CheckSelectedCondition p31SelectedCondition = new CheckSelectedCondition(editor, "p31");
        Rectangle parentBounds = editor.clickCentered("p31");
        bot.waitUntil(p31SelectedCondition);

        // Moved at parent x axis
        Point newParentLeft = parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_60X);
        editor.drag(parentBounds.getLeft(), newParentLeft);
        Rectangle bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected x location", newParentLeft.x, bBoundsAfterDrag.x);
        undo();

        // Moved just after its brother
        Rectangle aBoundsBeforeDrag = getBounds("A");
        editor.drag(parentBounds.getLeft(), parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_120X));
        bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected x location", aBoundsBeforeDrag.getRight().x + 1, bBoundsAfterDrag.x);
        undo();

        // Moved at parent x axis and second border node just after
        newParentLeft = parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_180X);
        editor.drag(parentBounds.getLeft(), newParentLeft);
        bBoundsAfterDrag = getBounds("B");
        Rectangle aBoundsAfterDrag = getBounds("A");
        assertEquals("B is not at the expected x location", newParentLeft.x, bBoundsAfterDrag.x);
        assertEquals("A is not at the expected x location", bBoundsAfterDrag.getRight().x + 1, aBoundsAfterDrag.x);
        undo();

        // Moved at parent x axis and second border node changed of side
        newParentLeft = parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_200X);
        editor.drag(parentBounds.getLeft(), newParentLeft);
        bBoundsAfterDrag = getBounds("B");
        aBoundsAfterDrag = getBounds("A");
        assertEquals("B is not at the expected x location", newParentLeft.x, bBoundsAfterDrag.x);
        Assert.assertNotEquals("A is not at the expected y location", aBoundsBeforeDrag.y, aBoundsAfterDrag.y);

        // Check border node on south side
        CheckSelectedCondition p32SelectedCondition = new CheckSelectedCondition(editor, "p32");
        parentBounds = editor.clickCentered("p32");
        bot.waitUntil(p32SelectedCondition);

        // Moved at parent x axis
        newParentLeft = parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_60X);
        editor.drag(parentBounds.getLeft(), newParentLeft);
        Rectangle dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected x location", newParentLeft.x, dBoundsAfterDrag.x);
        undo();

        // Moved just after its brother
        Rectangle cBoundsBeforeDrag = getBounds("C");
        editor.drag(parentBounds.getLeft(), parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_120X));
        dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected x location", cBoundsBeforeDrag.getRight().x + 1, dBoundsAfterDrag.x);
        undo();

        // Moved at parent x axis and second border node just after
        newParentLeft = parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_180X);
        editor.drag(parentBounds.getLeft(), newParentLeft);
        dBoundsAfterDrag = getBounds("D");
        Rectangle cBoundsAfterDrag = getBounds("C");
        assertEquals("D is not at the expected x location", newParentLeft.x, dBoundsAfterDrag.x);
        assertEquals("C is not at the expected x location", dBoundsAfterDrag.getRight().x + 1, cBoundsAfterDrag.x);
        undo();

        // Moved at parent x axis and second border node changed of side
        newParentLeft = parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_200X);
        editor.drag(parentBounds.getLeft(), parentBounds.getLeft().getTranslated(TRANSLATION_PLUS_200X));
        dBoundsAfterDrag = getBounds("D");
        cBoundsAfterDrag = getBounds("C");
        assertEquals("D is not at the expected x location", newParentLeft.x, dBoundsAfterDrag.x);
        Assert.assertNotEquals("C is not at the expected y location", cBoundsBeforeDrag.y, cBoundsAfterDrag.y);
    }

    /**
     * @throws Exception
     *             if an error occurs
     */
    public void test_resize_move_border_node_to_stay_in_parent_bounds_east() throws Exception {
        openDiagram("DiagWithBorderNodeOnNode", "caseTwoEast");
        // Check border node on north side
        CheckSelectedCondition p31SelectedCondition = new CheckSelectedCondition(editor, "p31");
        Rectangle parentBounds = editor.clickCentered("p31");
        bot.waitUntil(p31SelectedCondition);

        // Moved at parent right axis
        Point newParentRight = parentBounds.getRight().getTranslated(TRANSLATION_PLUS_60X.getNegated());
        editor.drag(parentBounds.getRight(), newParentRight);
        Rectangle bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected x location", newParentRight.x - bBoundsAfterDrag.width - 10, bBoundsAfterDrag.x);
        undo();

        // Moved just before its brother
        Rectangle aBoundsBeforeDrag = getBounds("A");
        editor.drag(parentBounds.getRight(), parentBounds.getRight().getTranslated(TRANSLATION_MINUS_140X));
        bBoundsAfterDrag = getBounds("B");
        assertEquals("B is not at the expected x location", aBoundsBeforeDrag.x - bBoundsAfterDrag.width - 1, bBoundsAfterDrag.x);
        undo();

        // Moved at parent right axis and second border node changed of side
        newParentRight = parentBounds.getRight().getTranslated(TRANSLATION_PLUS_180X.getNegated());
        editor.drag(parentBounds.getRight(), newParentRight);
        bBoundsAfterDrag = getBounds("B");
        Rectangle aBoundsAfterDrag = getBounds("A");
        assertEquals("B is not at the expected x location", newParentRight.x - bBoundsAfterDrag.width - 10, bBoundsAfterDrag.x);
        Assert.assertNotEquals("A is not at the expected y location", aBoundsBeforeDrag.y, aBoundsAfterDrag.y);

        // Check border node on south side
        CheckSelectedCondition p32SelectedCondition = new CheckSelectedCondition(editor, "p32");
        parentBounds = editor.clickCentered("p32");
        bot.waitUntil(p32SelectedCondition);

        // Moved at parent right axis
        newParentRight = parentBounds.getRight().getTranslated(TRANSLATION_PLUS_60X.getNegated());
        editor.drag(parentBounds.getRight(), newParentRight);
        Rectangle dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected x location", newParentRight.x - bBoundsAfterDrag.width - 10, dBoundsAfterDrag.x);
        undo();

        // Moved just before its brother
        Rectangle cBoundsBeforeDrag = getBounds("C");
        editor.drag(parentBounds.getRight(), parentBounds.getRight().getTranslated(TRANSLATION_MINUS_140X));
        dBoundsAfterDrag = getBounds("D");
        assertEquals("D is not at the expected x location", cBoundsBeforeDrag.x - dBoundsAfterDrag.width - 1, dBoundsAfterDrag.x);
        undo();

        // Moved at parent right axis and second border node changed of side
        newParentRight = parentBounds.getRight().getTranslated(TRANSLATION_PLUS_180X.getNegated());
        editor.drag(parentBounds.getRight(), newParentRight);
        dBoundsAfterDrag = getBounds("D");
        Rectangle cBoundsAfterDrag = getBounds("C");
        assertEquals("D is not at the expected x location", newParentRight.x - dBoundsAfterDrag.width - 10, dBoundsAfterDrag.x);
        Assert.assertNotEquals("C is not at the expected y location", cBoundsBeforeDrag.y, cBoundsAfterDrag.y);
    }

    private void openDiagram(final String representationName, final String diagramName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), representationName, diagramName, DDiagram.class);
        editor.setSnapToGrid(false);
    }

    private Rectangle getBounds(final String editPartName) {
        final SWTBotGefEditPart editPartBot = editor.getEditPart(editPartName);
        assertNotNull("No part named '" + editPartName + "' found.", editPartBot);
        final EditPart rawPart = editPartBot.part().getParent();
        assertTrue("Part named '" + editPartName + "' is not an IGraphicalEditPart.", rawPart instanceof IGraphicalEditPart);
        IGraphicalEditPart part = (IGraphicalEditPart) rawPart;
        return part.getFigure().getBounds().getCopy();
    }

    /**
     * Check that the bounds is OK :
     * <UL>
     * <LI>just after the drag,</LI>
     * <LI>after a save, close and open of the editor.</LI>
     * </UL>
     * 
     * @param portName
     *            The name of the port to check.
     * @param matcher
     *            The matcher to do the check
     */
    private void checkBoundsAfterDrag(String portName, Matcher<Rectangle> matcher) {
        checkBoundsAfterDrag(portName, matcher, "TC1479", "TC1479");
    }

    /**
     * Check that the bounds is OK :
     * <UL>
     * <LI>just after the drag,</LI>
     * <LI>after a save, close and open of the editor.</LI>
     * </UL>
     * 
     * @param portName
     *            The name of the port to check.
     * @param matcher
     *            The matcher to do the check
     */
    private void checkBoundsAfterDrag(String portName, Matcher<Rectangle> matcher, String representationName, String diagramName) {
        // Check just after drag
        final Rectangle boundsAfterDrag = getBounds(portName);
        assertThat(boundsAfterDrag, matcher);
        // Check after reopening
        editor.save();
        editor.close();
        SWTBotUtils.waitAllUiEvents();
        openDiagram(representationName, diagramName);
        final Rectangle boundsAfterReopening = getBounds(portName);
        assertThat("The port's position isn't the same before and after reopening the representation!", boundsAfterReopening, matcher);
    }
}
