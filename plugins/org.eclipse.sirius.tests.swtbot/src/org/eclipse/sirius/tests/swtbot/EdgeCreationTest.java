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

import org.eclipse.core.internal.runtime.InternalPlatform;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * when creating an edge between two elements with icon, the MapModeUtil doesn't
 * warn of using DefaultMapMode.
 * 
 * @author lredor
 */
@SuppressWarnings("restriction")
public class EdgeCreationTest extends AbstractSiriusSwtBotGefTestCase {
    private static final PrecisionPoint TOP_LEFT_CORNER = new PrecisionPoint(0.1, 0.1);

    private static final PrecisionPoint BOTTOM_RIGHT_CORNER = new PrecisionPoint(0.9, 0.9);

    private static final String MODEL_FILE = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "ticket2298.odesign";

    private static final String IMAGE_FILE = "Thing.gif";

    private static final String DATA_UNIT_DIR = "data/unit/edgeCreation/";

    private static final String REPRESENTATION_DESC_2298_NAME = "Diag2298";

    private ILogListener listener;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE, IMAGE_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    private void openDiagram(String name) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), name, name, DDiagram.class);
    }

    private boolean error;

    private synchronized boolean doesASpecificErrorOccurs() {
        return error;
    }

    private synchronized void errorOccurs() {
        error = true;
    }

    /** */
    public void testReferenceToolWithIcon() {
        openDiagram(REPRESENTATION_DESC_2298_NAME);

        listener = new ILogListener() {

            @Override
            public void logging(IStatus status, String plugin) {
                if (status.getSeverity() == IStatus.WARNING && status.getMessage().indexOf("MapModeUtil") != -1)
                    errorOccurs();
            }
        };
        // Add a log listener to catch an eventual warning message during the
        // edge creation
        InternalPlatform.getDefault().addLogListener(listener);

        // Create the edge
        createReference("C1", DNodeEditPart.class, TOP_LEFT_CORNER, "C2", DNodeEditPart.class, BOTTOM_RIGHT_CORNER);

        // Check that an edge has been created.
        getSingleDEdgeFrom("C1");

        // Remove the log listener
        InternalPlatform.getDefault().removeLogListener(listener);

        // Check if there is no warning message
        if (doesASpecificErrorOccurs())
            fail("A warning concerning the MapModeUtil was log during this test.");
    }

    private DEdgeEditPart getSingleDEdgeFrom(String sourceName) {
        SWTBotGefEditPart sourcePart = editor.getEditPart(sourceName).parent();
        assertEquals("Bad number of edge", 1, sourcePart.sourceConnections().size());
        SWTBotGefConnectionEditPart edge = sourcePart.sourceConnections().get(0);
        assertTrue(edge.part() instanceof DEdgeEditPart);
        return (DEdgeEditPart) edge.part();
    }

    private void createReference(String sourceName, Class<? extends EditPart> expectedSourceClass, PrecisionPoint sourcePosition, String targetName, Class<? extends EditPart> expectedTargetClass,
            PrecisionPoint targetPosition) {
        SWTBotGefEditPart source = editor.getEditPart(sourceName, expectedSourceClass);
        SWTBotGefEditPart target = editor.getEditPart(targetName, expectedTargetClass);
        createReference((IGraphicalEditPart) source.part(), sourcePosition, (IGraphicalEditPart) target.part(), targetPosition);
    }

    private void createReference(IGraphicalEditPart source, PrecisionPoint sourcePosition, IGraphicalEditPart target, PrecisionPoint targetPosition) {
        Point sourcePoint = getProportionalPoint(getAbsoluteBounds(source), sourcePosition);
        Point targetPoint = getProportionalPoint(getAbsoluteBounds(target), targetPosition);

        editor.activateTool("Reference");
        editor.click(sourcePoint);
        editor.click(targetPoint);
    }

    private Rectangle getAbsoluteBounds(IGraphicalEditPart part) {
        IFigure figure = part.getFigure();
        Rectangle r = figure.getBounds().getCopy();
        figure.getParent().translateToAbsolute(r);
        return r;
    }

    private Point getProportionalPoint(Rectangle bounds, PrecisionPoint proportions) {
        Point result = bounds.getTopLeft().getCopy();
        long xOffest = Math.round(bounds.width * proportions.preciseX());
        long yOffset = Math.round(bounds.height * proportions.preciseY());
        result.translate(new Dimension((int) xOffest, (int) yOffset));
        return result;
    }
}
