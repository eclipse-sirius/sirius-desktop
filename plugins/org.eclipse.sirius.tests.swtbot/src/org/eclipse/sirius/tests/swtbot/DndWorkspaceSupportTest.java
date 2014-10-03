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

import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.dnd.DndUtil;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Validate the export of diagram as image.
 * 
 * @author mchauvin
 */
public class DndWorkspaceSupportTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "436.ecore";

    private static final String SESSION_FILE = "436.aird";

    private static final String VSM_FILE = "436.odesign";

    private static final String SAMPLE_FILE = "file.properties";

    private static final String SAMPLE_MODEL_FILE = "file.ecore";

    private static final String DATA_UNIT_DIR = "data/unit/dragAndDrop/tc-436/";

    private static final String FILE_DIR = "/";

    /*
     * the offset comes from the scrollbar which are drown inside the figure
     * canvas. It will also depends on the window manager installed.
     */
    private int OFFSET = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, VSM_FILE, SESSION_FILE, SAMPLE_FILE, SAMPLE_MODEL_FILE);
    }

    public void testDragAndDropFile() {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Class diagram", "diagram", DDiagram.class);

        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotTreeItem sampleFile = tree.expandNode(designerProject.getName()).expandNode(SAMPLE_FILE);

        DndUtil util = new DndUtil(bot.getDisplay());
        util.dragAndDrop(sampleFile, editor.getCanvas());

        bot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return !((RootEditPart) editor.rootEditPart().part()).getContents().getChildren().isEmpty();
            }

            public String getFailureMessage() {
                return "no diagram element was created after the drag and drop";
            }
        });

        final GraphicalEditPart editPart = (GraphicalEditPart) ((RootEditPart) editor.rootEditPart().part()).getContents().getChildren().get(0);
        final Point location = editPart.getFigure().getBounds().getLocation();

        assertAround(((FigureCanvas) editor.getCanvas().widget).getViewport().getBounds().getCenter(), location, OFFSET);

    }

    private static void assertAround(Point expected, Point actual, int offset) {
        Dimension diff = expected.getDifference(actual);
        if (diff.width > offset || diff.height > offset)
            fail("The location : " + actual + "differs more than expected (" + offset + ") from the expected location : " + expected);
    }

    public void testDragAndDropModelFile() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Class diagram", "diagram", DDiagram.class);

        SWTBotTree tree = bot.viewByTitle("Model Explorer").bot().tree();
        SWTBotTreeItem sampleFile = tree.expandNode(designerProject.getName()).expandNode(SAMPLE_MODEL_FILE);

        final int numberOfSemanticResourcesBeforeDrag = getNumberOfSemanticResources(localSession);

        DndUtil util = new DndUtil(bot.getDisplay());
        util.dragAndDrop(sampleFile, editor.getCanvas());

        bot.waitUntil(new DefaultCondition() {

            public boolean test() throws Exception {
                return !((RootEditPart) editor.rootEditPart().part()).getContents().getChildren().isEmpty();
            }

            public String getFailureMessage() {
                return "no diagram element was created after the drag and drop";
            }
        });

        assertEquals(numberOfSemanticResourcesBeforeDrag + 1, getNumberOfSemanticResources(localSession));

    }

    private int getNumberOfSemanticResources(UILocalSession session) {
        return session.getOpenedSession().getSemanticResources().size();
    }
}
