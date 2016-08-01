/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * Tests the moving of edge labels.
 *
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class EdgeLabelsMoveTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    private SWTBotSiriusDiagramEditor diagramEditor;

    private static final String DATA_UNIT_DIR = "data/unit/edgeLabelMove/";

    private static final String MODEL = "edgeLabelsMoveTest.ecore";

    private static final String SESSION_FILE = "edgeLabelsMoveTest.aird";

    private static final String VSM = "VSMForEdgeLabelsMoveTest.odesign";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Diagram";

    boolean isOutlineViewOpened;

    boolean isPropertiesViewOpened;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);

        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        // Close outline & property view (to improve test performances)
        final IWorkbenchPage currentPage = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0];
        final IViewReference[] viewReferences = currentPage.getViewReferences();
        Display.getDefault().asyncExec(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < viewReferences.length; i++) {
                    if ("org.eclipse.ui.views.ContentOutline".equals(viewReferences[i].getId())) {
                        isOutlineViewOpened = true;
                        currentPage.hideView(viewReferences[i]);
                    } else if (PROPERTIES_VIEW_ID.equals(viewReferences[i].getId())) {
                        isPropertiesViewOpened = true;
                        currentPage.hideView(viewReferences[i]);
                    }
                }
            }
        });
    }

    @Override
    protected void tearDown() throws Exception {
        if (isOutlineViewOpened) {
            designerViews.openOutlineView();
        }
        if (isPropertiesViewOpened) {
            Display.getDefault().asyncExec(new Runnable() {
                @Override
                public void run() {
                    try {
                        PlatformUI.getWorkbench().getWorkbenchWindows()[0].getPages()[0].showView(PROPERTIES_VIEW_ID);
                    } catch (PartInitException e) {
                        fail("Could not reopen property view during teardown : " + e.getMessage());
                    }
                }
            });
        }
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Returns an editor set up according to the given test dimensions.
     *
     * @param diagramName
     *            the diagram name
     * @param zoomLevel
     *            the zoom level
     * @return
     */
    private SWTBotSiriusDiagramEditor setUpEditorAccordingToDimensions(String diagramName, ZoomLevel zoomLevel) {
        return setUpEditorAccordingToDimensions(DIAGRAM_DESCRIPTION_NAME, diagramName, zoomLevel);
    }

    /**
     * Returns an editor set up according to the given test dimensions.
     *
     * @param diagramDescriptionName
     *            the diagram description name
     * @param diagramName
     *            the diagram name
     * @param zoomLevel
     *            the zoom level
     * @return
     */
    private SWTBotSiriusDiagramEditor setUpEditorAccordingToDimensions(String diagramDescriptionName, String diagramName, ZoomLevel zoomLevel) {
        SWTBotSiriusDiagramEditor diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), diagramDescriptionName, diagramName, DDiagram.class, true, true);
        diagramEditor.zoom(zoomLevel);
        diagramEditor.maximize();
        return diagramEditor;
    }

    /**
     * Tests that drag and drop is working when dragging an edge label from a
     * point in the label's border.
     * 
     */
    public void testEdgeLabelMoveFromBorder() {
        String diagramName = "EdgeWith3SegmentsHVH";

        diagramEditor = setUpEditorAccordingToDimensions(diagramName, ZoomLevel.ZOOM_100);

        SWTBotGefEditPart editPart = diagramEditor.getEditPart("refToBCenter");
        editPart.select();
        GraphicalEditPart part = (GraphicalEditPart) editPart.part();

        IFigure figure = part.getFigure();
        // This initial location must be shift according to scrollbar
        figure.translateToAbsolute(figure.getBounds());

        int originalX = part.getFigure().getBounds().x;
        int originalY = part.getFigure().getBounds().y;
        CheckEditPartMoved checkEditPartMoved = new CheckEditPartMoved(editPart);
        diagramEditor.dragWithKey(part.getFigure().getBounds().x + 10, part.getFigure().getBounds().y, part.getFigure().getBounds().x + 10, part.getFigure().getBounds().y - 10, SWT.None);
        bot.waitUntil(checkEditPartMoved);

        assertEquals("The x coordinate should be the same as the move is only on y axis.", originalX, part.getFigure().getBounds().x);
        assertEquals("The y coordinate should be different as the label has been moved by moving its border.", originalY - 10, part.getFigure().getBounds().y);
    }
}
