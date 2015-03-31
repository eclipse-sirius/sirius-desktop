/*******************************************************************************
 * Copyright (c) 2014, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot;

import static org.junit.Assert.assertNotEquals;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.AssertionFailedError;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.BaseSlidableAnchor;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.tools.internal.commands.UnpinElementsCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.IAbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation.ZoomLevel;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.BendpointMovedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * Ensures that when moving, dropping, aligning or arranging nodes, only the
 * latest or first segment of edges are modified.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class BendpointsStabilityOnMovesTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PROPERTIES_VIEW_ID = "org.eclipse.ui.views.PropertySheet";

    private static final String ROUTING_STYLE_STRAIGHT = "Straight";

    private static final String ROUTING_STYLE_MANHATTAN = "Manhattan";

    private static final String ROUTING_STYLE_STRAIGHT_BORDERED = "Straight_Bordered";

    private static final String ROUTING_STYLE_MANHATTAN_BORDERED = "Manhattan_Bordered";

    private Set<AssertionFailedError> failures = Sets.newLinkedHashSet();

    private SWTBotSiriusDiagramEditor diagramEditor;

    private static final String DATA_UNIT_DIR = "data/unit/bendpointsStability/";

    private static final String MODEL = "bendpointsStability.ecore";

    private static final String INTERACTION_MODEL = "bendpointsStability.interactions";

    private static final String SESSION_FILE = "bendpointsStability.aird";

    private static final String VSM = "bendpointsStability.odesign";

    private static final String DIAGRAM_DESCRIPTION_NAME = "bendpointsStabilityDiag";

    private static final String BORDERED_DIAGRAM_DESCRIPTION_NAME = "bendpointsStabilityBorderedDiag";

    private static final Point LITTLE_MOVE = new Point(10, 10);

    private static final Point BIG_MOVE = new Point(2000, 2000);

    private static final Point HORIZONTAL_MOVE = new Point(300, 0);

    private static final Point HORIZONTAL_MOVE_EDGE = new Point(10, 0);

    private static final Point HORIZONTAL_NEGATIV_MOVE_EDGE = new Point(-10, 0);

    private static final Point VERTICAL_MOVE = new Point(0, 300);

    private static final Point VERTICAL_MOVE_EDGE = new Point(0, 10);

    private static final Point VERTICAL_NEGATIV_MOVE_EDGE = new Point(0, -10);

    boolean isOutlineViewOpened;

    boolean isPropertiesViewOpened;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM, INTERACTION_MODEL);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);

        // "bendpointsStability.aird" has references to
        // "bendpointsStability.interactions" and "bendpointsStability.ecore"
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
     * Ensures that Sequence Diagrams are not impacted by this feature: when
     * moving nodes related to edges with bendpoints, all bendpoints should
     * move. Notice that as it is impossible to have bendpoints when sequence
     * diagram edges go from one lifeline to another, we only have to test
     * messages coming and going to the same lifeline.
     */
    public void testSequenceDiagrams() {
        localSession.getOpenedSession().getTransactionalEditingDomain().getCommandStack()
                .execute(new AddSemanticResourceCommand(localSession.getOpenedSession(), URI.createPlatformResourceURI(TEMP_PROJECT_NAME + "/" + INTERACTION_MODEL, true), new NullProgressMonitor()));
        SWTBotUtils.waitAllUiEvents();
        // Step 1: open sequence diagram editor
        SWTBotSiriusDiagramEditor sequenceDiagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Sequence Diagram on Interaction", "SequenceDiagram",
                SequenceDDiagram.class);
        try {

            // Step 2: try to move each node on the lifeline : all bendpoints of
            // related edges should move
            Set<SWTBotGefEditPart> editPartsToMove = Sets.newLinkedHashSet();
            Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints = Maps.newLinkedHashMap();

            for (SWTBotGefConnectionEditPart connectionEditPart : sequenceDiagramEditor.getConnectionsEditPart()) {
                editPartsToMove.add(connectionEditPart.source());
                editPartsToMove.add(connectionEditPart.target());
                PointList points = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
                previousBendPoints.put(connectionEditPart, points);
            }
            for (SWTBotGefEditPart editPartToMove : editPartsToMove) {
                sequenceDiagramEditor.select(editPartToMove);
                SWTBotUtils.waitAllUiEvents();
                Point initialLocation = sequenceDiagramEditor.getBounds(editPartToMove).getCenter();
                sequenceDiagramEditor.drag(initialLocation, new Point(initialLocation.x + 50, initialLocation.y + 50));
                SWTBotUtils.waitAllUiEvents();
                try {
                    compareActualBendpointsWithExpected(sequenceDiagramEditor, Lists.newArrayList(editPartToMove), previousBendPoints, true, true, false);
                } finally {
                    try {
                        undo("Move sequence node");
                    } catch (WidgetNotFoundException e) {
                        undo("InstanceRole move");
                    }
                }
            }

        } finally {
            sequenceDiagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Ensures that Bracket Edges are not impacted by this feature: when moving
     * nodes related to edges with bendpoints, all bendpoints should move.
     */
    public void testBracketEdges() {
        // Step 1: open bracket edges diagram editor
        SWTBotSiriusDiagramEditor bracketEdgesDiagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, "Bracket Edges Diagram",
                DDiagram.class);
        try {

            // Step 2: try to move the nodes: all bendpoints should move
            Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints = Maps.newLinkedHashMap();
            for (SWTBotGefConnectionEditPart connectionEditPart : bracketEdgesDiagramEditor.getConnectionsEditPart()) {
                PointList points = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
                previousBendPoints.put(connectionEditPart, points);
            }
            for (Point move : Lists.newArrayList(HORIZONTAL_MOVE, VERTICAL_MOVE)) {
                SWTBotGefEditPart editPartToMove = bracketEdgesDiagramEditor.getEditPart("B", IAbstractDiagramNodeEditPart.class);
                bracketEdgesDiagramEditor.select(editPartToMove);
                SWTBotUtils.waitAllUiEvents();
                Point initialLocation = bracketEdgesDiagramEditor.getBounds(editPartToMove).getCenter();
                bracketEdgesDiagramEditor.drag(initialLocation, new Point(initialLocation.x + move.x, initialLocation.y + move.y));
                SWTBotUtils.waitAllUiEvents();
                try {
                    compareActualBendpointsWithExpected(bracketEdgesDiagramEditor, Lists.newArrayList(editPartToMove), previousBendPoints, true, true, false);
                } finally {
                    undo("Set Location or Size");
                    SWTBotUtils.waitAllUiEvents();
                }
            }

        } finally {
            bracketEdgesDiagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Ensures that moving one node with a single target edge only updates the
     * last/first bendpoint of this edge, in various configuration (zoom level,
     * scrollbars, border nodes or not...).
     */
    public void testMoveOneNodeWithOneTargetEdge() {
        // In rectilinear mode, bendpoints must not be modified only while the
        // move does not require to create new bendpoints
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_50, false, true, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, false, true, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(LITTLE_MOVE), ZoomLevel.ZOOM_125, false, true, false,
                "NodeWithOneTargetEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, false,
                "NodeWithOneTargetEdgeAnd3Bendpoints");

        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_100, false, false, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, true, false, false,
                "NodeWithOneTargetEdgeAnd2Bendpoints");

    }

    /**
     * Ensures that moving one node with a single source edge only updates the
     * last/first bendpoint of this edge, in various configuration (zoom level,
     * scrollbars, border nodes or not...).
     */
    public void testMoveOneNodeWithOneSourceEdge() {
        // In rectilinear mode, bendpoints must not be modified only while the
        // move does not require to create new bendpoints
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_50, false, false, true,
                "NodeWithOneSourceEdgeAnd2Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_125, true, false, true,
                "NodeWithOneSourceEdgeAnd2Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(LITTLE_MOVE), ZoomLevel.ZOOM_100, false, false, true,
                "NodeWithOneSourceEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, false, true,
                "NodeWithOneSourceEdgeAnd1Bendpoints");

        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, true,
                "NodeWithOneSourceEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, true,
                "NodeWithOneSourceEdgeAnd3Bendpoints");

    }

    /**
     * Ensures that moving one node with a several target edges only updates the
     * last/first bendpoint of these edges, in various configuration (zoom
     * level, scrollbars, border nodes or not...).
     */
    public void testMoveOneNodeWithSeveralTargetEdges() {
        // In rectilinear mode, bendpoints must not be modified only while the
        // move does not require to create new bendpoints
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_50, false, true, true,
                "NodeWithSeveralTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(new Point(-5, -5)), ZoomLevel.ZOOM_50, false, true, true,
                "NodeWithSeveralTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN, ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true,
                true, true, "NodeWithSeveralTargetEdgeAnd1Bendpoints");

        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, false,
                "NodeWithSeveralTargetEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, false,
                "NodeWithSeveralTargetEdgeAnd3Bendpoints");

    }

    /**
     * Ensures that moving one node with a several source edges only updates the
     * last/first bendpoint of these edges, in various configuration (zoom
     * level, scrollbars, border nodes or not...).
     */
    public void testMoveOneNodeWithSeveralSourceEdges() {
        // In rectilinear mode, bendpoints must not be modified only while the
        // move does not require to create new bendpoints
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithSeveralSourceEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(HORIZONTAL_MOVE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithSeveralTargetsAndSources2");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true, true, false,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true, true, false,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");

        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, true, true, true,
                "NodeWithSeveralSourceEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true, true, true,
                "NodeWithSeveralSourceEdgeAnd1Bendpoints");

    }

    /**
     * Ensures that moving one node with a several source and target edges only
     * updates the last/first bendpoint of these edges in various configuration
     * (zoom level, scrollbars, border nodes or not...).
     */
    public void testMoveOneNodeWithSeveralTargetAndSourcesEdges() {
        // In rectilinear mode, bendpoints must not be modified only while the
        // move does not require to create new bendpoints
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(HORIZONTAL_MOVE), ZoomLevel.ZOOM_125, false, true, false,
                "NodeWithSeveralTargetEdgeAnd2Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(LITTLE_MOVE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, false,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");

        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_100, false, true, true,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true, false, true,
                "NodeWithSeveralSourceEdgeAnd3Bendpoints");

    }

    /**
     * Ensures that moving several nodes with same edges moves all bendpoints in
     * various configuration (zoom level, scrollbars, border nodes or not...).
     */
    public void testMoveSeveralNodesWithSameEdges() {
        // Here all moved nodes's incoming our outgoing edges have their source
        // and target in the moved nodes => all bendpoints should move
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_STRAIGHT, ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_100, true,
                false, true, "NodeWithOneTargetEdgeAnd1Bendpoints", "NodeWithOneSourceEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true, false, true,
                "NodeWithOneSourceEdgeAnd1Bendpoints", "NodeWithOneTargetEdgeAnd2Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, true, false, true,
                "NodeWithOneSourceEdgeAnd1Bendpoints", "NodeWithOneTargetEdgeAnd2Bendpoints");
    }

    /**
     * Ensures that moving several nodes with different edges only updates the
     * last/first bendpoint of these edges in various configuration (zoom level,
     * scrollbars, border nodes or not...).
     */
    public void testMoveSeveralNodesWithDifferentEdges() {
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN_BORDERED), Lists.newArrayList(new Point(-10, 0)), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithSeveralTargetsAndSources2", "NodeWithSeveralSourceEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_50, false, false, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints", "NodeWithOneTargetEdgeAnd2Bendpoints", "NodeWithSeveralTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, false, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints", "NodeWithOneTargetEdgeAnd2Bendpoints", "NodeWithSeveralTargetEdgeAnd1Bendpoints");

        // Here some of the moved nodes are source or target of edges not
        // contained in the other moved nodes
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, false, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints", "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithSeveralTargetEdgeAnd1Bendpoints");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithOneTargetEdgeAnd1Bendpoints", "NodeWithOneTargetEdgeAnd2Bendpoints", "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithSeveralTargetEdgeAnd1Bendpoints");

    }

    /**
     * Ensures that moving one container with edges only updates the last/first
     * bendpoint of this edge, in various configuration (zoom level,
     * scrollbars,border nodes or not...).
     */
    public void testMoveContainerWithEdges() {
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, false, false, "EmptyContainer");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(HORIZONTAL_MOVE), ZoomLevel.ZOOM_50, false, false, false,
                "EmptyContainer");
    }

    /**
     * Ensures that moving one container with nodes having edges only updates
     * the last/first bendpoint of this edge, in various configuration (zoom
     * level, scrollbars, border nodes or not...).
     */
    public void testMoveContainerWithNodesHavingEdges() {
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_MANHATTAN), Lists.newArrayList(VERTICAL_MOVE), ZoomLevel.ZOOM_125, false, true, true,
                "NodeWithOneSourceEdgeInContainer");
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_50, false, true, true,
                "NodeWithOneSourceEdgeInContainer");
    }

    /**
     * Ensures that moving container nodes only updates the last/first bendpoint
     * of this edge, in various configuration (zoom level, scrollbars, border
     * nodes or not...).
     */
    public void testMoveSeveralContainersWithDifferentEdges() {
        doTestMoveNodesOnlyMovesFirstOrLastBendpoint(true, true, Lists.newArrayList(ROUTING_STYLE_STRAIGHT, ROUTING_STYLE_MANHATTAN), Lists.newArrayList(BIG_MOVE), ZoomLevel.ZOOM_100, false, true,
                true, "ContainerWithOneNode", "Scrollbar", "DragAndDropTarget", "ContainerWithOneNode2");
    }

    /**
     * Ensures that center edges keeps center after moving one of its extremity
     * node in various configuration (zoom level, scrollbars, border nodes or
     * not...).
     */
    public void testCenterEdges() {
        // Step 1: open diagram editor
        SWTBotSiriusDiagramEditor rectilinearDiagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, "CenteredEdge",
                DSemanticDiagram.class);
        rectilinearDiagramEditor.zoom(ZoomLevel.ZOOM_50);
        try {
            for (String editPartToMove : Lists.newArrayList("A", "B")) {
                // Step 2: move nodes, edge should always arrive on center
                SWTBotGefEditPart editParWithSource = rectilinearDiagramEditor.getEditPart(editPartToMove, IAbstractDiagramNodeEditPart.class);
                rectilinearDiagramEditor.select(editParWithSource);
                SWTBotUtils.waitAllUiEvents();
                Collection<Point> moveSourceDeltas = Lists.newArrayList();
                moveSourceDeltas.add(new Point(50, 50));
                moveSourceDeltas.add(new Point(500, 500));
                moveSourceDeltas.add(new Point(500, -500));
                moveSourceDeltas.add(new Point(50, -500));
                for (Point moveSourceDelta : moveSourceDeltas) {
                    Point initialLocation = rectilinearDiagramEditor.getBounds(editParWithSource).getCenter();
                    rectilinearDiagramEditor.drag(initialLocation, new Point(initialLocation.x + moveSourceDelta.x, initialLocation.y + moveSourceDelta.y));
                    SWTBotUtils.waitAllUiEvents();
                    try {
                        for (SWTBotGefConnectionEditPart connectionEditPart : rectilinearDiagramEditor.getConnectionsEditPart()) {
                            PolylineConnectionEx edgeFigure = ((PolylineConnectionEx) connectionEditPart.part().getFigure());
                            assertEquals("Edge is not centered after a move at the source side", "(0.5,0.5)", ((BaseSlidableAnchor) edgeFigure.getSourceAnchor()).getTerminal());
                            assertEquals("Edge is not centered after a move at the target side", "(0.5,0.5)", ((BaseSlidableAnchor) edgeFigure.getTargetAnchor()).getTerminal());
                        }
                    } finally {
                        undo("Set Location or Size");
                        SWTBotUtils.waitAllUiEvents();
                    }
                }
            }
        } finally {
            rectilinearDiagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Tests an advanced use case specific to rectilinear rooting : check that
     * when bendpoints do move, only the last 2 bendpoints are updated.
     */
    public void testAdvancedRectilinearUseCases() {
        // Step 1: open diagram editor
        SWTBotSiriusDiagramEditor rectilinearDiagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), DIAGRAM_DESCRIPTION_NAME, "AdvancedRectilinear",
                DSemanticDiagram.class);
        rectilinearDiagramEditor.zoom(ZoomLevel.ZOOM_50);
        PointList previousBendPoints = ((PolylineConnectionEx) rectilinearDiagramEditor.getConnectionsEditPart().get(0).part().getFigure()).getPoints();

        try {
            // Step 2: when moving edge source only the 2 first points should
            // change
            SWTBotGefEditPart editParWithSource = rectilinearDiagramEditor.getEditPart("B", IAbstractDiagramNodeEditPart.class);
            rectilinearDiagramEditor.select(editParWithSource);
            SWTBotUtils.waitAllUiEvents();
            Collection<Point> moveSourceDeltas = Lists.newArrayList();
            moveSourceDeltas.add(new Point(50, 50));
            moveSourceDeltas.add(new Point(500, 500));
            moveSourceDeltas.add(new Point(500, -500));
            moveSourceDeltas.add(new Point(50, -500));
            for (Point moveSourceDelta : moveSourceDeltas) {
                Point initialLocation = rectilinearDiagramEditor.getBounds(editParWithSource).getCenter();
                rectilinearDiagramEditor.drag(initialLocation, new Point(initialLocation.x + moveSourceDelta.x, initialLocation.y + moveSourceDelta.y));
                SWTBotUtils.waitAllUiEvents();
                PointList actualBendPoints = ((PolylineConnectionEx) rectilinearDiagramEditor.getConnectionsEditPart().get(0).part().getFigure()).getPoints();
                try {
                    assertNotEquals("First bendpoint should have move", previousBendPoints.getFirstPoint(), actualBendPoints.getFirstPoint());
                    assertNotEquals("Second bendpoint should have move", previousBendPoints.getPoint(1), actualBendPoints.getPoint(1));
                    for (int i = 2; i < previousBendPoints.size(); i++) {
                        assertEquals("All bendpoints except 2 first ones should not have move", previousBendPoints.getPoint(i), actualBendPoints.getPoint(i));
                    }
                } finally {
                    undo("Set Location or Size");
                    SWTBotUtils.waitAllUiEvents();
                }
            }

            // Step 3: when moving edge target only the 2 last points should
            // change
            SWTBotGefEditPart editParWithTarget = rectilinearDiagramEditor.getEditPart("A", IAbstractDiagramNodeEditPart.class);
            rectilinearDiagramEditor.select(editParWithTarget);
            SWTBotUtils.waitAllUiEvents();
            Collection<Point> moveTargetDeltas = Lists.newArrayList();
            moveTargetDeltas.add(new Point(50, 5));
            moveTargetDeltas.add(new Point(500, 5));
            moveTargetDeltas.add(new Point(500, 500));
            moveTargetDeltas.add(new Point(50, 500));
            for (Point moveTargetDelta : moveTargetDeltas) {
                Point initialLocation = rectilinearDiagramEditor.getBounds(editParWithTarget).getCenter();
                rectilinearDiagramEditor.drag(initialLocation, new Point(initialLocation.x + moveTargetDelta.x, initialLocation.y + moveTargetDelta.y));
                SWTBotUtils.waitAllUiEvents();
                PointList actualBendPoints = ((PolylineConnectionEx) rectilinearDiagramEditor.getConnectionsEditPart().get(0).part().getFigure()).getPoints();
                try {
                    assertNotEquals("Last bendpoint should have move", previousBendPoints.getLastPoint(), actualBendPoints.getFirstPoint());
                    assertNotEquals("Last but one bendpoint should have move", previousBendPoints.getPoint(previousBendPoints.size() - 2), actualBendPoints.getPoint(previousBendPoints.size() - 2));
                    for (int i = 0; i < previousBendPoints.size() - 2; i++) {
                        assertEquals("All bendpoints except 2 last ones should not have move", previousBendPoints.getPoint(i), actualBendPoints.getPoint(i));
                    }
                } finally {
                    undo("Set Location or Size");
                    SWTBotUtils.waitAllUiEvents();
                }
            }

        } finally {
            rectilinearDiagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Ensure that only the first or last bendpoint is moved when moving one of
     * edge extremity in various configuration (zoom level, edge style, ...)
     */
    public void testMoveEdgeExtremity() {
        doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(HORIZONTAL_MOVE_EDGE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithOneTargetEdgeAnd3Bendpoints");
        doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(HORIZONTAL_NEGATIV_MOVE_EDGE), ZoomLevel.ZOOM_125, false, false,
                false, "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithOneTargetEdgeAnd3Bendpoints");
        doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(false, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(HORIZONTAL_MOVE_EDGE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithOneTargetEdgeAnd3Bendpoints");
        doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(false, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT), Lists.newArrayList(HORIZONTAL_NEGATIV_MOVE_EDGE), ZoomLevel.ZOOM_125, false, false,
                false, "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithOneTargetEdgeAnd3Bendpoints");
        doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(VERTICAL_MOVE_EDGE), ZoomLevel.ZOOM_125, false, false, false,
                "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithOneTargetEdgeAnd3Bendpoints");
        doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(true, false, Lists.newArrayList(ROUTING_STYLE_STRAIGHT_BORDERED), Lists.newArrayList(VERTICAL_NEGATIV_MOVE_EDGE), ZoomLevel.ZOOM_125, false,
                false, false, "NodeWithOneSourceEdgeAnd3Bendpoints", "NodeWithOneTargetEdgeAnd3Bendpoints");
    }

    /**
     * Ensures that by dragging or arranging edit parts with the given names,
     * bendpoints are moved as expected.
     * 
     * @param firstOrLastBendPointShouldMove
     *            indicates if the first (if an edge source has been moved) or
     *            the last (if an edge target has been moved) bendpoint should
     *            move
     * @param allOtherBendpointsShouldMove
     *            indicates if any of the non-last bendpoints should move
     * @param routingStylesToTest
     *            used to determine on which diagram(s) we should perform this
     *            test
     * @param moveDeltas
     *            all the move deltas to use for dragging elements.
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param snapToGrid
     *            indicates whether snap to grid should be active
     * @param horizontalScrollbar
     *            indicates whether we should drop elements in order to make
     *            horizontal scrollbar appear
     * @param verticalScrollBar
     *            indicates whether we should drop elements in order to make
     *            vertical scrollbar appear
     * @param nodesToMove
     *            name of the Edit parts to move
     */
    private void doTestMoveNodesOnlyMovesFirstOrLastBendpoint(boolean firstOrLastBendPointShouldMove, boolean allOtherBendpointsShouldMove, Collection<String> routingStylesToTest,
            Collection<Point> moveDeltas, ZoomLevel zoomLevel, boolean snapToGrid, boolean horizontalScrollbar, boolean verticalScrollBar, String... nodesToMove) {
        for (String routingStyle : routingStylesToTest) {
            for (Point moveDelta : moveDeltas) {
                // Step 1: Open the corresponding diagram
                diagramEditor = setUpEditorAccordingToDimensions(routingStyle, zoomLevel, snapToGrid, horizontalScrollbar, verticalScrollBar);
                diagramEditor.reveal(nodesToMove[0]);
                try {
                    // Step 2: storing the previous
                    // bendpoints
                    Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints = Maps.newLinkedHashMap();
                    Collection<SWTBotGefEditPart> editPartsToMove = Sets.newLinkedHashSet();
                    for (String nodeToMove : nodesToMove) {
                        SWTBotGefEditPart nodeEditPart = diagramEditor.getEditPart(nodeToMove, IAbstractDiagramNodeEditPart.class);
                        editPartsToMove.add(nodeEditPart);

                        Collection<SWTBotGefConnectionEditPart> connectionsEditPart = getAllConnectionsEditPart(nodeEditPart);
                        for (SWTBotGefConnectionEditPart connectionEditPart : connectionsEditPart) {
                            PointList points = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
                            previousBendPoints.put(connectionEditPart, points);
                        }
                    }

                    try {
                        // Step 3: arrange selection and check bendpoints
                        // Notice that we will not test arrange selection on
                        // rectilinear edges as we are sure that bendpoints will
                        // move
                        if (!(routingStyle.contains(ROUTING_STYLE_MANHATTAN))) {
                            doArrangeSelectionAndCheckBendpoints(firstOrLastBendPointShouldMove, allOtherBendpointsShouldMove, diagramEditor, editPartsToMove, previousBendPoints, snapToGrid);
                        }

                        // Step 4: Drag nodes and check bendpoints
                        doPerformMoveAndCheckBendpoints(firstOrLastBendPointShouldMove, allOtherBendpointsShouldMove, diagramEditor, editPartsToMove, moveDelta, previousBendPoints);

                        // Step 5: if more than one edit part is selected,
                        // launch "Align" actions and check bendpoints
                        // It is not possible to align border nodes
                        if (editPartsToMove.size() > 1 && !(routingStyle.contains("Bordered"))) {
                            doAlignAndCheckBendpoints(firstOrLastBendPointShouldMove, allOtherBendpointsShouldMove, diagramEditor, editPartsToMove, previousBendPoints);
                        }
                    } catch (AssertionError e) {
                        failures.add(new AssertionFailedError("[ZoomLevel : " + zoomLevel + ", Routing-Style: " + routingStyle + ", MoveDelta: " + moveDelta + ", SnapToGrid:" + snapToGrid
                                + ", Horizontal-Scroll " + horizontalScrollbar + ", Vertical-Scroll: " + verticalScrollBar + "] " + e.getMessage()));
                        failures.add(new AssertionFailedError("[ZoomLevel : " + zoomLevel + ", Routing-Style: " + routingStyle + ", MoveDelta: " + moveDelta + ", SnapToGrid:" + snapToGrid
                                + ", Horizontal-Scroll " + horizontalScrollbar + ", Vertical-Scroll: " + verticalScrollBar + "] " + e.getMessage()));
                    }
                } finally {
                    if (horizontalScrollbar || verticalScrollBar) {
                        undo(localSession.getOpenedSession());
                        SWTBotUtils.waitAllUiEvents();
                    }
                }
            }
            diagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
        if (!failures.isEmpty()) {
            throw new AssertionFailedError(failures.size() + " failures. First one is : " + failures.iterator().next().getMessage());
        }
    }

    /**
     * Ensures that by moving one of edge extremities, bendpoints are moved as
     * expected.
     * 
     * @param moveSourceBendpoint
     *            indicates if the first (if an edge source has been moved) or
     *            the last (if an edge target has been moved) bendpoint should
     *            move
     * @param allOtherBendpointsShouldMove
     *            indicates if any of the non-last bendpoints should move
     * @param routingStylesToTest
     *            used to determine on which diagram(s) we should perform this
     *            test
     * @param moveDeltas
     *            all the move deltas to use for dragging edge extremities
     * @param zoomLevel
     *            the {@link ZoomLevel} to use for this test
     * @param snapToGrid
     *            indicates whether snap to grid should be active
     * @param horizontalScrollbar
     *            indicates whether we should drop elements in order to make
     *            horizontal scrollbar appear
     * @param verticalScrollBar
     *            indicates whether we should drop elements in order to make
     *            vertical scrollbar appear
     * @param sourceAndTargetOfEdgeToMove
     *            name of the source and the target to move
     */
    private void doTestMoveEdgesOnlyMovesFirstOrLastBendpoint(boolean moveSourceBendpoint, boolean allOtherBendpointsShouldMove, Collection<String> routingStylesToTest, Collection<Point> moveDeltas,
            ZoomLevel zoomLevel, boolean snapToGrid, boolean horizontalScrollbar, boolean verticalScrollBar, String... sourceAndTargetOfEdgeToMove) {
        for (String routingStyle : routingStylesToTest) {
            for (Point moveDelta : moveDeltas) {
                // Step 1: Open the corresponding diagram
                diagramEditor = setUpEditorAccordingToDimensions(routingStyle, zoomLevel, snapToGrid, horizontalScrollbar, verticalScrollBar);
                if (moveSourceBendpoint) {
                    diagramEditor.reveal(sourceAndTargetOfEdgeToMove[0]);
                } else {
                    diagramEditor.reveal(sourceAndTargetOfEdgeToMove[1]);
                }
                try {
                    // Step 2: storing the previous bendpoints
                    Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints = Maps.newLinkedHashMap();
                    Collection<SWTBotGefEditPart> editPartsToMove = Sets.newLinkedHashSet();
                    for (int i = 0; i < sourceAndTargetOfEdgeToMove.length; i += 2) {
                        SWTBotGefEditPart sourceNodeEditPart = diagramEditor.getEditPart(sourceAndTargetOfEdgeToMove[i], IAbstractDiagramNodeEditPart.class);
                        SWTBotGefEditPart targetNodeEditPart = diagramEditor.getEditPart(sourceAndTargetOfEdgeToMove[i + 1], IAbstractDiagramNodeEditPart.class);
                        List<SWTBotGefConnectionEditPart> connectionEditParts = diagramEditor.getConnectionEditPart(sourceNodeEditPart, targetNodeEditPart);
                        for (SWTBotGefConnectionEditPart connectionEditPart : connectionEditParts) {
                            editPartsToMove.add(connectionEditPart);
                            PointList points = ((PolylineConnectionEx) connectionEditPart.part().getFigure()).getPoints();
                            previousBendPoints.put(connectionEditPart, points);
                        }
                    }

                    try {
                        // Step 3: Drag edge extremity and check bendpoints
                        doPerformMoveEndBendpointAndCheckBendpoints(moveSourceBendpoint, allOtherBendpointsShouldMove, diagramEditor, editPartsToMove, moveDelta, zoomLevel, previousBendPoints);
                    } catch (AssertionFailedError e) {
                        failures.add(new AssertionFailedError("[ZoomLevel : " + zoomLevel + ", Routing-Style: " + routingStyle + ", MoveDelta: " + moveDelta + ", SnapToGrid:" + snapToGrid
                                + ", Horizontal-Scroll " + horizontalScrollbar + ", Vertical-Scroll: " + verticalScrollBar + "] " + e.getMessage()));
                        failures.add(new AssertionFailedError("[ZoomLevel : " + zoomLevel + ", Routing-Style: " + routingStyle + ", MoveDelta: " + moveDelta + ", SnapToGrid:" + snapToGrid
                                + ", Horizontal-Scroll " + horizontalScrollbar + ", Vertical-Scroll: " + verticalScrollBar + "] " + e.getMessage()));
                    }
                } finally {
                    if (horizontalScrollbar || verticalScrollBar) {
                        undo(localSession.getOpenedSession());
                        SWTBotUtils.waitAllUiEvents();
                    }
                }
            }
            diagramEditor.close();
            SWTBotUtils.waitAllUiEvents();
        }
        if (!failures.isEmpty()) {
            throw new AssertionFailedError(failures.size() + " failures. First one is : " + failures.iterator().next().getMessage());
        }
    }

    /**
     * @param moveSourceBendpoint
     *            indicates if the first (if an edge source has been moved) or
     *            the last (if an edge target has been moved) bendpoint should
     *            move
     * @param allOtherBendpointsShouldMove
     *            indicates if any of the non-last bendpoints should move
     * @param diagramEditor
     *            the currently active {@link SWTBotSiriusDiagramEditor}
     * @param editPartsToMove
     *            the editParts to drag
     * @param moveDelta
     *            the delta of the move
     * @param zoomLevel
     *            the zoom level
     * @param previousBendPoints
     *            the state of the bendpoints before launching the drag
     */
    protected void doPerformMoveEndBendpointAndCheckBendpoints(boolean moveSourceBendpoint, boolean allOtherBendpointsShouldMove, final SWTBotSiriusDiagramEditor diagramEditor,
            final Collection<SWTBotGefEditPart> editPartsToMove, Point moveDelta, ZoomLevel zoomLevel, final Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints) {
        diagramEditor.select(editPartsToMove);
        final ConnectionEditPart cep = (ConnectionEditPart) editPartsToMove.iterator().next().part();
        Point initialLocation = null;
        if (moveSourceBendpoint) {
            initialLocation = previousBendPoints.get(editPartsToMove.iterator().next()).getFirstPoint();
        } else {
            initialLocation = previousBendPoints.get(editPartsToMove.iterator().next()).getLastPoint();
        }
        final Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);
        initialLocation.performScale(zoomLevel.getAmount());
        targetLocation.performScale(zoomLevel.getAmount());
        // Perform drag
        diagramEditor.drag(initialLocation, targetLocation);
        SWTBotUtils.waitAllUiEvents();

        if (moveSourceBendpoint) {
            bot.waitUntil(new BendpointMovedCondition(cep, initialLocation).checkFirstBendpoint());
        } else {
            bot.waitUntil(new BendpointMovedCondition(cep, initialLocation).checkLastBendpoint());
        }
        try {
            assertNotSame("Edit parts were not correctly dragged ", initialLocation, diagramEditor.getBounds(editPartsToMove.iterator().next()).getCenter());

            // Get the new bendpoints list and compare it to expected
            compareActualBendpointsWithExpected(diagramEditor, previousBendPoints, moveSourceBendpoint, allOtherBendpointsShouldMove, false);
        } finally {
            // Undo move to restore clean state
            try {
                undo("Change Connection Target");
            } catch (WidgetNotFoundException e) {
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * @param firstOrLastBendPointShouldMove
     *            indicates if the first (if an edge source has been moved) or
     *            the last (if an edge target has been moved) bendpoint should
     *            move
     * @param allOtherBendpointsShouldMove
     *            indicates if any of the non-last bendpoints should move
     * @param diagramEditor
     *            the currently active {@link SWTBotSiriusDiagramEditor}
     * @param editPartsToMove
     *            the editParts to drag
     * @param moveDelta
     *            the delta of the move.
     * @param previousBendPoints
     *            the state of the bendpoints before launching the arrange
     *            selection
     */
    protected void doPerformMoveAndCheckBendpoints(boolean firstOrLastBendPointShouldMove, boolean allOtherBendpointsShouldMove, SWTBotSiriusDiagramEditor diagramEditor,
            Collection<SWTBotGefEditPart> editPartsToMove, Point moveDelta, Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints) {
        diagramEditor.select(editPartsToMove);
        diagramEditor.reveal(editPartsToMove.iterator().next().part());
        SWTBotUtils.waitAllUiEvents();
        List<SWTBotGefEditPart> selectedEditParts = diagramEditor.selectedEditParts();
        Point initialLocation = diagramEditor.getBounds(editPartsToMove.iterator().next()).getCenter();
        Point targetLocation = new Point(initialLocation.x + moveDelta.x, initialLocation.y + moveDelta.y);

        // Perform drag
        diagramEditor.drag(initialLocation, targetLocation);
        SWTBotUtils.waitAllUiEvents();
        try {
            assertEquals("Drag as failed: selection should be the same before and after drag.", selectedEditParts, diagramEditor.selectedEditParts());
            // Get the new bendpoints list and compare it to expected
            compareActualBendpointsWithExpected(diagramEditor, editPartsToMove, previousBendPoints, firstOrLastBendPointShouldMove, allOtherBendpointsShouldMove, false);
        } finally {
            // Undo move to restore clean state
            try {
                undo("Move Element");
            } catch (WidgetNotFoundException e) {
                try {
                    undo("Set Location or Size");
                } catch (WidgetNotFoundException e2) {

                }
            }
            SWTBotUtils.waitAllUiEvents();
        }
    }

    /**
     * Does the following:
     * <ul>
     * <li>Pin all elements, and launch an arrange selection on all given edit
     * parts: no edit part should move</li>
     * <li>Unpin all elements, and launch an arrange selection on all given edit
     * parts: only first or last bendpoints should move
     * <li>
     * </ul>
     * 
     * @param firstOrLastBendPointShouldMove
     *            indicates if the first (if an edge source has been moved) or
     *            the last (if an edge target has been moved) bendpoint should
     *            move
     * @param allOtherBendpointsShouldMove
     *            indicates if any of the non-last bendpoints should move
     * @param diagramEditor
     *            the currently active {@link SWTBotSiriusDiagramEditor}
     * @param editPartsToArrange
     *            the edit parts to select before launching an arrange selection
     * @param previousBendPoints
     *            the state of the bendpoints before launching the arrange
     *            selection
     * @param snapToGrid
     *            indicates if snap to grid is active or not
     */
    protected void doArrangeSelectionAndCheckBendpoints(boolean firstOrLastBendPointShouldMove, boolean allOtherBendpointsShouldMove, SWTBotSiriusDiagramEditor diagramEditor,
            Collection<SWTBotGefEditPart> editPartsToArrange, Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints, boolean snapToGrid) {
        TransactionalEditingDomain domain = localSession.getOpenedSession().getTransactionalEditingDomain();
        Collection<DDiagramElement> diagramElements = new DDiagramQuery((DDiagram) ((DialectEditor) diagramEditor.getReference().getEditor(false)).getRepresentation()).getAllDiagramElements();

        // Step 1: Pin all elements and launch arrange selection
        // TODO this first test should be made also if snap to grid is active
        // (see bugzilla 440762) Remove the "snapToGrid" parameter once it will
        // be solved
        if (!snapToGrid) {
            domain.getCommandStack().execute(new PinElementsCommand(diagramElements));
            try {
                diagramEditor.select(editPartsToArrange);
                SWTBotUtils.waitAllUiEvents();
                bot.toolbarDropDownButtonWithTooltip("Arrange Selection").click();
                SWTBotUtils.waitAllUiEvents();
                try {
                    // => no bendpoint should have changed
                    compareActualBendpointsWithExpected(diagramEditor, editPartsToArrange, previousBendPoints, false, false, true);
                } finally {
                    undo("Arrange Selection");
                }
            } finally {
                undo("Pin elements");
            }
        }

        // Step 2: unpin all elements and launch arrange selection
        domain.getCommandStack().execute(new UnpinElementsCommand(diagramElements));
        try {
            diagramEditor.select(editPartsToArrange);
            SWTBotUtils.waitAllUiEvents();
            bot.toolbarDropDownButtonWithTooltip("Arrange Selection").click();
            try {
                SWTBotUtils.waitAllUiEvents();
                compareActualBendpointsWithExpected(diagramEditor, editPartsToArrange, previousBendPoints, firstOrLastBendPointShouldMove, allOtherBendpointsShouldMove, true);
            } finally {
                undo("Arrange Selection");
            }
        } finally {
            undo("Unpin elements");
        }
    }

    /**
     * Launch an align on the given edit parts and check if bendpoints have
     * moved as expected.
     * 
     * @param firstOrLastBendPointShouldMove
     *            indicates if the first (if an edge source has been moved) or
     *            the last (if an edge target has been moved) bendpoint should
     *            move
     * @param allOtherBendpointsShouldMove
     *            indicates if any of the non-last bendpoints should move
     * @param diagramEditor
     *            the currently active {@link SWTBotSiriusDiagramEditor}
     * @param editPartsToAlign
     *            the edit parts to select before launching align actions
     * @param previousBendPoints
     *            the state of the bendpoints before launching the arrange
     *            selection
     */
    protected void doAlignAndCheckBendpoints(boolean firstOrLastBendPointShouldMove, boolean allOtherBendpointsShouldMove, SWTBotSiriusDiagramEditor diagramEditor,
            Collection<SWTBotGefEditPart> editPartsToAlign, Map<SWTBotGefConnectionEditPart, PointList> previousBendPoints) {
        Collection<String> alignKinds = Lists.newArrayList("Bottom", "Middle", "Top");
        for (String alignKind : alignKinds) {
            diagramEditor.select(editPartsToAlign);
            diagramEditor.clickContextMenu(alignKind);
            try {
                SWTBotUtils.waitAllUiEvents();
                compareActualBendpointsWithExpected(diagramEditor, editPartsToAlign, previousBendPoints, firstOrLastBendPointShouldMove, allOtherBendpointsShouldMove, true);
            } finally {
                undo(localSession.getOpenedSession());
                SWTBotUtils.waitAllUiEvents();
            }
        }
    }

    /**
     * Returns an editor set up according to the given test dimensions.
     * 
     * @param routingStyle
     *            the routing style
     * @param zoomLevel
     *            the zoom level
     * @param snapToGrid
     *            indicates whether snap to grid should be active
     * @param horizontalScrollbar
     *            indicates whether we should drop elements in order to make
     *            horizontal scrollbar appear
     * @param verticalScrollBar
     *            indicates whether we should drop elements in order to make
     *            vertical scrollbar appear
     * @return
     */
    private SWTBotSiriusDiagramEditor setUpEditorAccordingToDimensions(String routingStyle, ZoomLevel zoomLevel, boolean snapToGrid, boolean horizontalScrollbar, boolean verticalScrollBar) {
        String diagramDescriptionName = DIAGRAM_DESCRIPTION_NAME;
        if (routingStyle.contains("Bordered")) {
            diagramDescriptionName = BORDERED_DIAGRAM_DESCRIPTION_NAME;
        }
        SWTBotSiriusDiagramEditor diagramEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), diagramDescriptionName, routingStyle + " Edges Diagram",
                DDiagram.class);
        diagramEditor.zoom(zoomLevel);
        diagramEditor.setSnapToGrid(snapToGrid, 100, 2);
        handleScrollbar(diagramEditor, horizontalScrollbar, verticalScrollBar);
        return diagramEditor;
    }

    private void compareActualBendpointsWithExpected(SWTBotSiriusDiagramEditor diagramEditor, Collection<SWTBotGefEditPart> movedNodes, Map<SWTBotGefConnectionEditPart, PointList> expectedBendPoints,
            boolean firstOrLastBendPointShouldMove, boolean allOtherBendpointsShouldMove, boolean acceptNonMovedBendpoints) {
        // For Manhattan Diagrams not all bendpoints must move
        acceptNonMovedBendpoints = acceptNonMovedBendpoints || diagramEditor.getReference().getName().contains(ROUTING_STYLE_MANHATTAN);
        for (Entry<SWTBotGefConnectionEditPart, PointList> expectedEntry : expectedBendPoints.entrySet()) {
            SWTBotUtils.waitAllUiEvents();
            PointList actualBendPoints = ((PolylineConnectionEx) expectedEntry.getKey().part().getFigure()).getPoints();
            // First bendpoint should move only if the source has moved
            if (firstOrLastBendPointShouldMove && movedNodes.contains(expectedEntry.getKey().source()) || allOtherBendpointsShouldMove) {
                if (!acceptNonMovedBendpoints && !allOtherBendpointsShouldMove) {
                    assertNotEquals("First  Bendpoint should have moved", expectedEntry.getValue().getFirstPoint(), actualBendPoints.getFirstPoint());
                }
            } else if (expectedEntry.getValue().size() > 2 && !acceptNonMovedBendpoints) {
                // If there is only 2 bendpoints the first bendpoint can also
                // moved (change of side).
                assertEquals("First Bendpoint should not have moved", expectedEntry.getValue().getFirstPoint(), actualBendPoints.getFirstPoint());
            }
            if (!allOtherBendpointsShouldMove) {
                if (expectedEntry.getValue().size() != actualBendPoints.size()) {
                    // Theoretically no bendpoint should have been added or
                    // removed, but there is some specific cases
                    // If the edge has 2 bendpoints before move, it is possible
                    // that the move has created new bendpoints (in case where
                    // it will be not visible because it is only over the node).
                    if (expectedEntry.getValue().size() > 2) {
                        boolean atLeastOneBendpointUnderAMovedNode = false;
                        // Check if the bendpoint is under the node (in this
                        // case it is ignore by draw2d and it can explain a
                        // "regular" difference
                        for (int i = 1; i < (expectedEntry.getValue().size() - 1); i++) {
                            if (!atLeastOneBendpointUnderAMovedNode) {
                                final Point point = expectedEntry.getValue().getPoint(i);
                                atLeastOneBendpointUnderAMovedNode = !(Iterables.all(movedNodes, new Predicate<SWTBotGefEditPart>() {
                                    @Override
                                    public boolean apply(SWTBotGefEditPart input) {
                                        return !((GraphicalEditPart) input.part()).getFigure().getBounds().contains(point);
                                    }
                                }));
                            }
                        }
                        if (!atLeastOneBendpointUnderAMovedNode) {
                            assertEquals("No bendpoint should have been added or removed", expectedEntry.getValue().size(), actualBendPoints.size());
                        }
                    }
                }
            }
            boolean atLeastOneBendpointIgnored = false;
            int delta = 0;
            for (int i = 1; i < expectedEntry.getValue().size() - 1; i++) {
                if (!allOtherBendpointsShouldMove) {
                    final Point point = expectedEntry.getValue().getPoint(i);
                    boolean atLeastOneBendpointUnderAMovedNode = !(Iterables.all(movedNodes, new Predicate<SWTBotGefEditPart>() {
                        @Override
                        public boolean apply(SWTBotGefEditPart input) {
                            return !((GraphicalEditPart) input.part()).getFigure().getBounds().contains(point);
                        }
                    }));
                    if (atLeastOneBendpointUnderAMovedNode && !((expectedEntry.getValue().getPoint(i).equals(actualBendPoints.getPoint(i + delta))))) {
                        // Ignore current expected entry;
                        atLeastOneBendpointIgnored = true;
                        delta = -1;
                    } else {
                        assertEquals("Bendpoint should not have moved", expectedEntry.getValue().getPoint(i), actualBendPoints.getPoint(i + delta));
                    }
                }
            }

            // Last bendpoint should move only if the target has moved
            if (firstOrLastBendPointShouldMove && movedNodes.contains(expectedEntry.getKey().target()) || allOtherBendpointsShouldMove) {
                if (!acceptNonMovedBendpoints && !allOtherBendpointsShouldMove) {
                    assertNotEquals("Last Bendpoint should have moved", expectedEntry.getValue().getLastPoint(), actualBendPoints.getLastPoint());
                }
            } else if (expectedEntry.getValue().size() > 2 && !atLeastOneBendpointIgnored) {
                // If there is only 2 bendpoints, the last bendpoint can also
                // moved (change of side).
                // If at least one actual bendpoints is ignored, the last
                // bendpoint can also moved (caused by the removed bendpoint).
                assertEquals("Last Bendpoint should not have moved", expectedEntry.getValue().getLastPoint(), actualBendPoints.getLastPoint());
            }
        }
    }

    // Get the new bendpoints list and compare it to expected ones when moving
    // edge extremity
    private void compareActualBendpointsWithExpected(SWTBotSiriusDiagramEditor diagramEditor, Map<SWTBotGefConnectionEditPart, PointList> expectedBendPoints, boolean firstBendPointShouldMove,
            boolean allOtherBendpointsShouldMove, boolean acceptNonMovedBendpoints) {
        // For Manhattan Diagrams not all bendpoints must move
        acceptNonMovedBendpoints = acceptNonMovedBendpoints || diagramEditor.getReference().getName().contains(ROUTING_STYLE_MANHATTAN);
        for (Entry<SWTBotGefConnectionEditPart, PointList> expectedEntry : expectedBendPoints.entrySet()) {
            SWTBotUtils.waitAllUiEvents();
            PointList actualBendPoints = ((PolylineConnectionEx) expectedEntry.getKey().part().getFigure()).getPoints();
            // First bendpoint should move only if the source extremity has
            // moved
            if (firstBendPointShouldMove && !acceptNonMovedBendpoints) {
                assertNotEquals("First Bendpoint should have moved", expectedEntry.getValue().getFirstPoint(), actualBendPoints.getFirstPoint());
            }
            for (int i = 1; i < expectedEntry.getValue().size() - 1; i++) {
                if (!allOtherBendpointsShouldMove) {
                    assertEquals("Bendpoint should not have moved", expectedEntry.getValue().getPoint(i), actualBendPoints.getPoint(i));
                }
            }
            // Last bendpoint should move only if the target extremity has moved
            if (!firstBendPointShouldMove && !acceptNonMovedBendpoints) {
                assertNotEquals("Last  Bendpoint should have moved", expectedEntry.getValue().getLastPoint(), actualBendPoints.getLastPoint());
            }
        }
    }

    /**
     * Creates scrollbar horizontally, vertically or both by dragging elements.
     * 
     * @param diagramEditor
     *            the editor in which scrollbar must be handled
     * @param horizontalScrollbar
     *            true if there should be an horizontal scrollbar
     * @param verticalScrollBar
     *            true if there should be a vertical scrollbar
     */
    private void handleScrollbar(SWTBotSiriusDiagramEditor diagramEditor, boolean horizontalScrollbar, boolean verticalScrollBar) {
        SWTBotGefEditPart editPartToMoveForCreatingScroll = diagramEditor.getEditPart("Scrollbar", IAbstractDiagramNodeEditPart.class);
        diagramEditor.select(editPartToMoveForCreatingScroll);
        Point initialLocation = diagramEditor.getBounds(editPartToMoveForCreatingScroll).getCenter();
        int horizontalShift = 0;
        int verticalShift = 0;
        if (horizontalScrollbar) {
            horizontalShift = 10000;
        }
        if (verticalScrollBar) {
            verticalShift = 10000;
        }
        diagramEditor.drag(initialLocation, new Point(initialLocation.x + horizontalShift, initialLocation.y + verticalShift));
        SWTBotUtils.waitAllUiEvents();
        diagramEditor.scrollTo(0, 0);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Returns all connections editParts coming from or to the given
     * nodeEditPart
     * 
     * @param nodeEditPart
     *            the editpart to test
     * @return all connections editParts coming from or to the given
     *         nodeEditPart
     */
    private SetView<SWTBotGefConnectionEditPart> getAllConnectionsEditPart(SWTBotGefEditPart nodeEditPart) {
        return Sets.union(Sets.newLinkedHashSet(nodeEditPart.sourceConnections()), Sets.newLinkedHashSet(nodeEditPart.targetConnections()));
    }
}
