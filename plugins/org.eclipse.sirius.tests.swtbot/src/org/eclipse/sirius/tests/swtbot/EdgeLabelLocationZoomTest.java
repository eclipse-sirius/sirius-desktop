/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * This test verifies that an edge label is always at the same position, whatever the zoom and shape of the edge. In
 * particular, for a borderline case with the shape of the edge.
 * 
 * @author scosta
 */
@RunWith(Parameterized.class)
public class EdgeLabelLocationZoomTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "My.ecore";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "My.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/edgeLabelLocationZoom/";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "DTestBugLabelEdge";

    private String representationName;

    private ZoomLevel zoom;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    public EdgeLabelLocationZoomTest(String testName, String representationName, ZoomLevel zoom) {
        this.representationName = representationName;
        this.zoom = zoom;
        setName("testEdgeLabelLocationWithZoom." + representationName + "." + zoom);
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> getParameters() {
        String[] representations = { "testLabelZoomBugLimitCase1", "testLabelZoomBugLimitCase2", "testLabelZoomNormalCase" };
        ZoomLevel[] zooms = ZoomLevel.values();
        var x = Arrays.stream(representations).flatMap((String representation) -> {
            return Arrays.stream(zooms).map(zoom -> {
                String testName = "diagram '" + representation + "' with zoom" + zoom.getLevel();
                return new Object[] { testName, representation, zoom };
            });
        }).toList();
        return x;
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

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
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    private void assertBoundsEquals(String message, Rectangle expected, Rectangle actual, double delta) {
        boolean xCheck = Math.abs(expected.preciseX() - actual.preciseX()) <= delta;
        boolean yCheck = Math.abs(expected.preciseY() - actual.preciseY()) <= delta;
        boolean wCheck = Math.abs(expected.preciseWidth() - actual.preciseWidth()) <= delta;
        boolean hCheck = Math.abs(expected.preciseHeight() - actual.preciseHeight()) <= delta;
        if (!xCheck || !yCheck || !wCheck || !hCheck) {
            failNotEquals(message, expected, actual);
        }
    }

    @Test
    public void testEdgeLabelLocationWithZoom() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_DESCRIPTION_NAME, representationName, DDiagram.class);
        DEdgeNameEditPart labelEditPart = (DEdgeNameEditPart) editor.getEditPart("newEReference1", DEdgeNameEditPart.class).part();

        editor.zoom(zoom);
        Rectangle labelBounds = labelEditPart.getFigure().getBounds();

        assertBoundsEquals("The label has invalid bounds", new Rectangle(1372, 1055, 135, 23), labelBounds, 2.);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        sessionAirdResource = null;
        localSession = null;
        editor = null;
        diagram = null;
        super.tearDown();
    }
}
