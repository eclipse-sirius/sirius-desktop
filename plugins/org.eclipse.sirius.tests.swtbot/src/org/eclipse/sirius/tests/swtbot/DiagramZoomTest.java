/*******************************************************************************
 * Copyright (c) 2016, 2018 THALES GLOBAL SERVICES.
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

import java.util.Optional;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.SynchronizeStatusFigure;
import org.eclipse.sirius.tests.support.api.ICondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckDiagramSelected;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;

/**
 * Tests that diagram editor zooming is unaffected by synchronized decorator.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class DiagramZoomTest extends AbstractSiriusSwtBotGefTestCase {
    /**
     * Maximum distance on Y axis between the part at the lower position and the decorator.
     */
    private static final int MAX_DISTANCE_Y = 210;

    /**
     * Maximum distance on X axis between the part at the lower position and the decorator.
     */
    private static final int MAX_DISTANCE_X = 1100;

    private static final String FILE_DIR = "/";

    private static final String DIAGRAM_INSTANCE_NAME = "zoomMouseDiagram";

    private static final String DIAGRAM_DESCRIPTION = "Diagram";

    private static final String MODEL_FILE = "mouseZoomTest.ecore";

    private static final String SESSION_FILE = "mouseZoomTest.aird";

    private static final String VSM_FILE = "VSMForMouseZoomTest.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/mouseZoom/";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    private void openDiagram(String descriptionName, String instanceName, ZoomLevel zoomLevel) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), descriptionName, instanceName, DDiagram.class);
        editor.zoom(zoomLevel);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom done with constant with tabbar buttons when
     * the synchronized decorator is present. The decorator should not impact the zoom.
     */
    public void testZoomWithSynchronizedDecoratorConstantWithTabbarZoomButtons() {
        DiagramEditPart diagramPart = openDiagramEditorAndCheckDecorator();
        SWTBotToolbarButton toolbarButton = editor.bot().toolbarButtonWithTooltip("Zoom In (Ctrl+=)");
        toolbarButton.click();
        toolbarButton.click();
        toolbarButton.click();
        checkExpected(diagramPart);
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom done with constant when the synchronized
     * decorator is present. The decorator should not impact the zoom.
     */
    public void testZoomWithSynchronizedDecoratorConstant() {
        DiagramEditPart diagramPart = openDiagramEditorAndCheckDecorator();
        editor.zoom(ZoomLevel.ZOOM_100);
        checkExpected(diagramPart);
    }

    /**
     * Checks that the zoom was done correctly.
     * 
     * @param diagramPart
     */
    private void checkExpected(DiagramEditPart diagramPart) {
        SWTBotUtils.waitAllUiEvents();
        SWTBotGefEditPart bottomEditPart = editor.getEditPart("E");

        TestsUtil.waitUntil(new ICondition() {

            private int distanceBetweenPartAndDecoratorOnXaxis;

            private int distanceBetweenPartAndDecoratorOnYaxis;

            @Override
            public boolean test() throws Exception {
                // We test that the distance between the lower part and the
                // decorator on X and Y axis is not too big that would be the
                // sign it impacts zoom functionality.
                int viewportWidth = diagramPart.getViewport().getHorizontalRangeModel().getMaximum();
                int viewportHeight = diagramPart.getViewport().getVerticalRangeModel().getMaximum();
                int bottomEditPartXPosition = ((GraphicalEditPart) bottomEditPart.part()).getFigure().getBounds().x;
                int bottomEditPartYPosition = ((GraphicalEditPart) bottomEditPart.part()).getFigure().getBounds().y;
                distanceBetweenPartAndDecoratorOnXaxis = viewportWidth - bottomEditPartXPosition;
                distanceBetweenPartAndDecoratorOnYaxis = viewportHeight - bottomEditPartYPosition;
                if (distanceBetweenPartAndDecoratorOnXaxis < MAX_DISTANCE_X && distanceBetweenPartAndDecoratorOnYaxis < MAX_DISTANCE_Y) {
                    return true;
                }
                return false;
            }

            @Override
            public String getFailureMessage() {
                return "viewport after zoom is not correct. Synchronization decorator could have impacted the computing. Width is " + diagramPart.getViewport().getHorizontalRangeModel().getMaximum()
                        + ". And y is " + diagramPart.getViewport().getVerticalRangeModel().getMaximum() + ". Distance between E part and the decorator on x position is: "
                        + distanceBetweenPartAndDecoratorOnXaxis + " but should be lower. Distance between E part and the decorator on y position is: " + distanceBetweenPartAndDecoratorOnYaxis
                        + " but should be lower";
            }
        });
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom done with the mouse when the synchronized
     * decorator is present. The decorator should not impact the zoom.
     */
    public void testMouseZoomWithSynchronizedDecorator() {
        DiagramEditPart diagramPart = openDiagramEditorAndCheckDecorator();

        editor.mouseScrollWithKey(diagramPart.getViewport().getHorizontalRangeModel().getMaximum(), diagramPart.getViewport().getVerticalRangeModel().getMaximum(), SWT.CTRL, 1);
        editor.mouseScrollWithKey(diagramPart.getViewport().getHorizontalRangeModel().getMaximum(), diagramPart.getViewport().getVerticalRangeModel().getMaximum(), SWT.CTRL, 1);
        editor.mouseScrollWithKey(diagramPart.getViewport().getHorizontalRangeModel().getMaximum(), diagramPart.getViewport().getVerticalRangeModel().getMaximum(), SWT.CTRL, 1);
        checkExpected(diagramPart);
    }

    /**
     * Tests that the viewport is shifted to the right position after zoom done with mouse rectangular selection when
     * the synchronized decorator is present. The decorator should not impact the zoom.
     */
    public void testRectangularMouseZoomWithSynchronizedDecorator() {
        DiagramEditPart diagramPart = openDiagramEditorAndCheckDecorator();

        SWTBotGefEditPart paletteRootEditPartBot = editor.getPaletteRootEditPartBot();
        SWTBotGefEditPart swtBotGefEditPart = null;
        swtBotGefEditPart = paletteRootEditPartBot.children().iterator().next().children().get(0).children().get(1);
        assertEquals("The wrong tool is used.", "ToolEntryEditPart( Palette Entry (Zoom In) )", swtBotGefEditPart.part().toString());
        EditPart part = swtBotGefEditPart.part();
        final PaletteViewer viewer = (PaletteViewer) swtBotGefEditPart.part().getViewer();
        final ToolEntry toolEntry = (ToolEntry) part.getModel();
        editor.bot().getDisplay().asyncExec(() -> {
            viewer.setActiveTool(toolEntry);
        });
        editor.drag(new Point(0, 0), new Point(176, 176));
        checkExpected(diagramPart);
    }

    /**
     * Open the diagram editor and verify the synchronized decorator is present.
     * 
     * @return the {@link DiagramEditPart} of the opened diagram.
     */
    private DiagramEditPart openDiagramEditorAndCheckDecorator() {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_SHOW_SYNCHRONIZE_STATUS_DECORATOR.name(), true);
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME, ZoomLevel.ZOOM_25);

        CheckDiagramSelected condition = new CheckDiagramSelected(editor);
        editor.click(new Point(0, 0));
        bot.waitUntil(condition);

        DiagramEditPart diagramPart = (DiagramEditPart) ((IStructuredSelection) editor.getSelection()).getFirstElement();
        Optional<SynchronizeStatusFigure> diagramSynchronizeStatusFigure = SynchronizeStatusFigure.getDiagramSynchronizeStatusFigure((DiagramRootEditPart) diagramPart.getParent());
        assertTrue("The synchronization decorator is missing.", diagramSynchronizeStatusFigure.isPresent());
        SWTBotUtils.waitAllUiEvents();
        return diagramPart;
    }
}
