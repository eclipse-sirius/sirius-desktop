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
package org.eclipse.sirius.tests.swtbot.layout;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart.ViewEdgeFigure;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;

/**
 * Tests edge layout stability on adding element using a tool wizard.
 * 
 * @author lchituc
 */
public class EdgeLayoutStabilityWithToolWizardTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String WIZARD_TITLE = "Selection Wizard";

    private static final int NB_POINTS_EDGE_A_TO_B = 3;

    private static final String REPRESENTATION_INSTANCE_NAME = "new edgeLayoutPbDiag";

    private static final String REPRESENTATION_NAME = "edgeLayoutPbDiag";

    private static final String MODEL = "edgeLayoutPb.ecore";

    private static final String SESSION_FILE = "edgeLayoutPb.aird";

    private static final String VSM_FILE = "edgeLayoutPb.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/layout/edgeLayoutWithWizard/";

    private static final String FILE_DIR = "/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        final UILocalSession localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testEdgeLayoutStabilityOnToolWizard() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        // Gets the location of P1
        SWTBotGefEditPart p1Bot = editor.getEditPart("p1").parent();
        final Point p1Location = editor.getBounds(p1Bot).getLocation();

        // Checks the number of edges going from the border node of p1
        // package
        IGraphicalEditPart aPart = (IGraphicalEditPart) ((IGraphicalEditPart) editor.getEditPart("p1").part()).getParent().getChildren().get(2);
        assertEquals("There should be only one edge going from the border node of p1.", 1, ((NodeEditPart) aPart).getSourceConnections().size());
        final ConnectionEditPart edge = (ConnectionEditPart) ((NodeEditPart) aPart).getSourceConnections().get(0);
        assertTrue("The figure should be a ViewEdgeFigure.", edge.getFigure() instanceof ViewEdgeFigure);
        ViewEdgeFigure connectionFigure = (ViewEdgeFigure) edge.getFigure();
        // Checks the number of bend-points of the edge
        assertEquals("Bad number of bendpoints before adding the third package", NB_POINTS_EDGE_A_TO_B, connectionFigure.getPoints().size());
        // Gets the bend-points' position
        Point firstPoint = connectionFigure.getPoints().getFirstPoint().getCopy();
        Point middlePoint = connectionFigure.getPoints().getMidpoint().getCopy();
        Point lastPoint = connectionFigure.getPoints().getLastPoint().getCopy();

        // Adds the third package using the tool wizard
        editor.activateTool("clickOnPackage");
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                // Click on p1 package
                editor.click(p1Location.x + 20, p1Location.y + 20);
            }
        });
        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        final SWTBotShell shell = bot.shell(WIZARD_TITLE);
        shell.setFocus();
        shell.bot().tree(0).getTreeItem("p3").select();
        final SWTBot shellBot = new SWTBot(shell.widget);
        final SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();
        bot.waitUntilWidgetAppears(Conditions.shellCloses(shell));

        // Asserts that the third package and its border node were added.
        // Throw an exception if edit part is not found
        assertTrue("The third package, p3, should be displayed on the diagram", editor.getEditPart("p3") != null);
        assertTrue("The border node of the third package, C, should be displayed on the diagram", editor.getEditPart("p3").part().getParent().getChildren().get(2) != null);
        // Asserts that there is a new edge going from A to C
        assertEquals("There should be two edges going from the border node of p1.", 2, ((NodeEditPart) aPart).getSourceConnections().size());

        // Checks that the edge from A to B has the same number of
        // bend-points as before adding the third package and that their
        // position has not changed
        assertEquals("Bad number of bendpoints after adding the third package", NB_POINTS_EDGE_A_TO_B, ((ViewEdgeFigure) edge.getFigure()).getPoints().size());
        assertEquals("the first bendpoint of the edge is invalid.", firstPoint, connectionFigure.getPoints().getFirstPoint().getCopy());
        assertEquals("the middle bendpoint of the edge is invalid.", middlePoint, connectionFigure.getPoints().getMidpoint().getCopy());
        assertEquals("the last bendpoint of the edge is invalid.", lastPoint, connectionFigure.getPoints().getLastPoint().getCopy());
    }

}
