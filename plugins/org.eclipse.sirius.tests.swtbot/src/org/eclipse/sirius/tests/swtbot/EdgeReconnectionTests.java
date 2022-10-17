/*******************************************************************************
 * Copyright (c) 2015, 2022 THALES GLOBAL SERVICES and others.
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

import java.util.List;
import java.util.Optional;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.tests.support.api.GraphicTestsSupportHelp;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.hamcrest.core.IsInstanceOf;

/**
 * Tests edge reconnection.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class EdgeReconnectionTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/edgeReconnection/";

    private static final String MODELER_RESOURCE_NAME = "Bug467663." + SiriusUtil.DESCRIPTION_MODEL_EXTENSION;

    private static final String SEMANTIC_RESOURCE1_NAME = "Bug467663.ecore";

    private static final String SEMANTIC_RESOURCE2_NAME = "Bug467663.component";

    private static final String SESSION_RESOURCE_NAME = "Bug467663." + SiriusUtil.SESSION_RESOURCE_EXTENSION;

    private static final String REPRESENTATION1_NAME = "DiagramForBug467663";

    private static final String REPRESENTATION2_NAME = "DiagramForBug467663Bis";

    private static final String REPRESENTATION3_NAME = "DiagramForBug467663WithRectilinear";

    private static final String REPRESENTATION4_NAME = "DiagramForBug580924";


    /**
     * A selection listener to detect if the diagram is selected during the reconnection of an edge.
     * 
     * @author lredor
     */
    private class NoDiagramSelectionListener implements ISelectionListener {

        String errorMessage = null;

        /**
         * Default constructor.
         */
        public NoDiagramSelectionListener() {
        }

        @Override
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {
            if (selection instanceof StructuredSelection) {
                StructuredSelection structuredSelection = (StructuredSelection) selection;
                if (structuredSelection.size() != 1) {
                    errorMessage = "Only one element must be selected during the drag'n'drop (but was <" + structuredSelection.size() + ">."; //$NON-NLS-1$ //$NON-NLS-2$
                } else if (structuredSelection.getFirstElement() instanceof DDiagramEditPart) {
                    errorMessage = "The diagram must never be selected during the drag'n'drop."; //$NON-NLS-1$
                }
            } else {
                errorMessage = "The selection must be StructuredSelection during the drag'n'drop."; //$NON-NLS-1$
            }
        }

        public Optional<String> getError() {
            return Optional.ofNullable(errorMessage);
        }
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, MODELER_RESOURCE_NAME, SEMANTIC_RESOURCE1_NAME, SEMANTIC_RESOURCE2_NAME, SESSION_RESOURCE_NAME);
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
    }

    /**
     * Test reconnection of source and target edge end point from a container to
     * another with oblique style routing.
     */
    public void testSimpleEdgeSourceReconnectionWithObliqueStyleRouting() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION1_NAME, "new " + REPRESENTATION1_NAME, DDiagram.class, true);
        editor.maximize();
        try {
            SWTBotGefEditPart eClass1EditPartBot = editor.getEditPart("EClass1", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart eClass2EditPartBot = editor.getEditPart("EClass2", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart eClassKOEditPartBot = editor.getEditPart("EClassKO", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart ref1EditPartBot = editor.getEditPart("ref1", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart ref2EditPartBot = editor.getEditPart("ref2", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart ref3EditPartBot = editor.getEditPart("ref3", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart ref4EditPartBot = editor.getEditPart("ref4", AbstractDiagramContainerEditPart.class);
            SWTBotGefConnectionEditPart connection1EditPartBot = editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).get(0);
            SWTBotGefConnectionEditPart connection2EditPartBot = editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot).get(0);

            // Reconnect target of first connection
            PointList connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            Point from = connection1Points.getLastPoint();
            Point to = from.getCopy().setX(editor.getBounds(eClass2EditPartBot).x);
            connection1EditPartBot.select();

            // Add a selection listener to detect wrong diagram selection during drag'n'drop
            NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(ref1EditPartBot, eClass2EditPartBot);
                assertEquals(1, newConnection1EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
                PointList newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection1Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection1Points.getFirstPoint(), newConnection1Points.getFirstPoint(), 0,
                        1);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection1Points.getLastPoint(), 0, 1);
                connection1EditPartBot = newConnection1EditPartBot;
                // Check that the edge is selected after reconnection
                checkEdgeSelection(connection1EditPartBot);
            } finally {
                EclipseUIUtil.getActivePage().removeSelectionListener(selectionListener);
            }

            // Reconnect target of second connection
            PointList connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection2Points.getLastPoint();
            to = from.getCopy().setX(editor.getBounds(eClass2EditPartBot).x);
            connection2EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection2EditPartBotList = editor.getConnectionEditPart(ref2EditPartBot, eClass2EditPartBot);
                assertEquals(1, newConnection2EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
                PointList newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection2Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection2Points.getFirstPoint(), newConnection2Points.getFirstPoint(), 0,
                        2);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection2Points.getLastPoint(), 0, 2);
                connection2EditPartBot = newConnection2EditPartBot;
                // Check that the edge is selected after reconnection
                checkEdgeSelection(connection2EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect target of first connection as initially
            connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection1Points.getLastPoint();
            to = from.getCopy().setX(editor.getBounds(eClass1EditPartBot).getRight().x - 20);
            connection1EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass2EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot);
                assertEquals(1, newConnection1EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
                PointList newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection1Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection1Points.getFirstPoint(), newConnection1Points.getFirstPoint(), 1,
                        1);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection1Points.getLastPoint(), 1, 1);
                connection1EditPartBot = newConnection1EditPartBot;
                // Check that the edge is selected after reconnection
                checkEdgeSelection(connection1EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect target of second connection as initially
            connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection2Points.getLastPoint();
            to = from.getCopy().setX(editor.getBounds(eClass1EditPartBot).getRight().x - 20);
            connection2EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref2EditPartBot, eClass2EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection2EditPartBotList = editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot);
                assertEquals(1, newConnection2EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
                PointList newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection2Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection2Points.getFirstPoint(), newConnection2Points.getFirstPoint(), 1,
                        2);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection2Points.getLastPoint(), 1, 2);
                connection2EditPartBot = newConnection2EditPartBot;
                // Check that the edge is selected after reconnection
                checkEdgeSelection(connection2EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect source of first connection
            connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection1Points.getFirstPoint();
            to = from.getCopy().setX(editor.getBounds(ref3EditPartBot).x);
            connection1EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(ref3EditPartBot, eClass1EditPartBot);
                assertEquals(1, newConnection1EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
                PointList newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection1Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", to, newConnection1Points.getFirstPoint(), 0, 2);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", connection1Points.getLastPoint(), newConnection1Points.getLastPoint(), 0,
                        2);
                connection1EditPartBot = newConnection1EditPartBot;
                // Check that the edge is selected after reconnection
                checkEdgeSelection(connection1EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect source of second connection
            connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection2Points.getFirstPoint();
            to = from.getCopy().setX(editor.getBounds(ref4EditPartBot).x);
            connection2EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection2EditPartBotList = editor.getConnectionEditPart(ref4EditPartBot, eClass1EditPartBot);
                assertEquals(1, newConnection2EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
                PointList newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection2Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", to, newConnection2Points.getFirstPoint(), 0, 2);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", connection2Points.getLastPoint(), newConnection2Points.getLastPoint(), 0,
                        1);
                connection2EditPartBot = newConnection2EditPartBot;
                // Check that the edge is selected after reconnection
                checkEdgeSelection(connection2EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect source of first connection as initially
            connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection1Points.getFirstPoint();
            to = from.getCopy().setX(editor.getBounds(ref1EditPartBot).getRight().x - 2);
            connection1EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            PointList newConnection1Points;
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref3EditPartBot, eClass1EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot);
                assertEquals(1, newConnection1EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
                newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection1Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", to, newConnection1Points.getFirstPoint(), 0, 4);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", connection1Points.getLastPoint(), newConnection1Points.getLastPoint(), 0,
                        1);
                // Check that the edge is selected after reconnection
                checkEdgeSelection(newConnection1EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect source of second connection as initially
            connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection2Points.getFirstPoint();
            to = from.getCopy().setX(editor.getBounds(ref2EditPartBot).getRight().x - 2);
            connection2EditPartBot.select();
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection is correct
                assertEquals(0, editor.getConnectionEditPart(ref4EditPartBot, eClass2EditPartBot).size());
                List<SWTBotGefConnectionEditPart> newConnection2EditPartBotList = editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot);
                assertEquals(1, newConnection2EditPartBotList.size());
                SWTBotGefConnectionEditPart newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
                PointList newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
                assertEquals(3, newConnection2Points.size());
                GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", to, newConnection2Points.getFirstPoint(), 0, 2);
                GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", connection1Points.getLastPoint(), newConnection1Points.getLastPoint(), 0,
                        2);
                // Check that the edge is selected after reconnection
                checkEdgeSelection(newConnection2EditPartBot);
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }

            // Reconnect target of first connection on an element forbidden by a wrong precondition using otherEnd and
            // edgeView variables
            connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            from = connection1Points.getLastPoint();
            to = from.getCopy().setX(editor.getBounds(eClassKOEditPartBot).x);
            connection1EditPartBot.select();

            // Add a selection listener to detect wrong diagram selection during drag'n'drop
            selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection does nothing (wrong precondition)
                assertEquals("The edge should still exist as before reconnection, before the precondition is false.", 1, editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).size()); //$NON-NLS-1$
                List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(ref1EditPartBot, eClassKOEditPartBot);
                assertEquals("No edge should be created after reconnection.", 0, newConnection1EditPartBotList.size()); //$NON-NLS-1$
            } finally {
                removeSelectionListenerAndCheckIt(selectionListener);
            }
        } finally {
            editor.restore();
        }
    }

    /**
     * Check that the edge is selected.
     * 
     * @param connectionEditPartBot
     *            edit part to check
     */
    private void checkEdgeSelection(SWTBotGefConnectionEditPart connectionEditPartBot) {
        assertTrue("The edge should be selected after the reconnection.", connectionEditPartBot.part().getSelected() != EditPart.SELECTED_NONE);
    }

    /**
     * Test reconnection of target edge end point from a container to another
     * with rectilinear style routing.
     */
    public void testSimpleEdgeSourceReconnectionWithRectilinearStyleRouting() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION1_NAME, "new " + REPRESENTATION3_NAME, DDiagram.class, true);
        SWTBotGefEditPart eClass1EditPartBot = editor.getEditPart("EClass1", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart eClass2EditPartBot = editor.getEditPart("EClass2", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart ref1EditPartBot = editor.getEditPart("ref1", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart ref2EditPartBot = editor.getEditPart("ref2", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart ref3EditPartBot = editor.getEditPart("ref3", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart ref4EditPartBot = editor.getEditPart("ref4", AbstractDiagramContainerEditPart.class);
        SWTBotGefConnectionEditPart connection1EditPartBot = editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).get(0);
        SWTBotGefConnectionEditPart connection2EditPartBot = editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot).get(0);

        // Reconnect target of first connection
        PointList connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        Point from = connection1Points.getLastPoint();
        Point to = from.getCopy().setX(editor.getBounds(eClass2EditPartBot).x);
        connection1EditPartBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, connection1EditPartBot.part()));
        editor.drag(from, to);
        // Check that reconnection is correct
        assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).size());
        List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(ref1EditPartBot, eClass2EditPartBot);
        assertEquals(1, newConnection1EditPartBotList.size());
        SWTBotGefConnectionEditPart newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
        PointList newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection1Points.size());
        // Dimension delta = new Dimension(1, 2);
        assertEquals("After reconnection source end point is not at the correct position.", connection1Points.getFirstPoint(), newConnection1Points.getFirstPoint());
        assertEquals("After reconnection target end point is not at the correct position.", to, newConnection1Points.getLastPoint());
        connection1EditPartBot = newConnection1EditPartBot;

        // Reconnect target of second connection
        PointList connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = connection2Points.getLastPoint();
        to = from.getCopy().setX(editor.getBounds(eClass2EditPartBot).x);
        connection2EditPartBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, connection2EditPartBot.part()));
        editor.drag(from, to);
        // Check that reconnection is correct
        assertEquals(0, editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot).size());
        List<SWTBotGefConnectionEditPart> newConnection2EditPartBotList = editor.getConnectionEditPart(ref2EditPartBot, eClass2EditPartBot);
        assertEquals(1, newConnection2EditPartBotList.size());
        SWTBotGefConnectionEditPart newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
        PointList newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection2Points.size());
        GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection2Points.getFirstPoint(), newConnection2Points.getFirstPoint(), 1, 2);
        GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection2Points.getLastPoint(), 1, 2);
        connection2EditPartBot = newConnection2EditPartBot;

        // Reconnect target of first connection as initially
        connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = connection1Points.getLastPoint();
        to = from.getCopy().setX(editor.getBounds(eClass1EditPartBot).getRight().x - 2);
        connection1EditPartBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, connection1EditPartBot.part()));
        editor.drag(from, to);
        // Check that reconnection is correct
        assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass2EditPartBot).size());
        newConnection1EditPartBotList = editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot);
        assertEquals(1, newConnection1EditPartBotList.size());
        newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
        newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection1Points.size());
        GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection1Points.getFirstPoint(), newConnection1Points.getFirstPoint(), 1, 2);
        GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection1Points.getLastPoint(), 1, 2);
        connection1EditPartBot = newConnection1EditPartBot;
        
        // Reconnect target of second connection as initially
        connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = connection2Points.getLastPoint();
        to = from.getCopy().setX(editor.getBounds(eClass1EditPartBot).getRight().x - 2);
        connection2EditPartBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, connection2EditPartBot.part()));
        editor.drag(from, to);
        // Check that reconnection is correct
        assertEquals(0, editor.getConnectionEditPart(ref2EditPartBot, eClass2EditPartBot).size());
        newConnection2EditPartBotList = editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot);
        assertEquals(1, newConnection2EditPartBotList.size());
        newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
        newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection2Points.size());
        GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", connection2Points.getFirstPoint(), newConnection2Points.getFirstPoint(), 1, 2);
        GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", to, newConnection2Points.getLastPoint(), 1, 2);
        connection2EditPartBot = newConnection2EditPartBot;
        
        // Reconnect source of first connection
        connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = connection1Points.getFirstPoint();
        to = from.getCopy().setX(editor.getBounds(ref3EditPartBot).x);
        connection1EditPartBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, connection1EditPartBot.part()));
        editor.drag(from, to);
        // Check that reconnection is correct
        assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).size());
        newConnection1EditPartBotList = editor.getConnectionEditPart(ref3EditPartBot, eClass1EditPartBot);
        assertEquals(1, newConnection1EditPartBotList.size());
        newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
        newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection1Points.size());
        GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", to, newConnection1Points.getFirstPoint(), 0, 2);
        // GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", connection1Points.getLastPoint(),
        // newConnection1Points.getLastPoint(), 0, 2);
        connection1EditPartBot = newConnection1EditPartBot;

        // Reconnect source of second connection
        connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = connection2Points.getFirstPoint();
        to = from.getCopy().setX(editor.getBounds(ref4EditPartBot).x);
        connection2EditPartBot.select();
        bot.waitUntil(new CheckSelectedCondition(editor, connection2EditPartBot.part()));
        editor.drag(from, to);
        // Check that reconnection is correct
        assertEquals(0, editor.getConnectionEditPart(ref2EditPartBot, eClass1EditPartBot).size());
        newConnection2EditPartBotList = editor.getConnectionEditPart(ref4EditPartBot, eClass1EditPartBot);
        assertEquals(1, newConnection2EditPartBotList.size());
        newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
        newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection2Points.size());
        GraphicTestsSupportHelp.assertEquals("After reconnection source end point is not at the correct position.", to, newConnection2Points.getFirstPoint(), 0, 1);
        // GraphicTestsSupportHelp.assertEquals("After reconnection target end point is not at the correct position.", connection2Points.getLastPoint(),
        // newConnection2Points.getLastPoint(), 0, 1);
        connection2EditPartBot = newConnection2EditPartBot;
    }

    /**
     * Test reconnection of source edge port from a container to another with oblique style routing.
     */
    public void testEdgeWithPortSourceDnDWithObliqueStyleRouting() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION2_NAME, "new " + REPRESENTATION2_NAME, DDiagram.class, true);
        SWTBotGefEditPart c1EditPartBot = editor.getEditPart("c1", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart c11EditPartBot = editor.getEditPart("c11", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart c13EditPartBot = editor.getEditPart("c13", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart c131EditPartBot = editor.getEditPart("c131", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart c231EditPartBot = editor.getEditPart("c231", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart c111EditPartBot = editor.getEditPart("c111", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart c211EditPartBot = editor.getEditPart("c211", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefConnectionEditPart connection1EditPartBot = editor.getConnectionEditPart(c131EditPartBot, c231EditPartBot).get(0);
        SWTBotGefConnectionEditPart connection2EditPartBot = editor.getConnectionEditPart(c111EditPartBot, c211EditPartBot).get(0);

        // Reconnect source port of first connection
        PointList connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        Rectangle c131EditPartBounds = editor.getBounds(c131EditPartBot);
        Point from = c131EditPartBounds.getCenter();
        Point to = editor.getBounds(c1EditPartBot).getRight().setY(from.y).getTranslated(-5, 0);
        c131EditPartBot.select();
        editor.drag(from, to);
        // Check that port dnd is correct
        c131EditPartBot = editor.getEditPart("c131", AbstractDiagramBorderNodeEditPart.class);
        assertEquals(0, c13EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class)).size());
        List<SWTBotGefEditPart> borderNodesEditPartBot = c1EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class));
        borderNodesEditPartBot.remove(c111EditPartBot);
        assertEquals(2, borderNodesEditPartBot.size());
        List<SWTBotGefConnectionEditPart> newConnection1EditPartBotList = editor.getConnectionEditPart(c131EditPartBot, c231EditPartBot);
        assertEquals(1, newConnection1EditPartBotList.size());
        SWTBotGefConnectionEditPart newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
        PointList newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection1Points.size());
        assertEquals("After source port dnd source end point is not at the correct position.", to.getTranslated(5, 0), newConnection1Points.getFirstPoint());
        assertEquals("After source port dnd target end point is not at the correct position.", connection1Points.getLastPoint(), newConnection1Points.getLastPoint());
        connection1EditPartBot = newConnection1EditPartBot;

        // Reconnect source port of second connection
        PointList connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = editor.getBounds(c111EditPartBot).getCenter();
        to = editor.getBounds(c1EditPartBot).getRight().setY(from.y).getTranslated(-5, 0);
        c111EditPartBot.select();
        editor.drag(from, to);
        // Check that port dnd is correct
        c111EditPartBot = editor.getEditPart("c111", AbstractDiagramBorderNodeEditPart.class);
        assertEquals(0, c11EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class)).size());
        borderNodesEditPartBot = c1EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class));
        assertEquals(3, borderNodesEditPartBot.size());
        List<SWTBotGefConnectionEditPart> newConnection2EditPartBotList = editor.getConnectionEditPart(c111EditPartBot, c211EditPartBot);
        assertEquals(1, newConnection2EditPartBotList.size());
        SWTBotGefConnectionEditPart newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
        PointList newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection2Points.size());
        assertEquals("After source port dnd source end point is not at the correct position.", to.getTranslated(5, 0), newConnection2Points.getFirstPoint());
        assertEquals("After source port dnd target end point is not at the correct position.", connection2Points.getLastPoint(), newConnection2Points.getLastPoint());
        connection2EditPartBot = newConnection2EditPartBot;

        // Reconnect source port of first connection as initially
        connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = editor.getBounds(c131EditPartBot).getCenter();
        to = editor.getBounds(c13EditPartBot).getRight().setY(from.y).getTranslated(-5, 0);
        c131EditPartBot.select();
        editor.drag(from, to);
        // Check that port dnd is correct
        c131EditPartBot = editor.getEditPart("c131", AbstractDiagramBorderNodeEditPart.class);
        assertEquals(1, c13EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class)).size());
        borderNodesEditPartBot = c1EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class));
        borderNodesEditPartBot.remove(c131EditPartBot);
        assertEquals(2, borderNodesEditPartBot.size());
        newConnection1EditPartBotList = editor.getConnectionEditPart(c131EditPartBot, c231EditPartBot);
        assertEquals(1, newConnection1EditPartBotList.size());
        newConnection1EditPartBot = newConnection1EditPartBotList.get(0);
        newConnection1Points = ((AbstractConnectionEditPart) newConnection1EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection1Points.size());
        assertEquals("After source port dnd source end point is not at the correct position.", to.getTranslated(5, 0), newConnection1Points.getFirstPoint());
        assertEquals("After source port dnd target end point is not at the correct position.", connection1Points.getLastPoint(), newConnection1Points.getLastPoint());
        connection1EditPartBot = newConnection1EditPartBot;

        // Reconnect source port of second connection as initially
        connection2Points = ((AbstractConnectionEditPart) connection2EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
        from = editor.getBounds(c111EditPartBot).getCenter();
        to = editor.getBounds(c11EditPartBot).getRight().setY(from.y).getTranslated(-5, 0);
        c111EditPartBot.select();
        editor.drag(from, to);
        // Check that port dnd is correct
        c111EditPartBot = editor.getEditPart("c111", AbstractDiagramBorderNodeEditPart.class);
        assertEquals(1, c11EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class)).size());
        borderNodesEditPartBot = c1EditPartBot.descendants(IsInstanceOf.<EditPart> instanceOf(AbstractDiagramBorderNodeEditPart.class));
        borderNodesEditPartBot.remove(c111EditPartBot);
        borderNodesEditPartBot.remove(c131EditPartBot);
        assertEquals(1, borderNodesEditPartBot.size());
        newConnection2EditPartBotList = editor.getConnectionEditPart(c111EditPartBot, c211EditPartBot);
        assertEquals(1, newConnection2EditPartBotList.size());
        newConnection2EditPartBot = newConnection2EditPartBotList.get(0);
        newConnection2Points = ((AbstractConnectionEditPart) newConnection2EditPartBot.part()).getConnectionFigure().getPoints();
        assertEquals(2, newConnection2Points.size());
        assertEquals("After source port dnd source end point is not at the correct position.", to.getTranslated(5, 0), newConnection2Points.getFirstPoint());
        assertEquals("After source port dnd target end point is not at the correct position.", connection2Points.getLastPoint(), newConnection2Points.getLastPoint());
    }

    /**
     * Test that the selection change behavior is OK even after a reconnect tool that has an unexpected behavior, ie the
     * reconnect edge is no longer here after the execution of the reconnect tool.
     */
    public void testSelectionChangeBehaviorAfterAReconnectToolWithUnexpectedBehavior() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION4_NAME, "new " + REPRESENTATION4_NAME, DDiagram.class, true);
        try {
            // Access to property view
            SWTBotView propertiesView = bot.viewByTitle("Properties");
            propertiesView.setFocus();

            // Access to tab General
            SWTBotSiriusHelper.selectPropertyTabItem("General", propertiesView.bot());

            // Check that "root" item is displayed in the General tab of Properties view
            propertiesView.bot().tree(0).getTreeItem("root");

            SWTBotGefEditPart eClass1EditPartBot = editor.getEditPart("EClass1", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart eClass2EditPartBot = editor.getEditPart("EClass2", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart ref1EditPartBot = editor.getEditPart("ref1", AbstractDiagramContainerEditPart.class);
            SWTBotGefEditPart ref3EditPartBot = editor.getEditPart("ref3", AbstractDiagramContainerEditPart.class);
            SWTBotGefConnectionEditPart connection1EditPartBot = editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).get(0);

            // Reconnect source of a connection that "removes" the connection
            PointList connection1Points = ((AbstractConnectionEditPart) connection1EditPartBot.part()).getConnectionFigure().getPoints().getCopy();
            Point from = connection1Points.getFirstPoint();
            Point to = from.getCopy().setX(editor.getBounds(ref3EditPartBot).x);
            connection1EditPartBot.select();

            // Add a selection listener to detect wrong diagram selection during drag'n'drop
            NoDiagramSelectionListener selectionListener = new NoDiagramSelectionListener();
            EclipseUIUtil.getActivePage().addSelectionListener(selectionListener);
            try {
                editor.drag(from, to);
                // Check that reconnection no longer exists
                assertEquals(0, editor.getConnectionEditPart(ref1EditPartBot, eClass1EditPartBot).size());
                assertEquals(0, editor.getConnectionEditPart(ref3EditPartBot, eClass1EditPartBot).size());
                // Select another class to check that notifications are thrown, Properties view is used to check this.
                editor.select(eClass2EditPartBot);
                try {
                    // Check that "EClass2" item is displayed in the General tab of Properties view
                    propertiesView.bot().tree(0).getTreeItem("EClass2");
                } catch (WidgetNotFoundException e) {
                    fail("The properties view should be refreshed and the name of the selected class EClass2 should be dislayed in it.");
                }
            } finally {
                EclipseUIUtil.getActivePage().removeSelectionListener(selectionListener);
            }
        } finally {
            editor.restore();
        }
    }

    @Override
    public void tearDown() throws Exception {
        localSession.closeAndDiscardChanges();
        localSession = null;
        sessionAirdResource = null;
        super.tearDown();
    }

    /**
     * Check if an error message has been thrown by the selection listener and remove it from the active page.
     * 
     * @param selectionListener
     *            The selection listener to remove
     */
    private void removeSelectionListenerAndCheckIt(NoDiagramSelectionListener selectionListener) {
        EclipseUIUtil.getActivePage().removeSelectionListener(selectionListener);
        if (selectionListener.getError().isPresent()) {
            fail(selectionListener.getError().get());
        }
    }
}
